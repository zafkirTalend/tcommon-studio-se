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
package org.talend.repository.job.deletion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.talend.commons.exception.RuntimeExceptionHandler;

/**
 * Management of deletion and protection on resource of jobs.
 * 
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: ResourceManager.java 下午03:44:34 2007-7-6 +0000 (2007-7-6) yzhang $
 * 
 */
public class JobResourceManager {

    private static JobResourceManager manager;

    private Map<String, JobResource> protectedJobs;

    /**
     * Set the default constructor as private avoid accessed by other method.
     * 
     * yzhang ResourceManager constructor comment.
     */
    private JobResourceManager() {
        protectedJobs = new HashMap<String, JobResource>();
    }

    /**
     * Return the single instance of this class.
     * 
     * yzhang Comment method "getInstance".
     * 
     * @return
     */
    public static JobResourceManager getInstance() {
        if (manager == null) {
            manager = new JobResourceManager();
        }
        return manager;
    }

    /**
     * Add protection on the resource of job avoid deleted by some operation.
     * 
     * yzhang Comment method "addProtection".
     */
    public void addProtection(IJobResourceProtection protection) {
        String[] ids = protection.calculateProtectedIds();
        if (ids == null) {
            return;
        }
        for (String id : ids) {
            JobResource resource = protection.getJobResource(id);
            protectedJobs.put(id, resource);
        }

    }

    /**
     * To see whether this resource is protected by other operation.
     * 
     * yzhang Comment method "canBeDeleted".
     * 
     * @param resource
     */
    private boolean canBeDeleted(JobResource resource) {
        String uniqueName = resource.getUniqueResourceName();
        for (JobResource r : protectedJobs.values()) {
            if (r.getUniqueResourceName().equalsIgnoreCase(uniqueName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * To see whether this resource is protected by other operation.
     * 
     * yzhang Comment method "isProtected".
     * 
     * @param resource
     * @return
     */
    public boolean isProtected(JobResource resource) {
        return !canBeDeleted(resource);
    }

    /**
     * Delete the resource.
     * 
     * yzhang Comment method "deleteResource".
     * 
     * @param resource
     */
    public void deleteResource(JobResource resource) {
        if (!canBeDeleted(resource)) {
            return;
        }
        try {
            List<IResource> resources = resource.getResorce();
            for (IResource re : resources) {
                if (re != null) {
                    re.delete(true, null);
                }
            }
        } catch (CoreException e) {
            RuntimeExceptionHandler.process(e);
        }

    }

    /**
     * Release the protection of the resource under specific job.
     * 
     * yzhang Comment method "removeProtection".
     * 
     * @param protection
     */
    public void removeProtection(IJobResourceProtection protection) {
        String[] ids = protection.getProjectedIds();
        if (ids == null) {
            return;
        }
        for (String id : ids) {
            protectedJobs.remove(id);
        }
    }
}
