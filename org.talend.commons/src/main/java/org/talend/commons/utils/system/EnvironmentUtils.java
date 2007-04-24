// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.commons.utils.system;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public class EnvironmentUtils {

    public static boolean isWindowsSystem() {
        return getEnvOs().startsWith("Windows"); //$NON-NLS-N$
    }

    public static boolean isLinuxUnixSystem() {
        return !isWindowsSystem() && !isMacOsSytem();
    }
    
    /**
     * DOC amaumont Comment method "isMacOsSytem".
     * @return
     */
    public static boolean isMacOsSytem() {
        return getEnvOs().startsWith("Mac"); //$NON-NLS-N$
    }

    /**
     * DOC amaumont Comment method "getEnv".
     */
    private static String getEnvOs() {
        return System.getenv("OS"); //$NON-NLS-N$
    }
    
}
