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
package org.talend.core.model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;

/**
 * Utilities to work with IContextParamet objects. <br/>
 * 
 * $Id$
 * 
 */
public final class ContextParameterUtils {

    private static final String PERL_STARTWITH = "$_context{";

    private static final String PERL_ENDWITH = "}";

    private static final String JAVA_STARTWITH = "((String)context.getProperty(" + "\\" + "\"";

    private static final String JAVA_ENDWITH = "\\" + "\"))";

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
            code = PERL_STARTWITH + parameter.getName() + PERL_ENDWITH; //$NON-NLS-1$ //$NON-NLS-2$
            break;
        case JAVA:
            code = JAVA_STARTWITH + parameter.getName() + JAVA_ENDWITH;
            break;
        default:
            code = parameter.getName();
        }
        return code;
    }

    public static String parseScriptContextCode(String code, IContextManager contextManager) {
        // final ECodeLanguage language = ((RepositoryContext)
        // CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
        // .getProject().getLanguage();
        // if (!isContainContextParam(code)) {
        // return code;
        // } else {
        // String paraName = getContextString(code, language);
        for (IContext context : contextManager.getListContext()) {
            code = parseScriptContextCode(code, context);
        }
        // for (IContextParameter param : context.getContextParameterList()) {
        // if (param.getName().equals(paraName)) {
        // // return code.replace(getScriptCode(param, language), param.getValue());
        // return parseScriptContextCode(code.replace(getScriptCode(param, language), param.getValue()),
        // contextManager);
        // }
        // }
        // }
        // }
        return code;
    }

    public static String parseScriptContextCode(String code, IContext context) {
        final ECodeLanguage language = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        if (!isContainContextParam(code)) {
            return code;
        } else {
            String paraName = getContextString(code, language);
            for (IContextParameter param : context.getContextParameterList()) {
                if (param.getName().equals(paraName)) {
                    // return code.replace(getScriptCode(param, language), param.getValue());
                    return parseScriptContextCode(code.replace(getScriptCode(param, language), param.getValue()), context);
                }
            }
        }
        return code;
    }

    private static String getContextString(String code, ECodeLanguage language) {
        switch (language) {
        case PERL:
            return code.substring(code.indexOf(PERL_STARTWITH) + PERL_STARTWITH.length(), code.indexOf(PERL_ENDWITH));
        case JAVA:
            return code.substring(code.indexOf(JAVA_STARTWITH) + JAVA_STARTWITH.length(), code.indexOf(JAVA_ENDWITH));
        default:
            return code;
        }
    }

    public static boolean isContainContextParam(String code) {
        final ECodeLanguage language = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        switch (language) {
        case PERL:
            return code.contains(PERL_STARTWITH) && code.contains(PERL_ENDWITH);
        case JAVA:
            return code.contains(JAVA_STARTWITH) && code.contains(JAVA_ENDWITH);
        default:
            return false;
        }
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
