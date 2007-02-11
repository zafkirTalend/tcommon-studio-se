/*
 * Created on Jul 22, 2004
 */
package com.quantum.editors.graphical.actions;

/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Common Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Provides a context menu for the schema diagram editor. A virtual cut and paste from the flow example
 * @author Daniel Lee
 */
public class EntityRelationContextMenuProvider extends ContextMenuProvider
{

	private ActionRegistry actionRegistry;

	/**
	 * Creates a new FlowContextMenuProvider assoicated with the given viewer
	 * and action registry.
	 * 
	 * @param viewer
	 *            the viewer
	 * @param registry
	 *            the action registry
	 */
	public EntityRelationContextMenuProvider(EditPartViewer viewer, ActionRegistry registry)
	{
		super(viewer);
		setActionRegistry(registry);
	}

	/**
	 * @see ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void buildContextMenu(IMenuManager menu)
	{
		GEFActionConstants.addStandardActionGroups(menu);

		IAction action;
		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
        
        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_IN);
        menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT);
        menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        menu.add(getActionRegistry().getAction("com.quantum.actions.MarkForOutput"));
	}

	private ActionRegistry getActionRegistry()
	{
		return actionRegistry;
	}

	/**
	 * Sets the action registry
	 * 
	 * @param registry
	 *            the action registry
	 */
	public void setActionRegistry(ActionRegistry registry)
	{
		actionRegistry = registry;
	}

}