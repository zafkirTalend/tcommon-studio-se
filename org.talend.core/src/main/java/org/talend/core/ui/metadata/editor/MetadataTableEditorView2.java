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
package org.talend.core.ui.metadata.editor;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LINE_SELECTION;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.SHOW_ROW_SELECTION;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.swt.tableviewer.tableeditor.CheckboxTableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.editor.MetadataEditor2;
import org.talend.core.model.metadata.editor.MetadataEditorEvent;
import org.talend.core.model.metadata.editor.MetadataEditorEvent.TYPE;
import org.talend.core.ui.EImage;
import org.talend.core.ui.ImageProvider;

/**
 * DOC amaumont class global comment. Detailled comment <br/> TGU same purpose as MetadataTableEditorView but uses EMF
 * model directly
 * 
 * $Id$
 * 
 */
public class MetadataTableEditorView2 {

    private Label nameLabel;

    private TableViewerCreator<MetadataColumn> tableViewerCreator;

    private Composite composite;

    private MetadataEditor2 metadataTableEditor;

    private boolean executeSelectionEvent = true;

    private MetadataToolbarEditorView2 metadataToolbarEditorView2;

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME";

    private boolean showDbTypeColumn = false;

    public MetadataTableEditorView2(Composite parent, int style, MetadataEditor2 metadataEditor) {
        this(parent, style, false);
        setMetadataEditor(metadataEditor);
    }

    /**
     * MetadataTableEditorView2 constructor comment.
     * 
     * @param parent
     * @param style
     * @param showDbTypeColumn
     */
    public MetadataTableEditorView2(Composite parent, int style, boolean showDbTypeColumn) {
        super();
        this.showDbTypeColumn = showDbTypeColumn;
        composite = new Composite(parent, style);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);

        createComponents();
        addListeners();
    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
    }

    private void createComponents() {
        nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText("");
        nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        addMetadataTable();
        addMetadataToolbar();

    }

    /**
     * DOC amaumont Comment method "addMetadataToolbar".
     */
    private void addMetadataToolbar() {
        metadataToolbarEditorView2 = new MetadataToolbarEditorView2(composite, SWT.NONE, this);
    }

    private void addMetadataTable() {

        tableViewerCreator = new TableViewerCreator<MetadataColumn>(composite);
        tableViewerCreator.setLayout(new RowLayout(SWT.HORIZONTAL));

        tableViewerCreator.setAllColumnsResizable(true);
        tableViewerCreator.setBorderVisible(true);
        tableViewerCreator.setLayoutMode(LAYOUT_MODE.FILL_HORIZONTAL);
        tableViewerCreator.setAdjustWidthValue(-15);
        tableViewerCreator.setFirstColumnMasked(true);
        tableViewerCreator.setFirstVisibleColumnIsSelection(true);
        tableViewerCreator.setUseCustomItemColoring(true);

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

        String[] arrayTalendTypes = new String[0];
        try {
            arrayTalendTypes = MetadataTalendType.loadTalendTypes("TALENDDEFAULT", false);
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }

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

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Column");
        column.setId(ID_COLUMN_NAME);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getLabel();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setLabel(value);
            }

        });
        final Image imageKey = ImageProvider.getImage(EImage.KEY_ICON);
        final Image imageEmpty = org.talend.core.ui.ImageProvider.getImage(EImage.EMPTY);
        column.setImageProvider(new IColumnImageProvider() {

            public Image getImage(Object element) {
                MetadataColumn metadataColumn = (MetadataColumn) element;
                if (metadataColumn.isKey()) {
                    return imageKey;
                } else {
                    return imageEmpty;
                }
            }

        });
        column.setWeight(20);
        column.setModifiable(true);
        column.setMinimumWidth(30);

        // //////////////////////////////////////////////////////////////////////////////////////

        final TextCellEditor cellEditor = new TextCellEditor(tableViewerCreator.getTable());
        cellEditor.addListener(new DialogErrorForCellEditorListener(cellEditor, column) {

            @Override
            public void newValidValueTyped(int itemIndex, Object previousValue, Object newValue, CELL_EDITOR_STATE state) {
                Object currentModifiedObject = tableViewerCreator.getModifiedObjectInfo().getCurrentModifiedBean();
                ArrayList modifiedObjectList = new ArrayList(1);
                modifiedObjectList.add(currentModifiedObject);
                MetadataEditorEvent event = new MetadataEditorEvent(MetadataEditorEvent.TYPE.METADATA_NAME_VALUE_CHANGED);
                event.entries = modifiedObjectList;
                event.previousValue = previousValue;
                event.newValue = newValue;
                event.state = state;
                metadataTableEditor.fireEvent(event);
            }

            @Override
            public String validateValue(String newValue, int beanPosition) {
                return metadataTableEditor.validateColumnName(newValue, beanPosition);
            }

        });
        column.setCellEditor(cellEditor);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Key");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, Boolean>() {

            public Boolean get(MetadataColumn bean) {
                return new Boolean(bean.isKey());
            }

            public void set(MetadataColumn bean, Boolean value) {
                bean.setKey(value);
                tableViewerCreator.getTableViewer().refresh(bean);
                MetadataEditorEvent metadataEditorEvent = new MetadataEditorEvent(TYPE.METADATA_KEY_VALUE_CHANGED);
                metadataEditorEvent.entries.add(bean);
                metadataEditorEvent.entriesIndices = new int[] { metadataTableEditor.getMetadataColumnList().indexOf(bean) };
                metadataTableEditor.fireEvent(metadataEditorEvent);
            }

        });
        column.setWidth(35);
        column.setDisplayedValue("");
        column.setTableEditorContent(new CheckboxTableEditorContent());

        if (showDbTypeColumn) {
            // Initial Type
            column = new TableViewerCreatorColumn(tableViewerCreator);
            column.setTitle("Db Type");
            column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, String>() {

                public String get(MetadataColumn bean) {
                    return bean.getSourceType();
                }

                public void set(MetadataColumn bean, String value) {
                    bean.setSourceType(value);
                }

            });
            column.setModifiable(false);
            column.setWeight(10);
            column.setMinimumWidth(60);
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        // Talend Type
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Type");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getTalendType();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setTalendType(value);
            }

        });
        column.setModifiable(true);
        column.setWeight(10);
        column.setMinimumWidth(20);
        ComboBoxCellEditor comboTypeCellEditor = new ComboBoxCellEditor(table, arrayTalendTypes);
        ((CCombo) comboTypeCellEditor.getControl()).setEditable(false);
        column.setCellEditor(comboTypeCellEditor, comboValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Length");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, Integer>() {

            public Integer get(MetadataColumn bean) {
                return bean.getLength();
            }

            public void set(MetadataColumn bean, Integer value) {
                bean.setLength(value);
            }

        });
        column.setModifiable(true);
        column.setWidth(50);
        column.setCellEditor(new TextCellEditor(table), intValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Precision");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, Integer>() {

            public Integer get(MetadataColumn bean) {
                return bean.getPrecision();
            }

            public void set(MetadataColumn bean, Integer value) {
                bean.setPrecision(value);
            }

        });
        column.setModifiable(true);
        column.setWidth(55);
        column.setCellEditor(new TextCellEditor(table), intValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Nullable");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, Boolean>() {

            public Boolean get(MetadataColumn bean) {
                return new Boolean(bean.isNullable());
            }

            public void set(MetadataColumn bean, Boolean value) {
                bean.setNullable(value);
            }

        });
        column.setModifiable(true);
        column.setWidth(55);
        column.setDisplayedValue("");
        column.setTableEditorContent(new CheckboxTableEditorContent());

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Default");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getDefaultValue();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setDefaultValue(value);
            }

        });
        column.setWeight(10);
        column.setModifiable(true);
        column.setMinimumWidth(20);
        column.setCellEditor(new TextCellEditor(table));

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Comment");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getComment();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setComment(value);
            }

        });
        column.setWeight(15);
        column.setModifiable(true);
        column.setMinimumWidth(20);
        column.setCellEditor(new TextCellEditor(table));

    }

    public MetadataEditor2 getMetadataEditor() {
        return this.metadataTableEditor;
    }

    public void setMetadataEditor(MetadataEditor2 metadataTableEditor) {
        this.metadataTableEditor = metadataTableEditor;
        if (metadataTableEditor == null) {
            nameLabel.setText("");
            executeSelectionEvent = false;
            tableViewerCreator.init(new ArrayList());
            executeSelectionEvent = true;
            tableViewerCreator.layout();
        } else {
            nameLabel.setText(metadataTableEditor.getTitleName());
            executeSelectionEvent = false;
            tableViewerCreator.init(metadataTableEditor.getMetadataColumnList());
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
    public void setGridDataSize(final int minimumWidth, final int minimumHeight) {
        this.composite.setSize(minimumWidth, minimumHeight);

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.minimumWidth = minimumWidth;
        gridData.minimumHeight = minimumHeight;
        this.composite.setLayoutData(gridData);

    }

    public void setReadOnly(boolean b) {
        metadataToolbarEditorView2.setReadOnly(b);
        this.tableViewerCreator.getTable().setEnabled(!b);
    }

    /**
     * DOC ocarbone Comment method "setDefaultLabel". setDefaultLabel determine the label to use when Add button is
     * used.
     * 
     * @param string
     */
    public void setDefaultLabel(String label) {
        metadataToolbarEditorView2.setDefaultLabel(label);
    }

}
