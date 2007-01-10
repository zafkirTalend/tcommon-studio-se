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

/**
 * Core preferences. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface ITalendCorePrefConstants {

    /** Path to the directory of temporary files. */
    String FILE_PATH_TEMP = "filePathTemp";

    /** Perl interpreter. */
    String PERL_INTERPRETER = "perlInterpreter";

    /** Perl library directory. */
    // String PERL_LIB = "perlLib";
    /** Known servers list. */
    String SERVERS = "servers";

    /** Known context list. */
    String CONTEXTS = "servers";

    /** Known context list. */
    String DBLOGINS = "dblogins";

    /** Known context list. */
    String DBPASSWORDS = "dbpasswords";

    /** Known ports list. */
    String PORTS = "ports";

    /** Known servers list. */
    String REPOSITORIES = "repositories";

    /** Known users list. */
    String USERS = "users";

    /** Last used server. */
    String LAST_USED_SERVER = "lastUSedServer";

    /** Last used context. */
    String LAST_USED_CONTEXT = "lastUSedContext";

    /** Last used port. */
    String LAST_USED_PORT = "lastUSedPort";

    /** Last used port. */
    String LAST_USED_DBLOGIN = "lastUSedDblogin";

    /** Last used port. */
    String LAST_USED_DBPASSWORD = "lastUSedDbpassword";

    /** Last used repository. */
    String LAST_USED_REPOSITORY = "lastUSedRepository";

    /** Last used project. */
    String LAST_USED_PROJECT = "lastUSedProject";

    /** Last used user. */
    String LAST_USED_USER = "lastUSedUser";

    /** Preview Limit. */
    String PREVIEW_LIMIT = "previewLimit";
}
