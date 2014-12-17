// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.swt.linking;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.runtime.swt.tableviewer.selection.ILineSelectionListener;
import org.talend.commons.ui.runtime.swt.tableviewer.selection.LineSelectionEvent;
import org.talend.commons.ui.runtime.ws.WindowSystem;
import org.talend.commons.ui.swt.drawing.background.IBackgroundRefresher;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;

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

    private BgDrawableComposite bgDrawableComposite;

    private boolean forceDrawLinksGtk = true;

    public LinkableTable(IControlsLinker controlsLinker, IBackgroundRefresher backgroundRefresher, Table table,
            BgDrawableComposite bgDrawableComposite) {
        this(controlsLinker, backgroundRefresher, table, bgDrawableComposite, true);
    }

    /**
     * DOC amaumont LinkableTable constructor comment.
     * 
     * @param table
     * @param bgDrawableComposite TODO
     */
    public LinkableTable(IControlsLinker controlsLinker, IBackgroundRefresher backgroundRefresher, Table table,
            BgDrawableComposite bgDrawableComposite, boolean forceDrawLinksGtk) {
        super();
        this.table = table;
        this.controlsLinker = controlsLinker;
        this.backgroundRefresher = backgroundRefresher;
        this.bgDrawableComposite = bgDrawableComposite;
        this.forceDrawLinksGtk = forceDrawLinksGtk;
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

        // to correct graphic bug under Linux-GTK when the wizard is opened the first time
        if (WindowSystem.isGTK() && forceDrawLinksGtk) {
            table.addListener(SWT.Paint, new Listener() {

                public void handleEvent(Event event) {
                    // System.out.println("table Paint");
                    paintEvent(event);
                }

            });
        }

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
                    controlsLinker.updateLinksStyleAndControlsSelection(table, true);
                }

            });

        } else {
            table.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                }

                public void widgetSelected(SelectionEvent e) {
                    controlsLinker.updateLinksStyleAndControlsSelection(table, true);
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

    private void paintEvent(Event event) {
        // System.out.println("event.gc=" + event.gc);

        Point offsetPoint = event.display.map(bgDrawableComposite.getBgDrawableComposite(), table, new Point(0, 0));
        bgDrawableComposite.setOffset(offsetPoint);
        // System.out.println("paintEvent.gc="+event.gc);
        bgDrawableComposite.drawBackground(event.gc);

        // executionLimiter.startIfExecutable(event);
    }

}
