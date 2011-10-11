// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards.metadata.connection.files.xml.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.actions.SelectionProviderAction;
import org.talend.commons.xml.XmlUtil;
import org.talend.core.ui.metadata.dialog.RootNodeSelectDialog;
import org.talend.repository.ui.swt.utils.AbstractXmlStepForm;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.FOXTreeNode;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.util.TreeUtil;

/**
 * wzhang class global comment. Detailled comment
 */
public class ImportTreeFromXMLAction extends SelectionProviderAction {

    private TreeViewer xmlViewer;

    private AbstractXmlStepForm form;

    private boolean isXsd;

    public ImportTreeFromXMLAction(TreeViewer xmlViewer, String text) {
        super(xmlViewer, text);
        this.xmlViewer = xmlViewer;
    }

    public ImportTreeFromXMLAction(TreeViewer xmlViewer, AbstractXmlStepForm form, String text) {
        super(xmlViewer, text);
        this.xmlViewer = xmlViewer;
        this.form = form;
    }

    @Override
    public void run() {
        List<FOXTreeNode> newInput = new ArrayList<FOXTreeNode>();
        List<FOXTreeNode> nodes = treeNodeAdapt();
        if (nodes.size() == 0) {
            return;
        }

        boolean changed = true;
        if (nodes.size() > 1 && isXsd) {
            RootNodeSelectDialog dialog = new RootNodeSelectDialog(xmlViewer.getControl().getShell(), nodes);
            if (dialog.open() == IDialogConstants.OK_ID) {
                FOXTreeNode selectedNode = dialog.getSelectedNode();
                newInput.add(selectedNode);
                changed = true;
            } else {
                changed = false;
            }
        } else {
            newInput.add(nodes.get(0));
        }

        if (changed) {
            List<FOXTreeNode> treeData = form.getTreeData();
            treeData.clear();
            treeData.addAll(newInput);
            xmlViewer.setInput(treeData);
            xmlViewer.refresh();
            xmlViewer.expandAll();
            form.updateStatus();
            form.redrawLinkers();
            form.updateConnection();
        }
    }

    private List<FOXTreeNode> treeNodeAdapt() {
        List<FOXTreeNode> list = new ArrayList<FOXTreeNode>();
        FileDialog f = new FileDialog(xmlViewer.getControl().getShell());
        String file = f.open();
        if (file == null) {
            return list;
        }
        isXsd = XmlUtil.isXSDFile(file);
        list = TreeUtil.getAllFoxTreeNodes(file);
        return list;
    }

    @Override
    public void selectionChanged(IStructuredSelection selection) {
        this.setEnabled(true);
        FOXTreeNode node = (FOXTreeNode) this.getStructuredSelection().getFirstElement();
        if (node != null) {
            form.setSelectedText(node.getLabel());
        }
    }

}
