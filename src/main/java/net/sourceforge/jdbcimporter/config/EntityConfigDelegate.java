package net.sourceforge.jdbcimporter.config;

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
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 * The EntityConfigDelegate interface is a delegate interface used by the
 * EntityConfig to create EntityDef and ColumnDef objects.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public interface EntityConfigDelegate 
{
	/**
	 * Create an EntityDef from the given node and apply any custom attributes.
	 * The following are standard attributes that are applied by the EntityConfig:
	 * catalog, schema, table.
	 * 
	 * @param node the element node with the name 'entity'
	 * @return the EntityDef
	 * @throws DOMException if any custom attributes are missing or invalid
	 * @throws InvalidCustomObjectDefException if the custom EntityDef could not be created
	 */
	public EntityDef createEntityDef( Node node ) throws DOMException, InvalidCustomObjectDefException;
	
	/**
	 * Create a ColumnDef from the given node and apply any custom attributes.
	 * The following are standard attributes that are applied by the EntityConfig:
	 * name, SQLType, format, default.
	 * 
	 * @param node the element node with the name 'column'
	 * @return the ColumnDef
	 * @throws DOMException if any custom attributes are missing or invalid
	 * @throws InvalidCustomObjectDefException if the custom ColumnDef could not be created
	 */
	public ColumnDef createColumnDef( Node node ) throws DOMException, InvalidCustomObjectDefException;
}
