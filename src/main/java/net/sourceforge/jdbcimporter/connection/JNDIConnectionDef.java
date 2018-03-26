package net.sourceforge.jdbcimporter.connection;

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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;

import net.sourceforge.jdbcimporter.ConnectionDef;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The JNDIConnectionDef defines attributes to use to retrieve
 * a connection from a data source in a JNDI namespace.
 *
 * @version    0.63  
 * @author     Chris Nagy
 */
public class JNDIConnectionDef implements ConnectionDef
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( JNDIConnectionDef.class );
	
	/**
	 * The hashtable for the Initial Context.
	 */
	Hashtable env;
	
	/**
	 * The name of data source. 
	 */
	String    name;
	
	/**
	 * Create a JNDI connection definition.
	 */
	public JNDIConnectionDef( )
	{
		super( );
	}

	/**
	 * Sets the name of the data source.
	 * 
	 * @param name name of the data source
	 */
	public void setName( String name )
	{
		this.name = name;
	}
	
	/**
	 * Sets the url of the JNDI.
	 * 
	 * @param url the provider url
	 */
	public void setProviderURL( String url )
	{
		if ( env == null )
		{
			env = new Hashtable();
		}
		env.put( Context.PROVIDER_URL, url );
	}
	
	/**
	 * Sets the list of classnames that should be used
	 * as object factories.
	 * 
	 * @param factories the list of object factories
	 */
	public void setObjectFactories( String factories )
	{
		if ( env == null )
		{
			env = new Hashtable();
		}
		env.put( Context.OBJECT_FACTORIES, factories );
	}
	
	/**
	 * @see net.sourceforge.jdbcimporter.ConnectionDef#getConnection()
	 */
	public Connection getConnection( )
	{
		InitialContext ic = null;
		try
		{
			if ( env != null )
			{
				ic = new InitialContext(env);
			}
			else
			{
				ic = new InitialContext();
			}
			Object obj = ic.lookup( name );
			if ( obj instanceof ConnectionPoolDataSource )
			{
				ConnectionPoolDataSource ds = (ConnectionPoolDataSource) obj;
				PooledConnection poolConnection = ds.getPooledConnection();
				return poolConnection.getConnection();
			}
			else if ( obj instanceof DataSource )
			{
				DataSource ds = (DataSource) obj;
				return ds.getConnection();
			}
			else 
			{
				LOG.error("Unknown Datasource : "+obj.getClass().getName() );
			}
		}
		catch ( NamingException n )
		{
			LOG.error( "Could not lookup datasource", n );
		}
		catch ( SQLException e )
		{
			LOG.error( "Could not get connection", e );
		}
		return null;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.ConnectionDef#releaseConnection(java.sql.Connection)
	 */
	public void releaseConnection( Connection con )
	{
		try
		{
			con.close();
		}
		catch ( SQLException e )
		{
			LOG.error("Could not close connection", e );
		}
	}

}
