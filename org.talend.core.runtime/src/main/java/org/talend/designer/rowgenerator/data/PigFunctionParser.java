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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOC hcyi class global comment. Detailled comment
 */
public class PigFunctionParser extends AbstractTalendFunctionParser {

    public static final String NEWLINE_CHARACTER = "\n\n";

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parse()
     */
    @Override
    public void parse() {
        typeMethods.clear();
        try {
            Bundle b = Platform.getBundle(CoreRuntimePlugin.PLUGIN_ID);
            URL fileUrl = FileLocator.toFileURL(FileLocator.find(b, new Path("resources/" + "PigExpressionBuilder.xml"), null));
            File pigFile = new File(fileUrl.getFile());
            if (!pigFile.exists()) {
                throw new FileNotFoundException();
            }
            // use dom parse it
            useDomParse(fileUrl.getFile());

            // parse Pig UDF Functions
            super.parse();
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    @Override
    protected Function parseJavaCommentToFunctions(String str, String className, String fullName, String funcName,
            boolean isSystem) {
        Function function = super.parseJavaCommentToFunctions(str, className, fullName, funcName, isSystem);
        // Pig UDF Functions set a default category
        if (function != null && !isSystem) {
            String category = parseCategoryType(str);
            function.setCategory("Pig UDF Functions");
            function.setPreview(category);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractTalendFunctionParser#getPackageFragment()
     */
    @Override
    protected String getPackageFragment() {
        return JavaUtils.JAVA_PIGUDF_DIRECTORY;
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
                                parseJavaCommentToFunctions(strTemp.toString(), categoryName, functionName, functionName, true);
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

}
