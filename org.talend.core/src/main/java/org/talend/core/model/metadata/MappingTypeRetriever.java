// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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

import org.apache.log4j.Logger;
import org.talend.core.language.LanguageManager;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MappingTypeRetriever {

    private static Logger log = Logger.getLogger(MappingTypeRetriever.class);
    
    private Dbms dbms;

    private Map<MappingType, MappingType> mapDbToTalend = new HashMap<MappingType, MappingType>();

    private Map<MappingType, MappingType> mapTalendToDb = new HashMap<MappingType, MappingType>();

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
        List<MappingType> mappingTypes = dbms.getMappingTypes();
        int lstSize = mappingTypes.size();
        mapDbToTalend.clear();
        mapTalendToDb.clear();
        for (int i = 0; i < lstSize; i++) {
            MappingType mappingType = mappingTypes.get(i);

            MappingType mapDbToTalendKey = new MappingType();
            mapDbToTalendKey.setDbType(mappingType.getDbType().toUpperCase());
            mapDbToTalendKey.setNullable(mappingType.getNullable());
            mapDbToTalendKey.setDefaultSelected(mappingType.getDefaultSelected());
            mapDbToTalend.put(mapDbToTalendKey, mappingType);

            MappingType mapTalendToDbKey = new MappingType();
            mapTalendToDbKey.setTalendTypeName(mappingType.getTalendTypeName());
            mapTalendToDbKey.setNullable(mappingType.getNullable());
            mapTalendToDbKey.setDefaultSelected(mappingType.getDefaultSelected());
            mapTalendToDb.put(mapTalendToDbKey, mappingType);
        }
    }

    public MappingType getMappingType(String dbmsType, String talendType, Boolean nullable, Boolean defaultSelected) {
        mappingTypeKey.setDbType(dbmsType.toUpperCase());
        mappingTypeKey.setTalendTypeName(talendType);
        mappingTypeKey.setNullable(nullable);
        mappingTypeKey.setDefaultSelected(defaultSelected);
        MappingType mappingType = mapDbToTalend.get(mappingTypeKey);
        return mappingType;
    }

    /**
     * 
     * Search and return the Db type which matches with the given parameters.
     * If the Db type is not found, a new search is done with inverse of given <code>nullable</code>
     * @param dbmsType
     * @param nullable
     * @return
     */
    public String getDefaultSelectedTalendType(String dbmsType, boolean nullable) {
        mappingTypeKey.setDbType(dbmsType.toUpperCase());
        mappingTypeKey.setTalendTypeName(null);
        mappingTypeKey.setNullable(nullable);
        mappingTypeKey.setDefaultSelected(Boolean.TRUE);
        MappingType mappingType = mapDbToTalend.get(mappingTypeKey);
        if (mappingType == null) {
            mappingTypeKey.setNullable(!nullable);
            mappingType = mapDbToTalend.get(mappingTypeKey);
            if (mappingType == null) {
                mappingTypeKey.setDefaultSelected(Boolean.FALSE);
                mappingType = mapDbToTalend.get(mappingTypeKey);
                if (mappingType == null) {
                    mappingTypeKey.setNullable(nullable);
                    mappingType = mapDbToTalend.get(mappingTypeKey);
                    if (mappingType == null) {
                        return MetadataTalendType.getDefaultTalendType();
                    }
                }
            }
        }
        return mappingType.getTalendTypeName();
    }

    /**
     * 
     * Search and return the Db type which matches with the given parameters.
     * If the Db type is not found, a new search is done with inverse of given <code>nullable</code>
     * @param talendType
     * @param nullable
     * @return
     */
    public String getDefaultSelectedDbType(String talendType, boolean nullable) {
        mappingTypeKey.setDbType(null);
        mappingTypeKey.setTalendTypeName(talendType);
        mappingTypeKey.setNullable(nullable);
        mappingTypeKey.setDefaultSelected(Boolean.TRUE);
        MappingType mappingType = mapTalendToDb.get(mappingTypeKey);
        if (mappingType == null) {
            mappingTypeKey.setNullable(!nullable);
            mappingType = mapTalendToDb.get(mappingTypeKey);
            if (mappingType == null) {
                return dbms.getDefaultDbType();
            }
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
     * @param dbms the dbms to set
     */
    public void setDbms(Dbms dbms) {
        this.dbms = dbms;
        init(dbms);
    }

}
