package com.app.merchant;

import com.app.merchant.api.internal.InputParser;
import com.app.merchant.api.service.MerchantManager;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		InputParser inputParser = new InputParser();
		MerchantManager manager = inputParser
		        .parse("D:\\Workspaces\\sts\\team\\b2b_perforce_latest\\hackerRank\\src\\easy\\input.txt");
		System.out.println(manager.getOutput().get(0));
	}
}
