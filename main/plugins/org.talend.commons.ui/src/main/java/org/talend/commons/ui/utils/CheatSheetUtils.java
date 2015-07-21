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
package org.talend.commons.ui.utils;

import org.apache.log4j.Logger;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.cheatsheets.ICheatSheetResource;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * this class is used for cheatsheet view display.
 * 
 */
@SuppressWarnings("restriction")
public class CheatSheetUtils {

    private static CheatSheetUtils instance;

    /**
     * this flag means the first time when studio start. only when this value is true, when close the welcome page, we
     * will display and maximum display cheatsheet view, else do nothing.
     */
    private boolean isFirstTime = !PrefUtil.getAPIPreferenceStore().getBoolean(this.getClass().getSimpleName());

    /**
     * Sets the isFirstTime.
     * 
     * @param isFirstTime the isFirstTime to set
     */
    public void setFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    /**
     * Getter for isFirstTime.
     * 
     * @return the isFirstTime
     */
    public boolean isFirstTime() {
        return this.isFirstTime;
    }

    public static final String DQ_PERSPECTIVE_ID = "org.talend.dataprofiler.DataProfilingPerspective";//$NON-NLS-1$

    private static Logger log = Logger.getLogger(CheatSheetUtils.class);

    public static CheatSheetUtils getInstance() {
        if (instance == null) {
            instance = new CheatSheetUtils();
        }
        return instance;
    }

    /**
     * maximum display CheatSheetView.
     * 
     * @param view
     */
    public void maxDisplayCheatSheetView(CheatSheetView view) {
        // ADD msjian TDQ-7407 2013-8-23: Only display the Cheat Sheet view on new startup of the studio
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        activePage.setEditorAreaVisible(true);
        // activePage.resetPerspective();
        for (IViewReference ref : activePage.getViewReferences()) {
            if (view.equals(ref.getView(false))) {
                activePage.setPartState(ref, IWorkbenchPage.STATE_MAXIMIZED);
                activePage.bringToTop(ref.getView(false));
            }
            else {
                activePage.setPartState(ref, IWorkbenchPage.STATE_MINIMIZED);
            }
        }
        for (IEditorReference ref : activePage.getEditorReferences()) {
            activePage.setPartState(ref, IWorkbenchPage.STATE_MINIMIZED);
        }
        
        PrefUtil.getAPIPreferenceStore().setValue(this.getClass().getSimpleName(), true);
        setFirstTime(!PrefUtil.getAPIPreferenceStore().getBoolean(this.getClass().getSimpleName()));
        // TDQ-7407~
    }

    /**
     * find And maximum Display CheatSheet.
     */
    public void findAndmaxDisplayCheatSheet() {
        CheatSheetView findCheetSheet = findCheetSheet();
        if (findCheetSheet != null) {
            maxDisplayCheatSheetView(findCheetSheet);
        }
    }

    /**
     * get the cheet sheet view.
     * 
     * @return CheatSheetView
     */
    public CheatSheetView findCheetSheet() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        return (CheatSheetView) page.findView(ICheatSheetResource.CHEAT_SHEET_VIEW_ID);
    }

    /**
     * hide the cheet sheet view.
     * 
     * @param cheetSheet
     */
    public void hideCheetSheet(CheatSheetView cheetSheet) {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(cheetSheet);
        } catch (Throwable t) {
            log.warn(t, t);
        }
    }
}
