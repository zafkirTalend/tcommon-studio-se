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
package org.talend.core.model.migration;

import org.talend.core.model.general.Project;
import org.talend.core.model.properties.Item;

/**
 * Define an atomic migration task to run on a project to assure comptibility trough Talend versions. See
 * org.talend.core.migrationTask extension point.<br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public interface IProjectMigrationTask {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String desc);

    public boolean isApplicableOnItems();

    public ExecutionResult execute(Project project);

    public ExecutionResult execute(Project project, Item item);

    /**
     * Represents the execution status of this task.
     */
    public enum ExecutionResult {
        SUCCESS_WITH_ALERT, // task successfully done, will be show to user in summary pop-up
        SUCCESS_NO_ALERT, // task successfully done, will NOT be show to user in summary pop-up (only in error log)
        NOTHING_TO_DO, // nothing to do in the current context, will NOT be show to user in summary pop-up neither in
        // error log
        FAILURE, // task failed (stacktrace in error log). will be retry on next login
        SKIPPED; // task not failed, not show to user in summary pop-up. will be retry on next login
    }
}
