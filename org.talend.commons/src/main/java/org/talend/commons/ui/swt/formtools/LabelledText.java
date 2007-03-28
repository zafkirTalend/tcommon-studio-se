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
package org.talend.commons.ui.swt.formtools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * Create a Label and a Text.
 * 
 * $Id$
 * 
 */
public class LabelledText {

    private Text text;

    private Label label;

    private Point lastSelection;

    private static final int DEFAULT_FIELD_STYLE = SWT.BORDER | SWT.SINGLE;

    private static final int DEFAULT_LABEL_STYLE = SWT.LEFT;

    /**
     * Create a Label and a Text.
     * 
     * @param composite
     * @param string
     */
    public LabelledText(Composite composite, String string) {
        createLabelledText(composite, string, 1, DEFAULT_FIELD_STYLE, true, DEFAULT_LABEL_STYLE);
    }

    /**
     * Create a Label and a Text.
     * 
     * @param composite
     * @param string
     * @param isFill
     */
    public LabelledText(Composite composite, String string, boolean isFill) {
        createLabelledText(composite, string, 1, DEFAULT_FIELD_STYLE, isFill, DEFAULT_LABEL_STYLE);
    }

    /**
     * Create a Label and a Text width specific styleField.
     * 
     * @param composite
     * @param string
     * @param int horizontalSpan
     * @param int labelStyle
     */
    public LabelledText(Composite composite, String string, int horizontalSpan, boolean isFill, int labelStyle) {
        createLabelledText(composite, string, horizontalSpan, DEFAULT_FIELD_STYLE, isFill, labelStyle);
    }

    /**
     * Create a Label and a Text width specific styleField.
     * 
     * @param composite
     * @param string
     * @param int horizontalSpan
     */
    public LabelledText(Composite composite, String string, int horizontalSpan) {
        createLabelledText(composite, string, horizontalSpan, DEFAULT_FIELD_STYLE, true, DEFAULT_LABEL_STYLE);
    }

    /**
     * Create a Label and a Text width specific styleField.
     * 
     * @param composite
     * @param string
     * @param int horizontalSpan
     * @param styleField
     */
    public LabelledText(Composite composite, String string, int horizontalSpan, int styleField) {
        createLabelledText(composite, string, horizontalSpan, styleField, true, DEFAULT_LABEL_STYLE);
    }

    /**
     * Create a Label and a Text width Gridata option FILL.
     * 
     * @param composite
     * @param string
     * @param styleField
     * @param int horizontalSpan
     * @param isFill
     */
    public LabelledText(Composite composite, String string, int horizontalSpan, boolean isFill) {
        createLabelledText(composite, string, horizontalSpan, DEFAULT_FIELD_STYLE, isFill, DEFAULT_LABEL_STYLE);
    }

    /**
     * Create a Label and a Text width specific styleField and Gridata option FILL.
     * 
     * @param composite
     * @param string
     * @param int horizontalSpan
     * @param styleField
     * @param isFill
     */
    public LabelledText(Composite composite, String string, int horizontalSpan, int styleField, boolean isFill) {
        createLabelledText(composite, string, horizontalSpan, styleField, isFill, DEFAULT_LABEL_STYLE);
    }

    /**
     * Create a Label and a Text width specific styleField and Gridata option FILL.
     * 
     * @param composite
     * @param string
     * @param int horizontalSpan
     * @param styleField
     * @param isFill
     */
    private void createLabelledText(Composite composite, String string, int horizontalSpan, int styleField, boolean isFill, int labelStyle) {

        label = new Label(composite, labelStyle);
        if (string != null) {
            label.setText(string);
        }
        label.addListener(SWT.MouseEnter, new Listener() {

            public void handleEvent(final Event e) {
                if (getEditable()) {
                    text.forceFocus();
                }
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

        text.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                lastSelection = text.getSelection();
                if (Character.getType(e.character) != SWT.DEL) {
                    lastSelection.x = lastSelection.x - 1;
                } else {
                    lastSelection.x = lastSelection.x + 1;
                }
            }
        });

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
    }

    /**
     * getText to Text Object.
     * 
     * @return string
     */
    public String getText() {
        return text.getText();
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
        text.forceFocus();
        if (!text.getText().equals("")) {
            text.setSelection(0, text.getText().length());
        }
    }

    /**
     * setSelection to Text.
     * 
     * @param index
     */
    public void setSelection(int index) {
        text.setSelection(index);
    }

    /**
     * setSelection to Text.
     * 
     * @param start
     * @param end
     */
    public void setSelection(int start, int end) {
        text.setSelection(start, end);
    }

    /**
     * setEditable to Text and Label Object.
     * 
     * @param boolean
     */
    public void setEditable(final boolean visible) {
        text.setEditable(visible);
        label.setEnabled(visible);
    }

    /**
     * setReadOnly to Label Object.
     * 
     * @param boolean
     */
    public void setReadOnly(final boolean visible) {
        label.setEnabled(true);
        text.setEnabled(!visible);
    }

    /**
     * setVisible to Text and Label Object.
     * 
     * @param boolean
     */
    public void setVisible(final boolean visible) {
        text.setVisible(visible);
        label.setVisible(visible);
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
     * setLabelWidth.
     * 
     * @param width
     */
    public void setLabelWidth(int width) {
        label.setSize(width, label.getSize().y);
    }

    /**
     * DOC ocarbone Comment method "setSize".
     * 
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        text.setSize(width, height);
    }

    /**
     * DOC ocarbone Comment method "setLayoutData".
     * 
     * @param gridData
     */
    public void setLayoutData(GridData gridData) {
        text.setLayoutData(gridData);
    }

    /**
     * DOC ocarbone Comment method "addKeyListener".
     * 
     * @param adapter
     */
    public void addKeyListener(KeyAdapter adapter) {
        text.addKeyListener(adapter);
    }

    /**
     * DOC ocarbone Comment method "getSelection()".
     * 
     * @return Point
     */
    public Point getSelection() {
        return text.getSelection();
    }

    /**
     * Getter for lastSelection.
     * 
     * @return the lastSelection
     */
    public Point getLastSelection() {
        if (lastSelection == null) {
            return new Point(0, 0);
        } else {
            return this.lastSelection;
        }
    }

    /**
     * DOC ocarbone Comment method "setEnable".
     * 
     * @param visible
     */
    public void setEnabled(boolean visible) {
        text.setEnabled(visible);
    }

    /**
     * DOC ocarbone Comment method "addFocusListener".
     * 
     * @param listener
     */
    public void addFocusListener(FocusListener listener) {
        text.addFocusListener(listener);

    }

}
