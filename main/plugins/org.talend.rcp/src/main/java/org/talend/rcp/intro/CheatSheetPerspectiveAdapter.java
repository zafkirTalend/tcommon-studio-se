// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.rcp.intro;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.cheatsheets.ICheatSheetResource;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.ui.internal.cheatsheets.views.ViewUtilities;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * DOC yyi class global comment. Detailled comment
 */
public class CheatSheetPerspectiveAdapter extends PerspectiveAdapter {

    //public static final String DQ_CHEATSHEET_START_ID = "org.talend.dataprofiler.core.talenddataprofiler";//$NON-NLS-1$

    public static final String DQ_CHEATSHEET_START_ID = "org.talend.datacleansing.core.ui.dqcheatsheet";//$NON-NLS-1$
    protected String cheetSheetID;

    protected HashMap<String, Boolean> cheetSheetInPerspective = new HashMap<String, Boolean>();

    private static Logger log = Logger.getLogger(CheatSheetPerspectiveAdapter.class);

    private static final String DQ_PERSPECTIVE_ID = "org.talend.dataprofiler.DataProfilingPerspective";//$NON-NLS-1$

    public CheatSheetPerspectiveAdapter(String startId) {
        CheatSheetView cheetSheet = findCheetSheet();
        if (cheetSheet != null) {
            cheetSheet.setInput(startId);
        }
    }

    @Override
    public void perspectivePreDeactivate(IWorkbenchPage page, IPerspectiveDescriptor perspective) {

        CheatSheetView cheetSheet = findCheetSheet();
        if (null != cheetSheet) {
            cheetSheetID = cheetSheet.getCheatSheetID();
        }
        cheetSheetInPerspective.put(perspective.getId(), null != cheetSheet);
        // Always hide cheatsheet first on switching perspective
        hideCheetSheet();
        super.perspectivePreDeactivate(page, perspective);
    }

    @Override
    public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {

        if (null != cheetSheetInPerspective.get(perspective.getId()) && cheetSheetInPerspective.get(perspective.getId())) {
            restoreCheetSheet();
        }
        // MOD yyi 2011-04-08 19088: Close the cheat sheet view when the user is not in Data Profiler
        // perspective
        if (!PrefUtil.getAPIPreferenceStore().getBoolean(this.getClass().getName())
                && perspective.getId().equals(DQ_PERSPECTIVE_ID)) {
            PrefUtil.getAPIPreferenceStore().setValue(this.getClass().getName(), true);
            restoreCheetSheet();
        }
        super.perspectiveActivated(page, perspective);
    }

    private void hideCheetSheet() {
        if (findCheetSheet() != null) {
            try {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(findCheetSheet());
            } catch (Throwable t) {
                log.warn(t, t);
            }
        }
    }

    private void restoreCheetSheet() {
        Display.getDefault().asyncExec(new Runnable() {

            public void run() {
                // code clean by gdbu 2011-4-18
                // IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                // unused variable
                // IWorkbenchPage page = window.getActivePage();
                // ~code clean
                IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
                CheatSheetView view = ViewUtilities.showCheatSheetView();
                // MOD klliu bug TDQ-4130 if studio is started by other user(DI/MDM),the cheetSheetID will not be
                // initalized.
                // Then it is changed to DQ,cheetSheetID will be null,but CheatSheetView is not null,so that will show
                // an empty view.
                // Therefore DQ_CHEATSHEET_START_ID will be used to fill CheatSheetView.
                if (null != view) {
                    if (null != cheetSheetID) {
                        view.setInput(cheetSheetID);
                    } else {
                        view.setInput(DQ_CHEATSHEET_START_ID);
                    }
                }
                // ~
                if (null != activePart) {
                    activePart.setFocus();
                }
            }
        });
    }

    private CheatSheetView findCheetSheet() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();

        return (CheatSheetView) page.findView(ICheatSheetResource.CHEAT_SHEET_VIEW_ID);
    }
}
