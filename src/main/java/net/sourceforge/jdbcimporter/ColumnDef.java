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
 
import java.lang.reflect.Field;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * The ColumnDef class defines a column in an entity. 
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class ColumnDef
{
	/**
	 * Constant that indicates that the column's SQL Type is unknown.
	 * 
	 * @since 0.64
	 */
	public static final int UNKNOWN_SQL_TYPE = Integer.MIN_VALUE;
	
	/**
	 * The date format.
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * The time format.
	 */
	public static final String TIME_FORMAT = "HH:mm:ss";
	
	/**
	 * The timestamp format.
	 */
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * The integer/long format.
	 */
	public static final String FULL_NUMBER_FORMAT = "###0";
	
	/**
	 * The decimal format.
	 */
	public static final String DECIMAL_FORMAT     = "###0.##";
	
	/**
	 * The name of the column.
	 */
	String name;
	
	/**
	 * The SQL type of the column.
	 */
	int   type = UNKNOWN_SQL_TYPE;
	
	/**
	 * The default value (as a string).
	 */
	String defaultValue;
	
	/**
	 * The pattern that will used to format/parse an object to/from a string.
	 */
	String formatPattern;
	
	/**
	 * The format that will be used to format/parse an object to/from a string.
	 */
	Format format;
	
	/**
	 * The properties of the column definition.
	 */
	Properties properties;
	
	/**
	 * Sets the name of the column.
	 * 
	 * @param newName name of the column
	 */	
	public void setName( String newName )
	{
		name = newName;		
	}
	
	/**
	 * Returns the name of the column
	 * 
	 * @return name of the column
	 */
	public String getName()
	{
		return name;	
	}
	
	/**
	 * Sets the sql type of the column.
	 * 
	 * @param newType name (case-insensitive) of the sql type that equals
	 * the name of a static variable defined in java.sql.Types
	 */
	public void setSQLType( String newType )
	{
		type = convertStringToSQLType( newType );
		createFormat();
	}
	
	/**
	 * Returns the sql type of the column
	 * 
	 * @return the value of a static variable defined in java.sql.Types
	 */
	public int getType()
	{
		return type;	
	}

	/**
	 * Sets the sql type of the column
	 * 
	 * @param newType value of a static variable defined in java.sql.Types
	 */	
	public void setType( int newType )
	{
		type = newType;
		createFormat();	
	}
	
	/**
	 * Sets the default value of the column. The default value
	 * of the column is applied when a row's column value is null
	 * or an empty String.
	 * 
	 * @param newDefaultValue default value for this column
	 */
	public void setDefaultValue( String newDefaultValue )
	{
		defaultValue = newDefaultValue;	
	}
	
	/**
	 * Returns the default value of the column.
	 * 
	 * @return default value for this column
	 */
	public String getDefaultValue()
	{
		return defaultValue;	
	}
	
	/**
	 * Sets the format pattern used when parsing the data in the file.
	 * The pattern syntax is taken from java.text.SimpleDataFormat for
	 * date, time, and timestamp column types.
	 * 
	 * @param pattern the format pattern
	 */
	public void setFormatPattern( String pattern )
	{
		formatPattern = pattern;
		if ( format == null )
		{
			createFormat();
		}
		else if ( format instanceof SimpleDateFormat )
		{
			((SimpleDateFormat) format).applyPattern( pattern );
		}
		else if ( format instanceof DecimalFormat )
		{
			((DecimalFormat) format).applyPattern( pattern );
		}
	}

	/**
	 * Returns the format pattern used when parsing the data in the file.
	 * 
	 * @return the format pattern
	 */
	public String getFormatPattern()
	{
		return formatPattern;
	}
	
	/**
	 * Returns the format used when parsing the data in the file.
	 * 
	 * @return the format
	 */
	public Format getFormat()
	{
		return format;		
	}

	/**
	 * Add a property that is associated with the column def.
	 * 
	 * @param key the name of the property
	 * @param value the value of the property
	 */
	public void addProperty( String key, String value )
	{
		if ( properties == null )
		{
			properties = new Properties();
		}
		properties.put( key, value );
	}
	
	/**
	 * Returns the value of a property that is associated with the column def. If
	 * the property does not exist then null is returned.
	 * 
	 * @param key the name of the property
	 * @return the value of the property
	 */
	public String getProperty( String key )
	{
		if ( properties != null )
			return properties.getProperty( key );
		else
			return null;
	}
	/**
	 * Converts the name (case-insensitive) of a static variable defined in java.sql.Types
	 * into the value of the static variable. If no static variable is found
	 * with the given name then -1 is returned.
	 * 
	 * @param sqlString name of a static variable defined in java.sql.Types
	 */
	protected int convertStringToSQLType( String sqlString )
	{
		int returnValue = UNKNOWN_SQL_TYPE;
		Field[] fields = Types.class.getDeclaredFields();
		for ( int i = 0; i < fields.length; i++ )
		{
			if ( fields[i].getType().getName().equals("int") ||
				 fields[i].getType().equals( Integer.class ) )
			{
				if ( fields[i].getName().equalsIgnoreCase( sqlString ) )
				{
					try
					{
						returnValue = fields[i].getInt(null);
					}
					catch ( IllegalAccessException e )
					{
						//TODO: add logging messages
					}
					break;		
				}
			}	     
		}
		return returnValue;	
	}
	
	/**
	 * Create the format based on the SQL type and the pattern.
	 */
	protected void createFormat()
	{
		if ( format != null || type == -1 )
		{
			return;
		}

		if ( formatPattern == null )
		{
			switch( type )
			{
				case Types.DATE:
					formatPattern = DATE_FORMAT;
					break;
				case Types.TIME:
					formatPattern = TIME_FORMAT;
					break;
				case Types.TIMESTAMP:
					formatPattern = TIMESTAMP_FORMAT;
					break;
				case Types.BIGINT:
				case Types.INTEGER:
				case Types.SMALLINT:
				case Types.TINYINT:
					formatPattern = FULL_NUMBER_FORMAT;
					break;
				case Types.DECIMAL:
				case Types.DOUBLE:
				case Types.FLOAT:
				case Types.NUMERIC:
				case Types.REAL:
					formatPattern = DECIMAL_FORMAT;
					break;
				default:
					return;
			}
		}
		switch( type )
		{
			case Types.BIGINT:
			case Types.INTEGER:
			case Types.SMALLINT:
			case Types.TINYINT:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.NUMERIC:
			case Types.REAL:
				format = new DecimalFormat( formatPattern );
				break;
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				format = new SimpleDateFormat( formatPattern );
				break;
		}
	}
	
	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("ColumnDef : ");
		buf.append( "name = '"+getName()+"', ");
		buf.append( "type = '"+getType()+"', ");
		buf.append( "defaultValue = '"+getDefaultValue()+"', ");
		buf.append( "formatPattern = '"+getFormatPattern()+"', ");
		buf.append( "  properties = "+this.properties );
		return buf.toString();
	}
	
}
