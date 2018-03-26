package net.sourceforge.datagenerator.generator;

import java.util.Random;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;
import net.sourceforge.jdbcutils.id.Identity;
import net.sourceforge.jdbcutils.id.IdentityFactory;


public class UUIDGenerator extends ColumnValueGeneratorBase 
{

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[0];
	}
	
	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue(int row, Random r)
	{		
		ColumnValue val = new ColumnValue();
		val.setString(IdentityFactory.getInstance().getIdentity(Identity.STYLE_DATA_DEFAULT));
		return val;
	}
}
