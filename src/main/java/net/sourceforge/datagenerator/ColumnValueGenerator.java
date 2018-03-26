package net.sourceforge.datagenerator;

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

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The ColumnValueGenerator generates a value for a column. The generator 
 * can depend on values generated in other columns. 
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public interface ColumnValueGenerator 
{
	/**
	 * Sets the column definition.
	 * 
	 * @param columnDef the column definition
	 */
	public void setColumnDef( ColumnDef columnDef );
	
	/**
	 * Returns the columns that this generator needs values for before
	 * it can generate a value.
	 * 
	 * @return the list of column names
	 */
	public String[] getDependencies();
	
	/**
	 * Sets the dependent value for the give column.
	 * 
	 * @param name the column name
	 * @param value the column value in string format
	 */
	public void setDependentValue( String name, String value );
	
	/**
	 * Sets the dependent values for the given column.
	 * 
	 * @param name the column name
	 * @param values the list of column values
	 */
	public void setDependentValues( String name, String[] values );
	
	/**
	 * Returns the next generated value.
	 * 
	 * @param row the row number
	 * @param r a random number generator
	 * @return the next column value
	 */ 
	public ColumnValue getNextColumnValue(int row, Random r );
}
