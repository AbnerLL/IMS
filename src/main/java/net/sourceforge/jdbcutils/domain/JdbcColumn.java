package net.sourceforge.jdbcutils.domain;

import java.util.List;

public class JdbcColumn extends Column{
	
	List<JdbcProperty> property;
	JdbcNode generator;
	JdbcNode translator;
	
	public JdbcNode getGenerator() {
		return generator;
	}
	public void setGenerator(JdbcNode generator) {
		this.generator = generator;
	}
	public JdbcNode getTranslator() {
		return translator;
	}
	public void setTranslator(JdbcNode translator) {
		this.translator = translator;
	}
	public List<JdbcProperty> getProperty() {
		return property;
	}
	public void setProperty(List<JdbcProperty> property) {
		this.property = property;
	}

}
