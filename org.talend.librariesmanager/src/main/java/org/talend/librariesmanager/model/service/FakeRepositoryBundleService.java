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
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.IRepositoryBundleService;
import org.talend.core.language.ECodeLanguage;
import org.talend.librariesmanager.prefs.PreferencesUtilities;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public class FakeRepositoryBundleService implements IRepositoryBundleService {

    private static final String COMPONENTS_SETUP_DONE = "/.componentsSetupDone"; //$NON-NLS-1$

    public boolean isInitialized() {
        String installLocation = getOBRRoot().getAbsolutePath();
        File componentsLibsSetupDone = new File(installLocation + COMPONENTS_SETUP_DONE);
        return componentsLibsSetupDone.exists();
    }

    public void setInitialized() {
        String installLocation = getOBRRoot().getAbsolutePath();
        File componentsLibsSetupDone = new File(installLocation + COMPONENTS_SETUP_DONE); //$NON-NLS-1$
        try {
            componentsLibsSetupDone.createNewFile();
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
        componentsLibsSetupDone.setLastModified((new Date()).getTime());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#deploy()
     */
    public void deploy(URI jarFileUri, IProgressMonitor... monitorWrap) {
        try {
            File file = new File(jarFileUri);
            if (file == null || !file.exists())
                return;
            if (file.isDirectory()) {
                FilesUtils.copyFolder(new File(jarFileUri), getOBRRoot(), false, FilesUtils.getExcludeSystemFilesFilter(),
                        FilesUtils.getAcceptJARFilesFilter(), false, monitorWrap);
            } else {
                File target = new File(getOBRRoot().getAbsolutePath(), file.getName());
                FilesUtils.copyFile(file, target);
            }
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#deploy(java.util.Collection,
     * org.eclipse.core.runtime.IProgressMonitor[])
     */
    public void deploy(Collection<URI> jarFileUris, IProgressMonitor... monitorWrap) {
        if (jarFileUris == null || jarFileUris.size() == 0)
            return;
        for (URI uri : jarFileUris) {
            deploy(uri, monitorWrap);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#retrieve(java.lang.String, java.lang.String)
     */
    public boolean retrieve(String jarNeeded, String pathToStore, IProgressMonitor... monitorWrap) {
        try {
            List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getOBRRoot(), jarNeeded);
            if (jarFiles.size() > 0) {
                File jarFile = jarFiles.get(0);
                File target = new File(StringUtils.trimToEmpty(pathToStore));
                if (!target.exists()) {
                    target.mkdirs();
                }
                FilesUtils.copyFile(jarFile, new File(pathToStore, jarFile.getName()));
                return true;
            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#retrieve(java.util.Collection, java.lang.String,
     * org.eclipse.core.runtime.IProgressMonitor[])
     */
    public boolean retrieve(Collection<String> jarsNeeded, String pathToStore, IProgressMonitor... monitorWrap) {
        if (jarsNeeded == null || jarsNeeded.size() == 0)
            return false;
        boolean allIsOK = true;
        for (String jar : jarsNeeded) {
            if (!retrieve(jar, pathToStore, monitorWrap)) {
                allIsOK = false;
            }
        }
        return allIsOK;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#list(org.eclipse.core.runtime.IProgressMonitor[])
     */
    public List<URI> list(IProgressMonitor... monitorWrap) {
        List<URI> uris = new ArrayList<URI>();
        try {
            List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getOBRRoot(), null);
            if (jarFiles.size() > 0) {
                for (File file : jarFiles) {
                    uris.add(file.toURI());
                }
            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        }

        return uris;
    }

    private File getOBRRoot() {
        String librariesPath = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        File OBRDir = new File(librariesPath);
        return OBRDir;
    }
}
