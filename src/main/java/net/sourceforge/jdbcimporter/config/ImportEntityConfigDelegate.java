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
import java.util.Map;

import net.sourceforge.datagenerator.config.GenerateEntityConfigDelegate;
import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.ImportColumnDef;
import net.sourceforge.jdbcimporter.ImportEngine;
import net.sourceforge.jdbcimporter.ImportEntityDef;
import net.sourceforge.jdbcimporter.RowTranslator;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The ImportEntityConfigDelegate is an implementation of the 
 * EntityConfigDelegate that creates ImportEntityDef and ImportColumnDef.
 * A custom object factory should be set before creating the EntityDef.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ImportEntityConfigDelegate implements EntityConfigDelegate 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( ImportEntityConfigDelegate.class );

	/**
	 * The attribute name that contains the name of data file to import
	 * with this entity.
	 */	
	public static final String SOURCE_ATTR   = "source";

	/**
	 * The attribute name that contains the encoding of the source file.
	 */
	public static final String SOURCE_ENCODING_ATTR = "encoding";
	
	/**
	 * The attribute name that contains the class name of import engine used
	 * to import this entity.
	 */	
	public static final String IMPORT_ENGINE_ATTR = "engine";
	
	/**
	 * The attribute name in the COLUMN_TAG element that defines whether
	 * the column is imported from the source file (false) or generated
	 * (true).
	 */
	public static final String GENERATE_ATTR = "generate";
	
	/**
	 * The element name that contains the DelimiterParser definition.
	 */
	public static final String DELIMITER_TAG = "delimiter";
	
	/**
	 * The attribute name in the DELIMITER_TAG element that contains
	 * contains the delimiter type. 
	 */
	public static final String DELIMITER_TYPE_ATTR = "type";

	/**
	 * The element name that contains the ColumnTranslator classname to use
	 * on all column values to be imported.
	 */
	public static final String COLUMN_TRANSLATOR_ATTR = "translator";

	/**
	 * The element name that contains the ColumnTranslator definition to
	 * use on all column values to be imported.
	 */
	public static final String COLUMN_TRANSLATOR_TAG = "translator";
	
	/**
	 * The attribute name that contains the ColumnTranslator type that
	 * maps to a classname.
	 */
	public static final String COLUMN_TRANSLATOR_TYPE_ATTR = "type";
	
	/**
	 * The element name that contains the RowTranslator definition.
	 * @since 0.72
	 */
	public static final String ROW_TRANSLATOR_TAG = "translator";
	
	/**
	 * The attribute name that contains the RowTranslator type that
	 * maps to a classname.
	 * @since 0.72
	 */
	public static final String ROW_TRANSLATOR_TYPE_ATTR = "type";
	
	/**
	 * Custom object factory that creates delimiter parsers.
	 */
	protected CustomObjectFactory  delimiterParserFactory;

	/**
	 * Custom object factory that creates binary delimiter parsers.
	 * @since 0.74
	 */
	protected CustomObjectFactory  binaryDelimiterParserFactory;
	
	/**
	 * Custom object factory that creates column translators.
	 */
	protected CustomObjectFactory  columnTranslatorFactory;
	
	/**
	 * Delegate used to create GenerateColumnDef.
	 */
	protected GenerateEntityConfigDelegate generateColumnConfigDelegate;
	
	/**
	 * Custom object factory that creates row translators.
	 * @since 0.72
	 */
	protected CustomObjectFactory rowTranslatorFactory;
	
	/**
	 * Creates an ImportEntityConfigDelegate.
	 */
	public ImportEntityConfigDelegate()
	{
		generateColumnConfigDelegate = new GenerateEntityConfigDelegate();
	}
	
	/**
	 * Sets the DelimiterParser factory to use when creating the
	 * EntityDef.
	 * 
	 * @param newFactory the delimiter parser factory
	 */
	public void setDelimiterParserFactory( CustomObjectFactory newFactory )
	{
		delimiterParserFactory = newFactory;	
	}

	/**
	 * Sets the BinaryDelimiterParser factory to use when creating 
	 * the EntityDef.
	 * 
	 * @param newFactory the binary delimiter parser factory
	 * @since 0.74
	 */
	public void setBinaryDelimiterParserFactory( CustomObjectFactory newFactory )
	{
		binaryDelimiterParserFactory = newFactory;
	}
	
	/**
	 * Sets the ColumnTranslator factory to use when creating the
	 * ColumnDef(s).
	 * 
	 * @param newFactory the column translator factory
	 */
	public void setColumnTranslatorFactory( CustomObjectFactory newFactory )
	{
		columnTranslatorFactory = newFactory;
	}

	/**
	 * Set the custom object factory for creating column value
	 * generators.
	 * 
	 * @param factory the column value generator factory
	 */
	public void setColumnValueGeneratorFactory(CustomObjectFactory factory) 
	{
		this.generateColumnConfigDelegate.setColumnValueGeneratorFactory( factory );
	}
	
	/**
	 * Set the custom object factory for create row translators.
	 * 
	 * @param newFactory the row translator factory
	 */
	public void setRowTranslatorFactory(CustomObjectFactory newFactory)
	{
		rowTranslatorFactory = newFactory;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.config.EntityConfigDelegate#createEntityDef(org.w3c.dom.Node)
	 */
	public EntityDef createEntityDef(Node node) throws DOMException, InvalidCustomObjectDefException
	{
		ImportEntityDef entityDef = new ImportEntityDef();
		NamedNodeMap attributes = node.getAttributes();
		
		Node file = attributes.getNamedItem( SOURCE_ATTR );
		ImportConfig.assertNodeName(SOURCE_ATTR, file );
		if ( file == null || "".equals( file.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, SOURCE_ATTR );
		}
		File sourceFile = new File( file.getNodeValue() );
		entityDef.setSource( sourceFile );
		if ( entityDef.getSource() == null )
		{
			throw new DOMException( DOMException.INVALID_STATE_ERR, SOURCE_ATTR +" File Not Found : '"+sourceFile.getAbsolutePath()+"'" );
		}
		
		Node fileEncoding = attributes.getNamedItem( SOURCE_ENCODING_ATTR );
		if ( fileEncoding != null && !"".equals( fileEncoding.getNodeValue() ) )
		{
			entityDef.setSourceEncoding( fileEncoding.getNodeValue() );
		}
		
		Node delimiter = ImportConfig.getFirstElementChild(node, DELIMITER_TAG );
		ImportConfig.assertNodeName(DELIMITER_TAG, delimiter );
		initDelimiter( entityDef, delimiter );
		
		Node rowTranslator = ImportConfig.getNextElementSibling(delimiter);
		if ( ROW_TRANSLATOR_TAG.equals( rowTranslator.getNodeName() ) )
		{
			initRowTranslator( entityDef, rowTranslator );
		}
		Node engine = attributes.getNamedItem( IMPORT_ENGINE_ATTR );
		if ( engine != null && engine.getNodeValue() != null && !"".equals( engine.getNodeValue() ) )
		{
			String engineClassname = engine.getNodeValue();
			try
			{
				LOG.debug( "Instantiating Engine '"+engineClassname+"'");
				ImportEngine eng = (ImportEngine) Class.forName( engineClassname ).newInstance();
				entityDef.setImportEngine( eng );
			}
			catch ( ClassNotFoundException e )
			{
				throw new DOMException( DOMException.SYNTAX_ERR, "ImportEngine Class Not Found : '"+engineClassname+"'" );
			}
			catch ( IllegalAccessException e )
			{
				throw new DOMException( DOMException.SYNTAX_ERR,  "ImportEngine Class Not Instantiated : '"+engineClassname+"'");	
			}
			catch ( InstantiationException e )
			{
				throw new DOMException( DOMException.SYNTAX_ERR, "ImportEngine Class Not Instantiated : '"+engineClassname+"'");	
			}
			
		}
		return entityDef;
	}

	/**
	 * Initialize the delimiter parser from the XML node.
	 * 
	 * @param entityDef the entity definition to add the delimiter parser
	 * @param delimiter the node
	 * @throws DOMException if the node has missing attributes/elements
	 * @throws InvalidCustomObjectDefException if the delimiter parser could not
	 * be created from the delimiter parser factory
	 */
	protected void initDelimiter( ImportEntityDef entityDef, Node delimiter ) throws DOMException, InvalidCustomObjectDefException
	{
		NamedNodeMap attributes = delimiter.getAttributes();
		Node delimiterType = attributes.getNamedItem(DELIMITER_TYPE_ATTR);
		if ( delimiterType == null || "".equals( delimiterType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, DELIMITER_TAG + " attribute: "+ DELIMITER_TYPE_ATTR );
		}
		Map properties = ImportConfig.getProperties(delimiter.getChildNodes());
		try
		{
			DelimiterParser delimiterParser = (DelimiterParser) delimiterParserFactory.getObjectImpl(delimiterType.getNodeValue(), properties);
			entityDef.setDelimiterParser(delimiterParser);
		}
		catch ( InvalidCustomObjectDefException e )
		{
			try
			{
				BinaryDelimiterParser binaryDelimiterParser = (BinaryDelimiterParser) binaryDelimiterParserFactory.getObjectImpl(
					delimiterType.getNodeValue(), properties);
				entityDef.setBinaryDelimiterParser(binaryDelimiterParser);
			}
			catch ( InvalidCustomObjectDefException ce )
			{
				throw e;
			}
		}
	}	

	/**
	 * Initialize the row translator from the XML node.
	 * 
	 * @param entityDef the entity definition to add the row translator
	 * @param rowTranslator the node
	 * @throws DOMException if the node has missing elements/attributes
	 * @throws InvalidCustomObjectDefException if the row translator could not
	 * be created from the row translator factory
	 * @since 0.72
	 */
	protected void initRowTranslator( ImportEntityDef entityDef, Node rowTranslator ) throws DOMException, InvalidCustomObjectDefException
	{
		NamedNodeMap attributes = rowTranslator.getAttributes();
		Node rowTranslatorType = attributes.getNamedItem(ROW_TRANSLATOR_TYPE_ATTR);
		if ( rowTranslatorType == null || "".equals( rowTranslatorType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, ROW_TRANSLATOR_TAG + " attribute: "+ROW_TRANSLATOR_TYPE_ATTR);
		}
		Map properties = ImportConfig.getProperties(rowTranslator.getChildNodes());
		RowTranslator translator = (RowTranslator) rowTranslatorFactory.getObjectImpl(rowTranslatorType.getNodeValue(), properties );
		entityDef.setRowTranslator( translator );
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.config.EntityConfigDelegate#createColumnDef(org.w3c.dom.Node)
	 */
	public ColumnDef createColumnDef(Node column) throws DOMException, InvalidCustomObjectDefException {
		ImportColumnDef columnDef = new ImportColumnDef();
		
		NamedNodeMap attributes = column.getAttributes();
		
		Node generate   = attributes.getNamedItem( GENERATE_ATTR );
		if ( generate != null && "true".equals( generate.getNodeValue() ) )
		{
			return this.generateColumnConfigDelegate.createColumnDef( column );
		}
		
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
	 * Instantiate the ColumnTranslator.
	 * 
	 * @param className the class name of the column translator
	 * @return column translator
	 * @throws DOMException if the column translator could not be created
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
