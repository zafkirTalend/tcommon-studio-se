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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.ui.IEditorPart;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.repository.model.ERepositoryStatus;
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

    public static final String LATEST_JOB_VERSION = "Latest";

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

    // this character is used only when exporting a job in java, this will be
    // replaced by the correct separator
    // corresponding to the selected platform.
    public static final String TEMP_JAVA_CLASSPATH_SEPARATOR = "@";

    public static ProcessItem getProcessItem(String processId) {
        if (processId == null || "".equals(processId)) {
            return null;
        }

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject object = factory.getLastVersion(processId);
            if (object == null || object.getType() != ERepositoryObjectType.PROCESS) {
                return null;
            }
            ProcessItem lastVersionOfProcess = (ProcessItem) object.getProperty().getItem();
            versionsProcessItemCache.put(processId + "," + lastVersionOfProcess.getProperty().getVersion(), lastVersionOfProcess);
            return lastVersionOfProcess;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    private static Map<String, ProcessItem> versionsProcessItemCache = new HashMap<String, ProcessItem>();

    public static ProcessItem getProcessItem(String processId, String version) {
        if (processId == null || "".equals(processId)) {
            return null;
        }
        if (version == null || LATEST_JOB_VERSION.equals(version)) {
            return getProcessItem(processId);
        }
        ProcessItem selectedProcessItem = versionsProcessItemCache.get(processId + "," + version);
        if (selectedProcessItem != null) {
            return selectedProcessItem;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {

            List<IRepositoryObject> allVersions = factory.getAllVersion(processId);
            for (IRepositoryObject ro : allVersions) {
                if (ro.getType() == ERepositoryObjectType.PROCESS) {
                    versionsProcessItemCache.put(ro.getProperty().getId() + "," + ro.getProperty().getVersion(), (ProcessItem) ro
                            .getProperty().getItem());
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
        ProcessItem item = ProcessorUtilities.getProcessItem(processId);
        if (item != null) {
            return item.getProperty().getLabel();
        }
        return null;
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

    private static boolean isCodeGenerationNeeded(JobInfo jobInfo) {

        // if we do any export, the code generation will always be needed.
        if (exportConfig && (!(jobInfo.getProcess() instanceof IProcess2))) {
            return true;
        }
        IProcess2 attachedProcess = (IProcess2) jobInfo.getProcess();

        if (jobInfo.getFatherJobInfo() != null) {
            JobInfo fatherJobInfo = jobInfo.getFatherJobInfo();
            IProcessor processorFromFather = null;
            while (fatherJobInfo.getProcess().getProcessor() == null) {
                fatherJobInfo = fatherJobInfo.getFatherJobInfo();
            }
            processorFromFather = fatherJobInfo.getProcess().getProcessor();
            if (processorFromFather.isCodeGenerated()) {
                // if the code has been generated already for the father, the code of the children should be up to date.
                return false;
            }
        }

        // if the code has never been generated or if the process has been modified, generate the code
        if (attachedProcess.getProcessor() == null || !attachedProcess.getProcessor().isCodeGenerated()
                || attachedProcess.isNeedRegenerateCode()) {
            return true;
        }

        return false;
    }

    private static boolean generateCode(JobInfo jobInfo, boolean statistics, boolean trace, boolean properties, int option) {
        IProcess currentProcess = null;
        jobList.add(jobInfo);
        ProcessItem selectedProcessItem;

        selectedProcessItem = jobInfo.getProcessItem();
        if (selectedProcessItem == null) {
            if (jobInfo.getJobVersion() != null) {
                selectedProcessItem = getProcessItem(jobInfo.getJobId(), jobInfo.getJobVersion());
            } else {
                // child job
                selectedProcessItem = getProcessItem(jobInfo.getJobId());
            }
        }

        if (selectedProcessItem == null) {
            return false;
        }
        if (jobInfo.getProcess() == null) {
            if (selectedProcessItem != null) {
                IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();
                currentProcess = service.getProcessFromProcessItem(selectedProcessItem);
                jobInfo.setProcess(currentProcess);
            }
            if (currentProcess == null) {
                return false;
            }
        } else {
            currentProcess = jobInfo.getProcess();
        }

        resetRunJobComponentParameterForContextApply(jobInfo, currentProcess);

        boolean toReturn = true;
        if (option != GENERATE_MAIN_ONLY) {
            List<? extends INode> graphicalNodes = currentProcess.getGraphicalNodes();
            for (INode node : graphicalNodes) {
                if ((node != null) && node.getComponent().getName().equals("tRunJob")) {
                    IElementParameter processIdparam = node.getElementParameter("PROCESS_TYPE_PROCESS");
                    String jobId = (String) processIdparam.getValue();
                    ProcessItem processItem = (ProcessItem) processIdparam.getLinkedRepositoryItem();

                    String context = (String) node.getElementParameter("PROCESS_TYPE_CONTEXT").getValue();
                    String version = (String) node.getElementParameter("PROCESS_TYPE_VERSION").getValue();
                    JobInfo subJobInfo = null;
                    if (processItem != null) {
                        subJobInfo = new JobInfo(processItem, context);
                    } else {
                        subJobInfo = new JobInfo(jobId, context, version);
                    }
                    if (jobInfo.isApplyContextToChildren()) {
                        subJobInfo.setApplyContextToChildren(jobInfo.isApplyContextToChildren());
                        subJobInfo.setContextName(jobInfo.getContextName());
                    }
                    subJobInfo.setFatherJobInfo(jobInfo);

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
        }

        // generate the code of the father after the childrens
        // so the code won't have any error during the check, and it will help to check
        // if the generation is really needed.
        if (isCodeGenerationNeeded(jobInfo)) {
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
                        // main job will use stats / traces
                        processor.generateCode(statistics, trace, properties);
                    } catch (ProcessorException pe) {
                        MessageBoxExceptionHandler.process(pe);
                    }
                }
            }
            processor.setContext(currentContext);
            try {
                // main job will use stats / traces
                processor.generateCode(statistics, trace, properties);
            } catch (ProcessorException pe) {
                MessageBoxExceptionHandler.process(pe);
            }

            if (currentProcess instanceof IProcess2) {
                ((IProcess2) currentProcess).setNeedRegenerateCode(false);
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
                    // the corresponding parameter is
                    // EParameterName.PROCESS_TYPE_CONTEXT
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
     * @param version null if no specific version required
     */
    public static boolean generateCode(String processName, String contextName, String version, boolean statistics, boolean trace) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processName, contextName, version);
        boolean genCode = generateCode(jobInfo, statistics, trace, true, GENERATE_ALL_CHILDS);
        jobList.clear();
        return genCode;
    }

    private static boolean generateAllContexts = false;

    /**
     * This function will generate the code of the process and all of this sub process.
     * 
     * @param processName
     * @param contextName
     * @param version null if no specific version required
     */
    public static boolean generateCode(String processId, String contextName, String version, boolean statistics, boolean trace,
            boolean applyContextToChildren) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processId, contextName, version);
        jobInfo.setApplyContextToChildren(applyContextToChildren);
        generateAllContexts = true;
        boolean result = generateCode(jobInfo, statistics, trace, true, GENERATE_ALL_CHILDS);
        generateAllContexts = false;
        jobList.clear();
        return result;
    }

    public static boolean generateCode(ProcessItem process, String contextName, boolean statistics, boolean trace,
            boolean applyContextToChildren) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process, contextName);
        jobInfo.setApplyContextToChildren(applyContextToChildren);
        generateAllContexts = true;
        boolean result = generateCode(jobInfo, statistics, trace, true, GENERATE_ALL_CHILDS);
        generateAllContexts = false;
        jobList.clear();
        return result;
    }

    public static boolean generateCode(ProcessItem process, String contextName, boolean statistics, boolean trace) {
        return generateCode(process, contextName, statistics, trace, false);
    }

    public static boolean generateCode(String processId, String contextName, String version, boolean statistics, boolean trace,
            int option) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processId, contextName, version);
        boolean genCode = generateCode(jobInfo, statistics, trace, true, option);
        jobList.clear();
        return genCode;
    }

    public static boolean generateCode(IProcess process, IContext context, boolean statistics, boolean trace, boolean properties) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process.getId(), context.getName(), process.getVersion());
        jobInfo.setProcess(process);
        jobInfo.setContext(context);
        boolean genCode = false;
        genCode = generateCode(jobInfo, statistics, trace, properties, GENERATE_ALL_CHILDS);
        jobList.clear();
        return genCode;
    }

    public static boolean generateCode(IProcess process, IContext context, boolean statistics, boolean trace, boolean properties,
            int option) {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process, context);
        boolean genCode = generateCode(jobInfo, statistics, trace, properties, option);
        jobList.clear();
        return genCode;
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
    public static String[] getCommandLine(boolean externalUse, String processName, String contextName, int statisticPort,
            int tracePort, String... codeOptions) throws ProcessorException {
        return getCommandLine(null, externalUse, processName, contextName, statisticPort, tracePort, codeOptions);
    }

    public static String[] getCommandLine(boolean externalUse, String processName, String contextName, String version,
            int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
        return getCommandLine(null, externalUse, processName, contextName, version, statisticPort, tracePort, codeOptions);
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
    public static String[] getCommandLine(String targetPlatform, boolean externalUse, String processId, String contextName,
            int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
        ProcessItem selectedProcessItem = getProcessItem(processId);
        return getCommandLine(targetPlatform, externalUse, selectedProcessItem, contextName, statisticPort, tracePort,
                codeOptions);
    }

    public static String[] getCommandLine(String targetPlatform, boolean externalUse, String processId, String contextName,
            String version, int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
        ProcessItem selectedProcessItem = getProcessItem(processId, version);
        return getCommandLine(targetPlatform, externalUse, selectedProcessItem, contextName, statisticPort, tracePort,
                codeOptions);
    }

    public static String[] getCommandLine(String targetPlatform, boolean externalUse, ProcessItem processItem,
            String contextName, int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
        IProcess currentProcess = null;
        IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();

        if (processItem == null) {
            return new String[] {};
        }
        currentProcess = service.getProcessFromProcessItem(processItem);

        if (currentProcess == null) {
            return new String[] {};
        }
        IContext currentContext = getContext(currentProcess, contextName);
        IProcessor processor = getProcessor(currentProcess, currentContext);
        processor.setTargetPlatform(targetPlatform);
        return processor.getCommandLine(externalUse, statisticPort, tracePort, codeOptions);
    }

    public static String[] getMainCommand(String processName, String processVersion, String contextName, int statisticPort,
            int tracePort, String... codeOptions) throws ProcessorException {
        IProcess currentProcess = null;
        ProcessItem selectedProcessItem = null;
        if (processVersion == null || processVersion.equals(LATEST_JOB_VERSION)) {
            selectedProcessItem = getProcessItem(processName);
        } else {
            selectedProcessItem = getProcessItem(processName, processVersion);
        }
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
     * 
     * ggu Comment method "getAllVersionProcessList".
     * 
     * @param processId
     * @return
     */
    public static List<IRepositoryObject> getAllVersionObjectById(String id) {
        if (id == null || "".equals(id)) {
            return null;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            final List<IRepositoryObject> allVersion = factory.getAllVersion(id);
            final IRepositoryObject lastVersion = factory.getLastVersion(id);
            if (lastVersion != null && factory.getStatus(lastVersion) != ERepositoryStatus.DELETED) {
                return allVersion;
            }
        } catch (PersistenceException e) {
            // 
        }

        return null;
    }

    public static Set<JobInfo> getChildrenJobInfo(ProcessItem processItem) {
        Set<JobInfo> jobInfos = new HashSet<JobInfo>();
        List<NodeType> list = processItem.getProcess().getNode();
        for (NodeType nodeType : list) {
            if (nodeType.getComponentName().equals("tRunJob")) { //$NON-NLS-1$
                String jobId = null, jobContext = null, jobVersion = null;

                for (Object obj : nodeType.getElementParameter()) {
                    ElementParameterType element = (ElementParameterType) obj;
                    if (element.getName().contains("PROCESS_TYPE_PROCESS")) {
                        jobId = element.getValue();
                    }
                    if (element.getName().contains("PROCESS_TYPE_CONTEXT")) {
                        jobContext = element.getValue();
                    }
                    if (element.getName().contains("PROCESS_TYPE_VERSION")) {
                        jobVersion = element.getValue();
                    }
                }
                ProcessItem item = ProcessorUtilities.getProcessItem(jobId, jobVersion);
                if (item != null) {
                    JobInfo jobInfo = new JobInfo(item, jobContext);
                    if (!jobInfos.contains(jobInfo)) {
                        jobInfos.add(jobInfo);
                        jobInfos.addAll(getChildrenJobInfo(item));
                    }
                }
            }
        }
        return jobInfos;
    }
}
