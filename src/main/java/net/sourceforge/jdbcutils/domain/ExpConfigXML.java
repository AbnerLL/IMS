package net.sourceforge.jdbcutils.domain;

import com.thoughtworks.xstream.XStream;

public class ExpConfigXML extends ConfigXML{
	
	JdbcConnection connection;
	JdbcEntity entity;
	
	public String toString() {
		XStream xstream = new XStream();
		xstream.useAttributeFor(String.class);
		xstream.alias("export", ExpConfigXML.class);
		xstream.alias("column", JdbcColumn.class);
		xstream.alias("property", JdbcProperty.class);
		xstream.addImplicitCollection(JdbcEntity.class, "column");
		xstream.addImplicitCollection(JdbcConnection.class, "property");
		xstream.addImplicitCollection(JdbcNode.class, "property");
		return xstream.toXML(this);
	}

	public JdbcConnection getConnection() {
		return connection;
	}

	public void setConnection(JdbcConnection connection) {
		this.connection = connection;
	}

	public JdbcEntity getEntity() {
		return entity;
	}

	public void setEntity(JdbcEntity entity) {
		this.entity = entity;
	}

	
}
