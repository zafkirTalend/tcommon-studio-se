// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.metadata.managment.mdm;

import java.util.ArrayList;
import java.util.List;

/**
 * created by wchen on Apr 15, 2015 Detailled comment
 *
 */
public enum MDMVersions {
    MDM_S56("s56", "Version 5.6"),
    MDM_S60("s60", "Version 6.0");

    String key;

    String displayName;

    MDMVersions(String key, String displayName) {
        this.key = key;
        this.displayName = displayName;
    }

    public String getDispalyName() {
        return displayName;
    }

    public String getKey() {
        return key;
    }

    public static String getKey(String displayName) {
        for (MDMVersions version : MDMVersions.values()) {
            if (version.displayName.equals(displayName)) {
                return version.key;
            }
        }
        return "";
    }

    public static List<String> getVersions() {
        List<String> versions = new ArrayList<String>();
        for (MDMVersions version : MDMVersions.values()) {
            versions.add(version.displayName);
        }
        return versions;
    }

}
