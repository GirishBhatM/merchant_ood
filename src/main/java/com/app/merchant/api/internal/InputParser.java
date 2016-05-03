package com.app.merchant.api.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.app.merchant.api.exception.MerchantApplicationException;
import com.app.merchant.api.service.MerchantManager;

/**
 * Class responsible for parsing the input file and updates the {@link MerchantManager} with the input data.
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public class InputParser
{
	private static String WORD_TO_ROMAN_REGX = "^([a-z]+) is ([I|V|X|L|C|D|M])$";
	private static String CREDITS_REGEX = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
	private static String QUESTION_HOWMANY_REGX = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
	private static String QUESTION_HOWMUCH_REGEX = "^how much is ((?:\\w+[^0-9] )+)\\?$";
	private static String UNKNOWN_RESPONSE = "I have no idea what you are talking about";

	/**
	 * 
	 * @param filePath--input
	 *            file path
	 * @return--Returns {@link MerchantManager} containing parsed data
	 * @throws MerchantApplicationException
	 */
	public MerchantManager parse(String filePath) throws MerchantApplicationException
	{
		if (filePath == null | filePath.trim().length() == 0)
		{
			throw new MerchantApplicationException("Illegal Argument.File path is empty " + filePath);
		}
		File file = new File(filePath);
		if (!file.exists())
		{
			throw new MerchantApplicationException("Input file doesn't exists.");
		}
		MerchantManager manager = new MerchantManager();
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String inputLine = br.readLine();
			while (inputLine != null)
			{
				InputType type = this.findInputType(inputLine);
				switch (type)
				{
					case WORD_TO_ROMAN:
						this.parseWordToRoman(manager, inputLine);
						break;
					case WORD_TO_CREDITS:
						this.parseWordToCredits(manager, inputLine);
						break;
					case QUESTION_HOWMUCH:
						manager.getOutput().add(inputLine);
						break;
					case QUESTION_HOWMANY:
						manager.getOutput().add(inputLine);
						break;
					case UNKNOWN:
						manager.getOutput().add(UNKNOWN_RESPONSE);
						break;
					default:
						manager.getOutput().add(UNKNOWN_RESPONSE);
						break;
				}
				inputLine = br.readLine();
			}
		} catch (IOException e)
		{
			throw new MerchantApplicationException("Error occured while reading the file.");
		}
		return manager;
	}

	/**
	 * Checks whether the input line matches any of the predefined type, if no match found returns
	 * {@link InputType.UNKNOWN}
	 * 
	 * @param line--read
	 *            input
	 * @return -- returns type of input {@link InputType}
	 */
	protected InputType findInputType(String line)
	{
		InputType inputType = InputType.UNKNOWN;
		List<String> regexs = Arrays.asList(WORD_TO_ROMAN_REGX, CREDITS_REGEX, QUESTION_HOWMANY_REGX,
		        QUESTION_HOWMUCH_REGEX);
		for (String regex : regexs)
		{
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches())
			{
				int index = regexs.indexOf(regex);
				switch (index)
				{
					case 0:
						inputType = InputType.WORD_TO_ROMAN;
						break;
					case 1:
						inputType = InputType.WORD_TO_CREDITS;
						break;
					case 2:
						inputType = InputType.QUESTION_HOWMANY;
						break;
					case 3:
						inputType = InputType.QUESTION_HOWMUCH;
						break;
					default:
						inputType = InputType.UNKNOWN;
						break;
				}
			}
		}
		return inputType;
	}

	/**
	 * Populates the {@link MerchantManager.symbolToRomanMap}
	 * 
	 * @param manager--{@link
	 *            MerchantManager}
	 * @param line
	 */
	protected void parseWordToRoman(MerchantManager manager, String line)
	{
		Pattern pattern = Pattern.compile(WORD_TO_ROMAN_REGX);
		Matcher matcher = pattern.matcher(line);
		matcher.matches();
		String word = matcher.group(1).trim();
		String roman = matcher.group(2).trim();
		manager.getSymbolToRomanMap().put(word, roman);
	}

	/**
	 * Populates the {@link MerchantManager.symbolToDecimalMap}
	 * 
	 * @param manager--{@link
	 *            MerchantManager}
	 * @param line
	 */
	protected void parseWordToCredits(MerchantManager manager, String line)
	{
		Pattern pattern = Pattern.compile(CREDITS_REGEX);
		Matcher matcher = pattern.matcher(line);
		matcher.matches();
		String[] transactions = matcher.group(1).split(" ");
		String element = matcher.group(2);
		int credits = Integer.parseInt(matcher.group(3).trim());
		int transValue = manager.getValueForTransactions(transactions);
		double value = credits / transValue;
		manager.getSymbolToDecimalMap().put(element, value);
	}

}
