package net.sourceforge.jdbcexporter.util;

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
 
import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;
/**
 */
public class StringToArrayEditor extends PropertyEditorSupport {

	public void setAsText( String val ) throws IllegalArgumentException
	{
		StringTokenizer tokenizer = new StringTokenizer(val, ",");
		String[] values = new String[tokenizer.countTokens()];
		int index = 0;
		while (tokenizer.hasMoreTokens()) {
			String nextValueStr = tokenizer.nextToken();
			values[index] = nextValueStr;
			index++;
		}
		setValue(values); 
	}
}
