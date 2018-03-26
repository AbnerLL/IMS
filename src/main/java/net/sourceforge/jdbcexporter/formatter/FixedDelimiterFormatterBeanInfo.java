package net.sourceforge.jdbcexporter.formatter;

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

import net.sourceforge.jdbcimporter.util.IntegerRangeArrayEditor;

/**
 * The FixedDelimiterFormatterBeanInfo class provides the custom property editor
 * needed for the property 'columnPositions'.
 * 
 * @since      0.6
 * @author     Chris Nagy
 */
public class FixedDelimiterFormatterBeanInfo extends SimpleBeanInfo 
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
	 * Constructs a new FixedDelimiterFormatterBeanInfo.
	 * 
	 * @throws java.beans.IntrospectionException
	 */
	public FixedDelimiterFormatterBeanInfo()
		throws java.beans.IntrospectionException
	{
		try
		{
			descriptors = new PropertyDescriptor[2];
			descriptors[0] = new PropertyDescriptor( "columnPositions", FixedDelimiterFormatter.class, null, "setColumnPositions" );
			descriptors[0].setPropertyEditorClass( IntegerRangeArrayEditor.class );
			descriptors[1] = new PropertyDescriptor( "length", FixedDelimiterFormatter.class, null, "setLength" );
		}
		catch ( java.beans.IntrospectionException e )
		{
			e.printStackTrace();
			throw e;
		}
		descriptor = new BeanDescriptor(FixedDelimiterFormatter.class);
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
