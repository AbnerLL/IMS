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

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The BooleanToIntColumnTranslator translates a column value of 'true' into an integer (default '1')
 * and 'false' into an integer (default '0') if the column is of type 'BOOLEAN'.
 * 
 * @version 	0.62
 * @author Chris Nagy
 */
public class BooleanToIntColumnTranslator implements ColumnTranslator
{
	/**
	 * The integer representing the column value 'true'.
	 */
	protected int trueValue = 1;
	
	/**
	 * The integer representing the column value 'false'.
	 */
	protected int falseValue = 0;
	
	/**
	 * Sets the integer value representing 'true'.
	 * 
	 * @param newVal the integer value
	 */
	public void setTrueValue( int newVal )
	{
		this.trueValue = newVal;
	}
	
	/**
	 * Sets the integer value representing 'false'.
	 * 
	 * @param newVal the integer value
	 */
	public void setFalseValue( int newVal )
	{
		this.falseValue = newVal;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ColumnTranslator#getValue(net.sourceforge.jdbcimporter.ColumnDef, net.sourceforge.jdbcimporter.ColumnValue)
	 */
	public ColumnValue getValue( ColumnDef column, ColumnValue columnValue )
	{
		if ( column.getType() == Types.BOOLEAN )
		{
			if ( "false".equals( columnValue.getString() ) )
			{
				columnValue.setString(Integer.toString(falseValue));
			}
			else if ( "true".equals( columnValue.getString() ) )
			{
				columnValue.setString(Integer.toString(trueValue));
			}
		}
		return columnValue;
	}
}
