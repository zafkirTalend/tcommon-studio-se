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
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IMemento;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ActiveRepositoryFilterActionProvider extends AbstractRepositoryFilterActionProvider {

    public ActiveRepositoryFilterActionProvider() {
        super();
    }

    @Override
    protected void fillFilterMenus(IMenuManager menuManager) {

        ActiveFilterAction action = new ActiveFilterAction("Active Filter");
        menuManager.add(action);
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

        public ActiveFilterAction(String label) {
            super(label, IAction.AS_CHECK_BOX);
            this.setChecked(isActivedFilter());
            this.setToolTipText(this.getText()); // use same
        }

        @Override
        public void run() {
            super.run();
            ActiveRepositoryFilterActionProvider.this.setFiltering(isChecked(), false);
        }

    }

}
