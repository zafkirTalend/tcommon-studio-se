// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ICoreService;
import org.talend.core.model.process.IProcess2;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.designer.core.IMultiPageTalendEditor;
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
            IPreferenceStore preferenceStore = CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore();
            if (preferenceStore != null) {
                return preferenceStore.getInt(ITalendCorePrefConstants.PREVIEW_LIMIT);
            }
        }
        return 50;
    }

    public static List<IProcess2> getOpenedProcess(IEditorReference[] reference) {
        List<IProcess2> list = new ArrayList<IProcess2>();
        for (IEditorReference er : reference) {
            IEditorPart part = er.getEditor(false);
            if (part instanceof IMultiPageTalendEditor) {
                IMultiPageTalendEditor editor = (IMultiPageTalendEditor) part;
                list.add(editor.getProcess());
            }
        }
        return list;
    }

    public static List<IProcess2> getOpenedProcess() {
        return getOpenedProcess(getOpenedEditors());
    }

    public static IEditorReference[] getOpenedEditors() {
        final List<IEditorReference> list = new ArrayList<IEditorReference>();
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
                    IEditorReference[] reference = activeWorkbenchWindow.getActivePage().getEditorReferences();
                    list.addAll(Arrays.asList(reference));
                }
            }
        });
        return list.toArray(new IEditorReference[0]);
    }
}
