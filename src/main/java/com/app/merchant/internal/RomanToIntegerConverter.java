package com.app.merchant.internal;

import com.app.merchant.exception.ConversionException;
import com.app.merchant.utility.ValidationUtility;

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
		symbol = Character.toUpperCase(symbol);
		switch (symbol)
		{
			case 'I':
				value = RomanLiteral.I.getValue();
				break;
			case 'V':
				value = RomanLiteral.V.getValue();
				break;
			case 'X':
				value = RomanLiteral.X.getValue();
				break;
			case 'L':
				value = RomanLiteral.L.getValue();
				break;
			case 'C':
				value = RomanLiteral.C.getValue();
				break;
			case 'D':
				value = RomanLiteral.D.getValue();
				break;
			case 'M':
				value = RomanLiteral.M.getValue();
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

	/**
	 * Converts given roman string into equivalent int value
	 * 
	 * @param romanString
	 * @return equivalent int value, returns 0 if null or empty
	 */
	public Integer convert(String romanString)
	{
		if (ValidationUtility.getInstance().isNullOrEmpty(romanString))
		{
			return 0;
		}
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
