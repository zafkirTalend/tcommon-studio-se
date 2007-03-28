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
package org.talend.commons.ui.swt.advanced.dataeditor.button;

import org.eclipse.swt.widgets.Composite;
import org.talend.commons.i18n.internal.Messages;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedControlViewer;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class RemovePushButton extends ExtendedPushButton {

    /**
     * DOC amaumont AddPushButton constructor comment.
     * 
     * @param parent
     * @param tooltip
     * @param image
     */
    public RemovePushButton(Composite parent, AbstractExtendedControlViewer extendedControlViewer) {
        super(parent, extendedControlViewer, Messages.getString("RemovePushButton.RemoveButton.Tip"), ImageProvider.getImage(EImage.DELETE_ICON)); //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton#getEnabledState()
     */
    @Override
    public boolean getEnabledState() {
        return super.getEnabledState() && !getExtendedControlViewer().isReadOnly();
    }

}
