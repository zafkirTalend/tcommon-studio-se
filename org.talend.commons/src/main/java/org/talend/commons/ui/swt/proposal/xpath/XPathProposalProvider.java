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
package org.talend.commons.ui.swt.proposal.xpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPathExpressionException;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.xml.NodeRetriever;
import org.w3c.dom.Node;

/**
 * ContentProposalProvider which proposes child nodes. <br/>
 * 
 * $Id: ExpressionProposalProvider.java 311 2006-11-03 07:00:19 +0000 (ven., 03 nov. 2006) amaumont $
 * 
 */
public class XPathProposalProvider implements IContentProposalProvider {

    public static final String EMPTY_STRING = "";

    public static final String SLASH = "/";

    private static final String PIPE = "|";

    private IContentProposalProvider[] otherContentProposalProviders;

    private NodeRetriever nodeRetriever;

    private boolean isRelative;

    /**
     * Constructs a new ProcessProposalProvider.
     * 
     * @param nodeRetriever
     * 
     * @param tables
     * @param control
     */
    public XPathProposalProvider(NodeRetriever nodeRetriever, boolean isRelative) {
        super();
        this.nodeRetriever = nodeRetriever;
        this.isRelative = isRelative;
    }

    public void init() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
     */
    public IContentProposal[] getProposals(String contents, int position) {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();

        List<Node> nodeList = new ArrayList<Node>();

        String beforeCursorExp = null;
        boolean expressionIsRelative = !contents.trim().startsWith(SLASH);
        if (isRelative && expressionIsRelative) {
            beforeCursorExp = nodeRetriever.getCurrentLoopXPath() + SLASH + contents;
        } else {
            beforeCursorExp = contents.substring(0, position);
        }
        int lastIndexSlash = beforeCursorExp.lastIndexOf(SLASH);
        int lastIndexPipe = beforeCursorExp.lastIndexOf(PIPE);

        String currentExpr = null;
        if (isRelative && expressionIsRelative) {
            currentExpr = beforeCursorExp;
        } 
        if (lastIndexSlash == -1 || lastIndexSlash < lastIndexPipe && lastIndexPipe != -1) {
            currentExpr = EMPTY_STRING;
        } else if (lastIndexPipe < lastIndexSlash && lastIndexPipe != -1) {
            currentExpr = beforeCursorExp.substring(lastIndexPipe + 1, lastIndexSlash + 1);
        } else if (lastIndexSlash != -1) {
            currentExpr = beforeCursorExp.substring(0, lastIndexSlash + 1);
            // currentExpr = beforeCursorExp;
        } else {
            currentExpr = beforeCursorExp;
        }

        currentExpr = currentExpr.trim();

        String currentWord = extractLastWord(beforeCursorExp);

        // String xPathExpression =
        //            
        // // + " | " +
        // createXPathExpression(beforeCursorExp)
        // ;
        // System.out.println("#############################");
        // System.out.println("currentExpr='"+currentExpr+"'");
        // System.out.println("beforeCursorExp='"+beforeCursorExp+"'");
        // System.out.println("currentWord='"+currentWord+"'");
        // System.out.println("1");
        List<Node> list = new ArrayList<Node>(0);
        try {
            list = this.nodeRetriever.retrieveListOfNodes(createXPathExpression(currentExpr));
        } catch (XPathExpressionException e) {
            ExceptionHandler.process(e);
        }
        nodeList.addAll(list);
        if (list.size() == 0) {
            try {
                nodeList.addAll(nodeRetriever.retrieveListOfNodes(createXPathExpression(beforeCursorExp)));
            } catch (XPathExpressionException e) {
                ExceptionHandler.process(e);
            }
        }
        // System.out.println("nodeList.size()="+nodeList.size());

        if (nodeList != null) {
            Set<String> alreadyAdded = new HashSet<String>();
            int nodeListLength = nodeList.size();
            for (int j = 0; j < nodeListLength; ++j) {
                Node node = nodeList.get(j);
                String nodeName = node.getNodeName();
                String absoluteXPathFromNode = NodeRetriever.getAbsoluteXPathFromNode(node);
                if ((currentWord.length() > 0 && nodeName.startsWith(currentWord) || currentWord.length() == 0 || currentWord.equals("/"))
                        && !alreadyAdded.contains(absoluteXPathFromNode)) {
                    // System.out.println(absoluteXPathFromNode);
                    XPathContentProposal contentProposal = new XPathContentProposal(node);
                    if (isRelative && expressionIsRelative) {
                        contentProposal.setRelative(isRelative);
                        contentProposal.setFirstRelativeNode(contents.indexOf(SLASH) == -1);
                    }
                    proposals.add(contentProposal);
                    alreadyAdded.add(absoluteXPathFromNode);
                }
            }
        }

        IContentProposal[] res = new IContentProposal[proposals.size()];
        res = proposals.toArray(res);
        return res;
    }

    /**
     * DOC amaumont Comment method "extractLastWord".
     * 
     * @param currentExpr
     * @return
     */
    private String extractLastWord(String currentExpr) {
        int size = currentExpr.length();
        for (int i = size - 1; i > 0; i--) {
            if (!("" + currentExpr.charAt(i)).matches("\\w")) {
                return currentExpr.substring(i + 1, currentExpr.length());
            }
        }
        return currentExpr.substring(0, size);
    }

    /**
     * DOC amaumont Comment method "createXPathExpression".
     * 
     * @param slash
     * @param currentExpr
     * @return
     */
    private String createXPathExpression(String currentExpr) {
        String xPathExpression;
        String slash = SLASH;
        if (currentExpr.endsWith(SLASH)) {
            slash = EMPTY_STRING;
        }

        xPathExpression = currentExpr + slash + "*" + " | " + currentExpr + slash + "@*";
        // System.out.println("xPathExpression='"+xPathExpression+"'");
        return xPathExpression;
    }

    /**
     * Getter for otherContentProposalProviders.
     * 
     * @return the otherContentProposalProviders
     */
    public IContentProposalProvider[] getOtherContentProposalProviders() {
        return this.otherContentProposalProviders;
    }

    /**
     * Sets the otherContentProposalProviders.
     * 
     * @param otherContentProposalProviders the otherContentProposalProviders to set
     */
    public void setOtherContentProposalProviders(IContentProposalProvider[] otherContentProposalProviders) {
        this.otherContentProposalProviders = otherContentProposalProviders;
    }

}
