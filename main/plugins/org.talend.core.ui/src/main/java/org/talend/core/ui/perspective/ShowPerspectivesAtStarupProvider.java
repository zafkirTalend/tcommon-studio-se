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
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PerspectiveExtensionReader;
import org.eclipse.ui.internal.PerspectiveTagger;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.e4.compatibility.ModeledPageLayout;
import org.eclipse.ui.internal.menus.MenuHelper;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.internal.registry.UIExtensionTracker;

/**
 * created by ggu on Nov 10, 2014 Detailled comment
 *
 */
@SuppressWarnings("restriction")
public class ShowPerspectivesAtStarupProvider {

    @Inject
    private IWorkbench workbench;

    @Inject
    private EModelService fModelService;

    @Inject
    private MWindow fWindow;

    @Inject
    private EPartService fPartService;

    @Inject
    private MApplication fApp;

    private IExtensionTracker fTracker;

    private MPerspectiveStack fPerspectiveStack;

    private ShowPerspectivesAtStartupRegistryReader regReader;

    protected IExtensionTracker getExtensionTracker(Display display) {
        if (fTracker == null) {
            fTracker = new UIExtensionTracker(display);
        }
        return fTracker;
    }

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

    protected ShowPerspectivesAtStartupRegistryReader getStartupRegReader() {
        if (regReader == null) {
            regReader = new ShowPerspectivesAtStartupRegistryReader();
            regReader.init();
        }
        return regReader;
    }

    /**
     * 
     * DOC ggu Comment method "checkPerspectiveDisplayItems".
     * 
     * Try to create the perspectives which are providered via extension point.
     */
    public void checkPerspectiveDisplayItems() {
        String[] showPerspectiveIds = getStartupRegReader().getShowPerspectiveIds();
        if (showPerspectiveIds == null || showPerspectiveIds.length == 0) {
            return;
        }

        //
        List<MPerspective> validPerspectiveChildren = new ArrayList<MPerspective>();

        // create the perspectives.
        for (String perspId : showPerspectiveIds) {
            findAndCreatePerspective(perspId, validPerspectiveChildren);
        }

        // add the valid perspectives.
        final MPerspectiveStack mPerspStack = getMPerspectiveStack();
        if (mPerspStack != null) {
            List<MPerspective> children = mPerspStack.getChildren();
            // record the order of perspective.
            Map<Integer, MPerspective> otherCustomPerspMap = new HashMap<Integer, MPerspective>();
            // try add back other custom perspectives
            for (int i = 0; i < children.size(); i++) {
                MPerspective p = children.get(i);
                if (!validPerspectiveChildren.contains(p)) { // not added, another custom
                    otherCustomPerspMap.put(i, p);
                }// have added in front.
            }

            children.clear(); // clean other perspectives.
            children.addAll(validPerspectiveChildren); // add back the valid perspectives.

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

    /**
     * 
     * DOC ggu Comment method "findAndCreatePerspective".
     * 
     * try to find and create the perspective. if the existed custom perspective, will use the custom one. won't create
     * original one.
     * 
     */
    protected void findAndCreatePerspective(String id, List<MPerspective> validPerspList) {
        final MPerspectiveStack mPerspStack = getMPerspectiveStack();

        if (id != null && mPerspStack != null && fWindow != null && fPartService != null && fApp != null) {
            MPerspective mPersp = null;

            IPerspectiveRegistry perspectiveRegistry = PlatformUI.getWorkbench().getPerspectiveRegistry();
            IPerspectiveDescriptor perspDesc = perspectiveRegistry.findPerspectiveWithId(id);
            if (perspDesc != null) {
                // find the existed.
                if (mPerspStack != null) {
                    for (MPerspective mp : mPerspStack.getChildren()) {
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

                    WorkbenchPage workbenchPage = (WorkbenchPage) workbench.getActiveWorkbenchWindow().getActivePage();

                    mPersp = (MPerspective) fModelService.cloneSnippet(fApp, perspId, fWindow);

                    if (mPersp == null) {

                        // couldn't find the perspective, create a new one
                        mPersp = fModelService.createModelElement(MPerspective.class);

                        // tag it with the same id
                        mPersp.setElementId(perspDesc.getId());

                        // instantiate the perspective
                        IPerspectiveFactory factory = ((PerspectiveDescriptor) perspDesc).createFactory();
                        ModeledPageLayout modelLayout = new ModeledPageLayout(fWindow, fModelService, fPartService, mPersp,
                                perspDesc, workbenchPage, true);
                        factory.createInitialLayout(modelLayout);
                        PerspectiveTagger.tagPerspective(mPersp, fModelService);
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
                    fModelService.hideLocalPlaceholders(fWindow, mPersp);

                    // add it to the stack
                    mPerspStack.getChildren().add(mPersp);

                }
            }// else { // can't find or not be loaded.

            if (mPersp != null && !validPerspList.contains(mPersp)) {
                validPerspList.add(mPersp);
            }
        }
    }
}
