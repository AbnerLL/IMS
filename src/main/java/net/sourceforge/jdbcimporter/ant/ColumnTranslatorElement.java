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

package net.sourceforge.jdbcimporter.ant;

import java.util.Map;

import net.sourceforge.jdbcimporter.ColumnTranslator;

import org.apache.tools.ant.BuildException;

/**
 * The ColumnTranslatorElement provides an Ant wrapper for setting a
 * ColumnTranslator object from Ant.
 * 
 * @version 	0.63
 * @author Chris Nagy
 */
public class ColumnTranslatorElement
{
	/**
	 * The prefix used in the properties to identify that this property
	 * is a column translator mapping.
	 */	
	public static String PREFIX = "columntranslator.";

	/**
	 * The mapping of column translator types to classes.
	 */
	private Map plugins;
	
	/**
	 * The column translator.
	 */
	private ColumnTranslator translator;
	
	/**
	 * The column translator type.
	 */
	private String type;
	
	/**
	 * Creates a ColumnTranslatorElement with the given mapping of column
	 * translator types to classes.
	 * 
	 * @param plugins the plugin mappings
	 */
	public ColumnTranslatorElement( Map plugins )
	{
		super( );
		this.plugins = plugins;
	}

	/**
	 * Set the column translator type and instantiate the column translator.
	 * 
	 * @param type the column translator type
	 */
	public void setType( String type )
	{
		this.type = type;
		String className = (String) plugins.get( PREFIX+type );
		if (className == null)
		{
			throw new BuildException("Could not find column translator implementation class for type '"+type+"'. No value specified in plugins with name '"+PREFIX+type+"'" );
		}
		setClassname( className );
	}
	
	/**
	 * Set the class name of the column translator and instantiate the column translator.
	 * 
	 * @param name the class name of the column translator
	 */
	public void setClassname( String name )
	{
		translator = instantiateTranslator( name );
	}
	
	/**
	 * Instantiate the column translator.
	 * 
	 * @param className the class name
	 * @return column translator
	 */
	protected ColumnTranslator instantiateTranslator( String className )
	{
		try
		{
			Object obj = Class.forName( className ).newInstance();	
			if ( !(obj instanceof ColumnTranslator ) )
			{
				throw new BuildException( "Class '"+className+"' does not implement ColumnTranslator");
			}
			return (ColumnTranslator) obj;
		}	
		catch ( ClassNotFoundException ce )
		{
			throw new BuildException(ce);
		}
		catch ( IllegalAccessException iae )
		{
			throw new BuildException(iae);
		}
		catch ( InstantiationException ie )
		{
			throw new BuildException(ie);
		}
	}

	/**
	 * Add a property to the column translator.
	 * 
	 * @param element the property
	 */
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( translator == null )
		{
			throw new BuildException("ColumnTranslator not initialized");
		}
		
		JDBCImporterTask.applyProperty( translator, element.getName(), element.getValue() );
	}
	
	/**
	 * Returns the column translator.
	 * 
	 * @return column translator
	 */
	public ColumnTranslator getTranslator()
	{
		return translator;
	}
	
}
