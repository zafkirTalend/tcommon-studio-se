// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.database.conn.template;

import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.version.EDatabaseVersion4Drivers;

/**
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Aug 8, 2012
 */
public class DbConnStrForHive extends DbConnStr {

    public static final String URL_TEMPLATE_FOR_HIVE = "jdbc:hive://<host>:<port>/<sid>";

    public static final String URL_TEMPLATE_FOR_HIVE_EMBEDDED = "jdbc:hive://";

    /**
     * DOC Marvin DbConnStrForHive constructor comment.
     * 
     * @param dbType
     * @param urlTemplate
     */
    DbConnStrForHive(EDatabaseTypeName dbType, String urlTemplate) {
        super(dbType, urlTemplate);
    }

    public DbConnStrForHive(EDatabaseTypeName dbType, String urlTemplate, EDatabaseVersion4Drivers[] hiveModes) {
        super(dbType, urlTemplate);
    }

    String getUrlTemplate(EDatabaseVersion4Drivers version) {
        if (version == EDatabaseVersion4Drivers.HIVE_EMBEDDED)
            return URL_TEMPLATE_FOR_HIVE_EMBEDDED;
        return super.getUrlTemplate(version);
    }

    String getUrlPattern(EDatabaseVersion4Drivers version) {
        if (version == EDatabaseVersion4Drivers.HIVE_EMBEDDED) {
            return calcPattern(URL_TEMPLATE_FOR_HIVE_EMBEDDED);
        }
        return super.getUrlPattern(version);
    }
}
