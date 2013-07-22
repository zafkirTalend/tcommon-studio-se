// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
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

import org.eclipse.jface.fieldassist.IContentProposal;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.ui.snippet.SnippetCompletionProposal;
import org.talend.core.ui.snippet.SnippetManager;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 */
public class SnippetFunctionProposal implements IContentProposal {

    SnippetItem snippet;

    private String content;

    /**
     * DOC bqian RoutinesProposal constructor comment.
     * 
     * @param function
     */
    public SnippetFunctionProposal(SnippetItem snippet) {
        this.snippet = snippet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
     */
    @Override
    public String getContent() {
        SnippetManager manager = new SnippetManager();

        content = manager.applySnippet(snippet, null);
        return content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
     */
    @Override
    public int getCursorPosition() {
        return content.length();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
     */
    @Override
    public String getDescription() {
        String des = SnippetCompletionProposal.getSnippetDescription(snippet);
        return des.replaceAll("<br>", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    @Override
    public String getLabel() {
        return snippet.getProperty().getLabel();
    }
}
