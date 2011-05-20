// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.talend.core.model.metadata.builder.connection.Concept;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
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

    public static Concept getConcept(MDMConnection connection, MetadataTable table) {
        if (table == null || connection == null) {
            return null;
        }
        for (Object obj : connection.getSchemas()) {
            if (obj instanceof Concept) {
                Concept concept = (Concept) obj;
                if (concept.getLabel() != null && concept.getLabel().equals(table.getLabel())) {
                    return concept;
                }

            }
        }

        return null;

    }

    public static File getTempTemplateXSDFile() {
        IPath tempPath = new Path(System.getProperty("user.dir")).append("temp"); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$
        File tempFile = tempPath.toFile();
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        File file = new File(tempFile, "template.xsd"); //$NON-NLS-1$
        return file;
    }
}
