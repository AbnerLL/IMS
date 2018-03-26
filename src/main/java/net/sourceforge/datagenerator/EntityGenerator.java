package net.sourceforge.datagenerator;

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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import java.util.Random;

import net.sourceforge.datagenerator.event.DataGeneratorEntityEvent;
import net.sourceforge.datagenerator.event.DataGeneratorListener;
import net.sourceforge.datagenerator.event.DataGeneratorRowEvent;
import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcexporter.ExportColumnDef;
import net.sourceforge.jdbcexporter.ExportEngine;
import net.sourceforge.jdbcexporter.ExportEntityDef;
import net.sourceforge.jdbcexporter.engine.BasicEngine;
import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DbColumnTranslator;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.ImportColumnDef;
import net.sourceforge.jdbcimporter.ImportEntityDef;
import net.sourceforge.jdbcimporter.MalformedDataException;
import net.sourceforge.jdbcimporter.parser.BinaryDelimiterParserDelegate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The EntityGenerator generates data for all the given entities.
 * It manages the dependencies between each entity.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class EntityGenerator 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( DataGenerator.class );
	
	/**
	 * The database connection.
	 */
	Connection con;
	
	/**
	 * The values needed by columns before the columns' values are
	 * generated. 
	 */
	Map dependencyValues = new HashMap();
	
	/**
	 * The state of all entities' generation of data mapped by entity name.
	 */
	Map entityStateMap = new HashMap();
	
	/**
	 * The state of all entities' generation of data.
	 */
	List entityStateList = new ArrayList();
	
	/**
	 * The map of values.
	 */
	Map entityValues   = new HashMap();
	
	/**
	 * The flag to use disk cache for incomplete data generated.
	 */
	boolean useDiskCache = true;
	
	/**
	 * The current entities' generation of data state.
	 */
	GenerateEntityState currentEntity;
	
	/**
	 * The source entities used to fill dependent column values.
	 */
	EntityDef[] sourceEntities;
	
	/**
	 * The source entities' row counts.
	 */
	int[]       sourceEntitiesRowCount;
	
	/**
	 * The event listeners.
	 */
	List dataGeneratorListeners = new ArrayList();
		
	/**
	 * Set the connection.
	 * 
	 * @param con the connection
	 */
	public void setConnection( Connection con )
	{
		this.con = con;
	}
	
	/**
	 * Sets the source entities whose data will be used to fill 
	 * dependent column values.
	 * 
	 * @param entities the list of entities
	 */
	public void setSourceEntities( EntityDef[] entities )
	{
		this.sourceEntities = entities;
		if ( this.sourceEntities == null )
		{
			this.sourceEntitiesRowCount = null;
		}
		else
		{
			this.sourceEntitiesRowCount = new int[ this.sourceEntities.length ];
			for ( int i = 0; i < this.sourceEntities.length; i++ )
			{
				this.sourceEntitiesRowCount[i] = -1;
			}
		}
	}
	
	/**
	 * Build the dependency map between entities.
	 * 
	 * @param entities the entity definitions
	 */
	public void buildDependencyMap( GenerateEntityDef[] entities )
	{
		LOG.trace("buildDependencyMap( <GenerateEntityDef["+entities.length+"]> ) ->" );
		for ( int i = 0; i < entities.length; i++ )
		{
			GenerateEntityState entityState = new GenerateEntityState( entities[i], i );
			entityStateMap.put( entityState.getFullEntityName(), entityState );
			entityStateList.add( entityState.getFullEntityName() );
			try
			{
				fillInColumnTypes( entities[i] );
			}
			catch ( SQLException e )
			{
				LOG.error( "Unable to fill in column types", e );
			}
			if ( !useDiskCache )
			{
				LOG.debug("Allocating String["+entities[i].getCount()+"]["+entities[i].getColumns().length+"]");
				String[][] vals = new String[entities[i].getCount()][entities[i].getColumns().length];
				entityValues.put( entityState.getFullEntityName(), vals );
			}
		}

		if ( this.sourceEntities != null )
		{
			for ( int i = 0; i < sourceEntities.length; i++ )
			{
				GenerateEntityState entityState = new GenerateEntityState( sourceEntities[i], -1 );
				entityStateMap.put( entityState.getFullEntityName(), entityState );
				try
				{
					fillInColumnTypes( sourceEntities[i] );
				}
				catch ( SQLException e )
				{
					LOG.error( "Unable to fill in column types", e );
				}
			}
		}
		
		Object[] keys = entityStateList.toArray();
		for ( int i = 0; i < keys.length; i++ )
		{
			GenerateEntityState entityState = (GenerateEntityState) entityStateMap.get( keys[i] );
			if ( !(entityState.getEntityDef() instanceof GenerateEntityDef ) )
			{
				continue;
			}
			
			GenerateColumnState[] columnStates = entityState.getColumns();
			for ( int j = 0; j < columnStates.length; j++ )
			{
				GenerateColumnState columnState = columnStates[j];
				
				GenerateColumnDef columnDef = (GenerateColumnDef) columnState.getColumnDef();
				ColumnValueGenerator generator = columnDef.getColumnValueGenerator();
				String[] dependencies = generator.getDependencies();
				GenerateColumnState[] dependentColumns = new GenerateColumnState[dependencies.length];
				for ( int k = 0; k < dependentColumns.length; k++ )
				{
					LOG.debug("Find column '"+dependencies[k]+"'");
					dependentColumns[k] = findColumn( entityState, dependencies[k] );
					GenerateColumnState[] reverseDependentColumns = dependentColumns[k].getReverseDependencies();
					GenerateColumnState[] newColumns = new GenerateColumnState[reverseDependentColumns.length+1];
					System.arraycopy(reverseDependentColumns,0,newColumns,0,reverseDependentColumns.length);
					LOG.debug("Adding column '"+columnState.getFullColumnName()+
							"' to reverse dependency list of '"+dependentColumns[k].getFullColumnName() );
					newColumns[newColumns.length-1] = columnState;
					dependentColumns[k].setReverseDependencies(newColumns);					
				}
				columnState.setDependencies(dependentColumns);
			}
		}		
		LOG.trace("buildDependencyMap( <"+entities.length+"> ) <-" );
	}
	
	/**
	 * Find the state of the column's generation of data.
	 *  
	 * @param entityState the entity state that is requesting the state
	 * @param columnName the name of the column (could be fully qualified or local to the given entity state) 
	 * @return the state of the column's generator of data.
	 */
	protected GenerateColumnState findColumn( GenerateEntityState entityState, String columnName )
	{
		if ( columnName.indexOf(".") == -1 )
		{
			return entityState.getColumn( columnName );
		}
		else
		{
			GenerateEntityState targetEntityState = (GenerateEntityState) entityStateMap.get( columnName.substring(0,columnName.lastIndexOf(".") ) );
			return targetEntityState.getColumn( columnName.substring( columnName.lastIndexOf(".") + 1) );
		}
	}
	
	/**
	 * Process the given entity definitions.
	 * 
	 * @param entities the entity definitions
	 */
	public void processEntities( GenerateEntityDef[] entities )
	{
		LOG.trace( "processEntities( <GenerateEntityDef["+entities.length+"] ) ->");
		buildDependencyMap( entities );
		Iterator keyIterator = entityStateList.iterator();
		while ( keyIterator.hasNext() )
		{
			currentEntity = (GenerateEntityState) entityStateMap.get( keyIterator.next() );
			processEntity( currentEntity, false );
		}
		
		if ( this.sourceEntities != null )
		{
			for ( int i = 0; i < sourceEntities.length; i++ )
			{
				GenerateEntityState entityState = new GenerateEntityState( sourceEntities[i], -1 );
				for ( int j = 0; j < sourceEntitiesRowCount[i]; j++ )
				{
					this.deleteRowFromDisk( entityState.getFullEntityName(), j );
				}
			}
		}		
		LOG.trace( "processEntities( <GenerateEntityDef["+entities.length+"] ) <-");
	}

	/**
	 * Adds a data generator listener.
	 * 
	 * @param listener the listener
	 */
	public void addDataGeneratorListener( DataGeneratorListener listener )
	{
		if ( !dataGeneratorListeners.contains( listener ) )
		{
			dataGeneratorListeners.add( listener );
		}
	}
	
	/**
	 * Removes a data generator listener.
	 * 
	 * @param listener the listener
	 */	
	public void removeDataGeneratorListener( DataGeneratorListener listener )
	{
		dataGeneratorListeners.remove( listener );
	}
	
	/**
	 * Fire a data generator row event to the listeners.
	 * 
	 * @param e the data generator row event
	 */
	protected void fireDataGeneratorRowEvent( DataGeneratorRowEvent e )	
	{
		Iterator i = dataGeneratorListeners.iterator();
		while ( i.hasNext() )
		{
			((DataGeneratorListener) i.next()).rowGenerated(e);			
		}
	}

	/**
	 * Fire a data generator entity event to the listeners.
	 * 
	 * @param e the data generator entity event
	 */
	protected void fireDataGeneratorEntityEvent( DataGeneratorEntityEvent e )	
	{
		Iterator i = dataGeneratorListeners.iterator();
		while ( i.hasNext() )
		{
			((DataGeneratorListener) i.next()).entityDataGeneratorFinished(e);
		}
	}
	
	/**
	 * Process the current entity state by generating data for the columns. 
	 * 
	 * @param entityState the current entity state
	 * @param readFromTmp whether to read the already generated column values from a
	 * tmp file.
	 */
	protected void processEntity( GenerateEntityState entityState, boolean readFromTmp )
	{
		LOG.trace( "processEntity( <GenerateEntityState:"+entityState.getFullEntityName()+">,"+
				    " "+readFromTmp+") ->");
		GenerateEntityDef entityDef = (GenerateEntityDef) entityState.getEntityDef();
		int[] positions = entityState.getPositions();
		GenerateColumnState[] columnStates = entityState.getColumns();			
		ColumnValue[] values = new ColumnValue[ columnStates.length ];
		for ( int i = 0; i < values.length; i++ )
		{
			values[i] = new ColumnValue();
		}
		Random r = new Random();
		boolean[] columnGenerated = new boolean[ columnStates.length ];
		boolean   entityGenerated = true;
		List reverseEntities = new ArrayList();

		DelimiterFormatter formatter = null;
		Writer out = null;

		// Setup global dependences
		for ( int column = 0; column < columnStates.length; column++ )
		{
			GenerateColumnState columnState = columnStates[column];
			columnGenerated[column] = columnState.isGenerated();
			if ( columnState.isGenerated() || columnState.hasFutureDependency() )
			{
				entityGenerated &= !columnState.hasFutureDependency();	
				continue;
			}
			columnGenerated[column] = true;
			LOG.debug("Adding column "+columnState.getFullColumnName()+" to list");
			GenerateColumnDef columnDef = (GenerateColumnDef) columnState.getColumnDef();
			ColumnValueGenerator generator = columnDef.getColumnValueGenerator();
			GenerateColumnState[] dependencyColumns = columnState.getDependencies();
			String[] dependencies = generator.getDependencies();
			for ( int k = 0; k < dependencies.length; k++ )
			{
				if ( !( dependencies[k].indexOf(".") == -1 &&
					    dependencyColumns[k].getFullColumnName().startsWith( entityState.getFullEntityName() + "." ) ) )
				{
					LOG.debug("Setting dependency '"+dependencies[k]+"'");
					String[] vals = (String[]) dependencyValues.get( dependencyColumns[k].getFullColumnName() );
					if ( vals == null && !(dependencyColumns[k].getColumnDef() instanceof GenerateColumnDef) )
					{
						loadSourceValues( dependencyColumns[k] );
						vals = (String[]) dependencyValues.get( dependencyColumns[k].getFullColumnName() );
					}
					generator.setDependentValues( dependencies[k], vals );
				}
			}
		}
		LOG.debug( "All columns will be generated after this pass? "+(entityGenerated?"yes":"no"));
		
		// Setup the delimiter formatter if needed
		if ( entityGenerated )
		{
			LOG.debug("Setting up delimiter formatter");
			try
			{
				if ( entityDef.getTargetEncoding() != null )
				{
					out = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( entityDef.getTarget()), entityDef.getTargetEncoding()));
				}
				else
				{
					out = new FileWriter( entityDef.getTarget() );
				}
				formatter = entityDef.getDelimiterFormatter();
				formatter.setWriter( out );
			}	
			catch ( IOException e )
			{
				LOG.error("Error setting up delimiter formatter", e );
			}
		}

		for ( int row = 0; row < entityDef.getCount(); row++ )
		{
			if ( readFromTmp )
			{
				readRowFromDisk( entityState.getFullEntityName(), row, values );
			}
			
			for ( int j = 0; j < positions.length; j++ )
			{
				int column = positions[j];
				GenerateColumnState columnState = columnStates[column];
				if ( columnState.isGenerated() || columnState.hasFutureDependency() )
				{	
					if ( LOG.isDebugEnabled() && columnState.hasFutureDependency() )
					{
						LOG.debug("Column '"+columnState.getFullColumnName()+"' depends on a column whose values haven't been generated");
					}
					else if ( LOG.isDebugEnabled() )
					{
						LOG.debug("Column '"+columnState.getFullColumnName()+"' is already generated");
					}
					continue;
				}
				GenerateColumnDef columnDef = (GenerateColumnDef) columnState.getColumnDef();
				ColumnValueGenerator generator = columnDef.getColumnValueGenerator();
				GenerateColumnState[] dependencyColumns = columnState.getDependencies();
				String[] dependencies = generator.getDependencies();
				values[column].setString(null);
				
				// Setup local dependencies
				for ( int k = 0; k < dependencies.length; k++ )
				{
					if ( dependencies[k].indexOf(".") == -1 &&
					     dependencyColumns[k].getFullColumnName().startsWith( entityState.getFullEntityName() + "." ) )
					{
						LOG.debug("Setting dependency '"+dependencies[k]+"'");
						generator.setDependentValue( dependencies[k], values[ dependencyColumns[k].getIndex() ].getString()  );						
					}
				}

				values[column] = generator.getNextColumnValue(row, r);
				
				// If some other column depends on this column then
				// store all rows' column values in memory
				if ( columnState.getReverseDependencies().length != 0 )
				{
					LOG.debug("Storing values for column '"+columnState.getFullColumnName()+"' in memory for other columns");
					String[] dependentValues = (String[]) dependencyValues.get( columnState.getFullColumnName() );
					if ( dependentValues == null )
					{
						dependentValues = new String[ entityDef.getCount() ];
						dependencyValues.put( columnState.getFullColumnName(), dependentValues );
					}
					dependentValues[row]= values[column].getString();
				}
			}
			
			if ( entityGenerated )
			{
				deleteRowFromDisk( entityState.getFullEntityName(), row );
				
				try
				{
					formatter.writeNextRow( formatter.formatValues( values ) );
				}
				catch ( IOException e )
				{
					LOG.error( "Error writing next row to file", e);
				}
			}
			else
			{
				writeRowToDisk( entityState.getFullEntityName(), row, values);
			}
		}
		
		if ( formatter != null )
		{
			try
			{
				formatter.finish();
			}
			catch ( IOException e )
			{
				LOG.error("Error closing delimiter formatter", e);
			}
			this.fireDataGeneratorEntityEvent( new DataGeneratorEntityEvent( this, entityDef, null ) );
		}
		if ( out != null )
		{
			try
			{
				out.flush();
				out.close();
			}
			catch ( IOException e )
			{
				LOG.error("Could not close output file", e );
			}
		}

		// Setup the next entities to be generated
		for ( int column = 0; column < columnStates.length; column++ )
		{
			columnStates[column].setGenerated(columnGenerated[column] );
		}
		
		for ( int column = 0; column < columnStates.length; column++ )
		{
			GenerateColumnState columnState = columnStates[column];
			if ( !columnState.isGenerated() )
			{
				continue;
			}
			for ( int k = 0; k < columnState.getReverseDependencies().length; k++ )
			{
				GenerateColumnState reverseColumn = columnState.getReverseDependencies()[k];
				if ( reverseColumn.isGenerated() )
				{
					continue;
				}
				
				GenerateColumnDef reverseColumnDef = (GenerateColumnDef) reverseColumn.getColumnDef();
				
				// If the column is part of the same table that was just processed
				if ( reverseColumn.getParent().getFullEntityName().equals( entityState.getFullEntityName() ))
				{
					String[] reverseColumnDependencies = reverseColumnDef.getColumnValueGenerator().getDependencies();
					for ( int l = 0; l < reverseColumnDependencies.length; l++ )
					{
						
						if ( reverseColumnDependencies[l].indexOf(".") > -1 &&
							 reverseColumnDependencies[l].equals( columnState.getFullColumnName() ) &&
							 !reverseEntities.contains( reverseColumn.getParent() ) )
						{
							LOG.debug( "Adding entity '"+reverseColumn.getParent().getFullEntityName()+"' to list of entities that"+
									   " will be generated in the next pass");
							reverseEntities.add( reverseColumn.getParent() );
							break;
						}
					}
				}
				// We haven't read rows from temp files (ie. the first pass of this table) and the column belongs to a table that was
				// already processed or
				// We have read rows from temp files and the column belongs to a table that was already processed
				// 
				else if ( ( (!readFromTmp && reverseColumn.getParent().getIndex() < currentEntity.getIndex() ) ||
					 ( readFromTmp && reverseColumn.getParent().getIndex() <= currentEntity.getIndex() ) ) &&
					 !reverseEntities.contains( reverseColumn.getParent() ) )
				{
					LOG.debug( "Adding entity '"+reverseColumn.getParent().getFullEntityName()+"' to list of entities that"+
					   " will be generated in the next pass");
					reverseEntities.add( reverseColumn.getParent() );
				}
			}
		}
		
		
		for ( int i = 0; i < reverseEntities.size(); i++ )
		{
			processEntity( (GenerateEntityState) reverseEntities.get(i), true );
		}
		LOG.trace( "processEntity( <GenerateEntityState:"+entityState.getFullEntityName()+">,"+
			    " "+readFromTmp+") <-");
	}

	/**
	 * Load the source values for a given column.
	 * 
	 * @param state The column's state
	 */
	protected void loadSourceValues(GenerateColumnState state)
	{
		EntityDef entityDef = state.getParent().getEntityDef();
		int sourcePos = -1;
		for ( int i = 0; i < this.sourceEntities.length; i++ )
		{
			if ( this.sourceEntities[i] == entityDef )
			{
				sourcePos = i;
				break;
			}
		}
		if ( sourcePos == -1 )
		{
			return;
		}
		int columnPos = -1;
		Iterator columnIter = entityDef.getColumnIterator();
		for ( int i = 0; columnIter.hasNext(); i++ )
		{
			ColumnDef columnDef = (ColumnDef) columnIter.next();
			if ( columnDef == state.getColumnDef() )
			{
				columnPos = i;
				break;
			}
		}
		if ( columnPos == -1 )
		{
			return;
		}
	
		if ( this.sourceEntitiesRowCount[sourcePos] == -1 )
		{
			try
			{
				if ( state.getParent().getEntityDef() instanceof ImportEntityDef )
				{
					this.sourceEntitiesRowCount[sourcePos] = loadImportSourceValues( state.getParent() );
				}
				else if ( state.getParent().getEntityDef() instanceof ExportEntityDef )
				{
					this.sourceEntitiesRowCount[sourcePos] = loadExportSourceValues( state.getParent() );
				}
			}
			catch (IOException e)
			{
				LOG.error("Could not load source values for "+state.getFullColumnName(), e );
			}
			catch(SQLException e)
			{
				LOG.error("Could not load source values for "+state.getFullColumnName(), e );
			}
		}

		if ( this.sourceEntitiesRowCount[sourcePos] == -1 )
		{
			return;
		}
		
		String[] vals = new String[this.sourceEntitiesRowCount[sourcePos]];
		ColumnValue[] rowVals = new ColumnValue[ entityDef.getColumnCount() ];
		for ( int i = 0; i < rowVals.length; i++ )
		{
			rowVals[i] = new ColumnValue();
		}
		for ( int i = 0; i < vals.length; i++ )
		{
			this.readRowFromDisk( state.getParent().getFullEntityName(), i, rowVals );
			vals[i] = rowVals[columnPos].getString();
		}
		this.dependencyValues.put( state.getFullColumnName(), vals );
	}

	/**
	 * Load the source values for a given entity from a file.
	 * 
	 * @param entityState the entity's state
	 * @return the number of rows loaded
	 * @throws SQLException thrown if the db column translators could not be setup or cleaned up
	 * @throws IOException thrown if the file could not be parsed properly
	 */
	protected int loadImportSourceValues( GenerateEntityState entityState ) throws SQLException, IOException
	{
		ImportEntityDef entityDef = (ImportEntityDef) entityState.getEntityDef();

		setupDbColumnTranslators( entityDef );
		
		BinaryDelimiterParser delimiterParser = entityDef.getBinaryDelimiterParser();
		if ( delimiterParser == null )
		{
			delimiterParser = new BinaryDelimiterParserDelegate( 
				entityDef.getDelimiterParser(), entityDef.getSourceEncoding() );
		}
		
		InputStream in = null;
		int numRows = 0;
		try
		{
			in = new FileInputStream( entityDef.getSource() );
			delimiterParser.setInputStream(in);
			ColumnValue[] values = null;
			Object nextRow = delimiterParser.getNextRow();
			while ( nextRow != null )
			{
				if ( LOG.isDebugEnabled() )
				{
					LOG.debug("Row #"+numRows+" = '"+
						delimiterParser.getRowAsString(nextRow)+"'");
				}

				try
				{
					values = delimiterParser.getValues(nextRow);
					
					values = fillInValues( entityDef, values );

					this.writeRowToDisk( entityState.getFullEntityName(), numRows, values );
					
					numRows++;
				}
				catch ( MalformedDataException male )
				{
					LOG.debug("MalformedDataException caught while trying to import row", male);
				}
				
				nextRow = delimiterParser.getNextRow();
			}
			return numRows;
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
			
			cleanupDbColumnTranslators( entityDef );
		}
	}
	
	/**
	 * Load the source values for a given entity from the database.
	 * 
	 * @param entityState the entity's state
	 * @return the number of rows loaded
	 * @throws SQLException thrown if the data could not be retrieved from the database
	 */
	protected int loadExportSourceValues( GenerateEntityState entityState ) throws SQLException
	{
		ExportEntityDef entityDef = (ExportEntityDef) entityState.getEntityDef();

		setupDbColumnTranslators( entityDef );

		ExportEngine curEngine = new BasicEngine();
		if ( entityDef.getExportEngine() != null )
		{
			curEngine = entityDef.getExportEngine();
		}
		curEngine.setConnection( con );
		curEngine.setEntityDef(entityDef);
		curEngine.init();

		int numRows = 0;
		
		try
		{
			ColumnValue[] values = curEngine.exportRow();
			while ( values != null )
			{
				try
				{
					values = fillInValues( entityDef, values );
					this.writeRowToDisk( entityState.getFullEntityName(), numRows, values );
					numRows++;
				}
				catch ( MalformedDataException male )
				{
					LOG.debug("MalformedDataException caught while trying to export row", male);
				}
				values = curEngine.exportRow();			
			}
			return numRows;
		}
		finally
		{			
			cleanupDbColumnTranslators( entityDef );
			curEngine.cleanup();
			if ( entityDef.getExportEngine() != null )
			{
				// Make sure the export engine doesn't have access to the connection
				entityDef.getExportEngine().setConnection( null );
			}
		}
	}
	
	/**
	 * Delete a given row from temporary storage.
	 * 
	 * @param entityName the name of the entity
	 * @param row the row number
	 */	
	protected void deleteRowFromDisk( String entityName, int row )
	{
		File tmpFile = new File( entityName + "-"+Integer.toString(row) );
		if ( tmpFile.exists() && tmpFile.isFile() )
		{
			tmpFile.delete();
		}
	}
	
	/**
	 * Write a given row to a temporary file.
	 * 
	 * @param entityName the name of the entity
	 * @param row the row number
	 * @param values the values
	 */	
	protected void writeRowToDisk( String entityName, int row, ColumnValue[] values )
	{
		if ( !useDiskCache )
		{
			String[][] vals = (String[][]) entityValues.get(entityName);
			for ( int i = 0; i < values.length; i++ )
			{
				vals[row][i] = values[i].getString();
			}
			return;
		}
		ObjectOutputStream out = null;
		String filename = entityName+"-"+Integer.toString( row );
		try
		{
			String[] stringValues = new String[ values.length ];
			for ( int i = 0; i < stringValues.length; i++ )
			{
				stringValues[i] = values[i].getString();
			}
			File rowFile = new File( filename );
			out = new ObjectOutputStream( new FileOutputStream( rowFile ) );
			out.writeObject( stringValues );
		}
		catch ( IOException ioe )
		{
			LOG.error("Unable to write row to file '"+filename+"'", ioe );
		}
		finally
		{
			try
			{
				if ( out != null )
				{
					out.close();
				}
			}
			catch ( IOException ioe2 )
			{
				LOG.warn("Unable to close file '"+filename+"'", ioe2);
			}
		}
	}
	
	/**
	 * Read a given row to from a temporary file.
	 * 
	 * @param entityName the name of the entity
	 * @param row the row number
	 * @param values the values to read into
	 */	
	protected void readRowFromDisk( String entityName, int row, ColumnValue[] values )
	{
		if ( !useDiskCache )
		{
			String[][] vals = (String[][]) entityValues.get(entityName);
			for ( int i = 0; i < values.length; i++ )
			{
				values[i].setString( vals[row][i] );
			}
			return;
		}
		ObjectInputStream in = null;
		String filename = entityName+"-"+Integer.toString( row );
		try
		{
			File rowFile = new File( filename );
			if ( !rowFile.exists() || !rowFile.isFile() )
			{ 
				LOG.warn("File '"+rowFile+"' is not a file or does not exist");
				return;
			}
			in = new ObjectInputStream( new FileInputStream( rowFile ) );
			String[] returnValues = (String[]) in.readObject();
			for ( int i = 0; i < returnValues.length; i++ )
			{
				values[i].setString( returnValues[i] );
			}
		}
		catch ( ClassNotFoundException cnfe )
		{
			LOG.error("Unable to read row from file '"+filename+"'", cnfe );
		}
		catch ( IOException ioe )
		{
			LOG.error("Unable to write row to file '"+filename+"'", ioe );
		}
		finally
		{
			try
			{
				if ( in != null )
				{
					in.close();
				}
			}
			catch ( IOException ioe2 )
			{
				LOG.warn("Unable to close file '"+filename+"'", ioe2);
			}
		}
	}

	/**
	 * Fill in the column SQL Types that are missing.
	 * 
	 * @param entityDef the entity definition
	 * @throws SQLException thrown if the SQL Types could not be retrieved
	 */
	protected void fillInColumnTypes( EntityDef entityDef ) throws SQLException
	{	
		LOG.trace( "fillInColumnTypes( <GenerateEntityDef:"+entityDef.getTable()+"> )  ->");
		Iterator columnIter = entityDef.getColumnIterator();
		if ( con == null )
		{
			StringBuffer buf = new StringBuffer("");
			for ( int i = 0; columnIter.hasNext(); i++ )
			{
				ColumnDef columnDef = (ColumnDef) columnIter.next();
				if ( columnDef.getType() == ColumnDef.UNKNOWN_SQL_TYPE )
				{
					if ( buf.length() != 0 )
					{
						buf.append(",");
					}
					buf.append( "'"+columnDef.getName()+"'" );
				}
			}
			if ( buf.length() != 0 )
			{
				throw new SQLException("For entity '"+entityDef+"',"+
						" columns "+buf.toString()+" were not assigned a java.sql.Type");
			}
			LOG.trace( "fillInColumnTypes( <GenerateEntityDef:"+entityDef.getTable()+"> )  <-");
			return;
		}
		DatabaseMetaData metaData = null;
		Map columnToTypes = new HashMap();
		for ( int i = 0; columnIter.hasNext(); i++ )
		{
			ColumnDef def = (ColumnDef) columnIter.next();
			if ( def.getType() == ColumnDef.UNKNOWN_SQL_TYPE )
			{
				LOG.debug("Looking up SQL Type for column '"+def.getName()+"'");
				if ( metaData == null )
				{
					metaData = con.getMetaData();	
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
				LOG.debug("Found SQL Type '"+def.getType()+"' for column '" + def.getName() + "'");
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
				LOG.debug( "Column Format Pattern ='"+formatPattern+"'");
				def.setFormatPattern(formatPattern);
			}
		}	
		LOG.trace( "fillInColumnTypes( <GenerateEntityDef:"+entityDef.getTable()+"> )  <-");
	}

	/**
	 * Returns the transformed column values.
	 * 
	 * @param entityDef the entity definition
	 * @param values the rows' values
	 * @return column values the rows' values with defaults and column transformers applied.
	 * @throws MalformedDataException if the rows' values are incorrect
	 */	
	protected ColumnValue[] fillInValues( EntityDef entityDef, ColumnValue[] values ) throws MalformedDataException
	{
		LOG.trace( "fillInValues( <EntityDef:"+entityDef.getTable()+">, <ColumnValue[]:"+values.length+"> ) ->");

		if ( values.length != entityDef.getColumnCount() )
		{
			throw new MalformedDataException( "# of values not equal to # of columns ("+values.length+","+entityDef.getColumnCount()+")" );
		}

		Iterator columnIter = entityDef.getColumnIterator();
		for ( int i = 0; i < values.length; i++ )
		{
			ColumnDef columnDef = (ColumnDef) columnIter.next();
			if ( values[i] == null || "".equals( values[i].getString() ) )
			{
				if ( columnDef.getDefaultValue() != null )
				{
					values[i].setString( columnDef.getDefaultValue() );
				}
			}
			ColumnTranslator translator = null;
			if ( columnDef instanceof ImportColumnDef )
			{
				translator = ((ImportColumnDef) columnDef).getTranslator();
			}
			else if ( columnDef instanceof ExportColumnDef )
			{
				translator = ((ExportColumnDef) columnDef).getTranslator();
			}
			
			if ( translator != null )
			{
				if ( LOG.isDebugEnabled() )
					LOG.debug("Value before translator '"+values[i]+"' for column '"+columnDef.getName()+"'" );

				values[i] = translator.getValue(columnDef, values[i] );
				
				if ( LOG.isDebugEnabled() )
					LOG.debug("Value after translator '"+values[i]+"' for column '"+columnDef.getName()+"'" );
			}
		}
		LOG.trace( "fillInValues( <EntityDef:"+entityDef.getTable()+">, <ColumnValue[]:"+values.length+"> )  <-");
		return values;	
	}

	/**
	 * Setup any DbColumnTranslators.
	 * 
	 * @param entityDef the entity definition
	 */
	private void setupDbColumnTranslators( EntityDef entityDef ) throws SQLException
	{
		Iterator columnIter = entityDef.getColumnIterator();
		while ( columnIter.hasNext() )
		{
			ColumnDef columnDef = (ColumnDef) columnIter.next();
			ColumnTranslator translator = null;
			if ( columnDef instanceof ImportColumnDef )
			{
				translator = ((ImportColumnDef) columnDef).getTranslator();
			}
			else if ( columnDef instanceof ExportColumnDef )
			{
				translator = ((ExportColumnDef) columnDef).getTranslator();
			}
			if ( translator != null && translator instanceof DbColumnTranslator )
			{
				((DbColumnTranslator) translator).setup( con );
			}
		}
	}
	
	/**
	 * Cleanup any DbColumnTranslators.
	 * 
	 * @param entityDef The entity definition
	 */
	private void cleanupDbColumnTranslators( EntityDef entityDef ) throws SQLException 
	{
		Iterator columnIter = entityDef.getColumnIterator();
		while ( columnIter.hasNext() )
		{
			ColumnDef columnDef = (ColumnDef) columnIter.next();
			ColumnTranslator translator = null;
			if ( columnDef instanceof ImportColumnDef )
			{
				translator = ((ImportColumnDef) columnDef).getTranslator();
			}
			else if ( columnDef instanceof ExportColumnDef )
			{
				translator = ((ExportColumnDef) columnDef).getTranslator();
			}
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


/**
 * The GenerateEntityState keeps track of the state of the generation
 * of data for an entity.
 */
class GenerateEntityState
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( GenerateEntityState.class );
	
	/**
	 * The entity definition.
	 */
	EntityDef entityDef;
	
	/**
	 * The state of the generation of data for each column.
	 */
	GenerateColumnState[] columnStates;
	
	/**
	 * The index in the list of entries that this state belongs to.
	 */
	int index = -1;
	
	/**
	 * The positions that the columnStates should be placed in
	 * before writing a row to the output file.
	 */
	int[] positions;
	
	/**
	 * The full entity name.
	 */
	String fullEntityName;
	
	/**
	 * Constructs a new generate entity state based on the
	 * given entity def and the position. The initial state
	 * is that no column data has been generated.
	 * 
	 * @param def the entity definition
	 * @param index the position of the entity definition in the list of entitys whose
	 * will be generated
	 */
	public GenerateEntityState( EntityDef def, int index )
	{
		this.index = index;
		this.entityDef = def;
		StringBuffer buf = new StringBuffer();
		if ( entityDef.getCatalog() != null )
		{
			buf.append( entityDef.getCatalog() );
			buf.append( "." );
		}
		if ( entityDef.getSchema() != null )
		{
			buf.append( entityDef.getSchema() );
			buf.append(".");
		}
		buf.append( entityDef.getTable() );

		fullEntityName = buf.toString();
		
		columnStates = new GenerateColumnState[def.getColumnCount()];
		positions = new int[ def.getColumnCount() ];
		for ( int j = 0; j < positions.length; j++ )
		{
			positions[j] = -1;
		}

		Map reverseDependentFields = new HashMap();
		Iterator columnIter = def.getColumnIterator();
		LOG.debug("Setting up order that columns are processed");			
		for ( int j = 0; columnIter.hasNext(); j++ )
		{
			ColumnDef columnDef = (ColumnDef) columnIter.next();
			columnStates[j] = new GenerateColumnState( this, columnDef, j );
			if ( !(entityDef instanceof GenerateEntityDef) )
			{
				continue;
			}
			String fullColumnName = columnStates[j].getFullColumnName();
			ColumnValueGenerator generator = ((GenerateColumnDef) columnDef).getColumnValueGenerator();
			if ( generator.getDependencies().length != 0 )
			{
				String[] tmp = new String[ generator.getDependencies().length ];
				System.arraycopy(generator.getDependencies(),0,tmp,0,tmp.length);
				for ( int k = 0; k < tmp.length; k++ )
				{
					String tmpName = resolveColumnName( def, tmp[k] );
					tmp[k] = tmpName;
					List reverseDependency = (List) reverseDependentFields.get(tmpName);
					if ( reverseDependency == null )
					{
						reverseDependency = new ArrayList();
						reverseDependentFields.put( tmpName, reverseDependency );
					}
					reverseDependency.add( fullColumnName );
				}
				int pos = j;
				// a, b, c, d
				// b, a, c, d (a depends on b, d depends on c)
					
				for ( int k = 0; k < tmp.length; k++ )
				{
					if ( tmp[k].startsWith( fullEntityName+"." ) )
					{
						int tmpindex = findColumn( positions, def, tmp[k] );
						if ( tmpindex != -1 )
							pos = Math.max( pos, tmpindex + 1 );							
					}
				}
					
				List reverseDependency = (List) reverseDependentFields.get( fullColumnName );
				if ( reverseDependency != null )
				{
					for ( int k = 0; k < reverseDependency.size(); k++ )
					{
						String columnName = (String) reverseDependency.get(k);
						if ( columnName.startsWith( fullEntityName+"." ) )
						{
							int tmpindex = findColumn( positions, def, tmp[k] );
							if ( tmpindex != -1 )
								pos = Math.min( pos, tmpindex );
						}
					}
				}
				LOG.debug("Setting position "+pos+" to column #"+j);
				for ( int k = positions.length - 1; k > pos; k-- )
				{
					positions[k] = positions[k-1];
				}
				positions[pos] = j;
			}
			else
			{
				LOG.debug("Setting position 0 to column #"+j);
				for ( int k = positions.length - 1; k > 0; k-- )
				{
					positions[k] = positions[k-1];
				}
				positions[0] = j;
			}
		}
	}
	
	/**
	 * Returns the entity definition.
	 * 
	 * @return the entity definition
	 */
	public EntityDef getEntityDef()
	{
		return entityDef;
	}
	
	/**
	 * Returns the fully qualified entity name (schema and 
	 * catalog are added to the name if they are specified).
	 * 
	 * @return the full entity
	 */
	public String getFullEntityName()
	{
		return fullEntityName;
	}
	
	/**
	 * Returns the order in which the column values should be generated.
	 * 
	 * @return an array of indices into the column definitions  
	 */
	public int[] getPositions()
	{
		return positions;
	}
	
	/**
	 * Returns the index of the entity state.
	 * 
	 * @return the position of the entity definition
	 */
	public int getIndex()
	{
		return index;
	}
		
	/**
	 * Returns the state of all columns.
	 * 
	 * @return the column states
	 */
	public GenerateColumnState[] getColumns()
	{
		return columnStates;
	}

	/**
	 * Generate the fully qualified column name.
	 * 
	 * @param entityDef the source entity def
	 * @param columnName the column name
	 * @return the fully qualified name
	 */
	protected String resolveColumnName( EntityDef entityDef, String columnName )
	{
		if ( columnName.indexOf(".") != -1 )
		{
			return columnName;
		}
		else
		{
			return fullEntityName + "." + columnName;
		}	
	}
	
	/**
	 * Find the position of the given column name.
	 * 
	 * @param positions the positions of the column states
	 * @param entityDef the entity definition
	 * @param columnName the column name
	 * @return the position that the column will be generated at.
	 */
	protected int findColumn( int[] positions, EntityDef entityDef, String columnName )
	{
		Iterator iter = entityDef.getColumnIterator();
		int pos = -1;
		int order = -1;
		for ( int i = 0; iter.hasNext(); i++ )
		{
			ColumnDef columnDef = (ColumnDef) iter.next();
			String fullColumnName = fullEntityName + "." + columnDef.getName();
			if ( columnName.equals( fullColumnName ) )
			{
				order = i;
				break;
			}
		}
		if ( order == -1 )
		{
			return pos;
		}
		
		for ( int i = 0; i < positions.length; i++ )
		{
			if ( positions[i] == order )
			{
				pos = i;
				break;
			}
		}
		return pos;	
	}
	
	/**
	 * Returns the column state of the given column name.
	 * 
	 * @param columnName the column name
	 * @return the column state
	 */
	public GenerateColumnState getColumn( String columnName )
	{
		GenerateColumnState columnState = null;
		for ( int i = 0; i < columnStates.length; i++ )
		{
			if ( columnStates[i].getColumnDef().getName().equals( columnName ) )
			{
				columnState = columnStates[i];
				break;
			}
		}
		return columnState;
	}	
}

/**
 * The GenerateColumnState keeps track of the state of the generation
 * of data for a specific column.
 */
class GenerateColumnState
{
	/**
	 * The parent entities' state.
	 */
	GenerateEntityState parentState;
	
	/**
	 * The column definition.
	 */
	ColumnDef columnDef;
	
	/**
	 * The fully qualified column name.
	 */
	String fullColumnName;

	/**
	 * The position in the list columns that this column's values will
	 * be written to.
	 */	
	int index;
	
	/**
	 * The column states that this column depends upon.
	 */
	GenerateColumnState[] dependencies = new GenerateColumnState[0];
	
	/**
	 * The column states that depend on this column.
	 */
	GenerateColumnState[] reverseDependencies = new GenerateColumnState[0];
	
	/**
	 * Whether the columns' values have been generated or not.
	 */
	boolean generated;
	
	/**
	 * Constructs a column state with the given parent, column definition and
	 * index.
	 * 
	 * @param state the parent entity state
	 * @param columnDef the column definition
	 * @param index the position that the column's values will be generated.
	 */
	public GenerateColumnState( GenerateEntityState state, ColumnDef columnDef, int index )
	{
		this.index = index;
		this.parentState = state;
		this.columnDef = columnDef;
		this.fullColumnName = state.getFullEntityName()+"."+columnDef.getName();
		
		this.generated = false;
	}


	/**
	 * Returns the fully qualified column name.
	 * 
	 * @return the column name
	 */
	public String getFullColumnName()
	{
		return fullColumnName;
	}
	
	/**
	 * Returns the parent entity state.
	 * 
	 * @return the entity state
	 */
	public GenerateEntityState getParent()
	{
		return parentState;
	}
	
	/**
	 * Returns the column definition.
	 * 
	 * @return the column definition
	 */
	public ColumnDef getColumnDef()
	{
		return columnDef;
	}
	
	/**
	 * Returns whether this column depends on a column whose 
	 * values have not been generated.
	 * 
	 * @return flag indicating that the column depends on column(s) whose
	 * values have not been generated
	 */
	public boolean hasFutureDependency()
	{
		if ( generated || !(columnDef instanceof GenerateColumnDef ) )
		{
			return false;
		}
		boolean flag = false;
		
		String[] dependencyNames = ((GenerateColumnDef) columnDef).getColumnValueGenerator().getDependencies();
		for ( int i = 0; i < dependencies.length; i++ )
		{
			if ( dependencies[i].getParent().index > getParent().index &&
			     !dependencies[i].isGenerated() )
			{
				flag = true;
				break;
			}
			else if ( dependencies[i].getParent().index == getParent().index &&
			          dependencyNames[i].indexOf(".") > -1 &&
			          !dependencies[i].isGenerated() )
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * Returns whether the values have been generated for this column.
	 * 
	 * @return flag indicating that the values have been generated
	 */
	public boolean isGenerated()
	{
		return generated;
	}

	/**
	 * Returns whether the dependencies for this column have been
	 * fullfilled and the values can be generated.
	 * 
	 * @return flag indicating the values can be generated
	 */
	public boolean canGenerate()
	{
		boolean returnVal = true;
		for ( int i = 0; i < dependencies.length; i++ )
		{
			if ( !dependencies[i].isGenerated() )
			{
				returnVal = false;
				break;
			}
		}
		return returnVal;
	}
	
	/**
	 * Set that the column values have been generated.
	 * 
	 * @param flag flag indicating whether the values have been generated
	 */
	public void setGenerated( boolean flag )
	{
		generated = flag;	
	}
	
	/**
	 * Set the reverse dependencies states for this column.
	 * 
	 * @param columnStates the column states
	 */
	public void setReverseDependencies( GenerateColumnState[] columnStates )
	{
		reverseDependencies = columnStates;	
	}
		
	/**
	 * Returns the reverse dependencies states for this column.
	 * 
	 * @return the column states
	 */
	public GenerateColumnState[] getReverseDependencies()
	{
		return reverseDependencies;
	}
	
	/**
	 * Set the dependencies states for this column.
	 * 
	 * @param columnStates the column states
	 */
	public void setDependencies( GenerateColumnState[] columnStates )
	{
		dependencies = columnStates;	
	}
	
	/**
	 * Returns the dependencies states for this column.
	 * 
	 * @return the column states
	 */
	public GenerateColumnState[] getDependencies()
	{
		return dependencies;
	}
	
	/**
	 * Returns the position of the list of columns that 
	 * are written to the output file.
	 * 
	 * @return the position of the column
	 */
	public int getIndex()
	{
		return index;
	}
	
}
