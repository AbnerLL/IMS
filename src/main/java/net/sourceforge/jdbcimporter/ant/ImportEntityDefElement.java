package net.sourceforge.jdbcimporter.ant;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sourceforge.jdbcimporter.ImportEngine;
import net.sourceforge.jdbcimporter.ImportEntityDef;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;

/**
 * The ImportEntityDefElement provides an Ant wrapper for setting a
 * ImportEntityDef object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class ImportEntityDefElement extends EntityDefElement 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( ImportEntityDefElement.class );
	
	/**
	 * The mapping between connection definition/delimiter parser
	 * types and implementations.
	 */
	protected Map plugins;
		
	/**
	 * The delimiter parser element.
	 */
	protected DelimiterParserElement delimiterElement;
	
	/**
	 * The row translator element.
	 * @since 0.72
	 */
	protected RowTranslatorElement rowTranslatorElement;
	
	/**
	 * The list of columns.
	 */
	protected List columnElements;
	
	/**
	 * The class name of the engine used for the import of this entity.
	 */
	protected String engineClassname = null;	
	
	/**
	 * Constructs an EntityDefElement with the given
	 * mapping between connection definition/delimiter parser
	 * types and implementations.
	 * 
	 * @param plugins the plugin mappings
	 */
	public ImportEntityDefElement( Map plugins )
	{
		this.plugins = plugins;
		entityDef = new ImportEntityDef();
		columnElements = new ArrayList();
	}

	/**
	 * Creates an empty ImportColumnDefElement and returns it.
	 *
	 * @return import column definition element
	 */		
	public DelegateColumnDefElement createColumn()
	{
		DelegateColumnDefElement c = new DelegateColumnDefElement( plugins );
		columnElements.add(c);
		return c;
	}
		
	/**
	 * Creates an empty DelimiterParserElement and returns
	 * it.
	 *  
	 * @return delimiter parser element
	 */
	public DelimiterParserElement createDelimiter()
	{
		delimiterElement = new DelimiterParserElement( plugins );
		return delimiterElement;
	}
	
	/**
	 * Creates an empty RowTranslatorElement and returns it.
	 * 
	 * @return row translator element
	 * @since 0.72
	 */
	public RowTranslatorElement createTranslator()
	{
		rowTranslatorElement = new RowTranslatorElement( plugins );
		return rowTranslatorElement;
	}
	/**
	 * Sets the source of the import data.
	 * 
	 * @param file the file containing the data to import
	 */
	public void setSource( File file )
	{
		((ImportEntityDef) entityDef).setSource( file );
	}
	
	/**
	 * Sets the encoding of the source file.
	 * 
	 * @param encoding the encoding charset
	 * @since 0.71
	 */
	public void setEncoding( String encoding )
	{
		if ( "".equals(encoding) )
		{
			encoding = null;
		}
		((ImportEntityDef) entityDef).setSourceEncoding( encoding );
	}

	/**
	 * Sets the class name of the engine used for the import of the entity.
	 * 
	 * @param className the class name
	 */
	public void setEngine( String className )
	{
		engineClassname = className;
	}
	
	/**
	 * Returns the wrapped entity definition.
	 * 
	 * @return EntityDef the entity definition
	 * @throws BuildException if the entity's table, delimiter parser is invalid
	 */
	public ImportEntityDef getEntityDef() throws BuildException
	{
		if ( entityDef.getTable() == null )
		{
			throw new BuildException("No value specified for attribute 'table'");
		}
		
		for ( int i = 0; i < columnElements.size(); i++ )
		{
			DelegateColumnDefElement nextColumnElement = (DelegateColumnDefElement) columnElements.get(i);
			entityDef.addColumn( nextColumnElement.getColumnDef() );
		}

		if ( delimiterElement == null )
		{
			throw new BuildException("No element 'delimiter' found in entity '"+entityDef.getTable()+"'");
		}
		
		if ( delimiterElement.isBinaryDelimiterParser() )
		{
			((ImportEntityDef)entityDef).setBinaryDelimiterParser(delimiterElement.getBinaryDelimiterParser());
		}
		else
		{
			((ImportEntityDef)entityDef).setDelimiterParser(delimiterElement.getDelimiterParser());
		}
		
		if ( rowTranslatorElement != null )
		{
			((ImportEntityDef)entityDef).setRowTranslator(rowTranslatorElement.getTranslator());
		}
		
		try
		{
			if ( engineClassname != null )
			{
				LOG.debug( "Instantiating Engine '"+engineClassname+"'");
				ImportEngine engine = (ImportEngine) Class.forName( engineClassname ).newInstance();
				((ImportEntityDef) entityDef).setImportEngine( engine );
			}
		}
		catch ( ClassNotFoundException e )
		{
			throw new BuildException( "ImportEngine Class Not Found : '"+engineClassname+"'" );
		}
		catch ( IllegalAccessException e )
		{
			throw new BuildException( "ImportEngine Class Not Instantiated : '"+engineClassname+"'");	
		}
		catch ( InstantiationException e )
		{
			throw new BuildException( "ImportEngine Class Not Instantiated : '"+engineClassname+"'");	
		}
		
		return (ImportEntityDef) entityDef;	
	}
}
