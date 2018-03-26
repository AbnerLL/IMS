package net.sourceforge.datagenerator.ant;

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

import net.sourceforge.datagenerator.GenerateEntityDef;
import net.sourceforge.jdbcexporter.ExportEntityDef;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.ImportEntityDef;

import org.apache.tools.ant.BuildException;

/**
 * The DelegateEntityDefElement provides an Ant wrapper for creating a
 * EntityDef object from Ant. The EntityDef could be one of three types : 
 * GenerateEntityDef, ImportEntityDef and ExportEntityDef. To create a
 * GenerateEntityDef, the setter for the 'target' property should be invoked.
 * To create an ExportEntityDef, the setter for the 'source' property should
 * be invoked with the value 'db'. To create an ImportEntityDef, the setter
 * for the 'source' property should be invoked with any value except 'db'.
 * 
 * @version 0.69
 * @author Chris Nagy
 */
public class DelegateEntityDefElement
{
	/**
	 * Constant indicating that the EntityDef type is unknown.
	 */
	public static final int UNKNOWN_ENTITY_DEF = -1;
	
	/**
	 * Constant indicating an ImportEntityDef.
	 */
	public static final int IMPORT_ENTITY_DEF = 0;
	
	/**
	 * Constant indicating an ExportEntityDef.
	 */
	public static final int EXPORT_ENTITY_DEF = 1;
	
	/**
	 * Constant indicating a GenerateEntityDef.
	 */
	public static final int DATAGENERATE_ENTITY_DEF = 2;
	
	/**
	 * The mapping between custom
	 * types and implementations.
	 */
	protected Map plugins;

	/**
	 * The type of EntityDef to create.
	 */
	protected int entityDefType = UNKNOWN_ENTITY_DEF;
	
	// Common attributes
	/**
	 * The name of the schema that owns the table.
	 */
	protected String schema;
	
	/**
	 * The name of the catalog that contains the table.
	 */
	protected String catalog;
	
	/**
	 * The name of the table.
	 */
	protected String table;
	
	/**
	 * The source file (used when creating an ImportEntityDef).
	 */
	protected File source;
	
	/**
	 * The where clause (used when creating an ExportEntityDef).
	 */
	protected String where;
	
	/**
	 * The number of rows to generate (used when creating a GenerateEntityDef).
	 */
	protected int count;
	
	/**
	 * The target file (used when creating a GenerateEntityDef).
	 */
	protected File target;
	
	/**
	 * The encoding charset
	 */
	protected String encoding;
	
	/**
	 * The delegate delimiter element.
	 */
	protected DelegateDelimiterElement delimiterElement;
	
	/**
	 * The list of column def elements.
	 */
	protected List columnElements;
	
	/**
	 * Constructs an DelegateEntityDefElement with the given
	 * mapping between custom types and implementations.
	 * 
	 * @param plugins the plugin mappings
	 */
	public DelegateEntityDefElement( Map plugins )
	{
		this.plugins = plugins;
		columnElements = new ArrayList();
	}
	
	/**
	 * Sets the name of the schema that owns the table.
	 * 
	 * @param name name of the schema
	 */
	public void setSchema( String name )
	{
		schema = name;
	}
	
	/**
	 * Sets the name of the catalog that contains the table.
	 * 
	 * @param name name of the catalog
	 */
	public void setCatalog( String name )
	{
		catalog = name;
	}

	/**
	 * Sets the name of the table.
	 * 
	 * @param name name of the table
	 */	
	public void setTable( String name )
	{
		System.out.println("SetTable:"+name);
		table = name;
	}
	
	/**
	 * Sets the source. If the source is 'db' then the
	 * EntityDef created will be an ExportEntityDef. Otherwise
	 * the source should be the file name used by an 
	 * ImportEntityDef.
	 * 
	 * @param source either 'db' or the source file
	 */
	public void setSource( String source )
	{
		if ( "db".equals( source ) )
		{
			// initialize export def
			entityDefType = EXPORT_ENTITY_DEF;
		}
		else
		{
			// initialize import def
			entityDefType = IMPORT_ENTITY_DEF;
			this.source = new File( source );
		}
	}

	/**
	 * Sets the where clause for an ExportEntityDef.
	 * 
	 * @param where the where clause
	 */
	public void setWhere( String where )
	{
		this.where = where;
	}
	
	/**
	 * Sets the target of the generated data. The EntityDef
	 * created will be a GenerateEntityDef.
	 * 
	 * @param file the file containing the generated data.
	 */
	public void setTarget( File file )
	{
		entityDefType = DATAGENERATE_ENTITY_DEF;
		this.target = file;
	}

	/**
	 * Sets the encoding of the source or target file.
	 * 
	 * @param encoding the encoding charset
	 * @since 0.71
	 */
	public void setEncoding( String encoding)
	{
		this.encoding = encoding;
	}
	
	/**
	 * Sets the number of rows to generate
	 * 
	 * @param count the number of rows
	 */
	public void setCount( int count )
	{
		this.count = count;
	}
	
	/**
	 * Creates an empty ColumnDefElement and returns it.
	 * 
	 * @return column definition element
	 */
	public DelegateColumnDefElement createColumn()
	{
		DelegateColumnDefElement element = new DelegateColumnDefElement( plugins );
		columnElements.add( element );
		return element;
	}
	
	/**
	 * Creates an empty DelegateDelimiterDefElement and returns
	 * it.
	 *  
	 * @return delimiter formatter element
	 */
	public DelegateDelimiterElement createDelimiter()
	{
		delimiterElement = new DelegateDelimiterElement( plugins );
		return delimiterElement;
	}
			
	/**
	 * Returns the wrapped entity definition.
	 * 
	 * @return entity definition
	 * @throws BuildException if the entity table, delimiter formatter or columns have errors
	 */
	public EntityDef getEntityDef() throws BuildException
	{
		if ( this.table == null )
		{
			throw new BuildException("No value specified for attribute 'table'");
		}

		EntityDef entityDef;
		if ( entityDefType == DATAGENERATE_ENTITY_DEF )
		{
			entityDef = initGenerateEntityDef();
		}
		else if ( entityDefType == IMPORT_ENTITY_DEF )
		{
			entityDef = initImportEntityDef();
		}
		else if ( entityDefType == EXPORT_ENTITY_DEF )
		{
			entityDef = initExportEntityDef();
		}
		else
		{
			throw new BuildException("Unknown entity type '"+table+"' : 'source' or 'target' attribute must be defined");
		}
		
		entityDef.setTable( this.table );
		entityDef.setSchema( this.schema );
		entityDef.setCatalog( this.catalog );
		
		for ( int i = 0; i < columnElements.size(); i++ )
		{
			DelegateColumnDefElement nextColumnElement = (DelegateColumnDefElement) columnElements.get(i);
			entityDef.addColumn( nextColumnElement.createColumnDef( entityDefType ) );
		}
		return (EntityDef) entityDef;	
	}
	
	/**
	 * Initializes a GenerateEntityDef.
	 * 
	 * @return a GenerateEntityDef
	 */
	private GenerateEntityDef initGenerateEntityDef()
	{
		GenerateEntityDef entityDef = new GenerateEntityDef();
		
		if ( delimiterElement == null )
		{
			throw new BuildException("No element 'delimiter' found in entity '"+table+"'");
		}
		entityDef.setDelimiterFormatter( delimiterElement.createDelimiterFormatter() );
		entityDef.setTarget( target );
		if ( encoding != null && !"".equals( encoding ) )
		{
			entityDef.setTargetEncoding( encoding );
		}
		entityDef.setCount( count );
		
		return entityDef;
	}
	
	/**
	 * Initializes an ExportEntityDef.
	 * 
	 * @return an ExportEntityDef
	 */
	private ExportEntityDef initExportEntityDef()
	{
		ExportEntityDef entityDef = new ExportEntityDef();
		
		entityDef.setWhereClause( this.where );
		
		return entityDef;
	}

	/**
	 * Initializes an ImportEntityDef.
	 * 
	 * @return an ImportEntityDef
	 */
	private ImportEntityDef initImportEntityDef()
	{
		ImportEntityDef entityDef = new ImportEntityDef();
		
		if ( delimiterElement == null )
		{
			throw new BuildException("No element 'delimiter' found in entity '"+table+"'");
		}
		
		if ( delimiterElement.isBinaryDelimiterParser() )
		{
			entityDef.setBinaryDelimiterParser( delimiterElement.createBinaryDelimiterParser() );
		}
		else
		{
			entityDef.setDelimiterParser( delimiterElement.createDelimiterParser() );
		}
		entityDef.setSource( source );
		if ( encoding != null && !"".equals( encoding ) )
		{
			entityDef.setSourceEncoding( encoding );
		}
		return entityDef;
	}
	
}
