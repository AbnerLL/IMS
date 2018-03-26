package net.sourceforge.jdbcutils.formatter;

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

public class ExcelDelimiterFormatterBeanInfo extends SimpleBeanInfo {

	PropertyDescriptor[] descriptors;
	BeanDescriptor descriptor;
	
	public ExcelDelimiterFormatterBeanInfo()
		throws java.beans.IntrospectionException
	{
		try
		{
			descriptors = new PropertyDescriptor[1];
			descriptors[0] = new PropertyDescriptor( "outputFileName", ExcelDelimiterFormatter.class, null, "setOutputFileName" );
		}
		catch ( java.beans.IntrospectionException e )
		{
			e.printStackTrace();
			throw e;
		}
		descriptor = new BeanDescriptor(ExcelDelimiterFormatter.class);
	}
	
	public BeanDescriptor getBeanDescriptor()
	{
		return descriptor;
	}
	
	public PropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}
}
