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
package org.talend.librariesmanager.ui.actions;

import org.eclipse.jface.action.Action;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.CorePlugin;
import org.talend.librariesmanager.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: CheckModulesAction.java 1811 2007-02-05 03:29:11Z qzhang $
 * 
 */
public class CheckModulesAction extends Action {

    public CheckModulesAction() {
        super();
        setText(Messages.getString("CheckModulesAction.CheckRefrshBtn.Text")); //$NON-NLS-1$
        setToolTipText(Messages.getString("CheckModulesAction.CheckRefrshBtn.Text")); //$NON-NLS-1$
        setImageDescriptor(ImageProvider.getImageDesc(EImage.REFRESH_ICON));
        this.setActionDefinitionId("refreshModules");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        CorePlugin.getDefault().getLibrariesService().checkLibraries();
    }

}
