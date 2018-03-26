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
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.ImportColumnDef;
import net.sourceforge.jdbcimporter.ImportEntityDef;
import net.sourceforge.jdbcimporter.MalformedDataException;
import net.sourceforge.jdbcimporter.util.JDBCParameterHelper;

/**
 * Helper class for ImportEngines.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 * @Description Use net.sourceforge.jdbcimporter.util.JDBCHelper
 */
public class ImportEngineHelper 
{
	/**
	 * The helper.
	 */
	protected JDBCParameterHelper helper = new JDBCParameterHelper();
	
	/**
	 * Set the column value in the prepared statement.
	 * 
	 * @param pstmt the prepared statement
	 * @param column the column index
	 * @param def the column definition
	 * @param val the column value
	 * @throws MalformedDataException
	 */
	public void setColumn( PreparedStatement pstmt, int column, ColumnDef def, ColumnValue val ) throws MalformedDataException
	{
		helper.setColumn( pstmt, column, def, val );
	}
	
	/**
	 * Resolve the states of the rows.
	 * 
	 * @param con the connection
	 * @param entityDef the entity definition
	 * @param rows list of rows
	 * @param keyIndices the indices of column values that make up the primary key
	 * @param compareIndices the indices of column values that should be compared
	 * @return the row states
	 */
	public int[] resolveRowStates( Connection con, ImportEntityDef entityDef, List rows, int[] keyIndices, int[] compareIndices )
	{
		return helper.resolveRowStates( con, entityDef, rows, keyIndices, compareIndices );
	}
	
	/**
	 * Compares the values in the result set with the list of column values.
	 * 
	 * @param rset the result set
	 * @param columns the column definitions
	 * @param compareIndices the indices of column values that should be compared
	 * @param values the column values
	 * @return flag indicating that the rows match
	 * @throws SQLException if an error occurred while retrieving a column value from the result set
	 */
	public boolean compareResultSet( ResultSet rset, ImportColumnDef[] columns, int[] compareIndices, ColumnValue[] values ) throws SQLException
	{
		return helper.compareResultSet( rset, columns, compareIndices, values );
	}
	
	/**
	 * Returns column value at the given column location.
	 * 
	 * @param resultSet the result set
	 * @param column the column index
	 * @param def the column definition
	 * @return column value the column value
	 * @throws SQLException if an error occurred while retrieving the column value from the result set
	 */
	public ColumnValue getColumn( ResultSet resultSet, int column, ColumnDef def ) throws SQLException
	{
		return helper.getColumn( resultSet, column, def );
	}
	
}
