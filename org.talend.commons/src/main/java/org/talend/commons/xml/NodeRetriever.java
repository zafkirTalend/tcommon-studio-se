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
import javax.xml.xpath.XPathExpressionException;
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

    private Document doc;

    private XPath xpath;

    /**
     * DOC amaumont XMLNodeRetriever constructor comment.
     */
    public NodeRetriever(String filePath) {
        super();

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
            doc = db.parse(filePath);
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

    public List<Node> retrieveNodes(String xPathExpression) {

        NodeList nodeList = null;
        try {
            XPathExpression xpathSchema = xpath.compile(xPathExpression);
            nodeList = (NodeList) xpathSchema.evaluate(doc, XPathConstants.NODESET);
            int nodeListLength = nodeList.getLength();
            List<Node> list = new ArrayList<Node>(nodeListLength);
            for (int j = 0; j < nodeListLength; ++j) {
                list.add(nodeList.item(j));
            }

            return list;
        } catch (Exception e) {
            return null;
        }

        // return (List)new ArrayList<Node>(0);
        // /users/user_tuple | /users/user_tuple/userid2/
    }

    public static void main(String[] args) {
        String filePath = "D:\\dev\\examples\\java\\xml\\saxon-resources8-8\\use-cases\\r\\users.xml";

        NodeRetriever pathRetriever = new NodeRetriever(filePath);

        String xPathExpression = "/users/user_tuple/userid2/@attr";

        List<Node> nodeList = pathRetriever.retrieveNodes(xPathExpression);

        for (Node node : nodeList) {
            System.out.println(node.getNodeName() + ":" + node.getFirstChild().getNodeValue());
        }

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
            parentNode = ((Attr)node).getOwnerElement();
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

}
