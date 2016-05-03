package com.app.merchant.api.internal;

import com.app.merchant.api.exception.ConversionException;

/**
 * A singleton class which is needs to have one instance per application.
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public class RomanToIntegerConverter
{
	private final static RomanToIntegerConverter INSTANCE = new RomanToIntegerConverter();

	private RomanToIntegerConverter() {

	}

	public static RomanToIntegerConverter getInstance()
	{
		return INSTANCE;
	}

	/**
	 * This method gives the integer value corresponds to Roman Literal.
	 * 
	 * @param symbol--Roman
	 *            literal
	 * @return--integer value corresponding to literal
	 * @throws ConversionException--Thorws
	 *             exception If no mapping found
	 */
	public Integer convert(Character symbol) throws ConversionException
	{
		if (symbol == null)
		{
			throw new ConversionException("Symbol is undefined " + symbol);
		}
		Integer value = 0;
		switch (symbol)
		{
			case 'I':
				value = Roman.I.getValue();
				break;
			case 'V':
				value = Roman.V.getValue();
				break;
			case 'X':
				value = Roman.X.getValue();
				break;
			case 'L':
				value = Roman.L.getValue();
				break;
			case 'C':
				value = Roman.C.getValue();
				break;
			case 'D':
				value = Roman.D.getValue();
				break;
			case 'M':
				value = Roman.M.getValue();
				break;
			default:
				value = 0;
				break;
		}
		if (value == 0)
		{
			throw new ConversionException("Symbol is undefined " + symbol);
		}
		return value;
	}

	public Integer convert(String romanString)
	{
		int total = 0;
		int prev = 0;
		for (int i = romanString.length() - 1; i >= 0; i--)
		{

			int temp = this.convert(romanString.charAt(i));
			if (temp < prev)
			{
				total -= temp;
			}
			else
			{
				total += temp;
			}
			prev = temp;

		}
		return total;
	}
}
