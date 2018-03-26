package net.sourceforge.datagenerator.event;

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
 * The DataGeneratorListener defines methods that are called to indicate the status 
 * of the data generation.
 *
 * @version 0.69
 * @author Chris Nagy
 */
public interface DataGeneratorListener extends EventListener
{
	/**
	 * Indicates that the data generation started.
	 * 
	 * @param e general data generation event
	 */
	public void dataGeneratorStarted( DataGeneratorEvent e );
	
	/**
	 * Indicates that the data generation finished.
	 * 
	 * @param e general data generation event
	 */
	public void dataGeneratorFinished( DataGeneratorEvent e );

	/**
	 * Indicates that a row has been generated.
	 * 
	 * @param e data row generation event
	 */
	public void rowGenerated( DataGeneratorRowEvent e );
	
	/**
	 * Indicates that the data generation for an entity has finished.
	 * 
	 * @param e entity data generation event
	 */
	public void entityDataGeneratorFinished( DataGeneratorEntityEvent e );
}
