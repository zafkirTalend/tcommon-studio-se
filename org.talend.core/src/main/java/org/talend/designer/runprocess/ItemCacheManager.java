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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class ItemCacheManager {

    public static final String LATEST_VERSION = "Latest";

    // cache will be cleared at the begining of any code generation, to ensure
    // there is no problem and that old items are not kept.
    private static Map<String, ProcessItem> processItemCache = new HashMap<String, ProcessItem>();

    private static Map<String, JobletProcessItem> jobletItemCache = new HashMap<String, JobletProcessItem>();

    public static void clearCache() {
        processItemCache.clear();
        jobletItemCache.clear();
    }

    public static ProcessItem getProcessItem(Project project, String processId) {
        if (processId == null || "".equals(processId)) {
            return null;
        }
        ProcessItem lastVersionOfProcess = processItemCache.get(processId + " -- " + LATEST_VERSION);

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject object = factory.getLastVersion(project, processId);
            if (object == null || object.getType() != ERepositoryObjectType.PROCESS) {
                return null;
            }
            lastVersionOfProcess = (ProcessItem) object.getProperty().getItem();
            processItemCache.put(processId + " -- " + LATEST_VERSION, lastVersionOfProcess);
            processItemCache.put(processId + " -- " + lastVersionOfProcess.getProperty().getVersion(), lastVersionOfProcess);
            return lastVersionOfProcess;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    public static ProcessItem getProcessItem(String processId) {
        Project project = CorePlugin.getDefault().getProxyRepositoryFactory().getRepositoryContext().getProject();
        return getProcessItem(project, processId);
    }

    public static ProcessItem getProcessItem(String processId, String version) {
        Project project = CorePlugin.getDefault().getProxyRepositoryFactory().getRepositoryContext().getProject();
        return getProcessItem(project, processId, version);
    }

    public static ProcessItem getProcessItem(Project project, String processId, String version) {
        if (processId == null || "".equals(processId)) {
            return null;
        }
        if (version == null || LATEST_VERSION.equals(version)) {
            return getProcessItem(project, processId);
        }
        ProcessItem selectedProcessItem = processItemCache.get(processId + " -- " + version);
        if (selectedProcessItem != null) {
            return selectedProcessItem;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {

            List<IRepositoryObject> allVersions = factory.getAllVersion(project, processId);
            for (IRepositoryObject ro : allVersions) {
                if (ro.getType() == ERepositoryObjectType.PROCESS) {
                    processItemCache.put(processId + " -- " + ro.getVersion(), (ProcessItem) ro.getProperty().getItem());
                    if (ro.getVersion().equals(version)) {
                        selectedProcessItem = (ProcessItem) ro.getProperty().getItem();
                    }
                }
            }
            return selectedProcessItem;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    public static JobletProcessItem getJobletProcessItem(Project project, String jobletId) {
        if (jobletId == null || "".equals(jobletId)) {
            return null;
        }
        JobletProcessItem lastVersionOfJoblet = jobletItemCache.get(jobletId + " -- " + LATEST_VERSION);

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject object = factory.getLastVersion(project, jobletId);
            if (object == null || object.getType() != ERepositoryObjectType.PROCESS) {
                return null;
            }
            lastVersionOfJoblet = (JobletProcessItem) object.getProperty().getItem();
            jobletItemCache.put(jobletId + " -- " + LATEST_VERSION, lastVersionOfJoblet);
            jobletItemCache.put(jobletId + " -- " + lastVersionOfJoblet.getProperty().getVersion(), lastVersionOfJoblet);
            return lastVersionOfJoblet;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    public static JobletProcessItem getJobletProcessItem(String jobletId) {
        Project project = CorePlugin.getDefault().getProxyRepositoryFactory().getRepositoryContext().getProject();
        return getJobletProcessItem(project, jobletId);
    }

    public static JobletProcessItem getJobletProcessItem(Project project, String jobletId, String version) {
        if (jobletId == null || "".equals(jobletId)) {
            return null;
        }
        if (version == null || LATEST_VERSION.equals(version)) {
            return getJobletProcessItem(jobletId);
        }
        JobletProcessItem selectedProcessItem = jobletItemCache.get(jobletId + " -- " + version);
        if (selectedProcessItem != null) {
            return selectedProcessItem;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {

            List<IRepositoryObject> allVersions = factory.getAllVersion(project, jobletId);
            for (IRepositoryObject ro : allVersions) {
                if (ro.getType() == ERepositoryObjectType.JOBLET) {
                    jobletItemCache.put(jobletId + " -- " + ro.getVersion(), (JobletProcessItem) ro.getProperty().getItem());
                    if (ro.getVersion().equals(version)) {
                        selectedProcessItem = (JobletProcessItem) ro.getProperty().getItem();
                    }
                }
            }
            return selectedProcessItem;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    public static JobletProcessItem getJobletProcessItem(String jobletId, String version) {
        Project project = CorePlugin.getDefault().getProxyRepositoryFactory().getRepositoryContext().getProject();
        return getJobletProcessItem(project, jobletId, version);
    }
}
