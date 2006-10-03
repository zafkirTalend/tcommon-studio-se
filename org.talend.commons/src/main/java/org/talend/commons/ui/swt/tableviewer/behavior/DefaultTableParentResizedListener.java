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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;

/**
 * TODO create a layout in place of this class <br/> $Id: DefaultTableParentResizedListener.java,v 1.3.
 * 
 * 2006/06/02 15:24:10 amaumont Exp $
 */
public class DefaultTableParentResizedListener extends ControlAdapter {

    private TableViewerCreator tableViewerCreator;

    private int previousWidth = -1;

    public DefaultTableParentResizedListener(TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreator = tableViewerCreator;
    }

    public void controlResized(ControlEvent e) {
        if (tableViewerCreator.getLayoutMode() == LAYOUT_MODE.CURRENT_WIDTH) {
            resizeColumns();
        }
    }

    /**
     * DOC amaumont Comment method "resizeColumns".
     */
    protected void resizeColumns() {
        Table table = this.tableViewerCreator.getTable();
        Rectangle area = table.getParent().getClientArea();
        Point preferredSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        int width = area.width - 2 * table.getBorderWidth();
        if (preferredSize.y > area.height + table.getHeaderHeight()) {
            // Subtract the scrollbar width from the total column width
            // if a vertical scrollbar will be required
            Point vBarSize = table.getVerticalBar().getSize();
            width -= vBarSize.x;
        }
        if (width < 0) {
            width = 0;
        }
        if (this.previousWidth == -1) {
            this.previousWidth = width;
        }
        Point oldSize = table.getSize();
        if (oldSize.x > area.width) {
            // table is getting smaller so make the columns
            // smaller first and then resize the table to
            // match the client area width
            setWidthColumns(table, width);
            table.setSize(area.width, area.height);
        } else {
            // table is getting bigger so make the table
            // bigger first and then make the columns wider
            // to match the client area width
            table.setSize(area.width, area.height);
            setWidthColumns(table, width);
        }

        this.previousWidth = width;
    }

    protected void setWidthColumns(Table table, int width) {
        TableColumn[] tableColumns = table.getColumns();
        int[] widths = new int[tableColumns.length];
        int totalWidths = 0;
        for (int i = 0; i < tableColumns.length; i++) {
            TableColumn column = tableColumns[i];
            double coef = (double) width / (double) this.previousWidth;
            widths[i] = (int) (Math.round((double) coef * (double) column.getWidth()));
            totalWidths += widths[i];
        }
        int diff = width - totalWidths;
        for (int i = tableColumns.length - 1; i >= 0; i--) {
            TableColumn column = tableColumns[i];
            int widthTemp = 0;
            if (i == tableColumns.length - 1) {
                widthTemp = widths[i] + diff;
                // widthTemp = widths[i];
            } else {
                widthTemp = widths[i];
            }
            column.setWidth(widthTemp);
        }
    }

}
