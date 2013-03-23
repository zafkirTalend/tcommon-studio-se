// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.rowgenerator.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.ui.JavadocContentAccess;
import org.osgi.framework.Bundle;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.repository.ProjectManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOC hcyi class global comment. Detailled comment
 */
public class PigFunctionParser extends AbstractFunctionParser {

    private static Logger logger = Logger.getLogger(PigFunctionParser.class);

    public static final String NEWLINE_CHARACTER = "\n\n";

    // k: (Talend type Name).(method Name) v:(class Name).(method Name)
    private static Map<String, String> typeMethods = new HashMap<String, String>();

    private static Map<String, String> typePackgeMethods = new HashMap<String, String>();

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parse()
     */
    @Override
    public void parse() {
        try {
            Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
            URL fileUrl = FileLocator.toFileURL(FileLocator.find(b, new Path("resources/" + "PigExpressionBuilder.xml"), null));
            File pigFile = new File(fileUrl.getFile());
            if (!pigFile.exists()) {
                throw new FileNotFoundException();
            }
            // use dom parse it
            useDomParse(fileUrl.getFile());
            // parse Pig UDF Functions
            parsePigUDFFunction();
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    public void parsePigUDFFunction() {
        typeMethods.clear();
        try {
            IJavaProject javaProject = CorePlugin.getDefault().getRunProcessService().getJavaProject();
            if (javaProject != null) {
                IProject project = javaProject.getProject();
                IFolder srcFolder = project.getFolder(JavaUtils.JAVA_SRC_DIRECTORY);
                IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(srcFolder);
                List<IJavaElement> elements = new ArrayList<IJavaElement>();
                addEveryProjectElements(root, elements);
                for (int i = elements.size(); i > 0; i--) {
                    IJavaElement element = elements.get(i - 1);
                    if (element instanceof ICompilationUnit) {
                        ICompilationUnit compilationUnit = (ICompilationUnit) element;
                        IType[] types = compilationUnit.getAllTypes();
                        if (types.length > 0) {
                            SourceType sourceType = (SourceType) types[0];
                            if (sourceType != null) {
                                try {
                                    Reader reader = JavadocContentAccess.getContentReader(sourceType, true);
                                    if (reader != null) {
                                        char[] charBuffer = new char[1024];
                                        StringBuffer str = new StringBuffer();
                                        int index = 0;
                                        while ((index = reader.read(charBuffer)) != -1) {
                                            str.append(charBuffer, 0, index);
                                            index = 0;
                                        }
                                        reader.close();
                                        parsePigCommentToFunctions(str.toString(), sourceType.getElementName(),
                                                sourceType.getFullyQualifiedName(), sourceType.getElementName(), false);
                                    }
                                } catch (Exception e) {
                                    ExceptionHandler.process(e);
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    /**
     * 
     * DOC Comment method "useDomParse".
     * 
     * @param fileUrl
     */
    public void useDomParse(String fileUrl) {
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            InputStream is = new FileInputStream(fileUrl);
            Document doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            NodeList functions = root.getChildNodes();
            if (functions != null) {
                for (int i = 0; i < functions.getLength(); i++) {
                    // category
                    Node category = functions.item(i);
                    if (category.getNodeType() == Node.ELEMENT_NODE) {
                        String categoryName = category.getAttributes().getNamedItem("name").getNodeValue();
                        // function
                        for (Node function = category.getFirstChild(); function != null; function = function.getNextSibling()) {
                            //
                            StringBuffer strTemp = new StringBuffer();
                            String functionName = null, syntax = null, usage = null;
                            if (function.getNodeType() == Node.ELEMENT_NODE) {
                                functionName = function.getAttributes().getNamedItem("name").getNodeValue();
                                strTemp.append(functionName);
                                for (Node node = function.getFirstChild(); node != null; node = node.getNextSibling()) {
                                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                                        // Desc
                                        if (node.getNodeName().equals("Desc")) {
                                            String desc = node.getFirstChild().getNodeValue();
                                            strTemp.append(": " + desc);
                                        }
                                        // Syntax
                                        if (node.getNodeName().equals("Syntax")) {
                                            syntax = node.getFirstChild().getNodeValue();
                                        }
                                        // Usage
                                        if (node.getNodeName().equals("Usage")) {
                                            usage = node.getFirstChild().getNodeValue();
                                        }
                                    }
                                }
                                strTemp.append(NEWLINE_CHARACTER);
                                strTemp.append("{talendTypes} String");
                                strTemp.append(NEWLINE_CHARACTER);
                                strTemp.append("{Category}" + categoryName);
                                strTemp.append(NEWLINE_CHARACTER);
                                strTemp.append("{param}" + usage);
                                strTemp.append(NEWLINE_CHARACTER);
                                strTemp.append("{example}" + syntax);
                            }
                            if (strTemp != null && !strTemp.toString().equals("")) {
                                parsePigCommentToFunctions(strTemp.toString(), categoryName, functionName, functionName, true);
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parsePigCommentToFunctions(String str, String className, String fullName, String funcName, boolean isSystem) {
        try {
            String des = parseDescription(str);
            String category = parseCategoryType(str);
            String functionType = parseFunctionType(str);
            String[] parameter = parseFunctionParameters(str);
            if (functionType != null && category.trim().length() > 0) {
                Parameter[] paras = convertToParameter(parameter);
                Function function = new Function();
                function.setClassName(className != null ? className.replace(" ", "") : null);
                function.setName(funcName);
                function.setDescription(des);
                function.setParameters(Arrays.asList(paras));
                if (!category.equals(EMPTY_STRING)) {
                    function.setCategory(category);
                }
                // Pig UDF Functions set a default category
                if (!isSystem) {
                    function.setCategory("Pig UDF Functions");
                    function.setPreview(category);
                }
                function.setUserDefined(!isSystem);
                TalendType talendType = getTalendType(functionType);
                talendType.addFunctions(function);
                typeMethods.put(functionType + "." + funcName, className + "." + funcName); //$NON-NLS-1$ //$NON-NLS-2$
                typePackgeMethods.put(functionType + "." + funcName, fullName + "." + funcName); //$NON-NLS-1$ //$NON-NLS-2$
                function.setTalendType(talendType);
            }
        } catch (Exception e) {
            logger.error(Messages.getString("PigFunctionParser.checkMethod", fullName, funcName), e); //$NON-NLS-1$
        }
    }

    private void addEveryProjectElements(IPackageFragmentRoot root, List<IJavaElement> elements) throws JavaModelException {
        if (root == null || elements == null) {
            return;
        }
        // system
        IPackageFragment pigudfPkg = root.getPackageFragment(JavaUtils.JAVA_PIGUDF_DIRECTORY);
        if (pigudfPkg != null && pigudfPkg.exists()) {
            elements.addAll(Arrays.asList(pigudfPkg.getChildren()));
        }
        ProjectManager projectManager = ProjectManager.getInstance();
        Project currentProject = projectManager.getCurrentProject();
        // current project
        IPackageFragment userPigPkg = root.getPackageFragment(JavaUtils.JAVA_PIGUDF_DIRECTORY + "." //$NON-NLS-1$
                + currentProject.getLabel().toLowerCase());
        if (userPigPkg != null && userPigPkg.exists()) {
            elements.addAll(Arrays.asList(userPigPkg.getChildren()));
        }
        // referenced project.
        projectManager.retrieveReferencedProjects();
        for (Project p : projectManager.getReferencedProjects()) {
            userPigPkg = root.getPackageFragment(JavaUtils.JAVA_PIGUDF_DIRECTORY + "." + p.getLabel().toLowerCase()); //$NON-NLS-1$
            if (userPigPkg != null && userPigPkg.exists()) {
                elements.addAll(Arrays.asList(userPigPkg.getChildren()));
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parseDescription(java.lang.String)
     */
    @Override
    protected String parseDescription(String string) {
        String string2 = "";
        if (string.indexOf("{talendTypes}") > 0) {
            string2 = string.substring(0, string.indexOf("{talendTypes}"));
        }
        return string2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parseFunctionType(java.lang.String)
     */
    @Override
    protected String parseFunctionType(String string) {
        String string2 = super.parseFunctionType(string);
        if (string2 != null && !string2.trim().equals("")) {
            JavaType type = JavaTypesManager.getJavaTypeFromLabel(string2);
            if (type == null) {
                type = JavaTypesManager.getJavaTypeFromName(string2);
            }
            if (type != null) {
                return type.getId();
            }
        }
        return EMPTY_STRING;
    }

    public static Map<String, String> getTypeMethods() {
        return typeMethods;
    }

    public static Map<String, String> getTypePackgeMethods() {
        return typePackgeMethods;
    }
}
