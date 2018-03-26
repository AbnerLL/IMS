package net.sourceforge.jdbcexporter.event;

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

import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;

import net.sourceforge.jdbcimporter.util.MultipleBatchUpdateException;

/**
 * The LogExportListener is a export listener that writes to log file during the export.
 * 
 * @version 	0.67
 * @author      Chris Nagy
 */
public class LogExportListener implements ExportListener
{
	/**
	 * The start time of the export.
	 */
	long startExport = 0;
	
	/**
	 * The end time of the export.
	 */
	long startEntityExport = 0;
	
	/**
	 * The writer for information/error messages.
	 */
	PrintWriter logWriter = null;
	
	/**
	 * The total number of rows processed.
	 */
	int totalRowCounter = 0;
	
	/**
	 * The total number of rows that weren't exported successfully.
	 */
	int badRowCounter = 0;
			
	/**
	 * Create a LogExportListener with the given log writer.
	 */
	public LogExportListener( Writer logWriter )
	{
		super();
		this.logWriter = new PrintWriter( logWriter );
	}

	/**
	 * @see net.sourceforge.jdbcexporter.event.ExportListener#exportStarted(net.sourceforge.jdbcexporter.event.ExportEvent)
	 */
	public void exportStarted( ExportEvent event )
	{
		writeToLog( "Export Engine: '"+event.getEngineClassname()+"'\n"+
				 "Started: "+new java.sql.Timestamp(System.currentTimeMillis()).toString() );
		startExport = System.currentTimeMillis();
	}

	/**
	 * @see net.sourceforge.jdbcexporter.event.ExportListener#entityExportStarted(net.sourceforge.jdbcexporter.event.ExportEntityEvent)
	 */
	public void entityExportStarted( ExportEntityEvent event )
	{
		totalRowCounter = 0;
		badRowCounter = 0;
		writeToLog( "[" + event.getTable() + "] " );
		if ( event.getEngineClassname() != null )
		{
			writeToLog( "Export Engine: '"+event.getEngineClassname()+"'");
		}
		startEntityExport = System.currentTimeMillis();		
	}

	/**
	 * @see net.sourceforge.jdbcexporter.event.ExportListener#rowProcessed(net.sourceforge.jdbcexporter.event.ExportEntityRowEvent)
	 */
	public void rowProcessed( ExportEntityRowEvent event )
	{
		if ( event.getState() == ExportEntityRowEvent.STATE_ERROR )
		{
			writeToLog( "Row # "+(totalRowCounter+1) );
			if ( event.getException() != null )
			{
				writeToLog( event.getException() );
			}
			writeToLog( event.getRow() );
			badRowCounter++;								
		}
		totalRowCounter++;		
	}
	
	/**
	 * @see net.sourceforge.jdbcexporter.event.ExportListener#entityExportFinished(net.sourceforge.jdbcexporter.event.ExportEntityEvent)
	 */
	public void entityExportFinished( ExportEntityEvent event )
	{
		if ( event.getException() != null )
		{
			writeToLog(event.getException());
		}
		writeToLog( "Processed "+(totalRowCounter-badRowCounter)+" of "+totalRowCounter+" row(s) [took "+(System.currentTimeMillis()-startEntityExport)+" ms]");
	}

	/**
	 * @see net.sourceforge.jdbcexporter.event.ExportListener#exportFinished(net.sourceforge.jdbcexporter.event.ExportEvent)
	 */
	public void exportFinished( ExportEvent event )
	{
		writeToLog( "Finished Export [took "+(System.currentTimeMillis()-startExport)+" ms]");
	}
	
	/**
	 * Write the given string to the log.
	 * 
	 * @param str the string
	 */
	protected void writeToLog( String str )
	{
		logWriter.println( str );
		logWriter.flush();
	}

	/**
	 * Write the given exception to the log.
	 * 
	 * @param ex the exception
	 */
	protected void writeToLog( Exception ex )
	{
		if ( ex instanceof MultipleBatchUpdateException )
		{
			MultipleBatchUpdateException mEx = (MultipleBatchUpdateException) ex;
			mEx.printStackTrace( logWriter );
			logWriter.flush();
			SQLException[] nextExs = mEx.getNextExceptions();
			for ( int i = 0; i < nextExs.length; i++ )
			{
				SQLException nextEx = nextExs[i];
				while ( nextEx != null )
				{
					logWriter.println("SQLException : Error Code = "+nextEx.getErrorCode() + ", SQL State = "+nextEx.getSQLState() + ", Message = "+nextEx.getMessage() );
					nextEx.printStackTrace( logWriter );
					logWriter.flush();
					nextEx = nextEx.getNextException();
				}
				
			}
		}
		if ( ex instanceof SQLException )
		{
			SQLException nextEx = (SQLException) ex;
			while ( nextEx != null )
			{
				logWriter.println("SQLException : Error Code = "+nextEx.getErrorCode() + ", SQL State = "+nextEx.getSQLState() + ", Message = "+nextEx.getMessage() );
				nextEx.printStackTrace( logWriter );
				logWriter.flush();
				nextEx = nextEx.getNextException();
			}
		}
		else
		{
			ex.printStackTrace( logWriter );
			logWriter.flush();
		}
	}
}
