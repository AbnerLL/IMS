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
 
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The ImportDef class defines a set of entities to import into
 * a given database.  
 * This definition includes:
 * 
 * <ul>
 * <li> the set of entity definitions</li>
 * <li> the log file to write log messages</li>
 * <li> the log file to write bad data</li>
 * <li> the database connection definition</li>
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ImportDef
{
	/**
	 * The connection definition.
	 */
	ConnectionDef connectionDef;
	
	/**
	 * The log file.
	 */
	File logFile;
	
	/**
	 * The file that bad rows will be written to.
	 */
	File badFile;
	
	/**
	 * The list of entities that will be imported.
	 */
	List entities = new ArrayList();
	
	/**
	 * The number of rows to import before calling commit on the connection.
	 */
	int  commitCount = 1;
	
	/**
	 * The number of rows to import before calling executeBatch on the ImportEngine.
	 */
	int  batchCount  = 1;
	
	/**
	 * The file containing sql statements that are executed before the import.
	 */
	File preSQLFile;
	
	/**
	 * The file containing sql statements that are executed after the import.
	 */
	File postSQLFile;
	
	/**
	 * The flag indicating that values should be trimmed after the delimiter parser
	 * (default is false).
	 */
	boolean trimValues = false;
	
	/**
	 *
	 * @param newDef
	 */
	/**
	 * Sets the connection definition.
	 * 
	 * @param newDef definition of connection
	 */
	public void setConnectionDef( ConnectionDef newDef )
	{
		connectionDef = newDef;	
	}
	
	/**
	 * Returns to the connection definition.
	 * 
	 * @return definition of connection
	 */
	public ConnectionDef getConnectionDef()
	{
		return connectionDef;	
	}

	/**
	 * Sets the log file to write log messages.
	 * 
	 * @param newFile log file
	 */	
	public void setLogFile( File newFile )
	{
		if ( !newFile.exists() || newFile.isFile() )
		{
			logFile = newFile;	
		}	
	}
	
	/**
	 * Returns the log file to write log messages.
	 * 
	 * @return log file
	 */
	public File getLogFile()
	{
		return logFile;	
	}
	
	/**
	 * Sets the log file to write bad data.
	 * 
	 * @param newFile log file
	 */	
	public void setBadFile( File newFile )
	{
		if ( !newFile.exists() || newFile.isFile() )
		{
			badFile = newFile;	
		}	
	}
	
	/**
	 * Returns the log file to write bad data.
	 * 
	 * @return log file
	 */
	public File getBadFile()
	{
		return badFile;	
	}


	/**
	 * Sets the file containing the sql statements that will 
	 * be executed after the import. The sql statements inside
	 * the file should be separated by the semi-colon character.
	 * 
	 * @since 0.67
	 * @param newFile the file
	 */
	public void setPostSQLFile( File newFile )
	{
		if ( newFile.exists() && newFile.isFile() )
		{
			this.postSQLFile = newFile;
		}
	}
	
	/**
	 * Returns the file containing the sql statements that
	 * will be executed after the import.
	 * 
	 * @since 0.67
	 * @return the file
	 */
	public File getPostSQLFile( )
	{
		return postSQLFile;
	}
	
	/**
	 * Sets the file containing the sql statements that will
	 * be executed before the import. The sql statements inside
	 * the file should be separated by the semi-colon character.
	 * 
	 * @since 0.67
	 * @param newFile The preSQLFile to set.
	 */
	public void setPreSQLFile( File newFile )
	{
		if ( newFile.exists() && newFile.isFile() )
		{
			this.preSQLFile = newFile;
		}
	}

	/**
	 * Returns the file containing the sql statements that
	 * will be executed before the import.
	 * 
	 * @since 0.67
	 * @return the file
	 */
	public File getPreSQLFile( )
	{
		return preSQLFile;
	}
	
	
	/**
	 * Returns whether values should be trimmed after reading them from
	 * the delimiter parser.
	 * 
	 * @since 0.67
	 * @return flag
	 */
	public boolean isTrimValues( )
	{
		return trimValues;
	}
	
	/**
	 * Sets the flag indicating whether values should be trimmed after
	 * reading them from the delimiter parser.
	 * 
	 * @since 0.67
	 * @param trimValues flag.
	 */
	public void setTrimValues( boolean trimValues )
	{
		this.trimValues = trimValues;
	}
	
	/**
	 * Adds an entity definition into the set that is imported.
	 * 
	 * @param newEntity entity definition to be added
	 */
	public void addEntity( ImportEntityDef newEntity )
	{
		entities.add( newEntity );	
	}
	
	/**
	 * Returns the set of entity definitions to import.
	 * 
	 * @return set of entity definitions
	 */
	public ImportEntityDef[] getEntities()
	{
		Object[] objs = entities.toArray();
		ImportEntityDef[] defs= new ImportEntityDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (ImportEntityDef) objs[i];
		}
		return defs;	
	}
	
	/**
	 * Sets the number of rows to import before committing. 
	 * The default is one.
	 * 
	 * @param count the number of rows
	 */
	public void setCommitCount( int count )
	{
		this.commitCount = count;
	}
	
	/**
	 * Returns the number of rows to import before committing.
	 * 
	 * @return the number of rows
	 */
	public int getCommitCount()
	{
		return this.commitCount;
	}
	
	/**
	 * Sets the number of rows to import before calling executeBatch. 
	 * The default is one.
	 * 
	 * @param count the number of rows
	 */
	public void setBatchCount( int count )
	{
		this.batchCount = count;
	}
	
	/**
	 * Returns the number of rows to import before calling executeBatch.
	 * 
	 * @return the number of rows
	 */
	public int getBatchCount()
	{
		return this.batchCount;
	}
	
	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("ImportDef : ");
		buf.append( "logFile = '"+getLogFile()+"',");
		buf.append( "badFile = '"+getBadFile()+"',");
		buf.append( "commitCount = '"+getCommitCount()+"\n");
		buf.append( "commitCount = '"+getCommitCount()+"\n");
		buf.append( "connectionDef = "+getConnectionDef()+"\n");
		buf.append( "entities = [\n");
		for ( int i = 0; i < this.entities.size(); i++ )
		{
			buf.append( this.entities.get(i) );
			if ( i < this.entities.size() - 1 )
			{
				buf.append(",");
			}
			buf.append( "\n" );
		}
		buf.append( "]");
		return buf.toString();
	}
}
