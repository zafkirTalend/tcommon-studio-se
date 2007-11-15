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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ui.IEditorPart;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.INode;
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

    public static final int GENERATE_MAIN_ONLY = 1;

    public static final int GENERATE_WITH_FIRST_CHILD = 2;

    public static final int GENERATE_ALL_CHILDS = 3;

    private static String interpreter, codeLocation, libraryPath;

    private static boolean exportConfig = false;

    private static List<IEditorPart> openedEditors = new ArrayList<IEditorPart>();

    public static void addOpenEditor(IEditorPart editor) {
        openedEditors.add(editor);
    }

    public static void editorClosed(IEditorPart editor) {
        openedEditors.remove(editor);
    }

    public static List<IEditorPart> getOpenedEditors() {
        return openedEditors;
    }

    // this character is used only when exporting a job in java, this will be replaced by the correct separator
    // corresponding to the selected platform.
    public static final String TEMP_JAVA_CLASSPATH_SEPARATOR = "@";

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

    public static void setExportConfig(String exportInterpreter, String exportCodeLocation, String exportLibraryPath) {
        interpreter = exportInterpreter;
        codeLocation = exportCodeLocation;
        libraryPath = exportLibraryPath;
        exportConfig = true;
    }

    public static boolean isExportConfig() {
        return exportConfig;
    }

    public static void resetExportConfig() {
        interpreter = null;
        codeLocation = null;
        libraryPath = null;
        exportConfig = false;
    }

    public static String getInterpreter() {
        return interpreter;
    }

    public static String getLibraryPath() {
        return libraryPath;
    }

    public static String getCodeLocation() {
        return codeLocation;
    }

    /**
     * Process need to be loaded first to use this function.
     * 
     * @param process
     * @param context
     * @return
     */
    public static IProcessor getProcessor(IProcess process, IContext context) {
        IProcessor processor = getProcessor(process);
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
        if (process.getProcessor() == null) {
            IRunProcessService service = CorePlugin.getDefault().getRunProcessService();
            return service.createCodeProcessor(process, ((RepositoryContext) CorePlugin.getContext().getProperty(
                    Context.REPOSITORY_CONTEXT_KEY)).getProject().getLanguage(), true);
        } else {
            return process.getProcessor();
        }
    }

    private static boolean generateCode(JobInfo jobInfo, boolean statistics, boolean trace, boolean properties,
            int option) {
        IProcess currentProcess = null;
        jobList.add(jobInfo);
        ProcessItem selectedProcessItem = getProcessItem(jobInfo.getJobName());
        if (selectedProcessItem == null) {
            return false;
        }
        if (jobInfo.getProcess() == null) {
            if (selectedProcessItem != null) {
                IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();
                currentProcess = service.getProcessFromProcessItem(selectedProcessItem);
            }
            if (currentProcess == null) {
                return false;
            }
        } else {
            currentProcess = jobInfo.getProcess();

        }

        resetRunJobComponentParameterForContextApply(jobInfo, currentProcess);

        IContext currentContext;
        if (jobInfo.getContext() == null) {
            currentContext = getContext(currentProcess, jobInfo.getContextName());
        } else {
            currentContext = jobInfo.getContext();
        }
        IProcessor processor = getProcessor(currentProcess);

        // See issue 2188
        if (generateAllContexts) {
            List<IContext> list = currentProcess.getContextManager().getListContext();
            for (IContext context : list) {
                if (context.getName().equals(currentContext.getName())) {
                    continue;
                }
                processor.setContext(context);
                try {
                    processor.generateCode(statistics, trace, properties); // main job will use stats / traces
                } catch (ProcessorException pe) {
                    MessageBoxExceptionHandler.process(pe);
                }
            }
        }
        processor.setContext(currentContext);
        try {
            processor.generateCode(statistics, trace, properties); // main job will use stats / traces
        } catch (ProcessorException pe) {
            MessageBoxExceptionHandler.process(pe);
        }

        boolean toReturn = true;
        if (selectedProcessItem.getProcess().getRequired() != null && (option != GENERATE_MAIN_ONLY)) {
            EList emfJobList = selectedProcessItem.getProcess().getRequired().getJob();
            for (int j = 0; j < emfJobList.size() && toReturn; j++) {
                JobType jType = (JobType) emfJobList.get(j);
                JobInfo subJobInfo = new JobInfo(jType);
                if (jobInfo.isApplyContextToChildren()) {
                    subJobInfo.setApplyContextToChildren(jobInfo.isApplyContextToChildren());
                    subJobInfo.setContextName(jobInfo.getContextName());
                }
                if (!jobList.contains(subJobInfo)) {
                    // children won't have stats / traces
                    if (option == GENERATE_WITH_FIRST_CHILD) {
                        toReturn = generateCode(subJobInfo, false, false, true, GENERATE_MAIN_ONLY);
                    } else {
                        toReturn = generateCode(subJobInfo, false, false, true, GENERATE_ALL_CHILDS);
                    }
                }
            }
        }
        return toReturn;
    }

    /**
     * This method is used to reset the tRunJob component's context,see feature 1625.
     * 
     * @param jobInfo
     * @param currentProcess
     */
    private static void resetRunJobComponentParameterForContextApply(JobInfo jobInfo, IProcess currentProcess) {
        if (jobInfo.isApplyContextToChildren()) {
            for (Iterator<? extends INode> iter = currentProcess.getGraphicalNodes().iterator(); iter.hasNext();) {
                INode node = iter.next();
                if ((node != null) && node.getComponent().getName().equals("tRunJob")) {
                    Element element = (Element) node;
                    // the corresponding parameter is EParameterName.PROCESS_TYPE_CONTEXT
                    element.setPropertyValue("PROCESS_TYPE_CONTEXT", jobInfo.getContextName());
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
    public static boolean generateCode(String processName, String contextName, boolean statistics, boolean trace) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processName, contextName);
        return generateCode(jobInfo, statistics, trace, true, GENERATE_ALL_CHILDS);
    }

    private static boolean generateAllContexts = false;

    /**
     * This function will generate the code of the process and all of this sub process.
     * 
     * @param processName
     * @param contextName
     */
    public static boolean generateCode(String processName, String contextName, boolean statistics, boolean trace,
            boolean applyContextToChildren) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processName, contextName);
        jobInfo.setApplyContextToChildren(applyContextToChildren);
        generateAllContexts = true;
        boolean result = generateCode(jobInfo, statistics, trace, true, GENERATE_ALL_CHILDS);
        generateAllContexts = false;
        return result;
    }

    public static boolean generateCode(String processName, String contextName, boolean statistics, boolean trace,
            int option) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processName, contextName);
        return generateCode(jobInfo, statistics, trace, true, option);
    }

    public static boolean generateCode(IProcess process, IContext context, boolean statistics, boolean trace,
            boolean properties) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process.getName(), context.getName());
        jobInfo.setProcess(process);
        jobInfo.setContext(context);
        return generateCode(jobInfo, statistics, trace, properties, GENERATE_ALL_CHILDS);
    }

    public static boolean generateCode(IProcess process, IContext context, boolean statistics, boolean trace,
            boolean properties, int option) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process.getName(), context.getName());
        jobInfo.setProcess(process);
        jobInfo.setContext(context);
        return generateCode(jobInfo, statistics, trace, properties, option);
    }

    /**
     * 
     * Get the command line to launch the job.
     * 
     * @param externalUse if true, will add "" around path and change \ to /
     * @param processName
     * @param contextName
     * @param codeOptions
     * @return
     * @throws ProcessorException
     */
    public static String[] getCommandLine(boolean externalUse, String processName, String contextName,
            int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
        return getCommandLine(null, externalUse, processName, contextName, statisticPort, tracePort, codeOptions);
    }

    /**
     * Get the command line to launch the job.
     * 
     * @param targetPlatform for example Platform.OS_WIN32 / Platform.OS_LINUX
     * @param externalUse
     * @param processName
     * @param contextName
     * @param statisticPort
     * @param tracePort
     * @param codeOptions
     * @return
     * @throws ProcessorException
     */
    public static String[] getCommandLine(String targetPlatform, boolean externalUse, String processName,
            String contextName, int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
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
        processor.setTargetPlatform(targetPlatform);
        return processor.getCommandLine(externalUse, statisticPort, tracePort, codeOptions);
    }

    public static String[] getMainCommand(String processName, String contextName, int statisticPort, int tracePort,
            String... codeOptions) throws ProcessorException {
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

        String[] cmd = new String[] { processor.getCodePath().removeFirstSegments(1).toString().replace("/", ".") };
        if (codeOptions != null) {
            for (int i = 0; i < codeOptions.length; i++) {
                String string = codeOptions[i];
                if (string != null) {
                    cmd = (String[]) ArrayUtils.add(cmd, string);
                }
            }
        }
        if (contextName != null) {
            cmd = (String[]) ArrayUtils.add(cmd, "--context=" + contextName);
        }
        if (statisticPort != -1) {
            cmd = (String[]) ArrayUtils.add(cmd, "--stat_port=" + statisticPort);
        }
        if (tracePort != -1) {
            cmd = (String[]) ArrayUtils.add(cmd, "--trace_port=" + tracePort);
        }
        return cmd;
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
            return true;
        }

        @Override
        public String toString() {
            return "job:" + jobName + " / context:" + contextName; //$NON-NLS-1$ //$NON-NLS-2$
        }

        String jobName, contextName;

        IProcess process;

        IContext context;

        boolean applyContextToChildren = false;

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

        /**
         * Getter for applyContextToChildren.
         * 
         * @return the applyContextToChildren
         */
        public boolean isApplyContextToChildren() {
            return this.applyContextToChildren;
        }

        /**
         * Sets the applyContextToChildren.
         * 
         * @param applyContextToChildren the applyContextToChildren to set
         */
        public void setApplyContextToChildren(boolean applyContextToChildren) {
            this.applyContextToChildren = applyContextToChildren;
        }

    }
}
