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

import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.ColumnValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExcelDelimiterParser implements DelimiterFormatter {
	protected static Log LOG = LogFactory.getLog(ExcelDelimiterParser.class);
	protected Workbook workbook;
	protected WritableWorkbook writableWorkbook=null;
	protected WritableSheet writableSheet=null;
	WritableFont writableFont=null;
	WritableCellFormat writableCellFormat=null;
	
	protected OutputStream outputStream;
	protected int curRowNum;
	protected int maxRowNum;
	protected int curColumnNum;
	protected int maxColumnNum;
	protected String fileName = null;
	protected String sheet = "sheet";
	protected int curSheetNum=0;
	protected String header;
	protected boolean writeHeader = true;
	protected int count=0;
	protected int sheetNum=0;

	/**
	 * The character(s) that separate each column value.
	 */
	protected String columnDelimiter;

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
	 * Sets the header of the output file.
	 * 
	 * @param header
	 *            The initial contents of the file
	 * @since 0.74
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * Returns the header of the output file.
	 * 
	 * @return The initial contents of the file
	 * @since 0.74
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#setWriter(java.io.Writer)
	 */
	public void setWriter(Writer output) {
		writer = output;
		writeHeader = true;
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
	
	public void initWorkbook(){
		try {
			writableWorkbook=Workbook.createWorkbook(new File(fileName));
			writableSheet=writableWorkbook.createSheet(sheet+sheetNum,sheetNum);
			writableSheet.getSettings().setDefaultColumnWidth(30);
			writableFont= new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false);
		    writableCellFormat= new WritableCellFormat(writableFont);
		    writableCellFormat.setWrap(true);//自动换行
		    writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中  
			this.setCurRowNum(0);
			this.setCurColumnNum(0);
			this.setCurSheetNum(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeNextRow(String row) throws IOException {
		if(writableWorkbook==null&&fileName!=null){
			this.initWorkbook();
		}
		if(count==60000){
			count=0;
			sheetNum++;
			writableSheet=writableWorkbook.createSheet(sheet+sheetNum,sheetNum);
			writableSheet.getSettings().setDefaultColumnWidth(30);
		}
		if (writeHeader) {
			if (header != null) {
				String[] headerArray=header.split(columnDelimiter);
				try {
					for(int i=0;i<headerArray.length;i++){
						Label label= new Label(i,count,headerArray[i],writableCellFormat);
						writableSheet.addCell(label);
					}
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.curRowNum++;
				this.count++;
			}
			writeHeader = false;
		}
		
		if (row!= null) {
			String[] columnArray=row.split(columnDelimiter);
			try {
				for(int i=0;i<columnArray.length;i++){ 
					Label label= new Label(i,count,columnArray[i],writableCellFormat);
					writableSheet.addCell(label);
				}
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(curRowNum);
//			System.out.println(row);
			this.curRowNum++;
			this.count++;
		}
	}

	/**
	 * @see net.sourceforge.jdbcexporter.DelimiterFormatter#finish()
	 */
	public void finish() throws IOException {
		try {
			if(writableWorkbook!=null){
				writableWorkbook.write();
				writableWorkbook.close();
			}
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public int getCurRowNum() {
		return curRowNum;
	}

	public void setCurRowNum(int curRowNum) {
		this.curRowNum = curRowNum;
	}

	public int getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(int maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public WritableWorkbook getWritableWorkbook() {
		return writableWorkbook;
	}

	public void setWritableWorkbook(WritableWorkbook writableWorkbook) {
		this.writableWorkbook = writableWorkbook;
	}

	public WritableSheet getWritableSheet() {
		return writableSheet;
	}

	public void setWritableSheet(WritableSheet writableSheet) {
		this.writableSheet = writableSheet;
	}
	
	public int getCurColumnNum() {
		return curColumnNum;
	}

	public void setCurColumnNum(int curColumnNum) {
		this.curColumnNum = curColumnNum;
	}

	public int getMaxColumnNum() {
		return maxColumnNum;
	}

	public void setMaxColumnNum(int maxColumnNum) {
		this.maxColumnNum = maxColumnNum;
	}

	public int getCurSheetNum() {
		return curSheetNum;
	}

	public void setCurSheetNum(int curSheetNum) {
		this.curSheetNum = curSheetNum;
	}
}
