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

import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jdbcimporter.util.MultipleBatchUpdateException;

/**
 * The LogImportListener is a import listener that writes to log and bad files during the import.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
public class LogImportListener implements ImportListener
{
	/**
	 * The start time of the import.
	 */
	long startImport = 0;
	
	/**
	 * The end time of the import.
	 */
	long startEntityImport = 0;
	
	/**
	 * The writer for information/error messages.
	 */
	PrintWriter logWriter = null;
	
	/**
	 * The writer for rows that weren't imported successfully.
	 */
	PrintWriter badWriter = null;

	/**
	 * The total number of rows processed.
	 */
	int totalRowCounter = 0;
	
	/**
	 * The total number of rows that weren't imported successfully.
	 */
	int badRowCounter = 0;

	/**
	 * Flag indicating that batch mode is being used.
	 */
	boolean batchMode = false;
	
	/**
	 * The number of rows to import before executeBatch is called.
	 */
	int     batchCount = 0;

	/**
	 * The current set of rows being batch imported.
	 */
	List    batchRows = new ArrayList();
	
	/**
	 * The index of the current set of rows and its absolute position 
	 * in the import.
	 */
	int[]   batchRowsIdx;
	
	/**
	 * The last index of the current set of rows being batch imported.
	 */
	int     batchRowCounter = 0;
			
	/**
	 * Create a LogImportListener with the given log and bad writer.
	 */
	public LogImportListener( Writer logWriter, Writer badWriter )
	{
		super();
		this.logWriter = new PrintWriter( logWriter );
		this.badWriter = new PrintWriter( badWriter );
	}

	/**
	 * @see net.sourceforge.jdbcimporter.event.ImportListener#importStarted(net.sourceforge.jdbcimporter.event.ImportEvent)
	 */
	public void importStarted(ImportEvent event)
	{
		batchMode = event.isBatchMode();
		batchCount = event.getBatchCount();
		if ( batchMode && batchCount > 1 )
		{
			batchRowsIdx = new int[ batchCount ];
		}
		writeToLog( "Import Engine: '"+event.getEngineClassname()+"'\n"+
		 "Started: "+new java.sql.Timestamp(System.currentTimeMillis()).toString()+"\n" +
		 "Batch  : "+(batchMode?"Y":"N")+" ("+batchCount+")" );
		startImport = System.currentTimeMillis();
		
	}

	/**
	 * @see net.sourceforge.jdbcimporter.event.ImportListener#entityImportStarted(net.sourceforge.jdbcimporter.event.ImportEntityEvent)
	 */
	public void entityImportStarted(ImportEntityEvent event)
	{
		totalRowCounter = 0;
		badRowCounter = 0;
		batchRowCounter = 0;
		writeToLog( "[" + event.getTable() + "] " );
		if ( event.getEngineClassname() != null )
		{
			writeToLog( "Import Engine: '"+event.getEngineClassname()+"'");
		}
		writeToBad( "[" + event.getTable() + "] " );
		startEntityImport = System.currentTimeMillis();
	}

	/**
	 * @see net.sourceforge.jdbcimporter.event.ImportListener#rowProcessed(net.sourceforge.jdbcimporter.event.ImportEntityRowEvent)
	 */
	public void rowProcessed(ImportEntityRowEvent event)
	{
		if ( event.getState() == ImportEntityRowEvent.STATE_ERROR )
		{
			writeToLog( "Row # "+(totalRowCounter+1) );
			if ( event.getException() != null )
			{
				writeToLog( event.getException() );
			}
			writeToBad( event.getRow() );
			badRowCounter++;								
		}
		else
		{
			if ( batchMode && batchCount > 1 )
			{
				batchRows.add( event.getRow() );
				batchRowsIdx[batchRowCounter] = totalRowCounter;
				batchRowCounter++;
			}
		}
		totalRowCounter++;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.event.ImportListener#batchProcessed(net.sourceforge.jdbcimporter.event.ImportEntityBatchEvent)
	 */
	public void batchProcessed(ImportEntityBatchEvent event)
	{
		int[] badRows = event.getBadRows();
		StringBuffer buf = new StringBuffer("Row(s) # ");
		for ( int i = 0 ; i < badRows.length; i++ )
		{
			int row = batchRowsIdx[badRows[i]];
			if ( i > 0 )
			{
				buf.append(",");
			}
			if ( i % 40 == 0 && i > 0 )
			{
				buf.append("\n");
			}
			buf.append( (row+1) );
			writeToBad( (String) batchRows.get( i ) );			
			badRowCounter++;								
		}
		if ( badRows.length > 0 )
		{
			writeToLog( buf.toString() );
		}
		if ( event.getException() != null )
		{
			writeToLog( event.getException() );
		}
		batchRowCounter = 0;
		batchRows.clear();
		for ( int i = 0; i < batchRowsIdx.length; i++ )
		{
			batchRowsIdx[i] = -1;
		}
	}

	/**
	 * @see net.sourceforge.jdbcimporter.event.ImportListener#entityImportFinished(net.sourceforge.jdbcimporter.event.ImportEntityEvent)
	 */
	public void entityImportFinished(ImportEntityEvent event)
	{
		if ( event.getException() != null )
		{
			writeToLog(event.getException());
		}
		writeToLog( "Processed "+(totalRowCounter-badRowCounter)+" of "+totalRowCounter+" row(s) [took "+(System.currentTimeMillis()-startEntityImport)+" ms]");
	}

	/**
	 * @see net.sourceforge.jdbcimporter.event.ImportListener#importFinished(net.sourceforge.jdbcimporter.event.ImportEvent)
	 */
	public void importFinished(ImportEvent event)
	{
		writeToLog( "Finished Import [took "+(System.currentTimeMillis()-startImport)+" ms]");
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
	
	/**
	 * Write the string to the bad rows log.
	 * 
	 * @param str the string
	 */
	protected void writeToBad( String str )
	{
		badWriter.println( str );
		badWriter.flush();
	}

}
