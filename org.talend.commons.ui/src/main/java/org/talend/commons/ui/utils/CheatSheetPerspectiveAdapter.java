// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.utils;

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
import org.eclipse.ui.internal.PartPane;
import org.eclipse.ui.internal.PartSite;
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

    public HashMap<String, Boolean> cheetSheetInPerspective = new HashMap<String, Boolean>();

    private static Logger log = Logger.getLogger(CheatSheetPerspectiveAdapter.class);

    private static final String DQ_PERSPECTIVE_ID = "org.talend.dataprofiler.DataProfilingPerspective";//$NON-NLS-1$

    private boolean isFirstTime = true;

    private static CheatSheetPerspectiveAdapter instance = null;

    /**
     * CheatSheetPerspectiveAdapter constructor.
     * 
     */
    public CheatSheetPerspectiveAdapter() {
        CheatSheetView cheetSheet = findCheetSheet();
        if (cheetSheet != null) {
            cheetSheet.setInput(CheatSheetPerspectiveAdapter.DQ_CHEATSHEET_START_ID);
        }
    }

    /**
     * get Instance.
     * 
     * @return
     */
    public static CheatSheetPerspectiveAdapter getInstance() {
        if (instance == null) {
            instance = new CheatSheetPerspectiveAdapter();
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.PerspectiveAdapter#perspectivePreDeactivate(org.eclipse.ui.IWorkbenchPage,
     * org.eclipse.ui.IPerspectiveDescriptor)
     */
    @Override
    public void perspectivePreDeactivate(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
        CheatSheetView cheetSheet = findCheetSheet();
        if (null != cheetSheet) {
            cheetSheetID = cheetSheet.getCheatSheetID();
            // Always hide cheatsheet first on switching perspective
            hideCheetSheet(cheetSheet);
        }
        cheetSheetInPerspective.put(perspective.getId(), null != cheetSheet);
        super.perspectivePreDeactivate(page, perspective);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.PerspectiveAdapter#perspectiveActivated(org.eclipse.ui.IWorkbenchPage,
     * org.eclipse.ui.IPerspectiveDescriptor)
     */
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

    /**
     * hide the cheet sheet view.
     * 
     * @param cheetSheet
     */
    private void hideCheetSheet(CheatSheetView cheetSheet) {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(cheetSheet);
        } catch (Throwable t) {
            log.warn(t, t);
        }
    }

    /**
     * show the cheet sheet view.
     */
    private void restoreCheetSheet() {
        Display.getDefault().asyncExec(new Runnable() {

            public void run() {
                CheatSheetView view = ViewUtilities.showCheatSheetView();

                // MOD klliu bug TDQ-4130 if studio is started by other user(DI/MDM),the cheetSheetID will not be
                // initalized.
                // Then it is changed to DQ,cheetSheetID will be null,but CheatSheetView is not null,so that will show
                // an empty view.
                // Therefore DQ_CHEATSHEET_START_ID will be used to fill CheatSheetView.
                IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
                if (null != view) {
                    if (null != cheetSheetID) {
                        view.setInput(cheetSheetID);
                    } else {
                        view.setInput(DQ_CHEATSHEET_START_ID);
                    }

                    // ADD msjian TDQ-7407 2013-8-23: Only display the Cheat Sheet view on new startup of the studio
                    if (isFirstTime && !PrefUtil.getAPIPreferenceStore().getBoolean(this.getClass().getSimpleName())) {
                        PartPane pane = ((PartSite) view.getSite()).getPane();
                        view.getSite().getPage().toggleZoom(pane.getPartReference());
                        view.setFocus();

                        isFirstTime = false;
                        PrefUtil.getAPIPreferenceStore().setValue(this.getClass().getSimpleName(), true);
                    }
                    // TDQ-7407~
                }
                if (null != activePart) {
                    activePart.setFocus();
                }
            }
        });
    }

    /**
     * get the cheet sheet view.
     * 
     * @return CheatSheetView
     */
    private CheatSheetView findCheetSheet() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        return (CheatSheetView) page.findView(ICheatSheetResource.CHEAT_SHEET_VIEW_ID);
    }
}
