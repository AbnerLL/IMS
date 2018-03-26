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

import java.util.EventObject;

import net.sourceforge.jdbcimporter.ImportDef;

/**
 * The ImportEvent describes the state of the import.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
public class ImportEvent extends EventObject
{
	/**
	 * The configuration of the import.
	 */
	protected ImportDef importDef;
	
	/**
	 * The flag indicating that batch mode is being used.
	 */
	protected boolean   batchMode;
	
	/**
	 * The classname of the ImportEngine in use.
	 */
	protected String    engineClassname;
	
	/**
	 * Constructs a new import event.
	 * 
	 * @param source the source of the event
	 * @param importDef the configuration of the import
	 * @param engineClassname the classname of the ImportEngine in use
	 * @param batchMode if the import is using batch mode
	 */
	public ImportEvent(Object source, ImportDef importDef, String engineClassname, boolean batchMode )
	{
		super(source);
		this.importDef = importDef;
		this.batchMode = batchMode;
		this.engineClassname = engineClassname;
	}

	/**
	 * Returns the number of entities in this import.
	 * 
	 * @return number of entities
	 */
	public int getEntityCount()
	{
		return importDef.getEntities().length;
	}
	
	/**
	 * Returns the batch count used in this import.
	 * 
	 * @return number of rows imported before an execute batch is called
	 */
	public int getBatchCount()
	{
		return importDef.getBatchCount();
	}
	
	/**
	 * Returns the commit count used in this import.
	 * 
	 * @return number of rows imported before a commit is called
	 */
	public int getCommitCount()
	{
		return importDef.getCommitCount();
	}
	
	/**
	 * Returns if the import is using batch mode.
	 * 
	 * @return a flag indicating that the import is using batch mode
	 */
	public boolean isBatchMode()
	{
		return batchMode;
	}
	
	/**
	 * Returns the classname of the ImportEngine being used.
	 * 
	 * @return the classname of the ImportEngine
	 */
	public String getEngineClassname()
	{
		return engineClassname;
	}
}
