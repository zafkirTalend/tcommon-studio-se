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

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.dialog.IndicatorSelectDialog;
import org.talend.dataprofiler.core.ui.editor.preview.IndicatorTypeMapping;
import org.talend.dataprofiler.core.ui.wizard.indicator.IndicatorOptionsWizard;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.indicators.DataminingType;

/**
 * @author rli
 * 
 */
public class AnasisColumnTreeViewer extends AbstractPagePart {

    private static final int WIDTH1_CELL = 75;

    private Composite parentComp;

    private Tree tree;

    private ColumnIndicator[] columnIndicators;

    private Analysis analysis;

    public AnasisColumnTreeViewer(Composite parent) {
        parentComp = parent;
        this.tree = createTree(parent);
    }

    public AnasisColumnTreeViewer(Composite parent, ColumnIndicator[] columnIndicators, Analysis analysis) {
        this(parent);
        this.analysis = analysis;
        this.setElements(columnIndicators);
        this.setDirty(false);
    }

    /**
     * @param parent
     */
    private Tree createTree(Composite parent) {
        Tree newTree = new Tree(parent, SWT.NONE);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(newTree);

        newTree.setHeaderVisible(false);
        TreeColumn column1 = new TreeColumn(newTree, SWT.CENTER);
        column1.setWidth(190);
        TreeColumn column2 = new TreeColumn(newTree, SWT.CENTER);
        column2.setWidth(80);
        TreeColumn column3 = new TreeColumn(newTree, SWT.CENTER);
        column3.setWidth(120);
        // TreeColumn column4 = new TreeColumn(newTree, SWT.CENTER);
        // column4.setWidth(120);
        // TreeColumn column5 = new TreeColumn(newTree, SWT.CENTER);
        // column5.setWidth(120);
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

            treeItem.setImage(ImageLib.getImage(ImageLib.TD_COLUMN));
            String columnName = columnIndicator.getTdColumn().getName();
            treeItem.setText(0, columnName != null ? columnName + PluginConstant.SPACE_STRING + PluginConstant.PARENTHESIS_LEFT
                    + columnIndicator.getTdColumn().getSqlDataType().getName() + PluginConstant.PARENTHESIS_RIGHT : "null");
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

            editor.minimumWidth = WIDTH1_CELL;
            editor.setEditor(combo, treeItem, 1);

            /**
             * editor = new TreeEditor(tree); Button addButton = new Button(tree, SWT.NONE); addButton.setText("Add");
             * addButton.pack(); editor.minimumWidth = WIDTH1_CELL; // editor.minimumWidth = addButton.getSize().x;
             * editor.horizontalAlignment = SWT.CENTER; editor.setEditor(addButton, treeItem, 2);
             * addButton.addSelectionListener(new SelectionAdapter() {
             * 
             * public void widgetSelected(SelectionEvent e) { openIndicatorSelectDialog(); }
             * 
             * });
             * 
             * 
             * editor = new TreeEditor(tree); Button modButton = new Button(tree, SWT.NONE);
             * modButton.setText("Repository"); modButton.pack(); editor.minimumWidth = WIDTH1_CELL; //
             * editor.minimumWidth = modButton.getSize().x; editor.horizontalAlignment = SWT.CENTER;
             * editor.setEditor(modButton, treeItem, 3);
             */

            editor = new TreeEditor(tree);
            Label delButton = new Label(tree, SWT.NONE);
            delButton.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
            delButton.setImage(ImageLib.getImage(ImageLib.ACTION_DELETE));
            delButton.setToolTipText("delete");
            delButton.pack();
            delButton.addMouseListener(new MouseAdapter() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse.swt.events.MouseEvent)
                 */
                @Override
                public void mouseDown(MouseEvent e) {

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

            editor.minimumWidth = WIDTH1_CELL;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(delButton, treeItem, 2);
            if (columnIndicator.hasIndicators()) {
                createIndicatorItems(treeItem, columnIndicator.getIndicatorForMap());
            }
            treeItem.setExpanded(true);
        }
        this.setDirty(true);
    }

    private void createIndicatorItems(final TreeItem treeItem, IndicatorTypeMapping[] indicatorTypeMappings) {
        for (IndicatorTypeMapping indicatorMapping : indicatorTypeMappings) {
            final TreeItem indicatorItem = new TreeItem(treeItem, SWT.NONE);
            final IndicatorTypeMapping typeMapping = indicatorMapping;
            final IndicatorEnum indicatorEnum = indicatorMapping.getType();
            indicatorItem.setText(0, indicatorMapping.getType().getLabel());

            TreeEditor editor;
            if (!indicatorEnum.hasChildren()) {
                editor = new TreeEditor(tree);
                Label optionLabel = new Label(tree, SWT.NONE);
                optionLabel.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
                optionLabel.setImage(ImageLib.getImage(ImageLib.INDICATOR_OPTION));
                optionLabel.setToolTipText("Options");
                optionLabel.pack();
                optionLabel.setData(indicatorMapping);
                optionLabel.addMouseListener(new MouseAdapter() {

                    /*
                     * (non-Javadoc)
                     * 
                     * @see org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse.swt.events.MouseEvent)
                     */
                    @Override
                    public void mouseDown(MouseEvent e) {

                        IndicatorTypeMapping indicator = (IndicatorTypeMapping) ((Label) e.getSource()).getData();
                        IndicatorOptionsWizard wizard = new IndicatorOptionsWizard(indicator, analysis);

                        try {
                            // open the dialog
                            WizardDialog dialog = new WizardDialog(null, wizard);
                            dialog.setPageSize(300, 400);
                            if (Window.OK == dialog.open()) {

                                setDirty(true);
                            }

                        } catch (AssertionFailedException ex) {
                            MessageDialogWithToggle.openInformation(null, "Indicator Option", "No options to set!");
                        }
                    }

                });

                editor.minimumWidth = WIDTH1_CELL;
                editor.horizontalAlignment = SWT.CENTER;
                editor.setEditor(optionLabel, indicatorItem, 1);

            }

            editor = new TreeEditor(tree);
            Label delButton = new Label(tree, SWT.NONE);
            delButton.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
            delButton.setImage(ImageLib.getImage(ImageLib.ACTION_DELETE));
            delButton.setToolTipText("delete");
            delButton.pack();
            delButton.addMouseListener(new MouseAdapter() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse.swt.events.MouseEvent)
                 */
                @Override
                public void mouseDown(MouseEvent e) {

                    ((ColumnIndicator) treeItem.getData()).removeIndicatorMapping(typeMapping);
                    setElements(columnIndicators);
                }

            });

            editor.minimumWidth = WIDTH1_CELL;
            editor.horizontalAlignment = SWT.CENTER;
            editor.setEditor(delButton, indicatorItem, 2);
            if (indicatorEnum.hasChildren()) {
                indicatorItem.setData(treeItem.getData());
                createIndicatorItems(indicatorItem, indicatorMapping.getChildren());
            }

        }
    }

    public void openIndicatorSelectDialog(Shell shell) {
        IndicatorSelectDialog dialog = new IndicatorSelectDialog(shell, "Indicator Selector",

        columnIndicators);
        if (dialog.open() == Window.OK) {
            ColumnIndicator[] result = dialog.getResult();
            for (ColumnIndicator columnIndicator : result) {
                columnIndicator.storeTempIndicator();
            }
            this.setElements(result);
            return;
        }
    }

    public ColumnIndicator[] getColumnIndicator() {
        return this.columnIndicators;
    }

}
