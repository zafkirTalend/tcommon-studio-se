// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.proposal.ContentProposalAdapterExtended;
import org.talend.commons.ui.swt.proposal.TextCellEditorWithProposal;
import org.talend.commons.ui.swt.tableviewer.CellEditorValueAdapterFactory;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.ColumnCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnColorProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnLabelProvider;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.swt.tableviewer.tableeditor.CheckboxTableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.TypesManager;
import org.talend.core.ui.proposal.JavaSimpleDateFormatProposalProvider;
import org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <B> Type of beans in table
 */
public abstract class AbstractMetadataTableEditorView<B> extends AbstractDataTableEditorView<B> {

    private static Logger log = Logger.getLogger(AbstractMetadataTableEditorView.class);

    public static final Color READONLY_CELL_BG_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME"; //$NON-NLS-1$

    public static final String ID_COLUMN_KEY = "ID_COLUMN_KEY"; //$NON-NLS-1$

    public static final String ID_COLUMN_TYPE = "ID_COLUMN_TYPE"; //$NON-NLS-1$

    public static final String ID_COLUMN_DBTYPE = "ID_COLUMN_DBTYPE"; //$NON-NLS-1$

    public static final String ID_COLUMN_NULLABLE = "ID_COLUMN_NULLABLE"; //$NON-NLS-1$

    public static final String ID_COLUMN_PATTERN = "ID_COLUMN_PATTERN"; //$NON-NLS-1$

    public static final String ID_COLUMN_DEFAULT = "ID_COLUMN_DEFAULT"; //$NON-NLS-1$

    public static final String ID_COLUMN_COMMENT = "ID_COLUMN_COMMENT"; //$NON-NLS-1$

    public static final String ID_COLUMN_LENGHT = "ID_COLUMN_LENGHT"; //$NON-NLS-1$

    public static final String ID_COLUMN_PRECISION = "ID_COLUMN_PRECISION"; //$NON-NLS-1$

    protected boolean showDbTypeColumn;

    protected boolean showTalendTypeColumn = true;

    protected boolean showDbTypeColumnAtLeftPosition;

    protected boolean dbTypeColumnWritable;

    private boolean showPatternColumn = true;

    protected String dbmsId;

    /**
     * DOC amaumont AbstractMetadataTableEditorView constructor comment.
     */
    public AbstractMetadataTableEditorView() {
        super();
    }

    /**
     * DOC amaumont AbstractMetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param initGraphicsComponents
     */
    public AbstractMetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            boolean initGraphicsComponents) {
        super(parentComposite, mainCompositeStyle, initGraphicsComponents);
    }

    /**
     * DOC amaumont AbstractMetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     * @param labelVisible
     * @param initGraphicsComponents
     */
    public AbstractMetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<B> extendedTableModel, boolean readOnly, boolean toolbarVisible, boolean labelVisible,
            boolean initGraphicsComponents) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible, labelVisible,
                initGraphicsComponents);
    }

    /**
     * DOC amaumont AbstractMetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     * @param labelVisible
     */
    public AbstractMetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<B> extendedTableModel, boolean readOnly, boolean toolbarVisible, boolean labelVisible) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible, labelVisible);
    }

    /**
     * DOC amaumont AbstractMetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     */
    public AbstractMetadataTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<B> extendedTableModel) {
        super(parentComposite, mainCompositeStyle, extendedTableModel);
    }

    /**
     * DOC amaumont AbstractMetadataTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     */
    public AbstractMetadataTableEditorView(Composite parentComposite, int mainCompositeStyle) {
        super(parentComposite, mainCompositeStyle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#createColumns(org.talend.commons.ui.swt.tableviewer.TableViewerCreator,
     * org.eclipse.swt.widgets.Table)
     */
    @Override
    protected void createColumns(TableViewerCreator<B> tableViewerCreator, Table table) {
        // //////////////////////////////////////////////////////////////////////////////////////

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(""); //$NON-NLS-1$
        column.setDefaultInternalValue(""); //$NON-NLS-1$
        column.setWidth(15);

        // //////////////////////////////////////////////////////////////////////////////////////

        configureNameColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configureKeyColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configureTypeColumns(tableViewerCreator);

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

        configureNullableColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configurePatternColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configureLengthColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configurePrecisionColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configureDefaultColumn(tableViewerCreator);

        // //////////////////////////////////////////////////////////////////////////////////////

        configureCommentColumn(tableViewerCreator);

    }

    /**
     * DOC amaumont Comment method "configureCommentColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configureCommentColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.CommentTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.CommentTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_COMMENT);
        column.setBeanPropertyAccessors(getCommentAccessor());
        column.setWeight(10);
        column.setModifiable(!isReadOnly());
        column.setMinimumWidth(20);
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));
    }

    /**
     * DOC amaumont Comment method "getCommentAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, String> getCommentAccessor();

    /**
     * DOC amaumont Comment method "configureDefaultColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configureDefaultColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.DefaultTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.DefaultTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_DEFAULT);
        column.setBeanPropertyAccessors(getDefaultValueAccessor());
        column.setWeight(8);
        column.setModifiable(!isReadOnly());
        column.setMinimumWidth(30);
        column.setDefaultInternalValue("");
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));
    }

    /**
     * DOC amaumont Comment method "getDefaultValueAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, String> getDefaultValueAccessor();

    /**
     * DOC amaumont Comment method "configurePrecisionColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configurePrecisionColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.PrecisionTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.PrecisionTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_PRECISION);
        column.setBeanPropertyAccessors(getPrecisionAccessor());
        column.setModifiable(!isReadOnly());
        column.setWeight(10);
        column.setMinimumWidth(10);
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()), CellEditorValueAdapterFactory
                .getPositiveIntAdapter());
    }

    /**
     * DOC amaumont Comment method "getPrecisionAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, Integer> getPrecisionAccessor();

    /**
     * DOC amaumont Comment method "configureLengthColumn".
     * 
     * @param tableViewerCreator
     * @param positiveIntValueAdapter
     */
    protected void configureLengthColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.LengthTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.LengthTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_LENGHT);
        column.setBeanPropertyAccessors(getLengthAccessor());
        column.setModifiable(!isReadOnly());
        column.setWeight(10);
        column.setMinimumWidth(10);
        column.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()), CellEditorValueAdapterFactory
                .getPositiveIntAdapter());
    }

    /**
     * DOC amaumont Comment method "getLengthAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, Integer> getLengthAccessor();

    /**
     * DOC amaumont Comment method "configurePatternColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configurePatternColumn(TableViewerCreator<B> tableViewerCreator) {
        if (LanguageManager.getCurrentLanguage() == ECodeLanguage.JAVA && showPatternColumn) {
            final TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
            String patternTitle = Messages.getString("MetadataTableEditorView.PatternTitle"); //$NON-NLS-1$
            column.setTitle(patternTitle);
            column.setToolTipHeader(patternTitle);
            column.setId(ID_COLUMN_PATTERN);
            column.setBeanPropertyAccessors(getPatternAccessor());
            column.setModifiable(!isReadOnly());
            column.setWeight(16);
            final ColumnCellModifier columnCellModifier = new ColumnCellModifier(column) {

                public boolean canModify(Object bean) {
                    boolean typeIsDate = currentBeanHasJavaDateType(bean);
                    return typeIsDate;
                }

            };
            column.setColorProvider(new IColumnColorProvider() {

                public Color getBackgroundColor(Object bean) {
                    if (!columnCellModifier.canModify(bean)) {
                        return READONLY_CELL_BG_COLOR;
                    }
                    return null;
                }

                public Color getForegroundColor(Object bean) {
                    return null;
                }

            });
            column.setLabelProvider(new IColumnLabelProvider() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.talend.commons.ui.swt.tableviewer.behavior.IColumnLabelProvider#getLabel(java.lang.Object)
                 */
                public String getLabel(Object bean) {
                    if (!currentBeanHasJavaDateType(bean)) {
                        return "";
                    }
                    return null;
                }

            });
            column.setColumnCellModifier(columnCellModifier);

            JavaSimpleDateFormatProposalProvider proposalProvider = new JavaSimpleDateFormatProposalProvider();
            TextCellEditorWithProposal patternCellEditor = new TextCellEditorWithProposal(
                    tableViewerCreator.getTable(), column);
            ContentProposalAdapterExtended contentProposalAdapter = patternCellEditor.getContentProposalAdapter();
            contentProposalAdapter.setFilterStyle(ContentProposalAdapterExtended.FILTER_NONE);
            contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapterExtended.PROPOSAL_INSERT);
            patternCellEditor.setContentProposalProvider(proposalProvider);
            column.setCellEditor(patternCellEditor, CellEditorValueAdapterFactory.getNullToEmptyStringTextAdapater());

        }
    }

    /**
     * DOC amaumont Comment method "getPatternAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, String> getPatternAccessor();

    /**
     * DOC amaumont Comment method "configureNullableColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configureNullableColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        String nullableTitle = Messages.getString("MetadataTableEditorView.NullableTitle"); //$NON-NLS-1$
        column.setTitle(nullableTitle);
        column.setToolTipHeader(nullableTitle);
        column.setId(ID_COLUMN_NULLABLE);
        column.setBeanPropertyAccessors(getNullableAccessor());
        column.setModifiable(!isReadOnly());
        column.setWidth(56);
        column.setDisplayedValue(""); //$NON-NLS-1$
        CheckboxTableEditorContent nullableCheckbox = new CheckboxTableEditorContent();
        nullableCheckbox.setToolTipText(nullableTitle);
        column.setTableEditorContent(nullableCheckbox);
    }

    /**
     * DOC amaumont Comment method "getNullableAccessors".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, Boolean> getNullableAccessor();

    /**
     * DOC amaumont Comment method "configureTypeColumns".
     * 
     * @param tableViewerCreator
     * @param comboValueAdapter
     * @param arrayTalendTypes
     */
    protected void configureTypeColumns(TableViewerCreator<B> tableViewerCreator) {
        if (showDbTypeColumn) {
            if (showDbTypeColumnAtLeftPosition) {
                configureDbTypeColumn(tableViewerCreator);
                if (showTalendTypeColumn) {
                    configureTalendTypeColumn(tableViewerCreator);
                }
            } else {
                if (showTalendTypeColumn) {
                    configureTalendTypeColumn(tableViewerCreator);
                }
                configureDbTypeColumn(tableViewerCreator);
            }
        } else {
            configureTalendTypeColumn(tableViewerCreator);
        }
    }

    /**
     * DOC amaumont Comment method "configureKeyColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configureKeyColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.KeyTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.KeyTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_KEY);
        column.setModifiable(!isReadOnly());
        column.setBeanPropertyAccessors(getKeyAccesor());
        column.setWidth(35);
        column.setDisplayedValue(""); //$NON-NLS-1$
        CheckboxTableEditorContent keyCheckbox = new CheckboxTableEditorContent();
        column.setTableEditorContent(keyCheckbox);
        keyCheckbox.setToolTipText(Messages.getString("MetadataTableEditorView.KeyTitle")); //$NON-NLS-1$
    }

    /**
     * DOC amaumont Comment method "getKeyAccesor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, Boolean> getKeyAccesor();

    /**
     * DOC amaumont Comment method "configureNameColumn".
     * 
     * @param tableViewerCreator
     */
    protected void configureNameColumn(TableViewerCreator<B> tableViewerCreator) {
        TableViewerCreatorColumn column;
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setId(ID_COLUMN_NAME);
        column.setTitle(Messages.getString("MetadataTableEditorView.ColumnTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.ColumnTitle")); //$NON-NLS-1$

        column.setBeanPropertyAccessors(getLabelAccessor());
        final Image imageKey = ImageProvider.getImage(EImage.KEY_ICON);
        final Image imageEmpty = org.talend.commons.ui.image.ImageProvider.getImage(EImage.EMPTY);
        column.setImageProvider(new IColumnImageProvider() {

            public Image getImage(Object element) {
                if (getKeyAccesor().get((B) element)) {
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
                return validateColumnName(newValue, beanPosition);
            }

        });
        column.setCellEditor(cellEditor);
    }

    /**
     * DOC amaumont Comment method "validateColumnName".
     * 
     * @param newValue
     * @param beanPosition
     * @return
     */
    protected abstract String validateColumnName(String newValue, int beanPosition);

    /**
     * DOC amaumont Comment method "getLabelAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, String> getLabelAccessor();

    /**
     * DOC amaumont Comment method "initTalendTypeColumn".
     * 
     * @param tableViewerCreator
     * @param comboValueAdapter
     * @param arrayTalendTypes
     */
    private TableViewerCreatorColumn configureTalendTypeColumn(final TableViewerCreator<B> tableViewerCreator) {

        CellEditorValueAdapter comboValueAdapter = null;
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();

        if (codeLanguage == ECodeLanguage.JAVA) {
            comboValueAdapter = new JavaTypeComboValueAdapter<B>(JavaTypesManager.getDefaultJavaType(),
                    getNullableAccessor());
        } else if (codeLanguage == ECodeLanguage.PERL) {
            comboValueAdapter = CellEditorValueAdapterFactory.getComboAdapterForComboCellEditor();
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

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.TypleTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.TypleTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_TYPE);
        column.setBeanPropertyAccessors(getTalendTypeAccessor());
        column.setModifiable(!isReadOnly());
        column.setWeight(10);
        column.setMinimumWidth(30);
        ComboBoxCellEditor typeComboEditor = new ComboBoxCellEditor(tableViewerCreator.getTable(), arrayTalendTypes,
                SWT.READ_ONLY);
        CCombo typeCombo = (CCombo) typeComboEditor.getControl();
        typeCombo.setEditable(false);
        column.setCellEditor(typeComboEditor, comboValueAdapter);
        return column;
    }

    /**
     * DOC amaumont Comment method "getTalendType".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors<B, String> getTalendTypeAccessor();

    /**
     * DOC amaumont Comment method "initDbTypeColumn".
     * 
     * @param tableViewerCreator
     */
    private TableViewerCreatorColumn configureDbTypeColumn(final TableViewerCreator<B> tableViewerCreator) {

        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("MetadataTableEditorView.DBTypeTitle")); //$NON-NLS-1$
        column.setToolTipHeader(Messages.getString("MetadataTableEditorView.DBTypeTitle")); //$NON-NLS-1$
        column.setId(ID_COLUMN_DBTYPE);
        column.setBeanPropertyAccessors(getDbTypeAccessor());
        column.setModifiable(dbTypeColumnWritable);
        column.setWeight(10);
        column.setMinimumWidth(60);

        if (dbTypeColumnWritable) {

            CellEditorValueAdapter comboValueAdapter = CellEditorValueAdapterFactory
                    .getComboAdapterForComboCellEditor();
            String[] arrayDbTypes = new String[0];
            arrayDbTypes = MetadataTalendType.getDbTypes(getCurrentDbms()); //$NON-NLS-1$
            // System.out.println("currentDbms:" + getCurrentDbms() + " dbTypes:" + arrayDbTypes);
            ComboBoxCellEditor typeComboEditor = new ComboBoxCellEditor(tableViewerCreator.getTable(), arrayDbTypes,
                    SWT.READ_ONLY);
            CCombo typeCombo = (CCombo) typeComboEditor.getControl();
            typeCombo.setEditable(false);
            column.setCellEditor(typeComboEditor, comboValueAdapter);
        } else {
            column.setColorProvider(new IColumnColorProvider() {

                public Color getBackgroundColor(Object bean) {
                    return READONLY_CELL_BG_COLOR;
                }

                public Color getForegroundColor(Object bean) {
                    return null;
                }

            });
        }

        return column;

    }

    /**
     * DOC amaumont Comment method "getDbTypeAccessor".
     * 
     * @return
     */
    protected abstract IBeanPropertyAccessors getDbTypeAccessor();

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#setTableViewerCreatorOptions(org.talend.commons.ui.swt.tableviewer.TableViewerCreator)
     */
    @Override
    protected void setTableViewerCreatorOptions(TableViewerCreator<B> newTableViewerCreator) {
        super.setTableViewerCreatorOptions(newTableViewerCreator);
        newTableViewerCreator.setFirstVisibleColumnIsSelection(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#initToolBar()
     */
    @Override
    protected abstract ExtendedToolbarView initToolBar();

    public void setShowDbTypeColumn(boolean showDbTypeColumn, boolean showDbTypeColumnAtLeftPosition, boolean writable) {
        this.showDbTypeColumn = showDbTypeColumn;
        this.showDbTypeColumnAtLeftPosition = showDbTypeColumnAtLeftPosition;
        this.dbTypeColumnWritable = writable;
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

    /**
     * Getter for showPatternColumn.
     * 
     * @return the showPatternColumn
     */
    public boolean isShowPatternColumn() {
        return this.showPatternColumn;
    }

    /**
     * Sets the showPatternColumn.
     * 
     * @param showPatternColumn the showPatternColumn to set
     */
    public void setShowPatternColumn(boolean showPatternColumn) {
        this.showPatternColumn = showPatternColumn;
    }

    /**
     * DOC amaumont Comment method "currentBeanHasJavaDateType".
     * 
     * @param element
     * @return
     */
    private boolean currentBeanHasJavaDateType(Object element) {
        String talendType = getTalendTypeAccessor().get((B) element);
        boolean typeIsDate = JavaTypesManager.DATE.getId().equals(talendType);
        return typeIsDate;
    }

    public boolean isShowTalendTypeColumn() {
        return this.showTalendTypeColumn;
    }

    public void setShowTalendTypeColumn(boolean showTalenTypeColumn) {
        this.showTalendTypeColumn = showTalenTypeColumn;
    }

    public String getCurrentDbms() {
        // IMetadataConnection connection = getMetadataTableEditor().getMetadataTable().getParent();
        // if (connection != null) {
        // return connection.getDatabase();
        // }
        if (!this.showDbTypeColumn) {
            return null;
        }
        if (dbmsId == null) {
            return "mysql_id";
        }
        return dbmsId;
    }

    public void setCurrentDbms(String dbmsId) {
        this.dbmsId = dbmsId;
    }
}
