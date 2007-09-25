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
package org.talend.core.ui.context;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView;
import org.talend.commons.ui.swt.advanced.dataeditor.button.AddPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.AddPushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.button.CopyPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ExportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ImportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveDownPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveUpPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.PastePushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.RemovePushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.RemovePushButtonForExtendedTable;
import org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.commons.utils.data.list.ListenableListEvent;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.IProcess;
import org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter;

/**
 * bqian class global comment. Detailled comment <br/>
 * 
 */
public class ConextTemplateComposite extends Composite {

    private boolean readOnly;

    private CellEditor cellEditor;

    private TableViewerCreator<IContextParameter> tableViewerCreator;

    private ContextTemplateEditorView fieldsTableEditorView;

    private ContextTemplateModel fieldsModel;

    private static final int CNUM_DEFAULT = 4;

    private IContextModelManager modelManager = null;

    /**
     * bqian ConextTemplateComposite constructor comment.
     * 
     * @param parent
     * @param style
     */
    public ConextTemplateComposite(Composite parent, IContextModelManager manager) {
        super(parent, SWT.NONE);
        modelManager = manager;
        this.setBackground(parent.getBackground());
        this.setLayout(new GridLayout());
        initializeUI();
    }

    public IContextManager getContextManager() {
        return modelManager.getContextManager();
    }

    protected void setCommandStack(CommandStack commandStack) {
        fieldsTableEditorView.getExtendedTableViewer().setCommandStack(commandStack);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled) {
        // update the state of buttons

        // fieldsTableEditorView.getExtendedToolbar().getParentComposite().setEnabled(enabled);
        for (ExtendedPushButton button : fieldsTableEditorView.getExtendedToolbar().getButtons()) {
            button.getButton().setEnabled(enabled);
        }
        // update the table;
        for (TableViewerCreatorColumn column : tableViewerCreator.getColumns()) {
            column.setModifiable(enabled);
        }
    }

    /**
     * bqian Comment method "initializeUI".
     */
    private void initializeUI() {
        fieldsModel = new ContextTemplateModel(); //$NON-NLS-1$
        fieldsTableEditorView = new ContextTemplateEditorView(fieldsModel, this);

        final Composite fieldTableEditorComposite = fieldsTableEditorView.getMainComposite();
        fieldTableEditorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        fieldTableEditorComposite.setBackground(null);
        tableViewerCreator = fieldsTableEditorView.getTableViewerCreator();
    }

    private void addTableColumns(final TableViewerCreator<IContextParameter> tableViewerCreator, final Table table) {
        // Name column
        TableViewerCreatorColumn<IContextParameter, String> nameColumn = new TableViewerCreatorColumn<IContextParameter, String>(
                tableViewerCreator);
        nameColumn.setTitle(Messages.getString("ContextProcessSection.39")); //$NON-NLS-1$
        nameColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getName();
            }

            public void set(IContextParameter bean, String value) {
                bean.setName(value);
            }
        });
        nameColumn.setModifiable(true);
        nameColumn.setWidth(80);
        TextCellEditor textCellEditor = new TextCellEditor(table);
        nameColumn.setCellEditor(textCellEditor, paramNameCellEditorValueAdapter);

        // //////////////////////////////////////////////////////////
        // Type column
        TableViewerCreatorColumn<IContextParameter, String> typeColumn = new TableViewerCreatorColumn<IContextParameter, String>(
                tableViewerCreator);
        typeColumn.setTitle(Messages.getString("ContextProcessSection.43")); //$NON-NLS-1$
        typeColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getType();
            }

            public void set(IContextParameter bean, String value) {
                bean.setType(value);
                // if (!oldCellEditorValue.equals(newCellEditorValue)) {
                IContext context = getSelectedContext();
                onContextModify(getContextManager(), bean);
                // }
            }
        });
        typeColumn.setModifiable(true);
        typeColumn.setWidth(80);

        ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(table, MetadataTalendType
                .getCxtParameterTalendTypesLabels());
        CellEditorValueAdapter comboValueAdapter = null;
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            comboValueAdapter = javaComboCellEditorValueAdapter;
        } else if (codeLanguage == ECodeLanguage.PERL) {
            comboValueAdapter = perlComboCellEditorValueAdapter;
        }
        typeColumn.setCellEditor(comboBoxCellEditor, comboValueAdapter);

        ((CCombo) comboBoxCellEditor.getControl()).setEditable(false);

        // Code Column
        TableViewerCreatorColumn<IContextParameter, String> codeColumn = new TableViewerCreatorColumn<IContextParameter, String>(
                tableViewerCreator);
        codeColumn.setTitle(Messages.getString("ContextProcessSection.scriptCodeColumnTitle")); //$NON-NLS-1$
        codeColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getScriptCode();
            }

            public void set(IContextParameter bean, String value) {
                // do nothing since script code should not be modified.
            }
        });
        codeColumn.setModifiable(true);
        codeColumn.setWidth(400);
        TextCellEditor cellEditor = new TextCellEditor(table);
        ((Text) cellEditor.getControl()).setEditable(false);
        codeColumn.setCellEditor(cellEditor, setDirtyValueAdapter);
    }

    IBeanPropertyAccessors<IContextParameter, Boolean> nullableAccessors = new IBeanPropertyAccessors<IContextParameter, Boolean>() {

        public Boolean get(IContextParameter bean) {
            return Boolean.FALSE; // new Boolean(bean.isNullable());
        }

        public void set(IContextParameter bean, Boolean value) {
            // bean.setNullable(value);
        }

    };

    private boolean renameParameter(final String oldParamName, final String newParamName) {
        if (!getContextManager().checkValidParameterName(newParamName)) {
            MessageDialog
                    .openError(
                            this.getShell(),
                            Messages.getString("ContextProcessSection.errorTitle"), Messages.getString("ContextProcessSection.ParameterNameIsNotValid")); //$NON-NLS-1$ //$NON-NLS-2$
            return false;
        }

        onContextRenameParameter(getContextManager(), oldParamName, newParamName);
        return true;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    CellEditorValueAdapter perlComboCellEditorValueAdapter = new CellEditorValueAdapter() {

        @Override
        public Object getCellEditorTypedValue(final CellEditor cellEditor, final Object originalTypedValue) {
            Integer intValue = new Integer(-1);
            String[] values = ContextParameterJavaTypeManager.getPerlTypesLabels();
            for (int j = 0; j < values.length && intValue == -1; j++) {
                if (values[j].equals(originalTypedValue)) {
                    intValue = new Integer(j);
                }
            }
            return super.getCellEditorTypedValue(cellEditor, intValue);
        }

        @Override
        public Object getOriginalTypedValue(final CellEditor cellEditor, final Object cellEditorTypedValue) {
            Integer intValue = (Integer) cellEditorTypedValue;
            String value = ""; //$NON-NLS-1$
            if (intValue > 0) {
                value = ContextParameterJavaTypeManager.getPerlTypesLabels()[(Integer) cellEditorTypedValue];
            }
            return super.getOriginalTypedValue(cellEditor, value);
        }

        public String getColumnText(final CellEditor cellEditor, final Object cellEditorValue) {
            return (String) cellEditorValue;
        }
    };

    JavaTypeComboValueAdapter<IContextParameter> javaComboCellEditorValueAdapter = new JavaTypeComboValueAdapter<IContextParameter>(
            ContextParameterJavaTypeManager.getDefaultJavaType(), nullableAccessors) {

        @Override
        public Object getCellEditorTypedValue(CellEditor cellEditor, Object originalTypedValue) {
            String[] items = ((ComboBoxCellEditor) cellEditor).getItems();

            JavaType javaType = ContextParameterJavaTypeManager.getJavaTypeFromId((String) originalTypedValue);

            String label = javaType.getLabel();

            if (originalTypedValue == null && ContextParameterJavaTypeManager.getDefaultJavaType() != null) {
                label = ContextParameterJavaTypeManager.getDefaultJavaType().getLabel();
            }

            for (int i = 0; i < items.length; i++) {
                if (items[i].equals(label)) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public Object getOriginalTypedValue(CellEditor cellEditor, Object cellEditorTypedValue) {
            JavaType[] javaTypes = ContextParameterJavaTypeManager.getJavaTypes();
            int i = (Integer) cellEditorTypedValue;
            if (i >= 0) {
                return javaTypes[i].getId();
            }
            // else {
            // return null;
            // }
            throw new IllegalStateException("No selection is invalid"); //$NON-NLS-1$
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter#getColumnText(org.eclipse.jface.viewers.CellEditor,
         * java.lang.Object, java.lang.Object)
         */
        @Override
        public String getColumnText(CellEditor cellEditor, Object bean, Object originalTypedValue) {
            JavaType javaType = ContextParameterJavaTypeManager.getJavaTypeFromId((String) originalTypedValue);

            Class primitiveClass = javaType.getPrimitiveClass();
            Boolean nullable = nullableAccessors.get((IContextParameter) bean);
            String displayedValue = null;
            if (primitiveClass != null && !nullable.equals(Boolean.TRUE)) {
                displayedValue = primitiveClass.getSimpleName();
            } else if (originalTypedValue.equals(JavaTypesManager.DIRECTORY.getId())
                    || originalTypedValue.equals(JavaTypesManager.FILE.getId())) {
                displayedValue = javaType.getLabel();
            } else {
                displayedValue = javaType.getNullableClass().getSimpleName();
            }

            if (displayedValue == null && ContextParameterJavaTypeManager.getDefaultJavaType() != null) {
                displayedValue = ContextParameterJavaTypeManager.getDefaultJavaType().getLabel();
            }
            return displayedValue;
        }

    };

    CellEditorValueAdapter paramNameCellEditorValueAdapter = new CellEditorValueAdapter() {

        private String oldName;

        @Override
        public Object getCellEditorTypedValue(final CellEditor cellEditor, final Object originalTypedValue) {
            oldName = (String) originalTypedValue;
            return super.getCellEditorTypedValue(cellEditor, originalTypedValue);
        }

        @Override
        public Object getOriginalTypedValue(final CellEditor cellEditor, final Object cellEditorTypedValue) {
            if (!oldName.equals((String) cellEditorTypedValue)) {
                if (!renameParameter(oldName, (String) cellEditorTypedValue)) {
                    return super.getOriginalTypedValue(cellEditor, oldName);
                }
            }
            return super.getOriginalTypedValue(cellEditor, cellEditorTypedValue);
        }
    };

    private CellEditorValueAdapter setDirtyValueAdapter = new CellEditorValueAdapter() {

        @Override
        public Object getCellEditorTypedValue(final CellEditor cellEditor, final Object originalTypedValue) {
            return super.getCellEditorTypedValue(cellEditor, originalTypedValue);
        }

        @Override
        public Object getOriginalTypedValue(final CellEditor cellEditor, final Object cellEditorTypedValue) {
            return super.getOriginalTypedValue(cellEditor, cellEditorTypedValue);
        }
    };

    /**
     * bqian Comment method "setContexts".
     * 
     * @param jobContextManager2
     */
    public void refresh() {
        List<IContext> contexts = getContextManager().getListContext();
        List<IContextParameter> contextTemplate = computeContextTemplate(contexts);

        fieldsModel.removeAll();
        fieldsModel.addAll(contextTemplate);
        tableViewerCreator.getTable().deselectAll();
    }

    /**
     * Clear the data in this viewer.
     * 
     * @param jobContextManager2
     */
    public void clear() {
        fieldsModel.removeAll();
    }

    /**
     * bqian Comment method "computeContextTemplate".
     * 
     * @param contexts
     * 
     * @return
     */
    public static List<IContextParameter> computeContextTemplate(List<IContext> contexts) {
        List<IContextParameter> contextTemplate = new ArrayList<IContextParameter>();

        if (!contexts.isEmpty()) {
            // All the context has the same template
            List<IContextParameter> paras = contexts.get(0).getContextParameterList();
            for (IContextParameter contextParameter : paras) {
                contextTemplate.add(contextParameter.clone());
            }

        }
        return contextTemplate;
    }

    /**
     * A concrete implementation of ExtendedToolbarView for template toolbar <br/>
     * 
     */
    class ContextTemplateTableToolbarEditorView extends ExtendedToolbarView {

        /**
         * amaumont MetadataToolbarEditorView constructor comment.
         * 
         * @param parent
         * @param style
         * @param extendedTableViewer
         */
        public ContextTemplateTableToolbarEditorView(Composite parent, AbstractExtendedTableViewer extendedTableViewer) {
            super(parent, SWT.NONE, extendedTableViewer);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.extended.ExtendedToolbarView#createComponents(org.eclipse.swt.widgets.Composite)
         */
        @Override
        protected void createComponents(Composite parent) {
            super.createComponents(parent);

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.extended.ExtendedToolbarView#createAddPushButton()
         */
        @Override
        protected AddPushButton createAddPushButton() {
            return new AddPushButtonForExtendedTable(this.toolbar, getExtendedTableViewer()) {

                protected void handleSelectionEvent(Event event) {
                    ContextTemplateModel tableEditorModel = (ContextTemplateModel) getExtendedTableViewer()
                            .getExtendedControlModel();
                    IContextParameter parameter = (IContextParameter) tableEditorModel.createNewEntry();
                    modelManager.onContextAddParameter(getContextManager(), parameter);

                    List<IContextParameter> list = tableEditorModel.getBeansList();
                    int index = list.indexOf(parameter);
                    if (index >= 0) {
                        // find the real parameter to select
                        parameter = (IContextParameter) list.get(index);
                        tableViewerCreator.getTableViewer().setSelection(new StructuredSelection(parameter), true);
                    }
                }

                @Override
                protected Object getObjectToAdd() {
                    return null;
                }
            };
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView#createRemovePushButton()
         */
        @Override
        protected RemovePushButton createRemovePushButton() {
            return new RemovePushButtonForExtendedTable(toolbar, extendedTableViewer) {

                public boolean getEnabledState() {
                    if (modelManager.isReadOnly()) {
                        return false;
                    } else {
                        return super.getEnabledState();
                    }
                }

                protected void handleSelectionEvent(Event event) {
                    Object object = ((IStructuredSelection) tableViewerCreator.getTableViewer().getSelection()).getFirstElement();
                    if (object == null) {
                        return;
                    }
                    IContextParameter parameter = (IContextParameter) object;

                    ContextTemplateModel tableEditorModel = (ContextTemplateModel) getExtendedTableViewer()
                            .getExtendedControlModel();
                    List<IContextParameter> list = tableEditorModel.getBeansList();
                    int index = list.indexOf(parameter);

                    modelManager.onContextRemoveParameter(getContextManager(), parameter.getName());

                    if (list.isEmpty()) {
                        return;
                    }
                    if (index > list.size() - 1) {
                        index = list.size() - 1;
                    }
                    // find the real parameter to select
                    parameter = (IContextParameter) list.get(index);
                    tableViewerCreator.getTableViewer().setSelection(new StructuredSelection(parameter), true);
                }
            };
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView#createCopyPushButton()
         */
        @Override
        protected CopyPushButton createCopyPushButton() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView#createMoveDownPushButton()
         */
        @Override
        protected MoveDownPushButton createMoveDownPushButton() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView#createMoveUpPushButton()
         */
        @Override
        protected MoveUpPushButton createMoveUpPushButton() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.extended.ExtendedToolbarView#createPastButton()
         */
        @Override
        public PastePushButton createPastePushButton() {
            // return new PastePushButtonForExtendedTable(toolbar, extendedTableViewer) {
            //
            // @Override
            // protected Command getCommandToExecute(ExtendedTableModel extendedTableModel, Integer indexWhereInsert) {
            // return new PropertyTablePasteCommand<Map<String, Object>>(extendedTableModel, indexWhereInsert);
            // }
            //
            // };
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.extended.ExtendedToolbarView#createExportPushButton()
         */
        @Override
        protected ExportPushButton createExportPushButton() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.extended.ExtendedToolbarView#createPastButton()
         */
        @Override
        public ImportPushButton createImportPushButton() {
            return null;
        }
    }

    /**
     * bqian ConextTemplateComposite class global comment. Detailled comment <br/>
     * 
     */
    class ContextTemplateEditorView extends AbstractDataTableEditorView<IContextParameter> {

        public ContextTemplateEditorView(ContextTemplateModel model, Composite parent, int styleChild) {
            this(model, parent, styleChild, false);
        }

        public ContextTemplateEditorView(ContextTemplateModel model, Composite parent) {
            this(model, parent, SWT.NONE, false);
        }

        /**
         * TargetSchemaTableEditorView2 constructor comment.
         * 
         * @param parent
         * @param styleChild
         * @param showDbTypeColumn
         */
        public ContextTemplateEditorView(ContextTemplateModel model, Composite parent, int styleChild, boolean showDbTypeColumn) {
            super(parent, styleChild, model);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#handleBeforeListenableListOperationEvent(org.talend.commons.utils.data.list.ListenableListEvent)
         */
        @Override
        protected void handleBeforeListenableListOperationEvent(ListenableListEvent<IContextParameter> event) {
            super.handleBeforeListenableListOperationEvent(event);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedTableViewer#handleListenableListEvent(org.talend.commons.utils.data.list.ListenableListEvent)
         */
        @Override
        protected void handleAfterListenableListOperationEvent(ListenableListEvent<IContextParameter> event) {
            super.handleAfterListenableListOperationEvent(event);

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedTableViewer#setTableViewerCreatorOptions(org.talend.commons.ui.swt.tableviewer.TableViewerCreator)
         */
        @Override
        protected void setTableViewerCreatorOptions(TableViewerCreator<IContextParameter> newTableViewerCreator) {
            super.setTableViewerCreatorOptions(newTableViewerCreator);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.macrotable.AbstractExtendedTableViewer#createColumns(org.talend.commons.ui.swt.tableviewer.TableViewerCreator,
         * org.eclipse.swt.widgets.Table)
         */
        @Override
        protected void createColumns(TableViewerCreator<IContextParameter> tableViewerCreator, final Table table) {
            addTableColumns(tableViewerCreator, table);
        }

        public ContextTemplateModel getModel() {
            return (ContextTemplateModel) getExtendedTableModel();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#initToolBar()
         */
        @Override
        protected ExtendedToolbarView initToolBar() {
            return new ContextTemplateTableToolbarEditorView(getMainComposite(), getExtendedTableViewer());
        }

    }

    protected void onContextRenameParameter(IContextManager contextManager, String oldName, String newName) {
        modelManager.onContextRenameParameter(contextManager, oldName, newName);
    }

    protected void onContextModify(IContextManager contextManager, IContextParameter parameter) {
        modelManager.onContextModify(contextManager, parameter);
    }

    /**
     * DOC bqian Comment method "getProcess".
     * 
     * @return
     */
    private IProcess getProcess() {
        return modelManager.getProcess();
    }

    /**
     * DOC bqian Comment method "getCommandStack".
     * 
     * @return
     */
    private CommandStack getCommandStack() {
        return fieldsTableEditorView.getExtendedTableViewer().getCommandStack();
    }

    public IContext getSelectedContext() {
        return this.getContextManager().getDefaultContext();
    }

}
