package org.uta.steam.bl.util;

import java.util.regex.Pattern;

public class SteamUtil {

	private SteamUtil() {

	}

	public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";	
	public static final String RELEASE_DATE_FORMAT = "dd MMM, yyyy";
	
	public static final String POSTED_DATE_FORMAT_NO_YEAR = "d MMM hh:mma";
	public static final String POSTED_DATE_FORMAT_WITH_YEAR = "d MMM, yyyy hh:mma";	
	
	public static final Pattern NUMBER_PATTERN_INT = Pattern
			.compile("[0-9][0-9]*");

	public static final Pattern NUMBER_PATTERN_DOUBLE = Pattern
			.compile("[0-9]+(.)?[0-9]*");
	
	public static final String CSV_OUTPUT_FORMAT = "UTF-8";
	
	public static final String API_KEY = "6FCFCE5E8A5859EFC0E182056B4E612E";

}
