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
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.osgi.service.runnable.StartupMonitor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
 * created by ggu on Nov 10, 2014 Detailled comment
 *
 * The integer value of "service.ranking" is set by 10. And make sure the RestorePerspectiveStartupMonitor will be after
 * this. and set it 5.
 */
public class ShowPerspectivesAtStartupMonitor implements StartupMonitor {

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
    @SuppressWarnings("restriction")
    @Override
    public void applicationRunning() {
        if (!PlatformUI.isWorkbenchRunning()) { // if not running, nothing to do.
            return;
        }
        ShowPerspectivesAtStarupProvider perspProvider = new ShowPerspectivesAtStarupProvider();

        IEclipseContext context = null;

        IWorkbench workbench = PlatformUI.getWorkbench();
        context = (IEclipseContext) workbench.getService(IEclipseContext.class);

        if (context != null) {
            IEclipseContext activeContext = null;

            /*
             * NOTE: If it's first time to open studio, this active context is not ok, and will get the exception:
             * org.eclipse.e4.core.di.InjectionException: Unable to process "ShowPerspectivesAtStarupProvider.fWindow":
             * no actual value was found for the argument "MWindow".
             */
            activeContext = context.getActiveLeaf();

            // FIXME: Then try the following resolution.

            /*
             * Can't work, will return null for the method getMPerspectiveStack of class
             * ShowPerspectivesAtStarupProvider
             */
            // MApplication mApplication = context.get(MApplication.class);
            // if (mApplication != null) {
            // // copied the codes from method getActiveWorkbenchWindow of class Workbench
            // MWindow activeWindow = mApplication.getSelectedElement();
            // if (activeWindow == null && !mApplication.getChildren().isEmpty()) {
            // activeWindow = mApplication.getChildren().get(0);
            // }
            // if (activeWindow != null) {
            // activeContext = activeWindow.getContext();
            // }
            // }

            /*
             * Maybe, Shouldn't use the internal class. Should find other way to get the right context.
             * 
             * When have the exception for "MWindow", try to find from active WorkbenchWindow, will be ok always, even
             * opening studio first time.
             */
            IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null && activeWorkbenchWindow instanceof WorkbenchWindow) {
                MWindow mWindow = ((WorkbenchWindow) activeWorkbenchWindow).getModel();
                IEclipseContext mContext = mWindow.getContext();
                // even use mContext directly, and they are different, also work well.
                activeContext = mContext.getActiveLeaf();
            }

            ContextInjectionFactory.inject(perspProvider, activeContext);

            perspProvider.checkPerspectiveDisplayItems();
        }

    }
}
