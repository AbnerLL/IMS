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
 
import java.io.InputStream;

/**
 * The ColumnValue class represents a column value.
 * There are three types of column values:
 * 
 * <ul>
 * <li>String
 * <li>bytes[]
 * <li>InputStream
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ColumnValue
{
	/**
	 * Indicates that the column value is a String
	 */
	protected static final int STRING_TYPE = 0;

	/**
	 * Indicates that the column value is a set of bytes
	 */
	protected static final int BYTES_TYPE  = 1;

	/**
	 * Indicates that the column value is an input stream
	 */
	protected static final int STREAM_TYPE = 2;
	
	/**
	 * Indicates that the column value is an object
	 * @since 0.74
	 */
	protected static final int OBJECT_TYPE = 3;
	
	/**
	 * The input stream representing the value.
	 */
	protected InputStream inputStream;
	
	/**
	 * The length of the input stream.
	 * 
	 * @since 0.64
	 */
	protected int inputStreamLength;
	
	/**
	 * The string representing the value.
	 */
	protected String      string;
	
	/**
	 * The bytes representing the value.
	 */
	protected byte[]      bytes;
	
	/**
	 * The object representing the value.
	 * @since 0.74
	 */
	protected Object      object;
	
	/**
	 * The type of the value (must be one of STRING_TYPE, BYTES_TYPE, STREAM_TYPE, OBJECT_TYPE).
	 */
	protected int        inputType;
	
	/**
	 * Returns the column value as an input stream. If the column
	 * value is not an input stream then null is returned.
	 * 
	 * @return column value as an input stream
	 */
	public InputStream getInputStream()
	{
		return inputStream;		
	}

	/**
	 * Returns the length of the input stream. The number must be
	 * postive when importing data.
	 * 
	 * @return length
	 * @since 0.64
	 */
	public int getInputStreamLength( )
	{
		return inputStreamLength;
	}
	
	/**
	 * Sets the column value as an input stream.
	 * 
	 * @param newStream input stream
	 */
	public void setInputStream( InputStream newStream )
	{
		setInputStream( newStream, -1 );
	}
	
	/**
	 * Sets the column value as an input stream.
	 * 
	 * @param newStream input stream
	 * @param length length of the input stream
	 * @since 0.64
	 */
	public void setInputStream( InputStream newStream, int length )
	{
		inputStream = newStream;
		inputStreamLength = length;
		inputType = STREAM_TYPE;
		bytes  = null;
		string = null;
		object = null;
	}

	/**
	 * Returns true if the column value is an input stream.
	 * 
	 * @return whether the column value is an input stream
	 */
	public boolean isInputStream()
	{
		return (inputType == STREAM_TYPE);	
	}
	
	/**
	 * Returns the column value as a String. If the column
	 * value is not a String then null is returned.
	 * 
	 * @return column value as a String
	 */
	public String getString()
	{
		return string;	
	}
	
	/**
	 * Sets the column value as a String.
	 * 
	 * @param value new column value
	 */
	public void setString( String value )
	{
		string = value;
		inputType = STRING_TYPE;
		bytes       = null;
		inputStream = null;
		object      = null;
	}

	/**
	 * Returns true if the column value is a String.
	 * 
	 * @return whether the column value is a String
	 */
	public boolean isString()
	{
		return (inputType == STRING_TYPE);	
	}
	
	/**
	 * Returns the column value as an array of bytes. If the column
	 * value is not an array of bytes then null is returned.
	 * 
	 * @return column value as an array of bytes
	 */
	public byte[] getBytes()
	{
		return bytes;	
	}
	
	/**
	 * Sets the column value as an array of bytes.
	 * 
	 * @param newBytes new column value
	 */
	public void setBytes( byte[] newBytes )
	{
		bytes = newBytes;
		inputType = BYTES_TYPE;
		inputStream = null;
		string      = null;
		object      = null;
	}

	/**
	 * Returns true if the column value is an array of bytes.
	 * 
	 * @return whether the column value is an array of bytes
	 */
	public boolean isBytes()
	{
		return (inputType == BYTES_TYPE);	
	}
	
	/**
	 * Returns the column value as an object.
	 * 
	 * @return column value as an object
	 * @since 0.74
	 */
	public Object getObject()
	{
		if ( object != null )
		{
			return object;
		}
		else if ( bytes != null )
		{
			return bytes;
		}
		else if ( string != null )
		{
			return string;
		}
		return null;
	}
	
	/**
	 * Sets the column value as an object.
	 * 
	 * @param newObject new column value
	 * @since 0.74
	 */
	public void setObject( Object newObject )
	{
		if ( newObject instanceof String )
		{
			setString( (String) newObject );
		}
		else if ( newObject instanceof byte[] )
		{
			setBytes( (byte[]) newObject );
		}
		else
		{
			object = newObject;
			inputType = OBJECT_TYPE;
			bytes       = null;
			inputStream = null;
			string      = null;		
		}
	}

	/**
	 * Returns true if the column value is an object.
	 * 
	 * @return whether the column value is an object
	 * @since 0.74
	 */
	public boolean isObject()
	{
		return (inputType != STREAM_TYPE);	
	}
	
	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		if ( isString() )
		{
			return "'"+this.getString()+"'";
		}
		// TODO: The rest
		return "";
	}

}
