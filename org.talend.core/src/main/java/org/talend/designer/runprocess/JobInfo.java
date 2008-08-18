// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.runprocess;

import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ProcessItem;

/**
 * DOC nrousseau ProcessController class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class JobInfo {

    private String jobId, jobName, contextName, jobVersion;

    private IProcess process;

    private IContext context;

    boolean applyContextToChildren = false;

    private ProcessItem processItem;

    private JobInfo fatherJobInfo;

    private boolean forceRegenerate;

    public JobInfo(String jobId, String contextName, String version) {
        this.jobId = jobId;
        this.contextName = contextName;
        this.jobVersion = version;
    }

    /**
     * DOC nrousseau JobInfo constructor comment.
     * 
     * @param process2
     * @param context2
     */
    public JobInfo(IProcess process, IContext context) {
        jobId = process.getId();
        jobName = process.getName();
        contextName = context.getName();
        jobVersion = process.getVersion();
        this.context = context;
        this.process = process;
    }

    /**
     * DOC nrousseau JobInfo constructor comment.
     * 
     * @param process2
     * @param contextName2
     */
    public JobInfo(ProcessItem processItem, String contextName) {
        this.processItem = processItem;
        jobId = processItem.getProperty().getId();
        jobName = processItem.getProperty().getLabel();
        this.contextName = contextName;
        jobVersion = processItem.getProperty().getVersion();
    }

    /**
     * DOC nrousseau JobInfo constructor comment.
     * 
     * @param process2
     * @param contextName2
     */
    public JobInfo(ProcessItem processItem, String contextName, String processVersion) {
        this.processItem = processItem;
        jobId = processItem.getProperty().getId();
        jobName = processItem.getProperty().getLabel();
        this.contextName = contextName;
        jobVersion = processVersion;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public IProcess getProcess() {
        return process;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }

    public IContext getContext() {
        return context;
    }

    public void setContext(IContext context) {
        this.context = context;
    }

    public String getJobVersion() {
        return this.jobVersion;
    }

    public void setJobVersion(String jobVersion) {
        this.jobVersion = jobVersion;
    }

    public boolean isApplyContextToChildren() {
        return this.applyContextToChildren;
    }

    public void setApplyContextToChildren(boolean applyContextToChildren) {
        this.applyContextToChildren = applyContextToChildren;
    }

    public ProcessItem getProcessItem() {
        if (processItem != null) {
            return processItem;
        }

        if (getProcess() != null) {
            return (ProcessItem) getProcess().getProperty().getItem();
        }

        return this.processItem;
    }

    public void setProcessItem(ProcessItem processItem) {
        this.processItem = processItem;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contextName == null) ? 0 : contextName.hashCode());
        result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
        result = prime * result + ((jobVersion == null) ? 0 : jobVersion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        final JobInfo other = (JobInfo) obj;
        if (contextName == null) {
            if (other.contextName != null) {
                return false;
            }
        } else if (!contextName.equals(other.contextName)) {
            return false;
        }
        if (jobId == null) {
            if (other.jobId != null) {
                return false;
            }
        } else if (!jobId.equals(other.jobId)) {
            return false;
        }
        if (context == null) {
            if (other.context != null) {
                return false;
            }
        } else if (!context.equals(other.context)) {
            return false;
        }
        if (process == null) {
            if (other.process != null) {
                return false;
            }
        } else if (!process.equals(other.process)) {
            return false;
        }
        if (jobVersion == null) {
            if (other.jobVersion != null) {
                return false;
            }
        } else if (!jobVersion.equals(other.jobVersion)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "job:" + jobName + " / context:" + contextName + " / version:" + jobVersion; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Getter for fatherJobInfo.
     * 
     * @return the fatherJobInfo
     */
    public JobInfo getFatherJobInfo() {
        return this.fatherJobInfo;
    }

    /**
     * Sets the fatherJobInfo.
     * 
     * @param fatherJobInfo the fatherJobInfo to set
     */
    public void setFatherJobInfo(JobInfo fatherJobInfo) {
        this.fatherJobInfo = fatherJobInfo;
    }

    /**
     * Used only in the ProcessorUtilities to know if one subjob has been generated or not, to generate the father.
     * 
     * @return forceRegenerate
     */
    public boolean isForceRegenerate() {
        return this.forceRegenerate;
    }

    /**
     * Used only in the ProcessorUtilities to know if one subjob has been generated or not, to generate the father.
     * 
     * @param forceRegenerate
     */
    public void setForceRegenerate(boolean forceRegenerate) {
        this.forceRegenerate = forceRegenerate;
    }
}
