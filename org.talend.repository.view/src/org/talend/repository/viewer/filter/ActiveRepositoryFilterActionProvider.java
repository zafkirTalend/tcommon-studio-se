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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.repository.i18n.Messages;
import org.talend.repository.viewer.action.ActionConstants;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ActiveRepositoryFilterActionProvider extends AbstractRepositoryFilterActionProvider implements IPerspectiveListener {

    private boolean isPerspectiveFiltering = true; // default is enabled.

    public ActiveRepositoryFilterActionProvider() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.action.AbstractRepositoryActionProvider#fillToolBar(org.eclipse.jface.action.
     * IToolBarManager)
     */
    @Override
    protected void fillToolBar(IToolBarManager toolBarManager) {
        super.fillToolBar(toolBarManager);
        if (toolBarManager.find(ActionConstants.TALEND_GROUP_ID) == null) {
            toolBarManager.add(new Separator(ActionConstants.TALEND_GROUP_ID));
        }
        if (toolBarManager.find(ActiveFilterAction.ACTION_ID) == null) {
            final ActiveFilterAction action = new ActiveFilterAction(Messages.getString("ActiveRepositoryFilterActionProvider.ActivateFilter")); //$NON-NLS-1$
            toolBarManager.appendToGroup(ActionConstants.TALEND_GROUP_ID, action);
        }
    }

    @Override
    public void restoreState(IMemento aMemento) {
        super.restoreState(aMemento);
        if (aMemento != null) {
            Integer isFilteringInt = aMemento.getInteger(IS_FILTERING_WITH_PERSPECTIVE);
            if (isFilteringInt != null) {
                isPerspectiveFiltering = isFilteringInt.intValue() == 1;
            }
        }
        // in order to execute the action run
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(this);
    }

    private void doFiltering(boolean filtering, boolean restoring) {
        RepositoryNodeFilterHelper.filter(this.getActionSite(), filtering, isPerspectiveFiltering, restoring);
    }

    void setFiltering(boolean filtering, boolean restoring) {
        if (filtering != isActivedFilter()) {
            doFiltering(filtering, restoring);

            setActivedFilter(filtering);
        }
    }

    @Override
    public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
        doFiltering(isActivedFilter(), true);
    }

    @Override
    public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
        // if viewer is completly removed from all views then remove this from perspective listeners.
        CommonViewer commonViewer = getCommonViewer();
        if (commonViewer.getControl().isDisposed()) {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().removePerspectiveListener(this);
        }// else do nothing
    }

    /**
     * 
     * DOC ggu ActiveFilterAction class global comment. Detailled comment
     */
    class ActiveFilterAction extends Action {

        /**
         * action ID
         */
        public static final String ACTION_ID = "talend.filter.activation.action"; //$NON-NLS-1$

        public ActiveFilterAction(String label) {
            super(label, IAction.AS_CHECK_BOX);
            this.setChecked(isActivedFilter());
            this.setToolTipText(Messages.getString("ActiveRepositoryFilterActionProvider.ActiveFilterAvailable")); //$NON-NLS-1$
            this.setImageDescriptor(ImageProvider.getImageDesc(EImage.FILTER_ACTIVED_ICON));
            this.setDisabledImageDescriptor(ImageProvider.getImageDesc(EImage.FILTER_DEACTIVED_ICON));
            setId(ACTION_ID);
            updateImage();
        }

        @Override
        public void run() {
            super.run();
            ActiveRepositoryFilterActionProvider.this.setFiltering(isChecked(), false);

            updateImage();
        }

        public void updateImage() {
            if (isChecked()) {
                setImageDescriptor(ImageProvider.getImageDesc(EImage.FILTER_ACTIVED_ICON));
            } else {
                setImageDescriptor(ImageProvider.getImageDesc(EImage.FILTER_DEACTIVED_ICON));
            }
        }
    }

}
