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
package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.impl.dtd.DTDGrammar;
import org.apache.xerces.impl.dtd.XMLAttributeDecl;
import org.apache.xerces.impl.dtd.XMLDTDLoader;
import org.apache.xerces.impl.dtd.XMLElementDecl;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * DOC hwang class global comment. Detailled comment This class is used to populate an DTD schema tree from an dtd file.
 */

public class DTDFileSchemaTreePopulator {

    private DTDGrammar grammar = null;

    private List<DTDElement> elementList;

    private List<String> elementStrList;

    private static String CHILD_REG = "[\\\\(|\\\\)|\\\\*|\\\\+|\\\\?]|(#PCDATA)|(##any)|(##other)|(##local)";

    public ATreeNode getSchemaTree(String filePath, boolean incAttr) throws OdaException, IOException {
        ATreeNode root = new ATreeNode();
        root.setValue("ROOT");
        XMLDTDLoader loader = new XMLDTDLoader();
        XMLInputSource is = new XMLInputSource("", filePath, "");
        // try {
        grammar = (DTDGrammar) loader.loadGrammar(is);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        if (grammar != null) {
            parseAllElements();
        }
        List<DTDElement> strList = getAllElements();
        DTDElement dtd = strList.get(0);
        ATreeNode node = new ATreeNode();
        node.setValue(dtd.elementName);
        node.setType(ATreeNode.ELEMENT_TYPE);
        // try {
        node.setDataType(dtd.elementName);
        // } catch (OdaException e) {
        // e.printStackTrace();
        // }
        initTreeNode(dtd, node);
        root.addChild(node);
        return root;
    }

    private void initTreeNode(DTDElement dtdElement, ATreeNode node) throws OdaException {

        List<DTDElement> chds = getChildren(dtdElement);
        for (DTDElement childEle : chds) {
            ATreeNode childNode = new ATreeNode();
            childNode.setValue(childEle.elementName);
            childNode.setType(ATreeNode.ELEMENT_TYPE);
            // try {
            childNode.setDataType(childEle.elementName);
            // } catch (OdaException e) {
            // e.printStackTrace();
            // }
            node.addChild(childNode);
            if (getChildren(childEle) != null && getChildren(childEle).size() > 0) {
                initTreeNode(childEle, childNode);
            }

        }

    }

    /**
     * getAllElements
     * 
     * @return
     */
    public List<DTDElement> getAllElements() {
        return elementList;
    }

    /**
     * getAllElementname
     * 
     * @return
     */
    public List<String> getAllElementStrs() {
        return elementStrList;
    }

    /**
     * getChildren
     * 
     * @param dtd
     * @return
     */
    public List<DTDElement> getChildren(DTDElement dtd) {
        if (dtd.type != XMLElementDecl.TYPE_CHILDREN) {
            return null; // this node do not have child
        }
        List<DTDElement> childList = new ArrayList<DTDElement>();
        String specAsString = grammar.getContentSpecAsString(dtd.elementDeclIndex);
        if (specAsString == null || specAsString.equals("")) {
            return null;
        }
        specAsString = specAsString.replaceAll(DTDFileSchemaTreePopulator.CHILD_REG, ",");
        String[] childs = specAsString.split(",");

        for (String s : childs) {
            if ("".equals(s) || s == null) {
                continue;
            }
            if (elementStrList.indexOf(s) > 0 && elementList.size() > elementStrList.indexOf(s)) {
                DTDElement de = elementList.get(elementStrList.indexOf(s));
                childList.add(de);
            }
        }
        return childList;
    }

    private ATreeNode parseAllElements() {
        elementList = new ArrayList<DTDElement>();
        elementStrList = new ArrayList<String>();
        int elementDeclIndex = 0;
        XMLElementDecl elementDecl = new XMLElementDecl();
        while (grammar.getElementDecl(elementDeclIndex, elementDecl)) {
            DTDElement dtd = new DTDElement();
            dtd.elementDeclIndex = elementDeclIndex;
            dtd.elementName = elementDecl.name.rawname;
            dtd.type = elementDecl.type;
            int attributeDeclIndex = grammar.getFirstAttributeDeclIndex(elementDeclIndex);
            List<XMLAttributeDecl> attrList = new ArrayList<XMLAttributeDecl>();
            while (attributeDeclIndex != -1) {
                XMLAttributeDecl attributeDecl = new XMLAttributeDecl();
                if (grammar.getAttributeDecl(attributeDeclIndex, attributeDecl)) {
                    attrList.add(attributeDecl);
                }
                attributeDeclIndex = grammar.getNextAttributeDeclIndex(attributeDeclIndex);
            }
            dtd.attrList = attrList;
            elementList.add(dtd);
            elementStrList.add(elementDecl.name.rawname);
            elementDeclIndex++;
        }
        return null;
    }
}

class DTDElement {

    public int elementDeclIndex;

    public String elementName;

    public int type;

    public List<XMLAttributeDecl> attrList;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DTDElement) {
            DTDElement d = (DTDElement) obj;
            return d.elementDeclIndex == this.elementDeclIndex;
        }
        return false;
    }

}
