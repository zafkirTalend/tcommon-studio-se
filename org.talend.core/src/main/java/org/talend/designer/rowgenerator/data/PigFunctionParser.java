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
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
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
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

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
