package net.sourceforge.jdbcexporter.config;

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

import net.sourceforge.jdbcexporter.ExportDef;
import net.sourceforge.jdbcexporter.ExportEntityDef;
import net.sourceforge.jdbcimporter.ConnectionDef;
import net.sourceforge.jdbcimporter.config.EntityConfig;
import net.sourceforge.jdbcimporter.config.ImportConfig;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The ExportConfig class parses a Node to create an ExportDef.
 * The Node's name must be 'export'.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ExportConfig 
{
	/**
	 * The element name that contains the ExportDef.
	 */
	public static final String EXPORT_TAG = "export";
	
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
	 * The connection definition object factory.
	 */
	protected CustomObjectFactory connectionDefFactory;

	/**
	 * The entity config that parses entity definitons.
	 */
	protected EntityConfig entityConfig;

	/**
	 * The delegate that helps the entity config parse
	 * entity definitions.
	 */
	protected ExportEntityConfigDelegate entityConfigDelegate;

	/**
	 * Constructs an ExportConfig.
	 */
	public ExportConfig()
	{
		entityConfig = new EntityConfig();
		entityConfigDelegate = new ExportEntityConfigDelegate();
		entityConfig.setDelegate( entityConfigDelegate );
	}

	/**
	 * Sets the factory to use when creating an
	 * connection definitions.
	 * 
	 * @param newFactory the connection definition factory
	 */
	public void setConnectionDefFactory( CustomObjectFactory newFactory )
	{
		connectionDefFactory = newFactory;	
	}
	
	/**
	 * Sets the factory to use when creating
	 * delimiter formatters.
	 * 
	 * @param newFactory the delimiter formatter factory
	 */
	public void setDelimiterFormatterFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setDelimiterFormatterFactory(newFactory);
	}	

	/**
	 * Sets the factory to use when creating column translators.
	 * 
	 * @param newFactory the column translator factory
	 */
	public void setColumnTranslatorFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setColumnTranslatorFactory( newFactory );
	}	
	
	/**
	 * Returns the ExportDef defined in the given Node.
	 * 
	 * @param node the node
	 * @return the export definition
	 * @throws DOMException if the Node has missing or invalid attributes/elements
	 */
	public ExportDef getExport( Node node ) throws DOMException, InvalidCustomObjectDefException
	{
		ImportConfig.assertNodeName( EXPORT_TAG, node );
		ExportDef exportDef = new ExportDef();

		NamedNodeMap attributes = node.getAttributes();
		Node logFile = attributes.getNamedItem(LOG_FILE_ATTR);
		if ( logFile != null && !"".equals( logFile.getNodeValue() ) )
		{
			exportDef.setLogFile( new File( logFile.getNodeValue() ) );
		}
		
		Node connection = ImportConfig.getFirstElementChild(node);
		if ( connection != null && connection.getNodeName().equals( CONNECTION_TAG ) )
		{
			initConnection( exportDef, connection );
		}
		else if ( connection != null )
		{
			Node entity = connection;
			ImportConfig.assertNodeName( ENTITY_TAG, entity );
			exportDef.addEntity( (ExportEntityDef) entityConfig.getEntity( entity ) );
		}
		
		Node entity = ImportConfig.getNextElementSibling(connection);
		while ( entity != null )
		{
			ImportConfig.assertNodeName( ENTITY_TAG, entity );
			exportDef.addEntity( (ExportEntityDef) entityConfig.getEntity( entity ) );
			entity = ImportConfig.getNextElementSibling(entity);
		}
		return exportDef;
	}

	/**
	 * Load the connection definition defined in the given node into the ExportDef.
	 * 
	 * @param exportDef the ExportDef
	 * @param connection the node
	 * @throws DOMException if the node is invalid
	 * @throws InvalidCustomObjectDefException if the connection definition could not
	 * be created by the connection definition factory
	 */
	protected void initConnection( ExportDef exportDef, Node connection ) throws DOMException, InvalidCustomObjectDefException
	{
		NamedNodeMap attributes = connection.getAttributes();
		Node connectionType = attributes.getNamedItem(CONNECTION_TYPE_ATTR);
		if ( connectionType == null || "".equals( connectionType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, CONNECTION_TAG + " attribute: "+ CONNECTION_TYPE_ATTR );
		}
		Map properties = ImportConfig.getProperties( connection.getChildNodes() );
		ConnectionDef connectionDef = (ConnectionDef) connectionDefFactory.getObjectImpl(connectionType.getNodeValue(), properties );
		exportDef.setConnectionDef(connectionDef);
	}
}
