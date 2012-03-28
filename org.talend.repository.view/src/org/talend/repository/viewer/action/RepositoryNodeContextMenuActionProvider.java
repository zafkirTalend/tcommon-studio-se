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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.commands.ActionHandler;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.talend.commons.ui.swt.actions.ITreeContextualAction;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ICoreService;
import org.talend.repository.ui.actions.ActionsHelper;

public class RepositoryNodeContextMenuActionProvider extends CommonActionProvider {

    private List<ITreeContextualAction> contextualsActions;

    public RepositoryNodeContextMenuActionProvider() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
     */
    @Override
    public void init(ICommonActionExtensionSite aSite) {

        super.init(aSite);
        makeActions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    public void fillContextMenu(IMenuManager manager) {
        super.fillContextMenu(manager);

        IStructuredSelection sel = (IStructuredSelection) getContext().getSelection();
        MenuManager[] menuManagerGroups = null;
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ICoreService.class)) {
            final ICoreService coreService = (ICoreService) GlobalServiceRegister.getDefault().getService(ICoreService.class);
            if (coreService != null) {
                menuManagerGroups = coreService.getRepositoryContextualsActionGroups();
            }
        }
        // find group
        Set<String> processedGroupIds = new HashSet<String>();
        for (ITreeContextualAction action : contextualsActions) {
            action.init((TreeViewer) getActionSite().getStructuredViewer(), sel);
            if (action.isVisible() && action.isEnabled()) {
                IMenuManager groupMenu = findMenuManager(menuManagerGroups, action.getGroupId(), true); // find root
                if (groupMenu != null) { // existed
                    final String rootId = groupMenu.getId();
                    if (!processedGroupIds.contains(rootId)) {
                        manager.add(groupMenu);
                        processedGroupIds.add(rootId);
                    }
                }
                groupMenu = findMenuManager(menuManagerGroups, action.getGroupId(), false); // find last child
                if (groupMenu != null) { // existed
                    groupMenu.add(action);
                } else { // child
                    manager.add(action);
                }
            }
        }
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    private MenuManager findMenuManager(final MenuManager[] menuManagerGroups, String groupId, boolean findParent) {
        if (menuManagerGroups == null) {
            return null;
        }
        for (MenuManager groupMenu : menuManagerGroups) {
            if (groupMenu.getId().equals(groupId)) {
                if (findParent) {
                    final MenuManager parent = (MenuManager) groupMenu.getParent();
                    if (parent == null) {
                        return groupMenu;
                    } else {
                        return findMenuManager(menuManagerGroups, parent.getId(), findParent);
                    }
                } else {
                    return groupMenu;
                }
            }
        }
        return null;
    }

    protected void makeActions() {
        ICommonViewerWorkbenchSite navWorkSite = ((ICommonViewerWorkbenchSite) getActionSite().getViewSite());
        IHandlerService handlerService = (IHandlerService) navWorkSite.getSite().getService(IHandlerService.class);
        IHandler handler = null;

        contextualsActions = ActionsHelper.getRepositoryContextualsActions();
        for (ITreeContextualAction action : contextualsActions) {
            action.setWorkbenchPart(navWorkSite.getSite().getPart());
            if (action.getActionDefinitionId() != null) {
                handler = new ActionHandler(action);
                handlerService.activateHandler(action.getActionDefinitionId(), handler);
            }
        }

    }

}
