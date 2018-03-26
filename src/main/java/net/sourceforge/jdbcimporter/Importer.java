package net.sourceforge.jdbcimporter;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sourceforge.datagenerator.ColumnValueGenerator;
import net.sourceforge.jdbcimporter.config.ImportConfig;
import net.sourceforge.jdbcimporter.event.ImportEntityEvent;
import net.sourceforge.jdbcimporter.event.ImportEvent;
import net.sourceforge.jdbcimporter.event.ImportListener;
import net.sourceforge.jdbcimporter.event.LogImportListener;
import net.sourceforge.jdbcimporter.util.CustomObjectFactory;
import net.sourceforge.jdbcimporter.util.CustomObjectMapping;
import net.sourceforge.jdbcimporter.util.InvalidCustomObjectDefException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The Importer class is the main class that controls the import of data to the
 * database.
 * 
 * @version 0.6
 * @author Chris Nagy
 */
public class Importer {
	/**
	 * The log for debug information.
	 */
	protected static Log LOG = LogFactory.getLog(Importer.class);

	/**
	 * Prefix for the connection definition type to class in the custom
	 * properties.
	 */
	public static final String CONNECTION_PREFIX = "connection.";

	/**
	 * Prefix for the delimiter parser type to class in the custom properties.
	 */
	public static final String DELIMITER_PREFIX = "delimiter.";

	/**
	 * Prefix for the column translator type to class in the custom properties.
	 */
	public static final String COLUMNTRANSLATOR_PREFIX = "columntranslator.";

	/**
	 * Prefix for the column value generator classes to use.
	 */
	public static final String GENERATOR_PREFIX = "generator.";

	/**
	 * Prefix for the row translator type to class in the custom properties.
	 * 
	 * @since 0.72
	 */
	public static final String ROWTRANSLATOR_PREFIX = "rowtranslator.";
	/**
	 * Custom object factory used to create connection definitions.
	 */
	protected CustomObjectFactory connectionDefFactory;

	/**
	 * Custom object factory used to create delimiter parsers.
	 */
	protected CustomObjectFactory delimiterParserFactory;

	/**
	 * Custom object factory used to create binary delimiter parsers.
	 * 
	 * @since 0.74
	 */
	protected CustomObjectFactory binaryDelimiterParserFactory;

	/**
	 * Custom object factory used to create column translators.
	 */
	protected CustomObjectFactory columnTranslatorFactory;

	/**
	 * Custom object factory used to create column generators.
	 */
	protected CustomObjectFactory columnValueGeneratorFactory;

	/**
	 * Custom object factory used to create row translators.
	 * 
	 * @since 0.72
	 */
	protected CustomObjectFactory rowTranslatorFactory;

	/**
	 * The import definition.
	 */
	protected ImportDef importDef;

	/**
	 * The import engine classname.
	 */
	protected String engineClassname;

	/**
	 * The list of import listeners.
	 */
	protected List importListeners = new ArrayList();

	/**
	 * Flag indicating that the import should stop on the first error (default
	 * is false).
	 */
	protected boolean failOnError = false;

	/**
	 * Flag indicating that the import failed and the fail on error flag is set
	 * to true.
	 */
	protected boolean importFailed = false;

	/**
	 * Constructs a new importer.
	 */
	public Importer() {
		connectionDefFactory = new CustomObjectFactory(ConnectionDef.class);
		{
			Map connectionDefMap = CustomObjectMapping
					.getDefaultConnectionDefMapping();
			Iterator keys = connectionDefMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = (String) connectionDefMap.get(key);
				LOG.debug("Adding ConnectionDef mapping '" + key + "' = '"
						+ value + "'");
				connectionDefFactory.addMapping(key, value);
			}
		}

		delimiterParserFactory = new CustomObjectFactory(DelimiterParser.class);
		{
			Map delimiterParserMap = CustomObjectMapping
					.getDefaultDelimiterParserMapping();
			Iterator keys = delimiterParserMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = (String) delimiterParserMap.get(key);
				LOG.debug("Adding DelimiterParser mapping '" + key + "' = '"
						+ value + "'");
				delimiterParserFactory.addMapping(key, value);
			}
		}

		binaryDelimiterParserFactory = new CustomObjectFactory(
				BinaryDelimiterParser.class);

		columnTranslatorFactory = new CustomObjectFactory(
				ColumnTranslator.class);
		{
			Map columnTranslatorMap = CustomObjectMapping
					.getDefaultColumnTranslatorMapping();
			Iterator keys = columnTranslatorMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = (String) columnTranslatorMap.get(key);
				LOG.debug("Adding ColumnTranslator mapping '" + key + "' = '"
						+ value + "'");
				columnTranslatorFactory.addMapping(key, value);
			}
		}

		columnValueGeneratorFactory = new CustomObjectFactory(
				ColumnValueGenerator.class);
		{
			Map columnGeneratorMap = CustomObjectMapping
					.getDefaultColumnValueGeneratorMapping();
			Iterator keys = columnGeneratorMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = (String) columnGeneratorMap.get(key);
				LOG.debug("Adding ColumnValueGenerator mapping '" + key
						+ "' = '" + value + "'");
				columnValueGeneratorFactory.addMapping(key, value);
			}
		}

		rowTranslatorFactory = new CustomObjectFactory(RowTranslator.class);
		{
			Map rowTranslatorMap = CustomObjectMapping
					.getDefaultRowTranslatorMapping();
			Iterator keys = rowTranslatorMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = (String) rowTranslatorMap.get(key);
				LOG.debug("Adding RowTranslator mapping '" + key + "' = '"
						+ value + "'");
				rowTranslatorFactory.addMapping(key, value);
			}

		}
	}

	/**
	 * Sets the ImportDef.
	 * 
	 * @param importDef
	 *            import def
	 */
	public void setImportDef(ImportDef importDef) {
		this.importDef = importDef;
	}

	/**
	 * Sets a flag to indicate that the import should stop after the first
	 * error.
	 * 
	 * @param flag
	 *            flag
	 * @since 0.69
	 */
	public void setFailonerror(boolean flag) {
		this.failOnError = flag;
	}

	/**
	 * Returns the flag indicating that the import failed.
	 * 
	 * @return if true then the import failed
	 * @since 0.69
	 */
	public boolean hasImportFailed() {
		return this.importFailed;
	}

	/**
	 * Read and parse the import config file into an ImportDef class.
	 * 
	 * @param input
	 *            import config as xml
	 * @throws IOException
	 *             if the import config cannot be read
	 */
	public void initImportDef(InputSource input) throws IOException,
			ParserConfigurationException, SAXException, DOMException,
			InvalidCustomObjectDefException {
		LOG.trace("initImportDef( <InputSource> ) ->");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(input);
		ImportConfig config = new ImportConfig();
		config.setDelimiterParserFactory(delimiterParserFactory);
		config.setBinaryDelimiterParserFactory(binaryDelimiterParserFactory);
		config.setConnectionDefFactory(connectionDefFactory);
		config.setColumnTranslatorFactory(columnTranslatorFactory);
		config.setColumnValueGeneratorFactory(columnValueGeneratorFactory);
		config.setRowTranslatorFactory(rowTranslatorFactory);
		importDef = config.getImport(doc.getFirstChild());
		if (LOG.isDebugEnabled())
			LOG.debug("Loaded ImportDef : " + importDef.toString());
	}

	/**
	 * Returns the ImportDef.
	 * 
	 * @return ImportDef
	 */
	public ImportDef getImportDef() {
		return importDef;
	}

	/**
	 * Parse the plugin property file and adds the external connection
	 * definition config classes and delimiter parser config classes to the
	 * appropriate factories.
	 * 
	 * @param pluginFile
	 *            plugin property file
	 * @throws FileNotFoundException
	 *             if the plugin file cannot be found
	 * @throws IOException
	 *             if the plugin file cannot be opened and read
	 */
	public void initPlugins(String pluginFile) throws FileNotFoundException,
			IOException {
		LOG.trace("initPlugins( '" + pluginFile + "') ->");
		System.out.println("initPlugins( '" + pluginFile + "') ->");
		Properties plugins = new Properties();
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(pluginFile);
			plugins.load(fileInput);
			initPlugins(plugins);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					LOG.warn("Could not close file '" + pluginFile + "'", e);
				}
			}
			LOG.trace("initPlugins( '" + pluginFile + "') <-");
		}
	}

	/**
	 * Initialize the plugin mappings.
	 * 
	 * @param pluginProperties
	 *            the defined plugin mappings
	 */
	public void initPlugins(Properties pluginProperties) {
		LOG.trace("initPlugins( <Properties:" + pluginProperties.size()
				+ "> ) ->");
		Enumeration keys = pluginProperties.keys();
		while (keys.hasMoreElements()) {
			String nextKey = (String) keys.nextElement();
			if (nextKey.startsWith(CONNECTION_PREFIX)) {
				String connectionType = nextKey.substring(CONNECTION_PREFIX
						.length());
				String connectionClass = pluginProperties.getProperty(nextKey);
				LOG.debug("Adding ConnectionDef mapping '" + connectionType
						+ "' = '" + connectionClass + "'");
				connectionDefFactory
						.addMapping(connectionType, connectionClass);
			} else if (nextKey.startsWith(DELIMITER_PREFIX)) {
				String delimiterType = nextKey.substring(DELIMITER_PREFIX
						.length());
				String delimiterClass = pluginProperties.getProperty(nextKey);
				LOG
						.debug("Adding DelimiterParser (or BinaryDelimiterParser) mapping '"
								+ delimiterType + "', '" + delimiterClass + "'");
				delimiterParserFactory
						.addMapping(delimiterType, delimiterClass);
				binaryDelimiterParserFactory.addMapping(delimiterType,
						delimiterClass);
			} else if (nextKey.startsWith(COLUMNTRANSLATOR_PREFIX)) {
				String columnTranslatorType = nextKey
						.substring(COLUMNTRANSLATOR_PREFIX.length());
				String columnTranslatorClass = pluginProperties
						.getProperty(nextKey);
				LOG.debug("Adding ColumnTranslator mapping '"
						+ columnTranslatorType + "', '" + columnTranslatorClass
						+ "'");
				columnTranslatorFactory.addMapping(columnTranslatorType,
						columnTranslatorClass);
			} else if (nextKey.startsWith(GENERATOR_PREFIX)) {
				String generatorType = nextKey.substring(GENERATOR_PREFIX
						.length());
				String generatorClass = pluginProperties.getProperty(nextKey);
				LOG.debug("Adding ColumnValueGenerator mapping '"
						+ generatorType + "' = '" + generatorClass + "'");
				columnValueGeneratorFactory.addMapping(generatorType,
						generatorClass);
			} else if (nextKey.startsWith(ROWTRANSLATOR_PREFIX)) {
				String rowTranslatorType = nextKey
						.substring(ROWTRANSLATOR_PREFIX.length());
				String rowTranslatorClass = pluginProperties
						.getProperty(nextKey);
				LOG.debug("Adding RowTranslator mapping '" + rowTranslatorType
						+ "' = '" + rowTranslatorClass + "'");
				rowTranslatorFactory.addMapping(rowTranslatorType,
						rowTranslatorClass);
			} else {
				LOG.warn("Unrecognized property '" + nextKey + "'");
			}
		}
		LOG.trace("initPlugins( <Properties:" + pluginProperties.size()
				+ "> ) <-");
	}

	/**
	 * Sets the import engine to use.
	 * 
	 * @param engineClassname
	 *            the classname of the import engine
	 */
	public void setEngine(String engineClassname) {
		this.engineClassname = engineClassname;
	}

	/**
	 * Import data into the database by processing each entity definition via
	 * the import engine.
	 * 
	 * @throws FileNotFoundException
	 *             if the log file or bad log file is not valid
	 * @throws IOException
	 *             if the log file or bad log file cannot be opened and written
	 *             to
	 */
	public void beginImport() throws FileNotFoundException, IOException {
		LOG.trace("beginImport() ->");
		ImportEngine engine = null;
		LOG.debug("Instantiating Engine '" + engineClassname + "'");
		try {
			engine = (ImportEngine) Class.forName(engineClassname)
					.newInstance();
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Class Not Found : '"
					+ engineClassname + "'");
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Class Not Instantiated : '"
					+ engineClassname + "'");
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Class Not Instantiated : '"
					+ engineClassname + "'");
		}

		if (LOG.isDebugEnabled())
			LOG
					.debug("Initializing Connection "
							+ importDef.getConnectionDef());

		Connection connection = importDef.getConnectionDef().getConnection();
		if (connection == null) {
			throw new IOException("Could not initialize connection");
		}
		engine.setConnection(connection);
		EntityImporter importer = new EntityImporter();
		importer.setFailonerror(this.failOnError);
		FileWriter log = null;
		FileWriter bad = null;
		if (importDef.getLogFile() != null && importDef.getBadFile() != null) {
			log = new FileWriter(importDef.getLogFile());
			bad = new FileWriter(importDef.getBadFile());
			addImportListener(new LogImportListener(log, bad));
		}

		boolean autoCommit = true;
		if (importDef.getCommitCount() < importDef.getBatchCount()) {
			importDef.setCommitCount(importDef.getBatchCount());
		}
		try {
			connection.setAutoCommit((importDef.getCommitCount() <= 1));
			autoCommit = (importDef.getCommitCount() <= 1);
			importer.setBatchCount(importDef.getCommitCount());
		} catch (SQLException e) {
			LOG.warn("Could not set auto commit to '"
					+ (importDef.getCommitCount() <= 1) + "'", e);
		}

		boolean supportsBatch = false;
		if (importDef.getBatchCount() > 1 && !autoCommit) {
			try {
				supportsBatch = connection.getMetaData().supportsBatchUpdates();
			} catch (SQLException s) {
				LOG
						.warn(
								"Unable to detect if connection has batch mode support",
								s);
			}
			engine.setBatchMode(supportsBatch);
			importer.setSupportsBatch(supportsBatch);
			if (supportsBatch)
				importer.setBatchCount(importDef.getBatchCount());
		}

		importer.setTrimValues(importDef.isTrimValues());
		importer.setImportEngine(engine);
		for (int i = 0; i < this.importListeners.size(); i++) {
			importer.addImportListener((ImportListener) this.importListeners
					.get(i));
		}
		if (importDef.getPreSQLFile() != null) {
			executeSQLFile(engine.getConnection(), importDef.getPreSQLFile());
		}
		fireImportEvent(new ImportEvent(this, importDef, this.engineClassname,
				supportsBatch), true);
		ImportEntityDef[] entities = importDef.getEntities();
		boolean errorOccurred = false;
		for (int i = 0; i < entities.length; i++) {
			fireImportEntityEvent(
					new ImportEntityEvent(this, entities[i], null), true);
			try {
				importer.processEntity(entities[i]);
				fireImportEntityEvent(new ImportEntityEvent(this, entities[i],
						null), false);
			} catch (IOException ex) {
				fireImportEntityEvent(new ImportEntityEvent(this, entities[i],
						ex), false);
				if (failOnError) {
					errorOccurred = true;
					break;
				}
			} catch (SQLException ex) {
				fireImportEntityEvent(new ImportEntityEvent(this, entities[i],ex), false);
				if (failOnError) {
					errorOccurred = true;
					break;
				}
			}
		}
		fireImportEvent(new ImportEvent(this, importDef, this.engineClassname,
				supportsBatch), false);
		if (importDef.getPostSQLFile() != null && !errorOccurred) {
			executeSQLFile(engine.getConnection(), importDef.getPostSQLFile());
		}

		importDef.getConnectionDef().releaseConnection(connection);
		if (log != null && bad != null) {
			log.close();
			bad.close();
		}

		if (errorOccurred) {
			this.importFailed = true;
		}
		LOG.trace("beginImport() <-");
	}

	/**
	 * Adds an import listener.
	 * 
	 * @param listener
	 *            an import listener
	 */
	public void addImportListener(ImportListener listener) {
		if (!importListeners.contains(listener)) {
			importListeners.add(listener);
		}
	}

	/**
	 * Removes an import listener.
	 * 
	 * @param listener
	 *            an import listener
	 */
	public void removeImportListener(ImportListener listener) {
		importListeners.remove(listener);
	}

	/**
	 * Fire an import event to the listeners.
	 * 
	 * @param e
	 *            the import event
	 * @param start
	 *            true if the import is starting and false if the import is
	 *            finished
	 */
	protected void fireImportEvent(ImportEvent e, boolean start) {
		Iterator i = importListeners.iterator();
		while (i.hasNext()) {
			if (start) {
				((ImportListener) i.next()).importStarted(e);
			} else {
				((ImportListener) i.next()).importFinished(e);
			}
		}
	}

	/**
	 * Fire an import entity event to the listeners.
	 * 
	 * @param e
	 *            the import entity event
	 * @param start
	 *            true if the import of the entity is starting and false if the
	 *            import of the entity is finished
	 */
	protected void fireImportEntityEvent(ImportEntityEvent e, boolean start) {
		Iterator i = importListeners.iterator();
		while (i.hasNext()) {
			if (start) {
				((ImportListener) i.next()).entityImportStarted(e);
			} else {
				((ImportListener) i.next()).entityImportFinished(e);
			}
		}
	}

	/**
	 * Execute a series of sql statements stored in a file.
	 * 
	 * @param connection
	 *            The JDBC Connection
	 * @param SQLFile
	 *            The file containing sql statements
	 */
	protected void executeSQLFile(Connection connection, File SQLFile) {
		StringBuffer buf = new StringBuffer("");
		Statement stmt = null;
		BufferedReader in = null;
		try {
			stmt = connection.createStatement();
			in = new BufferedReader(new FileReader(SQLFile));
			String line = null;
			while ((line = in.readLine()) != null) {
				String trimmedLine = line.trim();
				if (trimmedLine.endsWith(";")) {
					buf.append(line.substring(0, line.indexOf(";")));
					stmt.execute(buf.toString());
					buf.setLength(0);
				} else if (trimmedLine.length() == 0 && buf.length() == 0) {
					continue;
				} else {
					buf.append(line);
				}
			}

			if (buf.length() > 0) {
				stmt.execute(buf.toString());
			}
		} catch (IOException e) {
			LOG.error("IOException while reading file '"
					+ SQLFile.getAbsolutePath() + "'", e);
		} catch (SQLException e) {
			LOG.error("SQLException while executing statment '"
					+ buf.toString() + "'", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					LOG.error("Could not close sql statement", e);
				}
			}
			if (in != null) {
				try {
					in.close();

				} catch (IOException e) {
					LOG.error("Could not close input stream", e);
				}
			}
			try {
				if (!connection.getAutoCommit()) {
					connection.commit();
				}
			} catch (SQLException e) {
				LOG.error("Could not commit", e);
			}
		}
	}

	public static void main(String[] args) {
		// TODO: Add parameters '-verbose' and '-help'
		if (args.length < 1) {
			System.out
					.println("Usage: java net.sourceforge.jdbcimporter.Importer <import config file> [plugin file]");
			System.exit(-1);
		}
		try {
			Importer importer = new Importer();
			if (args.length > 1) {
				importer.initPlugins(args[1]);
			}
			FileInputStream fileInput = null;
			String importConfigFile = args[0];
			try {
				fileInput = new FileInputStream(importConfigFile);
				InputSource in = new InputSource(fileInput);
				importer.initImportDef(in);
				importer.setEngine(System.getProperties().getProperty(
						"jdbcimporter.engine",
						"net.sourceforge.jdbcimporter.engine.BasicEngine"));
				importer.setFailonerror("true".equals(System.getProperties()
						.getProperty("jdbcimporter.failonerror")));
				importer.beginImport();
			} finally {
				if (fileInput != null) {
					try {
						fileInput.close();
					} catch (IOException e) {
						LOG.warn("Could not close file '" + importConfigFile
								+ "'", e);
					}
				}
			}
			if (importer.hasImportFailed()) {
				System.exit(-1);
			} else {
				System.exit(0);
			}
		} catch (Exception e) {
			LOG.fatal("An fatal error occurred while importing data", e);
			System.exit(-1);
		}
	}
}
