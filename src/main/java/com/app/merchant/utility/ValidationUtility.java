package com.app.merchant.utility;

/**
 * Utility class for handling minimal validations over the parameters
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public class ValidationUtility
{
	private static final ValidationUtility INSATNCE = new ValidationUtility();

	private ValidationUtility() {

	}

	public static ValidationUtility getInstance()
	{
		return INSATNCE;
	}

	public boolean isNullOrEmpty(String parameter)
	{
		return parameter == null || parameter.trim().length() == 0;
	}

	public boolean isNullOrEmpty(Object[] paramters)
	{
		return paramters == null || paramters.length == 0;
	}

	public boolean isTextFile(String filePath)
	{
		if (this.isNullOrEmpty(filePath))
		{
			return false;
		}
		int lastIndex = filePath.lastIndexOf(".");
		if (lastIndex == -1 || lastIndex == filePath.length() - 1)
		{
			return false;
		}
		String extension = filePath.substring(lastIndex + 1);
		return "txt".equals(extension);
	}
}
