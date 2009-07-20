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
package org.talend.core.model.metadata.designerproperties;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.CorePlugin;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class OpenXSDFileDialog extends TitleAreaDialog {

    /**
     * Dialog to open XML files when dragging and dropping a xsd metadata xml item, added by nma, 17/07/2009.
     * 
     * @param parentShell
     */
    protected OpenXSDFileDialog(Shell parentShell) {
        super(parentShell);
        this.setTitle("Select a XML File to Validate");
    }

    protected void createButtonsForButtonBar(Composite parent) {
        Button confirmButton = createButton(parent, 9999, "Confirm", true);
        confirmButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent arg0) {

            }

            public void widgetSelected(SelectionEvent arg0) {
                setReturnCode(OK);
                close();
            }
        });

        Button cancelButton = createButton(parent, 9998, "Cancel", true);
        cancelButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent arg0) {

            }

            public void widgetSelected(SelectionEvent arg0) {
                setReturnCode(CANCEL);
                close();
            }
        });
    }

    protected int getShellStyle() {
        return super.getShellStyle();
    }

    protected Point getInitialSize() {
        return new Point(430, 180);
    }

    public void create() {
        super.create();
        setTitle("Select a XML File to Validate");
        setMessage("As you have selected a XSD metadata file, \n there should be a xml file to validate.");
    }

    protected Control createDialogArea(Composite parent) {
        final Composite container = (Composite) super.createDialogArea(parent);
        GridLayout layout = new GridLayout(14, false);
        container.setLayout(layout);

        final Label label = new Label(container, SWT.NONE);
        final Text text = new Text(container, SWT.BORDER);
        text.setText(new Path(p.toPortableString()).toOSString());
        portableValue = new Path(p.toPortableString()).toOSString();
        Button findButton = new Button(container, SWT.NONE);
        findButton.setImage(ImageProvider.getImage(CorePlugin.getImageDescriptor("icons/dots_button.gif")));
        findButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                FileDialog dial = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.NONE);
                dial.setFilterExtensions(new String[] { "*.xml" });
                dial.setText("Select a XML File to Validate");
                dial.setFileName(new Path(p.toPortableString()).toOSString());
                String file = dial.open();
                if (file != null) {
                    if (!file.equals("")) {
                        portableValue = Path.fromOSString(file).toPortableString();
                        text.setText(portableValue);
                    }
                }
            }

        });

        label.setText("File Name: ");
        GridData data = new GridData(GridData.BEGINNING);
        data.horizontalSpan = 1;
        label.setLayoutData(data);

        GridData data1 = new GridData(GridData.BEGINNING);
        data1.horizontalSpan = 8;
        data1.widthHint = 305;
        data1.grabExcessVerticalSpace = true;
        text.setLayoutData(data1);

        GridData data2 = new GridData(GridData.BEGINNING);
        data2.horizontalSpan = 2;
        data2.widthHint = 30;
        data2.grabExcessVerticalSpace = true;
        findButton.setLayoutData(data2);

        return container;
    }

    /**
     * DOC Administrator Comment method "setPath".
     * 
     * @param p
     */
    public void setPath(Path p) {
        this.p = p;
    }

    public static String portableValue = "";

    private Path p;
}
