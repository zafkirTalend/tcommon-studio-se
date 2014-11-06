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

import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PerspectiveExtensionReader;
import org.eclipse.ui.internal.PerspectiveTagger;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.e4.compatibility.ModeledPageLayout;
import org.eclipse.ui.internal.menus.MenuHelper;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.internal.registry.UIExtensionTracker;
import org.talend.core.ui.branding.IBrandingConfiguration;

/**
 * DOC yhch class global comment. Detailled comment
 * 
 * 
 */
@SuppressWarnings("restriction")
public final class PerspectiveReviewUtil {

    IExtensionTracker tracker;

    /**
     * 
     * DOC ggu Comment method "checkPerspectiveDisplayItems".
     * 
     * try to display several Talend official perspectives.
     */
    @Inject
    EModelService modelService;

    @Inject
    MWindow mWindow;

    @Inject
    EPartService partService;

    @Inject
    MApplication mApp;

    public void checkPerspectiveDisplayItems() {
        if (modelService == null) {
            return;
        }

        List<MPerspectiveStack> perspStackList = modelService.findElements(mWindow, null, MPerspectiveStack.class, null);
        if (perspStackList.size() > 0) {
            MPerspectiveStack perspectiveStack = perspStackList.get(0);

            List<MPerspective> validChildren = new ArrayList<MPerspective>();

            // DI
            findAndCreatePerspective(perspectiveStack, IBrandingConfiguration.PERSPECTIVE_DI_ID, validChildren);
            // DQ
            findAndCreatePerspective(perspectiveStack, IBrandingConfiguration.PERSPECTIVE_DQ_ID, validChildren);
            // MDM
            findAndCreatePerspective(perspectiveStack, IBrandingConfiguration.PERSPECTIVE_MDM_ID, validChildren);
            // Camel
            findAndCreatePerspective(perspectiveStack, IBrandingConfiguration.PERSPECTIVE_CAMEL_ID, validChildren);

            List<MPerspective> children = perspectiveStack.getChildren();

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

            // add the other costom perspective back
            java.util.Iterator<Integer> iterator = otherCustomPerspMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer index = iterator.next();
                if (index != null) {
                    MPerspective persp = otherCustomPerspMap.get(index);
                    // maybe this is not good, because after add, the original index have be changed.
                    if (index < children.size()) {
                        children.add(index, persp);
                    } else {
                        children.add(persp);
                    }
                }
            }

        }

        // }
    }

    /**
     * 
     * DOC ggu Comment method "findAndCreatePerspective".
     * 
     * try to find and create the perspective. if the existed custom perspective, will use the custom one. won't create
     * original one.
     * 
     * @param mWindow
     */
    private void findAndCreatePerspective(MPerspectiveStack perspectiveStack, String id, List<MPerspective> validPerspList) {
        if (id != null && mApp != null && perspectiveStack != null) {
            MPerspective mPersp = null;

            IPerspectiveRegistry perspectiveRegistry = PlatformUI.getWorkbench().getPerspectiveRegistry();
            IPerspectiveDescriptor perspDesc = perspectiveRegistry.findPerspectiveWithId(id);
            if (perspDesc != null) {
                // find the existed.
                if (perspectiveStack != null) {
                    for (MPerspective mp : perspectiveStack.getChildren()) {
                        if (mp.getElementId().equals(id)) { // existed.
                            mPersp = mp;
                            break;
                        } else { // try to check custom perspective. (the element id should be different with id.)
                            IPerspectiveDescriptor persp = perspectiveRegistry.findPerspectiveWithId(mp.getElementId());
                            if (persp != null && persp instanceof PerspectiveDescriptor) {
                                PerspectiveDescriptor pd = (PerspectiveDescriptor) persp;

                                // if custom perspective, the original id and id are different.
                                if (!pd.getOriginalId().equals(pd.getId()) //
                                        && pd.getOriginalId().equals(id)) { // when custom, just use it.
                                    mPersp = mp;
                                    break;
                                }
                            }
                        }

                    }
                }
                // create new
                if (mPersp == null) { // FIXME copied some form method setPerspective of class WorkbenchPage
                    String perspId = perspDesc.getId();

                    WorkbenchPage workbenchPage = (WorkbenchPage) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                            .getActivePage();

                    mPersp = (MPerspective) modelService.cloneSnippet(mApp, perspId, mWindow);

                    if (mPersp == null) {

                        // couldn't find the perspective, create a new one
                        mPersp = modelService.createModelElement(MPerspective.class);

                        // tag it with the same id
                        mPersp.setElementId(perspDesc.getId());

                        // instantiate the perspective
                        IPerspectiveFactory factory = ((PerspectiveDescriptor) perspDesc).createFactory();
                        ModeledPageLayout modelLayout = new ModeledPageLayout(mWindow, modelService, partService, mPersp,
                                perspDesc, workbenchPage, true);
                        factory.createInitialLayout(modelLayout);
                        PerspectiveTagger.tagPerspective(mPersp, modelService);
                        PerspectiveExtensionReader reader = new PerspectiveExtensionReader();
                        reader.extendLayout(getExtensionTracker(workbenchPage.getWorkbenchWindow().getShell().getDisplay()),
                                perspDesc.getId(), modelLayout);
                    }

                    mPersp.setLabel(perspDesc.getLabel());

                    ImageDescriptor imageDescriptor = perspDesc.getImageDescriptor();
                    if (imageDescriptor != null) {
                        String imageURL = MenuHelper.getImageUrl(imageDescriptor);
                        mPersp.setIconURI(imageURL);
                    }

                    // Hide placeholders for parts that exist in the 'global' areas
                    modelService.hideLocalPlaceholders(mWindow, mPersp);

                    // add it to the stack
                    perspectiveStack.getChildren().add(mPersp);

                }
            }// else { // can't find or not be loaded.

            if (mPersp != null) {
                validPerspList.add(mPersp);
            }
        }
    }

    /**
     * DOC sgandon Comment method "getExtensionTracker".
     * 
     * @return
     */
    private IExtensionTracker getExtensionTracker(Display display) {
        if (tracker == null) {
            tracker = new UIExtensionTracker(display);
        }
        return tracker;
    }
}
