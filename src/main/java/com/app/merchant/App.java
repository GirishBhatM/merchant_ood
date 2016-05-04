package com.app.merchant;

import com.app.merchant.exception.MerchantApplicationException;
import com.app.merchant.parser.Parser;
import com.app.merchant.parser.TextFileParser;
import com.app.merchant.utility.ValidationUtility;

/**
 * Entry point for the application
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public class App
{
	public static void main(String[] args) throws MerchantApplicationException
	{
		if (ValidationUtility.getInstance().isNullOrEmpty(args))
		{
			throw new MerchantApplicationException("Input argument is null or empty");
		}
		String filePath = args[0];
		if (ValidationUtility.getInstance().isNullOrEmpty(filePath))
		{
			throw new MerchantApplicationException("Input file path is empty");
		}
		Parser inputParser = new TextFileParser();
		try
		{
			MerchantManager manager = inputParser.parse(filePath);
			for (String output : manager.getOutput())
			{
				System.out.println(output);
			}
		} catch (MerchantApplicationException e)
		{
			throw e;
		}

	}
}
