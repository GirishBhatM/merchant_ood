package com.app.merchant.parser;

import com.app.merchant.MerchantManager;
import com.app.merchant.exception.ParserException;

/**
 * Parser interface
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public interface Parser
{
	/**
	 * Method which parses the input data and populates the {@link MerchantManager} Input can be file path,json
	 * string,xml or ant other format.Implementer should provide the implementation for parsing the format.
	 * 
	 * @param inputparameter
	 * @return MerchantManager
	 */
	MerchantManager parse(String inputparameter) throws ParserException;
}
