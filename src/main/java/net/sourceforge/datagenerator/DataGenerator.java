package net.sourceforge.datagenerator;

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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sourceforge.datagenerator.config.GenerateConfig;
import net.sourceforge.datagenerator.event.DataGeneratorEvent;
import net.sourceforge.datagenerator.event.DataGeneratorListener;
import net.sourceforge.datagenerator.event.LogDataGeneratorListener;
import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ConnectionDef;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.Importer;
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
 * The DataGenerator class is the main class that controls the generation
 * of data.
 * 
 * @version  0.6 
 * @author   Chris Nagy
 */
public class DataGenerator 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( DataGenerator.class );

	/**
	 * Prefix for the connection definition classes to use.
	 */
	public static final String CONNECTION_PREFIX = "connection.";
	
	/**
	 * Prefix for the delimiter formatter classes to use.
	 */
	public static final String DELIMITER_PREFIX  = "delimiter.";

	/**
	 * Prefix for the delimiter parser classes to use.
	 */
	public static final String DELIMITER_PARSER_PREFIX = "delimiterparser.";
	
	/**
	 * Prefix for the column value generator classes to use.
	 */	
	public static final String GENERATOR_PREFIX = "generator.";
	
	/**
	 * Prefix for the column translator classes to use.
	 */
	public static final String COLUMNTRANSLATOR_PREFIX = Importer.COLUMNTRANSLATOR_PREFIX;
	
	/**
	 * The factory for producing connection definitions.
	 */
	protected CustomObjectFactory connectionDefFactory;
	
	/**
	 * The factory for producing delimiter formatters.
	 */
	protected CustomObjectFactory delimiterFormatterFactory;

	/**
	 * The factory for producing delimiter parsers.
	 */
	protected CustomObjectFactory delimiterParserFactory;
	
	/**
	 * The factory for producing binary delimiter parsers.
	 * @since 0.74
	 */
	protected CustomObjectFactory binaryDelimiterParserFactory;
	
	/**
	 * The factory for producing column value generators.
	 */
	protected CustomObjectFactory columnValueGeneratorFactory;

	/**
	 * The factory for producing column translators.
	 */
	protected CustomObjectFactory columnTranslatorFactory;
	
	/**
	 * The data generation definition.
	 */
	protected GenerateDef generateDef;
	
	/**
	 * The event listeners.
	 */
	protected List dataGeneratorListeners = new ArrayList();
	
	/**
	 * Constructs a new data generator with the default mappings.
	 */	
	public DataGenerator()
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

		delimiterParserFactory  = new CustomObjectFactory( DelimiterParser.class );
		{
			Map delimiterParserMap = CustomObjectMapping.getDefaultDelimiterParserMapping();
			Iterator keys = delimiterParserMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) delimiterParserMap.get( key );
				LOG.debug( "Adding DelimiterParser mapping '"+key+"' = '"+value+"'");
				delimiterParserFactory.addMapping( key, value );
			}
		}
		
		binaryDelimiterParserFactory = new CustomObjectFactory( BinaryDelimiterParser.class );
		
		columnValueGeneratorFactory = new CustomObjectFactory( ColumnValueGenerator.class );
		{
			Map columnValueGeneratorMap = CustomObjectMapping.getDefaultColumnValueGeneratorMapping();
			Iterator keys = columnValueGeneratorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) columnValueGeneratorMap.get( key );
				LOG.debug( "Adding ColumnValueGenerator mapping '"+key+"' = '"+value+"'");
				columnValueGeneratorFactory.addMapping( key, value );
			}
		}
		
		columnTranslatorFactory = new CustomObjectFactory( ColumnTranslator.class );
		{
			Map columnTranslatorMap = CustomObjectMapping.getDefaultColumnTranslatorMapping();
			Iterator keys = columnTranslatorMap.keySet().iterator();
			while( keys.hasNext() )
			{
				String key = (String) keys.next();
				String value = (String) columnTranslatorMap.get( key );
				LOG.debug( "Adding ColumnTranslator mapping '"+key+"' = '"+value+"'");
				columnTranslatorFactory.addMapping( key, value );
			}
		}
		
	}

	/**
	 * Sets the generate def.
	 * 
	 * @param def generate def
	 */
	public void setGenerateDef( GenerateDef def )
	{
		this.generateDef = def;
	}
	
	/**
	 * Read and parses the generate config file into a GenerateDef class.
	 * 
	 * @param source input source
	 * @throws IOException if the file could not be read
	 */
	public void initGenerateDef( InputSource source ) throws IOException,
		ParserConfigurationException, SAXException, DOMException, InvalidCustomObjectDefException
	{
		LOG.trace( "initImportDef( <InputSource> ) ->");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating( false );
		DocumentBuilder builder = factory.newDocumentBuilder();		
		Document doc = builder.parse( source );
		GenerateConfig config = new GenerateConfig();
		config.setColumnValueGeneratorFactory(columnValueGeneratorFactory);
		config.setDelimiterFormatterFactory(delimiterFormatterFactory);
		config.setDelimiterParserFactory(delimiterParserFactory);
		config.setBinaryDelimiterParserFactory(binaryDelimiterParserFactory);
		config.setConnectionDefFactory(connectionDefFactory);
		config.setColumnTranslatorFactory(columnTranslatorFactory);
		generateDef = config.getGenerate(doc.getFirstChild());							
		if ( LOG.isDebugEnabled() ) 
			LOG.debug("Loaded GenerateDef : "+generateDef.toString());
	}
		
	/**
	 * Parse the plugin property file and adds the external 
	 * connection definition classes, delimiter formatter
	 * and column value generator classes to the appropriate factories.
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
	 * Adds the external connection definition classes, delimiter formatter, delimiter parsers,
	 * column value generator, and column translator classes to the appropriate factories.
	 * 
	 * @param pluginProperties the set of plugins
	 */
	protected void initPlugins( Properties pluginProperties )
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
				LOG.debug( "Adding ConnectionDef mapping '"+connectionType+"' = '"+connectionClass+"'");
				connectionDefFactory.addMapping(connectionType,connectionClass);
			}
			else if ( nextKey.startsWith( DELIMITER_PREFIX ) )
			{
				String delimiterType = nextKey.substring(DELIMITER_PREFIX.length());
				String delimiterClass = pluginProperties.getProperty( nextKey );
				LOG.debug( "Adding DelimiterFormatter mapping '"+delimiterType+"' = '"+delimiterClass+"'");
				delimiterFormatterFactory.addMapping(delimiterType,delimiterClass);	
			}
			else if ( nextKey.startsWith( DELIMITER_PARSER_PREFIX ) )
			{
				String delimiterType = nextKey.substring(DELIMITER_PARSER_PREFIX.length());
				String delimiterClass = pluginProperties.getProperty( nextKey );
				LOG.debug( "Adding DelimiterParser (or BinaryDelimiterParser) mapping '"+delimiterType+"' = '"+delimiterClass+"'");
				delimiterParserFactory.addMapping(delimiterType,delimiterClass);
				binaryDelimiterParserFactory.addMapping(delimiterType,delimiterClass);
			}
			else if ( nextKey.startsWith( GENERATOR_PREFIX ) )
			{
				String generatorType = nextKey.substring(GENERATOR_PREFIX.length());
				String generatorClass = pluginProperties.getProperty( nextKey );
				LOG.debug( "Adding ColumnValueGenerator mapping '"+generatorType+"' = '"+generatorClass+"'");
				columnValueGeneratorFactory.addMapping(generatorType,generatorClass);	
			}
			else if ( nextKey.startsWith( COLUMNTRANSLATOR_PREFIX ) )
			{
				String translatorType = nextKey.substring(COLUMNTRANSLATOR_PREFIX.length());
				String translatorClass = pluginProperties.getProperty( nextKey );
				LOG.debug( "Adding ColumnTranslator mapping '"+translatorType+"' = '"+translatorClass+"'");
				columnTranslatorFactory.addMapping(translatorType,translatorClass);
			}
			else
			{
				LOG.warn( "Unrecognized property '"+nextKey+"'");
			}
		}
		LOG.trace( "initPlugins( <Properties:"+pluginProperties.size()+"> ) <-");
	}
	
	/**
	 * Generate data by processing entity definitions via the entity generator.
	 * 
	 * @throws FileNotFoundException if the log file is not valid
	 * @throws IOException if the log file file cannot be opened and written to
	 */
	public void beginGenerate() throws FileNotFoundException, IOException
	{		
		LOG.trace( "beginGenerate() ->" );
		FileWriter log = null;
		if ( generateDef.getLogFile() != null )
		{
			log = new FileWriter( generateDef.getLogFile() );
			addDataGeneratorListener( new LogDataGeneratorListener( log ));
		}
		
		ConnectionDef connectionDef = generateDef.getConnectionDef();
		Connection connection = null;
		
		if ( connectionDef != null )
		{
			if ( LOG.isDebugEnabled() ) 
				LOG.debug( "Initializing Connection "+connectionDef );
			
			connection = connectionDef.getConnection();
			if ( connection == null )
			{
				throw new IOException( "Could not initialize connection" );
			}
		}
		EntityGenerator generator = new EntityGenerator();
		for ( int i = 0; i < this.dataGeneratorListeners.size(); i++ )
		{
			generator.addDataGeneratorListener( (DataGeneratorListener) this.dataGeneratorListeners.get(i) );
		}
		generator.setConnection( connection );
		GenerateEntityDef[] entities = generateDef.getEntities();
		EntityDef[] sourceEntities   = generateDef.getSourceEntities();
		generator.setSourceEntities( sourceEntities );
		
		fireDataGeneratorEvent( new DataGeneratorEvent( this, generateDef ), true );
		generator.processEntities( entities );
		fireDataGeneratorEvent( new DataGeneratorEvent( this, generateDef ), false );
		
		if ( connectionDef != null )
		{
			connectionDef.releaseConnection( connection );
		}
		if ( log != null )
		{
			log.close();
		}
		LOG.trace( "beginGenerate() <-" );
	}
	
	/**
	 * Fire a data generator event to the event listeners.
	 * 
	 * @param e the event
	 * @param start true if the data generation started
	 */
	protected void fireDataGeneratorEvent( DataGeneratorEvent e, boolean start )
	{
		Iterator i = dataGeneratorListeners.iterator();
		while ( i.hasNext() )
		{
			if ( start )
			{
				((DataGeneratorListener) i.next()).dataGeneratorStarted( e );
			}
			else
			{
				((DataGeneratorListener) i.next()).dataGeneratorFinished( e );				
			}
		}
	}
	
	/**
	 * Adds a data generator listener.
	 * 
	 * @param listener the listener
	 */
	public void addDataGeneratorListener( DataGeneratorListener listener )
	{
		if ( !dataGeneratorListeners.contains( listener ) )
		{
			dataGeneratorListeners.add( listener );
		}
	}
	
	/**
	 * Removes a data generator listener.
	 * 
	 * @param listener the listener
	 */	
	public void removeDataGeneratorListener( DataGeneratorListener listener )
	{
		dataGeneratorListeners.remove( listener );
	}
	
	public static void main(String[] args)
	{
		// TODO: Add parameters '-verbose' and '-help'
		if ( args.length < 1 )
		{
			System.out.println("Usage: java net.sourceforge.datagenerator.DataGenerator <generate config file> [plugin file]");
			System.exit(-1);
		}
		try
		{
			DataGenerator importer = new DataGenerator();
			if ( args.length > 1 )
			{
				importer.initPlugins(args[1]);
			}
			FileInputStream fileInput = null;
			String generateConfigFile = args[0];
			try
			{
				fileInput = new FileInputStream( generateConfigFile );
				InputSource in = new InputSource( fileInput );
				importer.initGenerateDef(in);
				importer.beginGenerate();
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
						LOG.warn("Could not close file '"+generateConfigFile+"'", e );
					}
				}	
			}			
			System.exit(0);
		}
		catch ( Exception e )
		{
			e.printStackTrace();	
			System.exit(-1);
		}	
	}
	
}
