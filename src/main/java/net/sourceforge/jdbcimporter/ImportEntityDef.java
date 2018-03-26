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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The ImportEntityDef class defines an entity (table) to be imported.
 * 
 * This definition includes:
 * 
 * <ul>
 * <li> the name of table (along with the catalog and schema it belongs to)</li>
 * <li> the delimiter parser to use when importing data</li>
 * <li> the file containing the data to import</li>
 * <li> the columns in the table that are defined in the import data</li>
 * </ul>
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class ImportEntityDef extends EntityDef 
{
	/**
	 * The delimiter parser used to parse the source file.
	 */
	DelimiterParser parser;
	
	/**
	 * The binary delimiter parser used to parse the source file.
	 * @since 0.74
	 */
	BinaryDelimiterParser binaryParser;
	
	/**
	 * The source file.
	 */
	File            source;

	/**
	 * The source file's encoding charset.
	 */
	String          sourceEncoding;
	
	/**
	 * The import engine to use.
	 */
	ImportEngine    engine;
	
	/**
	 * The import columns.
	 */
	List            importColumns = new ArrayList();

	/**
	 * Flag indicating that some column's values need to be generated.
	 */
	boolean         hasColumnsToGenerate = false;
	
	/**
	 * The number of characters to skip before reading data.
	 */
	int            numBytesToSkip = 0;

	/**
	 * The row translator used to translate rows.
	 */
	RowTranslator  rowTranslator;
	
	/**
	 * Adds a column definition.
	 * 
	 * @param columnDef the definition of the column
	 */
	public void addColumn(ColumnDef columnDef)
	{
		super.addColumn(columnDef);
		if ( columnDef instanceof ImportColumnDef )
		{
			importColumns.add( columnDef );
		}
		else
		{
			// Create an import column def from the column def
			hasColumnsToGenerate = true;
			ImportColumnDef importColumnDef = new ImportColumnDef();
			importColumnDef.setDefaultValue( columnDef.getDefaultValue() );
			importColumnDef.setFormatPattern( columnDef.getFormatPattern() );
			importColumnDef.setName( columnDef.getName() );
			importColumnDef.setType( columnDef.getType() );
			importColumns.add( importColumnDef );
		}
	}

	/**
	 * Sets the source of the import data.
	 * 
	 * @param newSource the file containing the data to import.
	 */
	public void setSource( File newSource )
	{
		if ( newSource.exists() && newSource.isFile() )
		{
			source = newSource;	
		}
	}

	/**
	 * Returns the file containing the data to import.
	 * 
	 * @return import file
	 */
	public File getSource()
	{
		return source;	
	}

	/**
	 * Sets the source file's encoding of the import data.
	 * 
	 * @param newSourceEncoding the encoding of the file.
	 * @since 0.71
	 */
	public void setSourceEncoding( String newSourceEncoding )
	{
		this.sourceEncoding = newSourceEncoding;
	}
	
	/**
	 * Returns the file's encoding of the import data (may be null).
	 * 
	 * @return encoding
	 * @since 0.71
	 */
	public String getSourceEncoding()
	{
		return sourceEncoding; 
	}
	
	/**
	 * Sets the delimiter parser used to parse the input file into rows and 
	 * columns to be imported into the database.
	 * 
	 * @param newParser the delimiter parser
	 */
	public void setDelimiterParser( DelimiterParser newParser )
	{
		parser = newParser;	
	}
	
	/**
	 * Returns the delimiter parser used to parse the input file
	 * 
	 * @return delimiter parser
	 */
	public DelimiterParser getDelimiterParser()
	{
		return parser;	
	}
	
	/**
	 * Sets the binary delimiter parser used to parse the input file into
	 * rows and columns to be imported into the database.
	 * 
	 * @param newBinaryParser the binary delimiter parser
	 * @since 0.74
	 */
	public void setBinaryDelimiterParser( BinaryDelimiterParser newBinaryParser )
	{
		this.binaryParser = newBinaryParser;
	}
	
	/**
	 * Returns the binary delimiter parser used to parse the input file.
	 * 
	 * @return binary delimiter parser
	 * @since 0.74
	 */
	public BinaryDelimiterParser getBinaryDelimiterParser()
	{
		return this.binaryParser;
	}
	
	/**
	 * Sets the number of bytes to skip before passing the reader to the delimiter parser.
	 * 
	 * @param numBytes The number of bytes
	 * @since 0.72
	 */
	public void setNumBytesToSkip(int numBytes)
	{
		this.numBytesToSkip = numBytes;
	}
	
	/**
	 * Returns the number of bytes to skip before passing to the reader to the delimiter parser.
	 * 
	 * @return The number of bytes
	 * @since 0.72
	 */
	public int getNumBytesToSkip()
	{
		return this.numBytesToSkip;
	}
	
	/**
	 * Sets the row translator.
	 * 
	 * @param rowTranslator row translator
	 * @since 0.72
	 */
	public void setRowTranslator( RowTranslator rowTranslator )
	{
		this.rowTranslator = rowTranslator;
	}
	
	/**
	 * Returns the row translator.
	 * 
	 * @return row translator
	 * @since 0.72
	 */
	public RowTranslator getRowTranslator()
	{
		return this.rowTranslator;
	}
	
	/**
	 * Sets the import engine to use when importing data for 
	 * this entity.
	 * 
	 * @param engine the import engine
	 */
	public void setImportEngine( ImportEngine engine )
	{
		this.engine = engine;
	}
	
	/**
	 * Returns the import engine to use when importing
	 * data for this entity.
	 * 
	 * @return the import engine
	 */
	public ImportEngine getImportEngine()
	{
		return this.engine;
	}
	
	/**
	 * Returns the list of columns that will be imported
	 * 
	 * @return list of column definitions
	 */
	public ImportColumnDef[] getColumns()
	{
		Object[] objs = importColumns.toArray();
		ImportColumnDef[] defs= new ImportColumnDef[objs.length];
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[i] = (ImportColumnDef) objs[i];
		}
		return defs;	
	}

	/**
	 * Returns true if some column's values need to be generated.
	 * 
	 * @return true if some column's values need to be generated
	 * @since 0.7
	 */
	public boolean hasColumnsToGenerate()
	{
		return this.hasColumnsToGenerate;
	}
	
	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("ImportEntityDef : ");
		buf.append( "table = '"+getTable()+"',");
		buf.append( "schema = '"+getSchema()+"',");
		buf.append( "catalog = '"+getCatalog()+"',");
		buf.append( "encoding = '"+getSourceEncoding()+"',");
		buf.append( "source = '"+getSource()+"'\n");
		if ( this.parser != null )
		{
			buf.append( "delimiterParser = "+getDelimiterParser()+"\n" );
		}
		else
		{
			buf.append( "delimiterParser = "+getBinaryDelimiterParser()+"\n");
		}
		if ( engine != null )
		{
			buf.append( "importEngine = ["+engine.getClass().getName()+"]\n");
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
