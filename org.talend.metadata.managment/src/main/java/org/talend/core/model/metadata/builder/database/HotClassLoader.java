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
package org.talend.core.model.metadata.builder.database;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.talend.commons.ui.runtime.exception.ExceptionHandler;

/**
 * 
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public class HotClassLoader extends URLClassLoader {

    // qli modified to fix the bug 6281.
    private static HotClassLoader instance;

    public static HotClassLoader getInstance() {
        // bug 17800 fixed
        // if (instance == null) {
        instance = new HotClassLoader();
        // }
        return instance;
    }

    HotClassLoader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    public void addPath(String paths) {
        if (paths == null || paths.length() <= 0) {
            return;
        }
        String separator = System.getProperty("path.separator"); //$NON-NLS-1$
        String[] pathToAdds = paths.split(separator);
        for (int i = 0; i < pathToAdds.length; i++) {
            if (pathToAdds[i] != null && pathToAdds[i].length() > 0) {
                try {
                    File pathToAdd = new File(pathToAdds[i]).getCanonicalFile();
                    addURL(pathToAdd.toURL());
                } catch (IOException e) {
                    ExceptionHandler.process(e);
                }
            }
        }
    }

}
