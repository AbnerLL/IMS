package net.sourceforge.jdbcimporter.columntranslator;

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
 
import java.sql.Types;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnTranslator;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcutils.utils.DateUtil;
/**
 * 时间格式转换
 */
public class DateTimeColumnTranslator implements ColumnTranslator 
{
	public static final String SYSTEM_TIME = "SYSTEMTIME";
	
	/**
	 * @see net.sourceforge.jdbcimporter.ColumnTranslator#getValue(ColumnDef, ColumnValue)
	 */
	public ColumnValue getValue(ColumnDef column, ColumnValue columnValue) 
	{
		switch( column.getType() )
		{
			case Types.DATE:
				columnValue.setString(DateUtil.getString(DateUtil.getDate(columnValue.getString()),ColumnDef.DATE_FORMAT));break;
			case Types.TIME:
				columnValue.setString(DateUtil.getString(DateUtil.getDate(columnValue.getString()),ColumnDef.TIME_FORMAT));break;
			case Types.TIMESTAMP:
				columnValue.setString(DateUtil.getString(DateUtil.getDate(columnValue.getString()),ColumnDef.TIMESTAMP_FORMAT));break;
		}
		return columnValue;
	}
}
