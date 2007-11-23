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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.dialogs.ProgressDialog;
import org.talend.commons.ui.swt.formtools.Form;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.ui.images.ECoreImage;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class SelectRepositoryContextDialog extends SelectionDialog {

    private static final String DEFAULTMESAGE = Messages.getString("SelectRepositoryContextDialog.Label");

    private static final String TITILE = Messages.getString("SelectRepositoryContextDialog.Title");

    private IContextModelManager modelManager = null;

    private JobContextManager manager;

    private List<ContextItem> contextItemList = new ArrayList<ContextItem>();

    private CheckboxTreeViewer treeViewer;

    private Button bViewContent;

    private Button bSelectAll;

    private Button bDeselectAll;

    protected SelectRepositoryContextDialog(IContextModelManager modelManager, Shell parentShell) {
        super(parentShell);
        setBlockOnOpen(true);
        setDefaultImage(ImageProvider.getImageDesc(ECoreImage.CONTEXT_ICON).createImage());
        setTitle(TITILE);
        setMessage(DEFAULTMESAGE);
        setHelpAvailable(false);
        this.modelManager = modelManager;
        this.manager = (JobContextManager) modelManager.getContextManager();
        initContextItemList();
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setFont(parent.getFont());
        createMessageArea(composite);
        Group group = Form.createGroup(composite, 1, null, 200);
        Composite inner = new Composite(group, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.horizontalSpacing = 10;
        inner.setLayout(layout);
        createTreeViewer(inner);
        createButtons(inner);

        return composite;
    }

    private void createTreeViewer(Composite parent) {
        treeViewer = new CheckboxTreeViewer(parent);
        treeViewer.setContentProvider(new ContextTreeContentProvider());
        treeViewer.setLabelProvider(new ContextTreeLabelProvider());
        treeViewer.setInput(contextItemList);
        treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
        addTreeListener();
        updateTreeCheckedState();
    }

    private void createButtons(Composite parent) {

        Composite buttons = new Composite(parent, SWT.NONE);
        buttons.setFont(parent.getFont());
        GridData data = new GridData(GridData.FILL_VERTICAL);
        buttons.setLayoutData(data);

        GridLayout layout = new GridLayout();
        layout.marginHeight = 10;
        layout.marginWidth = 0;
        layout.marginRight = 0;

        buttons.setLayout(layout);

        bViewContent = new Button(buttons, SWT.PUSH);
        bViewContent.setText(Messages.getString("SelectRepositoryContextDialog.View"));
        bViewContent.setFont(parent.getFont());
        setButtonLayoutData(bViewContent);
        bViewContent.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                // TODO
                TreeSelection selection = (TreeSelection) treeViewer.getSelection();
                if (selection.isEmpty()) {
                    return;
                }
                if (selection.getFirstElement() instanceof ContextItem) {
                    ShowSelectedContextDialog showDialog = new ShowSelectedContextDialog((ContextItem) selection
                            .getFirstElement(), getParentShell());
                    showDialog.open();
                }
            }
        });

        bSelectAll = new Button(buttons, SWT.PUSH);
        bSelectAll.setText(Messages.getString("SelectRepositoryContextDialog.SelectAll"));
        bSelectAll.setFont(parent.getFont());
        setButtonLayoutData(bSelectAll);
        bSelectAll.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                selectAll(true);
            }
        });

        bDeselectAll = new Button(buttons, SWT.PUSH);
        bDeselectAll.setText(Messages.getString("SelectRepositoryContextDialog.DeselectAll"));
        bDeselectAll.setFont(parent.getFont());
        setButtonLayoutData(bDeselectAll);
        bDeselectAll.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                selectAll(false);
            }
        });
    }

    private void selectAll(boolean all) {
        treeViewer.setAllChecked(all);
        if (contextItemList != null) {
            for (ContextItem item : contextItemList) {
                treeViewer.setGrayed(item, false);
                treeViewer.setSubtreeChecked(item, all);
            }
        }
    }

    private void addTreeListener() {
        treeViewer.addCheckStateListener(new ICheckStateListener() {

            public void checkStateChanged(CheckStateChangedEvent event) {
                treeViewer.setGrayChecked(event.getElement(), false);
                treeViewer.setSubtreeChecked(event.getElement(), event.getChecked());
                // checked parent
                updateParentCheckedState(event.getElement());
            }

        });
        treeViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                TreeSelection selection = (TreeSelection) event.getSelection();
                if (selection.getFirstElement() instanceof ContextItem) {
                    bViewContent.setEnabled(true);
                } else {
                    bViewContent.setEnabled(false);
                }
            }

        });

    }

    private void initContextItemList() {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ContextItem> itemList = null;

        try {
            itemList = factory.getContextItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (itemList != null) {
            contextItemList = new ArrayList<ContextItem>();
            contextItemList.addAll(itemList);
            for (ContextItem contextItem : itemList) {
                if (factory.getStatus(contextItem) == ERepositoryStatus.DELETED) {
                    contextItemList.remove(contextItem);
                }
            }
        }

    }

    private void updateParentCheckedState(Object obj) {
        if (obj == null) {
            return;
        }
        Object parent = getParentContextItem(obj);
        if (parent == null) {
            return;
        }

        List siblings = getSiblingContextObject(obj);
        if (siblings == null) {
            return;
        }
        int num = 0;
        for (Object sibling : siblings) {
            if (treeViewer.getChecked(sibling)) {
                num++;
            }

        }
        if (num == 0) {
            treeViewer.setGrayChecked(parent, false);
        } else if (num == siblings.size()) {
            treeViewer.setChecked(parent, true);
            treeViewer.setGrayed(parent, false);
        } else {
            treeViewer.setGrayChecked(parent, true);
        }

    }

    private void updateTreeCheckedState() {
        for (ContextItem item : contextItemList) {
            ContextType type = getDefaultContextType(item);
            if (type != null) {
                for (Object paramObj : type.getContextParameter()) {
                    ContextParameterType paramType = (ContextParameterType) paramObj;
                    IContextParameter param = manager.getDefaultContext().getContextParameter(paramType.getName());
                    if (param != null && param.getSource().equals(item.getProperty().getLabel())) {
                        treeViewer.setChecked(paramType, true);
                        updateParentCheckedState(paramType);
                    }
                }
            }
        }

    }

    private Object getParentContextItem(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ContextItem) {
            return null;
        }
        if (contextItemList != null && obj instanceof ContextParameterType) {
            for (ContextItem contextItem : contextItemList) {
                for (Object objType : contextItem.getContext()) {
                    ContextType type = (ContextType) objType;
                    if (type.getName().equals(contextItem.getDefaultContext())) {
                        List paramList = type.getContextParameter();
                        if (paramList != null && paramList.contains(obj)) {
                            return contextItem;
                        }
                    }

                }
            }
        }
        return null;
    }

    private List getSiblingContextObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if (contextItemList != null) {
            if (obj instanceof ContextItem) {
                return contextItemList;
            }
            if (obj instanceof ContextParameterType) {
                for (ContextItem contextItem : contextItemList) {
                    for (Object objType : contextItem.getContext()) {
                        ContextType type = (ContextType) objType;
                        if (type.getName().equals(contextItem.getDefaultContext())) {
                            List paramList = type.getContextParameter();
                            if (paramList != null && paramList.contains(obj)) {
                                return paramList;
                            }
                        }
                    }
                }

            }
        }
        return null;
    }

    private List<IContextParameter> getSelect() {
        List<IContextParameter> contextParamList = new ArrayList<IContextParameter>();

        ContextParameterType contextParamType;
        JobContextParameter contextParam;

        List objList = Arrays.asList(treeViewer.getCheckedElements());
        for (Object obj : objList) {
            if (obj instanceof ContextParameterType) {
                contextParamType = (ContextParameterType) obj;
                ContextItem contextItem = (ContextItem) getParentContextItem(obj);
                if (contextItem == null) {
                    continue;
                }
                contextParam = new JobContextParameter();

                contextParam.setName(contextParamType.getName());
                contextParam.setPrompt(contextParamType.getPrompt());
                boolean exists = false;
                ECodeLanguage curLanguage = LanguageManager.getCurrentLanguage();
                if (curLanguage == ECodeLanguage.JAVA) {
                    exists = true;
                    try {
                        ContextParameterJavaTypeManager.getJavaTypeFromId(contextParamType.getType());
                    } catch (IllegalArgumentException e) {
                        exists = false;
                    }
                } else {
                    String[] existingTypes;
                    existingTypes = ContextParameterJavaTypeManager.getPerlTypesLabels();
                    for (int k = 0; k < existingTypes.length; k++) {
                        if (existingTypes[k].equals(contextParamType.getType())) {
                            exists = true;
                        }
                    }
                }
                if (exists) {
                    contextParam.setType(contextParamType.getType());
                } else {
                    contextParam.setType(MetadataTalendType.getDefaultTalendType());
                }
                contextParam.setValue(contextParamType.getValue());
                contextParam.setPromptNeeded(contextParamType.isPromptNeeded());
                contextParam.setComment(contextParamType.getComment());
                contextParam.setSource(contextItem.getProperty().getLabel());
                contextParamList.add(contextParam);

            }
        }
        return contextParamList;
    }

    private void progressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(getParentShell()) {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

                List<IContextParameter> result = getSelect();
                monitor.beginTask("", result.size()); //$NON-NLS-1$
                setResult(result);

                for (IContextParameter parameter : result) {
                    IContextParameter paramExisted = manager.getDefaultContext().getContextParameter(parameter.getName());
                    if (paramExisted != null) {
                        if (!paramExisted.isBuiltIn()) {
                            // has existed.
                            if (parameter.getSource().equals(paramExisted.getSource())) {
                                // update the parameter.
                                modelManager.onContextRemoveParameter(manager, parameter.getName());
                                modelManager.onContextAddParameter(manager, parameter);
                            } else {
                                // not same source
                            }
                        }
                    } else {
                        modelManager.onContextAddParameter(manager, parameter);
                    }
                    monitor.worked(1);
                }
                monitor.done();
            }
        };

        try {
            progressDialog.executeProcess();
        } catch (InvocationTargetException e) {
            MessageBoxExceptionHandler.process(e.getTargetException(), getParentShell());
        } catch (InterruptedException e) {
            // Nothing to do
        }
    }

    @Override
    protected void okPressed() {
        progressDialog();
        super.okPressed();

    }

    private ContextType getDefaultContextType(ContextItem item) {
        if (item == null) {
            return null;
        }
        for (Object obj : item.getContext()) {
            ContextType type = (ContextType) obj;
            if (type.getName().equals(item.getDefaultContext())) {
                return type;
            }
        }
        return null;
    }

    /**
     * 
     * DOC ggu SelectRepositoryContextDialog class global comment. Detailled comment
     */
    class ContextTreeContentProvider implements ITreeContentProvider {

        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof ContextItem) {
                ContextItem item = (ContextItem) parentElement;
                ContextType type = getDefaultContextType(item);
                if (type != null) {
                    return type.getContextParameter().toArray();
                }
            }
            return null;
        }

        public Object getParent(Object element) {

            return getParentContextItem(element);
        }

        public boolean hasChildren(Object element) {
            if (element instanceof ContextItem) {
                return true;
            }
            return false;
        }

        public Object[] getElements(Object inputElement) {
            return ((List) inputElement).toArray();
        }

        public void dispose() {

        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

        }

    }

    /**
     * 
     * DOC ggu SelectRepositoryContextDialog class global comment. Detailled comment
     */
    class ContextTreeLabelProvider implements ILabelProvider {

        public Image getImage(Object element) {
            // if (element instanceof ContextItem) {
            // return ImageProvider.getImageDesc(ECoreImage.CONTEXT_ICON).createImage();
            // }
            return null;
        }

        public String getText(Object element) {
            if (element instanceof ContextItem) {
                ContextItem item = (ContextItem) element;
                return "Context: " + item.getProperty().getLabel(); //$NON-NLS-1$
            }
            if (element instanceof ContextParameterType) {
                ContextParameterType paramType = (ContextParameterType) element;
                return "Var: " + paramType.getName(); //$NON-NLS-1$
            }
            return null;
        }

        public void addListener(ILabelProviderListener listener) {

        }

        public void dispose() {

        }

        public boolean isLabelProperty(Object element, String property) {

            return false;
        }

        public void removeListener(ILabelProviderListener listener) {

        }

    }
}
