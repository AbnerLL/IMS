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
 
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.util.JDBCParameterHelper;

/**
 * Helper for ExportEngine.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 * @Description Use net.sourceforge.jdbcimporter.util.JDBCHelper instead
 */
public class ExportEngineHelper 
{
	/**
	 * The real implementation.
	 */
	protected JDBCParameterHelper helper = new JDBCParameterHelper();
	
	/**
	 * Returns the column value from the result set.
	 * 
	 * @param resultSet the result set
	 * @param column the column index
	 * @param def the column definition
	 * @return the column value
	 * @throws SQLException
	 */
	public ColumnValue getColumn( ResultSet resultSet, int column, ColumnDef def ) throws SQLException
	{
		return helper.getColumn( resultSet, column, def );
	}
}
