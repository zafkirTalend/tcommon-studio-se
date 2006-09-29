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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Dimensionned Button ( defined width & height OR use FillLayout).
 * 
 * $Id$
 * 
 */
public class UtilsButton {

    Button button;

    /**
     * create Button width a RowData.
     * 
     * @param Composite parent
     * @param String string
     * @param int width
     * @param int height
     * 
     * @return Button
     */
    public UtilsButton(Composite parent, String string, int width, int height) {
        parent.setLayout(new RowLayout());
        createButton(parent, string, false);
        button.setLayoutData(new RowData(width, height));
    }

    /**
     * create Button width a possible FillLayout.
     * 
     * @param Composite parent
     * @param String string
     * @param boolean useFillLayout (use full space)
     * 
     * @return Button
     */
    public UtilsButton(Composite parent, String string, boolean useFillLayout) {
        createButton(parent, string, useFillLayout);
    }

    /**
     * create Button width image less FillLayout.
     * 
     * @param Composite parent
     * @param Image image
     * @param String string
     */
    public UtilsButton(Composite parent, Image image, String string) {
        button = new Button(parent, SWT.PUSH);
        button.setImage(image);
        button.setToolTipText(string);
    }

    /**
     * create Button width a possible FillLayout.
     * 
     * @param Composite parent
     * @param String string
     * @param boolean useFillLayout (use full space)
     * 
     * @return Button
     */
    private void createButton(Composite parent, String string, boolean useFillLayout) {
        button = new Button(parent, SWT.PUSH);
        button.setText(string);
        if (useFillLayout) {
            FillLayout fillLayout = new FillLayout();
            parent.setLayout(fillLayout);
        }
    }

    /**
     * button.setEnabled().
     * 
     * @param boolean
     */
    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }

    /**
     * setImage button.
     * 
     * @param image
     */
    public void setImage(Image image) {
        button.setImage(image);
    }

    /**
     * button.addSelectionListener().
     * 
     * @param adapter
     */
    public void addSelectionListener(SelectionAdapter adapter) {
        button.addSelectionListener(adapter);
    }

    /**
     * button.forceFocus().
     */
    public void forceFocus() {
        button.forceFocus();
    }

    /**
     * button.getEnabled ().
     * 
     * @return
     */
    public boolean getEnabled() {
        return button.getEnabled();
    }

    /**
     * button.setToolTipText().
     * 
     * @param string
     */
    public void setToolTipText(String string) {
        button.setToolTipText(string);
    }

    /**
     * button.setVisible().
     * 
     * @param boolean
     */
    public void setVisible(boolean visible) {
        button.setVisible(visible);
    }

    /**
     * button.setText().
     * 
     * @param string
     */
    public void setText(String string) {
        button.setText(string);
    }

    /**
     * button.addMouseListener().
     * 
     * @param adapter
     */
    public void addMouseListener(MouseAdapter adapter) {
        button.addMouseListener(adapter);
    }

    /**
     * button.getText().
     * 
     * @return string
     */
    public String getText() {
        return button.getText();
    }
}
