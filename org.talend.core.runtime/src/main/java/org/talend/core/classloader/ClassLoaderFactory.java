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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.ExceptionHandler;
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

    /**
     * DOC ycbai Comment method "getClassLoader".
     * 
     * @param index
     * @return the classLoader by index
     */
    public static DynamicClassLoader getClassLoader(String index) {
        if (classLoadersMap == null) {
            initClassLoaders();
        }

        return classLoadersMap.get(index);
    }

    public static DynamicClassLoader getCustomClassLoader(String index, String jarsStr) {
        return getCustomClassLoader(index, jarsStr, SEPARATOR);
    }

    protected static DynamicClassLoader getCustomClassLoader(String index, String jars, String jarSeparator) {
        Set<String> jarSet = new HashSet<String>();
        if (jars != null) {
            String[] jarsArray = jars.split(jarSeparator);
            for (String jar : jarsArray) {
                jarSet.add(jar);
            }
        }

        return getCustomClassLoader(index, jarSet);
    }

    /**
     * DOC ycbai Comment method "getCustomClassLoader".
     * 
     * @param index
     * @param libraries
     * @return the classLoader by specific libraries
     */
    public static DynamicClassLoader getCustomClassLoader(String index, Set<String> libraries) {
        DynamicClassLoader classLoader = getClassLoader(index);
        if (libraries != null) {
            if (classLoader == null) {
                classLoader = createCustomClassLoader(index, libraries);
            } else {
                boolean changed;
                Set<String> oldLibraries = classLoader.getLibraries();
                Set<String> oldLibrariesClone = new HashSet<String>(oldLibraries);
                changed = oldLibrariesClone.retainAll(libraries);
                if (!changed) {
                    Set<String> newLibrariesClone = new HashSet<String>(libraries);
                    changed = newLibrariesClone.retainAll(oldLibraries);
                }
                if (changed) {
                    File libFolder = new File(classLoader.getLibStorePath());
                    if (libFolder.exists()) {
                        FilesUtils.removeFolder(libFolder, true);
                    }
                    classLoader = createCustomClassLoader(index, libraries);
                }
            }
        }

        return classLoader;
    }

    private static DynamicClassLoader createCustomClassLoader(String index, Set<String> libraries) {
        DynamicClassLoader classLoader = new DynamicClassLoader();
        loadLibraries(classLoader, libraries.toArray(new String[0]));
        classLoadersMap.put(index, classLoader);

        return classLoader;
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
                    loadLibraries(classLoader, librariesArray);

                }
                classLoadersMap.put(index, classLoader);
            }
        }
    }

    private static void loadLibraries(DynamicClassLoader classLoader, String[] driversArray) {
        List<String> jarPathList = new ArrayList<String>();
        if (driversArray == null || driversArray.length == 0) {
            return;
        }

        ILibraryManagerService librairesManagerService = (ILibraryManagerService) GlobalServiceRegister.getDefault().getService(
                ILibraryManagerService.class);
        String libPath = getLibPath();
        for (String driverName : driversArray) {
            String jarPath = libPath + PATH_SEPARATOR + driverName;
            File jarFile = new File(jarPath);
            if (!jarFile.exists()) {
                librairesManagerService.retrieve(driverName, libPath, new NullProgressMonitor());
            }
            jarPathList.add(jarFile.getAbsolutePath());
        }

        classLoader.setLibStorePath(libPath);
        classLoader.addLibraries(jarPathList);
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
            // do nothing
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
