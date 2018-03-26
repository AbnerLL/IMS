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

import java.util.EventListener;

/**
 * The ExportListener defines methods that are called to indicate the status 
 * of the export.
 * 
 * @version 	0.67
 * @author      Chris Nagy
 */
public interface ExportListener extends EventListener
{
	/**
	 * Indicates that the export started.
	 * 
	 * @param event general export event
	 */
	public void exportStarted( ExportEvent event );
	
	/**
	 * Indicates that the export of an entity started.
	 * 
	 * @param event general export entity event
	 */
	public void entityExportStarted( ExportEntityEvent event );

	/**
	 * Indicates that a row has been processed.
	 * 
	 * @param event export entity row event
	 */
	public void rowProcessed( ExportEntityRowEvent event );
	
	/**
	 * Indicates that the export of an entity finished.
	 * 
	 * @param event general Export entity event
	 */
	public void entityExportFinished( ExportEntityEvent event );
	
	/**
	 * Indicates that the export finished.
	 * 
	 * @param event general export event
	 */
	public void exportFinished( ExportEvent event );

}
