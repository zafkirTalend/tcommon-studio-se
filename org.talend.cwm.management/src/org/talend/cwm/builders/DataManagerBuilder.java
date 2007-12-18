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

import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdDataManager;
import org.talend.cwm.softwaredeployment.TdMachine;
import org.talend.cwm.softwaredeployment.TdProviderConnection;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class DataManagerBuilder extends CwmBuilder {

    private final TdDataManager dataManager;

    private final TdProviderConnection providerConnection;

    private final TdMachine machine;

    /**
     * DOC scorreia DataManagerBuilder constructor comment.
     * 
     * @param conn
     * @throws SQLException
     */
    public DataManagerBuilder(Connection conn) throws SQLException {
        super(conn);
        this.dataManager = initializeDataManager();
        this.providerConnection = initializeProviderConnection();
        this.machine = initializeMachine();
    }

    /**
     * DOC scorreia Comment method "initializeMachine".
     * 
     * @return
     */
    private TdMachine initializeMachine() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * DOC scorreia Comment method "initializeProviderConnection".
     * 
     * @return
     * @throws SQLException
     */
    private TdProviderConnection initializeProviderConnection() throws SQLException {
        // TODO Auto-generated method stub

        boolean readOnly = connection.isReadOnly();

        TdProviderConnection provConn = SoftwaredeploymentFactory.eINSTANCE.createTdProviderConnection();
        // TODO scorreia set name
        provConn.setIsReadOnly(readOnly);
        return provConn;
    }

    /**
     * DOC scorreia Comment method "initializeDataManager".
     * 
     * @return
     */
    private TdDataManager initializeDataManager() {
        // TODO scorreia get data manager informations

        TdDataManager dataMgr = SoftwaredeploymentFactory.eINSTANCE.createTdDataManager();
        // TODO scorreia set a name?
        return dataMgr;
    }

}
