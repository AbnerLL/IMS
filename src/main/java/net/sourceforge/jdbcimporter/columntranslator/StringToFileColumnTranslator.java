package net.sourceforge.jdbcimporter.columntranslator;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Types;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The StringToFileColumnTranslator translates a String into an InputStream by reading in a file whose name
 * matches the string. 
 * 
 * @version 	0.64
 * @author Chris Nagy
 */
public class StringToFileColumnTranslator implements ColumnTranslator
{
	/**
	 * The log for error information.
	 */
	protected static Log LOG = LogFactory.getLog( StringToFileColumnTranslator.class );

	/**
	 * The base directory from which the files will be read.
	 */
	protected String baseDir = null;
	
	/**
	 * Sets the base directory from which the files will be read.
	 * 
	 * @param newBaseDir the base directory
	 */
	public void setBaseDir( String newBaseDir )
	{
		this.baseDir = newBaseDir;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ColumnTranslator#getValue(net.sourceforge.jdbcimporter.ColumnDef, net.sourceforge.jdbcimporter.ColumnValue)
	 */
	public ColumnValue getValue( ColumnDef column, ColumnValue columnValue )
	{
		if ( columnValue.getString() != null &&
			 (column.getType() == Types.LONGVARBINARY ||
			  column.getType() == Types.LONGVARCHAR ) )
		{
			File file = new File( columnValue.getString() );
			if ( baseDir != null )
			{
				file = new File ( new File( baseDir ), columnValue.getString() );
			}
			
			if ( !file.exists() || !file.isFile() )
			{
				return columnValue;
			}
			
			RandomAccessFile rFile = null;
			long lengthLong = -1;
			try
			{
				rFile = new RandomAccessFile( file, "r" );
				lengthLong = rFile.length();
			}
			catch ( IOException ioe )
			{
				return columnValue;
			}
			finally
			{	
				if ( rFile != null )
				{
					try
					{
						rFile.close();
					}
					catch ( IOException e )
					{
						
					}
				}
			}
			
			int length = Integer.MAX_VALUE;
			if ( lengthLong > Integer.MAX_VALUE ) // Can't handle it
			{
				LOG.error("Could not convert filename into InputStream :"+
						"File too big ("+Long.toString(lengthLong)+">"+
						Integer.toString(Integer.MAX_VALUE)+")");
				return columnValue;
			}
			else
			{
				length = (int) lengthLong;
			}

			try
			{
				ColumnValue newColumnValue = new ColumnValue();
				newColumnValue.setInputStream( new FileInputStream( file ), length );
				return newColumnValue;
			}
			catch ( IOException ioe )
			{
				LOG.error( "Could not open file : "+file.getAbsolutePath(), ioe );
			}
		}
		return columnValue;
	}

}
