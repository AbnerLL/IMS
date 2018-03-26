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

import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcexporter.ExportColumnDef;
import net.sourceforge.jdbcexporter.ExportEngine;
import net.sourceforge.jdbcexporter.ExportEntityDef;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.config.EntityConfigDelegate;
import net.sourceforge.jdbcimporter.config.ImportConfig;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The ExportEntityConfigDelegate constructs ExportEntityDef
 * and ExportColumnDef from the Node objects.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ExportEntityConfigDelegate implements EntityConfigDelegate 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( ExportEntityConfigDelegate.class );
	
	/**
	 * The attribute name that contains the name of data file that will
	 * contain the data exported.
	 */	
	public static final String TARGET_ATTR   = "target";

	/**
	 * The attribute name that contains the encoding of the target file.
	 */
	public static final String TARGET_ENCODING_ATTR = "encoding";
	
	/**
	 * The attribute name that contains the where clause for the 
	 * sql select statement.
	 */
	public static final String WHERE_CLAUSE_ATTR = "where";

	/**
	 * The element name that contains the class name of export engine used
	 * to export this entity.
	 */	
	public static final String EXPORT_ENGINE_ATTR = "engine";
	
	/**
	 * The element name that contains the DelimiterFormatter definition.
	 */
	public static final String DELIMITER_TAG = "delimiter";
	
	/**
	 * The attribute name in the DELIMITER_TAG element that contains
	 * contains the delimiter type. 
	 */
	public static final String DELIMITER_TYPE_ATTR = "type";

	/**
	 * The attribute name that contains the ColumnTranslator classname to use
	 * on all column values to be exported.
	 */
	public static final String COLUMN_TRANSLATOR_ATTR = "translator";

	/**
	 * The element name that contains the ColumnTranslator definition.
	 */
	public static final String COLUMN_TRANSLATOR_TAG = "translator";
	
	/**
	 * The attribute name in the COLUMN_TRANSLATOR_TAG element that
	 * contains the column translator type.
	 */
	public static final String COLUMN_TRANSLATOR_TYPE_ATTR = "type";
	
	/**
	 * The custom object factory that can create DelimiterFormatters
	 * from delimiter formatter types. 
	 */
	protected CustomObjectFactory  delimiterFormatterFactory;

	/**
	 * The custom object factory that can create ColumnTranslators
	 * from column translator types.
	 */
	protected CustomObjectFactory  columnTranslatorFactory;
	
	/**
	 * Flag indicating that the 'target' attribute is mandatory (default true).
	 */
	protected boolean targetMandatory = true;
	
	/**
	 * Flag indicating that the 'delimiter' child element is mandatory (default true).
	 */
	protected boolean delimiterMandatory = true;
	
	/**
	 * Creates an ExportEntityConfigDelegate that requires the 'target' attribute and
	 * the 'delimiter' child element.
	 *
	 */
	public ExportEntityConfigDelegate()
	{
		
	}
	
	/**
	 * Creates an ExportEntityConfigDelegate with the given mandatory flags.
	 * 
	 * @param targetMandatory flag indicating whether the 'target' attribute is required or not
	 * @param delimiterMandatory flag indicating whether the 'delimiter' element is required or not
	 * @since 0.69
	 */
	public ExportEntityConfigDelegate( boolean targetMandatory, boolean delimiterMandatory )
	{
		this.targetMandatory = targetMandatory;
		this.delimiterMandatory = delimiterMandatory;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.config.EntityConfigDelegate#createEntityDef(org.w3c.dom.Node)
	 */
	public EntityDef createEntityDef(Node node)throws DOMException, InvalidCustomObjectDefException {
		ExportEntityDef entityDef = new ExportEntityDef();
		NamedNodeMap attributes = node.getAttributes();
	
		Node file = attributes.getNamedItem( TARGET_ATTR );
		if ( targetMandatory )
		{
			if ( ( file == null || "".equals( file.getNodeValue() ) ) )
			{
				throw new DOMException( DOMException.NOT_FOUND_ERR, TARGET_ATTR );
			}
			entityDef.setTarget( new File( file.getNodeValue() ) );
		}
		else 
		{
			if ( ( file != null && !"".equals( file.getNodeValue() ) ) )
			{
				entityDef.setTarget( new File( file.getNodeValue() ) );
			}
		}
		Node where = attributes.getNamedItem( WHERE_CLAUSE_ATTR );
		if ( where != null && !"".equals( where.getNodeValue() ) )
		{
			entityDef.setWhereClause( where.getNodeValue() );
		}
		
		Node encoding = attributes.getNamedItem( TARGET_ENCODING_ATTR );
		if ( encoding != null && !"".equals( encoding.getNodeValue() ) )
		{
			entityDef.setTargetEncoding(encoding.getNodeValue());
		}
			
		Node delimiter = ImportConfig.getFirstElementChild(node, DELIMITER_TAG);
		if ( delimiterMandatory || delimiter != null)
		{
			ImportConfig.assertNodeName(DELIMITER_TAG, delimiter );
			initDelimiter( entityDef, delimiter );
		}

		Node engine = attributes.getNamedItem( EXPORT_ENGINE_ATTR );
		if ( engine != null && engine.getNodeValue() != null && !"".equals( engine.getNodeValue() ) )
		{
			String engineClassname = engine.getNodeValue();
			try
			{
				LOG.debug( "Instantiating Engine '"+engineClassname+"'");
				ExportEngine eng = (ExportEngine) Class.forName( engineClassname ).newInstance();
				entityDef.setExportEngine( eng );
			}
			catch ( ClassNotFoundException e )
			{
				throw new DOMException( DOMException.SYNTAX_ERR, "ExportEngine Class Not Found : '"+engineClassname+"'" );
			}
			catch ( IllegalAccessException e )
			{
				throw new DOMException( DOMException.SYNTAX_ERR,  "ExportEngine Class Not Instantiated : '"+engineClassname+"'");	
			}
			catch ( InstantiationException e )
			{
				throw new DOMException( DOMException.SYNTAX_ERR, "ExportEngine Class Not Instantiated : '"+engineClassname+"'");	
			}
			
		}		
		return entityDef;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.config.EntityConfigDelegate#createColumnDef(org.w3c.dom.Node)
	 */
	public ColumnDef createColumnDef(Node column)
		throws DOMException, InvalidCustomObjectDefException 
	{
		ExportColumnDef columnDef = new ExportColumnDef();
	
		NamedNodeMap attributes = column.getAttributes();
		
		Node translator = attributes.getNamedItem( COLUMN_TRANSLATOR_ATTR );
		if ( translator != null && !"".equals( translator.getNodeValue() ) )
		{
			columnDef.setTranslator( instantiateTranslator( translator.getNodeValue() ) );
		}
		else
		{
			Node child = ImportConfig.getFirstElementChild( column );
			while ( child != null )
			{
				if ( COLUMN_TRANSLATOR_TAG.equals( child.getNodeName() ) )
				{
					translator = child.getAttributes().getNamedItem( COLUMN_TRANSLATOR_TYPE_ATTR );
					Map properties = ImportConfig.getProperties(child.getChildNodes());
					columnDef.setTranslator( (ColumnTranslator) 
						columnTranslatorFactory.getObjectImpl( translator.getNodeValue(), properties ) 
					);
					break;
				}
				child = ImportConfig.getNextElementSibling( child );
			}
		}
		return columnDef;
	}

	/**
	 * Set the custom object factory for creating delimiter formatters.
	 * 
	 * @param factory the delimiter formatter factory
	 */
	public void setDelimiterFormatterFactory(CustomObjectFactory factory) 
	{
		delimiterFormatterFactory = factory;
	}

	/**
	 * Set the custom object factory for create column translators.
	 * 
	 * @param factory the delimiter formatter factory
	 */
	public void setColumnTranslatorFactory(CustomObjectFactory factory )
	{
		columnTranslatorFactory = factory;
		
	}
	/**
	 * Load the delimiter formatter defined in the given node into the ExportEntityDef.
	 * 
	 * @param entityDef the export entity definition
	 * @param delimiter the node containing the delimiter formatter definition.
	 * @throws DOMException if the node is invalid
	 * @throws InvalidCustomObjectDefException if the delimiter formatter could not
	 * be created by the delimiter formatter factory
	 */
	protected void initDelimiter( ExportEntityDef entityDef, Node delimiter ) throws DOMException, InvalidCustomObjectDefException
	{
		NamedNodeMap attributes = delimiter.getAttributes();
		Node delimiterType = attributes.getNamedItem(DELIMITER_TYPE_ATTR);
		if ( delimiterType == null || "".equals( delimiterType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, DELIMITER_TAG + " attribute: "+ DELIMITER_TYPE_ATTR );
		}
		Map properties = ImportConfig.getProperties(delimiter.getChildNodes());
		DelimiterFormatter delimiterParser = (DelimiterFormatter) delimiterFormatterFactory.getObjectImpl(delimiterType.getNodeValue(), properties);
		entityDef.setDelimiterFormatter(delimiterParser);
	}	

	/**
	 * Instantiate a column translator.
	 * 
	 * @param className the class name of the column translator
	 * @return the column translator
	 * @throws DOMException if the column translator could not be instantiated
	 */
	protected ColumnTranslator instantiateTranslator( String className ) throws DOMException
	{
		try
		{
			Object obj = Class.forName( className ).newInstance();	
			if ( !(obj instanceof ColumnTranslator ) )
			{
				throw new DOMException( DOMException.NOT_FOUND_ERR, COLUMN_TRANSLATOR_ATTR + " value '"+className+"' is not a ColumnTranslator");
			}
			return (ColumnTranslator) obj;
		}	
		catch ( ClassNotFoundException ce )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, COLUMN_TRANSLATOR_ATTR + " value '"+className+"' was not found in CLASSPATH" );	
		}
		catch ( IllegalAccessException iae )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, COLUMN_TRANSLATOR_ATTR + " value '"+className+"' could not be instantiated (illegal access)" );	
		}
		catch ( InstantiationException ie )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, COLUMN_TRANSLATOR_ATTR + " value '"+className+"' could not be instantiated" );	
		}
	}
}
