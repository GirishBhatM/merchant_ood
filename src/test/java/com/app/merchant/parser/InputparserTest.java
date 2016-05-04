package com.app.merchant.parser;

import org.junit.Assert;
import org.junit.Test;

import com.app.merchant.MerchantManager;
import com.app.merchant.exception.ParserException;
import com.app.merchant.internal.InputType;

public class InputparserTest
{
	private static final TextFileParser parser = new TextFileParser();

	@Test(expected = ParserException.class)
	public void testParseForException()
	{
		String filePath = null;
		parser.parse(filePath);
	}

	@Test
	public void testFindInputType()
	{
		InputType inputType = parser.findInputType("glob is I");
		Assert.assertTrue(InputType.WORD_TO_ROMAN == inputType);
		inputType = parser.findInputType("glob glob Silver is 34 Credits");
		Assert.assertTrue(InputType.WORD_TO_CREDITS == inputType);
		inputType = parser.findInputType("how much is pish tegj glob glob ?");
		Assert.assertTrue(InputType.QUESTION_HOWMUCH == inputType);
		inputType = parser.findInputType("how many Credits is glob prok Silver ?");
		Assert.assertTrue(InputType.QUESTION_HOWMANY == inputType);
		inputType = parser.findInputType("this is an unlnown pattern");
		Assert.assertTrue(InputType.UNKNOWN == inputType);
	}

	@Test
	public void testParseWordToRoman()
	{
		final MerchantManager merchantManager = new MerchantManager();
		String inputLine = "glob is I";
		parser.parseWordToRoman(merchantManager, inputLine);
		Assert.assertTrue(merchantManager.getSymbolToRomanMap().size() != 0);
		Assert.assertTrue(merchantManager.getSymbolToRomanMap().get("glob") != null);
		Assert.assertTrue(merchantManager.getSymbolToRomanMap().get("glob").equals("I"));
	}

	@Test
	public void testParseWordToCredits()
	{
		final MerchantManager merchantManager = new MerchantManager();
		merchantManager.getSymbolToRomanMap().put("glob", "I");
		String inputLine = "glob glob Silver is 34 Credits";
		parser.parseWordToCredits(merchantManager, inputLine);
		Assert.assertTrue(merchantManager.getSymbolToDecimalMap().size() != 0);
		Assert.assertTrue(merchantManager.getSymbolToDecimalMap().get("Silver") != null);
		Assert.assertTrue(merchantManager.getSymbolToDecimalMap().get("Silver") == 17.0);
	}

	@Test
	public void testParseQuestionHowMuch()
	{
		final MerchantManager merchantManager = new MerchantManager();
		merchantManager.getSymbolToRomanMap().put("glob", "I");
		merchantManager.getSymbolToRomanMap().put("pish", "X");
		merchantManager.getSymbolToRomanMap().put("tegj", "L");
		merchantManager.getSymbolToRomanMap().put("glob", "I");
		String inputLine = "how much is pish tegj glob glob ?";
		parser.parseQuestionHowMuch(merchantManager, inputLine);
		Assert.assertTrue(merchantManager.getOutput().size() != 0);
		Assert.assertTrue(merchantManager.getOutput().get(0).endsWith("42"));
	}

	@Test
	public void testParseQuestionHowMany()
	{
		final MerchantManager merchantManager = new MerchantManager();
		merchantManager.getSymbolToRomanMap().put("glob", "I");
		merchantManager.getSymbolToRomanMap().put("prok", "V");
		merchantManager.getSymbolToDecimalMap().put("Silver", 17.0);
		String inputLine = "how many Credits is glob prok Silver ?";
		parser.parseQuestionHowMany(merchantManager, inputLine);
		Assert.assertTrue(merchantManager.getOutput().size() != 0);
		Assert.assertTrue(merchantManager.getOutput().get(0).endsWith("68.0 Credits"));
	}

}
