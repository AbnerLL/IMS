package net.sourceforge.datagenerator;

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
import net.sourceforge.jdbcimporter.EntityDef;

/**
 * The GenerateDef class defines a set of entities to generate data for.
 * This definition includes:
 * 
 * <ul>
 * <li> the set of entity definitions</li>
 * <li> the log file to write log messages</li>
 * <li> the database connection definition</li>
 * </ul>
 * 
 * @version  0.6
 * @author   Chris Nagy
 */
public class GenerateDef 
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
	 * The list of entities to generate data.
	 */
	List entities = new ArrayList();
	
	/**
	 * The list of entities used as source data.
	 */
	List sourceEntities = new ArrayList();
	
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
	 * Adds an entity definition into the set that is generated.
	 * 
	 * @param newEntity entity definition to be added
	 */
	public void addEntity( GenerateEntityDef newEntity )
	{
		entities.add( newEntity );	
	}
	
	/**
	 * Adds an entity definition into the set that will be
	 *  used as source data.
	 *  
	 * @param newEntity entity definition
	 */
	public void addSourceEntity( EntityDef newEntity )
	{
		sourceEntities.add( newEntity );
	}
	
	/**
	 * Returns the set of entity definitions to generate.
	 * 
	 * @return set of entity definitions
	 */
	public GenerateEntityDef[] getEntities()
	{
		Object[] objs = entities.toArray();
		GenerateEntityDef[] defs= new GenerateEntityDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (GenerateEntityDef) objs[i];
		}
		return defs;	
	}

	/**
	 * Returns the set of entity definitions used as source data.
	 * 
	 * @return set of entity definitions
	 */
	public EntityDef[] getSourceEntities()
	{
		Object[] objs = sourceEntities.toArray();
		EntityDef[] defs= new EntityDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (EntityDef) objs[i];
		}
		return defs;	
	}
	
	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("GenerateDef : ");
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
