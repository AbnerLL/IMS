package net.sourceforge.jdbcutils.formatter;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sourceforge.jdbcexporter.DelimiterFormatter;
import net.sourceforge.jdbcimporter.ColumnValue;

public class ExcelDelimiterFormatter implements DelimiterFormatter
{
	OutputStream outputStream;
	String outputFileName;
	int rowNum;
	List rowList;
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public String formatValues(ColumnValue[] values)
	{
		StringBuffer nextLine = new StringBuffer("");
		for ( int i = 0; i < values.length; i++ )
		{
			String val = values[i].getString();
					nextLine.append(' ');
		}
		return nextLine.toString();
	}

	public void writeNextRow(String row) throws IOException
	{
		rowNum++;
	}

	/**
	 * 输出excel
	 */
	public void finish() throws IOException
	{
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet wsQ = wwb.createSheet("sheet1", 0);
		// WritableSheet wsO = wwb.createSheet("Order Report", 1);
		//set column width for WritableSheet
		wsQ.setColumnView(0, 12);
		for (int i = 0; i < rowNum; i++) {//行从第四行开始 beginRow=3
			//get datas in rows
			ColumnValue[]rows=(ColumnValue[])rowList.get(i);
			for (int j = 0; j < rows.length; j++) {//列0
				// write Strings
				try {
					//j列  i+1行（第一个 0列 beginRow+1行 ）
					wsQ.addCell(new jxl.write.Label(j, i ,rows[j].getString()));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void setWriter(Writer output) {
		rowNum=0;
		try {
			outputStream = new FileOutputStream(outputFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
