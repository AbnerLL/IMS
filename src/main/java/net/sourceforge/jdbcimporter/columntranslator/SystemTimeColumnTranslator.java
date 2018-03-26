package net.sourceforge.jdbcimporter.columntranslator;

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
 
import java.sql.Types;
import java.util.Calendar;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;


/**
 * The SystemTimeColumnTranslator translates a specific column value (default: 'SYSTEMTIME')
 * into the current date/time if the column is of type 'DATE', 'TIMESTAMP' or 'TIME'.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class SystemTimeColumnTranslator implements ColumnTranslator 
{
	/**
	 * The default string that will be converted into the current date/time.
	 */
	public static final String SYSTEM_TIME = "SYSTEMTIME";
	
	/**
	 * The column value that will be translated
	 * into the current/date time.
	 */
	protected String translateFrom = SYSTEM_TIME;

	/**
	 * Set the column value that will be translated
	 * into the current date/time.
	 * 
	 * @param newVal the column value
	 */
	public void setTranslateFrom( String newVal )
	{
		this.translateFrom = newVal;
	}
	
	/**
	 * Returns the column value that will be translated
	 * into the current date/time.
	 * 
	 * @return the column value
	 */
	public String getTranslateFrom()
	{
		return this.translateFrom;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ColumnTranslator#getValue(ColumnDef, ColumnValue)
	 */
	public ColumnValue getValue(ColumnDef column, ColumnValue columnValue) 
	{
		if ( translateFrom.equalsIgnoreCase( columnValue.getString() ) )
		{
			switch ( column.getType() )
			{
				case Types.DATE:
				case Types.TIME:
				case Types.TIMESTAMP:
					columnValue.setString( column.getFormat().format( Calendar.getInstance().getTime() ) );
					break;
			}
		}
		return columnValue;
	}
}
