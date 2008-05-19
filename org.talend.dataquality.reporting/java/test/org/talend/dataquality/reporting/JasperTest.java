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
package org.talend.dataquality.reporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class JasperTest {

    public static void main(String args[]) {

        Connection connection;

        try {

            // load mysql driver

            Class.forName("com.mysql.jdbc.Driver");

            // makes connection to database

            // replace bn with your database name and ivms with username and ivms with password respectively.

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            JasperReportBuilder builder = new JasperReportBuilder();
            builder.setConnection(connection);
            builder.setJrxmlSource("dev_conf/jasperReport_demo.jrxml");
            builder.run();

        } catch (ClassNotFoundException classNotFoundException)

        {

            classNotFoundException.printStackTrace();

        } catch (SQLException sqlException)

        {

            sqlException.printStackTrace();

        }

    }
}
