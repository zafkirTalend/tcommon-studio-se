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
package org.talend.core.model.metadata.types;

import java.util.HashMap;
import java.util.Map;

import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.MetadataTalendType;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TypesManager {

    private static Map<String, String> perlTempMappingXmlToTalend;

    private static Map<String, String> perlTempMappingTalendToXml;

    private static boolean initDone = false;

    public static void initializePerlTypes() {
        if (!initDone) {
            perlTempMappingXmlToTalend = new HashMap<String, String>();
            perlTempMappingTalendToXml = new HashMap<String, String>();
            perlTempMappingXmlToTalend.put("number", "int");
            perlTempMappingXmlToTalend.put("decimal", "float");
            perlTempMappingXmlToTalend.put("boolean", "String"); // ?
            perlTempMappingXmlToTalend.put("string", "String");
            perlTempMappingXmlToTalend.put("date", "Day");

            perlTempMappingTalendToXml.put("char", "string");
            perlTempMappingTalendToXml.put("Day", "string");
            perlTempMappingTalendToXml.put("double", "decimal");
            perlTempMappingTalendToXml.put("float", "decimal");
            perlTempMappingTalendToXml.put("int", "number");
            perlTempMappingTalendToXml.put("long", "number");
            perlTempMappingTalendToXml.put("String", "string");
            initDone = true;
        }
    }

    public static String getNameFromInterfaceType(String type) {
        switch (LanguageManager.getCurrentLanguage()) {
        case JAVA:
            return type;
        default:
            initializePerlTypes();
            return perlTempMappingTalendToXml.get(type);
        }
    }
    
    public static String getTalendTypeFromXmlType(String type) {
        switch (LanguageManager.getCurrentLanguage()) {
        case JAVA:
            return type;
        default:
            initializePerlTypes();
            return perlTempMappingXmlToTalend.get(type);
        }
    }

    public static String getDBTypeFromTalendType(String dbms, String talendType) {
        String typeName = getNameFromInterfaceType(talendType);
        return MetadataTalendType.getMappingTypeRetriever(dbms).getDefaultSelectedDbType(typeName);
    }

    public static boolean checkDBType(String dbms, String talendType, String dbType) {
        String typeName = getNameFromInterfaceType(talendType);

        String defaultDbType = MetadataTalendType.getMappingTypeRetriever(dbms).getDefaultSelectedDbType(typeName);
        if (defaultDbType.equals(dbType)) {
            return true;
        }
        
        String defaultTalendType = MetadataTalendType.getMappingTypeRetriever(dbms).getDefaultSelectedTalendType(dbType);
        if (defaultTalendType.equals(typeName)) {
            return true;
        }
       return false;
    }
}
