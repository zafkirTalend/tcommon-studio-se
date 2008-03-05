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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.relational.TdTable;
import orgomg.cwm.resource.relational.Schema;

/**
 * @author scorreia
 * 
 * Utility for handling Schema.
 */
public final class SchemaHelper {

    private SchemaHelper() {
    }

    public static List<TdSchema> getSchemas(Collection<? extends EObject> elements) {
        List<TdSchema> schemas = new ArrayList<TdSchema>();
        for (EObject modelElement : elements) {
            TdSchema schema = SwitchHelpers.SCHEMA_SWITCH.doSwitch(modelElement);
            if (schema != null) {
                schemas.add(schema);
            }
        }
        return schemas;
    }

    public static List<TdTable> getTables(Schema schema) {
        return TableHelper.getTables(schema.getOwnedElement());
    }

    public static boolean addTables(Collection<TdTable> tables, Schema schema) {
        return schema.getOwnedElement().addAll(tables);
    }
}
