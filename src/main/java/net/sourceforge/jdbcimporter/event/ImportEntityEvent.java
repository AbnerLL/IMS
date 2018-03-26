package net.sourceforge.jdbcimporter.event;
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

import java.util.EventObject;

import net.sourceforge.jdbcimporter.ImportEntityDef;

/**
 * The ImportEntityEvent describes the state of an entity being imported.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
public class ImportEntityEvent extends EventObject
{
	/**
	 * The configuration of the entity being imported.
	 */
	protected ImportEntityDef entityDef;
	
	/**
	 * The exception causing the import of the entity to end.
	 */
	protected Exception       exception;
	
	/**
	 * Constructs an ImportEntityEvent.
	 * 
	 * @param source the source of the event
	 * @param entityDef the configuration of the entity being imported
	 * @param ex the exception that caused abnormal termination of the entity being imported
	 */
	public ImportEntityEvent(Object source, ImportEntityDef entityDef, Exception ex )
	{
		super(source);
		this.entityDef = entityDef;
		this.exception = ex;
	}

	/**
	 * Returns the number of columns for each row being imported.
	 * 
	 * @return the number of columns
	 */
	public int getColumnCount()
	{
		return entityDef.getColumns().length;
	}
	
	/**
	 * Returns the table name.
	 * 
	 * @return the table name
	 */
	public String getTable()
	{
		return entityDef.getTable();
	}
	
	/**
	 * Returns the schema that the entity belongs to.
	 * 
	 * @return the schema name
	 */
	public String getSchema()
	{
		return entityDef.getSchema();
	}

	/**
	 * Returns the catalog that the entity belongs to.
	 * 
	 * @return the catalog name
	 */
	public String getCatalog()
	{
		return entityDef.getCatalog();
	}
	
	public String getEngineClassname()
	{
		if ( entityDef.getImportEngine() != null )
		{
			return entityDef.getImportEngine().getClass().getName();
		}
		else
		{
			return null;
		}
		
	}
	/**
	 * Returns the exception that caused the import of the entity to finish.
	 * The exception is available only when the ImportListener.entityImportFinished
	 * method is called.
	 * 
	 * @return the exception
	 */
	public Exception getException()
	{
		return exception;
	}
}
