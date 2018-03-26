package net.sourceforge.jdbcimporter.ant;

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
 
import net.sourceforge.jdbcimporter.ColumnDef;

import org.apache.tools.ant.BuildException;

/**
 * The ColumnDefElement provides an Ant wrapper for setting a
 * ColumnDef object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class ColumnDefElement 
{

	/**
	 * The wrapped column definition.
	 */
	protected ColumnDef columnDef;
		
	/**
	 * Returns the column definition.
	 * 
	 * @return the column definition
	 * @throws BuildException if the column definition's name has not been set.
	 */
	public ColumnDef getColumnDef() throws BuildException
	{
		if ( columnDef.getName() == null || "".equals( columnDef.getName() ) )
		{
			throw new BuildException("Required attribute 'name' missing from 'column' element");
		}
		return columnDef;
	}
	
	/**
	 * Sets the name of the column.
	 * 
	 * @param newName name of the column
	 */	
	public void setName( String newName )
	{
		columnDef.setName( newName );
	}
		
	/**
	 * Sets the sql type of the column.
	 * 
	 * @param newType name (case-insensitive) of the sql type that equals
	 * the name of a static variable defined in java.sql.Types
	 */
	public void setSQLType( String newType )
	{
		columnDef.setSQLType( newType );
	}
		
	/**
	 * Sets the default value of the column. The default value
	 * of the column is applied when a row's column value is null
	 * or an empty String.
	 * 
	 * @param newDefaultValue default value for this column
	 */
	public void setDefaultValue( String newDefaultValue )
	{
		columnDef.setDefaultValue( newDefaultValue );
	}
		
	/**
	 * Sets the format pattern used when parsing the data in the file.
	 * The pattern syntax is taken from java.text.SimpleDataFormat for
	 * date, time, and timestamp column types.
	 * 
	 * @param pattern the format pattern
	 */
	public void setFormat( String pattern )
	{
		columnDef.setFormatPattern( pattern );
	}
	
	/**
	 * Adds a configured property element to the column def. The property
	 * element must have a non-null name and value.
	 * 
	 * @param element the property element
	 */
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( element.getName() == null || element.getValue() == null )
		{
			// TODO: Error Logging
			return; 
		}
		
		columnDef.addProperty( element.getName(), element.getValue() );
	}
}
