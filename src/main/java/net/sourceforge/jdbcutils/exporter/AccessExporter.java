package net.sourceforge.jdbcutils.exporter;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.sourceforge.jdbcexporter.Exporter;
import net.sourceforge.jdbcutils.domain.ExpConfigXML;
import net.sourceforge.jdbcutils.domain.JdbcColumn;
import net.sourceforge.jdbcutils.domain.JdbcConnection;
import net.sourceforge.jdbcutils.domain.JdbcEntity;
import net.sourceforge.jdbcutils.domain.JdbcNode;
import net.sourceforge.jdbcutils.domain.JdbcProperty;
import net.sourceforge.jdbcutils.utils.StringUtils;

import org.xml.sax.InputSource;

import com.navinfo.config.DBConstant;

public class AccessExporter {
	
	public static final String XML_HEADER="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	String fileName;//excel文件绝对路径
	String columnDelimiter;//CSV列分隔符，一般都是逗号
	String enclosedDelimiter;
	String generatorPlugin="uuid";//列值自动生成插件
	String accessTable;
	String[] accessColumns;//导出对应列
	String[] accessColumnTypes;//列类型
	
	String[]databaseColumns;//导入应列
	String[]generatorColumns;//导入自动生成值对应列
	String[]databaseDefaultColumns;//导入默认值的对应列
	String[]databaseDefaultColumnValues;//导入默认值
	String databaseTable;//目标库表
	String whereClause;//查询条件
	String queryStr;//查询语句
	
	String exportengine="BasicEngine";//导出引擎
	String delimiterType="exp_access";
	
	String logFileName="/program/download/road/log";//记录导出LOG记录
			
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	public String getColumnDelimiter() {
		return columnDelimiter;
	}
	public void setColumnDelimiter(String columnDelimiter) {
		this.columnDelimiter = columnDelimiter;
	}
	public String getDelimiterType() {
		return delimiterType;
	}
	public void setDelimiterType(String delimiterType) {
		this.delimiterType = delimiterType;
	}
	
	public String[] getDatabaseColumns() {
		return databaseColumns;
	}
	public void setDatabaseColumns(String[] databaseColumns) {
		this.databaseColumns = databaseColumns;
	}
	public String[] getGeneratorColumns() {
		return generatorColumns;
	}
	public void setGeneratorColumns(String[] generatorColumns) {
		this.generatorColumns = generatorColumns;
	}
	public String[] getDatabaseDefaultColumns() {
		return databaseDefaultColumns;
	}
	public void setDatabaseDefaultColumns(String[] databaseDefaultColumns) {
		this.databaseDefaultColumns = databaseDefaultColumns;
	}
	public String[] getDatabaseDefaultColumnValues() {
		return databaseDefaultColumnValues;
	}
	public void setDatabaseDefaultColumnValues(String[] databaseDefaultColumnValues) {
		this.databaseDefaultColumnValues = databaseDefaultColumnValues;
	}
	
	public String getDatabaseTable() {
		return databaseTable;
	}
	public void setDatabaseTable(String databaseTable) {
		this.databaseTable = databaseTable;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLogFileName() {
		return logFileName;
	}
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}
	
	public String getEnclosedDelimiter() {
		return enclosedDelimiter;
	}
	public void setEnclosedDelimiter(String enclosedDelimiter) {
		this.enclosedDelimiter = enclosedDelimiter;
	}
	public String getExportengine() {
		return exportengine;
	}
	public void setExportengine(String exportengine) {
		this.exportengine = exportengine;
	}
	public String getGeneratorPlugin() {
		return generatorPlugin;
	}
	public void setGeneratorPlugin(String generatorPlugin) {
		this.generatorPlugin = generatorPlugin;
	}
	public String[] getAccessColumns() {
		return accessColumns;
	}
	public void setAccessColumns(String[] accessColumns) {
		this.accessColumns = accessColumns;
	}
	public String getAccessTable() {
		return accessTable;
	}
	public void setAccessTable(String accessTable) {
		this.accessTable = accessTable;
	}
	public String[] getAccessColumnTypes() {
		return accessColumnTypes;
	}
	public void setAccessColumnTypes(String[] accessColumnTypes) {
		this.accessColumnTypes = accessColumnTypes;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	/**
	 * 获得数据库连接对象
	 * 
	 * @return
	 */
	public JdbcConnection initConnection(){
		JdbcConnection jdbcConnection=new JdbcConnection();
		List propertyList=new ArrayList();
		//集成使用navwork数据源
//		expConnection.setType(ExpConnection.TYPE_SPRING);
//		ExpProperty expProperty=new ExpProperty();
//		expProperty.setName("dataSourceBeanID");
//		expProperty.setValue("myDataSource");
//		expPropertyList.add(expProperty);
		
		//普通配置用于测试
		jdbcConnection.setType(JdbcConnection.TYPE_JDBC);
		JdbcProperty property1=new JdbcProperty();
		property1.setName("username");
		property1.setValue(DBConstant.IQU_DATABASE_USER);
		JdbcProperty property2=new JdbcProperty();
		property2.setName("url");
		property2.setValue(DBConstant.IQU_DATABASE_URL);
		JdbcProperty property3=new JdbcProperty();
		property3.setName("password");
		property3.setValue(DBConstant.IQU_DATABASE_PASSWORD);
		JdbcProperty property4=new JdbcProperty();
		property4.setName("driver");
		property4.setValue(DBConstant.DATABASE_DRIVER);
		propertyList.add(property1);
		propertyList.add(property2);
		propertyList.add(property3);
		propertyList.add(property4);
		
		jdbcConnection.setProperty(propertyList);
		return jdbcConnection;
		
	}
	
	/**
	 * 生成导入配置文件.xml
	 * 
	 * @return
	 */
	public ExpConfigXML init(){
		ExpConfigXML expConfigXML=new ExpConfigXML();
		expConfigXML.setLog(logFileName);
		JdbcConnection jdbcConnection=initConnection();	
		JdbcEntity jdbcEntity=new JdbcEntity();
		JdbcNode jdbcNode=new JdbcNode();
		List propertyList=new ArrayList();
		jdbcNode.setType(delimiterType);
		
		JdbcProperty property1=new JdbcProperty();
		property1.setName("fileName");
		property1.setValue(fileName);
		
		JdbcProperty property2=new JdbcProperty();
		property2.setName("tableName");
		property2.setValue(accessTable);
		
		JdbcProperty property3=new JdbcProperty();
		property3.setName("columnNames");
		property3.setValue(StringUtils.arrayToCommaDelimitedString(accessColumns));
		
		JdbcProperty property4=new JdbcProperty();
		property4.setName("columnTypes");
		property4.setValue(StringUtils.arrayToCommaDelimitedString(accessColumnTypes));
		
		JdbcProperty property5=new JdbcProperty();
		property5.setName("columnDelimiter");
		property5.setValue(columnDelimiter);		
				
		propertyList.add(property1);
		propertyList.add(property2);
		propertyList.add(property3);
		propertyList.add(property4);
		propertyList.add(property5);
		
		jdbcNode.setProperty(propertyList);
		
		List columnList=new ArrayList();
		if(databaseColumns!=null&&databaseColumns.length>0){
			for(int i=0;i<databaseColumns.length;i++){
				JdbcColumn column=new JdbcColumn();
				column.setName(databaseColumns[i]);
				columnList.add(column);
			}
		}
		//设置默认值列
		if(databaseDefaultColumns!=null&&databaseDefaultColumns.length>0){
			for(int i=0;i<databaseDefaultColumns.length;i++){
				JdbcColumn column=new JdbcColumn();
				column.setName(databaseDefaultColumns[i]);
				column.setGenerate("true");
				JdbcNode generator=new JdbcNode();
				generator.setType("generator_defaultValue");
				JdbcProperty property=new JdbcProperty();
				property.setName("value");
				property.setValue(databaseDefaultColumnValues[i]);
				List<JdbcProperty> propertyList2=new ArrayList();
				propertyList2.add(property);
				generator.setProperty(propertyList2);
				column.setGenerator(generator);
				columnList.add(column);
			}
		}
		//设置动态生成列
		if(generatorColumns!=null&&generatorColumns.length>0){
			for(int i=0;i<generatorColumns.length;i++){
				JdbcColumn column=new JdbcColumn();
				column.setName(generatorColumns[i]);
				column.setGenerate("true");
				JdbcNode generator=new JdbcNode();
				generator.setType(generatorPlugin);
				column.setGenerator(generator);
				columnList.add(column);
			}
		}
		
		jdbcEntity.setTable(databaseTable);
		jdbcEntity.setWhereClause(whereClause);
		jdbcEntity.setQueryStr(queryStr);
		jdbcEntity.setTarget(fileName);
		jdbcEntity.setColumn(columnList);
		jdbcEntity.setDelimiter(jdbcNode);
		expConfigXML.setConnection(jdbcConnection);
		expConfigXML.setEntity(jdbcEntity);
		
		return expConfigXML;
	}
	
	/**
	 * 生成导入配置文件export.xml
	 * 
	 * @param str
	 * @return
	 */
	public boolean generatorConfigXMLFile(String str){
		String config_file="/program/download/road/log/export"+new Date().getTime()+".xml";
//		String config_file="D:/accessExport.xml";
		try {
			FileOutputStream file = new FileOutputStream(config_file);
			try {
				file.write(str.getBytes("UTF-8"));
				file.close();
				return true;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * beginExport
	 * @return
	 */
	public void beginExport(){
		long a=System.currentTimeMillis();
		try {
			Exporter exporter = init(null);
			InputStream fileInput = null;
			try {
				
				String export_xml=XML_HEADER+init().toString();
				fileInput = new ByteArrayInputStream(export_xml.getBytes("UTF-8"));
				
				generatorConfigXMLFile(export_xml);
				
				InputSource inputSource = new InputSource(fileInput);

				exporter.initExportDef(inputSource);// export.xml文件初始化
				
				if(exportengine.equals("BasicEngine")){
					exporter.setEngine(System
							.getProperties()
							.getProperty("jdbcexporter.engine",
									"net.sourceforge.jdbcexporter.engine.BasicEngine"));
				}
				exporter.beginExport();
				System.out.println("总共耗时:"+(System.currentTimeMillis()-a)/1000.0+"秒");
			} finally {
				if (fileInput != null) {
					try {
						fileInput.close();
					} catch (IOException e) {

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Exporter init(Exporter exporter){
		if(exporter==null){
			exporter = new Exporter();
		}
		Properties pluginProperties = new Properties();
		pluginProperties.put(Exporter.DELIMITER_PREFIX + "exp_access",
						"net.sourceforge.jdbcexporter.parser.AccessDelimiterParser");
		exporter.setEngine(System.getProperties().getProperty("jdbcexporter.engine",
						"net.sourceforge.jdbcexporter.engine.BasicEngine"));
		exporter.initPlugins(pluginProperties);
		return exporter;
	}
	
	
	public static void main(String[] args) {
    	String[] accessColumns={"check_result","idcode","original_id","task_code","task_name","name","address","telephone","pre_admin_code","pre_city","pre_district","admin_flag","admin_code","admin_name","kind_flag","kind_code","kind_name","poi_num","poi_name","poi_address","poi_telephone","poi_kindName","poi_adminName","poi_lon","poi_lat","diff_result","diff_scores","diff_distance","diff_desc","englishname","aliasname","old_name","fax","post_code","star_level","label","food_series","foodtype","fatherson","active_attr","lon","lat","lonlat_series","lonlat_source","geo_lon","geo_lat","geo_source","geo_accuracy","guide_lon","guide_lat","guide_lonlat_source","source_code","source_name","source_url","del_flag","create_time"};	
		String[] accessColumnTypes={"12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12","12"};
		String[] databaseColumns={"check_result","idcode","original_id","task_code","task_name","name","address","telephone","pre_admin_code","pre_city","pre_district","admin_flag","admin_code","admin_name","kind_flag","kind_code","kind_name","poi_num","poi_name","poi_address","poi_telephone","poi_kindName","poi_adminName","poi_lon","poi_lat","diff_result","diff_scores","diff_distance","diff_desc","englishname","aliasname","old_name","fax","post_code","star_level","label","food_series","foodtype","fatherson","active_attr","lon","lat","lonlat_series","lonlat_source","geo_lon","geo_lat","geo_source","geo_accuracy","guide_lon","guide_lat","guide_lonlat_source","source_code","source_name","source_url","del_flag","create_time"};
		String dataBaseTable="pdf_data";
//		String whereClause=" task_code='201412050018' and del_flag is null";
		String whereClause=" 1=1";
//		String queryStr="SELECT a.idcode, a.original_id, a.task_code, a.task_name, a.name, a.address, a.telephone, a.admin_code, a.admin_name, a.kind_code, a.kind_name, a.pdf_poi_id as poi_num,b.name as poi_name,b.address as poi_address,b.telephone as poi_telephone,b.kind_name as poi_kindName,b.admin_name as poi_adminName,b.lon as poi_lon,b.lat as poi_lat,a.diff_result, a.diff_scores, a.diff_distance, a.diff_desc, a.englishname, a.aliasname, a.old_name, a.fax, a.post_code, a.star_level, a.label, a.food_series, a.foodtype, a.fatherson, a.active_attr, a.lon, a.lat, a.lonlat_series, a.lonlat_source, a.geo_lon, a.geo_lat, a.geo_source, a.geo_accuracy, a.guide_lon, a.guide_lat, a.guide_lonlat_source, a.source_code, a.source_name, a.source_url FROM pdf_data a left join pdf_poi b on a.pdf_poi_id=b.pdf_poi_id where a.task_code='201412050018' and a.del_flag is null";
//		String columnDelimiter=",";
		String queryStr="SELECT a.check_result,a.idcode, a.original_id, a.task_code, a.task_name, a.name, a.address, a.telephone, a.pre_admin_code, a.pre_city, a.pre_district, a.admin_flag, a.admin_code, a.admin_name, a.kind_flag, a.kind_code, a.kind_name, a.pdf_poi_id as poi_num,b.name as poi_name,b.address as poi_address,b.telephone as poi_telephone,b.kind_name as poi_kindName,b.admin_name as poi_adminName,b.lon as poi_lon,b.lat as poi_lat,a.diff_result, a.diff_scores, a.diff_distance, a.diff_desc, a.englishname, a.aliasname, a.old_name, a.fax, a.post_code, a.star_level, a.label, a.food_series, a.foodtype, a.fatherson, a.active_attr, a.lon, a.lat, a.lonlat_series, a.lonlat_source, a.geo_lon, a.geo_lat, a.geo_source, a.geo_accuracy, a.guide_lon, a.guide_lat, a.guide_lonlat_source, a.source_code, a.source_name, a.source_url, a.del_flag,a.create_time FROM pdf_data a left join pdf_poi b on a.pdf_poi_id=b.pdf_poi_id where a.original_id='943da0a2-574a-492e-9129-38b609aa39e3'";
		String columnDelimiter=";,;";
		String delimiterType="exp_access";
		String[] databaseDefaultColumns={""};
		String[] databaseDefaultColumnValues={""};
				
		AccessExporter accessExporter = new AccessExporter();
		accessExporter.setDelimiterType(delimiterType);
		accessExporter.setFileName("D:/test_exp.accdb");
		accessExporter.setAccessTable("pdfData");
		accessExporter.setAccessColumns(accessColumns);
		accessExporter.setAccessColumnTypes(accessColumnTypes);
		accessExporter.setColumnDelimiter(columnDelimiter);
		accessExporter.setDatabaseTable(dataBaseTable);
		accessExporter.setWhereClause(whereClause);
		accessExporter.setQueryStr(queryStr);
		accessExporter.setDatabaseColumns(databaseColumns);
//		accessExporter.setDatabaseDefaultColumns(databaseDefaultColumns);
//		accessExporter.setDatabaseDefaultColumnValues(databaseDefaultColumnValues);
		
		accessExporter.beginExport();
		
//		String[] a=",f54b3faf-9cfe-47b2-87d2-549618100aa5,201412050007,糯米差分,爆浆鸡排,上海市杨浦区平凉路１８５０号,１８７２１５０１７３２,310110,杨浦区,,美食|小吃快餐,0021141020QQB04660,,Perfect,458.2,,,,,,,,,,3009,,,121.543973,31.268639,百度地图,,,,,,,,,BDNM20141121_1,糯米,http://sh.nuomi.com/deal/hojmqo7y.html".split(",");
//		System.out.println(accessColumns.length);
//		System.out.println(a.length);
		
		
		
		
		
		
		
		
		
//		long a=System.currentTimeMillis();
//		try {
//			AccessExporter accessExporter = new AccessExporter();
//			Exporter exporter = accessExporter.init(null);
//			InputStream fileInput = null;
//			try {
//				
//				String export_xml=IOUtils.readToString("D:\\export1420681768301.xml");
//				fileInput = new ByteArrayInputStream(export_xml.getBytes("UTF-8"));
//				
////				generatorConfigXMLFile(export_xml);
//				
//				InputSource inputSource = new InputSource(fileInput);
//
//				exporter.initExportDef(inputSource);// export.xml文件初始化
//				
//				if(accessExporter.exportengine.equals("BasicEngine")){
//					exporter.setEngine(System
//							.getProperties()
//							.getProperty("jdbcexporter.engine",
//									"net.sourceforge.jdbcexporter.engine.BasicEngine"));
//				}
//				exporter.beginExport();
//				System.out.println("总共耗时:"+(System.currentTimeMillis()-a)/1000.0+"秒");
//			} finally {
//				if (fileInput != null) {
//					try {
//						fileInput.close();
//					} catch (IOException e) {
//
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}


}
