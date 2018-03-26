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
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The DataGeneratorRowEvent describes the state of a row being generated.
 * 
 * @version 0.69
 * @author  Chris Nagy
 *
 */
public class DataGeneratorRowEvent extends EventObject
{
	/**
	 * Constant that indicates there was an error generating the row.
	 */
	public static int STATE_ERROR   = -1;

	/**
	 * Constant that indicates the row was generated successfully.
	 */
	public static int STATE_SUCCESS = 1;
	
	/**
	 * The configuration of the entity being generated.
	 */
	GenerateEntityDef entityDef;
	
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
	 * The exception that prevented the row from being generated.
	 */
	Exception exception;

	/**
	 * Constructs an DataGeneratorRowEvent
	 * 
	 * @param source the source of the event
	 * @param entityDef the configuration of the entity
	 * @param line the line representing the row
	 * @param values the column values of the row
	 * @param state the state of the generation of the row
	 * @param ex the exception that prevented the row from being generated.
	 */
	public DataGeneratorRowEvent(Object source, GenerateEntityDef entityDef, String line, ColumnValue[] values, int state, Exception ex )
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
	 * Returns the state of the data generation of the row. The state
	 * will be one of : STATE_ERROR, STATE_SUCCESS.
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
	 * Returns the exception that prevented the row from being data generated. 
	 * The exception is only set when the state = STATE_ERROR.
	 * 
	 * @return the exception
	 */
	public Exception getException()
	{
		return this.exception;
	}

}
