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
package org.talend.cwm.helper.factories;

import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.RelationalFactory;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class ColumnFactory {

    private ColumnFactory() {
    }

    public static Column createColumn(String name) {
        Column column = RelationalFactory.eINSTANCE.createColumn();
        column.setName(name);

        return column;
    }
}
