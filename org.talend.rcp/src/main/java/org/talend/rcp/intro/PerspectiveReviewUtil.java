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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
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

/**
 * DOC yhch class global comment. Detailled comment
 */
public final class PerspectiveReviewUtil {

    private static final String PERSPECTIVE_DI_ID = "org.talend.rcp.perspective"; //$NON-NLS-1$

    private static final String PERSPECTIVE_DQ_ID = "org.talend.dataprofiler.DataProfilingPerspective"; //$NON-NLS-1$

    private static final String PERSPECTIVE_MDM_ID = "org.talend.mdm.perspective"; //$NON-NLS-1$

    private static String isfirst = "";

    static List<String> diViewList = new ArrayList<String>();

    static List<String> dqViewList = new ArrayList<String>();

    static List<String> mdmViewList = new ArrayList<String>();

    // DI View
    static String componentSettingViewerId = "org.talend.designer.core.ui.views.properties.ComponentSettingsView";//$NON-NLS-1$

    static String navigatorId = "org.eclipse.ui.views.ResourceNavigator"; //$NON-NLS-1$

    static String outlineId = "org.eclipse.ui.views.ContentOutline"; //$NON-NLS-1$

    static String codeId = "org.talend.designer.core.codeView"; //$NON-NLS-1$

    static String repositoryId = "org.talend.repository.views.repository"; //$NON-NLS-1$

    static String runProcessViewId = "org.talend.designer.runprocess.ui.views.processview"; //$NON-NLS-1$

    static String problemsViewId = "org.talend.designer.core.ui.views.ProblemsView"; //$NON-NLS-1$

    static String modulesViewId = "org.talend.designer.codegen.perlmodule.ModulesView"; //$NON-NLS-1$

    static String ecosystemViewId = "org.talend.designer.components.ecosystem.ui.views.EcosystemView"; //$NON-NLS-1$

    static String schedulerViewId = "org.talend.scheduler.views.Scheduler"; //$NON-NLS-1$

    static String contextsViewId = "org.talend.designer.core.ui.views.ContextsView"; //$NON-NLS-1$

    static String gefPaletteViewId = "org.eclipse.gef.ui.palette_view"; //$NON-NLS-1$

    static String jobSettingsViewId = "org.talend.designer.core.ui.views.jobsettings.JobSettingsView"; //$NON-NLS-1$

    static String jobHierarchyViewId = "org.talend.designer.core.ui.hierarchy.JobHierarchyViewPart"; //$NON-NLS-1$

    // DQ View
    static String dqRepositoryViewId = "org.talend.dataprofiler.core.ui.views.DQRespositoryView";//$NON-NLS-1$

    static String dqRepositoryDetailViewId = "org.talend.dataprofiler.core.ui.views.RespositoryDetailView";

    // MDM View
    static String mdmServerViewId = "org.talend.mdm.workbench.views.ServerView";

    public static void setPerspectiveReviewUtil() {
        // DI
        diViewList.add(componentSettingViewerId);
        diViewList.add(navigatorId);
        diViewList.add(outlineId);
        diViewList.add(codeId);
        diViewList.add(repositoryId);
        diViewList.add(runProcessViewId);
        diViewList.add(problemsViewId);
        diViewList.add(modulesViewId);
        diViewList.add(ecosystemViewId);
        diViewList.add(schedulerViewId);
        diViewList.add(contextsViewId);
        diViewList.add(gefPaletteViewId);
        diViewList.add(jobSettingsViewId);
        diViewList.add(jobHierarchyViewId);

        // DQ
        dqViewList.add(dqRepositoryViewId);
        dqViewList.add(dqRepositoryDetailViewId);

        // MDM
        mdmViewList.add(mdmServerViewId);
    }

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
        diViewList.clear();
        dqViewList.clear();
        mdmViewList.clear();
        setPerspectiveReviewUtil();
        IWorkbenchWindow workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (workBenchWindow != null) {
            IWorkbenchPage page = workBenchWindow.getActivePage();
            if (page != null) {
                String perId = page.getPerspective().getId();
                if ((!"".equals(perId) && null != perId)) {
                    // eg : use DI, then switch to DQ : All view from DI must be hidden when switch
                    if (perId.equalsIgnoreCase(PERSPECTIVE_DI_ID)) {
                        for (String strId : dqViewList) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                        for (String strId : mdmViewList) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                    } else if (perId.equalsIgnoreCase(PERSPECTIVE_DQ_ID)) {
                        for (String strId : diViewList) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                        for (String strId : mdmViewList) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }

                    } else if (perId.equalsIgnoreCase(PERSPECTIVE_MDM_ID)) {
                        for (String strId : diViewList) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                        for (String strId : dqViewList) {
                            IViewPart viewPart = page.findView(strId);
                            if (viewPart != null) {
                                page.hideView(viewPart);
                            }
                        }
                    }
                }
            }
        }
    }
}
