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
package org.talend.core.classloader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.model.general.Project;
import org.talend.repository.ProjectManager;
import org.talend.utils.io.FilesUtils;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public class ClassLoaderFactory {

    private static Logger log = Logger.getLogger(ClassLoaderFactory.class);

    private final static String EXTENSION_POINT_ID = "org.talend.core.runtime.classloader_provider"; //$NON-NLS-1$

    private static IConfigurationElement[] configurationElements = null;

    private final static String SEPARATOR = ";"; //$NON-NLS-1$

    private final static String PATH_SEPARATOR = "/"; //$NON-NLS-1$

    private static Map<String, DynamicClassLoader> classLoadersMap = null;

    private static final String INDEX_ATTR = "index"; //$NON-NLS-1$

    private static final String LIB_ATTR = "libraries"; //$NON-NLS-1$

    static {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        configurationElements = registry.getConfigurationElementsFor(EXTENSION_POINT_ID);
    }

    public static ClassLoader getClassLoader(String index) {
        if (classLoadersMap == null) {
            initClassLoaders();
        }

        return classLoadersMap.get(index);
    }

    private static void initClassLoaders() {
        File tmpFolder = getTmpFolder();
        if (tmpFolder.exists()) {
            FilesUtils.removeFolder(tmpFolder, true);
        }
        classLoadersMap = new HashMap<String, DynamicClassLoader>();
        for (IConfigurationElement current : configurationElements) {
            String index = current.getAttribute(INDEX_ATTR);
            String libraries = current.getAttribute(LIB_ATTR);
            if (StringUtils.isNotEmpty(index)) {
                DynamicClassLoader classLoader = new DynamicClassLoader();
                if (StringUtils.isNotEmpty(libraries)) {
                    String[] librariesArray = libraries.split(SEPARATOR);
                    List<String> jarPathList = retrieveJarPaths(librariesArray);
                    classLoader.addLibraries(jarPathList);
                }
                classLoadersMap.put(index, classLoader);
            }
        }
    }

    private static List<String> retrieveJarPaths(String[] driversArray) {
        List<String> jarPathList = new ArrayList<String>();
        if (driversArray == null || driversArray.length == 0) {
            return jarPathList;
        }

        ILibraryManagerService librairesManagerService = (ILibraryManagerService) GlobalServiceRegister.getDefault().getService(
                ILibraryManagerService.class);
        String libPath = getLibPath();
        for (String driverName : driversArray) {
            String jarPath = libPath + PATH_SEPARATOR + driverName;
            File jarFile = new File(jarPath);
            if (!jarFile.exists()) {
                boolean retrieved = librairesManagerService.retrieve(driverName, libPath, new NullProgressMonitor());
                if (!retrieved) {
                    log.warn("Cannot retrieve lib: " + driverName);
                }
            }
            jarPathList.add(jarFile.getAbsolutePath());
        }

        return jarPathList;
    }

    private static String getLibPath() {
        File tmpFolder = getTmpFolder();
        if (!tmpFolder.exists()) {
            tmpFolder.mkdirs();
        }
        try {
            tmpFolder = File.createTempFile("libs", null, tmpFolder); //$NON-NLS-1$
            if (tmpFolder.exists() && tmpFolder.isFile()) {
                tmpFolder.delete();
                tmpFolder.mkdirs();
            }
        } catch (IOException e) {
        }
        return tmpFolder.getAbsolutePath();
    }

    private static File getTmpFolder() {
        Project project = ProjectManager.getInstance().getCurrentProject();
        IProject physProject;
        String tmpFolderPath = System.getProperty("user.dir"); //$NON-NLS-1$
        try {
            physProject = ResourceUtils.getProject(project.getTechnicalLabel());
            tmpFolderPath = physProject.getFolder("temp").getLocation().toPortableString(); //$NON-NLS-1$
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        tmpFolderPath = tmpFolderPath + "/libraries"; //$NON-NLS-1$

        return new File(tmpFolderPath);
    }
}
