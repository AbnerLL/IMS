package net.sourceforge.jdbcimporter;

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
 
/**
 * The RowTranslator interface defines the method used to translate
 * a row's values from a file into a row's values to be imported
 * into the database.
 * 
 * @version 0.72
 * @author cnagy
 */
public interface RowTranslator 
{
	/**
	 * Returns the translated row's values. If null is returned then the JDBCImporter will skip the row.
	 *  
	 * @param entityDef the definition of the entity
	 * @param rowvalues the row's values read from a file
	 * @return the new row's values or null
	 */
	public ColumnValue[] getValues( EntityDef entityDef, ColumnValue[] rowvalues );
}
