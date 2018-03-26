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

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.jdbcimporter.ColumnDef;

/**
 * The ColumnValueGeneratorBase class implements some general methods
 * in the ColumnValueGenerator interface.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public abstract class ColumnValueGeneratorBase implements ColumnValueGenerator 
{
	/**
	 * The map of dependent columns and their values.
	 */
	protected Map dependentValues = new HashMap();
	
	/**
	 * The column definition.
	 */
	protected ColumnDef columnDef = null;

	/**
	 * @see ColumnValueGenerator#setColumnDef(ColumnDef)
	 */	
	public void setColumnDef( ColumnDef columnDef )
	{
		this.columnDef = columnDef;	
	}
	
	/**
	 * @see ColumnValueGenerator#setDependentValue(String,String)
	 */	
	public void setDependentValue( String name, String value )
	{
		dependentValues.put( name, new String[] { value } );	
	}

	/**
	 * @see ColumnValueGenerator#setDependentValues(String,String[])
	 */	
	public void setDependentValues( String name, String[] values )
	{
		dependentValues.put( name, values );
	}

}
