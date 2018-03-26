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

import java.util.Random;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The DependentChoiceValueGenerator class generates random values by selecting
 * a value from another columns' generated values.
 * The other column must be specified by setting the 'dependency' property.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class DependentChoiceValueGenerator extends ColumnValueGeneratorBase 
{
	/**
	 * The column whose values will be available to choose from.
	 */
	String dependentField = null;
	 
	/**
	 * Set the column whose values will be available to choose from.
	 * 
	 * @param field the column name
	 */
	public void setDependency( String field )
	{
		dependentField = field;
	}

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies() 
	{
		return new String[] { dependentField };
	}

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue( int row, Random r )
	{
		ColumnValue result = new ColumnValue();
		String[] dependency = ( String[] ) dependentValues.get( dependentField );
		if ( dependency == null )
		{
			return result;
		}
		if ( dependency.length > 1 )
		{
			int nextIndex = Math.abs( r.nextInt() % dependency.length );
			result.setString( dependency[nextIndex] );
		}
		else if ( dependency.length == 1)
		{
			result.setString( dependency[0] );
		}
		return result;
	}

}
