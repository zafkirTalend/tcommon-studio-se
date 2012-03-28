// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.filter;

import java.util.Arrays;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentDescriptor;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.repository.viewer.action.AbstractRepositoryActionProvider;

/**
 * Provide a menu to enable perspective filtering, that is according to extension point
 * org.talend.cnf.perspective.filter definition the CNF view associated with this provider will see it's content
 * filtered.
 */
public class PerspectiveFilterActionProvider extends AbstractRepositoryActionProvider implements IPerspectiveListener {

    private static final String IS_FILTERING_WITH_PERSPECTIVE = "is.filtering.with.perspective"; //$NON-NLS-1$

    boolean isFiltering;

    private PerspectiveFilterAction perspectiveFilterAction;

    private PerspectiveFilterRegistryReader perspectiveFilterExtensionsReader;

    public PerspectiveFilterActionProvider() {
    }

    @Override
    protected void fillMenus(IMenuManager menuManager) {
        super.fillMenus(menuManager);
        perspectiveFilterAction = new PerspectiveFilterAction(this, isFiltering, "Perspective Filter Actions");
        menuManager.add(perspectiveFilterAction);
    }

    @Override
    public void restoreState(IMemento aMemento) {
        super.restoreState(aMemento);
        if (aMemento != null) {
            Integer isFilteringInt = aMemento.getInteger(IS_FILTERING_WITH_PERSPECTIVE);
            isFiltering = isFilteringInt == null || isFilteringInt.intValue() == 1;
            if (isFiltering) {
                filterView(true);
            } else {
                unfilterView(true);
            }
        } else {
            unfilterView(true);
        }
    }

    @Override
    public void saveState(IMemento aMemento) {
        if (aMemento != null) {
            int isFilteringInt = isFiltering ? 1 : 0;
            aMemento.putInteger(IS_FILTERING_WITH_PERSPECTIVE, isFilteringInt);
        }
        super.saveState(aMemento);
    }

    /**
     * set the filtering state of the viewer and update the content of the viewer accordingly.
     * 
     * @param filtering, if true filtering is active (false make filtering unactive)
     */
    public void setFiltering(boolean filtering, boolean restoring) {
        if (filtering != isFiltering) {
            if (filtering) {
                filterView(restoring);
            } else {
                unfilterView(restoring);
            }
            isFiltering = filtering;
        }
    }

    /**
     * start filtering the view with the current perspective
     * 
     * @param restoring
     * */
    private void filterView(boolean restoring) {
        // register this as perspective listener
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(this);
        doFiltering(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId(), restoring);
    }

    private void unfilterView(boolean restoring) {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(this);
        undoFiltering(restoring);
    }

    /**
     * filter on the given perspective
     * 
     * @param perspectiveId
     * @param restoring
     */
    private void doFiltering(String perspectiveId, boolean restoring) {
        refreshNavigatorContents(getExtensionIdsToActivate(perspectiveId), restoring);
    }

    /**
     * unfilter
     */
    private void undoFiltering(boolean restoring) {
        refreshNavigatorContents(getNavigatorContentService().getVisibleExtensionIds(), restoring);
    }

    /**
     * Only activate the view content providers with the extension ID belonging to extensionIdsToActivate array.
     * 
     * 
     * @param extensionIdsToActivate
     * @param restoring, used because setting new selection cause renentrance and may create 2 action types when
     * restoring state
     */
    protected void refreshNavigatorContents(String[] extensionIdsToActivate, boolean restoring) {
        boolean updateExtensionActivation = false;
        String[] contentExtensionsToActivate = Arrays.copyOf(extensionIdsToActivate, extensionIdsToActivate.length);
        // we sort the array in order to use Array.binarySearch();
        Arrays.sort(contentExtensionsToActivate);

        IStructuredSelection ssel = null;
        CommonViewer commonViewer = getCommonViewer();
        INavigatorContentService contentService = getNavigatorContentService();
        try {

            commonViewer.getControl().setRedraw(false);
            // store the selection
            ISelection selection = commonViewer.getSelection();
            if (selection instanceof IStructuredSelection) {
                ssel = (IStructuredSelection) selection;
            }

            INavigatorContentDescriptor[] visibleContentDescriptors = contentService.getVisibleExtensions();

            int indexofContentExtensionIdToBeActivated;
            /* is there a delta? */
            for (int i = 0; i < visibleContentDescriptors.length && !updateExtensionActivation; i++) {
                indexofContentExtensionIdToBeActivated = Arrays.binarySearch(contentExtensionsToActivate,
                        visibleContentDescriptors[i].getId());
                /*
                 * Either we have a filter that should be active that isn't XOR a filter that shouldn't be active that
                 * is currently
                 */
                if (indexofContentExtensionIdToBeActivated >= 0 ^ contentService.isActive(visibleContentDescriptors[i].getId())) {
                    updateExtensionActivation = true;
                }
            }

            /* If so, update */
            if (updateExtensionActivation) {

                contentService.getActivationService().activateExtensions(contentExtensionsToActivate, true);
                contentService.getActivationService().persistExtensionActivations();
                // try to keep expanded elements state
                Object[] expandedElements = commonViewer.getExpandedElements();

                contentService.update();
                commonViewer.refresh();
                if (!restoring) {
                    Object[] originalObjects = ssel != null ? ssel.toArray() : new Object[0];

                    commonViewer.setExpandedElements(expandedElements);
                    // restore the selection.
                    IStructuredSelection newSelection = new StructuredSelection(originalObjects);
                    commonViewer.setSelection(newSelection, true);
                }
            }

        } finally {
            commonViewer.getControl().setRedraw(true);
        }

    }

    protected String[] getExtensionIdsToActivate(String perspectiveId) {
        if (perspectiveFilterExtensionsReader == null) {
            perspectiveFilterExtensionsReader = new PerspectiveFilterRegistryReader(getActionSite().getExtensionId());
            perspectiveFilterExtensionsReader.readRegistry();
        }
        INavigatorContentService contentService = getNavigatorContentService();
        String[] filteredIds = perspectiveFilterExtensionsReader.getContentProviderFilteredIds(contentService, perspectiveId);
        return filteredIds;
    }

    @Override
    public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
        CommonViewer commonViewer = getCommonViewer();
        if (!commonViewer.getControl().isDisposed() && commonViewer.getControl().isVisible() && isFiltering) {// force
                                                                                                              // refiltering
                                                                                                              // caus
                                                                                                              // perspective
                                                                                                              // changed
                                                                                                              // and may
            // not be same filter
            doFiltering(perspective.getId(), false);
        }
    }

    @Override
    public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
        // if viewer is completly removed from all views then remove this from perspective listeners.
        CommonViewer commonViewer = getCommonViewer();
        if (commonViewer.getControl().isDisposed()) {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().removePerspectiveListener(this);
        }// else do nothing
    }

}
