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

/**
 * The PropertyElement provides an Ant wrapper for setting a
 * property into a connection definition, delimiter parser
 * or plugin mapping.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class PropertyElement 
{
	/**
	 * The name of the property.
	 */
	protected String name;
	
	/**
	 * The value of the property.
	 */
	protected String value;
	
	/**
	 * Sets the name of the property.
	 * 
	 * @param nam the name of the property
	 */
	public void setName( String nam )
	{
		name = nam;
	}
	
	/**
	 * Sets the value of the property.
	 * 
	 * @param val the value of the property
	 */
	public void setValue( String val )
	{
		value = val;
	}

	/**
	 * Returns the name of the property.
	 * 
	 * @return name
	 */
	public String getName()
	{
		return name;	
	}
	
	/**
	 * Returns the value of the property.
	 * 
	 * @return value
	 */
	public String getValue()
	{
		return value;
	}
}
