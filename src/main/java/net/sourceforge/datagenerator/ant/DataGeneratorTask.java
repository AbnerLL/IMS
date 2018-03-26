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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.datagenerator.DataGenerator;
import net.sourceforge.datagenerator.GenerateDef;
import net.sourceforge.datagenerator.GenerateEntityDef;
import net.sourceforge.datagenerator.event.LogDataGeneratorListener;
import net.sourceforge.jdbcexporter.ant.DelimiterFormatterElement;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.ant.ColumnTranslatorElement;
import net.sourceforge.jdbcimporter.ant.ConnectionDefElement;
import net.sourceforge.jdbcimporter.ant.DelimiterParserElement;
import net.sourceforge.jdbcimporter.ant.PropertyElement;
import net.sourceforge.jdbcimporter.util.CustomObjectMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * The DataGeneratorTask class is the Ant task that controls the generation
 * of data.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class DataGeneratorTask extends Task 
{	
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( DataGeneratorTask.class );

	/**
	 * The ConnectionDefElement containing the connection definition.
	 */
	protected ConnectionDefElement connectionElement = null;
	
	/**
	 * The list of ElementDefElement.
	 */
	protected List entityElements = new ArrayList();
	
	/**
	 * The mapping between connection definition/column value generator/delimiter formatter
	 * types and implementations.
	 */
	protected Map plugins;

	/**
	 * Creates an empty data generator task with the default plugin mappings.
	 */
	public DataGeneratorTask()
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
			Map delimiterParserMap = CustomObjectMapping.getDefaultDelimiterParserMapping();
			Iterator keys = delimiterParserMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) delimiterParserMap.get( key );
				LOG.debug( "Adding DelimiterParser mapping '"+key+"' = '"+value+"'");
				plugins.put( DelimiterParserElement.FULL_PREFIX + key, value );
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
			Map columnTranslatorMap = CustomObjectMapping.getDefaultColumnTranslatorMapping();
			Iterator keys = columnTranslatorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) columnTranslatorMap.get( key );
				LOG.debug( "Adding ColumnValueGenerator mapping '"+key+"' = '"+value+"'");
				plugins.put( ColumnTranslatorElement.PREFIX + key, value );
			}
		}
		
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
	public DelegateEntityDefElement createEntity()
	{
		// TODO: validate the previous entity definition
		DelegateEntityDefElement element = new DelegateEntityDefElement( plugins );
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
	 * @throws BuildException if the data could not be generated
	 */
	public void execute() throws BuildException
	{
		LOG.trace("execute() ->");
		
		if ( entityElements.size() == 0 )
		{
			throw new BuildException("No 'entity' elements defined");
		}

		GenerateDef generateDef = new GenerateDef();
		// GenerateEntityDef[] entities = new GenerateEntityDef[entityElements.size()];
		for ( int i = 0; i < entityElements.size(); i++ )
		{
			DelegateEntityDefElement element = (DelegateEntityDefElement) entityElements.get(i);
			EntityDef entityDef = element.getEntityDef();
			if ( entityDef instanceof GenerateEntityDef )
			{
				generateDef.addEntity( (GenerateEntityDef) entityDef );
			}
			else
			{
				generateDef.addSourceEntity( entityDef );
			}
		}
		if ( connectionElement != null )
		{
			generateDef.setConnectionDef( connectionElement.getConnectionDef() );
		}
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.setGenerateDef( generateDef );
		
		OutputStreamWriter logWriter = new OutputStreamWriter( System.out );
		dataGenerator.addDataGeneratorListener(new LogDataGeneratorListener( logWriter ));

		try
		{
			dataGenerator.beginGenerate();		
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
		LOG.trace("execute() <-");
	}		
}
