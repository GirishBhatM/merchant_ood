package com.app.merchant.internal;

import org.junit.Assert;
import org.junit.Test;

import com.app.merchant.exception.ConversionException;

public class RomanToIntegerConverterTest
{

	@Test
	public void testGetInstance()
	{
		Assert.assertNotNull(RomanToIntegerConverter.getInstance());
	}

	@Test(expected = ConversionException.class)
	public void testConvertCharacterForException()
	{
		RomanToIntegerConverter.getInstance().convert(Character.MIN_VALUE);
	}

	@Test
	public void testConvertString()
	{
		String romanString = null;
		Assert.assertTrue(0 == RomanToIntegerConverter.getInstance().convert(romanString));
		romanString = "XX";
		Assert.assertTrue(20 == RomanToIntegerConverter.getInstance().convert(romanString));
		romanString = "IV";
		Assert.assertTrue(4 == RomanToIntegerConverter.getInstance().convert(romanString));
	}

}
