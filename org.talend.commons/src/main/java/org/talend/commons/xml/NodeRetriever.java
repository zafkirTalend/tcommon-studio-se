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

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;
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
     * 
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
    private synchronized void initSource(String filePath) {
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
     * @throws XPathExpressionException
     */
    public List<Node> retrieveListOfNodes(String xPathExpression) throws XPathExpressionException {

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
     * @throws XPathExpressionException
     */
    public synchronized NodeList retrieveNodeList(String xPathExpression) throws XPathExpressionException {

        xPathExpression = simplifyXPathExpression(xPathExpression);

        NodeList nodeList = null;
//        System.out.println("xPathExpression = "+xPathExpression);
        XPathExpression xpathSchema = xpath.compile(xPathExpression);
        nodeList = (NodeList) xpathSchema.evaluate(document, XPathConstants.NODESET);
        return nodeList;
    }

    /**
     * DOC amaumont Comment method "simplifyXPathExpression".
     * 
     * @param pathExpression
     * @return
     */
    private static String simplifyXPathExpression(String xpathExpression) {

        Perl5Matcher matcher = new Perl5Matcher();

        Perl5Compiler compiler = new Perl5Compiler();

        Pattern pattern = null;
        try {
            pattern = compiler.compile("(.*)/\\s*\\w+\\s*(/(\\.\\.|parent))(.*)");
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        Perl5Substitution substitution = new Perl5Substitution("$1$4", Perl5Substitution.INTERPOLATE_ALL);

        int lengthOfPreviousXPath = 0;

        do {
            lengthOfPreviousXPath = xpathExpression.length();
            if (matcher.matches(xpathExpression, pattern)) {
                xpathExpression = Util.substitute(matcher, pattern, substitution, xpathExpression, Util.SUBSTITUTE_ALL);
            }
        } while (xpathExpression.length() != lengthOfPreviousXPath);

        return xpathExpression;
    }

    /**
     * DOC amaumont Comment method "retrieveNodeList".
     * 
     * @param xPathExpression
     * @param nodeList
     * @return <code>NodeList</code> or null if expression is invalid
     * @throws XPathExpressionException
     */
    public synchronized NodeList retrieveNodeListFromNode(String relativeXPathExpression, Node referenceNode)
            throws XPathExpressionException {
        relativeXPathExpression = simplifyXPathExpression(relativeXPathExpression);
        NodeList nodeList = (NodeList) xpath.evaluate(relativeXPathExpression, referenceNode, XPathConstants.NODESET);
        return nodeList;
    }

    /**
     * DOC amaumont Comment method "retrieveNodeList".
     * 
     * @param xPathExpression
     * @param nodeList
     * @return <code>NodeList</code> or null if expression is invalid
     * @throws XPathExpressionException
     */
    public synchronized Node retrieveNode(String xPathExpression) throws XPathExpressionException {
        xPathExpression = simplifyXPathExpression(xPathExpression);
        XPathExpression xpathSchema = xpath.compile(xPathExpression);
        Node node = (Node) xpathSchema.evaluate(document, XPathConstants.NODE);
        return node;
    }

    /**
     * DOC amaumont Comment method "retrieveNodeList".
     * 
     * @param relativeXPathExpression
     * @param nodeList
     * @return <code>NodeList</code> or null if expression is invalid
     * @throws XPathExpressionException
     */
    public synchronized Node retrieveNodeFromNode(String relativeXPathExpression, Node referenceNode) throws XPathExpressionException {
        relativeXPathExpression = simplifyXPathExpression(relativeXPathExpression);
        Node node = (Node) xpath.evaluate(relativeXPathExpression, referenceNode, XPathConstants.NODE);
        return node;
    }

    /**
     * DOC amaumont Comment method "retrieveNodeList".
     * 
     * @param xPathExpression
     * @param nodeList
     * @return <code>NodeList</code> or null if expression is invalid
     * @throws XPathExpressionException
     */
    public synchronized Double retrieveNodeCount(String xPathExpression) throws XPathExpressionException {
        XPathExpression xpathSchema = xpath.compile(xPathExpression);
        Object countNode = (Object) xpathSchema.evaluate(document, XPathConstants.NUMBER);
        return (Double) countNode;
    }

    public static void main(String[] args) throws XPathExpressionException {

        String string = simplifyXPathExpression("/doc/members/member/..");

        System.out.println(string);

        if (true) {
            return;
        }

        String filePath = "C:/test_xml/Microsoft.DirectX.Direct3DX.xml";

        NodeRetriever pathRetriever = new NodeRetriever(filePath, "");

        String currentExpr = "child::node()";
        // String currentExpr = "/doc/members";

        String xPathExpression;
        String slash = "/";
        if (currentExpr.endsWith("/")) {
            slash = "";
        }

        xPathExpression = currentExpr + slash + "";
        // xPathExpression = currentExpr + slash + "*" + " | " + currentExpr + slash + "@*";

        // 7689 nodes
        xPathExpression = "/doc/members/../members/member/child::*";
        xPathExpression = "/doc/members/../members/member/*";

        // 17925 nodes + text
        xPathExpression = "/doc/members/../members/member/child::node()";

        // 2547 attributes
        xPathExpression = "/doc/members/../members/member/@*";

        xPathExpression = "/doc/members/../members/member/*";
        xPathExpression = "/doc/members/member/../";

        String mainXPathNode = "/doc/members/member";
        String field1 = "..";
        String field2 = "summary/";

        System.out.println("main expression =" + mainXPathNode);
        Node mainNode = pathRetriever.retrieveNode(mainXPathNode);
        if (mainNode != null) {
            System.out.println("mainNode=" + mainNode.getNodeName());
        }

        Node field1Node = pathRetriever.retrieveNodeFromNode(field1, mainNode);
        if (field1Node != null) {
            System.out.println("field1Node=" + field1Node.getNodeName());
        }

        Node field2Node = pathRetriever.retrieveNodeFromNode(field2, mainNode);
        if (field2Node != null) {
            System.out.println("field2Node=" + field2Node.getNodeName());
        }

        String proposal1 = "../*";
        String proposal2 = "./member/see/*";
        proposal2 = "member/* | member/@*";
        NodeList proposal1Nodes = pathRetriever.retrieveNodeListFromNode(proposal1, mainNode);
        if (field1Node != null) {
            int length = proposal1Nodes.getLength();
            System.out.println("proposal1Nodes : " + length);
            // int lstSize = length;
            // for (int i = 0; i < lstSize; i++) {
            // System.out.println(proposal1Nodes.item(i));
            // }
        }

        NodeList proposal2Nodes = pathRetriever.retrieveNodeListFromNode(proposal2, mainNode);
        if (proposal2Nodes != null) {
            int length = proposal2Nodes.getLength();
            System.out.println("proposal2Nodes : " + length);
            // int lstSize = length;
            // for (int i = 0; i < lstSize; i++) {
            // System.out.println(proposal2Nodes.item(i));
            // }
        }

        // if (true) {
        // return;
        // }

        // String countXPathExpression = null;
        // countXPathExpression = xPathExpression + "[count(*)]";
        // countXPathExpression = "count(" + xPathExpression + ")";
        // countXPathExpression = xPathExpression + "self::count()";
        // System.out.println("countXPathExpression = " + countXPathExpression);
        // TimeMeasure.start("count");
        // Double count = pathRetriever.retrieveNodeCount(countXPathExpression);
        // TimeMeasure.end("count");
        //
        // System.out.println("count=" + count);
        //
        // TimeMeasure.start("nodeList");
        // NodeList nodeList2 = pathRetriever.retrieveNodeList(xPathExpression);
        // System.out.println("Count result : " + nodeList2.getLength());
        // TimeMeasure.end("nodeList");
        //
        if (true) {
            return;
        }

        System.out.println("xPathExpression = '" + xPathExpression + "'");
        List<Node> nodeList = pathRetriever.retrieveListOfNodes(xPathExpression);

        System.out.println("Count result : " + nodeList.size());

        int lstSize = nodeList.size();
        int limit = 100;
        for (int i = 0; i < lstSize; i++) {
            if (i > limit) {
                break;
            }
            Node node = (Node) nodeList.get(i);
            System.out.println(node.getNodeName());

        }

        // for (Node node : nodeList) {
        // // System.out.println(node.getNodeName() + ":" + node.getFirstChild().getNodeValue());
        // System.out.println(node.getNodeName());
        // }

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
     * 
     * @param newValue
     */
    public void setCurrentLoopXPath(String currentLoopXPath) {
        this.currentLoopXPath = currentLoopXPath;
    }

    /**
     * Getter for currentLoopXPath.
     * 
     * @return the currentLoopXPath
     */
    public String getCurrentLoopXPath() {
        return this.currentLoopXPath;
    }

    /**
     * DOC amaumont Comment method "dispose".
     */
    public void dispose() {
        if (document != null) {
            // document.re
        }
    }

}
