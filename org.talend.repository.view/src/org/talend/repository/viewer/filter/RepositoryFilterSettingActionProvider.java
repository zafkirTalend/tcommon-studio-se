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
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.talend.repository.i18n.Messages;
import org.talend.repository.viewer.dialog.RepositoryFilterSettingDialog;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepositoryFilterSettingActionProvider extends AbstractRepositoryFilterActionProvider {

    private static final String DOT = "..."; //$NON-NLS-1$

    private boolean isPecpectiveFiltering = true;

    public RepositoryFilterSettingActionProvider() {
        super();
    }

    @Override
    protected void fillMenus(IMenuManager menuManager) {

        FilterSettingAction action = new FilterSettingAction(Messages.getString("RepositoryFilterSettingActionProvider.FilterSetting") + DOT); //$NON-NLS-1$
        menuManager.add(action);
    }

    @Override
    public void restoreState(IMemento aMemento) {
        super.restoreState(aMemento);
        if (aMemento != null) {
            Integer isFilteringInt = aMemento.getInteger(IS_FILTERING_WITH_PERSPECTIVE);
            if (isFilteringInt != null) {
                isPecpectiveFiltering = isFilteringInt.intValue() == 1;
            }
        }
    }

    /**
     * 
     * DOC ggu FilterSettingAction class global comment. Detailled comment
     */
    class FilterSettingAction extends Action {

        public FilterSettingAction(String label) {
            super(label);
            this.setToolTipText(this.getText()); // use same
        }

        @Override
        public void run() {
            super.run();
            final ICommonActionExtensionSite actionSite = RepositoryFilterSettingActionProvider.this.getActionSite();

            RepositoryFilterSettingDialog dialog = new RepositoryFilterSettingDialog(actionSite, isActivedFilter(),
                    isPecpectiveFiltering);
            if (dialog.open() == Window.OK) {
                actionSite.getStructuredViewer().refresh();
            }
        }

    }

}
