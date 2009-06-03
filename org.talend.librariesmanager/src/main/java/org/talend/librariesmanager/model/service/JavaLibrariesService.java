// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.routines.IRoutinesProvider;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.i18n.Messages;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.prefs.PreferencesUtilities;
import org.talend.resource.IResourceService;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class JavaLibrariesService extends AbstractLibrariesService {

    private static Logger log = Logger.getLogger(JavaLibrariesService.class);

    public static final String SOURCE_JAVA_ROUTINES_FOLDER = "routines"; //$NON-NLS-1$

    private static boolean isLibSynchronized;

    @Override
    public String getLibrariesPath() {
        return PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
    }

    @Override
    public URL getRoutineTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/" + SOURCE_JAVA_ROUTINES_FOLDER + "/__TEMPLATE__.java"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSqlPatternTemplate()
     */
    public URL getSqlPatternTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/" + SOURCE_SQLPATTERN_FOLDER + "/__TEMPLATE__" + TEMPLATE_SUFFIX); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemRoutines()
     */
    public List<URL> getSystemRoutines() {
        List<URL> toReturn = new ArrayList<URL>();

        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.addAll(routineProvider.getSystemRoutines());
        }
        return toReturn;
    }

    public List<URL> getSystemSQLPatterns() {
        return FilesUtils.getFilesFromFolder(Activator.BUNDLE, "resources/java/" + SOURCE_SQLPATTERN_FOLDER, //$NON-NLS-1$
                SQLPATTERN_FILE_SUFFIX, false, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getTalendRoutines()
     */
    public List<URL> getTalendRoutinesFolder() throws IOException {
        List<URL> toReturn = new ArrayList<URL>();

        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.add(routineProvider.getTalendRoutinesFolder());
        }

        return toReturn;
    }

    public List<URL> getTalendRoutines() {
        List<URL> toReturn = new ArrayList<URL>();
        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.addAll(routineProvider.getTalendRoutines());
        }

        return toReturn;
    }

    @Override
    public void checkInstalledLibraries() {
        // Display whole TOS project libraries status.  No relitionship with dynamic project classpath
        List<IClasspathEntry> classpath = new ArrayList<IClasspathEntry>();
        File externalLibDirectory = new File(CorePlugin.getDefault().getLibrariesService().getLibrariesPath());

        if ((externalLibDirectory != null) && (externalLibDirectory.isDirectory())) {
            for (File externalLib : externalLibDirectory.listFiles(FilesUtils.getAcceptJARFilesFilter())) {
                if (externalLib.isFile()) {
                    classpath.add(JavaCore.newLibraryEntry(new Path(externalLib.getAbsolutePath()), null, null));
                }
            }
        }

        IClasspathEntry[] entrys = new IClasspathEntry[classpath.size()];
        classpath.toArray(entrys);

        List<String> existLibraries = new ArrayList<String>();
        for (IClasspathEntry entry : classpath) {
            IPath path = entry.getPath();
            existLibraries.add(path.lastSegment());
        }

        List<ModuleNeeded> toCheck = ModulesNeededProvider.getModulesNeeded();
        for (ModuleNeeded current : toCheck) {
            if (existLibraries.contains(current.getModuleName())) {
                current.setStatus(ELibraryInstallStatus.INSTALLED);
            } else {
                current.setStatus(ELibraryInstallStatus.NOT_INSTALLED);
            }
        }

        String libpath = CorePlugin.getDefault().getLibrariesService().getLibrariesPath();
        File file = new File(libpath);
        if (file.exists() && file.isDirectory()) {
            List<String> modulesNeededNames = ModulesNeededProvider.getModulesNeededNames();
            ModulesNeededProvider.getUnUsedModules().clear();
            File[] listFiles = file.listFiles(FilesUtils.getAcceptModuleFilesFilter());
            for (File file2 : listFiles) {
                if (!modulesNeededNames.contains(file2.getName())) {
                    ModulesNeededProvider.userAddUnusedModules(file2.getPath(), file2.getName());
                }
            }
        }
    }

    public void syncLibraries(IProgressMonitor... monitorWrap) {
        File target = new File(getLibrariesPath());
        try {
            // 1. Talend libraries:
            File talendLibraries = new File(FileLocator.resolve(Activator.BUNDLE.getEntry("resources/java/lib/")).getFile()); //$NON-NLS-1$
            FilesUtils.copyFolder(talendLibraries, target, false, FilesUtils.getExcludeSystemFilesFilter(), FilesUtils
                    .getAcceptJARFilesFilter(), true, monitorWrap);

            // 2. Components libraries
            IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                    IComponentsService.class);
            File componentsLibraries = new File(service.getComponentsFactory().getComponentPath().getFile());
            
            FilesUtils.copyFolder(componentsLibraries, target, false, FilesUtils.getExcludeSystemFilesFilter(), FilesUtils
                    .getAcceptJARFilesFilter(), false, monitorWrap);

            // 3.Add resource libraires.
            IResourceService resourceService = CorePlugin.getDefault().getResourceService();
            File resourceLibraries = new File(resourceService.getJavaLibraryPath());
            FilesUtils.copyFolder(resourceLibraries, target, false, FilesUtils.getExcludeSystemFilesFilter(), FilesUtils
                    .getAcceptJARFilesFilter(), false, monitorWrap);
            
            checkInstalledLibraries();

            log.debug(Messages.getString("JavaLibrariesService.synchronization")); //$NON-NLS-1$
            isLibSynchronized = true;
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }

    @Override
    protected void addResolvedClasspathPath(File targetFile) {
        CorePlugin.getDefault().getLibrariesService().resetModulesNeeded();
        // Adds the classpath to java project.
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
        IJavaProject project = JavaCore.create(prj);

        List<IClasspathEntry> projectLibraries = new ArrayList<IClasspathEntry>();

        try {
            IClasspathEntry[] resolvedClasspath = project.getResolvedClasspath(true);
            List<String> librariesString = new ArrayList<String>();
            for (IClasspathEntry entry : resolvedClasspath) {
                IPath path = entry.getPath();
                librariesString.add(path.lastSegment());
            }
            projectLibraries.addAll(Arrays.asList(resolvedClasspath));
            if (!librariesString.contains(targetFile.getName())) {
                projectLibraries.add(JavaCore.newLibraryEntry(new Path(targetFile.getAbsolutePath()), null, null));
            }
            project.setRawClasspath(projectLibraries.toArray(new IClasspathEntry[projectLibraries.size()]), null);
        } catch (JavaModelException e) {
            ExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#isLibSynchronized()
     */
    public boolean isLibSynchronized() {
        return this.isLibSynchronized;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getJavaLibrariesPath()
     */
    public String getJavaLibrariesPath() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getPerlLibrariesPath()
     */
    public String getPerlLibrariesPath() {
        // TODO Auto-generated method stub
        return null;
    }

}
