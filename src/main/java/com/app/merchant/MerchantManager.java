package com.app.merchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.merchant.internal.RomanToIntegerConverter;

public class MerchantManager
{
	private Map<String, String> symbolToRomanMap;
	private Map<String, Double> symbolToDecimalMap;
	private List<String> output;

	public Map<String, String> getSymbolToRomanMap()
	{
		if (this.symbolToRomanMap == null)
		{
			this.symbolToRomanMap = new HashMap<String, String>();
		}
		return this.symbolToRomanMap;
	}

	public Map<String, Double> getSymbolToDecimalMap()
	{
		if (this.symbolToDecimalMap == null)
		{
			this.symbolToDecimalMap = new HashMap<String, Double>();
		}
		return this.symbolToDecimalMap;
	}

	public List<String> getOutput()
	{
		if (this.output == null)
		{
			this.output = new ArrayList<>();
		}
		return this.output;
	}

	/**
	 * Returns integer(decimal conversion) of transaction
	 * 
	 * @param transactions--List
	 *            of transactions made
	 * @return--Integer value for corresponding to transactions.Returns 0 if transaction is invalid or no mapping found
	 */
	public int getValueForTransactions(String[] transactions)
	{
		int value = 0;
		String romanString = "";
		for (String tx : transactions)
		{
			if (!this.getSymbolToRomanMap().keySet().contains(tx))
			{
				return 0;
			}
			romanString = romanString + this.getSymbolToRomanMap().get(tx);
		}
		value = RomanToIntegerConverter.getInstance().convert(romanString);
		return value;
	}

}
