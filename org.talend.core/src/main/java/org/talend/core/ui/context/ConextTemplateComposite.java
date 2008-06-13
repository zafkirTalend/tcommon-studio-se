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
package org.talend.core.ui.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.button.CopyPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ExportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.ImportPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveDownPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.MoveUpPushButton;
import org.talend.commons.ui.swt.advanced.dataeditor.button.PastePushButton;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.ui.images.ECoreImage;
import org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter;

/**
 * zwang class global comment. Detailled comment <br/>
 * 
 */
public class ConextTemplateComposite extends AbstractContextTabEditComposite {

    /**
     * 
     */
    private static final String BUILT_IN = IContextParameter.BUILT_IN;

    private static final String NAME_COLUMN_NAME = "Name";

    private static final String SOURCE_COLUMN_NAME = "Source";

    private static final String TYPE_COLUMN_NAME = "Type";

    private static final String SCRIPTCODE_COLUMN_NAME = "Script code";

    private static final String COMMENT_COLUMN_NAME = "Comment";

    private static final String[] GROUP_BY_SOURCE_COLUMN_PROPERTIES = new String[] { NAME_COLUMN_NAME, SOURCE_COLUMN_NAME,
            TYPE_COLUMN_NAME, SCRIPTCODE_COLUMN_NAME, COMMENT_COLUMN_NAME };

    private static final String[] GROUP_BY_REPOSITORYSOURCE_COLUMN_PROPERTIES = new String[] { NAME_COLUMN_NAME,
            TYPE_COLUMN_NAME, SCRIPTCODE_COLUMN_NAME, COMMENT_COLUMN_NAME };

    private static final String[] ITEMS = new String[] { "boolean | Boolean", "char | Character", "Date", "double | Double",
            "float | Float", "int | Integer", "long | Long", "short | Short", "String", "BigDecimal", "Object", "File",
            "Directory", "List Of Value" };

    public static final String NEW_PARAM_NAME = "new"; //$NON-NLS-1$

    private boolean readOnly;

    private CellEditor cellEditor;

    private DefaultCellEditorFactory cellFactory;

    private TreeViewer viewer;

    private ViewerProvier provider;

    private CellModifier cellModifier;

    private static final int CNUM_DEFAULT = 4;

    private IContextModelManager modelManager = null;

    private ContextManagerHelper helper;

    private ICellEditorListener editorListener;

    private List<Button> buttonList;

    /**
     * bqian ConextTemplateComposite constructor comment.
     * 
     * @param parent
     * @param style
     */
    public ConextTemplateComposite(Composite parent, IContextModelManager manager) {
        super(parent, SWT.NONE);
        buttonList = new ArrayList<Button>();
        modelManager = manager;
        cellFactory = new DefaultCellEditorFactory(modelManager);
        this.helper = new ContextManagerHelper(manager.getContextManager());
        this.setBackground(parent.getBackground());
        this.setLayout(new GridLayout());
        initializeUI();
    }

    public IContextManager getContextManager() {
        return modelManager.getContextManager();
    }

    public IContextModelManager getContextModelManager() {
        return modelManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled) {
        // update the state of buttons
        for (Button button : buttonList) {
            if (modelManager.isReadOnly()) {
                button.setEnabled(false);
            } else {
                button.setEnabled(enabled);
            }
        }
    }

    /**
     * zwang Comment method "initializeUI".
     */
    private void initializeUI() {
        ComboBoxCellEditor comboBoxCellEditor = null;
        viewer = new TreeViewer(this, SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final Tree tree = viewer.getTree();
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);
        tree.setLayoutData(new GridData(GridData.FILL_BOTH));

        TreeColumn column = new TreeColumn(tree, SWT.NONE);
        column.setText(NAME_COLUMN_NAME);
        column.setWidth(120);

        if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
            column = new TreeColumn(tree, SWT.NONE);
            column.setText(SOURCE_COLUMN_NAME);
            column.setWidth(80);
        }

        column = new TreeColumn(tree, SWT.NONE);
        column.setText(TYPE_COLUMN_NAME);
        column.setWidth(80);

        column = new TreeColumn(tree, SWT.NONE);
        column.setText(SCRIPTCODE_COLUMN_NAME);
        column.setWidth(250);

        column = new TreeColumn(tree, SWT.NONE);
        column.setText(COMMENT_COLUMN_NAME);
        column.setWidth(300);

        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            comboBoxCellEditor = new ComboBoxCellEditor(tree, ITEMS);
        } else {
            String[] values = ContextParameterJavaTypeManager.getPerlTypesLabels();
            comboBoxCellEditor = new ComboBoxCellEditor(tree, values);
        }
        boolean isRepositoryContext = (modelManager instanceof ContextComposite)
                && ((ContextComposite) modelManager).isRepositoryContext();

        if (!isRepositoryContext) {
            viewer.setColumnProperties(GROUP_BY_SOURCE_COLUMN_PROPERTIES);
            viewer.setCellEditors(new CellEditor[] { new TextCellEditor(tree), null, comboBoxCellEditor,
                    new TextCellEditor(tree), new TextCellEditor(tree) });
            ((CCombo) comboBoxCellEditor.getControl()).setEditable(false);
        } else {
            viewer.setColumnProperties(GROUP_BY_REPOSITORYSOURCE_COLUMN_PROPERTIES);
            viewer.setCellEditors(new CellEditor[] { new TextCellEditor(tree), comboBoxCellEditor, new TextCellEditor(tree),
                    new TextCellEditor(tree) });
            ((CCombo) comboBoxCellEditor.getControl()).setEditable(false);
        }

        cellModifier = new CellModifier();
        viewer.setCellModifier(cellModifier);

        provider = new ViewerProvier();

        if (isGroupBySource()) {
            provider.setProvider(new GroupBySourceProvier());
        } else {
            provider.setProvider(new GroupByNothingProvier());
        }

        viewer.setLabelProvider(provider);
        viewer.setContentProvider(provider);

        addSorter(viewer);

        final TreeEditor treeEditor = new TreeEditor(tree);
        createEditorListener(treeEditor);
        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent e) {
                Rectangle rect = null;
                if (modelManager.isReadOnly()) {
                    return;
                }
                Point pt = new Point(e.x, e.y);
                TreeItem item = tree.getItem(pt);
                // deactivate the current cell editor
                if (cellEditor != null && !cellEditor.getControl().isDisposed()) {
                    deactivateCellEditor(treeEditor);
                }
                if (item != null && !item.isDisposed()) {
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        rect = item.getBounds(CNUM_DEFAULT);
                    } else {
                        rect = item.getBounds(CNUM_DEFAULT - 1);
                    }

                    if (rect.contains(pt)) {
                        handleSelect(item, tree, treeEditor);
                    }
                }
            }
        });

        final Composite buttonsComposite = new Composite(this, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.swtDefaults().spacing(0, 0).margins(0, 0).numColumns(4).create());
        GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.DOWN).grab(true, false).applyTo(buttonsComposite);
        buttonList.clear();
        Button addButton = createAddPushButton(buttonsComposite);
        buttonList.add(addButton);
        Button removeButton = createRemovePushButton(buttonsComposite);
        buttonList.add(removeButton);

        if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
            Button selectContextVariablesButton = createSelectContextVariablesPushButton(buttonsComposite);
            buttonList.add(selectContextVariablesButton);
            createDefaultContext(buttonsComposite);
        }
    }

    private void addSorter(final TreeViewer viewer2) {
        final Tree table = viewer2.getTree();
        Listener sortListener = new Listener() {

            private int direction = 1;

            public void handleEvent(Event e) {
                final TreeColumn column = (TreeColumn) e.widget;

                if (column == table.getSortColumn()) {
                    direction = -direction;
                }
                if (direction == 1) {
                    table.setSortDirection(SWT.UP);
                } else {
                    table.setSortDirection(SWT.DOWN);
                }

                table.setSortColumn(column);
                viewer2.setSorter(new ViewerSorter() {

                    int index = 0;

                    @Override
                    public void sort(Viewer viewer, Object[] elements) {
                        while (index < table.getColumns().length && table.getColumn(index) != column) {
                            index++;
                        }
                        super.sort(viewer, elements);
                    }

                    @Override
                    public int compare(Viewer viewer, Object e1, Object e2) {
                        ITableLabelProvider labelProvider = (ITableLabelProvider) viewer2.getLabelProvider();
                        String columnText = labelProvider.getColumnText(e1, index) != null ? labelProvider.getColumnText(e1,
                                index) : "";
                        String columnText2 = labelProvider.getColumnText(e2, index) != null ? labelProvider.getColumnText(e2,
                                index) : "";
                        return getComparator().compare(columnText, columnText2) * direction;
                    }
                });
                // viewer2.expandAll();
            }
        };
        table.getColumn(0).addListener(SWT.Selection, sortListener);
        if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
            table.getColumn(1).addListener(SWT.Selection, sortListener);
        }
        table.setSortColumn(table.getColumn(0));
        table.setSortDirection(SWT.UP);
    }

    private void activateCellEditor(final TreeItem item, final Tree tree, final TreeEditor treeEditor) {
        // ensure the cell editor is visible
        tree.showSelection();
    }

    protected void handleSelect(final TreeItem item, final Tree tree, final TreeEditor treeEditor) {
        // get the new selection
        // activateCellEditor(item, tree, treeEditor);
    }

    private void deactivateCellEditor(final TreeEditor tableEditor) {
        if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
            tableEditor.setEditor(null, null, CNUM_DEFAULT);
        } else {
            tableEditor.setEditor(null, null, CNUM_DEFAULT - 1);
        }
        if (cellEditor != null) {
            cellEditor.deactivate();
            cellEditor.removeListener(editorListener);
            cellEditor = null;
        }
    }

    private ICellEditorListener createEditorListener(final TreeEditor tableEditor) {
        editorListener = new ICellEditorListener() {

            public void cancelEditor() {
                deactivateCellEditor(tableEditor);
            }

            public void editorValueChanged(boolean oldValidState, boolean newValidState) {
            }

            public void applyEditorValue() {
            }
        };
        return editorListener;
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
        IContextParameter param = getContextManager().getDefaultContext().getContextParameter(oldParamName);
        if (param != null && !param.isBuiltIn()) {
            // not built-in, not update
            return false;

        }

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
            throw new IllegalStateException("No selection is invalid"); //$NON-NLS-1$
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter#getColumnText(org.eclipse.jface.viewers.CellEditor,
         * java.lang.Object, java.lang.Object)
         */
        // @Override
        // public String getColumnText(CellEditor cellEditor, Object bean, Object originalTypedValue) {
        // JavaType javaType =
        //
        // Class primitiveClass = javaType.getPrimitiveClass();
        // Boolean nullable = nullableAccessors.get((IContextParameter) bean);
        // String displayedValue = null;
        // if (primitiveClass != null && !nullable.equals(Boolean.TRUE)) {
        // displayedValue = primitiveClass.getSimpleName();
        // } else if (originalTypedValue.equals(JavaTypesManager.DIRECTORY.getId())
        // || originalTypedValue.equals(JavaTypesManager.FILE.getId())
        // || originalTypedValue.equals(JavaTypesManager.VALUE_LIST.getId())) {
        // displayedValue = javaType.getLabel();
        // } else {
        // displayedValue = javaType.getNullableClass().getSimpleName();
        // }
        //
        // return displayedValue;
        // }
        @Override
        protected JavaType getJavaType(Object originalTypedValue) {
            return ContextParameterJavaTypeManager.getJavaTypeFromId((String) originalTypedValue);
        }

        @Override
        protected String getDefaultDisplayValue(String displayedValue) {
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
            if (!oldName.equals(cellEditorTypedValue)) {
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

    private boolean isGroupBySource() {
        boolean isRepositoryContext = false;
        if (modelManager != null) {
            isRepositoryContext = (modelManager instanceof ContextComposite)
                    && ((ContextComposite) modelManager).isRepositoryContext();
        }
        boolean value = getPreferenceStore().getBoolean(ITalendCorePrefConstants.CONTEXT_GROUP_BY_SOURCE);
        return value && !isRepositoryContext;

    }

    /**
     * zwang Comment method "setContexts".
     * 
     * @param jobContextManager2
     */
    @Override
    public void refresh() {

        if (isGroupBySource()) {
            provider.setProvider(new GroupBySourceProvier());
        } else {
            provider.setProvider(new GroupByNothingProvier());
        }

        viewer.setLabelProvider(provider);
        viewer.setContentProvider(provider);

        IContextManager cm = modelManager.getContextManager();
        viewer.setInput(cm.getListContext());
        viewer.expandAll();

        List<IContext> contexts = getContextManager().getListContext();
        refreshVariableSource(contexts);
    }

    private void refreshVariableSource(List<IContext> contexts) {
        if (helper == null) {
            return;
        }
        helper.initHelper(getContextManager());
        for (IContext context : contexts) {
            for (IContextParameter param : context.getContextParameterList()) {
                if (!param.isBuiltIn()) {
                    ContextItem item = helper.getContextItemByName(param.getSource());
                    if (item == null) { // source not found
                        param.setSource(IContextParameter.BUILT_IN);
                        continue;
                    }
                    // // the variable not exist in the ContextItem
                    // if (!helper.hasExistedVariableFromContextItem(item, param.getName())) {
                    // param.setSource(IContextParameter.BUILT_IN);
                    // }

                }
            }
        }
    }

    /**
     * Clear the data in this viewer.
     * 
     * @param jobContextManager2
     */
    public void clear() {
        viewer.setInput(Collections.EMPTY_LIST);
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

    private Button createAddPushButton(final Composite parent) {

        Button addPushButton = new Button(parent, SWT.PUSH);
        addPushButton.addSelectionListener(new SelectionListener() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                // TODO Auto-generated method stub
                IContextParameter parameter = (IContextParameter) createNewEntry();
                if (parameter != null) {
                    // set the source to built-in
                    parameter.setSource(IContextParameter.BUILT_IN);
                    modelManager.onContextAddParameter(getContextManager(), parameter);

                    TreeItem[] treeItemArray = viewer.getTree().getItems();
                    for (TreeItem treeitem : treeItemArray) {
                        if (treeitem.getData() instanceof GroupBySourceProvier.Parent) {
                            if (BUILT_IN.equals(((GroupBySourceProvier.Parent) treeitem.getData()).sourceName)) {
                                if (((GroupBySourceProvier.Parent) treeitem.getData()).builtContextParameter != null) {
                                    if (parameter.getName().equals(
                                            ((GroupBySourceProvier.Parent) treeitem.getData()).builtContextParameter.getName())) {
                                        viewer.getTree().setSelection(treeitem);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        Image image = ImageProvider.getImage(EImage.ADD_ICON);
        addPushButton.setImage(image);
        return addPushButton;
    }

    public Object createNewEntry() {
        List<IContextParameter> listParams = getContextManager().getDefaultContext().getContextParameterList();
        Integer numParam = new Integer(1);
        boolean paramNameFound;
        String paramName = null;
        do { // look for a new name
            paramNameFound = true;
            paramName = NEW_PARAM_NAME + numParam;
            for (int i = 0; i < listParams.size() && paramNameFound; i++) {
                if (paramName.equals(listParams.get(i).getName())) {
                    paramNameFound = false;
                }
            }
            if (!paramNameFound) {
                numParam++;
            }
        } while (!paramNameFound);

        JobContextParameter contextParam = new JobContextParameter();
        contextParam.setName(paramName);
        ECodeLanguage curLanguage = LanguageManager.getCurrentLanguage();
        if (curLanguage == ECodeLanguage.JAVA) {
            contextParam.setType(ContextParameterJavaTypeManager.getDefaultJavaType().getId());
        } else {
            contextParam.setType(MetadataTalendType.getDefaultTalendType());
        }
        contextParam.setPrompt(paramName + "?"); //$NON-NLS-1$
        String defaultValue;
        if (curLanguage == ECodeLanguage.JAVA) {
            defaultValue = ContextParameterJavaTypeManager.getDefaultValueFromJavaIdType(ContextParameterJavaTypeManager
                    .getDefaultJavaType().getId(), false);
        } else {
            defaultValue = TalendTextUtils.addQuotes(""); //$NON-NLS-1$
        }
        contextParam.setValue(defaultValue);
        contextParam.setComment(""); //$NON-NLS-1$
        contextParam.setSource(""); //$NON-NLS-1$
        return contextParam;
    }

    private Button createRemovePushButton(final Composite parent) {
        Button removePushButton = new Button(parent, SWT.PUSH);
        removePushButton.addSelectionListener(new SelectionListener() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

                IContextParameter parameter = null;
                TreeItem[] items = viewer.getTree().getSelection();
                if (items.length > 0) {
                    Object object = items[0].getData();
                    if (object == null) {
                        return;
                    }
                    if (object instanceof GroupBySourceProvier.Parent) {
                        if (BUILT_IN.equals(((GroupBySourceProvier.Parent) object).sourceName)) {
                            parameter = ((GroupBySourceProvier.Parent) object).builtContextParameter;
                            removeParameter(parameter);
                        } else {
                            for (GroupBySourceProvier.Son son : ((GroupBySourceProvier.Parent) object).son) {
                                parameter = son.parameter;
                                removeParameter(parameter);
                            }
                        }
                    } else if (object instanceof GroupBySourceProvier.Son) {
                        parameter = ((GroupBySourceProvier.Son) object).parameter;
                        removeParameter(parameter);
                    } else if (object instanceof GroupByNothingProvier.Parent) {
                        parameter = ((GroupByNothingProvier.Parent) object).parameter;
                        removeParameter(parameter);
                    }
                }
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }

        });
        Image image = ImageProvider.getImage(EImage.DELETE_ICON);
        removePushButton.setImage(image);
        return removePushButton;
    }

    private void removeParameter(IContextParameter parameter) {
        List<IContextParameter> list = getContextManager().getDefaultContext().getContextParameterList();

        modelManager.onContextRemoveParameter(getContextManager(), parameter.getName());

        if (list.isEmpty()) {
            return;
        }

        viewer.getTree().setSelection(viewer.getTree().getItem(0));
    }

    private Button createSelectContextVariablesPushButton(final Composite parent) {
        Button selectContextVariablesPushButton = new Button(parent, SWT.PUSH);
        Image image = ImageProvider.getImage(ECoreImage.CONTEXT_ICON);
        selectContextVariablesPushButton.addSelectionListener(new SelectionListener() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                // TODO Auto-generated method stub
                SelectRepositoryContextDialog dialog = new SelectRepositoryContextDialog(getContextModelManager(), parent
                        .getShell(), helper);
                dialog.open();
                refresh();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        selectContextVariablesPushButton.setImage(image);
        return selectContextVariablesPushButton;
    }

    private void createDefaultContext(final Composite parent) {
        Composite defaultComposite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;

        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.CENTER;
        defaultComposite.setLayout(layout);
        if (getContextModelManager() instanceof ContextComposite) {
            ContextComposite cComposite = (ContextComposite) getContextModelManager();
            cComposite.addChoiceComponents(defaultComposite);
        }

    }

    private CopyPushButton createCopyPushButton() {
        return null;
    }

    private MoveDownPushButton createMoveDownPushButton() {
        return null;
    }

    private MoveUpPushButton createMoveUpPushButton() {
        return null;
    }

    private PastePushButton createPastePushButton() {
        return null;
    }

    private ExportPushButton createExportPushButton() {
        return null;
    }

    private ImportPushButton createImportPushButton() {
        return null;
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

    public IContext getSelectedContext() {
        return this.getContextManager().getDefaultContext();
    }

    /**
     * zwang ConextTableValuesComposite class global comment. Detailled comment <br/>
     * 
     */
    class CellModifier implements ICellModifier {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
         */
        public boolean canModify(Object element, String property) {
            if (modelManager.isReadOnly()) {
                return false;
            }
            IContextParameter para = getRealParameter(element);
            if (para == null) {
                return false;
            }

            if (!para.isBuiltIn()) {
                // not built-in, not update
                return false;

            }

            if (property.equals(SOURCE_COLUMN_NAME) || property.equals(SCRIPTCODE_COLUMN_NAME)) {
                return false;
            }

            return true;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
         */
        public Object getValue(Object element, String property) {
            IContextParameter para = getRealParameter(element);

            if (para != null) {
                if (property.equals(NAME_COLUMN_NAME)) {
                    return para.getName();
                } else if (property.equals(TYPE_COLUMN_NAME)) {
                    String s = convertFormat(para.getType());
                    final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                    if (codeLanguage == ECodeLanguage.JAVA) {
                        for (int i = 0; i < ITEMS.length; i++) {
                            if (s.equals(ITEMS[i])) {
                                return i;
                            }
                        }
                        return -1;
                    } else {
                        for (int i = 0; i < ContextParameterJavaTypeManager.getPerlTypesLabels().length; i++) {
                            if (s.equals(ContextParameterJavaTypeManager.getPerlTypesLabels()[i])) {
                                return i;
                            }
                        }
                        return -1;
                    }

                } else if (property.equals(COMMENT_COLUMN_NAME)) {
                    return para.getComment();
                }
            }
            return "";
        }

        /**
         * zwang Comment method "getRealParameter".
         * 
         * @param property
         * @param templatePara
         * @return
         */
        private IContextParameter getRealParameter(Object element) {
            IContextParameter para = null;
            IContext context = getContextManager().getDefaultContext();

            if (isGroupBySource()) {
                if (element instanceof GroupBySourceProvier.Parent) {
                    if (BUILT_IN.equals(((GroupBySourceProvier.Parent) element).sourceName)) {
                        para = context.getContextParameter(((GroupBySourceProvier.Parent) element).builtContextParameter
                                .getName());
                    }
                } else if (element instanceof GroupBySourceProvier.Son) {
                    para = context.getContextParameter((((GroupBySourceProvier.Son) element).parameter).getName());
                }
            } else {
                if (element instanceof GroupByNothingProvier.Parent) {
                    para = context.getContextParameter(((GroupByNothingProvier.Parent) element).parameter.getName());
                }
            }
            return para;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
         */
        public void modify(Object element, final String property, final Object value) {
            TreeItem item = (TreeItem) element;
            final Object object = item.getData();

            final IContextParameter para = getRealParameter(object);
            if (para == null) {
                return;
            }

            if (property.equals(NAME_COLUMN_NAME)) {
                if (para.getName().equals(value)) {
                    return;
                }
                String name = para.getName();
                renameParameter(name, (String) value);
            } else if (property.equals(TYPE_COLUMN_NAME)) {
                int index = -1;
                String s = convertFormat(para.getType());
                final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                if (codeLanguage == ECodeLanguage.JAVA) {
                    for (int i = 0; i < ITEMS.length; i++) {
                        if (s.equals(ITEMS[i])) {
                            index = i;
                        }
                    }
                    if (index == ((Integer) value)) {
                        return;
                    }
                    String newType = getRealType(ITEMS[(Integer) value]);
                    String name = para.getName();
                    for (IContext context : getContextManager().getListContext()) {
                        for (IContextParameter contextParameter : context.getContextParameterList()) {
                            if (name.equals(contextParameter.getName())) {
                                contextParameter.setType(newType);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < ContextParameterJavaTypeManager.getPerlTypesLabels().length; i++) {
                        if (s.equals(ContextParameterJavaTypeManager.getPerlTypesLabels()[i])) {
                            index = i;
                        }
                    }
                    if (index == ((Integer) value)) {
                        return;
                    }
                    String newType = getRealType(ContextParameterJavaTypeManager.getPerlTypesLabels()[(Integer) value]);
                    String name = para.getName();
                    for (IContext context : getContextManager().getListContext()) {
                        for (IContextParameter contextParameter : context.getContextParameterList()) {
                            if (name.equals(contextParameter.getName())) {
                                contextParameter.setType(newType);
                            }
                        }
                    }
                }
            } else if (property.equals(COMMENT_COLUMN_NAME)) {
                if (para.getComment().equals(value)) {
                    return;
                }
                String name = para.getName();
                for (IContext context : getContextManager().getListContext()) {
                    for (IContextParameter contextParameter : context.getContextParameterList()) {
                        if (name.equals(contextParameter.getName())) {
                            contextParameter.setComment((String) value);
                        }
                    }
                }
            }

            viewer.update(object, null);
            setNeedRefresh(false);

            Command command = new Command() {

                @Override
                public void execute() {
                    modelManager.refresh();
                }
            };

            runCommand(command);

            // set updated flag.
            if (modelManager != null) {
                IContextManager manager = modelManager.getContextManager();
                if (manager != null && manager instanceof JobContextManager) {
                    ((JobContextManager) manager).setModified(true);
                }
            }
        }
    }

    private IPreferenceStore getPreferenceStore() {
        return CorePlugin.getDefault().getPreferenceStore();
    }

    private void runCommand(Command command) {
        if (modelManager.getCommandStack() == null) {
            command.execute();
        } else {
            modelManager.getCommandStack().execute(command);
        }
    }

    private String convertFormat(String contextParameterType) {
        String newType = null;

        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            String[] values = ITEMS;
            newType = contextParameterType.substring(3, contextParameterType.length());
            for (String format : values) {
                if (format.indexOf("|") != -1) {
                    String[] formats = format.split("\\|");
                    for (String aformat : formats) {
                        if (newType.trim().equals(aformat.trim())) {
                            return format;
                        }
                    }
                } else {
                    if (newType.trim().equals(format.trim())) {
                        return format;
                    }
                }
            }
        } else {
            String[] values = ContextParameterJavaTypeManager.getPerlTypesLabels();
            if ("".equals(contextParameterType)) {
                newType = "";
            } else {
                newType = contextParameterType;
            }
            for (String format : values) {
                if (format.indexOf("|") != -1) {
                    String[] formats = format.split("\\|");
                    for (String aformat : formats) {
                        if (newType.trim().equals(aformat.trim())) {
                            return format;
                        }
                    }
                } else {
                    if (newType.trim().equals(format.trim())) {
                        return format;
                    }
                }
            }
        }
        return "";
    }

    private String getRealType(String type) {
        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            StringBuffer sb = new StringBuffer("id_");
            JavaType javaType = JavaTypesManager.getJavaTypeFromLabel(type);
            if (type.indexOf("|") != -1) {
                return javaType.getId();
            } else {
                if (javaType != null) {
                    return javaType.getId();
                } else {
                    return sb.append(type.trim()).toString();
                }

            }
        } else {
            return type;
        }
    }

    /**
     * A label and content provider for the treeviewer which groups the Contexts by source.
     * 
     */
    class GroupBySourceProvier extends ProviderProxy {

        /**
         * Temporary model for provider. <br/>
         * 
         */
        class Parent {

            String sourceName;

            IContextParameter builtContextParameter;

            List<Son> son = new ArrayList<Son>();
        }

        /**
         * Temporary model for provider. <br/>
         * 
         */
        class Son {

            Parent parent;

            IContextParameter parameter;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            // int index = -1;
            // String name=getContextManager().getDefaultContext().getName();
            if (element instanceof Parent) {
                switch (columnIndex) {
                case 0:
                    if (BUILT_IN.equals(((Parent) element).sourceName)) {
                        if (((Parent) element).builtContextParameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).builtContextParameter.getName()) != null) {
                                return getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()).getName();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (((Parent) element).sourceName != null) {
                            return ((Parent) element).sourceName;
                        } else {
                            break;
                        }
                    }
                case 1:
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        if (BUILT_IN.equals(((Parent) element).sourceName)) {
                            if (((Parent) element).builtContextParameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()) != null) {
                                    return getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()).getSource();
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            if (((Parent) element).sourceName != null) {
                                return ((Parent) element).sourceName;
                            } else {
                                break;
                            }
                        }
                    } else {
                        if (BUILT_IN.equals(((Parent) element).sourceName)) {
                            if (((Parent) element).builtContextParameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()) != null) {
                                    String contextParameterType = getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()).getType();
                                    String s = convertFormat(contextParameterType);
                                    String[] string = null;
                                    if (s.indexOf("|") != -1) {
                                        string = s.split("\\|");
                                        if ("".equals(getContextManager().getDefaultContext().getContextParameter(
                                                ((Parent) element).builtContextParameter.getName()).getValue())
                                                || "null".equals(getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).getValue())) {
                                            if ("boolean | Boolean".equals(s)) {
                                                getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).setValue("false");
                                            }
                                            return string[1];
                                        } else {
                                            if ("boolean | Boolean".equals(s)) {
                                                if (!("true".equals(getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).getValue()))
                                                        && !("false".equals(getContextManager().getDefaultContext()
                                                                .getContextParameter(
                                                                        ((Parent) element).builtContextParameter.getName())
                                                                .getValue()))) {
                                                    getContextManager().getDefaultContext().getContextParameter(
                                                            ((Parent) element).builtContextParameter.getName()).setValue("false");
                                                }
                                            }
                                            return string[0];
                                        }
                                    } else {
                                        return convertFormat(contextParameterType);
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            if (((Parent) element).sourceName != null) {
                                return "-";
                            } else {
                                break;
                            }
                        }
                    }
                case 2:
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        if (BUILT_IN.equals(((Parent) element).sourceName)) {
                            if (((Parent) element).builtContextParameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()) != null) {
                                    String contextParameterType = getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()).getType();
                                    String s = convertFormat(contextParameterType);
                                    String[] string = null;
                                    if (s.indexOf("|") != -1) {
                                        string = s.split("\\|");
                                        if ("".equals(getContextManager().getDefaultContext().getContextParameter(
                                                ((Parent) element).builtContextParameter.getName()).getValue())
                                                || "null".equals(getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).getValue())) {
                                            if ("boolean | Boolean".equals(s)) {
                                                getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).setValue("false");
                                            }
                                            return string[1];
                                        } else {
                                            if ("boolean | Boolean".equals(s)) {
                                                if (!("true".equals(getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).getValue()))
                                                        && !("false".equals(getContextManager().getDefaultContext()
                                                                .getContextParameter(
                                                                        ((Parent) element).builtContextParameter.getName())
                                                                .getValue()))) {
                                                    getContextManager().getDefaultContext().getContextParameter(
                                                            ((Parent) element).builtContextParameter.getName()).setValue("false");
                                                }
                                            }
                                            return string[0];
                                        }
                                    } else {
                                        return convertFormat(contextParameterType);
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            if (((Parent) element).sourceName != null) {
                                return "-";
                            } else {
                                break;
                            }
                        }
                    } else {
                        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                        if (codeLanguage == ECodeLanguage.JAVA) {
                            if (BUILT_IN.equals(((Parent) element).sourceName)) {
                                if (((Parent) element).builtContextParameter != null) {
                                    if (getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()) != null) {
                                        return "context."
                                                + getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).getName();
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                if (((Parent) element).sourceName != null) {
                                    return "-";
                                } else {
                                    break;
                                }
                            }
                        } else {
                            if (BUILT_IN.equals(((Parent) element).sourceName)) {
                                if (((Parent) element).builtContextParameter != null) {
                                    if (getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()) != null) {
                                        return getContextManager().getDefaultContext().getContextParameter(
                                                ((Parent) element).builtContextParameter.getName()).getScriptCode();
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                if (((Parent) element).sourceName != null) {
                                    return "-";
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                case 3:
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                        if (codeLanguage == ECodeLanguage.JAVA) {
                            if (BUILT_IN.equals(((Parent) element).sourceName)) {
                                if (((Parent) element).builtContextParameter != null) {
                                    if (getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()) != null) {
                                        return "context."
                                                + getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).builtContextParameter.getName()).getName();
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                if (((Parent) element).sourceName != null) {
                                    return "-";
                                } else {
                                    break;
                                }
                            }
                        } else {
                            if (BUILT_IN.equals(((Parent) element).sourceName)) {
                                if (((Parent) element).builtContextParameter != null) {
                                    if (getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()) != null) {
                                        return getContextManager().getDefaultContext().getContextParameter(
                                                ((Parent) element).builtContextParameter.getName()).getScriptCode();
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                if (((Parent) element).sourceName != null) {
                                    return "-";
                                } else {
                                    break;
                                }
                            }
                        }
                    } else {
                        if (BUILT_IN.equals(((Parent) element).sourceName)) {
                            if (((Parent) element).builtContextParameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()) != null) {
                                    return getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()).getComment();
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            if (((Parent) element).sourceName != null) {
                                return "-";
                            } else {
                                break;
                            }
                        }
                    }
                case 4:
                    if (BUILT_IN.equals(((Parent) element).sourceName)) {
                        if (((Parent) element).builtContextParameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).builtContextParameter.getName()) != null) {
                                return getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()).getComment();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (((Parent) element).sourceName != null) {
                            return "-";
                        } else {
                            break;
                        }
                    }
                }
            } else {
                Son son = (Son) element;
                switch (columnIndex) {
                case 0:
                    // name column
                    if (son.parameter != null) {
                        if (getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                            return getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()).getName();
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                case 1:
                    // source column
                    if (son.parameter != null) {
                        if (getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                            return getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                    .getSource();
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                case 2:
                    // type column
                    if (son.parameter != null) {
                        if (getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                            String sonType = getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                    .getType();
                            String s = convertFormat(sonType);
                            String[] string = null;
                            if (s.indexOf("|") != -1) {
                                string = s.split("\\|");
                                if ("".equals(getContextManager().getDefaultContext()
                                        .getContextParameter(son.parameter.getName()).getValue())
                                        || "null".equals(getContextManager().getDefaultContext().getContextParameter(
                                                son.parameter.getName()).getValue())) {
                                    if ("boolean | Boolean".equals(s)) {
                                        getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                                .setValue("false");
                                    }
                                    return string[1];
                                } else {
                                    if ("boolean | Boolean".equals(s)) {
                                        if (!("true".equals(getContextManager().getDefaultContext().getContextParameter(
                                                son.parameter.getName()).getValue()))
                                                && !("false".equals(getContextManager().getDefaultContext().getContextParameter(
                                                        son.parameter.getName()).getValue()))) {
                                            getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                                    .setValue("false");
                                        }
                                    }
                                    return string[0];
                                }
                            } else {
                                return convertFormat(sonType);
                            }
                            // return convertFormat(sonType);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                case 3:
                    // script code column
                    final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                    if (codeLanguage == ECodeLanguage.JAVA) {
                        if (son.parameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                                return "context."
                                        + getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                                .getName();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (son.parameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                                return getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                        .getScriptCode();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                case 4:
                    // comment column
                    if (son.parameter != null) {
                        if (getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                            return getContextManager().getDefaultContext().getContextParameter(son.parameter.getName())
                                    .getComment();
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            return "";
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.context.ConextTreeValuesComposite.ProviderProxy#getColumnImage(java.lang.Object, int)
         */
        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            boolean flag = false;
            boolean b = false;
            boolean builtin = false;
            List<String> containers = new ArrayList<String>();
            List<String> nameContainers = new ArrayList<String>();
            List<String> oldBuiltinName = new ArrayList<String>();

            List<IContext> contexts = (List<IContext>) inputElement;
            if (!contexts.isEmpty()) {
                // because all the contexts have the save templ
                for (IContextParameter para : contexts.get(0).getContextParameterList()) {
                    flag = false;
                    if (!containers.isEmpty()) {
                        for (String source : containers) {
                            if (source.equals(para.getSource())) {
                                if (!(BUILT_IN.equals(para.getSource()))) {
                                    flag = true;
                                }
                            }
                        }
                        if (!flag) {
                            containers.add(para.getSource());
                        }
                    } else {
                        containers.add(para.getSource());
                    }
                }
            }

            if (!contexts.isEmpty()) {
                for (IContextParameter para : contexts.get(0).getContextParameterList()) {
                    nameContainers.add(para.getName());
                }
            }

            List<Parent> root = new ArrayList<Parent>();

            if (!contexts.isEmpty()) {
                IContext context = contexts.get(0);
                oldBuiltinName.clear();
                for (String sourceName : containers) {
                    builtin = false;
                    Parent parent = new Parent();
                    parent.sourceName = sourceName;
                    for (String paraName : nameContainers) {
                        IContextParameter contextPara = context.getContextParameter(paraName);
                        if (!(BUILT_IN.equals(contextPara.getSource()))) {
                            if (parent.sourceName.equals(contextPara.getSource())) {
                                Son son = new Son();
                                son.parameter = contextPara;
                                son.parent = parent;
                                parent.son.add(son);
                            }
                        } else {
                            if (BUILT_IN.equals(parent.sourceName)) {
                                if (!builtin) {
                                    b = false;
                                    for (String name : oldBuiltinName) {
                                        if (name.equals(contextPara.getName())) {
                                            b = true;
                                        }
                                    }
                                    if (!b) {
                                        parent.builtContextParameter = contextPara;
                                        oldBuiltinName.add(contextPara.getName());
                                        builtin = true;
                                    }
                                }
                            }
                        }
                    }
                    root.add(parent);
                }
            }
            return root.toArray();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof Parent) {
                return ((Parent) parentElement).son.toArray();
            }
            return new Object[0];
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(Object element) {
            if (element instanceof Son) {
                return ((Son) element).parent;
            }
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(Object element) {
            if (element instanceof Parent) {
                return !((Parent) element).son.isEmpty();
            }
            return false;
        }
    }

    /**
     * A label and content provider for the treeviewer which groups the Contexts by nothing.
     * 
     */
    class GroupByNothingProvier extends ProviderProxy {

        /**
         * Temporary model for provider. <br/>
         * 
         */
        class Parent {

            IContextParameter parameter;

            List<Son> son = new ArrayList<Son>();
        }

        /**
         * Temporary model for provider. <br/>
         * 
         */
        class Son {

            Parent parent;

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof Parent) {
                switch (columnIndex) {
                case 0:
                    if (((Parent) element).parameter != null) {
                        if (getContextManager().getDefaultContext().getContextParameter(((Parent) element).parameter.getName()) != null) {
                            return getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()).getName();
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                case 1:
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        if (((Parent) element).parameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()) != null) {
                                return getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()).getSource();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (((Parent) element).parameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()) != null) {
                                String contextParameterType = getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()).getType();
                                String s = convertFormat(contextParameterType);
                                String[] string = null;
                                if (s.indexOf("|") != -1) {
                                    string = s.split("\\|");
                                    if ("".equals(getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).parameter.getName()).getValue())
                                            || "null".equals(getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).getValue())) {
                                        if ("boolean | Boolean".equals(s)) {
                                            getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).setValue("false");
                                        }
                                        return string[1];
                                    } else {
                                        if ("boolean | Boolean".equals(s)) {
                                            if (!("true".equals(getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).getValue()))
                                                    && !("false".equals(getContextManager().getDefaultContext()
                                                            .getContextParameter(((Parent) element).parameter.getName())
                                                            .getValue()))) {
                                                getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).parameter.getName()).setValue("false");
                                            }
                                        }
                                        return string[0];
                                    }
                                } else {
                                    return convertFormat(contextParameterType);
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                case 2:
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        if (((Parent) element).parameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()) != null) {
                                String contextParameterType = getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()).getType();
                                String s = convertFormat(contextParameterType);
                                String[] string = null;
                                if (s.indexOf("|") != -1) {
                                    string = s.split("\\|");
                                    if ("".equals(getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).parameter.getName()).getValue())
                                            || "null".equals(getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).getValue())) {
                                        if ("boolean | Boolean".equals(s)) {
                                            getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).setValue("false");
                                        }
                                        return string[1];
                                    } else {
                                        if ("boolean | Boolean".equals(s)) {
                                            if (!("true".equals(getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).getValue()))
                                                    && !("false".equals(getContextManager().getDefaultContext()
                                                            .getContextParameter(((Parent) element).parameter.getName())
                                                            .getValue()))) {
                                                getContextManager().getDefaultContext().getContextParameter(
                                                        ((Parent) element).parameter.getName()).setValue("false");
                                            }
                                        }
                                        return string[0];
                                    }
                                } else {
                                    return convertFormat(contextParameterType);
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                        if (codeLanguage == ECodeLanguage.JAVA) {
                            if (((Parent) element).parameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()) != null) {
                                    return "context."
                                            + getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).getName();
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            if (((Parent) element).parameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()) != null) {
                                    return getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).parameter.getName()).getScriptCode();
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                case 3:
                    if ((modelManager instanceof ContextComposite) && !((ContextComposite) modelManager).isRepositoryContext()) {
                        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
                        if (codeLanguage == ECodeLanguage.JAVA) {
                            if (((Parent) element).parameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()) != null) {
                                    return "context."
                                            + getContextManager().getDefaultContext().getContextParameter(
                                                    ((Parent) element).parameter.getName()).getName();
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            if (((Parent) element).parameter != null) {
                                if (getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()) != null) {
                                    return getContextManager().getDefaultContext().getContextParameter(
                                            ((Parent) element).parameter.getName()).getScriptCode();
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    } else {
                        if (((Parent) element).parameter != null) {
                            if (getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()) != null) {
                                return getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).parameter.getName()).getComment();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                case 4:
                    if (((Parent) element).parameter != null) {
                        if (getContextManager().getDefaultContext().getContextParameter(((Parent) element).parameter.getName()) != null) {
                            return getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()).getComment();
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            return "";
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.context.ConextTreeValuesComposite.ProviderProxy#getColumnImage(java.lang.Object, int)
         */
        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            List<IContext> contexts = (List<IContext>) inputElement;
            List<Parent> root = new ArrayList<Parent>();

            if (!contexts.isEmpty()) {
                IContext context = contexts.get(0);
                for (IContextParameter contextParameter : context.getContextParameterList()) {
                    Parent parent = new Parent();
                    parent.parameter = contextParameter;

                    root.add(parent);
                }
            }
            return root.toArray();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof Parent) {
                return ((Parent) parentElement).son.toArray();
            }
            return new Object[0];
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(Object element) {
            if (element instanceof Son) {
                return ((Son) element).parent;
            }
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(Object element) {
            if (element instanceof Parent) {
                return !((Parent) element).son.isEmpty();
            }
            return false;
        }
    }

    /**
     * A label and content provider for the treeviewer which groups the Contexts by source.
     * 
     */
    abstract class ProviderProxy extends LabelProvider implements ITreeContentProvider, ITableLabelProvider {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         * java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }

    /**
     * A label and content provider for the treeviewer which groups the Contexts by variable.
     * 
     */
    class ViewerProvier extends LabelProvider implements ITreeContentProvider, ITableLabelProvider {

        ProviderProxy provider = new GroupBySourceProvier();

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            return provider.getColumnImage(element, columnIndex);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         * java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            return provider.getColumnText(element, columnIndex);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(Object parentElement) {
            return provider.getChildren(parentElement);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(Object element) {
            return provider.getParent(element);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(Object element) {
            return provider.hasChildren(element);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return provider.getElements(inputElement);
        }

        /**
         * Sets the provider.
         * 
         * @param provider the provider to set
         */
        public void setProvider(ProviderProxy provider) {
            this.provider = provider;
        }
    }
}
