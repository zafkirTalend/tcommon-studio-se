// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.swt.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Defines contextual actions used on a TreeViewer.
 * 
 * $Id$
 * 
 */
public interface ITreeContextualAction extends IAction {

    /**
     * Initialise the action on a given tree with the actual selection.<br/>
     * 
     * Use the <code>setEnabled</code> method to anable/disable this action for the current selection.
     * 
     * @param viewer - The tree.
     * @param selection - Actual selection in the tree.
     */
    void init(TreeViewer viewer, IStructuredSelection selection);

    /**
     * Specifies if this action can be launch by double-click.
     * 
     * @return <code>true</code> if this action can be launch by double-click.
     */
    public boolean isDoubleClickAction();

    /**
     * Defines the Class on wich this action is launch by double-click. Means that when a double-click occurs in the
     * TreeViewer, the first registred IContextuelAction matching the selection Class is launch.
     * 
     * @return the class correspondinf to this action
     */
    public Class getClassForDoubleClick();

    public boolean isVisible();

    public int getLevel();

    public void setLevel(int level);
}
