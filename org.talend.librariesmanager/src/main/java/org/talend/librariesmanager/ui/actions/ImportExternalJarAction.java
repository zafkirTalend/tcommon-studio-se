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
package org.talend.librariesmanager.ui.actions;

import java.io.File;
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
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.ui.images.ECoreImage;

/**
 * Imports the external jar files into talend.
 * 
 * $Id: ImportExternalJarAction.java Mar 15, 20075:58:30 PM bqian $
 * 
 */
public class ImportExternalJarAction extends Action {

    /**
     * DOC acer ImportExternalJarAction constructor comment.
     */
    public ImportExternalJarAction() {
        super();
        this.setText(Messages.getString("ImportExternalJarAction.title")); //$NON-NLS-1$
        this.setDescription(Messages.getString("ImportExternalJarAction.title")); //$NON-NLS-1$
        this.setImageDescriptor(ImageProvider.getImageDesc(ECoreImage.IMPORT_JAR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {

        FileDialog fileDialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN
                | SWT.MULTI);
        fileDialog.setFilterExtensions(FilesUtils.getAcceptJARFilesSuffix()); //$NON-NLS-1$
        fileDialog.open();
        final String path = fileDialog.getFilterPath();
        final String[] fileNames = fileDialog.getFileNames();

        BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

            public void run() {
                for (int i = 0; i < fileNames.length; i++) {

                    final File file = new File(path + File.separatorChar + fileNames[i]);
                    try {
                        CorePlugin.getDefault().getLibrariesService().deployLibrary(file.toURL());
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    }
                }

                // Adds the classpath to java project.
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
                IJavaProject project = JavaCore.create(prj);

                List<IClasspathEntry> projectLibraries = new ArrayList<IClasspathEntry>();

                try {
                    IClasspathEntry[] resolvedClasspath = project.getResolvedClasspath(true);

                    projectLibraries.addAll(Arrays.asList(resolvedClasspath));

                    for (int i = 0; i < fileNames.length; i++) {
                        final File file = new File(path + File.separatorChar + fileNames[i]);
                        projectLibraries.add(JavaCore.newLibraryEntry(new Path(file.getAbsolutePath()), null, null));
                    }
                    project.setRawClasspath(projectLibraries.toArray(new IClasspathEntry[projectLibraries.size()]), null);

                } catch (JavaModelException e) {
                    ExceptionHandler.process(e);
                }
            }
        });
    }
}
