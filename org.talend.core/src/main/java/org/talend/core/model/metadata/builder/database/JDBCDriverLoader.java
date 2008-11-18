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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.core.CorePlugin;

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
    public Connection getConnection(String jarPath, String driverClassName, String url, String username, String password)
            throws Exception {

        HotClassLoader loader = new HotClassLoader();

        loader.addPath(jarPath);

        DriverShim wapperDriver = null;

        try {
            Class<?> driver = Class.forName(driverClassName, true, loader);
            // Object driver = loader.loadClass(driverClassName).newInstance();
            wapperDriver = new DriverShim((Driver) (driver.newInstance()));

            // DriverManager.registerDriver(wapperDriver);
            Properties info = new Properties();
            info.put("user", username);
            info.put("password", password);
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

    /**
     * 
     * ggu Comment method "getConnectionBySpecialWays".
     * 
     */
    public Connection getConnectionBySpecialWays(String dbType, String url, String username, String pwd, final String driverClass)
            throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", pwd);

        Class<?> klazz = null;
        DriverShim wapperDriver = null;
        /*
         * 1.
         */

        try {
            klazz = Class.forName(driverClass);
        } catch (Throwable e) {
            //
        }
        if (klazz != null) {
            wapperDriver = new DriverShim((Driver) (klazz.newInstance()));
            return wapperDriver.connect(url, info);
        }

        /*
         * 2.
         */
        final String jdbcJar = "nzjdbc.jar"; // only for netezza so far.
        final String libPluginId = "org.talend.libraries";

        List<String> jdbcJarNames = new ArrayList<String>();
        jdbcJarNames.add(jdbcJar);

        URLClassLoader classLoader = createClassLoaders(libPluginId, jdbcJarNames);
        try {
            klazz = Class.forName(driverClass, true, classLoader);
        } catch (ClassNotFoundException e) {
            //
        }
        if (klazz != null) {
            wapperDriver = new DriverShim((Driver) (klazz.newInstance()));
            return wapperDriver.connect(url, info);
        }

        /*
         * 3.
         */

        return getConnection(findJDBCInTOSLib(jdbcJar), driverClass, url, username, pwd);
    }

    private String findJDBCInTOSLib(final String jdbcJarName) {
        String path = CorePlugin.getDefault().getLibrariesService().getJavaLibrariesPath();
        File jar = new File(path, jdbcJarName);
        if (jar.exists()) {
            return jar.getAbsolutePath();
        }
        return null;
    }

    private URLClassLoader createClassLoaders(final String pluginId, final List<String> jdbcJarNames) throws IOException {
        if (pluginId == null || jdbcJarNames == null) {
            return null;
        }
        Bundle b = Platform.getBundle(pluginId);
        if (b == null) {
            return null;
        }
        List<URL> jars = new ArrayList<URL>();

        for (String jarName : jdbcJarNames) {
            URL jarUrl = FileLocator.find(b, new Path(jarName), null);

            if (jarUrl != null) {
                jarUrl = FileLocator.toFileURL(jarUrl);
                jars.add(jarUrl);
            }

        }
        if (!jars.isEmpty()) {
            URLClassLoader classLoader = new URLClassLoader(jars.toArray(new URL[0]));
            return classLoader;
        }
        return null;
    }

    public static void main(String[] args) {
        // test IBM DB2
        String jarPath = "D:\\YeXiaowei\\workrela\\eclipse_3_3\\lib\\java\\db2jcc.jar";
        String driverClassName = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://192.168.0.108:50000/talend";
        String username = "root";
        String password = "toor";

        JDBCDriverLoader loader = new JDBCDriverLoader();
        Connection conn;
        try {
            conn = loader.getConnection(jarPath, driverClassName, url, username, password);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // System.out.println(conn.getClass());
    }

}
