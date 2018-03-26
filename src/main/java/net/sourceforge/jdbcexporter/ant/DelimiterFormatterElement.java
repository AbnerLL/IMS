package net.sourceforge.jdbcexporter.ant;

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

import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.ant.JDBCImporterTask;
import net.sourceforge.jdbcimporter.ant.PropertyElement;

import org.apache.tools.ant.BuildException;

/**
 * The DelimiterFormatterElement provides an Ant wrapper for setting a
 * DelimiterFormatter object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class DelimiterFormatterElement 
{	
	/**
	 * The prefix used in the properties to identify that this property
	 * is a delimiter formatter definition mapping.
	 */	
	public static String PREFIX = "delimiter.";
	
	/**
	 * The mapping between delimiter formatter types
	 * and implementations.
	 */
	protected Map plugins = null;

	/**
	 * The delimiter formatter type.
	 */
	protected String type;
	
	/**
	 * The wrapped delimiter formatter.
	 */	
	protected DelimiterFormatter formatter;
	
	/**
	 * Constructs an empty DelimiterFormatterElement with the given
	 * mapping between delimiter formatter types and implementations.
	 * 
	 * @param plugins the plugin mappings
	 */	
	public DelimiterFormatterElement( Map plugins )
	{
		this.plugins = plugins;
	}
	
	/**
	 * Sets the delimtier formatter type. The type should map to an implementation
	 * class.
	 * 
	 * @param type the delimiter formatter type
	 */
	public void setType( String type )
	{
		this.type = type;
		String className;	
		className = (String) plugins.get( PREFIX+type );
		if (className == null)
		{
			throw new BuildException("Could not find delimiter formatter implementation class for type '"+type+"'. No value specified in plugins with name '"+PREFIX+type+"'" );
		}
		
		try
		{
			formatter = (DelimiterFormatter) Class.forName( className ).newInstance();	
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
	 * Add a property to the delimiter formatter.
	 * 
	 * @param element the property element
	 */
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( formatter == null )
		{
			throw new BuildException("DelimiterFormatter not initialized");
		}
		
		JDBCImporterTask.applyProperty( formatter, element.getName(), element.getValue() );
		
	}
	
	/**
	 * Returns the wrapped delimiter formatter.
	 * 
	 * @return the delimiter formatter
	 */
	public DelimiterFormatter getDelimiterFormatter() throws BuildException
	{
		if ( formatter == null )
		{
			throw new BuildException("DelimiterFormatter not initialized");
		}
		
		return formatter;
	}
}
