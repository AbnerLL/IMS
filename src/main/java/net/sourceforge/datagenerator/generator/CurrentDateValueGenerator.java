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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The CurrentDateValueGenerator generates the current date/time. An increment can
 * be added or subtracted from the current date/time.
 * No dependencies can be defined when using this column value generator.
 * 
 * @version  0.70
 * @author     Chris Nagy
 */
public class CurrentDateValueGenerator extends ColumnValueGeneratorBase
{
	/**
	 * The string representing the increment date/time.
	 */
	String incrementStr = null;

	/**
	 * The date returned as the value.
	 */
	Calendar date = null;
		
	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[0];
	}

	/**
	 * Sets the increment date that will split the range into a finite
	 * set. The date format should be the same as the formatted string
	 * that will be generated for the column value.
	 * 
	 * @param val the increment date string
	 */
	public void setIncrement ( String val )
	{
		incrementStr = val;
	}
	
	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue(int row, Random r)
	{
		if ( date == null )
		{
			date = Calendar.getInstance();
			if ( incrementStr != null )
			{
				applyIncrement(date);
			}
		}

		ColumnValue val = new ColumnValue();
		val.setString( columnDef.getFormat().format( date.getTime() ) );
		return val;
	}

	/**
	 * Parses the increment date string and adds it to the 
	 * given calendar.
	 * 
	 * @param cal the date
	 */
	protected void applyIncrement( Calendar cal )
	{
		if ( incrementStr == null)
		{
			return;
		}

		SimpleDateFormat format = (SimpleDateFormat) columnDef.getFormat();
		String pattern = format.toPattern();
		
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;

		int sign = 1;
		int incrementStrOffset = 0;
		if ( incrementStr.charAt(0) == '-' )
		{
			sign = -1;
			incrementStrOffset = 1;
		}
		
		// TODO: Make it more robust
		for ( int i = 0; i < pattern.length() && i + incrementStrOffset < incrementStr.length(); i++ )
		{
			if ( pattern.charAt(i) == 'y' )
			{
				if ( year > 0 )
				{
					year *= 10;
				}
				year += Integer.parseInt( new Character( incrementStr.charAt(i+incrementStrOffset) ).toString() );
			}
			else if ( pattern.charAt(i) == 'M' ) // What if its the month name?
			{
				if ( month > 0 )
				{
					month *= 10;
				}
				month += Integer.parseInt( new Character( incrementStr.charAt(i+incrementStrOffset) ).toString() );
			}
			else if ( pattern.charAt(i) == 'd' )
			{
				if ( day > 0 )
				{
					day *= 10;
				}
				day += Integer.parseInt( new Character( incrementStr.charAt(i+incrementStrOffset) ).toString() );
			}
			else if ( pattern.charAt(i) == 'H' )
			{
				if ( hour > 0 )
				{
					hour *= 10;
				}
				hour += Integer.parseInt( new Character( incrementStr.charAt(i+incrementStrOffset) ).toString() );
			}
			else if ( pattern.charAt(i) == 'm' )
			{
				if ( minute > 0 )
				{
					minute *= 10;
				}
				minute += Integer.parseInt( new Character( incrementStr.charAt(i+incrementStrOffset) ).toString() );
			}
			else if ( pattern.charAt(i) == 's' )
			{
				if ( second > 0 )
				{
					second *= 10;
				}
				second += Integer.parseInt( new Character( incrementStr.charAt(i+incrementStrOffset) ).toString() );
			}
		}
	
		cal.add(Calendar.YEAR,sign*year);
		cal.add(Calendar.MONTH,sign*month);
		cal.add(Calendar.DAY_OF_MONTH,sign*day);
		cal.add(Calendar.HOUR,sign*hour);
		cal.add(Calendar.MINUTE,sign*minute);
		cal.add(Calendar.SECOND,sign*second);
	}	
}
