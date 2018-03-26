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
 
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The ImportEngine defines the methods needed to import
 * a row into a database.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public interface ImportEngine
{
	/**
	 * Static variable that is equal to constant java.sql.Statement.SUCCESS_NO_INFO
	 * (from jdk1.4)
	 */
	public static int BATCH_SUCCESS_ROWS_UNKNOWN = -2;

	/**
	 * Sets the database connection to use for importing the row.
	 *
	 * @param con database connection
	 */
	public void setConnection( Connection con );
	
	/**
	 * Returns the database connection to use for importing the row.
	 * 
	 * @return database connection
	 */
	public Connection getConnection();
	
	/**
	 * Sets the entity definition to use for importing the row
	 * 
	 * @param entityDef entity definition
	 */
	public void setEntityDef( ImportEntityDef entityDef );
	
	/**
	 * Initializes the import engine with the current entity definition.
	 * 
	 * @throws SQLException if a database error occurs during initialization
	 */
	public void init() throws SQLException;

	/**
	 * Cleans up any resources that the import engine used to import data for
	 * the current entity definition.
	 * 
	 * @throws SQLException if a database error occurs during cleanup
	 */
	public void cleanup() throws SQLException;
		
	/**
	 * Imports the given set of values as a row into the database
	 * 
	 * @param values the column values for the row
	 * @throws SQLException if a database error occurs during the import
	 */
	public void importRow( ColumnValue[] values ) throws SQLException, MalformedDataException;
	
	/** 
	 * Sets whether the import will use batch mode.
	 * 
	 * @param flag true if the import will use batch mode
	 */
	public void setBatchMode( boolean flag );
	
	/**
	 * Call executeBatch on the allocated statements.
	 * 
	 * @throws BatchUpdateException If not all rows were imported then throw a BatchUpdateException
	 */
	public void executeBatch() throws BatchUpdateException;	
}
