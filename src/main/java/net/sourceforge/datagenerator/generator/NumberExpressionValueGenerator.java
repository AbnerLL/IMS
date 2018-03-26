package net.sourceforge.datagenerator.generator;

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
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

import net.sourceforge.datagenerator.ColumnValueGeneratorBase;
import net.sourceforge.jdbcimporter.ColumnValue;

/**
 * The NumberExpressionValueGenerator generates values by evaluating an
 * numeric expression. The expression can be made up of the following operators :
 * '+', '-', '*', '/' and it can contain sub-expressions that are enclosed
 * in brackets. The actual values can be staticly specified or selected from
 * another columns' generated values.
 * Examples:
 * 
 * <pre>
 * '2+3+4-5' 
 * '(4+3*(5-1))/2-6'
 * 'minimum+10000'
 * 'Order.quantity*Order.unitPrice'
 * </pre>
 * 
 * @version 0.6
 * @author  Chris Nagy
 */
public class NumberExpressionValueGenerator extends ColumnValueGeneratorBase 
{

	/**
	 * The columns that will be used in the expression.
	 */
	String[] dependentFields = new String[0];
	
	/**
	 * The root expression node.
	 */
	NumberExpressionNode root;
	
	/**
	 * Set the expression.
	 * 
	 * @param expression the expression
	 */	
	public void setExpression( String expression )
	{
		Stack curRoots         = new Stack();
		Stack rightLeafParents = new Stack();
		
		OperationNode curRoot = null;
		OperationNode rightLeafParent = null;
		
		NumberExpressionNode prevNode = null;
		StringBuffer curNum = new StringBuffer("");
		boolean curNumIsDependency = false;
		boolean curNumIsRange = false;
		
		List dependentFieldList = new ArrayList(); 		
		for ( int i = 0; i < expression.length(); i++ )
		{
			if ( expression.charAt(i) == '+' ||
			     expression.charAt(i) == '-' )
			{
				OperationNode opNode = new OperationNode();
				opNode.setOperator(expression.charAt(i));

				if ( curNum.length() > 0 )
				{
					if ( curNumIsDependency )
					{
						DependentNumberNode node = new DependentNumberNode();
						node.setDependentField( curNum.toString() );
						if ( !dependentFieldList.contains( curNum.toString() ) )
						{
							dependentFieldList.add(curNum.toString());
						}
						prevNode = node;
					}
					else if ( curNumIsRange )
					{
						StringTokenizer tokenizer = new StringTokenizer( curNum.toString(), "," );
						// TODO: Error Checking
						BigDecimal startNum = new BigDecimal( tokenizer.nextToken() );
						BigDecimal endNum = new BigDecimal( tokenizer.nextToken() );
						BigDecimal incrementNum = new BigDecimal( "1" );
						if ( tokenizer.hasMoreTokens() )
						{
							incrementNum = new BigDecimal( tokenizer.nextToken() );
						}
						RangeNumberNode node = new RangeNumberNode( startNum, endNum, incrementNum );
						prevNode = node;
					}
					else 
					{
						BaseNumberNode node = new BaseNumberNode();
						node.setNumber( new BigDecimal( curNum.toString() ) );
						prevNode = node;
					}
				}
				if ( rightLeafParent != null )
				{
					rightLeafParent.setRight( prevNode );
					opNode.setLeft(curRoot);
					rightLeafParent = opNode;
				}
				else
				{
					opNode.setLeft( prevNode );
				}
				
				curNum.setLength(0);
				curNumIsDependency = false;
				curNumIsRange = false;
				curRoot = opNode;
				rightLeafParent=opNode;
				prevNode = null;
			}
			else if ( expression.charAt(i) == '*' ||
			          expression.charAt(i) == '/' )
			{
				if ( curNum.length() > 0 )
				{
					if ( curNumIsDependency )
					{
						DependentNumberNode node = new DependentNumberNode();
						node.setDependentField( curNum.toString() );
						if ( !dependentFieldList.contains( curNum.toString() ) )
						{
							dependentFieldList.add(curNum.toString());
						}
						prevNode = node;
					}
					else if ( curNumIsRange )
					{
						StringTokenizer tokenizer = new StringTokenizer( curNum.toString(), "," );
						// TODO: Error Checking
						BigDecimal startNum = new BigDecimal( tokenizer.nextToken() );
						BigDecimal endNum = new BigDecimal( tokenizer.nextToken() );
						BigDecimal incrementNum = new BigDecimal( "1" );
						if ( tokenizer.hasMoreTokens() )
						{
							incrementNum = new BigDecimal( tokenizer.nextToken() );
						}
						RangeNumberNode node = new RangeNumberNode( startNum, endNum, incrementNum );
						prevNode = node;
					}
					else 
					{
						BaseNumberNode node = new BaseNumberNode();
						node.setNumber( new BigDecimal( curNum.toString() ) );
						prevNode = node;
					}
				}

				OperationNode opNode = new OperationNode();
				opNode.setOperator(expression.charAt(i));
				
				if ( rightLeafParent != null )
				{
					if ( rightLeafParent instanceof OperationNode &&
						 (((OperationNode) rightLeafParent).getOperator() == '*' ||
						  ((OperationNode) rightLeafParent).getOperator() == '/' )
						)
					{
						rightLeafParent.setRight( prevNode );
						opNode.setLeft( rightLeafParent );
						if ( rightLeafParent == curRoot )
						{
							curRoot = opNode;
						}
					}
					else
					{
						rightLeafParent.setRight( opNode );
						opNode.setLeft( prevNode );
					}
				}
				else
				{
					curRoot = opNode;
					opNode.setLeft( prevNode );
				}

				rightLeafParent = opNode;
				curNum.setLength(0);
				curNumIsDependency = false;
				curNumIsRange = false;
				prevNode = null;
			}
			else if ( expression.charAt(i) == '(' )
			{
				if ( curRoot != null && rightLeafParent != null )
				{
					curRoots.push( curRoot );
					rightLeafParents.push( rightLeafParent );
					curRoot = null;
					rightLeafParent = null;
					prevNode = null;
				}
			}
			else if ( expression.charAt(i) == ')' )
			{
				if ( curNum.length() > 0 )
				{
					if ( curNumIsDependency )
					{
						DependentNumberNode node = new DependentNumberNode();
						node.setDependentField( curNum.toString() );
						if ( !dependentFieldList.contains( curNum.toString() ) )
						{
							dependentFieldList.add(curNum.toString());
						}
						prevNode = node;
					}
					else if ( curNumIsRange )
					{
						StringTokenizer tokenizer = new StringTokenizer( curNum.toString(), "," );
						// TODO: Error Checking
						BigDecimal startNum = new BigDecimal( tokenizer.nextToken() );
						BigDecimal endNum = new BigDecimal( tokenizer.nextToken() );
						BigDecimal incrementNum = new BigDecimal( "1" );
						if ( tokenizer.hasMoreTokens() )
						{
							incrementNum = new BigDecimal( tokenizer.nextToken() );
						}
						RangeNumberNode node = new RangeNumberNode( startNum, endNum, incrementNum );
						prevNode = node;
					}
					else 
					{
						BaseNumberNode node = new BaseNumberNode();
						node.setNumber( new BigDecimal( curNum.toString() ) );
						prevNode = node;
					}
				}
				
				rightLeafParent.setRight( prevNode );
				
				prevNode = curRoot;
				curNum.setLength(0);
				curNumIsDependency = false;
				curNumIsRange = false;
				if ( curRoots.size() > 0)
				{				
					curRoot = (OperationNode) curRoots.pop();
					rightLeafParent = (OperationNode) rightLeafParents.pop();
				}
				else
				{
					curRoot = null;
					rightLeafParent = null;
				}
			}
			else if ( expression.charAt(i) == '[' ) // TODO: Check for close ']'
			{
				curNumIsRange = true;
			}
			else if ( expression.charAt(i) == ']' )
			{
				// Eat it
			}
			else if ( expression.charAt(i) == ',' && curNumIsRange )
			{
				curNum.append( expression.charAt(i) );
			}
			else if ( Character.isLetter( expression.charAt(i) ) )
			{
				curNum.append( expression.charAt(i) );
				curNumIsDependency = true;
 			}
 			else if ( !Character.isWhitespace( expression.charAt(i) ) )
 			{
 				curNum.append( expression.charAt(i) );
 			}
 		}

		if ( curNum.length() > 0 )
		{
			if ( curNumIsDependency )
			{
				DependentNumberNode node = new DependentNumberNode();
				node.setDependentField( curNum.toString() );
				if ( !dependentFieldList.contains( curNum.toString() ) )
				{
					dependentFieldList.add(curNum.toString());
				}
				prevNode = node;
			}
			else if ( curNumIsRange )
			{
				StringTokenizer tokenizer = new StringTokenizer( curNum.toString(), "," );
				// TODO: Error Checking
				BigDecimal startNum = new BigDecimal( tokenizer.nextToken() );
				BigDecimal endNum = new BigDecimal( tokenizer.nextToken() );
				BigDecimal incrementNum = new BigDecimal( "1" );
				if ( tokenizer.hasMoreTokens() )
				{
					incrementNum = new BigDecimal( tokenizer.nextToken() );
				}
				RangeNumberNode node = new RangeNumberNode( startNum, endNum, incrementNum );
				prevNode = node;
			}
			else 
			{
				BaseNumberNode node = new BaseNumberNode();
				node.setNumber( new BigDecimal( curNum.toString() ) );
				prevNode = node;
			}
		}
		rightLeafParent.setRight( prevNode );
		root = curRoot;
		dependentFields = new String[ dependentFieldList.size() ];
		for ( int i = 0; i < dependentFields.length; i++ )
		{
			dependentFields[i] = (String) dependentFieldList.get(i);
		}
		
	}

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getDependencies()
	 */
	public String[] getDependencies() 
	{
		return dependentFields;
	}

	/**
	 * @see net.sourceforge.datagenerator.ColumnValueGenerator#getNextColumnValue(int,java.util.Random)
	 */
	public ColumnValue getNextColumnValue(int row, Random r) 
	{
		ColumnValue val = new ColumnValue();
		val.setString( this.columnDef.getFormat().format( root.calculate(r, this.dependentValues ) ) );
		return val;
	}

	public static void main(String[] args)
	{
		NumberExpressionValueGenerator gen = new NumberExpressionValueGenerator();
		try
		{
			BufferedReader buf = new BufferedReader( new InputStreamReader( System.in ) );
			String line = null;
			System.out.print(">");
			System.out.flush();
			while ( ( line = buf.readLine() ) != null )
			{
				if ( "quit".equals( line ) )
				{
					break;
				}
				gen.setExpression( line );
				System.out.println(gen.root);			
				System.out.print(">");
				System.out.flush();
			}
		}
		catch (IOException e)
		{
			
		}
	}
}

/**
 * Interface used by NumberExpressionValueGenerator.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
interface NumberExpressionNode 
{
	/**
	 * Calculate the value with the given Random and dependency map.
	 * 
	 * @param r the random number generator
	 * @param dependencyMap the map between dependent fields and their generated values
	 * @return the generated number
	 */
	public Number calculate( Random r, Map dependencyMap );
	
	/**
	 * Return the string version of the node. Used for debugging
	 * purposes.
	 * 
	 * @param depth the depth to indent
	 * @return the string
	 */
	public String toString( int depth );
}

/**
 * Base Number Node is a leaf node that contains a number. No
 * calculation is done.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
class BaseNumberNode implements NumberExpressionNode
{
	/**
	 * The number.
	 */
	Number number;
	
	/**
	 * Set the number.
	 * 
	 * @param num the number
	 */
	public void setNumber( Number num )
	{
		number = num;
	}
	
	/**
	 * Returns the number.
	 */
	public Number calculate( Random r, Map dependencyMap)
	{
		return number;
	}
	
	/**
	 * @see net.sourceforge.datagenerator.generator.NumberExpressionNode#toString(int)
	 */
	public String toString( int depth )
	{
		StringBuffer buf = new StringBuffer("");
		for ( int i = 0; i < depth; i++ )
		{
			buf.append( "  " );
		}
		buf.append( "|- ");
		buf.append( number.toString() );
		buf.append("\n");
		return buf.toString();
	}
}

/**
 * Range Number Node is a leaf node that contains a number range. A 
 * random number is chosen inside the number range.
 * 
 * ie. [10,100,5]
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
class RangeNumberNode implements NumberExpressionNode
{
	BigDecimal startNumber;
	BigDecimal endNumber;
	BigDecimal numChoices;
	BigDecimal increment;
	
	public RangeNumberNode( Number startNumber, Number endNumber, Number increment )
	{
		this.startNumber = new BigDecimal( startNumber.toString() );
		this.endNumber   = new BigDecimal( endNumber.toString() );
		this.increment   = new BigDecimal( increment.toString() );
		BigDecimal range = new BigDecimal( endNumber.toString() ).subtract( this.startNumber );
		numChoices = range.divide( new BigDecimal( increment.toString() ), BigDecimal.ROUND_DOWN );
	}	
	
	/**
	 * Returns the number.
	 */
	public Number calculate( Random r, Map dependencyMap)
	{
		long offset = Math.abs(r.nextLong() % numChoices.longValue() );
		BigDecimal val = startNumber.add( increment.multiply( new BigDecimal( offset ) ) );
		return val;
	}
	
	/**
	 * @see net.sourceforge.datagenerator.generator.NumberExpressionNode#toString(int)
	 */
	public String toString( int depth )
	{
		StringBuffer buf = new StringBuffer("");
		for ( int i = 0; i < depth; i++ )
		{
			buf.append( "  " );
		}
		buf.append( "|- [");
		buf.append( startNumber.toString() );
		buf.append(",");
		buf.append( endNumber.toString() );
		buf.append( ",");
		buf.append( increment.toString() );
		buf.append( "]");
		buf.append("\n");
		return buf.toString();
	}
}

/**
 * Base Number Node is a leaf node that contains the name of the column
 * whose value(s) will be used to evaluate the expression. If there are 
 * multiple values then one will be selected at random.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
class DependentNumberNode implements NumberExpressionNode
{
	String dependentField;

	/**
	 * Set the dependent column.
	 * 
	 * @param field
	 */	
	public void setDependentField( String field )
	{
		dependentField = field;
	}
		
	/**
	 * Returns the number.
	 */
	public Number calculate( Random r, Map dependencyMap )
	{
		String[] values = (String[]) dependencyMap.get( dependentField );
		if ( values == null || values.length == 0 )
		{
			return new BigDecimal(0);
		}
		if ( values.length == 1 )
		{
			return new BigDecimal( values[0] );
		}
		else
		{
			return new BigDecimal( values[ Math.abs( r.nextInt() % values.length )]);
		}
	}

	/**
	 * @see net.sourceforge.datagenerator.generator.NumberExpressionNode#toString(int)
	 */
	public String toString( int depth )
	{
		StringBuffer buf = new StringBuffer("");
		for ( int i = 0; i < depth; i++ )
		{
			buf.append( "  " );
		}
		buf.append( "|- '");
		buf.append( dependentField );
		buf.append("'\n");
		return buf.toString();
	}
}

/**
 * Operation Node is a tree node that contains an operator and two operands.
 * It will retrieve the two values of the operands and apply the operator during
 * calculation.
 * 
 * @version 	0.6
 * @author      Chris Nagy
 */
class OperationNode implements NumberExpressionNode
{
	char operator = '+';
	NumberExpressionNode left;
	NumberExpressionNode right;

	/**
	 * Set the operator.
	 * 
	 * @param op
	 */	
	public void setOperator( char op )
	{
		//TODO: validate operator
		operator = op;	
	}
	
	/**
	 * Return the operator.
	 * 
	 * @return
	 */
	public char getOperator()
	{
		return operator;
	}
	
	/**
	 * Set the left operand node.
	 * 
	 * @param node
	 */
	public void setLeft( NumberExpressionNode node )
	{
		left = node;
	}
	
	/**
	 * Return the left operand node.
	 * 
	 * @return
	 */
	public NumberExpressionNode getLeft()
	{
		return left;
	}
	
	/**
	 * Set the right operand node.
	 * 
	 * @param node
	 */
	public void setRight( NumberExpressionNode node )
	{
		right = node;
	}
	
	/**
	 * Return the right operand node.
	 * 
	 * @return
	 */
	public NumberExpressionNode getRight()
	{
		return right;
	}

	/**
	 * Returns the calculated value.
	 */	
	public Number calculate( Random r, Map dependencyMap )
	{
		Number leftNum = left.calculate(r, dependencyMap );
		Number rightNum = right.calculate(r, dependencyMap );
		if ( operator == '-' )
		{
			return new BigDecimal( leftNum.toString() ).subtract( new BigDecimal( rightNum.toString() ));
		}
		else if ( operator == '*' )
		{
			return new BigDecimal( leftNum.toString() ).multiply( new BigDecimal( rightNum.toString() ));
		}
		else if ( operator == '/' )
		{
			return new BigDecimal( leftNum.toString() ).divide( new BigDecimal( rightNum.toString()), BigDecimal.ROUND_DOWN );
		}
		else // ( operator == '+' )
		{
			return new BigDecimal( leftNum.toString() ).add( new BigDecimal( rightNum.toString() ));
		}
	}
	
	/**
	 * Overrides toString()
	 */
	public String toString()
	{
		return toString(0);
	}
	
	/**
	 * @see net.sourceforge.datagenerator.generator.NumberExpressionNode#toString(int)
	 */
	public String toString( int depth )
	{
		StringBuffer buf = new StringBuffer("");
		for ( int i = 0; i < depth; i++ )
		{
			buf.append( "  " );
		}
		buf.append( "|- ");
		buf.append( operator );
		buf.append("\n");
		if ( left != null )
		{
			buf.append( left.toString( depth + 1 ) );
		}
		else
		{
			for ( int i = 0; i < depth; i++ )
			{
				buf.append( "  " );
			}
			buf.append( "- <null>\n" );
		}
		if ( right != null )
		{
			buf.append( right.toString( depth+1) );
		}
		else
		{
			for ( int i = 0; i < depth; i++ )
			{
				buf.append( "  " );
			}
			buf.append( "- <null>\n" );
		}
		return buf.toString();
	}
}

/*

Example: 

2+3+4-5 = 4

       -    = 4
    +    5
  +   4
2   3


2+3*4-5 = 9

     -     = 9
  +    5
2   *
  3   4

3+10/2-5*3 = -7

      -
  +      *
3    /  5  3
   10  2
    
3+10/2*5-3 = 25

    -
  +   3
3   /
  10  *
     2  5

(3+4)*2 = 14

  *     = 14
 + 2
3 4
          
2*(3+4) = 14


 *
2 +
 3 4
 
2*(3+4*(9-6))-5

   -
 *   5
2  +
  3  *
    4  -
      9 6
      
(4+3*(5-1))/2-6

     - 
   /  6
 +  2
4  *
  3  -
    5 1

*/
 
