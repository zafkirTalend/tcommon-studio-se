// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata;

import java.util.Hashtable;
import java.util.List;

/**
 * created by rdubois on 30 juil. 2015 Detailled comment
 *
 */
public abstract class MetadataTalendTypeFilter {

    /**
     * This method returns a List of String, representing the data types that must not be shown in the Talend schema.
     * DOC rdubois Comment method "getUnsupportedTypes".
     * 
     * @return
     */
    protected abstract List<String> getUnsupportedTypes();

    /**
     * This method has the responsibility to remove the unsupported type from the Talend schema. DOC rdubois Comment
     * method "filter".
     * 
     * @param arrayTalendTypes
     * @return
     */
    public String[] filter(String[] arrayTalendTypes) {
        Hashtable<Integer, String> hash = new Hashtable<>();
        List<String> unsupportedTypes = getUnsupportedTypes();
        int index = 0;
        for (int i = 0; i < arrayTalendTypes.length; i++) {
            if (!unsupportedTypes.contains(arrayTalendTypes[i])) {
                hash.put(index++, arrayTalendTypes[i]);
            }
        }
        String[] arrayTalendTypesNew = new String[hash.size()];
        for (int j = 0; j < hash.size(); j++) {
            arrayTalendTypesNew[j] = hash.get(j);
        }
        return arrayTalendTypesNew;
    }
}
