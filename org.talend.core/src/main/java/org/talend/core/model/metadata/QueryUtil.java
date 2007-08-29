// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.metadata;

import java.util.List;

import org.talend.core.language.LanguageManager;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class QueryUtil {

	public static final String DEFAULT_TABLE_NAME = "_MyTable_";

	public static String generateNewQuery(Element node,
			IMetadataTable repositoryMetadata, String dbType, String schema,
			String realTableName) {
		String tableName = getTableName(node, repositoryMetadata, schema,
				dbType, realTableName);
		return generateNewQuery(repositoryMetadata, dbType, tableName);
	}

	public static String generateNewQuery(IMetadataTable repositoryMetadata,
			String dbType, String tableName) {
		List<IMetadataColumn> metaDataColumnList = repositoryMetadata
				.getListColumns();
		int index = metaDataColumnList.size();
		if (index == 0) {
			return "";
		}

		StringBuffer query = new StringBuffer();
		String enter = "\n";
		String space = " ";
		query.append("SELECT").append(space);

		String tableNameForColumnSuffix = TalendTextUtils
				.addQuotesWithSpaceField(tableName, dbType)
				+ ".";

		for (int i = 0; i < metaDataColumnList.size(); i++) {
			IMetadataColumn metaDataColumn = metaDataColumnList.get(i);
			String columnName = TalendTextUtils.addQuotesWithSpaceField(
					getColumnName(metaDataColumn.getOriginalDbColumnName(),
							dbType), dbType);
			if (i != index - 1) {
				query.append(tableNameForColumnSuffix).append(columnName)
						.append(",").append(space);
			} else {
				query.append(tableNameForColumnSuffix).append(columnName)
						.append(space);
			}
		}
		query.append(enter).append("FROM").append(space).append(
				TalendTextUtils.addQuotesWithSpaceField(tableName, dbType));

		return query.toString();
	}

	private static String getColumnName(String name, String dbType) {
		String nameAfterChanged;
		if (name.startsWith("\"") && name.endsWith("\"")) {
			nameAfterChanged = name;
		} else {
			if (!dbType.equalsIgnoreCase("PostgreSQL")) {
				nameAfterChanged = name;
			} else {
				nameAfterChanged = "\"" + name + "\"";
			}
		}
		return nameAfterChanged;
	}

	public static String getTableName(Element node,
			IMetadataTable repositoryMetadata, String schema, String dbType,
			String realTableName) {
		String currentTableName;
		String dbTableName = getDbTableName(node);
		if (dbTableName != null) {
			switch (LanguageManager.getCurrentLanguage()) {
			case JAVA:
				if (dbTableName.contains(TalendTextUtils.QUOTATION_MARK)) {
					if (dbTableName.startsWith(TalendTextUtils.QUOTATION_MARK)
							&& dbTableName
									.endsWith(TalendTextUtils.QUOTATION_MARK)
							&& dbTableName.length() > 2) {
						return dbTableName.substring(1,
								dbTableName.length() - 1);
					} else {
						currentTableName = null;
					}
				} else {
					currentTableName = dbTableName;
				}
				break;
			default:
				if (dbTableName.contains(TalendTextUtils.SINGLE_QUOTE)) {
					if (dbTableName.startsWith(TalendTextUtils.SINGLE_QUOTE)
							&& dbTableName
									.endsWith(TalendTextUtils.SINGLE_QUOTE)
							&& dbTableName.length() > 2) {
						return dbTableName.substring(1,
								dbTableName.length() - 1);
					} else {
						currentTableName = null;
					}
				} else {
					currentTableName = dbTableName;
				}
			}
		}
		currentTableName = realTableName;
		if (schema != null && schema.length() > 0) {
			if (dbType.equalsIgnoreCase("PostgreSQL")) {
				currentTableName = "\"" + schema + "\"" + "." + "\""
						+ currentTableName + "\"";
				return currentTableName;
			}
		}
		if (currentTableName == null) {
			currentTableName = DEFAULT_TABLE_NAME;
			return currentTableName;
		}
		return currentTableName;
	}

	private static String getDbTableName(Element node) {
		IElementParameter param = node
				.getElementParameterFromField(EParameterFieldType.DBTABLE);
		if (param != null && param.isShow(node.getElementParameters())) {
			return (String) param.getValue();
		}
		return null;
	}
}
