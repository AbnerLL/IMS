package net.sourceforge.jdbcimporter.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.sourceforge.jdbcimporter.ConnectionDef;

public class SpringConnectionDef implements ConnectionDef
{
	
	protected DataSource dataSource ;
	
	protected Connection conn;

	public Connection getConnection()
	{
		try {
			conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void releaseConnection(Connection con)
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
