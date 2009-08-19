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
package org.talend.core.model.process;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public enum EComponentCategory {
    MAIN("Main", 1), //$NON-NLS-1$
    VIEW("View", 3), //$NON-NLS-1$
    APPEARANCE("Appearance", 2), //$NON-NLS-1$
    RULERS_AND_GRID("Rulers & Grid", 3), //$NON-NLS-1$
    ASSIGNMENT("Assignment", 4), //$NON-NLS-1$
    DOC("Documentation", 4), //$NON-NLS-1$
    CONTEXT("Context", 5), //$NON-NLS-1$
    VERSIONS("Version", 9), //$NON-NLS-1$
    SVNHISTORY("SVN History", 9), //$NON-NLS-1$
    LOGS("Logs", 6), //$NON-NLS-1$
    STATSANDLOGS("Stats & Logs", 7), //$NON-NLS-1$
    TECHNICAL("Technical", 8), // for non displayed parameters //$NON-NLS-1$
    ADVANCED("Advanced settings", 9), //$NON-NLS-1$
    RESUMING("Resuming", 15), //$NON-NLS-1$
    BASIC("Basic settings", 10), //$NON-NLS-1$
    ADVANCED_PROPERTIES("Properties", 11, BASIC, ADVANCED), //$NON-NLS-1$
    EXTRA("Extra", 12), //$NON-NLS-1$
    DYNAMICS_SETTINGS("Dynamic settings", 13), //$NON-NLS-1$
    SQL_PATTERN("SQL Template", 14); //$NON-NLS-1$

    private String title;

    private int priority;

    private EComponentCategory[] subCategories;

    private EComponentCategory aliasFor;

    private EComponentCategory(String title, EComponentCategory aliasFor, int priority) {
        this.title = title;
        this.priority = priority;
        this.aliasFor = aliasFor;
    }

    /**
     * yzhang EComponentCategory constructor comment.
     */
    private EComponentCategory(String title, int priority, EComponentCategory... subCategories) {
        this.title = title;
        this.priority = priority;
        this.subCategories = subCategories;
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

    /**
     * yzhang Comment method "getSubCategories".
     * 
     * @return
     */
    public EComponentCategory[] getSubCategories() {
        return subCategories;
    }

    /**
     * yzhang Comment method "hadSubCategories".
     * 
     * @return
     */
    public boolean hadSubCategories() {
        return subCategories.length > 0;
    }

    /**
     * yzhang Comment method "getAliasFor".
     * 
     * @return
     */
    public EComponentCategory getAliasFor() {
        return aliasFor;
    }

    /**
     * yzhang Comment method "isAlias".
     * 
     * @return
     */
    public boolean isAlias() {
        return aliasFor != null;
    }

}
