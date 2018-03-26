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
import java.text.ParseException;

import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.MalformedDataException;
import net.sourceforge.jdbcimporter.util.CustomMessageFormat;

/**
 * The PatternDelimiterParser class implements the DelimiterParser interface
 * to parse column values based on a java.text.MessageFormat pattern. It uses a custom
 * Format class that includes several enhancements. The first column value will
 * be parsed from the string where '{1}' is found in the pattern.
 * 
 * @version 	0.61
 * @see        net.sourceforge.jdbcimporter.util.CustomMessageFormat
 * @author     Chris Nagy
 */
public class PatternDelimiterParser implements DelimiterParser
{
	/**
	 * The input reader.
	 */
	BufferedReader reader;
	
	/**
	 * The MessageFormat used to parse column values from a string.
	 */	
	CustomMessageFormat format;
	
	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#setReader(java.io.Reader)
	 */
	public void setReader( Reader input )
	{
		reader = new BufferedReader( input );
	}

	/**
	 * Sets the pattern to use when parsing values.
	 * 
	 * @param pattern the pattern
	 */
	public void setPattern( String pattern )
	{
		format = new CustomMessageFormat( pattern );
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getNextRow()
	 */
	public String getNextRow( ) throws IOException
	{
		return reader.readLine();
	}

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getValues(java.lang.String)
	 */
	public ColumnValue[] getValues( String nextRow )
			throws MalformedDataException
	{
		try
		{
			Object[] vals = (Object[]) format.parseObject( nextRow );
			ColumnValue[] returnVals = new ColumnValue[ vals.length - 1];
			for ( int i = 1; i < vals.length; i++ )
			{
				returnVals[i-1] = new ColumnValue();
				if ( vals[i] != null )
					returnVals[i-1].setString( vals[i].toString() );
			}
			return returnVals;
		}
		catch ( ParseException p )
		{
			throw new MalformedDataException( "Could not parse next row", p );
		}
	}

}
