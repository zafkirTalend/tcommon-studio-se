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
package org.talend.core.model.metadata.builder.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import org.talend.commons.exception.ExceptionHandler;

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
    public Connection getConnection(String jarPath, String driverClassName, String url, String username, String password) {

        HotClassLoader loader = new HotClassLoader();

        loader.addPath(jarPath);

        try {
            Object driver = loader.loadClass(driverClassName).newInstance();
            DriverShim wapperDriver = new DriverShim((Driver) driver);
            DriverManager.registerDriver(wapperDriver);
            Connection connection = DriverManager.getConnection(url, username, password);
            DriverManager.deregisterDriver(wapperDriver);

            return connection;

            // if (driver != null && driver instanceof Driver) {
            // Properties info = new Properties();
            // if (username != null) {
            // info.put("user", username);
            // }
            // if (password != null) {
            // info.put("password", password);
            // }
            //
            // return ((Driver) driver).connect(url, info);
            // }

            // return null;

        } catch (Exception e) {
            ExceptionHandler.process(e);
            return null;
        }

    }

    public static void main(String[] args) {
        // test IBM DB2
        String jarPath = "D:\\YeXiaowei\\workrela\\eclipse_3_3\\lib\\java\\db2jcc.jar";
        String driverClassName = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://192.168.0.108:50000/talend";
        String username = "root";
        String password = "toor";

        JDBCDriverLoader loader = new JDBCDriverLoader();
        Connection conn = loader.getConnection(jarPath, driverClassName, url, username, password);
        System.out.println(conn.getClass());
    }

}
