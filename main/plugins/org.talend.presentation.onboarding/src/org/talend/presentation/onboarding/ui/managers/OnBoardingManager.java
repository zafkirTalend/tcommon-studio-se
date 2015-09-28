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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.presentation.onboarding.i18n.Messages;
import org.talend.presentation.onboarding.interfaces.IOnBoardingJsonI18n;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingJsonDoc;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPageBean;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPerspectiveBean;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPresentationData;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingRegistedResource;

/**
 * created by cmeng on Sep 25, 2015 Detailled comment
 *
 */
public class OnBoardingManager {

    private static Map<String, OnBoardingManager> managers = new HashMap<String, OnBoardingManager>();

    private IWorkbenchWindow workBenchWindow;

    private OnBoardingUIManager uiManager;

    private OnBoardingResourceManager resourceManager;

    private Shell parentShell;

    private List<OnBoardingPresentationData> presentationDatas;

    private String managerId = Integer.toHexString(hashCode());

    private String docId = null;

    private String perspId = null;

    private IOnBoardingJsonI18n i18n = null;

    private int currentSelectedIndex = -1;

    public OnBoardingManager(Shell _parentShell) {
        registOnBoardingManager();
        IWorkbench workBench = PlatformUI.getWorkbench();
        if (workBench == null) {
            throw new RuntimeException(Messages.getString("OnBoardingManager.NPE.workbench")); //$NON-NLS-1$
        }
        workBenchWindow = workBench.getActiveWorkbenchWindow();
        // if (workBenchWindow == null) {
        // IWorkbenchWindow workbenchWindows[] = workBench.getWorkbenchWindows();
        // if (workbenchWindows != null && 0 < workbenchWindows.length) {
        // workBenchWindow = workbenchWindows[0];
        //                OnBoardingExceptionHandler.log(Messages.getString("OnBoardingManager.workbenchWindow.notFound")); //$NON-NLS-1$
        // }
        // }
        if (workBenchWindow == null) {
            throw new RuntimeException(Messages.getString("OnBoardingManager.NPE.workbenchWindow")); //$NON-NLS-1$
        }
        if (_parentShell == null) {
            parentShell = workBenchWindow.getShell();
            if (parentShell == null) {
                throw new RuntimeException(Messages.getString("OnBoardingManager.NPE.workbenchWindowShell")); //$NON-NLS-1$
            }
        } else {
            parentShell = _parentShell;
        }
    }

    public void createDefaultUIAndResourceManagers() {
        uiManager = new OnBoardingUIManager(this);
        resourceManager = OnBoardingResourceManager.getDefaultResourceManager();
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

    public void close() {
        unRegistOnBoardingManager();
        uiManager.close();
    }

    public void onBoarding(int index) {
        uiManager.onBoarding(index);
    }

    public void reloadResource() {
        if (docId == null) {
            throw new RuntimeException(Messages.getString("OnBoardingManager.docId.null")); //$NON-NLS-1$
        }
        OnBoardingRegistedResource resource = resourceManager.getOnBoardingRegistedResource(docId);
        if (resource == null) {
            throw new RuntimeException(Messages.getString("OnBoardingManager.OnBoardingRegistedResource.notFound")); //$NON-NLS-1$
        }
        OnBoardingJsonDoc jsonDoc = resource.getJsonDoc();
        if (perspId == null) {
            perspId = jsonDoc.getDefaultPerspId();
        }
        presentationDatas = new ArrayList<OnBoardingPresentationData>();
        i18n = resource.getI18n();
        OnBoardingPerspectiveBean perspBean = jsonDoc.getPerspectiveBean(perspId);
        if (perspBean != null) {
            List<OnBoardingPageBean> pages = perspBean.getPages();
            if (pages != null) {
                for (OnBoardingPageBean page : pages) {
                    OnBoardingPresentationData presentationData = new OnBoardingPresentationData();
                    presentationData.setPageBean(page);
                    presentationDatas.add(presentationData);
                }
            }
        }
    }

    public OnBoardingPresentationData getCurrentSelectedPresentationData() {
        if (currentSelectedIndex < 0 || presentationDatas == null || presentationDatas.size() <= currentSelectedIndex) {
            return null;
        }
        return presentationDatas.get(currentSelectedIndex);
    }

    public OnBoardingUIManager getUiManager() {
        return this.uiManager;
    }

    public void setUiManager(OnBoardingUIManager uiManager) {
        this.uiManager = uiManager;
    }

    public OnBoardingResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public void setResourceManager(OnBoardingResourceManager resourceManager) {
        this.resourceManager = resourceManager;
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

    private void registOnBoardingManager() {
        managers.put(managerId, this);
    }

    private void unRegistOnBoardingManager() {
        managers.remove(managerId);
    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public IOnBoardingJsonI18n getI18n() {
        return this.i18n;
    }

    public void setI18n(IOnBoardingJsonI18n i18n) {
        this.i18n = i18n;
    }

    public Shell getParentShell() {
        return this.parentShell;
    }

    public void setParentShell(Shell parentShell) {
        this.parentShell = parentShell;
    }

    public String getPerspId() {
        return this.perspId;
    }

    public void setPerspId(String perspId) {
        this.perspId = perspId;
    }

}
