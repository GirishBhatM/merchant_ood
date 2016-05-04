package com.app.merchant.internal;

/**
 * enum class for representing all the Roman literals, each enum has a Roman Key and Integer value.
 * 
 * @author girishbhat.m7@gmail.com
 *
 */
public enum RomanLiteral
{
	I('I', 1), V('V', 5), X('X', 10), L('L', 50), C('C', 100), D('D', 500), M('M', 1000);

	private Character symbol;
	private int value;

	private RomanLiteral(Character symbol, int value) {
		this.symbol = symbol;
		this.value = value;
	}

	public Character getSymbol()
	{
		return this.symbol;
	}

	public int getValue()
	{
		return this.value;
	}
}
