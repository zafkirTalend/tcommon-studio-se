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

    private boolean isRepositoryContext;

    /**
     * bqian ContextComposite constructor comment.
     * 
     * @param parent
     * @param style
     */
    public ContextComposite(Composite parent) {
        this(parent, true);
    }

    public ContextComposite(Composite parent, boolean isRepositoryContext) {
        super(parent, SWT.NONE);
        this.isRepositoryContext = isRepositoryContext;
        this.setBackground(parent.getBackground());
        this.setLayout(new GridLayout());
        initializeUI();
    }

    private void setTabEnable(boolean enable) {

        // no need to set the ConextTreeValuesComposite and ConextTableValuesComposite. They can take care of
        // themselvies.
        template.setEnabled(enable);
        tableValues.setEnabled(enable);
        treeValues.setEnabled(enable);
        contextCombo.setEnabled(enable);

    }

    public void refresh() {
        refreshChoiceComposite();
        if (getContextManager() == null) {
            this.setEnabled(false);
            clearChildrenUI();
        } else {
            this.setEnabled(true);
            setTabEnable(!isReadOnly());
            refreshChildrenUI();
        }

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
        toolgeRefreshContextRelitiveComposite(template);
        toolgeRefreshContextRelitiveComposite(treeValues);
        toolgeRefreshContextRelitiveComposite(tableValues);
    }

    /**
     * 
     * DOC YeXiaowei Comment method "refreshContextEditComposite".
     * 
     * @param composite
     */
    private void toolgeRefreshContextRelitiveComposite(AbstractContextTabEditComposite composite) {
        if (composite == null) {
            return;
        }
        if (composite.isNeedRefresh()) {
            composite.refresh();
        }

        // set need refresh back to true
        composite.setNeedRefresh(true);
    }

    /**
     * Set the current context Manager and refresh the UI.
     * 
     * @param jobContextManager
     */
    public void clearChildrenUI() {
        template.clear();
        treeValues.clear();
        tableValues.clear();
    }

    public abstract IContextManager getContextManager();

    /**
     * bqian Comment method "initializeUI".
     */
    protected void initializeUI() {
        if (isRepositoryContext) {
            addChoiceComposite();
        }

        tab = new CTabFolder(this, SWT.FLAT | SWT.BORDER);
        tab.setLayoutData(new GridData(GridData.FILL_BOTH));
        CTabItem templateItem = new CTabItem(tab, SWT.NONE);
        templateItem.setText("Variables");
        creatTemplate(tab, templateItem);

        CTabItem treeValuesItem = new CTabItem(tab, SWT.NONE);
        treeValuesItem.setText("Values as tree");
        creatTreeValues(tab, treeValuesItem);

        CTabItem tableValuesItem = new CTabItem(tab, SWT.NONE);
        tableValuesItem.setText("Values as table");
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
        // it is hidden by feature 0004334: set default context in context management popup
        composite.setVisible(false);
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

        @Override
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

    /**
     * Getter for isRepositoryContext.
     * 
     * @return the isRepositoryContext
     */
    public boolean isRepositoryContext() {
        return this.isRepositoryContext;
    }

}
