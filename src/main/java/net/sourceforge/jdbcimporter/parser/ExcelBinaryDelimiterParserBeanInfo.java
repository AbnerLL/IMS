package net.sourceforge.jdbcimporter.parser;

import java.beans.BeanDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

import net.sourceforge.jdbcimporter.util.StringListEditor;

public class ExcelBinaryDelimiterParserBeanInfo extends SimpleBeanInfo {

	PropertyDescriptor[] descriptors;
	BeanDescriptor descriptor;
	
	public ExcelBinaryDelimiterParserBeanInfo()
		throws java.beans.IntrospectionException
	{
		try
		{
			descriptors = new PropertyDescriptor[4];
//			descriptors[0] = new PropertyDescriptor( "sheet",   ExcelBinaryDelimiterParser.class, null, "setSheet" );
//			descriptors[1] = new PropertyDescriptor( "columns", ExcelBinaryDelimiterParser.class, null, "setColumns" );
//			descriptors[1].setPropertyEditorClass( IntegerArrayEditor.class );
			
			descriptors[0] = new PropertyDescriptor( "fileName", ExcelDelimiterParser.class, null, "setFileName" );
			descriptors[1] = new PropertyDescriptor( "sheet", ExcelDelimiterParser.class, null, "setSheet" );
			descriptors[2] = new PropertyDescriptor( "ignoreRows", ExcelDelimiterParser.class, null, "setIgnoreRows" );
			descriptors[2].setPropertyEditorClass( StringListEditor.class );
			descriptors[3] = new PropertyDescriptor( "columns", ExcelDelimiterParser.class, null, "setColumns" );
			descriptors[3].setPropertyEditorClass( StringListEditor.class );
		}
		catch ( java.beans.IntrospectionException e )
		{
			e.printStackTrace();
			throw e;
		}
		descriptor = new BeanDescriptor(ExcelBinaryDelimiterParser.class);
	}
	
	public BeanDescriptor getBeanDescriptor()
	{
		return descriptor;
	}

	public PropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}
	

}
