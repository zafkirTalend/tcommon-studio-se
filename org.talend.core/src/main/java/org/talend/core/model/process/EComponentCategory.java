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
package org.talend.core.model.process;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public enum EComponentCategory {
    MAIN("Main", 1),
    VIEW("View", 3),
    PROPERTY("Properties", 2),
    DOC("Documentation", 4),
    CONTEXT("Context", 5),
    VERSIONS("Version", 9),
    LOGS("Logs", 6),
    STATSANDLOGS("Statsand logs", 7),
    TECHNICAL("Technical", 8); // for non displayed parameters

    private String title;

    private int priority;

    /**
     * yzhang EComponentCategory constructor comment.
     */
    private EComponentCategory(String title, int priority) {
        this.title = title;
        this.priority = priority;
    }

    /**
     * yzhang Comment method "getTitle".
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * yzhang Comment method "getPriorityNumber".
     * 
     * @return
     */
    public int getPriorityNumber() {
        return priority;
    }
}
