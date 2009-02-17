// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CellEditor;
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
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.tooltip.AbstractTreeTooltip;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.core.ui.context.model.ContextValueErrorChecker;
import org.talend.core.ui.context.model.template.ContextConstant;

/**
 * DOC zwang class global comment. Detailled comment <br/>
 * 
 */
public class ConextTableValuesComposite extends AbstractContextTabEditComposite {

    private static final String COLUMN_NAME_PROPERTY = "Name"; //$NON-NLS-1$

    public static final int CONTEXT_COLUMN_WIDTH = 200;

    private TreeViewer viewer;

    private ViewerProvier provider;

    private IContextModelManager modelManager = null;

    private CellModifier cellModifier;

    private DefaultCellEditorFactory cellFactory;

    private ConfigureContextAction configContext;

    private ToolItem contextConfigButton;

    private CellEditor[] cellEditors;

    private ContextValueErrorChecker valueChecker;

    private static final int VALUES_INDEX = 1;

    /**
     * Constructor.
     * 
     * @param parent
     * @param style
     */
    public ConextTableValuesComposite(Composite parent, IContextModelManager manager) {
        super(parent, SWT.NONE);
        modelManager = manager;
        cellFactory = new DefaultCellEditorFactory(modelManager);
        this.setBackground(parent.getBackground());
        this.setLayout(GridLayoutFactory.swtDefaults().spacing(0, 0).create());
        initializeUI();
    }

    /**
     * zwang Comment method "initializeUI".
     * 
     * @param viewer
     */
    private void initializeUI() {
        final ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.NO_BACKGROUND);
        GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(toolBar);

        createToolBar(toolBar);

        viewer = new TreeViewer(this, SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        Tree tree = viewer.getTree();
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);
        tree.setLayoutData(new GridData(GridData.FILL_BOTH));

        TreeColumn column = new TreeColumn(tree, SWT.NONE);
        column.setText(COLUMN_NAME_PROPERTY);
        column.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);

        List<IContext> contextList = getContexts();
        for (IContext context : contextList) {
            column = new TreeColumn(tree, SWT.NONE);
            column.setText(context.getName());
            column.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);
        }

        cellEditors = new CellEditor[getContexts().size() + 1];
        for (int i = 0; i < getContexts().size() + 1; i++) {
            if (i == 0) {
                cellEditors[i] = null;
            } else {
                cellEditors[i] = new TextCellEditor(tree);
            }
        }
        properties = new String[contextList.size() + 1];
        properties[0] = COLUMN_NAME_PROPERTY;
        for (int i = 0; i < contextList.size(); i++) {
            properties[i + 1] = contextList.get(i).getName();
        }
        viewer.setColumnProperties(properties);
        viewer.setCellEditors(cellEditors);
        tree.layout();

        boolean isRepositoryContext = (modelManager instanceof ContextComposite)
                && ((ContextComposite) modelManager).isRepositoryContext();
        cellModifier = new CellModifier(isRepositoryContext);
        viewer.setCellModifier(cellModifier);

        provider = new ViewerProvier();
        boolean value = getPreferenceStore().getBoolean(ITalendCorePrefConstants.CONTEXT_GROUP_BY_SOURCE);
        if (value) {
            provider.setProvider(new GroupBySourceProvier());
        } else {
            provider.setProvider(new GroupByNothingProvier());
        }

        viewer.setLabelProvider(provider);
        viewer.setContentProvider(provider);
        addSorter(viewer);

        final TreeEditor treeEditor = new TreeEditor(viewer.getTree());

        viewer.getTree().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent e) {
                if (modelManager.isReadOnly()) {
                    return;
                }
                Point pt = new Point(e.x, e.y);
                if (e.x > 0 && e.x < (viewer.getTree().getColumnCount()) * ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH) {
                    createEditorListener(treeEditor, e.x / CONTEXT_COLUMN_WIDTH);
                }
                TreeItem item = viewer.getTree().getItem(pt);
                // deactivate the current cell editor
                if (cellEditor != null && !cellEditor.getControl().isDisposed()) {
                    deactivateCellEditor(treeEditor, e.x / CONTEXT_COLUMN_WIDTH);
                }
                if (item != null && !item.isDisposed()) {
                    Rectangle rect = item.getBounds(viewer.getTree().getColumnCount() - 1);

                    if (e.x > 0 && e.x < (viewer.getTree().getColumnCount()) * ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH) {
                        handleSelect(item, viewer.getTree(), treeEditor, viewer.getTree().getColumnCount() - 1, e.x
                                / CONTEXT_COLUMN_WIDTH);
                    }
                }

            }
        });
        valueChecker = new ContextValueErrorChecker(viewer);
        if (LanguageManager.getCurrentLanguage() == ECodeLanguage.PERL) {
            createTreeTooltip(tree);
        }
    }

    /**
     * DOC bqian Comment method "createTreeTooltip".
     * 
     * @param tree
     */
    protected void createTreeTooltip(final Tree tree) {
        new AbstractTreeTooltip(tree) {

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.talend.commons.ui.swt.tooltip.AbstractTreeTooltip#getTooltipContent(org.eclipse.swt.widgets.TreeItem)
             */
            @Override
            public String getTooltipContent(TreeItem item) {

                String property = ""; //$NON-NLS-1$
                if (properties != null && properties.length > VALUES_INDEX) {
                    property = properties[VALUES_INDEX];
                }

                IContextParameter para = cellModifier.getRealParameter(property, item.getData());
                if (para.getType().equalsIgnoreCase(getPerlStringType())) {
                    return Messages.getString("PromptDialog.stringTip"); //$NON-NLS-1$
                }

                return null;
            }
        };
    }

    private IPreferenceStore getPreferenceStore() {
        return CorePlugin.getDefault().getPreferenceStore();
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
                                index) : ""; //$NON-NLS-1$
                        String columnText2 = labelProvider.getColumnText(e2, index) != null ? labelProvider.getColumnText(e2,
                                index) : ""; //$NON-NLS-1$
                        return getComparator().compare(columnText, columnText2) * direction;
                    }
                });
            }
        };
        table.getColumn(0).addListener(SWT.Selection, sortListener);
        if (getContexts().size() > 0) {
            for (int i = 0; i < getContexts().size(); i++) {
                table.getColumn(i + 1).addListener(SWT.Selection, sortListener);
            }
        }
        table.setSortColumn(table.getColumn(0));
        table.setSortDirection(SWT.UP);
    }

    private void activateCellEditor(final TreeItem item, final Tree tree, final TreeEditor treeEditor, int columnIndex, int column) {

        IContextParameter para = cellModifier.getRealParameter(properties[column], item.getData());

        if (para == null) {
            return;
        }
        valueChecker.checkErrors(item, column, para);
        if (!para.isBuiltIn()) {
            // not built-in
            return;
        }
        cellEditor = cellFactory.getCustomCellEditor(para, tree);

        if (cellEditor == null) {
            // unable to create the editor
            return;
        }

        // activate the cell editor
        cellEditor.activate();
        // if the cell editor has no control we can stop now
        Control control = cellEditor.getControl();
        if (control == null) {
            cellEditor.deactivate();
            cellEditor = null;
            return;
        }
        Text textControl = valueChecker.getTextControl(control);
        if (textControl != null) {
            if (ContextParameterUtils.isPasswordType(para)) {
                textControl.setEchoChar('*');
            } else {
                textControl.setEchoChar((char) 0);
            }
        }

        valueChecker.register(control);
        // add our editor listener
        cellEditor.addListener(createEditorListener(treeEditor, column));

        // set the layout of the tree editor to match the cell editor
        CellEditor.LayoutData layout = cellEditor.getLayoutData();
        treeEditor.horizontalAlignment = layout.horizontalAlignment;
        treeEditor.grabHorizontal = layout.grabHorizontal;
        treeEditor.minimumWidth = layout.minimumWidth;

        treeEditor.setEditor(control, item, column);
        // give focus to the cell editor
        cellEditor.setFocus();

    }

    protected void handleSelect(final TreeItem item, final Tree tree, final TreeEditor treeEditor, int columnIndex, int column) {
        // get the new selection
        activateCellEditor(item, tree, treeEditor, columnIndex, column);
    }

    private void deactivateCellEditor(final TreeEditor tableEditor, int columnIndex) {
        tableEditor.setEditor(null, null, columnIndex);
        if (cellEditor != null) {
            Control control = cellEditor.getControl();
            if (control != null) {
                valueChecker.unregister(control);
            }
            cellEditor.deactivate();
            cellEditor.removeListener(editorListener);
            cellEditor = null;
        }
    }

    private ICellEditorListener createEditorListener(final TreeEditor tableEditor, final int columnIndex) {
        editorListener = new ICellEditorListener() {

            public void cancelEditor() {
                deactivateCellEditor(tableEditor, columnIndex);
            }

            public void editorValueChanged(boolean oldValidState, boolean newValidState) {
            }

            public void applyEditorValue() {
                editing = true;
            }
        };
        return editorListener;
    }

    /**
     * bqian Comment method "createMenuBar".
     * 
     * @param toolBar
     */
    private void createToolBar(final ToolBar toolBar) {
        configContext = new ConfigureContextAction(modelManager, this.getShell());
        contextConfigButton = new ToolItem(toolBar, SWT.PUSH);

        contextConfigButton.setImage(ImageProvider.getImage(configContext.getImageDescriptor()));
        contextConfigButton.setToolTipText(configContext.getText());
        contextConfigButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                configContext.run();
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        configContext.setEnabled(enabled);

    }

    private ICellEditorListener editorListener;

    private CellEditor cellEditor;

    private String[] properties;

    private boolean editing;

    /**
     * bqian Comment method "getContexts".
     * 
     * @return
     */
    public List<IContext> getContexts() {
        List<IContext> contexts = new ArrayList<IContext>();
        IContextManager cm = modelManager.getContextManager();
        if (cm != null) {
            contexts = cm.getListContext();
        }
        return contexts;
    }

    @Override
    public void refresh() {
        if (editing) {
            viewer.refresh();
            editing = false;
            return;
        }
        final Tree tree = viewer.getTree();
        TreeColumn[] columns = tree.getColumns();
        for (TreeColumn tableColumn : columns) {
            tableColumn.dispose();
        }

        TreeColumn column = new TreeColumn(tree, SWT.NONE);
        column.setText(COLUMN_NAME_PROPERTY);
        column.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);

        List<IContext> contextList = getContexts();
        for (IContext context : contextList) {
            column = new TreeColumn(tree, SWT.NONE);
            column.setText(context.getName());
            column.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);
        }

        cellEditors = new CellEditor[getContexts().size() + 1];
        for (int i = 0; i < getContexts().size() + 1; i++) {
            if (i == 0) {
                cellEditors[i] = null;
            } else {
                cellEditors[i] = new TextCellEditor(tree);
            }
        }
        properties = new String[contextList.size() + 1];
        properties[0] = COLUMN_NAME_PROPERTY;
        for (int i = 0; i < contextList.size(); i++) {
            properties[i + 1] = contextList.get(i).getName();
        }
        viewer.setColumnProperties(properties);
        viewer.setCellEditors(cellEditors);

        boolean value = getPreferenceStore().getBoolean(ITalendCorePrefConstants.CONTEXT_GROUP_BY_SOURCE);
        if (value) {
            provider.setProvider(new GroupBySourceProvier());
        } else {
            provider.setProvider(new GroupByNothingProvier());
        }
        viewer.setLabelProvider(provider);
        viewer.setContentProvider(provider);
        addSorter(viewer);

        List<IContextParameter> contextTemplate = ContextTemplateComposite.computeContextTemplate(contextList);
        viewer.setInput(contextTemplate);
        viewer.expandAll();
        contextConfigButton.setEnabled(!modelManager.isReadOnly());
        // (feature 1597)
        checkItemValueErrors(tree.getItems());
    }

    private void checkItemValueErrors(final TreeItem[] items) {
        if (items == null) {
            return;
        }
        for (TreeItem item : items) {
            for (int i = 1; i < viewer.getColumnProperties().length; i++) {
                IContextParameter para = cellModifier.getRealParameter((String) viewer.getColumnProperties()[i], item.getData());
                if (para != null && para instanceof IContextParameter) {
                    valueChecker.checkErrors(item, i, para);
                }
            }
            checkItemValueErrors(item.getItems());
        }
    }

    /**
     * Clear the data in this viewer.
     * 
     * @param jobContextManager2
     */
    public void clear() {
        final Tree tree = viewer.getTree();
        TreeColumn[] columns = tree.getColumns();
        for (TreeColumn tableColumn : columns) {
            tableColumn.dispose();
        }
        viewer.setInput(Collections.EMPTY_LIST);
    }

    /**
     * zwang ConextTableValuesComposite class global comment. Detailled comment <br/>
     * 
     */
    class CellModifier implements ICellModifier {

        IContextManager contextManager;

        private boolean reposFlag = false;

        public CellModifier(boolean reposFlag) {
            this.reposFlag = reposFlag;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
         */
        public boolean canModify(Object element, String property) {
            IContextParameter para = null;
            if (modelManager.isReadOnly()) {
                return false;
            }
            List<IContext> contextList = getContexts();
            for (int i = 0; i < (contextList.size() + 1); i++) {
                if (property.equals(properties[i])) {
                    para = getRealParameter(properties[i], element);
                }
            }

            if (para == null) {
                return false;
            }

            if (!para.isBuiltIn() && !reposFlag) {
                // not built-in, not update
                return false;
            }

            if (property.equals(COLUMN_NAME_PROPERTY)) {
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
            IContextParameter para = null;
            contextManager = modelManager.getContextManager();
            List<IContext> contextList = getContexts();
            for (int i = 0; i < (contextList.size() + 1); i++) {
                if (property.equals(properties[i])) {
                    para = getRealParameter(properties[i], element);
                }
            }

            if (para != null) {
                for (IContext context : contextList) {
                    if (property.equals(context.getName())) {
                        return para.getValue();
                    }
                }
            }
            return ""; //$NON-NLS-1$
        }

        /**
         * zwang Comment method "getRealParameter".
         * 
         * @param property
         * @param templatePara
         * @return
         */
        public IContextParameter getRealParameter(String property, Object element) {
            IContextParameter para = null;
            IContext context = null;

            if (!(property.equals(COLUMN_NAME_PROPERTY))) {
                context = modelManager.getContextManager().getContext(property);
            }
            if (context == null) {
                return null;
            }

            if (element instanceof GroupBySourceProvier.Parent) {
                if (IContextParameter.BUILT_IN.equals(((GroupBySourceProvier.Parent) element).sourceName)) {
                    IContextParameter builtContextParameter = ((GroupBySourceProvier.Parent) element).builtContextParameter;
                    if (builtContextParameter != null) {
                        para = context.getContextParameter(builtContextParameter.getName());
                    }
                }
            } else if (element instanceof GroupBySourceProvier.Son) {
                para = context.getContextParameter(((GroupBySourceProvier.Son) element).parameter.getName());
            } else if (element instanceof GroupByNothingProvier.Parent) {
                para = context.getContextParameter(((GroupByNothingProvier.Parent) element).parameter.getName());
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

            IContextParameter para = null;
            List<IContext> contextList = getContexts();
            for (int i = 0; i < (contextList.size() + 1); i++) {
                if (property.equals(properties[i])) {
                    para = getRealParameter(properties[i], object);
                    valueChecker.checkErrors(item, i, para);
                }
            }

            if (para == null) {
                return;
            }

            for (IContext context : contextList) {
                if (property.equals(context.getName())) {
                    if (para.getValue().equals(value)) {
                        return;
                    }
                    para.setValue((String) value);
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
                    JobContextManager jobContextManager = (JobContextManager) manager;
                    if (!modelManager.isRepositoryContext() || modelManager.isRepositoryContext()
                            && jobContextManager.isOriginalParameter(para.getName())) {
                        jobContextManager.setModified(true);
                    }
                }
            }
        }
    }

    private void runCommand(Command command) {
        if (modelManager.getCommandStack() == null) {
            command.execute();
        } else {
            modelManager.getCommandStack().execute(command);
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
            int count = 0;
            List<IContext> contextList = getContexts();
            count = contextList.size();

            if (element instanceof Parent) {
                if (columnIndex == 0) {
                    if (((Parent) element).parameter != null) {
                        if (modelManager.getContextManager().getDefaultContext().getContextParameter(
                                ((Parent) element).parameter.getName()) != null) {
                            return modelManager.getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).parameter.getName()).getName();
                        }
                    }
                }

                for (int j = 1; j <= count; j++) {
                    if (j == 1) {
                        if (columnIndex == j) {
                            if (((Parent) element).parameter != null) {
                                if (contextList.get(columnIndex - 1).getContextParameter(((Parent) element).parameter.getName()) != null) {
                                    return ContextParameterUtils.checkAndHideValue(contextList.get(columnIndex - 1)
                                            .getContextParameter(((Parent) element).parameter.getName()));
                                }
                            }
                        }
                    } else {
                        if (columnIndex == j) {
                            if (((Parent) element).parameter != null) {
                                if (contextList.get(columnIndex - 1).getContextParameter(((Parent) element).parameter.getName()) != null) {
                                    return ContextParameterUtils.checkAndHideValue(contextList.get(columnIndex - 1)
                                            .getContextParameter(((Parent) element).parameter.getName()));
                                }
                            }
                        }
                    }
                }
            }
            return ""; //$NON-NLS-1$
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
            List<IContextParameter> contexts = (List<IContextParameter>) inputElement;
            List<Parent> root = new ArrayList<Parent>();

            for (IContextParameter contextParameter : contexts) {
                Parent parent = new Parent();
                parent.parameter = contextParameter;
                root.add(parent);
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
            int count = 0;
            List<IContext> contextList = getContexts();
            count = contextList.size();

            if (element instanceof Parent) {
                if (columnIndex == 0) {
                    if (IContextParameter.BUILT_IN.equals(((Parent) element).sourceName)) {
                        if (((Parent) element).builtContextParameter != null) {
                            if (modelManager.getContextManager().getDefaultContext().getContextParameter(
                                    ((Parent) element).builtContextParameter.getName()) != null) {
                                return modelManager.getContextManager().getDefaultContext().getContextParameter(
                                        ((Parent) element).builtContextParameter.getName()).getName();
                            }
                        }
                    } else {
                        if (((Parent) element).sourceName != null) {
                            return ((Parent) element).sourceName;
                        }
                    }
                }

                for (int j = 1; j <= count; j++) {
                    if (j == 1) {
                        if (columnIndex == j) {
                            if (IContextParameter.BUILT_IN.equals(((Parent) element).sourceName)) {
                                if (((Parent) element).builtContextParameter != null) {
                                    if (contextList.get(columnIndex - 1).getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()) != null) {
                                        return ContextParameterUtils.checkAndHideValue(contextList.get(columnIndex - 1)
                                                .getContextParameter(((Parent) element).builtContextParameter.getName()));
                                    }
                                }
                            } else {
                                StringBuffer sb = new StringBuffer();
                                for (Son son : ((Parent) element).son) {
                                    if (son.parameter != null) {
                                        for (IContextParameter contextParameter : contextList.get(columnIndex - 1)
                                                .getContextParameterList()) {
                                            if (son.parameter.getName().equals(contextParameter.getName())) {
                                                if (ContextConstant.NULL_STRING.equals(contextParameter.getValue())) {
                                                    sb.append("" + "/"); //$NON-NLS-1$ //$NON-NLS-2$
                                                } else {
                                                    sb.append(ContextParameterUtils.checkAndHideValue(contextParameter) + "/"); //$NON-NLS-1$
                                                }
                                            }
                                        }
                                    }
                                }
                                if (sb.toString().lastIndexOf("/") != -1) { //$NON-NLS-1$
                                    return sb.toString().substring(0, sb.toString().lastIndexOf("/")); //$NON-NLS-1$
                                }
                            }
                        }
                    } else {
                        if (columnIndex == j) {
                            if (IContextParameter.BUILT_IN.equals(((Parent) element).sourceName)) {
                                if (((Parent) element).builtContextParameter != null) {
                                    if (contextList.get(columnIndex - 1).getContextParameter(
                                            ((Parent) element).builtContextParameter.getName()) != null) {
                                        return ContextParameterUtils.checkAndHideValue(contextList.get(columnIndex - 1)
                                                .getContextParameter(((Parent) element).builtContextParameter.getName()));
                                    }
                                }
                            } else {
                                StringBuffer sb = new StringBuffer();
                                for (Son son : ((Parent) element).son) {
                                    if (son.parameter != null) {
                                        for (IContextParameter contextParameter : contextList.get(columnIndex - 1)
                                                .getContextParameterList()) {
                                            if (son.parameter.getName().equals(contextParameter.getName())) {
                                                if (ContextConstant.NULL_STRING.equals(contextParameter.getValue())) {
                                                    sb.append("" + "/"); //$NON-NLS-1$ //$NON-NLS-2$
                                                } else {
                                                    sb.append(ContextParameterUtils.checkAndHideValue(contextParameter) + "/"); //$NON-NLS-1$
                                                }
                                            }
                                        }
                                    }
                                }
                                if (sb.toString().lastIndexOf("/") != -1) { //$NON-NLS-1$
                                    return sb.toString().substring(0, sb.toString().lastIndexOf("/")); //$NON-NLS-1$
                                }
                            }
                        }
                    }
                }
            } else {
                Son son = (Son) element;
                if (columnIndex == 0) {
                    if (son.parameter != null) {
                        if (modelManager.getContextManager().getDefaultContext().getContextParameter(son.parameter.getName()) != null) {
                            return modelManager.getContextManager().getDefaultContext().getContextParameter(
                                    son.parameter.getName()).getName();
                        }
                    }
                }

                for (int j = 1; j <= count; j++) {
                    if (j == 1) {
                        if (columnIndex == j) {
                            if (son.parameter != null) {
                                for (IContextParameter contextParameter : contextList.get(columnIndex - 1)
                                        .getContextParameterList()) {
                                    if (son.parameter.getName().equals(contextParameter.getName())) {
                                        return ContextParameterUtils.checkAndHideValue(contextParameter);
                                    }
                                }
                            }
                        }
                    } else {
                        if (columnIndex == j) {
                            if (son.parameter != null) {
                                for (IContextParameter contextParameter : contextList.get(columnIndex - 1)
                                        .getContextParameterList()) {
                                    if (son.parameter.getName().equals(contextParameter.getName())) {
                                        return ContextParameterUtils.checkAndHideValue(contextParameter);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return ""; //$NON-NLS-1$
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
        @SuppressWarnings("unchecked")
        public Object[] getElements(Object inputElement) {
            boolean flag = false;
            boolean b = false;
            boolean builtin = false;
            int index = 0;
            List<String> containers = new ArrayList<String>();
            List<String> nameContainers = new ArrayList<String>();
            List<String> oldBuiltinName = new ArrayList<String>();

            List<IContextParameter> contexts = (List<IContextParameter>) inputElement;

            if (!contexts.isEmpty()) {
                // because all the contexts have the save templ
                for (IContextParameter para : contexts) {
                    nameContainers.add(para.getName());
                    flag = false;
                    if (!containers.isEmpty()) {
                        for (String source : containers) {
                            if (source.equals(para.getSource())) {
                                if (!(IContextParameter.BUILT_IN.equals(para.getSource()))) {
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

            List<Parent> root = new ArrayList<Parent>();

            if (!contexts.isEmpty()) {
                for (String sourceName : containers) {
                    builtin = false;
                    Parent parent = new Parent();
                    parent.sourceName = sourceName;
                    for (String paraName : nameContainers) {
                        for (IContextParameter para : contexts) {
                            if (para.getName().equals(paraName)) {
                                index = contexts.indexOf(para);
                            }
                        }
                        IContextParameter contextPara = contexts.get(index);
                        if (!(IContextParameter.BUILT_IN.equals(contextPara.getSource()))) {
                            if (parent.sourceName.equals(contextPara.getSource())) {
                                Son son = new Son();
                                son.parameter = contextPara;
                                son.parent = parent;
                                parent.son.add(son);
                            }
                        } else {
                            if (IContextParameter.BUILT_IN.equals(parent.sourceName)) {
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
