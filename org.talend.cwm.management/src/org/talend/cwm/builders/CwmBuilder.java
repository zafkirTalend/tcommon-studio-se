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
package org.talend.cwm.builders;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author scorreia
 * 
 * This class is used by children for converting a java.sql.connection into CWM classes.
 */
abstract class CwmBuilder {

    protected final DatabaseMetaData databaseMetadata;

    protected final Connection connection;

    /**
     * CwmBuilder constructor.
     * 
     * @param conn a connection
     */
    public CwmBuilder(Connection conn) throws SQLException {
        this.connection = conn;
        this.databaseMetadata = connection.getMetaData();
    }

    protected void print(String tag, String str) { // for tests only
        System.out.println(tag + " " + str);
    }

}
