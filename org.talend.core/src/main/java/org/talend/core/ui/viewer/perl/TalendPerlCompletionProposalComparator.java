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
package org.talend.core.ui.viewer.perl;

import org.epic.perleditor.templates.perl.PerlCompletionProposalComparator;
import org.talend.core.ui.viewer.proposal.TalendCompletionProposal;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendPerlCompletionProposalComparator extends PerlCompletionProposalComparator {

    /*
     * (non-Javadoc)
     * 
     * @see org.epic.perleditor.templates.perl.PerlCompletionProposalComparator#compare(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof TalendCompletionProposal) {
            return 1;
        }
        return super.compare(o1, o2);
    }

}
