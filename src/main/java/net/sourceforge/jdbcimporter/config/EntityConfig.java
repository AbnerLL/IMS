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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The EntityConfig class parses a Node to create an EntityDef.
 * The Node's name must be 'entity'.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class EntityConfig
{
	/** 
	 * The attribute name that contains the name of the catalog.
	 */
	public static final String ENTITY_CATALOG_ATTR = "catalog";
	
	/**
	 * The attribute name that contains the name of the schema.
	 */
	public static final String ENTITY_SCHEMA_ATTR  = "schema";
	
	/**
	 * The attribute name that contains the name of the table.
	 */
	public static final String ENTITY_TABLE_ATTR   = "table";
	
	/**
	 * The attribute name that contains the name of the table.
	 */
	public static final String ENTITY_WHERECLAUSE_ATTR   = "whereClause";
	
	/**
	 * The attribute name that contains the name of the table.
	 */
	public static final String ENTITY_QUERYSTR_ATTR   = "queryStr";
			
	/**
	 * The element name that contains the ColumnDef.
	 */
	public static final String COLUMN_TAG = "column";
	
	/**
	 * The attribute name in the COLUMN_TAG element that contains
	 * the name of the column.
	 */
	public static final String COLUMN_NAME_ATTR = "name";
	
	/**
	 * The attribute name in the COLUMN_TAG element that contains
	 * the type of the column.
	 */
	public static final String COLUMN_SQLTYPE_ATTR = "SQLType";
	
	/**
	 * The attribute name in the COLUMN_TAG element that contains
	 * the format pattern of the column (only used for date, time and
	 * timestamp columns).
	 */
	public static final String COLUMN_FORMAT_ATTR = "format";
	
	/**
	 * The attribute name in the COLUMN_TAG element that contains
	 * the default value of the column.
	 */
	public static final String COLUMN_DEFAULT_ATTR = "defaultValue";

	/**
	 * The element name that contains the column properties.
	 */
	public static final String COLUMN_PROPERTY_TAG = "property";
	
	/**
	 * The attribute name in the COLUMN_PROPERTY_TAG element that contains
	 *  the name of the property.
	 */
	public static final String COLUMN_PROPERTY_NAME_ATTR = "name";
	
	/**
	 * The attribute name in the COLUMN_PROPERTY_TAG element that contains
	 *  the value of the property.
	 */
	public static final String COLUMN_PROPERTY_VALUE_ATTR = "value";

	/**
	 * The element name that contains the entity properties.
	 */
	public static final String ENTITY_PROPERTY_TAG = "property";
	
	/**
	 * The attribute name in the ENTITY_PROPERTY_TAG element that contains
	 *  the name of the property.
	 */
	public static final String ENTITY_PROPERTY_NAME_ATTR = "name";
	
	/**
	 * The attribute name in the ENTITY_PROPERTY_TAG element that contains
	 *  the value of the property.
	 */
	public static final String ENTITY_PROPERTY_VALUE_ATTR = "value";
	
	/**
	 * The delegate that handles creation of EntityDef and ColumnDef
	 * objects.
	 */		
	protected EntityConfigDelegate delegate;
	
	/**
	 * Set the delegate that handles creation of EntityDef and
	 * ColumnDef objects.
	 * 
	 * @param delegate the delegate
	 */	
	public void setDelegate( EntityConfigDelegate delegate )
	{
		this.delegate = delegate;
	}
	
	/**
	 * Returns the EntityDef defined in the given Node.
	 * 
	 * @param node the Node
	 * @return the entity definition
	 * @throws DOMException if the Node has missing or invalid attributes/elements
	 */
	public EntityDef getEntity( Node node ) throws DOMException, InvalidCustomObjectDefException
	{
		EntityDef entityDef = delegate.createEntityDef(node);
		NamedNodeMap attributes = node.getAttributes();
		Node table = attributes.getNamedItem( ENTITY_TABLE_ATTR );
		if ( table == null || "".equals( table.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, ImportConfig.ENTITY_TAG + " attribute: "+ ENTITY_TABLE_ATTR );	
		}
		entityDef.setTable(table.getNodeValue());
		
		Node whereClause = attributes.getNamedItem( ENTITY_WHERECLAUSE_ATTR );
		if ( whereClause != null && !"".equals( whereClause.getNodeValue() ) )
		{
			entityDef.setWhereClause(whereClause.getNodeValue());
		}
		
		Node queryStr = attributes.getNamedItem( ENTITY_QUERYSTR_ATTR );
		if ( queryStr != null && !"".equals( queryStr.getNodeValue() ) )
		{
			entityDef.setQueryStr(queryStr.getNodeValue());
		}
		
		Node catalog = attributes.getNamedItem( ENTITY_CATALOG_ATTR );
		if ( catalog != null && !"".equals( catalog.getNodeValue() ) )
		{
			entityDef.setCatalog( catalog.getNodeValue() );
		}
	
		Node schema  = attributes.getNamedItem( ENTITY_SCHEMA_ATTR );
		if ( schema != null && !"".equals( schema.getNodeValue() ) )
		{
			entityDef.setSchema( schema.getNodeValue() );	
		}	
	
		Node child = ImportConfig.getFirstElementChild(node);
		while ( child != null && ENTITY_PROPERTY_TAG.equals( child.getNodeName() ))
		{
			NamedNodeMap childAttrs = child.getAttributes();
			Node propertyName  = childAttrs.getNamedItem( ENTITY_PROPERTY_NAME_ATTR );
			Node propertyValue = childAttrs.getNamedItem( ENTITY_PROPERTY_VALUE_ATTR );
			if ( propertyName != null && !"".equals( propertyName.getNodeValue() ) && 
				 propertyValue != null && !"".equals( propertyValue.getNodeValue() ) )
			{
				entityDef.addProperty(propertyName.getNodeValue(),propertyValue.getNodeValue());
			}
			child = ImportConfig.getNextElementSibling( child );
		}
		
		child = ImportConfig.getFirstElementChild(node);
		while ( child != null && !COLUMN_TAG.equals( child.getNodeName() ) )
		{
			child = ImportConfig.getNextElementSibling( child );
		}
		
		Node column = child;
		while ( column != null )
		{
			ImportConfig.assertNodeName( COLUMN_TAG, column );
		
			addColumn( entityDef, column );
			column = ImportConfig.getNextElementSibling(column);
		}
		return entityDef;
	}
	
	/**
	 * Add a column represented by the XML node to the entity def.
	 * 
	 * @param entityDef the entity definition
	 * @param column the node
	 * @throws DOMException if the node has missing attributes/elements
	 * @throws InvalidCustomObjectDefException if a custom object could not
	 * be created from their respective factories
	 */
	protected void addColumn( EntityDef entityDef, Node column ) throws DOMException, InvalidCustomObjectDefException
	{
		ColumnDef columnDef = delegate.createColumnDef(column);
		
		NamedNodeMap attributes = column.getAttributes();
		Node name = attributes.getNamedItem(COLUMN_NAME_ATTR);
		if ( name == null || "".equals( name.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, COLUMN_TAG + " attribute: "+ COLUMN_SQLTYPE_ATTR );
		}
		columnDef.setName( name.getNodeValue() );
		
		Node type = attributes.getNamedItem( COLUMN_SQLTYPE_ATTR );
		if ( type != null && !"".equals( type.getNodeValue() ) )
		{
			columnDef.setSQLType( type.getNodeValue() );		
		}
	
		Node format = attributes.getNamedItem( COLUMN_FORMAT_ATTR );
		String formatPattern = null;
		if ( format != null && !"".equals( format.getNodeValue() ) )
		{
			formatPattern = format.getNodeValue();
			columnDef.setFormatPattern(formatPattern);
		}
		
		Node defaultValue = attributes.getNamedItem( COLUMN_DEFAULT_ATTR );
		if ( defaultValue != null && !"".equals( defaultValue.getNodeValue() ) )
		{
			columnDef.setDefaultValue( defaultValue.getNodeValue() );	
		}
		
		//
		Node child = ImportConfig.getFirstElementChild( column );
		while ( child != null )
		{
			if ( COLUMN_PROPERTY_TAG.equals( child.getNodeName() ) )
			{
				NamedNodeMap childAttrs = child.getAttributes();
				Node propertyName  = childAttrs.getNamedItem( COLUMN_PROPERTY_NAME_ATTR );
				Node propertyValue = childAttrs.getNamedItem( COLUMN_PROPERTY_VALUE_ATTR );
				if ( propertyName != null && !"".equals( propertyName.getNodeValue() ) && 
				     propertyValue != null && !"".equals( propertyValue.getNodeValue() ) )
				{
					columnDef.addProperty(propertyName.getNodeValue(),propertyValue.getNodeValue());
				}
			}
			child = ImportConfig.getNextElementSibling( child );
		}
		entityDef.addColumn( columnDef );
	}	
}
