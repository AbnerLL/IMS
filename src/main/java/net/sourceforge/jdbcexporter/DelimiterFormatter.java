package net.sourceforge.jdbcexporter;

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
import java.io.Writer;

import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The DelimiterFormatter interface defines a set of methods used to write
 * a set of rows into a file.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public interface DelimiterFormatter 
{
	/**
	 * Sets the output source for the formatter.
	 * 
	 * @param output the destination of the data
	 */
	public void setWriter( Writer output );

	/**
	 * Creates a string representing the row of column values.
	 * 
	 * @param values the column values
	 * @return the string
	 */	
	public String formatValues( ColumnValue[] values );

	/**
	 * Writes the next row to the output source.
	 * 
	 * @param row the next row
	 * @throws IOException if an error occurs while writing the next row
	 */
	public void writeNextRow( String row ) throws IOException;
	
	/**
	 * Finishs the writing of rows to the output source.
	 */
	public void finish() throws IOException;
}
