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
 
package net.sourceforge.jdbcimporter.util;

import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;

/**
 * The IntegerArrayEditor converts a comma-separated list of numbers into
 * an array of integers. The array should be in ascending order.
 * 
 * @version 0.61
 * @author Chris Nagy
 */
public class IntegerArrayEditor extends PropertyEditorSupport
{

	/**
	 * Parses a string into an array of integers that are in ascending order.
	 * 
	 * @param val the string
	 */
	public void setAsText( String val ) throws IllegalArgumentException
	{
		StringTokenizer tokenizer = new StringTokenizer( val, "," );
		int[] values = new int[tokenizer.countTokens()];
		int index = 0;
		int prevValue = -1;
		while (tokenizer.hasMoreTokens())
		{
			String nextValueStr = tokenizer.nextToken();
			try
			{
				int nextValue = Integer.parseInt( nextValueStr );
				if ( nextValue <= prevValue )
				{
					throw new IllegalArgumentException( 
						"Invalid integer : "+nextValue+" <= Previous integer : "+prevValue);
				}
				values[index] = nextValue;
				prevValue = nextValue;
			}
			catch ( NumberFormatException n )
			{
				throw new IllegalArgumentException( "Invalid integer '"+nextValueStr+"'" );
			}
			index++;
		}
		setValue( values ); 
	}

}
