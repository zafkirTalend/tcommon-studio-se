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
package org.talend.designer.runprocess;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.i18n.Messages;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.JobType;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class ProcessorUtilities {

    public static ProcessItem getProcessItem(String processName) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        ProcessItem selectedProcessItem = null;

        try {
            List<IRepositoryObject> list = factory.getAll(ERepositoryObjectType.PROCESS);

            for (IRepositoryObject process : list) {
                if (processName.equals(process.getLabel())) {
                    if (process.getProperty().getItem() instanceof ProcessItem) {
                        selectedProcessItem = (ProcessItem) process.getProperty().getItem();
                        break;
                    }
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return selectedProcessItem;
    }

    /**
     * Process need to be loaded to use this function.
     * 
     * @param process
     * @param selectedContext
     * @return
     */
    public static IContext getContext(IProcess process, String selectedContext) {
        return process.getContextManager().getContext(selectedContext);
    }

    /**
     * Process need to be loaded first to use this function.
     * 
     * @param process
     * @param context
     * @return
     */
    public static IProcessor getProcessor(IProcess process, IContext context) {
        IRunProcessService service = CorePlugin.getDefault().getRunProcessService();
        IProcessor processor = service.createCodeProcessor(process, ((RepositoryContext) CorePlugin.getContext()
                .getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject().getLanguage(), true);
        processor.setContext(context);
        return processor;
    }

    /**
     * Process need to be loaded first to use this function.
     * 
     * @param process
     * @param context
     * @return
     */
    public static IProcessor getProcessor(IProcess process) {
        IRunProcessService service = CorePlugin.getDefault().getRunProcessService();
        IProcessor processor = service.createCodeProcessor(process, ((RepositoryContext) CorePlugin.getContext()
                .getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject().getLanguage(), true);
        return processor;
    }

    private static void generateCode(JobInfo jobInfo) {
        IProcess currentProcess = null;
        jobList.add(jobInfo);
        ProcessItem selectedProcessItem = getProcessItem(jobInfo.getJobName());
        if (selectedProcessItem != null) {
            IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();
            currentProcess = service.getProcessFromProcessItem(selectedProcessItem);
        }
        if (currentProcess == null) {
            return;
        }
        IContext currentContext = getContext(currentProcess, jobInfo.getContextName());
        IProcessor processor = getProcessor(currentProcess, currentContext);
        try {
            processor.generateCode(currentContext, false, false, true);
        } catch (ProcessorException pe) {
            MessageBoxExceptionHandler.process(pe);
        }

        if (selectedProcessItem.getProcess().getRequired() != null) {
            EList emfJobList = selectedProcessItem.getProcess().getRequired().getJob();
            for (int j = 0; j < emfJobList.size(); j++) {
                JobType jType = (JobType) emfJobList.get(j);
                JobInfo subJobInfo = new JobInfo(jType);
                if (!jobList.contains(subJobInfo)) {
                    generateCode(subJobInfo);
                }
            }
        }
    }

    static List<JobInfo> jobList = new ArrayList<JobInfo>();

    /**
     * This function will generate the code of the process and all of this sub process.
     * 
     * @param processName
     * @param contextName
     */
    public static void generateCode(String processName, String contextName) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processName, contextName);
        generateCode(jobInfo);

    }

    public static String[] getCommandLine(String processName, String contextName, String... codeOptions)
            throws ProcessorException {
        IProcess currentProcess = null;
        ProcessItem selectedProcessItem = getProcessItem(processName);
        if (selectedProcessItem != null) {
            IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();
            currentProcess = service.getProcessFromProcessItem(selectedProcessItem);
        }
        if (currentProcess == null) {
            return new String[] {};
        }
        IContext currentContext = getContext(currentProcess, contextName);
        IProcessor processor = getProcessor(currentProcess, currentContext);
        return processor.getCommandLine(IProcessor.NO_STATISTICS, IProcessor.NO_TRACES, codeOptions);
    }

    /**
     * DOC nrousseau ProcessController class global comment. Detailled comment <br/>
     * 
     * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
     * 
     */
    private static class JobInfo {

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((contextName == null) ? 0 : contextName.hashCode());
            result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
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
            if (jobName == null) {
                if (other.jobName != null) {
                    return false;
                }
            } else if (!jobName.equals(other.jobName)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "job:" + jobName + " / context:" + contextName; //$NON-NLS-1$ //$NON-NLS-2$
        }

        String jobName, contextName;

        JobInfo(String jobName, String contextName) {
            this.jobName = jobName;
            this.contextName = contextName;
        }

        JobInfo(JobType jobType) {
            this.jobName = jobType.getName();
            this.contextName = jobType.getContext();
        }

        public String getContextName() {
            return contextName;
        }

        public void setContextName(String contextName) {
            this.contextName = contextName;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String processName) {
            this.jobName = processName;
        }
    }
}
