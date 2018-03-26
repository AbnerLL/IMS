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

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The CustomMessageFormat class formats/parses strings similar to java.text.MessageFormat.
 * It has the following enhancements : numbers greater than 9 are recognized, '-' character
 * can be used to mark a portion of the pattern as ignored. Ignored portions will not be returned
 * when parsing a string and empty strings will be placed into those positions when formatting a string.
 * 
 * @version 	0.61
 * @author     Chris Nagy
 */
public class CustomMessageFormat extends Format
{
	/**
	 * The list of tokens in the pattern.
	 */
	protected List   tokens = new ArrayList();
	
	/**
	 * The pattern without the tokens.
	 */
	protected String pattern;
	
	/**
	 * The maximum index of a token.
	 */
	protected int    arrayLength;
	
	/**
	 * Creates a CustomMessageFormat with the given pattern.
	 * 
	 * @param pattern the pattern
	 */
	public CustomMessageFormat( String pattern )
	{
		super( );
		parsePattern( pattern );
	}

	/**
	 * @see java.text.Format#parseObject(java.lang.String, java.text.ParsePosition)
	 */
	public Object parseObject( String source, ParsePosition pos )
	{
		Object[] vals = new Object[ arrayLength ];
				
		StringBuffer templateBuf = new StringBuffer(pattern);
		StringBuffer sourceBuf   = new StringBuffer(source);

		int offset = 0;

		int tokenStart, tokenEnd;
		
		for (int i = 0; i < tokens.size(); i++)
		{
			TokenField nextToken   = (TokenField) tokens.get(i);
			tokenStart = nextToken.origpos + offset;

			if (nextToken.origpos + 1 > pattern.length()) 
			{
				if (tokenStart < source.length()) 
				{
					Object retVal = nextToken.parseObject( source.substring(tokenStart, source.length()), 0 );
					if ( nextToken.index >= 0 && nextToken.index < vals.length )
					{
						vals[nextToken.index] = retVal;
					}
				}
				pos.setIndex( source.length() );
				break;
			}

			String nextSearchStr = pattern.substring( nextToken.origpos );
			if ( i < tokens.size() - 1)
			{
				TokenField token = (TokenField) tokens.get(i+1);
				nextSearchStr = pattern.substring( nextToken.origpos, token.origpos );
			}
			tokenEnd   = source.indexOf( nextSearchStr, tokenStart);

			if (tokenEnd >= 0) 
			{
				Object retVal = nextToken.parseObject( source.substring(tokenStart, tokenEnd), 0 );

				if ( nextToken.index >= 0 && nextToken.index < vals.length )
				{
					vals[nextToken.index] = retVal;
				}
			
				offset += (tokenEnd - tokenStart);
				pos.setIndex( tokenEnd );
			}
		}

		return vals;
	}

	/**
	 * @see java.text.Format#format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition)
	 */
	public StringBuffer format( Object obj, StringBuffer toAppendTo, FieldPosition pos )
	{
		Object[] vals = (Object[]) obj;
		StringBuffer returnBuffer = new StringBuffer(pattern);

		int offset = 0;	
		for (int i = 0; i < tokens.size(); i++)
		{
			TokenField nextToken = (TokenField) tokens.get(i);
			if (nextToken.index < 0 || nextToken.index >= vals.length)
			{
				continue;
			}
			
			Object val = vals[nextToken.index];
			int origLen = returnBuffer.length();
			returnBuffer = nextToken.format(val, returnBuffer, offset);
			int newLen = returnBuffer.length();
			offset += (newLen - origLen);
		}
		return returnBuffer;
	}

	/**
	 * Parses the pattern into an internal format.
	 * 
	 * @param origPattern the original pattern
	 */
	protected void parsePattern( String origPattern )
	{
		String tokenStart    = "{";
		String tokenEnd      = "}";
		
		pattern = "";
		tokens.clear();
		arrayLength = 0;
		
		int offset    = 0;
		int startData = 0; 
		int endData   = origPattern.length();
		int nextTokenStart = origPattern.indexOf(tokenStart, startData);
		int nextTokenEnd   = origPattern.indexOf(tokenEnd, startData);
		while(nextTokenStart != -1 && nextTokenEnd != -1 && startData < endData)
		{
			String token = origPattern.substring(nextTokenStart + 1, nextTokenEnd);
			StringTokenizer tokenizer = new StringTokenizer(token, ",");
			String tokenIndexStr = "";
			String tokenType     = "";
			String tokenFormat   = "";
			if (tokenizer.hasMoreTokens()) 
			{
				tokenIndexStr = tokenizer.nextToken();
				if (tokenizer.hasMoreTokens())
				{
					tokenType = tokenizer.nextToken();
				}
				if ( tokenizer.hasMoreTokens() )
				{
					tokenFormat = tokenizer.nextToken();
				}
				int tokenIndex = -1;
				try
				{
					if ( !"-".equals( tokenIndexStr ) )
					{	
						tokenIndex = Integer.parseInt( tokenIndexStr );
					}
				}
				catch ( NumberFormatException n )
				{
					
				}
				arrayLength = Math.max( tokenIndex + 1, arrayLength );
				tokens.add(new TokenField(nextTokenStart - offset, tokenIndex, tokenType, tokenFormat));
				offset   += nextTokenEnd - nextTokenStart + 1;
			}
			pattern += origPattern.substring(startData, nextTokenStart);
			startData = nextTokenEnd + 1;
			nextTokenStart = origPattern.indexOf(tokenStart, startData);
			nextTokenEnd   = origPattern.indexOf(tokenEnd,   startData);
		}

		if (startData < endData)
				pattern += origPattern.substring(startData, endData);
	}

	class TokenField 
	{
		public int    origpos;
		public int    index;
		public String formatType;
		public String format;


		public TokenField(int pos, int index, String formatType, String format)
		{
			this.origpos = pos;
			this.index   = index;
			this.format  = formatType;
			this.format  = format;
		}
		
		public StringBuffer format(Object val, StringBuffer data, int offset)
		{
			if( val == null )
			{
				return data;
			}
			StringBuffer returnString = data;
			if (returnString.length() > origpos + offset) {
				returnString.insert(origpos + offset, val.toString());
			}
			else if (returnString.length() == origpos + offset) {
				returnString.append(val.toString());
			}
			return returnString;
		}
		
		public Object parseObject( String val, int pos )
		{
			return val;
		}
	}	
}
