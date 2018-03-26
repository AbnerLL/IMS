package net.sourceforge.jdbcexporter;

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
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sourceforge.jdbcexporter.config.ExportConfig;
import net.sourceforge.jdbcexporter.event.ExportEntityEvent;
import net.sourceforge.jdbcexporter.event.ExportEvent;
import net.sourceforge.jdbcexporter.event.ExportListener;
import net.sourceforge.jdbcexporter.event.LogExportListener;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ConnectionDef;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.CustomObjectMapping;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The Exporter class is the main class that controls the export
 * of data from the database.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class Exporter
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( Exporter.class );
	
	/**
	 * Prefix for the connection config type/class mappings to use.
	 */
	public static final String CONNECTION_PREFIX = "connection.";
	
	/**
	 * Prefix for the delimiter formatter type/class mappings to use.
	 */
	public static final String DELIMITER_PREFIX  = "delimiter.";

	/**
	 * Prefix for the column translator type/class mappings to use.
	 */
	public static final String COLUMNTRANSLATOR_PREFIX  = "columntranslator.";
	
	/**
	 * Custom object factory for connection definitions.
	 */
	protected CustomObjectFactory connectionDefFactory;
	
	/**
	 * Custom object factory for delimiter formatters.
	 */
	protected CustomObjectFactory delimiterFormatterFactory;
	
	/**
	 * Custom object factory for column translators.
	 */
	protected CustomObjectFactory columnTranslatorFactory;
	
	/**
	 * Export definition.
	 */
	protected ExportDef exportDef;
	
	/**
	 * Export engine class name.
	 */
	protected String engineClassname;

	/**
	 * The list of export listeners.
	 */
	protected List   exportListeners = new ArrayList();
	
	/**
	 * Constructs a new exporter.
	 */
	public Exporter()
	{
		connectionDefFactory = new CustomObjectFactory( ConnectionDef.class );
		{
			Map connectionDefMap = CustomObjectMapping.getDefaultConnectionDefMapping();
			Iterator keys = connectionDefMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) connectionDefMap.get( key );
				LOG.debug( "Adding ConnectionDef mapping '"+key+"' = '"+value+"'");
				connectionDefFactory.addMapping( key, value );
			}
		}
		
		delimiterFormatterFactory  = new CustomObjectFactory( DelimiterFormatter.class );
		{
			Map delimiterFormatterMap = CustomObjectMapping.getDefaultDelimiterFormatterMapping();
			Iterator keys = delimiterFormatterMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) delimiterFormatterMap.get( key );
				LOG.debug( "Adding DelimiterFormatter mapping '"+key+"' = '"+value+"'");
				delimiterFormatterFactory.addMapping( key, value );
			}
		}
		
		columnTranslatorFactory  = new CustomObjectFactory( ColumnTranslator.class );
		{
			Map columnTranslatorMap = CustomObjectMapping.getDefaultColumnTranslatorMapping();
			Iterator keys = columnTranslatorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) columnTranslatorMap.get( key );
				LOG.debug( "Adding ColumnTranslator mapping '"+key+"' = '"+value+"'");
				columnTranslatorFactory.addMapping( key , value );
			}
		}
	}

	/**
	 * Sets the export def.
	 * 
	 * @param exportDef the export def
	 */
	public void setExportDef( ExportDef exportDef )
	{
		this.exportDef = exportDef;
	}
	
	/**
	 * Read and parse the export config file into an ExportDef class.
	 * 
	 * @param source export config file
	 * @throws IOException if the export config file cannot be opened and read
	 */
	public void initExportDef( InputSource source ) throws FileNotFoundException,IOException,
		ParserConfigurationException, SAXException, DOMException, InvalidCustomObjectDefException
	{
		LOG.trace( "initExportDef( <InputSource> ) ->");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating( false );
		DocumentBuilder builder = factory.newDocumentBuilder();		
		Document doc = builder.parse( source );
		ExportConfig config = new ExportConfig();
		config.setDelimiterFormatterFactory(delimiterFormatterFactory);
		config.setConnectionDefFactory(connectionDefFactory);
		config.setColumnTranslatorFactory( columnTranslatorFactory );
		exportDef = config.getExport(doc.getFirstChild());							
		if ( LOG.isDebugEnabled() ) 
			LOG.debug("Loaded ExportDef : "+exportDef.toString());
	}
		
	/**
	 * Parse the plugin property file and adds the external 
	 * connection definition classes and delimiter formatter
	 * classes to the appropriate factories.
	 * 
	 * @param pluginFile plugin property file
	 * @throws FileNotFoundException if the plugin file cannot be found
	 * @throws IOException if the plugin file cannot be opened and read
	 */
	public void initPlugins( String pluginFile ) throws FileNotFoundException, IOException
	{
		LOG.trace( "initPlugins( '"+pluginFile+"') ->");
		Properties plugins = new Properties();
		FileInputStream fileInput = null;
		try
		{
			fileInput = new FileInputStream( pluginFile );
			plugins.load( fileInput );
			initPlugins( plugins );
		}
		finally
		{
			if ( fileInput != null )
			{
				try
				{
					fileInput.close();
				}
				catch ( IOException e )
				{
					LOG.warn("Could not close file '"+pluginFile+"'", e );
				}
			}	
			LOG.trace( "initPlugins( '"+pluginFile+"') <-");
		}
	}
	
	/**
	 * Adds the external connection definition classes and delimiter formatter
	 * classes to the appropriate factories.
	 *
	 * @param pluginProperties the plugin properties
	 */
	public void initPlugins( Properties pluginProperties )
	{
		LOG.trace( "initPlugins( <Properties:"+pluginProperties.size()+"> ) ->");
		Enumeration keys = pluginProperties.keys();
		while ( keys.hasMoreElements() )
		{
			String nextKey = (String) keys.nextElement();
			if ( nextKey.startsWith(CONNECTION_PREFIX) )
			{
				String connectionType = nextKey.substring(CONNECTION_PREFIX.length());
				String connectionClass = pluginProperties.getProperty( nextKey );
				LOG.debug("Registering ConnectionDef : '"+connectionType+"', '"+connectionClass+"'" );
				connectionDefFactory.addMapping(connectionType,connectionClass);
			}
			else if ( nextKey.startsWith( DELIMITER_PREFIX ) )
			{
				String delimiterType = nextKey.substring(DELIMITER_PREFIX.length());
				String delimiterClass = pluginProperties.getProperty( nextKey );
				LOG.debug("Registering DelimiterFormatter : '"+delimiterType+"', '"+delimiterClass+"'" );
				delimiterFormatterFactory.addMapping(delimiterType,delimiterClass);	
			}
			else if ( nextKey.startsWith( COLUMNTRANSLATOR_PREFIX ) )
			{
				String columnTranslatorType = nextKey.substring(COLUMNTRANSLATOR_PREFIX.length());
				String columnTranslatorClass = pluginProperties.getProperty( nextKey );
				LOG.debug("Adding ColumnTranslator mapping '"+columnTranslatorType+"', '"+columnTranslatorClass+"'" );
				columnTranslatorFactory.addMapping(columnTranslatorType,columnTranslatorClass);	
			}
			else
			{
				LOG.warn("Unrecognized plugin : "+nextKey);	
			}
		}
		LOG.trace( "initPlugins( <Properties:"+pluginProperties.size()+"> ) <-");
	}
	
	/**
	 * Sets the export engine to use.
	 * 
	 * @param engineClassname the classname of the export engine
	 */	
	public void setEngine( String engineClassname )
	{
		this.engineClassname = engineClassname;		
	}
	
	/**
	 * Export data from the database by processing each
	 * entity definition via the export engine.
	 * 
	 * @throws FileNotFoundException if the log file is not valid
	 * @throws IOException if the log file cannot be opened and written to
	 */
	public void beginExport() throws FileNotFoundException, IOException
	{
		LOG.trace( "beginExport() ->" );
		ExportEngine engine = null;
		LOG.debug( "Instantiating Engine '"+engineClassname+"'");
		try
		{
			engine = (ExportEngine) Class.forName( engineClassname ).newInstance();	
		}
		catch ( ClassNotFoundException e )
		{
			throw new IllegalArgumentException( "Class Not Found : '"+engineClassname+"'" );
		}
		catch ( IllegalAccessException e )
		{
			throw new IllegalArgumentException( "Class Not Instantiated : '"+engineClassname+"'");	
		}
		catch ( InstantiationException e )
		{
			throw new IllegalArgumentException( "Class Not Instantiated : '"+engineClassname+"'");	
		}
		
		if ( LOG.isDebugEnabled() ) 
			LOG.debug( "Initializing Connection "+exportDef.getConnectionDef() );

		
		FileWriter log = null;
		if ( exportDef.getLogFile() != null )
		{
			log = new FileWriter( exportDef.getLogFile() );
			addExportListener( new LogExportListener( log ));
		}
		
		Connection connection = exportDef.getConnectionDef().getConnection();
		if ( connection == null )
		{
			throw new IOException( "Could not initialize connection" );
		}
		engine.setConnection(connection);
		EntityExporter exporter = new EntityExporter();
		exporter.setExportEngine(engine);
		for ( int i = 0; i < this.exportListeners.size(); i++ )
		{
			exporter.addExportListener( (ExportListener) this.exportListeners.get(i) );
		}

		fireExportEvent( new ExportEvent( this, exportDef, this.engineClassname), true );		
		ExportEntityDef[] entities = exportDef.getEntities();
		for ( int i = 0; i < entities.length; i++ )
		{
			fireExportEntityEvent( new ExportEntityEvent( this, entities[i], null ), true );
			try
			{
				exporter.processEntity( entities[i] );
				fireExportEntityEvent( new ExportEntityEvent( this, entities[i], null ), false );
			}
			catch ( IOException ex )
			{
				fireExportEntityEvent( new ExportEntityEvent( this, entities[i], ex ), false );
			}
			catch ( SQLException ex )
			{
				fireExportEntityEvent( new ExportEntityEvent( this, entities[i], ex ), false );
			}
		}
		fireExportEvent( new ExportEvent( this, exportDef, this.engineClassname), false );		
		exportDef.getConnectionDef().releaseConnection( connection );
		if ( log != null )
		{
			log.close();
		}
		LOG.trace( "beginExport() <-" );
	}
	
	/**
	 * Adds an export listener.
	 * 
	 * @param listener an export listener
	 */
	public void addExportListener( ExportListener listener )
	{
		if ( !exportListeners.contains( listener ) )
		{
			exportListeners.add( listener );
		}
	}

	/**
	 * Removes an export listener.
	 * 
	 * @param listener an export listener
	 */
	public void removeExportListener( ExportListener listener )
	{
		exportListeners.remove( listener );
	}

	/**
	 * Fire an export event to the listeners.
	 * 
	 * @param e the export event
	 * @param start true if the export is starting and false if the export is finished
	 */
	protected void fireExportEvent( ExportEvent e, boolean start )	
	{
		Iterator i = exportListeners.iterator();
		while ( i.hasNext() )
		{
			if ( start )
			{
				((ExportListener) i.next()).exportStarted(e);			
			}
			else
			{
				((ExportListener) i.next()).exportFinished(e);			
			}
		}
	}
	
	/**
	 * Fire an export entity event to the listeners.
	 * 
	 * @param e the export entity event
	 * @param start true if the export of the entity is starting and false if the
	 * export of the entity is finished
	 */
	protected void fireExportEntityEvent( ExportEntityEvent e, boolean start )
	{
		Iterator i = exportListeners.iterator();
		while ( i.hasNext() )
		{
			if ( start )
			{
				((ExportListener) i.next()).entityExportStarted(e);			
			}
			else
			{
				((ExportListener) i.next()).entityExportFinished(e);			
			}
		}
	}
	
	public static void main(String[] args)
	{
		// TODO: Add parameters '-verbose' and '-help'
		if ( args.length < 1 )
		{
			System.out.println("Usage: java net.sourceforge.jdbcexporter.Exporter <export config file> [plugin file]");
			System.exit(-1);
		}
		try
		{
			Exporter exporter = new Exporter();
			if ( args.length > 1 )
			{
				exporter.initPlugins(args[1]);
			}
			FileInputStream fileInput = null;
			String exportConfigFile = args[0];
			try
			{
				fileInput = new FileInputStream( exportConfigFile );
				InputSource in = new InputSource( fileInput );
				exporter.initExportDef( in );
				exporter.setEngine( System.getProperties().getProperty("jdbcexporter.engine", "net.sourceforge.jdbcexporter.engine.BasicEngine") );
				exporter.beginExport();
			}
			finally
			{
				if ( fileInput != null )
				{
					try
					{
						fileInput.close();
					}
					catch ( IOException e )
					{
						LOG.warn("Could not close file '"+exportConfigFile+"'", e );
					}
				}	
			}			
		}
		catch ( Exception e )
		{
			e.printStackTrace();	
			System.exit(-1);
		}
	}
}
