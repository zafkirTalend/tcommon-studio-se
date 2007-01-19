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
package org.talend.migrationtool.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProviders;
import org.talend.commons.utils.workbench.extensions.ExtensionPointImpl;
import org.talend.commons.utils.workbench.extensions.ISimpleExtensionPoint;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.core.model.migration.IWorkspaceMigrationTask;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class GetTasksHelper {

    public static List<IProjectMigrationTask> getProjectTasks() {
        List<IProjectMigrationTask> toReturn = new ArrayList<IProjectMigrationTask>();
        ISimpleExtensionPoint actionExtensionPoint = new ExtensionPointImpl("org.talend.core.migrationTask", "projecttask", -1,
                -1);
        List<IConfigurationElement> extension = ExtensionImplementationProviders.getInstanceV2(actionExtensionPoint);

        for (IConfigurationElement current : extension) {
            try {
                IProjectMigrationTask currentAction = (IProjectMigrationTask) current.createExecutableExtension("class");
                currentAction.setId(current.getAttribute("id"));
                currentAction.setName(current.getAttribute("name"));
                toReturn.add(currentAction);
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }

        return toReturn;
    }

    public static List<IWorkspaceMigrationTask> getWorkspaceTasks() {
        List<IWorkspaceMigrationTask> toReturn = new ArrayList<IWorkspaceMigrationTask>();
        ISimpleExtensionPoint actionExtensionPoint = new ExtensionPointImpl("org.talend.core.migrationTask", "workspacetask", -1,
                -1);
        List<IConfigurationElement> extension = ExtensionImplementationProviders.getInstanceV2(actionExtensionPoint);

        for (IConfigurationElement current : extension) {
            try {
                IWorkspaceMigrationTask currentAction = (IWorkspaceMigrationTask) current.createExecutableExtension("class");
                currentAction.setId(current.getAttribute("id"));
                currentAction.setName(current.getAttribute("name"));
                toReturn.add(currentAction);
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }

        return toReturn;
    }
}
