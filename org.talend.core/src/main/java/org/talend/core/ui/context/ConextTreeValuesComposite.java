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
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.CorePlugin;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.ui.context.ConextTreeValuesComposite.GroupByVariableProvier.Son;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class ConextTreeValuesComposite extends Composite {

    /**
     * 
     */
    private static final int VARIABLE_COLUMN_INDEX = 4;

    private static final String PRESENTATION_TYPE_KEY = "PRESENTATION_TYPE_KEY";

    private static final String VARIABLE_COLUMN_NAME = "Variable";

    private static final String CONTEXT_COLUMN_NAME = "Context";

    private static final String PROMPTNEEDED_COLUMN_NAME = "PROMPTNEEDED";

    // private static final String COMMENT_COLUMN_NAME = "Comment";

    private static final String VALUE_COLUMN_NAME = "Value";

    private static final String PROMPT_COLUMN_NAME = "Prompt";

    private static final String[] GROUP_BY_VARIABLE_COLUMN_PROPERTIES = new String[] { VARIABLE_COLUMN_NAME, CONTEXT_COLUMN_NAME,
            PROMPTNEEDED_COLUMN_NAME, PROMPT_COLUMN_NAME, VALUE_COLUMN_NAME };

    private static final String[] GROUP_BY_CONTEXT_COLUMN_PROPERTIES = new String[] { CONTEXT_COLUMN_NAME, VARIABLE_COLUMN_NAME,
            PROMPTNEEDED_COLUMN_NAME, PROMPT_COLUMN_NAME, VALUE_COLUMN_NAME };

    private TreeViewer viewer;

    private TreeColumn column1st;

    private TreeColumn column2nd;

    private ViewerProvier provider;

    private IContextModelManager modelManager = null;

    private GroupByVariableAction groupByVariable;

    private GroupByContextAction groupByContext;

    private CellModifier cellModifier;

    private DefaultCellEditorFactory cellFactory;

    private ConfigureContextAction configContext;

    /**
     * bqian ConextTemplateComposite constructor comment.
     * 
     * @param parent
     * @param style
     */
    public ConextTreeValuesComposite(Composite parent, IContextModelManager manager) {
        super(parent, SWT.NONE);
        this.setBackground(parent.getBackground());
        modelManager = manager;
        cellFactory = new DefaultCellEditorFactory(modelManager);
        this.setLayout(GridLayoutFactory.swtDefaults().spacing(0, 0).create());
        initializeUI();
    }

    /**
     * bqian Comment method "initializeUI".
     */
    private void initializeUI() {
        final ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.NO_BACKGROUND);
        GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(toolBar);

        createMenuBar(toolBar);

        viewer = new TreeViewer(this, SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final Tree tree = viewer.getTree();
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);
        tree.setLayoutData(new GridData(GridData.FILL_BOTH));

        column1st = new TreeColumn(tree, SWT.NONE);
        column1st.setText(VARIABLE_COLUMN_NAME);
        column1st.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);
        column2nd = new TreeColumn(tree, SWT.NONE);
        column2nd.setText(CONTEXT_COLUMN_NAME);
        column2nd.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);

        TreeColumn column = new TreeColumn(tree, SWT.NONE);
        column.setResizable(false);
        column.setWidth(20);

        column = new TreeColumn(tree, SWT.NONE);
        column.setText(PROMPT_COLUMN_NAME);
        column.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);
        column = new TreeColumn(tree, SWT.NONE);
        column.setText(VALUE_COLUMN_NAME);
        column.setWidth(ConextTableValuesComposite.CONTEXT_COLUMN_WIDTH);

        viewer.setCellEditors(new CellEditor[] { null, null, new CheckboxCellEditor(tree), new TextCellEditor(tree),
                new TextCellEditor(tree) });
        cellModifier = new CellModifier();
        viewer.setCellModifier(cellModifier);

        provider = new ViewerProvier();
        viewer.setLabelProvider(provider);
        viewer.setContentProvider(provider);
        setDefaultPresentationType();

        final TreeEditor treeEditor = new TreeEditor(tree);
        createEditorListener(treeEditor);
        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent e) {
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
                    Rectangle rect = item.getBounds(VARIABLE_COLUMN_INDEX);
                    if (rect.contains(pt)) {
                        handleSelect(item, tree, treeEditor);
                    }
                }
            }
        });

    }

    public void setEnabled(boolean enabled) {
        configContext.setEnabled(enabled);

    }

    private void activateCellEditor(final TreeItem item, final Tree tree, final TreeEditor treeEditor) {
        // ensure the cell editor is visible
        tree.showSelection();

        IContextParameter para = cellModifier.getRealParameter(item.getData());
        if (para == null) {
            return;
        }
        //
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
        // add our editor listener
        cellEditor.addListener(createEditorListener(treeEditor));

        // set the layout of the tree editor to match the cell editor
        CellEditor.LayoutData layout = cellEditor.getLayoutData();
        treeEditor.horizontalAlignment = layout.horizontalAlignment;
        treeEditor.grabHorizontal = layout.grabHorizontal;
        treeEditor.minimumWidth = layout.minimumWidth;
        treeEditor.setEditor(control, item, VARIABLE_COLUMN_INDEX);
        // give focus to the cell editor
        cellEditor.setFocus();

    }

    protected void handleSelect(final TreeItem item, final Tree tree, final TreeEditor treeEditor) {
        // get the new selection
        activateCellEditor(item, tree, treeEditor);
    }

    private void deactivateCellEditor(final TreeEditor tableEditor) {
        tableEditor.setEditor(null, null, VARIABLE_COLUMN_INDEX);
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

    private ICellEditorListener editorListener;

    private CellEditor cellEditor;

    /**
     * bqian Comment method "createMenuBar".
     * 
     * @param toolBar
     */
    private void createMenuBar(final ToolBar toolBar) {
        ToolItem pullDownButton = new ToolItem(toolBar, SWT.PUSH);
        Image hoverImage = WorkbenchImages.getImage(IWorkbenchGraphicConstants.IMG_LCL_RENDERED_VIEW_MENU);
        pullDownButton.setDisabledImage(hoverImage);
        pullDownButton.setImage(hoverImage);
        pullDownButton.setToolTipText(WorkbenchMessages.Menu);

        MenuManager manager = new MenuManager("Context Configuration");
        IMenuManager submenu = new MenuManager("Context Presentation");
        manager.add(submenu);

        configContext = new ConfigureContextAction(modelManager, this.getShell());
        manager.add(configContext);

        groupByVariable = new GroupByVariableAction();
        groupByContext = new GroupByContextAction();

        submenu.add(groupByVariable);
        submenu.add(groupByContext);

        final Menu aMenu = manager.createContextMenu(toolBar.getParent());

        pullDownButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                Point toolbarSize = toolBar.getSize();
                toolbarSize = toolBar.toDisplay(0, toolbarSize.y);
                aMenu.setLocation(toolbarSize);
                aMenu.setVisible(true);
            }
        });
    }

    public void refresh() {
        IContextManager cm = modelManager.getContextManager();
        viewer.setInput(cm.getListContext());
        viewer.expandAll();
    }

    /**
     * Clear the data in this viewer.
     * 
     * @param jobContextManager2
     */
    public void clear() {
        viewer.setInput(Collections.EMPTY_LIST);
    }

    private IPreferenceStore getPreferenceStore() {
        return CorePlugin.getDefault().getPreferenceStore();
    }

    private void setDefaultPresentationType() {
        IPreferenceStore store = getPreferenceStore();
        String presentationType = store.getString(PRESENTATION_TYPE_KEY);
        if (presentationType == null || presentationType.length() == 0) {
            groupByVariable.setChecked(true);
            groupByContext.setChecked(false);
            groupByVariable.run();
        } else if (store.getString(PRESENTATION_TYPE_KEY).equals(VARIABLE_COLUMN_NAME)) {
            groupByVariable.setChecked(true);
            groupByContext.setChecked(false);
            groupByVariable.run();
        } else if (store.getString(PRESENTATION_TYPE_KEY).equals(CONTEXT_COLUMN_NAME)) {
            groupByContext.setChecked(true);
            groupByVariable.setChecked(false);
            groupByContext.run();
        }
    }

    /**
     * A label and content provider for the treeviewer which groups the Contexts by variable.
     * 
     */
    class ViewerProvier extends LabelProvider implements ITreeContentProvider, ITableLabelProvider {

        ProviderProxy provider = new GroupByVariableProvier();

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

    /**
     * A label and content provider for the treeviewer which groups the Contexts by variable.
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
    class GroupByVariableProvier extends ProviderProxy {

        /**
         * Temporary model for provider. <br/>
         * 
         */
        class Parent {

            String name;

            List<Son> son = new ArrayList<Son>();
        }

        /**
         * Temporary model for provider. <br/>
         * 
         */
        class Son {

            String context;

            Parent parent;

            IContextParameter parameter;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof Parent) {
                if (columnIndex == 0) {
                    // Variable name column
                    return ((Parent) element).name;
                }
            } else {
                Son son = (Son) element;
                switch (columnIndex) {
                case 1:
                    // context column
                    return son.context;
                case 3:
                    // prompt column
                    return son.parameter.getPrompt();
                case 4:
                    // value column
                    return son.parameter.getValue();
                case 5:
                    // comment column
                    return son.parameter.getComment();
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
            if (columnIndex == 2 && (element instanceof Son)) {
                Son son = (Son) element;
                if (son.parameter.isPromptNeeded()) {
                    return ImageProvider.getImage(EImage.CHECKED_ICON);
                } else {
                    return ImageProvider.getImage(EImage.UNCHECKED_ICON);
                }

            }
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            List<String> containers = new ArrayList<String>();

            List<IContext> contexts = (List<IContext>) inputElement;
            if (!contexts.isEmpty()) {
                // because all the contexts have the save templ
                for (IContextParameter para : contexts.get(0).getContextParameterList()) {
                    containers.add(para.getName());
                }
            }

            List<Parent> root = new ArrayList<Parent>();

            for (String paraName : containers) {
                Parent parent = new Parent();
                parent.name = paraName;
                for (IContext context : contexts) {
                    IContextParameter contextPara = context.getContextParameter(paraName);
                    Son son = new Son();
                    son.context = context.getName();
                    son.parameter = contextPara;
                    son.parent = parent;
                    parent.son.add(son);
                }
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
     * bqian ConextTableValuesComposite class global comment. Detailled comment <br/>
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
            if (property.equals(VARIABLE_COLUMN_NAME) || property.equals(CONTEXT_COLUMN_NAME)) {
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
                if (property.equals(VALUE_COLUMN_NAME)) {
                    return para.getValue();
                } else if (property.equals(PROMPT_COLUMN_NAME)) {
                    return para.getPrompt();
                } else if (property.equals(PROMPTNEEDED_COLUMN_NAME)) {
                    return para.isPromptNeeded();
                }
            }
            return "";
        }

        /**
         * bqian Comment method "getRealParameter".
         * 
         * @param property
         * @param templatePara
         * @return
         */
        private IContextParameter getRealParameter(Object element) {
            IContextParameter para = null;

            if (element instanceof IContextParameter) {
                para = (IContextParameter) element;
            } else if (element instanceof Son) {
                para = ((Son) element).parameter;
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

            if (property.equals(VALUE_COLUMN_NAME)) {
                if (para.getValue().equals(value)) {
                    return;
                }
                para.setValue((String) value);
            } else if (property.equals(PROMPT_COLUMN_NAME)) {
                if (para.getPrompt().equals(value)) {
                    return;
                }
                para.setPrompt((String) value);
            } else if (property.equals(PROMPTNEEDED_COLUMN_NAME)) {
                if (para.isPromptNeeded() == ((Boolean) value).booleanValue()) {
                    return;
                }
                para.setPromptNeeded((Boolean) value);
            }
            Command command = new Command() {

                public void execute() {
                    modelManager.refresh();
                }
            };
            runCommand(command);
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
     * A label and content provider for the treeviewer which groups the Contexts by variable.
     * 
     */
    class GroupByContextProvier extends ProviderProxy {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof IContext) {
                if (columnIndex == 0) {
                    // Context column
                    return ((IContext) element).getName();
                }
            } else {
                IContextParameter para = (IContextParameter) element;
                switch (columnIndex) {
                case 1:
                    // variable column
                    return para.getName();
                case 3:
                    // prompt column
                    return para.getPrompt();
                case 4:
                    // value column
                    return para.getValue();
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
            if (columnIndex == 2 && (element instanceof IContextParameter)) {
                IContextParameter para = (IContextParameter) element;
                if (para.isPromptNeeded()) {
                    return ImageProvider.getImage(EImage.CHECKED_ICON);
                } else {
                    return ImageProvider.getImage(EImage.UNCHECKED_ICON);
                }

            }
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            List<IContext> contexts = (List<IContext>) inputElement;
            return contexts.toArray();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof IContext) {
                return ((IContext) parentElement).getContextParameterList().toArray();
            }
            return new Object[0];
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(Object element) {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(Object element) {
            if (element instanceof IContext) {
                return !((IContext) element).getContextParameterList().isEmpty();
            }
            return false;
        }
    }

    /**
     * This action is used to group the text by variables.
     * 
     */
    class GroupByVariableAction extends Action {

        /**
         * bqian GroupByVariableAction constructor comment.
         */
        public GroupByVariableAction() {
            super("Group by Variable", IAction.AS_RADIO_BUTTON);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.action.Action#run()
         */
        @Override
        public void run() {
            getPreferenceStore().setValue(PRESENTATION_TYPE_KEY, VARIABLE_COLUMN_NAME);
            column1st.setText(VARIABLE_COLUMN_NAME);
            column2nd.setText(CONTEXT_COLUMN_NAME);
            viewer.setColumnProperties(GROUP_BY_VARIABLE_COLUMN_PROPERTIES);
            GroupByVariableProvier labelprovider = new GroupByVariableProvier();
            provider.setProvider(labelprovider);
            Tree tree = viewer.getTree();
            viewer.setCellEditors(new CellEditor[] { null, null, new CheckboxCellEditor(tree), new TextCellEditor(tree),
                    new TextCellEditor(tree) });
            viewer.refresh();
            viewer.expandAll();
        }
    }

    /**
     * This action is used to group the text by Context.
     * 
     */
    class GroupByContextAction extends Action {

        /**
         * bqian GroupByVariableAction constructor comment.
         */
        public GroupByContextAction() {
            super("Group by Context", IAction.AS_RADIO_BUTTON);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.action.Action#run()
         */
        @Override
        public void run() {
            getPreferenceStore().setValue(PRESENTATION_TYPE_KEY, CONTEXT_COLUMN_NAME);
            column1st.setText(CONTEXT_COLUMN_NAME);
            column2nd.setText(VARIABLE_COLUMN_NAME);
            viewer.setColumnProperties(GROUP_BY_CONTEXT_COLUMN_PROPERTIES);
            GroupByContextProvier labelprovider = new GroupByContextProvier();
            provider.setProvider(labelprovider);
            Tree tree = viewer.getTree();
            viewer.setCellEditors(new CellEditor[] { null, null, new CheckboxCellEditor(tree), new TextCellEditor(tree),
                    new TextCellEditor(tree) });
            viewer.refresh();
            viewer.expandAll();
        }
    }

}
