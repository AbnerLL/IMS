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

import java.io.File;

import net.sourceforge.jdbcimporter.EntityDef;

/**
 * The ExportEntityDef class defines an entity (table) to be exported.
 * 
 * This definition includes:
 * 
 * <ul>
 * <li> the name of table (along with the catalog and schema it belongs to)</li>
 * <li> the delimiter formatter to use when writing the data to the file</li>
 * <li> the file that will contain the exported data</li>
 * <li> the columns in the table that will be exported</li>
 * <li> the where SQL clause that restricts the data exported (optional)</li>
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ExportEntityDef extends EntityDef 
{
	/**
	 * The delimiter formatter.
	 */
	DelimiterFormatter formatter;
	
	/**
	 * The file that will contain the exported rows.
	 */
	File               target;
	
	/**
	 * The target file's encoding.
	 */
	String             targetEncoding;

	/**
	 * The export engine to use.
	 */
	ExportEngine    engine;
	
	/**
	 * Returns the delimiter formatter used by the engine.
	 *
	 * @return delimiter formatter
	 */
	public DelimiterFormatter getDelimiterFormatter() 
	{
		return formatter;
	}

	/**
	 * Returns the file to write the export data to.
	 * 
	 * @return target file
	 */
	public File getTarget() 
	{
		return target;
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
	 * Set the delimiter formatter.
	 * 
	 * @param formatter delimiter formatter
	 */
	public void setDelimiterFormatter(DelimiterFormatter formatter) 
	{
		this.formatter = formatter;
	}

	/**
	 * Set the target file.
	 * 
	 * @param file target file
	 */
	public void setTarget(File file) 
	{
		target = file;
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
	 * Sets the export engine to use when exporting data for 
	 * this entity.
	 * 
	 * @since 0.67
	 * @param engine the export engine
	 */
	public void setExportEngine( ExportEngine engine )
	{
		this.engine = engine;
	}
	
	/**
	 * Returns the export engine to use when exporting
	 * data for this entity.
	 * 
	 * @since 0.67
	 * @return the export engine
	 */
	public ExportEngine getExportEngine()
	{
		return this.engine;
	}
	
	/**
	 * Returns the list of columns that will be exported.
	 * 
	 * @return list of column definitions
	 */
	public ExportColumnDef[] getColumns()
	{
		Object[] objs = columns.toArray();
		ExportColumnDef[] defs= new ExportColumnDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (ExportColumnDef) objs[i];
		}
		return defs;	
	}

	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("ExportEntityDef : ");
		buf.append( "table = '"+getTable()+"',");
		buf.append( "schema = '"+getSchema()+"',");
		buf.append( "catalog = '"+getCatalog()+"',");
		buf.append( "whereClause = '"+getWhereClause()+"',");
		buf.append( "queryStr = '"+getQueryStr()+"',");
		buf.append( "encoding = '"+getTargetEncoding()+"',");
		buf.append( "target = '"+getTarget()+"'\n");
		buf.append( "delimiterFormatter = "+getDelimiterFormatter()+"\n" );
		if ( engine != null )
		{
			buf.append( "exportEngine = ["+engine.getClass().getName()+"]\n");
		}
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
