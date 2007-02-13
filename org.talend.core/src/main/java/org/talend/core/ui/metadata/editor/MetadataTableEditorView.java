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

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.tableviewer.CellEditorValueAdapterFactory;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnColorProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.swt.tableviewer.tableeditor.CheckboxTableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.editor.MetadataTableEditor;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter;

/**
 * MetadataTableEditorView2 must be used.
 * 
 * $Id$
 * 
 */
public class MetadataTableEditorView extends AbstractDataTableEditorView<IMetadataColumn> {

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME"; //$NON-NLS-1$

    public static final String ID_COLUMN_KEY = "ID_COLUMN_KEY"; //$NON-NLS-1$

    public static final String ID_COLUMN_TYPE = "ID_COLUMN_TYPE"; //$NON-NLS-1$

    public static final String ID_COLUMN_NULLABLE = "ID_COLUMN_NULLABLE"; //$NON-NLS-1$

    public static final String ID_COLUMN_PATTERN = "ID_COLUMN_PATTERN"; //$NON-NLS-1$

    private boolean showDbTypeColumn;

    private boolean showDbTypeColumnAtLeftPosition;

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
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     * @param labelVisible
     * @param initGraphicsComponents
     */
    public MetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<IMetadataColumn> extendedTableModel, boolean readOnly, boolean toolbarVisible, boolean labelVisible,
            boolean initGraphicsComponents) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible, labelVisible, initGraphicsComponents);
    }

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     */
    public MetadataTableEditorView() {
        super();
    }

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param initGraphicsComponents
     */
    public MetadataTableEditorView(Composite parentComposite, int mainCompositeStyle) {
        super(parentComposite, mainCompositeStyle, false);
    }

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     * @param labelVisible
     */
    public MetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<IMetadataColumn> extendedTableModel, boolean readOnly, boolean toolbarVisible, boolean labelVisible) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible, labelVisible);
    }

    /**
     * DOC amaumont MetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     */
    public MetadataTableEditorView(Composite parentComposite, int mainCompositeStyle, ExtendedTableModel<IMetadataColumn> extendedTableModel) {
        super(parentComposite, mainCompositeStyle, extendedTableModel);
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
    protected ExtendedToolbarView initToolBar() {
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
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();

        IBeanPropertyAccessors<IMetadataColumn, Boolean> nullableAccessors = new IBeanPropertyAccessors<IMetadataColumn, Boolean>() {

            public Boolean get(IMetadataColumn bean) {
                return new Boolean(bean.isNullable());
            }

            public void set(IMetadataColumn bean, Boolean value) {
                bean.setNullable(value);
            }

        };

        if (codeLanguage == ECodeLanguage.JAVA) {
            comboValueAdapter = new JavaTypeComboValueAdapter<IMetadataColumn>(JavaTypesManager.getDefaultJavaType(), nullableAccessors);
        } else if (codeLanguage == ECodeLanguage.PERL) {
            comboValueAdapter = CellEditorValueAdapterFactory.getComboAdapter();
        }

        String[] arrayTalendTypes = new String[0];
        try {
            arrayTalendTypes = MetadataTalendType.getTalendTypesLabels();
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
        column.setTitle(""); //$NON-NLS-1$
        column.setDefaultInternalValue(""); //$NON-NLS-1$
        column.setWidth(15);

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setId(ID_COLUMN_NAME);
        column.setTitle(Messages.getString("MetadataTableEditorView.ColumnTitle")); //$NON-NLS-1$
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
        column.setTitle(Messages.getString("MetadataTableEditorView.KeyTitle")); //$NON-NLS-1$
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
        column.setDisplayedValue(""); //$NON-NLS-1$
        CheckboxTableEditorContent keyCheckbox = new CheckboxTableEditorContent(isReadOnly());
        column.setTableEditorContent(keyCheckbox);
        keyCheckbox.setToolTipText(Messages.getString("MetadataTableEditorView.KeyTitle")); //$NON-NLS-1$

        // //////////////////////////////////////////////////////////////////////////////////////

        if (showDbTypeColumn) {
            if (showDbTypeColumnAtLeftPosition) {
                initDbTypeColumn(tableViewerCreator);
                initTalendTypeColumn(tableViewerCreator, comboValueAdapter, arrayTalendTypes);
            } else {
                initTalendTypeColumn(tableViewerCreator, comboValueAdapter, arrayTalendTypes);
                initDbTypeColumn(tableViewerCreator);
            }
        } else {
            initTalendTypeColumn(tableViewerCreator, comboValueAdapter, arrayTalendTypes);
        }

        // final CellEditorValueAdapter finalComboValueAdapter = comboValueAdapter;
        // final String[] finalArrayTalendTypes = arrayTalendTypes;
        //        
        // column.setTableEditorContent(new TableEditorContent() {
        //
        // /* (non-Javadoc)
        // * @see
        // org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorContent#initialize(org.eclipse.swt.widgets.Table,
        // org.eclipse.swt.custom.TableEditor, org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn,
        // java.lang.Object, java.lang.Object)
        // */
        // @Override
        // public Control initialize(Table table, TableEditor tableEditor, TableViewerCreatorColumn currentColumn,
        // Object currentRowObject, Object currentCellValue) {
        // CCombo combo = new CCombo(table, SWT.FLAT | SWT.READ_ONLY);
        // combo.setItems(finalArrayTalendTypes);
        // // combo.setText(String.valueOf(currentCellValue));
        // combo.computeSize(SWT.DEFAULT, table.getItemHeight());
        // // Set attributes of the editor
        // tableEditor.grabHorizontal = true;
        // tableEditor.minimumHeight = combo.getSize().y - 4;
        // tableEditor.minimumWidth = combo.getSize().x;
        // return combo;
        // }
        //            
        // });

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        String nullableTitle = Messages.getString("MetadataTableEditorView.NullableTitle"); //$NON-NLS-1$
        column.setTitle(nullableTitle);
        column.setId(ID_COLUMN_NULLABLE);
        column.setBeanPropertyAccessors(nullableAccessors);
        column.setModifiable(!isReadOnly());
        column.setWidth(56);
        column.setDisplayedValue(""); //$NON-NLS-1$
        CheckboxTableEditorContent nullableCheckbox = new CheckboxTableEditorContent(isReadOnly());
        nullableCheckbox.setToolTipText(nullableTitle);
        column.setTableEditorContent(nullableCheckbox);
        column.setToolTipHeader(nullableTitle);

        // //////////////////////////////////////////////////////////////////////////////////////
        if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA) {
            column = new TableViewerCreatorColumn(tableViewerCreator);
            String patternTitle = Messages.getString("MetadataTableEditorView.PatternTitle"); //$NON-NLS-1$
            column.setTitle(patternTitle);
            column.setId(ID_COLUMN_PATTERN);
            column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

                public String get(IMetadataColumn bean) {
                    return bean.getPattern();
                }

                public void set(IMetadataColumn bean, String value) {
                    bean.setPattern(value);
                }

            });
            column.setModifiable(!isReadOnly());
            column.setToolTipHeader(patternTitle);
            column.setWeight(16);
            TextCellEditor patternCellEditor = new TextCellEditor(tableViewerCreator.getTable());
            column.setCellEditor(patternCellEditor);
        }

        // //////////////////////////////////////////////////////////////////////////////////////

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.LengthTitle")); //$NON-NLS-1$
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
        column.setTitle(Messages.getString("MetadataTableEditorView.PrecisionTitle")); //$NON-NLS-1$
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
        column.setTitle(Messages.getString("MetadataTableEditorView.CommentTitle")); //$NON-NLS-1$
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

        if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA) {
            configureCellModifierForJava(tableViewerCreator);
        }

    }

    /**
     * DOC amaumont Comment method "initTalendTypeColumn".
     * 
     * @param tableViewerCreator
     * @param comboValueAdapter
     * @param arrayTalendTypes
     */
    private void initTalendTypeColumn(final TableViewerCreator<IMetadataColumn> tableViewerCreator,
            CellEditorValueAdapter comboValueAdapter, String[] arrayTalendTypes) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.TypleTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_TYPE);
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
        ComboBoxCellEditor typeComboEditor = new ComboBoxCellEditor(tableViewerCreator.getTable(), arrayTalendTypes, SWT.READ_ONLY);
        CCombo typeCombo = (CCombo) typeComboEditor.getControl();
        typeCombo.setEditable(false);
        column.setCellEditor(typeComboEditor, comboValueAdapter);
    }

    /**
     * DOC amaumont Comment method "initDbTypeColumn".
     * 
     * @param tableViewerCreator
     */
    private void initDbTypeColumn(final TableViewerCreator<IMetadataColumn> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataEmfTableEditorView.DBTypeTitle")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

            public String get(IMetadataColumn bean) {
                return bean.getType();
            }

            public void set(IMetadataColumn bean, String value) {
                throw new UnsupportedOperationException();
            }

        });
        column.setModifiable(false);
        column.setWeight(10);
        column.setMinimumWidth(60);
        final Color readonlyColor = tableViewerCreator.getTable().getDisplay().getSystemColor(SWT.COLOR_GRAY);

        column.setColorProvider(new IColumnColorProvider() {

            public Color getBackgroundColor(Object bean) {
                return readonlyColor;
            }

            public Color getForegroundColor(Object bean) {
                return null;
            }

        });
    }

    public MetadataToolbarEditorView getToolBar() {
        return (MetadataToolbarEditorView) getExtendedToolbar();
    }

    protected void configureCellModifierForJava(TableViewerCreator<IMetadataColumn> tableViewerCreator) {
        ICellModifier cellModifier = new MetadataTableCellModifierForJava(tableViewerCreator);
        tableViewerCreator.setCellModifier(cellModifier);
    }

    /**
     * . <br/>
     * 
     * $Id$
     * 
     */
    class MetadataTableCellModifierForJava extends DefaultCellModifier {

        /**
         * DOC amaumont MetadataTableCellModifier constructor comment.
         * 
         * @param tableViewerCreator
         */
        public MetadataTableCellModifierForJava(TableViewerCreator tableViewerCreator) {
            super(tableViewerCreator);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier#canModify(java.lang.Object,
         * java.lang.String)
         */
        @Override
        public boolean canModify(Object element, String property) {
            IMetadataColumn metadataColumn = (IMetadataColumn) element;
            boolean typeIsDate = JavaTypesManager.DATE.getId().equals(metadataColumn.getTalendType());
            boolean columnIsPattern = ID_COLUMN_PATTERN.equals(property);
            return super.canModify(element, property) && (columnIsPattern && typeIsDate || !columnIsPattern);
        }

    }

    public void setDbTypeColumnsState(boolean showDbTypeColumn, boolean showDbTypeColumnAtLeftPosition) {
        this.showDbTypeColumn = showDbTypeColumn;
        this.showDbTypeColumnAtLeftPosition = showDbTypeColumnAtLeftPosition;
    }

}
