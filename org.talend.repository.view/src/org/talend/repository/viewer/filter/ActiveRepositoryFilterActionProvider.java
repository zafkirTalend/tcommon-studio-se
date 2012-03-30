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
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.repository.viewer.action.ActionConstants;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ActiveRepositoryFilterActionProvider extends AbstractRepositoryFilterActionProvider {

    private final ActiveFilterAction activeFilterAction;

    public ActiveRepositoryFilterActionProvider() {
        super();
        activeFilterAction = new ActiveFilterAction("Active Filter");
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
            toolBarManager.appendToGroup(ActionConstants.TALEND_GROUP_ID, new ActiveFilterAction("Activate Filter")); //$NON-NLS-1$
        }
    }

    @Override
    public void restoreState(IMemento aMemento) {
        super.restoreState(aMemento);
        doFiltering(isActivedFilter(), true);
    }

    private void doFiltering(boolean filtering, boolean restoring) {
        RepositoryNodeFilterHelper.filter(this.getActionSite(), filtering, restoring);
        //
    }

    void setFiltering(boolean filtering, boolean restoring) {
        if (filtering != isActivedFilter()) {
            doFiltering(filtering, restoring);

            setActivedFilter(filtering);
        }
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
            this.setToolTipText(this.getText()); // use same
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
