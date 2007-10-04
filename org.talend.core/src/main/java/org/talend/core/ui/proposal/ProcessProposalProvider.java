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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeReturn;
import org.talend.core.model.process.IProcess;
import org.talend.designer.rowgenerator.data.Function;
import org.talend.designer.rowgenerator.data.FunctionManager;
import org.talend.designer.rowgenerator.data.TalendType;

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

        int initialPosition = position;

        if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.JAVA)) {

            int end = position;
            int start = end;
            while (--start >= 0) {
                if (!Character.isJavaIdentifierPart(contents.charAt(start))) {
                    break;
                }
            }
            start++;
            initialPosition = start;
        }

        System.out.println("content:" + contents + " / position:" + position + " / initialPosition:" + initialPosition);

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

        // Proposals based on global variables(only perl ).
        switch (LanguageManager.getCurrentLanguage()) {
        case JAVA:
            // do nothing
            break;
        case PERL:
        default:
            IContentProposal[] vars = PerlGlobalUtils.getProposals();
            for (int i = 0; i < vars.length; i++) {
                proposals.add(vars[i]);
            }
        }

        // Proposals based on routines
        FunctionManager functionManager = new FunctionManager();

        List<TalendType> talendTypes = functionManager.getTalendTypes();
        for (TalendType type : talendTypes) {
            for (Object objectFunction : type.getFunctions()) {
                Function function = (Function) objectFunction;
                proposals.add(new RoutinesFunctionProposal(function));
            }
        }
        // sort the list
        Collections.sort(proposals, new Comparator<IContentProposal>() {

            public int compare(IContentProposal arg0, IContentProposal arg1) {
                return arg0.getLabel().compareToIgnoreCase(arg1.getLabel());
            }

        });

        IContentProposal[] res = new IContentProposal[proposals.size()];
        res = proposals.toArray(res);
        return res;
    }

}
