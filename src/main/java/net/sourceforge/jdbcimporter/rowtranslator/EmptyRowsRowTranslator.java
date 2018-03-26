package net.sourceforge.jdbcimporter.rowtranslator;

import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcimporter.EntityDef;
import net.sourceforge.jdbcimporter.RowTranslator;

public class EmptyRowsRowTranslator implements RowTranslator 
{

	public ColumnValue[] getValues(EntityDef entity, ColumnValue[] rowValues) 
	{
		boolean isEmptyRow=true;
		for(int i=0;i<rowValues.length;i++){
			if(rowValues[i].getString()!=null
					&&!rowValues[i].getString().trim().equals("")){
				isEmptyRow=false;
				break;
			}
		}
		if (isEmptyRow) 
		{
			return null;
		}
		else 
		{
			return rowValues;
		}
	}

}
