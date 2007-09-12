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

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.talend.core.i18n.Messages;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IProcess;

/**
 * This class must be extended for implementing the specific context composite. <br/>
 * 
 */
public abstract class ContextComposite extends Composite implements IContextModelManager {

    private boolean readOnly;

    private ConextTemplateComposite template;

    private ConextTreeValuesComposite treeValues;

    private ConextTableValuesComposite tableValues;

    private CTabFolder tab;

    private CCombo contextCombo;

    /**
     * bqian ContextComposite constructor comment.
     * 
     * @param parent
     * @param style
     */
    public ContextComposite(Composite parent) {
        super(parent, SWT.NONE);

        this.setBackground(parent.getBackground());
        this.setLayout(new GridLayout());
        initializeUI();
    }

    private void setTabEnable(boolean enable) {
        for (Control tabItem : tab.getTabList()) {
            tabItem.setEnabled(enable);
        }
    }

    public void refresh() {
        if (getContextManager() == null) {
            tab.setEnabled(false);
            setTabEnable(false);
            clearChildrenUI();
        } else {
            tab.setEnabled(true);
            setTabEnable(!isReadOnly());
            refreshChildrenUI();
        }
        refreshChoiceComposite();
        if (getContextManager() != null) {
            getContextManager().fireContextsChangedEvent();
        }
    }

    /**
     * Set the current context Manager and refresh the UI.
     * 
     * @param jobContextManager
     */
    public void refreshChildrenUI() {
        template.setCommandStack(getCommandStack());
        template.refresh();
        treeValues.refresh();
        tableValues.refresh();

    }

    /**
     * Set the current context Manager and refresh the UI.
     * 
     * @param jobContextManager
     */
    public void clearChildrenUI() {
        template.setCommandStack(null);
        template.clear();
        treeValues.clear();
        tableValues.clear();

    }

    public abstract IContextManager getContextManager();

    /**
     * bqian Comment method "initializeUI".
     */
    protected void initializeUI() {
        addChoiceComposite();

        tab = new CTabFolder(this, SWT.FLAT | SWT.BORDER);
        tab.setLayoutData(new GridData(GridData.FILL_BOTH));
        CTabItem templateItem = new CTabItem(tab, SWT.NONE);
        templateItem.setText("Template");
        creatTemplate(tab, templateItem);

        CTabItem treeValuesItem = new CTabItem(tab, SWT.NONE);
        treeValuesItem.setText("Tree Values");
        creatTreeValues(tab, treeValuesItem);

        CTabItem tableValuesItem = new CTabItem(tab, SWT.NONE);
        tableValuesItem.setText("Table Values");
        creatTableValues(tab, tableValuesItem);
        tab.setSelection(templateItem);
    }

    protected void addChoiceComposite() {
        Composite dataComp = new Composite(this, SWT.BORDER);
        dataComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        dataComp.setBackground(this.getBackground());
        dataComp.setLayout(new RowLayout());
        addChoiceComponents(dataComp);
    }

    protected void layoutButtonBar() {
        this.layout();
    }

    protected void addChoiceComponents(Composite composite) {
        CLabel label = new CLabel(composite, SWT.NONE);
        label.setBackground(this.getBackground());
        label.setText(Messages.getString("ContextProcessSection.49")); //$NON-NLS-1$
        label.setAlignment(SWT.RIGHT);

        contextCombo = new CCombo(composite, SWT.BORDER);
        contextCombo.setEditable(false);
        contextCombo.addSelectionListener(listenerSelection);
    }

    protected void refreshChoiceComposite() {
        String[] stringList = null;
        if (getContextManager() == null) {
            stringList = new String[0];
            contextCombo.setItems(stringList);
            return;
        }
        stringList = new String[getContextManager().getListContext().size()];
        for (int i = 0; i < getContextManager().getListContext().size(); i++) {
            stringList[i] = getContextManager().getListContext().get(i).getName();
        }
        contextCombo.setItems(stringList);
        contextCombo.setText(getContextManager().getDefaultContext().getName());
    }

    private SelectionListener listenerSelection = new SelectionAdapter() {

        public void widgetSelected(final SelectionEvent e) {
            for (int i = 0; i < getContextManager().getListContext().size(); i++) {
                if (getContextManager().getListContext().get(i).getName().equals(contextCombo.getText())) {
                    onContextChangeDefault(getContextManager(), getContextManager().getListContext().get(i));
                }
            }
        }
    };

    private void creatTemplate(CTabFolder tab, CTabItem templateItem) {
        template = new ConextTemplateComposite(tab, this);
        templateItem.setControl(template);
    }

    private void creatTreeValues(CTabFolder tab, CTabItem treeValuesItem) {
        treeValues = new ConextTreeValuesComposite(tab, this);
        treeValuesItem.setControl(treeValues);
    }

    private void creatTableValues(CTabFolder tab, CTabItem tableValuesItem) {
        tableValues = new ConextTableValuesComposite(tab, this);
        tableValuesItem.setControl(tableValues);
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.context.IContextModelManager#getProcess()
     */
    public IProcess getProcess() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.context.IContextModelManager#getCommandStack()
     */
    public CommandStack getCommandStack() {
        return null;
    }

    // public abstract void onContextChangeDefault(IContextManager contextManager, IContext newDefault);
    //
    // public abstract void onContextRenameParameter(IContextManager contextManager, String oldName, String newName);
    //
    // public abstract void onContextModify(IContextManager contextManager, IContextParameter parameter);
}
