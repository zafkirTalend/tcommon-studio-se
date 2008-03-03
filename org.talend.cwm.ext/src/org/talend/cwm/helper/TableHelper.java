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
package org.talend.cwm.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.talend.cwm.relational.TdTable;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public final class TableHelper {

    private TableHelper() {
    }

    public static List<TdTable> getTables(EList<ModelElement> elements) {
        List<TdTable> tables = new ArrayList<TdTable>();
        for (ModelElement modelElement : elements) {
            TdTable table = SwitchHelpers.TABLE_SWITCH.doSwitch(modelElement);
            if (table == null) {
                continue;
            }
            // else
            tables.add(table);
        }
        return tables;
    }
}
