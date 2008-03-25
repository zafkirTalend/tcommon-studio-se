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
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataquality.indicators.DataminingType;

/**
 * @author rli
 * 
 */
public class AnasisColumnTreeViewer {

    private TreeViewer treeView;

    private final Tree tree;

    public AnasisColumnTreeViewer(final Tree tree) {
        this.tree = tree;
        tree.setHeaderVisible(false);
        TreeColumn column1 = new TreeColumn(tree, SWT.CENTER);
        // column1.setText("Column 1");
        column1.setWidth(160);
        TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
        // column2.setText("Column 2");
        column2.setWidth(80);
        TreeColumn column3 = new TreeColumn(tree, SWT.CENTER);
        // column3.setText("Column 3");
        column3.setWidth(120);
        TreeColumn column4 = new TreeColumn(tree, SWT.CENTER);
        // column4.setText("Column 4");
        column4.setWidth(120);
        TreeColumn column5 = new TreeColumn(tree, SWT.CENTER);
        // column5.setText("Column 5");
        column5.setWidth(120);
        // this.setElements(null);
        // treeView = new TreeViewer(tree);
        // for (int i = 0; i < 3; i++) {
        // TreeItem item = new TreeItem(tree, SWT.NONE);
        // item.setText("item " + i);
        // for (int j = 0; j < 3; j++) {
        // TreeItem subItem = new TreeItem(item, SWT.NONE);
        // subItem.setText("item " + i + " " + j);
        // }
        // }
        //
        // final TreeEditor editor = new TreeEditor(tree);
        // // The editor must have the same size as the cell and must
        // // not be any smaller than 50 pixels.
        // editor.horizontalAlignment = SWT.LEFT;
        // editor.grabHorizontal = true;
        // editor.minimumWidth = 50;
        //
        // tree.addSelectionListener(new SelectionAdapter() {
        //
        // public void widgetSelected(SelectionEvent e) {
        // // Clean up any previous editor control
        // Control oldEditor = editor.getEditor();
        // if (oldEditor != null)
        // oldEditor.dispose();
        //
        // // Identify the selected row
        // TreeItem item = (TreeItem) e.item;
        // if (item == null)
        // return;
        // Button newEditor = new Button(tree, SWT.NONE);
        // newEditor.setText(item.getText());
        // newEditor.addSelectionListener(new SelectionListener() {
        //
        // public void widgetDefaultSelected(SelectionEvent e) {
        // System.out.println("widgetDefaultSelected");
        //
        // }
        //
        // public void widgetSelected(SelectionEvent e) {
        // System.out.println("widgetSelected");
        //
        // }
        //
        // });
        // newEditor.setFocus();
        // editor.setEditor(newEditor, item);
        // }
        // });
    }

    /**
     * @return the treeView
     */
    public TreeViewer getTreeView() {
        return treeView;
    }

    public void setElements(Object[] columns) {
        if (columns != null && columns.length != 0) {
            if (!(columns[0] instanceof TdColumn)) {
                return;
            }
        }
        TreeItem treeItem;
        for (int i = 0; i < columns.length; i++) {
            treeItem = new TreeItem(tree, SWT.NONE);
            
            treeItem.setText(0, ((TdColumn) columns[i]).getName());
            
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

            editor = new TreeEditor(tree);
            Button modButton = new Button(tree, SWT.PUSH);
            modButton.setText("Edit..");
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
        }
    }

}
