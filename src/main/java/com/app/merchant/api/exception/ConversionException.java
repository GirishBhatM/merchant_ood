package com.app.merchant.api.exception;

/**
 * Exception category for non parsepable or un recognized Roman literal, which cannot be parsed.
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public class ConversionException extends MerchantApplicationException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConversionException(String message) {
		super(message);
	}

}
