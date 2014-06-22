// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.snippet;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.i18n.Messages;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.SnippetVariable;
import org.talend.core.model.snippets.SnippetManager;
import org.talend.core.ui.viewer.proposal.TalendCompletionProposal;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class SnippetCompletionProposal extends TalendCompletionProposal {

    private SnippetItem snippet;

    private String content;

    /**
     * DOC bqian SnippetCompletionProposal constructor comment.
     * 
     * @param replacementString
     * @param replacementOffset
     * @param replacementLength
     * @param cursorPosition
     * @param image
     * @param displayString
     * @param contextInformation
     * @param additionalProposalInfo
     */
    public SnippetCompletionProposal(SnippetItem snippet, int replacementOffset, int replacementLength, Image image,
            String displayString, IContextInformation contextInformation, String additionalProposalInfo) {
        super("", replacementOffset, replacementLength, 0, image, displayString, contextInformation, //$NON-NLS-1$
                additionalProposalInfo);
        this.snippet = snippet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.proposal.TalendCompletionProposal#apply(org.eclipse.jface.text.IDocument)
     */
    @Override
    public void apply(IDocument document) {
        SnippetManager manager = new SnippetManager();

        content = manager.applySnippet(snippet, null);
        try {
            document.replace(fReplacementOffset, fReplacementLength, content);
        } catch (BadLocationException e) {
            ExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.proposal.TalendCompletionProposal#getAdditionalProposalInfo()
     */
    @Override
    public String getAdditionalProposalInfo() {
        return getSnippetDescription(snippet);
    }

    public static String getSnippetDescription(SnippetItem snippet) {
        String msg = Messages.getString("RoutinesFunctionProposal.Description") + "{0}<br>" + "Label:" + "{1}<br><br>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        MessageFormat format = new MessageFormat(msg);
        Object[] args = new Object[] { snippet.getProperty().getDescription(), snippet.getProperty().getLabel() }; //$NON-NLS-1$
        msg = format.format(args);

        StringBuilder sb = new StringBuilder(msg);
        sb.append("Variables:<br>"); //$NON-NLS-1$

        List<SnippetVariable> vars = (List<SnippetVariable>) snippet.getVariables();
        for (SnippetVariable variable : vars) {
            sb.append(variable.getName() + " = " + variable.getValue()).append("<br>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        sb.append("<br>Content:<br>").append(snippet.getContent()); //$NON-NLS-1$
        // sb.append("<table border=\"true\">");
        // sb.append("<tr><td>Name</td><td>Value</td></tr>");
        // for (SnippetVariable variable : vars) {
        // sb.append("<tr>").append("<td>").append(variable.getName()).append("</td>").append("<td>")
        // .append(variable.getValue()).append("</td>").append("</tr>");
        // }
        // sb.append("</table>");
        //
        // sb.append("Content:<br>").append(snippet.getContent());

        //$NON-NLS-2$

        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.proposal.TalendCompletionProposal#getSelection(org.eclipse.jface.text.IDocument)
     */
    @Override
    public Point getSelection(IDocument document) {
        return new Point(fReplacementOffset + content.length(), 0);
    }

}
