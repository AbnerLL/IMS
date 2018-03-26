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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcexporter.ant.DelimiterFormatterElement;
import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.ant.DelimiterParserElement;
import net.sourceforge.jdbcimporter.ant.PropertyElement;

/**
 * The DelegateDelimiterElement provides an Ant wrapper for creating
 * a delimiter formatter or a delimiter parser.
 * 
 * @version 0.69
 * @author Chris Nagy
 */
public class DelegateDelimiterElement
{
	/**
	 * The mapping between custom types
	 * and implementations.
	 */
	protected Map plugins = null;

	/**
	 * The delimiter formatter/parser type.
	 */
	protected String type;
	
	/**
	 * The list of child property elements.
	 */
	protected List propertyElements;
	
	/**
	 * The real delimiter parser element.
	 * @since 0.74
	 */
	protected DelimiterParserElement realParserElement;
	
	/**
	 * Creates a DelegateDelimiterElement with the given
	 * mapping between custom types and implementations.
	 *  
	 * @param plugins the plugin mappings
	 */
	public DelegateDelimiterElement( Map plugins )
	{
		super();
		this.plugins = plugins;
		propertyElements = new ArrayList();
	}

	/**
	 * Sets the delimiter formatter/parser type. The type should map
	 * to an implementation class.
	 * 
	 * @param type the delimiter formatter/parser type 
	 */
	public void setType( String type )
	{
		this.type = type;
	}
	
	/**
	 * Add a property element to the list of child property elements.
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
		propertyElements.add( element );		
	}

	/**
	 * Creates a delimiter formatter and configures it with the list
	 * of property elements.
	 * 
	 * @return the delimiter formatter
	 */
	public DelimiterFormatter createDelimiterFormatter()
	{
		DelimiterFormatterElement realElement = new DelimiterFormatterElement( plugins );
		realElement.setType( type );
		for ( int i = 0; i < propertyElements.size(); i++ )
		{
			realElement.addConfiguredProperty( (PropertyElement) propertyElements.get(i) );
		}
		return realElement.getDelimiterFormatter();
	}

	/**
	 * Creates a delimiter parser and configures it with the list
	 * of property elements.
	 * 
	 * @return the delimiter parser
	 */
	public DelimiterParser createDelimiterParser()
	{
		initDelimiterParserElement();
		return realParserElement.getDelimiterParser();
	}
	
	/**
	 * Creates a binary delimiter parser and configures it with the
	 * list of property elements.
	 * 
	 * @return the binary delimiter parser
	 * @since 0.74
	 */
	public BinaryDelimiterParser createBinaryDelimiterParser()
	{
		initDelimiterParserElement();
		return realParserElement.getBinaryDelimiterParser();
	}

	/**
	 * Returns true if the delimiter parser is a binary delimiter parser.
	 * 
	 * @return Flag indicating that the delimiter parser is a binary
	 * delimiter parser
	 * @since 0.74
	 */
	public boolean isBinaryDelimiterParser() 
	{
		initDelimiterParserElement();
		return realParserElement.isBinaryDelimiterParser();
	}
	
	/**
	 * Creates the real delimiter parser element.
	 * @since 0.74 
	 */
	protected void initDelimiterParserElement()
	{
		if ( realParserElement != null )
		{
			return;
		}

		DelimiterParserElement realElement = new DelimiterParserElement( DelimiterParserElement.FULL_PREFIX, plugins );
		realElement.setType( type );
		for ( int i = 0; i < propertyElements.size(); i++ )
		{
			realElement.addConfiguredProperty( (PropertyElement) propertyElements.get(i) );
		}
		this.realParserElement = realElement;
	}
}
