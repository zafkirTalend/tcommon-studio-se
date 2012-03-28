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
package org.talend.repository.viewer.action;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractRepositoryActionProvider extends CommonActionProvider {

    private boolean contributedToViewMenu = false;

    public AbstractRepositoryActionProvider() {
        super();
    }

    protected INavigatorContentService getNavigatorContentService() {
        return getActionSite().getContentService();
    }

    protected CommonViewer getCommonViewer() {
        return (CommonViewer) getActionSite().getStructuredViewer();
    }

    protected CommonNavigator getCommonNavigator() {
        return getCommonViewer().getCommonNavigator();
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        if (!contributedToViewMenu) {
            try {
                super.fillActionBars(actionBars);

                final IMenuManager menuManager = actionBars.getMenuManager();
                fillMenus(menuManager);

                final IToolBarManager toolBarManager = actionBars.getToolBarManager();
                fillToolBar(toolBarManager);
            } finally {
                contributedToViewMenu = true;
            }
        }
    }

    protected void fillMenus(final IMenuManager menuManager) {
        //
    }

    protected void fillToolBar(final IToolBarManager toolBarManager) {
        //
    }
}
