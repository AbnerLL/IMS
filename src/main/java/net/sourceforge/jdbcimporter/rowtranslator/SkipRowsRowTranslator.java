package net.sourceforge.jdbcimporter.rowtranslator;


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
 
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.RowTranslator;

/**
 * The SkipRowsRowTranslator translates a row's values so that the first n rows are skipped.
 * 
 * @version 0.72
 * @author cnagy
 *
 */
public class SkipRowsRowTranslator implements RowTranslator 
{
	
	/**
	 * The number of rows to skip.
	 */
	protected int numRows = 0;
	
	/**
	 * The current row.
	 */
	private int curRow  = 0;
	
	/**
	 * Sets the number of rows to skip.
	 * 
	 * @param rows number of rows to skip
	 */
	public void setNumRows( int rows )
	{
		this.numRows = rows;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.RowTranslator#getValues(net.sourceforge.jdbcimporter.EntityDef, net.sourceforge.jdbcimporter.ColumnValue[])
	 */
	public ColumnValue[] getValues(EntityDef entityDef, ColumnValue[] rowvalues) 
	{
		if ( curRow < numRows )
		{
			curRow++;
			return null;
		}
		else
		{
			curRow++;
			return rowvalues;
		}
	}

}
