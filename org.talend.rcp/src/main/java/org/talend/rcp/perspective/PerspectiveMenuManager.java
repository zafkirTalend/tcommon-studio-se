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
package org.talend.rcp.perspective;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.talend.rcp.i18n.Messages;

/**
 * Menu manager for perspective switching. <br/>
 * 
 * $Id$
 * 
 */
public class PerspectiveMenuManager extends MenuManager {
    
    private static final String[] PERSPECTIVE_IDS = new String[] {"org.talend.rcp.perspective", "org.eclipse.debug.ui.DebugPerspective"}; //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * Constructs a new PerspectiveMenuManager.
     */
    public PerspectiveMenuManager() {
        super(Messages.getString("PerspectiveMenuManager.perspectiveLabel"), "perspective"); //$NON-NLS-1$ //$NON-NLS-2$
        
        addMenuListener(new MenuFiller());
        
        setRemoveAllWhenShown(true);
        add(new ChangePerspectiveAction(Messages.getString("PerspectiveMenuManager.dummy"))); //$NON-NLS-1$
    }

    /**
     * Fills the perspective menu.
     * <br/>
     *
     * $Id$
     *
     */
    private static class MenuFiller implements IMenuListener {
        /**
         * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
         */
        public void menuAboutToShow(IMenuManager manager) {
            IPerspectiveRegistry registry = PlatformUI.getWorkbench().getPerspectiveRegistry();
            
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            String activePersp = page.getPerspective().getId();
            
            for (String perspId : PERSPECTIVE_IDS) {
                // Search perspective name & icon
                IPerspectiveDescriptor desc = registry.findPerspectiveWithId(perspId);
                if (desc != null) {
                    ChangePerspectiveAction perspAction = new ChangePerspectiveAction(perspId);
                    perspAction.setText(desc.getLabel());
                    perspAction.setToolTipText(desc.getDescription());
                    perspAction.setImageDescriptor(desc.getImageDescriptor());
                    perspAction.setChecked(desc.getId().equals(activePersp));
                
                    manager.add(perspAction);
                }
            }
        }
    }
}
