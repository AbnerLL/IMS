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

import net.sourceforge.datagenerator.ColumnValueGenerator;
import net.sourceforge.jdbcimporter.ant.JDBCImporterTask;
import net.sourceforge.jdbcimporter.ant.PropertyElement;

import org.apache.tools.ant.BuildException;

/**
 * The ColumnValueGeneratorElement is an Ant wrapper for a column
 * value generator.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ColumnValueGeneratorElement 
{
	/**
	 * The prefix used in the properties to identify that this property
	 * is a connection definition mapping.
	 */	
	public static String PREFIX = "generator.";
	
	/**
	 * The column generator type.
	 */
	String                      type;
		
	/**
	 * The plugin mappings registered by the ant task.
	 */
	Map                         plugins;
	
	/**
	 * The column value generator.
	 */
	ColumnValueGenerator generator;
	
	/**
	 * Creates a ColumnValueGeneratorElement with the given plugin
	 * mappings.
	 * 
	 * @param plugins the plugin mappings
	 */
	public ColumnValueGeneratorElement( Map plugins )
	{
		this.plugins = plugins;
	}
	
	/**
	 * Sets the column value generator type.
	 * The type should map to an implementation class.
	 * 
	 * @param type the column value generator type
	 */
	public void setType( String type )
	{
		this.type = type;
		if ( !plugins.containsKey( PREFIX + type ) )
		{
			throw new BuildException("Could not find column value generator implementation class for type '"+type+"'. No value specified in plugins with name '"+PREFIX+type+"'" );
		}

		String className = (String) plugins.get( PREFIX+type );		
		try
		{
			generator = (ColumnValueGenerator) Class.forName( className ).newInstance();	
		}
		catch ( ClassNotFoundException e )
		{
			throw new BuildException( "Class Not Found : '"+className+"'", e );
		}
		catch ( IllegalAccessException e )
		{
			throw new BuildException( "Class Not Instantiated : '"+className+"'", e);	
		}
		catch ( InstantiationException e )
		{
			throw new BuildException( "Class Not Instantiated : '"+className+"'", e);	
		}
		
	}
	
	/**
	 * Add a property to the column value generator.
	 * 
	 * @param element the property element
	 */
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( generator == null )
		{
			throw new BuildException("ColumnValueGenerator not initialized");
		}
		
		JDBCImporterTask.applyProperty( generator, element.getName(), element.getValue() );
		
	}
	
	/**
	 * Returns the wrapped column value generator.
	 * 
	 * @return the column value generator
	 */
	public ColumnValueGenerator getColumnValueGenerator() throws BuildException
	{
		if ( generator == null )
		{
			throw new BuildException("ColumnValueGenerator not initialized");
		}
		
		return generator;
	}
	
}
