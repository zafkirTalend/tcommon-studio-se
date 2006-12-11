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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Tree;
import org.talend.commons.ui.swt.drawing.background.BackgroundRefresher;
import org.talend.commons.ui.swt.drawing.background.IBackgroundRefresher;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class LinkableTree implements ILinkableControl {

    private Tree tree;
    private IBackgroundRefresher backgroundRefresher;
    
    /**
     * DOC amaumont LinkableTable constructor comment.
     * @param tree
     */
    public LinkableTree(Tree tree, IBackgroundRefresher backgroundRefresher) {
        super();
        this.tree = tree;
        
        // setBackgroundMode to correct graphic bug when background is update with ExecutionLimiter
        this.tree.setBackgroundMode(SWT.INHERIT_NONE);
        
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
     * @return the table
     */
    public Tree getTree() {
        return this.tree;
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
                backgroundRefresher.refreshBackgroundWithLimiter();
            }

        };

        tree.addControlListener(controlListener);

        tree.addTreeListener(new TreeListener() {

            public void treeCollapsed(TreeEvent e) {
                backgroundRefresher.refreshBackgroundWithLimiter();
            }

            public void treeExpanded(TreeEvent e) {
                backgroundRefresher.refreshBackgroundWithLimiter();
            }

        });

        tree.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
//                updateLinksAndTableItemsHighlightState();
            }

        });

        ScrollBar vBarTree = tree.getVerticalBar();
        ScrollBar hBarTree = tree.getHorizontalBar();

        vBarTree.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                backgroundRefresher.refreshBackground();
            }
        });

        SelectionListener scrollListener = new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                // updateBackgroundWithLimiter();
                backgroundRefresher.refreshBackgroundWithLimiter();
            }
        };
        vBarTree.addSelectionListener(scrollListener);
        hBarTree.addSelectionListener(scrollListener);

    }

    
}
