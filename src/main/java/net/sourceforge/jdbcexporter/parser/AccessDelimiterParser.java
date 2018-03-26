package net.sourceforge.jdbcexporter.parser;

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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.ColumnValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

public class AccessDelimiterParser implements DelimiterFormatter {
	protected static Log LOG = LogFactory.getLog(AccessDelimiterParser.class);
	Database database=null;
	Table curTable=null;
	String fileName=null;
	String tableName=null;
	String[] columnNames=null;
	String[] columnTypes=null;
	
	protected OutputStream outputStream;
	protected int maxColumnNum;
	protected String columnDelimiter;
	protected int count=0;
	List<String[]> columnArrayList=new ArrayList();

	/**
	 * The character(s) that enclose each column value.
	 */
	protected String enclosedDelimiter;

	/**
	 * The flag indicating each column value must be enclosed by the set of
	 * characters defined.
	 */
	protected boolean enclosedOptional = false;

	/**
	 * Sets the positions, in the csv, to place empty strings as their values.
	 */
	protected int[] emptyPositions;

	/**
	 * The output writer.
	 */
	Writer writer;

	/**
	 * Sets the character(s) that separate each column value.
	 * 
	 * @param delimiter
	 *            the column value delimiter.
	 */
	public void setColumnDelimiter(String delimiter) {
		columnDelimiter = delimiter;
	}

	/**
	 * Returns the character(s) that separate each column value.
	 * 
	 * @return the column value delimiter
	 */
	public String getColumnDelimiter() {
		return columnDelimiter;
	}

	/**
	 * Sets the character(s) that enclose each column value.
	 * 
	 * @param delimiter
	 *            the enclose delimiter
	 */
	public void setEnclosedDelimiter(String delimiter) {
		enclosedDelimiter = delimiter;
	}

	/**
	 * Returns the character(s) that enclose each column value.
	 * 
	 * @return the enclose delimiter
	 */
	public String getEnclosedDelimiter() {
		return enclosedDelimiter;
	}

	/**
	 * Sets that each column value must be enclosed by the set of characters
	 * defined.
	 * 
	 * @param optional
	 *            boolean flag indicating that each column value must be
	 *            enclosed.
	 */
	public void setEnclosedOptional(boolean optional) {
		enclosedOptional = optional;
	}

	/**
	 * Returns whether each column value must be enclosed by the set of
	 * characters defined.
	 * 
	 * @return boolean flag indicating that each column value must be enclosed.
	 */
	public boolean isEnclosedOptional() {
		return enclosedOptional;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#setWriter(java.io.Writer)
	 */
	public void setWriter(Writer output) {
		initDatabase();
//		writer = output;
	}

	/**
	 * Sets the positions, in the csv, to place empty strings as their values.
	 * 
	 * @since 0.61
	 * @param positions
	 *            a comma-separated list of positions (first position = 1)
	 */
	public void setEmptyPositions(int[] positions) {
		this.emptyPositions = positions;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#formatValues(net.sourceforge.jdbcimporter.ColumnValue[])
	 */
	public String formatValues(ColumnValue[] values) {
		StringBuffer buf = new StringBuffer("");
		int valueIndex = 0;
		int position = 1;
		int emptyIndex = 0;

		while (valueIndex < values.length) {
			if (position > 1) {
				buf.append(columnDelimiter);
			}

			String valueStr = "";
			if (emptyPositions == null
					|| emptyIndex >= emptyPositions.length
					|| (emptyIndex < emptyPositions.length && emptyPositions[emptyIndex] != position)) {
				valueStr = values[valueIndex].getString();
				if (valueStr == null) {
					valueStr = "";
				}
				valueIndex++;
			} else {
				emptyIndex++;
			}
			boolean enclose = enclosedDelimiter != null
					&& (!enclosedOptional || valueStr.indexOf(columnDelimiter) != -1);
			if (enclose) {
				buf.append(enclosedDelimiter);
				valueStr = addEscapeCharacters(valueStr);
			}
			buf.append(valueStr);
			if (enclose) {
				buf.append(enclosedDelimiter);
			}
			position++;
		}

		if (emptyPositions != null && emptyIndex < emptyPositions.length
				&& emptyPositions[emptyIndex] == position) {
			if (position > 1) {
				buf.append(columnDelimiter);
			}
			String valueStr = "";
			boolean enclose = enclosedDelimiter != null
					&& (!enclosedOptional || valueStr.indexOf(columnDelimiter) != -1);
			if (enclose) {
				buf.append(enclosedDelimiter);
			}
			buf.append(valueStr);
			if (enclose) {
				buf.append(enclosedDelimiter);
			}
		}
		return buf.toString();
	}
	
	public void initDatabase(){
		if(database==null&&fileName!=null){
			try {
				File mdbFile=new File(fileName);
				if(mdbFile.exists()){
					try {
						database=Database.open(mdbFile,false);
					}catch (IOException e) {
						// TODO Auto-generated catch block
						database=Database.create(mdbFile);
					}
				}else{
					database = Database.create(mdbFile);
				}
				if(database.getTable(tableName)==null){
					TableBuilder tableBuilder=new TableBuilder(tableName);
					for(int i=0;i<columnNames.length;i++){
						ColumnBuilder columnBuilder=new ColumnBuilder(columnNames[i]);
						columnBuilder.setSQLType(Integer.parseInt(columnTypes[i]));
						tableBuilder.addColumn(columnBuilder.toColumn());
					}
					curTable=tableBuilder.toTable(database);
					
				}else{
					curTable=database.getTable(tableName);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeNextRow(String row) throws IOException {
		if (row!= null) {
			if(count==5000){
				try{
					columnArrayList.add(row.split(columnDelimiter));
					curTable.addRows(columnArrayList);
					count=0;
					columnArrayList=new ArrayList();
				}catch(Exception e){
					e.printStackTrace();
					return;
				}
			}else{
				try{
					columnArrayList.add(row.split(columnDelimiter));
					count++;
				}catch(Exception e){
					e.printStackTrace();
					return;
				}
			}
//			String[] columnArray=row.split(columnDelimiter);
//			curTable.addRow("ids","name","address");
//			curTable.addRow(columnArray);
//			System.out.println(curRowNum);
//			System.out.println(row);
		}
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#finish()
	 */
	public void finish() throws IOException {
		if(count>0){
			curTable.addRows(columnArrayList);
		}
		if(database!=null){
			database.close();
		}
	}

	/**
	 * add escaped characters to the next value.
	 * 
	 * @param nextValue
	 *            the next value
	 * @return the next value
	 */
	private String addEscapeCharacters(String nextValue) {
		StringBuffer buf = new StringBuffer("");
		int start = 0;
		int nextEscapePos = nextValue.indexOf(enclosedDelimiter);
		while (nextEscapePos != -1) {
			buf.append(nextValue.substring(start, nextEscapePos));
			buf.append(enclosedDelimiter);
			buf.append(enclosedDelimiter);
			start = nextEscapePos + enclosedDelimiter.length();
			nextEscapePos = nextValue.indexOf(enclosedDelimiter, start);
		}
		if (start < nextValue.length()) {
			buf.append(nextValue.substring(start));
		}
		return buf.toString();
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public int getMaxColumnNum() {
		return maxColumnNum;
	}

	public void setMaxColumnNum(int maxColumnNum) {
		this.maxColumnNum = maxColumnNum;
	}

	public Database getDatabase() {
		return database;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Table getCurTable() {
		return curTable;
	}

	public void setCurTable(Table curTable) {
		this.curTable = curTable;
	}

	public String[] getColumnTypes() {
		return columnTypes;
	}

	public void setColumnTypes(String[] columnTypes) {
		this.columnTypes = columnTypes;
	}
	
	public static void main(String[] args) {
		
		String fileName="D:/test_exp4.mdb";
		String tableName="test2";
		String[] columnNames={"id","name","address"};
		String[] columnType={"12","12","12"};
		List<Column> columnList=new ArrayList();
		Database database;
		Table table=null;
		
		
		File mdbFile=new File(fileName);
		if(mdbFile.exists()){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}
//		try {
//			File mdbFile=new File(fileName);
//			if(mdbFile.exists()){
//				database=Database.open(mdbFile);
//			}else{
//				database = Database.create(mdbFile);
//			}
//			if(database.getTable(tableName)==null){
//				TableBuilder tableBuilder=new TableBuilder(tableName);
//				for(int i=0;i<columnNames.length;i++){
//					ColumnBuilder columnBuilder=new ColumnBuilder(columnNames[i]);
//					columnBuilder.setSQLType(Types.VARCHAR);
//					tableBuilder.addColumn(columnBuilder.toColumn());
//				}
//				table=tableBuilder.toTable(database);
//				
//			}else{
//				table=database.getTable(tableName);
//			}
//			for(int i=0;i<3;i++){
//				table.addRow("ids","name","address");
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	
}
