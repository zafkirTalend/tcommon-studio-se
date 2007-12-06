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
package org.talend.repository.ui.proposals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.snippets.SnippetManager;
import org.talend.core.ui.images.ECoreImage;
import org.talend.core.ui.proposal.IExternalProposals;
import org.talend.core.ui.proposal.SnippetFunctionProposal;
import org.talend.core.ui.snippet.SnippetCompletionProposal;
import org.talend.core.ui.viewer.proposal.TalendCompletionProposal;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class SnippetslProposals implements IExternalProposals {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.proposal.IExternalProposals#getStandardProposals()
     */
    public List<IContentProposal> getStandardProposals() {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();

        // add snippets
        SnippetManager snippetManager = new SnippetManager();
        for (SnippetItem snippet : snippetManager.getListSnippet()) {
            proposals.add(new SnippetFunctionProposal(snippet));
        }
        return proposals;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.proposal.IExternalProposals#getAdvancedProposals()
     */
    public List<ICompletionProposal> getAdvancedProposals(int offset, String prefix) {
        List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
        // add snippets
        SnippetManager snippetManager = new SnippetManager();
        for (SnippetItem snippet : snippetManager.getListSnippet()) {
            String display = snippet.getProperty().getLabel();
            if (prefix.equals("") || display.startsWith(prefix)) {
                String description = "Snippet description";
                SnippetCompletionProposal proposal = new SnippetCompletionProposal(snippet, offset, 0, ImageProvider
                        .getImage(ECoreImage.SNIPPETS_ICON), display, null, description);
                proposal.setType(TalendCompletionProposal.SNIPPET);
                proposals.add(proposal);
            }
        }
        return proposals;
    }

}
