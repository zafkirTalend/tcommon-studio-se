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
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeReturn;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ContextItem;
import org.talend.designer.rowgenerator.data.Function;
import org.talend.designer.rowgenerator.data.FunctionManager;
import org.talend.designer.rowgenerator.data.TalendType;

/**
 * ContentProposalProvider based on a Process. <br/>
 * 
 * $Id$
 * 
 */
public class TalendProposalProvider implements IContentProposalProvider {

    private IProcess process;

    /**
     * Constructs a new ProcessProposalProvider.
     */
    public TalendProposalProvider(IProcess process) {
        super();
        this.process = process;
    }

    /**
     * yzhang ProcessProposalProvider constructor comment.
     */
    public TalendProposalProvider() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
     */
    public IContentProposal[] getProposals(String contents, int position) {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();

        if (process != null) {
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

        } else {
            List<ContextItem> allContextItem = ContextUtils.getAllContextItem();
            List<IContextParameter> ctxParams = new ArrayList<IContextParameter>();
            if (allContextItem != null) {
                for (ContextItem item : allContextItem) {
                    List<IContextParameter> tmpParams = new JobContextManager(item.getContext(), item.getDefaultContext())
                            .getDefaultContext().getContextParameterList();
                    ctxParams.addAll(tmpParams);
                }
            }
            for (IContextParameter ctxParam : ctxParams) {
                proposals.add(new ContextParameterProposal(ctxParam));
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

            // Add specifical prososal see feature:3725
            List<? extends INode> nodes = process.getGraphicalNodes();
            for (INode node : nodes) {

                if (!node.getUniqueName().startsWith("tMsgBox_")) {
                    continue;
                }

                IContentProposal[] varsAssists = getRowColumnsProposals(node);

                if (varsAssists == null) {
                    continue;
                }

                for (int i = 0; i < varsAssists.length; i++) {
                    if (!proposals.contains(varsAssists[i])) {
                        proposals.add(varsAssists[i]);
                    }
                }
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

    private IContentProposal[] getRowColumnsProposals(final INode node) {
        List<IMetadataTable> metaTables = node.getMetadataList();
        if (metaTables != null) {
            IMetadataTable table = metaTables.get(0);// iterater all or just iterater first one?
            if (table != null) {
                List<IMetadataColumn> columns = table.getListColumns();
                if (columns != null && !columns.isEmpty()) {
                    IContentProposal[] res = new IContentProposal[columns.size()];
                    for (int i = 0; i < columns.size(); i++) {
                        IMetadataColumn column = columns.get(i);
                        StringBuilder proposal = new StringBuilder();
                        proposal.append("$row[");
                        proposal.append(column.getLabel());
                        proposal.append("]");
                        res[i] = makeContentProposal(proposal.toString(), node.getUniqueName() + "." + proposal.toString(),
                                proposal.toString() + " would be automatically converted to " + node.getUniqueName()
                                        + "->[index] in the generated code");
                    }
                    return res;
                }
            }
        }

        return null;
    }

    /*
     * Make an IContentProposal for showing the specified String.
     */
    private IContentProposal makeContentProposal(final String proposal, final String label, final String description) {
        return new IContentProposal() {

            public String getContent() {
                return proposal;
            }

            public String getDescription() {
                return description;
            }

            public String getLabel() {
                return label;
            }

            public int getCursorPosition() {
                return proposal.length();
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#hashCode()
             */
            @Override
            public int hashCode() {
                return getLabel().hashCode() ^ getDescription().hashCode() ^ getContent().hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof IContentProposal)) {
                    return false;
                }
                IContentProposal proposal = (IContentProposal) obj;

                return proposal.getContent().equals(getContent()) && proposal.getLabel().equals(getLabel())
                        && proposal.getDescription().equals(getDescription());
            }

        };
    }
}
