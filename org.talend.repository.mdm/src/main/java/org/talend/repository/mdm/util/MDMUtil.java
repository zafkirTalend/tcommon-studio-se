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
package org.talend.repository.mdm.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMUtil {

    public static List<String> getConcepts(XSDSchema schema) {
        EList xsdElementDeclarations = schema.getElementDeclarations();
        List<String> list = new ArrayList<String>();
        for (XSDElementDeclaration el : (XSDElementDeclaration[]) xsdElementDeclarations
                .toArray(new XSDElementDeclaration[xsdElementDeclarations.size()])) {
            if (!el.getIdentityConstraintDefinitions().isEmpty()) {
                list.add(el.getName());
            }
        }
        return list;
    }

    /**
     * Returns and XSDSchema Object from an xsd
     * 
     * @param schema
     * @return
     * @throws Exception
     */
    public static XSDSchema getXSDSchema(String schema) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
        InputSource source = new InputSource(new StringReader(schema));
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(source);
        XSDSchema xsdSchema = null;

        xsdSchema = XSDSchemaImpl.createSchema(document.getDocumentElement());

        return xsdSchema;
    }
}
