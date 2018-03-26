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
 * The CSVDelimiterFormatter class implements the DelimiterFormatter interface
 * to format column values in a character delimiter format.
 * Each column value is separated from another column value by a character or 
 * set of characters (ex. ','). In addition, the CSVDelimiterFormatter
 * supports the ability to enclose each column value with a character
 * or set of characters (ex. '"' ).
 * 
 * @since  	0.6
 * @author  Chris Nagy
 */
public class CSVDelimiterFormatter implements DelimiterFormatter 
{
	/**
	 * The character(s) that separate each column value.
	 */
	protected String columnDelimiter;
	
	/**
	 * The character(s) that enclose each column value.
	 */
	protected String enclosedDelimiter;
	
	/**
	 * The flag indicating each column value must be enclosed by the set
	 * of characters defined.
	 */
	protected boolean enclosedOptional = false;
	
	/**
	 * Sets the positions, in the csv, to place empty strings as their values.
	 */
	protected int[] emptyPositions;
	
	/**
	 * The initial contents of the file.
	 * @since 0.74
	 */
	protected String header;

	/**
	 * Flag to indicate that the header should be written 
	 * before the next row is.
	 * @since 0.74
	 */
	protected boolean writeHeader = true;
	
	/**
	 * The output writer.
	 */
	Writer writer;

	/**
	 * Sets the character(s) that separate each column value.
	 * 
	 * @param delimiter the column value delimiter.
	 */	
	public void setColumnDelimiter( String delimiter )
	{
		columnDelimiter = delimiter;	
	}
	
	/**
	 * Returns the character(s) that separate each column value.
	 * 
	 * @return the column value delimiter
	 */
	public String getColumnDelimiter()
	{
		return columnDelimiter;	
	}
	
	/**
	 * Sets the character(s) that enclose each column value.
	 * 
	 * @param delimiter the enclose delimiter
	 */
	public void setEnclosedDelimiter( String delimiter )
	{
		enclosedDelimiter = delimiter;		
	}
	
	/**
	 * Returns the character(s) that enclose each column value.
	 * 
	 * @return the enclose delimiter
	 */
	public String getEnclosedDelimiter()
	{
		return enclosedDelimiter;	
	}
	
	/**
	 * Sets that each column value must be enclosed by the set
	 * of characters defined.
	 * 
	 * @param optional boolean flag indicating that each column
	 * value must be enclosed.
	 */
	public void setEnclosedOptional( boolean optional )
	{
		enclosedOptional = optional;	
	}
	
	/**
	 * Returns whether each column value must be enclosed by
	 * the set of characters defined.
	 *
	 * @return boolean flag indicating that each column value
	 * must be enclosed.
	 */
	public boolean isEnclosedOptional()
	{
		return enclosedOptional;	
	}

	/**
	 * Sets the header of the output file.
	 * 
	 * @param header The initial contents of the file
	 * @since 0.74
	 */
	public void setHeader( String header )
	{
		this.header = header;
	}
	
	/**
	 * Returns the header of the output file.
	 * 
	 * @return The initial contents of the file
	 * @since 0.74
	 */
	public String getHeader()
	{
		return header;
	}
	
	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#setWriter(java.io.Writer)
	 */
	public void setWriter(Writer output) 
	{
		writer = output;
		writeHeader = true;
	}

	/**
	 * Sets the positions, in the csv, to place empty strings as their values.
	 * 
	 * @since 0.61 
	 * @param positions a comma-separated list of positions (first position = 1)
	 */
	public void setEmptyPositions(int[] positions) 
	{
		this.emptyPositions = positions;
	}
	
	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#formatValues(net.sourceforge.jdbcimporter.ColumnValue[])
	 */
	public String formatValues(ColumnValue[] values) 
	{
		StringBuffer buf = new StringBuffer("");
		int valueIndex = 0;
		int position   = 1;
		int emptyIndex = 0;
		
		while (valueIndex < values.length)
		{
			if ( position > 1 )
			{
				buf.append( columnDelimiter );
			}

			String valueStr = "";
			if ( emptyPositions == null || 
				 emptyIndex >= emptyPositions.length ||
				 (emptyIndex < emptyPositions.length && emptyPositions[emptyIndex] != position) )
			{
				valueStr = values[valueIndex].getString();
				if ( valueStr == null )
				{
					valueStr = "";
				}
				valueIndex++;
			}
			else
			{
				emptyIndex++;
			}
			boolean enclose = enclosedDelimiter != null &&  
			 ( !enclosedOptional || valueStr.indexOf( columnDelimiter ) != -1 );
			if ( enclose )
			{
				buf.append( enclosedDelimiter );
				valueStr = addEscapeCharacters( valueStr );
			}
			buf.append( valueStr );
			if ( enclose )
			{
				buf.append( enclosedDelimiter );					
			}
			position++;
		}

		if ( emptyPositions != null && 
			 emptyIndex < emptyPositions.length &&
			 emptyPositions[emptyIndex] == position )
		{
			if ( position > 1 )
			{
				buf.append( columnDelimiter );
			}
			String valueStr = "";
			boolean enclose = enclosedDelimiter != null &&  
			 ( !enclosedOptional || valueStr.indexOf( columnDelimiter ) != -1 );
			if ( enclose )
			{
				buf.append( enclosedDelimiter );			
			}
			buf.append( valueStr );
			if ( enclose )
			{
				buf.append( enclosedDelimiter );					
			}
		}
		return buf.toString();
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#writeNextRow(java.lang.String)
	 */
	public void writeNextRow(String row) throws IOException 
	{
		if ( writeHeader )
		{
			if ( header != null )
			{
				writer.write( header );
				if ( !header.endsWith( "\n") )
				{
					writer.write('\n');
				}
			}
			writeHeader = false;
		}
		writer.write(row);
		writer.write('\n');
	}
	
	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#finish()
	 */
	public void finish() throws IOException
	{
		// do nothing
	}
	
	/**
	 * add escaped characters to the next value.
	 * 
	 * @param nextValue the next value
	 * @return the next value
	 */
	private String addEscapeCharacters( String nextValue )
	{
		StringBuffer buf = new StringBuffer(""); 
		int start = 0;
		int nextEscapePos = nextValue.indexOf( enclosedDelimiter );
		while ( nextEscapePos != -1 )
		{
			buf.append( nextValue.substring( start, nextEscapePos ) );
			buf.append( enclosedDelimiter );
			buf.append( enclosedDelimiter );
			start = nextEscapePos + enclosedDelimiter.length();
			nextEscapePos = nextValue.indexOf( enclosedDelimiter, start );
		}
		if ( start < nextValue.length() )
		{
			buf.append( nextValue.substring( start ) );
		}
		return buf.toString();
	}
}
