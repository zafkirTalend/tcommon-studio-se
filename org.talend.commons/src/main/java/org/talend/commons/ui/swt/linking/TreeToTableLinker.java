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
package org.talend.commons.ui.swt.linking;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.ui.swt.drawing.background.BackgroundRefresher;
import org.talend.commons.ui.swt.drawing.link.BezierHorizontalLink;
import org.talend.commons.ui.swt.drawing.link.IDrawableLink;
import org.talend.commons.ui.swt.drawing.link.IExtremityLink;
import org.talend.commons.ui.swt.drawing.link.IStyleLink;
import org.talend.commons.ui.swt.drawing.link.LinkDescriptor;
import org.talend.commons.ui.swt.drawing.link.LinksManager;
import org.talend.commons.ui.swt.drawing.link.StyleLink;
import org.talend.commons.ui.utils.TableUtils;
import org.talend.commons.ui.utils.TreeUtils;
import org.talend.commons.utils.threading.ExecutionLimiter;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <D1> the data item of extremety 1
 * @param <D2> the data item of extremety 2
 */
public class TreeToTableLinker<D1, D2> extends BackgroundRefresher {

    protected Tree tree;

    protected Table table;

    protected LinksManager<D1, D2> linksManager = new LinksManager<D1, D2>();

    private IStyleLink defaultStyleLink;

    private IStyleLink selectedStyleLink;

    private IStyleLink unselectedStyleLink;

    private Comparator<LinkDescriptor<D1, D2>> selectedLinksComparator;

    private ExecutionLimiter executionLimiter = new ExecutionLimiter(50, true) {

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.utils.threading.ExecutionLimiter#execute(boolean)
         */
        @Override
        protected void execute(final boolean isFinalExecution) {
            commonParent.getDisplay().asyncExec(new Runnable() {

                public void run() {
                    // if (isFinalExecution) {
                    updateBackground();
                    // }
                }

            });

        }

    };

    /**
     * DOC amaumont TreeToTableLinker constructor comment.
     * 
     * @param tree
     * @param table
     */
    public TreeToTableLinker(Composite commonParent, Tree tree, Table table) {
        super(commonParent);
        this.tree = tree;
        this.tree.setBackgroundMode(SWT.INHERIT_NONE); // to correct graphic bug when background is update with
                                                        // ExecutionLimiter
        this.table = table;
        addListeners();
        createLinksComparators();
    }

    protected IStyleLink getDefaultStyleLink() {
        if (defaultStyleLink == null) {
            StyleLink styleLink = new StyleLink();
            styleLink.setDrawableLink(new BezierHorizontalLink(styleLink));
            styleLink.setForegroundColor(tree.getDisplay().getSystemColor(SWT.COLOR_BLACK));
            styleLink.setLineWidth(2);
            this.defaultStyleLink = styleLink;
        }
        return this.defaultStyleLink;
    }

    /**
     * DOC amaumont Comment method "createLinkSorter".
     */
    protected void createLinksComparators() {

        this.selectedLinksComparator = getSelectedLinksComparator();

    }

    /**
     * DOC amaumont Comment method "getSelectedLinksComparator".
     */
    protected Comparator<LinkDescriptor<D1, D2>> getSelectedLinksComparator() {
        if (this.selectedLinksComparator == null) {
            this.selectedLinksComparator = new Comparator<LinkDescriptor<D1, D2>>() {

                public int compare(LinkDescriptor<D1, D2> link1, LinkDescriptor<D1, D2> link2) {
                    IStyleLink link1StyleLink = link1.getStyleLink();
                    IStyleLink link2StyleLink = link2.getStyleLink();
                    if (link1StyleLink == link2StyleLink) {
                        return 0;
                    }
                    if (link1StyleLink == getUnselectedStyleLink()) {
                        return -1;
                    }
                    return 1;
                }

            };
        }
        return this.selectedLinksComparator;
    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
        ControlListener controlListener = new ControlListener() {

            public void controlMoved(ControlEvent e) {
                // updateBackgroundWithLimiter();
            }

            public void controlResized(ControlEvent e) {
                updateBackgroundWithLimiter();
            }

        };

        configureScrollBars(table.getDisplay());

        table.addControlListener(controlListener);
        tree.addControlListener(controlListener);

        tree.addTreeListener(new TreeListener() {

            public void treeCollapsed(TreeEvent e) {
                updateBackgroundWithLimiter();
            }

            public void treeExpanded(TreeEvent e) {
                updateBackgroundWithLimiter();
            }

        });

        tree.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                updateLinksAndTableItemsHighlightState();
            }

        });

        table.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                updateLinksAndTreeItemsHighlightState();
            }

        });

    }

    private void configureScrollBars(final Display display) {
        ScrollBar vBarTable = table.getVerticalBar();
        ScrollBar vBarTree = tree.getVerticalBar();
        ScrollBar hBarTree = tree.getHorizontalBar();

        vBarTree.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                updateBackground();
            }
        });

        SelectionListener scrollListener = new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                // updateBackgroundWithLimiter();
                updateBackgroundWithLimiter();
            }
        };
        vBarTable.addSelectionListener(scrollListener);
        vBarTree.addSelectionListener(scrollListener);
        hBarTree.addSelectionListener(scrollListener);
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
     * Getter for tree.
     * 
     * @return the tree
     */
    public Tree getTree() {
        return this.tree;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.BackgroundRefresher#drawBackground(org.eclipse.swt.graphics.GC)
     */
    @Override
    public void drawBackground(GC gc) {

        List<LinkDescriptor<D1, D2>> links = linksManager.getLinks();
        int lstSize = links.size();

        int xStartBezierLink = findXRightStartBezierLink(tree.getItems(), 0);

        Display display = table.getDisplay();

        Point treeToCommonPoint = display.map(tree, commonParent, new Point(0, 0));
        // System.out.println("treeToCommonPoint=" + treeToCommonPoint);
        Point tableToCommonPoint = display.map(table, commonParent, new Point(0, 0));

        int treeItemHeight = tree.getItemHeight();
        Rectangle treeBounds = tree.getBounds();

        gc.fillRectangle(treeToCommonPoint.x, treeToCommonPoint.y, treeBounds.width - tree.getBorderWidth(), treeBounds.height
                - tree.getBorderWidth());

        // System.out.println(isAntialiasAllowed());
        if (isAntialiasAllowed()) {
            gc.setAntialias(SWT.ON);
        } else {
            gc.setAntialias(SWT.OFF);
            // gc.setAdvanced(false);
        }
        for (int i = 0; i < lstSize; i++) {
            LinkDescriptor<D1, D2> link = links.get(i);

            IDrawableLink drawableLink = link.getStyleLink().getDrawableLink();
            if (drawableLink == null) {
                drawableLink = getDefaultStyleLink().getDrawableLink();
            }
            drawableLink.getStyle().apply(gc);

            IExtremityLink<D1> extremity1 = link.getExtremity1();
            IExtremityLink<D2> extremity2 = link.getExtremity2();

            TreeItem treeItem = TreeUtils.getTreeItem(tree, (Object) extremity1.getDataItem());

            TreeItem firstExpandedAscTreeItem = findFirstVisibleItemAscFrom(treeItem);

            Rectangle treeItemBounds = firstExpandedAscTreeItem.getBounds();

            int yStraight = treeToCommonPoint.y + treeItemHeight / 2 + treeItemBounds.y;
            Point pointStartStraight = new Point(treeToCommonPoint.x + treeItemBounds.x + treeItemBounds.width, yStraight);
            Point pointEndStraight = new Point(treeToCommonPoint.x + xStartBezierLink, yStraight);

            Rectangle tableItemBounds = TableUtils.getTableItem(table, (Object) extremity2.getDataItem()).getBounds();
            Rectangle tableBounds = table.getBounds();

            Point pointEndCentralCurve = convertPointToCommonParentOrigin(new Point(tableItemBounds.x - 2, tableItemBounds.y
                    + table.getItemHeight() / 2 + table.getBorderWidth()), table);

            boolean lineStyleDot = false;

            Point point = display.map(tree, commonParent, new Point(0, 0));

            if (yStraight < point.y || yStraight > point.y + treeBounds.height) {
                lineStyleDot = true;
            }

            if (pointEndCentralCurve.y < tableToCommonPoint.y) {
                pointEndCentralCurve.y = tableToCommonPoint.y;
                lineStyleDot = true;
            }

            if (pointEndCentralCurve.y > tableToCommonPoint.y + tableBounds.height) {
                pointEndCentralCurve.y = tableToCommonPoint.y + tableBounds.height - 2 * table.getBorderWidth();
                lineStyleDot = true;
            }

            if (firstExpandedAscTreeItem == treeItem && !lineStyleDot) {
                gc.setLineStyle(SWT.LINE_SOLID);
            } else {
                gc.setLineStyle(SWT.LINE_DOT);
            }

            gc.drawLine(pointStartStraight.x, pointStartStraight.y, pointEndStraight.x, pointEndStraight.y);

            drawableLink.setPoint1(pointEndStraight);
            drawableLink.setPoint2(pointEndCentralCurve);

            drawableLink.draw(gc);
        }

    }

    /**
     * DOC amaumont Comment method "findFirstVisibleItemAscFrom".
     * 
     * @param treeItem
     */
    private TreeItem findFirstVisibleItemAscFrom(TreeItem treeItem) {
        TreeItem parentItem = treeItem.getParentItem();
        if (parentItem == null) {
            return treeItem;
        } else if (parentItem.getExpanded()) {
            TreeItem treeItemFound = getNextCollapseParent(parentItem);
            if (treeItemFound != null) {
                return findFirstVisibleItemAscFrom(treeItemFound);
            } else {
                return treeItem;
            }
        } else {
            return findFirstVisibleItemAscFrom(parentItem);
        }
    }

    /**
     * DOC amaumont Comment method "getNextCollapseParent".
     * 
     * @param parentItem
     */
    private TreeItem getNextCollapseParent(TreeItem treeItem) {
        TreeItem parentItem = treeItem.getParentItem();
        if (parentItem == null) {
            return null;
        } else if (!parentItem.getExpanded()) {
            return parentItem;
        } else {
            TreeItem treeItemFound = getNextCollapseParent(parentItem);
            if (treeItemFound != null) {
                return parentItem;
            } else {
                return null;
            }
        }

    }

    /**
     * DOC amaumont Comment method "findMaxWidth".
     * 
     * @param items
     * @param maxWidth
     * @return
     */
    private int findXRightStartBezierLink(TreeItem[] items, int maxWidth) {
        for (int i = 0; i < items.length; i++) {
            TreeItem item = items[i];
            if (!item.isDisposed()) {
                Rectangle bounds = item.getBounds();
                maxWidth = Math.max(maxWidth, bounds.x + bounds.width);
                if (item.getExpanded()) {
                    maxWidth = findXRightStartBezierLink(item.getItems(), maxWidth);
                }
            }
        }
        return maxWidth;
    }

    public void updateLinksAndTableItemsHighlightState() {

        table.deselectAll();

        TreeItem[] selection = tree.getSelection();
        HashSet selectedItems = new HashSet();
        for (int i = 0; i < selection.length; i++) {
            TreeItem treeItem = selection[i];
            selectedItems.add(treeItem.getData());
        }

        List<LinkDescriptor<D1, D2>> links = linksManager.getLinks();
        for (LinkDescriptor<D1, D2> link : links) {
            IStyleLink styleLink = null;
            boolean currentItemIsSelected = selectedItems.contains(link.getExtremity1().getDataItem());
            if (currentItemIsSelected) {
                styleLink = selectedStyleLink;
            } else {
                styleLink = unselectedStyleLink;
            }
            if (styleLink == null) {
                styleLink = getDefaultStyleLink();
            }
            link.setStyleLink(styleLink);

        }
        linksManager.sortLinks(getSelectedLinksComparator());
        updateBackground();
    }

    public void updateLinksAndTreeItemsHighlightState() {

        tree.deselectAll();

        TableItem[] selection = table.getSelection();
        HashSet selectedItems = new HashSet();
        for (int i = 0; i < selection.length; i++) {
            TableItem tableItem = selection[i];
            selectedItems.add(tableItem.getData());
        }

        List<LinkDescriptor<D1, D2>> links = linksManager.getLinks();
        for (LinkDescriptor<D1, D2> link : links) {
            IStyleLink styleLink = null;
            boolean currentItemIsSelected = selectedItems.contains(link.getExtremity2().getDataItem());
            if (currentItemIsSelected) {
                styleLink = selectedStyleLink;
            } else {
                styleLink = unselectedStyleLink;
            }
            if (styleLink == null) {
                styleLink = getDefaultStyleLink();
            }
            link.setStyleLink(styleLink);

        }
        linksManager.sortLinks(getSelectedLinksComparator());
        updateBackgroundWithLimiter();
    }

    /**
     * Getter for selectedStyleLink.
     * 
     * @return the selectedStyleLink
     */
    public IStyleLink getSelectedStyleLink() {
        return this.selectedStyleLink;
    }

    /**
     * Sets the selectedStyleLink.
     * 
     * @param selectedStyleLink the selectedStyleLink to set
     */
    public void setSelectedStyleLink(IStyleLink selectedStyleLink) {
        this.selectedStyleLink = selectedStyleLink;
    }

    /**
     * Getter for unselectedStyleLink.
     * 
     * @return the unselectedStyleLink
     */
    public IStyleLink getUnselectedStyleLink() {
        return this.unselectedStyleLink;
    }

    /**
     * Sets the unselectedStyleLink.
     * 
     * @param unselectedStyleLink the unselectedStyleLink to set
     */
    public void setUnselectedStyleLink(IStyleLink unselectedStyleLink) {
        this.unselectedStyleLink = unselectedStyleLink;
    }

    /**
     * DOC amaumont Comment method "updateBackroundAsynchronous".
     */
    private void updateBackgroundWithLimiter() {
        executionLimiter.startIfExecutable();
    }

}
