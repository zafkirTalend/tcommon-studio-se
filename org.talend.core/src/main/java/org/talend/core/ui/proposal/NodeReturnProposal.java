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
package org.talend.core.ui.proposal;

import java.text.MessageFormat;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeReturn;

/**
 * Content proposal based on a INodeReturn. <br/>
 * 
 * $Id$
 * 
 */
public class NodeReturnProposal implements IContentProposal {

    private INode node;

    private INodeReturn nodeReturn;

    /**
     * Constructs a new NodeReturnProposal.
     */
    public NodeReturnProposal(INode node, INodeReturn nodeReturn) {
        super();

        this.node = node;
        this.nodeReturn = nodeReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
     */
    public String getContent() {
        return ElementParameterParser.parse(node, nodeReturn.getVarName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
     */
    public int getCursorPosition() {
        return getContent().length();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
     */
    public String getDescription() {
        String message = "Description: {0}";
        message += "\n\nGlobal variable, property of component {1} [{2}].";
        message += "\nType: {3}";
        message += "\nAvailability: {4}";
        message += "\n\nVariable Name: {5}";

        MessageFormat format = new MessageFormat(message);
        Object[] args = new Object[] { nodeReturn.getDisplayName(), node.getComponentName(), node.getUniqueName(),
                nodeReturn.getType().getDisplayName(), nodeReturn.getAvailability(), getContent() };
        return format.format(args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    public String getLabel() {
        return node.getUniqueName() + "." + nodeReturn.getName();
    }

}
