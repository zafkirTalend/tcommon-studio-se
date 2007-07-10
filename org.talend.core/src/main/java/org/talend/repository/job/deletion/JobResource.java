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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;

/**
 * Reference to both jobs and its resources.
 * 
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: JobResource.java 下午03:41:44 2007-7-6 +0000 (2007-7-6) yzhang $
 * 
 */
public class JobResource {

    private String jobName;

    private String projectName;

    private IWorkspace workspace;

    /**
     * Constructor.
     * 
     * yzhang JobResource constructor comment.
     */
    public JobResource() {
        workspace = ResourcesPlugin.getWorkspace();
    }

    /**
     * Constructor.
     * 
     * yzhang JobResource constructor comment.
     * 
     * @param pn project name.
     * @param jn job name.
     */
    public JobResource(String pn, String jn) {
        this();
        jobName = jn.toLowerCase();
        projectName = pn.toLowerCase();
    }

    /**
     * Getter for jobName.
     * 
     * @return the jobName
     */
    public String getJobName() {
        return this.jobName;
    }

    /**
     * Sets the jobName.
     * 
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName.toLowerCase();
    }

    /**
     * Getter for projectName.
     * 
     * @return the projectName
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Sets the projectName.
     * 
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName.toLowerCase();
    }

    /**
     * Get the unique resource name.
     * 
     * yzhang Comment method "getResourceName".
     */
    public String getUniqueResourceName() {
        return projectName + "." + jobName;
    }

    /**
     * Return the resource of current job.
     * 
     * yzhang Comment method "getResorcePath".
     * 
     * @return
     */
    public List<IResource> getResorce() {

        List<IResource> resources = new ArrayList<IResource>();
        ECodeLanguage language = LanguageManager.getCurrentLanguage();
        IResource res = null;

        if (language == ECodeLanguage.JAVA) {
            res = workspace.getRoot().findMember(".Java/src" + "/" + projectName + "/" + jobName);
            resources.add(res);
        } else if (language == ECodeLanguage.PERL) {
            res = workspace.getRoot().findMember(".Perl" + "/" + projectName.toUpperCase() + ".job_" + jobName + ".pl");
            resources.add(res);
            res = workspace.getRoot().findMember(
                    ".Perl" + "/" + projectName.toUpperCase() + ".job_" + jobName + "_Default.pl");
            resources.add(res);
        }
        return resources;
    }
}
