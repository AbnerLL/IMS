package net.sourceforge.datagenerator.event;

import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;

import net.sourceforge.jdbcimporter.util.MultipleBatchUpdateException;

/**
 * The LogDataGeneratorListener is a data generator listener that writes to log file during the
 * data generation.
 * 
 * @version 	0.69
 * @author      Chris Nagy
 */
public class LogDataGeneratorListener implements DataGeneratorListener
{
	/**
	 * The start time of the data generation.
	 */
	long startDataGenerate = 0;
		
	/**
	 * The writer for information/error messages.
	 */
	PrintWriter logWriter = null;
		
	/**
	 * Creates a LogDataGeneratorListener that outputs to the given writer.
	 * 
	 * @param logWriter the writer
	 */
	public LogDataGeneratorListener( Writer logWriter )
	{
		super();
		this.logWriter = new PrintWriter( logWriter );
	}

	/**
	 * @see net.sourceforge.datagenerator.event.DataGeneratorListener#dataGeneratorStarted(net.sourceforge.datagenerator.event.DataGeneratorEvent)
	 */
	public void dataGeneratorStarted(DataGeneratorEvent event)
	{
		writeToLog( "Started: "+new java.sql.Timestamp(System.currentTimeMillis()).toString() );
		startDataGenerate = System.currentTimeMillis();
	}

	/**
	 * @see net.sourceforge.datagenerator.event.DataGeneratorListener#dataGeneratorFinished(net.sourceforge.datagenerator.event.DataGeneratorEvent)
	 */
	public void dataGeneratorFinished(DataGeneratorEvent event)
	{
		writeToLog( "Finished Data Generation [took "+(System.currentTimeMillis()-startDataGenerate)+" ms]");
	}

	/**
	 * @see net.sourceforge.datagenerator.event.DataGeneratorListener#rowGenerated(net.sourceforge.datagenerator.event.DataGeneratorRowEvent)
	 */
	public void rowGenerated(DataGeneratorRowEvent event)
	{
	}

	/**
	 * @see net.sourceforge.datagenerator.event.DataGeneratorListener#entityDataGeneratorFinished(net.sourceforge.datagenerator.event.DataGeneratorEntityEvent)
	 */
	public void entityDataGeneratorFinished(DataGeneratorEntityEvent e)
	{
		writeToLog("["+e.getTable()+"]\n");
		if ( e.getException() != null )
		{
			writeToLog("Error Generating Data");
			writeToLog( e.getException() );
		}
		else
		{
			writeToLog("Generated "+e.getCount()+" row(s)\n");
		}
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
