// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.utils;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public final class TalendPropertiesUtil {

    /**
     * 
     * DOC ggu Comment method "isHideExchange".
     * 
     * 
     * @return
     */
    public static boolean isHideExchange() {
        String value = System.getProperty("talend.hide.exchange"); //$NON-NLS-1$

        return Boolean.parseBoolean(value);
    }

    /**
     * 
     * DOC ggu Comment method "isHideBuildNumber".
     * 
     * @return
     */
    public static boolean isHideBuildNumber() {
        String value = System.getProperty("talend.hide.buildNumber"); //$NON-NLS-1$

        return Boolean.parseBoolean(value);
    }

    /**
     * 
     * DOC ggu Comment method "isEnabledMultiBranchesInWorkspacee".
     * 
     * @return
     */
    public static boolean isEnabledMultiBranchesInWorkspace() {
        String value = System.getProperty("talend.enable.multiBranchesInWorkspace"); //$NON-NLS-1$

        return Boolean.parseBoolean(value);
    }
}
