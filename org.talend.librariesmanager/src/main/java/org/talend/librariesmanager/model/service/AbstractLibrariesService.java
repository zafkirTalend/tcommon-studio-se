// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.Problem;
import org.talend.core.model.process.Problem.ProblemStatus;
import org.talend.librariesmanager.i18n.Messages;
import org.talend.librariesmanager.model.ModulesNeededProvider;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractLibrariesService implements ILibrariesService {

    private final List<IChangedLibrariesListener> listeners = new ArrayList<IChangedLibrariesListener>();

    public abstract String getLibrariesPath();

    public abstract URL getRoutineTemplate();

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
        // TODO SML Allow perl module to be deploy in a folder structure in "lib/perl/..."
        File sourceFile = new File(source.getFile());
        File targetFile = new File(getLibrariesPath() + File.separatorChar + sourceFile.getName());
        FilesUtils.copyFile(sourceFile, targetFile);
        ModulesNeededProvider.userAddImportModules(targetFile.getPath(), sourceFile.getName(),
                ELibraryInstallStatus.INSTALLED);
        if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.JAVA)) {
            addResolvedClasspathPath(targetFile);
        }
        fireLibrariesChanges();
    }

    private void addResolvedClasspathPath(File targetFile) {
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

    public void undeployLibrary(String path) throws IOException {
        File file = new File(path);
        FilesUtils.removeFile(file);
        ModulesNeededProvider.userRemoveUnusedModules(file.getPath());
        fireLibrariesChanges();
    }

    public List<Problem> getProblems(INode node, Element element) {
        List<Problem> toReturn = new ArrayList<Problem>();
        List<ModuleNeeded> list = node.getComponent().getModulesNeeded();
        for (ModuleNeeded current : list) {
            if (current.getStatus() == ELibraryInstallStatus.NOT_INSTALLED && current.isRequired()) {
                toReturn.add(new Problem(element, "Module " + current.getModuleName() + " required", //$NON-NLS-1$ //$NON-NLS-2$
                        ProblemStatus.ERROR));
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

    private void fireLibrariesChanges() {
        for (IChangedLibrariesListener current : listeners) {
            current.afterChangingLibraries();
        }
    }
}
