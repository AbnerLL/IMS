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

import net.sourceforge.jdbcimporter.EntityDef;

/**
 * The EntityDefElement provides an Ant wrapper for setting a
 * EntityDef object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class EntityDefElement 
{	
	/**
	 * The wrapped entity definition.
	 */
	protected EntityDef entityDef;
		
		
	/**
	 * Sets the name of the schema that owns the table.
	 * 
	 * @param name name of the schema
	 */
	public void setSchema( String name )
	{
		entityDef.setSchema(name);
	}
	
	/**
	 * Sets the name of the catalog that contains the table.
	 * 
	 * @param name name of the catalog
	 */
	public void setCatalog( String name )
	{
		entityDef.setCatalog( name );
	}

	/**
	 * Sets the name of the table.
	 * 
	 * @param name name of the table
	 */	
	public void setTable( String name )
	{
		entityDef.setTable(name);
	}
	
	/**
	 * Adds a configured property element to the entity def. The property
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
		
		entityDef.addProperty( element.getName(), element.getValue() );
	}
}
