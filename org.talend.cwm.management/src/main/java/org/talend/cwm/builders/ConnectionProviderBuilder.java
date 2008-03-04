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
import java.sql.SQLException;
import java.util.Properties;

import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.cwm.softwaredeployment.TdProviderConnection;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class ConnectionProviderBuilder extends CwmBuilder {

    private final TdProviderConnection providerConnection;

    public ConnectionProviderBuilder(Connection conn, String dbUrl, String driverClassName, Properties props)
            throws SQLException {
        super(conn);
        this.providerConnection = DatabaseContentRetriever.getProviderConnection(dbUrl, driverClassName, props,
                connection);
    }

    /**
     * DOC scorreia Comment method "initializeProviderConnection".
     * 
     * @param props
     * @param driverClassName
     * @param dbUrl
     * 
     * @return
     * @throws SQLException
     */

    public TdProviderConnection getProviderConnection() {
        return this.providerConnection;
    }

}
