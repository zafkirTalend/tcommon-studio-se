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

import org.talend.cwm.db.connection.DBConnect;
import org.talend.cwm.db.connection.TalendCwmFactory;
import org.talend.cwm.management.connection.ConnectionParameters;
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

        Connection connection = null;
        try {
            connection = ConnectionUtils.createConnection(url, driverClassName, props);
            rc = (ConnectionUtils.isValid(connection));
        } catch (SQLException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (InstantiationException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (IllegalAccessException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (ClassNotFoundException e) {
            rc.setReturnCode(e.getMessage(), false);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    rc.setReturnCode(e.getMessage(), false);
                }
            }
        }

        return rc;
    }

    public static TdDataProvider createConnection(ConnectionParameters connectionParameters) {
        DBConnect connector = new DBConnect(connectionParameters);
        try {
            TdDataProvider dataProvider = TalendCwmFactory.createDataProvider(connector, connectionParameters
                    .getFolderProvider());
            // TODO scorreia set other parameters here
            dataProvider.setName(connectionParameters.getConnectionName());
            return dataProvider;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            connector.closeConnection();
            //MODRLI 2008-3-10
            connector.saveInFiles();
        }
        return null;
    }

}
