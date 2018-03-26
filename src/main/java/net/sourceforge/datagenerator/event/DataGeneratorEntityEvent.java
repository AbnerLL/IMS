package net.sourceforge.datagenerator.event;

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

import net.sourceforge.datagenerator.GenerateEntityDef;

/**
 * The DataGeneratorEntityEvent describes the state of an entity being whose data is being generated.
 * 
 * @version 	0.69
 * @author      Chris Nagy
 */
public class DataGeneratorEntityEvent extends EventObject
{
	/**
	 * The configuration of the entity whose data will be generated.
	 */
	protected GenerateEntityDef entityDef;
	
	/**
	 * The exception causing the data generation of the entity to end.
	 */
	protected Exception       exception;

	/**
	 * Constructs an DataGeneratorEntityEvent.
	 * 
	 * @param source the source of the event
	 * @param entityDef the configuration of the entity whose data is being generated
	 * @param ex the exception that caused abnormal termination of the data generation
	 */	
	public DataGeneratorEntityEvent(Object source, GenerateEntityDef entityDef, Exception ex )
	{
		super(source);
		this.entityDef = entityDef;
		this.exception = ex;
	}

	/**
	 * Returns the number of columns for each row being generated.
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

	/**
	 * Returns the number of rows that will be generated.
	 * 
	 * @return the number of rows
	 */
	public int getCount()
	{
		return entityDef.getCount();
	}
	
	/**
	 * Returns the exception that caused the data generation of the entity to finish.
	 * 
	 * @return the exception
	 */
	public Exception getException()
	{
		return exception;
	}
	
}
