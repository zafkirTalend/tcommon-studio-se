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

import org.eclipse.jface.fieldassist.IContentProposal;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * Content proposal based on a IContextParameter. <br/>
 * 
 * $Id: EntryContentProposal.java 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class XPathContentProposal implements IContentProposal {

    private String content;

    private Node node;

    private boolean relative;

    private boolean firstRelativeNode;

    private String comment;

    /**
     * Constructs a new ContextParameterProposal.
     * 
     * @param node
     */
    public XPathContentProposal(Node node) {
        super();
        this.node = node;
    }

    /**
     * Constructs a new ContextParameterProposal.
     * 
     * @param node
     */
    public XPathContentProposal(String comment) {
        super();
        this.comment = comment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
     */
    public String getContent() {
        if (comment != null) {
            content = "";
        } else {
            content = getLabel();
        }
        return content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
     */
    public int getCursorPosition() {
        return content.length() + 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
     */
    public String getDescription() {
//        StringBuilder sb = new StringBuilder();
        return null;
    }

    public String format(Object object) {
        if (object == null) {
            return "";
        }
        return String.valueOf(object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    public String getLabel() {
        if (comment != null) {
            return comment;
        }

        String label = "";
        if (relative && firstRelativeNode) {
            // nothing
        } else {
            label = "/";
        }

        if (this.node instanceof Attr) {
            label = label + "@" + this.node.getNodeName();
        } else {
            label = label + this.node.getNodeName();
        }
        return label;
    }

    /**
     * Getter for relative.
     * 
     * @return the relative
     */
    public boolean isRelative() {
        return this.relative;
    }

    /**
     * Sets the relative.
     * 
     * @param relative the relative to set
     */
    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    /**
     * Getter for startOfExpression.
     * 
     * @return the startOfExpression
     */
    public boolean isFirstRelativeNode() {
        return this.firstRelativeNode;
    }

    /**
     * Sets the startOfExpression.
     * 
     * @param startOfExpression the startOfExpression to set
     */
    public void setFirstRelativeNode(boolean startOfExpression) {
        this.firstRelativeNode = startOfExpression;
    }

}
