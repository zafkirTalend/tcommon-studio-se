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
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.relational.TdTable;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Catalog;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public final class CatalogHelper {

    private CatalogHelper() {
    }

    public static List<TdTable> getTables(Catalog catalog) {
        return TableHelper.getTables(catalog.getOwnedElement());
    }

    public static List<TdSchema> getSchemas(Catalog catalog) {
        List<TdSchema> schemas = new ArrayList<TdSchema>();
        EList<ModelElement> ownedElements = catalog.getOwnedElement();
        for (ModelElement modelElement : ownedElements) {
            TdSchema schema = SwitchHelpers.SCHEMA_SWITCH.doSwitch(modelElement);
            if (schema == null) {
                continue;
            }
            // else
            schemas.add(schema);
        }
        return schemas;
    }
}
