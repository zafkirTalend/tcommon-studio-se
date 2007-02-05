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
package org.talend.repository.ui.views;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IViewPart;
import org.talend.core.i18n.Messages;
import org.talend.repository.model.RepositoryNode;

/**
 * Defines all methods a repository view must provides. Actually the only view implementing this interface is
 * RepositoryView.<br/>
 * 
 * $Id: IRepositoryView.java 797 2006-11-30 16:16:52 +0000 (星期四, 30 十一月 2006) amaumont $
 * 
 */
public interface IRepositoryView extends IViewPart {

    public static final String VIEW_ID = "org.talend.repository.views.repository"; //$NON-NLS-1$
    
    public StructuredViewer getViewer();

    public void refresh();

    public void refresh(Object object);

    public void expand(Object object);

    public void expand(Object object, boolean state);

    public boolean getExpandedState(Object object);

    public RepositoryNode getRoot();

}
