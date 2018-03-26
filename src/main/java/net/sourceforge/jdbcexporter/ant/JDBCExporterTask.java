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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.jdbcexporter.ExportDef;
import net.sourceforge.jdbcexporter.Exporter;
import net.sourceforge.jdbcexporter.event.LogExportListener;
import net.sourceforge.jdbcimporter.ant.ColumnTranslatorElement;
import net.sourceforge.jdbcimporter.ant.ConnectionDefElement;
import net.sourceforge.jdbcimporter.ant.PropertyElement;
import net.sourceforge.jdbcimporter.util.CustomObjectMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * The JDBCExporterTask class is the Ant task that controls the export
 * of data from the database.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class JDBCExporterTask extends Task 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( JDBCExporterTask.class );

	/**
	 * The class name of the engine used for the export.
	 */
	protected String engineClassname = "net.sourceforge.jdbcexporter.engine.BasicEngine";
	
	/**
	 * The export definition.
	 */
	protected ExportDef exportDef = new ExportDef();
	
	/**
	 * The ConnectionDefElement containing the connection definition.
	 */
	protected ConnectionDefElement connectionElement = null;
	
	/**
	 * The list of entities to be exported.
	 */
	protected List entityElements = new ArrayList();
	
	/**
	 * The mapping between connection definition/column translator/delimiter formatter
	 * types and implementations.
	 */
	protected Map plugins;
	
	/**
	 * Creates a JDBCExporter Ant task.
	 */
	public JDBCExporterTask()
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
			Map delimiterFormatterMap = CustomObjectMapping.getDefaultDelimiterFormatterMapping();
			Iterator keys = delimiterFormatterMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) delimiterFormatterMap.get( key );
				LOG.debug( "Adding DelimiterFormatter mapping '"+key+"' = '"+value+"'");
				plugins.put( DelimiterFormatterElement.PREFIX + key, value );
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
	}
	/**
	 * Sets the class name of the engine used for the export.
	 * 
	 * @param className
	 */
	public void setEngine( String className )
	{
		engineClassname = className;
	}

	/**
	 * Creates an empty ConnectionDefElement and returns it.
	 * 
	 * @return the connection definition element
	 */
	public ConnectionDefElement createConnection()
	{
		connectionElement = new ConnectionDefElement( plugins );
		return connectionElement;
	}
	
	/**
	 * Creates an empty EntityDefElement and returns it.
	 * 
	 * @return the entity definition element
	 */
	public ExportEntityDefElement createEntity()
	{
		// TODO: validate the previous entity definition
		ExportEntityDefElement element = new ExportEntityDefElement( plugins );
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
	 * Export data into the database by processing each
	 * entity definition via the export engine.
	 * 
	 * @throws BuildException if the export could not be processed
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
		
		exportDef.setConnectionDef( connectionElement.getConnectionDef() );
		for ( int i = 0; i < entityElements.size(); i++ )
		{
			ExportEntityDefElement element = (ExportEntityDefElement) entityElements.get(i);
			exportDef.addEntity( element.getEntityDef() );		
		}
		
		Exporter exporter = new Exporter();
		exporter.setEngine( engineClassname );
		exporter.setExportDef( exportDef );

		OutputStreamWriter logWriter = new OutputStreamWriter( System.out );
		exporter.addExportListener(new LogExportListener( logWriter ));

		try
		{
			exporter.beginExport();		
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
	}	
}
