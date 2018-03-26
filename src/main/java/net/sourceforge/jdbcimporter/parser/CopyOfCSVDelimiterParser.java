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
import java.util.ArrayList;

import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.MalformedDataException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The CSVDelimiterParser class implements the DelimiterParser interface
 * to parse character delimited column values. Each column value is separated
 * from another column value by a character or set of characters (ex. ','). In
 * addition, the CSVDelimiterParser supports the ability to enclose each
 * column value with a character or set of characters (ex. '"' ).
 * 
 * @since      0.6
 * @author     Chris Nagy
 */
public class CopyOfCSVDelimiterParser implements DelimiterParser
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( CopyOfCSVDelimiterParser.class );
	
	/**
	 * The character(s) that separate each column value.
	 */
	protected String columnDelimiter;
	
	/**
	 * The character(s) that enclose each column value.
	 */
	protected String enclosedDelimiter;
	
	/**
	 * Flag indicating that all column values are enclosed
	 * by the enclosed delimiter.
	 */
	protected boolean enclosedOptional = false;
	
	/**
	 * The column indices in the file that should be skipped.
	 */
	protected int[]   ignoredPositions;
	
	/**
	 * The input reader.
	 */
	protected BufferedReader inputReader;

	/**
	 * Flag indicating that rows could be multi-line.
	 */
	protected boolean multiline = false;
	
	/**
	 * Flag indicating that the first line should be ignored.
	 */
	protected boolean ignoreFirstLine = false;
	
	/**
	 * Flag indicating that the first line has been read from the input reader.
	 */
	protected boolean firstLine       = false;
	
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
	 * Sets the positions, in the csv, to ignore and not return
	 * as values.
	 * 
	 * @param positions a comma-separated list of positions (first position = 1)
	 */
	public void setIgnoredPositions( int[] positions )
	{
		this.ignoredPositions = positions;
	}
	
	/**
	 * Sets the flag to indicate that the csv may contain rows that span multiple
	 * lines. The enclosed delimiter must also be set.
	 * 
	 * @param flag flag to indicate that rows may span multiple lines
	 */
	public void setMultiline( boolean flag )
	{
		this.multiline = flag;		
	}
	
	/**
	 * Sets the flag to indicate that the first line should be ignored.
	 * 
	 * @param flag flag to indicate that the first line should be ignored
	 */
	public void setIgnoreFirstLine( boolean flag )
	{
		this.ignoreFirstLine = flag;
	}
	
	/**
	 * @see DelimiterParser#setReader(Reader)
	 */
	public void setReader( Reader reader )
	{
		inputReader = new BufferedReader( reader );
		firstLine = true;
	}
		
	/**
	 * @see DelimiterParser#getNextRow()
	 */
	public String getNextRow() throws IOException
	{
		String line = null;
		if ( enclosedDelimiter == null || !multiline )
		{
			line = inputReader.readLine();
		}
		else
		{
			line = this.getColumnEnclosedNextRow();
		}
		
		if ( ignoreFirstLine && firstLine )
		{
			firstLine = false;
			if ( enclosedDelimiter == null || !multiline )
			{
				line = inputReader.readLine();
			}
			else
			{
				line = this.getColumnEnclosedNextRow();
			}
		}
		return line;
	}
	
	/**
	 * @see DelimiterParser#getValues(String nextRow)
	 */
	public ColumnValue[] getValues(String nextRow) throws MalformedDataException
	{
		String line = nextRow;
		if ( enclosedDelimiter != null )
		{
			return parseColumnEnclosedDelimitedLine( line );
		}
		else
		{
			return parseColumnDelimitedLine( line );
		}
	}

	/**
	 * Parse the character delimited line into ColumnValues.
	 * 
	 * @param line the line
	 * @return column values
	 */
	protected ColumnValue[] parseColumnDelimitedLine( String line )
	{
		int nextColumn = line.indexOf( columnDelimiter );
		int nextStart = 0;
		ArrayList list = new ArrayList();
		int position = 1;
		int ignoredIndex = 0;
		while ( nextColumn >= 0 )
		{
			String next = line.substring(nextStart,nextColumn);
			if ( ignoredPositions == null || ignoredIndex >= ignoredPositions.length ||
				 (ignoredIndex < ignoredPositions.length && ignoredPositions[ignoredIndex] != position) )
			{
				LOG.debug("Next Value '"+next+"'");
				list.add( next );
			}
			else
			{
				LOG.debug("Ignoring Value '"+next+"'");
				ignoredIndex++;
			}
			nextStart = nextColumn + columnDelimiter.length();
			nextColumn = line.indexOf( columnDelimiter, nextStart );
			position++;
		}
		String next = line.substring( nextStart, line.length() );
		if ( ignoredPositions == null || ignoredIndex >= ignoredPositions.length || 
			 (ignoredIndex < ignoredPositions.length && ignoredPositions[ignoredIndex] != position) )
		{
			LOG.debug("Next Value '"+next+"'");
			list.add( next );
		}
		else
		{
			LOG.debug("Ignoring Value '"+next+"'");
			ignoredIndex++;
		}
		
		ColumnValue[] array = new ColumnValue[ list.size() ];
		for ( int i = 0; i < array.length; i++ )
		{
			array[i] = new ColumnValue();
			array[i].setString((String) list.get(i));
		}
		return array;
	}
	
	/**
	 * Parse the character delimited line, whose values are also enclosed,
	 * into ColumnValues.
	 * 
	 * @param line the line
	 * @return column values
	 * @throws MalformedDataException
	 */
	protected ColumnValue[] parseColumnEnclosedDelimitedLine( String line ) throws MalformedDataException
	{
		int nextColumn = line.indexOf( columnDelimiter );
		int nextStart  = 0;
		int nextEnclosed = line.indexOf( enclosedDelimiter );
		int position = 1;

		int ignoredIndex = 0;

		ArrayList list = new ArrayList();
		while ( nextColumn >= 0 )
		{
			if ( !enclosedOptional && nextColumn < nextEnclosed )
			{
				throw new MalformedDataException( "Column #"+position+" is not enclosed with '"+enclosedDelimiter+"'", position );
			}
			String nextValue = null;
			if ( nextEnclosed > -1 && nextEnclosed < nextColumn )
			{
				int startEnclosed = nextEnclosed;
				int endEnclosed   = findEndEnclosedDelimiter( line, startEnclosed + enclosedDelimiter.length() );		
				if ( endEnclosed < 0 )
				{
					throw new MalformedDataException( "Column #"+position+" starts with '"+enclosedDelimiter+"' but does not end with it.", position);	
				}	
				nextColumn = line.indexOf( columnDelimiter, endEnclosed + enclosedDelimiter.length() );
				if ( nextColumn != endEnclosed + enclosedDelimiter.length() && nextColumn >= 0 )
				{
					throw new MalformedDataException( "Column #"+position+" does not end with '"+columnDelimiter+"'", position);	
				}
				nextValue = removeEscapeCharacters( line.substring( startEnclosed + enclosedDelimiter.length(), endEnclosed ) );
				
				if ( nextColumn >= 0 )
				{
					nextStart = nextColumn + columnDelimiter.length();
					nextColumn = line.indexOf( columnDelimiter, nextStart );
					nextEnclosed = line.indexOf( enclosedDelimiter, nextStart );
				}
				else
				{
					nextStart = line.length();
				}
			}
			else
			{
				nextValue = line.substring( nextStart, nextColumn );
				nextStart = nextColumn + columnDelimiter.length();
				nextColumn = line.indexOf( columnDelimiter, nextStart );			
				nextEnclosed = line.indexOf( enclosedDelimiter, nextStart );
			}
			
			if ( ignoredPositions == null || ignoredIndex >= ignoredPositions.length ||
				 (ignoredIndex < ignoredPositions.length && ignoredPositions[ignoredIndex] != position) )
			{
				LOG.debug("Next Value '"+nextValue+"'");
				list.add( nextValue );
			}
			else
			{
				LOG.debug("Ignoring Value '"+nextValue+"'");
				ignoredIndex++;
			}
			position++;
		}
		if ( nextStart <= line.length() )
		{
			nextEnclosed = line.indexOf( enclosedDelimiter, nextStart );
			String nextValue = line.substring( nextStart, line.length() );
			if ( nextEnclosed >= 0 )
			{
				int startEnclosed = nextEnclosed;
				int endEnclosed   = findEndEnclosedDelimiter( line, startEnclosed + enclosedDelimiter.length() );
				if ( endEnclosed < 0 )
				{
					throw new MalformedDataException( "Column #"+position+" starts with '"+enclosedDelimiter+"' but does not end with it.", position);	
				}				
				nextValue = line.substring( startEnclosed + enclosedDelimiter.length(), endEnclosed );
			}
			nextValue = removeEscapeCharacters( nextValue );
			if ( ignoredPositions == null || ignoredIndex >= ignoredPositions.length || 
				 (ignoredIndex < ignoredPositions.length && ignoredPositions[ignoredIndex] != position) )
			{
				LOG.debug("Next Value '"+nextValue+"'");
				list.add( nextValue );
			}
			else
			{
				LOG.debug("Ignoring Value '"+nextValue+"'");
				ignoredIndex++;
			}
		}

		ColumnValue[] array = new ColumnValue[ list.size() ];
		for ( int i = 0; i < array.length; i++ )
		{
			array[i] = new ColumnValue();
			array[i].setString((String) list.get(i));
		}
		return array;
	}
	

	/**
	 * Returns the next row which may span multiple lines.
	 * 
	 * @return the next row
	 * @throws IOException
	 */
	protected String getColumnEnclosedNextRow() throws IOException
	{
		String line = null;
		StringBuffer nextRow = new StringBuffer("");

		int nextColumn   = -1;
		int nextEnclosed = -1;
		while ( nextColumn == -1 && nextEnclosed == -1 )
		{
			line = getRawNextLine();
			if ( line == null ) return line;
			
			nextColumn   = line.indexOf( columnDelimiter );
			nextEnclosed = line.indexOf( enclosedDelimiter );
		}
		
		int nextStart  = 0;
		while ( nextColumn >= 0 || nextEnclosed >= nextStart )
		{
			if ( nextEnclosed > -1 && (nextColumn == -1 || nextEnclosed < nextColumn ) )
			{
				int startEnclosed = nextEnclosed;
				int endEnclosed   = findEndEnclosedDelimiter( line, startEnclosed + enclosedDelimiter.length() );
				while ( endEnclosed < 0 )
				{
					nextRow.append( line );
					line = getRawNextLine();
					if ( line == null )
					{
						break;
					}
					endEnclosed = findEndEnclosedDelimiter( line, 0 );
				}

				if ( line == null )
				{
					break;
				}
				
				nextColumn = line.indexOf( columnDelimiter, endEnclosed + enclosedDelimiter.length() );
				if ( nextColumn >= 0 )
				{
					nextStart    = nextColumn + columnDelimiter.length();
					nextColumn   = line.indexOf( columnDelimiter, nextStart );
					nextEnclosed = line.indexOf( enclosedDelimiter, nextStart );
				}
				else
				{
					nextStart = line.length();
					nextEnclosed = -1;
				}
			}
			else
			{
				nextStart = nextColumn + columnDelimiter.length();
				nextColumn = line.indexOf( columnDelimiter, nextStart );			
				nextEnclosed = line.indexOf( enclosedDelimiter, nextStart );
			}
		}
		if ( line != null && nextStart <= line.length() )
		{
			nextEnclosed = line.indexOf( enclosedDelimiter, nextStart );
			String nextValue = line.substring( nextStart, line.length() );
			if ( nextEnclosed >= 0 )
			{
				int startEnclosed = nextEnclosed;
				int endEnclosed   = findEndEnclosedDelimiter( line, startEnclosed + enclosedDelimiter.length() );
				while ( endEnclosed < 0 )
				{
					nextRow.append( line );
					line = getRawNextLine();
					if ( line == null )
					{
						break;
					}
					endEnclosed = findEndEnclosedDelimiter( line, 0 );
				}
			}
		}
		if ( line != null )
		{
			// don't take the last character
			if ( line.endsWith("\n") || line.endsWith("\r") )
			{
				line = line.substring(0,line.length()-1);
			}
			nextRow.append( line );
		}
		return nextRow.toString();
	}
	
	/**
	 * Returns the position of the ending enclosed delimiter skipping over
	 * any escaped enclosed delimiters.
	 * 
	 * @param line the line
	 * @param startPos the start position to search for the end enclosed delimiter
	 * @return the position or -1 if not found
	 */
	private int findEndEnclosedDelimiter( String line, int startPos )
	{
		int endPos = line.indexOf( enclosedDelimiter, startPos );
		while ( endPos != -1 && enclosedDelimiter.length() < line.length() - endPos - enclosedDelimiter.length() )
		{
			String endPart = line.substring( endPos + enclosedDelimiter.length() );
			if ( !endPart.startsWith( enclosedDelimiter ) )
			{
				break;
			}
			else
			{
				startPos = endPos + (enclosedDelimiter.length()*2);
				endPos = line.indexOf( enclosedDelimiter, startPos );
			}
		}
		return endPos;
	}
	
	/**
	 * Returns the next line in the reader including the new line character.
	 * 
	 * @return the next line
	 * @throws IOException
	 */
	private String getRawNextLine() throws IOException
	{
		char[] c = new char[1];
		int    count = -1;
		StringBuffer nextLine = new StringBuffer("");
		while ( (count = inputReader.read(c) ) != -1 )
		{
			nextLine.append( c[0] );
			if ( c[0] == '\n' || c[0] == '\r' )
			{
				break;
			}
		}
		if ( nextLine.length() == 0 )
		{
			return null;
		}
		else
		{
			return nextLine.toString();
		}
	}
	
	/**
	 * Removes escaped characters from the next value.
	 * 
	 * @param nextValue the next value
	 * @return the next value
	 */
	private String removeEscapeCharacters( String nextValue )
	{
		StringBuffer buf = new StringBuffer(""); 
		int start = 0;
		int nextEscapePos = nextValue.indexOf( enclosedDelimiter + enclosedDelimiter );
		while ( nextEscapePos != -1 )
		{
			buf.append( nextValue.substring( start, nextEscapePos ) );
			buf.append( enclosedDelimiter );
			start = nextEscapePos + (enclosedDelimiter.length()*2);
			nextEscapePos = nextValue.indexOf( enclosedDelimiter + enclosedDelimiter, start );
		}
		if ( start < nextValue.length() )
		{
			buf.append( nextValue.substring( start ) );
		}
		return buf.toString();
	}
	
}
