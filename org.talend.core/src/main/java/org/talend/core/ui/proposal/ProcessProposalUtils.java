// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.core.ui.proposal;

import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.swt.widgets.Control;
import org.talend.commons.ui.swt.proposal.ContentProposalAdapterExtended;
import org.talend.commons.ui.swt.proposal.ProposalUtils;
import org.talend.core.model.process.IProcess;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class ProcessProposalUtils {

    /**
     * Installs a proposal provider on a text component based on a process.
     * 
     * @param text Text component on wich proposals are installed.
     * @param process Process from wich proposals are built.
     */
    public static ContentProposalAdapterExtended installOn(Control text, IProcess process) {
        IContentProposalProvider proposalProvider = new ProcessProposalProvider(process);
        return ProposalUtils.getCommonProposal(text, proposalProvider);
    }


}
