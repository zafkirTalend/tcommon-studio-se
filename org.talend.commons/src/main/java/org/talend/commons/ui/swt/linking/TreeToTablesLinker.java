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

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.ui.swt.drawing.background.IBackgroundRefresher;
import org.talend.commons.ui.swt.drawing.background.IBgDrawableComposite;
import org.talend.commons.ui.swt.drawing.link.BezierHorizontalLink;
import org.talend.commons.ui.swt.drawing.link.IDrawableLink;
import org.talend.commons.ui.swt.drawing.link.IExtremityLink;
import org.talend.commons.ui.swt.drawing.link.IStyleLink;
import org.talend.commons.ui.swt.drawing.link.LinkDescriptor;
import org.talend.commons.ui.swt.drawing.link.LinksManager;
import org.talend.commons.ui.swt.drawing.link.StyleLink;
import org.talend.commons.ui.utils.TableUtils;
import org.talend.commons.ui.utils.TreeUtils;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <D1> the data item of extremety 1
 * @param <D2> the data item of extremety 2
 */
public class TreeToTablesLinker<D1, D2> extends BgDrawableComposite implements IBgDrawableComposite, IControlsLinker {

    protected LinksManager<TreeItem, D1, Table, D2> linksManager = new LinksManager<TreeItem, D1, Table, D2>();

    private IStyleLink defaultStyleLink;

    private IStyleLink selectedStyleLink;

    private IStyleLink unselectedStyleLink;

    private Comparator<LinkDescriptor<TreeItem, D1, Table, D2>> selectedLinksComparator;

    private IBackgroundRefresher backgroundRefresher;

    private Display display;

    private Tree tree;

    private List<Table> tables;

    /**
     * DOC amaumont TreeToTableLinker constructor comment.
     * 
     * @param tree
     * @param table
     */
    public TreeToTablesLinker(Composite commonParent) {
        super(commonParent);
    }

    /**
     * DOC amaumont Comment method "init".
     * 
     * @param tree
     * @param tables
     * @param backgroundRefresher
     */
    public void init(Tree tree, Table[] tables, IBackgroundRefresher backgroundRefresher) {
        this.display = tree.getDisplay();
        this.backgroundRefresher = backgroundRefresher;
        for (Table table : tables) {
            new LinkableTable(this, backgroundRefresher, table);
        }
        new LinkableTree(this, backgroundRefresher, tree);
        this.tables = Arrays.asList(tables);
        this.tree = tree;
    }

    protected IStyleLink getDefaultStyleLink() {
        if (defaultStyleLink == null) {
            StyleLink styleLink = new StyleLink();
            styleLink.setDrawableLink(new BezierHorizontalLink(styleLink));
            styleLink.setForegroundColor(display.getSystemColor(SWT.COLOR_BLACK));
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
    protected Comparator<LinkDescriptor<TreeItem, D1, Table, D2>> getSelectedLinksComparator() {
        if (this.selectedLinksComparator == null) {
            this.selectedLinksComparator = new Comparator<LinkDescriptor<TreeItem, D1, Table, D2>>() {

                public int compare(LinkDescriptor<TreeItem, D1, Table, D2> link1, LinkDescriptor<TreeItem, D1, Table, D2> link2) {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.drawing.link.BackgroundRefresher#drawBackground(org.eclipse.swt.graphics.GC)
     */
    @Override
    public void drawBackground(GC gc) {

        List<LinkDescriptor<TreeItem, D1, Table, D2>> links = linksManager.getLinks();
        int lstSize = links.size();

        int xStartBezierLink = findXRightStartBezierLink(tree.getItems(), 0);

        Point treeToCommonPoint = display.map(tree, getBgDrawableComposite(), new Point(0, 0));
        // System.out.println("treeToCommonPoint=" + treeToCommonPoint);

        int treeItemHeight = tree.getItemHeight();
        Rectangle treeBounds = tree.getBounds();

        gc.fillRectangle(treeToCommonPoint.x, treeToCommonPoint.y, treeBounds.width - tree.getBorderWidth(), treeBounds.height
                - tree.getBorderWidth());

        // System.out.println(isAntialiasAllowed());
        if (backgroundRefresher.isAntialiasAllowed()) {
            gc.setAntialias(SWT.ON);
        } else {
            gc.setAntialias(SWT.OFF);
            // gc.setAdvanced(false);
        }
        for (int i = 0; i < lstSize; i++) {
            LinkDescriptor<TreeItem, D1, Table, D2> link = links.get(i);

            Table table = link.getExtremity2().getGraphicalObject();
            Point tableToCommonPoint = display.map(table, getBgDrawableComposite(), new Point(0, 0));

            IDrawableLink drawableLink = link.getStyleLink().getDrawableLink();
            if (drawableLink == null) {
                drawableLink = getDefaultStyleLink().getDrawableLink();
            }
            drawableLink.getStyle().apply(gc);

            IExtremityLink<TreeItem, D1> extremity1 = link.getExtremity1();
            IExtremityLink<Table, D2> extremity2 = link.getExtremity2();

            TreeItem treeItem = TreeUtils.getTreeItem(tree, (Object) extremity1.getDataItem());

            TreeItem firstExpandedAscTreeItem = TreeUtils.findFirstVisibleItemAscFrom(treeItem);

            Rectangle treeItemBounds = firstExpandedAscTreeItem.getBounds();

            int yStraight = treeToCommonPoint.y + treeItemHeight / 2 + treeItemBounds.y;
            Point pointStartStraight = new Point(treeToCommonPoint.x + treeItemBounds.x + treeItemBounds.width, yStraight);
            Point pointEndStraight = new Point(treeToCommonPoint.x + xStartBezierLink, yStraight);

            Rectangle tableItemBounds = TableUtils.getTableItem(table, (Object) extremity2.getDataItem()).getBounds();
            Rectangle tableBounds = table.getBounds();

            Point pointEndCentralCurve = backgroundRefresher.convertPointToCommonParentOrigin(new Point(tableItemBounds.x - 2,
                    tableItemBounds.y + table.getItemHeight() / 2 + table.getBorderWidth()), table);

            boolean lineStyleDot = false;

            Point point = display.map(tree, getBgDrawableComposite(), new Point(0, 0));

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

    @SuppressWarnings("unchecked")
    public void updateLinksStyleAndControlsSelection(Control currentControl) {

        boolean isTable = false;
        if (currentControl instanceof Table) {
            isTable = true;
        } else if (currentControl instanceof Tree) {
            isTable = false;
        } else {
            throw new IllegalArgumentException("This type of currentControl is unsupported");
        }

        HashSet selectedItems = new HashSet();

        if (isTable) {
            for (Table table : tables) {
                if (table != currentControl) {
                    table.deselectAll();
                }
            }

            TableItem[] selection = ((Table) currentControl).getSelection();
            for (int i = 0; i < selection.length; i++) {
                TableItem tableItem = selection[i];
                selectedItems.add(tableItem.getData());
            }

        } else {
            tree.deselectAll();

            TreeItem[] selection = tree.getSelection();
            for (int i = 0; i < selection.length; i++) {
                TreeItem treeItem = selection[i];
                selectedItems.add(treeItem.getData());
            }

        }

        List<LinkDescriptor<TreeItem, D1, Table, D2>> links = linksManager.getLinks();
        for (LinkDescriptor<TreeItem, D1, Table, D2> link : links) {
            IStyleLink styleLink = null;
            IExtremityLink extremity = null;
            if (isTable) {
                extremity = link.getExtremity2();
            } else {
                extremity = link.getExtremity1();
            }
            boolean currentItemIsSelected = selectedItems.contains(extremity.getDataItem());
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
        backgroundRefresher.refreshBackground();
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
     * Getter for backgroundRefresher.
     * 
     * @return the backgroundRefresher
     */
    public IBackgroundRefresher getBackgroundRefresher() {
        return this.backgroundRefresher;
    }

    /**
     * Sets the backgroundRefresher.
     * 
     * @param backgroundRefresher the backgroundRefresher to set
     */
    public void setBackgroundRefresher(IBackgroundRefresher backgroundRefresher) {
        this.backgroundRefresher = backgroundRefresher;
    }

    /**
     * Getter for tables.
     * 
     * @return the tables
     */
    public List<Table> getTables() {
        return this.tables;
    }

    /**
     * Getter for tree.
     * 
     * @return the tree
     */
    public Tree getTree() {
        return this.tree;
    }

    /**
     * Getter for linksManager.
     * 
     * @return the linksManager
     */
    protected LinksManager<TreeItem, D1, Table, D2> getLinksManager() {
        return this.linksManager;
    }

}
