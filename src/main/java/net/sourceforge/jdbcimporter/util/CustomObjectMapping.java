package net.sourceforge.jdbcimporter.util;

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

import java.util.HashMap;
import java.util.Map;

/**
 * The CustomObjectMapping class provides default mappings for ColumnValueGenerators,
 * ColumnTranslators, ConnectionDefs, DelimiterFormatters and DelimiterParsers.
 * 
 * @version 0.63
 * @author Chris Nagy
 */
public class CustomObjectMapping
{
	/**
	 * Returns the default mappings for ColumnValueGenerators.
	 * 
	 * @return default mappings
	 */
	public static Map getDefaultColumnValueGeneratorMapping()
	{
		Map map = new HashMap();
		map.put( "choice",           "net.sourceforge.datagenerator.generator.ChoiceValueGenerator");
		map.put( "currentDate",      "net.sourceforge.datagenerator.generator.CurrentDateValueGenerator");
		map.put( "dependentChoice",  "net.sourceforge.datagenerator.generator.DependentChoiceValueGenerator");
		map.put( "dateRange",        "net.sourceforge.datagenerator.generator.DateRangeValueGenerator");
		map.put( "numberExpression", "net.sourceforge.datagenerator.generator.NumberExpressionValueGenerator");
		map.put( "numberRange",      "net.sourceforge.datagenerator.generator.NumberRangeValueGenerator");
		map.put( "numberSequence",   "net.sourceforge.datagenerator.generator.NumberSequenceValueGenerator");
		return map;
	}

	/**
	 * Returns the default mappings for ConnectionDefs.
	 * 
	 * @return default mappings
	 */
	public static Map getDefaultConnectionDefMapping()
	{
		Map map = new HashMap();
		map.put( "jdbc",  "net.sourceforge.jdbcimporter.connection.JDBCConnectionDef" );
		map.put( "jndi",  "net.sourceforge.jdbcimporter.connection.JNDIConnectionDef" );
		map.put( "xconn", "net.sourceforge.jdbcimporter.connection.XalanConnectionDef" );
		return map;
	}
	
	/**
	 * Returns the default mappings for DelimiterFormatters.
	 * 
	 * @return default mappings
	 */
	public static Map getDefaultDelimiterFormatterMapping()
	{
		Map map = new HashMap();
		map.put( "csv",     "net.sourceforge.jdbcexporter.formatter.CSVDelimiterFormatter" );
		map.put( "fixed",   "net.sourceforge.jdbcexporter.formatter.FixedDelimiterFormatter" );
		map.put( "pattern", "net.sourceforge.jdbcexporter.formatter.PatternDelimiterFormatter" );
		map.put( "xml",     "net.sourceforge.jdbcexporter.formatter.XMLDelimiterFormatter" );
		return map;
	}
	
	/**
	 * Returns the default mappings for DelimiterParsers.
	 * 
	 * @return default mappings
	 */
	public static Map getDefaultDelimiterParserMapping()
	{
		Map map = new HashMap();
		map.put( "csv",     "net.sourceforge.jdbcimporter.parser.CSVDelimiterParser" );
		map.put( "fixed",   "net.sourceforge.jdbcimporter.parser.FixedDelimiterParser" );
		map.put( "pattern", "net.sourceforge.jdbcimporter.parser.PatternDelimiterParser" );
		map.put( "xml",     "net.sourceforge.jdbcimporter.parser.XMLDelimiterParser" );
		return map;
	}
	
	/**
	 * Returns the default mappings for ColumnTranslators.
	 * 
	 * @return default mappings
	 */
	public static Map getDefaultColumnTranslatorMapping()
	{
		Map map = new HashMap();
		map.put( "booleanToInt", "net.sourceforge.jdbcimporter.columntranslator.BooleanToIntColumnTranslator" );
		map.put( "intToBoolean", "net.sourceforge.jdbcimporter.columntranslator.IntToBooleanColumnTranslator" );
		map.put( "systemtime",   "net.sourceforge.jdbcimporter.columntranslator.SystemTimeColumnTranslator" );
		map.put( "stringToFile", "net.sourceforge.jdbcimporter.columntranslator.StringToFileColumnTranslator" );
		map.put( "fileToString", "net.sourceforge.jdbcimporter.columntranslator.FileToStringColumnTranslator" );
		return map;
	}

	/**
	 * Returns the default mappings for RowTranslators.
	 * 
	 * @return default mappings
	 */
	public static Map getDefaultRowTranslatorMapping() 
	{
		Map map = new HashMap();
		map.put("skipRows", "new.sourceforge.jdbcimporter.rowtranslator.SkipRowsRowTranslator" );
		return map;
	}
	
}
