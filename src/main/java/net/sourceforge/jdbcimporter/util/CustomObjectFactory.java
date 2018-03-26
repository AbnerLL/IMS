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
package net.sourceforge.jdbcimporter.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * The CustomObjectFactory class is a factory for custom objects. The
 * factory can also apply properties to the custom object via 
 * Java Bean setter methods.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class CustomObjectFactory 
{
	/**
	 * The mapping between types and implementation class names.
	 */
	protected Map mapping;
	
	/**
	 * The base interface or class for all custom object created.
	 */
	protected Class     objBaseClass;

	/**
	 * Creates a new custom object factory that will create objects
	 * of the given class.
	 * 
	 * @param objClass the base class of all custom objects
	 */	 
	public CustomObjectFactory( Class objClass )
	{
		mapping = new HashMap();
		objBaseClass = objClass;	
	}

	/**
	 * Add a mapping of a short prefix to a class name that 
	 * is an ancestor of the base class.
	 * 
	 * @param prefix the string prefix
	 * @param className the class name
	 */
	public void addMapping( String prefix, String className )
	{
		mapping.put( prefix, className );
	}
	
	/**
	 * Create a custom object based on the class name mapped to the prefix
	 * and apply the properties to the custom object.
	 * 
	 * @param prefix the prefix mapping
	 * @param properties the properties to apply
	 * @return the custom object
	 * @throws InvalidCustomObjectDefException if the prefix is not mapped,
	 * the class is not an ancestor of the base class or a property could not
	 * be applied to the custom object.
	 */
	public Object getObjectImpl( String prefix, Map properties ) throws InvalidCustomObjectDefException
	{
		String className = (String) mapping.get( prefix );
		if ( className == null )
		{
			throw new InvalidCustomObjectDefException( "Prefix '"+prefix+"' not mapped to a classname");
		}
		try
		{
			Object obj = Class.forName( className ).newInstance();
			if ( !(objBaseClass.isAssignableFrom(obj.getClass())) )
			{
				throw new InvalidCustomObjectDefException( "Class '"+className+"' is not an instance of '"+objBaseClass.getName()+"'");
			}
			
			Iterator keys = properties.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String val = (String) properties.get(key);
				applyProperty(obj,key,val);
			}
			return obj;
		}	
		catch ( ClassNotFoundException ce )
		{
			throw new InvalidCustomObjectDefException( "Class '"+className+"' was not found in CLASSPATH", ce );	
		}
		catch ( IllegalAccessException iae )
		{
			throw new InvalidCustomObjectDefException( "Class '"+className+"' could not be instantiated (illegal access)", iae );	
		}
		catch ( InstantiationException ie )
		{
			throw new InvalidCustomObjectDefException( "Class '"+className+"' could not be instantiated", ie );	
		}
	}

	/**
	 * Set the property in the object with the value.
	 * 
	 * @param obj the object
	 * @param name the name of the property
	 * @param value the value
	 * @throws InvalidCustomObjectDefException
	 */
	protected void applyProperty( Object obj, String name, String value ) throws InvalidCustomObjectDefException
	{
		if ( name == null || "".equals( name ) )
		{
			throw new InvalidCustomObjectDefException("Invalid property: missing attribute 'name' in 'property' element");
		}
		try
		{
			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
			int index = -1;
			StringBuffer otherChoices = new StringBuffer("");
			for ( int i = 0; i < descriptors.length; i++ )
			{
				if ( name.equals( descriptors[i].getName() ) )
				{
					index = i;
					break;
				}
				
				if ( name.equalsIgnoreCase( descriptors[i].getName() ) ||
					 descriptors[i].getName().startsWith( name ) )
				{
					if ( otherChoices.length() > 0 )
					{
						otherChoices.append(",");		
					}
					otherChoices.append("'");
					otherChoices.append(descriptors[i].getName());
					otherChoices.append("'");
				}
			}
			if ( index == -1 )
			{
				throw new InvalidCustomObjectDefException("Could not find property '"+name+"' in Class '"+obj.getClass().getName()+
					"' (Similar matches = "+otherChoices.toString()+")");
			}
			else
			{
				// System.out.println("Property '"+name+"' found "+descriptors[index]);
			}
			Method writeMethod = descriptors[index].getWriteMethod();
			if ( writeMethod == null )
			{
				throw new InvalidCustomObjectDefException("No write method for property '"+name+"' in Class '"+obj.getClass().getName()+"'");
			}
			
			PropertyEditor editor = null;
			
			Class propertyEditorClass = descriptors[index].getPropertyEditorClass();
			if ( propertyEditorClass != null )
			{
				// System.out.println("PropertyEditorClass = "+propertyEditorClass.getName());
				try
				{
					editor = (PropertyEditor) propertyEditorClass.newInstance();
				}
				catch ( IllegalAccessException iae )
				{
				}
				catch ( InstantiationException ie )
				{
				}
			}
			else
			{
				// System.out.println("PropertyEditorClass = null");
			}
			if ( editor == null )
			{
				editor = PropertyEditorManager.findEditor(descriptors[index].getPropertyType());
			}
			
			if ( editor == null )
			{
				throw new InvalidCustomObjectDefException("Could not retrieve property editor "+
				 "for property '"+name+"' "+
				 "whose class is '"+descriptors[index].getPropertyType().getName()+"'");
			}
			
			editor.setAsText(value);
			writeMethod.invoke(obj, new Object[] { editor.getValue() } );
		}
		catch ( IntrospectionException e )
		{
			throw new InvalidCustomObjectDefException("Could not set property '"+name+"'='"+value+"' on object of type '"+obj.getClass()+"'", e);
		}
		catch ( IllegalAccessException iae )
		{
			throw new InvalidCustomObjectDefException("Could not set property '"+name+"'='"+value+"' on object of type '"+obj.getClass()+"'", iae);
		}
		catch ( InvocationTargetException ite )
		{
			throw new InvalidCustomObjectDefException("Could not set property '"+name+"'='"+value+"' on object of type '"+obj.getClass()+"'", ite);
		}
	}
	
}
