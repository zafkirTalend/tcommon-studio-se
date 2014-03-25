// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.INode;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public class PerlDynamicProposalUtil {

    /**
     * 
     * DOC YeXiaowei Comment method "addDynamicProposals".
     * 
     * @param proposals
     * @param process
     */
    public static List<IContentProposal> createDynamicProposals(final INode node) {

        if (node == null) {
            return null;
        }

        List<IContentProposal> proposals = new ArrayList<IContentProposal>();

        // Add specifical prososal see feature:3725
        IContentProposal[] varsAssists = getRowColumnsProposals(node);

        if (varsAssists == null) {
            return null;
        }

        for (int i = 0; i < varsAssists.length; i++) {
            if (!proposals.contains(varsAssists[i])) {
                proposals.add(varsAssists[i]);
            }
        }

        return proposals;
    }

    private static IContentProposal[] getRowColumnsProposals(final INode node) {
        List<IMetadataTable> metaTables = node.getMetadataList();
        if (metaTables != null && !metaTables.isEmpty()) {
            IMetadataTable table = metaTables.get(0);// iterater all or just iterater first one?
            if (table != null) {
                List<IMetadataColumn> columns = table.getListColumns();
                if (columns != null && !columns.isEmpty()) {
                    IContentProposal[] res = new IContentProposal[columns.size()];
                    for (int i = 0; i < columns.size(); i++) {
                        IMetadataColumn column = columns.get(i);
                        StringBuilder proposal = new StringBuilder();
                        proposal.append("$row["); //$NON-NLS-1$
                        proposal.append(column.getLabel());
                        proposal.append("]"); //$NON-NLS-1$
                        res[i] = makeContentProposal(proposal.toString(), proposal.toString(), proposal.toString()
                                + Messages.getString("PerlDynamicProposalUtil.autoConvert", node.getUniqueName())); //$NON-NLS-1$
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
    private static IContentProposal makeContentProposal(final String proposal, final String label, final String description) {
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
