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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode;
import org.talend.dataprofiler.core.model.nodes.indicator.IndicatorTreeModelBuilder;

/**
 * @author rli
 * 
 */
public class IndicatorSelectDialog extends Dialog {

    // private Object[] tdColumns;

    private ColumnIndicator[] columnIndicators;

    /**
     * @param parentShell
     */
    public IndicatorSelectDialog(Shell parentShell, String title, ColumnIndicator[] columnIndicators) {
        super(parentShell);
        this.columnIndicators = columnIndicators;
    }

    protected Control createDialogArea(Composite parent) {
        Composite comp = (Composite) super.createDialogArea(parent);
        Tree tree = new Tree(comp, SWT.BORDER);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tree);
        ((GridData) tree.getLayoutData()).widthHint = 650;
        ((GridData) tree.getLayoutData()).heightHint = 320;
        createTreeStructure(tree);
        tree.pack();
        comp.layout();        
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

            // Create the catalog items
            for (int j = 0; j < treeColumns.length; j++) {
                if (j == 0) {
                    treeItem.setText(0, branchNodes[i].getLabel());
                    continue;
                }
            }
            if (branchNodes[i].hasChildren()) {
                createChildrenNode(tree, treeItem, treeColumns, branchNodes[i].getChildren());
            }
            treeItem.setExpanded(true);
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
                Button checkButton = new Button(tree, SWT.CHECK);
                if (((ColumnIndicator) treeColumns[j].getData()).contains(branchNodes[i].getIndicatorEnum())) {
                    checkButton.setSelection(true);
                }
                checkButton.pack();
                editor.minimumWidth = checkButton.getSize().x;
                editor.horizontalAlignment = SWT.CENTER;
                editor.setEditor(checkButton, treeItem, j);

                final TreeColumn treeColumn = treeColumns[j];
                final IIndicatorNode indicatorNode = branchNodes[i];
                checkButton.addSelectionListener(new SelectionAdapter() {

                    public void widgetSelected(SelectionEvent e) {
                        if (((Button) e.getSource()).getSelection()) {
                            ((ColumnIndicator) treeColumn.getData()).addIndicatorEnum(indicatorNode.getIndicatorEnum());
                        } else {
                            ((ColumnIndicator) treeColumn.getData()).removeIndicatorEnum(indicatorNode.getIndicatorEnum());
                        }

                    }

                });
            }
            if (branchNodes[i].hasChildren()) {
                createChildrenNode(tree, treeItem, treeColumns, branchNodes[i].getChildren());
            }
        }

    }

    private TreeColumn[] createTreeColumns(Tree tree) {
        tree.setHeaderVisible(true);
        TreeColumn[] treeColumn = new TreeColumn[columnIndicators.length + 1];
        treeColumn[0] = new TreeColumn(tree, SWT.CENTER);
        treeColumn[0].setWidth(200);
        for (int i = 0; i < this.columnIndicators.length; i++) {
            treeColumn[i + 1] = new TreeColumn(tree, SWT.CENTER);
            treeColumn[i + 1].setWidth(100);
            treeColumn[i + 1].setText(columnIndicators[i].getTdColumn().getName());
            treeColumn[i + 1].setData(columnIndicators[i]);
        }
        return treeColumn;
    }

    public ColumnIndicator[] getResult() {
        return columnIndicators;
    }

}
