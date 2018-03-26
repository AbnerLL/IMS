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

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;

/**
 * The ExportColumnDef class defines a column in an entity that will
 * used in the exporting of data. 
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class ExportColumnDef extends ColumnDef 
{
	/**
	 * The column translator.
	 */
	ColumnTranslator translator;
	
	/**
	 * Sets the column translator used on every row's column value
	 * during the export.
	 * 
	 * @param newTranslator column translator
	 */
	public void setTranslator( ColumnTranslator newTranslator )
	{
		translator= newTranslator;
	}
	
	/**
	 * Returns the column translator used on every row's column value
	 * during the export.
	 * 
	 * @return column translator
	 */
	public ColumnTranslator getTranslator()
	{
		return translator;	
	}

	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("ImportColumnDef : ");
		buf.append( "name = '"+getName()+"', ");
		buf.append( "type = '"+getType()+"', ");
		buf.append( "defaultValue = '"+getDefaultValue()+"', ");
		buf.append( "formatPattern = '"+getFormatPattern()+"', ");
		buf.append( "translator = "+getTranslator()+"\n");
		return buf.toString();
	}
	
}
