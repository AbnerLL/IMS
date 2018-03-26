package net.sourceforge.datagenerator.config;

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

import java.io.File;
import java.util.Map;

import net.sourceforge.datagenerator.GenerateDef;
import net.sourceforge.datagenerator.GenerateEntityDef;
import net.sourceforge.jdbcexporter.config.ExportEntityConfigDelegate;
import net.sourceforge.jdbcimporter.ConnectionDef;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.config.EntityConfig;
import net.sourceforge.jdbcimporter.config.ImportConfig;
import net.sourceforge.jdbcimporter.config.ImportEntityConfigDelegate;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The GenerateConfig class parses a Node to create an GenerateDef.
 * The Node's name must be 'generate'.
 * 
 * @version  0.6
 * @author   Chris Nagy
 */
public class GenerateConfig 
{
	
	/**
	 * The element name that contains the GenerateDef.
	 */
	public static final String GENERATE_TAG = "generate";
	
	/**
	 * The element name that contains the ConnectionDef.
	 */
	public static final String CONNECTION_TAG = "connection";
	
	/**
	 * The attribute name in the CONNECTION_TAG element that contains
	 * the connection type.
	 */
	public static final String CONNECTION_TYPE_ATTR = "type";

	/**
	 * The attribute name in the LOG_TAG element that contains
	 * the filename of the log file.
	 */
	public static final String LOG_FILE_ATTR = "log";
		
	/**
	 * The element name that contains the EntityDef.
	 */ 
	public static final String ENTITY_TAG = "entity";
	
	/**
	 * The source attribute's value that indicates that
	 * the entity should be an ExportEntityDef.
	 */
	public static final String SOURCE_DB_VALUE = "db";
	
	/**
	 * The connection definition object factory.
	 */
	protected CustomObjectFactory connectionDefFactory;
	
	/**
	 * The entity config that parses data generate entity definitons.
	 */
	protected EntityConfig entityConfig;
	
	/**
	 * The entity config that parses import entity definitons.
	 */
	protected EntityConfig importEntityConfig;
	
	/**
	 * The entity config that parses export entity definitons.
	 */
	protected EntityConfig exportEntityConfig;
	
	/**
	 * The delegate that helps the entity config parse
	 * data generate entity definitions.
	 */
	protected GenerateEntityConfigDelegate entityConfigDelegate;

	/**
	 * The delegate that helps the entity config parse
	 * import entity definitions.
	 */
	protected ImportEntityConfigDelegate importEntityConfigDelegate;

	/**
	 * The delegate that helps the entity config parse
	 * export entity definitions.
	 */
	protected ExportEntityConfigDelegate exportEntityConfigDelegate;
	
	/**
	 * Constructs an GenerateConfig.
	 */
	public GenerateConfig()
	{
		entityConfig = new EntityConfig();
		entityConfigDelegate = new GenerateEntityConfigDelegate();
		entityConfig.setDelegate( entityConfigDelegate );
		
		importEntityConfig = new EntityConfig();
		importEntityConfigDelegate = new ImportEntityConfigDelegate();
		importEntityConfig.setDelegate( importEntityConfigDelegate );

		exportEntityConfig = new EntityConfig();
		exportEntityConfigDelegate = new ExportEntityConfigDelegate( false, false );
		exportEntityConfig.setDelegate( exportEntityConfigDelegate );		
	}

	/**
	 * Sets the ConnectionDefFactory to use when creating an
	 * GenerateDef.
	 * 
	 * @param newFactory the connection definition factory
	 */
	public void setConnectionDefFactory( CustomObjectFactory newFactory )
	{
		connectionDefFactory = newFactory;	
	}
	
	/**
	 * Sets the DelimiterFormatterFactory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the delimiter formatter factory
	 */
	public void setDelimiterFormatterFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setDelimiterFormatterFactory(newFactory);
		exportEntityConfigDelegate.setDelimiterFormatterFactory( newFactory );
	}	

	/**
	 * Sets the DelimiterParserFactory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the delimiter parser factory
	 */
	public void setDelimiterParserFactory(CustomObjectFactory newFactory)
	{
		importEntityConfigDelegate.setDelimiterParserFactory(newFactory);
	}	
	
	/**
	 * Sets the BinaryDelimiterParserFactory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the binary delimiter parser factory
	 * @since 0.74
	 */
	public void setBinaryDelimiterParserFactory(CustomObjectFactory newFactory)
	{
		importEntityConfigDelegate.setBinaryDelimiterParserFactory(newFactory);
	}
	
	/**
	 * Sets the ColumnValueGeneratorFactory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the column value generator factory
	 */
	public void setColumnValueGeneratorFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setColumnValueGeneratorFactory(newFactory);
	}	

	/**
	 * Sets the ColumnTranslatorFactory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the column translator factory
	 */
	public void setColumnTranslatorFactory( CustomObjectFactory newFactory )
	{
		exportEntityConfigDelegate.setColumnTranslatorFactory( newFactory );
		importEntityConfigDelegate.setColumnTranslatorFactory( newFactory );
	}
	
	/**
	 * Returns the GenerateDef defined in the given Node.
	 * 
	 * @param node the node
	 * @return the generate definition
	 * @throws DOMException if the node has missing or invalid attributes/elements
	 */
	public GenerateDef getGenerate( Node node ) throws DOMException, InvalidCustomObjectDefException
	{
		ImportConfig.assertNodeName( GENERATE_TAG, node );
		GenerateDef generateDef = new GenerateDef();

		NamedNodeMap attributes = node.getAttributes();
		Node logFile = attributes.getNamedItem(LOG_FILE_ATTR);
		if ( logFile != null && !"".equals( logFile.getNodeValue() ) )
		{
			generateDef.setLogFile( new File( logFile.getNodeValue() ) );
		}
		
		Node connection = ImportConfig.getFirstElementChild(node);
		if ( connection != null && connection.getNodeName().equals( CONNECTION_TAG ) )
		{
			initConnection( generateDef, connection );
		}
		else if ( connection != null )
		{
			Node entity = connection;
			ImportConfig.assertNodeName( ENTITY_TAG, entity );
			EntityConfig localEntityConfig = getEntityConfig( entity );
			EntityDef entityDef = localEntityConfig.getEntity( entity );
			if ( entityDef instanceof GenerateEntityDef )
			{
				generateDef.addEntity( (GenerateEntityDef) entityDef );
			}
			else
			{
				generateDef.addSourceEntity( entityDef );
			}
		}
		
		Node entity = ImportConfig.getNextElementSibling(connection);
		while ( entity != null )
		{
			ImportConfig.assertNodeName( ENTITY_TAG, entity );
			EntityConfig localEntityConfig = getEntityConfig( entity );
			EntityDef entityDef = localEntityConfig.getEntity( entity );
			if ( entityDef instanceof GenerateEntityDef )
			{
				generateDef.addEntity( (GenerateEntityDef) entityDef );
			}
			else
			{
				generateDef.addSourceEntity( entityDef );
			}
			entity = ImportConfig.getNextElementSibling(entity);
		}
		return generateDef;
	}

	/**
	 * Load the connection definition defined in the given node into the GenerateDef.
	 * 
	 * @param generateDef the generate definition
	 * @param connection the node
	 * @throws DOMException if the node is invalid
	 * @throws InvalidCustomObjectDefException if the connection definition could not be created 
	 * by the connection definition factory
	 */
	protected void initConnection( GenerateDef generateDef, Node connection ) throws DOMException, InvalidCustomObjectDefException
	{
		NamedNodeMap attributes = connection.getAttributes();
		Node connectionType = attributes.getNamedItem(CONNECTION_TYPE_ATTR);
		if ( connectionType == null || "".equals( connectionType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, CONNECTION_TAG + " attribute: "+ CONNECTION_TYPE_ATTR );
		}
		Map properties = ImportConfig.getProperties( connection.getChildNodes() );
		ConnectionDef connectionDef = (ConnectionDef) connectionDefFactory.getObjectImpl(connectionType.getNodeValue(), properties );
		generateDef.setConnectionDef(connectionDef);
	}

	/**
	 * Returns the correct EntityConfig that can handle parsing the given node.
	 * 
	 * @param entity the node representing the entity
	 * @return the entity config that can create the EntityDef
	 */
	protected EntityConfig getEntityConfig( Node entity )
	{
		if ( entity.getAttributes().getNamedItem( GenerateEntityConfigDelegate.TARGET_ATTR ) != null )
		{
			return entityConfig;
		}
		else
		{
			Node sourceAttr = entity.getAttributes().getNamedItem( ImportEntityConfigDelegate.SOURCE_ATTR );
			if ( sourceAttr == null )
			{
				return entityConfig; // Let it handle the error
			}
			
			if ( SOURCE_DB_VALUE.equals( sourceAttr.getNodeValue() ) )
			{
				return exportEntityConfig;
			}
			else
			{
				return importEntityConfig;
			}
		}
	}

	
}
