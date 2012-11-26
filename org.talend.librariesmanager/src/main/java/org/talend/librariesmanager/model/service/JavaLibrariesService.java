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
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.PluginChecker;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.routines.IRoutinesProvider;
import org.talend.core.model.routines.RoutineLibraryMananger;
import org.talend.core.utils.TalendCacheUtils;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.i18n.Messages;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.repository.ProjectManager;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class JavaLibrariesService extends AbstractLibrariesService {

    private static Logger log = Logger.getLogger(JavaLibrariesService.class);

    private static ILibraryManagerService repositoryBundleService = CorePlugin.getDefault().getRepositoryBundleService();

    public static final String SOURCE_JAVA_ROUTINES_FOLDER = "routines"; //$NON-NLS-1$

    public static final String SOURCE_JAVA_BEANS_FOLDER = "beans"; //$NON-NLS-1$

    private static boolean isLibSynchronized;

    @Override
    public URL getRoutineTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/" + SOURCE_JAVA_ROUTINES_FOLDER + "/__TEMPLATE__.java"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public URL getBeanTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/" + SOURCE_JAVA_BEANS_FOLDER + "/__BEAN_TEMPLATE__.java"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSqlPatternTemplate()
     */
    @Override
    public URL getSqlPatternTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/" + SOURCE_SQLPATTERN_FOLDER + "/__TEMPLATE__" + TEMPLATE_SUFFIX); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemRoutines()
     */
    @Override
    public List<URL> getSystemRoutines() {
        List<URL> toReturn = new ArrayList<URL>();

        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.addAll(routineProvider.getSystemRoutines());
        }
        return toReturn;
    }

    @Override
    public List<URL> getSystemSQLPatterns() {
        return FilesUtils.getFilesFromFolder(Activator.BUNDLE, "resources/java/" + SOURCE_SQLPATTERN_FOLDER, //$NON-NLS-1$
                SQLPATTERN_FILE_SUFFIX, false, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getTalendRoutines()
     */
    @Override
    public List<URL> getTalendRoutinesFolder() throws IOException {
        List<URL> toReturn = new ArrayList<URL>();

        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.add(routineProvider.getTalendRoutinesFolder());
        }

        return toReturn;
    }

    @Override
    public List<URL> getTalendBeansFolder() throws IOException {
        List<URL> toReturn = new ArrayList<URL>();

        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.add(routineProvider.getTalendRoutinesFolder());
        }

        return toReturn;
    }

    @Override
    public List<URL> getTalendRoutines() {
        List<URL> toReturn = new ArrayList<URL>();
        for (IRoutinesProvider routineProvider : RoutineProviderManager.getInstance().getProviders(ECodeLanguage.JAVA)) {
            toReturn.addAll(routineProvider.getTalendRoutines());
        }

        return toReturn;
    }

    @Override
    public void checkInstalledLibraries() {
        Set<String> existLibraries = repositoryBundleService.list();
        Set<String> existDllLibraries = repositoryBundleService.listAllDllFiles();
        existLibraries.addAll(existDllLibraries);
        List<ModuleNeeded> toCheck = ModulesNeededProvider.getModulesNeeded();
        for (ModuleNeeded current : toCheck) {
            if (existLibraries.contains(current.getModuleName())) {
                current.setStatus(ELibraryInstallStatus.INSTALLED);
            } else {
                current.setStatus(ELibraryInstallStatus.NOT_INSTALLED);
            }
        }

        List<String> modulesNeededNames = ModulesNeededProvider.getModulesNeededNames();
        ModulesNeededProvider.getUnUsedModules().clear();
        for (String library : existLibraries) {
            if (!modulesNeededNames.contains(library)) {
                ModulesNeededProvider.userAddUnusedModules("Unknown", library);
            }
        }
    }

    @Override
    public void syncLibraries(IProgressMonitor... monitorWrap) {
        try {
            // 1. Talend libraries:
            File talendLibraries = new File(FileLocator.resolve(Activator.BUNDLE.getEntry("resources/java/lib/")).getFile()); //$NON-NLS-1$
            repositoryBundleService.deploy(talendLibraries.toURI(), monitorWrap);

            if (TalendCacheUtils.cleanComponentCache()) {
                repositoryBundleService.clearCache();
            }
            // Add a new system file, if exists, means all components libs are already setup, so no need to do again.
            // if clean the component cache, it will automatically recheck all libs still.
            if (!repositoryBundleService.isInitialized()) {
                // 2. Components libraries
                IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                        IComponentsService.class);
                Map<String, File> componentsFolders = service.getComponentsFactory().getComponentsProvidersFolder();
                Set<String> contributeIdSet = componentsFolders.keySet();
                for (String contributeID : contributeIdSet) {
                    repositoryBundleService.deploy(componentsFolders.get(contributeID).toURI(), monitorWrap);
                }
                repositoryBundleService.setInitialized();
            }

            // 3. system routine libraries
            // Map<String, List<URI>> routineAndJars = RoutineLibraryMananger.getInstance().getRoutineAndJars();
            // Iterator<Entry<String, List<URI>>> rjsIter = routineAndJars.entrySet().iterator();
            // while (rjsIter.hasNext()) {
            // Map.Entry<String, List<URI>> entry = rjsIter.next();
            // repositoryBundleService.deploy(entry.getValue(), monitorWrap);
            // }
            // 3. deploy system routine libraries
            RoutineLibraryMananger.getInstance().initializeSystemLibs();

            // 4. check in the libs directory of the project and add the jar with other ones.
            syncLibrariesFromLibs(monitorWrap);

            checkInstalledLibraries();

            // clean the temp library of job needed in .java\lib
            cleanTempProLib();

            log.debug(Messages.getString("JavaLibrariesService.synchronization")); //$NON-NLS-1$
            isLibSynchronized = true;
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }

    @Override
    protected void addResolvedClasspathPath(String libName) {
        CorePlugin.getDefault().getLibrariesService().resetModulesNeeded();
        // Adds the classpath to java project.
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
        IJavaProject project = JavaCore.create(prj);
        IPath libPath = project.getResource().getLocation().append(JavaUtils.JAVA_LIB_DIRECTORY);
        File libFile = libPath.toFile();
        if (!libFile.exists()) {
            libFile.mkdirs();
        }

        boolean hasJar = false;
        File[] jarFiles = libFile.listFiles(FilesUtils.getAcceptJARFilesFilter());
        if (jarFiles != null && jarFiles.length > 0) {
            for (File file : jarFiles) {
                if (file.isFile() && file.getName().equals(libName)) {
                    hasJar = true;
                    break;
                }
            }
        }
        if (!hasJar) {
            repositoryBundleService.retrieve(libName, libFile.getAbsolutePath());
        }

        File targetFile = new File(libPath.toOSString(), libName);
        List<IClasspathEntry> projectLibraries = new ArrayList<IClasspathEntry>();
        try {
            // fix for 15295 , derby data viewer will change classpath in current system
            // IClasspathEntry[] resolvedClasspath = project.getResolvedClasspath(true);
            IClasspathEntry jreClasspathEntry = JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER")); //$NON-NLS-1$
            IClasspathEntry classpathEntry = JavaCore.newSourceEntry(project.getPath().append(JavaUtils.JAVA_SRC_DIRECTORY));
            IClasspathEntry[] classpathEntryArray = project.getRawClasspath();
            if (!ArrayUtils.contains(classpathEntryArray, jreClasspathEntry)) {
                classpathEntryArray = (IClasspathEntry[]) ArrayUtils.add(classpathEntryArray, jreClasspathEntry);
            }
            if (!ArrayUtils.contains(classpathEntryArray, classpathEntry)) {
                IClasspathEntry source = null;
                for (IClasspathEntry entry : classpathEntryArray) {
                    if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
                        source = entry;
                        break;
                    }
                }
                if (source != null) {
                    classpathEntryArray = (IClasspathEntry[]) ArrayUtils.remove(classpathEntryArray,
                            ArrayUtils.indexOf(classpathEntryArray, source));
                }
                classpathEntryArray = (IClasspathEntry[]) ArrayUtils.add(classpathEntryArray, classpathEntry);
            }
            List<String> librariesString = new ArrayList<String>();
            for (IClasspathEntry entry : classpathEntryArray) {
                IPath path = entry.getPath();
                librariesString.add(path.lastSegment());
            }
            projectLibraries.addAll(Arrays.asList(classpathEntryArray));
            if (!librariesString.contains(libName)) {
                projectLibraries.add(JavaCore.newLibraryEntry(new Path(targetFile.getAbsolutePath()), null, null));
            }
            project.setRawClasspath(projectLibraries.toArray(new IClasspathEntry[projectLibraries.size()]), null);
        } catch (JavaModelException e) {
            ExceptionHandler.process(e);
        }
    }

    protected void updateProjectLib(String libName) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#isLibSynchronized()
     */
    @Override
    public boolean isLibSynchronized() {
        return this.isLibSynchronized;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getPerlLibrariesPath()
     */
    @Override
    public String getPerlLibrariesPath() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.general.ILibrariesService#syncLibrariesFromLibs(org.eclipse.core.runtime.IProgressMonitor
     * [])
     */
    @Override
    public void syncLibrariesFromLibs(IProgressMonitor... monitorWrap) {
        // for feature 12877
        if (PluginChecker.isSVNProviderPluginLoaded()) {
            String path = new Path(Platform.getInstanceLocation().getURL().getPath()).toFile().getPath();
            String projectLabel = ProjectManager.getInstance().getCurrentProject().getTechnicalLabel();
            path = path + File.separatorChar + projectLabel + File.separatorChar
                    + ERepositoryObjectType.getFolderName(ERepositoryObjectType.LIBS);
            File libsTargetFile = new File(path);
            repositoryBundleService.deploy(libsTargetFile.toURI(), monitorWrap);
        }
    }

    public void cleanTempProLib() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
        IJavaProject project = JavaCore.create(prj);
        IPath libPath = project.getResource().getLocation().append(JavaUtils.JAVA_LIB_DIRECTORY);
        if (libPath.toFile().exists()) {
            FilesUtils.emptyFolder(libPath.toFile());
        }
    }

}
