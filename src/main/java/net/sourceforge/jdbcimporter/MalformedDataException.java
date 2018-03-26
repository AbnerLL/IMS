package net.sourceforge.jdbcimporter;

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
 
/**
 * The MalformedDataException exception indicates that one or more
 * column values for the given row is cannot be imported.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class MalformedDataException extends Exception
{
	/**
	 * The wrapped exception.
	 */
	Exception wrappedException;
	
	/**
	 * The string representing the row.
	 */
	String   row;
	
	/**
	 * The index of the column that caused the exception.
	 */
	int      column;

	/**
	 * Constructs a MalformedDataException with no column identified.
	 * 
	 * @param msg the exception message
	 */	
	public MalformedDataException( String msg )
	{
		super(msg);
		this.column = -1;
	}
	
	/**
	 * Constructs a MalformedDataException for the given column index.
	 * 
	 * @param msg the exception message
	 * @param newColumn the column index
	 */
	public MalformedDataException( String msg, int newColumn )
	{
		super(msg);
		this.column = newColumn;	
	}
	
	/**
	 * Constructs a MalformedDataException with no column indicated.
	 * 
	 * @param msg the exception message
	 * @param e the wrapped exception
	 */
	public MalformedDataException( String msg, Exception e )
	{
		super(msg);	
		this.wrappedException = e;
	}
	
	/**
	 * Constructs a MalformedDataException for the given column index and
	 * a wrapped exception.
	 * 
	 * @param msg the exception message
	 * @param newColumn the column index
	 * @param e the wrapped exception
	 */
	public MalformedDataException( String msg, int newColumn, Exception e )
	{
		super(msg);
		this.wrappedException = e;
		this.column = newColumn;
	}

	/**
	 * Constructs a MalformedDataException for the given column index and
	 * a wrapped exception.
	 * 
	 * @param msg the exception message
	 * @param row the row
	 * @param e the wrapped exception
	 */
	public MalformedDataException( String msg, String row, Exception e )
	{
		super(msg);
		this.wrappedException = e;
		this.row = row;
	}
	
	/**
	 * Overrides printStackTrace().
	 */
	public void printStackTrace()
	{
		printStackTrace( System.err );
	}
	
	/**
	 * Overrides printStackTrace(PrintStream).
	 */
	public void printStackTrace( java.io.PrintStream stream )
	{
		super.printStackTrace(stream);
		wrappedException.printStackTrace(stream);
	}
	
	/**
	 * Returns the index of the column that caused the exception.
	 * 
	 * @return column index
	 */
	public int getColumn()
	{
		return column;	
	}
	
	/**
	 * Returns the row that caused the exception.
	 * 
	 * @return row
	 */
	public String getRow()
	{
		return row;
	}
	
	/**
	 * Sets the row that caused the exception. This value can only be set once.
	 * 
	 * @param row the string representation of the row
	 */
	public void setRow( String row )
	{
		if ( this.row == null )
		{
			this.row = row;
		}
	}
}
