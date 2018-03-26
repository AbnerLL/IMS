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

import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.DelimiterParser;

import org.apache.tools.ant.BuildException;

/**
 * The DelimiterParserElement provides an Ant wrapper for setting a
 * DelimiterParser object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class DelimiterParserElement 
{	
	/**
	 * The prefix used in the properties to identify that this property
	 * is a delimiter parser mapping.
	 */	
	public static String PREFIX = "delimiter.";
	
	/**
	 * Alternative prefix used in the properties to identify that this
	 * property is a delimiter parser mapping. This prefix is used by
	 * the DataGeneratorTask.
	 * @since 0.69
	 */
	public static String FULL_PREFIX = "delimiterparser.";
	
	/**
	 * The current prefix used to lookup delimiter parsers.
	 */
	protected String prefix = PREFIX;
	
	/**
	 * The mapping between delimtier parser types
	 * and implementations.
	 */
	protected Map plugins = null;

	/**
	 * The delimiter parser type.
	 */
	protected String type;
	
	/**
	 * The wrapped delimiter parser.
	 */	
	protected DelimiterParser parser;
	
	/**
	 * The wrapped binary delimiter parser.
	 * @since 0.74
	 */
	protected BinaryDelimiterParser binaryParser;
	
	/**
	 * Constructs an empty DelimiterParserElement with the given
	 * mapping between delimiter parser types and implementations.
	 * 
	 * @param plugins the plugin mapping
	 */	
	public DelimiterParserElement( Map plugins )
	{
		this.plugins = plugins;
	}

	/**
	 * Constructs an empty DelimiterParserElement with the given
	 * mapping between delimiter parser types and implementations. 
	 * The DelimiterParserElement will use the given prefix too lookup
	 * delimiter parser implementations.
	 * 
	 * @param prefix the prefix
	 * @param plugins the plugin mapping
	 * @since 0.69
	 */
	public DelimiterParserElement( String prefix, Map plugins )
	{
		this.plugins = plugins;
		this.prefix = prefix;
	}
	
	/**
	 * Sets the delimiter parser type. The type should map to an implementation
	 * class.
	 * 
	 * @param type the delimiter parser type
	 */
	public void setType( String type )
	{
		this.type = type;
		String className = (String) plugins.get( prefix+type );
		if (className == null)
		{
			throw new BuildException("Could not find delimiter parser implementation class for type '"+type+"'. No value specified in plugins with name '"+PREFIX+type+"'" );
		}
		
		try
		{
			Object obj = Class.forName( className ).newInstance();
			if ( obj instanceof BinaryDelimiterParser )
			{
				binaryParser = (BinaryDelimiterParser) obj;
			}
			else
			{
				parser = (DelimiterParser) obj;
			}
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
	 * Add a property to the delimiter parser.
	 * 
	 * @param element the property element
	 */
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( parser == null && binaryParser == null )
		{
			throw new BuildException("DelimiterParser/BinaryDelimiterParser not initialized");
		}
		
		if ( parser != null )
		{
			JDBCImporterTask.applyProperty( parser, element.getName(), element.getValue() );
		}
		else 
		{
			JDBCImporterTask.applyProperty( binaryParser, element.getName(), element.getValue() );
		}
		
	}
	
	/**
	 * Returns the wrapped delimiter parser.
	 * 
	 * @return the delimiter parser
	 */
	public DelimiterParser getDelimiterParser() throws BuildException
	{
		if ( parser == null )
		{
			throw new BuildException("DelimiterParser not initialized");
		}
		
		return parser;
	}

	/**
	 * Returns the wrapped binary delimiter parser.
	 * 
	 * @return the binary delimiter parser
	 * @since 0.74
	 */
	public BinaryDelimiterParser getBinaryDelimiterParser() 
	{
		if ( binaryParser == null )
		{
			throw new BuildException("BinaryDelimiterParser not initialized");
		}
		
		return binaryParser;
	}

	/**
	 * Returns true if the wrapped delimiter parser is a binary delimiter parser.
	 * 
	 * @return Flag indicating that the wrapped delimiter parser is a binary delimiter parser
	 * @since 0.74
	 */
	public boolean isBinaryDelimiterParser() 
	{
		return binaryParser != null;
	}
}
