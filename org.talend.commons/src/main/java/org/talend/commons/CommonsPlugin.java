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
package org.talend.commons;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Activator for Code Generator.
 * 
 * $Id$
 * 
 */
public class CommonsPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.commons"; //$NON-NLS-1$

    // The shared instance
    private static CommonsPlugin plugin;

    /**
     * Default Constructor.
     */
    public CommonsPlugin() {
        plugin = this;
    }

    public static CommonsPlugin getDefault() {
        return plugin;
    }
}
