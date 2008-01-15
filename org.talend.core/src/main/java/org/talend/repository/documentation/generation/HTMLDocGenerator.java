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
package org.talend.repository.documentation.generation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.Project;
import org.talend.core.model.genhtml.FileCopyUtils;
import org.talend.core.model.genhtml.HTMLDocUtils;
import org.talend.core.model.genhtml.HTMLHandler;
import org.talend.core.model.genhtml.IHTMLDocConstants;
import org.talend.core.model.genhtml.XMLHandler;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ConnectionType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.repository.documentation.ExportFileResource;
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

    private Map<String, URL> externalNodeHTMLMap = new HashMap<String, URL>();

    private ERepositoryObjectType repositoryObjectType;

    public HTMLDocGenerator(ERepositoryObjectType repositoryObjectType) {
        this.designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
        this.mapList = designerCoreService.getMaps();
        this.repositoryConnectionItemMap = mapList.get(0);
        this.repositoryDBIdAndNameMap = mapList.get(1);
        this.repositoryObjectType = repositoryObjectType;
    }

    /*
     * This method is used for generating HTML file base on an instance of <code>ExportFileResource</code>
     * (non-Javadoc)
     * 
     * @see org.talend.repository.documentation.generation.IDocumentationGenerator#generateHTMLFile(org.talend.repository.documentation.ExportFileResource)
     */
    public void generateHTMLFile(ExportFileResource resource) {
        try {

            // Store all pictures' path.
            List<URL> picList = new ArrayList<URL>(5);

            String jobName = resource.getItem().getProperty().getLabel();

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
                String value = (String) picFilePathMap.get(key);
                FileCopyUtils.copy(value, picFolderPath + File.separatorChar + key);
                picList.add(new File(picFolderPath + File.separatorChar + key).toURL());
            }

            List<URL> resultFiles = parseXML2HTML(tempFolderPath, jobName, xslFilePath);

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
     * This method is used for generating HTML file base on an instance of <code>ExportFileResource</code>
     * (non-Javadoc)
     * 
     * @see org.talend.repository.documentation.generation.IDocumentationGenerator#generateDocumentation(org.talend.repository.documentation.ExportFileResource,
     * java.lang.String, java.lang.String[])
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
        String version = "";

        // Checks if the job's version is specified, see it on "Export documentation" Dialog:
        if (jobVersion != null && jobVersion.length == 1) {
            version = jobVersion[0];
        } else {
            version = resource.getItem().getProperty().getVersion();
        }
        targetPath = targetPath + "_" + version;

        File file = new File(targetPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        handleXMLFile(resource, targetPath, version);

        String picFolderPath = checkPicDirIsExists(resource, targetPath);

        // Gets the "org.talend.repository" plug-in:
        final Bundle b = Platform.getBundle("org.talend.repository");

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

        Set keySet = picFilePathMap.keySet();
        for (Object key : keySet) {
            String value = (String) picFilePathMap.get(key);
            FileCopyUtils.copy(value, picFolderPath + File.separatorChar + key);
            picList.add(new File(picFolderPath + File.separatorChar + key).toURL());
        }

        List<URL> resultFiles = parseXML2HTML(targetPath, jobName + "_" + version, xslFilePath);

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
        String tempDirPath = HTMLDocUtils.getTmpFolder() + File.separator + resource.getDirectoryName();
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

        Element jobElement = generateJobInfo(item, projectElement);

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

        Element internalNodeElement = jobElement.addElement("internalNodeComponents");
        Element externalNodeElement = jobElement.addElement("externalNodeComponents");

        if (internalNodeComponentsList.size() > 0) {
            InternalNodeComponentHandler internalNodeComponentHandler = new InternalNodeComponentHandler(this.picFilePathMap,
                    internalNodeElement, internalNodeComponentsList, this.sourceConnectionMap, this.targetConnectionMap,
                    this.designerCoreService, this.repositoryConnectionItemMap, this.repositoryDBIdAndNameMap);

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

        if (connectionList != null || connectionList.size() != 0) {
            generateConnectionsInfo(jobElement, connectionList);
        }

        String versionPath = "";
        if (version != null && version.length == 1) {
            String currentVersion = (version[0].equals("") ? item.getProperty().getVersion() : version[0]);
            versionPath = "_" + currentVersion;
        }

        String filePath = tempFolderPath + File.separatorChar + item.getProperty().getLabel() + versionPath
                + IHTMLDocConstants.XML_FILE_SUFFIX;

        XMLHandler.generateXMLFile(tempFolderPath, filePath, document);
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

            ImageData imageData = node.getComponent().getIcon32().getImageData();
            if (imageData != null) {
                width = imageData.width;
                height = imageData.height;
            }

            if (componentNameListElement == null) {
                componentNameListElement = inputJobElement.addElement("componentList");
            }
            Element componentItemElement = null;
            componentItemElement = componentNameListElement.addElement("componentItem");
            componentItemElement.addAttribute("name", node.getUniqueName());
            componentItemElement.addAttribute("link", node.getUniqueName());
            componentItemElement.addAttribute("type", node.getComponent().getName());
            componentItemElement.addAttribute("leftTopX", x + "");
            componentItemElement.addAttribute("leftTopY", y + "");
            componentItemElement.addAttribute("rightBottomX", x + width + "");
            componentItemElement.addAttribute("rightBottomY", y + height + "");
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
                screenshotOffset.x = Integer.valueOf("".equals(HTMLDocUtils.checkString(curElem.getValue())) ? "0" : curElem
                        .getValue());
            } else if (curElem.getName().equals(IProcess.SCREEN_OFFSET_Y)) {
                screenshotOffset.y = Integer.valueOf("".equals(HTMLDocUtils.checkString(curElem.getValue())) ? "0" : curElem
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
        Element connectionsElement = jobElement.addElement("connections");
        for (int j = 0; j < connectionList.size(); j++) {
            ConnectionType type = (ConnectionType) connectionList.get(j);
            Element connectionElement = connectionsElement.addElement("connection");
            connectionElement.addAttribute("label", HTMLDocUtils.checkString(type.getLabel()));
            connectionElement.addAttribute("lineStyle", HTMLDocUtils.checkString(type.getLineStyle() + ""));
            connectionElement.addAttribute("metaname", HTMLDocUtils.checkString(type.getMetaname()));
            connectionElement.addAttribute("offsetLabelX", HTMLDocUtils.checkString(type.getOffsetLabelX() + ""));
            connectionElement.addAttribute("offsetLabelY", HTMLDocUtils.checkString(type.getOffsetLabelY() + ""));
            connectionElement.addAttribute("source", HTMLDocUtils.checkString(type.getSource()));
            connectionElement.addAttribute("target", HTMLDocUtils.checkString(type.getTarget()));
        }
    }

    /**
     * Generates job(process) information in XML base on <code>ProcessItem</code> and project element.
     * 
     * @param item <code>ProcessItem</code>
     * @param projectElement <code>Element</code>
     * @return an instance of <code>Element</code>
     */
    private Element generateJobInfo(Item item, Element projectElement) {

        picFilePathMap = new HashMap<String, String>();
        // IProcess process = CorePlugin.getDefault().getDesignerCoreService().getProcessFromProcessItem(processItem);

        Property property = item.getProperty();
        String jobName = property.getLabel();
        String jobVersion = property.getVersion();
        Element jobElement = projectElement.addElement("job");
        jobElement.addAttribute("name", HTMLDocUtils.checkString(jobName));

        jobElement.addAttribute("author", HTMLDocUtils.checkString(property.getAuthor().toString()));
        jobElement.addAttribute("version", HTMLDocUtils.checkString(property.getVersion()));
        jobElement.addAttribute("purpose", HTMLDocUtils.checkString(property.getPurpose()));
        jobElement.addAttribute("status", HTMLDocUtils.checkString(property.getStatusCode()));
        jobElement.addAttribute("description", HTMLDocUtils.checkString(property.getDescription()));

        jobElement.addAttribute("creation", HTMLDocUtils.checkDate(property.getCreationDate()));
        jobElement.addAttribute("modification", HTMLDocUtils.checkDate(property.getModificationDate()));

        String picName = jobName + "_" + jobVersion + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX;
        IPath filePath = null;
        if (item instanceof ProcessItem) {
            filePath = DocumentationPathProvider.getPathFileName(RepositoryConstants.IMG_DIRECTORY_OF_JOB_OUTLINE, picName);
        } else if (item instanceof JobletProcessItem) {
            filePath = DocumentationPathProvider.getPathFileName(RepositoryConstants.IMG_DIRECTORY_OF_JOBLET_OUTLINE, picName);
        }

        Element previewElement = jobElement.addElement("preview");
        if (filePath == null) {
            previewElement.addAttribute("picture", "");
        } else {
            String filePathStr = filePath.toOSString();
            File file = new File(filePathStr);
            if (file.exists()) {
                previewElement.addAttribute("picture", IHTMLDocConstants.PICTUREFOLDERPATH + picName);
                picFilePathMap.put(picName, filePathStr);
            }
        }
        return jobElement;
    }

    /**
     * Generates project element information in XML file.
     * 
     * @param document <code>Document</code>
     * @return an instance of <code>Element</code>
     */
    private Element generateProjectInfo(Document document) {
        Element projectElement = document.addElement("project");
        projectElement.addAttribute("name", getProject().getLabel());
        projectElement.addAttribute("logo", IHTMLDocConstants.PICTUREFOLDERPATH + IHTMLDocConstants.TALEND_LOGO_FILE_NAME);
        projectElement.addAttribute("title", IHTMLDocConstants.TITLE_GEN + getFullProductName());
        projectElement.addAttribute("link", IHTMLDocConstants.WEBSITE_LINK);
        projectElement.addAttribute("language", getProject().getLanguage().getName());
        projectElement.addAttribute("description", getProject().getDescription());
        projectElement.addAttribute("generatedDate", DateFormat.getDateTimeInstance().format(new Date()));
        projectElement.addAttribute("versionName", getProductVersionName());
        projectElement.addAttribute("version", getCurrentTOSVersion());
        return projectElement;
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
    private Project getProject() {
        return ((org.talend.core.context.RepositoryContext) CorePlugin.getContext().getProperty(
                org.talend.core.context.Context.REPOSITORY_CONTEXT_KEY)).getProject();
    }

    /**
     * Get source connections and target connections base on given <code>ProcessItem</code>.
     * 
     * @param item ProcessItem
     */
    protected void getSourceAndTargetConnection(Item item) {
        if (item instanceof ProcessItem) {
            EList connectionList = ((ProcessItem) item).getProcess().getConnection();
            if (connectionList != null || connectionList.size() != 0) {
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
            URL html = externalNodeHTMLMap.get(key);
            if (html != null) {
                externalList.add(html);// html

                String htmlStr = html.toString();
                String xmlStr = htmlStr.substring(0, htmlStr.lastIndexOf(IHTMLDocConstants.HTML_FILE_SUFFIX))
                        + IHTMLDocConstants.XML_FILE_SUFFIX;
                externalList.add(new URL(xmlStr));// xml
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

    private void saveLogoImage(int type, File file) throws IOException {
        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);
        ImageData imageData = brandingService.getLoginHImage().getImageData();
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        ImageLoader imageLoader = new ImageLoader();
        imageLoader.data = new ImageData[] { imageData };

        imageLoader.save(result, type);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(result.toByteArray());
        fos.close();

    }
}
