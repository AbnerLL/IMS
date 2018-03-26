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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import net.sourceforge.jdbcimporter.ConnectionDef;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The JDBCConnectionDef class defines the attributes to use
 * to retrieve a connection from the DriverManager.
 * 
 * @version 	0.6
 * @author     Chris Nagy
 */
public class JDBCConnectionDef implements ConnectionDef 
{
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog( JDBCConnectionDef.class );
	
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
	 * @see ConnectionDef#getConnection()
	 */
	public Connection getConnection() 
	{
		if ( driver == null )
		{
			System.err.println("Driver class not specified");
			return null;
		}
		
		if ( url == null )
		{
			System.err.println("Connection url not specified");
			return null;	
		}
	
		try
		{
			Class.forName( driver );
			if ( username == null && password == null )
			{
				if ( properties == null )
				{
					return DriverManager.getConnection( url );
				}
				else
				{
					return DriverManager.getConnection( url, properties );
				}
			}
			else
			{
				return DriverManager.getConnection( url, username, password );
			}
		}
		catch ( ClassNotFoundException c )
		{
			LOG.fatal("Cound not load class : "+driver, c);
		}		
		catch ( SQLException s )
		{
			LOG.fatal("Count not connect to '"+url+"'", s);
		}
		return null;
	}
	
	
	/**
	 * @see ConnectionDef#releaseConnection(Connection)
	 */
	public void releaseConnection( Connection con )
	{
		try
		{
			con.close();	
		}
		catch ( SQLException s )
		{
			LOG.error("Could not close connection", s );
		}	
	}

	/**
	 * Overrides Object.toString()
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("JDBCConnectionDef : ");
		buf.append( "driver = '"+this.driver+"',");
		buf.append( "url = '"+this.url+"',");
		buf.append( "username = '"+this.username+"',");
		buf.append( "password = '"+this.password+"',");
		buf.append( "properties = "+this.properties );
		return buf.toString();
	}
}
