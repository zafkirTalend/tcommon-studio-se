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
package org.talend.core.model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.process.IContextParameter;

/**
 * Utilities to work with IContextParamet objects. <br/>
 * 
 * $Id$
 * 
 */
public final class ContextParameterUtils {

    /**
     * Constructs a new ContextParameterUtils.
     */
    private ContextParameterUtils() {
    }

    /**
     * Script code used to invoque a context parameter in a given language.
     * 
     * @param parameter Context parameter used in script.
     * @param language Language of the script.
     * @return Script code invoquing the context parameter.
     */
    public static String getScriptCode(IContextParameter parameter, ECodeLanguage language) {
        String code;
        switch (language) {
        case PERL:
            code = "$_context{" + parameter.getName() + "}"; //$NON-NLS-1$ //$NON-NLS-2$
            break;
        default:
            code = parameter.getName();
        }
        return code;
    }

    /**
     * Tells if a context parameter is a valid name.
     * 
     * @param name Context parameter name tested.
     * @param language Language where the context parameter is used.
     * @return true if the name is valid, false otherwise.
     * 
     * @deprecated should be tested in by the context manager of a process
     */
    public static boolean isValidName(String name, ECodeLanguage language) {
        boolean valid;
        switch (language) {
        case PERL:
            String perlPattern = "[a-zA-Z0-9_]+"; //$NON-NLS-1$
            Pattern p = Pattern.compile(perlPattern);
            Matcher m = p.matcher(name);
            valid = m.matches();
            break;
        default:
            valid = true;
        }
        return valid;
    }
}
