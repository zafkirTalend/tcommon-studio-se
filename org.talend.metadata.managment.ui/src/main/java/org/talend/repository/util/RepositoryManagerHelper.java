// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.util;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ICoreService;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * ggu class global comment. Detailled comment
 */
public final class RepositoryManagerHelper {

    public static IRepositoryView getRepositoryView() {
        if (CommonsPlugin.isHeadless()) {
            return null;
        }

        IViewPart part = null;
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
            IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            if (activePage != null) {
                part = activePage.findView(IRepositoryView.VIEW_ID);

                if (part == null) {
                    try {
                        part = activePage.showView(IRepositoryView.VIEW_ID);
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    }
                }
            }
        }

        return (IRepositoryView) part;
    }

    public static int getMaximumRowsToPreview() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ICoreService.class)) {
            return CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                    .getInt(ITalendCorePrefConstants.PREVIEW_LIMIT);
        }
        return 50;
    }
}
