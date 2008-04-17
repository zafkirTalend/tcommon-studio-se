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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.dialog.IndicatorSelectDialog;
import org.talend.dataprofiler.core.ui.wizard.indicator.IndicatorOptionsWizard;
import org.talend.dataquality.indicators.DataminingType;

/**
 * @author rli
 * 
 */
public class AnasisColumnTreeViewer extends AbstractPagePart {

    private static final int WIDTH1_CELL = 75;

    private static final int WIDTH2_CELL = 65;

    private Composite parentComp;

    private Tree tree;

    private ColumnIndicator[] columnIndicators;

    public AnasisColumnTreeViewer(Composite parent) {
        parentComp = parent;
        this.tree = createTree(parent);
    }

    public AnasisColumnTreeViewer(Composite parent, ColumnIndicator[] columnIndicators) {
        this(parent);
        this.setElements(columnIndicators);
        this.setDirty(false);
    }

    /**
     * @param parent
     */
    private Tree createTree(Composite parent) {
        Tree newTree = new Tree(parent, SWT.NONE);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(newTree);
        ((GridData) newTree.getLayoutData()).heightHint = 300;
        ((GridData) newTree.getLayoutData()).widthHint = 500;
        newTree.setHeaderVisible(false);
        TreeColumn column1 = new TreeColumn(newTree, SWT.CENTER);
        column1.setWidth(160);
        TreeColumn column2 = new TreeColumn(newTree, SWT.CENTER);
        column2.setWidth(80);
        TreeColumn column3 = new TreeColumn(newTree, SWT.CENTER);
        column3.setWidth(120);
        TreeColumn column4 = new TreeColumn(newTree, SWT.CENTER);
        column4.setWidth(120);
        TreeColumn column5 = new TreeColumn(newTree, SWT.CENTER);
        column5.setWidth(120);
        parent.layout();
        return newTree;
    }

    public void setInput(Object[] objs) {
        if (objs != null && objs.length != 0) {
            if (!(objs[0] instanceof TdColumn)) {
                return;
            }
        }
        Map<String, TdColumn> columnsMap = new HashMap<String, TdColumn>();
        for (Object obj : objs) {
            columnsMap.put(((TdColumn) obj).getName(), (TdColumn) obj);
        }
        List<ColumnIndicator> columnIndicatorList = new ArrayList<ColumnIndicator>();
        for (ColumnIndicator columnIndicator : columnIndicators) {
            if (columnsMap.containsKey(columnIndicator.getTdColumn().getName())) {
                columnIndicatorList.add(columnIndicator);
                columnsMap.remove(columnIndicator.getTdColumn().getName());
            }
        }

        Collection<TdColumn> values = columnsMap.values();
        for (TdColumn column : values) {
            columnIndicatorList.add(new ColumnIndicator(column));
        }
        this.columnIndicators = columnIndicatorList.toArray(new ColumnIndicator[columnIndicatorList.size()]);
        this.setElements(columnIndicators);
    }

    public void setElements(final ColumnIndicator[] columnIndicators) {
        this.tree.dispose();
        this.tree = createTree(this.parentComp);
        this.columnIndicators = columnIndicators;
        for (int i = 0; i < columnIndicators.length; i++) {
            final TreeItem treeItem = new TreeItem(tree, SWT.NONE);

            final ColumnIndicator columnIndicator = (ColumnIndicator) columnIndicators[i];

            treeItem.setText(0, columnIndicator.getTdColumn().getName());
            treeItem.setData(columnIndicator);

            TreeEditor editor = new TreeEditor(tree);
            final CCombo combo = new CCombo(tree, SWT.BORDER);
            for (DataminingType type : DataminingType.values()) {
                combo.add(type.getLiteral()); // MODSCA 2008-04-10 use literal for presentation
            }
            if (columnIndicator.getDataminingType() == null) {
                combo.select(0);
            } else {
                combo.setText(columnIndicator.getDataminingType().getLiteral());
            }
            combo.addSelectionListener(new SelectionAdapter() {

                public void widgetSelected(SelectionEvent e) {
                    columnIndicator.setDataminingType(DataminingType.get(combo.getText()));
                    setDirty(true);
                }

            });
            combo.setEditable(false);
            // editor.grabHorizontal = true;
            editor.minimumWidth = WIDTH1_CELL;
            editor.setEditor(combo, treeItem, 1);

            editor = new TreeEditor(tree);
            Button addButton = new Button(tree, SWT.NONE);
            addButton.setText("Add");
            addButton.pack();
            editor.minimumWidth = WIDTH1_CELL;
            // editor.minimumWidth = addButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(addButton, treeItem, 2);
            addButton.addSelectionListener(new SelectionAdapter() {

                public void widgetSelected(SelectionEvent e) {
                    openIndicatorSelectDialog();
                }

            });

            editor = new TreeEditor(tree);
            Button modButton = new Button(tree, SWT.NONE);
            modButton.setText("Repository");
            modButton.pack();
            editor.minimumWidth = WIDTH1_CELL;
            // editor.minimumWidth = modButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(modButton, treeItem, 3);

            editor = new TreeEditor(tree);
            Button delButton = new Button(tree, SWT.NONE);
            delButton.setText("Del");
            delButton.pack();
            delButton.addSelectionListener(new SelectionAdapter() {

                public void widgetSelected(SelectionEvent e) {
                    // remove the corresponding columnIndicator object, set the input element with new value to recreate
                    // the tree.
                    ColumnIndicator[] leaves = new ColumnIndicator[columnIndicators.length - 1];
                    int j = 0;
                    for (int i = 0; i < columnIndicators.length; i++) {
                        if (columnIndicators[i] == columnIndicator) {
                            continue;
                        }
                        leaves[j] = columnIndicators[i];
                        j++;
                    }
                    setElements(leaves);
                }

            });
            editor.minimumWidth = WIDTH2_CELL;
            // editor.minimumWidth = delButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(delButton, treeItem, 4);
            if (columnIndicator.hasIndicators()) {
                createIndicatorItems(treeItem, columnIndicator.getIndicatorEnums());
            }
            treeItem.setExpanded(true);
        }
        this.setDirty(true);
    }

    private void createIndicatorItems(final TreeItem treeItem, IndicatorEnum[] indicatorEnums) {
        for (int i = 0; i < indicatorEnums.length; i++) {
            final TreeItem indicatorItem = new TreeItem(treeItem, SWT.NONE);
            final IndicatorEnum indicatorEnum = indicatorEnums[i];
            indicatorItem.setText(0, indicatorEnums[i].getLabel());

            TreeEditor editor = new TreeEditor(tree);
            Button modButton = new Button(tree, SWT.NONE);
            modButton.setText("Options");
            modButton.pack();
            editor.minimumWidth = WIDTH1_CELL;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(modButton, indicatorItem, 1);

            editor = new TreeEditor(tree);
            Button delButton = new Button(tree, SWT.NONE);
            delButton.setText("Del");
            delButton.pack();
            editor.minimumWidth = WIDTH1_CELL;
            // editor.minimumWidth = delButton.getSize().x;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(delButton, indicatorItem, 2);
            delButton.addSelectionListener(new SelectionAdapter() {

                public void widgetSelected(SelectionEvent e) {
                    // remove the corresponding indicatorEnum object, set the input element and recreate the tree.
                    ((ColumnIndicator) treeItem.getData()).removeIndicatorEnum(indicatorEnum);
                    setElements(columnIndicators);
                }

            });

            modButton.addSelectionListener(new SelectionAdapter() {

                public void widgetSelected(SelectionEvent e) {

                    ColumnIndicator columnIndicator = (ColumnIndicator) treeItem.getData();
                    // open the wizard
                    IndicatorOptionsWizard wizard = new IndicatorOptionsWizard(columnIndicator, indicatorEnum);

                    try {
                        WizardDialog dialog = new WizardDialog(e.widget.getDisplay().getActiveShell(), wizard);
                        dialog.setPageSize(300, 400);
                        dialog.open();
                        
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }
                    
                }
            });
        }
    }

    private void openIndicatorSelectDialog() {
        IndicatorSelectDialog dialog = new IndicatorSelectDialog(this.tree.getShell(), "Indicator Selector", columnIndicators);
        if (dialog.open() == Window.OK) {
            ColumnIndicator[] result = dialog.getResult();
            this.setElements(result);
            return;
        }
    }

    public ColumnIndicator[] getColumnIndicator() {
        return this.columnIndicators;
    }

}
