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

import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.ImportEntityDef;

/**
 * The ImportEntityRowEvent describes the state of a row being imported.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
public class ImportEntityRowEvent extends EventObject
{
	/**
	 * Constant that indicates there was an error processing the row.
	 */
	public static int STATE_ERROR   = -1;

	/**
	 * Constant that indicates the row was imported successfully.
	 */
	public static int STATE_SUCCESS = 1;

	/**
	 * Constant that indicates the rows was processed successfully but hasn't been imported.
	 * The row will be imported when executeBatch is called and ImportListener.batchProcessed 
	 * method is called.
	 */
	public static int STATE_BATCH   = 2;
	
	/**
	 * The configuration of the entity being imported.
	 */
	ImportEntityDef entityDef;
	
	/**
	 * The line representing the row.
	 */
	String          line;
	
	/**
	 *  The column values of the row.
	 */
	ColumnValue[]   values;
	
	/**
	 * The state of the row.
	 */
	int state;	
	
	/**
	 * The exception that prevented the row from being imported.
	 */
	Exception exception;

	/**
	 * Constructs an ImportEntityRowEvent
	 * 
	 * @param source the source of the event
	 * @param entityDef the configuration of the entity
	 * @param line the line representing the row
	 * @param values the column values of the row
	 * @param state the state of row's import
	 * @param ex the exception that prevented the row from being imported.
	 */
	public ImportEntityRowEvent(Object source, ImportEntityDef entityDef, String line, ColumnValue[] values, int state, Exception ex )
	{
		super(source);
		this.entityDef = entityDef;
		this.line = line;
		this.values = values;
		this.state = state;
		this.exception = ex;
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
	 * Returns the line representing the row.
	 * 
	 * @return the line
	 */
	public String getRow()
	{
		return line;
	}
	
	/**
	 * Returns the state of the import of the row. The state
	 * will be one of : STATE_ERROR, STATE_SUCCESS, STATE_BATCH.
	 * 
	 * @return the state
	 */
	public int getState()
	{
		return state;
	}
	
	/**
	 * Returns the column values of the row.
	 * 
	 * @return the column values
	 */
	public ColumnValue[] getValues()
	{
		ColumnValue[] returnValues = new ColumnValue[values.length];
		for ( int i = 0; i < returnValues.length; i++ )
		{
			returnValues[i] = new ColumnValue();
			returnValues[i].setString( values[i].getString() );
		}
		return returnValues;
	}
	
	/**
	 * Returns the exception that prevented the row from being imported. 
	 * The exception is only set when the state = STATE_ERROR.
	 * 
	 * @return the exception
	 */
	public Exception getException()
	{
		return this.exception;
	}
}
