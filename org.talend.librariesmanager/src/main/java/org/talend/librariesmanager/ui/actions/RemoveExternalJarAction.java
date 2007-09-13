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
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.librariesmanager.ui.views.ModulesViewComposite;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class RemoveExternalJarAction extends Action {

    /**
     * qzhang RemoveExternalJarAction constructor comment.
     */
    public RemoveExternalJarAction() {
        super();
        setText("Remove external JARs"); //$NON-NLS-1$
        setDescription("Remove external JARs"); //$NON-NLS-1$
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
                .getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
        setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
                ISharedImages.IMG_TOOL_DELETE_DISABLED));
        setEnabled(true);
        init();
    }

    List<ModuleNeeded> modules;

    public void init() {
        modules = new ArrayList<ModuleNeeded>();
        if (ModulesViewComposite.getTableViewerCreator() != null) {
            ModulesViewComposite.getTableViewerCreator().getTable().addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {

                }

                public void widgetSelected(SelectionEvent e) {
                    modules.clear();
                    TableItem[] selection = ModulesViewComposite.getTableViewerCreator().getTable().getSelection();
                    for (TableItem tableItem : selection) {
                        ModuleNeeded needed = (ModuleNeeded) tableItem.getData();
                        if (needed.getStatus() == ELibraryInstallStatus.UNUSED) {
                            modules.add(needed);
                        } else {
                            setEnabled(false);
                            return;
                        }
                    }
                    setEnabled(true);
                }
            });
            setEnabled(false);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

            public void run() {
                String libpath = CorePlugin.getDefault().getLibrariesService().getLibrariesPath() + File.separatorChar;
                for (ModuleNeeded module : modules) {

                    try {
                        CorePlugin.getDefault().getLibrariesService().undeployLibrary(libpath + module.getModuleName());
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    }
                }
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IProject prj = root.getProject(JavaUtils.JAVA_PROJECT_NAME);
                IJavaProject project = JavaCore.create(prj);
                List<IClasspathEntry> projectLibraries = new ArrayList<IClasspathEntry>();
                try {
                    IClasspathEntry[] resolvedClasspath = project.getResolvedClasspath(true);
                    projectLibraries.addAll(Arrays.asList(resolvedClasspath));
                    for (ModuleNeeded module : modules) {
                        projectLibraries.remove(JavaCore.newLibraryEntry(new Path(libpath + module.getModuleName()),
                                null, null));
                    }
                    project.setRawClasspath(projectLibraries.toArray(new IClasspathEntry[projectLibraries.size()]),
                            null);
                    setEnabled(false);
                } catch (JavaModelException e) {
                    ExceptionHandler.process(e);
                }
            }
        });
    }
}
