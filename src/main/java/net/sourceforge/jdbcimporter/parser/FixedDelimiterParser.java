package net.sourceforge.jdbcimporter.parser;

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
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.MalformedDataException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The FixedDelimiterParser class implements the DelimiterParser interface
 * to parse column values from a fixed file format. Each column value is taken
 * from a section of the line (specified by the columnPositions property).
 * 
 * @since      0.6
 * @author     Chris Nagy
 */
public class FixedDelimiterParser implements DelimiterParser 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( FixedDelimiterParser.class );

	/**
	 * The input reader.
	 */
	protected BufferedReader reader;

	/**
	 * The number range for each column.
	 */
	protected int[][] columnPositions;
	
	/**
	 * The length of each row in the file.
	 */	
	protected int     length = -1;
	
	/**
	 * Sets the number range for each column value. A number range is specified by two numbers :
	 * the starting position and the ending position). The size of the first dimension should be
	 * equal to the number of columns to be parsed. The size of the second dimension should be
	 * 2, where the first value is the starting position and the second value is the ending position.
	 * The starting position should be greater then the ending position and the both values should
	 * be greater than zero.
	 * Example: 
	 * <pre>
	 * setColumnPositions( new int[][] { { 1, 5 }, { 16, 20 }, { 6, 15 } } )
	 * </pre>
	 * This will setup the FixedDelimiterParser to parse three column values: the first
	 * column value is stored from the 1st character to the 5th character, the second
	 * column value is stored from the 15th character to the 20th character, and 
	 * the last column value is stored from the 6th character to the 15th character.
	 *    
	 * @param positions The number ranges for all column values
	 */
	public void setColumnPositions( int[][] positions )
	{
		columnPositions = positions;
	}

	/**
	 * Sets the length of each row.
	 * 
	 * @since 0.61
	 * @param len the length
	 */
	public void setLength( int len )
	{
		if ( len > 0 )
		{
			this.length = len;
		}
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#setReader(Reader)
	 */
	public void setReader( Reader input ) 
	{
		reader = new BufferedReader( input );
	}

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getNextRow()
	 */
	public String getNextRow() throws IOException
	{
		if ( length == -1 )
		{
			return reader.readLine();
		}
		else
		{
			char[] values = new char[length];
			int nextLength = reader.read(values);
			if ( nextLength < length )
			{
				return null;
			}
			return new String( values );
		}
	}

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getValues(String)
	 */
	public ColumnValue[] getValues(String nextRow) throws MalformedDataException
	{
		ColumnValue[] vals = new ColumnValue[columnPositions.length];
		
		for ( int i = 0; i < columnPositions.length; i++ )
		{
			if ( nextRow.length() < columnPositions[i][1] )
			{
				throw new MalformedDataException("Row is too short : "+nextRow.length()+" < "+columnPositions[i][1], nextRow, null );
			}
			vals[i] = new ColumnValue();
			String nextValue = nextRow.substring( columnPositions[i][0]-1, columnPositions[i][1] );
			LOG.debug( "Next Value '"+nextValue+"'");
			vals[i].setString( nextValue );
		}
		return vals;
	}

}
