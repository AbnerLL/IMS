package net.sourceforge.jdbcimporter.util;

import java.sql.BatchUpdateException;
import java.sql.SQLException;

/**
 * The MultipleBatchUpdateException is used to combine multiple SQLExceptions into
 * one exception.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class MultipleBatchUpdateException extends BatchUpdateException 
{
	/**
	 * The set of exception causes.
	 */
	SQLException[] nextExceptions;
	
	/**
	 * Creates the exception with the given row states.
	 * 
	 * @param rowStates the row states
	 */
	public MultipleBatchUpdateException(int[] rowStates) 
	{
		super(rowStates);
	}

	/**
	 * Sets the SQLExceptions that caused the batch update exception.
	 * 
	 * @param exceptions the SQLExceptions
	 */
	public void setNextExceptions( SQLException[] exceptions )
	{
		nextExceptions = exceptions;
	}
	
	/**
	 * Returns the SQLExceptions that occurred when the executeBatch
	 * method was called.
	 * 
	 * @return the SQLExceptions
	 */
	public SQLException[] getNextExceptions()
	{
		return nextExceptions;
	}
}
