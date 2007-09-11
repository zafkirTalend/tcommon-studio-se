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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.LAYOUT_MODE;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.celleditor.DateDialog;
import org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorManager;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContext;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.designer.core.ui.celleditor.JavaTypeComboValueAdapter;
import org.talend.repository.model.RepositoryConstants;

/**
 * nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public abstract class JobContextComposite extends Composite {

    private CTabFolder tabFolder;

    private static final String DEFAULT_CONTEXT = "defaultContext"; //$NON-NLS-1$

    private static final String MENU_TABLE = Messages.getString("ContextProcessSection.1"); //$NON-NLS-1$

    private static final String ASK_CONFIRMATION = Messages.getString("ContextProcessSection.2"); //$NON-NLS-1$

    private static final int FIRST_COLUMN_WIDTH = 50;

    private static final int NAME_COLUMN_WIDTH = 80;

    private static final int PROMPT_COLUMN_WIDTH = 120;

    private TableViewerCreatorColumn columnDefault;

    private static final int TYPE_COLUMN_WIDTH = 80;

    private static final int VALUE_COLUMN_WIDTH = 140;

    private static final int COMMENT_COLUMN_WIDTH = 200;

    private static final int SCRIPT_COLUMN_WIDTH = 120;

    private Map<String, Object> hashCurControls;

    private static final String NEW_PARAM_NAME = "new"; //$NON-NLS-1$

    private static final String ID_DEFAULT = "id_columnDefault";

    private static final int CNUM_DEFAULT = 4;

    private Map<IContext, TableViewerCreator> tableViewerCreatorMap;

    private Map<IContext, Button> removeButtons;

    boolean readOnly = false;

    private CellEditor cellEditor;

    private IContextManager jobContextManager;

    public JobContextComposite(Composite parent) {
        super(parent, SWT.None);
        this.setBackground(parent.getBackground());
    }

    public void setContextManager(IContextManager jobContextManager) {
        this.jobContextManager = jobContextManager;
    }

    public IContextManager getContextManager() {
        return jobContextManager;
    }

    protected abstract void onContextAdd(JobContextComposite composite, IContext newContext, CCombo combo);

    protected abstract void onContextRemove(JobContextComposite composite, String contextName, CCombo combo);

    protected abstract void onContextModify(IContextManager contextManager, IContext oldContext, IContext newContext);

    protected abstract void onContextChangeDefault(IContextManager contextManager, IContext newDefault);

    protected abstract void onContextAddParameter(IContextManager contextManager, IContextParameter contextParam);

    protected abstract void onContextRemoveParameter(IContextManager contextManager, String contextParamName);

    protected abstract void onContextRenameParameter(IContextManager contextManager, String oldName, String newName);

    private SelectionListener listenerSelection = new SelectionListener() {

        public void widgetDefaultSelected(final SelectionEvent e) {
        }

        public void widgetSelected(final SelectionEvent e) {
            if (e.getSource() instanceof Button) {
                Button b = (Button) e.getSource();
                boolean selected = b.getSelection();
                IContext context = getSelectedContext();
                IContext oldContextCloned = context.clone();
                context.setConfirmationNeeded(selected);

                onContextModify(jobContextManager, oldContextCloned, context);
            } else {

                CCombo combo = (CCombo) e.getSource();
                for (int i = 0; i < jobContextManager.getListContext().size(); i++) {
                    if (jobContextManager.getListContext().get(i).getName().equals(combo.getText())) {
                        onContextChangeDefault(jobContextManager, jobContextManager.getListContext().get(i));
                    }
                }
                IContext context = getSelectedContext();
                if (context.getName().equals(jobContextManager.getDefaultContext().getName())) {
                    removeButtons.get(context).setEnabled(false);
                } else {
                    removeButtons.get(context).setEnabled(!isReadOnly());
                }
            }
        }
    };

    private Listener menuTableListener = new Listener() {

        public void handleEvent(final Event event) {
            Menu menuTable = (Menu) hashCurControls.get(MENU_TABLE);
            MenuItem[] menuItems = menuTable.getItems();
            for (int i = 0; i < menuItems.length; i++) {
                menuItems[i].dispose();
            }

            final IContext selectedContext = getSelectedContext();
            TableViewerCreator tableViewerCreator = tableViewerCreatorMap.get(selectedContext);
            final Table table = tableViewerCreator.getTable();
            final TableItem[] tableItems = table.getSelection();

            MenuItem menuItem = new MenuItem(menuTable, SWT.PUSH);
            menuItem.setText(Messages.getString("ContextProcessSection.21")); //$NON-NLS-1$
            menuItem.addListener(SWT.Selection, new Listener() {

                public void handleEvent(final Event event) {
                    addNewParameter();
                }
            });

            if (tableItems.length == 1) {
                menuItem = new MenuItem(menuTable, SWT.PUSH);
                menuItem.setText(Messages.getString("ContextProcessSection.22") //$NON-NLS-1$ 
                        + tableItems[0].getText(1) + Messages.getString("ContextProcessSection.0")); //$NON-NLS-1$
                menuItem.addListener(SWT.Selection, new Listener() {

                    public void handleEvent(final Event event) {
                        TableItem tableItem = tableItems[0];
                        String paramName = ((IContextParameter) tableItem.getData()).getName();
                        onContextRemoveParameter(jobContextManager, paramName);
                        table.deselectAll();
                        removeButtons.get(selectedContext).setEnabled(false);
                    }
                });
            }
        }
    };

    CellEditorValueAdapter perlComboCellEditorValueAdapter = new CellEditorValueAdapter() {

        @Override
        public Object getCellEditorTypedValue(final CellEditor cellEditor, final Object originalTypedValue) {
            Integer intValue = new Integer(-1);
            oldCellEditorValue = originalTypedValue;
            oldContext = getSelectedContext().clone();
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
            newCellEditorValue = cellEditorTypedValue;
            Integer intValue = (Integer) cellEditorTypedValue;
            String value = ""; //$NON-NLS-1$
            if (intValue >= 0) {
                value = ContextParameterJavaTypeManager.getPerlTypesLabels()[(Integer) cellEditorTypedValue];
            }
            return super.getOriginalTypedValue(cellEditor, value);
        }

        public String getColumnText(final CellEditor cellEditor, final Object cellEditorValue) {
            return (String) cellEditorValue;
        }
    };

    IBeanPropertyAccessors<IContextParameter, Boolean> nullableAccessors = new IBeanPropertyAccessors<IContextParameter, Boolean>() {

        public Boolean get(IContextParameter bean) {
            return Boolean.FALSE; // new Boolean(bean.isNullable());
        }

        public void set(IContextParameter bean, Boolean value) {
            // bean.setNullable(value);
        }

    };

    JavaTypeComboValueAdapter<IContextParameter> javaComboCellEditorValueAdapter = new JavaTypeComboValueAdapter<IContextParameter>(
            ContextParameterJavaTypeManager.getDefaultJavaType(), nullableAccessors) {

        @Override
        public Object getCellEditorTypedValue(CellEditor cellEditor, Object originalTypedValue) {
            oldCellEditorValue = originalTypedValue;
            oldContext = getSelectedContext().clone();
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
            newCellEditorValue = cellEditorTypedValue;
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

    // used when fields are modified in the table viewer
    private Object oldCellEditorValue, newCellEditorValue;

    private IContext oldContext;

    private CellEditorValueAdapter setDirtyValueAdapter = new CellEditorValueAdapter() {

        @Override
        public Object getCellEditorTypedValue(final CellEditor cellEditor, final Object originalTypedValue) {
            oldCellEditorValue = originalTypedValue;
            oldContext = getSelectedContext().clone();
            return super.getCellEditorTypedValue(cellEditor, originalTypedValue);
        }

        @Override
        public Object getOriginalTypedValue(final CellEditor cellEditor, final Object cellEditorTypedValue) {
            newCellEditorValue = cellEditorTypedValue;
            return super.getOriginalTypedValue(cellEditor, cellEditorTypedValue);
        }
    };

    private SelectionAdapter checkTableAdapter = new SelectionAdapter() {

        public void widgetSelected(final SelectionEvent e) {
            if (e.detail == SWT.CHECK) {
                TableItem tableItem = (TableItem) e.item;
                boolean promptNeeded = tableItem.getChecked();
                String paramName = ((IContextParameter) tableItem.getData()).getName();
                IContext context = getSelectedContext();
                IContext oldContextCloned = context.clone();
                List<IContextParameter> listParams = context.getContextParameterList();
                boolean paramNameFound = false;
                for (int i = 0; i < listParams.size() && !paramNameFound; i++) {
                    if (paramName.equals(listParams.get(i).getName())) {
                        listParams.get(i).setPromptNeeded(promptNeeded);
                        paramNameFound = true;
                        onContextModify(jobContextManager, oldContextCloned, context);
                    }
                }
            }

        }
    };

    private Listener removeContextListener = new Listener() {

        public void handleEvent(final Event event) {
            String contextName = tabFolder.getSelection().getText();
            boolean delete = MessageDialog.openQuestion(getShell(), Messages.getString("ContextProcessSection.18"), //$NON-NLS-1$
                    Messages.getString("ContextProcessSection.19") + contextName + ")"); //$NON-NLS-1$ //$NON-NLS-2$

            if (delete) {
                removeContext(contextName);
            }
        }
    };

    private Listener copyContextListener = new Listener() {

        public void handleEvent(final Event event) {
            InputDialog inputDial = new InputDialog(getShell(), Messages.getString("ContextProcessSection.6"), //$NON-NLS-1$
                    Messages.getString("ContextProcessSection.7"), "", null); //$NON-NLS-1$ //$NON-NLS-2$

            inputDial.open();
            String returnValue = inputDial.getValue();
            if (returnValue != null) {
                if (!returnValue.equals("") && Pattern.matches(RepositoryConstants.CODE_ITEM_PATTERN, returnValue)) { //$NON-NLS-1$
                    createContext(returnValue);
                } else {
                    MessageDialog.openWarning(new Shell(), Messages.getString(Messages
                            .getString("ContextProcessSection.50")), Messages //$NON-NLS-1$
                            .getString(Messages.getString("ContextProcessSection.51"))); //$NON-NLS-1$
                }
            }
        }
    };

    private Listener renameContextListener = new Listener() {

        public void handleEvent(final Event event) {
            String contextName = tabFolder.getSelection().getText();
            InputDialog inputDial = new InputDialog(getShell(), Messages.getString("ContextProcessSection.12"), //$NON-NLS-1$
                    Messages.getString("ContextProcessSection.13", contextName), "", null); //$NON-NLS-1$ //$NON-NLS-2$
            inputDial.open();
            String newName = inputDial.getValue();
            if (newName != null) {
                if (!newName.equals("") && Pattern.matches(RepositoryConstants.CODE_ITEM_PATTERN, newName)) { //$NON-NLS-1$
                    renameContext(contextName, newName);
                } else {
                    MessageDialog.openWarning(new Shell(), Messages.getString(Messages
                            .getString("ContextProcessSection.52")), Messages //$NON-NLS-1$
                            .getString(Messages.getString("ContextProcessSection.53"))); //$NON-NLS-1$
                }
            }
        }
    };

    private Listener removeParameterListener = new Listener() {

        public void handleEvent(final Event event) {
            final IContext selectedContext = getSelectedContext();
            TableViewerCreator tableViewerCreator = tableViewerCreatorMap.get(selectedContext);
            Table table = tableViewerCreator.getTable();
            final TableItem[] tableItems = table.getSelection();
            if (tableItems.length < 1) {
                return;
            }
            TableItem tableItem = tableItems[0];
            String paramName = ((IContextParameter) tableItem.getData()).getName();
            table.deselectAll();
            onContextRemoveParameter(jobContextManager, paramName);
            removeButtons.get(selectedContext).setEnabled(false);
        }
    };

    private void addNewParameter() {
        IContext context = getSelectedContext();
        List<IContextParameter> listParams = context.getContextParameterList();
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
            defaultValue = ContextParameterJavaTypeManager.getDefaultValueFromJavaIdType(
                    ContextParameterJavaTypeManager.getDefaultJavaType().getId(), false);
        } else {
            defaultValue = TalendTextUtils.addQuotes(""); //$NON-NLS-1$
        }
        contextParam.setValue(defaultValue);
        contextParam.setComment(""); //$NON-NLS-1$
        onContextAddParameter(jobContextManager, contextParam);
    }

    private boolean renameParameter(final String oldParamName, final String newParamName) {
        if (!jobContextManager.checkValidParameterName(newParamName)) {
            MessageDialog
                    .openError(
                            this.getShell(),
                            Messages.getString("ContextProcessSection.errorTitle"), Messages.getString("ContextProcessSection.ParameterNameIsNotValid")); //$NON-NLS-1$ //$NON-NLS-2$
            return false;
        }

        onContextRenameParameter(jobContextManager, oldParamName, newParamName);
        return true;
    }

    private IContext getSelectedContext() {
        List<IContext> contexts = jobContextManager.getListContext();
        CTabItem tabItem = tabFolder.getSelection();
        int numContext = 0;

        while (!tabItem.getText().equals(contexts.get(numContext).getName())) {
            numContext++;
        }
        return contexts.get(numContext);
    }

    public void createContext(final String name) {
        IContext context = this.getSelectedContext();

        for (int i = 0; i < jobContextManager.getListContext().size(); i++) {
            if (jobContextManager.getListContext().get(i).getName().equalsIgnoreCase(name)) {
                MessageBox mBox = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                mBox.setText(Messages.getString("ContextProcessSection.29")); //$NON-NLS-1$
                mBox.setMessage(Messages.getString("ContextProcessSection.30")); //$NON-NLS-1$
                mBox.open();
                return;
            }
        }
        JobContext newContext = new JobContext(name);

        List<IContextParameter> newParamList = new ArrayList<IContextParameter>();
        newContext.setContextParameterList(newParamList);
        JobContextParameter param;
        for (int i = 0; i < context.getContextParameterList().size(); i++) {
            param = new JobContextParameter();
            param.setName(context.getContextParameterList().get(i).getName());
            param.setPrompt(context.getContextParameterList().get(i).getPrompt());
            param.setType(context.getContextParameterList().get(i).getType());
            param.setValue(context.getContextParameterList().get(i).getValue());
            param.setComment(context.getContextParameterList().get(i).getComment());
            param.setPromptNeeded(context.getContextParameterList().get(i).isPromptNeeded());
            newParamList.add(param);
        }
        CCombo combo = (CCombo) hashCurControls.get(DEFAULT_CONTEXT);
        onContextAdd(this, newContext, combo);
    }

    public void removeContext(final String contextName) {
        if (contextName.equals(jobContextManager.getDefaultContext().getName())) {
            MessageBox mBox = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            mBox.setText(Messages.getString("ContextProcessSection.31")); //$NON-NLS-1$
            mBox.setMessage(Messages.getString("ContextProcessSection.32")); //$NON-NLS-1$
            mBox.open();
            return;
        }
        CCombo combo = (CCombo) hashCurControls.get(DEFAULT_CONTEXT);

        onContextRemove(this, contextName, combo);
        this.layout();
    }

    private void renameContext(final String contextName, final String newName) {
        IContext context = this.getSelectedContext();
        IContext oldContextCloned = context.clone();
        boolean found = false;

        for (int i = 0; i < jobContextManager.getListContext().size() && !found; i++) {
            if (jobContextManager.getListContext().get(i).getName().equalsIgnoreCase(newName)) {
                MessageBox mBox = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                mBox.setText(Messages.getString("ContextProcessSection.33")); //$NON-NLS-1$
                mBox.setMessage(Messages.getString("ContextProcessSection.34")); //$NON-NLS-1$
                mBox.open();
                return;
            }
        }
        context.setName(newName);
        found = false;
        for (int i = 0; i < tabFolder.getItemCount() && !found; i++) {
            if (tabFolder.getItem(i).getText().equals(contextName)) {
                tabFolder.getItem(i).setText(newName);
                found = true;
            }
        }
        tabFolder.update();

        CCombo combo = (CCombo) hashCurControls.get(DEFAULT_CONTEXT);
        String[] stringList = new String[jobContextManager.getListContext().size()];
        for (int i = 0; i < jobContextManager.getListContext().size(); i++) {
            stringList[i] = jobContextManager.getListContext().get(i).getName();
        }

        combo.setItems(stringList);
        this.layout();
        combo.setText(jobContextManager.getDefaultContext().getName());
        combo.clearSelection();
        onContextModify(jobContextManager, oldContextCloned, context);
    }

    public void addContext(final IContext context) {
        Composite contextComposite = new Composite(tabFolder, SWT.NONE);
        contextComposite.setLayout(new GridLayout());
        contextComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        Composite buttonComposite = new Composite(contextComposite, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(3, false));
        buttonComposite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

        final Button checkBtn;
        checkBtn = new Button(buttonComposite, SWT.CHECK | SWT.FLAT);
        checkBtn.setText(ASK_CONFIRMATION);
        checkBtn.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
        checkBtn.addSelectionListener(listenerSelection);
        checkBtn.setEnabled(!isReadOnly());
        checkBtn.setSelection(context.isConfirmationNeeded());

        Composite buttonParameterComposite = new Composite(buttonComposite, SWT.NONE);
        buttonParameterComposite.setLayout(new GridLayout(3, false));
        buttonParameterComposite
                .setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL));
        CLabel label = new CLabel(buttonParameterComposite, SWT.NONE);
        label.setText(Messages.getString("ContextProcessSection.parametersLabel")); //$NON-NLS-1$
        label.setAlignment(SWT.RIGHT);
        Button addParameter = new Button(buttonParameterComposite, SWT.None);
        addParameter.setText(Messages.getString("ContextProcessSection.addParameterLabel")); //$NON-NLS-1$
        addParameter.addListener(SWT.Selection, new Listener() {

            public void handleEvent(final Event event) {
                addNewParameter();
            }
        });
        addParameter.setEnabled(!isReadOnly());
        final Button removeParameter = new Button(buttonParameterComposite, SWT.None);
        removeParameter.setText(Messages.getString("ContextProcessSection.removeParameterLabel")); //$NON-NLS-1$
        removeParameter.setEnabled(false);

        Composite buttonContextComposite = new Composite(buttonComposite, SWT.NONE);
        buttonContextComposite.setLayout(new GridLayout(4, false));
        buttonContextComposite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL));
        label = new CLabel(buttonContextComposite, SWT.NONE);
        label.setText(Messages.getString("ContextProcessSection.contextLabel")); //$NON-NLS-1$
        label.setAlignment(SWT.RIGHT);
        Button copyContext = new Button(buttonContextComposite, SWT.None);
        copyContext.setText(Messages.getString("ContextProcessSection.copyLabel")); //$NON-NLS-1$
        copyContext.addListener(SWT.Selection, copyContextListener);
        copyContext.setEnabled(!isReadOnly());
        Button renameContext = new Button(buttonContextComposite, SWT.None);
        renameContext.setText(Messages.getString("ContextProcessSection.renameLabel")); //$NON-NLS-1$
        renameContext.addListener(SWT.Selection, renameContextListener);
        renameContext.setEnabled(!isReadOnly());
        final Button removeContext = new Button(buttonContextComposite, SWT.None);
        removeContext.setText(Messages.getString("ContextProcessSection.removeLabel")); //$NON-NLS-1$
        removeContext.addListener(SWT.Selection, removeContextListener);
        removeContext.setEnabled(!isReadOnly());
        removeButtons.put(context, removeContext);

        tabFolder.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            public void widgetSelected(final SelectionEvent e) {
                IContext context = getSelectedContext();
                checkBtn.setSelection(context.isConfirmationNeeded());
                if (!isReadOnly()) {
                    if (context.getName().equals(jobContextManager.getDefaultContext().getName())) {
                        removeContext.setEnabled(false);
                    } else {
                        removeContext.setEnabled(true);
                    }
                }
            }
        });
        CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
        tabItem.setText(context.getName());

        final TableViewerCreator tableViewerCreator = new TableViewerCreator(contextComposite);
        tableViewerCreatorMap.put(context, tableViewerCreator);
        tableViewerCreator.setBorderVisible(true);
        tableViewerCreator.setCheckboxInFirstColumn(true);
        tableViewerCreator.setColumnsResizableByDefault(true);
        tableViewerCreator.setColumnsSortableByDefault(true);
        tableViewerCreator.setLayoutMode(LAYOUT_MODE.FILL_HORIZONTAL);

        final Table newtable = tableViewerCreator.createTable();

        final TableEditor treeEditor = new TableEditor(newtable);
        createEditorListener(treeEditor);
        removeParameter.addListener(SWT.Selection, removeParameterListener);
        newtable.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        if (!isReadOnly()) {
            Menu menuTable;
            menuTable = (Menu) hashCurControls.get(MENU_TABLE);
            newtable.setMenu(menuTable);
            menuTable.addListener(SWT.Show, menuTableListener);
        }

        newtable.setEnabled(!isReadOnly());
        newtable.addSelectionListener(checkTableAdapter);

        addTableColumns(tableViewerCreator, newtable);

        final List<IContextParameter> listContextParam = context.getContextParameterList();
        tableViewerCreator.init(listContextParam);
        TableItem tableItem;
        for (int i = 0; i < newtable.getItemCount(); i++) {
            tableItem = newtable.getItem(i);
            String paramName = ((IContextParameter) tableItem.getData()).getName();
            List<IContextParameter> listParams = context.getContextParameterList();
            boolean paramNameFound = false;
            for (int j = 0; j < listParams.size() && !paramNameFound; j++) {
                if (paramName.equals(listParams.get(j).getName())) {
                    tableItem.setChecked(listParams.get(i).isPromptNeeded());
                    paramNameFound = true;
                }
            }
        }

        final TableEditorManager tableEditorManager = new TableEditorManager(tableViewerCreator);
        tableEditorManager.init();
        tabItem.setControl(contextComposite);
        if (context.getName().equals(jobContextManager.getDefaultContext().getName())) {
            tabFolder.setSelection(tabItem);
            removeContext.setEnabled(false);
        }

        newtable.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                removeParameter.setEnabled(!isReadOnly());
            }
        });
        newtable.addMouseListener(new MouseAdapter() {

            public void mouseDown(MouseEvent e) {
                Rectangle clientArea = newtable.getClientArea();
                Point pt = new Point(e.x, e.y);
                TableItem item = newtable.getItem(pt);
                // deactivate the current cell editor
                if (cellEditor != null && !cellEditor.getControl().isDisposed()) {
                    deactivateCellEditor(treeEditor);
                }
                if (item != null) {
                    boolean visible = false;
                    for (int i = 0; i < newtable.getColumnCount(); i++) {
                        Rectangle rect = item.getBounds(i);
                        if (rect.contains(pt)) {
                            final int column = i;
                            if (column == CNUM_DEFAULT) {
                                handleSelect(item, newtable, treeEditor);
                            }
                        }
                        if (!visible && rect.intersects(clientArea)) {
                            visible = true;
                        }
                    }
                    if (!visible) {
                        return;
                    }
                }
            }

        });
    }

    /**
     * zx Comment method "handleSelect".
     * 
     * @param item
     * @link PropertySheetViewer
     */

    protected void handleSelect(final TableItem selection, final Table table, final TableEditor tableEditor) {
        // get the new selection
        TableItem[] sel = new TableItem[] { selection };
        if (sel.length == 0) {
            return;
        } else {
            activateCellEditor(sel[0], table, tableEditor);
        }
    }

    private DefaultCellEditorFactory cellEditorFactory = new DefaultCellEditorFactory();

    /**
     * zx Comment method "activateCellEditor".
     * 
     * @param item
     */
    private void activateCellEditor(final TableItem item, final Table table, final TableEditor tableEditor) {
        // ensure the cell editor is visible
        table.showSelection();

        cellEditor = cellEditorFactory.getCustomCellEditor(((IContextParameter) item.getData()).getType(), table,
                getSelectedContext(), tableViewerCreatorMap.get(getSelectedContext()));
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
        cellEditor.addListener(createEditorListener(tableEditor));

        // set the layout of the tree editor to match the cell editor
        CellEditor.LayoutData layout = cellEditor.getLayoutData();
        tableEditor.horizontalAlignment = layout.horizontalAlignment;
        tableEditor.grabHorizontal = layout.grabHorizontal;
        tableEditor.minimumWidth = layout.minimumWidth;
        tableEditor.setEditor(control, item, CNUM_DEFAULT);
        // give focus to the cell editor
        cellEditor.setFocus();

    }

    private ICellEditorListener editorListener;

    /**
     * zx Comment method "deactivateCellEditor".
     */
    private void deactivateCellEditor(final TableEditor tableEditor) {
        tableEditor.setEditor(null, null, CNUM_DEFAULT);
        if (cellEditor != null) {
            cellEditor.deactivate();
            cellEditor.removeListener(editorListener);
            cellEditor = null;
        }
    }

    private ICellEditorListener createEditorListener(final TableEditor tableEditor) {
        editorListener = new ICellEditorListener() {

            public void cancelEditor() {
                deactivateCellEditor(tableEditor);
            }

            public void editorValueChanged(boolean oldValidState, boolean newValidState) {
                // Do nothing
            }

            public void applyEditorValue() {
                // Do nothing
            }
        };
        return editorListener;
    }

    private CellEditor textCellEditor2;

    private void addTableColumns(final TableViewerCreator tableViewerCreator, final Table table) {
        TableViewerCreatorColumn column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ContextProcessSection.prompt")); //$NON-NLS-1$
        column.setModifiable(true);
        column.setWidth(FIRST_COLUMN_WIDTH);
        column.setToolTipHeader(Messages.getString("ContextProcessSection.38")); //$NON-NLS-1$

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ContextProcessSection.39")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getName();
            }

            public void set(IContextParameter bean, String value) {
                bean.setName(value);
            }
        });
        column.setModifiable(true);
        column.setWidth(NAME_COLUMN_WIDTH);
        TextCellEditor textCellEditor = new TextCellEditor(table);
        column.setCellEditor(textCellEditor, paramNameCellEditorValueAdapter);

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ContextProcessSection.41")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getPrompt();
            }

            public void set(IContextParameter bean, String value) {
                bean.setPrompt(value);
                if (!oldCellEditorValue.equals(newCellEditorValue)) {
                    IContext context = getSelectedContext();
                    onContextModify(jobContextManager, oldContext, context);
                }
            }
        });
        column.setModifiable(true);
        column.setWidth(PROMPT_COLUMN_WIDTH);
        textCellEditor = new TextCellEditor(table);
        column.setCellEditor(textCellEditor, setDirtyValueAdapter);

        // //////////////////////////////////////////////////////////
        // Type column
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ContextProcessSection.43")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getType();
            }

            public void set(IContextParameter bean, String value) {
                bean.setType(value);
                if (!oldCellEditorValue.equals(newCellEditorValue)) {
                    IContext context = getSelectedContext();
                    onContextModify(jobContextManager, oldContext, context);
                }
                /*
                 * columnDefault.setCellEditor(getCellEditor(table, value)); tableViewerCreator.attachCellEditors();
                 */
            }
        });
        column.setModifiable(true);
        column.setWidth(TYPE_COLUMN_WIDTH);

        ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(table, MetadataTalendType
                .getCxtParameterTalendTypesLabels());
        CellEditorValueAdapter comboValueAdapter = null;
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            comboValueAdapter = javaComboCellEditorValueAdapter;
        } else if (codeLanguage == ECodeLanguage.PERL) {
            comboValueAdapter = perlComboCellEditorValueAdapter;
        }
        column.setCellEditor(comboBoxCellEditor, comboValueAdapter);

        ((CCombo) comboBoxCellEditor.getControl()).setEditable(false);
        // //////////////////////////////////////////////////////////

        columnDefault = new TableViewerCreatorColumn(tableViewerCreator);
        columnDefault.setId(ID_DEFAULT);
        columnDefault.setTitle(Messages.getString("ContextProcessSection.45")); //$NON-NLS-1$
        columnDefault.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getValue();
            }

            public void set(IContextParameter bean, String value) {
                bean.setValue(value);
                if (!oldCellEditorValue.equals(newCellEditorValue)) {
                    IContext newContext = getSelectedContext();
                    onContextModify(jobContextManager, oldContext, newContext);
                }
            }
        });
        columnDefault.setModifiable(true);
        columnDefault.setWidth(VALUE_COLUMN_WIDTH);
        textCellEditor2 = new TextCellEditor(table);
        columnDefault.setCellEditor(textCellEditor2, setDirtyValueAdapter);
        textCellEditor2.getControl().addMouseListener(new MouseAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.MouseAdapter#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
             */
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                final TableItem[] selection = tableViewerCreator.getTable().getSelection();
                if (selection.length > 0) {
                    TableItem item = selection[0];
                    if (item.getData() instanceof JobContextParameter) {
                        JobContextParameter parameter = (JobContextParameter) item.getData();
                        String id = parameter.getType();
                        if (e.getSource() instanceof Text) {
                            Text text = (Text) e.getSource();
                            if (id.equals(JavaTypesManager.FILE.getId())) {
                                FileDialog d = new FileDialog(text.getShell());
                                String open = d.open();
                                if (open != null) {
                                    // open = open.replaceAll("\\\\", "/");
                                    parameter.setValue(PathUtils.getPortablePath(open));
                                }
                            } else if (id.equals(JavaTypesManager.DIRECTORY.getId())) {
                                DirectoryDialog d = new DirectoryDialog(text.getShell());
                                String open = d.open();
                                if (open != null) {
                                    // open = open.replaceAll("\\\\", "/");
                                    parameter.setValue(PathUtils.getPortablePath(open));
                                }
                            } else if (id.equals(JavaTypesManager.DATE.getId())) {
                                DateDialog d = new DateDialog(text.getShell());
                                int res = d.open();
                                if (res == Dialog.OK) {
                                    parameter.setValue(d.getTalendDateString());
                                }
                            }
                            tableViewerCreator.refreshTableEditorControls();
                            tableViewerCreator.getTableViewer().refresh();
                        }
                    }
                }
            }

        });
        // ///////////////////////////////////////////////////////////////
        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ContextProcessSection.47")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getComment();
            }

            public void set(IContextParameter bean, String value) {
                bean.setComment(value);
                if (!oldCellEditorValue.equals(newCellEditorValue)) {
                    onContextModify(jobContextManager, oldContext, getSelectedContext());
                }
            }
        });
        column.setModifiable(true);
        column.setWidth(COMMENT_COLUMN_WIDTH);
        column.setCellEditor(new TextCellEditor(table), setDirtyValueAdapter);

        column = new TableViewerCreatorColumn(tableViewerCreator);
        column.setTitle(Messages.getString("ContextProcessSection.scriptCodeColumnTitle")); //$NON-NLS-1$
        column.setBeanPropertyAccessors(new IBeanPropertyAccessors<IContextParameter, String>() {

            public String get(IContextParameter bean) {
                return bean.getScriptCode();
            }

            public void set(IContextParameter bean, String value) {
                // do nothing since script code should not be modified.
            }
        });
        column.setModifiable(true);
        column.setWidth(SCRIPT_COLUMN_WIDTH);
        TextCellEditor cellEditor = new TextCellEditor(table);
        ((Text) cellEditor.getControl()).setEditable(false);
        column.setCellEditor(cellEditor, setDirtyValueAdapter);
    }

    public void addComponents() {
        hashCurControls = new HashMap<String, Object>();

        addChoiceComposite();
        addTabComposite();
    }

    protected void addChoiceComposite() {
        Composite dataComp = new Composite(this, SWT.NONE);
        dataComp.setBackground(this.getBackground());
        dataComp.setLayout(new RowLayout());
        addChoiceComponents(dataComp);
    }

    protected void addChoiceComponents(Composite composite) {
        CLabel label = new CLabel(composite, SWT.NONE);
        label.setBackground(this.getBackground());
        label.setText(Messages.getString("ContextProcessSection.49")); //$NON-NLS-1$
        label.setAlignment(SWT.RIGHT);

        CCombo combo = new CCombo(composite, SWT.BORDER);
        combo.setEditable(false);
        hashCurControls.put(DEFAULT_CONTEXT, combo);
        String[] stringList = null;
        if (jobContextManager != null) {
            stringList = new String[jobContextManager.getListContext().size()];
            for (int i = 0; i < jobContextManager.getListContext().size(); i++) {
                stringList[i] = jobContextManager.getListContext().get(i).getName();
            }
        } else {
            stringList = new String[0];
            setReadOnly(true);
        }
        combo.setItems(stringList);
        combo.addSelectionListener(listenerSelection);
        combo.setEnabled(!isReadOnly());
    }

    protected void addTabComposite() {
        tabFolder = new CTabFolder(this, SWT.BORDER);
        tabFolder.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        Menu menuTable = new Menu(tabFolder);
        hashCurControls.put(MENU_TABLE, menuTable);

        IContext context;

        tableViewerCreatorMap = new HashMap<IContext, TableViewerCreator>();
        removeButtons = new HashMap<IContext, Button>();

        if (jobContextManager != null) {
            for (int i = 0; i < jobContextManager.getListContext().size(); i++) {
                context = jobContextManager.getListContext().get(i);
                addContext(context);
            }
        }
    }

    public void refresh() {
        if (hashCurControls == null || jobContextManager == null) {
            return;
        }
        CCombo combo = (CCombo) hashCurControls.get(DEFAULT_CONTEXT);
        combo.setText(jobContextManager.getDefaultContext().getName());
        combo.clearSelection();

        for (int i = 0; i < jobContextManager.getListContext().size(); i++) {
            TableViewerCreator tableViewerCreator = tableViewerCreatorMap
                    .get(jobContextManager.getListContext().get(i));
            tableViewerCreator.getTableViewer().refresh();

            Table table = tableViewerCreator.getTable();
            TableItem tableItem;
            for (int j = 0; j < table.getItemCount(); j++) {
                tableItem = table.getItem(j);
                String paramName = ((IContextParameter) tableItem.getData()).getName();
                List<IContextParameter> listParams = jobContextManager.getListContext().get(i)
                        .getContextParameterList();
                boolean paramNameFound = false;
                for (int k = 0; k < listParams.size() && !paramNameFound; k++) {
                    if (paramName.equals(listParams.get(k).getName())) {
                        tableItem.setChecked(listParams.get(j).isPromptNeeded());
                        paramNameFound = true;
                    }
                }
            }
        }

        tabFolder.update();

        this.layout();
    }

    public CTabFolder getTabFolder() {
        return this.tabFolder;
    }

    public void setTabFolder(final CTabFolder tabFolder) {
        this.tabFolder = tabFolder;
    }

    public Map<IContext, TableViewerCreator> getTableViewerCreatorMap() {
        return this.tableViewerCreatorMap;
    }

    public void setTableViewerCreatorMap(final Map<IContext, TableViewerCreator> tableViewerCreatorMap) {
        this.tableViewerCreatorMap = tableViewerCreatorMap;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

}
