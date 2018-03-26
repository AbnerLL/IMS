package net.sourceforge.jdbcimporter.parser;

import java.beans.BeanDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

import net.sourceforge.jdbcimporter.util.StringListEditor;

public class AccessBinaryDelimiterParserBeanInfo extends SimpleBeanInfo {

	PropertyDescriptor[] descriptors;
	BeanDescriptor descriptor;

	public AccessBinaryDelimiterParserBeanInfo()
			throws java.beans.IntrospectionException {
		try {
			descriptors = new PropertyDescriptor[3];
			descriptors[0] = new PropertyDescriptor("table",
					AccessBinaryDelimiterParser.class, null, "setTable");
			descriptors[1] = new PropertyDescriptor("columnNames",
					AccessBinaryDelimiterParser.class, null, "setColumnNames");
			descriptors[1].setPropertyEditorClass(StringListEditor.class);
			descriptors[2] = new PropertyDescriptor("fileName",
					AccessBinaryDelimiterParser.class, null, "setFileName");

		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
			throw e;
		}
		descriptor = new BeanDescriptor(AccessBinaryDelimiterParser.class);
	}

	public BeanDescriptor getBeanDescriptor() {
		return descriptor;
	}

	public PropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

}
