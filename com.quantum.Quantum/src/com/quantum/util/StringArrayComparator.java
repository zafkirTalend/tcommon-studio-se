package com.quantum.util;

import java.util.Comparator;


/**
 * @author BC
 */
public class StringArrayComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		return compare((String[]) arg0, (String[]) arg1);
	}

	public int compare(String[] arg0, String[] arg1) {
		if (arg0 == null && arg1 == null) {
			return 0;
		} else if (arg0 == null) {
			return -1;
		} else if (arg1 == null) {
			return 1;
		} else if (arg0.length != arg1.length) {
			return arg0.length - arg1.length;
		} else {
			int result = 0;
			for (int i = 0, length = arg0 == null ? 0 : arg0.length; 
					result == 0 && i < length; i++) {
				result = compare(arg0[i], arg1[i]);
			} 
			return result;
		}
	}

	private int compare(String arg0, String arg1) {
		if (arg0 == null && arg1 == null) {
			return 0;
		} else if (arg0 == null) {
			return -1;
		} else if (arg1 == null) {
			return 1;
		} else {
			return arg0.compareTo(arg1);
		}
	}
}
