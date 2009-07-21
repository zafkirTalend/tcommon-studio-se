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
package org.talend.repository.documentation.generation;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.image.ImageUtils;
import org.talend.commons.utils.image.ImageUtils.ICON_SIZE;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.PasswordEncryptUtil;
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.general.Project;
import org.talend.core.model.genhtml.FileCopyUtils;
import org.talend.core.model.genhtml.HTMLDocUtils;
import org.talend.core.model.genhtml.HTMLHandler;
import org.talend.core.model.genhtml.IHTMLDocConstants;
import org.talend.core.model.genhtml.IJobSettingConstants;
import org.talend.core.model.genhtml.XMLHandler;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.core.ui.images.CoreImageProvider;
import org.talend.designer.codegen.ITalendSynchronizer;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ConnectionType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ParametersType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.runprocess.IProcessor;
import org.talend.designer.runprocess.ProcessorException;
import org.talend.designer.runprocess.ProcessorUtilities;
import org.talend.repository.ProjectManager;
import org.talend.repository.documentation.ExportFileResource;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;

/**
 * This class is used for generating HTML file.
 * 
 * $Id: XMLGenerator.java 2007-3-8,下午01:09:34 ftang $
 * 
 */
public class HTMLDocGenerator implements IDocumentationGenerator {

    private Map<String, List> targetConnectionMap = null;

    private Map<String, List> sourceConnectionMap = null;

    private Map<String, String> picFilePathMap;

    private List<Map> mapList;

    private Map<String, ConnectionItem> repositoryConnectionItemMap;

    private static Map<String, String> repositoryDBIdAndNameMap;

    private IDesignerCoreService designerCoreService;

    private Map<String, Object> externalNodeHTMLMap = new HashMap();

    private ERepositoryObjectType repositoryObjectType;

    private static Map<Integer, ByteArrayOutputStream> logoImageCache = new HashMap<Integer, ByteArrayOutputStream>();

    private Project project;

    public HTMLDocGenerator(Project project, ERepositoryObjectType repositoryObjectType) {
        this.project = project;
        this.designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        this.mapList = designerCoreService.getMaps();
        this.repositoryConnectionItemMap = mapList.get(0);
        this.repositoryDBIdAndNameMap = mapList.get(1);
        this.repositoryObjectType = repositoryObjectType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.documentation.generation.IDocumentationGenerator#getRepositoryObjectType()
     */
    public ERepositoryObjectType getRepositoryObjectType() {
        return this.repositoryObjectType;
    }

    /*
     * This method is used for generating HTML file base on an instance of <code>ExportFileResource</code> (non-Javadoc)
     * 
     * @see
     * org.talend.repository.documentation.generation.IDocumentationGenerator#generateHTMLFile(org.talend.repository
     * .documentation.ExportFileResource)
     */
    public void generateHTMLFile(ExportFileResource resource) {
        try {

            // Store all pictures' path.
            List<URL> picList = new ArrayList<URL>(5);

            String jobName = resource.getItem().getProperty().getLabel();

            String jobVersion = resource.getItem().getProperty().getVersion();

            String tempFolderPath = checkTempDirIsExists(resource);

            handleXMLFile(resource, tempFolderPath);

            String picFolderPath = checkPicDirIsExists(resource, tempFolderPath);

            final Bundle b = Platform.getBundle(IHTMLDocConstants.REPOSITORY_PLUG_IN_ID);

            final URL xslFileUrl = FileLocator.toFileURL(FileLocator
                    .find(b, new Path(IHTMLDocConstants.MAIN_XSL_FILE_PATH), null));
            // final URL logoFileUrl = FileLocator.toFileURL(FileLocator.find(b,
            // new Path(IHTMLDocConstants.LOGO_FILE_PATH), null));

            File logoFile = new File(picFolderPath + File.separatorChar + IHTMLDocConstants.TALEND_LOGO_FILE_NAME);
            saveLogoImage(SWT.IMAGE_JPEG, logoFile);

            String xslFilePath = xslFileUrl.getPath();
            // String logoFilePath = logoFileUrl.getPath();
            // FileCopyUtils.copy(logoFilePath, picFolderPath + File.separatorChar
            // + IHTMLDocConstants.TALEND_LOGO_FILE_NAME);

            picList.add(logoFile.toURL());

            Set keySet = picFilePathMap.keySet();
            for (Object key : keySet) {
                String value = picFilePathMap.get(key);
                FileCopyUtils.copy(value, picFolderPath + File.separatorChar + key);
                picList.add(new File(picFolderPath + File.separatorChar + key).toURL());
            }

            byte[] innerContent = null;
            ProcessType processType = null;
            if (resource.getItem() instanceof ProcessItem) {
                processType = ((ProcessItem) resource.getItem()).getProcess();
                innerContent = processType.getScreenshot();
            } else if (resource.getItem() instanceof JobletProcessItem) {
                processType = ((JobletProcessItem) resource.getItem()).getJobletProcess();
                innerContent = processType.getScreenshot();
            }

            if (innerContent != null) {
                ImageDescriptor imagedesc = ImageUtils.createImageFromData(innerContent);
                String picName = jobName + "_" + jobVersion + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX; //$NON-NLS-1$
                ImageUtils.save(imagedesc.createImage(), picFolderPath + File.separatorChar + picName, SWT.IMAGE_PNG);
                picList.add(new File(picFolderPath + File.separatorChar + picName).toURL());
            }
            for (NodeType node : (List<NodeType>) processType.getNode()) {
                if (node.getScreenshot() != null && node.getScreenshot().length != 0) {
                    byte[] screenshot = node.getScreenshot();
                    ImageDescriptor imagedesc = ImageUtils.createImageFromData(screenshot);
                    String uniqueName = "";
                    for (Object o : node.getElementParameter()) {
                        if (o instanceof ElementParameterType) {
                            if ("UNIQUE_NAME".equals(((ElementParameterType) o).getName())) {
                                uniqueName = ((ElementParameterType) o).getValue();
                            }
                        }
                    }
                    String picName = uniqueName + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX;
                    ImageUtils.save(imagedesc.createImage(), picFolderPath + File.separatorChar + picName, SWT.IMAGE_PNG);
                    picList.add(new File(picFolderPath + File.separatorChar + picName).toURL());
                }
            }

            List<URL> resultFiles = parseXML2HTML(tempFolderPath, jobName + "_" + jobVersion, xslFilePath); //$NON-NLS-1$

            addResources(resource, resultFiles);

            resource.addResources(IHTMLDocConstants.PIC_FOLDER_NAME, picList);

            // List<URL> externalList = getExternalHtmlPath();
            // resource.addResources(IHTMLDocConstants.EXTERNAL_FOLDER_NAME, externalList);

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.process(e);

        }

        targetConnectionMap = null;
        sourceConnectionMap = null;
    }

    /*
     * This method is used for generating HTML file base on an instance of <code>ExportFileResource</code> (non-Javadoc)
     * 
     * @see
     * org.talend.repository.documentation.generation.IDocumentationGenerator#generateDocumentation(org.talend.repository
     * .documentation.ExportFileResource, java.lang.String, java.lang.String[])
     */
    public void generateDocumentation(ExportFileResource resource, String targetPath, String... jobVersion) throws Exception {

        // Store all pictures' path.
        List<URL> picList = new ArrayList<URL>(5);

        String jobName = resource.getItem().getProperty().getLabel();

        String jobPath = resource.getItem().getProperty().getItem().getState().getPath();

        // Used for generating/updating all jobs' documentaiton only.
        if (targetPath.endsWith(this.repositoryObjectType.toString().toLowerCase())) {
            targetPath = targetPath + IPath.SEPARATOR + jobPath + IPath.SEPARATOR + jobName;
        }
        String version = ""; //$NON-NLS-1$

        // Checks if the job's version is specified, see it on "Export documentation" Dialog:
        if (jobVersion != null && jobVersion.length == 1) {
            version = jobVersion[0];
        } else {
            version = resource.getItem().getProperty().getVersion();
        }
        targetPath = targetPath + "_" + version; //$NON-NLS-1$

        File file = new File(targetPath);

        // Delete if folde is existing.
        if (file.exists()) {
            FilesUtils.removeFolder(file, true);
        }

        file.mkdirs();

        handleXMLFile(resource, targetPath, jobVersion);

        String picFolderPath = checkPicDirIsExists(resource, targetPath);

        // Gets the "org.talend.repository" plug-in:
        final Bundle b = Platform.getBundle("org.talend.repository"); //$NON-NLS-1$

        final URL xslFileUrl = FileLocator.toFileURL(FileLocator.find(b, new Path(IHTMLDocConstants.MAIN_XSL_FILE_PATH), null));
        // final URL logoFileUrl = FileLocator.toFileURL(FileLocator.find(b,
        // new Path(IHTMLDocConstants.LOGO_FILE_PATH), null));

        File logoFile = new File(picFolderPath + File.separatorChar + IHTMLDocConstants.TALEND_LOGO_FILE_NAME);
        saveLogoImage(SWT.IMAGE_JPEG, logoFile);

        String xslFilePath = xslFileUrl.getPath();
        // String logoFilePath = logoFileUrl.getPath();
        // FileCopyUtils.copy(logoFilePath, picFolderPath + File.separatorChar
        // + IHTMLDocConstants.TALEND_LOGO_FILE_NAME);

        picList.add(logoFile.toURL());
        // Property property = item.getProperty();
        // String jobName = property.getLabel();
        // String jobVersion = property.getVersion();
        byte[] innerContent = null;
        ProcessType processType = null;
        if (resource.getItem() instanceof ProcessItem) {
            processType = ((ProcessItem) resource.getItem()).getProcess();
            innerContent = processType.getScreenshot();
        } else if (resource.getItem() instanceof JobletProcessItem) {
            processType = ((JobletProcessItem) resource.getItem()).getJobletProcess();
            innerContent = processType.getScreenshot();
        }

        if (innerContent != null) {
            ImageDescriptor imagedesc = ImageUtils.createImageFromData(innerContent);
            String picName = jobName + "_" + version + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX; //$NON-NLS-1$
            ImageUtils.save(imagedesc.createImage(), picFolderPath + File.separatorChar + picName, SWT.IMAGE_PNG);

        }
        for (NodeType node : (List<NodeType>) processType.getNode()) {
            if (node.getScreenshot() != null && node.getScreenshot().length != 0) {
                byte[] screenshot = node.getScreenshot();
                ImageDescriptor imagedesc = ImageUtils.createImageFromData(screenshot);
                String uniqueName = "";
                for (Object o : node.getElementParameter()) {
                    if (o instanceof ElementParameterType) {
                        if ("UNIQUE_NAME".equals(((ElementParameterType) o).getName())) {
                            uniqueName = ((ElementParameterType) o).getValue();
                        }
                    }
                }
                String picName = uniqueName + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX;
                ImageUtils.save(imagedesc.createImage(), picFolderPath + File.separatorChar + picName, SWT.IMAGE_PNG);

            }
        }

        Set keySet = picFilePathMap.keySet();
        for (Object key : keySet) {
            String value = picFilePathMap.get(key);

            FileCopyUtils.copy(value, picFolderPath + File.separatorChar + key);

            picList.add(new File(picFolderPath + File.separatorChar + key).toURL());
        }

        List<URL> resultFiles = parseXML2HTML(targetPath, jobName + "_" + version, xslFilePath); //$NON-NLS-1$

        resource.addResources(resultFiles);

        resource.addResources(IHTMLDocConstants.PIC_FOLDER_NAME, picList);

        // List<URL> externalList = getExternalHtmlPath();
        // resource.addResources(IHTMLDocConstants.EXTERNAL_FOLDER_NAME, externalList);

        targetConnectionMap = null;
        sourceConnectionMap = null;
    }

    /**
     * Checks if pictures directory is existing.
     * 
     * @param resource
     */
    private static String checkPicDirIsExists(ExportFileResource resource, String tempFolderPath) {
        String picFolderPath = tempFolderPath + File.separator + IHTMLDocConstants.PIC_FOLDER_NAME;
        File file = new File(picFolderPath);
        if (!file.exists()) {
            file.mkdir();
        }
        return picFolderPath;

    }

    /**
     * Checks if temporary directory is existing.
     * 
     * @param resource
     * @return
     */
    private String checkTempDirIsExists(ExportFileResource resource) {
        String tempDirPath = HTMLDocUtils.getTmpFolder() + File.separator + resource.getDirectoryName() + "_" //$NON-NLS-1$
                + resource.getItem().getProperty().getVersion();
        File file = new File(tempDirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return tempDirPath;
    }

    /**
     * Using xslt to parse the xml to html.
     * 
     * @param jobName
     * @param tempFolderPath
     * @param xslFileName
     * 
     * @return top folder path of this job.
     * @throws Exception
     */
    private List<URL> parseXML2HTML(String tempFolderPath, String jobName, String xslFilePath) throws Exception {
        List<URL> list = new ArrayList<URL>(1);
        // clear the cache, maybe need improve it latter.
        HTMLHandler.clearExternalNodeFileCache();
        String htmlFilePath = tempFolderPath + File.separatorChar + jobName + IHTMLDocConstants.HTML_FILE_SUFFIX;
        String xmlFilePath = tempFolderPath + File.separatorChar + jobName + IHTMLDocConstants.XML_FILE_SUFFIX;
        HTMLHandler.generateHTMLFile(tempFolderPath, xslFilePath, xmlFilePath, htmlFilePath, this.externalNodeHTMLMap);

        File tmpFolder = new File(tempFolderPath);

        File[] files = tmpFolder.listFiles();
        for (int i = 0; i < files.length; i++) {
            // Checks if current file is html file or xml file, otherwise ignore it.
            if (!(files[i].isDirectory())) {
                list.add(files[i].toURL());
            }
        }

        return list;
    }

    /**
     * Generates the xml file base on an instance of <code>ExportFileResource</code> and the temporary folder path.
     * 
     * @param resource
     * @param tempFolderPath
     * @param version
     */
    private void handleXMLFile(ExportFileResource resource, String tempFolderPath, String... version) throws Exception {
        Item item = resource.getItem();
        targetConnectionMap = new HashMap<String, List>();
        sourceConnectionMap = new HashMap<String, List>();
        getSourceAndTargetConnection(item);

        Document document = DocumentHelper.createDocument();
        Element projectElement = generateProjectInfo(document);

        Element jobElement = generateJobInfo(item, projectElement, version);

        // This two element see feature 4162
        generateContextInfo(item, jobElement);

        generateJobSettingInfo(item, jobElement);

        List<List> allList = seperateNodes(item);
        if (allList == null || allList.size() != 3) {
            return;
        }
        List<INode> allComponentsList = allList.get(0);
        List<INode> internalNodeComponentsList = allList.get(1);
        List<INode> externalNodeComponentsList = allList.get(2);

        if (allComponentsList.size() > 0) { // Generates information for 'Component List' part in exported HTML file.
            generateAllComponentsSummaryInfo(item, jobElement, allComponentsList);
        }

        Element internalNodeElement = jobElement.addElement("internalNodeComponents"); //$NON-NLS-1$
        Element externalNodeElement = jobElement.addElement("externalNodeComponents"); //$NON-NLS-1$

        if (internalNodeComponentsList.size() > 0) {
            InternalNodeComponentHandler internalNodeComponentHandler = new InternalNodeComponentHandler(this.picFilePathMap,
                    internalNodeElement, internalNodeComponentsList, this.sourceConnectionMap, this.targetConnectionMap,
                    this.designerCoreService, this.repositoryConnectionItemMap, this.repositoryDBIdAndNameMap,
                    externalNodeHTMLMap);

            // Generates internal node components information.
            internalNodeComponentHandler.generateComponentInfo();
        }

        if (externalNodeComponentsList.size() > 0) {
            ExternalNodeComponentHandler externalNodeComponentHandler = new ExternalNodeComponentHandler(this.picFilePathMap,
                    externalNodeElement, externalNodeComponentsList, this.sourceConnectionMap, this.targetConnectionMap,
                    this.designerCoreService, this.repositoryConnectionItemMap, this.repositoryDBIdAndNameMap,
                    externalNodeHTMLMap/*
                                        * ,
                                        */);
            // Generates external node components(tMap etc.) information.

            externalNodeComponentHandler.generateComponentInfo();
        }

        // Generates all connection information(include internal node and external node).
        EList connectionList = null;
        if (item instanceof ProcessItem) {
            connectionList = ((ProcessItem) item).getProcess().getConnection();
        } else if (item instanceof JobletProcessItem) {
            connectionList = (((JobletProcessItem) item).getJobletProcess().getConnection());
        }

        if (connectionList != null && connectionList.size() != 0) {
            generateConnectionsInfo(jobElement, connectionList);
        }

        String versionPath = "_"; //$NON-NLS-1$
        if (version != null && version.length == 1) {

            versionPath = versionPath + version[0];
        } else {
            versionPath = versionPath + item.getProperty().getVersion();
        }

        String filePath = tempFolderPath + File.separatorChar + item.getProperty().getLabel() + versionPath
                + IHTMLDocConstants.XML_FILE_SUFFIX;

        // This element see feature 4382
        if (item instanceof ProcessItem) {
            generateSourceCodeInfo((ProcessItem) item, jobElement);
        }

        XMLHandler.generateXMLFile(tempFolderPath, filePath, document);
    }

    /**
     * DOC qwei Comment method "generateSourceCodeInfo".
     */
    private void generateSourceCodeInfo(ProcessItem item, Element element) {
        if (CorePlugin.getDefault().getPreferenceStore().getBoolean(ITalendCorePrefConstants.DOC_GENERATESOURCECODE)) {
            IDesignerCoreService service = CorePlugin.getDefault().getDesignerCoreService();
            IProcess process = service.getProcessFromProcessItem(item);
            IProcessor processor = ProcessorUtilities.getProcessor(process, process.getContextManager().getDefaultContext());
            if (ProjectManager.getInstance().getCurrentProject().getEmfProject().isHidePassword()) {
                hideSourcecodePassword(process);
            }
            try {
                processor.generateCode(false, true, false);
            } catch (ProcessorException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                ExceptionHandler.process(e);
            }
            Element sourceCodeInfoElement = DocumentHelper.createElement("sourcecodes"); //$NON-NLS-1$
            ITalendSynchronizer synchronizer = CorePlugin.getDefault().getCodeGeneratorService().createRoutineSynchronizer();
            // StringBuffer componentsCode = new StringBuffer();
            try {
                IFile codeFile = synchronizer.getFile(item);
                String tempStr = ""; //$NON-NLS-1$
                InputStream in = codeFile.getContents();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                while ((tempStr = buffer.readLine()) != null) {
                    Element codeElement = DocumentHelper.createElement("code"); //$NON-NLS-1$
                    // componentsCode.append(tempStr).append("\n");
                    codeElement.addAttribute("content", tempStr); //$NON-NLS-1$
                    sourceCodeInfoElement.add(codeElement);
                }
                buffer.close();
                in.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                ExceptionHandler.process(e);
            }

            element.add(sourceCodeInfoElement);
        }

    }

    /**
     * DOC qwei Comment method "hideSourcecodePassword".
     */
    private void hideSourcecodePassword(IProcess process) {
        List<? extends IElementParameter> processParam = process.getElementParameters();
        for (IElementParameter elementParameter : processParam) {
            if (elementParameter.getRepositoryValue() != null && elementParameter.getRepositoryValue().equals("PASSWORD") //$NON-NLS-1$
                    && !ContextParameterUtils.containContextVariables((String) elementParameter.getValue())) {
                elementParameter.setValue("******"); //$NON-NLS-1$

            }

        }
        List<? extends INode> nodes = process.getGraphicalNodes();
        for (INode node : nodes) {
            List<? extends IElementParameter> nodeParamList = node.getElementParameters();
            for (IElementParameter nodeParam : nodeParamList) {
                if (nodeParam.getRepositoryValue() != null && nodeParam.getRepositoryValue().equals("PASSWORD") //$NON-NLS-1$
                        && !ContextParameterUtils.containContextVariables((String) nodeParam.getValue())) {
                    nodeParam.setValue("******"); //$NON-NLS-1$

                }

            }

        }

    }

    /**
     * DOC YeXiaowei Comment method "genereateJobSettingInfo".
     * 
     * @return
     */
    private void generateJobSettingInfo(final Item item, final Element element) {
        Element jobSettingInfoElement = DocumentHelper.createElement("jobSetting"); //$NON-NLS-1$

        ParametersType jobDirectParams = null;

        if (item instanceof ProcessItem) {
            jobDirectParams = ((ProcessItem) item).getProcess().getParameters();
        } else if (item instanceof JobletProcessItem) {
            jobDirectParams = ((JobletProcessItem) item).getJobletProcess().getParameters();
        }

        if (jobDirectParams == null || jobDirectParams.getElementParameter() == null
                || jobDirectParams.getElementParameter().isEmpty()) {
            return;
        }

        EList params = jobDirectParams.getElementParameter();

        Map<String, String> nameValueMap = new HashMap<String, String>();

        for (int i = 0; i < params.size(); i++) {
            ElementParameterType param = (ElementParameterType) params.get(i);
            nameValueMap.put(param.getName(), HTMLDocUtils.checkString(param.getValue()));
        }
        // Main settinparam info

        // Extra setting
        Element extraElement = DocumentHelper.createElement("extra"); //$NON-NLS-1$
        jobSettingInfoElement.add(extraElement);

        if (item instanceof JobletProcessItem) {
            createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.STARTABLE));
            element.add(jobSettingInfoElement);
            return;
        }

        createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.COMP_DEFAULT_FILE_DIR));

        createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.MULTI_THREAD_EXECATION));

        createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.IMPLICIT_TCONTEXTLOAD));
        if (StringUtils.equals(nameValueMap.get(IJobSettingConstants.IMPLICIT_TCONTEXTLOAD), "true")) { //$NON-NLS-1$

            if (StringUtils.equals(nameValueMap.get(IJobSettingConstants.FROM_FILE_FLAG_IMPLICIT_CONTEXT), "true")) { //$NON-NLS-1$
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap,
                        IJobSettingConstants.FROM_FILE_FLAG_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap,
                        IJobSettingConstants.IMPLICIT_TCONTEXTLOAD_FILE));
            }

            if (StringUtils.equals(nameValueMap.get(IJobSettingConstants.FROM_DATABASE_FLAG_IMPLICIT_CONTEXT), "true")) { //$NON-NLS-1$
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap,
                        IJobSettingConstants.FROM_DATABASE_FLAG_IMPLICIT_CONTEXT));

                // some params about databse setting
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap,
                        IJobSettingConstants.PROPERTY_TYPE_IMPLICIT_CONTEXT_PROPERTY_TYPE));
                if (!StringUtils.equalsIgnoreCase(nameValueMap
                        .get(IJobSettingConstants.PROPERTY_TYPE_IMPLICIT_CONTEXT_PROPERTY_TYPE), "built_in")) { //$NON-NLS-1$
                    createSingleJobParameter(extraElement, getConnectionLabelById(makeNameValue(nameValueMap,
                            IJobSettingConstants.PROPERTY_TYPE_IMPLICIT_CONTEXT_REPOSITORY_PROPERTY_TYPE), null));
                }

                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.DB_TYPE_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.HOST_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.PORT_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.DBNAME_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap,
                        IJobSettingConstants.SCHEMA_DB_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.USER_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.PASS_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.DBFILE_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.DBTABLE_IMPLICIT_CONTEXT));
                createSingleJobParameter(extraElement, makeNameValue(nameValueMap,
                        IJobSettingConstants.QUERY_CONDITION_IMPLICIT_CONTEXT));

            }
            // print operation
            createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.PRINT_OPERATIONS));

            // diable warnings
            createSingleJobParameter(extraElement, makeNameValue(nameValueMap, IJobSettingConstants.DISABLE_WARNINGS));

        }

        // Stats & logs setting
        Element statsAndLotsElement = DocumentHelper.createElement("Statslogs"); //$NON-NLS-1$
        jobSettingInfoElement.add(statsAndLotsElement);

        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.ON_STATCATCHER_FLAG));
        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.ON_LOGCATCHER_FLAG));
        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.ON_METERCATCHER_FLAG));

        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.ON_CONSOLE_FLAG));

        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.ON_FILES_FLAG));
        if (StringUtils.equals(nameValueMap.get(IJobSettingConstants.ON_FILES_FLAG), "true")) { //$NON-NLS-1$
            // add on file details
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.FILE_PATH));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.FILENAME_LOGS));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.FILENAME_METTER));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.FILENAME_STATS));
        }

        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.ON_DATABASE_FLAG));
        if (StringUtils.equals(nameValueMap.get(IJobSettingConstants.ON_DATABASE_FLAG), "true")) { //$NON-NLS-1$
            // add on database details
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.PROPERTY_TYPE));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap,
                    IJobSettingConstants.PROPERTY_TYPE_PROPERTY_TYPE));
            if (!StringUtils.equalsIgnoreCase(nameValueMap.get(IJobSettingConstants.PROPERTY_TYPE_PROPERTY_TYPE), "built_in")) { //$NON-NLS-1$
                createSingleJobParameter(statsAndLotsElement, getConnectionLabelById(makeNameValue(nameValueMap,
                        IJobSettingConstants.PROPERTY_TYPE_REPOSITORY_PROPERTY_TYPE), null));
            }

            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.DB_TYPE));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.HOST));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.PORT));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.DBNAME));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.PROPERTIES));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.SCHEMA_DB));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.USER));

            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.PASS));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.DBFILE));

            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.TABLE_LOGS));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.TABLE_METER));
            createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.TABLE_STATS));
        }

        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.CATCH_REALTIME_STATS));
        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.CATCH_RUNTIME_ERRORS));
        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.CATCH_USER_ERRORS));
        createSingleJobParameter(statsAndLotsElement, makeNameValue(nameValueMap, IJobSettingConstants.CATCH_USER_WARNING));

        // verson setting see job info

        element.add(jobSettingInfoElement);
    }

    /**
     * 
     * DOC YeXiaowei Comment method "createSingleJobParameter".
     * 
     * @param root
     * @param nameValue
     * @return
     */
    private Element createSingleJobParameter(final Element root, final String... nameValue) {
        Element param = DocumentHelper.createElement("jobParameter"); //$NON-NLS-1$

        // Use label replace name
        String displayName = CorePlugin.getDefault().getDesignerCoreService().getDisplayForProcessParameterFromName(nameValue[0]);
        param.addAttribute("name", displayName); //$NON-NLS-1$
        param.addAttribute("value", nameValue[1]); //$NON-NLS-1$
        root.add(param);
        return param;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "getConnectionLabelById".
     * 
     * @param nameValue
     * @param replaceName
     * @return
     */
    private String[] getConnectionLabelById(String[] nameValue, String replaceName) {

        String[] res = new String[2];

        // Repalce attribute name
        res[0] = replaceName == null ? nameValue[0] : replaceName;

        // replace attribute value from id to label
        ConnectionItem connItem = getConnectionItemById(nameValue[1]);
        if (connItem != null) {
            res[1] = connItem.getProperty().getLabel();
        } else {
            res[1] = nameValue[1];
        }

        return res;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "makeNameValue".
     * 
     * @param map
     * @param key
     * @return
     */
    private String[] makeNameValue(Map<String, String> map, final String key) {
        if (key.equals(IJobSettingConstants.PASS)) {
            return new String[] { key, "******" }; //$NON-NLS-1$
        } else {
            return new String[] { key, map.get(key) };
        }
    }

    /**
     * DOC YeXiaowei Comment method "generateContextInfo".
     * 
     * @return
     */
    private void generateContextInfo(final Item item, final Element element) {

        EList contexts = null;

        if (item instanceof ProcessItem) {
            contexts = ((ProcessItem) item).getProcess().getContext();
        } else if (item instanceof JobletProcessItem) {
            contexts = ((JobletProcessItem) item).getJobletProcess().getContext();
        }

        if (contexts == null || contexts.isEmpty()) {
            return;
        }

        Element contextListElement = DocumentHelper.createElement("contextList"); // Context root //$NON-NLS-1$

        for (int i = 0, n = contexts.size(); i < n; i++) {
            // export single context infomation
            Element contextElement = DocumentHelper.createElement("context"); //$NON-NLS-1$
            ContextType context = (ContextType) contexts.get(i);

            // Attributes
            contextElement.addAttribute("name", HTMLDocUtils.checkString(context.getName())); //$NON-NLS-1$

            IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getProxyRepositoryFactory();
            // Context parameters as children
            EList params = context.getContextParameter();
            if (params != null && !params.isEmpty()) {
                for (int j = 0, k = params.size(); j < k; j++) {
                    /*
                     * <contextParameter comment="Give server name" name="server" prompt="Default Server "
                     * promptNeeded="false" repositoryContextId="_crJMkCCQEd2Oweh7yRMWjQ" type=""
                     * value="'192.168.0.109'"/>
                     */
                    ContextParameterType param = (ContextParameterType) params.get(j);
                    Element contextParamElement = DocumentHelper.createElement("contextParameter"); //$NON-NLS-1$
                    contextParamElement.addAttribute("name", HTMLDocUtils.checkString(param.getName())); //$NON-NLS-1$
                    contextParamElement.addAttribute("prompt", HTMLDocUtils.checkString(param.getPrompt())); //$NON-NLS-1$
                    contextParamElement.addAttribute("promptNeeded", HTMLDocUtils.checkString(Boolean.toString(param //$NON-NLS-1$
                            .isPromptNeeded())));
                    contextParamElement.addAttribute("type", HTMLDocUtils.checkString(param.getType())); //$NON-NLS-1$
                    // wzhang modified to fix bug 8058
                    if (ProjectManager.getInstance().getCurrentProject().getEmfProject().isHidePassword()
                            && PasswordEncryptUtil.isPasswordType(param.getType())) {
                        contextParamElement.addAttribute("value", "******"); //$NON-NLS-1$ //$NON-NLS-2$
                    } else {
                        contextParamElement.addAttribute("value", HTMLDocUtils.checkString(param.getValue())); //$NON-NLS-1$
                    }
                    // replace repository id with context label
                    if (param.getRepositoryContextId() != null) {
                        ContextItem contextItem = ContextUtils.getContextItemById(param.getRepositoryContextId());
                        if (contextItem != null) { // bug 5978: repository link to context item might be lost.
                            String label = contextItem.getProperty().getLabel();
                            contextParamElement.addAttribute("source", HTMLDocUtils.checkString(label)); //$NON-NLS-1$
                        }
                    }
                    contextElement.add(contextParamElement);
                }
            }

            contextListElement.add(contextElement);
        }

        element.add(contextListElement);
    }

    /**
     * Generates all components summary information.
     * 
     * @param item
     * 
     * @param inputJobElement
     * @param allComponentsList
     */
    public void generateAllComponentsSummaryInfo(Item item, Element inputJobElement, List<INode> allComponentsList) {
        Element componentNameListElement = null;
        Point screenshotOffset = new Point();

        ProcessItem processItem = (ProcessItem) item;
        if (processItem.getProcess().getParameters() != null) {
            List<ElementParameterType> elemParamList = processItem.getProcess().getParameters().getElementParameter();
            getScreenShotOffset(screenshotOffset, elemParamList);
        }

        getComponentListInfo(inputJobElement, allComponentsList, componentNameListElement, screenshotOffset);
    }

    /**
     * DOC tang Comment method "getComponentListInfo".
     * 
     * @param inputJobElement
     * @param allComponentsList
     * @param componentNameListElement
     * @param screenshotOffset
     */
    protected void getComponentListInfo(Element inputJobElement, List<INode> allComponentsList, Element componentNameListElement,
            Point screenshotOffset) {
        int x = 0, y = 0, width = 0, height = 0;
        for (INode node : allComponentsList) {
            if (node.getLocation() != null) {
                Point point = node.getLocation();
                x = point.x + screenshotOffset.x;
                y = point.y + screenshotOffset.y;
            }
            Image icon = CoreImageProvider.getComponentIcon(node.getComponent(), ICON_SIZE.ICON_32);
            if (icon != null) {
                ImageData imageData = icon.getImageData();
                if (imageData != null) {
                    width = imageData.width;
                    height = imageData.height;
                }
            }

            if (componentNameListElement == null) {
                componentNameListElement = inputJobElement.addElement("componentList"); //$NON-NLS-1$
            }
            Element componentItemElement = null;
            componentItemElement = componentNameListElement.addElement("componentItem"); //$NON-NLS-1$
            componentItemElement.addAttribute("name", node.getUniqueName()); //$NON-NLS-1$
            componentItemElement.addAttribute("link", node.getUniqueName()); //$NON-NLS-1$
            componentItemElement.addAttribute("type", node.getComponent().getName()); //$NON-NLS-1$
            componentItemElement.addAttribute("leftTopX", x + ""); //$NON-NLS-1$ //$NON-NLS-2$
            componentItemElement.addAttribute("leftTopY", y + ""); //$NON-NLS-1$ //$NON-NLS-2$
            componentItemElement.addAttribute("rightBottomX", x + width + ""); //$NON-NLS-1$ //$NON-NLS-2$
            componentItemElement.addAttribute("rightBottomY", y + height + ""); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * DOC tang Comment method "getScreenShotOffset".
     * 
     * @param screenshotOffset
     * @param processItem
     */
    protected void getScreenShotOffset(Point screenshotOffset, List<ElementParameterType> elemParamList) {
        for (ElementParameterType curElem : elemParamList) {
            if (curElem.getName().equals(IProcess.SCREEN_OFFSET_X)) {
                screenshotOffset.x = Integer.valueOf("".equals(HTMLDocUtils.checkString(curElem.getValue())) ? "0" : curElem //$NON-NLS-1$ //$NON-NLS-2$
                        .getValue());
            } else if (curElem.getName().equals(IProcess.SCREEN_OFFSET_Y)) {
                screenshotOffset.y = Integer.valueOf("".equals(HTMLDocUtils.checkString(curElem.getValue())) ? "0" : curElem //$NON-NLS-1$ //$NON-NLS-2$
                        .getValue());
            }
        }
    }

    /**
     * This method is used for seperating all nodes into internal and external.
     * 
     * @param item
     * @return
     */
    protected List<List> seperateNodes(Item item) {
        List<INode> internalNodeComponentList = new ArrayList<INode>();
        List<INode> externalNodeComponentList = new ArrayList<INode>();
        List<INode> allNodeComponentList = new ArrayList<INode>();
        List<List> componentsList = new ArrayList<List>();

        IProcess process = null;
        boolean isJobletProcessItem = item instanceof JobletProcessItem;
        boolean isProcessItem = item instanceof ProcessItem;
        if (isProcessItem) {
            process = CorePlugin.getDefault().getDesignerCoreService().getProcessFromProcessItem((ProcessItem) item);
        }
        // if (isJobletProcessItem) {
        // AbstractProcessProvider processProvider =
        // AbstractProcessProvider.findProcessProviderFromPID(IComponent.JOBLET_PID);
        // if (processProvider != null) {
        // process = processProvider.buildNewGraphicProcess(item);
        // }
        // }
        if (process == null) {
            return componentsList;
        }

        List<INode> graphicalNodeList = (List<INode>) process.getGraphicalNodes();

        for (INode node : graphicalNodeList) {
            // If component is not activate, do not need to get it's information
            if (!node.isActivate()) {
                continue;
            }

            allNodeComponentList.add(node);

            if (node.getExternalNode() != null) {
                externalNodeComponentList.add(node);
            } else {
                internalNodeComponentList.add(node);
            }
        }

        // Sorts the component list in alpha-order.
        Comparator comparator = getComparator();
        Collections.sort(allNodeComponentList, comparator);
        Collections.sort(internalNodeComponentList, comparator);
        Collections.sort(externalNodeComponentList, comparator);

        componentsList.add(allNodeComponentList);
        componentsList.add(internalNodeComponentList);
        componentsList.add(externalNodeComponentList);

        return componentsList;
    }

    /**
     * Generates connections information base on <code>jobElement</code>,<code>connectionList</code>
     * 
     * @param jobElement
     * @param connectionList
     */
    private void generateConnectionsInfo(Element jobElement, EList connectionList) {
        Element connectionsElement = jobElement.addElement("connections"); //$NON-NLS-1$
        for (int j = 0; j < connectionList.size(); j++) {
            ConnectionType type = (ConnectionType) connectionList.get(j);
            Element connectionElement = connectionsElement.addElement("connection"); //$NON-NLS-1$
            connectionElement.addAttribute("label", HTMLDocUtils.checkString(type.getLabel())); //$NON-NLS-1$
            connectionElement.addAttribute("lineStyle", HTMLDocUtils.checkString(type.getLineStyle() + "")); //$NON-NLS-1$ //$NON-NLS-2$
            connectionElement.addAttribute("metaname", HTMLDocUtils.checkString(type.getMetaname())); //$NON-NLS-1$
            connectionElement.addAttribute("offsetLabelX", HTMLDocUtils.checkString(type.getOffsetLabelX() + "")); //$NON-NLS-1$ //$NON-NLS-2$
            connectionElement.addAttribute("offsetLabelY", HTMLDocUtils.checkString(type.getOffsetLabelY() + "")); //$NON-NLS-1$ //$NON-NLS-2$
            connectionElement.addAttribute("source", HTMLDocUtils.checkString(type.getSource())); //$NON-NLS-1$
            connectionElement.addAttribute("target", HTMLDocUtils.checkString(type.getTarget())); //$NON-NLS-1$
        }
    }

    /**
     * Generates job(process) information in XML base on <code>ProcessItem</code> and project element.
     * 
     * @param item <code>ProcessItem</code>
     * @param projectElement <code>Element</code>
     * @param version
     * @return an instance of <code>Element</code>
     */
    private Element generateJobInfo(Item item, Element projectElement, String... version) {

        picFilePathMap = new HashMap<String, String>();
        // IProcess process = CorePlugin.getDefault().getDesignerCoreService().getProcessFromProcessItem(processItem);

        Property property = item.getProperty();
        String jobName = property.getLabel();
        String jobVersion = property.getVersion();
        Element jobElement = projectElement.addElement("job"); //$NON-NLS-1$
        jobElement.addAttribute("name", HTMLDocUtils.checkString(jobName)); //$NON-NLS-1$

        jobElement.addAttribute("author", HTMLDocUtils.checkString(property.getAuthor().toString())); //$NON-NLS-1$

        if (version != null && version.length == 1) {
            jobVersion = version[0];
        }
        jobElement.addAttribute("version", HTMLDocUtils.checkString(jobVersion)); //$NON-NLS-1$
        jobElement.addAttribute("purpose", HTMLDocUtils.checkString(property.getPurpose())); //$NON-NLS-1$
        jobElement.addAttribute("status", HTMLDocUtils.checkString(property.getStatusCode())); //$NON-NLS-1$

        //        jobElement.addAttribute("description", HTMLDocUtils.checkString(property.getDescription())); //$NON-NLS-1$

        jobElement.addAttribute("creation", HTMLDocUtils.checkDate(property.getCreationDate())); //$NON-NLS-1$
        jobElement.addAttribute("modification", HTMLDocUtils.checkDate(property.getModificationDate())); //$NON-NLS-1$

        Element descr = jobElement.addElement("description"); //$NON-NLS-1$
        descr.addCDATA(HTMLDocUtils.checkString(property.getDescription()).replaceAll("\\r\\n", "<br/>")); //$NON-NLS-1$ //$NON-NLS-2$

        String picName = jobName + "_" + jobVersion + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX; //$NON-NLS-1$
        IPath filePath = null;
        if (item instanceof ProcessItem) {
            filePath = DocumentationPathProvider.getPathFileName(item, RepositoryConstants.IMG_DIRECTORY_OF_JOB_OUTLINE, picName);
        } else if (item instanceof JobletProcessItem) {
            filePath = DocumentationPathProvider.getPathFileName(item, RepositoryConstants.IMG_DIRECTORY_OF_JOBLET_OUTLINE,
                    picName);
        }
        Element previewElement = jobElement.addElement("preview"); //$NON-NLS-1$
        previewElement.addAttribute("picture", IHTMLDocConstants.PICTUREFOLDERPATH + picName); //$NON-NLS-1$

        // if (filePath == null) {
        //            previewElement.addAttribute("picture", ""); //$NON-NLS-1$ //$NON-NLS-2$
        // } else {
        // String filePathStr = filePath.toOSString();
        // File file = new File(filePathStr);
        // if (file.exists()) {
        //                previewElement.addAttribute("picture", IHTMLDocConstants.PICTUREFOLDERPATH + picName); //$NON-NLS-1$
        // picFilePathMap.put(picName, filePathStr);
        // }
        // }
        return jobElement;
    }

    /**
     * Generates project element information in XML file.
     * 
     * @param document <code>Document</code>
     * @return an instance of <code>Element</code>
     */
    protected Element generateProjectInfo(Document document) {
        Element projectElement = document.addElement("project"); //$NON-NLS-1$
        projectElement.addAttribute("name", getProject().getLabel()); //$NON-NLS-1$
        projectElement.addAttribute("logo", IHTMLDocConstants.PICTUREFOLDERPATH + IHTMLDocConstants.TALEND_LOGO_FILE_NAME); //$NON-NLS-1$
        projectElement.addAttribute("title", IHTMLDocConstants.TITLE_GEN + getFullProductName()); //$NON-NLS-1$
        projectElement.addAttribute("link", IHTMLDocConstants.WEBSITE_LINK); //$NON-NLS-1$
        projectElement.addAttribute("language", getProject().getLanguage().getName()); //$NON-NLS-1$
        //        projectElement.addAttribute("description", getProject().getDescription()); //$NON-NLS-1$
        projectElement.addAttribute("generatedDate", DateFormat.getDateTimeInstance().format(new Date())); //$NON-NLS-1$
        projectElement.addAttribute("versionName", getProductVersionName()); //$NON-NLS-1$
        projectElement.addAttribute("version", getCurrentTOSVersion()); //$NON-NLS-1$
        projectElement.addAttribute("docType", getDocTypeAttribute()); //$NON-NLS-1$
        Element proDesc = projectElement.addElement("pro-description"); //$NON-NLS-1$
        proDesc.addCDATA(getProject().getDescription().replaceAll("\\r\\n", "<br/>")); //$NON-NLS-1$ //$NON-NLS-2$
        return projectElement;
    }

    /**
     * ftang Comment method "addDocTypeAttribute".
     * 
     * @param projectElement
     */
    protected String getDocTypeAttribute() {
        return ERepositoryObjectType.JOB_DOC.toString();
    }

    /**
     * Add resources.
     * 
     * @param resource <code>ExportFileResource</code>
     * @param resultFiles a <code>List</code> of <code>URL</code>
     */
    private void addResources(ExportFileResource resource, List<URL> resultFiles) {
        resource.addResources(resultFiles);
    }

    /**
     * Get the current project.
     * 
     * @return an instance of <code>Project</code>
     */
    protected Project getProject() {
        if (this.project == null) {
            return ProjectManager.getInstance().getCurrentProject();
        }
        return this.project;
    }

    /**
     * Get source connections and target connections base on given <code>ProcessItem</code>.
     * 
     * @param item ProcessItem
     */
    protected void getSourceAndTargetConnection(Item item) {
        if (item instanceof ProcessItem) {
            EList connectionList = ((ProcessItem) item).getProcess().getConnection();
            if (connectionList != null && connectionList.size() != 0) {
                handleSourceAndTargetConnection(connectionList);
            }
        }
    }

    /**
     * Comment method "handleSourceAndTargetConnection".
     * 
     * @param sourceConnectionMap
     * @param targetConnectionMap
     * @param connectionList
     */
    protected void handleSourceAndTargetConnection(EList connectionList) {
        List<String> targetList = new ArrayList<String>();
        List<String> sourceList = new ArrayList<String>();
        for (int j = 0; j < connectionList.size(); j++) {
            ConnectionType type = (ConnectionType) connectionList.get(j);
            if (!targetConnectionMap.containsKey(type.getSource())) {
                targetList = new ArrayList<String>();
            }
            if (!targetList.contains(type.getTarget())) {
                targetList.add(type.getTarget());
            }

            targetConnectionMap.put(type.getSource(), targetList);

            if (!sourceConnectionMap.containsKey(type.getTarget())) {
                sourceList = new ArrayList<String>();
            }
            sourceList.add(type.getSource());
            sourceConnectionMap.put(type.getTarget(), sourceList);
        }

    }

    /**
     * This method is used for generating current T.O.S version.
     * 
     * @return
     */
    private String getCurrentTOSVersion() {
        String currentVersion = IHTMLDocConstants.UNKNOWN;
        currentVersion = (String) CorePlugin.getDefault().getBundle().getHeaders().get(
                org.osgi.framework.Constants.BUNDLE_VERSION);
        return currentVersion;
    }

    /**
     * 
     * DOC ggu Comment method "getExternalHtmlPath".<br>
     * 
     * add external Doc file list
     * 
     * @return
     * @throws MalformedURLException
     */
    private List<URL> getExternalHtmlPath() throws MalformedURLException {
        List<URL> externalList = new ArrayList<URL>();

        Set keySet = externalNodeHTMLMap.keySet();
        for (Object key : keySet) {
            if (externalNodeHTMLMap.get(key) instanceof URL) {
                URL html = (URL) externalNodeHTMLMap.get(key);
                if (html != null) {
                    externalList.add(html);// html

                    String htmlStr = html.toString();
                    String xmlStr = htmlStr.substring(0, htmlStr.lastIndexOf(IHTMLDocConstants.HTML_FILE_SUFFIX))
                            + IHTMLDocConstants.XML_FILE_SUFFIX;
                    externalList.add(new URL(xmlStr));// xml
                }

            }
        }

        return externalList;
    }

    /**
     * 
     * DOC ggu Comment method "getProductName".
     * 
     * @return
     */
    private String getFullProductName() {
        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);

        return brandingService.getFullProductName();
    }

    private String getProductVersionName() {
        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);

        return brandingService.getShortProductName() + IHTMLDocConstants.VERSION;
    }

    protected void saveLogoImage(int type, File file) throws IOException {
        // get image from cache
        ByteArrayOutputStream result = logoImageCache.get(type);
        if (result == null) {
            result = new ByteArrayOutputStream(3072);
            IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);
            ImageData imageData = brandingService.getLoginHImage().getImageData();
            new ByteArrayOutputStream();

            ImageLoader imageLoader = new ImageLoader();
            imageLoader.data = new ImageData[] { imageData };

            imageLoader.save(result, type);
            // put image to cache, no need to generate next time
            logoImageCache.put(type, result);
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(result.toByteArray());
        fos.close();

    }

    /**
     * 
     * DOC YeXiaowei Comment method "getConnectionItemById".
     * 
     * @param id
     * @return
     */
    private ConnectionItem getConnectionItemById(final String id) {

        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();

        List<ConnectionItem> connectionItems = null;

        try {
            connectionItems = factory.getMetadataConnectionsItem();
            if (connectionItems != null && !connectionItems.isEmpty()) {
                for (ConnectionItem item : connectionItems) {
                    if (item.getProperty().getId().equals(id)) {
                        return item;
                    }
                }
            }
        } catch (PersistenceException e) {
            return null;
        }

        return null;
    }

    /**
     * It is used for component list sort.
     * 
     * @return Comparator.
     */
    private Comparator getComparator() {

        return new Comparator() {

            public int compare(Object arg0, Object arg1) {

                if (arg0 == null || arg1 == null) {
                    return 0;
                }
                String name0 = ((INode) arg0).getUniqueName();
                String name1 = ((INode) arg1).getUniqueName();

                if (name0 == null || name1 == null) {
                    return 0;
                }

                return name0.compareTo(name1);
            }

        };
    }
}
