package net.sourceforge.jdbcutils.domain;


public class Column {
	
	String name;
	String SQLType;
	String generate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSQLType() {
		return SQLType;
	}
	public void setSQLType(String type) {
		SQLType = type;
	}
	public String getGenerate() {
		return generate;
	}
	public void setGenerate(String generate) {
		this.generate = generate;
	}
	

}
