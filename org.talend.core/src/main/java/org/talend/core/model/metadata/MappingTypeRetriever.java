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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MappingTypeRetriever {

	private static Logger log = Logger.getLogger(MappingTypeRetriever.class);

	private Dbms dbms;

	private MultiMap mapDbToTalendTypes = new MultiValueMap();

	private MultiMap mapTalendToDbTypes = new MultiValueMap();

	private Map<MappingType, MappingType> defaultMappings = new HashMap<MappingType, MappingType>();

	private MappingType mappingTypeKey = new MappingType();

	/**
	 * DOC amaumont MappingTypeHelper constructor comment.
	 * 
	 * @param dbms
	 */
	public MappingTypeRetriever(Dbms dbms) {
		super();
		this.dbms = dbms;
		init(dbms);
	}

	/**
	 * DOC amaumont Comment method "init".
	 */
	private void init(Dbms dbms) {
		Set<MappingType> mappingTypes = dbms.getTalendToDbTypes();
		defaultMappings.clear();
		mapTalendToDbTypes.clear();

		for (MappingType mappingType : mappingTypes) {

			MappingType mapTalendToDbKey = new MappingType();
			mapTalendToDbKey.setTalendType(mappingType.getTalendType());
			mapTalendToDbTypes.put(mapTalendToDbKey, mappingType);

			if (mappingType.getDefaultSelected()) {
				mapTalendToDbKey = new MappingType();
				mapTalendToDbKey.setTalendType(mappingType.getTalendType());
				mapTalendToDbKey.setDefaultSelected(mappingType
						.getDefaultSelected());
				defaultMappings.put(mapTalendToDbKey, mappingType);
			}

		}

		mappingTypes = dbms.getDbToTalendTypes();
		mapDbToTalendTypes.clear();
		for (MappingType mappingType : mappingTypes) {

			MappingType mapDbToTalendKey = new MappingType();
			mapDbToTalendKey.setDbType(mappingType.getDbType());
			mapDbToTalendTypes.put(mapDbToTalendKey, mappingType);

			if (mappingType.getDefaultSelected()) {
				mapDbToTalendKey = new MappingType();
				mapDbToTalendKey.setDbType(mappingType.getDbType()
						.toUpperCase());
				mapDbToTalendKey.setDefaultSelected(mappingType
						.getDefaultSelected());
				defaultMappings.put(mapDbToTalendKey, mappingType);
			}
		}
	}

	/**
	 * Returns adviced mappings for a given db type.
	 * 
	 * @param dbType
	 * @return a list of MappingType or null if not found
	 */
	public List<MappingType> getAdvicedDbToTalendTypes(String dbType) {
		mappingTypeKey.setDbType(dbType.toUpperCase());
		mappingTypeKey.setTalendType(null);
		mappingTypeKey.setDefaultSelected(null);
		mapDbToTalendTypes.get(mappingTypeKey);
		return (List<MappingType>) mapDbToTalendTypes.get(mappingTypeKey);
	}

	/**
	 * Returns adviced mappings for a given Talend type.
	 * 
	 * @param talendType
	 * @return a list of MappingType or null if not found
	 */
	public List<MappingType> getAdvicedTalendToDbTypes(String talendType) {
		mappingTypeKey.setDbType(null);
		mappingTypeKey.setTalendType(talendType);
		mappingTypeKey.setDefaultSelected(null);
		mapTalendToDbTypes.get(mappingTypeKey);
		return (List<MappingType>) mapTalendToDbTypes.get(mappingTypeKey);
	}

	public boolean isAdvicedTalendToDbType(String talendType, String dbType) {
		List<MappingType> advicedTalendToDbTypes = getAdvicedTalendToDbTypes(talendType);
		if (advicedTalendToDbTypes == null) {
			return false;
		}
		int advicedTalendToDbTypesListSize = advicedTalendToDbTypes.size();
		for (int i = 0; i < advicedTalendToDbTypesListSize; i++) {
			MappingType type = advicedTalendToDbTypes.get(i);
			if (type.getDbType().equals(dbType)) {
				return true;
			}
		}
		return false;
	}

	public boolean isAdvicedDBToTalendType(String dbType, String talendType) {
		List<MappingType> advicedDbToTalendTypes = getAdvicedDbToTalendTypes(dbType);
		if (advicedDbToTalendTypes == null) {
			return false;
		}
		int advicedDbToTalendTypesListSize = advicedDbToTalendTypes.size();
		for (int i = 0; i < advicedDbToTalendTypesListSize; i++) {
			MappingType type = advicedDbToTalendTypes.get(i);
			if (type.getDbType().equals(dbType)) {
				return true;
			}
		}
		return false;
	}

	public boolean isLengthIgnored(String dbmsId, String dbType) {
		Dbms dbms = MetadataTalendType.getDbms(dbmsId);
		List ignoreLP = dbms.getIgnoreLengthPrecision();
		String ignore = new String("");
		for (int i = 0; i < ignoreLP.size(); i++) {
			DbIgnoreLengthAndPrecision dbIgnore = (DbIgnoreLengthAndPrecision) ignoreLP
					.get(i);
			if (dbIgnore.getDbType().equals(dbType)) {
				ignore = dbIgnore.getIgnoreLength();
				if (ignore == null) {
					return false;
				} else if (ignore.equals("true")) {
					return true;
				} else {
					return false;
				}
			}// end if
		}// end for
		return false;
	}

	public boolean isPrecisionIgnored(String dbmsId, String dbType) {
		Dbms dbms = MetadataTalendType.getDbms(dbmsId);
		List ignoreLP = dbms.getIgnoreLengthPrecision();
		String ignore = new String("");
		for (int i = 0; i < ignoreLP.size(); i++) {
			DbIgnoreLengthAndPrecision dbIgnore = (DbIgnoreLengthAndPrecision) ignoreLP
					.get(i);
			if (dbIgnore.getDbType().equals(dbType)) {
				ignore = dbIgnore.getIgnorePrecision();
				if (ignore == null) {
					return false;
				} else if (ignore.equals("true")) {
					return true;
				} else {
					return false;
				}
			}// end if
		}// end for
		return false;
	}

	public boolean isPreBeforeLength(String dbmsId, String dbType) {
		Dbms dbms = MetadataTalendType.getDbms(dbmsId);
		List preBeforeLen = dbms.getPrebeforelength();
		String before = new String("");
		for (int i = 0; i < preBeforeLen.size(); i++) {
			DbPreBeforeLength dbPBL = (DbPreBeforeLength) preBeforeLen.get(i);
			if (dbPBL.getDbType().equals(dbType)) {
				before = dbPBL.getPreBeforeLen();
				if (before == null)
					return false;
				if (before.equals("true"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	/**
	 * 
	 * Search and return the Db type which matches with the given parameters. If
	 * the Db type is not found, a new search is done with inverse of given
	 * <code>nullable</code>
	 * 
	 * @param dbmsType
	 * @param nullable
	 * @return
	 */
	public String getDefaultSelectedTalendType(String dbmsType) {
		if (dbmsType == null) {
			return MetadataTalendType.getDefaultTalendType();
		}
		mappingTypeKey.setDbType(dbmsType.toUpperCase());
		mappingTypeKey.setTalendType(null);
		mappingTypeKey.setDefaultSelected(Boolean.TRUE);
		MappingType mappingType = defaultMappings.get(mappingTypeKey);
		if (mappingType == null) {
			mappingTypeKey.setDefaultSelected(Boolean.FALSE);
			mappingType = defaultMappings.get(mappingTypeKey);
			if (mappingType == null) {
				return MetadataTalendType.getDefaultTalendType();
			}
		}
		return mappingType.getTalendType();
	}

	/**
	 * 
	 * Search and return the Db type which matches with the given parameters. If
	 * the Db type is not found, a new search is done with inverse of given
	 * <code>nullable</code>
	 * 
	 * @param talendType
	 * @param nullable
	 * @return
	 */
	public String getDefaultSelectedDbType(String talendType) {
		mappingTypeKey.setDbType(null);
		mappingTypeKey.setTalendType(talendType);
		mappingTypeKey.setDefaultSelected(Boolean.TRUE);
		MappingType mappingType = defaultMappings.get(mappingTypeKey);
		if (mappingType == null) {
			return dbms.getDefaultDbType();
		}
		return mappingType.getDbType();

	}

	/**
	 * Getter for the current loaded dbms.
	 * 
	 * @return the dbms
	 */
	public Dbms getDbms() {
		return this.dbms;
	}

	/**
	 * Sets the dbms.
	 * 
	 * @param dbms
	 *            the dbms to set
	 */
	public void setDbms(Dbms dbms) {
		this.dbms = dbms;
		init(dbms);
	}

}
