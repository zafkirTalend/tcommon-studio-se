// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.model.ModulesNeededProvider;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class JavaLibrariesService extends AbstractLibrariesService {

    private static Logger log = Logger.getLogger(JavaLibrariesService.class);

    private static final String SOURCE_JAVA_ROUTINES_FOLDER = "routines";

    @Override
    public String getLibrariesPath() {
        return Platform.getInstallLocation().getURL().getFile() + "/lib/java";
    }

    @Override
    public URL getRoutineTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/" + SOURCE_JAVA_ROUTINES_FOLDER + "/Template.java");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemRoutines()
     */
    public List<URL> getSystemRoutines() {
        List<URL> toReturn = new ArrayList<URL>();

        Enumeration entryPaths = Activator.BUNDLE.getEntryPaths("resources/java/" + SOURCE_JAVA_ROUTINES_FOLDER + "/system/");
        for (Enumeration enumer = entryPaths; enumer.hasMoreElements();) {
            String routine = (String) enumer.nextElement();
            if (routine.endsWith(".java")) {
                URL url = Activator.BUNDLE.getEntry(routine);
                toReturn.add(url);
            }
        }
        return toReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getTalendRoutines()
     */
    public URL getTalendRoutinesFolder() throws IOException {
        URL url = Activator.BUNDLE.getEntry("resources/java/talend"); //$NON-NLS-1$
        return FileLocator.resolve(url);
    }
    

    public List<URL> getTalendRoutines() {
        return FilesUtils.getFilesFromFolder(Activator.BUNDLE, "resources/java/talend", "");
    }

    public void checkInstalledLibraries() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
        IJavaProject project = JavaCore.create(prj);

        List<String> projectLibraries = new ArrayList<String>();
        try {
            IClasspathEntry[] resolvedClasspath = project.getResolvedClasspath(true);
            for (IClasspathEntry entry : resolvedClasspath) {
                IPath path = entry.getPath();
                projectLibraries.add(path.lastSegment());
            }
        } catch (JavaModelException e) {
            ExceptionHandler.process(e);
            return;
        }

        List<ModuleNeeded> toCheck = ModulesNeededProvider.getModulesNeeded();
        for (ModuleNeeded current : toCheck) {
            if (projectLibraries.contains(current.getModuleName())) {
                current.setStatus(ELibraryInstallStatus.INSTALLED);
            } else {
                current.setStatus(ELibraryInstallStatus.NOT_INSTALLED);
            }
        }
    }

    public void syncLibraries() {
        File target = new File(getLibrariesPath());
        try {
            // 1. Talend libraries:
            File talendLibraries = new File(FileLocator.resolve(Activator.BUNDLE.getEntry("resources/java/lib/")).getFile());
            FilesUtils.copyFolder(talendLibraries, target, false, FilesUtils.getExcludeSVNFilesFilter(), null, true);

            // 2. Components libraries
            IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                    IComponentsService.class);
            File componentsLibraries = new File(service.getComponentsFactory().getComponentPath().getFile());
            FilesUtils.copyFolder(componentsLibraries, target, false, FilesUtils.getExcludeSVNFilesFilter(), FilesUtils
                    .getAcceptJARFilesFilter(), false);

            log.debug("Java libraries synchronization done");
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }
}
