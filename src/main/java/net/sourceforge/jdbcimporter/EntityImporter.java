package net.sourceforge.jdbcimporter;

/*
 * JDBC Importer - database import utility/framework.
 * Copyright (C) 2002 Chris Nagy
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 * Chris Nagy
 * Email:  cnagyxyz@hotmail.com
 */
 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sourceforge.datagenerator.ColumnValueGenerator;
import net.sourceforge.datagenerator.GenerateColumnDef;
import net.sourceforge.jdbcimporter.event.ImportEntityBatchEvent;
import net.sourceforge.jdbcimporter.event.ImportEntityRowEvent;
import net.sourceforge.jdbcimporter.event.ImportListener;
import net.sourceforge.jdbcimporter.parser.BinaryDelimiterParserDelegate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The EntityImporter class imports an entity into the database
 * using a given import engine. It ensures that all the
 * columns for the entity have their SQL Types defined properly.
 * The data is parsed using the DelimiterParser defined by the EntityDef.
 * Each column value may be transformed by a ColumnTranslator 
 * before the current row is passed to the ImportEngine.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class EntityImporter
{	
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( EntityImporter.class );

	/**
	 * The import engine that will be used.
	 */
	protected ImportEngine defaultEngine;
	
	/**
	 * The number of rows to import before calling executeBatch.
	 */
	protected int          batchCount = -1;
	
	/**
	 * The number of rows to import before calling commit on the connection.
	 */
	protected int          commitCount = -1;
	
	/**
	 * The flag indicating the JDBC driver supports batch updates.
	 */
	protected boolean      supportsBatch = false;

	/**
	 * The flag indicating that values should be trimmed after being 
	 * read by the delimiter parser.
	 */
	protected boolean      trimValues = false;
	
	/**
	 * The list of import listeners.
	 */
	protected List         importListeners = new ArrayList();

	/**
	 * Flag indicating that the import should stop on the first error (default is false).
	 */
	protected boolean      failOnError = false;
	
	/**
	 * Random number generator used for columns that will be generated.
	 */
	protected Random       random = new Random();
	
	/**
	 * Sets the import engine.
	 * 
	 * @param newEngine import engine
	 */
	public void setImportEngine( ImportEngine newEngine )
	{
		defaultEngine = newEngine;
	}
	
	/**
	 * Sets the number of records to import before executing a batch update.
	 * 
	 * @param count the number of records
	 */
	public void setBatchCount( int count )
	{
		this.batchCount = count;
	}
	
	/**
	 * Sets the number of records to import before committing.
	 * 
	 * @param count the number of records
	 */
	public void setCommitCount( int count )
	{
		this.commitCount = count;
	}
	
	/**
	 * Sets a flag to indicate whether the connection supports
	 * batch updates.
	 * 
	 * @param flag whether the connection supports batch update or not
	 */
	public void setSupportsBatch( boolean flag )
	{
		this.supportsBatch = flag;
	}

	/**
	 * Sets a flag to indicate that values should be trimmed after
	 * being read by the delimiter parser
	 * @param flag flag
	 */
	public void setTrimValues( boolean flag)
	{
		this.trimValues =  flag;
	}

	/**
	 * Sets a flag to indicate that the import should stop after the
	 * first error.
	 * 
	 * @param flag flag
	 * @since 0.69
	 */
	public void setFailonerror( boolean flag )
	{
		this.failOnError = flag;
	}
	
	/**
	 * Process the given entity definition by ensuring that all
	 * columns defined have an SQL Type, parsing the file
	 * with the appropriate DelimiterParser, applying any ColumnTranslator
	 * to each column value and using the ImportEngine to
	 * import the row.
	 * 
	 * @param entityDef entity definition to import
	 */
	public void processEntity( ImportEntityDef entityDef ) throws SQLException, IOException
	{
		LOG.trace( "processEntity( <ImportEntityDef:"+entityDef.getTable()+"> ) ->");
		
		long startTime = System.currentTimeMillis();
		EntityImporterState state = new EntityImporterState();
		state.currentRow = 0;
		state.entityDef  = entityDef;
		
		fillInColumnTypes( entityDef );
		fillInGeneratedColumns( state );
		setupDbColumnTranslators( entityDef );
		ImportEngine curEngine = defaultEngine;
		Connection connection = defaultEngine.getConnection();
		if ( entityDef.getImportEngine() != null )
		{
			curEngine = entityDef.getImportEngine();
			curEngine.setConnection( connection );
		}
		
		boolean autoCommit = connection.getAutoCommit();
		if ( commitCount < batchCount )
		{
			commitCount = batchCount;
		}
						
		curEngine.setEntityDef(entityDef);
		curEngine.init();
		state.engine = curEngine;
		
		BinaryDelimiterParser delimiterParser = entityDef.getBinaryDelimiterParser();
		if ( delimiterParser == null )
		{
			delimiterParser = new BinaryDelimiterParserDelegate( 
					entityDef.getDelimiterParser(),
					entityDef.getSourceEncoding() );
		}
		state.delimiterParser = delimiterParser;
		InputStream in = null;

		int numRows = 0;
		int numRowsBad = 0;
		int rowNum = 1;
		List batchRows = new ArrayList();
		state.batchRows = batchRows;
		try
		{
			in = new FileInputStream( entityDef.getSource() );
			if ( entityDef.getNumBytesToSkip() > 0 )
			{
				in.skip(entityDef.getNumBytesToSkip());
			}
			delimiterParser.setInputStream(in);
			ColumnValue[] values = null;
			Object nextRow = delimiterParser.getNextRow();
			while ( nextRow != null )
			{
				numRows++;
				if ( LOG.isDebugEnabled() )
				{
					LOG.debug("Row #"+numRows+" = '"+delimiterParser.getRowAsString(nextRow)+"'");
				}
				state.currentRow = numRows;
				boolean successFlag = importNextRow( state, nextRow ); 
				if ( !successFlag )
				{
					numRowsBad++;
				}

				if ( numRows % batchCount == 0 && !autoCommit && supportsBatch)
				{
					try
					{
						curEngine.executeBatch();
						int[] rows = new int[ batchRows.size() ];
						for ( int i = 0; i < rows.length; i++ )
						{
							rows[i] = 1;
						}
						fireImportEntityBatchEvent( new ImportEntityBatchEvent( this, entityDef, rows, new int[0], null ) );
					}
					catch ( BatchUpdateException bue )
					{
						numRowsBad += processBatchUpdateException( entityDef, batchRows, bue );
					}
					batchRows.clear();
				}					
								
				if ( numRows % commitCount == 0 && !autoCommit )
				{
					connection.commit();
				}
				
				if ( !successFlag && failOnError )
				{
					throw new SQLException("JDBCImporter failed to import row");
				}
				nextRow = delimiterParser.getNextRow();
				rowNum++;
			}
		}
		finally
		{
			if ( in != null )
			{
				try
				{
					in.close();	
				}
				catch (IOException ie )
				{
					LOG.warn( "Could not close reader for file '"+entityDef.getSource()+"'", ie );
				}	
			}
			
			try
			{
				if ( numRows % batchCount != 0 && !autoCommit && supportsBatch ) curEngine.executeBatch();
			}
			catch ( BatchUpdateException bue )
			{
				numRowsBad += processBatchUpdateException( entityDef, batchRows, bue );
			}
			batchRows.clear();
			
			curEngine.cleanup();
			
			try
			{
				if ( !autoCommit ) connection.commit();
			}
			catch ( SQLException e )
			{
				LOG.error( "SQLException while trying to commit connection", e );
			}
			
			if ( entityDef.getImportEngine() != null )
			{
				// Make sure the import engine doesn't have access to the connection
				entityDef.getImportEngine().setConnection( null );
			}
		
			cleanupDbColumnTranslators( entityDef );
		}
		LOG.trace( "processEntity( <ImportEntityDef:"+entityDef.getTable()+"> ) <-");
	}

	/**
	 * Adds an import listener.
	 * 
	 * @param listener the listener
	 */
	public void addImportListener( ImportListener listener )
	{
		if ( !importListeners.contains( listener ) )
		{
			importListeners.add( listener );
		}
	}
	
	/**
	 * Removes an import listener.
	 * 
	 * @param listener the listener
	 */	
	public void removeImportListener( ImportListener listener )
	{
		importListeners.remove( listener );
	}
	
	/**
	 * Fill in the column types.
	 * 
	 * @param entityDef the entity definition
	 * @throws SQLException if the column types could not be read from the database meta
	 */
	protected void fillInColumnTypes( ImportEntityDef entityDef ) throws SQLException
	{	
		LOG.trace( "fillInColumnTypes( <ImportEntityDef:"+entityDef.getTable()+"> )  ->");
		DatabaseMetaData metaData = null;
		ColumnDef[] columns = entityDef.getColumns();
		
		Map columnToTypes = new HashMap();
		for ( int i = 0; i < columns.length; i++ )
		{
			ColumnDef def = columns[i];
			if ( def.getType() == ColumnDef.UNKNOWN_SQL_TYPE )
			{
				LOG.debug("Looking up SQL Type for column '"+def.getName()+"'");
				if ( metaData == null )
				{
					metaData = defaultEngine.getConnection().getMetaData();	
					String catalog = null;
					String schema  = null;
					if ( entityDef.getCatalog() != null )
					{
						catalog = resolveCatalogName( metaData, entityDef.getCatalog() );	
					}					
					if ( entityDef.getSchema() != null )
					{
						schema = resolveSchemaName( metaData, catalog, entityDef.getSchema() );
					}
					
					String table = resolveTableName( metaData, catalog, schema, entityDef.getTable() );
					
					ResultSet rset = metaData.getColumns( catalog, schema, table, null );
					while (rset.next())
					{
						columnToTypes.put( rset.getString("COLUMN_NAME").toLowerCase(),
								           new Short(rset.getShort("DATA_TYPE") ) );
					}
					rset.close();
				}
				
				if ( !columnToTypes.containsKey( def.getName().toLowerCase() ) )
				{
					throw new SQLException( "Column '"+def.getName()+"' not found!" );	
				}
				def.setType( ((Short) columnToTypes.get( def.getName().toLowerCase() ) ).intValue() );
				LOG.debug("Found SQL Type '"+def.getType()+"' for column '" + def.getName() + "'");			}
			else
			{
				LOG.debug("Predefined SQL Type '"+def.getType()+"' for column '" + def.getName() + "'");
			}
			
			// TODO add format checking here
			String formatPattern = def.getFormatPattern();
			if ( formatPattern == null )
			{
				switch ( def.getType() )
				{
					case Types.DATE:
						formatPattern = ColumnDef.DATE_FORMAT;
						break;
					case Types.TIME:
						formatPattern = ColumnDef.TIME_FORMAT;
						break;
					case Types.TIMESTAMP:
						formatPattern = ColumnDef.TIMESTAMP_FORMAT;
						break;
				}
				LOG.debug("Using format '"+formatPattern+"' for column '"+def.getName()+"'");
				def.setFormatPattern(formatPattern);
			}
		}	
		LOG.trace( "fillInColumnTypes( <ImportEntityDef:"+entityDef.getTable()+"> ) <-");
	}

	/**
	 * Setup the column value generators for any columns whose values will be generated.
	 * 
	 * @param state the entity import state
	 * @throws IOException if the column value generator dependencies were not found.
	 */
	private void fillInGeneratedColumns(EntityImporterState state) throws IOException
	{
		if ( !state.entityDef.hasColumnsToGenerate() )
			return;
		
		state.numGeneratedColumns = 0;
		ImportColumnDef[] columns = state.entityDef.getColumns();
		int pos = 0;
		for ( Iterator iter = state.entityDef.getColumnIterator(); iter.hasNext(); )
		{
			ColumnDef nextColumnDef = (ColumnDef) iter.next();
			if ( nextColumnDef instanceof GenerateColumnDef )
			{
				nextColumnDef.setType( columns[pos].getType() );
				GenerateColumnDef nextGenerateColumnDef = (GenerateColumnDef) nextColumnDef;
				ColumnValueGenerator generator = nextGenerateColumnDef.getColumnValueGenerator();
				String[] dependencies = generator.getDependencies();
				for ( int i = 0; i < dependencies.length; i++ )
				{
					if ( dependencies[i].indexOf(".") != -1 )
					{
						throw new IOException("Invalid dependency '"+dependencies[i]+"' for generated column "+
							"'"+nextColumnDef.getName()+"', only simple dependencies allowed");
					}
					if ( state.columnToIndexMap == null )
					{
						state.columnToIndexMap = new HashMap();
						ImportColumnDef[] columnDefs = state.entityDef.getColumns();
						for ( int j = 0; j < columnDefs.length; j++)
						{
							state.columnToIndexMap.put( columnDefs[j].getName(), new Integer(j) );
						}
					}
					if ( !state.columnToIndexMap.containsKey( dependencies[i] ) )
					{
						throw new IOException("Invalid dependency '"+dependencies[i]+"' for generated column "+
								"'"+nextColumnDef.getName()+"', could not find column");
					}
				}
				state.numGeneratedColumns++;
			}
			pos++;
		}
	}
	
	/**
	 * Import the next row.
	 * 
	 * @param state the entity import state
	 * @param nextRow the string representing the row
	 * @return flag indicating that the row was imported successfully
	 */
	protected boolean importNextRow( EntityImporterState state, Object nextRow )
	{
		ImportEngine curEngine = state.engine;
		ImportEntityDef entityDef = state.entityDef; 
		BinaryDelimiterParser delimiterParser = state.delimiterParser;
		RowTranslator   rowTranslator = entityDef.getRowTranslator();
		List batchRows = state.batchRows;
		
		ColumnValue[] values = null;
		String nextRowDebug = delimiterParser.getRowAsString(nextRow);
		try
		{
			values = delimiterParser.getValues(nextRow);
			if ( trimValues )
			{
				for ( int i = 0; i < values.length; i++ )
				{
					if ( values[i].isString() && values[i].getString() != null )
					{
						values[i].setString( values[i].getString().trim() );
					}
				}
			}
			
			if ( LOG.isDebugEnabled() ) 
				LOG.debug( "Parsed Column Values = ["+columnValuesToString( values ) +"]");
			
			if ( rowTranslator != null )
			{
				ColumnValue[] origValues = values;
				values = rowTranslator.getValues(entityDef, values);
				if ( LOG.isDebugEnabled() )
				{
					if ( values == null )
					{
						LOG.debug("Row Translated Column Values = null");
					}
					else
					{
						LOG.debug("Row Translated Column Values = ["+columnValuesToString(values)+"]");
					}
				}
				
				if ( values == null )
				{
					LOG.debug("Row Translator return null");
					fireImportEntityRowEvent( new ImportEntityRowEvent( this, entityDef, 
						nextRowDebug, origValues, ImportEntityRowEvent.STATE_ERROR, null ));
					return false;
				}
			}
			
			values = fillInValues( state, values );
			if ( LOG.isDebugEnabled() )
				LOG.debug( "Filled Column Values = ["+columnValuesToString( values ) + "]" );
						
			curEngine.importRow(values);
			if ( LOG.isDebugEnabled() )
				LOG.debug( "Imported Column Values = ["+columnValuesToString( values ) + "]" );
						
			if ( batchCount > 1 && supportsBatch )
			{
				batchRows.add( nextRow );
				fireImportEntityRowEvent( new ImportEntityRowEvent( this, entityDef, 
						nextRowDebug, values, ImportEntityRowEvent.STATE_BATCH, null ));
			}
			else
			{
				fireImportEntityRowEvent( new ImportEntityRowEvent( this, entityDef, 
						nextRowDebug, values, ImportEntityRowEvent.STATE_SUCCESS, null ));
			}
			return true;
		}
		catch ( SQLException s )
		{
			LOG.debug("SQLException caught while trying to import row", s);
			fireImportEntityRowEvent( new ImportEntityRowEvent( this, entityDef,
					nextRowDebug, values, ImportEntityRowEvent.STATE_ERROR, s ));
			return false;
		}
		catch ( MalformedDataException male )
		{
			LOG.debug("MalformedDataException caught while trying to import row", male);
			male.setRow( nextRowDebug );
			fireImportEntityRowEvent( new ImportEntityRowEvent( this, entityDef,
					nextRowDebug, values, ImportEntityRowEvent.STATE_ERROR, male ));
			return false;
		}
	}
	
	/**
	 * Fill in the column values with default values, if necessary, and call 
	 * column translators for the values.
	 * 
	 * @param state the entity import state
	 * @param values the column values
	 * @return the values with default values and column translators applied
	 * @throws MalformedDataException if the number of column values is invalid
	 */
	protected ColumnValue[] fillInValues( EntityImporterState state, ColumnValue[] values ) throws MalformedDataException
	{
		ImportEntityDef entityDef = state.entityDef;
		LOG.trace( "fillInValues( <ImportEntityDef:"+entityDef.getTable()+">, <ColumnValue[]:"+values.length+"> ) ->");

	
		ColumnDef[] columns = entityDef.getColumns();
		if ( values.length + state.numGeneratedColumns != columns.length )
		{
			StringBuffer buf = new StringBuffer("");
			for ( int i = 0; i < values.length; i++ )
			{
				buf.append( "\nValue #"+i+" = ");
				if ( values[i].isString() )
				{
					buf.append(values[i].getString());
				}
				else if ( values[i].isBytes() )
				{
					buf.append("<bytes:"+values[i].getBytes().length+">");
				}
				else if ( values[i].isInputStream() )
				{
					buf.append("<InputStream:"+values[i].getInputStreamLength()+">");
				}
			}
			throw new MalformedDataException( "# of values not equal to # of columns "+
					"("+(values.length+state.numGeneratedColumns)+","+columns.length+") "+buf.toString() );
		}

		if ( entityDef.hasColumnsToGenerate() )
		{
			ColumnValue[] newValues = new ColumnValue[ entityDef.getColumnCount() ];
			int oldValuesIndex = 0;
			int newValuesIndex = 0;
			for ( Iterator iter = entityDef.getColumnIterator(); iter.hasNext(); )
			{
				ColumnDef nextColumn = (ColumnDef) iter.next();
				if ( nextColumn instanceof ImportColumnDef )
				{
					newValues[newValuesIndex] = values[oldValuesIndex];
					oldValuesIndex++;
				}
				else
				{
					newValues[newValuesIndex] = new ColumnValue();
				}
				newValuesIndex++;
			}			
			values = newValues;
		}
		
		for ( int i = 0; i < values.length; i++ )
		{
			ImportColumnDef columnDef = (ImportColumnDef) columns[i];
			if ( values[i] == null || "".equals( values[i].getString() ) )
			{
				if ( columnDef.getDefaultValue() != null )
				{
					LOG.debug("Setting default value '"+columnDef.getDefaultValue()+"' for column '"+columnDef.getName()+"'");
					values[i].setString( columnDef.getDefaultValue() );
				}
			}
			ColumnTranslator translator = columnDef.getTranslator();
			if ( translator != null )
			{
				if ( LOG.isDebugEnabled() )
					LOG.debug("Value before translator '"+values[i]+"' for column '"+columnDef.getName()+"'" );
					
				values[i] = translator.getValue(columnDef, values[i] );
				
				if ( LOG.isDebugEnabled() )
					LOG.debug("Value after translator '"+values[i]+"' for column '"+columnDef.getName()+"'" );
			}
		}
		
		if ( entityDef.hasColumnsToGenerate() )
		{
			int index = 0;
			for ( Iterator iter = entityDef.getColumnIterator(); iter.hasNext(); )
			{
				ColumnDef nextColumn = (ColumnDef) iter.next();
				if ( nextColumn instanceof GenerateColumnDef )
				{
					GenerateColumnDef nextGenerateColumn = (GenerateColumnDef) nextColumn;
					ColumnValueGenerator generator = nextGenerateColumn.getColumnValueGenerator();
					String[] dependencies = generator.getDependencies();
					for ( int k = 0; k < dependencies.length; k++ )
					{
						int colIndex = ((Integer) state.columnToIndexMap.get( dependencies[k] ) ).intValue();
						generator.setDependentValue(dependencies[k],values[colIndex].getString());
					}
					values[index] = generator.getNextColumnValue( state.currentRow, random );
				}
				index++;
			}
		}
		
		LOG.trace( "fillInValues( <ImportEntityDef:"+entityDef.getTable()+">, <ColumnValue[]:"+values.length+"> )  <-");
		return values;	
	}
	
	/**
	 * Process batch update exception.
	 * 
	 * @param entityDef the entity definition
	 * @param batchRows the rows processed during this batch
	 * @param bue the batch update exception
	 * @return number of bad rows
	 */
	protected int processBatchUpdateException( ImportEntityDef entityDef, List batchRows, BatchUpdateException bue )
	{
		int[] rowStatus = bue.getUpdateCounts();
		List goodRowList = new ArrayList();
		List badRowList  = new ArrayList();
		int numRowsBad = 0;	
		for ( int i = 0; i < batchRows.size(); i++ )
		{
			if ( i < rowStatus.length )
			{
				if ( rowStatus[i] == 1 || rowStatus[i] == ImportEngine.BATCH_SUCCESS_ROWS_UNKNOWN )
				{
					goodRowList.add( new Integer(i) );
				}
				else
				{
					badRowList.add( new Integer(i) );
					numRowsBad++;								
				}
			}
			else
			{
				badRowList.add( new Integer(i) );
				numRowsBad++;								
			}
		}
		int[] goodRows = new int[goodRowList.size()];
		for ( int i = 0; i < goodRowList.size(); i++ )
		{
			goodRows[i] = ((Integer) goodRowList.get(i)).intValue();
		}
		int[] badRows = new int[badRowList.size()];
		for ( int i = 0; i < badRowList.size(); i++ )
		{
			badRows[i] = ((Integer) badRowList.get(i)).intValue();
		}
		fireImportEntityBatchEvent( new ImportEntityBatchEvent( this, entityDef, goodRows, badRows, bue ) );
		return numRowsBad;
	}
	
	/**
	 * Fire an import entity row event to the listeners.
	 * 
	 * @param e the import entity row event
	 */
	protected void fireImportEntityRowEvent( ImportEntityRowEvent e )	
	{
		Iterator i = importListeners.iterator();
		while ( i.hasNext() )
		{
			((ImportListener) i.next()).rowProcessed(e);			
		}
	}
	
	/**
	 * Fire an import entity batch event to the listeners.
	 * 
	 * @param e the import batch event
	 */
	protected void fireImportEntityBatchEvent( ImportEntityBatchEvent e )
	{
		Iterator i = importListeners.iterator();
		while ( i.hasNext() )
		{
			((ImportListener) i.next()).batchProcessed(e);			
		}
	}
	
	/**
	 * Convert the column values into a string representation for logging purposes.
	 * 
	 * @param values the column values
	 * @return A comma-separated list of values
	 */
	protected String columnValuesToString( ColumnValue[] values )
	{
		StringBuffer buf = new StringBuffer("");
		for ( int i = 0; i < values.length; i++ )
		{
			if ( i > 0 )
			{
				buf.append( ", " );
			}
			buf.append( values[i] );
		}
		return buf.toString();		
	}

	/**
	 * Setup any DbColumnTranslators.
	 * 
	 * @param entityDef The import entityDef
	 */
	private void setupDbColumnTranslators( ImportEntityDef entityDef ) throws SQLException
	{
		ImportColumnDef[] columns = entityDef.getColumns();
		for ( int i = 0; i < columns.length; i++ )
		{
			ColumnTranslator translator = columns[i].getTranslator();
			if ( translator != null && translator instanceof DbColumnTranslator )
			{
				((DbColumnTranslator) translator).setup( this.defaultEngine.getConnection() );
			}
		}
	}
	
	/**
	 * Cleanup any DbColumnTranslators.
	 * 
	 * @param entityDef The import entityDef
	 */
	private void cleanupDbColumnTranslators( ImportEntityDef entityDef ) throws SQLException 
	{
		ImportColumnDef[] columns = entityDef.getColumns();
		for ( int i = 0; i < columns.length; i++ )
		{
			ColumnTranslator translator = columns[i].getTranslator();
			if ( translator != null && translator instanceof DbColumnTranslator )
			{
				((DbColumnTranslator) translator).cleanup();
			}
		}
	}

	/**
	 * Make sure the catalog name exists in the database and return the
	 * correct case.
	 * 
	 * @param meta the database meta data
	 * @param catalog the catalog name
	 * @return the catalog name with the correct case
	 */
	private String resolveCatalogName( DatabaseMetaData metaData, String catalog ) throws SQLException
	{
		ResultSet resultSet = metaData.getCatalogs();
		String resolvedCatalog = null;
		while (resultSet.next() )
		{
			String nextCatalog = resultSet.getString("TABLE_CAT");
			if ( nextCatalog.equalsIgnoreCase( catalog ) )
			{
				resolvedCatalog = nextCatalog;
				break;
			}
		}
		resultSet.close();
		if ( resolvedCatalog == null )
		{
			throw new SQLException("Could not find catalog '"+catalog+"' in DatabaseMetaData ");
		}
		return resolvedCatalog;
	}
	
	/**
	 * Make sure the schema name exists in the database and return the
	 * correct case.
	 * 
	 * @param meta the database meta data
	 * @param catalog the catalog name
	 * @param schema the schema name
	 * @return the schema name with the correct case
	 */
	private String resolveSchemaName( DatabaseMetaData metaData, String catalog, String schema ) throws SQLException
	{
		ResultSet resultSet = metaData.getSchemas();
		String resolvedSchema = null;
		ResultSetMetaData rsetMetaData = resultSet.getMetaData();
		while (resultSet.next() )
		{
			String nextCatalog = null;
			if ( rsetMetaData.getColumnCount() > 1 ) // it does have the catalog
			{
				nextCatalog = resultSet.getString("TABLE_CATALOG");
			}
			String nextSchema  = resultSet.getString("TABLE_SCHEM");
			if ( ( (nextCatalog == null && catalog == null ) ||  nextCatalog.equals( catalog ) ) &&
				 nextSchema.equalsIgnoreCase( schema ) )
			{
				resolvedSchema = nextSchema;
				break;
			}
		}
		resultSet.close();
		if ( resolvedSchema == null )
		{
			throw new SQLException("Could not find schema '"+schema+"' of catalog '"+catalog+"' in DatabaseMetaData ");
		}
		return resolvedSchema;		
	}
	
	/**
	 * Make sure the table name exists in the database and return the
	 * correct case.
	 * 
	 * @param meta the database meta data
	 * @param catalog the catalog name
	 * @param schema the schema name
	 * @param table the table name
	 * @return the table name with the correct case
	 */
	private String resolveTableName( DatabaseMetaData metaData, String catalog, String schema, String table ) throws SQLException
	{
		ResultSet resultSet = metaData.getTables( catalog, schema, table, null );
		String resolvedTable = null;
		if ( resultSet.next())
		{
			resolvedTable = table;
		}
		resultSet.close();
		
		if ( resolvedTable == null )
		{
			resultSet = metaData.getTables( catalog, schema, table.toLowerCase(), null );
			if ( resultSet.next())
			{
				resolvedTable = table.toLowerCase();
			}
			resultSet.close();
		}
		
		if ( resolvedTable == null )
		{
			resultSet = metaData.getTables( catalog, schema, table.toUpperCase(), null );
			if ( resultSet.next())
			{
				resolvedTable = table.toUpperCase();
			}
			resultSet.close();
		}

		if ( resolvedTable == null )
		{
			throw new SQLException("Could not find table '"+table+"'"+
				" of schema '"+schema+"' of catalog '"+catalog+"' in DatabaseMetaData ");
		}
		
		return resolvedTable;
	}

}

class EntityImporterState
{
	int currentRow;
	
	ImportEntityDef entityDef;
	ImportEngine    engine;
	BinaryDelimiterParser delimiterParser;
	List            batchRows;
	
	Map columnToIndexMap    = null;
	int numGeneratedColumns = 0;
}