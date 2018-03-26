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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.MalformedDataException;

/**
 * The BinaryDelimiterParserDelegate class implements the BinaryDelimiterParser interface
 * by delegating calls to a DelimiterParser.
 *  
 * @version 0.74
 * @author Chris Nagy
 */
public class BinaryDelimiterParserDelegate implements BinaryDelimiterParser
{
	/**
	 * The DelimiterParser.
	 */
	DelimiterParser delegate;
	
	/**
	 * The encoding of the input stream.
	 */
	String          encoding;
	
	/**
	 * Creates a BinaryDelimiterParserDelegate with the given DelimiterParser and 
	 * input stream encoding.
	 * 
	 * @param delegate The DelimiterParser that will parse the input stream
	 * @param encoding The encoding of the input stream (may be null)
	 */
	public BinaryDelimiterParserDelegate( DelimiterParser delegate, String encoding )
	{
		this.delegate = delegate;
		this.encoding = encoding;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.BinaryDelimiterParser#setInputStream(java.io.InputStream)
	 */
	public void setInputStream( InputStream input )
	{
		try
		{
			if ( encoding != null )
			{
				delegate.setReader( new InputStreamReader( input, encoding ) );
			}
			else
			{
				delegate.setReader( new InputStreamReader( input ) );
			}
		}
		catch ( UnsupportedEncodingException n )
		{
			throw new RuntimeException( n.getMessage(), n );
		}
	}

	/**
	 * @see net.sourceforge.jdbcimporter.BinaryDelimiterParser#getNextRow()
	 */
	public Object getNextRow( ) throws IOException
	{
		return delegate.getNextRow();
	}

	/**
	 * @see net.sourceforge.jdbcimporter.BinaryDelimiterParser#getValues(Object)
	 */
	public ColumnValue[] getValues( Object nextRow )
			throws MalformedDataException
	{
		return delegate.getValues( (String) nextRow );
	}

	/**
	 * @see net.sourceforge.jdbcimporter.BinaryDelimiterParser#getRowAsString(Object)
	 */
	public String getRowAsString(Object nextRow) 
	{
		return (String) nextRow;
	}

}
