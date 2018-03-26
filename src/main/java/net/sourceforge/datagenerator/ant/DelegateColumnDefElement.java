package net.sourceforge.datagenerator.ant;

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

import java.util.Map;
import java.util.Properties;

import net.sourceforge.datagenerator.GenerateColumnDef;
import net.sourceforge.jdbcexporter.ExportColumnDef;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ImportColumnDef;
import net.sourceforge.jdbcimporter.ant.ColumnTranslatorElement;
import net.sourceforge.jdbcimporter.ant.PropertyElement;

import org.apache.tools.ant.BuildException;

/**
 * The DelegateColumnDefElement provides an Ant wrapper for creating a
 * ColumnDef object from Ant. The ColumnDef could be one of three types:
 * GenerateColumnDef, ImportColumnDef and ExportColumnDef.
 * 
 * @version 0.69
 * @author Chris Nagy
 */
public class DelegateColumnDefElement
{
	/**
	 * The plugin mapping for custom types and classes.
	 */
	protected Map plugins;

	/**
	 * The name of the column.
	 */
	String name;
	
	/**
	 * The SQL type of the column.
	 */
	String SQLType;
	
	/**
	 * The default value of the column.
	 */
	String defaultValue;
	
	/**
	 * The format pattern used when parsing/formatting a
	 * value of the column.
	 */
	String format;
	
	/**
	 * The properties associated with the column.
	 */
	Properties properties;
	
	/**
	 * The column value generator element that will create
	 * the column value generator.
	 */
	ColumnValueGeneratorElement element;
	
	/**
	 * The column translator element that will create
	 * the column translator.
	 */
	ColumnTranslatorElement translatorElement = null;

	/**
	 * Creates a DelegateColumnDefElement with the given 
	 * mapping between custom types and implementations.	 
	 * 
	 * @param plugins the plugin mappings
	 */
	public DelegateColumnDefElement( Map plugins )
	{
		super();
		this.plugins = plugins;
		this.properties = new Properties();
	}

	/**
	 * Sets the name of the column.
	 * 
	 * @param newName name of the column
	 */	
	public void setName( String newName )
	{
		this.name = newName;
	}
		
	/**
	 * Sets the sql type of the column.
	 * 
	 * @param newType name (case-insensitive) of the sql type that equals
	 * the name of a static variable defined in java.sql.Types
	 */
	public void setSQLType( String newType )
	{
		this.SQLType = newType;
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
		this.defaultValue = newDefaultValue;
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
		this.format = pattern;
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
		properties.put( element.getName(), element.getValue() );
	}

	/**
	 * Returns a new column value generator element.
	 * 
	 * @return new column value generator
	 */
	public ColumnValueGeneratorElement createGenerator()
	{
		element = new ColumnValueGeneratorElement( plugins );
		return element;
	}

	/**
	 * Sets the column translator used on every row's column value
	 * during the import.
	 * 
	 * @param className column translator
	 */
	public void setTranslator( String className )
	{
		translatorElement = new ColumnTranslatorElement( plugins );
		translatorElement.setClassname( className );
	}

	/**
	 * Returns a new column value generator element.
	 * 
	 * @return new column value generator
	 */
	public ColumnTranslatorElement createTranslator()
	{
		translatorElement = new ColumnTranslatorElement( plugins );
		return translatorElement;
	}

	/**
	 * Creates a ColumnDef based on the given entity type (ie.
	 * if the entity type is DATAGENERATE_ENTITY_DEF then a GenerateColumnDef
	 * is created).
	 * 
	 * @param entityDefType the entity type
	 * @return the ColumnDef
	 */
	public ColumnDef createColumnDef(int entityDefType)
	{
		if ( this.name == null || "".equals( name ) )
		{
			throw new BuildException("Required attribute 'name' missing from 'column' element");
		}
		ColumnDef returnDef;
		
		switch ( entityDefType )
		{
			case DelegateEntityDefElement.DATAGENERATE_ENTITY_DEF:
			{
				GenerateColumnDef def = new GenerateColumnDef();
				def.setColumnValueGenerator( element.getColumnValueGenerator() );
				returnDef = def;
				break;
			}
			case DelegateEntityDefElement.EXPORT_ENTITY_DEF:
			{
				ExportColumnDef def = new ExportColumnDef();
				if ( translatorElement != null )
				{
					def.setTranslator( translatorElement.getTranslator() );
				}
				returnDef = def;
				break;
			}
			case DelegateEntityDefElement.IMPORT_ENTITY_DEF:
			{
				ImportColumnDef def = new ImportColumnDef();
				if ( translatorElement != null )
				{
					def.setTranslator( translatorElement.getTranslator() );
				}
				returnDef = def;
				break;
			}
			default:
			{
				throw new BuildException("Unknown entity type");
			}
		}
		returnDef.setName( name );
		if ( defaultValue != null )
			returnDef.setDefaultValue( defaultValue );
		if ( format != null )
			returnDef.setFormatPattern( format );
		if ( SQLType != null )
			returnDef.setSQLType( SQLType );
		
		return returnDef;
	}
	
}
