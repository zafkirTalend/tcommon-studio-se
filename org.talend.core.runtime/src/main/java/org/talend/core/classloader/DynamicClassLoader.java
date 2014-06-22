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
package org.talend.core.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.talend.commons.ui.runtime.exception.ExceptionHandler;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public class DynamicClassLoader extends URLClassLoader {

    /**
     * DOC ycbai DynamicClassLoader constructor comment.
     */
    public DynamicClassLoader() {
        super(new URL[0], DynamicClassLoader.class.getClassLoader());
    }

    public void addLibraries(String lib) {
        if (lib != null) {
            File libFile = new File(lib);
            try {
                addURL(libFile.toURL());
            } catch (MalformedURLException e) {
                ExceptionHandler.process(e);
            }
        }
    }

    public void addLibraries(List<String> libs) {
        if (libs != null && libs.size() > 0) {
            for (String lib : libs) {
                addLibraries(lib);
            }
        }
    }

}
