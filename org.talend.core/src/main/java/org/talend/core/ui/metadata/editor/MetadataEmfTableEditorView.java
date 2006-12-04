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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractExtendedToolbar;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.swt.tableviewer.tableeditor.CheckboxTableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.editor.MetadataEmfTableEditor;
import org.talend.core.ui.images.EImage;
import org.talend.core.ui.images.ImageProvider;

/**
 * DOC amaumont class global comment. Detailled comment <br/> TGU same purpose as MetadataTableEditorView but uses EMF
 * model directly
 * 
 * $Id$
 * 
 */
public class MetadataEmfTableEditorView extends AbstractDataTableEditorView<MetadataColumn> {

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME";

    private boolean showDbTypeColumn = false;

    /**
     * MetadataTableEditorView2 constructor comment.
     * 
     * @param parent
     * @param style
     * @param showDbTypeColumn
     */
    public MetadataEmfTableEditorView(Composite parent, int style, boolean showDbTypeColumn) {
        super(parent, style);
        this.showDbTypeColumn = showDbTypeColumn;
    }

    /**
     * DOC amaumont MetadataEmfTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     */
    public MetadataEmfTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<MetadataColumn> extendedTableModel, boolean readOnly, boolean toolbarVisible) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible);
    }

    /**
     * DOC amaumont MetadataEmfTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     */
    public MetadataEmfTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<MetadataColumn> extendedTableModel) {
        super(parentComposite, mainCompositeStyle, extendedTableModel);
    }

    /**
     * DOC amaumont MetadataEmfTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     */
    public MetadataEmfTableEditorView(Composite parentComposite, int mainCompositeStyle) {
        super(parentComposite, mainCompositeStyle);
    }

    
    
    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#initToolBar()
     */
    @Override
    protected AbstractExtendedToolbar initToolBar() {
        return new MetadataEmfToolbarEditor(getMainComposite(), SWT.NONE, this.getExtendedTableViewer());
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#setTableViewerCreatorOptions(org.talend.commons.ui.swt.tableviewer.TableViewerCreator)
     */
    @Override
    protected void setTableViewerCreatorOptions(TableViewerCreator<MetadataColumn> newTableViewerCreator) {
        super.setTableViewerCreatorOptions(newTableViewerCreator);
        newTableViewerCreator.setFirstVisibleColumnIsSelection(true);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedTableViewer#createColumns(org.talend.commons.ui.swt.tableviewer.TableViewerCreator,
     * org.eclipse.swt.widgets.Table)
     */
    @Override
    protected void createColumns(final TableViewerCreator<MetadataColumn> tableViewerCreator, final Table table) {

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
        final Image imageEmpty = org.talend.core.ui.images.ImageProvider.getImage(EImage.EMPTY);
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
            }

            @Override
            public String validateValue(String newValue, int beanPosition) {
                return getMetadataEditor().validateColumnName(newValue, beanPosition);
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

    public MetadataEmfTableEditor getMetadataEditor() {
        return (MetadataEmfTableEditor) getExtendedTableModel();
    }

    public void setMetadataEditor(MetadataEmfTableEditor metadataTableEditor) {
        setExtendedTableModel(metadataTableEditor);
    }

    public TableViewerCreator<MetadataColumn> getTableViewerCreator() {
        return getExtendedTableViewer().getTableViewerCreator();
    }

    /**
     * DOC ocarbone Comment method "setGridDataSize".
     * 
     * @param minimumWidth
     * @param minimumHeight
     */
    public void setGridDataSize(final int minimumWidth, final int minimumHeight) {
        getMainComposite().setSize(minimumWidth, minimumHeight);

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.minimumWidth = minimumWidth;
        gridData.minimumHeight = minimumHeight;
        getMainComposite().setLayoutData(gridData);

    }

}
