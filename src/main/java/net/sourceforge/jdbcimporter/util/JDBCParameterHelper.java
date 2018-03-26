package net.sourceforge.jdbcimporter.util;

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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import net.sourceforge.jdbcimporter.ColumnDef;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.ImportColumnDef;
import net.sourceforge.jdbcimporter.ImportEntityDef;
import net.sourceforge.jdbcimporter.MalformedDataException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The JDBCParameterHelper provides a methods to set parameters of a PreparedStatement and
 * retrieve values from a ResultSet.
 * 
 * @version 0.62
 * @author Chris Nagy
 */
public class JDBCParameterHelper
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( JDBCParameterHelper.class );
	
	protected boolean emptyStringAsNull = false;

	/**
	 * Sets a flag indicating how empty strings are set in a prepared statement.
	 * If the flag is true then empty strings will be treated as null for character
	 * columns, otherwise they will be inserted as is. 
	 * 
	 * @param flag flag indicating how empty strings are set in a prepared statement
	 */
	public void setEmptyStringAsNull( boolean flag )
	{
		this.emptyStringAsNull = flag;
	}
	
	/**
	 * Set the column value in the prepared statement.
	 * 
	 * @param pstmt the prepared statement
	 * @param column the column index
	 * @param def the column definition
	 * @param val the column value
	 * @throws MalformedDataException
	 */
	public void setColumn( PreparedStatement pstmt, int column, ColumnDef def, ColumnValue val ) throws MalformedDataException
	{
		if ( LOG.isTraceEnabled() )
		{
			LOG.trace( "setColumn( <PreparedStatement>, "+column+", <ColumnDef:"+def.getName()+">, "+val+" ) -> ");
		}
		String formatType = "object";
		try
		{
			if ( (val.isString() && isStringNull( def.getType(), val.getString() ) ) ||
				 (val.isBytes()  && val.getBytes() == null ) ||
				 (val.isInputStream() && val.getInputStream() == null ) ||
				 (val.isObject() && val.getObject() == null ) )
			{
				pstmt.setNull(column,def.getType());
				return;
			}
			switch( def.getType() )
			{
				case Types.VARCHAR:
				case Types.CHAR:
					{
						if ( val.isString() )
						{
							pstmt.setString(column, val.getString());
						}
						else if ( val.isObject() )
						{
							pstmt.setString(column, val.getObject().toString() );
						}
					}
					break;
				case Types.INTEGER:
					{
						if ( val.isString() )
						{
					   		formatType = "integer";
							Number num = (Number) def.getFormat().parseObject( val.getString() );
							pstmt.setInt(column, num.intValue() );
						}
						else if ( val.isObject() && val.getObject() instanceof Number )
						{
							Number num = (Number) val.getObject();
							pstmt.setInt(column, num.intValue() );
						}
					}
					break;
				case Types.SMALLINT:
				case Types.TINYINT:
					{
						if ( val.isString() )
						{
					   		formatType = "short";
							Number num = (Number) def.getFormat().parseObject( val.getString() );
							pstmt.setShort(column, num.shortValue() );
						}
						else if ( val.isObject() && val.getObject() instanceof Number )
						{
							Number num = (Number) val.getObject();
							pstmt.setShort(column, num.shortValue() );
						}
					}
					break;
				case Types.BIGINT:
					{
						if ( val.isString() )
						{
					   		formatType = "long";
							Number num = (Number) def.getFormat().parseObject( val.getString() );
							pstmt.setLong(column, num.longValue() );
						}
						else if ( val.isObject() && val.getObject() instanceof Number )
						{
							Number num = (Number) val.getObject();
							pstmt.setLong(column, num.longValue() );
						}
					}
					break;
				case Types.NUMERIC:
				case Types.DECIMAL:
					{
						if ( val.isString() )
						{
					   		formatType = "bigdecimal";
							Number num = (Number) def.getFormat().parseObject( val.getString() );
							pstmt.setBigDecimal(column, new BigDecimal( num.toString() ) );
						}
						else if ( val.isObject() && val.getObject() instanceof Number )
						{
							Number num = (Number) val.getObject();
							pstmt.setBigDecimal(column, new BigDecimal( num.toString() ) );
						}
					}
					break;
				case Types.DOUBLE:
					{
						if ( val.isString() )
						{
				   			formatType = "double";
							Number num = (Number) def.getFormat().parseObject( val.getString() );
							pstmt.setDouble(column, num.doubleValue() );
						}
						else if ( val.isObject() && val.getObject() instanceof Number )
						{
							Number num = (Number) val.getObject();
							pstmt.setDouble(column, num.doubleValue() );
						}
					}
					break;
				case Types.DATE:
					{
						if ( val.isString() )
						{
				   			formatType = "date";
							Date date = (Date) def.getFormat().parseObject( val.getString() );
							pstmt.setDate(column, new java.sql.Date( date.getTime() ) );
						}
						else if ( val.isObject() && val.getObject() instanceof Date )
						{
							Date date = (Date) val.getObject();
							pstmt.setDate(column, new java.sql.Date( date.getTime() ) );
						}
					}
					break;
				case Types.FLOAT:
				case Types.REAL:
					{
						if ( val.isString() )
						{
			   				formatType = "float";
							Number num = (Number) def.getFormat().parseObject( val.getString() );
							pstmt.setFloat(column, num.floatValue() );
						}
						else if ( val.isObject() && val.getObject() instanceof Number )
						{
							Number num = (Number) val.getObject();
							pstmt.setFloat(column, num.floatValue() );
						}
					}
					break;
				case Types.TIME:
					{
						if ( val.isString() )
						{
				   			formatType = "time";
							Date date = (Date) def.getFormat().parseObject( val.getString() );
							pstmt.setTime(column, new java.sql.Time( date.getTime() ) );
						}
						else if ( val.isObject() && val.getObject() instanceof Date )
						{
							Date date = (Date) val.getObject();
							pstmt.setTime(column, new java.sql.Time( date.getTime() ) );
						}
					}
					break;
				case Types.TIMESTAMP:	
					{
						if ( val.isString() )
						{
				   			formatType = "timestamp";
							Date date = (Date) def.getFormat().parseObject( val.getString() );
							pstmt.setTimestamp(column, new java.sql.Timestamp( date.getTime() ) );
						}
						else if ( val.isObject() && val.getObject() instanceof Date )
						{
							Date date = (Date) val.getObject();
							pstmt.setTimestamp(column, new java.sql.Timestamp( date.getTime() ) );
						}
					}
					break;
				case Types.BOOLEAN :
				case Types.BIT :
					{
						formatType = "boolean";
						boolean bool = false;
						if ( val.isString() )
							bool = Boolean.valueOf( val.getString() ).booleanValue();
						else if ( val.isBytes() )
							bool = ((val.getBytes()[0] & 0x01)== 0x01);
						else if ( val.isObject() && val.getObject() instanceof Boolean )
							bool = ((Boolean) val.getObject() ).booleanValue();
						pstmt.setBoolean( column, bool );
					}
					break;
				case Types.VARBINARY :
				case Types.BINARY :
					{
						formatType = "byte[]";
						byte[] bytes;
						if ( val.isString() )
							bytes = val.getString().getBytes();
						else
							bytes = val.getBytes();
						pstmt.setBytes( column, bytes );
					}
					break;
				case Types.LONGVARBINARY:
				case Types.LONGVARCHAR:
					{
						formatType = "InputStream";
						InputStream in;
						int length = -1;
						if ( val.isString() )
						{
							in = new ByteArrayInputStream( val.getString().getBytes() );
							length = val.getString().getBytes().length;
						}
						else if ( val.isBytes() )
						{
							in = new ByteArrayInputStream( val.getBytes() );
							length = val.getBytes().length;
						}
						else
						{
							in = val.getInputStream();
							length = val.getInputStreamLength();
						}

						if ( def.getType() == Types.LONGVARBINARY )
						{
							pstmt.setBinaryStream( column, in, length );
						}
						else if ( def.getType() == Types.LONGVARCHAR )
						{
							pstmt.setAsciiStream( column, in, length );
						}
					}
					break;
				default:
					throw new MalformedDataException( "Unhandled SQL Type : "+def.getType()+" at column #"+column );
			}
		}
		catch (SQLException s)
		{
			throw new MalformedDataException( "Could not bind column",column, s);
		}
		catch (NumberFormatException n)
		{
			throw new MalformedDataException( "Could not parse number", column, n);
		}
		catch (ParseException p)
		{
			throw new MalformedDataException("Could not parse "+formatType, column, p );
		}
		if ( LOG.isTraceEnabled() )
		{
			LOG.trace( "setColumn( <PreparedStatement>, "+column+", <ColumnDef:"+def.getName()+">, "+val+" ) <- ");
		}
	}
	
	/**
	 * Resolve whether the rows provided where imported into the database by select them based on the
	 * key indices.
	 * 
	 * @param con the connection
	 * @param entityDef the entity def
	 * @param rows the rows to resolve
	 * @param keyIndices the indices into the entity def columns that represent the primary key
	 * @param compareIndices the indices of the entity def columns that need to be compared
	 * @return a list of row states : 0 = row not found or does not match, 1 = row matches
	 */
	public int[] resolveRowStates( Connection con, ImportEntityDef entityDef, List rows, int[] keyIndices, int[] compareIndices )
	{
		LOG.trace( "resolveRowStates( <Connection>, <ImportEntityDef:"+entityDef.getTable()+">, <List:"+rows.size()+">, <int[]:"+keyIndices.length+">, <int[]:"+compareIndices.length+"> ) -> ");
		int[] rowStates = new int[ rows.size() ];
		for ( int i = 0; i < rowStates.length; i++ )
		{
			rowStates[i] = 0;
		}
		PreparedStatement pstmt = null;
		int curRow = 0;
		try
		{
			ImportColumnDef[] columns = entityDef.getColumns();
			StringBuffer sql = new StringBuffer("select ");
			if ( compareIndices.length > 0 )
			{
				for ( int i = 0; i < compareIndices.length; i++ )
				{
					if ( i > 0 )
					{
						sql.append(", ");
					}
					sql.append( columns[ compareIndices[i]].getName() );
				}
			}
			else
			{
				sql.append( columns[ keyIndices[0] ].getName() );
			}
			sql.append( " from ");
			sql.append( entityDef.getTable() );
			sql.append( " where " );
			for ( int i = 0; i < keyIndices.length; i++ )
			{
				if ( i > 0 )
				{
					sql.append( " and " );
				}
				sql.append( columns[ keyIndices[i] ].getName() );
				sql.append( " = ? ");
			}
			LOG.debug( "Resolve Row States Select Sql : "+sql.toString() );
			pstmt = con.prepareStatement( sql.toString() );
			
			for ( curRow = 0; curRow < rows.size(); curRow++ )
			{
				ColumnValue[] values = (ColumnValue[]) rows.get(curRow);
				for ( int j = 0; j < keyIndices.length; j++ )
				{
					this.setColumn( pstmt, j+1, columns[ keyIndices[j] ], values[ keyIndices[j] ] );
				}
				ResultSet rset = pstmt.executeQuery();
				boolean next = rset.next();
				LOG.debug("Row #"+curRow+", Next = "+next);
				if ( !next )
				{
					rowStates[curRow] = 0;
				}
				else if ( compareIndices.length > 0 )
				{
					rowStates[curRow] = (compareResultSet( rset, columns, compareIndices, values )? 1 : 0);
				}
				else
				{
					rowStates[curRow] = 1;
				}
				rset.close();
			}
		}
		catch ( SQLException s )
		{
			LOG.error("Unable to resolve bad rows", s );
		}
		catch ( MalformedDataException male )
		{
			LOG.error("Unable to resolve bad rows", male );
		}
		finally
		{
			if ( pstmt != null )
			{
				try
				{
					pstmt.close();
				}
				catch ( SQLException s )
				{
					LOG.error("Unable to close prepared statement", s );
				}
			}
		}
		LOG.trace( "resolveRowStates( <Connection>, <ImportEntityDef:"+entityDef.getTable()+">, <List:"+rows.size()+">, <int[]:"+keyIndices.length+">, <int[]:"+compareIndices.length+"> ) <- ");
		return rowStates;
	}
	
	/**
	 * Compare the row in the result set with the values given.
	 * 
	 * @param rset the result set
	 * @param columns the list of columns
	 * @param compareIndices the indices of the columns that should be compared
	 * @param values the column values
	 * @return true if the row matches, otherwise false
	 * @throws SQLException
	 */
	public boolean compareResultSet( ResultSet rset, ImportColumnDef[] columns, int[] compareIndices, ColumnValue[] values ) throws SQLException
	{
		for ( int i = 0; i < compareIndices.length; i++ )
		{
			ColumnValue resultValue = getColumn( rset, i + 1, columns[compareIndices[i] ] );
			if ( !resultValue.equals( values[ compareIndices[i] ] ) && 
			     !( columns[ compareIndices[i] ].getType() == Types.CHAR && 
			        resultValue.getString().trim().equals( values[ compareIndices[i] ].getString() ) )
			   )
			{
				 return false;		
			}
		}
		return true;
	}
	
	/**
	 * Returns the column value for a given column index in the result set.
	 * 
	 * @param resultSet the result set
	 * @param column the column index
	 * @param def the column definition
	 * @return the column value
	 * @throws SQLException
	 */
	public ColumnValue getColumn( ResultSet resultSet, int column, ColumnDef def ) throws SQLException
	{
		LOG.trace( "getColumn( <ResultSet>, "+column+", <ColumnDef:"+def.getName()+"> ) -> ");
		ColumnValue value = new ColumnValue();
		try
		{
			Object val = null;
			if ( def.getType() != Types.LONGVARBINARY && def.getType() != Types.LONGVARCHAR ){
				val = resultSet.getObject(column);
				if ( val == null ){
					return value;
				}
				if(def.getType() == Types.CLOB){
					 oracle.sql.CLOB clob = (oracle.sql.CLOB) val;
					 String str = clob.getSubString(1, (int) clob.length());
					 value.setString(str);
					 return value;
				}
				LOG.debug("type='"+def.getType()+"', val instanceof "+val.getClass().getName());
			}
			switch( def.getType() )
			{
				case Types.VARCHAR:
				case Types.CHAR:
					{
						if ( val instanceof String )
						{
							value.setString( (String) val );
						}
						else
						{
							value.setString( resultSet.getString(column));
						}
					}
					break;
				case Types.INTEGER:
					{
						if ( val instanceof Number )
						{
							value.setString( def.getFormat().format( new Integer( ((Number) val).intValue() ) ));
						}
						else
						{
							value.setString( def.getFormat().format( new Integer( resultSet.getInt(column)) ));
						}
					}
					break;
				case Types.SMALLINT:
				case Types.TINYINT:
					{
						if ( val instanceof Number )
						{
							value.setString( def.getFormat().format( new Short( ((Number) val).shortValue() ) ));
						}
						else
						{
							value.setString( def.getFormat().format( new Short(resultSet.getShort(column)) ));
						}
					}
					break;
				case Types.BIGINT:
					{
						if ( val instanceof Number )
						{
							value.setString( def.getFormat().format( new Long( ((Number) val).longValue() ) ));
						}
						else
						{
							value.setString( def.getFormat().format( new Long(resultSet.getLong(column)) ));
						}
					}
					break;
				case Types.NUMERIC:
				case Types.DECIMAL:
					{
						if ( val instanceof Number )
						{
							value.setString( def.getFormat().format( ((Number) val) ) );
						}
						else
						{
							value.setString( def.getFormat().format( resultSet.getBigDecimal(column)) );
						}
					}
					break;
				case Types.DOUBLE:
					{
						if ( val instanceof Number )
						{
							value.setString( def.getFormat().format( new Double( ((Number) val).doubleValue() ) ));
						}
						else
						{
							value.setString( def.getFormat().format( new Double(resultSet.getDouble(column)) ));
						}
					}
					break;
				case Types.FLOAT:
				case Types.REAL:
					{
						if ( val instanceof Number )
						{
							value.setString( def.getFormat().format( new Float( ((Number) val).floatValue() ) ));
						}
						else
						{
							value.setString( def.getFormat().format( new Float(resultSet.getFloat(column)) ));
						}
					}
					break;
				case Types.DATE:
					{
						if ( val instanceof java.sql.Date )
						{
							value.setString( def.getFormat().format( (java.sql.Date) val ) );
						}
						else
						{
							value.setString( def.getFormat().format( resultSet.getDate(column) ) );
						}
					}
					break;
				case Types.TIME:
					{
						if ( val instanceof java.sql.Time )
						{
							value.setString( def.getFormat().format( (java.sql.Time) val ) );
						}
						else
						{
							value.setString( def.getFormat().format( resultSet.getTime(column) ) );
						}
					}
					break;
				case Types.TIMESTAMP:	
					{
						if ( val instanceof java.sql.Timestamp )
						{
							value.setString( def.getFormat().format( (java.sql.Timestamp) val ) );
						}
						else
						{
							value.setString( def.getFormat().format( resultSet.getTimestamp(column) ) );
						}
					}
					break;
				case Types.BOOLEAN:
					{
						if ( val instanceof Boolean )
						{
							value.setString( val.toString() );
						}
						else
						{
							value.setString( Boolean.toString( resultSet.getBoolean( column ) ) );
						}
					}
					break;
				case Types.BIT:
					{
						if ( val instanceof Boolean )
						{
							value.setBytes( new byte[] { (byte) ( ((Boolean) val).booleanValue() ? 0x01 : 0x00) } );
						}
						else
						{
							value.setBytes( new byte[] { (byte) (resultSet.getBoolean( column ) ? 0x01 : 0x00) } );
						}
					}
					break;
				case Types.VARBINARY :
				case Types.BINARY :
					{
						if ( val instanceof byte[] )
						{
							value.setBytes( (byte[]) val );
						}
						else
						{
							value.setBytes( resultSet.getBytes( column ) );
						}
					}
					break;
				case Types.LONGVARBINARY:
					value.setInputStream( resultSet.getBinaryStream( column ) );
					break;
				case Types.LONGVARCHAR:
					value.setInputStream( resultSet.getAsciiStream( column ) );
					break;
				default:
					throw new SQLException( "Unhandled SQL Type : "+def.getType()+" at column #"+column );
			}
		}
		catch (SQLException s)
		{
			s.printStackTrace();
			SQLException wrapper = new SQLException( "Could not retrieve column #"+column);
			wrapper.setNextException( s );
			throw wrapper;
		}
		LOG.trace( "getColumn( <ResultSet>, "+column+", <ColumnDef:"+def.getName()+"> ) <- ");
		return value;
	}	
	
	/**
	 * Returns the fully qualified table name (that includes the catalog and the schema).
	 * 
	 * @param metaData The database meta data
	 * @param catalog The catalog
	 * @param schema The schema
	 * @param table The table
	 * @return The fully qualified table name
	 * @throws SQLException
	 */
	public String getFullyQualifiedTableName( DatabaseMetaData metaData, String catalog, String schema, String table ) 
		throws SQLException
	{
		if ( LOG.isTraceEnabled() )
		{
			LOG.trace("Catalog max length : "+metaData.getMaxCatalogNameLength() );
			LOG.trace("Catalog at start   ? "+metaData.isCatalogAtStart() );
			LOG.trace("Catalog term       : "+metaData.getCatalogTerm());
			LOG.trace("Schema max length  : "+metaData.getMaxSchemaNameLength());
			LOG.trace("Schema term        : "+metaData.getSchemaTerm());
		}

		StringBuffer fullTableName = new StringBuffer(table);
		
		if ( schema != null && !"".equals( schema ) && 
			 metaData.getMaxSchemaNameLength() > 0 )
		{
			fullTableName.insert(0, schema+"." );
		}
		
		if ( catalog == null || "".equals( catalog ) || 
			 metaData.getMaxCatalogNameLength() <= 0 )
		{
			LOG.trace( "getFullyQualifiedTableName( <DatabaseMetaData>, "+
					"'"+catalog+"', '"+schema+"', '"+table+"' ) <- '"+
					fullTableName.toString()+"'");
			return fullTableName.toString();
		}
		
		if ( metaData.isCatalogAtStart() )
		{
			fullTableName.insert( 0, catalog + metaData.getCatalogSeparator() );
		}
		else
		{
			fullTableName.append( metaData.getCatalogSeparator() + catalog );
		}
		
		LOG.trace( "getFullyQualifiedTableName( <DatabaseMetaData>, "+
				"'"+catalog+"', '"+schema+"', '"+table+"' ) <- '"+
				fullTableName.toString()+"'");
		return fullTableName.toString();
	}
	
	
	protected boolean isStringNull( int type, String val )
	{
		if ( val == null ) return true;
		
		switch( type )
		{
			case Types.INTEGER:
			case Types.SMALLINT:
			case Types.TINYINT:
			case Types.BIGINT:
			case Types.NUMERIC:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.DATE:
			case Types.FLOAT:
			case Types.REAL:
			case Types.TIME:
			case Types.TIMESTAMP:	
			case Types.BOOLEAN :
			case Types.BIT :
				return (val.trim().length() == 0);
			case Types.VARCHAR :
			case Types.LONGVARCHAR :
				return (val.trim().length() == 0 && emptyStringAsNull);
			default :
				return (val.length() == 0 && emptyStringAsNull);
		}
	}
}

