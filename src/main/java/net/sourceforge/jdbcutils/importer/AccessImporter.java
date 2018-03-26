package net.sourceforge.jdbcutils.importer;

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

import net.sourceforge.jdbcimporter.Importer;
import net.sourceforge.jdbcutils.domain.ImpConfigXML;
import net.sourceforge.jdbcutils.domain.JdbcColumn;
import net.sourceforge.jdbcutils.domain.JdbcConnection;
import net.sourceforge.jdbcutils.domain.JdbcEntity;
import net.sourceforge.jdbcutils.domain.JdbcNode;
import net.sourceforge.jdbcutils.domain.JdbcProperty;
import net.sourceforge.jdbcutils.utils.StringUtils;

import org.xml.sax.InputSource;

import com.navinfo.config.DBConstant;

/**
 * @author liubinsheng
 */
public class AccessImporter {
	
	String[]accessColumns;//导出对应列
	String[]databaseColumns;//导入对应列
	String[]generatorColumns;//导入自动生成值对应列
	String[]databaseDefaultColumns;//导入默认值的对应列
	String[]databaseDefaultColumnValues;//导入默认值
	String accessTable;//导出库表
	String databaseTable;//导入库表
	String fileName;//Access文件绝对路径
	String importengine="BasicEngine";//导入引擎
	String generatorPlugin="uuid";//列值自动生成插件
	
	String badFileName="E:/app/log/bad.log";//导入过程中，将报错内容填写到这个文件当中
	String logFileName="E:/app/log/log.log";//记录导入LOG记录
	String logUrl;
	
	//CLOB字段名
	public static String clobField="INFO_CONTENT;MAP_CODE;DATA_CONTENT;";
		
	public String[] getAccessColumns() {
		return accessColumns;
	}

	public void setAccessColumns(String[] accessColumns) {
		this.accessColumns = accessColumns;
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

	public String getLogUrl() {
		return logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	public String[] getDatabaseDefaultColumnValues() {
		return databaseDefaultColumnValues;
	}

	public void setDatabaseDefaultColumnValues(String[] databaseDefaultColumnValues) {
		this.databaseDefaultColumnValues = databaseDefaultColumnValues;
	}

	public String getAccessTable() {
		return accessTable;
	}

	public void setAccessTable(String accessTable) {
		this.accessTable = accessTable;
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

	public String getImportengine() {
		return importengine;
	}

	public void setImportengine(String importengine) {
		this.importengine = importengine;
	}

	public String getGeneratorPlugin() {
		return generatorPlugin;
	}

	public void setGeneratorPlugin(String generatorPlugin) {
		this.generatorPlugin = generatorPlugin;
	}

	public String getBadFileName() {
		return badFileName;
	}

	public void setBadFileName(String badFileName) {
		this.badFileName = badFileName;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
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
//		jdbcConnection.setType(JdbcConnection.TYPE_SPRING);
//		JdbcProperty impProperty=new JdbcProperty();
//		impProperty.setName("dataSourceBeanID");
//		impProperty.setValue("myDataSource");
//		propertyList.add(impProperty);
		
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
	public ImpConfigXML init(){
		ImpConfigXML impConfigXML=new ImpConfigXML();
		impConfigXML.setBad(badFileName);
		impConfigXML.setLog(logFileName);
		JdbcConnection jdbcConnection=initConnection();	
		JdbcEntity jdbcEntity=new JdbcEntity();
		JdbcNode jdbcNode=new JdbcNode();
		List propertyList=new ArrayList();
		jdbcNode.setType("imp_access");
		
		JdbcProperty property1=new JdbcProperty();
		property1.setName("fileName");
		property1.setValue(fileName);
		
		JdbcProperty property2=new JdbcProperty();
		property2.setName("table");
		property2.setValue(accessTable);
		
		JdbcProperty property3=new JdbcProperty();
		property3.setName("columnNames");
		property3.setValue(StringUtils.arrayToCommaDelimitedString(accessColumns));
		
		propertyList.add(property1);
		propertyList.add(property2);
		propertyList.add(property3);
		
		jdbcNode.setProperty(propertyList);
		
		List columnList=new ArrayList();
		if(databaseColumns!=null&&databaseColumns.length>0){
			for(int i=0;i<databaseColumns.length;i++){
				JdbcColumn column=new JdbcColumn();
				column.setName(databaseColumns[i]);
				
//				if(databaseColumns[i].indexOf("TIME")!=-1&&!databaseColumns[i].equals("NEWS_TIME")){
//					column.setSQLType("TIMESTAMP");
//					JdbcNode translator=new JdbcNode();
//					translator.setType("navwork_datetime");
//					column.setTranslator(translator);
//				}else if(clobField.indexOf(databaseColumns[i]+";")!=-1){
//					column.setSQLType("VARCHAR");
//					JdbcNode translator=new JdbcNode();
//					translator.setType("navwork_clob");
//					column.setTranslator(translator);
//				}
				
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
				JdbcProperty jdbcProperty=new JdbcProperty();
				jdbcProperty.setName("value");
				jdbcProperty.setValue(databaseDefaultColumnValues[i]);
				List<JdbcProperty> propertyList2=new ArrayList();
				propertyList2.add(jdbcProperty);
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
		jdbcEntity.setSource(fileName);
		jdbcEntity.setColumn(columnList);
		jdbcEntity.setDelimiter(jdbcNode);
		impConfigXML.setConnection(jdbcConnection);
		impConfigXML.setEntity(jdbcEntity);
		
		return impConfigXML;
	}
	
	/**
	 * 生成导入配置文件import.xml
	 * 
	 * @param str
	 * @return
	 */
	public boolean generatorConfigXMLFile(String str){
		String config_file;
		if(StringUtils.hasText(logUrl)){
			config_file=logUrl+"import"+new Date().getTime()+".xml";
		}else{
			config_file="E:/app/log/import"+new Date().getTime()+".xml";
		}
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
	 * beginImport
	 * @return
	 */
	public boolean beginImport(){
		long a=System.currentTimeMillis();
		try {
			Importer importer = init(null);
			InputStream fileInput = null;
			try {
				
				String import_xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+init().toString();
				fileInput = new ByteArrayInputStream(import_xml.getBytes("UTF-8"));
				
				generatorConfigXMLFile(import_xml);
				
				InputSource inputSource = new InputSource(fileInput);

				importer.initImportDef(inputSource);// import.xml文件初始化
				
				if(importengine.equals("BasicEngine")){
					importer.setEngine(System
							.getProperties()
							.getProperty("jdbcimporter.engine",
									"net.sourceforge.jdbcimporter.engine.BasicEngine"));
				}else{
					importer.setEngine(System
							.getProperties()
							.getProperty("jdbcimporter.engine",
									"net.sourceforge.jdbcimporter.engine.UpdateEngine"));
				}
				importer.setFailonerror(true);
				importer.getImportDef().setCommitCount(50);
				importer.getImportDef().setBatchCount(50);
				importer.beginImport();
				System.out.print((System.currentTimeMillis()-a)/1000.0);
			} finally {
				if (fileInput != null) {
					try {
						fileInput.close();
					} catch (IOException e) {

					}
				}
			}
			if (importer.hasImportFailed()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public Importer init(Importer importer){
		if(importer==null){
			importer = new Importer();
		}
		Properties pluginProperties = new Properties();
		pluginProperties
		.put(Importer.ROWTRANSLATOR_PREFIX + "imp_skipRows",
						"net.sourceforge.jdbcimporter.rowtranslator.SkipRowsRowTranslator");

		pluginProperties
				.put(Importer.CONNECTION_PREFIX + "spring_conn",
						"net.sourceforge.jdbcimporter.connection.SpringConnectionDef");
		pluginProperties
				.put(Importer.DELIMITER_PREFIX + "imp_excel",
						"net.sourceforge.jdbcimporter.parser.ExcelDelimiterParser");
		pluginProperties
		.put(Importer.DELIMITER_PREFIX + "imp_access",
				"net.sourceforge.jdbcimporter.parser.AccessBinaryDelimiterParser");
		pluginProperties
				.put(Importer.COLUMNTRANSLATOR_PREFIX + "navwork_systemtime",
						"net.sourceforge.jdbcimporter.columntranslator.SystemTimeColumnTranslator");
		pluginProperties
				.put(Importer.COLUMNTRANSLATOR_PREFIX + "navwork_datetime",
						"net.sourceforge.jdbcimporter.columntranslator.DateTimeColumnTranslator");
		pluginProperties
				.put(Importer.COLUMNTRANSLATOR_PREFIX + "navwork_clob",
						"net.sourceforge.jdbcimporter.columntranslator.ClobColumnTranslator");
		pluginProperties
				.put(Importer.COLUMNTRANSLATOR_PREFIX + "navwork_lookup",
						"net.sourceforge.jdbcimporter.columntranslator.DbLookupTableColumnTranslator");
		pluginProperties
		.put(Importer.GENERATOR_PREFIX+"generator_defaultValue",
				"net.sourceforge.datagenerator.generator.FixedValueGenerator");
		pluginProperties
		.put(Importer.GENERATOR_PREFIX+"uuid",
				"net.sourceforge.datagenerator.generator.UUIDGenerator");
		importer
		.setEngine(System
				.getProperties()
				.getProperty("jdbcimporter.engine",
						"net.sourceforge.jdbcimporter.engine.UpdateEngine"));
		importer.setFailonerror("true".equals(System.getProperties()
		.getProperty("jdbcimporter.failonerror")));
		importer.initPlugins(pluginProperties);
		return importer;
	}
	
	
	public static void main(String[] args) {

		//关联导入开始
		String[] accessColumns={"姓名","性别","年龄","学号","地址","创建时间"};
		String[] databaseColumns={"name","sex","age","code","address","create_time"};
		String databaseTable="test_import";
		String[] generatorColumns={};
		String[] databaseDefaultColumns={};
		String[] databaseDefaultColumnValues={};
				
		AccessImporter accessImporter = new AccessImporter();
		accessImporter.setFileName("C:\\Users\\Administrator\\Desktop\\测试数据.accdb");
		accessImporter.setAccessTable("sheet1");
		accessImporter.setAccessColumns(accessColumns);
		accessImporter.setDatabaseTable(databaseTable);
		accessImporter.setDatabaseColumns(databaseColumns);
		accessImporter.setGeneratorColumns(generatorColumns);
		accessImporter.setDatabaseDefaultColumns(databaseDefaultColumns);
		accessImporter.setDatabaseDefaultColumnValues(databaseDefaultColumnValues);
		accessImporter.beginImport();
	}

}
