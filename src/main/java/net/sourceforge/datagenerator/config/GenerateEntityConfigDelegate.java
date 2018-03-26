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

import net.sourceforge.datagenerator.ColumnValueGenerator;
import net.sourceforge.datagenerator.GenerateColumnDef;
import net.sourceforge.datagenerator.GenerateEntityDef;
import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.config.EntityConfigDelegate;
import net.sourceforge.jdbcimporter.config.ImportConfig;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The GenerateEntityConfigDelegate constructs GenerateEntityDef
 * and GenerateColumnDef from the Node objects.
 * 
 * @version  0.6
 * @author     Chris Nagy
 */
public class GenerateEntityConfigDelegate implements EntityConfigDelegate 
{
	
	/**
	 * The attribute name that contains the name of data file that will
	 * contain the data generated.
	 */	
	public static final String TARGET_ATTR   = "target";

	/**
	 * The attribute name that contains the encoding of the target file.
	 */
	public static final String TARGET_ENCODING_ATTR = "encoding";
	
	/**
	 * The attribute name that contains the number of rows that will
	 * be generated. 
	 */
	public static final String COUNT_ATTR   = "count";

	/**
	 * The element name that contains the delimiter formatter definition.
	 */
	public static final String DELIMITER_TAG = "delimiter";
	
	/**
	 * The attribute name in the DELIMITER_TAG element that contains
	 * contains the delimiter formatter type. 
	 */
	public static final String DELIMITER_TYPE_ATTR = "type";

	/**
	 * The element name that contains the column value generator definiton.
	 */
	public static final String COLUMN_VALUE_GENERATOR_TAG = "generator";

	/**
	 * The attribute name in the COLUMN_VALUE_GENERATOR_TAG element that
	 * contains the column value generator type. 
	 */
	public static final String COLUMN_VALUE_GENERATOR_TYPE_ATTR = "type";

	
	/**
	 * The custom object factory that can create DelimiterFormatters
	 * from delimiter formatter types. 
	 */
	protected CustomObjectFactory  delimiterFormatterFactory;

	/**
	 * The custom object factory that can create ColumnValueGenerator
	 * from column value generator types. 
	 */	
	protected CustomObjectFactory  columnValueGeneratorFactory;

	/**
	 * @see net.sourceforge.jdbcimporter.config.EntityConfigDelegate#createEntityDef(org.w3c.dom.Node)
	 */
	public EntityDef createEntityDef(Node node)
		throws DOMException, InvalidCustomObjectDefException 
	{
		GenerateEntityDef entityDef = new GenerateEntityDef();
		
		NamedNodeMap attributes = node.getAttributes();
		
		Node file = attributes.getNamedItem( TARGET_ATTR );
		ImportConfig.assertNodeName(TARGET_ATTR, file );
		if ( file == null || "".equals( file.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, TARGET_ATTR );
		}
		entityDef.setTarget( new File( file.getNodeValue() ) );
		
		Node count = attributes.getNamedItem( COUNT_ATTR );
		ImportConfig.assertNodeName(COUNT_ATTR, count );
		if ( count == null || "".equals( count.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, COUNT_ATTR );
		}
		entityDef.setCount( Integer.parseInt( count.getNodeValue() ) );

		Node encoding = attributes.getNamedItem( TARGET_ENCODING_ATTR );
		if ( encoding != null && !"".equals( encoding.getNodeValue() ) )
		{
			entityDef.setTargetEncoding(encoding.getNodeValue());
		}
		
		Node delimiter = ImportConfig.getFirstElementChild(node, DELIMITER_TAG );
		ImportConfig.assertNodeName(DELIMITER_TAG, delimiter );
		initDelimiter( entityDef, delimiter );

		return entityDef;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.config.EntityConfigDelegate#createColumnDef(org.w3c.dom.Node)
	 */
	public ColumnDef createColumnDef(Node node)
		throws DOMException, InvalidCustomObjectDefException {
		GenerateColumnDef columnDef = new GenerateColumnDef();
		
		Node generator = ImportConfig.getFirstElementChild(node, COLUMN_VALUE_GENERATOR_TAG );
		ImportConfig.assertNodeName( COLUMN_VALUE_GENERATOR_TAG, generator );
		
		NamedNodeMap attributes = generator.getAttributes();
		Node generatorType = attributes.getNamedItem(COLUMN_VALUE_GENERATOR_TYPE_ATTR);
		if ( generatorType == null || "".equals( generatorType.getNodeValue() ) )
		{
			throw new DOMException( DOMException.NOT_FOUND_ERR, COLUMN_VALUE_GENERATOR_TAG + " attribute: "+ COLUMN_VALUE_GENERATOR_TYPE_ATTR );
		}
		Map properties = ImportConfig.getProperties(generator.getChildNodes());
		ColumnValueGenerator columnValueGenerator = (ColumnValueGenerator) columnValueGeneratorFactory.getObjectImpl(generatorType.getNodeValue(), properties);
		columnDef.setColumnValueGenerator(columnValueGenerator);

		return columnDef;
	}

	/**
	 * Set the custom object factory for creating column value
	 * generators.
	 * 
	 * @param factory the column value generator factory
	 */
	public void setColumnValueGeneratorFactory(CustomObjectFactory factory) {
		columnValueGeneratorFactory = factory;
	}

	/**
	 * Set the custom object factory for creating delimiter
	 * formatters.
	 * 
	 * @param factory the delimiter formatter factory
	 */
	public void setDelimiterFormatterFactory(CustomObjectFactory factory) {
		delimiterFormatterFactory = factory;
	}

	/**
	 * Load the delimiter formatter defined in the given node into the GenerateEntityDef.
	 * 
	 * @param entityDef the generate entity definition
	 * @param delimiter the node containing the delimiter formatter definition.
	 * @throws DOMException if the node is invalid
	 * @throws InvalidCustomObjectDefException if the delimiter formatter could not be 
	 * created by the delimiter formatter factory
	 */
	protected void initDelimiter( GenerateEntityDef entityDef, Node delimiter ) throws DOMException, InvalidCustomObjectDefException
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


}
