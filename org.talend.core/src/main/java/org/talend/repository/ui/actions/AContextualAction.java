// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.swt.actions.ITreeContextualAction;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * Used to manage contextual actions on repository objects.<br/>
 * 
 * $Id: AContextualAction.java 219 2006-10-24 13:45:54 +0000 (星期二, 24 十月 2006) smallet $
 * 
 */
public abstract class AContextualAction extends Action implements ITreeContextualAction {

    private int level; // Used to manage order of actions in contextual menu

    private boolean readAction = false;;

    private boolean editAction = false;

    private boolean propertiesAction = false;

    public boolean isEditAction() {
        return editAction;
    }

    public void setEditAction(boolean editAction) {
        this.editAction = editAction;
    }

    public boolean isReadAction() {
        return readAction;
    }

    public void setReadAction(boolean readAction) {
        this.readAction = readAction;
    }

    public boolean isPropertiesAction() {
        return propertiesAction;
    }

    public void setPropertiesAction(boolean propertiesAction) {
        this.propertiesAction = propertiesAction;
    }

    /**
     * Getter for level.
     * 
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the level.
     * 
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.actions.ITreeContextualAction#isVisible()
     */
    public boolean isVisible() {
        return isEnabled();
    }

    /**
     * Returns if this action is accessible by double-click.
     * 
     * @return <code>true</code> if action is accessible by double-click, <code>true</code> otherwise
     */
    public final boolean isDoubleClickAction() {
        return getClassForDoubleClick() != null;
    }

    /**
     * Returns the class on wich this action may be call by double-click.
     * 
     * @return the class on wich this action may be call by double-click
     */
    public Class getClassForDoubleClick() {
        return null;
    }

    /**
     * Convenience method user to refresh view on wich action had been called.
     */
    public final void refresh() {
        getViewPart().refresh();
    }

    /**
     * Convenience method user to refresh view (starting with the given element) on wich action had been called.
     * 
     * @param obj - object to start the refresh on
     */
    public final void refresh(Object obj) {
        getViewPart().refresh(obj);
        getViewPart().expand(obj, true);
    }

    /**
     * The repository view selection.
     * 
     * @return the selection
     */
    protected final ISelection getSelection() {
        IWorkbenchPartSite site = getActivePage().getActivePart().getSite();
        return site.getSelectionProvider().getSelection();
    }

    /**
     * 
     * Returns the repository view..
     * 
     * @return - the repository biew
     */
    protected final IRepositoryView getViewPart() {
        IViewPart viewPart = (IViewPart) getActivePage().findView(IRepositoryView.VIEW_ID);
        return (IRepositoryView) viewPart;
    }

    /**
     * Returns the currently active page for this workbench window.
     * 
     * @return the active page, or <code>null</code> if none
     */
    protected final IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    protected void notifySQLBuilder(List<IRepositoryObject> list) {
        CorePlugin.getDefault().getRepositoryService().notifySQLBuilder(list);
    }

}
