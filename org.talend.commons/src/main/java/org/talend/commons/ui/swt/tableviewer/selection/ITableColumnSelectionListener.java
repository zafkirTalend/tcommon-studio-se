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
package org.talend.commons.ui.swt.tableviewer.selection;

import org.eclipse.swt.events.SelectionListener;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.sort.IColumnSortedListener;

/**
 * DOC amaumont class global comment. Detailled comment <br/> $Id: ITableColumnSelectionListener.java,v 1.2 2006/06/02
 * 15:24:10 amaumont Exp $
 */
public interface ITableColumnSelectionListener extends SelectionListener {

    public TableViewerCreatorColumn getTableViewerCreatorColumn();

    public void setTableViewerCreatorColumn(TableViewerCreatorColumn tableViewerCreatorColumn);

    public TableViewerCreator getTableViewerCreator();

    public void setTableViewerCreator(TableViewerCreator tableViewerCreator);

    public void addColumnSortedListener(IColumnSortedListener columnSortedListener);

    public void removeColumnSortedListener(IColumnSortedListener columnSortedListener);

}
