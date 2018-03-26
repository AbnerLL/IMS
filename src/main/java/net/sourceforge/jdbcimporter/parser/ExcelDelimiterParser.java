package net.sourceforge.jdbcimporter.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.XFRecord;
import jxl.read.biff.BiffException;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.MalformedDataException;
import net.sourceforge.jdbcutils.utils.DateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;


public class ExcelDelimiterParser extends CSVDelimiterParser {
	protected static Log LOG = LogFactory.getLog( ExcelDelimiterParser.class );
	protected Workbook workbook ;
	protected InputStream inputStream ;
	protected int curRowNum;
	String fileName = null;
	String sheet=null;
	String[] ignoreRows=null;
	String[] columns =null;
		
	public String[] getIgnoreRows() {
		return ignoreRows;
	}

	public void setIgnoreRows(String[] ignoreRows) {
		this.ignoreRows = ignoreRows;
	}

	public int getCurRowNum() {
		return curRowNum;
	}

	public void setCurRowNum(int curRowNum) {
		this.curRowNum = curRowNum;
	}

	/**
	 * Constructor for ExcelDelimiterParser.
	 */
	public ExcelDelimiterParser() {
		super();
		if(columnDelimiter==null){
			columnDelimiter="|*|";
		}
	}

	public void setInputStream(InputStream input)
	{
		inputStream=input;
		try {			
			WorkbookSettings setting=new WorkbookSettings();   
			java.util.Locale locale=new java.util.Locale("zh","CN");   
			setting.setLocale(locale);
			workbook = Workbook.getWorkbook(inputStream,setting);
			this.setCurRowNum(0);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getNextRow()
	 */
	public String getNextRow() throws IOException {
		
		if(workbook==null&&fileName!=null){
			try {
				this.setInputStream(new FileInputStream(fileName));
				LOG.debug("init excel workbook.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		while(checkIgnoreCurRow(curRowNum)){
			curRowNum++;
		}
		
		if(curRowNum>=workbook.getSheet(Integer.parseInt(sheet)).getRows()){
			this.workbook.close();
			this.getInputStream().close();
			return null;
		}
		
		StringBuffer sb=new StringBuffer();
		boolean isEmptyRow=true;
		for (int j = 0; j < columns.length; j++) {
			int i=Integer.parseInt(columns[j]);
			if(i<workbook.getSheet(Integer.parseInt(sheet)).getRow(curRowNum).length){
				Cell cell=workbook.getSheet(Integer.parseInt(sheet)).getRow(curRowNum)[i];
//				System.out.println(i);
				if(cell.getContents()!=null&&!cell.getContents().trim().equals("")){
					isEmptyRow=false;
				}
				if(LOG.isDebugEnabled()){
					LOG.debug(i +"列"+cell.getType() );
				}
				 if(cell.getType()   ==   CellType.DATE)   
				  {   
					 DateCell   datec00   =   (DateCell)cell;   
				  	 Date   dt   =   datec00.getDate();   
//				  SimpleDateFormat   formatter   =   new   SimpleDateFormat("yy-MM-dd   HH:mm:ss");   
//				  String   sd   =   formatter.format(dt);   
				  	sb.append(DateUtil.getString(dt));
				  	if(LOG.isDebugEnabled()){
				  		LOG.debug(i +"列 日期"+cell.getContents()+":"+DateUtil.getString(dt));
				  	}
				  }else if (cell.getType() == CellType.NUMBER) { 
					    NumberCell nc = (NumberCell) cell; 
					    XFRecord xfr = (XFRecord) nc.getCellFormat(); 
					    final int INDEX_OF_DATE = 58; 
					    if(xfr.formatIndex == INDEX_OF_DATE) { 
					    	Date date = HSSFDateUtil.getJavaDate(nc.getValue()); 
					    	sb.append(DateUtil.getString(date));
					    }else{
					    	sb.append(cell.getContents());
					    }
				  }else{
					  sb.append(cell.getContents());
				  }   

				
			}else{
				sb.append("");
			}
//			if(i<workbook.getSheet(0).getColumns()-1){
//				sb.append(columnDelimiter);
//			}
			if(j < columns.length-1){
				sb.append(columnDelimiter);
			}
		}
		curRowNum++;
		if(isEmptyRow){//只要读到空行就结束
			curRowNum = workbook.getSheet(Integer.parseInt(sheet)).getRows();
		}
//		System.out.print(sb.toString()+" excel："+workbook.getSheet(Integer.parseInt(sheet)).getColumns());
		
		return sb.toString();

	}

	/**
	 * @see net.sourceforge.jdbcimporter.DelimiterParser#getValues(String)
	 */
	public ColumnValue[] getValues(String nextRow)
		throws MalformedDataException 
	{
		return parseColumnDelimitedLine(nextRow);
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
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
	
	public void finalize()
	{
		if ( workbook != null )
		{
			workbook.close();
			workbook = null;
			sheet = null;
		}
	}

}
