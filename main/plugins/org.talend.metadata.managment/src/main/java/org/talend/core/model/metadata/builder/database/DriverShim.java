// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public class DriverShim implements Driver {

    private Driver driver;

    public DriverShim(Driver d) {
        this.driver = d;
    }

    public boolean acceptsURL(String u) throws SQLException {
        return this.driver.acceptsURL(u);
    }

    public Connection connect(String u, Properties p) throws SQLException {
        return this.driver.connect(u, p);
    }

    public int getMajorVersion() {
        return this.driver.getMajorVersion();
    }

    public int getMinorVersion() {
        return this.driver.getMinorVersion();
    }

    public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
        return this.driver.getPropertyInfo(u, p);
    }

    public boolean jdbcCompliant() {
        return this.driver.jdbcCompliant();
    }
}
