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
package org.talend.dataprofiler.core.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode;
import org.talend.dataprofiler.core.model.nodes.indicator.IndicatorTreeModelBuilder;

/**
 * @author rli
 * 
 */
public class IndicatorSelectDialog extends Dialog {

    private Object[] tdColumns;

    /**
     * @param parentShell
     */
    public IndicatorSelectDialog(Shell parentShell, String title, Object[] columns) {
        super(parentShell);
        this.tdColumns = columns;
    }

    protected Control createDialogArea(Composite parent) {
        Composite comp = (Composite) super.createDialogArea(parent);
        Tree tree = new Tree(comp, SWT.BORDER);
        GridDataFactory.fillDefaults().span(1, 1).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tree);
        ((GridData) tree.getLayoutData()).widthHint = 800;
        ((GridData) tree.getLayoutData()).heightHint = 600;
        createTreeStructure(tree);
        return comp;
    }

    private void createTreeStructure(Tree tree) {

        TreeColumn[] treeColumns = createTreeColumns(tree);

        IIndicatorNode[] branchNodes = IndicatorTreeModelBuilder.buildIndicatorCategory();

        createChildrenNode(tree, treeColumns, branchNodes);
    }

    /**
     * @param tree
     * @param treeColumns
     * @param branchNodes
     * @param i
     */
    private void createChildrenNode(Tree tree, TreeColumn[] treeColumns, IIndicatorNode[] branchNodes) {
        for (int i = 0; i < branchNodes.length; i++) {
            TreeItem treeItem;
            treeItem = new TreeItem(tree, SWT.NONE);
            TreeEditor editor;
            for (int j = 0; j < treeColumns.length; j++) {
                if (j == 0) {
                    treeItem.setText(0, branchNodes[i].getLabel());
                    continue;
                }
                editor = new TreeEditor(tree);
                Button delButton = new Button(tree, SWT.CHECK);
                delButton.pack();
                editor.minimumWidth = delButton.getSize().x;
                editor.horizontalAlignment = SWT.CENTER;
                editor.setEditor(delButton, treeItem, j);
            }
            if (branchNodes[i].hasChildren()) {
                createChildrenNode(tree, treeItem, treeColumns, branchNodes[i].getChildren());
            }
        }

    }

    private void createChildrenNode(Tree tree, TreeItem parentItem, TreeColumn[] treeColumns, IIndicatorNode[] branchNodes) {
        for (int i = 0; i < branchNodes.length; i++) {
            TreeItem treeItem;
            treeItem = new TreeItem(parentItem, SWT.NONE);
            TreeEditor editor;
            for (int j = 0; j < treeColumns.length; j++) {
                if (j == 0) {
                    treeItem.setText(0, branchNodes[i].getLabel());
                    continue;
                }
                editor = new TreeEditor(tree);
                Button delButton = new Button(tree, SWT.CHECK);
                delButton.pack();
                editor.minimumWidth = delButton.getSize().x;
                editor.horizontalAlignment = SWT.CENTER;
                editor.setEditor(delButton, treeItem, j);
            }
            if (branchNodes[i].hasChildren()) {
                createChildrenNode(tree, treeItem, treeColumns, branchNodes[i].getChildren());
            }
        }

    }

    private TreeColumn[] createTreeColumns(Tree tree) {
        tree.setHeaderVisible(true);
        TreeColumn[] treeColumn = new TreeColumn[tdColumns.length + 1];
        treeColumn[0] = new TreeColumn(tree, SWT.CENTER);
        treeColumn[0].setWidth(200);
        for (int i = 0; i < this.tdColumns.length; i++) {
            treeColumn[i + 1] = new TreeColumn(tree, SWT.CENTER);
            treeColumn[i + 1].setWidth(100);
            treeColumn[i + 1].setText(((TdColumn) tdColumns[i]).getName());
        }
        return treeColumn;
    }

}
