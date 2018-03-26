package net.sourceforge.jdbcutils.domain;

import java.util.Collection;
public class JdbcEntity extends Entity{
	
	JdbcProperty property;
	JdbcNode delimiter;
	JdbcNode translator;
	Collection<JdbcColumn> column;
	public Collection<JdbcColumn> getColumn() {
		return column;
	}
	public void setColumn(Collection<JdbcColumn> column) {
		this.column = column;
	}
	public JdbcNode getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(JdbcNode delimiter) {
		this.delimiter = delimiter;
	}
	public JdbcNode getTranslator() {
		return translator;
	}
	public void setTranslator(JdbcNode translator) {
		this.translator = translator;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public JdbcProperty getProperty() {
		return property;
	}
	public void setProperty(JdbcProperty property) {
		this.property = property;
	}
}
