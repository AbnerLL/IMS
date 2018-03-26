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
import java.io.Reader;

/**
 * The DelimiterParser interface defines a set of methods used to parse a file
 * into a set of rows to be added to the database.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public interface DelimiterParser
{
	/**
	 * Sets the input source for the parser.
	 * 
	 * @param input the source of the data to import.
	 */
	public void setReader( Reader input );
	
	/**
	 * Returns the next row to be imported as a String.
	 * 
	 * @return the next row to import
	 * @throws IOException if an error occurs while retrieving the next row
	 */
	public String   getNextRow() throws IOException;
	
	/**
	 * Returns the column values for the row specified.
	 * 
	 * @param nextRow the row to be parsed into column values
	 * @return the column values
	 * @throws MalformedDataException if the row is not formatted properly
	 */
	public ColumnValue[] getValues(String nextRow) throws MalformedDataException;
}
