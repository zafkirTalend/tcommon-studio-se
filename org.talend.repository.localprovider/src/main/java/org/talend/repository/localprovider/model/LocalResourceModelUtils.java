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
package org.talend.repository.localprovider.model;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.ResourceModelUtils;

/**
 * Provides utilities methods relative to model on IResource.<br/>
 * 
 * $Id$
 * 
 */
public final class LocalResourceModelUtils extends ResourceModelUtils {

    private LocalResourceModelUtils() {
    }

    public static IFolder getFolder(Project project, ERepositoryObjectType type) throws PersistenceException {
        IProject fsProject = LocalResourceModelUtils.getProject(project);
        return ResourceUtils.getFolder(fsProject, getFolderName(type), true);
    }

    public static String getFolderName(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return "businessProcess";
        case PROCESS:
            return "process";
        case ROUTINES:
            return "routines";
        case DOCUMENTATION:
            return "documentations";
        case METADATA:
            return "metadata";
        case METADATA_CONNECTIONS:
            return "metadata/connections";
        case METADATA_FILE_DELIMITED:
            return "metadata/fileDelimited";
        case METADATA_FILE_POSITIONAL:
            return "metadata/filePositional";
        case METADATA_FILE_REGEXP:
            return "metadata/fileRegex";
        default:
            throw new IllegalArgumentException("Folder for type " + type + " cannot be found");
        }
    }
}
