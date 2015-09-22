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
package org.talend.presentation.onboarding.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.talend.presentation.onboarding.i18n.Messages;
import org.talend.presentation.onboarding.ui.managers.OnBoardingManager;
import org.talend.presentation.onboarding.ui.managers.OnBoardingResourceManager;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPresentationData;
import org.talend.presentation.onboarding.utils.OnBoardingUtils;

/**
 * created by cmeng on Sep 18, 2015 Detailled comment
 *
 */
public class OnBoardingHandler extends AbstractHandler {

    @Override
    public boolean isEnabled() {
        if (!OnBoardingUtils.isSupportBrowser()) {
            return false;
        }
        OnBoardingResourceManager resourceManager = OnBoardingResourceManager.getDefaultResourceManager();
        if (resourceManager == null) {
            return false;
        }
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null || !PlatformUI.isWorkbenchRunning()) {
            return false;
        }
        IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
        if (workbenchWindow == null) {
            return false;
        }
        List<OnBoardingPresentationData> presDatas = resourceManager.getOnBoardingPresentationDatas(OnBoardingUtils
                .getCurrentSelectedPerspectiveId(workbenchWindow));
        if (presDatas == null || presDatas.isEmpty()) {
            return false;
        }
        return super.isEnabled();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        boolean isAgree = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
                Messages.getString("OnBoardingHandler.confirm.title"), //$NON-NLS-1$
                Messages.getString("OnBoardingHandler.confirm.msg")); //$NON-NLS-1$
        if (!isAgree) {
            return null;
        }
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
        OnBoardingManager obManager = OnBoardingManager.getDefaultOnBoardingManager();
        if (obManager != null) {
            obManager.onBoarding(0);
        }
        return null;
    }

}
