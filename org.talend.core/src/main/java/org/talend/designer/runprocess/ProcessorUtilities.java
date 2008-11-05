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
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.ui.editor.ITalendJobEditor;
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

    private static List<IEditorPart> openedEditors = new ArrayList<IEditorPart>();

    static Set<ProcessItem> processItems = new HashSet<ProcessItem>();

    private static IDesignerCoreService designerCoreService = (IDesignerCoreService) GlobalServiceRegister.getDefault()
            .getService(IDesignerCoreService.class);

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
        if (jobInfo.isForceRegenerate()) {
            return true;
        }
        IProcess attachedProcess = jobInfo.getProcess();

        if (jobInfo.getFatherJobInfo() != null) {
            JobInfo fatherJobInfo = jobInfo.getFatherJobInfo();
            IProcessor processorFromFather = null;
            while (fatherJobInfo != null && fatherJobInfo.getProcess().getProcessor() == null) {
                fatherJobInfo = fatherJobInfo.getFatherJobInfo();
            }
            if (fatherJobInfo == null) {
                return true;
            }
            processorFromFather = fatherJobInfo.getProcess().getProcessor();
            if (processorFromFather.isCodeGenerated()) {
                // if the code has been generated already for the father, the code of the children should be up to date.
                Date modificationDate = jobInfo.getProcess().getProperty().getModificationDate();
                String jobId = jobInfo.getJobId();
                Date originalDate = designerCoreService.getJobModificationDateMap(getTopJobInfo(fatherJobInfo).getProcess()).get(
                        jobId);
                if (originalDate == null || modificationDate.compareTo(originalDate) != 0) {
                    jobInfo.getFatherJobInfo().setForceRegenerate(true);
                    return true;
                }
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

    private static boolean generateCode(JobInfo jobInfo, String selectedContextName, boolean statistics, boolean trace,
            boolean properties, int option) throws ProcessorException {
        if (jobInfo.getFatherJobInfo() == null) {
            // if it's the father, reset the processMap to ensure to have a good
            // code generation
            ItemCacheManager.clearCache();
            // if it's the father or main job, initialize the routines name
            CodeGeneratorRoutine.initializeRoutinesName(jobInfo.getProcessItem());
        }

        IProcess currentProcess = null;
        jobList.add(jobInfo);
        ProcessItem selectedProcessItem;

        selectedProcessItem = jobInfo.getProcessItem();
        // if (selectedProcessItem == null) {
        // if (jobInfo.getJobVersion() != null) {
        // selectedProcessItem = ItemCacheManager.getProcessItem(jobInfo.getJobId(), jobInfo.getJobVersion());
        // } else {
        // // child job
        // selectedProcessItem = ItemCacheManager.getProcessItem(jobInfo.getJobId());
        // }
        // }

        if (selectedProcessItem == null && jobInfo.getJobVersion() == null) {
            // child job
            selectedProcessItem = ItemCacheManager.getProcessItem(jobInfo.getJobId());
        }

        if (jobInfo.getJobVersion() != null) {
            selectedProcessItem = ItemCacheManager.getProcessItem(jobInfo.getJobId(), jobInfo.getJobVersion());
        }

        if (selectedProcessItem == null && jobInfo.getProcess() == null) {
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

        resetRunJobComponentParameterForContextApply(jobInfo, currentProcess, selectedContextName);

        boolean toReturn = true;
        if (option != GENERATE_MAIN_ONLY) {
            // handle subjob in joblet. see bug 004937: tRunJob in a Joblet
            List<? extends INode> graphicalNodes = currentProcess.getGeneratingNodes();
            // List<? extends INode> graphicalNodes = currentProcess.getGraphicalNodes();
            for (INode node : graphicalNodes) {
                if ((node != null) && node.getComponent().getName().equals("tRunJob")) {
                    IElementParameter processIdparam = node.getElementParameter("PROCESS_TYPE_PROCESS");
                    String jobId = (String) processIdparam.getValue();
                    ProcessItem processItem = null;

                    String context = (String) node.getElementParameter("PROCESS_TYPE_CONTEXT").getValue();
                    String version = (String) node.getElementParameter("PROCESS_TYPE_VERSION").getValue();
                    JobInfo subJobInfo = null;
                    subJobInfo = new JobInfo(jobId, context, version);
                    // get processitem from job
                    processItem = ItemCacheManager.getProcessItem(jobId, version);

                    if (jobInfo.isApplyContextToChildren()) {
                        subJobInfo.setApplyContextToChildren(jobInfo.isApplyContextToChildren());
                        // see bug 0003862: Export job with the flag "Apply to children" if the child don't have the
                        // same context fails.
                        if (checkIfContextExisted(processItem, selectedContextName)) {
                            subJobInfo.setContextName(selectedContextName);
                        } else {
                            // use the default context of subjob
                            String defaultContext = processItem.getProcess().getDefaultContext();
                            Element element = (Element) node;
                            element.setPropertyValue("PROCESS_TYPE_CONTEXT", defaultContext);
                            subJobInfo.setContextName(defaultContext);
                        }
                    }
                    subJobInfo.setFatherJobInfo(jobInfo);

                    if (!jobList.contains(subJobInfo)) {
                        processItems.add(processItem);
                        // children won't have stats / traces
                        if (option == GENERATE_WITH_FIRST_CHILD) {
                            toReturn = generateCode(subJobInfo, selectedContextName, false, false, true, GENERATE_MAIN_ONLY);
                        } else {
                            toReturn = generateCode(subJobInfo, selectedContextName, false, false, true, GENERATE_ALL_CHILDS);
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

            // always generate all context files.
            List<IContext> list = currentProcess.getContextManager().getListContext();
            for (IContext context : list) {
                if (context.getName().equals(currentContext.getName())) {
                    processor.setContext(currentContext); // generate current context.
                } else {
                    processor.setContext(context);
                }
                try {
                    processor.generateContextCode();
                } catch (ProcessorException pe) {
                    ExceptionHandler.process(pe);
                }

            }

            processor.setContext(currentContext);
            // main job will use stats / traces
            processor.generateCode(statistics, trace, properties);
            if (jobInfo.getProcessItem() != null) {
                designerCoreService.getJobModificationDateMap(getTopJobInfo(jobInfo).getProcess()).put(jobInfo.getJobId(),
                        jobInfo.getProcessItem().getProperty().getModificationDate());
            }

            if (currentProcess instanceof IProcess2) {
                ((IProcess2) currentProcess).setNeedRegenerateCode(false);
            }
        }
        if (jobInfo.getFatherJobInfo() == null) {
            if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA) {
                try {
                    CorePlugin.getDefault().getRunProcessService().getJavaProject().getProject().build(
                            IncrementalProjectBuilder.AUTO_BUILD, null);
                } catch (CoreException e) {
                    throw new ProcessorException(e);
                }
            }
        }

        return toReturn;
    }

    /**
     * ftang Comment method "getTopJobInfo".
     * 
     * @param jobInfo
     * @return
     */
    private static JobInfo getTopJobInfo(JobInfo jobInfo) {
        if (jobInfo.getFatherJobInfo() != null) {
            return getTopJobInfo(jobInfo.getFatherJobInfo());
        }
        return jobInfo;
    }

    /**
     * Return true if we can find a context name from the child job that matches the selected context name. see bug
     * 0003862: Export job with the flag "Apply to children" if the child don't have the same context fails.
     * 
     * @param processItem
     * @param selectedContextName
     * @return
     */
    public static boolean checkIfContextExisted(ProcessItem processItem, String selectedContextName) {
        for (Object o : processItem.getProcess().getContext()) {
            if (o instanceof ContextType) {
                ContextType context = (ContextType) o;
                if (context.getName().equals(selectedContextName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method is used to reset the tRunJob component's context,see feature 1625.
     * 
     * @param jobInfo
     * @param currentProcess
     * @param selectedContextName
     */
    private static void resetRunJobComponentParameterForContextApply(JobInfo jobInfo, IProcess currentProcess,
            String selectedContextName) {

        if (jobInfo.isApplyContextToChildren()) {
            for (Iterator<? extends INode> iter = currentProcess.getGraphicalNodes().iterator(); iter.hasNext();) {
                INode node = iter.next();
                if ((node != null) && node.getComponent().getName().equals("tRunJob")) {
                    Element element = (Element) node;
                    // the corresponding parameter is
                    // EParameterName.PROCESS_TYPE_CONTEXT
                    element.setPropertyValue("PROCESS_TYPE_CONTEXT", selectedContextName);
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
     * @throws ProcessorException
     */
    public static boolean generateCode(String processName, String contextName, String version, boolean statistics, boolean trace)
            throws ProcessorException {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processName, contextName, version);
        boolean genCode = generateCode(jobInfo, contextName, statistics, trace, true, GENERATE_ALL_CHILDS);
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
     * @throws ProcessorException
     */
    public static boolean generateCode(String processId, String contextName, String version, boolean statistics, boolean trace,
            boolean applyContextToChildren) throws ProcessorException {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processId, contextName, version);
        jobInfo.setApplyContextToChildren(applyContextToChildren);
        generateAllContexts = true;
        boolean result = generateCode(jobInfo, contextName, statistics, trace, true, GENERATE_ALL_CHILDS);
        generateAllContexts = false;
        jobList.clear();
        return result;
    }

    public static boolean generateCode(ProcessItem process, String contextName, boolean statistics, boolean trace,
            boolean applyContextToChildren) throws ProcessorException {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process, contextName);
        jobInfo.setApplyContextToChildren(applyContextToChildren);
        generateAllContexts = true;
        boolean result = generateCode(jobInfo, contextName, statistics, trace, true, GENERATE_ALL_CHILDS);
        generateAllContexts = false;
        jobList.clear();
        return result;
    }

    public static boolean generateCode(ProcessItem process, String contextName, String version, boolean statistics,
            boolean trace, boolean applyContextToChildren) throws ProcessorException {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process, contextName, version);
        jobInfo.setApplyContextToChildren(applyContextToChildren);
        generateAllContexts = true;
        boolean result = generateCode(jobInfo, contextName, statistics, trace, true, GENERATE_ALL_CHILDS);
        generateAllContexts = false;
        jobList.clear();
        return result;
    }

    public static boolean generateCode(ProcessItem process, String contextName, boolean statistics, boolean trace)
            throws ProcessorException {
        return generateCode(process, contextName, statistics, trace, false);
    }

    public static boolean generateCode(String processId, String contextName, String version, boolean statistics, boolean trace,
            int option) throws ProcessorException {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(processId, contextName, version);
        boolean genCode = generateCode(jobInfo, contextName, statistics, trace, true, option);
        jobList.clear();
        return genCode;
    }

    public static List<ProcessItem> getAllProcessItems() {
        return new ArrayList<ProcessItem>(processItems);
    }

    public static boolean generateCode(IProcess process, IContext context, boolean statistics, boolean trace, boolean properties)
            throws ProcessorException {
        processItems.clear();
        processItems.add((ProcessItem) process.getProperty().getItem());
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process.getId(), context.getName(), process.getVersion());
        jobInfo.setProcess(process);
        jobInfo.setContext(context);
        boolean genCode = false;
        genCode = generateCode(jobInfo, context.getName(), statistics, trace, properties, GENERATE_ALL_CHILDS);
        jobList.clear();
        return genCode;
    }

    public static boolean generateCode(IProcess process, IContext context, boolean statistics, boolean trace, boolean properties,
            int option) throws ProcessorException {
        jobList.clear();
        JobInfo jobInfo = new JobInfo(process, context);
        boolean genCode = generateCode(jobInfo, context.getName(), statistics, trace, properties, option);
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
        ProcessItem selectedProcessItem = ItemCacheManager.getProcessItem(processId);
        return getCommandLine(targetPlatform, externalUse, selectedProcessItem, contextName, statisticPort, tracePort,
                codeOptions);
    }

    public static String[] getCommandLine(String targetPlatform, boolean externalUse, String processId, String contextName,
            String version, int statisticPort, int tracePort, String... codeOptions) throws ProcessorException {
        ProcessItem selectedProcessItem = ItemCacheManager.getProcessItem(processId, version);
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
        selectedProcessItem = ItemCacheManager.getProcessItem(processName, processVersion);
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

    // see bug 0004939: making tRunjobs work loop will cause a error of "out of memory" .
    private static Set<JobInfo> getAllJobInfo(ProcessItem processItem, Set<JobInfo> jobInfos) {

        IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();
        IProcess process = null;

        // check in opened jobs to avoid to reload the job.
        for (IEditorPart part : openedEditors) {
            if (part instanceof ITalendJobEditor) {
                IProcess currentProcess = ((ITalendJobEditor) part).getProcess();
                if (currentProcess.getLabel().equals(processItem.getProperty().getLabel())
                        && currentProcess.getVersion().equals(processItem.getProperty().getVersion())) {
                    process = currentProcess;
                }
            }
        }

        if (process == null) {
            process = service.getProcessFromProcessItem(processItem);
        }

        List<? extends INode> graphicalNodes = process.getGeneratingNodes();

        for (INode node : graphicalNodes) {
            if ((node != null) && node.getComponent().getName().equals("tRunJob")) {

                IElementParameter processIdparam = node.getElementParameter("PROCESS_TYPE_PROCESS");
                String jobId = (String) processIdparam.getValue();
                String jobContext = (String) node.getElementParameter("PROCESS_TYPE_CONTEXT").getValue();
                String jobVersion = (String) node.getElementParameter("PROCESS_TYPE_VERSION").getValue();

                ProcessItem item = ItemCacheManager.getProcessItem(jobId, jobVersion);
                if (item != null) {
                    JobInfo jobInfo = new JobInfo(item, jobContext);
                    if (!jobInfos.contains(jobInfo)) {
                        jobInfos.add(jobInfo);
                        getAllJobInfo(item, jobInfos);
                    }
                }
            }
        }
        return jobInfos;
    }

    public static Set<JobInfo> getChildrenJobInfo(ProcessItem processItem) {
        // delegate to the new method, prevent dead loop method call. see bug 0004939: making tRunjobs work loop will
        // cause a error of "out of memory" .
        return getAllJobInfo(processItem, new HashSet<JobInfo>());

    }

}
