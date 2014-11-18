// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.perspective;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.osgi.service.runnable.StartupMonitor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * created by ggu on 12 nov. 2014 Detailled comment
 *
 * The integer value of "service.ranking" is set by 5, this restoring should be lower than the
 * RestoreAllRegisteredPerspectivesStartupMonitor.
 */
public class ActivatePerspectiveStartupMonitor implements StartupMonitor {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.service.runnable.StartupMonitor#update()
     */
    @Override
    public void update() {
        //
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.osgi.service.runnable.StartupMonitor#applicationRunning()
     */
    @Override
    public void applicationRunning() {
        if (!PlatformUI.isWorkbenchRunning()) { // if not running, nothing to do.
            return;
        }
        ActivatePerspectiveProvider restorePerspProvider = new ActivatePerspectiveProvider();

        IWorkbench workbench = PlatformUI.getWorkbench();
        IEclipseContext activeContext = ((IEclipseContext) workbench.getService(IEclipseContext.class)).getActiveLeaf();
        /*
         * FIXME, seem this active context is different with the RestoreAllRegisteredPerspectivesStartupMonitor, so
         * there is no problem here. strange
         */

        // IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
        // if (activeWorkbenchWindow != null && activeWorkbenchWindow instanceof WorkbenchWindow) {
        // MWindow mWindow = ((WorkbenchWindow) activeWorkbenchWindow).getModel();
        // IEclipseContext mContext = mWindow.getContext();
        // // even use mContext directly, and they are different, also work well.
        // activeContext = mContext.getActiveLeaf();
        // }

        ContextInjectionFactory.inject(restorePerspProvider, activeContext);

        restorePerspProvider.restore();
    }

}
