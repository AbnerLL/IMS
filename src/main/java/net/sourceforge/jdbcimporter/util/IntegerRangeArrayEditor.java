package net.sourceforge.jdbcimporter.util;

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

import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;

/**
 * The IntegerRangeArrayEditor class parses a comma separated list of two numbers
 * with a '-' character in between (ex '1-10,13-20,21-23') into a two dimensional
 * array of integers. The first number should be less than the second number.
 * 
 * @version 0.61
 * @author Chris Nagy
 */
public class IntegerRangeArrayEditor extends PropertyEditorSupport
{
	/**
	 * Parses a string into a two dimensional array of integers.
	 */
	public void setAsText( String val ) throws IllegalArgumentException
	{
		StringTokenizer tokenizer = new StringTokenizer( val, "," );
		int[][] realColumnDefs = new int[tokenizer.countTokens()][2];
		int index = 0;
		while (tokenizer.hasMoreTokens())
		{
			String columnDef = tokenizer.nextToken();
			StringTokenizer rangeTokenizer = new StringTokenizer( columnDef, "-" );
			if ( rangeTokenizer.countTokens() != 2 )
			{
				throw new IllegalArgumentException(  "Invalid column range '"+columnDef+"'" );
			}
			try
			{
				realColumnDefs[index][0] = Integer.parseInt( rangeTokenizer.nextToken() );
				if ( realColumnDefs[index][0] < 1 )
				{
				
				}
				realColumnDefs[index][1] = Integer.parseInt( rangeTokenizer.nextToken() );
				if ( realColumnDefs[index][1] < 1 )
				{
				
				}
				if ( realColumnDefs[index][0] >= realColumnDefs[index][1] )
				{
					throw new IllegalArgumentException( 
						"Invalid column range : "+realColumnDefs[index][0]+" >= "+realColumnDefs[index][1]);
				}
				
			}
			catch ( NumberFormatException n )
			{
				throw new IllegalArgumentException( "Invalid column range '"+columnDef+"'" );
			}
			index++;
		}
		setValue( realColumnDefs ); 
	}

}
