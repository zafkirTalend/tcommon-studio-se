// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import java.util.Properties;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public class JDBCDriverLoader {

    /**
     * 
     * DOC YeXiaowei Comment method "getConnection".
     * 
     * @param jarPath
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    public Connection getConnection(String[] jarPath, String driverClassName, String url, String username, String password)
            throws Exception {

        HotClassLoader loader = new HotClassLoader();

        for (int i = 0; i < jarPath.length; i++) {
            loader.addPath(jarPath[i]);
        }

        DriverShim wapperDriver = null;

        try {
            Class<?> driver = Class.forName(driverClassName, true, loader);
            // Object driver = loader.loadClass(driverClassName).newInstance();
            wapperDriver = new DriverShim((Driver) (driver.newInstance()));

            // DriverManager.registerDriver(wapperDriver);
            Properties info = new Properties();
            info.put("user", username); //$NON-NLS-1$
            info.put("password", password); //$NON-NLS-1$
            Connection connection = wapperDriver.connect(url, info);
            // DriverManager.deregisterDriver(wapperDriver);

            return connection;

        } catch (Throwable e) {
            // if (wapperDriver != null) {
            // try {
            // // DriverManager.deregisterDriver(wapperDriver);
            // } catch (SQLException e1) {
            // ExceptionHandler.process(e);
            // }
            // }
            throw new RuntimeException(e);
        }

    }

    // public static void main(String[] args) {
    // // test IBM DB2
    //        String jarPath = "D:\\YeXiaowei\\workrela\\eclipse_3_3\\lib\\java\\db2jcc.jar"; //$NON-NLS-1$
    //        String driverClassName = "com.ibm.db2.jcc.DB2Driver"; //$NON-NLS-1$
    //        String url = "jdbc:db2://192.168.0.108:50000/talend"; //$NON-NLS-1$
    //        String username = "root"; //$NON-NLS-1$
    //        String password = "toor"; //$NON-NLS-1$
    //
    // JDBCDriverLoader loader = new JDBCDriverLoader();
    // Connection conn;
    // try {
    // conn = loader.getConnection(String[] jarPath, driverClassName, url, username, password);
    // } catch (Throwable e) {
    // e.printStackTrace();
    // }
    // // System.out.println(conn.getClass());
    // }

}
