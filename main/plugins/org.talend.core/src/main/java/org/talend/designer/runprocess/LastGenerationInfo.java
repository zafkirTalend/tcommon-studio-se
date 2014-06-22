// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.process.JobInfo;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class LastGenerationInfo {

    private HashMap<String, Set<ModuleNeeded>> modulesNeededPerJob;

    private HashMap<String, Set<String>> routinesNeededPerJob;

    private HashMap<String, Set<ModuleNeeded>> modulesNeededWithSubjobPerJob;

    private HashMap<String, Set<String>> routinesNeededWithSubjobPerJob;

    private HashMap<String, Set<String>> contextPerJob;

    private HashMap<String, Boolean> useDynamic;

    private static LastGenerationInfo instance;

    private JobInfo lastMainJob;

    private Set<JobInfo> lastGeneratedjobs; // main job + child jobs

    private LastGenerationInfo() {
        modulesNeededPerJob = new HashMap<String, Set<ModuleNeeded>>();
        contextPerJob = new HashMap<String, Set<String>>();
        modulesNeededWithSubjobPerJob = new HashMap<String, Set<ModuleNeeded>>();
        lastGeneratedjobs = new HashSet<JobInfo>();
        routinesNeededPerJob = new HashMap<String, Set<String>>();
        routinesNeededWithSubjobPerJob = new HashMap<String, Set<String>>();
        useDynamic = new HashMap<String, Boolean>();
    }

    public static LastGenerationInfo getInstance() {
        if (instance == null) {
            instance = new LastGenerationInfo();
        }
        return instance;
    }

    /**
     * 
     * @return the modulesNeededPerJob
     */
    public Set<ModuleNeeded> getModulesNeededWithSubjobPerJob(String jobId, String jobVersion) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (!modulesNeededWithSubjobPerJob.containsKey(key)) {
            modulesNeededWithSubjobPerJob.put(key, new HashSet<ModuleNeeded>());
        }
        return modulesNeededWithSubjobPerJob.get(key);
    }

    /**
     * 
     * @return the modulesNeededPerJob
     */
    public Set<ModuleNeeded> getModulesNeededPerJob(String jobId, String jobVersion) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (!modulesNeededPerJob.containsKey(key)) {
            modulesNeededPerJob.put(key, new HashSet<ModuleNeeded>());
        }
        return modulesNeededPerJob.get(key);
    }

    /**
     * Getter for contextPerJob.
     * 
     * @return the contextPerJob
     */
    public Set<String> getContextPerJob(String jobId, String jobVersion) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (!contextPerJob.containsKey(key)) {
            contextPerJob.put(key, new HashSet<String>());
        }
        return contextPerJob.get(key);
    }

    /**
     * Sets the modulesNeededPerJob.
     * 
     * @param modulesNeededPerJob the modulesNeededPerJob to set
     */
    public void setModulesNeededPerJob(String jobId, String jobVersion, Set<ModuleNeeded> modulesNeeded) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        modulesNeededPerJob.put(key, new HashSet<ModuleNeeded>(modulesNeeded));
    }

    /**
     * Sets the modulesNeededWithSubjobPerJob.
     * 
     * @param modulesNeededWithSubjobPerJob the modulesNeededWithSubjobPerJob to set
     */
    public void setModulesNeededWithSubjobPerJob(String jobId, String jobVersion, Set<ModuleNeeded> modulesNeeded) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (modulesNeeded == null) {
            modulesNeededWithSubjobPerJob.put(key, null);
        } else {
            modulesNeededWithSubjobPerJob.put(key, new HashSet<ModuleNeeded>(modulesNeeded));
        }
    }

    /**
     * Sets the contextPerJob.
     * 
     * @param contextPerJob the contextPerJob to set
     */
    public void setContextPerJob(String jobId, String jobVersion, Set<String> contexts) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        contextPerJob.put(key, new HashSet<String>(contexts));
    }

    public void setUseDynamic(String jobId, String jobVersion, boolean dynamic) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        useDynamic.put(key, dynamic);
    }

    public boolean isUseDynamic(String jobId, String jobVersion) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (!useDynamic.containsKey(key)) {
            return false;
        }
        return useDynamic.get(key);
    }

    public HashMap<String, Boolean> getUseDynamicMap() {
        return this.useDynamic;
    }

    /**
     * Getter for lastMainJob.
     * 
     * @return the lastMainJob
     */
    public JobInfo getLastMainJob() {
        return this.lastMainJob;
    }

    /**
     * Sets the lastMainJob.
     * 
     * @param lastMainJob the lastMainJob to set
     */
    public void setLastMainJob(JobInfo lastMainJob) {
        this.lastMainJob = lastMainJob;
    }

    /**
     * Getter for lastGeneratedjobs.
     * 
     * @return the lastGeneratedjobs
     */
    public Set<JobInfo> getLastGeneratedjobs() {
        return this.lastGeneratedjobs;
    }

    /**
     * Sets the lastGeneratedjobs.
     * 
     * @param lastGeneratedjobs the lastGeneratedjobs to set
     */
    public void setLastGeneratedjobs(Set<JobInfo> lastGeneratedjobs) {
        this.lastGeneratedjobs = lastGeneratedjobs;
    }

    /**
     * 
     * @return the modulesNeededPerJob
     */
    public Set<String> getRoutinesNeededPerJob(String jobId, String jobVersion) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (!routinesNeededPerJob.containsKey(key)) {
            routinesNeededPerJob.put(key, new HashSet<String>());
        }
        return routinesNeededPerJob.get(key);
    }

    /**
     * Sets the routinesNeededPerJob.
     * 
     * @param modulesNeededPerJob the modulesNeededPerJob to set
     */
    public void setRoutinesNeededPerJob(String jobId, String jobVersion, Set<String> modulesNeeded) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        routinesNeededPerJob.put(key, new HashSet<String>(modulesNeeded));
    }

    /**
     * 
     * @return the modulesNeededPerJob
     */
    public Set<String> getRoutinesNeededWithSubjobPerJob(String jobId, String jobVersion) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        if (!routinesNeededWithSubjobPerJob.containsKey(key)) {
            routinesNeededWithSubjobPerJob.put(key, new HashSet<String>());
        }
        return routinesNeededWithSubjobPerJob.get(key);
    }

    /**
     * Sets the routinesNeededPerJob.
     * 
     * @param modulesNeededPerJob the modulesNeededPerJob to set
     */
    public void setRoutinesNeededWithSubjobPerJob(String jobId, String jobVersion, Set<String> modulesNeeded) {
        String key = jobId + "_" + jobVersion; //$NON-NLS-1$
        routinesNeededWithSubjobPerJob.put(key, new HashSet<String>(modulesNeeded));
    }

}
