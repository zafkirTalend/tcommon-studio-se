// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.snippet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.utils.StringUtils;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.SnippetVariable;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class VariableInsertionDialog extends Dialog {

    private List disposeListeners = new ArrayList();

    protected StyledText fDescriptionPane = null;

    protected SnippetItem fItem = null;

    protected String fPreparedText = null;

    protected StyledText fPreviewPane = null;

    protected StringPropertyTableViewer fTableViewer = null;

    /**
     * Constructor for VariableInsertionDialog.
     * 
     * @param parentShell
     */
    public VariableInsertionDialog(Shell parentShell, boolean clearModality) {
        super(parentShell);
        /**
         * Required to fix defect 218700, since Dialogs default to APPLICATION_MODAL.
         */
        if (clearModality)
            setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MODELESS);
        else
            setShellStyle(SWT.RESIZE | getShellStyle());
    }

    /**
     * subclasses can override this to add more contents into the dialog
     */
    protected void addContents(Composite composite) {
        //
    }

    public void addDisposeListener(DisposeListener listener) {
        if (!disposeListeners.contains(listener))
            disposeListeners.add(listener);
    }

    /**
     * @see org.eclipse.jface.window.Window#create()
     */
    public void create() {
        super.create();
        for (int i = 0; i < disposeListeners.size(); i++) {
            getShell().addDisposeListener((DisposeListener) disposeListeners.get(i));
        }
        getShell().setActive();
    }

    /**
     * @see Dialog#createButtonsForButtonBar(Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        getButton(IDialogConstants.OK_ID).setText("Insert"); //$NON-NLS-1$
    }

    /*
     * @see Dialog#createDialogArea(Composite)
     */
    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText(getDialogTitle());

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = true;
        layout.horizontalSpacing += 2;
        layout.verticalSpacing += 2;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parent.getFont());

        GridData doubleWide = new GridData(GridData.FILL_BOTH);
        doubleWide.horizontalSpan = 2;
        Text instructions = new Text(composite, SWT.WRAP | SWT.LEFT);
        instructions.setBackground(composite.getBackground());
        doubleWide.heightHint = instructions.getFont().getFontData()[0].getHeight() * 3;
        instructions.setLayoutData(doubleWide);
        instructions
                .setText("Edit the values for the variables in the table below.  The text that will be inserted is previewed in the Source pane below"); //$NON-NLS-1$
        instructions.setEditable(false);

        Text tableLabel = new Text(composite, SWT.NONE);
        tableLabel.setBackground(composite.getBackground());
        tableLabel.setText("Variables:"); //$NON-NLS-1$
        tableLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tableLabel.setEditable(false);

        Text descriptionBoxLabel = new Text(composite, SWT.NONE);
        descriptionBoxLabel.setBackground(composite.getBackground());
        descriptionBoxLabel.setText("Description of variable:"); //$NON-NLS-1$
        descriptionBoxLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        descriptionBoxLabel.setEditable(false);

        // pity we can't just use a PropertySheetPage, but the column headers
        // aren't customizable
        fTableViewer = new StringPropertyTableViewer();
        fTableViewer.setColumnNames(new String[] { "Variable Name", "Value" }); //$NON-NLS-1$ //$NON-NLS-2$
        fTableViewer.setEditFirstColumn(false);
        fTableViewer.createContents(composite);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.heightHint = fTableViewer.getTable().getItemHeight() * 5;
        fTableViewer.getControl().setLayoutData(data);
        fTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                SnippetVariable variable = null;
                if (fTableViewer.getSelection() != null) {
                    variable = getVariable(fTableViewer.getSelection());
                }
                fDescriptionPane.setText(variable != null ? variable.getDescription() : ""); //$NON-NLS-1$
                fDescriptionPane.setHorizontalPixel(2);
                fDescriptionPane.redraw();
            }
        });
        fTableViewer.addValueChangedListener(new IValueChangedListener() {

            public void valueChanged(String key, String property, String oldValue, String newValue) {
                update();
            }
        });

        fDescriptionPane = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP);
        fDescriptionPane.setLayoutData(new GridData(GridData.FILL_BOTH));
        fDescriptionPane.setEnabled(true);
        fDescriptionPane.setEditable(false);
        fDescriptionPane.addVerifyKeyListener(createVerifyListener(fDescriptionPane));
        fDescriptionPane.setFont(JFaceResources.getDialogFont());
        setAccessible(fDescriptionPane, "Description of variable:"); //$NON-NLS-1$

        doubleWide = new GridData(GridData.FILL_BOTH);
        doubleWide.horizontalSpan = 2;
        Text previewLabel = new Text(composite, SWT.NULL);
        previewLabel.setBackground(composite.getBackground());
        previewLabel.setText("Source:"); //$NON-NLS-1$
        previewLabel.setEditable(false);

        doubleWide = new GridData(GridData.FILL_BOTH);
        doubleWide.horizontalSpan = 2;
        doubleWide.heightHint = parent.getDisplay().getClientArea().height / 6;
        doubleWide.widthHint = parent.getDisplay().getClientArea().width / 8;
        fPreviewPane = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        fPreviewPane.addVerifyKeyListener(createVerifyListener(fPreviewPane));
        fPreviewPane.addLineStyleListener(new LineStyleListener() {

            public void lineGetStyle(LineStyleEvent event) {
                // System.out.println("hilight: " + event.lineOffset);
            }
        });
        fPreviewPane.setEnabled(true);
        fPreviewPane.setEditable(false);
        fPreviewPane.setFont(JFaceResources.getTextFont());
        fPreviewPane.setLayoutData(doubleWide);
        setAccessible(fPreviewPane, "Source:"); //$NON-NLS-1$

        if (fItem != null) {
            fTableViewer.clear();
            populateTableViewer();
            fTableViewer.refresh();
        }

        addContents(composite);

        update();
        fTableViewer.getControl().setFocus();
        return composite;
    }

    private VerifyKeyListener createVerifyListener(Control control) {
        final Control widget = control;
        return new VerifyKeyListener() {

            public void verifyKey(VerifyEvent event) {
                if (event.character == '\t') {
                    if ((event.stateMask & SWT.SHIFT) != 0)
                        widget.traverse(SWT.TRAVERSE_TAB_PREVIOUS);
                    else
                        widget.traverse(SWT.TRAVERSE_TAB_NEXT);
                }
            }
        };
    }

    /**
     * Returns the dialog title to use
     * 
     * @return
     */
    protected String getDialogTitle() {
        String itemLabel = getItem().getProperty().getLabel();
        return itemLabel;
    }

    /**
     * Gets the item.
     * 
     * @return Returns a ISnippetItem
     */
    public SnippetItem getItem() {
        return fItem;
    }

    /**
     * Gets the preparedText.
     * 
     * @return Returns a String
     */
    public String getPreparedText() {
        if (fPreparedText == null)
            prepareText();
        return fPreparedText;
    }

    protected SnippetVariable getVariable(String id) {
        if (fItem == null)
            return null;
        List<SnippetVariable> variables = (List<SnippetVariable>) fItem.getVariables();

        for (int i = 0; i < variables.size(); i++) {
            SnippetVariable var = variables.get(i);
            if (var.getId().equals(id))
                return var;
        }
        return null;
    }

    /*
     * @see Dialog#okPressed()
     */
    protected void okPressed() {
        super.okPressed();
        prepareText();
    }

    protected void populateTableViewer() {
        List<SnippetVariable> variables = (List<SnippetVariable>) fItem.getVariables();

        for (int i = 0; i < variables.size(); i++) {
            SnippetVariable var = variables.get(i);
            fTableViewer.getColumnData()[0].put((var).getId(), var.getName());
            fTableViewer.getColumnData()[1].put((var).getId(), var.getValue());
        }
    }

    protected void prepareText() {
        // this could be horribly inefficient
        String text = fItem.getContent();
        List<SnippetVariable> variables = (List<SnippetVariable>) fItem.getVariables();

        for (int i = 0; i < variables.size(); i++) {
            SnippetVariable var = variables.get(i);
            String value = (String) fTableViewer.getColumnData()[1].get(var.getId());
            text = StringUtils.replace(text, "${" + var.getName() + "}", value); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // remove all cursor markers
        text = StringUtils.replace(text, "${cursor}", ""); //$NON-NLS-1$ //$NON-NLS-2$

        // Update EOLs (bug 80231)
        String systemEOL = System.getProperty("line.separator"); //$NON-NLS-1$
        text = StringUtils.replace(text, "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        text = StringUtils.replace(text, "\r", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        if (!"\n".equals(systemEOL) && systemEOL != null) { //$NON-NLS-1$
            text = StringUtils.replace(text, "\n", systemEOL); //$NON-NLS-1$
        }

        setPreparedText(text);
    }

    public String[] prepareVariablesText() {
        Set<String> keys = fTableViewer.getColumnData()[0].keySet();

        String[] result = new String[keys.size()];
        int i = 0;
        for (String key : keys) {
            String name = (String) fTableViewer.getColumnData()[0].get(key);
            String value = (String) fTableViewer.getColumnData()[1].get(key);
            result[i] = name + "=" + value; //$NON-NLS-1$
            i++;
        }
        return result;

    }

    public void removeDisposeListener(DisposeListener listener) {
        disposeListeners.remove(listener);
    }

    /**
     * Specifically set the reporting name of a control for accessibility
     */
    private void setAccessible(Control control, String name) {
        if (control == null)
            return;
        final String n = name;
        control.getAccessible().addAccessibleListener(new AccessibleAdapter() {

            public void getName(AccessibleEvent e) {
                if (e.childID == ACC.CHILDID_SELF)
                    e.result = n;
            }
        });
    }

    /**
     * Sets the item.
     * 
     * @param item The item to set
     */
    public void setItem(SnippetItem item) {
        fItem = item;
        if (fTableViewer != null) {
            fTableViewer.clear();
            populateTableViewer();
        }
    }

    /**
     * Sets the preparedText.
     * 
     * @param preparedText The preparedText to set
     */
    protected void setPreparedText(String preparedText) {
        fPreparedText = preparedText;
    }

    protected void update() {
        prepareText();
        if (fPreviewPane != null && !fPreviewPane.isDisposed()) {
            fPreviewPane.setText(getPreparedText());
            fPreviewPane.setHorizontalPixel(2);
            fPreviewPane.redraw();
        }
    }
}
