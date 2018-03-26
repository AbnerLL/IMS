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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Types;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The FileToStringColumnTranslator translates an InputStream into a filename by
 * writing the contents of the InputStream into a file. The format of the filename will be '[prefix][number][suffix]'
 * where 'prefix' and 'suffix' can be specified by the user and 'number' is a sequence number.
 * 
 * @version 	0.64
 * @author Chris Nagy
 */
public class FileToStringColumnTranslator implements ColumnTranslator
{
	/**
	 * The log for error information.
	 */
	protected static Log LOG = LogFactory.getLog( FileToStringColumnTranslator.class );
	
	/**
	 * The directory that the file wile reside in.
	 */
	protected String baseDir = null;
	
	/**
	 * The filename prefix (default 'file').
	 */
	protected String filenamePrefix = "file";
	
	/**
	 * The filename suffix.
	 */
	protected String filenameSuffix = null;
	
	/**
	 * The sequence number.
	 */
	protected int count = 0;
	
	/**
	 * Sets the base directory that the files will reside in.
	 * 
	 * @param newBaseDir the directory
	 */
	public void setBaseDir( String newBaseDir )
	{
		this.baseDir = newBaseDir;
	}

	/**
	 * Sets the filename prefix for all files created.
	 * 
	 * @param newFilenamePrefix the prefix
	 */
	public void setFilenamePrefix( String newFilenamePrefix )
	{
		this.filenamePrefix = newFilenamePrefix;
	}

	/**
	 * Sets the filename suffix for all files created.
	 * 
	 * @param newFilenameSuffix the suffix
	 */
	public void setFilenameSuffix( String newFilenameSuffix )
	{
		this.filenameSuffix = newFilenameSuffix;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ColumnTranslator#getValue(net.sourceforge.jdbcimporter.ColumnDef, net.sourceforge.jdbcimporter.ColumnValue)
	 */
	public ColumnValue getValue( ColumnDef column, ColumnValue columnValue )
	{
		if (column.getType() != Types.LONGVARBINARY &&
			column.getType() != Types.LONGVARCHAR )
		{
			return columnValue;
		}
		
		InputStream in = null;
		if ( columnValue.isString() && columnValue.getString() != null )
		{
			in = new ByteArrayInputStream( columnValue.getString().getBytes() );
		}
		else if ( columnValue.isBytes() && columnValue.getBytes() != null )
		{
			in = new ByteArrayInputStream( columnValue.getBytes() );
		}
		else if ( columnValue.isInputStream() )
		{
			in = columnValue.getInputStream();
		}
		
		if ( in == null )
		{
			return columnValue;
		}
		
		String filename = filenamePrefix + Integer.toString( count );
		if ( filenameSuffix != null )
		{
			filename = filenamePrefix + Integer.toString( count ) + filenameSuffix;
		}
		File file = new File( filename );
		if ( baseDir != null )
		{
			file = new File( new File( baseDir ), filename );
		}
		
		OutputStream out = null;
		try
		{
			out = new FileOutputStream( file );
			byte[] bytes = new byte[1024];
			int len = -1;
			while ( ( len = in.read( bytes )) > 0 )
			{
				out.write( bytes, 0, len );
			}
			ColumnValue val = new ColumnValue();
			val.setString( filename );
			count++;
			return val;
		}
		catch ( IOException e )
		{
			LOG.error("Error writing InputStream to file : "+file.getAbsolutePath(), e );
		}
		finally
		{
			if ( out != null )
			{
				try
				{
					out.close();
				}
				catch ( IOException e )
				{
					LOG.warn( "Could not close OutputStream of file : "+file.getAbsolutePath(), e );
				}
			}
		}
		return columnValue;
	}

}
