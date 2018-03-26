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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.datagenerator.ant.ColumnValueGeneratorElement;
import net.sourceforge.jdbcimporter.ImportDef;
import net.sourceforge.jdbcimporter.Importer;
import net.sourceforge.jdbcimporter.event.LogImportListener;
import net.sourceforge.jdbcimporter.util.CustomObjectMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * The JDBCImporterTask class is the Ant task that controls the import
 * of data to the database.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class JDBCImporterTask extends Task 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( JDBCImporterTask.class );

	/**
	 * The class name of the engine used for the import.
	 */
	protected String engineClassname = "net.sourceforge.jdbcimporter.engine.BasicEngine";
	
	/**
	 * The import definition.
	 */
	protected ImportDef importDef = new ImportDef();
	
	/**
	 * The ConnectionDefElement containing the connection definition.
	 */
	protected ConnectionDefElement connectionElement = null;
	
	/**
	 * The list of ElementDefElement.
	 */
	protected List entityElements = new ArrayList();
	
	/**
	 * The mapping between connection definition/delimiter parser
	 * types and implementations.
	 */
	protected Map plugins = new HashMap();
	
	/**
	 * Flag indicating that the import should stop on the first error (default is false).
	 */	
	protected boolean failOnError = false;
	
	/**
	 * Create a JDBCImporter Ant task.
	 */
	public JDBCImporterTask()
	{
		plugins = new HashMap();

		{
			Map connectionDefMap = CustomObjectMapping.getDefaultConnectionDefMapping();
			Iterator keys = connectionDefMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) connectionDefMap.get( key );
				LOG.debug( "Adding ConnectionDef mapping '"+key+"' = '"+value+"'");
				plugins.put( ConnectionDefElement.PREFIX + key, value );
			}
		}
		
		{
			Map delimiterParserMap = CustomObjectMapping.getDefaultDelimiterParserMapping();
			Iterator keys = delimiterParserMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) delimiterParserMap.get( key );
				LOG.debug( "Adding delimiterParser mapping '"+key+"' = '"+value+"'");
				plugins.put( DelimiterParserElement.PREFIX + key, value );
			}
		}
		
		{
			Map columnTranslatorMap = CustomObjectMapping.getDefaultColumnTranslatorMapping();
			Iterator keys = columnTranslatorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) columnTranslatorMap.get( key );
				LOG.debug( "Adding ColumnTranslator mapping '"+key+"' = '"+value+"'");
				plugins.put( ColumnTranslatorElement.PREFIX + key, value );
			}
		}

		{
			Map columnValueGeneratorMap = CustomObjectMapping.getDefaultColumnValueGeneratorMapping();
			Iterator keys = columnValueGeneratorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) columnValueGeneratorMap.get( key );
				LOG.debug( "Adding ColumnValueGenerator mapping '"+key+"' = '"+value+"'");
				plugins.put( ColumnValueGeneratorElement.PREFIX + key, value );
			}
		}
		
		{
			Map rowTranslatorMap = CustomObjectMapping.getDefaultRowTranslatorMapping();
			Iterator keys = rowTranslatorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) rowTranslatorMap.get( key );
				LOG.debug( "Adding RowTranslator mapping '"+key+"' = '"+value+"'");
				plugins.put( RowTranslatorElement.PREFIX + key, value );
			}
			
		}
	}
	/**
	 * Sets the class name of the engine used for the import.
	 * 
	 * @param className the class name
	 */
	public void setEngine( String className )
	{
		engineClassname = className;
	}

	/**
	 * Sets the number of rows to import before calling execute batch.
	 * 
	 * @param count the number of rows
	 */
	public void setBatchCount( int count )
	{
		importDef.setBatchCount( count );
	}
	
	/**
	 * Sets the number of rows to import before committing.
	 * 
	 * @param count the number of rows
	 */
	public void setCommitCount( int count )
	{
		importDef.setCommitCount( count );
	}

	/**
	 * Sets the file containing the sql statements that will 
	 * be executed before the import. The sql statements inside
	 * the file should be separated by the semi-colon character.
	 * 
	 * @param file the file
	 */	
	public void setPreSQLFile( File file )
	{
		importDef.setPreSQLFile( file );
	}
	
	/**
	 * Sets the file containing the sql statements that will 
	 * be executed after the import. The sql statements inside
	 * the file should be separated by the semi-colon character.
	 * 
	 * @param file the file
	 */
	public void setPostSQLFile( File file )
	{
		importDef.setPostSQLFile( file );
	}
	
	/**
	 * Sets the flag indicating whether values should be trimmed
	 * after reading them from the delimiter parser.
	 * 
	 * @param flag trim values flag.
	 */
	public void setTrimValues( boolean flag )
	{
		importDef.setTrimValues( flag );
	}
	
	/**
	 * Sets a flag to indicate that the import should stop after the
	 * first error.
	 * 
	 * @param flag flag
	 * @since 0.69
	 */
	public void setFailonerror( boolean flag )
	{
		this.failOnError = flag;
	}
	
	/**
	 * Creates an empty ConnectionDefElement and returns it.
	 * 
	 * @return connection definition element
	 */
	public ConnectionDefElement createConnection()
	{
		connectionElement = new ConnectionDefElement( plugins );
		return connectionElement;
	}
	
	/**
	 * Creates an empty EntityDefElement and returns it.
	 * 
	 * @return entity definition element
	 */
	public ImportEntityDefElement createEntity()
	{
		ImportEntityDefElement element = new ImportEntityDefElement( plugins );
		entityElements.add( element );
		return element;
	}
	
	/**
	 * Adds a property to the plugin properties.
	 * 
	 * @param element the property element
	 */	
	public void addConfiguredProperty( PropertyElement element )
	{
		if ( element.getName() != null && element.getValue() != null )
		{
			plugins.put( element.getName(), element.getValue() );
		}
	}
	
	/**
	 * Import data into the database by processing each
	 * entity definition via the import engine.
	 * 
	 * @throws BuildException if there is no connection definition or entity definitions
	 */
	public void execute() throws BuildException
	{
		if ( connectionElement == null )
		{
			throw new BuildException("No 'connection' element defined");	
		}
		
		if ( entityElements.size() == 0 )
		{
			throw new BuildException("No 'entity' elements defined");
		}

		importDef.setConnectionDef( connectionElement.getConnectionDef() );
		for ( int i = 0; i < entityElements.size(); i++ )
		{
			ImportEntityDefElement element = (ImportEntityDefElement) entityElements.get(i);
			importDef.addEntity( element.getEntityDef() );		
		}

		Importer importer = new Importer();
		importer.setEngine( engineClassname );
		importer.setImportDef( importDef );
		importer.setFailonerror( this.failOnError );
		
		OutputStreamWriter logWriter = new OutputStreamWriter( System.out );
		OutputStreamWriter badWriter = new OutputStreamWriter( System.err );  
		importer.addImportListener(new LogImportListener( logWriter, badWriter ));

		try
		{
			importer.beginImport();		
		}
		catch ( IllegalArgumentException e )
		{
			throw new BuildException(e.getMessage(),e);
		}
		catch ( FileNotFoundException e )
		{
			throw new BuildException(e.getMessage(),e);
		}
		catch ( IOException e )
		{
			throw new BuildException(e.getMessage(),e);
		}
		
		if ( importer.hasImportFailed() )
		{
			throw new BuildException( "Import has failed" );
		}
	}	

	/**
	 * Apply the property to the given object.
	 * 
	 * @param obj the object
	 * @param name the name of the property
	 * @param value the string value of the property
	 * @throws BuildException
	 */
	public static void applyProperty( Object obj, String name, String value ) throws BuildException
	{
		if ( name == null || "".equals( name ) )
		{
			throw new BuildException("Invalid property: missing attribute 'name' in 'property' element");
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
				throw new BuildException("Could not find property '"+name+"' in Class '"+obj.getClass().getName()+
					"' (Similar matches = "+otherChoices.toString()+")");
			}
			else
			{
				// System.out.println("Property '"+name+"' found "+descriptors[index]);
			}
			Method writeMethod = descriptors[index].getWriteMethod();
			if ( writeMethod == null )
			{
				throw new BuildException("No write method for property '"+name+"' in Class '"+obj.getClass().getName()+"'");
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
				throw new BuildException("Could not retrieve property editor "+
				 "for property '"+name+"' "+
				 "whose class is '"+descriptors[index].getPropertyType().getName()+"'");
			}
			
			editor.setAsText(value);
			writeMethod.invoke(obj, new Object[] { editor.getValue() } );
		}
		catch ( IntrospectionException e )
		{
			throw new BuildException("Could not set property '"+name+"'='"+value+"' on object of type '"+obj.getClass()+"'", e);
		}
		catch ( IllegalAccessException iae )
		{
			throw new BuildException("Could not set property '"+name+"'='"+value+"' on object of type '"+obj.getClass()+"'", iae);
		}
		catch ( InvocationTargetException ite )
		{
			throw new BuildException("Could not set property '"+name+"'='"+value+"' on object of type '"+obj.getClass()+"'", ite);
		}
	}
}
