// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.IComponentsService;
import org.talend.librariesmanager.emf.librariesindex.LibrariesIndex;
import org.talend.librariesmanager.prefs.PreferencesUtilities;
import org.talend.librariesmanager.utils.ModulesInstaller;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public class LocalLibraryManager implements ILibraryManagerService {

    private static final String JAR_INDEX = "/index.xml"; //$NON-NLS-1$

    private Set<String> jarList = new HashSet<String>();

    // private long totalSizeCanBeReduced = 0;

    @Override
    public boolean isInitialized() {
        String installLocation = getStorageDirectory().getAbsolutePath();
        File indexFile = new File(installLocation + JAR_INDEX);
        if (indexFile.exists()) {
            LibrariesIndexManager.getInstance().loadResource();
            return LibrariesIndexManager.getInstance().getIndex().isInitialized();
        }
        return false;
    }

    @Override
    public void setInitialized() {
        LibrariesIndexManager.getInstance().loadResource();
        LibrariesIndexManager.getInstance().getIndex().setInitialized(true);
        LibrariesIndexManager.getInstance().saveResource();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#deploy()
     */
    @Override
    public void deploy(URI jarFileUri, IProgressMonitor... monitorWrap) {
        String installLocation = getStorageDirectory().getAbsolutePath();
        File indexFile = new File(installLocation + JAR_INDEX);
        if (indexFile.exists()) {
            LibrariesIndexManager.getInstance().loadResource();
        }

        IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(IComponentsService.class);
        Map<String, File> componentsFolders = service.getComponentsFactory().getComponentsProvidersFolder();
        Set<String> contributeIdSet = componentsFolders.keySet();

        try {
            File file = new File(jarFileUri);
            String contributeID = "";
            for (String contributor : contributeIdSet) {
                if (file.getAbsolutePath().contains(contributor)) {
                    contributeID = contributor;
                    break;
                }
            }
            if (!"".equals(contributeID)) {
                String actualPluginPath = FileLocator.getBundleFile(Platform.getBundle(contributeID)).getPath();
                // check if the path of the imported jar is at least from the same studio, in case import a jar from a
                // studio to another one.
                if (!file.getAbsolutePath().startsWith(actualPluginPath)) {
                    contributeID = "";
                }
            }
            if (file == null || !file.exists()) {
                return;
            }
            if (contributeID.equals("")) {
                if (file.isDirectory()) {
                    FilesUtils.copyFolder(new File(jarFileUri), getStorageDirectory(), false,
                            FilesUtils.getExcludeSystemFilesFilter(), FilesUtils.getAcceptJARFilesFilter(), false, monitorWrap);
                } else {
                    File target = new File(getStorageDirectory().getAbsolutePath(), file.getName());
                    FilesUtils.copyFile(file, target);
                }
            } else {
                if ("org.talend.designer.components.model.UserComponentsProvider".contains(contributeID)
                        || "org.talend.designer.components.exchange.ExchangeComponentsProvider".contains(contributeID)) {
                    if (file.isDirectory()) {
                        FilesUtils.copyFolder(new File(jarFileUri), getStorageDirectory(), false,
                                FilesUtils.getExcludeSystemFilesFilter(), FilesUtils.getAcceptJARFilesFilter(), false,
                                monitorWrap);
                    } else {
                        File target = new File(getStorageDirectory().getAbsolutePath(), file.getName());
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
                                } else {
                                    // System.out.println("duplicate jar " + name + " found\n in :" +
                                    // jarsToRelativePath.get(name)
                                    // + "\n and : " + relativePath);
                                    // totalSizeCanBeReduced += jarFile.length();
                                    // System.out.println("total size can be reduced from:" + totalSizeCanBeReduced /
                                    // 1024 + "kb\n");
                                }
                            }
                        }
                        if (modified) {
                            LibrariesIndexManager.getInstance().saveResource();
                        }
                    }

                    // copy dll files
                    List<File> dlls = FilesUtils.getDllFilesFromFolder(file, null);
                    for (File dllFile : dlls) {
                        FilesUtils.copyFile(dllFile, new File(installLocation, dllFile.getName()));
                    }
                }
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
    @Override
    public void deploy(Collection<URI> jarFileUris, IProgressMonitor... monitorWrap) {
        if (jarFileUris == null || jarFileUris.size() == 0) {
            return;
        }
        for (URI uri : jarFileUris) {
            deploy(uri, monitorWrap);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#retrieve(java.lang.String, java.lang.String)
     */
    @Override
    public boolean retrieve(String jarNeeded, String pathToStore, IProgressMonitor... monitorWrap) {
        return retrieve(jarNeeded, pathToStore, true, monitorWrap);
    }

    private boolean retrieve(String jarNeeded, String pathToStore, boolean popUp, IProgressMonitor... monitorWrap) {

        LibrariesIndexManager.getInstance().loadResource();
        String sourcePath = null, targetPath = pathToStore;
        try {
            List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getStorageDirectory(), jarNeeded);
            if (jarFiles.size() > 0) {
                File jarFile = jarFiles.get(0);
                File target = new File(StringUtils.trimToEmpty(pathToStore));
                if (!target.exists()) {
                    target.mkdirs();
                }
                sourcePath = jarFile.getAbsolutePath();
                FilesUtils.copyFile(jarFile, new File(pathToStore, jarFile.getName()));
                return true;
            }
            // retrieve jar from the index.xml if not find in lib/java
            else {
                EMap<String, String> jarsToRelative = LibrariesIndexManager.getInstance().getIndex().getJarsToRelativePath();
                String relativePath = jarsToRelative.get(jarNeeded);
                String bundleLocation = "";
                String jarLocation = "";
                IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                        IComponentsService.class);
                Map<String, File> componentsFolders = service.getComponentsFactory().getComponentsProvidersFolder();
                Set<String> contributeIdSet = componentsFolders.keySet();
                boolean jarFound = false;
                if (relativePath != null) {
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
                }
                sourcePath = jarLocation;

                if (!jarFound && popUp && !CommonsPlugin.isHeadless()) {
                    Shell shell = Display.getCurrent().getActiveShell();
                    ModulesInstaller.installModules(new Shell(shell), new String[] { jarNeeded });
                    return false;
                }

                if (!jarFound) {
                    // CommonExceptionHandler.log("Jar: " + jarNeeded + " not found, not in the plugins available:"
                    // + contributeIdSet);
                    return false;
                }
                FilesUtils.copyFile(new File(sourcePath), new File(pathToStore, jarNeeded));
                return true;
            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        } catch (IOException e) {
            ExceptionHandler.process(new Exception("Can not copy: " + sourcePath + " to :" + targetPath, e));
        }
        return false;

    }

    @Override
    public boolean retrieve(Collection<String> jarsNeeded, String pathToStore, boolean showDialog,
            IProgressMonitor... monitorWrap) {
        if (jarsNeeded == null || jarsNeeded.size() == 0) {
            return false;
        }
        List<String> jarNotFound = new ArrayList<String>();

        boolean allIsOK = true;
        for (String jar : jarsNeeded) {
            if (!retrieve(jar, pathToStore, false, monitorWrap)) {
                jarNotFound.add(jar);
                allIsOK = false;
            }
        }
        if (showDialog && !jarNotFound.isEmpty() && !CommonsPlugin.isHeadless()) {
            Shell shell = Display.getCurrent().getActiveShell();
            ModulesInstaller.installModules(new Shell(shell), jarNotFound.toArray(new String[jarNotFound.size()]));
        }

        return allIsOK;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#retrieve(java.util.Collection, java.lang.String,
     * org.eclipse.core.runtime.IProgressMonitor[])
     */
    @Override
    public boolean retrieve(Collection<String> jarsNeeded, String pathToStore, IProgressMonitor... monitorWrap) {
        return retrieve(jarsNeeded, pathToStore, true, monitorWrap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IRepositoryBundleService#list(org.eclipse.core.runtime.IProgressMonitor[])
     */
    @Override
    public Set<String> list(IProgressMonitor... monitorWrap) {
        Set<String> names = new HashSet<String>();
        try {
            List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getStorageDirectory(), null);
            if (jarFiles.size() > 0) {
                for (File file : jarFiles) {
                    names.add(file.getName());
                }
            } else {

            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        }

        LibrariesIndexManager.getInstance().loadResource();

        EMap<String, String> jarsToRelative = LibrariesIndexManager.getInstance().getIndex().getJarsToRelativePath();
        names.addAll(jarsToRelative.keySet());

        return names;
    }

    @Override
    public Set<String> listAllDllFiles() {
        Set<String> names = new HashSet<String>();
        try {
            List<File> dllFiles = FilesUtils.getDllFilesFromFolder(getStorageDirectory(), null);
            if (dllFiles.size() > 0) {
                for (File file : dllFiles) {
                    names.add(file.getName());
                }
            } else {

            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        }

        return names;
    }

    private File getStorageDirectory() {
        String librariesPath = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        File storageDir = new File(librariesPath);
        return storageDir;
    }

    @Override
    public void clearCache() {
        if (isInitialized()) {
            LibrariesIndexManager.getInstance().loadResource();
            LibrariesIndexManager.getInstance().getIndex().setInitialized(false);
            LibrariesIndexManager.getInstance().getIndex().getJarsToRelativePath().clear();
            LibrariesIndexManager.getInstance().saveResource();
        }
    }

    @Override
    public boolean contains(String jarName) {
        if (jarList.contains(jarName)) {
            return true;
        }
        jarList = list(new NullProgressMonitor());
        return jarList.contains(jarName);
    }

    /*
     * (non-Jsdoc)
     * 
     * @see org.talend.core.ILibraryManagerService#delete(java.lang.String)
     */
    @Override
    public boolean delete(String jarName) {
        // only delete jar from lib/java, do not delete jars from original components providers.

        try {
            List<File> jarFiles = FilesUtils.getJarFilesFromFolder(getStorageDirectory(), null);
            if (jarFiles.size() > 0) {
                for (File file : jarFiles) {
                    if (file.getName().equals(jarName)) {
                        file.delete();
                        jarList.remove(jarName);
                        return true;
                    }
                }
            }
        } catch (MalformedURLException e) {
            ExceptionHandler.process(e);
        }

        return false;
    }

}
