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
 * The ColumnTranslator interface defines the method used to 
 * translate a column value read from a file into a column value
 * to be imported into the database.
 * 
 * Some examples:
 * 
 * <ul>
 * <li> Encryption : The translator encrypts a string. This can be used
 * when inserting passwords into the database that should have one-way
 * encryption
 * <li> File Reader : The translator identifies the string as a filename and
 * reads the file in as the new column value.
 * </ul>
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public interface ColumnTranslator
{
	/**
	 * Returns the translated column value.
	 * 
	 * @param column the definition of the column
	 * @param columnValue the value of the column read from a file
	 * @return the new value of the column
	 */
	public ColumnValue getValue( ColumnDef column, ColumnValue columnValue );
}
