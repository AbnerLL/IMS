package net.sourceforge.datagenerator.generator;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The ChoiceValueGenerator class generates random values by selecting
 * a value from a list of choices. The list of choices is set by calling
 * the 'setChoices' method with a comma-separated list of choices.
 * No dependencies can be defined when using this column value generator.
 * 
 * @version  0.6
 * @author     Chris Nagy
 */
public class ChoiceValueGenerator extends ColumnValueGeneratorBase 
{
	/**
	 * The list of choices.
	 */
	List choices = new ArrayList();
	
	/**
	 * Set the list of choices.
	 * 
	 * @param args comma-separated list of choices
	 */
	public void setChoices( String args )
	{
		choices.clear();
		StringTokenizer tokenizer = new StringTokenizer( args, "," );
		while ( tokenizer.hasMoreTokens() )
		{
			choices.add( tokenizer.nextToken() );
		}
	}
	
	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[0];
	}
	
	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue( int row, Random r )
	{		
		String result = null;
		int nextIndex = Math.abs( r.nextInt() % choices.size() );
		result = (String) choices.get(nextIndex);
		ColumnValue val = new ColumnValue();
		val.setString( result );
		return val;
	}
}
