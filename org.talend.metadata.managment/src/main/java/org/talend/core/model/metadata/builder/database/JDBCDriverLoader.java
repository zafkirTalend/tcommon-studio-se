// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
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

import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.map.MultiKeyMap;
import org.talend.commons.utils.encoding.CharsetToolkit;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.version.EDatabaseVersion4Drivers;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public class JDBCDriverLoader {

    public static final String SHUTDOWN_PARAM = ";shutdown=true"; //$NON-NLS-1$

    private static MultiKeyMap classLoadersMap = new MultiKeyMap();

    /**
     * Loads the jars for hive embedded mode required, I do not think it is the better method to do this here. Due to
     * the limitation on code structure, I have to write this method to load the jar required by Hive. If metadata
     * connection part is refactored, developer could adjust this method. Added by Marvin Wang on Oct 24, 2012.
     * 
     * @param libraries
     * @param dbType
     * @param dbVersion
     */
    public void loadForHiveEmbedded(List<String> libraries, String dbType, String dbVersion) {
        boolean flog = EDatabaseVersion4Drivers.containTypeAndVersion(dbType, dbVersion);
        HotClassLoader loader = null;
        if (flog) {
            loader = getHotClassLoaderFromCache(dbType, dbVersion);
            if (loader == null) {
                loader = new HotClassLoader();
                for (int i = 0; i < libraries.size(); i++) {
                    loader.addPath(libraries.get(i));
                }
                classLoadersMap.put(dbType, dbVersion, loader);
            } else {
                URL[] urls = loader.getURLs();
                if (urls != null && urls.length > 0) {
                    for (URL url : urls) {
                        String urlPath = url.getPath();
                        for (int j = 0; j < libraries.size(); j++) {
                            if (urlPath != null && !"".equals(urlPath) && urlPath.equals(libraries.get(j))) {
                                loader.addPath(libraries.get(j));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * DOC msjian Comment method "getHotClassLoaderFromCache".
     * 
     * @param dbType
     * @param dbVersion
     * @return
     */
    public HotClassLoader getHotClassLoaderFromCache(String dbType, String dbVersion) {
        HotClassLoader loader;
        loader = (HotClassLoader) classLoadersMap.get(dbType, dbVersion);
        return loader;
    }

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
    public List getConnection(String[] jarPath, String driverClassName, String url, String username, String password,
            String dbType, String dbVersion, String additionalParams) throws Exception {
        // qli modified to fix the bug 7656.
        List list = new ArrayList();
        HotClassLoader loader;
        boolean flog = EDatabaseVersion4Drivers.containTypeAndVersion(dbType, dbVersion);
        if (flog) {
            loader = getHotClassLoaderFromCache(dbType, dbVersion);
            if (loader == null) {
                loader = new HotClassLoader();
                classLoadersMap.put(dbType, dbVersion, loader);
            } // else loader gotten from cache
        } else {
            loader = new HotClassLoader();
        }
        for (String element : jarPath) {
            // bug 17800 fixed: fix a problem of jdbc drivers used in the wizard.
            if (element.contains(";")) {
                String[] splittedPath = element.split(";");
                for (String element2 : splittedPath) {
                    loader.addPath(element2);
                }
            } else {
                loader.addPath(element);
            }
        }

        DriverShim wapperDriver = null;
        Connection connection = null;
        try {
            Class<?> driver = Class.forName(driverClassName, true, loader);
            wapperDriver = new DriverShim((Driver) (driver.newInstance()));
            // Object driver = loader.loadClass(driverClassName).newInstance();
            // wapperDriver = new DriverShim((Driver) (driver));
            Properties info = new Properties();

            // to avoid NPE
            username = username != null ? username : "";
            password = password != null ? password : "";
            info.put("user", username); //$NON-NLS-1$
            info.put("password", password); //$NON-NLS-1$
            if (dbType.equals(EDatabaseTypeName.ACCESS.getXmlName()) || dbType.equals(EDatabaseTypeName.GODBC.getXmlName())) {
                Charset systemCharset = CharsetToolkit.getInternalSystemCharset();
                if (systemCharset != null && systemCharset.displayName() != null) {
                    info.put("charSet", systemCharset.displayName()); //$NON-NLS-1$
                }
            }

            if (additionalParams != null && !"".equals(additionalParams) && dbType.toUpperCase().contains("ORACLE")) {
                additionalParams = additionalParams.replaceAll("&", "\n");//$NON-NLS-1$//$NON-NLS-2$
                info.load(new java.io.ByteArrayInputStream(additionalParams.getBytes()));
                connection = wapperDriver.connect(url, info);

            } else {
                if (dbType.equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName())
                        && additionalParams.indexOf(SHUTDOWN_PARAM) == -1) {
                    url = url + SHUTDOWN_PARAM;
                }
                // MOD klliu TDQ-4659 sso could not check passed.2012-02-10
                // if (dbType.equals(EDatabaseTypeName.MSSQL.getDisplayName())) {
                // connection = ConnectionUtils.createConnection(url, (Driver) (driver.newInstance()), info);
                // } else {
                // we need to change later when TDQ supports hive embedded mode.
                // Changed by Marvin Wang on Oct. 6, 2012 for bug TDI-24027, because for hive standalone mode, it also
                // need get a class loader from the current thread.
                if (EDatabaseTypeName.HIVE.getDisplayName().equals(dbType)) {
                    Thread.currentThread().setContextClassLoader(loader);
                }
                connection = wapperDriver.connect(url, info);
            }
            // }
            // DriverManager.deregisterDriver(wapperDriver);
            // bug 9162
            list.add(connection);
            list.add(wapperDriver);
            return list;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

}
