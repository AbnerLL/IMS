package net.sourceforge.jdbcexporter.ant;

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

import java.util.Map;

import net.sourceforge.jdbcexporter.ExportColumnDef;
import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ant.ColumnDefElement;
import net.sourceforge.jdbcimporter.ant.ColumnTranslatorElement;

/**
 * The ExportColumnDefElement provides an Ant wrapper for setting a
 * ExportColumnDef object from Ant.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class ExportColumnDefElement extends ColumnDefElement 
{
	/**
	 * The mapping between column translator types and classes.
	 */
	Map plugins;
	
	/**
	 * The element that will create the column translator.
	 */
	ColumnTranslatorElement translatorElement = null;
	
	/**
	 * Creates a new ExportColumnDefElement.
	 * 
	 * @param plugins the plugin mappings
	 */
	public ExportColumnDefElement( Map plugins )
	{
		this.plugins = plugins;
		this.columnDef = new ExportColumnDef();
	}

	/**
	 * Sets the column translator used on every row's column value
	 * during the import.
	 * 
	 * @param className column translator
	 */
	public void setTranslator( String className )
	{
		translatorElement = new ColumnTranslatorElement( plugins );
		translatorElement.setClassname( className );
	}

	/**
	 * Returns a new column value generator element.
	 * 
	 * @return new column value generator
	 */
	public ColumnTranslatorElement createTranslator()
	{
		translatorElement = new ColumnTranslatorElement( plugins );
		return translatorElement;
	}

	/**
	 * Returns the column definition.
	 * 
	 * @return the column definition
	 */
	public ColumnDef getColumnDef()
	{
		if ( translatorElement != null )
		{
			((ExportColumnDef) this.columnDef).setTranslator( translatorElement.getTranslator() );
		}
		return this.columnDef;
	}
		
}
