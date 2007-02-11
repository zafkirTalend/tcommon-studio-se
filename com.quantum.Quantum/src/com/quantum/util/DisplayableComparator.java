package com.quantum.util;

import java.util.Comparator;


/**
 * This class is used to compare two displayable objects.
 * 
 * @author BC Holmes
 */
public class DisplayableComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		if (arg0 == null && arg1 == null) {
			return 0;
		} else if (arg0 == null) {
			return -1;
		} else if (arg1 == null) {
			return 1;
		} else {
			return compare((Displayable) arg0, (Displayable) arg1);
		}
	}

	/**
	 * @param displayable0
	 * @param displayable1
	 * @return
	 */
	private int compare(Displayable displayable0, Displayable displayable1) {
		if (displayable0.getDisplayName() == null && displayable1.getDisplayName() == null) {
			return 0;
		} else if (displayable0.getDisplayName() == null) {
			return -1;
		} else if (displayable1.getDisplayName() == null) {
			return 1;
		} else {
			return displayable0.getDisplayName().toLowerCase().compareTo(
					displayable1.getDisplayName().toLowerCase());
		}
	}

}
