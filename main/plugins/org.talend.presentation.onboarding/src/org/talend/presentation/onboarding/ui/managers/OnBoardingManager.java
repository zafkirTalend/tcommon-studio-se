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
package org.talend.presentation.onboarding.ui.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.presentation.onboarding.exceptions.OnBoardingExceptionHandler;
import org.talend.presentation.onboarding.i18n.Messages;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingDocBean;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPresentationData;
import org.talend.presentation.onboarding.ui.shells.HighlightShell;
import org.talend.presentation.onboarding.ui.shells.OnBoardingShell;
import org.talend.presentation.onboarding.utils.OnBoardingUtils;
import org.talend.presentation.onboarding.utils.WidgetFinder;

/**
 * created by cmeng on Sep 11, 2015 Detailled comment
 *
 */
public class OnBoardingManager {

    private OnBoardingResourceManager resourceManager;

    private List<OnBoardingPresentationData> presentationDatas;

    private int currentSelectedIndex = -1;

    private HighlightShell highlightShell;

    private OnBoardingShell onBoardingShell;

    private String managerId = Integer.toHexString(hashCode());

    private static Map<String, OnBoardingManager> managers = new HashMap<String, OnBoardingManager>();

    private IWorkbenchWindow workBenchWindow;

    // the values are different between different perspectives
    public static OnBoardingManager getDefaultOnBoardingManager() {
        OnBoardingManager obm = null;
        OnBoardingResourceManager defaultResourceManager = OnBoardingResourceManager.getDefaultResourceManager();
        if (defaultResourceManager != null) {
            obm = new OnBoardingManager();
            obm.setResourceManager(defaultResourceManager);
        }
        return obm;
    }

    public OnBoardingManager() {
        registOnBoardingManager();

        IWorkbench workBench = PlatformUI.getWorkbench();
        if (workBench == null) {
            unRegistOnBoardingManager();
            throw new RuntimeException(Messages.getString("OnBoardingManager.NPE.workbench")); //$NON-NLS-1$
        }
        workBenchWindow = workBench.getActiveWorkbenchWindow();
        if (workBenchWindow == null) {
            IWorkbenchWindow workbenchWindows[] = workBench.getWorkbenchWindows();
            if (workbenchWindows != null && 0 < workbenchWindows.length) {
                workBenchWindow = workbenchWindows[0];
                OnBoardingExceptionHandler.log(Messages.getString("OnBoardingManager.workbenchWindow.notFound")); //$NON-NLS-1$
            }
        }
        if (workBenchWindow == null) {
            unRegistOnBoardingManager();
            throw new RuntimeException(Messages.getString("OnBoardingManager.NPE.workbenchWindow")); //$NON-NLS-1$
        }
        final Shell parentShell = workBenchWindow.getShell();
        if (parentShell == null) {
            unRegistOnBoardingManager();
            throw new RuntimeException(Messages.getString("OnBoardingManager.NPE.workbenchWindowShell")); //$NON-NLS-1$
        }

        highlightShell = new HighlightShell(parentShell, this);
        onBoardingShell = new OnBoardingShell(highlightShell.getHighlightShell(), this);
        highlightShell.open();
    }

    public OnBoardingPresentationData getCurrentSelectedPresentationData() {
        if (currentSelectedIndex < 0 || presentationDatas == null || presentationDatas.size() <= currentSelectedIndex) {
            return null;
        }
        return presentationDatas.get(currentSelectedIndex);
    }

    public void onBoarding(int index) {
        int size = this.getPresentationDatas().size();
        if (index < 0 || size <= index) {
            return;
        }
        this.setCurrentSelectedPresentationDataIndex(index);
        OnBoardingPresentationData presData = this.getCurrentSelectedPresentationData();
        if (presData == null) {
            return;
        }
        Widget widget = null;
        OnBoardingDocBean docBean = presData.getDocBean();
        String[] cssIds = docBean.getCssIds();
        widget = getWidget(cssIds);

        boolean isOpened = onBoardingShell.isOpened();
        if (isOpened) {
            onBoardingShell.setVisible(false);
        }

        onBoardingShell.setFocusedWidget(widget);
        onBoardingShell.setPresentationData(presData);
        onBoardingShell.refreshInUIThread();

        highlightShell.focusOnWidgetInUIThread(widget);

        // if (isOpened) {
        // onBoardingShell.setVisible(true);
        // }
    }

    public void onHighlightShellMoveCompleted() {
        if (!onBoardingShell.getOnBoardingShell().isDisposed()) {
            onBoardingShell.setVisible(true);
        }
    }

    private Widget getWidget(String[] cssIds) {
        Widget widget = null;
        if (cssIds != null && 0 < cssIds.length) {
            for (String cssId : cssIds) {
                if (cssId == null) {
                    continue;
                }
                Collection<Widget> widgets = WidgetFinder.findWidgetsByCSSInUIThread(cssId.trim());
                if (widgets != null && !widgets.isEmpty()) {
                    widget = widgets.iterator().next();
                    break;
                }
            }
        }
        return widget;
    }

    private String getCurrentSelectedPerspectiveId() {
        return OnBoardingUtils.getCurrentSelectedPerspectiveId(workBenchWindow);
    }

    public OnBoardingResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public void setResourceManager(OnBoardingResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.presentationDatas = this.resourceManager.getOnBoardingPresentationDatas(getCurrentSelectedPerspectiveId());
    }

    public void close() {
        unRegistOnBoardingManager();
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                highlightShell.close();

                if (onBoardingShell.getParentShell() != highlightShell.getHighlightShell()) {
                    onBoardingShell.close();
                }
            }
        });

    }

    public String getManagerId() {
        return managerId;
    }

    public static OnBoardingManager getRegistedManager(String id) {
        if (id == null) {
            return null;
        }
        return managers.get(id);
    }

    private void registOnBoardingManager() {
        managers.put(managerId, this);
    }

    private void unRegistOnBoardingManager() {
        managers.remove(managerId);
    }

    public List<OnBoardingPresentationData> getPresentationDatas() {
        return presentationDatas;
    }

    public void setCurrentSelectedPresentationDataIndex(int index) {
        this.currentSelectedIndex = index;
    }

    public int getCurrentSelectedPresentationDataIndex() {
        return currentSelectedIndex;
    }

}
