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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.IComponentsService;
import org.talend.librariesmanager.emf.librariesindex.LibrariesIndex;
import org.talend.librariesmanager.prefs.PreferencesUtilities;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class LocalLibraryManagerTest {

    private static final String JAR_INDEX = "/index.xml"; //$NON-NLS-1$

    private Set<String> jarList = new HashSet<String>();

    private LocalLibraryManager localLibMana = new LocalLibraryManager();

    /**
     * Test method for
     * {@link org.talend.librariesmanager.model.service.LocalLibraryManagerTest#deploy(java.net.URI, org.eclipse.core.runtime.IProgressMonitor[])}
     * .
     * 
     * @throws IOException
     */
    @Test(expected = ArithmeticException.class)
    public void testDeployURIIProgressMonitorArray() throws IOException {
        String librariesPath = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        File storageDir = new File(librariesPath);
        String installLocation = storageDir.getAbsolutePath();
        File indexFile = new File(installLocation + JAR_INDEX);
        if (indexFile.exists()) {
            LibrariesIndexManager.getInstance().loadResource();
        }
        IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(IComponentsService.class);
        Map<String, File> componentsFolders = service.getComponentsFactory().getComponentsProvidersFolder();
        Set<String> contributeIdSet = componentsFolders.keySet();
        String jarFileUri = "D:\\My Documents\\обть\\classpath.jar";
        File file = new File(jarFileUri);
        String contributeID = "";
        for (String contributor : contributeIdSet) {
            if (file.getAbsolutePath().contains(contributor)) {
                contributeID = contributor;
                break;
            }
        }
        if (contributeID.equals("")) {
            if (file.isDirectory()) {
                FilesUtils.copyFolder(new File(jarFileUri), storageDir, false, FilesUtils.getExcludeSystemFilesFilter(),
                        FilesUtils.getAcceptJARFilesFilter(), false, new NullProgressMonitor());
            } else {
                File target = new File(storageDir.getAbsolutePath(), file.getName());
                FilesUtils.copyFile(file, target);
            }
        } else {
            if ("org.talend.designer.components.model.UserComponentsProvider".contains(contributeID)
                    || "org.talend.designer.components.exchange.ExchangeComponentsProvider".contains(contributeID)) {
                if (file.isDirectory()) {
                    FilesUtils.copyFolder(new File(jarFileUri), storageDir, false, FilesUtils.getExcludeSystemFilesFilter(),
                            FilesUtils.getAcceptJARFilesFilter(), false, new NullProgressMonitor());
                } else {
                    File target = new File(storageDir.getAbsolutePath(), file.getName());
                    FilesUtils.copyFile(file, target);
                }
            } else {
                LibrariesIndex index = LibrariesIndexManager.getInstance().getIndex();
                EMap<String, String> jarsToRelativePath = index.getJarsToRelativePath();
                List<File> jarFiles = FilesUtils.getJarFilesFromFolder(file, null);
                boolean modified = false;
                if (jarFiles.size() > 0) {
                    for (File jarFile : jarFiles) {
                        String name = jarFile.getName();
                        String fullPath = jarFile.getAbsolutePath();
                        // caculate the relative path
                        if (fullPath.indexOf(contributeID) != -1) {
                            fullPath = new Path(fullPath).toPortableString();
                            String relativePath = fullPath.substring(fullPath.indexOf(contributeID));
                            if (!jarsToRelativePath.keySet().contains(name)) {
                                jarsToRelativePath.put(name, relativePath);
                                modified = true;
                            }
                        }
                    }
                    if (modified) {
                        LibrariesIndexManager.getInstance().saveResource();
                    }
                }
            }
        }
    }

    /**
     * Test method for
     * {@link org.talend.librariesmanager.model.service.LocalLibraryManagerTest#retrieve(java.lang.String, java.lang.String, org.eclipse.core.runtime.IProgressMonitor[])}
     * .
     * 
     * @throws IOException
     */
    @Test(expected = ArithmeticException.class)
    public void testRetrieveStringStringIProgressMonitorArray() throws IOException {
        String pathToStore = "D:\\target-5.1.0\\lib\\java";
        String jarNeeded = "mysql-connector-java-5.1.0-bin.jar";
        LibrariesIndexManager.getInstance().loadResource();
        String sourcePath = null, targetPath = pathToStore;
        List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getStorageDirectory(), jarNeeded);
        if (jarFiles.size() > 0) {
            File jarFile = jarFiles.get(0);
            File target = new File(StringUtils.trimToEmpty(pathToStore));
            if (!target.exists()) {
                target.mkdirs();
            }
            sourcePath = jarFile.getAbsolutePath();
            FilesUtils.copyFile(jarFile, new File(pathToStore, jarFile.getName()));
            return;
        }
        // retrieve jar from the index.xml if not find in lib/java
        else {
            EMap<String, String> jarsToRelative = LibrariesIndexManager.getInstance().getIndex().getJarsToRelativePath();
            String relativePath = jarsToRelative.get(jarNeeded);
            if (relativePath == null) {
                return;
            }
            String bundleLocation = "";
            String jarLocation = "";
            IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                    IComponentsService.class);
            Map<String, File> componentsFolders = service.getComponentsFactory().getComponentsProvidersFolder();
            Set<String> contributeIdSet = componentsFolders.keySet();
            boolean jarFound = false;
            for (String contributor : contributeIdSet) {
                if (relativePath.contains(contributor)) {
                    // caculate the the absolute path of the jar
                    bundleLocation = componentsFolders.get(contributor).getAbsolutePath();
                    int index = bundleLocation.indexOf(contributor);
                    jarLocation = new Path(bundleLocation.substring(0, index)).append(relativePath).toPortableString();
                    jarFound = true;
                    break;
                }
            }
            sourcePath = jarLocation;
            if (!jarFound) {
                ExceptionHandler.process(new Exception("Jar: " + jarNeeded + " not found, not in the plugins available:"
                        + contributeIdSet));
                return;
            }
            FilesUtils.copyFile(new File(jarLocation), new File(pathToStore, jarNeeded));
            return;
        }

    }

    /**
     * Test method for
     * {@link org.talend.librariesmanager.model.service.LocalLibraryManagerTest#list(org.eclipse.core.runtime.IProgressMonitor[])}
     * .
     */
    @Test(expected = ArithmeticException.class)
    public void testList() throws MalformedURLException {
        Set<String> names = new HashSet<String>();
        List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getStorageDirectory(), null);
        if (jarFiles.size() > 0) {
            for (File file : jarFiles) {
                names.add(file.getName());
            }
        }

        LibrariesIndexManager.getInstance().loadResource();
        //
        EMap<String, String> jarsToRelative = LibrariesIndexManager.getInstance().getIndex().getJarsToRelativePath();
        names.addAll(jarsToRelative.keySet());

        assertTrue(names.size() > 0);

    }

    /**
     * Test method for
     * {@link org.talend.librariesmanager.model.service.LocalLibraryManagerTest#delete(java.lang.String)}.
     */
    @Test(expected = ArithmeticException.class)
    public void testDelete() throws MalformedURLException {
        String jarName = "classpath.jar";
        List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getStorageDirectory(), null);
        if (jarFiles.size() > 0) {
            for (File file : jarFiles) {
                if (file.getName().equals(jarName)) {
                    file.delete();
                    jarList.remove(jarName);
                }
            }
        }
    }

    private File getStorageDirectory() {
        String librariesPath = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        File storageDir = new File(librariesPath);
        return storageDir;
    }

}
