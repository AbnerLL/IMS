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

import java.util.EventObject;

import net.sourceforge.jdbcexporter.ExportDef;

/**
 * The ExportEvent describes the state of the export.
 * 
 * @version 	0.67
 * @author      Chris Nagy
 */
public class ExportEvent extends EventObject
{
	/**
	 * The configuration of the export.
	 */
	protected ExportDef exportDef;
		
	/**
	 * The classname of the ExportEngine in use.
	 */
	protected String    engineClassname;
	
	/**
	 * Constructs a new export event.
	 * 
	 * @param source the source of the event
	 * @param exportDef the configuration of the export
	 * @param engineClassname the classname of the ExportEngine in use
	 */
	public ExportEvent(Object source, ExportDef exportDef, String engineClassname )
	{
		super(source);
		this.exportDef = exportDef;
		this.engineClassname = engineClassname;
	}

	/**
	 * Returns the number of entities in this import.
	 * 
	 * @return number of entities
	 */
	public int getEntityCount()
	{
		return exportDef.getEntities().length;
	}
		
	/**
	 * Returns the classname of the ExportEngine being used.
	 * 
	 * @return the classname of the ExportEngine
	 */
	public String getEngineClassname()
	{
		return engineClassname;
	}
}
