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

import java.util.EventObject;

import net.sourceforge.datagenerator.GenerateDef;

/**
 * The DataGeneratorEvent describes the state of the data generation.
 * 
 * @version 0.69
 * @author Chris Nagy
 *
 */
public class DataGeneratorEvent extends EventObject
{
	/**
	 * The configuration of the data generation.
	 */
	protected GenerateDef generateDef;

	/**
	 * Creates a DataGeneratorEvent with the given configuration.
	 * 
	 * @param src the source
	 * @param def the configuration of the data generation
	 */
	public DataGeneratorEvent(Object src, GenerateDef def )
	{
		super(src);
		this.generateDef = def;
	}

	/**
	 * Returns the number of entities whose data will be generated.
	 * 
	 * @return the number of entities
	 */
	public int getEntityCount()
	{
		return this.generateDef.getEntities().length;
	}
	
	/**
	 * Returns the number of entities used for source data.
	 * 
	 * @return the number of entities
	 */
	public int getSourceEntityCount()
	{
		return this.generateDef.getSourceEntities().length;
	}
}
