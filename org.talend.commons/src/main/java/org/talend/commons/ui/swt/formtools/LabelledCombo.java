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

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * Create Label and Combo.
 * 
 * $Id$
 * 
 */
public class LabelledCombo {

    /**
     * 
     */

    private Label label;

    private Combo combo;

    private static final int MAX_VISIBLE_ITEM_COUNT = 25;

    private static final int DEFAULT_COMBO_STYLE = SWT.READ_ONLY;

    private static final int DEFAULT_LABEL_STYLE = SWT.LEFT;

    /**
     * create Label and Combo width a List of String.
     * 
     * @param Composite parent
     * @param String label
     * 
     * @param String tip
     * @param List<String> item
     * 
     * @return Combo
     */
    public LabelledCombo(Composite parent, String labelStr, String tip, List<String> item) {
        String[] items = null;
        if (item != null && !item.isEmpty()) {
            items = new String[item.size()];
            Iterator<String> iterate = item.iterator();
            int i = 0;
            while (iterate.hasNext()) {
                items[i] = iterate.next();
                i++;
            }
        }
        createLabelledCombo(parent, labelStr, tip, items, 1, false, DEFAULT_COMBO_STYLE, DEFAULT_LABEL_STYLE);
    }

    /**
     * create Label and Combo width a Array of String.
     * 
     * @param Composite parent
     * @param String label
     * @param String tip
     * @param String[] item
     * @param int horizontalSpan
     * @param boolean isFill
     * 
     * @return Combo
     */
    public LabelledCombo(final Composite parent, final String labelStr, final String tip, final String[] item,
            int horizontalSpan, boolean isFill) {
        createLabelledCombo(parent, labelStr, tip, item, horizontalSpan, isFill, DEFAULT_COMBO_STYLE,
                DEFAULT_LABEL_STYLE);
    }

    /**
     * create Label and Combo width a Array of String.
     * 
     * @param Composite parent
     * @param String label
     * @param String tip
     * @param String[] item
     * @param int horizontalSpan
     * 
     * @return Combo
     */
    public LabelledCombo(Composite parent, String labelStr, String tip, String[] item, int horizontalSpan) {
        createLabelledCombo(parent, labelStr, tip, item, horizontalSpan, false, DEFAULT_COMBO_STYLE,
                DEFAULT_LABEL_STYLE);
    }

    /**
     * create Label and Combo width a Array of String.
     * 
     * @param Composite parent
     * @param String label
     * @param String tip
     * @param String[] item
     * 
     * @return Combo
     */
    public LabelledCombo(Composite parent, String labelStr, String tip, String[] item) {
        createLabelledCombo(parent, labelStr, tip, item, 1, false, DEFAULT_COMBO_STYLE, DEFAULT_LABEL_STYLE);
    }

    /**
     * create Label and Combo width a Array of String.
     * 
     * @param Composite parent
     * @param String label
     * @param String tip
     * @param List<String> item
     * @param int horizontalSpan
     * @param boolean isFill
     * @param int style
     */
    public LabelledCombo(final Composite parent, final String labelStr, final String tip, final String[] item,
            final int horizontalSpan, final boolean isFill, final int comboStyle) {
        createLabelledCombo(parent, labelStr, tip, item, horizontalSpan, isFill, comboStyle, SWT.LEFT);
    }

    /**
     * create Label and Combo width a Array of String.
     * 
     * @param Composite parent
     * @param String label
     * @param String tip
     * @param List<String> item
     * @param int horizontalSpan
     * @param boolean isFill
     * @param int comboStyle
     * @param int labelStyle
     */

    public LabelledCombo(final Composite parent, final String labelStr, final String tip, final String[] item,
            final int horizontalSpan, final boolean isFill, final int comboStyle, int labelStyle) {
        createLabelledCombo(parent, labelStr, tip, item, horizontalSpan, isFill, comboStyle, labelStyle);
    }

    /**
     * create Label and Combo width a Array of String.
     * 
     * @param parent
     * @param labelStr
     * @param tip
     * @param item
     * @param horizontalSpan
     * @param isFill
     * @param comboStyle
     * @param labelStyle
     */
    private void createLabelledCombo(final Composite parent, final String labelStr, final String tip,
            final String[] item, final int horizontalSpan, final boolean isFill, final int comboStyle,
            final int labelStyle) {

        label = new Label(parent, labelStyle);
        if (labelStr != null) {
            label.setText(labelStr);
        }
        label.addListener(SWT.MouseEnter, new Listener() {

            public void handleEvent(final Event e) {
                combo.forceFocus();
            }
        });

        combo = new Combo(parent, comboStyle);
        GridData gridData;
        if (isFill) {
            gridData = new GridData(GridData.FILL_HORIZONTAL);
        } else {
            gridData = new GridData();
        }
        gridData.horizontalSpan = horizontalSpan;
        combo.setLayoutData(gridData);

        if (item != null) {
            for (int i = 0; i < item.length; i++) {
                combo.add(item[i]);
            }
        }
        int visibleItemCount = combo.getItemCount();
        if (visibleItemCount > MAX_VISIBLE_ITEM_COUNT) {
            visibleItemCount = MAX_VISIBLE_ITEM_COUNT;
        }
        combo.setVisibleItemCount(visibleItemCount);
        if (tip != null) {
            combo.setToolTipText(tip);
        }
    }

    /**
     * return Object Combo.
     * 
     * @return Combo
     */
    public Combo getCombo() {
        return combo;
    }

    /**
     * return SelectionIndex of the combo.
     * 
     * @return int
     */
    public int getSelectionIndex() {
        return combo.getSelectionIndex();
    }

    /**
     * select a item of the combo.
     * 
     * @param index
     */
    public void select(int index) {
        combo.select(index);
    }

    /**
     * setVisibleItemCount of the combo.
     * 
     * @param size
     */
    public void setVisibleItemCount(int size) {
        combo.setVisibleItemCount(size);
    }

    /**
     * getItem of the combo.
     * 
     * @param index
     * @return int
     */
    public String getItem(int index) {
        return combo.getItem(index);
    }

    /**
     * addModifyListener to the combo.
     * 
     * @param listener
     */
    public void addModifyListener(ModifyListener listener) {
        combo.addModifyListener(listener);
    }

    /**
     * forceFocus.
     */
    public void forceFocus() {
        combo.forceFocus();
    }

    /**
     * addKeyListener to the combo.
     * 
     * @param adapter
     */
    public void addKeyListener(KeyAdapter adapter) {
        combo.addKeyListener(adapter);
    }

    public String getText() {
        if (combo.getSelectionIndex() < -1) {
            return combo.getItem(combo.getSelectionIndex());
        } else {
            return getCombo().getText();
        }
    }

    /**
     * clearSelection to the combo.
     */
    public void clearSelection() {
        combo.clearSelection();
        combo.setSelection(new Point(1, 1));
    }

    /**
     * deselectAll to the combo.
     */
    public void deselectAll() {
        combo.deselectAll();
    }

    /**
     * removeAll to the combo.
     */
    public void removeAll() {
        combo.removeAll();
    }

    /**
     * setEnabled to the label and the combo.
     */
    public void setEnabled(Boolean b) {
        label.setEnabled(b);
        combo.setEnabled(b);
    }

    /**
     * combo.getItemCount().
     * 
     * @return
     */
    public int getItemCount() {
        return combo.getItemCount();
    }

    /**
     * combo.add().
     * 
     * @param string
     */
    public void add(String string) {
        combo.add(string);
    }

    /**
     * combo.setText (use less SWT.READ_ONLY).
     * 
     * @param escapeCharComboOldValue
     */
    public void setText(String string) {
        if (string != null) {
            combo.setText(string);
        } else {
            combo.setText("");
        }
    }

    /**
     * label.getText().
     * 
     * @return string
     */
    public String getLabel() {
        return label.getText();
    }

    /**
     * DOC ocarbone Comment method "setReadOnly".
     * 
     * @param b
     */
    public void setReadOnly(boolean visible) {
        label.setEnabled(true);
        combo.setEnabled(!visible);
    }

}
