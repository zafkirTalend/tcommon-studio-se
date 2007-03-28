// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeReturn;
import org.talend.core.model.process.IProcess;

/**
 * ContentProposalProvider based on a Process. <br/>
 * 
 * $Id$
 * 
 */
public class ProcessProposalProvider implements IContentProposalProvider {

    private IProcess process;

    /**
     * Constructs a new ProcessProposalProvider.
     */
    public ProcessProposalProvider(IProcess process) {
        super();

        this.process = process;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
     */
    public IContentProposal[] getProposals(String contents, int position) {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();

        // Proposals based on process context
        List<IContextParameter> ctxParams = process.getContextManager().getDefaultContext().getContextParameterList();
        for (IContextParameter ctxParam : ctxParams) {
            proposals.add(new ContextParameterProposal(ctxParam));
        }

        // Proposals based on global variables
        List<? extends INode> nodes = process.getGraphicalNodes();
        for (INode node : nodes) {
            List<? extends INodeReturn> nodeReturns = node.getReturns();
            for (INodeReturn nodeReturn : nodeReturns) {
                proposals.add(new NodeReturnProposal(node, nodeReturn));
            }
        }

        IContentProposal[] res = new IContentProposal[proposals.size()];
        res = proposals.toArray(res);
        return res;
    }

}
