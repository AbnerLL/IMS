package net.sourceforge.datagenerator.generator;

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

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Random;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The NumberSequenceValueGenerator generates values by incrementing a number.
 * By default, the number is incremented by one. This can configured by setting
 * the increment property.
 * No dependencies can be defined when using this column value generator.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class NumberSequenceValueGenerator extends ColumnValueGeneratorBase 
{
	/**
	 * The string representing the start number.
	 */
	String startStr = null;
	
	/**
	 * The string representing the end number.
	 */
	String incrementStr = null;

	/**
	 * The current number.
	 */
	Number current = null;
	
	/**
	 * The increment.
	 */
	Number increment = null;
	
	/**
	 * Set the start number.
	 * 
	 * @param val the start number string
	 */
	public void setStart( String val )
	{
		startStr = val;
	}
		
	/**
	 * Set the increment number that will added to the subsequent generated
	 * value.
	 * 
	 * @param val the increment number string
	 */
	public void setIncrement ( String val )
	{
		incrementStr = val;
	}

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies() {
		return new String[0];
	}

	/**
	 * Parse the string into a number.
	 * 
	 * @param val the string representation of the number
	 * @return the number
	 */
	protected Number parseNumber( String val )
	{
		try
		{
			return (Number) columnDef.getFormat().parseObject( val );
		}
		catch ( ParseException e )
		{
			
		}
		return null;
	}

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue( int row, Random r) 
	{
		if ( current == null )
		{
			current = parseNumber( startStr );
			if ( incrementStr != null )
			{
				increment = parseNumber( incrementStr );
			}
			else
			{
				increment = new Integer(1);
			}
		}
		Number nextNumber = current;
		current = new BigDecimal( current.toString() ).add( new BigDecimal( increment.toString() ) );
		ColumnValue val = new ColumnValue();
		val.setString( columnDef.getFormat().format( nextNumber ) );
		return val;
	}

}
