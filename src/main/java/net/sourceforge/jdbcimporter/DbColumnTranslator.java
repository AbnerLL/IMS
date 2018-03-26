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

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The DbColumnTranslator interface defines extra methods used to execute
 * queries on the database that is source/destination of the data.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */

public interface DbColumnTranslator extends ColumnTranslator
{
	/**
	 * Setup any database resources (ie. PreparedStatements). This
	 * method is called before the getValue method is called.
	 * 
	 * @param con The connection to the database
	 * @throws SQLException If an error occurs
	 */
	public void setup( Connection con ) throws SQLException;
	
	/**
	 * Cleanup any database resources (ie. PreparedStatements). This
	 * method is called after all the rows are processed.
	 * 
	 * @throws SQLException If an error occurs
	 */
	public void cleanup() throws SQLException;
}
