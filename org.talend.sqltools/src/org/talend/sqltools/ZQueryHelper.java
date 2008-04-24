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
package org.talend.sqltools;

import java.util.Vector;

import Zql.ZFromItem;
import Zql.ZSelectItem;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public final class ZQueryHelper {

    private ZQueryHelper() {
    }

    public static Vector<ZSelectItem> createSelectVector(String... f) {
        Vector<ZSelectItem> v = new Vector<ZSelectItem>();
        if (f == null) {
            return v;
        }
        for (String string : f) {
            if (string != null) {
                v.add(new ZSelectItem(string));
            }
        }
        return v;
    }

    public static Vector<ZFromItem> createFromVector(String... f) {
        Vector<ZFromItem> v = new Vector<ZFromItem>();
        if (f == null) {
            return v;
        }
        for (String string : f) {
            if (string != null) {
                v.add(new ZFromItem(string));
            }
        }
        return v;
    }
}
