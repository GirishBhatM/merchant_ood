package com.app.merchant.exception;

/**
 * A root exception class for the application. All other kind of application needs to subclass this. If exception is not
 * categorized then root exception will be thrown
 * 
 * @author girish.b
 *
 */
public class MerchantApplicationException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MerchantApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MerchantApplicationException(String message) {
		super(message);
	}

}
