// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.ui.swt.proposal;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.i18n.internal.Messages;

/**
 * Utilities for proposals. <br/>
 * 
 * $Id$
 * 
 */
public final class ProposalUtils {

    /**
     * Constructs a new ProposalUtils.
     */
    private ProposalUtils() {
    }

    public static ContentProposalAdapterExtended getCommonProposal(Control control, IContentProposalProvider proposalProvider) {
        IControlContentAdapter controlContentAdapter = null;
        if (control instanceof Text) {
            controlContentAdapter = new TextContentAdapterExtended();
        } else if (control instanceof StyledText) {
            controlContentAdapter = new StyledTextContentAdapterExtended();
        } else {
            throw new IllegalArgumentException(Messages.getString("ProposalUtils.CtrlProposal.ErrorMsg") + control.getClass()); //$NON-NLS-1$
        }
        final ContentProposalAdapterExtended contentProposalAdapter = getContentProposalAdapter(control, controlContentAdapter,
                proposalProvider);

        // to don't write Carriage Return when accept a proposal
        if (control instanceof StyledText) {
            final VerifyKeyListener verifyKeyListener = new VerifyKeyListener() {

                public void verifyKey(VerifyEvent verifyEvent) {
                    if (verifyEvent.character == '\r' && contentProposalAdapter.isProposalOpened()) {
                        verifyEvent.doit = false;
                    } else {
                        verifyEvent.doit = true;
                    }
                }
            };
            ((StyledText) control).addVerifyKeyListener(verifyKeyListener);
        }

        return contentProposalAdapter;
    }

    private static ContentProposalAdapterExtended getContentProposalAdapter(Control control, IControlContentAdapter controlContentAdapter) {
        return getContentProposalAdapter(control, controlContentAdapter, null);
    }

    private static ContentProposalAdapterExtended getContentProposalAdapter(Control control, IControlContentAdapter controlContentAdapter,
            IContentProposalProvider proposalProvider) {
        ContentProposalAdapterExtended contentProposalAdapter = null;
        try {
            KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+Space");

            contentProposalAdapter = new ContentProposalAdapterExtended(control, controlContentAdapter, proposalProvider, keyStroke, null);
            contentProposalAdapter.setPropagateKeys(true);
            contentProposalAdapter.setFilterStyle(ContentProposalAdapterExtended.FILTER_CUMULATIVE);
            contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapterExtended.PROPOSAL_INSERT);
        } catch (ParseException pe) {
            // No proposal will be available, sorry
            // IStatus status = new Status(IStatus.WARNING, "org.talend.designer", IStatus.OK, "Proposal installation
            // failed.", pe);
            // CorePlugin.getDefault().getLog().log(status);
            throw new RuntimeException(pe);
        }
        return contentProposalAdapter;
    }

    public static ContentProposalAdapterExtended getCommonProposal(Control control) {
        return getCommonProposal(control, null);
    }

    public static ContentProposalAdapterExtended getCommonProposal(CellEditor cellEditor) {
        if (cellEditor instanceof TextCellEditorWithProposal) {
            return getContentProposalAdapter(cellEditor.getControl(), new TextCellEditorContentAdapterExtended());
        } else {
            throw new IllegalArgumentException(Messages.getString("ProposalUtils.CellProposal.Error") + cellEditor.getClass()); //$NON-NLS-1$
        }
    }

}
