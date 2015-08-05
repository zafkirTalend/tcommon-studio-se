// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.proposal;

import java.text.MessageFormat;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.i18n.Messages;
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
        String message = Messages.getString("NodeReturnProposal.Description"); //$NON-NLS-1$
        message += Messages.getString("NodeReturnProposal.GlobalVariable"); //$NON-NLS-1$
        message += Messages.getString("NodeReturnProposal.Type"); //$NON-NLS-1$
        message += Messages.getString("NodeReturnProposal.Availability"); //$NON-NLS-1$
        message += Messages.getString("NodeReturnProposal.VariableName"); //$NON-NLS-1$

        MessageFormat format = new MessageFormat(message);
        Object[] args = new Object[] { nodeReturn.getDisplayName(), node.getComponent().getName(), node.getLabel(),
                nodeReturn.getDisplayType(), nodeReturn.getAvailability(), getContent() };
        return format.format(args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    public String getLabel() {
        return node.getLabel() + "." + nodeReturn.getName(); //$NON-NLS-1$
    }

}
