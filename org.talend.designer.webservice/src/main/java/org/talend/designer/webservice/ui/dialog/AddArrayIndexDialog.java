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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.talend.designer.webservice.i18n.Messages;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class AddArrayIndexDialog extends Dialog {

    private Label titleLable;

    private Text text;

    private ParameterInfo para;

    private int arraySize = 0;

    private Shell parentShell;

    private String indexText;

    /**
     * DOC Administrator ErrorMessageDialog constructor comment.
     * 
     * @param parentShell
     */
    protected AddArrayIndexDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.parentShell = parentShell;
    }

    public AddArrayIndexDialog(Shell parentShell, ParameterInfo para) {
        this(parentShell);
        this.parentShell = parentShell;
        this.para = para;
        this.arraySize = para.getArraySize();
    }

    protected Control createDialogArea(Composite parent) {
        Composite createDialogArea = (Composite) super.createDialogArea(parent);
        GridLayout layout = new GridLayout();
        layout.horizontalSpacing = 1;
        layout.numColumns = 5;
        createDialogArea.setLayout(layout);

        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 1;
        titleLable = new Label(createDialogArea, SWT.NONE);
        titleLable.setText(Messages.getString("AddArrayIndexDialog.index")); //$NON-NLS-1$
        titleLable.setLayoutData(data);

        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 4;
        text = new Text(createDialogArea, SWT.BORDER);
        text.setLayoutData(data);
        text.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                indexText = text.getText();
                // String com = text.getText();
                // int index = Integer.valueOf(com);
                // if(index<0||index>arraySize){
                //                    
                // }
            }

        });

        return createDialogArea;
    }

    protected void okPressed() {
        String com = text.getText();
        int index = Integer.valueOf(com);

        if (arraySize != -1 && (index < 0 || index > arraySize)) {
            MessageBox box = new MessageBox(parentShell, SWT.ICON_ERROR | SWT.OK | SWT.CANCEL);
            box.setText(Messages.getString("AddArrayIndexDialog.Error")); //$NON-NLS-1$
            box.setMessage(Messages.getString("AddArrayIndexDialog.check_size")); //$NON-NLS-1$
            box.open();
            text.setText(""); //$NON-NLS-1$
            return;
        }
        super.okPressed();
    }

    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setBounds(500, 300, 250, 150);
    }

    public int returnIndex() {
        int index = -1;
        if (indexText != null && !"".equals(indexText)) { //$NON-NLS-1$
            index = Integer.valueOf(indexText);
            if (arraySize != -1 && (index < 0 || index > arraySize)) {
                index = -1;
            }
        }
        return index;
    }
}
