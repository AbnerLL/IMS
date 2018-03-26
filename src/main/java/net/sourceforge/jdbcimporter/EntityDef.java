package net.sourceforge.jdbcimporter;

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
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * The EntityDef class defines an entity (table).
 * This definition includes:
 * 
 * <ul>
 * <li> the name of table (along with the catalog and schema it belongs to)</li>
 * <li> the columns in the table</li>
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 * @see ImportEntityDef
 * @see net.sourceforge.datagenerator.GenerateEntityDef
 * @see net.sourceforge.jdbcexporter.ExportEntityDef
 */
public class EntityDef
{
	/**
	 * The name of the table.
	 */
	String table;
	
	/**
	 * The name of the schema.
	 */
	String schema;
	
	/**
	 * The name of the catalog.
	 */
	String catalog;
	
	/**
	 * The where clause.
	 */
	String whereClause;
	
	/**
	 * The sql clause.
	 */
	String queryStr;
	
	/**
	 * The properties of the entity definition.
	 */
	Properties properties;	

	/**
	 * The list of columns.
	 */
	protected List columns = new ArrayList();
	
	/**
	 * Returns the SQL where clause that will restrict the data
	 * exported.
	 * 
	 * @return where clause
	 */
	public String getWhereClause() 
	{
		return whereClause;
	}
		
	/**
	 * Set the where clause.
	 * 
	 * @param string where clause
	 */
	public void setWhereClause(String string) 
	{
		whereClause = string;
	}
	
	/**
	 * Sets the name of the table.
	 * 
	 * @param newTable name of the table
	 */
	public void setTable( String newTable )
	{
		table = newTable;	
	}
	
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	/**
	 * Returns the name of the table.
	 * 
	 * @return name of the table
	 */
	public String getTable()
	{
		return table;	
	}
	
	/**
	 * Sets the name of the catalog that contains the table.
	 * 
	 * @param newCatalog name of the catalog
	 */
	public void setCatalog( String newCatalog )
	{
		catalog = newCatalog;	
	}
	
	/**
	 * Returns the name of the catalog.
	 * 
	 * @return name of the catalog
	 */
	public String getCatalog()
	{
		return catalog;
	}	
	
	/**
	 * Sets the name of the schema that owns the table.
	 * 
	 * @param newSchema name of the schema
	 */
	public void setSchema( String newSchema )
	{
		schema = newSchema;	
	}
	
	/**
	 * Returns the name of the schema.
	 * 
	 * @return name of the schema
	 */
	public String getSchema()
	{
		return schema;	
	}
		
	/**
	 * Adds a column definition.
	 * 
	 * @param columnDef the definition of the column
	 */
	public void addColumn( ColumnDef columnDef )
	{
		columns.add( columnDef );	
	}
	
	/**
	 * Returns the list of columns.
	 * 
	 * @return list of column definitions
	 */
	public Iterator getColumnIterator()
	{
		return columns.iterator();
	}
	
	/**
	 * Returns the number of columns
	 * 
	 * @return number of columns
	 * @since 0.69
	 */
	public int getColumnCount()
	{
		return columns.size();
	}
	
	/**
	 * Add a property that is associated with the entity def.
	 * 
	 * @param key the name of the property
	 * @param value the value of the property
	 */
	public void addProperty( String key, String value )
	{
		if ( properties == null )
		{
			properties = new Properties();
		}
		properties.put( key, value );
	}
	
	/**
	 * Returns the value of a property that is associated with the entity def. If
	 * the property does not exist then null is returned.
	 * 
	 * @param key the name of the property
	 * @return the value of the property
	 */
	public String getProperty( String key )
	{
		if ( properties != null )
			return properties.getProperty( key );
		else
			return null;
	}
	
}
