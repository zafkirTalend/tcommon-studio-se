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
package org.talend.core.ui.targetschema.editor;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LINE_SELECTION;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SHOW_SELECTION;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.data.ModifiedObjectInfo;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.targetschema.editor.TargetSchemaEditor2;
import org.talend.core.model.targetschema.editor.TargetSchemaEditorEvent;

/**
 * DOC amaumont class global comment. Detailled comment <br/> TGU same purpose as TargetSchemaTableEditorView but uses
 * EMF model directly
 * 
 * $Id$
 * 
 */
public class TargetSchemaTableEditorView2 {

    private Label nameLabel;

    private TableViewerCreator<SchemaTarget> tableViewerCreator;

    private Composite composite;

    private TargetSchemaEditor2 targetSchemaTableEditor;

    private boolean executeSelectionEvent = true;

    private TargetSchemaToolbarEditorView2 targetSchemaToolbarEditorView2;

    public final static String ID_COLUMN_NAME = "ID_COLUMN_NAME";

    private boolean showDbTypeColumn = false;

    public TargetSchemaTableEditorView2(Composite parent, int style, TargetSchemaEditor2 targetSchemaEditor) {
        this(parent, style, false);
        this.composite = parent;
        setTargetSchemaEditor(targetSchemaEditor);
    }

    /**
     * TargetSchemaTableEditorView2 constructor comment.
     * 
     * @param parent
     * @param style
     * @param showDbTypeColumn
     */
    public TargetSchemaTableEditorView2(Composite parent, int style, boolean showDbTypeColumn) {
        super();
        this.showDbTypeColumn = showDbTypeColumn;
        // composite = new Composite(parent, style);
        GridLayout layout = new GridLayout();
        parent.setLayout(layout);
        createComponents(parent);
        addListeners();
    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
    }

    private void createComponents(Composite composite) {
        nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText("");
        nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        addTargetSchemaTable(composite);
        // addTargetSchemaToolbar();

    }

    /**
     * DOC amaumont Comment method "addTargetSchemaToolbar".
     */
    // private void addTargetSchemaToolbar(Composite composite) {
    // targetSchemaToolbarEditorView2 = new TargetSchemaToolbarEditorView2(composite, SWT.NONE, this);
    // }
    private void addTargetSchemaTable(Composite composite) {

        tableViewerCreator = new TableViewerCreator<SchemaTarget>(composite);
        tableViewerCreator.setLayout(new RowLayout(SWT.HORIZONTAL));

        tableViewerCreator.setHeaderVisible(true);
        tableViewerCreator.setAllColumnsResizable(true);
        tableViewerCreator.setBorderVisible(true);
        tableViewerCreator.setLinesVisible(true);
        tableViewerCreator.setShowSelection(SHOW_SELECTION.FULL);
        tableViewerCreator.setLineSelection(LINE_SELECTION.MULTI);
        tableViewerCreator.setLayoutMode(LAYOUT_MODE.FILL_HORIZONTAL);
        tableViewerCreator.setAdjustWidthValue(-15);
        tableViewerCreator.setFirstColumnMasked(true);
        tableViewerCreator.setFirstVisibleColumnIsSelection(true);
        tableViewerCreator.setUseCustomColoring(true);

        final Table table = tableViewerCreator.createTable();
        table.setLayoutData(new GridData(GridData.FILL_BOTH));

        TableViewer tableViewer = tableViewerCreator.getTableViewer();
        tableViewer.addOpenListener(new IOpenListener() {

            public void open(OpenEvent event) {
                // System.out.println("OpenEvent");

            }

        });

        initColumns(tableViewer.getTable());
    }

    private void initColumns(Table table) {

        CellEditorValueAdapter intValueAdapter = new CellEditorValueAdapter() {

            public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
                try {
                    return new Integer(value.toString());
                } catch (Exception ex) {
                    return null;
                }
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                if (value != null) {
                    return String.valueOf(value);
                }
                return "";
            }
        };

        // boolean ValueAdapter
        CellEditorValueAdapter booleanValueAdapter = new CellEditorValueAdapter() {

            public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
                return (value == new Integer(1));
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                if (value == new Boolean(true)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        // comboValueAdapter
        CellEditorValueAdapter comboValueAdapter = new CellEditorValueAdapter() {

            public Object getOriginalTypedValue(final CellEditor cellEditor, Object value) {
                String[] items = ((ComboBoxCellEditor) cellEditor).getItems();
                int i = new Integer(value.toString());
                if (i >= 0) {
                    return items[i];
                } else {
                    return "";
                }
            }

            public Object getCellEditorTypedValue(final CellEditor cellEditor, Object value) {
                String[] items = ((ComboBoxCellEditor) cellEditor).getItems();
                for (int i = 0; i < items.length; i++) {
                    if (items[i] == value) {
                        return i;
                    }
                }
                return -1;
            }
        };

        // //////////////////////////////////////////////////////////////////////////////////////

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("");
        column.setDefaultInternalValue("");
        column.setWidth(15);

        // //////////////////////////////////////////////////////////////////////////////////////
        // X Path Query

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("X Path Query");
        column.setId(ID_COLUMN_NAME);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<SchemaTarget, String>() {

            public String get(SchemaTarget bean) {
                return bean.getXPathQuery();
            }

            public void set(SchemaTarget bean, String value) {
                bean.setXPathQuery(value);
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////

        final TableViewerCreatorColumn nameColumn = column;
        final TextCellEditor cellEditor = new TextCellEditor(tableViewerCreator.getTable());
        cellEditor.addListener(new ICellEditorListener() {

            Text text = (Text) cellEditor.getControl();

            String lastValidValue = null;

            public void applyEditorValue() {
                ModifiedObjectInfo<SchemaTarget> modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
                // System.out.println("------- applyEditorValue=" + text.getText());
                Object bean = modifiedObjectInfo.getCurrentModifiedBean() != null ? modifiedObjectInfo.getCurrentModifiedBean()
                        : modifiedObjectInfo.getPreviousModifiedBean();
                fireEventIfValidColumnName(text.getText(), true, bean);
                lastValidValue = null;
            }

            public void cancelEditor() {
                ModifiedObjectInfo<SchemaTarget> modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
                String originalName = (String) modifiedObjectInfo.getOriginalPropertyBeanValue();
                text.setText(originalName);
                fireEventIfValidColumnName(originalName, false, modifiedObjectInfo.getCurrentModifiedBean());
                lastValidValue = null;
            }

            public void editorValueChanged(boolean oldValidState, boolean newValidState) {
                ModifiedObjectInfo<SchemaTarget> modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
                if (!newValidState) {
                    // MessageDialog.openError(composite.getShell(), "Error", cellEditor.getErrorMessage());
                } else {
                }
                String newValue = text.getText();
                fireEventIfValidColumnName(newValue, false, modifiedObjectInfo.getCurrentModifiedBean());
            }

            private void fireEventIfValidColumnName(final String newValue, boolean showAlertIfError,
                    final Object currentModifiedBean) {
                final ModifiedObjectInfo<SchemaTarget> modifiedObjectInfo = tableViewerCreator.getModifiedObjectInfo();
                String originalValue = (String) modifiedObjectInfo.getOriginalPropertyBeanValue();
                lastValidValue = lastValidValue != null ? lastValidValue : originalValue;

                int beanPosition = tableViewerCreator.getInputList().indexOf(currentModifiedBean);
                final String errorMessage = targetSchemaTableEditor.validateColumnName(newValue, beanPosition);
                // System.out.println(errorMessage);
                if (errorMessage == null) {
                    createAndFireEvent(lastValidValue, newValue);
                    text.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_WHITE));
                    lastValidValue = newValue;
                } else {
                    text.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_RED));
                    if (showAlertIfError) {
                        final Point selection = text.getSelection();
                        // System.out.println("setText:lastValidValue"+lastValidValue);
                        text.setText(lastValidValue);

                        new Thread() {

                            public void run() {

                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                text.getDisplay().asyncExec(new Runnable() {

                                    public void run() {
                                        MessageDialog.openError(composite.getShell(), "Error", errorMessage);
                                        // System.out.println("setText:" + newValue);
                                        final int columnPosition = tableViewerCreator.getColumns().indexOf(nameColumn);
                                        tableViewerCreator.getTableViewer().editElement(currentModifiedBean, columnPosition);
                                        text.setText(newValue);
                                        text.setSelection(selection.x, selection.y);
                                    }

                                });
                            };
                        }.start();
                    }
                }
            }

            private void createAndFireEvent(String previousValue, String newValue) {
                SchemaTarget currentModifiedObject = tableViewerCreator.getModifiedObjectInfo().getCurrentModifiedBean();
                ArrayList<Object> modifiedObjectList = new ArrayList<Object>(1);
                modifiedObjectList.add(currentModifiedObject);
                TargetSchemaEditorEvent event = new TargetSchemaEditorEvent(
                        TargetSchemaEditorEvent.TYPE.METADATA_NAME_VALUE_CHANGED);
                event.entries = modifiedObjectList;
                event.previousValue = previousValue;
                event.newValue = newValue;
                targetSchemaTableEditor.fireEvent(event);
            }

        });
        column.setCellEditor(cellEditor);

        column.setModifiable(true);
        column.setWeight(10);
        column.setMinimumWidth(50);
        column.setCellEditor(new TextCellEditor(table));

        // //////////////////////////////////////////////////////////////////////////////////////
        // Tag Name
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Tag Name");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<SchemaTarget, String>() {

            public String get(SchemaTarget bean) {
                return bean.getTagName();
            }

            public void set(SchemaTarget bean, String value) {
                bean.setTagName(value);
            }

        });
        column.setModifiable(true);
        column.setWeight(10);
        column.setMinimumWidth(50);
        column.setCellEditor(new TextCellEditor(table));

        // //////////////////////////////////////////////////////////////////////////////////////
        // Loop
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Loop");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<SchemaTarget, String>() {

            public String get(SchemaTarget bean) {
                return "" + bean.isIsBoucle();
            }

            public void set(SchemaTarget bean, String value) {
                bean.setIsBoucle(false);
            }

        });
        column.setModifiable(true);
        column.setWidth(50);
        column.setDisplayedValue("");
        // column.setTableEditorContent(new CheckboxTableEditorContent());
        // column.setCellEditor(new TextCellEditor(table), booleanValueAdapter);
        String[] bool = { "false", "true" };
        ComboBoxCellEditor comboTypeCellEditor = new ComboBoxCellEditor(table, bool);
        ((CCombo) comboTypeCellEditor.getControl()).setEditable(false);
        column.setCellEditor(comboTypeCellEditor, comboValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////
        // Loop limit
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Loop limit");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<SchemaTarget, Integer>() {

            public Integer get(SchemaTarget bean) {
                return bean.getLimitBoucle();
            }

            public void set(SchemaTarget bean, Integer value) {
                bean.setLimitBoucle(value.intValue());
            }

        });
        column.setModifiable(true);
        column.setWidth(30);
        column.setCellEditor(new TextCellEditor(table), intValueAdapter);
    }

    public TargetSchemaEditor2 getTargetSchemaEditor() {
        return this.targetSchemaTableEditor;
    }

    public void setTargetSchemaEditor(TargetSchemaEditor2 targetSchemaTableEditor) {
        this.targetSchemaTableEditor = targetSchemaTableEditor;
        if (targetSchemaTableEditor == null) {
            nameLabel.setText("");
            executeSelectionEvent = false;
            tableViewerCreator.init(new ArrayList());
            executeSelectionEvent = true;
            tableViewerCreator.layout();
        } else {
            nameLabel.setText(targetSchemaTableEditor.getTitleName());
            executeSelectionEvent = false;
            tableViewerCreator.init(targetSchemaTableEditor.getSchemaTargetList());
            executeSelectionEvent = true;
            tableViewerCreator.layout();
        }
    }

    public TableViewerCreator getTableViewerCreator() {
        return this.tableViewerCreator;
    }

    /**
     * DOC amaumont Comment method "setTableSelection".
     * 
     * @param selectionIndices
     */
    public void setTableSelection(int[] selectionIndices, boolean executeSelectionEvent) {
        this.executeSelectionEvent = executeSelectionEvent;
        this.tableViewerCreator.getTable().setSelection(selectionIndices);
        this.executeSelectionEvent = true;

    }

    public boolean isExecuteSelectionEvent() {
        return this.executeSelectionEvent;
    }

    public void setExecuteSelectionEvent(boolean executeSelectionEvent) {
        this.executeSelectionEvent = executeSelectionEvent;
    }

    /**
     * DOC ocarbone Comment method "setGridDataSize".
     * 
     * @param minimumWidth
     * @param minimumHeight
     */
    // public void setGridDataSize(final int minimumWidth, final int minimumHeight) {
    // this.composite.setSize(minimumWidth, minimumHeight);
    //
    // GridData gridData = new GridData(GridData.FILL_BOTH);
    // gridData.minimumWidth = minimumWidth;
    // gridData.minimumHeight = minimumHeight;
    // this.composite.setLayoutData(gridData);
    //
    // }
    public void setReadOnly(boolean b) {
        targetSchemaToolbarEditorView2.setReadOnly(b);
        this.tableViewerCreator.getTable().setEnabled(!b);
    }

    /**
     * DOC ocarbone Comment method "setDefaultLabel". setDefaultLabel determine the label to use when Add button is
     * used.
     * 
     * @param string
     */
    public void setDefaultLabel(String label) {
        targetSchemaToolbarEditorView2.setDefaultLabel(label);
    }

}
