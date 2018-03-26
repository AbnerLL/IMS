package net.sourceforge.jdbcexporter.ant;

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

import net.sourceforge.jdbcexporter.ExportEngine;
import net.sourceforge.jdbcexporter.ExportEntityDef;
import net.sourceforge.jdbcimporter.ant.EntityDefElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;

/**
 * The ExportEntityDefElement provides an Ant wrapper for setting a
 * ExportEntityDef object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class ExportEntityDefElement extends EntityDefElement 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( ExportEntityDefElement.class );

	/**
	 * The mapping between connection definition/delimiter formatter
	 * types and implementations.
	 */
	protected Map plugins;
		
	/**
	 * The delimiter formatter element.
	 */
	protected DelimiterFormatterElement delimiterElement;
	
	/**
	 * The list of column def elements.
	 */
	protected List columnElements;
	
	/**
	 * The class name of the engine used for the export of this entity.
	 */
	protected String engineClassname = null;	
	
	/**
	 * Constructs an EntityDefElement with the given
	 * mapping between connection definition/delimiter formatter
	 * types and implementations.
	 * 
	 * @param plugins the plugin mappings
	 */
	public ExportEntityDefElement( Map plugins )
	{
		this.plugins = plugins;
		entityDef = new ExportEntityDef();
		columnElements = new ArrayList();
	}
	
	/**
	 * Creates an empty ExportColumnDefElement and returns it.
	 *
	 * @return the export column definition element 
	 */		
	public ExportColumnDefElement createColumn()
	{
		ExportColumnDefElement c = new ExportColumnDefElement( plugins );
		columnElements.add(c);
		return c;
	}
	
	/**
	 * Creates an empty DelimiterFormatterElement and returns
	 * it.
	 *  
	 * @return the delimiter formatter element
	 */
	public DelimiterFormatterElement createDelimiter()
	{
		delimiterElement = new DelimiterFormatterElement( plugins );
		return delimiterElement;
	}
		
	/**
	 * Sets the target file of the exported data.
	 * 
	 * @param file The file that will contain the exported data
	 */
	public void setTarget( File file )
	{
		((ExportEntityDef) entityDef).setTarget( file );
	}

	/**
	 * Sets the target file's encoding.
	 * 
	 * @param encoding the encoding charset
	 * @since 0.71
	 */
	public void setEncoding( String encoding )
	{
		if ( "".equals( encoding ) )
		{
			encoding = null;
		}
		((ExportEntityDef) entityDef).setTargetEncoding( encoding );
	}
	
	/**
	 * Sets the where clause used by the select statement.
	 * 
	 * @param clause the where clause
	 */
	public void setWhere( String clause )
	{
		((ExportEntityDef) entityDef).setWhereClause( clause );
	}

	/**
	 * Sets the class name of the engine used for the export of the entity.
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
	 * @return the export entity definition
	 * @throws BuildException if the entity definition's table, delimiter formatter or column definitions
	 * are invalid
	 */
	public ExportEntityDef getEntityDef() throws BuildException
	{
		if ( entityDef.getTable() == null )
		{
			throw new BuildException("No value specified for attribute 'table'");
		}
		
		for ( int i = 0; i < columnElements.size(); i++ )
		{
			ExportColumnDefElement nextColumnElement = (ExportColumnDefElement) columnElements.get(i);
			entityDef.addColumn( nextColumnElement.getColumnDef() );
		}

		if ( delimiterElement == null )
		{
			throw new BuildException("No element 'delimiter' found in entity '"+entityDef.getTable()+"'");
		}
		((ExportEntityDef)entityDef).setDelimiterFormatter(delimiterElement.getDelimiterFormatter());
		
		try
		{
			if ( engineClassname != null )
			{
				LOG.debug( "Instantiating Engine '"+engineClassname+"'");
				ExportEngine engine = (ExportEngine) Class.forName( engineClassname ).newInstance();
				((ExportEntityDef) entityDef).setExportEngine( engine );
			}
		}
		catch ( ClassNotFoundException e )
		{
			throw new BuildException( "ExportEngine Class Not Found : '"+engineClassname+"'" );
		}
		catch ( IllegalAccessException e )
		{
			throw new BuildException( "ExportEngine Class Not Instantiated : '"+engineClassname+"'");	
		}
		catch ( InstantiationException e )
		{
			throw new BuildException( "ExportEngine Class Not Instantiated : '"+engineClassname+"'");	
		}
		
		return (ExportEntityDef) entityDef;	
	}
}
