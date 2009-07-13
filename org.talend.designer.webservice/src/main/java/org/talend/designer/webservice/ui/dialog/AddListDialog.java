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
package org.talend.designer.webservice.ui.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.talend.designer.webservice.i18n.Messages;
import org.talend.designer.webservice.ui.ParameterInfoUtil;
import org.talend.designer.webservice.ui.tree.WebServiceTreeContentProvider;
import org.talend.designer.webservice.ui.tree.WebServiceTreeLabelProvider;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class AddListDialog extends Dialog {

    private TreeViewer treeViewer = null;

    private Tree tree = null;

    private ParameterInfo para;

    private boolean maximized;

    private Rectangle size;

    private String title;

    private ParameterInfo selectedParaInfo;

    private Shell parentShell;

    /**
     * DOC Administrator AddListDialog constructor comment.
     * 
     * @param parentShell
     */
    protected AddListDialog(Shell parentShell) {
        super(parentShell);
        this.parentShell = parentShell;
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    /**
     * DOC Administrator AddListDialog constructor comment.
     * 
     * @param parentShell
     */
    public AddListDialog(Shell parentShell, ParameterInfo para) {
        super(parentShell);
        this.para = para;
        this.parentShell = parentShell;
        // Composite baseCom = new Composite(parentShell, SWT.NONE);
        // createTreeDialogArea(baseCom);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        // Composite baseCom = (Composite) super.createDialogArea(parent);
        Composite createDialogArea = (Composite) super.createDialogArea(parent);
        createDialogArea.setLayout(new FillLayout());
        SashForm baseCom = new SashForm(createDialogArea, SWT.VERTICAL | SWT.BORDER);
        treeViewer = new TreeViewer(baseCom, SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
        tree = treeViewer.getTree();
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);
        tree.setLayoutData(new GridData(GridData.FILL_BOTH));
        // DrillDownAdapter adapter = new DrillDownAdapter(treeView);
        treeViewer.setContentProvider(new WebServiceTreeContentProvider());

        treeViewer.setLabelProvider(new WebServiceTreeLabelProvider());

        treeViewer.setInput(para);

        tree.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                ISelection select = (ISelection) treeViewer.getSelection();
                Object obj = ((IStructuredSelection) select).getFirstElement();
                if (obj instanceof ParameterInfo) {
                    // if (((ParameterInfo) obj).getParameterInfos().size() > 0) {
                    // // ErrorDialog dialog = new ErrorDialog(parentShell, "", "", null, 0);
                    // // dialog.open();
                    // selectedParaInfo = null;
                    // } else {
                    selectedParaInfo = (ParameterInfo) obj;
                    // }
                }
            }

        });
        return baseCom;
    }

    // @Override
    // protected Point getInitialSize() {
    // return super.getInitialSize();
    // }

    public ParameterInfo getSelectedParaInfo() {
        return selectedParaInfo;
    }

    @Override
    protected void okPressed() {
        boolean falg = false;
        ParameterInfo selPara = getSelectedParaInfo();
        ParameterInfoUtil paraUtil = new ParameterInfoUtil();
        int currentindex = -1;
        int arraySize = selPara.getArraySize();
        if (arraySize == 0 && selPara.getParameterInfos().size() == 0) {
            List<ParameterInfo> paraList = paraUtil.getAllParameterInfo(selPara);
            goout: for (ParameterInfo para : paraList) {
                if (para.getArraySize() != 0) {
                    falg = true;
                    break goout;
                }
            }
        }
        if (falg) {
            InputDialog dlg = new InputDialog(parentShell, "", Messages.getString("AddListDialog.INPUTINDEX"), "", null); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            if (dlg.open() == dlg.OK) {
                String indexValue = dlg.getValue();
                currentindex = Integer.valueOf(indexValue);
            }
            if (currentindex < 0 || (arraySize != -1 && currentindex > arraySize)) {
                currentindex = -1;
                MessageBox box = new MessageBox(parentShell, SWT.ICON_ERROR | SWT.OK | SWT.CANCEL);
                box.setText(Messages.getString("AddListDialog.Error")); //$NON-NLS-1$
                box.setMessage(Messages.getString("AddListDialog.CHECKSIZE")); //$NON-NLS-1$
                box.open();
                return;
            }
        }

        paraUtil.setCurrentindex(currentindex);
        super.okPressed();
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (title != null) {
            newShell.setText(title);
        }
        // if (maximized) {
        // newShell.setMaximized(true);
        // } else {
        // newShell.setBounds(size);
        newShell.setBounds(500, 300, 400, 350);
        // }
    }

    public void setTitle(String title) {
        this.title = title;

    }

    /**
     * Sets the size.
     * 
     * @param size the size to set
     */
    public void setSize(Rectangle size) {
        this.size = size;
    }

    /**
     * Sets the maximizedSize.
     * 
     * @param maximizedSize the maxmimizedSize to set
     */
    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }

}
