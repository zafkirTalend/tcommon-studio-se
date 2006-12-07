// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.ui.swt.formtools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * Create a Label and a Text.
 * 
 * $Id$
 * 
 */
public class LabelledFileField {

    private Text text;

    private Label label;

    private Button button;

    private Composite compositeButton;

    private static final int DEFAULT_FIELD_STYLE = SWT.BORDER | SWT.SINGLE;

    /**
     * Create a Label and a Text.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     */
    public LabelledFileField(Composite composite, String string, String[] extensions) {
        createFileField(composite, string, extensions, 1, DEFAULT_FIELD_STYLE, true);
    }

    /**
     * Create a Label and a Text.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     * @param isFill
     */
    public LabelledFileField(Composite composite, String string, String[] extensions, boolean isFill) {
        createFileField(composite, string, extensions, 1, DEFAULT_FIELD_STYLE, isFill);
    }

    /**
     * Create a Label and a Text width specific styleField.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     * @param int horizontalSpan
     */
    public LabelledFileField(Composite composite, String string, String[] extensions, int horizontalSpan) {
        createFileField(composite, string, extensions, horizontalSpan, DEFAULT_FIELD_STYLE, true);
    }

    /**
     * Create a Label and a Text width specific styleField.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     * @param int horizontalSpan
     * @param styleField
     */
    public LabelledFileField(Composite composite, String string, String[] extensions, int horizontalSpan, int styleField) {
        createFileField(composite, string, extensions, horizontalSpan, styleField, true);
    }

    /**
     * Create a Label and a Text width Gridata option FILL.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     * @param styleField
     * @param int horizontalSpan
     * @param isFill
     */
    public LabelledFileField(Composite composite, String string, String[] extensions, int horizontalSpan, boolean isFill) {
        createFileField(composite, string, extensions, horizontalSpan, DEFAULT_FIELD_STYLE, isFill);
    }

    /**
     * Create a Label and a Text width specific styleField and Gridata option FILL.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     * @param int horizontalSpan
     * @param styleField
     * @param isFill
     */
    public LabelledFileField(Composite composite, String string, String[] extensions, int horizontalSpan, int styleField, boolean isFill) {
        createFileField(composite, string, extensions, horizontalSpan, styleField, isFill);
    }

    /**
     * Create a Label and a Text width specific styleField and Gridata option FILL.
     * 
     * @param composite
     * @param string
     * @param String[] extensions
     * @param int horizontalSpan
     * @param styleField
     * @param isFill
     */
    private void createFileField(final Composite composite, String string, final String[] extensions, int horizontalSpan, int styleField,
            boolean isFill) {
        label = new Label(composite, SWT.LEFT);
        label.setText(string);
        label.addListener(SWT.MouseEnter, new Listener() {

            public void handleEvent(final Event e) {
                button.forceFocus();
            }
        });

        text = new Text(composite, styleField);
        text.selectAll(); // enable fast erase use
        int gridDataStyle = SWT.NONE;
        if (isFill) {
            gridDataStyle = SWT.FILL;
        }
        GridData gridData = new GridData(gridDataStyle, SWT.CENTER, true, false);
        gridData.horizontalSpan = horizontalSpan;
        text.setLayoutData(gridData);

        compositeButton = new Composite(composite, SWT.NONE);
        compositeButton.setLayout(new GridLayout());
        compositeButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));
        button = new Button(compositeButton, SWT.PUSH);
        button.setText("Browse...");

        // The action of the button "Browse..."
        button.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                displayDialog(composite, extensions);
            }

        });

        compositeButton.addListener(SWT.MouseDown, new Listener() {

            // Event FocusIn
            public void handleEvent(final Event e) {
                if (!button.getEnabled()) {
                    text.forceFocus();
                    if (text.getEditable()) {
                        displayDialog(composite, extensions);
                    }
                }
            }
        });
    }

    /**
     * displayDialog to choose a file path.
     * 
     * @param composite
     * @param String[] extensions
     */
    protected void displayDialog(Composite composite, String[] extensions) {
        FileDialog fileDialog = new FileDialog(composite.getShell(), SWT.OPEN);
        fileDialog.setFileName(text.getText());
        fileDialog.setText("Select a " + label.getText());
        if (extensions != null) {
            fileDialog.setFilterExtensions(extensions);
        }
        // fileDialog.setFilterNames(filterNames);
        String result = fileDialog.open();
        if (result != null) {
            text.setText(result);
        }
    }

    /**
     * setTextLimit to Text Object.
     * 
     * @param limit
     */
    public void setTextLimit(final int limit) {
        text.setTextLimit(limit);
    }

    /**
     * setToolTipText to Text Object.
     * 
     * @param string
     */
    public void setToolTipText(final String string) {
        text.setToolTipText(string);
        button.setToolTipText(string);
    }

    /**
     * getText to Text Object.
     * 
     * @return string
     */
    public String getText() {
        if (text.getText() == null) {
            return "";
        }
        return text.getText().replace("\\", "\\\\");
    }

    /**
     * setText to Text Object.
     * 
     * @param string
     */
    public void setText(final String string) {
        if (string != null) {
            text.setText(string);
        } else {
            text.setText("");
        }
    }

    /**
     * setText to Label Object.
     * 
     * @param string
     */
    public void setLabelText(final String string) {
        if (string != null) {
            label.setText(string);
        } else {
            label.setText("");
        }
    }

    /**
     * setEditable to Text and Label Object.
     * 
     * @param boolean
     */
    public void forceFocus() {
        setEditable(true);
        button.forceFocus();
    }

    /**
     * setEditable to Text and Label Object.
     * 
     * @param boolean
     */
    public void setEditable(final boolean visible) {
        text.setEditable(visible);
        label.setEnabled(visible);
        button.setEnabled(visible);
    }

    /**
     * setVisible to Text and Label Object.
     * 
     * @param boolean
     */
    public void setVisible(final boolean visible) {
        text.setVisible(visible);
        label.setVisible(visible);
        button.setVisible(visible);
    }

    /**
     * addListener to Text Object.
     * 
     * @param eventType
     * @param listener
     */
    public void addListener(int eventType, Listener listener) {
        text.addListener(eventType, listener);
    }

    /**
     * addModifyListener to Text Object.
     * 
     * @param listener
     */
    public void addModifyListener(ModifyListener listener) {
        text.addModifyListener(listener);
    }

    /**
     * getEditable to Text Object.
     * 
     * @return boolean
     */
    public boolean getEditable() {
        return text.getEditable();
    }

    /**
     * getCharCount to Text Object.
     * 
     * @return int
     */
    public int getCharCount() {
        return text.getCharCount();
    }

    /**
     * DOC ocarbone Comment method "setReadOnly".
     * 
     * @param b
     */
    public void setReadOnly(boolean visible) {
        label.setEnabled(true);
        text.setEnabled(!visible);
        button.setEnabled(!visible);
    }

}
