package net.sourceforge.jdbcexporter;

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

import net.sourceforge.jdbcimporter.ConnectionDef;

/**
 * The ExportDef class defines a set of entities to export from
 * a given database.  
 * This definition includes:
 * 
 * <ul>
 * <li> the set of entity definitions</li>
 * <li> the log file to write log messages</li>
 * <li> the database connection definition</li>
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ExportDef
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
	 * The list of entity definitions.
	 */
	List entities = new ArrayList();
	
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
	 * Adds an entity definition into the set that is exported.
	 * 
	 * @param newEntity entity definition to be added
	 */
	public void addEntity( ExportEntityDef newEntity )
	{
		entities.add( newEntity );	
	}
	
	/**
	 * Returns the set of entity definitions to export.
	 * 
	 * @return set of entity definitions
	 */
	public ExportEntityDef[] getEntities()
	{
		Object[] objs = entities.toArray();
		ExportEntityDef[] defs= new ExportEntityDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (ExportEntityDef) objs[i];
		}
		return defs;	
	}

	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("ExportDef : ");
		buf.append( "logFile = '"+getLogFile()+"',");
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
