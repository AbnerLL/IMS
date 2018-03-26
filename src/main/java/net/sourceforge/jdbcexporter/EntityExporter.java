package net.sourceforge.jdbcexporter;

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
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

import net.sourceforge.jdbcexporter.event.ExportEntityRowEvent;
import net.sourceforge.jdbcexporter.event.ExportListener;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DbColumnTranslator;
import net.sourceforge.jdbcimporter.MalformedDataException;
import net.sourceforge.jdbcimporter.event.ImportEntityRowEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The EntityExporter class exports an entity into the database
 * using a given export engine. It ensures that all the
 * columns for the entity have their SQL Types defined properly.
 * The data is formatted using the DelimiterFormatter defined by the EntityDef.
 * Each column value may be transformed by a ColumnTranslator 
 * before the current row is passed to the ExportEngine.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class EntityExporter
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( EntityExporter.class );
		
	/**
	 * The export engine.
	 */
	protected ExportEngine defaultEngine;
	
	/**
	 * The list of export listeners.
	 */
	protected List         exportListeners = new ArrayList();
		
	/**
	 * Sets the export engine.
	 * 
	 * @param newEngine export engine
	 */
	public void setExportEngine( ExportEngine newEngine )
	{
		defaultEngine = newEngine;
	}
	
	/**
	 * Process the given entity definition by ensuring that all
	 * columns defined have an SQL Type, applying any ColumnTranslator
	 * to each column value, formatting the rows
	 * with the appropriate DelimiterFormatter and using the 
	 * ExportEngine to export the row to a file.
	 * 
	 * @param entityDef entity definition to export.
	 */
	public void processEntity( ExportEntityDef entityDef ) throws SQLException, IOException
	{
		LOG.trace( "processEntity( <ExportEntityDef:"+entityDef.getTable()+"> ) ->");

		fillInColumnTypes( entityDef );
		setupDbColumnTranslators( entityDef );

		ExportEngine curEngine = defaultEngine;
		Connection connection = defaultEngine.getConnection();
		if ( entityDef.getExportEngine() != null )
		{
			curEngine = entityDef.getExportEngine();
			curEngine.setConnection( connection );
		}
		curEngine.setEntityDef(entityDef);
		curEngine.init();

		DelimiterFormatter delimiterFormatter = entityDef.getDelimiterFormatter();
		Writer writer = null;
		int numRows = 0;
		int numRowsBad = 0;
		int rowNum = 1;
		
		try
		{
			if ( entityDef.getTargetEncoding() != null )
			{
				writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( entityDef.getTarget() ), entityDef.getTargetEncoding() ) );
			}
			else{
				writer = new FileWriter(entityDef.getTarget(),true);
			}
			delimiterFormatter.setWriter(writer);
			ColumnValue[] values = curEngine.exportRow();
			String nextRow = null;
			while ( values != null )
			{
				numRows++;
				try
				{
					values = fillInValues( entityDef, values );
					nextRow = delimiterFormatter.formatValues(values);
					delimiterFormatter.writeNextRow(nextRow);
					fireExportEntityRowEvent( new ExportEntityRowEvent( this, entityDef, nextRow, values, ImportEntityRowEvent.STATE_SUCCESS, null ));
				}
				catch ( MalformedDataException male )
				{
					fireExportEntityRowEvent( new ExportEntityRowEvent( this, entityDef, nextRow, values, ImportEntityRowEvent.STATE_ERROR, male ));
					numRowsBad++;
				}
				values = curEngine.exportRow();			
				rowNum++;
			}
			
			delimiterFormatter.finish();
		}
		finally
		{
			if ( writer != null )
			{
				try
				{
					writer.flush();
					writer.close();	
				}
				catch (IOException ie )
				{
					LOG.warn( "Could not close writer for file '"+entityDef.getTarget().getAbsolutePath()+"'", ie );
				}	
			}
			
			curEngine.cleanup();
			cleanupDbColumnTranslators( entityDef );

			if ( entityDef.getExportEngine() != null )
			{
				// Make sure the import engine doesn't have access to the connection
				entityDef.getExportEngine().setConnection( null );
			}
		}
		LOG.trace( "processEntity( <ExportEntityDef:"+entityDef.getTable()+"> ) <-");
	}
	
	/**
	 * Adds an export listener.
	 * 
	 * @param listener the listener
	 */
	public void addExportListener( ExportListener listener )
	{
		if ( !exportListeners.contains( listener ) )
		{
			exportListeners.add( listener );
		}
	}
	
	/**
	 * Removes an export listener.
	 * 
	 * @param listener the listener
	 */	
	public void removeExportListener( ExportListener listener )
	{
		exportListeners.remove( listener );
	}
	
	/**
	 * Fill in the column SQL Types that are missing.
	 * 
	 * @param entityDef the entity definition
	 * @throws SQLException if the column SQL Types could not be retrieved from the
	 * database meta
	 */
	protected void fillInColumnTypes( ExportEntityDef entityDef ) throws SQLException
	{	
		LOG.trace( "fillInColumnTypes( <ExportEntityDef:"+entityDef.getTable()+"> )  ->");
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
//					throw new SQLException( "Column '"+def.getName()+"' not found!" );
					LOG.error("Found SQL Type '"+def.getType()+"' for column '" + def.getName() + "'");
					System.err.println("Found SQL Type '"+def.getType()+"' for column '" + def.getName() + "'");
					def.setType(12);
				}else{
					def.setType( ((Short) columnToTypes.get( def.getName().toLowerCase() ) ).intValue() );
				}
//				LOG.debug("Found SQL Type '"+def.getType()+"' for column '" + def.getName() + "'");
			}
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
		LOG.trace( "fillInColumnTypes( <ExportEntityDef:"+entityDef.getTable()+"> ) <-");
	}

	/**
	 * Returns the transformed column values.
	 * 
	 * @param entityDef the entity definition
	 * @param values the rows' values
	 * @return column values the rows' values with defaults and column transformers applied.
	 * @throws MalformedDataException if the rows' values are incorrect
	 */	
	protected ColumnValue[] fillInValues( ExportEntityDef entityDef, ColumnValue[] values ) throws MalformedDataException
	{
		LOG.trace( "fillInValues( <ExportEntityDef:"+entityDef.getTable()+">, <ColumnValue[]:"+values.length+"> ) ->");

		ColumnDef[] columns = entityDef.getColumns();
		if ( values.length != columns.length )
		{
			throw new MalformedDataException( "# of values not equal to # of columns ("+values.length+","+columns.length+")" );
		}
		
		for ( int i = 0; i < values.length; i++ )
		{
			ExportColumnDef columnDef = (ExportColumnDef) columns[i];
			if ( values[i] == null || "".equals( values[i].getString() ) )
			{
				if ( columnDef.getDefaultValue() != null )
				{
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
		LOG.trace( "fillInValues( <ExportEntityDef:"+entityDef.getTable()+">, <ColumnValue[]:"+values.length+"> )  <-");
		return values;	
	}
	
	/**
	 * Fire an export entity row event to the listeners.
	 * 
	 * @param e the export entity row event
	 */
	protected void fireExportEntityRowEvent( ExportEntityRowEvent e )	
	{
		Iterator i = exportListeners.iterator();
		while ( i.hasNext() )
		{
			((ExportListener) i.next()).rowProcessed(e);			
		}
	}

	/**
	 * Setup any DbColumnTranslators.
	 * 
	 * @param entityDef The export entityDef
	 */
	private void setupDbColumnTranslators( ExportEntityDef entityDef ) throws SQLException
	{
		ExportColumnDef[] columns = entityDef.getColumns();
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
	 * @param entityDef The export entityDef
	 */
	private void cleanupDbColumnTranslators( ExportEntityDef entityDef ) throws SQLException 
	{
		ExportColumnDef[] columns = entityDef.getColumns();
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
	 * @param metaData the database meta data
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
	 * @param metaData the database meta data
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
	 * @param metaData the database meta data
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
