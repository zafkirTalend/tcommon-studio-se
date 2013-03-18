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
package org.talend.metadata.managment.hive;

import org.talend.core.classloader.ClassLoaderFactory;
import org.talend.core.database.conn.ConnParameterKeys;
import org.talend.core.model.metadata.IMetadataConnection;

/**
 * Created by Marvin Wang on Mar 12, 2013.
 */
public class HiveClassLoaderFactory {

    private static HiveClassLoaderFactory instance = null;

    private HiveClassLoaderFactory() {
    }

    /**
     * Gets an instance of <code>HiveClassLoaderFactory</code>, which is synchronized. Added by Marvin Wang on Mar 12,
     * 2013.
     * 
     * @return
     */
    public static synchronized HiveClassLoaderFactory getInstance() {
        if (instance == null) {
            instance = new HiveClassLoaderFactory();
        }
        return instance;
    }

    /**
     * Gets a class loader for different version by the given parameters. Added by Marvin Wang on Mar 12, 2013.
     * 
     * @param distribution
     * @param distroVersion
     * @param model
     * @return
     */
    public ClassLoader getClassLoader(String distribution, String distroVersion, String model) {
        String index = "HIVE" + ":" + distribution + ":" + distroVersion + ":" + model; //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$ //$NON-NLS-4$ 
        ClassLoader loader = ClassLoaderFactory.getClassLoader(index);
        // If there is no class loader gotten by index, then use the current classloader.
        if (loader == null) {
            loader = HiveClassLoaderFactory.class.getClassLoader();
        }
        return loader;
    }

    /**
     * Gets an instance of <code>ClassLoader</code> by the given argument, it invokes {
     * {@link #getClassLoader(String, String, String)}. The parameters required by {
     * {@link #getClassLoader(String, String, String)} are fetched from {@link IMetadataConnection}. Added by Marvin
     * Wang on Mar 13, 2013.
     * 
     * @param metadataConn
     * @return
     */
    public ClassLoader getClassLoader(IMetadataConnection metadataConn) {
        // For hive embedded model, all parameters below should not be null, if null, then need to check.
        String distro = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_DISTRIBUTION);
        String distroVersion = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_VERSION);
        String hiveModel = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_MODE);
        return getClassLoader(distro, distroVersion, hiveModel);
    }

    public ClassLoader getClassLoader(String distribution, String distroVersion, String hiveVersion, String model) {
        String index = "HIVE" + ":" + distribution + ":" + distroVersion + ":" + hiveVersion + ":" + model; //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        ClassLoader loader = ClassLoaderFactory.getClassLoader(index);
        if (loader == null) {
            loader = HiveClassLoaderFactory.class.getClassLoader();
        }
        return loader;
    }
}
