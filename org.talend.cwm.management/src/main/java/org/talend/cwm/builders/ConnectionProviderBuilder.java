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
import java.util.Enumeration;
import java.util.Properties;

import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import orgomg.cwm.objectmodel.core.TaggedValue;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class ConnectionProviderBuilder extends CwmBuilder {

    private final TdProviderConnection providerConnection;

    public ConnectionProviderBuilder(Connection conn, String dbUrl, String driverClassName, Properties props)
            throws SQLException {
        super(conn);
        this.providerConnection = initializeProviderConnection(dbUrl, driverClassName, props);
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
    private TdProviderConnection initializeProviderConnection(String dbUrl, String driverClassName, Properties props)
            throws SQLException {
        TdProviderConnection prov = SoftwaredeploymentFactory.eINSTANCE.createTdProviderConnection();
        prov.setDriverClassName(driverClassName);
        prov.setConnectionString(dbUrl);
        prov.setIsReadOnly(connection.isReadOnly());

        // ---add properties as tagged value of the provider connection.
        Enumeration<?> propertyNames = props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String key = propertyNames.nextElement().toString();
            String property = props.getProperty(key);
            TaggedValue taggedValue = TaggedValueHelper.createTaggedValue(key, property);
            prov.getTaggedValue().add(taggedValue);
        }

        // TODO scorreia set name? or let it be set outside of this class?

        return prov;
    }

    public TdProviderConnection getProviderConnection() {
        return this.providerConnection;
    }

}
