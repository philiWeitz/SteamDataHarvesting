package org.uta.steam.bl.util;

import java.util.regex.Pattern;

public class SteamUtil {

	private SteamUtil() {

	}

	
	public static final Pattern NUMBER_PATTERN_INT = Pattern
			.compile("[0-9][0-9]*");

	public static final Pattern NUMBER_PATTERN_DOUBLE = Pattern
			.compile("[0-9]+(.)?[0-9]*");

	public static final Pattern STRING_INPUT = Pattern
			.compile("[a-zA-Z0-9.,-: ]*");
	
	public static final String CSV_OUTPUT_FORMAT = "UTF-8";
}
