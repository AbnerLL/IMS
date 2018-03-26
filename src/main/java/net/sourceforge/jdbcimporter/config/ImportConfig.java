package net.sourceforge.jdbcimporter.config;

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
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.jdbcimporter.ConnectionDef;
import net.sourceforge.jdbcimporter.ImportDef;
import net.sourceforge.jdbcimporter.ImportEntityDef;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The ImportConfig class parses a Node to create an ImportDef.
 * The Node's name must be 'import'.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ImportConfig
{
	/**
	 * The element name that contains the ImportDef.
	 */
	public static final String IMPORT_TAG = "import";
	
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
	 * The element name that contains a custom property.
	 */
	public static final String PROPERTY_TAG = "property";
	
	/**
	 * The attribute name in the PROPERTY_TAG element that contains
	 * the name of the custom property.
	 */
	public static final String PROPERTY_NAME_ATTR = "name";
	
	/**
	 * The attribute name in the PROPERTY_TAG element that contains
	 * the value of the custom property.
	 */
	public static final String PROPERTY_VALUE_ATTR = "value";
		
	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the filename of the log file.
	 */
	public static final String LOG_FILE_ATTR = "log";
	
	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the filename of the log file to write bad data.
	 */
	public static final String LOG_BAD_ATTR  = "bad";
	
	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the filename of the sql script file that will be executed
	 * before the import.
	 */
	public static final String PRE_SQL_FILE_ATTR = "preSQLFile";

	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the filename of the sql script file that will be executed
	 * after the import.
	 */	
	public static final String POST_SQL_FILE_ATTR = "postSQLFile";
	
	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the flag indicating that values should be trimmed after
	 * being read with the delimiter parser.
	 */
	public static final String TRIM_VALUES_ATTR = "trimValues";
	
	/**
	 * The element name that contains the EntityDef.
	 */ 
	public static final String ENTITY_TAG = "entity";
	
	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the number of records to import before executing batch
	 * update.
	 */
	public static final String BATCH_COUNT_ATTR = "batchCount";

	/**
	 * The attribute name in the IMPORT_TAG element that contains
	 * the number of records to import before commiting.
	 */
	public static final String COMMIT_COUNT_ATTR = "commitCount";
	
	/**
	 * Custom object factory that creates connection definitions.
	 */
	protected CustomObjectFactory connectionDefFactory;
	
	/**
	 * The entity config used to create entity definitions from XML nodes. 
	 */
	protected EntityConfig entityConfig;
	
	/**
	 * The entity config delegate.
	 */
	protected ImportEntityConfigDelegate entityConfigDelegate;
	
	/**
	 * Constructs an ImportConfig.
	 */
	public ImportConfig()
	{
		entityConfig = new EntityConfig();
		entityConfigDelegate = new ImportEntityConfigDelegate();
		entityConfig.setDelegate( entityConfigDelegate );
	}
	
	/**
	 * Sets the ConnectionDef factory to use when creating an
	 * ImportDef.
	 * 
	 * @param newFactory the connection definition factory
	 */
	public void setConnectionDefFactory( CustomObjectFactory newFactory )
	{
		connectionDefFactory = newFactory;	
	}
	
	/**
	 * Sets the DelimiterParser factory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the delimiter parser factory
	 */
	public void setDelimiterParserFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setDelimiterParserFactory(newFactory);
	}	

	/**
	 * Sets the BinaryDelimiterParser factory to use when creating
	 * EntityDef(s).
	 * 
	 * @param newFactory the binary delimiter parser factory
	 * @since 0.74
	 */
	public void setBinaryDelimiterParserFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setBinaryDelimiterParserFactory(newFactory);
	}
	
	/**
	 * Sets the ColumnTranslator factory to use when creating
	 * ColumnDef(s).
	 * 
	 * @param newFactory the column translator factory
	 */
	public void setColumnTranslatorFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setColumnTranslatorFactory(newFactory);
	}

	/**
	 * Sets the ColumnValueGeneratorFactory to use when creating
	 * ColumnDef(s).
	 * 
	 * @param newFactory the column value generator factory
	 */
	public void setColumnValueGeneratorFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setColumnValueGeneratorFactory(newFactory);
	}	

	/**
	 * Sets the RowTranslatorFactory to use when creating EntityDef(s).
	 * 
	 * @param newFactory the row translator factory
	 * @since 0.72
	 */
	public void setRowTranslatorFactory( CustomObjectFactory newFactory )
	{
		entityConfigDelegate.setRowTranslatorFactory(newFactory);
	}
	/**
	 * Returns the ImportDef defined in the given Node.
	 * 
	 * @param node the Node
	 * @return the import definition
	 * @throws DOMException if the Node has missing or invalid attributes/elements
	 * @throws InvalidCustomObjectDefException  if a custom object could not
	 * be created from their respective factories 
	 */
	public ImportDef getImport( Node node ) throws DOMException, InvalidCustomObjectDefException
	{
		assertNodeName( IMPORT_TAG, node );
		ImportDef importDef = new ImportDef();

		NamedNodeMap attributes = node.getAttributes();
		Node logFile = attributes.getNamedItem(LOG_FILE_ATTR);
		if ( logFile != null && !"".equals( logFile.getNodeValue() ) )
		{
			importDef.setLogFile( new File( logFile.getNodeValue() ) );
		}
		Node badFile = attributes.getNamedItem(LOG_BAD_ATTR);
		if ( badFile != null && !"".equals( badFile.getNodeValue() ) )
		{
			importDef.setBadFile( new File( badFile.getNodeValue() ) );	
		}
		Node batchCount = attributes.getNamedItem(BATCH_COUNT_ATTR);
		if ( batchCount != null && !"".equals( batchCount.getNodeValue() ) )
		{
			try
			{
				importDef.setBatchCount( Integer.parseInt( batchCount.getNodeValue() ) );
			}
			catch ( NumberFormatException n )
			{
				// TODO: Logging
			}
		}

		Node commitCount = attributes.getNamedItem(COMMIT_COUNT_ATTR);
		if ( commitCount != null && !"".equals( commitCount.getNodeValue() ) )
		{
			try
			{
				importDef.setCommitCount( Integer.parseInt( commitCount.getNodeValue() ) );
			}
			catch ( NumberFormatException n )
			{
				// TODO: Logging
			}
		}
		
		Node preSQLFile = attributes.getNamedItem(PRE_SQL_FILE_ATTR);
		if ( preSQLFile != null && !"".equals( preSQLFile.getNodeValue() ) )
		{
			importDef.setPreSQLFile( new File( preSQLFile.getNodeValue() ) );
		}
		
		Node postSQLFile = attributes.getNamedItem(POST_SQL_FILE_ATTR);
		if ( postSQLFile != null && !"".equals( postSQLFile.getNodeValue() ) )
		{
			importDef.setPostSQLFile( new File( postSQLFile.getNodeValue() ) );
		}
		
		Node trimValues = attributes.getNamedItem(TRIM_VALUES_ATTR);
		if ( trimValues != null && !"".equals( trimValues.getNodeValue() ) )
		{
			importDef.setTrimValues( Boolean.valueOf( trimValues.getNodeValue() ).booleanValue() );
		}
		
		Node connection = ImportConfig.getFirstElementChild(node);
		assertNodeName( CONNECTION_TAG, connection );
		initConnection( importDef, connection );
		
		Node entity = ImportConfig.getNextElementSibling(connection);
		while ( entity != null )
		{
			assertNodeName( ENTITY_TAG, entity );
			importDef.addEntity( (ImportEntityDef) entityConfig.getEntity( entity ) );
			entity = ImportConfig.getNextElementSibling(entity);
		}
		return importDef;
	}

	/**
	 * Asserts that the given node has the given name. If the node
	 * is null or does not have the given name then a DOMException is thrown.
	 * 
	 * @param nodeName the name of node expected
	 * @param node the node
	 * @throws DOMException if the node is null or doesn't have the given name
	 */	
	public static void assertNodeName( String nodeName, Node node ) throws DOMException
	{
		if ( node == null || !nodeName.equals(node.getNodeName()) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, "'"+ nodeName + "' expected, '"+(node==null?"null":node.getNodeName())+"' actual" );
		}
	}

	/**
	 * Returns the first element child of the given node. If there
	 * is not element child then null is returned.
	 * 
	 * @param parent the node
	 * @return the first element child
	 */
	public static Node getFirstElementChild( Node parent )
	{
		Node child = parent.getFirstChild();
		while ( child != null && child.getNodeType() != Node.ELEMENT_NODE	)
		{
			child = child.getNextSibling();	
		}
		return child;
	}

	/**
	 * Returns the next element sibling of the given node. If there
	 * is no element sibling then null is returned.
	 * 
	 * @param sibling the node
	 * @return the next element sibling
	 */
	public static Node getNextElementSibling( Node sibling )
	{
		Node nextSibling = sibling.getNextSibling();
		while ( nextSibling != null && nextSibling.getNodeType() != Node.ELEMENT_NODE )
		{
			nextSibling = nextSibling.getNextSibling();
		}
		return nextSibling;
	}
	
	/**
	 * Returns the first element child of the given node with the given name.
	 * If there is not element child then null is returned.
	 * 
	 * @param parent the node
	 * @param name the child node name
	 * @return the first element child
	 */
	public static Node getFirstElementChild( Node parent, String name )
	{
		Node child = parent.getFirstChild();
		while ( child != null && (child.getNodeType() != Node.ELEMENT_NODE || !name.equals( child.getNodeName() )) )
		{
			child = child.getNextSibling();	
		}
		return child;
	}
	
	/**
	 * Returns the first child's value of the given node. If the node does not have
	 * a child or the first child's value is "" then a DOMException is thrown.
	 * 
	 * @param node the node
	 * @return the first child's value
	 * @throws DOMException if the node does not have a child or the first child's
	 * value is "".
	 */
	public static String getNodeValue( Node node ) throws DOMException
	{
		if ( node.getFirstChild() == null ||
		     "".equals( node.getFirstChild().getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, node.getNodeName() );
		}
		return node.getFirstChild().getNodeValue();
	}	
	
	/**
	 * Initializes the connection definition from the XML node.
	 * 
	 * @param importDef the import definition to add the connection
	 * @param connection the node
	 * @throws DOMException if the node has missing attributes/elements
	 * @throws InvalidCustomObjectDefException if the connection definition
	 * could not be created from the connection definition factory
	 */
	protected void initConnection( ImportDef importDef, Node connection ) throws DOMException, InvalidCustomObjectDefException
	{
		NamedNodeMap attributes = connection.getAttributes();
		Node connectionType = attributes.getNamedItem(CONNECTION_TYPE_ATTR);
		if ( connectionType == null || "".equals( connectionType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, CONNECTION_TAG + " attribute: "+ CONNECTION_TYPE_ATTR );
		}
		Map properties = getProperties( connection.getChildNodes() );
		ConnectionDef connectionDef = (ConnectionDef) connectionDefFactory.getObjectImpl(connectionType.getNodeValue(), properties );
		importDef.setConnectionDef(connectionDef);
	}	

	/**
	 * Returns a map of all the property elements found in the given node 
	 * list.
	 * 
	 * @param properties the node list containing property elements
	 * @return the map
	 */			
	public static Map getProperties( NodeList properties )
	{
		Map props = new HashMap();
		for ( int i = 0; i < properties.getLength(); i++ )
		{
			Node node = properties.item(i);
			if ( PROPERTY_TAG.equals( node.getNodeName() ) )
			{
				NamedNodeMap attributes = node.getAttributes();
				Node name = attributes.getNamedItem( PROPERTY_NAME_ATTR );
				Node value = attributes.getNamedItem( PROPERTY_VALUE_ATTR );
				if ( name != null && !"".equals( name.getNodeValue() ) && value != null )
				{
					props.put( name.getNodeValue(), value.getNodeValue() );	
				}
			}
		}
		return props;
	}
}
