// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.perspective;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ui.branding.IBrandingService;

/**
 * created by ggu on Nov 12, 2014 Detailled comment
 *
 */
public class RestorePerspectiveProvider {

    @Inject
    private IWorkbench workbench;

    @Inject
    private EModelService fModelService;

    @Inject
    private MWindow fWindow;

    private MPerspectiveStack fPerspectiveStack;

    protected MPerspectiveStack getMPerspectiveStack() {
        if (fPerspectiveStack != null) {
            return fPerspectiveStack;
        }

        if (fPerspectiveStack == null && fWindow != null && fModelService != null) {
            List<MPerspectiveStack> perspStackList = fModelService.findElements(fWindow, null, MPerspectiveStack.class, null);
            if (perspStackList.size() > 0) {
                fPerspectiveStack = perspStackList.get(0);
                return fPerspectiveStack;
            }
        }
        for (MWindowElement child : fWindow.getChildren()) {
            if (child instanceof MPerspectiveStack) {
                fPerspectiveStack = (MPerspectiveStack) child;
                return fPerspectiveStack;
            }
        }
        return null;
    }

    /**
     * 
     * DOC ggu Comment method "restore".
     * 
     * restore and select the right perspective.
     */
    public void restore() {
        final MPerspectiveStack mPerspectiveStack = getMPerspectiveStack();
        if (mPerspectiveStack == null) {
            return;
        }

        boolean needReset = true;
        IPerspectiveDescriptor validPerspDesc = null;

        // just find the last perspective.
        String lastPerspId = mPerspectiveStack.getSelectedElement() != null ? mPerspectiveStack.getSelectedElement()
                .getElementId() : null;
        // PTODO, seems can't get the last perspective.
        if (lastPerspId != null) {
            validPerspDesc = workbench.getPerspectiveRegistry().findPerspectiveWithId(lastPerspId);
            needReset = false; // have existed before, no need reset.
        }// else { //not found, open new one.

        // use the default one
        if (validPerspDesc == null) {
            IPerspectiveRegistry perspectiveRegistry = workbench.getPerspectiveRegistry();
            String defaultPerspectiveId = perspectiveRegistry.getDefaultPerspective();

            // get branding default perspective.
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IBrandingService.class)) {
                IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                        IBrandingService.class);
                defaultPerspectiveId = service.getBrandingConfiguration().getInitialWindowPerspectiveId();
            }
            validPerspDesc = perspectiveRegistry.findPerspectiveWithId(defaultPerspectiveId);
        }

        // find the perspective model.
        List<MPerspective> mPerspChildren = mPerspectiveStack.getChildren();
        MPerspective mPersp = null;
        if (validPerspDesc != null) {
            for (MPerspective mp : mPerspChildren) {
                if (mp.getElementId().equals(validPerspDesc.getId())) { // existed.
                    mPersp = mp;
                    break;
                }
            }
        }
        // if not found, try to use the first one.
        if (mPersp == null && !mPerspChildren.isEmpty()) {
            mPersp = mPerspChildren.get(0);
        }

        if (mPersp != null) {
            // get the real perspective.
            validPerspDesc = workbench.getPerspectiveRegistry().findPerspectiveWithId(mPersp.getElementId());
            IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null && validPerspDesc != null) {
                mPerspectiveStack.setSelectedElement(mPersp); // set one valid perspective.

                if (needReset) {// dp reset
                    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                    if (activePage != null) {
                        activePage.resetPerspective();
                    }
                }
            }
        }
    }

}
