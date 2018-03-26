package net.sourceforge.jdbcutils.domain;


public class Connection {
	
	public final static String TYPE_JDBC="jdbc";
	public final static String TYPE_SPRING="spring_conn";
	String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
