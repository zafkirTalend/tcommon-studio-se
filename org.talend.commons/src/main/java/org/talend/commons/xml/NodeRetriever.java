// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class NodeRetriever {

    public static final String STRING_EMPTY = "";

    public static final String STRING_AT = "@";

    private Document document;

    private XPath xpath;

    private String currentLoopXPath;

    /**
     * DOC amaumont XMLNodeRetriever constructor comment.
     * @param string 
     */
    public NodeRetriever(String filePath, String loopXPath) {
        super();
        this.currentLoopXPath = loopXPath;
        initSource(filePath);

    }

    /**
     * DOC amaumont Comment method "initSource".
     * 
     * @param filePath2
     */
    private void initSource(String filePath) {
        // Parse document containing schemas and validation roots
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(filePath);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Create XPath factory for selecting schema and validation roots
        XPathFactory xpf = XPathFactory.newInstance();
        xpath = xpf.newXPath();

    }

    /**
     * 
     * DOC amaumont Comment method "retrieveListOfNodes".
     * 
     * @param xPathExpression
     * @return always a <code>List</code> empty or not
     */
    public List<Node> retrieveListOfNodes(String xPathExpression) {

        NodeList nodeList = retrieveNodeList(xPathExpression);
        if (nodeList != null) {
            int nodeListLength = nodeList.getLength();
            List<Node> list = new ArrayList<Node>(nodeListLength);
            for (int j = 0; j < nodeListLength; ++j) {
                list.add(nodeList.item(j));
            }
            return list;
        } else {
            return new ArrayList<Node>(0);
        }

    }

    /**
     * DOC amaumont Comment method "retrieveNodeList".
     * 
     * @param xPathExpression
     * @param nodeList
     * @return <code>NodeList</code> or null if expression is invalid
     */
    public NodeList retrieveNodeList(String xPathExpression) {
        NodeList nodeList = null;
        try {
            XPathExpression xpathSchema = xpath.compile(xPathExpression);
            nodeList = (NodeList) xpathSchema.evaluate(document, XPathConstants.NODESET);
        } catch (Exception e) {
            return null;
        }
        return nodeList;
    }

//    /**
//     * DOC amaumont Comment method "retrieveListOfNodes".
//     * @param relativeXpath
//     * @param node
//     */
//    public NodeList retrieveListOfNodes(String relativeXpath, Node node) {
//        NodeList nodeList = null;
//        try {
//            XPathExpression xpathSchema = xpath.compile(relativeXpath);
//            nodeList = (NodeList) xpathSchema.evaluate(document, XPathConstants.NODESET);
//        } catch (Exception e) {
//            return null;
//        }
//        return nodeList;
//    }


    public static void main(String[] args) {
        String filePath = "C:/test_xml/Microsoft.DirectX.Direct3DX.xml";

        NodeRetriever pathRetriever = new NodeRetriever(filePath, "");

        String currentExpr = "/doc/members/member/..";

        
        String xPathExpression;
        String slash = "/";
        if (currentExpr.endsWith("/")) {
            slash = "";
        }

//        xPathExpression = currentExpr + slash + "*"; //+ " | " + currentExpr + slash + "@*";
        xPathExpression = currentExpr + slash + "*" + " | " + currentExpr + slash + "@*";
        
        System.out.println("xPathExpression = '"+xPathExpression + "'");
        
        List<Node> nodeList = pathRetriever.retrieveListOfNodes(xPathExpression);

        System.out.println("Count result : "+ nodeList.size());
        
//        for (Node node : nodeList) {
////            System.out.println(node.getNodeName() + ":" + node.getFirstChild().getNodeValue());
//            System.out.println(node.getNodeName());
//        }

    }

    /**
     * DOC amaumont Comment method "getAbsoluteXPathFromNode".
     * 
     * @param node
     */
    public static String getAbsoluteXPathFromNode(Node node) {
        return getAbsoluteXPathFromNode(node, "");
    }

    /**
     * DOC amaumont Comment method "getAbsoluteXPathFromNode".
     * 
     * @param node
     * @param string
     */
    private static String getAbsoluteXPathFromNode(Node node, String currentXPath) {
        Node parentNode = null;
        String at = "";
        if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
            parentNode = ((Attr) node).getOwnerElement();
            at = STRING_AT;
        } else {
            parentNode = node.getParentNode();
            at = STRING_EMPTY;
        }
        currentXPath = "/" + at + node.getNodeName() + currentXPath;
        if (parentNode == node.getOwnerDocument()) {
            return currentXPath;
        } else {
            return getAbsoluteXPathFromNode(parentNode, currentXPath);
        }
    }

    /**
     * Getter for document.
     * 
     * @return the document
     */
    public Document getDocument() {
        return this.document;
    }

    /**
     * DOC amaumont Comment method "setCurrentLoopXPath".
     * @param newValue
     */
    public void setCurrentLoopXPath(String currentLoopXPath) {
        this.currentLoopXPath = currentLoopXPath;
    }

    
    /**
     * Getter for currentLoopXPath.
     * @return the currentLoopXPath
     */
    public String getCurrentLoopXPath() {
        return this.currentLoopXPath;
    }

    
}
