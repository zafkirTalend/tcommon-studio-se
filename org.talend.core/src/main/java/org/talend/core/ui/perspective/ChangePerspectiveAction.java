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
package org.talend.core.ui.perspective;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.talend.core.CorePlugin;

/**
 * Changes the active perspective. <br/>
 * 
 * $Id: ChangePerspectiveAction.java 1774 2007-02-03 02:05:47 +0000 (Sat, 03 Feb 2007) bqian $
 * 
 */
public class ChangePerspectiveAction extends Action {

    /** Id of the perspective to move to front. */
    private String perspectiveId;

    /**
     * Constructs a new ChangePerspectiveAction.
     */
    public ChangePerspectiveAction(String perspectiveId) {
        super(perspectiveId, AS_CHECK_BOX);

        this.perspectiveId = perspectiveId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
        if (!perspectiveId.equals(page.getPerspective().getId())) {
            try {
                workbench.showPerspective(perspectiveId, workbench.getActiveWorkbenchWindow());
            } catch (WorkbenchException e) {
                IStatus status = new Status(IStatus.ERROR, CorePlugin.PLUGIN_ID, IStatus.OK, "Show perspective failed.", e); //$NON-NLS-1$
                CorePlugin.getDefault().getLog().log(status);
            }
        }
    }
}
