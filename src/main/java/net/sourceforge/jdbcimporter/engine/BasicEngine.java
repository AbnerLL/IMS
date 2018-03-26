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
import java.sql.PreparedStatement;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The BasicEngine class implements the ImportEngine interface
 * to provide basic import capability. The BasicEngine simply tries to insert
 * the row into the current table. The following SQL Types are supported:
 * 
 * <ul>
 * <li>VARCHAR</li>
 * <li>CHAR</li>
 * <li>INTEGER</li>
 * <li>SMALLINT</li>
 * <li>TINYINT</li>
 * <li>BIGINT</li>
 * <li>NUMERIC</li>
 * <li>DECIMAL</li>
 * <li>DOUBLE</li>
 * <li>DATE</li>
 * <li>FLOAT</li>
 * <li>TIME</li>
 * <li>TIMESTAMP</li>	
 * <li>BINARY</li>
 * <li>LONGVARBINARY</li>
 * <li>LONGVARCHAR</li>
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class BasicEngine implements ImportEngine
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( BasicEngine.class );
	
	/**
	 * The JDBC connection.
	 */
	Connection connection;
	
	/**
	 * The current entity being imported.
	 */
	ImportEntityDef  entityDef;
	
	/**
	 * The insert prepared statement used to import rows.
	 */
	PreparedStatement insertStmt;
	
	/**
	 * The helper for setting values in the insert prepared statement.
	 */
	JDBCParameterHelper helper = new JDBCParameterHelper();
	
	/**
	 * The flag indicating that batch mode is being used.
	 */
	boolean batchFlag = false;
	
	/**
	 * The list of rows in the current batch.
	 */
	List batchRows = new ArrayList();
	
	/**
	 * @see ImportEngine#setConnection(Connection)
	 */
	public void setConnection(Connection con)
	{
		connection = con;
	}

	/**
	 * @see ImportEngine#getConnection()
	 */
	public Connection getConnection()
	{
		return connection;	
	}
	
	/**
	 * @see ImportEngine#setEntityDef(ImportEntityDef)
	 */
	public void setEntityDef(ImportEntityDef newEntityDef)
	{
		entityDef = newEntityDef;
	}

	/**
	 * @see ImportEngine#init()
	 */
	public void init() throws SQLException
	{
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append( helper.getFullyQualifiedTableName( 
				connection.getMetaData(), 
				entityDef.getCatalog(),
				entityDef.getSchema(), 
				entityDef.getTable()
		) );
		
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
		LOG.debug("Using Insert Stmt : "+sql.toString());
		insertStmt = connection.prepareStatement(sql.toString());
		batchRows.clear();
	}
	
	/**
	 * @see ImportEngine#cleanup()
	 */
	public void cleanup() throws SQLException
	{
		insertStmt.close();		
	}
	
	/**
	 * @see ImportEngine#importRow(ColumnValue[])
	 */
	public void importRow(ColumnValue[] values) throws SQLException, MalformedDataException
	{		
		ImportColumnDef[] columns = entityDef.getColumns();
		for ( int i = 0; i < values.length; i++ )
		{
			int column = i+1;
			helper.setColumn(insertStmt, column, columns[i], values[i]);
		}
		try
		{
			if ( !batchFlag )
			{
				int rows = insertStmt.executeUpdate();
				if ( rows != 1 ) 
				{
					throw new SQLException(rows+" rows inserted");
				}
			}
			else
			{
				insertStmt.addBatch();
				batchRows.add( values );
			}
		}
		catch ( SQLException s )
		{
			throw s;
		}
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ImportEngine#setBatchMode(boolean)
	 */
	public void setBatchMode( boolean flag )
	{
		batchFlag = flag;
	}
	
	/**
	 * @see ImportEngine#executeBatch()
	 */
	public void executeBatch() throws BatchUpdateException
	{
		try
		{
			int[] rows = insertStmt.executeBatch();
		}
		catch ( SQLException sqle )
		{
			if ( sqle instanceof BatchUpdateException )
			{
				throw (BatchUpdateException) sqle;
			}
			else
			{
				LOG.debug("Caught SQLException, trying to get row states manually", sqle );
				int[] keys = new int[entityDef.getColumns().length];
				for ( int i = 0; i < keys.length; i++ )
				{
					keys[i] = i;
				}
				int[] rowStates = helper.resolveRowStates( connection, entityDef, batchRows, keys, new int[0] );
				BatchUpdateException be = new BatchUpdateException( "SQL Exception", rowStates );
				be.setNextException( sqle );
				throw be;
			}
		}
		finally
		{
			batchRows.clear();
		}
	}
	
}
