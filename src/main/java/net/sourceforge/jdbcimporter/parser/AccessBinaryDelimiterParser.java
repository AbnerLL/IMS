package net.sourceforge.jdbcimporter.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import net.sourceforge.jdbcimporter.BinaryDelimiterParser;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.MalformedDataException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.healthmarketscience.jackcess.ByteUtil;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

public class AccessBinaryDelimiterParser implements BinaryDelimiterParser {
	protected static Log LOG = LogFactory
			.getLog(AccessBinaryDelimiterParser.class);

	Database database = null;
	String fileName = null;
	Table curTable = null;
	int curRow = 0;
	InputStream curIn = null;
	int curMaxRow = 0;

	String table = null;
	String[] columnNames = null;

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void finalize() {
		if (database != null) {
			try {
				database.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			database = null;
			curTable = null;
		}
	}

	public Object getNextRow() throws IOException {
		if (database == null) {
			try {
				database = Database.open(new File(fileName));
				curTable = database.getTable(table);
				curRow = 0;
				curMaxRow = curTable.getRowCount();
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			}
		}

		if (curRow >= curMaxRow) {
			return null;
		}
		Map<String, Object> row = null;
		if (this.getColumnNames() != null) {
			row = curTable.getNextRow(Arrays.asList(this.getColumnNames()));
		} else {
			row = curTable.getNextRow();
		}
		curRow++;
		return row;
	}

	public String getRowAsString(Object row) {
		Map<String, Object> rowMap = (Map<String, Object>) row;
		StringBuffer buf = new StringBuffer("");
		for (Iterator<Object> iter = rowMap.values().iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof byte[]) {
				byte[] b = (byte[]) obj;
				buf.append(ByteUtil.toHexString(b));
				// This block can be used to easily dump a binary column to a
				// file
				/*
				 * java.io.File f = java.io.File.createTempFile("ole", ".bin");
				 * java.io.FileOutputStream out = new
				 * java.io.FileOutputStream(f); out.write(b); out.flush();
				 * out.close();
				 */
			} else {
				buf.append(String.valueOf(obj));
			}
			if (iter.hasNext()) {
				buf.append(",");
			}
		}
		return buf.toString();
	}

	public ColumnValue[] getValues(Object row) throws MalformedDataException {
		Map<String, Object> rowMap = (Map<String, Object>) row;
		ColumnValue[] vals = new ColumnValue[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			String colName = columnNames[i];
			Object cell = rowMap.get(colName);
			vals[i] = new ColumnValue();
			if (cell instanceof String) {
				vals[i].setString(cell.toString());
			} else if (cell instanceof byte[]) {
				vals[i].setBytes(((byte[]) cell));
			} else if (cell instanceof Date) {
				vals[i].setObject(((Date) cell));
			} else {
				vals[i].setObject(cell);
			}
		}
		return vals;
	}

	public void setInputStream(InputStream in) {
		if (database != null) {
			try {
				database.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			database = null;
			curTable = null;
		}
		curIn = in;
		try {
			curIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
