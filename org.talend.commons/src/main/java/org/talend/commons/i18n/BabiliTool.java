// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * DOC wzhang class global comment. Detailled comment
 */
public final class BabiliTool {

    private static Map<String, String> translation = new HashMap<String, String>();

    public static void storeBabiliTranslation(String key, String bundleName, String textTranslated) {
        translation.put(key + ";" + bundleName, textTranslated); //$NON-NLS-1$
    }

    // public static void storeBabiliTranslation(Map<String, String> translations) {
    // translation = new HashMap<String, String>(translation);
    // }

    public static String getBabiliTranslation(String key, String bundleName) {
        Set<String> keys = translation.keySet();

        for (String currentKey : keys) {
            String[] splittedKey = currentKey.split(";"); //$NON-NLS-1$
            if (splittedKey[0].equals(key)) {
                if (bundleName == null) {
                    return translation.get(currentKey);
                } else {
                    if (splittedKey[1].startsWith(bundleName)) {
                        return translation.get(currentKey);
                    }
                }
            }
        }

        return null;
    }

}
