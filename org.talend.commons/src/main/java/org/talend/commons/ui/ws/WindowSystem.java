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
package org.talend.commons.ui.ws;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;

/**
 * 
 * DOC root class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
@SuppressWarnings("restriction")
public class WindowSystem {

    private static String ws;

    static {
        BundleContext bundleContext = InternalPlatform.getDefault().getBundleContext();
        if (bundleContext != null) {
            ws = Platform.getWS();
        }

    }

    public static boolean isGTK() {
        return Platform.WS_GTK.equals(ws);
    }

    /**
     * .
     * 
     * @return true if WIN32 or eclipse bundle is null
     */
    public static boolean isWIN32() {
        return Platform.WS_WIN32.equals(ws) || ws == null;
    }

}
