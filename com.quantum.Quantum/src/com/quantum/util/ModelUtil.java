/*
 * Created on 27/02/2005
 *	Utilities for the model structure
 */
package com.quantum.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.quantum.model.Column;

/**
 * @author Julen
 *
 */
public class ModelUtil {
	/**
	 * @param columns	Array of columns with possibly a primary key
	 * @return	An array of columns with the columns of the primary key, in the right order
	 */
	public static Column[] extractPrimaryKeyColumns(Column[] columns) {
		if (columns == null) {
			return null;
		}
		Map temp = new TreeMap();
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if (column.getPrimaryKeyOrder() > 0) {
				temp.put(new Integer(column.getPrimaryKeyOrder()), column);
			}
			
		}
		if (temp.size() == 0) {
			return null;
		}
		List columnList = Collections.synchronizedList(
		        new ArrayList(temp.values()));
		Collections.sort(columnList);
		return (Column[]) columnList.toArray(new Column[columnList.size()]);
	}

}
