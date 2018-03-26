package net.sourceforge.jdbcimporter.columntranslator;

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

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DbColumnTranslator;
import net.sourceforge.jdbcimporter.util.JDBCParameterHelper;

public class DbLookupTableColumnTranslator implements DbColumnTranslator
{
	protected String lookupTable;
	protected String lookupColumn;
	protected String sourceColumn;
	
	protected PreparedStatement stmt;
	protected JDBCParameterHelper helper = new JDBCParameterHelper();
	
	public void setLookupTable( String table )
	{
		this.lookupTable = table;
	}

	public void setLookupColumn( String column )
	{
		this.lookupColumn = column;
	}
			
	public void setSourceColumn( String column )
	{
		this.sourceColumn = column;
	}	

	public void setup( Connection con ) throws SQLException
	{
		stmt = con.prepareStatement( "SELECT "+lookupColumn+" FROM "+
				lookupTable+ " WHERE "+sourceColumn+" = ?");
	}

	public void cleanup( ) throws SQLException
	{
		stmt.close();
	}

	public ColumnValue getValue( ColumnDef column, ColumnValue columnValue )
	{
		ColumnValue returnValue = new ColumnValue();
		try
		{
			stmt.setString( 1, columnValue.getString() );
			ResultSet resultSet = stmt.executeQuery();
			if ( resultSet.next() )
			{
				returnValue = helper.getColumn( resultSet, 1, column );
			}
			resultSet.close();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return returnValue;
	}

}
