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
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.softwaredeployment.SoftwaredeploymentFactory;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import orgomg.cwm.objectmodel.core.TaggedValue;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class DataProviderBuilder extends CwmBuilder {

    private static Logger log = Logger.getLogger(DataProviderBuilder.class);

    private final TdDataProvider dataProvider;

    /**
     * DataProviderBuilder constructor.
     * 
     * @param conn the connection
     * @param driver the JDBC driver
     * @param databaseUrl the database connection string (must not be null)
     * @param driverProperties the properties passed to the driver (could be null)
     * @throws SQLException
     */
    public DataProviderBuilder(Connection conn, Driver driver, String databaseUrl, Properties driverProperties)
            throws SQLException {
        super(conn);
        this.dataProvider = initializeDataProvider(driver, databaseUrl, driverProperties);
    }

    // TODO scorreia ctor with TdProviderConnection

    private TdDataProvider initializeDataProvider(Driver driver, String databaseUrl, Properties driverProperties)
            throws SQLException {
        TdDataProvider provider = SoftwaredeploymentFactory.eINSTANCE.createTdDataProvider();
        provider.setName(driver.getClass().getName()); // TODO scorreia should data provider name be something else?

        // print driver properties
        // TODO scorreia adapt this code in order to store information in CWM ????
        DriverPropertyInfo[] driverProps = driver.getPropertyInfo(databaseUrl, driverProperties);
        for (int i = 0; i < driverProps.length; i++) {
            DriverPropertyInfo prop = driverProps[i];

            if (log.isDebugEnabled()) { // TODO use logger here
                log.debug("Prop description = " + prop.description);
                log.debug(prop.name + "=" + prop.value);
            }

            TaggedValue taggedValue = TaggedValueHelper.createTaggedValue(prop.name, prop.value);
            provider.getTaggedValue().add(taggedValue);

            if (log.isDebugEnabled()) {
                if (prop.choices != null) {
                    for (int j = 0; j < prop.choices.length; j++) {
                        log.debug("prop choice " + j + " = " + prop.choices[j]);
                    }
                }
            }
        }

        return provider;
    }

    public TdDataProvider getDataProvider() {
        return this.dataProvider;
    }
}
