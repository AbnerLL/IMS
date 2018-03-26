package net.sourceforge.jdbcimporter.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.MalformedDataException;

public class ExcelBinaryDelimiterParser extends CSVDelimiterParser {

	Workbook  workbook   = null;
	Sheet     curSheet      = null;
	int         curRow      = 0;
	InputStream curIn       = null;
	int         curMaxRow   = 0;
	
	String fileName = null;
	String[] ignoreRows=null;
	String[] columns=null;
	
	String sheet;
//	int[] columns = new int[0];
	
	public void finalize()
	{
		if ( workbook != null )
		{
			workbook.close();
			workbook = null;
			curSheet = null;
		}
	}
	

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public String getSheet() {
		return sheet;
	}



	public void setSheet(String sheet) {
		this.sheet = sheet;
	}



	public boolean checkIgnoreCurRow(int rowNum){
		if(ignoreRows==null&&ignoreRows.length==0){
			return false;
		}
		for(int i=0;i<ignoreRows.length;i++){
			if(rowNum==Integer.parseInt(ignoreRows[i])){
				return true;
			}
		}
		return false;
	}
	
	public String getNextRow() throws IOException 
	{
		if ( workbook==null&&fileName!=null)
		{
			try
			{
				curIn=new FileInputStream(fileName);
				workbook = Workbook.getWorkbook(curIn);
				curSheet = workbook.getSheet(sheet);
				curRow   = 0;
				curMaxRow = curSheet.getRows();
			}
			catch ( BiffException e )
			{
				throw new IOException( e.getMessage() );
			}
		}
		
		while(checkIgnoreCurRow(curRow)){
			curRow++;
		}
		
		if ( curRow >= curMaxRow )
		{
			return null;
		}
		
		boolean isNull = true;
		
		for ( int i = 0; i < columns.length; i++ ){
			int col = Integer.parseInt(columns[i]);
			if ( curSheet.getCell(col,curRow).getContents() != null ){
				isNull = false;
				break;
			}
		}
		
		if ( isNull ){
			return null;
		}
		Integer returnInteger = new Integer(curRow);
		curRow++;
		return returnInteger.toString();
	}

	public String getRowAsString(Object row) {
		Integer rowInt = (Integer) row;
		int nextRow = rowInt.intValue();
		StringBuffer buf = new StringBuffer("");
		ColumnValue[] vals = new ColumnValue[columns.length];
		for ( int i = 0; i < columns.length; i++ ){
			int col = Integer.parseInt(columns[i]);
			Cell cell = curSheet.getCell(col,nextRow);
			if ( i > 0 ){
				buf.append(",");
			}
			if ( cell.getContents() != null ){
				buf.append( cell.getContents() );
			}
		}
		return buf.toString();
	}

	public ColumnValue[] getValues(Object row) throws MalformedDataException 
	{
		Integer rowInt = (Integer) row;
		int nextRow = rowInt.intValue();
		ColumnValue[] vals = new ColumnValue[columns.length];
		for ( int i = 0; i < columns.length; i++ ){
			int col = Integer.parseInt(columns[i]);
			Cell cell = curSheet.getCell(col,nextRow);
			vals[i] = new ColumnValue();
			if (cell.getType() == CellType.LABEL){
				vals[i].setString( ((LabelCell) cell).getString() );
			}
			else if (cell.getType() == CellType.NUMBER){
			    vals[i].setObject( new Double( ((NumberCell) cell).getValue() ) );
			}
			else if (cell.getType() == CellType.DATE){
				vals[i].setObject( ((DateCell) cell).getDate() );
			}
		}
		return vals;
	}

	public void setInputStream(InputStream in) 
	{
		if ( workbook != null )
		{
			workbook.close();
			workbook = null;
			curSheet = null;
		}
		curIn = in;
	}

}
