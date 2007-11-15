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
