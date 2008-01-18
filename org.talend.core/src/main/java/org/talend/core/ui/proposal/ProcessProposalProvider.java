// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
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
        // add proposals on global variables in java (bugtracker 2554)
        switch (LanguageManager.getCurrentLanguage()) {
        case JAVA:
            // add variables in java
            IContentProposal[] javavars = JavaGlobalUtils.getProposals();
            for (int i = 0; i < javavars.length; i++) {
                proposals.add(javavars[i]);
            }
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

        for (IExternalProposals externalProposals : ProposalFactory.getInstances()) {
            proposals.addAll(externalProposals.getStandardProposals());
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
