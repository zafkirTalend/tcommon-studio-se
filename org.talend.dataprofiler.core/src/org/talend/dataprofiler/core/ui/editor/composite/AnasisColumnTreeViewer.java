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
package org.talend.dataprofiler.core.ui.editor.composite;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.dialog.IndicatorSelectDialog;
import org.talend.dataquality.indicators.DataminingType;

/**
 * @author rli
 * 
 */
public class AnasisColumnTreeViewer {

    private TreeViewer treeView;

    private final Tree tree;

    private ColumnIndicator[] tdColumns;

    public AnasisColumnTreeViewer(final Tree tree) {
        this.tree = tree;
        tree.setHeaderVisible(false);
        TreeColumn column1 = new TreeColumn(tree, SWT.CENTER);
        column1.setWidth(160);
        TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
        column2.setWidth(80);
        TreeColumn column3 = new TreeColumn(tree, SWT.CENTER);
        column3.setWidth(120);
        TreeColumn column4 = new TreeColumn(tree, SWT.CENTER);
        column4.setWidth(120);
        TreeColumn column5 = new TreeColumn(tree, SWT.CENTER);
        column5.setWidth(120);
    }

    /**
     * @return the treeView
     */
    public TreeViewer getTreeView() {
        return treeView;
    }

    public void setInput(Object[] obj) {
        if (obj != null && obj.length != 0) {
            if (!(obj[0] instanceof TdColumn)) {
                return;
            }
        }
        this.tdColumns = new ColumnIndicator[obj.length];
        for (int i = 0; i < obj.length; i++) {
            tdColumns[i] = new ColumnIndicator((TdColumn) obj[i]);
        }
        this.setElements(tdColumns);
    }

    public void setElements(ColumnIndicator[] columnIndicators) {
        this.tdColumns = columnIndicators;
        TreeItem treeItem;
        for (int i = 0; i < columnIndicators.length; i++) {
            treeItem = new TreeItem(tree, SWT.NONE);

            ColumnIndicator columnIndicator = (ColumnIndicator) columnIndicators[i];

            treeItem.setText(0, columnIndicator.getTdColumn().getName());

            TreeEditor editor = new TreeEditor(tree);
            CCombo combo = new CCombo(tree, SWT.BORDER);
            for (DataminingType type : DataminingType.values()) {
                combo.add(type.getName());
            }
            combo.select(0);
            editor.grabHorizontal = true;
            editor.setEditor(combo, treeItem, 1);

            editor = new TreeEditor(tree);
            Button addButton = new Button(tree, SWT.PUSH);
            addButton.setText("Add..");
            addButton.pack();
            editor.minimumWidth = addButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(addButton, treeItem, 2);
            addButton.addSelectionListener(new SelectionAdapter() {

                // public void widgetDefaultSelected(SelectionEvent e) {
                // }

                public void widgetSelected(SelectionEvent e) {
                    openIndicatorSelectDialog();
                }

            });

            editor = new TreeEditor(tree);
            Button modButton = new Button(tree, SWT.PUSH);
            modButton.setText("Repository");
            modButton.pack();
            editor.minimumWidth = modButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(modButton, treeItem, 3);

            editor = new TreeEditor(tree);
            Button delButton = new Button(tree, SWT.PUSH);
            delButton.setText("Del");
            delButton.pack();
            editor.minimumWidth = delButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(delButton, treeItem, 4);
            if (columnIndicator.hasIndicators()) {
                createIndicatorItems(treeItem, columnIndicator.getIndicatorEnums());
            }
        }
    }

    private void createIndicatorItems(TreeItem treeItem, IndicatorEnum[] indicatorEnums) {
        TreeItem indicatorItem;
        for (int i = 0; i < indicatorEnums.length; i++) {
            indicatorItem = new TreeItem(treeItem, SWT.NONE);
            indicatorItem.setText(0, indicatorEnums[i].getLabel());

            TreeEditor editor = new TreeEditor(tree);
            Button modButton = new Button(tree, SWT.PUSH);
            modButton.setText("Options");
            modButton.pack();
            editor.minimumWidth = modButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(modButton, indicatorItem, 1);

            editor = new TreeEditor(tree);
            Button delButton = new Button(tree, SWT.PUSH);
            delButton.setText("Del");
            delButton.pack();
            editor.minimumWidth = delButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(delButton, indicatorItem, 2);
        }
    }

    private void openIndicatorSelectDialog() {
        IndicatorSelectDialog dialog = new IndicatorSelectDialog(this.tree.getShell(), "Indicator Selector", tdColumns);
        if (dialog.open() == Window.OK) {
            ColumnIndicator[] columnIndicators = dialog.getResult();
            this.setElements(columnIndicators);
            return;
        }
    }

}
