// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.database;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public enum EDatabaseDriver4Version {
    // see feature 4720
    // just now , for oracle

    ORACLE_10("Oracle_10", "ojdbc5.jar"),
    ORACLE_9("Oracle_9", "ojdbc_for_9.jar"),
    ORACLE_8("Oracle_8", "ojdbc_for_8.jar");

    private String dbVersionName;

    /**
     * Getter for versionName.
     * 
     * @return the versionName
     */
    public String getDbVersionName() {
        return this.dbVersionName;
    }

    /**
     * Getter for driverName.
     * 
     * @return the driverName
     */
    public String getDriverName() {
        return this.driverName;
    }

    private String driverName;

    EDatabaseDriver4Version(String versionName, String driverName) {
        this.dbVersionName = versionName;
        this.driverName = driverName;
    }

    public static String getDriverByVersion(final String versionName) {
        for (EDatabaseDriver4Version driverVersion : values()) {
            if (driverVersion.getDbVersionName().equals(versionName)) {
                return driverVersion.getDriverName();
            }
        }
        return null;
    }

}
