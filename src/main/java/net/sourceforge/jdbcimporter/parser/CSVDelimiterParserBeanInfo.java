package net.sourceforge.jdbcimporter.parser;

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

import java.beans.BeanDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

import net.sourceforge.jdbcimporter.util.IntegerArrayEditor;

/**
 * The CSVDelimiterParserBeanInfo class provides the custom property editor
 * needed for the property 'ignoredPositions'.
 * 
 * @version 0.61
 * @author Chris Nagy
 */
public class CSVDelimiterParserBeanInfo extends SimpleBeanInfo
{
	/**
	 * The property descriptors.
	 */
	PropertyDescriptor[] descriptors;
	
	/**
	 * The bean descriptor.
	 */
	BeanDescriptor descriptor;

	/**
	 * Construct a new bean info for CSVDelimiterParser.
	 */
	public CSVDelimiterParserBeanInfo()
		throws java.beans.IntrospectionException
	{
		try
		{
			descriptors = new PropertyDescriptor[7];
			descriptors[0] = new PropertyDescriptor( "columnDelimiter",   CSVDelimiterParser.class, "getColumnDelimiter", "setColumnDelimiter" );
			descriptors[1] = new PropertyDescriptor( "enclosedDelimiter", CSVDelimiterParser.class, "getEnclosedDelimiter", "setEnclosedDelimiter" );
			descriptors[2] = new PropertyDescriptor( "enclosedOptional", CSVDelimiterParser.class, "isEnclosedOptional", "setEnclosedOptional" );
			descriptors[3] = new PropertyDescriptor( "ignoredPositions", CSVDelimiterParser.class, null, "setIgnoredPositions" );
			descriptors[3].setPropertyEditorClass( IntegerArrayEditor.class );
			descriptors[4] = new PropertyDescriptor( "multiline", CSVDelimiterParser.class, null, "setMultiline" );
			descriptors[5] = new PropertyDescriptor( "ignoreFirstLine", CSVDelimiterParser.class, null, "setIgnoreFirstLine" );
			descriptors[6] = new PropertyDescriptor( "fileName", CSVDelimiterParser.class, null, "setFileName" );
		}
		catch ( java.beans.IntrospectionException e )
		{
			e.printStackTrace();
			throw e;
		}
		descriptor = new BeanDescriptor(CSVDelimiterParser.class);
	}
	
	/**
	 * Returns the bean descriptor.
	 * 
	 * @return bean descriptor
	 */
	public BeanDescriptor getBeanDescriptor()
	{
		return descriptor;
	}

	/**
	 * Returns the array of property descriptors for the bean.
	 * 
	 * @return property descriptors
	 */
	public PropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}

}
