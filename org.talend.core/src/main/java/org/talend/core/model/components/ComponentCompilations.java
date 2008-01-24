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
package org.talend.core.model.components;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;

/**
 * DOC mhirt class global comment. Detailled comment
 */
public class ComponentCompilations {

    private static File f = null;

    public static void deleteMarkers() {
        initFile();
        if ((f != null) && (f.exists())) {
            f.delete();
        }
    }

    public static void addMarkers() {
        initFile();
        if ((f != null) && (!f.exists())) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public static boolean getMarkers() {
        initFile();
        if ((f != null) && (f.exists())) {
            return true;
        } else {
            return false;
        }
    }

    private static void initFile() {
        try {
            if (f == null) {
                String filePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
                f = new File(filePath + File.pathSeparator + ".JetEmitters" + File.pathSeparator + "FirstCompilationMarker");
            }
        } catch (Exception e) {
            // do nothing
        }
    }
}
