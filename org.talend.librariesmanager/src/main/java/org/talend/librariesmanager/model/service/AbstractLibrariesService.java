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
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerService;
import org.talend.core.PluginChecker;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.Problem;
import org.talend.core.model.process.Problem.ProblemStatus;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.librariesmanager.i18n.Messages;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.prefs.PreferencesUtilities;
import org.talend.repository.ProjectManager;
import org.talend.repository.RepositoryWorkUnit;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractLibrariesService implements ILibrariesService {

    private static Logger log = Logger.getLogger(AbstractLibrariesService.class);

    private final List<IChangedLibrariesListener> listeners = new ArrayList<IChangedLibrariesListener>();

    // protected String LIBS = "libs";

    public abstract URL getRoutineTemplate();

    public abstract URL getBeanTemplate();

    public ELibraryInstallStatus getLibraryStatus(String libName) throws BusinessException {
        checkInstalledLibraries();
        for (ModuleNeeded current : ModulesNeededProvider.getModulesNeeded()) {
            if (current.getModuleName().equals(libName)) {
                return current.getStatus();
            }
        }
        throw new BusinessException(Messages.getString("ModulesNeededProvider.Module.Exception", libName)); //$NON-NLS-1$
    }

    public void deployLibrary(URL source) throws IOException {
        LocalLibraryManager repositoryBundleService = (LocalLibraryManager) CorePlugin.getDefault().getRepositoryBundleService();
        // TODO SML Allow perl module to be deploy in a folder structure in "lib/perl/..."
        /* fix for bug 0020350,if URL contains a space character it will cause problem */
        //            URI sourceURI = new URI(source.toString().replace(' ', '\0'));//$NON-NLS-0$ //$NON-NLS-1$

        // fix for bug 0020953
        // if jdk is not 1.5, need decode %20 for space.
        final String decode = URLDecoder.decode(source.getFile(), "UTF-8"); //$NON-NLS-N$

        final File sourceFile = new File(decode);
        final File targetFile = new File(PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA) + File.separatorChar
                + sourceFile.getName());

        if (!repositoryBundleService.contains(source.getFile())) {
            repositoryBundleService.deploy(sourceFile.toURI());
        }

        ModulesNeededProvider.userAddImportModules(targetFile.getPath(), sourceFile.getName(), ELibraryInstallStatus.INSTALLED);
        // if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.JAVA)) {
        // addResolvedClasspathPath(sourceFile.getName());
        // }
        fireLibrariesChanges();

        // for feature 12877
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        final String projectLabel = currentProject.getTechnicalLabel();

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject eclipseProject = workspace.getRoot().getProject(projectLabel);

        if (PluginChecker.isSVNProviderPluginLoaded()) {
            RepositoryWorkUnit repositoryWorkUnit = new RepositoryWorkUnit(currentProject, "") {

                public void run() throws PersistenceException {

                    String path = new Path(Platform.getInstanceLocation().getURL().getPath()).toFile().getPath();
                    path = path + File.separatorChar + projectLabel + File.separatorChar
                            + ERepositoryObjectType.getFolderName(ERepositoryObjectType.LIBS) + File.separatorChar
                            + sourceFile.getName();
                    File libsTargetFile = new File(path);

                    try {
                        FilesUtils.copyFile(sourceFile, libsTargetFile);
                        eclipseProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
                    } catch (CoreException e1) {
                        ExceptionHandler.process(e1);
                    } catch (IOException e) {
                        ExceptionHandler.process(e);
                    }
                }
            };
            repositoryWorkUnit.setAvoidUnloadResources(true);
            CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory()
                    .executeRepositoryWorkUnit(repositoryWorkUnit);
        }

    }

    protected void addResolvedClasspathPath(String libName) {
    }

    public void undeployLibrary(String jarName) throws IOException {
        ILibraryManagerService service = (ILibraryManagerService) GlobalServiceRegister.getDefault().getService(
                ILibraryManagerService.class);
        if (service.delete(jarName)) {
            ModulesNeededProvider.userRemoveUnusedModules(jarName);
            fireLibrariesChanges();
        } else {
            ExceptionHandler.process(new Exception("Can not remove the module " + jarName
                    + ", this is certainly a module from a component provider and not a user module"));
        }
    }

    public List<Problem> getProblems(INode node, IElement element) {
        List<Problem> toReturn = new ArrayList<Problem>();
        List<ModuleNeeded> list = node.getComponent().getModulesNeeded();
        List<ModuleNeeded> modulesNeeded = ModulesNeededProvider.getModulesNeeded();
        for (ModuleNeeded module : modulesNeeded) {
            for (ModuleNeeded current : list) {
                if (current.getContext().equals(module.getContext()) && current.getModuleName().equals(module.getModuleName())) {
                    if (module.getStatus() == ELibraryInstallStatus.NOT_INSTALLED
                            && current.isRequired(node.getElementParameters())) {
                        Problem problem = new Problem(element, "Module " + current.getModuleName() + " required", //$NON-NLS-1$ //$NON-NLS-2$
                                ProblemStatus.ERROR);
                        problem.setKey("Module_" + current.getModuleName());//$NON-NLS-1$
                        toReturn.add(problem);
                    }
                }

            }
        }
        return toReturn;
    }

    public void resetModulesNeeded() {
        ModulesNeededProvider.reset();
        checkLibraries();
    }

    public void checkLibraries() {
        this.checkInstalledLibraries();
        fireLibrariesChanges();
    }

    public abstract void checkInstalledLibraries();

    public void addChangeLibrariesListener(IChangedLibrariesListener listener) {
        listeners.add(listener);
    }

    public void removeChangeLibrariesListener(IChangedLibrariesListener listener) {
        listeners.remove(listener);
    }

    private void fireLibrariesChanges() {
        for (IChangedLibrariesListener current : listeners) {
            current.afterChangingLibraries();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.general.ILibrariesService#resetModulesNeededForCurrentJob(org.talend.core.model.properties
     * .Item)
     */
    public void updateModulesNeededForCurrentJob(IProcess process) {
        ModulesNeededProvider.resetCurrentJobNeededModuleList(process);
        checkLibraries();

    }

}
