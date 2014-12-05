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
 * created by ggu on Nov 10, 2014 Detailled comment
 *
 * The integer value of "service.ranking" is set by 10. And make sure the ActivatePerspectiveStartupMonitor will be
 * after this. and set it 5.
 */
public class RestoreAllRegisteredPerspectivesStartupMonitor implements StartupMonitor {

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
        RestoreAllRegisteredPerspectivesProvider perspProvider = new RestoreAllRegisteredPerspectivesProvider();

        IWorkbench workbench = PlatformUI.getWorkbench();
        IEclipseContext activeContext = ((IEclipseContext) workbench.getService(IEclipseContext.class)).getActiveLeaf();

        ContextInjectionFactory.inject(perspProvider, activeContext);

        perspProvider.restoreAlwaysVisiblePerspectives();

    }
}
