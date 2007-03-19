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
package org.talend.core.ui.perspective;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.talend.core.i18n.Messages;

/**
 * Menu manager for perspective switching. <br/>
 * 
 * $Id: PerspectiveMenuManager.java 1774 2007-02-03 02:05:47 +0000 (星期六, 03 二月 2007) bqian $
 * 
 */
public class PerspectiveMenuManager extends MenuManager {
    
    private static String[] perspectiveIds;
    
    static {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] talendPpElements = registry
                .getExtensionPoint("org.talend.core.talendperspectives").getConfigurationElements(); //$NON-NLS-1$
        perspectiveIds = getTalendPerspectives(talendPpElements);

    } 
    
    /**
     * Constructs a new PerspectiveMenuManager.
     */
    public PerspectiveMenuManager() {
        super(Messages.getString("PerspectiveMenuManager.perspectiveLabel"), "perspective"); //$NON-NLS-1$ //$NON-NLS-2$
        
        addMenuListener(new MenuFiller());
        
        setRemoveAllWhenShown(true);
        add(new ChangePerspectiveAction(Messages.getString("PerspectiveMenuManager.dummy"))); //$NON-NLS-1$
    }
    
    public static String[] getTalendPerspectives(IConfigurationElement[] talendPpElements) {
        String talendPpId = null;
        List<String> talendPpList = new ArrayList<String>();
        for (int i = 0; i < talendPpElements.length; i++) {
            talendPpId = talendPpElements[i].getAttribute("refPerspectiveId"); //$NON-NLS-1$
            if (talendPpId == null) {
                continue;
            } else {
                talendPpList.add(talendPpId);
            }
        }
        return talendPpList.toArray(new String[talendPpList.size()]);
    }

    /**
     * Fills the perspective menu. <br/>
     * 
     * $Id: PerspectiveMenuManager.java 1774 2007-02-03 02:05:47 +0000 (星期六, 03 二月 2007) bqian $
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
            
            for (String perspId : perspectiveIds) {
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
