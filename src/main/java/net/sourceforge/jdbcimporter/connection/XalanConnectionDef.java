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
import java.util.Properties;

import net.sourceforge.jdbcimporter.ConnectionDef;

import org.apache.xalan.lib.sql.ConnectionPool;
import org.apache.xalan.lib.sql.ConnectionPoolManager;
import org.apache.xalan.lib.sql.DefaultConnectionPool;

/**
 * The XalanConnectionDef class defines the attributes to use
 * to retrieve a connection from the DriverManager and supply it to 
 * Apache Xalan for use in an XSL.
 *
 * @version 	0.6
 * @author      Chris Nagy
 */
public class XalanConnectionDef implements ConnectionDef
{
	/**
	 * The JDBC Driver classname.
	 */
	protected String driver;
	
	/**
	 * The connection url.
	 */
	protected String url;
	
	/**
	 * The username.
	 */
	protected String username;
	
	/**
	 * The password.
	 */
	protected String password;
	
	/**
	 * The properties for the connection.
	 */
	protected Properties properties;

	/**
	 * The name of pool.
	 */
	protected String name = "ext-pool";
	
	/**
	 * The minimum number of connections to allocate in the pool.
	 */
	protected int    min = 2;
	
	/**
	 * The flag indicating that the pool should be removed.
	 */
	protected boolean cleanupPool = true;
	
	/**
	 * The flag indicating that the pool was created by the connection def.
	 */
	protected boolean created = false;
	
	/**
	 * The connection pool manager.
	 */
	protected ConnectionPoolManager poolManager = new ConnectionPoolManager();
	
	/**
	 * @see net.sourceforge.jdbcimporter.ConnectionDef#getConnection()
	 */
	public Connection getConnection()
	{
		ConnectionPool pool = poolManager.getPool( name );
		if ( pool == null )
		{
			DefaultConnectionPool cp = new DefaultConnectionPool();
			cp.setDriver( this.getDriver() );
			cp.setURL( this.getUrl() );
			cp.setUser( this.getUsername() );
			cp.setPassword( this.getPassword() );
			cp.setMinConnections( min );
			cp.setPoolEnabled( (min > 1));
			poolManager.registerPool( name, cp );
			pool = cp;
			created = true;
		}
		try
		{
			return pool.getConnection();
		}
		catch ( SQLException ex )
		{
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @see net.sourceforge.jdbcimporter.ConnectionDef#releaseConnection(java.sql.Connection)
	 */
	public void releaseConnection(Connection con)
	{
		ConnectionPool pool = poolManager.getPool(name);
		try
		{
			pool.releaseConnection( con );
		}
		catch ( SQLException ex )
		{
			ex.printStackTrace();
		}
		if ( created && cleanupPool )
		{
			poolManager.removePool( name );
		}
	}

	/**
	 * Returns true if the connection def should cleanup the pool that it created.
	 * If the pool existed before the connection def then it will not be cleaned up.
	 * 
	 * @return whether the connection def will cleanup the connection pool
	 */
	public boolean isCleanupPool()
	{
		return cleanupPool;
	}

	/**
	 * Returns the min number of connections in the pool.
	 * 
	 * @return minimum number of connections
	 */
	public int getMin()
	{
		return min;
	}

	/**
	 * Returns the name of the pool.
	 * 
	 * @return name of pool
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the flag the determines if the connection def should cleanup
	 * the pool that it created. By default, the flag is true. 
	 * 
	 * @param b flag to indicate that the connection def should cleanup the
	 * connection pool
	 */
	public void setCleanupPool(boolean b)
	{
		cleanupPool = b;
	}

	/**
	 * Sets the minimum number of connections in the pool. This
	 * should be greater than 1. By default, the pool size is 2.
	 * 
	 * @param i minimum number of connections
	 */
	public void setMin(int i)
	{
		min = i;
	}

	/**
	 * Sets the name of the pool in Xalan.
	 * 
	 * @param string the name of the pool
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * Sets the driver classname.
	 * 
	 * @param newDriver the driver classname.
	 */	
	public void setDriver( String newDriver )
	{
		driver = newDriver;	
	}
	
	/**
	 * Returns the driver classname.
	 * 
	 * @return the driver classname
	 */
	public String getDriver()
	{
		return driver;	
	}
	
	/**
	 * Sets the connection url.
	 * 
	 * @param newUrl the connection url
	 */
	public void setUrl( String newUrl )
	{
		url = newUrl;	
	}
	
	/**
	 * Returns the connection url.
	 * 
	 * @return the connection url
	 */
	public String getUrl()
	{
		return url;	
	}
	
	/**
	 * Sets the username.
	 * 
	 * @param newUsername the username
	 */
	public void setUsername( String newUsername )
	{
		username = newUsername;
	}
	
	/** 
	 * Returns the username.
	 * 
	 * @return the username
	 */
	public String getUsername()
	{
		return username;	
	}
	
	/**
	 * Sets the password.
	 * 
	 * @param newPassword the password
	 */
	public void setPassword( String newPassword )
	{
		password = newPassword;	
	}
	
	/**
	 * Return the password.
	 * 
	 * @return the password
	 */
	public String getPassword()
	{
		return password;	
	}
	
	/**
	 * Sets the properties.
	 * 
	 * @param newProperties the properties
	 */
	public void setProperties( Properties newProperties )
	{
		properties = newProperties;	
	}
	
	/**
	 * Returns the properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties()
	{
		return properties;	
	}

	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("XalanConnectionDef : ");
		buf.append( "driver = '"+driver+"',");
		buf.append( "url = '"+this.url+"',");
		buf.append( "username = '"+this.username+"',");
		buf.append( "password = '"+this.password+"',");
		buf.append( "poolName = '"+this.name+"',");
		buf.append( "cleanup = '"+this.cleanupPool+"',");
		buf.append( "minPoolSize = '"+this.min+"',");
		buf.append( "properties = "+properties );
		return buf.toString();
	}
	
}
