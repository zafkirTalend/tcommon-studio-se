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
package org.talend.commons.debug;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.framework.debug.Debug;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.CommonExceptionHandler;

/**
 * ggu class global comment. Detailled comment
 * 
 * add the function for debug "--talendDebug"
 */
public final class TalendDebugHandler {
    public static final String TALEND_DEBUG="--talendDebug"; //$NON-NLS-1$
    /**
     * 
     * ggu Comment method "debug".
     * 
     * @param e
     */
    public static void debug(Throwable e) {
        debug(e, true, true);
    }

    public static void debug(Throwable e, boolean console, boolean log) {
        if (!CommonsPlugin.isDebugMode() || e == null) {
            return;
        }
        if (console && isEclipseDebug()) {
            e.printStackTrace(System.err);
        }
        if (log) {
            CommonExceptionHandler.process(e);
        }

    }

    /**
     * 
     * ggu Comment method "debug".
     * 
     * @param messages
     */
    public static void debug(String messages) {
        debug(messages, true, true);
    }

    public static void debug(String messages, boolean console, boolean log) {
        if (!CommonsPlugin.isDebugMode()) {
            return;
        }
        if (console && isEclipseDebug()) {
            System.out.println(messages);
        }
        if (log) {
            CommonExceptionHandler.log(messages);
        }
    }

    @SuppressWarnings("restriction")
    public static boolean isEclipseDebug() {
        return Debug.DEBUG_ENABLED;
    }
}
