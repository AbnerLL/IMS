package net.sourceforge.jdbcexporter.engine;

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
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sourceforge.jdbcexporter.ExportColumnDef;
import net.sourceforge.jdbcexporter.ExportEngine;
import net.sourceforge.jdbcexporter.ExportEntityDef;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.util.JDBCParameterHelper;

/**
 * The BasicEngine class implements the ExportEngine interface
 * to provide basic export capability. The BasicEngine exports
 * rows from a database via a select statement.
 * The following SQL Types are supported:
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
public class BasicEngine implements ExportEngine 
{
	/**
	 * The helper class for retrieving parameters from result sets.
	 */
	protected JDBCParameterHelper exportEngineHelper = new JDBCParameterHelper();
	
	/**
	 * The JDBC connection.
	 */
	protected Connection connection;
	
	/**
	 * The current entity definition that is being exported.
	 */
	protected ExportEntityDef  entityDef;
	
	/**
	 * The current entity definition's columns that is being exported.
	 */
	protected ExportColumnDef[] columnDefs;
	
	/**
	 * The statement.
	 */
	protected Statement statement;
	
	/**
	 * The result set.
	 */
	protected ResultSet resultSet;
		
	/**
	 * @see net.sourceforge.jdbcexporter.ExportEngine#setConnection(java.sql.Connection)
	 */
	public void setConnection(Connection con) 
	{
		this.connection = con;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.ExportEngine#getConnection()
	 */
	public Connection getConnection() 
	{
		return connection;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.ExportEngine#setEntityDef(net.sourceforge.jdbcexporter.ExportEntityDef)
	 */
	public void setEntityDef(ExportEntityDef entityDef) {
		this.entityDef = entityDef;
		this.columnDefs = entityDef.getColumns();
	}

	/**
	 * @see net.sourceforge.jdbcexporter.ExportEngine#init()
	 */
	public void init() throws SQLException {
		
		String fullTableName = exportEngineHelper.getFullyQualifiedTableName( 
				connection.getMetaData(), 
				entityDef.getCatalog(),
				entityDef.getSchema(), 
				entityDef.getTable()
		);
		
		StringBuffer sql = new StringBuffer("SELECT ");
		for ( int i = 0; i < columnDefs.length; i++ )
		{
			sql.append( columnDefs[i].getName() );
			if ( i < columnDefs.length - 1 )
			{
				sql.append( ", " );
			}
		}
		sql.append( " FROM ");
		sql.append( fullTableName );
		if ( entityDef.getWhereClause() != null &&
		     !"".equals( entityDef.getWhereClause() ) )
		{
			sql.append (" WHERE ");
			sql.append ( entityDef.getWhereClause() );
		}
		statement = connection.createStatement();
//		System.out.println("SQL: " + sql.toString());
//		System.out.println(entityDef.toString());
		if (entityDef.getQueryStr() == null||"".equals(entityDef.getQueryStr())) {
			resultSet = statement.executeQuery(sql.toString());
		} else {
			resultSet = statement.executeQuery(entityDef.getQueryStr());
		}
	}

	/**
	 * @see net.sourceforge.jdbcexporter.ExportEngine#cleanup()
	 */
	public void cleanup() throws SQLException 
	{
		resultSet.close();
		statement.close();
	}

	/**
	 * @see net.sourceforge.jdbcexporter.ExportEngine#exportRow()
	 */
	public ColumnValue[] exportRow() throws SQLException 
	{
		if (!resultSet.next())
		{
			return null;
		}
		
		ColumnValue[] values = new ColumnValue[columnDefs.length];
		for ( int i = 0; i < values.length; i++ )
		{
			values[i] = exportEngineHelper.getColumn( resultSet, i+1, columnDefs[i] );
		}
		return values;
	}

}
