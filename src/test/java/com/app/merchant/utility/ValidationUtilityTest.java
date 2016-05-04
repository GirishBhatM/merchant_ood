package com.app.merchant.utility;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

public class ValidationUtilityTest
{

	@Test
	public void testGetInstance()
	{
		assertNotNull(ValidationUtility.getInstance());
	}

	@Test
	public void testIsNullOrEmptyString()
	{
		String parameter = null;
		Assert.assertTrue(ValidationUtility.getInstance().isNullOrEmpty(parameter));
		parameter = "     ";
		Assert.assertTrue(ValidationUtility.getInstance().isNullOrEmpty(parameter));
		parameter = "nonempty value";
		Assert.assertTrue(!ValidationUtility.getInstance().isNullOrEmpty(parameter));
	}

	@Test
	public void testIsNullOrEmptyObjectArray()
	{
		Object[] parameters = null;
		Assert.assertTrue(ValidationUtility.getInstance().isNullOrEmpty(parameters));
		parameters = new Object[0];
		Assert.assertTrue(ValidationUtility.getInstance().isNullOrEmpty(parameters));
		parameters = new Object[1];
		parameters[0] = new Object();
		Assert.assertTrue(!ValidationUtility.getInstance().isNullOrEmpty(parameters));
	}

	@Test
	public void testIsTextFile()
	{
		String filePath = null;
		Assert.assertFalse(ValidationUtility.getInstance().isTextFile(filePath));
		filePath = "D:\\temp\\input.txt";
		Assert.assertTrue(ValidationUtility.getInstance().isTextFile(filePath));
	}

}
