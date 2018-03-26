package net.sourceforge.jdbcimporter.engine;

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
 
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.ImportColumnDef;
import net.sourceforge.jdbcimporter.ImportEngine;
import net.sourceforge.jdbcimporter.ImportEntityDef;
import net.sourceforge.jdbcimporter.MalformedDataException;
import net.sourceforge.jdbcimporter.util.JDBCParameterHelper;
import net.sourceforge.jdbcimporter.util.MultipleBatchUpdateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The UpdateEngine class executes an insert or update sql statement. It
 *  determines whether to use an insert or update sql statement by executing
 *  a select (based on the primary keys of the table). It will fail if the
 *  data to be imported does not contain a column that is part of the primary key.
 * 
 * @version 0.6 
 * @author     Chris Nagy
 */
public class UpdateEngine implements ImportEngine 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( UpdateEngine.class );

	/**
	 * The property on an entity definition whose value will be appended
	 * to the select statement.
	 */
	public static final String SELECT_POSTFIX_PROPERTY = "selectPostfix";
	
	/**
	 * The property on a column definition that will indicate the 
	 * the column is a primary key (the value should be 'true').
	 */
	public static final String PRIMARY_KEY_PROPERTY = "primaryKey";
	
	/**
	 * The property on a column definition whose value will be used
	 * as the update expression for that column (the value should contain
	 * the character '?').
	 */
	public static final String UPDATE_EXPRESSION_PROPERTY = "updateExpression";
			
	/**
	 * The JDBC connection.
	 */
	Connection connection;
	
	/**
	 * The current import entity definition.
	 */
	ImportEntityDef  entityDef;
	
	/**
	 * The insert prepared statement.
	 */
	PreparedStatement insertStmt;
	
	/**
	 * The update prepared statement.
	 */
	PreparedStatement updateStmt;
	
	/**
	 * The select prepared statement used to figure out whether the
	 * insert or update prepared statement.
	 */
	PreparedStatement selectStmt;
	
	/**
	 * The map of column indices that is used in the update prepared statement. 
	 */
	int[] updateColumnMap;
	
	/**
	 * The map of column indices that are the primary key.
	 */
	int[] uniqueKeys;
	
	/**
	 * The helper class used to set values into the prepared statement.
	 */
	JDBCParameterHelper helper = new JDBCParameterHelper();

	/**
	 * The flag indicating that batch mode is being used.
	 */
	boolean batchFlag = false;

	/**
	 * The list of statement types.
	 */
	List stmtTypes = new ArrayList();
	
	/**
	 * The list of rows in the current batch.
	 */
	List batchRows = new ArrayList();
	
	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#setConnection(Connection)
	 */
	public void setConnection(Connection con) 
	{
		connection = con;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#getConnection()
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#setEntityDef(ImportEntityDef)
	 */
	public void setEntityDef(ImportEntityDef entityDef) 
	{
		this.entityDef = entityDef;
		this.updateColumnMap = new int[ this.entityDef.getColumns().length ];
		for ( int i = 0; i < updateColumnMap.length; i++ )
		{
			updateColumnMap[i] = i;
		}
	}

	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#init()
	 */
	public void init() throws SQLException
	{
		String fullTableName = helper.getFullyQualifiedTableName( 
				connection.getMetaData(), 
				entityDef.getCatalog(),
				entityDef.getSchema(), 
				entityDef.getTable()
		);
		// first the insert statement
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append( fullTableName );
		sql.append( " ( " );
		ImportColumnDef[] columns = entityDef.getColumns();
		StringBuffer sqlvalues = new StringBuffer(" ( " );
		for ( int i = 0; i < columns.length; i++ )
		{
			if ( i > 0 )
			{
				sql.append( ", " );	
				sqlvalues.append( ", " );
			}
			ColumnDef nextColumn = columns[i];
			sql.append( nextColumn.getName() );
			sqlvalues.append( "?" );
		}
		sql.append( " ) VALUES " );
		sqlvalues.append( " ) " );
		sql.append( sqlvalues.toString() );
		LOG.debug("Insert Stmt : "+sql.toString() );
		insertStmt = connection.prepareStatement(sql.toString());

		String catalog = entityDef.getCatalog();
		String schema  = entityDef.getSchema();
		String table   = entityDef.getTable();
		
		List primaryKeyNames = getDefinedPrimaryKeys();
		
		if ( primaryKeyNames.size() == 0 )
		{
			primaryKeyNames = getPrimaryKeys( catalog, schema, table );
		}
		
		if ( primaryKeyNames.size() == 0 )
		{
			if ( catalog != null ) catalog = catalog.toUpperCase();
			if ( schema  != null ) schema  = schema.toUpperCase();
			if ( table   != null ) table   = table.toUpperCase();
			primaryKeyNames = getPrimaryKeys( catalog, schema, table );
		}
		
		if ( primaryKeyNames.size() == 0 )
		{
			if ( catalog != null ) catalog = catalog.toLowerCase();
			if ( schema  != null ) schema  = schema.toLowerCase();
			if ( table   != null ) table   = table.toLowerCase();
			primaryKeyNames = getPrimaryKeys( catalog, schema, table );
		}
		
		if ( primaryKeyNames.size() == 0 )
		{
			LOG.warn("No Primary Keys for Entity : '"+entityDef.getTable()+"'" );
			// no primary keys
			return;
		}
		else
		{
			LOG.debug("Primary Keys for Entity : "+primaryKeyNames );
		}

		uniqueKeys = new int[ primaryKeyNames.size() ];
		for ( int i = 0; i < uniqueKeys.length; i++ )
		{
			uniqueKeys[i] = -1;
		}
		
		int lastWhereIndex = columns.length - primaryKeyNames.size();
		int lastSetIndex   = 0;
		for ( int i = 0; i < columns.length; i++ )
		{
			ColumnDef columnDef = columns[i];
			boolean found = false;
			for ( int j = 0; j < primaryKeyNames.size(); j++ )
			{
				String nextKey = (String) primaryKeyNames.get(j);
				if ( columnDef.getName().equalsIgnoreCase( nextKey ) )
				{
					uniqueKeys[j] = i;
					found = true;
					break;
				}
			}
			if ( !found )
			{
				updateColumnMap[lastSetIndex] = i;
				lastSetIndex++;
			}
			else
			{
				updateColumnMap[lastWhereIndex] = i;
				lastWhereIndex++;
			}
		}

		for ( int i = 0; i < uniqueKeys.length; i++ )
		{
			// could not find primary key in one of the import columns
			if ( uniqueKeys[i] == -1) return;
		}
						
		// third, the select statement
		sql = new StringBuffer("SELECT ");
		StringBuffer sqlwhere = new StringBuffer("");		
		for ( int i = 0; i < primaryKeyNames.size(); i++ )				
		{
			sql.append( (String) primaryKeyNames.get(i) );
			if ( i < primaryKeyNames.size() - 1 )
			{
				sql.append( "," );
			}
			sqlwhere.append( (String) primaryKeyNames.get(i) );
			sqlwhere.append( " = ? " );
			if ( i < primaryKeyNames.size() - 1 )
			{
				sqlwhere.append( " AND " );		
			}	
		}
		sql.append( " FROM " );
		sql.append( fullTableName );
		sql.append( " WHERE ");
		sql.append( sqlwhere.toString() );
		String selectPostfix = entityDef.getProperty(SELECT_POSTFIX_PROPERTY);
		if ( selectPostfix != null )
		{
			sql.append( " " );
			sql.append( selectPostfix );
		}
		LOG.debug("Select Stmt : "+sql.toString() );
		selectStmt = connection.prepareStatement( sql.toString() );
				
		// fourth, the update statement
		sql = new StringBuffer("UPDATE ");
		sql.append( fullTableName );
		sql.append( " SET " );
		sqlwhere = new StringBuffer("" );
		for ( int i = 0; i < columns.length; i++ )
		{		
			ColumnDef columnDef = columns[updateColumnMap[i]];
			if ( i < columns.length - uniqueKeys.length ) 
			{
				sql.append( columnDef.getName() );
				if ( columnDef.getProperty(UPDATE_EXPRESSION_PROPERTY) != null )
				{
					String expression = columnDef.getProperty(UPDATE_EXPRESSION_PROPERTY);
					if ( expression.indexOf("?") != -1 ) // TODO: More error checking
					{
						sql.append( " = "+expression + " ");
					}
					else
					{
						// TODO: Error message
						sql.append( " = ? ");
					}
				}
				else
				{
					sql.append( " = ? ") ;
				}
				if ( i < columns.length - uniqueKeys.length - 1 )
				{
					sql.append( ", " );
				}		
			}
			else
			{
				sqlwhere.append( columnDef.getName() );
				sqlwhere.append( " = ? " );
				if ( i < columns.length - 1 )
				{
					sqlwhere.append( " AND " );
				}
			}
		}
		sql.append( " WHERE " );
		sql.append( sqlwhere.toString() );
		LOG.debug("Update Stmt : "+sql.toString() );
		updateStmt = connection.prepareStatement( sql.toString() );
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#cleanup()
	 */
	public void cleanup() throws SQLException
	{
		if ( selectStmt != null ) selectStmt.close();
		insertStmt.close();		
		if ( updateStmt != null ) updateStmt.close();
		
		insertStmt = null;
		selectStmt = null;
		updateStmt = null;
		stmtTypes.clear();
		batchRows.clear();
	}

	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#importRow(ColumnValue[])
	 */
	public void importRow(ColumnValue[] values)
		throws SQLException, MalformedDataException 
	{
		boolean insertFlag    = true;
		ImportColumnDef[] columns = entityDef.getColumns();
		if ( selectStmt != null && updateStmt != null )
		{
			for ( int i = 0; i < uniqueKeys.length; i++ )
			{
				ColumnDef columnDef = columns[ uniqueKeys[i] ];
				ColumnValue columnValue = values[ uniqueKeys[i] ];
				helper.setColumn( selectStmt, i+1,	columnDef, columnValue );
			}		
			
			ResultSet rset = selectStmt.executeQuery();
			insertFlag = !rset.next();
			rset.close();
		}
		LOG.debug("Using "+(insertFlag?"Insert":"Update")+" SQL");
		PreparedStatement stmt = null;
		if ( insertFlag )
		{
			stmt = insertStmt;
			if ( batchFlag ) stmtTypes.add("insert");
		}
		else
		{
			stmt = updateStmt;
			if ( batchFlag ) stmtTypes.add("update");
		}
					
		for ( int i = 0; i < values.length; i++ )
		{
			int column = i+1;
			int realIndex = i;
			if ( !insertFlag )
			{
				realIndex = updateColumnMap[i];
			}
			ColumnDef columnDef = columns[realIndex];
			ColumnValue columnVal = values[realIndex];
			LOG.trace(column + " ["+realIndex+"] -> "+columnVal.getString());
			helper.setColumn( stmt, column, columnDef, columnVal );		
		}
		
		try
		{
			if ( !batchFlag )
			{
				int rows = stmt.executeUpdate();
				if ( rows != 1 ) 
				{
					throw new SQLException(rows+" rows inserted");
				}
			}
			else
			{
				batchRows.add( values );
				stmt.addBatch();
			}
		}
		catch ( SQLException s )
		{
			throw s;
		}
	}
		
	/**
	 * Returns the list of primary keys defined in the import xml.
	 * 
	 * @return list of primary keys
	 */
	protected List getDefinedPrimaryKeys()
	{
		ImportColumnDef[] columns = this.entityDef.getColumns();
		List primaryKeyNames = new ArrayList();
		for ( int i = 0; i < columns.length; i++ )
		{
			if ( "true".equalsIgnoreCase( columns[i].getProperty(PRIMARY_KEY_PROPERTY) ) )
			{
				primaryKeyNames.add( columns[i].getName() );	
			}
		}
		return primaryKeyNames;		
	}
	
	/**
	 * Returns the list of primary keys from the DatabaseMeta.
	 * 
	 * @param catalog The catalog
	 * @param schema The schema
	 * @param table The table
	 * @return list of primary keys
	 * @throws SQLException
	 */
	protected List getPrimaryKeys( String catalog, String schema, String table ) throws SQLException
	{
		LOG.trace("getPrimaryKeys( '"+catalog+"', '"+schema+"', '"+table+"' )");
		List primaryKeyNames = new ArrayList();
		
		DatabaseMetaData dbMeta = connection.getMetaData();
		ResultSet rset = dbMeta.getPrimaryKeys(
			catalog,
			schema,
			table
		);
		
		while ( rset.next() )
		{
			primaryKeyNames.add( rset.getString("COLUMN_NAME") );
		}
		rset.close();
		return primaryKeyNames;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#setBatchMode(boolean)
	 */
	public void setBatchMode( boolean flag )
	{
		batchFlag = flag;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#executeBatch()
	 */
	public void executeBatch() throws BatchUpdateException
	{
		int[] rowsUpdated = new int[ stmtTypes.size() ];
		for ( int i = 0; i < rowsUpdated.length; i++ )
		{
			rowsUpdated[i] = -1;
		}
		try
		{
			SQLException insertEx = null;
			int[] insertRowStates = null;
			try
			{
				insertRowStates = insertStmt.executeBatch();
			}
			catch ( BatchUpdateException bue )
			{
				insertEx = bue;
				insertRowStates = bue.getUpdateCounts();
			}
			catch ( SQLException sqle )
			{
				insertEx = sqle;
				LOG.error("SQLException while trying to execute batch insert statements", sqle );
				if ( uniqueKeys != null )
				{
					List insertRows = new ArrayList();
					for ( int i = 0; i < stmtTypes.size(); i++ )
					{
						if ( "insert".equals( stmtTypes.get(i) ) )
						{
							insertRows.add( batchRows.get(i) );	
						}
					}
					insertRowStates = helper.resolveRowStates( connection, entityDef, insertRows, uniqueKeys, new int[0] );
				}
				else 
				{
					insertRowStates = new int[0];
				}
			}
			
			int insertCounter = 0;
			for ( int i = 0; i < rowsUpdated.length; i++ )
			{
				if ( "insert".equals( stmtTypes.get(i) ) && insertCounter < insertRowStates.length )
				{
					rowsUpdated[i] = insertRowStates[insertCounter];
					insertCounter++;
				}
			}

			SQLException updateEx = null;			
			int[] updateRowStates = null;
			try
			{
				if ( updateStmt != null )
					updateRowStates = updateStmt.executeBatch();
				else
					updateRowStates = new int[0];
			}
			catch ( BatchUpdateException bue )
			{
				updateEx = bue;
				updateRowStates = bue.getUpdateCounts();			
			}
			catch ( SQLException sqle )
			{
				updateEx = sqle;
				LOG.error("SQLException while trying to execute batch update statements", sqle );
				List updateRows = new ArrayList();
				for ( int i = 0; i < stmtTypes.size(); i++ )
				{
					if ( "update".equals( stmtTypes.get(i) ) )
					{
						updateRows.add( batchRows.get(i) );	
					}
				}
				int[] selectKeys = new int[ updateColumnMap.length - uniqueKeys.length ];
				System.arraycopy(updateColumnMap,0,selectKeys,0,selectKeys.length);
				updateRowStates = helper.resolveRowStates( connection, entityDef, updateRows, uniqueKeys, selectKeys );
			}
			
			int updateCounter = 0;
			for ( int i = 0; i < rowsUpdated.length; i++ )
			{
				if ( "update".equals( stmtTypes.get(i) )  && updateCounter < updateRowStates.length )
				{
					rowsUpdated[i] = updateRowStates[updateCounter];
					updateCounter++;
				}
			}
			
			// Validate that all stmts updated only one row
			for ( int i = 0; i < rowsUpdated.length; i++ )
			{
				if ( rowsUpdated[i] != 1 && rowsUpdated[i] != BATCH_SUCCESS_ROWS_UNKNOWN )
				{
					BatchUpdateException bue;
					if ( updateEx != null && insertEx != null )
					{
						MultipleBatchUpdateException mBue = new MultipleBatchUpdateException( rowsUpdated );
						mBue.setNextExceptions( new SQLException[] { insertEx, updateEx } );
						bue = mBue;
					}
					else if ( insertEx != null )
					{
						if ( insertEx instanceof BatchUpdateException )
						{
							bue = (BatchUpdateException) insertEx;
						}
						else
						{
							bue = new BatchUpdateException( rowsUpdated );
							bue.setNextException( insertEx );
						}
					}
					else if ( updateEx != null )
					{
						if ( updateEx instanceof BatchUpdateException )
						{
							bue = (BatchUpdateException) updateEx;
						}
						else
						{
							bue = new BatchUpdateException( rowsUpdated );
							bue.setNextException( updateEx );
						}
					}
					else
					{
						bue = new BatchUpdateException( rowsUpdated );
					}
					
					throw bue;
				}
			}
		}
		finally
		{
			stmtTypes.clear();
			batchRows.clear();
		}
	}
		
}
