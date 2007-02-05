// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.prefs;

import org.talend.core.i18n.Messages;

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

    /** Java interpreter */
    String JAVA_INTERPRETER = "javaInterpreter"; //$NON-NLS-1$

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
}
