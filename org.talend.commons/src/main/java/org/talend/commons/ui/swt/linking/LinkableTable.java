// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.commons.ui.swt.linking;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.drawing.background.IBackgroundRefresher;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.selection.ILineSelectionListener;
import org.talend.commons.ui.swt.tableviewer.selection.LineSelectionEvent;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LinkableTable implements ILinkableControl {

    private Table table;

    private IBackgroundRefresher backgroundRefresher;

    private IControlsLinker controlsLinker;

    private TableViewerCreator tableViewerCreator;

    /**
     * DOC amaumont LinkableTable constructor comment.
     * 
     * @param table
     */
    public LinkableTable(IControlsLinker controlsLinker, IBackgroundRefresher backgroundRefresher, Table table) {
        super();
        this.table = table;
        this.controlsLinker = controlsLinker;
        this.backgroundRefresher = backgroundRefresher;
        init();
    }

    /**
     * DOC amaumont LinkableTable constructor comment.
     * 
     * @param table
     */
    public LinkableTable(IControlsLinker controlsLinker, IBackgroundRefresher backgroundRefresher, TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreator = tableViewerCreator;
        this.table = this.tableViewerCreator.getTable();
        this.controlsLinker = controlsLinker;
        this.backgroundRefresher = backgroundRefresher;
        init();
    }

    /**
     * DOC amaumont Comment method "init".
     */
    private void init() {
        addListeners();
    }

    /**
     * Getter for table.
     * 
     * @return the table
     */
    public Table getTable() {
        return this.table;
    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
        ControlListener controlListener = new ControlListener() {

            public void controlMoved(ControlEvent e) {
            }

            public void controlResized(ControlEvent e) {
                backgroundRefresher.refreshBackgroundWithLimiter();
            }

        };
        table.addControlListener(controlListener);

        if (tableViewerCreator != null) {
            tableViewerCreator.getSelectionHelper().addAfterSelectionListener(new ILineSelectionListener() {

                public void handle(LineSelectionEvent e) {
                    controlsLinker.updateLinksStyleAndControlsSelection(table);
                }

            });

        } else {
            table.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                }

                public void widgetSelected(SelectionEvent e) {
                    controlsLinker.updateLinksStyleAndControlsSelection(table);
                }

            });
        }

        ScrollBar vBarTable = table.getVerticalBar();

        SelectionListener scrollListener = new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                backgroundRefresher.refreshBackgroundWithLimiter();
            }
        };
        vBarTable.addSelectionListener(scrollListener);

    }

}
