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
package org.talend.core.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.talend.commons.exception.ExceptionHandler;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public class DynamicClassLoader extends URLClassLoader {

    private String libStorePath;

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
                addURL(libFile.toURI().toURL());
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

    /**
     * DOC ycbai Comment method "getLibraries".
     * 
     * <p>
     * Get libraries which this classloader contains.
     * </p>
     * 
     * @return
     */
    public Set<String> getLibraries() {
        Set<String> set = new HashSet<String>();
        URL[] urLs = getURLs();
        if (urLs != null) {
            for (URL url : urLs) {
                try {
                    File file = new File(url.toURI());
                    set.add(file.getName());
                } catch (URISyntaxException e) {
                    // dont care...
                }
            }
        }

        return set;
    }

    /**
     * DOC ycbai Comment method "getLibStorePath".
     * 
     * <p>
     * Get folder path which stores libraries of classloader.
     * </p>
     * 
     * @return
     */
    public String getLibStorePath() {
        return this.libStorePath;
    }

    public void setLibStorePath(String libStorePath) {
        this.libStorePath = libStorePath;
    }

}
