// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.commons.ui.swt.tableviewer.tableeditor;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.IModifiedBeanListener;
import org.talend.commons.ui.swt.tableviewer.ModifiedBeanEvent;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.utils.threading.AsynchronousThreading;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CheckboxTableEditorContent extends TableEditorContent {

    public static final boolean CHECKED = true;

    public static final boolean UNCHECKED = false;

    private boolean readOnly;

    private String toolTipText;

    private static boolean highlightingCheck;

    private Color color;

    private static Color bgColorCheck;

    /**
     * DOC amaumont CheckboxTableEditorContent constructor comment.
     */
    public CheckboxTableEditorContent() {
        super();
    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.TableEditorContent#initialize(org.eclipse.swt.widgets.Table,
     * org.eclipse.swt.custom.TableEditor, org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn,
     * java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Control initialize(final Table table, final TableEditor tableEditor,
            final TableViewerCreatorColumn currentColumn, final Object currentRowObject, final Object currentCellValue) {

        /*
         * Do not set check control as field because one instance of CheckboxTableEditorContent is shared by all checks
         * of a same column
         * 
         */

        final TableViewerCreator<Object> tableViewerCreator = currentColumn.getTableViewerCreator();

        final Button check = new Button(table, SWT.CHECK);
        boolean enabled = currentColumn.isModifiable()
                && (tableViewerCreator.getCellModifier() == null || tableViewerCreator.getCellModifier().canModify(
                        currentRowObject, currentColumn.getId()));
        check.setEnabled(enabled);
        check.setText("");
        check.setToolTipText(toolTipText);
        check.setBackground(table.getBackground());
        check.setSelection(currentCellValue.equals(true));
        if (bgColorCheck == null) {
            bgColorCheck = new Color(check.getDisplay(), 28, 81, 128);
        }
        check.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            @SuppressWarnings("unchecked")
            public void widgetSelected(SelectionEvent e) {
                tableViewerCreator.getCellModifier().modify(tableEditor.getItem(), currentColumn.getId(),
                        ((Button) e.getSource()).getSelection() ? CHECKED : UNCHECKED);
                tableViewerCreator.getTableViewer().setSelection(new StructuredSelection(currentRowObject));
            }

        });
        Point size = check.computeSize(SWT.DEFAULT, table.getItemHeight());

        tableViewerCreator.addModifiedBeanListener(new IModifiedBeanListener<Object>() {

            public void handleEvent(ModifiedBeanEvent<Object> event) {
                if (check.isDisposed()) {
                    tableViewerCreator.removeModifiedBeanListener(this);
                    return;
                }
                Object beanValue = tableViewerCreator.getBeanValue(currentColumn, currentRowObject);
                if (beanValue instanceof Boolean) {
                    check.setSelection(((Boolean) beanValue).booleanValue());
                }
            }

        });

        check.addFocusListener(new FocusListener() {

            public synchronized void focusGained(final FocusEvent e) {
                check.setBackground(bgColorCheck);
            }

            public void focusLost(FocusEvent e) {
                check.setBackground(table.getBackground());
            }

        });

        // Set attributes of the editor
        tableEditor.horizontalAlignment = SWT.CENTER;
        tableEditor.minimumHeight = size.y;
        tableEditor.minimumWidth = size.x;
        return check;
    }

    /**
     * Sets the toolTipText.
     * 
     * @param toolTipText the toolTipText to set
     */
    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

}
