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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.internal.ui.workingsets.WorkingSetMessages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.i18n.Messages;
import org.talend.core.model.context.JobContext;
import org.talend.core.model.context.JobContextParameter;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.ui.images.ECoreImage;
import org.talend.repository.model.RepositoryConstants;

/**
 * A dialog that config the context value sets.
 * 
 */
public class ContextSetConfigurationDialog extends SelectionDialog {

    IContextManager manager = null;

    /**
     * DOC bqian ContextSetConfigurationDialog class global comment. Detailled comment <br/>
     * 
     */
    private class ContextLabelProvider extends LabelProvider {

        public Image getImage(Object object) {
            return ImageProvider.getImage(ECoreImage.CONTEXT_ICON);
        }

        public String getText(Object object) {
            IContext context = (IContext) object;
            return context.getName();
        }
    }

    private List<IContext> fAllContexts;

    private TableViewer fTableViewer;

    private Button fNewButton;

    private Button fEditButton;

    private Button fRemoveButton;

    private Button fUpButton;

    private Button fDownButton;

    private Button fSelectAll;

    private Button fDeselectAll;

    private IContext[] fResult;

    private Label msgLabel;

    private int nextButtonId = IDialogConstants.CLIENT_ID + 1;

    @SuppressWarnings("restriction")
    public ContextSetConfigurationDialog(Shell parentShell, IContextManager manager) {
        super(parentShell);
        setTitle("Configure Contexts");
        setMessage("Configure Contexts for Job                                      ");
        this.manager = manager;
        fAllContexts = new ArrayList<IContext>(manager.getListContext());
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    /**
     * {@inheritDoc}
     */
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);
        updateButtonAvailability();
        return control;
    }

    /**
     * {@inheritDoc}
     */
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setFont(parent.getFont());
        msgLabel = createMessageArea(composite);
        // createRemoveArea(composite);
        Composite inner = new Composite(composite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        inner.setLayout(layout);
        createTableViewer(inner);
        createOrderButtons(inner);
        createModifyButtons(composite);
        fTableViewer.setInput(fAllContexts);

        return composite;
    }

    private void createTableViewer(Composite parent) {
        fTableViewer = new TableViewer(parent);
        fTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                updateButtonAvailability();
            }
        });

        GridData data = new GridData(GridData.FILL_BOTH);
        data.heightHint = convertHeightInCharsToPixels(20);
        data.widthHint = convertWidthInCharsToPixels(50);
        fTableViewer.getTable().setLayoutData(data);
        fTableViewer.getTable().setFont(parent.getFont());

        fTableViewer.setLabelProvider(new ContextLabelProvider());
        fTableViewer.setContentProvider(new ArrayContentProvider());
    }

    private void createModifyButtons(Composite composite) {
        Composite buttonComposite = new Composite(composite, SWT.RIGHT);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        buttonComposite.setLayout(layout);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
        data.grabExcessHorizontalSpace = true;
        composite.setData(data);

        fNewButton = createButton(buttonComposite, nextButtonId++, WorkingSetMessages.WorkingSetConfigurationDialog_new_label,
                false);
        fNewButton.setFont(composite.getFont());
        fNewButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                createContext();
            }
        });

        fEditButton = createButton(buttonComposite, nextButtonId++, WorkingSetMessages.WorkingSetConfigurationDialog_edit_label,
                false);
        fEditButton.setFont(composite.getFont());
        fEditButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                editSelectedContext();
            }
        });

        fRemoveButton = createButton(buttonComposite, nextButtonId++,
                WorkingSetMessages.WorkingSetConfigurationDialog_remove_label, false);
        fRemoveButton.setFont(composite.getFont());
        fRemoveButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                removeSelectedContexts();
            }
        });
    }

    private void createOrderButtons(Composite parent) {
        Composite buttons = new Composite(parent, SWT.NONE);
        buttons.setFont(parent.getFont());
        buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        buttons.setLayout(layout);

        fUpButton = new Button(buttons, SWT.PUSH);
        fUpButton.setText(WorkingSetMessages.WorkingSetConfigurationDialog_up_label);
        fUpButton.setFont(parent.getFont());
        setButtonLayoutData(fUpButton);
        fUpButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                moveUp(((IStructuredSelection) fTableViewer.getSelection()).toList());
            }
        });

        fDownButton = new Button(buttons, SWT.PUSH);
        fDownButton.setText(WorkingSetMessages.WorkingSetConfigurationDialog_down_label);
        fDownButton.setFont(parent.getFont());
        setButtonLayoutData(fDownButton);
        fDownButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                moveDown(((IStructuredSelection) fTableViewer.getSelection()).toList());
            }
        });

        fSelectAll = new Button(buttons, SWT.PUSH);
        fSelectAll.setText(WorkingSetMessages.WorkingSetConfigurationDialog_selectAll_label);
        fSelectAll.setFont(parent.getFont());
        setButtonLayoutData(fSelectAll);
        fSelectAll.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                selectAll();
            }
        });

        fDeselectAll = new Button(buttons, SWT.PUSH);
        fDeselectAll.setText(WorkingSetMessages.WorkingSetConfigurationDialog_deselectAll_label);
        fDeselectAll.setFont(parent.getFont());
        setButtonLayoutData(fDeselectAll);
        fDeselectAll.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                deselectAll();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    protected void okPressed() {
        List<IContext> newResult = getResultContexts();
        fResult = (IContext[]) newResult.toArray(new IContext[newResult.size()]);
        setResult(newResult);
        super.okPressed();
    }

    public List<IContext> getResultContexts() {
        return fAllContexts;
    }

    /**
     * {@inheritDoc}
     */
    protected void cancelPressed() {
        super.cancelPressed();
    }

    private void createContext() {
        InputDialog inputDial = new InputDialog(getShell(), Messages.getString("ContextProcessSection.6"), //$NON-NLS-1$
                Messages.getString("ContextProcessSection.7"), "", null); //$NON-NLS-1$ //$NON-NLS-2$

        inputDial.open();
        String returnValue = inputDial.getValue();
        if (returnValue == null) {
            return;
        }
        if (!validateContextName(returnValue)) {
            return;
        }
        createContext(returnValue);
        refreshViewer();
    }

    private boolean validateContextName(String name) {
        if (name.equals("") || !name.matches(RepositoryConstants.CODE_ITEM_PATTERN)) {
            MessageDialog.openWarning(new Shell(), Messages.getString(Messages.getString("ContextProcessSection.50")), Messages //$NON-NLS-1$
                    .getString(Messages.getString("ContextProcessSection.51"))); //$NON-NLS-1$
            return false;
        }

        return !isContextExisting(name);
    }

    private boolean isContextExisting(String name) {
        for (IContext context : fAllContexts) {
            if (context.getName().equalsIgnoreCase(name)) {
                MessageBox mBox = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                mBox.setText(Messages.getString("ContextProcessSection.29")); //$NON-NLS-1$
                mBox.setMessage(Messages.getString("ContextProcessSection.30")); //$NON-NLS-1$
                mBox.open();
                return true;
            }
        }
        return false;
    }

    private void createContext(final String name) {
        IContext context = manager.getDefaultContext();

        JobContext newContext = new JobContext(name);

        List<IContextParameter> newParamList = new ArrayList<IContextParameter>();
        newContext.setContextParameterList(newParamList);
        JobContextParameter param;
        for (int i = 0; i < context.getContextParameterList().size(); i++) {
            param = new JobContextParameter();
            param.setContext(newContext);
            param.setName(context.getContextParameterList().get(i).getName());
            param.setPrompt(context.getContextParameterList().get(i).getPrompt());
            param.setType(context.getContextParameterList().get(i).getType());
            param.setValue(context.getContextParameterList().get(i).getValue());
            param.setComment(context.getContextParameterList().get(i).getComment());
            param.setPromptNeeded(context.getContextParameterList().get(i).isPromptNeeded());
            newParamList.add(param);
        }
        fAllContexts.add(newContext);
    }

    private void editSelectedContext() {
        IContext selectedContext = (IContext) ((IStructuredSelection) fTableViewer.getSelection()).getFirstElement();
        String contextName = selectedContext.getName();
        InputDialog inputDial = new InputDialog(getShell(), Messages.getString("ContextProcessSection.12"), //$NON-NLS-1$
                Messages.getString("ContextProcessSection.13", contextName), "", null); //$NON-NLS-1$ //$NON-NLS-2$
        inputDial.open();
        String returnValue = inputDial.getValue();
        if (returnValue == null) {
            return;
        }
        if (!validateContextName(returnValue)) {
            return;
        }
        renameContext(selectedContext, returnValue);
        refreshViewer();
    }

    private void renameContext(IContext context, String newName) {
        context.setName(newName);
    }

    /**
     * Overrides method in Dialog
     * 
     * @see org.eclipse.jface.dialogs.Dialog#open()
     */
    public int open() {
        return super.open();
    }

    /**
     * Removes the selected working sets from the workbench.
     */
    private void removeSelectedContexts() {
        ISelection selection = fTableViewer.getSelection();
        if (selection instanceof IStructuredSelection) {
            Iterator iter = ((IStructuredSelection) selection).iterator();
            while (iter.hasNext()) {

                fAllContexts.remove((IContext) iter.next());
            }
        }
        refreshViewer();
    }

    /**
     * bqian Comment method "refreshViewer".
     */
    private void refreshViewer() {
        fTableViewer.refresh();
    }

    /**
     * Updates the modify buttons' enabled state based on the current seleciton.
     */
    private void updateButtonAvailability() {
        IStructuredSelection selection = (IStructuredSelection) fTableViewer.getSelection();
        boolean hasSelection = !selection.isEmpty();
        boolean hasSingleSelection = selection.size() == 1;
        boolean selectDefaultContext = false;
        for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
            IContext context = (IContext) iterator.next();
            if (context == manager.getDefaultContext()) {
                selectDefaultContext = true;
                break;
            }

        }
        if (selectDefaultContext) {
            String contextNname = manager.getDefaultContext().getName();
            // label.setText(Messages.getString("ContextProcessSection.RemoveInformation", contextNname)); //$NON-NLS-1$
            msgLabel.setText(Messages.getString("ContextProcessSection.RemoveInformation", contextNname)); //$NON-NLS-1$
        } else {
            msgLabel.setText("Configure Contexts for Job                                      ");
        }
        fRemoveButton.setEnabled(hasSelection && !selectDefaultContext);
        // fEditButton.setEnabled(hasSingleSelection && !selectDefaultContext);
        fEditButton.setEnabled(hasSingleSelection);
        if (fUpButton != null) {
            fUpButton.setEnabled(canMoveUp());
        }
        if (fDownButton != null) {
            fDownButton.setEnabled(canMoveDown());
        }

    }

    private void moveUp(List toMoveUp) {
        if (toMoveUp.size() > 0) {
            setElements(moveUp(fAllContexts, toMoveUp));
            fTableViewer.reveal(toMoveUp.get(0));
        }
    }

    private void moveDown(List toMoveDown) {
        if (toMoveDown.size() > 0) {
            setElements(reverse(moveUp(reverse(fAllContexts), toMoveDown)));
            fTableViewer.reveal(toMoveDown.get(toMoveDown.size() - 1));
        }
    }

    private void setElements(List elements) {
        fAllContexts = elements;
        fTableViewer.setInput(fAllContexts);
        updateButtonAvailability();
    }

    private List moveUp(List elements, List move) {
        int nElements = elements.size();
        List res = new ArrayList(nElements);
        Object floating = null;
        for (int i = 0; i < nElements; i++) {
            Object curr = elements.get(i);
            if (move.contains(curr)) {
                res.add(curr);
            } else {
                if (floating != null) {
                    res.add(floating);
                }
                floating = curr;
            }
        }
        if (floating != null) {
            res.add(floating);
        }
        return res;
    }

    private List reverse(List p) {
        List reverse = new ArrayList(p.size());
        for (int i = p.size() - 1; i >= 0; i--) {
            reverse.add(p.get(i));
        }
        return reverse;
    }

    private boolean canMoveUp() {
        int[] indc = fTableViewer.getTable().getSelectionIndices();
        for (int i = 0; i < indc.length; i++) {
            if (indc[i] != i) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveDown() {
        int[] indc = fTableViewer.getTable().getSelectionIndices();
        int k = fAllContexts.size() - 1;
        for (int i = indc.length - 1; i >= 0; i--, k--) {
            if (indc[i] != k) {
                return true;
            }
        }
        return false;
    }

    // ---- select / deselect --------------------------------------------------

    private void selectAll() {
        fTableViewer.setSelection(new StructuredSelection(fAllContexts));

    }

    private void deselectAll() {
        fTableViewer.setSelection(StructuredSelection.EMPTY);
    }

}
