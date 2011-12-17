package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * This class is used to process nested xml columns, either Simple one or Complex one.
 * For the definition of Simple nesed xml columns and Complex nested xml columns, please 
 * refer to RelationInformation
 */
class NestedColumnUtil {
	
	//	The HashMap 
	private HashMap nestedXMLColumns;

	private RelationInformation relationInfo;

	private String tableName;

	/**
	 * Constructor
	 * 
	 * @param relationInfo
	 * @param tableName
	 * @param isSimple
	 */
	NestedColumnUtil(RelationInformation relationInfo, String tableName,
			boolean isSimple) {
		this.relationInfo = relationInfo;
		this.tableName = tableName == null ? "":tableName.trim( );
		String[] namesOfNestedColumns = isSimple ? relationInfo
				.getTableSimpleNestedXMLColumnNames(this.tableName) : relationInfo
				.getTableComplexNestedXMLColumnNames(this.tableName);

		nestedXMLColumns = new HashMap();
		for (int i = 0; i < namesOfNestedColumns.length; i++)
		{
			int backRefNumber = relationInfo.getTableNestedColumnBackRefNumber( tableName, namesOfNestedColumns[i] );
			int forwardRefNumber = relationInfo.getTableColumnForwardRefNumber( tableName, namesOfNestedColumns[i] );
			
			nestedXMLColumns.put(namesOfNestedColumns[i], new ThreshHoldInfo(
					backRefNumber, forwardRefNumber, isSimple));
		}
	}

	/**
	 * Return the value of certain nested column marked by the column name and current root path(the SaxParser generated one)
	 * 
	 * @param columnName
	 * @param currentRootPath
	 * @return
	 */
	String getNestedColumnValue(String columnName, String currentRootPath) {
		ThreshHoldInfo thInfo = (ThreshHoldInfo) nestedXMLColumns
				.get(columnName);
		assert columnName != null;
		return thInfo.getValue(currentRootPath);
	}

	void update(String columnName, String path, String value) {
		String columnPath = relationInfo.getTableColumnPath(tableName,
				columnName);
		if (SaxParserUtil.isSamePath(columnPath, path))
			((ThreshHoldInfo) this.nestedXMLColumns.get(columnName)).addInfo(
					path, value);
	}
}

/**
 * This class is used to map column XPath expressions and their corresponding values. Each instance of this
 * class is responsible for the storage of values of one nested XML column.  
 *
 */
class ThreshHoldInfo {
	//
	private Map map;

	private int backRefLevel;

	private boolean isSimple;

	private int forwardRefLevel;
	/**
	 * The constructor.
	 *
	 */
	ThreshHoldInfo(int backRefLevel, int forwardRefLevel, boolean isSimple) {
		this.map = new HashMap();
		this.backRefLevel = backRefLevel;
		this.isSimple = isSimple;
		this.forwardRefLevel = forwardRefLevel;
	}

	/**
	 * Add nested column info.
	 * 
	 * @param path
	 * @param value
	 */
	void addInfo(String path, String value) {
		map.put(path, value);
	}

	/**
	 * Get value of nested column according to the current rootpath and the backRefNumber of the very nested column. 
	 * 
	 * @param rootPath
	 * @return
	 */
	String getValue(String rootPath) {
		String nestedXMLPath = getCorrespondingNestedXMLColumnPath(rootPath);

		if (nestedXMLPath == null)
			return null;

		return map.get(nestedXMLPath).toString();
	}

	/**
	 * @param rootPath
	 * @param backRefNumber
	 * @return
	 */
	private String getCorrespondingNestedXMLColumnPath(String rootPath) {
		Iterator it = map.keySet().iterator();
		String nestedXMLPath = null;
		while (it.hasNext()) {
			String temp = it.next().toString();
			if (pathMatch(temp, rootPath)) {
				nestedXMLPath = temp;
				break;
			}
		}
		return nestedXMLPath;
	}

	/**
	 * Test if the current root path is acceptable as the root path of nested XML column,
	 * if so then return true else return false. 
	 * 
	 * @param path
	 * @param currentRootPath
	 * @param backRefNumber
	 * @return
	 */
	private boolean pathMatch(String path, String currentRootPath) {
		if (path == null)
			return false;
		String[] paths = path.split("/");
		String[] nestedXMLColumnPaths = currentRootPath.split("/");
		int commonRootLen = nestedXMLColumnPaths.length - backRefLevel;
		if (commonRootLen < 0 || paths.length != commonRootLen + this.forwardRefLevel)
			return false;

		for (int i = 0; i < commonRootLen; i++) {
			if (!paths[i].startsWith(nestedXMLColumnPaths[i]))
				return false;
		}

		if (isSimple && paths.length != commonRootLen)
			return false;
		return true;
	}
}