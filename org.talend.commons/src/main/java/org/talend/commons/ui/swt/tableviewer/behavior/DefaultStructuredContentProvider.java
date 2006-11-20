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
package org.talend.commons.ui.swt.tableviewer.behavior;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;

/**
 * 
 * Default implementation of <code>IStructuredContentProvider</code> used by <code>TableViewerCreator</code>. <br/>
 * 
 * $Id$
 * 
 * @see <code>IStructuredContentProvider</code>
 */
public class DefaultStructuredContentProvider implements IStructuredContentProvider {

    TableViewerCreator tableViewerCreator;

    public DefaultStructuredContentProvider(TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreator = tableViewerCreator;
    }

    public Object[] getElements(Object inputElement) {
        return (Object[]) ((Collection) inputElement).toArray();
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

}
