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

import java.util.EventListener;

/**
 * The ImportListener defines methods that are called to indicate the status 
 * of the import.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
public interface ImportListener extends EventListener
{
	/**
	 * Indicates that the import started.
	 * 
	 * @param event general import event
	 */
	public void importStarted( ImportEvent event );
	
	/**
	 * Indicates that the import of an entity started.
	 * 
	 * @param event general import entity event
	 */
	public void entityImportStarted( ImportEntityEvent event );

	/**
	 * Indicates that a row has been processed.
	 * 
	 * @param event import entity row event
	 */
	public void rowProcessed( ImportEntityRowEvent event );

	/**
	 * Indicates that a batch of rows have been processed.
	 * 
	 * @param event import entity batch event
	 */
	public void batchProcessed( ImportEntityBatchEvent event );
	
	/**
	 * Indicates that the import of an entity finished.
	 * 
	 * @param event general import entity event
	 */
	public void entityImportFinished( ImportEntityEvent event );
	
	/**
	 * Indicates that the import finished.
	 * 
	 * @param event general import event
	 */
	public void importFinished( ImportEvent event );
}
