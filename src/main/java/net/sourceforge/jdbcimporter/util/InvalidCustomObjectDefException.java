package net.sourceforge.jdbcimporter.util;
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
 * The InvalidCustomObjectDefException is an exception thrown when
 * trying to create a custom object from the CustomObjectFactory.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class InvalidCustomObjectDefException extends Exception 
{
	/**
	 * The root exception cause.
	 */
	protected Exception cause;

	/**
	 * Constructor with a message and a root exception cause.
	 * 
	 * @param msg the message
	 * @param cause the root exception
	 */	
	public InvalidCustomObjectDefException( String msg, Exception cause )
	{
		super(msg);
		this.cause = cause;
	}

	/**
	 * Constructor with a message.
	 * 
	 * @param msg the message
	 */	
	public InvalidCustomObjectDefException( String msg )
	{
		super(msg);
		this.cause = null;
	}
}
