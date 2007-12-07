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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.swt.actions.ITreeContextualAction;
import org.talend.core.CorePlugin;
import org.talend.core.model.repository.IRepositoryObject;
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

    private boolean isToolbar = false;

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
    public void refresh() {
        getViewPart().refresh();
    }

    /**
     * Convenience method user to refresh view (starting with the given element) on wich action had been called.
     * 
     * @param obj - object to start the refresh on
     */
    public void refresh(Object obj) {
        getViewPart().refresh(obj);
        getViewPart().expand(obj, true);
    }

    private IWorkbenchPart workbenchPart = null;

    /**
     * Getter for workbenchPart.
     * 
     * @return the workbenchPart
     */
    public IWorkbenchPart getWorkbenchPart() {
        return this.workbenchPart;
    }

    /**
     * Sets the workbenchPart.
     * 
     * @param workbenchPart the workbenchPart to set
     */
    public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
        this.workbenchPart = workbenchPart;
    }

    /**
     * The repository view selection.
     * 
     * @return the selection
     */
    public final ISelection getSelection() {
        if (workbenchPart != null) {
            if (workbenchPart instanceof IRepositoryView) {
                IRepositoryView view = (IRepositoryView) workbenchPart;
                return view.getViewer().getSelection();
            }
        }
        if (getActivePage().getActiveEditor() == null) {
            return null;
        }
        IWorkbenchPartSite site = getActivePage().getActiveEditor().getSite();
        return site.getSelectionProvider().getSelection();
    }

    /**
     * 
     * Returns the repository view..
     * 
     * @return - the repository biew
     */
    public IRepositoryView getViewPart() {
        if (workbenchPart != null) {
            if (workbenchPart instanceof IRepositoryView) {
                return (IRepositoryView) workbenchPart;
            }
        }
        IViewPart viewPart = (IViewPart) getActivePage().findView(IRepositoryView.VIEW_ID);
        return (IRepositoryView) viewPart;
    }

    /**
     * Returns the currently active page for this workbench window.
     * 
     * @return the active page, or <code>null</code> if none
     */
    public IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    protected void notifySQLBuilder(List<IRepositoryObject> list) {
        CorePlugin.getDefault().getRepositoryService().notifySQLBuilder(list);
    }
    /**
     * Getter for isToolbar.
     * 
     * @return the isToolbar
     */
    public boolean isToolbar() {
        return this.isToolbar;
    }

    /**
     * Sets the isToolbar.
     * 
     * @param isToolbar the isToolbar to set
     */
    public void setToolbar(boolean isToolbar) {
        this.isToolbar = isToolbar;
    }

}
