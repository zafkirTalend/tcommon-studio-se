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

import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.talend.repository.i18n.Messages;
import org.talend.repository.viewer.action.AbstractRepositoryActionProvider;

/**
 * Provide a menu to enable perspective filtering, that is according to extension point
 * org.talend.cnf.perspective.filter definition the CNF view associated with this provider will see it's content
 * filtered.
 */
public class PerspectiveFilterActionProvider extends AbstractRepositoryActionProvider implements IPerspectiveListener {

    /**
     * maybe, not good for fixed id
     */
    public static final String ID = "org.talend.repository.viewer.actionbar.menu.perspectiveFilter"; //$NON-NLS-1$

    private static final String IS_FILTERING_WITH_PERSPECTIVE = "is.filtering.with.perspective"; //$NON-NLS-1$

    private boolean isFiltering = true; // default is enabled.

    private PerspectiveFilterAction perspectiveFilterAction = new PerspectiveFilterAction(this,
            Messages.getString("PerspectiveFilterActionProvider.PerspectiveContentFilter"));

    private final PerspectiveFilterHelper helper;

    public PerspectiveFilterActionProvider() {
        helper = new PerspectiveFilterHelper();
    }

    @Override
    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);

        helper.setTreeViewer(getCommonViewer());
        helper.setNavigatorContentService(getNavigatorContentService());
        helper.setActionProviderId(this.getActionSite().getExtensionId());
        perspectiveFilterAction.setChecked(isFiltering);
    }

    @Override
    public void restoreState(IMemento aMemento) {
        super.restoreState(aMemento);
        if (aMemento != null) {
            Integer isFilteringInt = aMemento.getInteger(IS_FILTERING_WITH_PERSPECTIVE);
            if (isFilteringInt != null) {
                isFiltering = isFilteringInt.intValue() == 1;
            }
        }
        // in order to execute the action run
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(this);
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
                String perspectiveId = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective()
                        .getId();
                helper.doFiltering(perspectiveId, filtering, restoring);
            } else {
                helper.doFiltering(null, filtering, restoring);
            }
            isFiltering = filtering;
        }
    }

    @Override
    public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
        helper.doFiltering(perspective.getId(), isFiltering, false);
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
