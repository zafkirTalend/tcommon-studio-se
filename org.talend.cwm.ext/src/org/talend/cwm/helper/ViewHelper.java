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

import java.util.Collection;

import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdView;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class ViewHelper {

    private ViewHelper() {
    }

    public static boolean addColumns(TdView view, Collection<TdColumn> columns) {
        assert view != null;
        assert columns != null;
        return view.getFeature().addAll(columns);
    }

}
