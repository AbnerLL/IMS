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
import net.sourceforge.jdbcimporter.util.CustomMessageFormat;

/**
 * The PatternDelimiterFormatter class implements the DelimiterFormatter interface
 * to format column values in a java.text.MessageFormat pattern. It uses a custom
 * Format class that includes several enhancements. The first column value will
 * be placed into the pattern where '{1}' is found.
 * 
 * @version 	0.61
 * @since       0.61
 * @see        net.sourceforge.jdbcimporter.util.CustomMessageFormat
 * @author     Chris Nagy
 */
public class PatternDelimiterFormatter implements DelimiterFormatter
{
	/**
	 * The MessageFormat used to format column values into a string.
	 */
	CustomMessageFormat format;

	/**
	 * The output writer.
	 */
	Writer writer;
	
	/**
	 * Sets the pattern to use when formatting the values.
	 * 
	 * @param pattern the pattern
	 */
	public void setPattern( String pattern )
	{
		format = new CustomMessageFormat( pattern );
	}
	
	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#setWriter(java.io.Writer)
	 */
	public void setWriter( Writer output )
	{
		writer = output;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#formatValues(net.sourceforge.jdbcimporter.ColumnValue[])
	 */
	public String formatValues( ColumnValue[] values )
	{
		Object[] vals = new Object[ values.length + 1];
		vals[0] = null;
		for ( int i = 0; i < values.length; i++ )
		{
			vals[i+1] = values[i].getString();
		}
		return format.format( vals );
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#writeNextRow(java.lang.String)
	 */
	public void writeNextRow( String row ) throws IOException
	{
		writer.write( row );
		writer.write('\n');
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#finish()
	 */
	public void finish( ) throws IOException
	{
		// do nothing
	}

}
