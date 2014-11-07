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
package org.talend.rcp.intro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.talend.core.ui.branding.IBrandingConfiguration;
import org.talend.rcp.perspective.AbstractPerpsectiveProvider;

/**
 * Check the DI, DQ, MDM, ESB perspectives and make sure display in the perspective bar always.
 * 
 */
public final class PerspectiveReviewUtil extends AbstractPerpsectiveProvider {

    @Inject
    EModelService fModelService;

    @Inject
    MWindow fWindow;

    @Inject
    EPartService fPartService;

    @Inject
    MApplication fApp;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.rcp.perspective.AbstractPerpsectiveProvider#getEModelService()
     */
    @Override
    protected EModelService getEModelService() {
        return fModelService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.rcp.perspective.AbstractPerpsectiveProvider#getMWindow()
     */
    @Override
    protected MWindow getMWindow() {
        return fWindow;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.rcp.perspective.AbstractPerpsectiveProvider#getEPartService()
     */
    @Override
    protected EPartService getEPartService() {
        return fPartService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.rcp.perspective.AbstractPerpsectiveProvider#getMApplication()
     */
    @Override
    protected MApplication getMApplication() {
        return fApp;
    }

    /**
     * 
     * DOC ggu Comment method "checkPerspectiveDisplayItems".
     * 
     * try to display several Talend official perspectives.
     */
    @Override
    public void checkPerspectiveDisplayItems() {
        List<MPerspective> validChildren = new ArrayList<MPerspective>();

        // DI
        findAndCreatePerspective(IBrandingConfiguration.PERSPECTIVE_DI_ID, validChildren);
        // Camel
        findAndCreatePerspective(IBrandingConfiguration.PERSPECTIVE_CAMEL_ID, validChildren);
        // DQ
        findAndCreatePerspective(IBrandingConfiguration.PERSPECTIVE_DQ_ID, validChildren);
        // MDM
        findAndCreatePerspective(IBrandingConfiguration.PERSPECTIVE_MDM_ID, validChildren);

        // add the valid perspectives.
        final MPerspectiveStack mPerspStack = getMPerspectiveStack();
        if (mPerspStack != null) {
            List<MPerspective> children = mPerspStack.getChildren();
            // record the order of perspective.
            Map<Integer, MPerspective> otherCustomPerspMap = new HashMap<Integer, MPerspective>();
            // try add back other custom perspectives
            for (int i = 0; i < children.size(); i++) {
                MPerspective p = children.get(i);
                if (!validChildren.contains(p)) { // not added, another custom
                    otherCustomPerspMap.put(i, p);
                }// have added in front.
            }

            children.clear(); // clean other perspectives.
            children.addAll(validChildren); // add back the valid perspectives.

            // add the other costom perspectives back
            java.util.Iterator<Integer> iterator = otherCustomPerspMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer index = iterator.next();
                if (index != null) {
                    MPerspective persp = otherCustomPerspMap.get(index);
                    // maybe this is not good, because after add, the original index have been changed.
                    if (index < children.size()) {
                        children.add(index, persp);
                    } else {
                        children.add(persp);
                    }
                }
            }
        }
    }

}
