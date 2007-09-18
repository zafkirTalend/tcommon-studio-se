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
package org.talend.core.ui.viewer.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProcessor;
import org.eclipse.jdt.internal.ui.text.java.JavaMethodCompletionProposal;
import org.eclipse.jdt.internal.ui.text.java.LazyJavaTypeCompletionProposal;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.ui.IEditorPart;
import org.talend.core.ui.viewer.proposal.TalendCompletionProposal;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendJavaCompletionProcessor extends JavaCompletionProcessor {

    public TalendJavaCompletionProcessor(IEditorPart editor, ContentAssistant assistant, String partition) {
        super(editor, assistant, partition);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.internal.ui.text.java.JavaCompletionProcessor#filterAndSortProposals(java.util.List,
     * org.eclipse.core.runtime.IProgressMonitor, org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext)
     */
    @Override
    protected List filterAndSortProposals(List proposals, IProgressMonitor monitor, ContentAssistInvocationContext context) {
        List newProposals = super.filterAndSortProposals(proposals, monitor, context);
        List toRemove = new ArrayList();
        try {
            for (Object o : newProposals) {
                ICompletionProposal proposal = (ICompletionProposal) o;
                if (context.getDocument().getChar(context.getInvocationOffset() - 1) != '.') {
                    if (proposal instanceof JavaMethodCompletionProposal) {
                        toRemove.add(proposal);
                    }
                }
                if (proposal.getDisplayString().startsWith("myFunction")) {
                    toRemove.add(proposal);
                }
                if (proposal instanceof LazyJavaTypeCompletionProposal) {
                    if (((LazyJavaTypeCompletionProposal) proposal).getReplacementString().equals(
                            TalendJavaSourceViewer.VIEWER_CLASS_NAME)) {
                        toRemove.add(proposal);
                    }
                }
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        newProposals.removeAll(toRemove);
        Collections.sort(newProposals, new Comparator() {

            public int compare(Object arg0, Object arg1) {
                if (arg1 instanceof TalendCompletionProposal) {
                    return 1;
                }
                return 0;
            }

        });
        return newProposals;
    }

}
