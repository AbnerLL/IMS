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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The DateRangeValueGenerator generates random values by selecting
 * a date in the defined date range. By default, the range is split up
 * by days. It can configured by setting the increment property.
 * No dependencies can be defined when using this column value generator.
 * 
 * @version  0.6
 * @author     Chris Nagy
 */
public class DateRangeValueGenerator extends ColumnValueGeneratorBase 
{
	/**
	 * The string representing the start date.
	 */
	String startStr = null;
	
	/**
	 * The string representing the end date.
	 */
	String endStr   = null;
	
	/**
	 * The string representing the increment date/time.
	 */
	String incrementStr = null;

	/**
	 * The start date.
	 */
	Date start = null;
	
	/**
	 * The end date.
	 */
	Date end   = null;
	
	/**
	 * The range in milliseconds.
	 */
	long rangeInMillis = -1;
	
	/**
	 * The number of choices in the range.
	 */
	long numChoices = -1;
	
	/**
	 * The increment date/time in milliseconds.
	 */
	long incrementInMillis = 0;
	
	/**
	 * Sets the start date. The date format should be the same as the
	 * formatted string that will be generated for the column value.
	 * 
	 * @param val the start date string
	 */
	public void setStart( String val )
	{
		startStr = val;
	}
	
	/**
	 * Sets the end date. The date format should be the same as the
	 * formatted string that will be generated for the column value.
	 * 
	 * @param val the end date string
	 */
	public void setEnd( String val )
	{
		endStr = val;
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
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[0];
	}

	/**
	 * Parse the string value into a date.
	 * 
	 * @param val the string representation of the date
	 * @return the date
	 */		
	protected Date parseDate( String val )
	{
		try
		{
			return (Date) columnDef.getFormat().parseObject( val );
		}
		catch ( ParseException e )
		{
		}
		return null;
	}
	
	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue( int row, Random r )
	{
		Calendar cal = Calendar.getInstance();
		if ( rangeInMillis == -1 )
		{
			start = parseDate( startStr );
			end   = parseDate( endStr );	
			incrementInMillis = parseIncrement();
			rangeInMillis = end.getTime() - start.getTime();
			numChoices = rangeInMillis / incrementInMillis;
		}
		ColumnValue val = new ColumnValue();
		if ( numChoices == 0 )
		{
			val.setString( columnDef.getFormat().format( start ) );	
		}
		else
		{
			long offset = Math.abs(r.nextLong() % numChoices);
			Date date = new Date( start.getTime() + (offset*incrementInMillis) );
			val.setString( columnDef.getFormat().format( date ) );	
		}
		return val;
	}

	/**
	 * Parses the increment date string and returns
	 * the increment as number of milliseconds.
	 * 
	 * @return increment in milliseconds
	 */
	protected long parseIncrement()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,0);
		cal.set(Calendar.MONTH,0);
		cal.set(Calendar.DAY_OF_MONTH, 0 );
		cal.set(Calendar.HOUR, 0 );
		cal.set(Calendar.MINUTE, 0 );
		cal.set(Calendar.SECOND, 0 );
		cal.set(Calendar.MILLISECOND, 0 );
		if ( incrementStr == null)
		{
			Calendar cloneCal = (Calendar) cal.clone();
			cloneCal.add(Calendar.DAY_OF_MONTH,1);
			Date increment = cloneCal.getTime();
			return increment.getTime() - cal.getTime().getTime();
		}
		else
		{
			SimpleDateFormat format = (SimpleDateFormat) columnDef.getFormat();
			String pattern = format.toPattern();
			
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int second = 0;
			
			// TODO: Make it more robust
			for ( int i = 0; i < pattern.length() && i < incrementStr.length(); i++ )
			{
				if ( pattern.charAt(i) == 'y' )
				{
					if ( year > 0 )
					{
						year *= 10;
					}
					year += Integer.parseInt( new Character( incrementStr.charAt(i) ).toString() );
				}
				else if ( pattern.charAt(i) == 'M' ) // What if its the month name?
				{
					if ( month > 0 )
					{
						month *= 10;
					}
					month += Integer.parseInt( new Character( incrementStr.charAt( i ) ).toString() );
				}
				else if ( pattern.charAt(i) == 'd' )
				{
					if ( day > 0 )
					{
						day *= 10;
					}
					day += Integer.parseInt( new Character( incrementStr.charAt(i) ).toString() );
				}
				else if ( pattern.charAt(i) == 'H' )
				{
					if ( hour > 0 )
					{
						hour *= 10;
					}
					hour += Integer.parseInt( new Character( incrementStr.charAt(i) ).toString() );
				}
				else if ( pattern.charAt(i) == 'm' )
				{
					if ( minute > 0 )
					{
						minute *= 10;
					}
					minute += Integer.parseInt( new Character( incrementStr.charAt(i) ).toString() );
				}
				else if ( pattern.charAt(i) == 's' )
				{
					if ( second > 0 )
					{
						second *= 10;
					}
					second += Integer.parseInt( new Character( incrementStr.charAt(i) ).toString() );
				}
			}
		
			Calendar cloneCal = (Calendar) cal.clone();
			cloneCal.add(Calendar.YEAR,year);
			cloneCal.add(Calendar.MONTH,month);
			cloneCal.add(Calendar.DAY_OF_MONTH,day);
			cloneCal.add(Calendar.HOUR,hour);
			cloneCal.add(Calendar.MINUTE,minute);
			cloneCal.add(Calendar.SECOND,second);
			Date increment = cloneCal.getTime();
			return increment.getTime() - cal.getTime().getTime();
		}
	}
}
