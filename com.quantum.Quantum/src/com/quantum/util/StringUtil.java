package com.quantum.util;

import java.util.Arrays;

/**
 * @author BC Holmes
 */
public class StringUtil {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public static String substituteString(String original, String placeHolder, String replacementValue) {
		StringBuffer buffer = new StringBuffer();
		char[] originalChars = original == null ? new char[0] : original.toCharArray();
		char[] placeHolderChars = placeHolder.toCharArray();
		char[] placeHolderComparisonArray = new char[placeHolderChars.length];
		
		for (int i = 0, length = originalChars.length; i < length; i++) {
			if (i <= length - placeHolderChars.length) {
				System.arraycopy(originalChars, i, placeHolderComparisonArray, 0, placeHolderComparisonArray.length);
				if (Arrays.equals(placeHolderComparisonArray, placeHolderChars)) {
					i += placeHolderComparisonArray.length - 1;
					buffer.append(replacementValue);
				} else {
					buffer.append(originalChars[i]);
				}
			} else {
				buffer.append(originalChars[i]);
			}
		}
		
		return original == null ? null : buffer.toString();
	}
	
	public static boolean startsWithIgnoreCase(String string, String potentialPrefix) {
		if (string == null || potentialPrefix == null) {
			return false;
		} else {
			return string.toLowerCase().startsWith(potentialPrefix.toLowerCase());
		}
	}

	public static boolean isNotEmpty(String string) {
		return string != null && string.trim().length() > 0;
	}
}
