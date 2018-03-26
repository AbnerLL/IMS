package net.sourceforge.jdbcexporter.formatter;

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

import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The FixedDelimiterFormatter class implements the DelimiterFormatter interface
 * to format column values in a fixed file format. Each column value is placed into
 * a section of the line (specified by the columnPositions property).
 * 
 * @since      0.6
 * @author     Chris Nagy
 */
public class FixedDelimiterFormatter implements DelimiterFormatter 
{
	/**
	 * The output writer.
	 */
	protected Writer writer;
	
	/**
	 * The number range for each column.
	 */
	protected int[][] columnPositions;
	
	/**
	 * The length of each row in the file.
	 */
	protected int length = -1;
	
	/**
	 * Sets the number range for each column value. A number range is specified by two numbers :
	 * the starting position and the ending position). The size of the first dimension should be
	 * equal to the number of columns to be formatted. The size of the second dimension should be
	 * 2, where the first value is the starting position and the second value is the ending position.
	 * The starting position should be greater then the ending position and the both values should
	 * be greater than zero.
	 * Example: 
	 * <pre>
	 * setColumnPositions( new int[][] { { 1, 5 }, { 16, 20 }, { 6, 15 } } )
	 * </pre>
	 * This will setup the FixedDelimiterFormatter to format three column values: the first
	 * column value will be stored from the 1st character to the 5th character, the second
	 * column value will be stored from the 15th character to the 20th character, and 
	 * the last column value will be stored from the 6th character to the 15th character.
	 *    
	 * @param positions the number ranges for all column values
	 */
	public void setColumnPositions( int[][] positions )
	{
		columnPositions = positions;
	}
	
	/**
	 * Sets the length of each row. If rows are longer then this value then they are 
	 * truncated. If rows are shorter then spaces are appended.
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
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#setWriter(java.io.Writer)
	 */
	public void setWriter(Writer output) {
		writer = output;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#formatValues(net.sourceforge.jdbcimporter.ColumnValue[])
	 */
	public String formatValues(ColumnValue[] values) {
		StringBuffer nextLine = new StringBuffer("");
		for ( int i = 0; i < columnPositions.length; i++ )
		{
			int start = columnPositions[i][0] - 1;
			int length = columnPositions[i][1] - columnPositions[i][0] + 1;
			String val = values[i].getString();
			if ( val == null ) 
			{
				val = "";
			}
			if ( nextLine.length() < start )
			{
				for ( int j = 0; nextLine.length() < start; j++ )
				{
					nextLine.append(' ');
				}
			}
			
			for ( int j = 0; j < length; j++ )
			{
				char nextChar = ' ';
				if ( val.length() > j )
				{
					nextChar = val.charAt(j);
				}
				
				if ( nextLine.length() > start + j )
				{
					nextLine.setCharAt(start+j, nextChar ); 
				}
				else
				{
					nextLine.append(nextChar);
				}
			}
		}
		return nextLine.toString();
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#writeNextRow(java.lang.String)
	 */
	public void writeNextRow(String row) throws IOException 
	{
		if ( length == -1 )
		{
			writer.write( row );
			writer.write( '\n' );
		}
		else
		{
			StringBuffer buf = new StringBuffer(row);
			while ( buf.length() < length )
			{
				buf.append(" ");
			}
			if ( buf.length() > length )
			{
				buf.setLength( length );
			}
			writer.write( buf.toString() );
		}
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#finish()
	 */
	public void finish() throws IOException
	{
		// do nothing
	}
}
