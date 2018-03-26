package net.sourceforge.datagenerator;

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

import java.io.File;

import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.EntityDef;

/**
 * The GenerateEntityDef defines an entity (table) that the DataGenerator
 * will generate values for.
 * 
 * @version 	0.6
 * @author Chris Nagy
 */
public class GenerateEntityDef extends EntityDef 
{
	/**
	 * The delimiter formatter.
	 */
	DelimiterFormatter formatter;
	
	/**
	 * The file that will contain the generated data.
	 */
	File            target;

	/**
	 * The target file's encoding.
	 */
	String          targetEncoding;
	
	/**
	 * The number of rows to generate.
	 */
	int             count = 1;
	
	/**
	 * Sets the target of the generated data.
	 * 
	 * @param newTarget the file containing the data generated.
	 */
	public void setTarget( File newTarget )
	{
		if ( !newTarget.exists() || newTarget.isFile() )
		{
			target = newTarget;	
		}
	}

	/**
	 * Returns the file containing the data generated.
	 * 
	 * @return target file
	 */
	public File getTarget()
	{
		return target;	
	}

	/**
	 * Set the target file's encoding.
	 * 
	 * @param newTargetEncoding target file's encoding charset
	 * @since 0.71
	 */
	public void setTargetEncoding( String newTargetEncoding )
	{
		this.targetEncoding = newTargetEncoding;
	}	
	
	/**
	 * Returns the target file's encoding (may be null).
	 * 
	 * @return target file's encoding charset
	 * @since 0.71
	 */
	public String getTargetEncoding()
	{
		return targetEncoding;
	}
	
	/**
	 * Sets the number of rows to generate.
	 * 
	 * @param newCount the number of rows
	 */
	public void setCount( int newCount )
	{
		if ( newCount > 0 )
		{
			count = newCount;
		}
	}
	
	/**
	 * Returns the number of rows to generate.
	 * 
	 * @return number of rows
	 */
	public int getCount()
	{
		return count;	
	}
	
	/**
	 * Sets the delimiter formatter used to format rows and columns into
	 * the output file.
	 * 
	 * @param newFormatter the delimiter formatter
	 */
	public void setDelimiterFormatter( DelimiterFormatter newFormatter )
	{
		formatter = newFormatter;	
	}
	
	/**
	 * Returns the delimiter formatter used to format the output file.
	 * 
	 * @return delimiter formatter
	 */
	public DelimiterFormatter getDelimiterFormatter()
	{
		return formatter;	
	}
	
	/**
	 * Returns the list of columns whose values should be generated.
	 * 
	 * @return list of column definitions
	 */
	public GenerateColumnDef[] getColumns()
	{
		Object[] objs = columns.toArray();
		GenerateColumnDef[] defs= new GenerateColumnDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (GenerateColumnDef) objs[i];
		}
		return defs;	
	}
	
	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("GenerateEntityDef : ");
		buf.append( "table = '"+getTable()+"',");
		buf.append( "schema = '"+getSchema()+"',");
		buf.append( "catalog = '"+getCatalog()+"',");
		buf.append( "encoding = '"+getTargetEncoding()+"',");
		buf.append( "target = '"+getTarget()+"'\n");
		buf.append( "delimiterFormatter = "+getDelimiterFormatter()+"\n" );
		buf.append( "columns = [\n");
		for ( int i = 0; i < this.columns.size(); i++ )
		{
			buf.append( this.columns.get(i) );
			if ( i < this.columns.size() - 1 )
			{
				buf.append(",");
			}
			buf.append( "\n" );
		}
		buf.append( "]");
		return buf.toString();
	}

}
