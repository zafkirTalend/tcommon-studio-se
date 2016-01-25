// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.database.hbase.conn.version;

import java.util.ArrayList;
import java.util.List;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public enum EHBaseDistributions {

    HORTONWORKS("HortonWorks"),

    CLOUDERA("Cloudera"),

    MAPR("MapR"),

    APACHE("Apache"),

    AMAZON_EMR("AmazonEMR"),

    PIVOTAL_HD("Pivotal HD"),

    CUSTOM("Custom - Unsupported");

    private String displayName;

    EHBaseDistributions(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static List<String> getAllDistributionDisplayNames() {
        return getAllDistributionNames(true);
    }

    public static List<String> getAllDistributionNames(boolean display) {
        List<String> names = new ArrayList<String>();
        EHBaseDistributions[] values = values();
        for (EHBaseDistributions distribution : values) {
            if (display) {
                names.add(distribution.getDisplayName());
            } else {
                names.add(distribution.getName());
            }
        }

        return names;
    }

    public static EHBaseDistributions getDistributionByDisplayName(String name) {
        return getDistributionByName(name, true);
    }

    public static EHBaseDistributions getDistributionByName(String name, boolean display) {
        if (name != null) {
            for (EHBaseDistributions distribution : values()) {
                if (display) {
                    if (name.equalsIgnoreCase(distribution.getDisplayName())) {
                        return distribution;
                    }
                } else {
                    if (name.equalsIgnoreCase(distribution.getName())) {
                        return distribution;
                    }
                }
            }
        }
        return null;
    }

}
