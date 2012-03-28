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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.core.model.repository.IRepositoryPrefConstants;
import org.talend.repository.RepositoryViewPlugin;
import org.talend.repository.viewer.action.AbstractRepositoryActionProvider;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractRepositoryFilterActionProvider extends AbstractRepositoryActionProvider {

    private static final String REPOSITORY_FILTER_GROUP = "talend.repository.filter.group"; //$NON-NLS-1$

    public AbstractRepositoryFilterActionProvider() {
        super();
    }

    @Override
    protected void fillMenus(IMenuManager menuManager) {
        super.fillMenus(menuManager);
        IContributionItem filterMenu = menuManager.find(REPOSITORY_FILTER_GROUP);
        if (filterMenu == null) {
            filterMenu = new MenuManager("Repository filter", REPOSITORY_FILTER_GROUP);
            menuManager.add(filterMenu);
        }
        fillFilterMenus((MenuManager) filterMenu);
    }

    protected abstract void fillFilterMenus(IMenuManager menuManager);

    protected boolean isActivedFilter() {
        final IPreferenceStore preferenceStore = RepositoryViewPlugin.getDefault().getPreferenceStore();
        return preferenceStore.getBoolean(IRepositoryPrefConstants.USE_FILTER);
    }

    protected void setActivedFilter(boolean activedFilter) {
        final IPreferenceStore preferenceStore = RepositoryViewPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(IRepositoryPrefConstants.USE_FILTER, activedFilter);
    }

}
