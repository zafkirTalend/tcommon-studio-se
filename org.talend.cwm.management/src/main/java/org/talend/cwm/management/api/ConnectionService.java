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
package org.talend.cwm.management.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.talend.cwm.db.connection.ConnectionParameters;
import org.talend.cwm.db.connection.DBConnect;
import org.talend.cwm.db.connection.TalendCwmFactory;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sugars.ReturnCode;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public final class ConnectionService {

    private ConnectionService() {
    }

    public static ReturnCode checkConnection(String url, String driverClassName, Properties props) {
        ReturnCode rc = new ReturnCode();
        rc.setOk(true); // everything is ok at the beginning.
        Connection connection = null;
        try {
            connection = ConnectionUtils.createConnection(url, driverClassName, props);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (!rc.isOk()) {
            return rc;
        }
        rc = ConnectionUtils.isValid(connection);
        return rc;
    }

    public static TdDataProvider createConnection(ConnectionParameters connectionParameters) {
        DBConnect connector = new DBConnect(connectionParameters);
        try {
            return TalendCwmFactory.createDataProvider(connector, connectionParameters.getFolderProvider());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
        return null;
    }
}
