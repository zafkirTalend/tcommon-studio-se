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
package org.talend.commons.ui.swt.tableviewer.tableeditor;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: CheckboxTableEditorContent.java 7183 2007-11-23 11:03:36Z amaumont $
 * 
 */
public class CheckboxTableEditorContent extends TableEditorContent {

    public static final boolean CHECKED = true;

    public static final boolean UNCHECKED = false;

    private String toolTipText;

    private static Color bgColorCheck;

    private Point checkSize;

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

//        System.out.println("Intitlalize:" + CheckboxTableEditorContent.this + " with check " + check.hashCode());
        
        boolean enabled = currentColumn.isModifiable()
                && (tableViewerCreator.getCellModifier() == null || tableViewerCreator.getCellModifier().canModify(
                        currentRowObject, currentColumn.getId()));

        if (!enabled) { // performance purpose
            check.setEnabled(enabled);
        }
        if (toolTipText != null && toolTipText.length() > 0) { // performance purpose
            check.setToolTipText(toolTipText);
        }
        // check.setBackground(table.getBackground()); // performance purpose
        if (currentCellValue.equals(true)) { // performance purpose
            check.setSelection(true);
        }
        if (bgColorCheck == null) {
            bgColorCheck = new Color(check.getDisplay(), 28, 81, 128);
        }
        
        final SelectionListener selectionListener = new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            @SuppressWarnings("unchecked")
            public void widgetSelected(SelectionEvent e) {
//                System.out.println("Checking " + check.hashCode());
                tableViewerCreator.getCellModifier().modify(tableEditor.getItem(), currentColumn.getId(),
                        ((Button) e.getSource()).getSelection() ? CHECKED : UNCHECKED);
                tableViewerCreator.getTableViewer().setSelection(new StructuredSelection(currentRowObject));
            }

        };
        check.addSelectionListener(selectionListener);

        final IModifiedBeanListener<Object> modifiedBeanListener = new IModifiedBeanListener<Object>() {

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

        };
        tableViewerCreator.addModifiedBeanListener(modifiedBeanListener);

        final FocusListener focusListener = new FocusListener() {

            public synchronized void focusGained(final FocusEvent e) {
                check.setBackground(bgColorCheck);
            }

            public void focusLost(FocusEvent e) {
                check.setBackground(table.getBackground());
            }

        };
        check.addFocusListener(focusListener);

        check.addDisposeListener(new DisposeListener() {

            public void widgetDisposed(DisposeEvent e) {
//                System.out.println("Disposing:" + CheckboxTableEditorContent.this + " with check " + check.hashCode());
                check.removeFocusListener(focusListener);
                tableViewerCreator.removeModifiedBeanListener(modifiedBeanListener);
                check.removeSelectionListener(selectionListener);
            }
            
        });

        
        // Set attributes of the editor
        tableEditor.horizontalAlignment = SWT.CENTER;

        if (checkSize == null) { // performance purpose
            checkSize = check.computeSize(SWT.DEFAULT, table.getItemHeight());
        }

        tableEditor.minimumHeight = checkSize.y;
        tableEditor.minimumWidth = checkSize.x;

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
