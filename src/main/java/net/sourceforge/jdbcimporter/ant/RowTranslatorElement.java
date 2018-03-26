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
 
import java.util.Map;

import net.sourceforge.jdbcimporter.RowTranslator;

import org.apache.tools.ant.BuildException;

/**
 * The RowTranslatorElement provides an Ant wrapper for setting a 
 * RowTranslator object from Ant.
 * 
 * @version 0.72
 * @author cnagy
 *
 */
public class RowTranslatorElement 
{

	/**
	 * The prefix used in the properties to identify that this property
	 * is a row translator mapping.
	 */
	public static String PREFIX = "rowtranslator.";
	
	/**
	 * The mapping of row translator types to classes.
	 */
	private Map plugins;
	
	/**
	 * The row translator.
	 */
	private RowTranslator translator;
		
	/**
	 * The row translator type.
	 */
	private String type;
	
	/**
	 * Creates a RowTranslatorElement with the given mapping of row
	 * translator types to classes.
	 *  
	 * @param plugins the plugin mappings
	 */
	public RowTranslatorElement( Map plugins )
	{
		this.plugins = plugins;
	}
	
	/**
	 * Set the row translator type and instantiate the row translator.
	 * 
	 * @param type the row translator type
	 */
	public void setType( String type )
	{
		this.type = type;
		String className = (String) plugins.get(PREFIX+type);
		if ( className == null )
		{
			throw new BuildException("Could not find row translator implementation class for type '"+type+"'. No value specified in plugins with name '"+PREFIX+type+"'");
		}
		try
		{
			Object obj = Class.forName( className ).newInstance();	
			if ( !(obj instanceof RowTranslator ) )
			{
				throw new BuildException( "Class '"+className+"' does not implement RowTranslator");
			}
			translator = (RowTranslator) obj;
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
	 * Add a property to the row translator.
	 * 
	 * @param element the property
	 */
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( translator == null )
		{
			throw new BuildException("RowTranslator not initialized");
		}
		
		JDBCImporterTask.applyProperty( translator, element.getName(), element.getValue() );
	}
	
	/**
	 * Returns the row translator.
	 * 
	 * @return row translator
	 */
	public RowTranslator getTranslator()
	{
		return translator;
	}
	
}
