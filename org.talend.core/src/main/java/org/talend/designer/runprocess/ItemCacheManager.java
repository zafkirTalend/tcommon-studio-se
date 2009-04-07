// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class ItemCacheManager {

    public static final String LATEST_VERSION = "Latest"; //$NON-NLS-1$

    // cache will be cleared at the begining of any code generation, to ensure
    // there is no problem and that old items are not kept.
    private static Map<String, ProcessItem> processItemCache = new HashMap<String, ProcessItem>();

    private static Map<String, JobletProcessItem> jobletItemCache = new HashMap<String, JobletProcessItem>();

    public static void clearCache() {
        processItemCache.clear();
        jobletItemCache.clear();
    }

    public static ProcessItem getProcessItem(Project project, String processId) {
        if (processId == null || "".equals(processId)) { //$NON-NLS-1$
            return null;
        }
        ProcessItem lastVersionOfProcess = processItemCache.get(processId + " -- " + LATEST_VERSION); //$NON-NLS-1$

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject object = factory.getLastVersion(project, processId);
            if (object == null || object.getType() != ERepositoryObjectType.PROCESS) {
                return null;
            }
            lastVersionOfProcess = (ProcessItem) object.getProperty().getItem();
            processItemCache.put(processId + " -- " + LATEST_VERSION, lastVersionOfProcess); //$NON-NLS-1$
            processItemCache.put(processId + " -- " + lastVersionOfProcess.getProperty().getVersion(), lastVersionOfProcess); //$NON-NLS-1$
            return lastVersionOfProcess;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    public static ProcessItem getProcessItem(String processId) {
        ProjectManager instance = ProjectManager.getInstance();
        ProcessItem processItem = getRefProcessItem(instance.getCurrentProject(), processId);
        return processItem;
    }

    public static ProcessItem getRefProcessItem(Project project, String processId) {
        ProjectManager instance = ProjectManager.getInstance();
        ProcessItem processItem = getProcessItem(project, processId);
        if (processItem == null) {
            for (Project p : instance.getReferencedProjects(project)) {
                processItem = getRefProcessItem(p, processId);
                if (processItem != null) {
                    break;
                }
            }
        }

        return processItem;
    }

    public static ProcessItem getProcessItem(String processId, String version) {
        ProcessItem refProcessItem = getRefProcessItem(ProjectManager.getInstance().getCurrentProject(), processId, version);
        return refProcessItem;
    }

    public static ProcessItem getRefProcessItem(Project project, String processId, String version) {
        ProjectManager projectManager = ProjectManager.getInstance();
        ProcessItem processItem = getProcessItem(project, processId, version);
        if (processItem == null) {
            for (Project p : projectManager.getReferencedProjects(project)) {
                processItem = getRefProcessItem(p, processId, version);
                if (processItem != null) {
                    break;
                }
            }
        }
        return processItem;
    }

    public static ProcessItem getProcessItem(Project project, String processId, String version) {
        if (processId == null || "".equals(processId)) { //$NON-NLS-1$
            return null;
        }
        if (version == null || LATEST_VERSION.equals(version)) {
            return getProcessItem(project, processId);
        }
        ProcessItem selectedProcessItem = processItemCache.get(processId + " -- " + version); //$NON-NLS-1$
        if (selectedProcessItem != null) {
            return selectedProcessItem;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {

            List<IRepositoryObject> allVersions = factory.getAllVersion(project, processId);
            for (IRepositoryObject ro : allVersions) {
                if (ro.getType() == ERepositoryObjectType.PROCESS) {
                    processItemCache.put(processId + " -- " + ro.getVersion(), (ProcessItem) ro.getProperty().getItem()); //$NON-NLS-1$
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

    public static String getProcessNameByProcessId(String processId) {
        ProcessItem item = getProcessItem(processId);
        if (item != null) {
            return item.getProperty().getLabel();
        }
        return null;
    }

    public static JobletProcessItem getJobletProcessItem(Project project, String jobletId) {
        if (jobletId == null || "".equals(jobletId)) { //$NON-NLS-1$
            return null;
        }
        JobletProcessItem lastVersionOfJoblet = jobletItemCache.get(jobletId + " -- " + LATEST_VERSION); //$NON-NLS-1$

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject object = factory.getLastVersion(project, jobletId);
            if (object == null || object.getType() != ERepositoryObjectType.PROCESS) {
                return null;
            }
            lastVersionOfJoblet = (JobletProcessItem) object.getProperty().getItem();
            jobletItemCache.put(jobletId + " -- " + LATEST_VERSION, lastVersionOfJoblet); //$NON-NLS-1$
            jobletItemCache.put(jobletId + " -- " + lastVersionOfJoblet.getProperty().getVersion(), lastVersionOfJoblet); //$NON-NLS-1$
            return lastVersionOfJoblet;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    public static JobletProcessItem getJobletProcessItem(String jobletId) {
        ProjectManager projectManager = ProjectManager.getInstance();
        JobletProcessItem jobletProcessItem = getJobletProcessItem(projectManager.getCurrentProject(), jobletId);

        if (jobletProcessItem == null) {
            for (Project p : projectManager.getReferencedProjects()) {
                jobletProcessItem = getJobletProcessItem(p, jobletId);
                if (jobletProcessItem != null) {
                    break;
                }
            }
        }
        return jobletProcessItem;
    }

    public static JobletProcessItem getJobletProcessItem(Project project, String jobletId, String version) {
        if (jobletId == null || "".equals(jobletId)) { //$NON-NLS-1$
            return null;
        }
        if (version == null || LATEST_VERSION.equals(version)) {
            return getJobletProcessItem(jobletId);
        }
        JobletProcessItem selectedProcessItem = jobletItemCache.get(jobletId + " -- " + version); //$NON-NLS-1$
        if (selectedProcessItem != null) {
            return selectedProcessItem;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {

            List<IRepositoryObject> allVersions = factory.getAllVersion(project, jobletId);
            for (IRepositoryObject ro : allVersions) {
                if (ro.getType() == ERepositoryObjectType.JOBLET) {
                    jobletItemCache.put(jobletId + " -- " + ro.getVersion(), (JobletProcessItem) ro.getProperty().getItem()); //$NON-NLS-1$
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
        ProjectManager projectManager = ProjectManager.getInstance();
        JobletProcessItem jobletProcessItem = getJobletProcessItem(projectManager.getCurrentProject(), jobletId, version);

        if (jobletProcessItem == null) {
            for (Project p : projectManager.getReferencedProjects()) {
                jobletProcessItem = getJobletProcessItem(p, jobletId, version);
                if (jobletProcessItem != null) {
                    break;
                }
            }
        }
        return jobletProcessItem;
    }
}
