package net.sourceforge.jdbcutils.domain;

import java.util.Collection;

public class JdbcConnection extends Connection{
	
	Collection<JdbcProperty> property;

	public Collection<JdbcProperty> getProperty() {
		return property;
	}

	public void setProperty(Collection<JdbcProperty> property) {
		this.property = property;
	}
	
}