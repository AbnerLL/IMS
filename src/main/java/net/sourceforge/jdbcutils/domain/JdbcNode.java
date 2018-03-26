package net.sourceforge.jdbcutils.domain;

import java.util.List;

public class JdbcNode extends Node{
	
	List<JdbcProperty> property;
	
	public List<JdbcProperty> getProperty() {
		return property;
	}
	public void setProperty(List<JdbcProperty> property) {
		this.property = property;
	}

}
