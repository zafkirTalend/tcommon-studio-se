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
package org.talend.runprocess.data;

import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnectionCategory;

/**
 * DOC zwei class global comment. Detailled comment
 */
public class HtmlFromPerformance {

    public static String getLabel(EConnectionType lineStyle, String msg) {
        if (lineStyle.equals(EConnectionType.ITERATE)) {
            return new IteratePerformance().getLabel(msg);
        } else if (lineStyle.hasConnectionCategory(IConnectionCategory.DEPENDENCY)) {
            // "OnComponentOK/OnComponentError/OnSubJobOK/OnSubJobError/If"
            return new LiteralPerformance().getLabel(msg);
        } else {
            // if no parallel execution existed, just delegate to super class.
            return new ParallelPerformance().getLabel(lineStyle, msg);
        }
    }
}
