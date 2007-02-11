package com.quantum.sql;

import java.util.ArrayList;

public class FilterSort {
	private ArrayList filterList = new ArrayList();
	private ArrayList sortList = new ArrayList();
	private ArrayList ascDescList = new ArrayList();
	public void addFilter(String column, String operator, String value, boolean isString) {
		FilterRow row = new FilterRow();
		row.column = column;
		row.operator = operator;
		row.value = value;
		row.isString = isString;
		filterList.add(row);
	} 
	public void clearFilters() {
		filterList.clear();
	}
	public void addSort(String column, String ascDesc) {
		int ind = sortList.indexOf(column);
		if (ind < 0) {
			sortList.add(column);
			ascDescList.add(ascDesc);
			return;
		} 
		if (ind < ascDescList.size()) {
			ascDescList.remove(ind);
		}
		ascDescList.add(ind, ascDesc);
	}
	public void removeSort(String column) {
		int ind = sortList.indexOf(column);
		if (ind < 0) return;
		sortList.remove(ind);
		ascDescList.remove(ind);
	}
	public String toString() {
		StringBuffer text = new StringBuffer();
		if (filterList.size() > 0) {
			text.append(" WHERE "); //$NON-NLS-1$
			for (int i = 0; i < filterList.size(); i++) {
				FilterRow row = (FilterRow) filterList.get(i);
				text.append(row.column);
				text.append(" "); //$NON-NLS-1$
				text.append(row.operator);
				text.append(" "); //$NON-NLS-1$
				if (row.isString) {
					text.append(escape(row.value));
				} else {
					text.append(row.value);
				}
				text.append(" "); //$NON-NLS-1$
				if (i < filterList.size() - 1) {
					text.append("AND "); //$NON-NLS-1$
				}
			}
		}
		if (sortList.size() > 0) {
			text.append(" ORDER BY "); //$NON-NLS-1$
			for (int i = 0; i < sortList.size(); i++) {
				String value = (String) sortList.get(i);
				text.append(value);
				text.append(" ");
				text.append(ascDescList.get(i));
				if (i < sortList.size() - 1) {
					text.append(", "); //$NON-NLS-1$
				}
			}
		}
		return text.toString();
	}
	public static String escape(String original) {
		return '\'' + original + '\'';
	}
}

class FilterRow {
	public String column;
	public String operator;
	public String value;
	public boolean isString;
}