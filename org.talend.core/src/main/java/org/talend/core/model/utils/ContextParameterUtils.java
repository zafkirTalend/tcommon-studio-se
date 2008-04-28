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
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;

/**
 * Utilities to work with IContextParamet objects. <br/>
 * 
 * $Id$
 * 
 */
public final class ContextParameterUtils {

    private static final String JAVA_NEW_CONTEXT_PREFIX = "context."; //$NON-NLS-1$

    private static final String PERL_STARTWITH = "$_context{"; //$NON-NLS-1$

    private static final String PERL_ENDWITH = "}"; //$NON-NLS-1$

    private static final String JAVA_STARTWITH = "((String)context.getProperty(\""; //$NON-NLS-1$

    private static final String JAVA_ENDWITH = "\"))"; //$NON-NLS-1$

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
        if (parameter == null || language == null) {
            return null;
        }
        String code = getScriptCode(parameter.getName(), parameter.getType(), language);
        if (code == null) {
            return parameter.getName();
        }
        return code;
    }

    public static String getScriptCode(ContextParameterType parameter, ECodeLanguage language) {
        if (parameter == null || language == null) {
            return null;
        }
        String code = getScriptCode(parameter.getName(), parameter.getType(), language);
        if (code == null) {
            return parameter.getName();
        }
        return code;
    }

    private static String getScriptCode(final String name, final String type, ECodeLanguage language) {
        if (name == null || type == null) {
            return null;
        }
        String code;

        final String string = JAVA_STARTWITH + name + JAVA_ENDWITH;
        switch (language) {
        case PERL:
            code = PERL_STARTWITH + name + PERL_ENDWITH; //$NON-NLS-1$ //$NON-NLS-2$
            break;
        case JAVA:
            JavaType javaType = ContextParameterJavaTypeManager.getJavaTypeFromId(type);
            String typeToGenerate = ContextParameterJavaTypeManager.getTypeToGenerate(type, true);
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
            code = name;
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
        // // return code.replace(getScriptCode(param, language),
        // param.getValue());
        // return parseScriptContextCode(code.replace(getScriptCode(param,
        // language), param.getValue()),
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
        if (code == null) {
            return null;
        }
        final ECodeLanguage language = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        if (!isContainContextParam(code)) {
            return code;
        } else {
            String paraName = getContextString(code, language);
            IContextParameter param = context.getContextParameter(paraName);
            if (param != null) {
                return parseScriptContextCode(param.getValue(), context);// Multi-layer
                // context
                // refrence
            } else {
                return code;
            }

        }
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
            } else if (containNewContext(code)) {
                return trimContextPrefix(code);
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
            return (!code.startsWith("\"") || !code.endsWith("\"")) || containContextPrefix(code);
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

    private static boolean containContextPrefix(String code) {
        if (code == null)
            return false;
        return code.startsWith(JAVA_NEW_CONTEXT_PREFIX);
    }

    /**
     * 
     * DOC yexiaowei Comment method "trimContextPrefix".
     * 
     * @param code
     * @return
     */
    public static String trimContextPrefix(String code) {
        if (containContextPrefix(code))
            return code.substring(JAVA_NEW_CONTEXT_PREFIX.length());
        else
            return code;
    }

    /**
     * 
     * ggu Comment method "getNewScriptCode".
     * 
     * example: context.var1 for java, $_context{var1} for perl.
     */
    public static String getNewScriptCode(final String name, ECodeLanguage language) {
        if (name == null) {
            return null;
        }
        String code = null;

        switch (language) {
        case PERL:
            code = PERL_STARTWITH + name + PERL_ENDWITH;
            break;
        case JAVA:
            code = JAVA_NEW_CONTEXT_PREFIX + name;
            break;
        default:
            code = name;
        }
        return code;
    }

    /**
     * 
     * ggu Comment method "getVariableFromCode".
     * 
     * only for new script code. and if there is no variable in code, return null.
     */
    public static String getVariableFromCode(String code) {
        if (code == null) {
            return null;
        }
        if (isContainContextParam(code)) {
            String pattern = null;
            String varPattern = "(.+?)"; //$NON-NLS-1$
            switch (LanguageManager.getCurrentLanguage()) {
            case JAVA:
                String wordPattern = "\\b"; //$NON-NLS-1$
                pattern = wordPattern + replaceCharForRegex(JAVA_NEW_CONTEXT_PREFIX) + varPattern + wordPattern;
                break;
            case PERL:
            default:
                pattern = replaceCharForRegex(PERL_STARTWITH) + varPattern + replaceCharForRegex(PERL_ENDWITH);
            }
            if (pattern != null) {
                Pattern regex = Pattern.compile(pattern, Pattern.CANON_EQ);
                Matcher regexMatcher = regex.matcher(code);
                if (regexMatcher.find()) {
                    try {
                        String var = regexMatcher.group(1);
                        if (var != null) {
                            return var;
                        }
                    } catch (RuntimeException re) {
                        // not match
                    }
                }
            }
        }
        return null;
    }

    private static String replaceCharForRegex(String pattern) {
        if (pattern == null) {
            return null;
        }
        pattern = pattern.replaceAll("\\(", "\\\\("); //$NON-NLS-1$ //$NON-NLS-2$
        pattern = pattern.replaceAll("\\)", "\\\\)"); //$NON-NLS-1$ //$NON-NLS-2$
        // for java
        pattern = pattern.replaceAll("\\.", "\\\\."); //$NON-NLS-1$ //$NON-NLS-2$
        // for perl
        pattern = pattern.replaceAll("\\{", "\\\\{"); //$NON-NLS-1$ //$NON-NLS-2$
        pattern = pattern.replaceAll("\\}", "\\\\}"); //$NON-NLS-1$ //$NON-NLS-2$
        // error??
        // pattern = pattern.replaceAll("\\$", "\\\\$"); //$NON-NLS-1$ //$NON-NLS-2$
        int index = pattern.indexOf("$"); //$NON-NLS-1$
        if (index > -1) { // found
            String str1 = pattern.substring(0, index);
            String str2 = pattern.substring(index + 1);
            pattern = str1 + "\\$" + str2; //$NON-NLS-1$

        }
        return pattern;
    }

}
