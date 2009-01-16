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
package org.talend.core.model.metadata;

/**
 * cLi class global comment. Detailled comment
 */
public final class MultiSchemasUtil {

    public static String getConnectionBaseName(String base) {
        final String space = "_"; //$NON-NLS-1$
        if (base == null | "".equals(base)) { //$NON-NLS-1$
            base = ""; //$NON-NLS-1$
        } else {
            base = base + space;
        }

        return "row" + space + base; //$NON-NLS-1$
    }
}
