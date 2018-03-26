package net.sourceforge.jdbcimporter.parser;

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
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.DelimiterParser;
import net.sourceforge.jdbcimporter.MalformedDataException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The XMLDelimiterParser class implements the DelimiterParser interface
 * to parse column values from an XML document that was transformed
 * with the given XSL.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class XMLDelimiterParser implements DelimiterParser 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( XMLDelimiterParser.class );

	/**
	 * The XML Parser.
	 */
	protected DocumentBuilder documentBuilder;
	
	/**
	 * The XSL used to transform the XML.
	 */
	protected String xslString;
	
	/**
	 * The input reader.
	 */
	protected StreamSource xmlSource;

	/**
	 * The input reader after the XML has been transformed with the XSL. 
	 */
	protected Reader xslResultReader;
	
	/**
	 * The filename to store the XML after it has been transformed with the XSL.
	 */
	protected String xslResultFile;
	
	/**
	 * Flag to indicate that a thread should be forked to transform the XML.
	 */
	protected boolean forkThread = false;
	
	/**
	 * Constructor for XMLDeliimiterParser.
	 */
	public XMLDelimiterParser() {
		super();
	}

	/**
	 * Sets the XSL file.
	 * 
	 * @param filename the XSL filename
	 */
	public void setXSLFile( String filename )
	{
		StringBuffer xslBuf = new StringBuffer("");
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader( new FileReader( filename ) );
			String line = null;
			while ( ( line = reader.readLine() ) != null )
			{
				xslBuf.append(line);
				xslBuf.append("\n");
			}					
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException(e.getMessage());
		}
		finally
		{
			if ( reader != null )
			{
				try
				{
					reader.close();		
				}
				catch ( IOException e )
				{
					
				}
			}
		}
		setXSL( xslBuf.toString() );
	}
	
	/**
	 * Sets the XSL.
	 * 
	 * @param str the XSL
	 */
	public void setXSL( String str )
	{
		xslString = str;
	}
	
	/**
	 * Sets the file to store the resulting XML after it has been
	 * transformed by the XSL.
	 * 
	 * @param file the filename
	 */
	public void setResultFile( String file )
	{
		xslResultFile = file;
	}
	
	/**
	 * Sets a flag to indicate whether the XMLDelimiterParser
	 * will fork a thread to process the XSL.
	 * 
	 * @param flag fork thread flag
	 */
	public void setForkThread( boolean flag )
	{
		this.forkThread = flag;
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#setReader(Reader)
	 */
	public void setReader(Reader input) 
	{
		xmlSource = new StreamSource( input );
		xslResultReader = null;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getNextRow()
	 */
	public String getNextRow() throws IOException 
	{
		if ( xslResultReader == null )
		{
			try
			{
				documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			}
			catch ( FactoryConfigurationError e )
			{
				LOG.error("Error creating document builder factory", e);
				throw new IOException( e.getMessage() );
			}
			catch ( ParserConfigurationException e )
			{
				LOG.error("Error configuring parser", e );
				throw new IOException( e.getMessage() );
			}
			
			if ( xslResultFile != null )
			{
				LOG.debug("Storing XML Result in file '"+xslResultFile+"'");
				xslResultReader = initFileReader();
			}
			else if ( forkThread )
			{
				LOG.debug("Forking Thread");
				xslResultReader = initPipedReader();
			}
			else 
			{
				LOG.debug("Storing XML Result in memory");
				xslResultReader = initStringReader();
			}
			
			// Go to the beginning
			StringBuffer buf = new StringBuffer("");
			char[] nextChar = new char[1];
			int result = xslResultReader.read(nextChar);
			int count = 0;
			while ( result != -1 ) 
			{
				buf.append( nextChar[0] );
				if  ( buf.length() == 6 )
				{
					if ( !"<rows>".equals( buf.toString() ) )
					{
						buf.delete(0,1);
					}
					else
					{
						break;
					}
				}			
				count++;
				result = xslResultReader.read(nextChar);
			}			
			if ( !"<rows>".equals( buf.toString() ) )
			{
				try
				{
					xslResultReader.close();
				}
				catch ( IOException e )
				{
				}
				throw new IOException("Could not find start tag");
			}
		}
		
		StringBuffer buf = new StringBuffer("");
		char[] nextChar = new char[1];
		int result = xslResultReader.read(nextChar);
		while ( result != -1 )
		{
			buf.append( nextChar[0] );
			if ( buf.length() > 6 && "</row>".equals( buf.substring(buf.length()-6,buf.length()) ) )
			{
				break;
			}
			else if ( buf.length() >= 7 && "</rows>".equals( buf.substring(buf.length()-7,buf.length()) ) )
			{
				buf.delete(buf.length()-7,buf.length());
				break;
			}
			result = xslResultReader.read(nextChar);
		}
		if ( "".equals( buf.toString().trim() ) )
		{
			try
			{
				xslResultReader.close();
			}
			catch ( IOException e )
			{
			}
			return null;
		}
		else
		{
			return buf.toString().trim();
		}
	}

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getValues(String)
	 */
	public ColumnValue[] getValues(String nextRow)
		throws MalformedDataException 
	{
		Document document;
		try
		{
			document = documentBuilder.parse( new InputSource( new StringReader( nextRow ) ) );
		}
		catch ( IOException e )
		{
			throw new MalformedDataException( "IOException while trying to parse row", nextRow, e );	
		}	
		catch ( SAXException e )
		{
			throw new MalformedDataException( "SAXException while trying to parse row", nextRow, e );
		}
		Node parent = document.getFirstChild();
		if ( !"row".equals( parent.getNodeName() ) )
		{
			throw new MalformedDataException( "Root of document is not named 'row', but '"+parent.getNodeName()+"'", nextRow, null );
		}
		NodeList children = parent.getChildNodes();
		List columnValueList = new ArrayList();
		for ( int i = 0; i < children.getLength(); i++ )
		{
			if ( children.item(i).getNodeType() != Node.ELEMENT_NODE )
				continue;
			
			if ( !"column".equals( children.item(i).getNodeName() ) )
			{
				throw new MalformedDataException( "Expected node named 'column' for next column value,"+
					" but got node named '"+children.item(i).getNodeName()+"'", nextRow, null );
			}
			ColumnValue columnValue = new ColumnValue();
			if ( children.item(i).getFirstChild() == null )
			{
				LOG.debug("Next Value <null>");
				columnValue.setString( null );
			}
			else
			{
				String nextValue = children.item(i).getFirstChild().getNodeValue();
				LOG.debug("Next Value '"+nextValue+"'");
				columnValue.setString( nextValue );
			}
			columnValueList.add( columnValue );
		}
		ColumnValue[] columnValues = new ColumnValue[ columnValueList.size() ];
		System.arraycopy( columnValueList.toArray(), 0, columnValues, 0, columnValues.length );
		return columnValues;
	}

	/**
	 * Initializes a file reader that will be used to parse the resulting XML.
	 * 
	 * @return the reader
	 * @throws IOException
	 */
	protected Reader initFileReader() throws IOException
	{
		File resultFile = new File( xslResultFile );
		if ( resultFile.exists() && resultFile.isFile() )
		{
			resultFile.delete();
		}
		FileWriter writer = new FileWriter( xslResultFile );
		XSLRunnable runnable = new XSLRunnable( writer );
		runnable.run();
		writer.close();
		return new FileReader( xslResultFile );
	}
	
	/**
	 * Initializes a piped reader that will be used to parse the resulting XML.
	 *  
	 * @return the reader
	 * @throws IOException
	 */
	protected Reader initPipedReader() throws IOException
	{
		PipedReader reader = new PipedReader( );
		PipedWriter writer	= new PipedWriter( reader );
			
		Thread xslThread = new Thread( new XSLRunnable( writer ) );
		xslThread.start();
		return reader;
	}
	
	/**
	 * Initializes a string reader that will be used to parse the resulting XML.
	 * 
	 * @return the reader
	 * @throws IOException
	 */
	protected Reader initStringReader() throws IOException
	{
		StringWriter writer = new StringWriter();
		XSLRunnable runnable = new XSLRunnable( writer );
		runnable.run();
		
		return new StringReader( writer.toString() ); 
	}
	
	/**
	 * XSLRunnable transforms the XML.
	 */
	class XSLRunnable implements Runnable
	{
		Writer xslResultWriter;
		
		public XSLRunnable( Writer xslResultWriter )
		{
			this.xslResultWriter = xslResultWriter;	
		}
		
		public void run()
		{
			try
			{
				Transformer transformer = TransformerFactory.newInstance().newTransformer( new StreamSource( new StringReader( xslString ) ) );
				transformer.transform(xmlSource, new StreamResult( xslResultWriter ) );
			}
			catch ( TransformerConfigurationException e )
			{
				LOG.error("TransformerConfigurationException",e);
			}
			catch ( TransformerException e )
			{
				LOG.error("TransformerException", e );
			}
		}	
	}
}
