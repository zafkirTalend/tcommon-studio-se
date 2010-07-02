// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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

import org.talend.core.i18n.Messages;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public enum EComponentCategory {
    MAIN(Messages.getString("EComponentCategory_main"), 1), //$NON-NLS-1$
    VIEW(Messages.getString("EComponentCategory_view"), 3), //$NON-NLS-1$
    APPEARANCE(Messages.getString("EComponentCategory_appearance"), 2), //$NON-NLS-1$
    RULERS_AND_GRID(Messages.getString("EComponentCategory_rulerAndGrid"), 3), //$NON-NLS-1$
    ASSIGNMENT(Messages.getString("EComponentCategory_assignment"), 4), //$NON-NLS-1$
    DOC(Messages.getString("EComponentCategory_doc"), 4), //$NON-NLS-1$
    CONTEXT(Messages.getString("EComponentCategory_context"), 5), //$NON-NLS-1$
    VERSIONS(Messages.getString("EComponentCategory_version"), 9), //$NON-NLS-1$
    HEADERFOOTER(Messages.getString("EComponentCategory_headerFooter"), 9), //$NON-NLS-1$
    SVNHISTORY(Messages.getString("EComponentCategory_svnHistory"), 9), //$NON-NLS-1$
    LOGS(Messages.getString("EComponentCategory_logs"), 6), //$NON-NLS-1$
    STATSANDLOGS(Messages.getString("EComponentCategory_statsAndLogs"), 7), //$NON-NLS-1$
    TECHNICAL(Messages.getString("EComponentCategory_technical"), 8), // for non displayed parameters //$NON-NLS-1$
    ADVANCED(Messages.getString("EComponentCategory_advanceSetting"), 9), //$NON-NLS-1$
    RESUMING(Messages.getString("EComponentCategory_errorRecovery"), 15), //$NON-NLS-1$
    BASIC(Messages.getString("EComponentCategory_basicSetting"), 10), //$NON-NLS-1$
    ADVANCED_PROPERTIES(Messages.getString("EComponentCategory_properties"), 11, BASIC, ADVANCED), //$NON-NLS-1$
    EXTRA(Messages.getString("EComponentCategory_extra"), 12), //$NON-NLS-1$
    DYNAMICS_SETTINGS(Messages.getString("EComponentCategory_dynamicSetting"), 13), //$NON-NLS-1$
    SQL_PATTERN(Messages.getString("EComponentCategory_sqlTemplate"), 14), //$NON-NLS-1$
    BREAKPOINT("Breakpoint", 15),
    BASICRUN("Basic Run", 1),
    DEBUGRUN("Debug Run", 2),
    ADVANCESETTING("Advance Setting", 3),
    TARGET("Target Exec", 4);

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
