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
package org.talend.commons.ui.swt.tableviewer.celleditor;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.talend.expressionbuilder.IExpressionConsumer;
import org.talend.expressionbuilder.IExtendedCellEditorBehavior;

/**
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: ExtendedTextCellEditor.java 下午03:55:26 2007-8-1 +0000 (2007-8-1) yzhang $
 * 
 */
public class ExtendedTextCellEditor extends TextCellEditor implements IExpressionConsumer {

    private IExtendedCellEditorBehavior cellEditorBehavior;

    private ModifyListener modifyListener;

    /**
     * State information for updating action enablement
     */
    private boolean isSelection = false;

    private boolean isDeleteable = false;

    private boolean isSelectable = false;

    private final Composite parent;

    /**
     * yzhang ExtendedTextCellEditor constructor comment.
     */
    public ExtendedTextCellEditor(Composite parent) {
        this.parent = parent;
        setStyle(SWT.SINGLE);
    }

    /**
     * Sets the cellEditorBehavior.
     * 
     * @param cellEditorBehavior the cellEditorBehavior to set
     */
    public void setCellEditorBehavior(IExtendedCellEditorBehavior cellEditorBehavior) {
        this.cellEditorBehavior = cellEditorBehavior;
    }

    /**
     * yzhang Comment method "init".
     */
    public void init() {

        create(parent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.TextCellEditor#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createControl(Composite parent) {
        return cellEditorBehavior.createBehaviorControls(parent);

    }

    public Text getTextControl() {
        return text;
    }

    public Text createText(Composite parent) {

        text = new Text(parent, SWT.NONE);
        text.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                handleDefaultSelection(e);
            }
        });
        text.addKeyListener(new KeyAdapter() {

            // hook key pressed - see PR 14201
            @Override
            public void keyPressed(KeyEvent e) {
                keyReleaseOccured(e);

                // as a result of processing the above call, clients may have
                // disposed this cell editor
                if ((getControl() == null) || getControl().isDisposed()) {
                    return;
                }
                checkSelection(); // see explaination below
                checkDeleteable();
                checkSelectable();
            }
        });
        text.addTraverseListener(new TraverseListener() {

            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                }
            }
        });
        // We really want a selection listener but it is not supported so we
        // use a key listener and a mouse listener to know when selection changes
        // may have occured
        text.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(MouseEvent e) {
                checkSelection();
                checkDeleteable();
                checkSelectable();
            }
        });

        text.setFont(parent.getFont());
        text.setBackground(parent.getBackground());
        text.setText("");//$NON-NLS-1$
        text.addModifyListener(getModifyListener());
        return text;

    }

    /**
     * Return the modify listener.
     */
    public ModifyListener getModifyListener() {
        if (modifyListener == null) {
            modifyListener = new ModifyListener() {

                public void modifyText(ModifyEvent e) {
                    editOccured(e);
                }
            };
        }
        return modifyListener;
    }

    /**
     * yzhang Comment method "checkSelection".
     */
    public void checkSelection() {
        boolean oldIsSelection = isSelection;
        isSelection = text.getSelectionCount() > 0;
        if (oldIsSelection != isSelection) {
            fireEnablementChanged(COPY);
            fireEnablementChanged(CUT);
        }
    }

    /**
     * yzhang Comment method "checkDeleteable".
     */
    public void checkDeleteable() {
        boolean oldIsDeleteable = isDeleteable;
        isDeleteable = isDeleteEnabled();
        if (oldIsDeleteable != isDeleteable) {
            fireEnablementChanged(DELETE);
        }
    }

    /**
     * yzhang Comment method "checkSelectable".
     */
    public void checkSelectable() {
        boolean oldIsSelectable = isSelectable;
        isSelectable = isSelectAllEnabled();
        if (oldIsSelectable != isSelectable) {
            fireEnablementChanged(SELECT_ALL);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.expressionbuilder.IExpressionConsumer#setConsumerExpression(java.lang.String)
     */
    public void setConsumerExpression(String expression) {
        text.setText(expression);
        focusLost();
    }

    /**
     * yzhang Comment method "getText".
     * 
     * @return
     */
    public String getText() {
        return text.getText();
    }

}
