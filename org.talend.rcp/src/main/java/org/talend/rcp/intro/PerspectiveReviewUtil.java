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
package org.talend.rcp.intro;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PerspectiveBarContributionItem;
import org.eclipse.ui.internal.PerspectiveBarManager;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.util.PrefUtil;
import org.talend.designer.core.ui.views.RefreshView;

/**
 * DOC yhch class global comment. Detailled comment
 */
public final class PerspectiveReviewUtil {

    private static final String PERSPECTIVE_DI_ID = "org.talend.rcp.perspective"; //$NON-NLS-1$

    private static final String PERSPECTIVE_DQ_ID = "org.talend.dataprofiler.DataProfilingPerspective"; //$NON-NLS-1$

    private static final String PERSPECTIVE_MDM_ID = "org.talend.mdm.perspective"; //$NON-NLS-1$

    private static String isfirst = "";

    /**
     * 
     * DOC Comment method "setPerspectiveTabs".
     */
    public static void setPerspectiveTabs() {
        // feature 19053 add
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        PerspectiveBarManager barManager = ((WorkbenchWindow) activeWorkbenchWindow).getPerspectiveBar();
        if (barManager != null && (barManager instanceof PerspectiveBarManager)) {

            IContributionItem iconItem = barManager.find(PERSPECTIVE_DI_ID);
            if (null == iconItem) {
                IPerspectiveDescriptor diMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                        .findPerspectiveWithId(PERSPECTIVE_DI_ID);
                if (null != diMailPerspective && (diMailPerspective instanceof IPerspectiveDescriptor)) {
                    PerspectiveBarContributionItem diItem = new PerspectiveBarContributionItem(diMailPerspective,
                            activeWorkbenchWindow.getActivePage());
                    if (null != diItem && (diItem instanceof PerspectiveBarContributionItem)) {
                        barManager.addItem(diItem);
                    }
                }
            }

            iconItem = barManager.find(PERSPECTIVE_DQ_ID);
            if (null == iconItem) {
                IPerspectiveDescriptor dqMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                        .findPerspectiveWithId(PERSPECTIVE_DQ_ID);
                if (null != dqMailPerspective && (dqMailPerspective instanceof IPerspectiveDescriptor)) {
                    PerspectiveBarContributionItem dqItem = new PerspectiveBarContributionItem(dqMailPerspective,
                            activeWorkbenchWindow.getActivePage());
                    if (null != dqItem && (dqItem instanceof PerspectiveBarContributionItem)) {
                        barManager.addItem(dqItem);
                    }
                }
            }

            iconItem = barManager.find(PERSPECTIVE_MDM_ID);
            if (null == iconItem) {
                IPerspectiveDescriptor mdmMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                        .findPerspectiveWithId(PERSPECTIVE_MDM_ID);
                if (null != mdmMailPerspective && (mdmMailPerspective instanceof IPerspectiveDescriptor)) {
                    PerspectiveBarContributionItem mdmItem = new PerspectiveBarContributionItem(mdmMailPerspective,
                            activeWorkbenchWindow.getActivePage());
                    if (null != mdmItem && (mdmItem instanceof PerspectiveBarContributionItem)) {
                        barManager.addItem(mdmItem);
                    }
                }
            }
        }
    }

    /**
     * 
     * DOC Comment method "setPerspectiveTabsBarSize".
     */
    public static void setPerspectiveTabsBarSize() {
        IPreferenceStore apiStore = PrefUtil.getAPIPreferenceStore();
        apiStore.setValue(IWorkbenchPreferenceConstants.PERSPECTIVE_BAR_SIZE, 300);
    }

    /**
     * 
     * DOC Comment method "regisitPerspectiveBarSelectListener".
     */
    public static void regisitPerspectiveBarSelectListener() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(new PerspectiveAdapter() {

            @Override
            public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
                String pId = perspective.getId();
                if (null == isfirst || "".equals(isfirst)) {
                    isfirst = perspective.getId();
                    refreshAll();
                } else if (pId.equals(isfirst) && !"".equals(isfirst)) {
                    return;
                } else if (!pId.equals(isfirst) && !"".equals(isfirst)) {
                    isfirst = perspective.getId();
                    refreshAll();
                }
            }
        });
    }

    /**
     * 
     * DOC Comment method "refreshAll".
     */
    private static void refreshAll() {
        IWorkbenchWindow workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (workBenchWindow == null) {
            return;
        }
        IWorkbenchPage workBenchPage = workBenchWindow.getActivePage();
        if (workBenchPage == null) {
            return;
        }
        workBenchPage.resetPerspective();
    }

}
