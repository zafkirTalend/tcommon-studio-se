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
package org.talend.migrationtool.model;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.core.model.migration.IWorkspaceMigrationTask;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class GetTasksHelper {

    public static List<IProjectMigrationTask> getProjectTasks(final boolean beforeLogon) {
        IExtensionPointLimiter actionExtensionPoint = new ExtensionPointLimiterImpl(
                "org.talend.core.migrationTask", "projecttask"); //$NON-NLS-1$ //$NON-NLS-2$

        ExtensionImplementationProvider<IProjectMigrationTask> provider = new ExtensionImplementationProvider<IProjectMigrationTask>(
                actionExtensionPoint) {

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider#createImplementation(org.eclipse.core.runtime.IExtension,
             * org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter)
             */
            @Override
            protected IProjectMigrationTask createImplementation(IExtension extension,
                    IExtensionPointLimiter extensionPointLimiter, IConfigurationElement configurationElement) {
                try {
                    if (new Boolean(configurationElement.getAttribute("beforeLogon")) == beforeLogon) {
                        IProjectMigrationTask currentAction = (IProjectMigrationTask) configurationElement
                                .createExecutableExtension("class"); //$NON-NLS-1$
                        currentAction.setId(configurationElement.getAttribute("id")); //$NON-NLS-1$
                        currentAction.setName(configurationElement.getAttribute("name")); //$NON-NLS-1$
                        return currentAction;
                    }
                } catch (CoreException e) {
                    ExceptionHandler.process(e);
                }
                return null;
            }

        };

        return provider.createInstances();
    }

    public static List<IWorkspaceMigrationTask> getWorkspaceTasks() {
        IExtensionPointLimiter actionExtensionPoint = new ExtensionPointLimiterImpl(
                "org.talend.core.migrationTask", "workspacetask"); //$NON-NLS-1$ //$NON-NLS-2$

        ExtensionImplementationProvider<IWorkspaceMigrationTask> provider = new ExtensionImplementationProvider<IWorkspaceMigrationTask>(
                actionExtensionPoint) {

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider#createImplementation(org.eclipse.core.runtime.IExtension,
             * org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter)
             */
            @Override
            protected IWorkspaceMigrationTask createImplementation(IExtension extension,
                    IExtensionPointLimiter extensionPointLimiter, IConfigurationElement configurationElement) {
                try {
                    IWorkspaceMigrationTask currentAction = (IWorkspaceMigrationTask) configurationElement
                            .createExecutableExtension("class"); //$NON-NLS-1$
                    currentAction.setId(configurationElement.getAttribute("id")); //$NON-NLS-1$
                    currentAction.setName(configurationElement.getAttribute("name")); //$NON-NLS-1$
                    return currentAction;
                } catch (CoreException e) {
                    ExceptionHandler.process(e);
                }
                return null;
            }

        };
        return provider.createInstances();
    }
}
