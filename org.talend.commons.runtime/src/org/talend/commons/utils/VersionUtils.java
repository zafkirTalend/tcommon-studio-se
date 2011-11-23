// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Represents a version. Contents a major and a minor version.<br/>
 * 
 * $Id: Version.java 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class VersionUtils {

    public static final String DEFAULT_VERSION = "0.1"; //$NON-NLS-1$

    public static int compareTo(String arg0, String arg1) {
        return new Version(arg0).compareTo(new Version(arg1));
    }

    public static String upMinor(String version) {
        Version toReturn = new Version(version);
        toReturn.upMinor();
        return toReturn.toString();
    }

    public static String upMajor(String version) {
        Version toReturn = new Version(version);
        toReturn.upMajor();
        return toReturn.toString();
    }

    public static String getVersion() {
        String version = System.getProperty("talend.studio.version"); //$NON-NLS-1$
        if (version == null || "".equals(version.trim())) { //$NON-NLS-1$
            Bundle bundle = Platform.getBundle("org.talend.commons.runtime"); //$NON-NLS-1$
            if (bundle != null) {
                version = (String) bundle.getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION);
            }
        }
        return version;
    }
}
