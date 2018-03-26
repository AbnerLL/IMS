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

import java.io.IOException;
import java.io.InputStream;

/**
 * The BinaryDelimiterParser interface defines a set of methods used to parse a binary file
 * into a set of rows to be added to the database.
 * 
 * @version 	0.74
 * @author     Chris Nagy
 */

public interface BinaryDelimiterParser 
{

	/**
	 * Sets the input source for the parser.
	 * 
	 * @param input the source of the data to import.
	 */
	public void setInputStream( InputStream in );
	
	/**
	 * Returns the next row to be imported as an Object.
	 * 
	 * @return the next row to import
	 * @throws IOException if an error occurs while retrieving the next row
	 */
	
	public Object getNextRow() throws IOException;
	
	/**
	 * Returns the column values for the row specified.
	 * 
	 * @param nextRow the row to be parsed into column values
	 * @return the column values
	 * @throws MalformedDataException if the row is not formatted properly
	 */
	public ColumnValue[] getValues( Object row ) throws MalformedDataException;
	
	/**
	 * Returns the String representation of the next row. The String will
	 * be used if the row is written to a log file.
	 * 
	 * @param row the row 
	 * @return The String representing the row
	 */
	public String getRowAsString( Object row );
}
