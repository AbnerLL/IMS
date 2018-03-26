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
 * The ImportEntityBatchEvent represents the state of a batch import.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
public class ImportEntityBatchEvent extends EventObject
{
	/**
	 * The configuration of the entity.
	 */
	ImportEntityDef entityDef;
	
	/**
	 * The rows that were imported. The values are relative
	 * to the last execute batch.
	 */
	int[] goodRows;
	
	/**
	 * The rows that could not be imported. The values are relative
	 * to the last execute batch.
	 */
	int[] badRows;
	
	/**
	 * The cause of the rows that could not be imported.
	 */
	Exception exception;
	
	/**
	 * Constructs a new ImportEntityBatchEvent
	 * 
	 * @param source the source of the event
	 * @param entityDef the configuration of the entity
	 * @param goodRows the rows that were imported
	 * @param badRows the rows that could not be imported
	 * @param exception the exception that describes why the rows were not imported
	 */
	public ImportEntityBatchEvent(Object source, ImportEntityDef entityDef, int[] goodRows, int[] badRows, Exception exception )
	{
		super(source);
		this.entityDef = entityDef;
		this.goodRows = goodRows;
		this.badRows  = badRows;
		this.exception = exception;
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

	/**
	 * Returns the row indices that could not be imported. The value
	 * of the indices are relative to the last execute batch.
	 * 
	 * @return the row indices
	 */
	public int[] getBadRows()
	{
		return badRows;
	}

	/**
	 * Returns the exception that caused some rows to not be imported.
	 * 
	 * @return the exception
	 */
	public Exception getException()
	{
		return exception;
	}

	/**
	 * Returns the row indices that were imported. The value
	 * of the indices are relative to the last execute batch.
	 * 
	 * @return the row indices.
	 */
	public int[] getGoodRows()
	{
		return goodRows;
	}

}
