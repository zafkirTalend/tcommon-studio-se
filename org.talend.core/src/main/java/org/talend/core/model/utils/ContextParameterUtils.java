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
package org.talend.core.model.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaType;
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

    private static final String JAVA_STARTWITH = "((String)context.getProperty(" + "\"";

    private static final String JAVA_ENDWITH = "\"))";

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

        final String string = JAVA_STARTWITH + parameter.getName() + JAVA_ENDWITH;
        switch (language) {
        case PERL:
            code = PERL_STARTWITH + parameter.getName() + PERL_ENDWITH; //$NON-NLS-1$ //$NON-NLS-2$
            break;
        case JAVA:
            JavaType javaType = ContextParameterJavaTypeManager.getJavaTypeFromId(parameter.getType());
            String typeToGenerate = ContextParameterJavaTypeManager.getTypeToGenerate(parameter.getType(), true);
            if (javaType.isPrimitive()) {
                if (typeToGenerate.compareTo("String") == 0) {
                    code = string;
                } else if (typeToGenerate.compareTo("Integer") == 0) {
                    code = "Integer.parseInt(" + string + ")";
                } else {
                    code = typeToGenerate + ".parse" + typeToGenerate + "(" + string + ")";
                }
            } else if (typeToGenerate.compareTo("java.util.Date") == 0) {

                code = "(" + typeToGenerate + ")" + "(new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\")" + ".parse" + "(" + string
                        + "))";
            } else if (typeToGenerate.compareTo("java.lang.Object") == 0) {
                code = "(" + typeToGenerate + ")" + string;
            } else {
                code = "(" + typeToGenerate + ")" + string;
            }
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

    public static Date getDate(String s) {
        try {
            final Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
            return parse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
                    if (containOldContext(code)) {
                        return parseScriptContextCode(code.replace(getScriptCode(param, language), param.getValue()), context);
                    } else if (containNewContext(code)) {
                        return param.getValue();
                    }
                }
            }
        }
        return code;
    }

    private static String getContextString(String code, ECodeLanguage language) {
        switch (language) {
        case PERL:
            if (containOldContext(code)) {
                return code.substring(code.indexOf(PERL_STARTWITH) + PERL_STARTWITH.length(), code.indexOf(PERL_ENDWITH));
            }
        case JAVA:
            if (containOldContext(code)) {
                return code.substring(code.indexOf(JAVA_STARTWITH) + JAVA_STARTWITH.length(), code.indexOf(JAVA_ENDWITH));
            }
        default:
            return code;
        }
    }

    public static boolean isContainContextParam(String code) {
        return containOldContext(code) || containNewContext(code);
    }

    /**
     * DOC qzhang Comment method "containoldContext".
     * 
     * @param code
     * @return
     */
    private static boolean containOldContext(String code) {
        final ECodeLanguage language = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        switch (language) {
        case PERL:
            return (code.contains(PERL_STARTWITH) && code.contains(PERL_ENDWITH));
        case JAVA:
            return (code.contains(JAVA_STARTWITH.substring(0, JAVA_STARTWITH.length() - 1)) && code.contains(JAVA_ENDWITH));
        default:
            return false;
        }
    }

    /**
     * DOC qzhang Comment method "containoldContext".
     * 
     * @param code
     * @return
     */
    private static boolean containNewContext(String code) {
        final ECodeLanguage language = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        switch (language) {
        case PERL:
            return (!code.startsWith("'") || !code.endsWith("'"));
        case JAVA:
            return (!code.startsWith("\"") || !code.endsWith("\""));
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
    @Deprecated
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
