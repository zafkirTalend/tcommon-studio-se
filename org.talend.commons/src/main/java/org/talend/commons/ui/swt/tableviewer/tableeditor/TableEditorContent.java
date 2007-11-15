// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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

import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/> $Id: TableEditorInitializer.java,v 1.1 2006/06/02 15:24:10
 * amaumont Exp $
 */
public abstract class TableEditorContent {

    /**
     * You can override this method if necessary.
     * 
     * @param table
     * @return TableEditor
     */
    public TableEditor createTableEditor(Table table) {
        return new TableEditor(table);
    }

    public abstract Control initialize(Table table, TableEditor tableEditor, TableViewerCreatorColumn currentColumn,
            Object currentRowObject, Object currentCellValue);

}
