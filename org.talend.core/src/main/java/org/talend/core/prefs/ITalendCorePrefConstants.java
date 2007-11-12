// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.core.prefs;

/**
 * Core preferences. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface ITalendCorePrefConstants {

    /** Path to the directory of temporary files. */
    String FILE_PATH_TEMP = "filePathTemp"; //$NON-NLS-1$

    /** Perl interpreter. */
    String PERL_INTERPRETER = "perlInterpreter"; //$NON-NLS-1$

    /** Java interpreter. */
    String JAVA_INTERPRETER = "javaInterpreter"; //$NON-NLS-1$

    /** Lanugage selector. */
    String LANGUAGE_SELECTOR = "languageSelector"; //$NON-NLS-1$

    /** Perl library directory. */
    // String PERL_LIB = "perlLib";
    /** Known users list. */
    String USERS = "users"; //$NON-NLS-1$

    String CONNECTIONS = "connections"; //$NON-NLS-1$

    /** Last used connection. */
    String LAST_USED_CONNECTION = "lastUSedConnection"; //$NON-NLS-1$

    /** Last used project. */
    String LAST_USED_PROJECT = "lastUSedProject"; //$NON-NLS-1$

    /** Last used user. */
    String LAST_USED_USER = "lastUSedUser"; //$NON-NLS-1$

    /** Preview Limit. */
    String PREVIEW_LIMIT = "previewLimit"; //$NON-NLS-1$

    String WORKSPACE_TASKS_DONE = "workspaceTasksDone"; //$NON-NLS-1$

    String RUN_IN_MULTI_THREAD = "runInMultiThread"; //$NON-NLS-1$

    String SQL_ADD_QUOTE = "addSqlQuote"; //$NON-NLS-1$
    
    String DOC_GENERATION = "doc_generation"; //$NON-NLS-1$
}
