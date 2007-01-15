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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractExtendedToolbar;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.tableviewer.CellEditorValueAdapterFactory;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.swt.tableviewer.tableeditor.CheckboxTableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.model.temp.ECodeLanguage;

/**
 * MetadataTableEditorView2 must be used.
 * 
 * $Id$
 * 
 */
public class MetadataTableEditorView extends AbstractDataTableEditorView<IMetadataColumn> {

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME";

    public static final String ID_COLUMN_KEY = "ID_COLUMN_KEY";

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     * 
     * @param parent
     * @param style
     * @param metadataTableEditor
     */
    public MetadataTableEditorView(Composite parent, int style, MetadataTableEditor metadataTableEditor) {
        super(parent, style, metadataTableEditor);
    }

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     */
    public MetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<IMetadataColumn> extendedTableModel, boolean readOnly, boolean toolbarVisible) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible, true);
    }

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     */
    public MetadataTableEditorView(Composite parentComposite, int mainCompositeStyle) {
        super(parentComposite, mainCompositeStyle);
    }

    public MetadataTableEditor getMetadataTableEditor() {
        return (MetadataTableEditor) getExtendedTableModel();
    }

    public void setMetadataTableEditor(MetadataTableEditor metadataTableEditor) {
        setExtendedTableModel(metadataTableEditor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#setTableViewerCreatorOptions(org.talend.commons.ui.swt.tableviewer.TableViewerCreator)
     */
    @Override
    protected void setTableViewerCreatorOptions(TableViewerCreator<IMetadataColumn> newTableViewerCreator) {
        super.setTableViewerCreatorOptions(newTableViewerCreator);
        newTableViewerCreator.setFirstVisibleColumnIsSelection(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#initToolBar()
     */
    @Override
    protected AbstractExtendedToolbar initToolBar() {
        return new MetadataToolbarEditorView(getMainComposite(), SWT.NONE, this.getExtendedTableViewer());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedTableViewer#createColumns(org.talend.commons.ui.swt.tableviewer.TableViewerCreator,
     * org.eclipse.swt.widgets.Table)
     */
    @Override
    protected void createColumns(final TableViewerCreator<IMetadataColumn> tableViewerCreator, final Table table) {
        CellEditorValueAdapter comboValueAdapter = null;
        String dbms = null;
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY);
        ECodeLanguage codeLanguage = repositoryContext.getProject().getLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            comboValueAdapter = CellEditorValueAdapterFactory.getComboAdapter("String");
            dbms = MetadataTalendType.LANGUAGE_JAVA;
        } else {
            comboValueAdapter = CellEditorValueAdapterFactory.getComboAdapter();
            dbms = MetadataTalendType.TALENDDEFAULT;
        }
        
        String[] arrayTalendTypes = new String[0];
        try {
            arrayTalendTypes = MetadataTalendType.loadTalendTypes(dbms, false);
        } catch (NoClassDefFoundError e) {
            // shouln't be happend
            e.printStackTrace();
        } catch (ExceptionInInitializerError e) {
            // shouln't be happend
            e.printStackTrace();
        }


        CellEditorValueAdapter positiveIntValueAdapter = CellEditorValueAdapterFactory.getPositiveIntAdapter();


        // //////////////////////////////////////////////////////////////////////////////////////

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("");
        column.setDefaultInternalValue("");
        column.setWidth(15);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setId(ID_COLUMN_NAME);
        column.setTitle("Column");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getLabel();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setLabel(value);
            }

        });
        final Image imageKey = ImageProvider.getImage(EImage.KEY_ICON);
        final Image imageEmpty = org.talend.commons.ui.image.ImageProvider.getImage(EImage.EMPTY);
        column.setImageProvider(new IColumnImageProvider() {

            public Image getImage(Object element) {
                IMetadataColumn metadataColumn = (MetadataColumn) element;
                if (metadataColumn.isKey()) {
                    return imageKey;
                } else {
                    return imageEmpty;
                }
            }

        });
        column.setWeight(25);
        column.setModifiable(!isReadOnly());
        column.setMinimumWidth(45);
        final TextCellEditor cellEditor = new TextCellEditor(tableViewerCreator.getTable());
        cellEditor.addListener(new DialogErrorForCellEditorListener(cellEditor, column) {

            @Override
            public void newValidValueTyped(int itemIndex, Object previousValue, Object newValue, CELL_EDITOR_STATE state) {
            }

            @Override
            public String validateValue(String newValue, int beanPosition) {
                return getMetadataTableEditor().validateColumnName(newValue, beanPosition);
            }

        });
        column.setCellEditor(cellEditor);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Key");
        column.setId(ID_COLUMN_KEY);
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Boolean>() {

            public Boolean get(IMetadataColumn bean) {
                return new Boolean(bean.isKey());
            }

            public void set(IMetadataColumn bean, Boolean value) {
                bean.setKey(value);
            }

        });
        column.setWidth(35);
        column.setDisplayedValue("");
        CheckboxTableEditorContent checkboxTableEditorContent = new CheckboxTableEditorContent(isReadOnly());
        column.setTableEditorContent(checkboxTableEditorContent);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Type");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getTalendType();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setTalendType(value);
            }

        });
        column.setModifiable(!isReadOnly());
        column.setWeight(10);
        column.setMinimumWidth(30);
        column.setCellEditor(new ComboBoxCellEditor(tableViewerCreator.getTable(), arrayTalendTypes), comboValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Length");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Integer>() {

            public Integer get(IMetadataColumn bean) {
                return bean.getLength();
            }

            public void set(IMetadataColumn bean, Integer value) {
                bean.setLength(value);
            }

        });
        column.setModifiable(!isReadOnly());
        column.setWidth(55);
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()), positiveIntValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Precision");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Integer>() {

            public Integer get(IMetadataColumn bean) {
                return bean.getPrecision();
            }

            public void set(IMetadataColumn bean, Integer value) {
                bean.setPrecision(value);
            }

        });
        column.setModifiable(!isReadOnly());
        column.setWidth(60);
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()), positiveIntValueAdapter);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Nullable");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, Boolean>() {

            public Boolean get(IMetadataColumn bean) {
                return new Boolean(bean.isNullable());
            }

            public void set(IMetadataColumn bean, Boolean value) {
                bean.setNullable(value);
            }

        });
        column.setModifiable(!isReadOnly());
        column.setWidth(56);
        column.setDisplayedValue("");
        column.setTableEditorContent(new CheckboxTableEditorContent(isReadOnly()));

        // //////////////////////////////////////////////////////////////////////////////////////

        // column = new TableViewerCreatorColumn(tableViewerCreator);
        // column.setTitle("Default");
        // column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {
        //
        // public String get(IMetadataColumn bean) {
        // return bean.getDefault();
        // }
        //
        // public void set(IMetadataColumn bean, String value) {
        // bean.setDefault(value);
        // }
        //
        // });
        // column.setWeight(8);
        // column.setModifiable(!isReadOnly());
        // column.setMinimumWidth(30);
        // column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle("Comment");
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getComment();
            }

            public void set(IMetadataColumn bean, String value) {
                bean.setComment(value);
            }

        });
        column.setWeight(10);
        column.setModifiable(!isReadOnly());
        column.setMinimumWidth(20);
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));

    }

    public MetadataToolbarEditorView getToolBar() {
        return (MetadataToolbarEditorView) getExtendedToolbar();
    }

}
