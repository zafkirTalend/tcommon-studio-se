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
package org.talend.core.ui.metadata.celleditor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.LinkRulesItem;
import org.talend.core.model.properties.RulesItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.ui.metadata.celleditor.RuleOperationChoiceDialog.EProcessTypeForRule;
import org.talend.repository.model.IRepositoryService;

/**
 * DOC hywang class global comment. Detailled comment
 */
public class RuleCellEditor extends DialogCellEditor {

    private static String drlExtension = "*.drl"; //$NON-NLS-1$

    //    private static String dslExtension = "*.dslr"; //$NON-NLS-1$

    private static String builtInRule = "Built-In:"; //$NON-NLS-1$

    private INode node;

    private AbstractDataTableEditorView tableEditorView;

    public RuleCellEditor(Composite parent, INode node) {
        super(parent, SWT.NONE);
        this.node = node;
    }

    public AbstractDataTableEditorView getTableEditorView() {
        return this.tableEditorView;
    }

    private TableViewer getTableViewer() {
        if (this.tableEditorView != null) {
            AbstractExtendedTableViewer extendedTableViewer = this.tableEditorView.getExtendedTableViewer();
            if (extendedTableViewer != null) {
                TableViewerCreator tableViewerCreator = extendedTableViewer.getTableViewerCreator();
                if (tableViewerCreator != null) {
                    return tableViewerCreator.getTableViewer();
                }
            }
        }
        return null;
    }

    public void setTableEditorView(AbstractDataTableEditorView tableEditorView) {
        this.tableEditorView = tableEditorView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {

        String ruleToEdit = (String) this.getValue();

        // for Rule(feature 6484),hywang add
        Object[] allRules = null;
        RulesItem[] repositoryRuleItems = null;
        LinkRulesItem[] linkRuleItems = null;
        List<RulesItem> listRulesItems = new ArrayList<RulesItem>();
        List<LinkRulesItem> listLinkItems = new ArrayList<LinkRulesItem>();
        // Map<String, List<IRepositoryObject>> allVersions = new HashMap<String, List<IRepositoryObject>>();
        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        try {
            allRules = service.getProxyRepositoryFactory().getAll(ERepositoryObjectType.METADATA_FILE_RULES).toArray();
            if (allRules != null) {
                int rulesItemsNo = 0;
                int linkItemsNo = 0;
                for (int i = 0; i < allRules.length; i++) {
                    IRepositoryObject obj = ((IRepositoryObject) allRules[i]);
                    if (obj.getProperty().getItem() instanceof RulesItem) {
                        RulesItem tempRuleItem = (RulesItem) obj.getProperty().getItem();
                        listRulesItems.add(tempRuleItem);
                        rulesItemsNo++;
                    }
                    if (obj.getProperty().getItem() instanceof LinkRulesItem) {
                        LinkRulesItem tempLinkItem = (LinkRulesItem) obj.getProperty().getItem();
                        listLinkItems.add(tempLinkItem);
                        linkItemsNo++;
                    }
                }
                repositoryRuleItems = new RulesItem[rulesItemsNo];
                linkRuleItems = new LinkRulesItem[linkItemsNo];
                for (int i = 0; i < rulesItemsNo; i++) {
                    repositoryRuleItems[i] = listRulesItems.get(i);
                }
                for (int i = 0; i < linkItemsNo; i++) {
                    linkRuleItems[i] = listLinkItems.get(i);
                }
                // get all versions of each rule
                // for (RulesItem item : repositoryRuleItems) {
                // List<IRepositoryObject> eachRuleVersions = service.getProxyRepositoryFactory().getAllVersion(
                // item.getProperty().getId());
                // String key = item.getProperty().getCreationDate() + item.getProperty().getId();
                // allVersions.put(key, eachRuleVersions);
                // }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        RuleOperationChoiceDialog ruleChoiceDialog = new RuleOperationChoiceDialog(cellEditorWindow.getShell(), node,
                repositoryRuleItems, linkRuleItems, EProcessTypeForRule.CREATE, ruleToEdit, node.getProcess().isReadOnly());
        if (ruleChoiceDialog.open() == Window.OK) {
            return TalendTextUtils.QUOTATION_MARK + ruleChoiceDialog.getSelectedRuleFileName() + TalendTextUtils.QUOTATION_MARK;
            // else {
            // // from file system
            // OpenDrlFileFromFileSystemDialog fd = new
            // OpenDrlFileFromFileSystemDialog(this.getTableViewer().getControl()
            // .getShell(), SWT.OPEN);
            // String[] ruleExtensions = new String[] { drlExtension };
            // fd.setFilterExtensions(ruleExtensions);
            // String fileName = fd.open();
            // if (fileName != null) {
            // return builtInRule + fd.getFileName();
            // }
            // }
        }

        return "";
    }
}
