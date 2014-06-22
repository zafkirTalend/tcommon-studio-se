// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;
import org.eclipse.emf.common.util.EList;
import org.talend.core.model.context.UpdateContextVariablesHelper;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.utils.TalendQuoteUtils;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;

/**
 * cli class global comment. Detailled comment
 */
public final class ParameterValueUtil {

    private ParameterValueUtil() {
    }

    @SuppressWarnings("unchecked")
    public static void renameValues(final IElementParameter param, final String oldName, final String newName) {
        if (param == null || oldName == null || newName == null) {
            return;
        }
        boolean flag = true;
        if (param.getFieldType() == EParameterFieldType.MEMO_SQL)
            flag = false;
        if (param.getValue() instanceof String) { // for TEXT / MEMO etc..
            String value = (String) param.getValue();
            if (value.contains(oldName)) {
                // param.setValue(value.replaceAll(oldName, newName));
                String newValue = renameValues(value, oldName, newName, flag);
                if (!value.equals(newValue)) {
                    param.setValue(newValue);
                }
            }
        } else if (param.getValue() instanceof List) { // for TABLE
            List<Map<String, Object>> tableValues = (List<Map<String, Object>>) param.getValue();
            for (Map<String, Object> line : tableValues) {
                for (String key : line.keySet()) {
                    Object cellValue = line.get(key);
                    if (cellValue instanceof String) { // cell is text so
                        // rename data if
                        // needed
                        String value = (String) cellValue;
                        if (value.contains(oldName)) {
                            // line.put(key, value.replaceAll(oldName,
                            // newName));
                            String newValue = renameValues(value, oldName, newName, flag);
                            if (!value.equals(newValue)) {
                                line.put(key, newValue);
                            }
                        }
                    }
                }
            }
        }
    }

    public static String renameValues(final String value, final String oldName, final String newName, boolean flag) {
        if (value == null || oldName == null || newName == null) {
            return value; // keep original value
        }
        PatternCompiler compiler = new Perl5Compiler();
        Perl5Matcher matcher = new Perl5Matcher();
        matcher.setMultiline(true);
        Perl5Substitution substitution = new Perl5Substitution(newName + "$2", Perl5Substitution.INTERPOLATE_ALL); //$NON-NLS-1$

        Pattern pattern;
        try {
            pattern = compiler.compile(getQuotePattern(oldName));
        } catch (MalformedPatternException e) {
            return value; // keep original value
        }

        if (matcher.contains(value, pattern)) {
            // replace
            String returnValue = "";
            if (value.contains(TalendQuoteUtils.getQuoteChar()) && !flag) {
                returnValue = splitQueryData(oldName, newName, value);
            } else {
                returnValue = Util.substitute(matcher, pattern, substitution, value, Util.SUBSTITUTE_ALL);
            }
            return returnValue;

        }
        return value; // keep original value

    }

    // for bug 12594 split "; for bug 29092(it has JUnits)
    public static String splitQueryData(String oldName, String newName, String value) {
        // example:"drop table "+context.oracle_schema+".\"TDI_26803\""
        // >>>>>>>>_△_(const)__ _____△_(varible)_______ __△_(const)___
        String regex = "\"\"|\".*?([^\\\\]\")";
        // obtain all varibles
        String[] split = value.split(regex);
        Map<String, String> replacedStrings = new HashMap<String, String>();
        String returnValue = "";
        // replace the variables & store both value of old and new
        for (String s : split) {
            if (s.contains(oldName)) {
                replacedStrings.put(s, s.replaceAll("\\b" + oldName + "\\b", newName));
            }
        }
        // obtain consts & concat the consts with the variables
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            int x = matcher.start();
            int y = matcher.end();
            int curPos = 0;
            int valueLength = value.length();
            String oldFill = null;
            String newFill = null;
            while (true) {
                if (curPos == valueLength) {
                    break;
                }
                if (curPos < x) {
                    oldFill = value.substring(curPos, x);
                    if ((newFill = replacedStrings.get(oldFill)) != null) {
                        returnValue += newFill;
                    } else {
                        returnValue += oldFill;
                    }
                    curPos = x;
                    continue;
                }
                returnValue += matcher.group();
                curPos = y;
                if (!matcher.find()) {
                    x = valueLength;
                } else {
                    x = matcher.start();
                    y = matcher.end();
                }
            }
        }
        return returnValue;
    }

    public static boolean isUseData(final IElementParameter param, final String name) {
        if (param == null || name == null) {
            return false;
        }
        if (param.getValue() instanceof String) { // for TEXT / MEMO etc..
            String value = (String) param.getValue();
            if (ParameterValueUtil.valueContains(value, name)) {
                return true;
            }
        } else if (param.getValue() instanceof List) { // for TABLE
            List<Map<String, Object>> tableValues = (List<Map<String, Object>>) param.getValue();
            for (Map<String, Object> line : tableValues) {
                for (String key : line.keySet()) {
                    Object cellValue = line.get(key);
                    if (cellValue instanceof String) { // cell is text so
                        // test data
                        if (ParameterValueUtil.valueContains((String) cellValue, name)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isUseData(final ElementParameterType param, final String name) {
        if (param == null || name == null) {
            return false;
        }
        if (param.getField().equals(EParameterFieldType.TABLE.getName())) { // for TABLE
            EList elementValue = param.getElementValue();
            if (elementValue != null) {
                for (ElementValueType valueType : (List<ElementValueType>) elementValue) {
                    if (valueType.getValue() != null) { // cell is text so
                        // test data
                        if (ParameterValueUtil.valueContains((String) valueType.getValue(), name)) {
                            return true;
                        }
                    }
                }
            }
        } else {
            String value = param.getValue();
            if (value != null && ParameterValueUtil.valueContains(value, name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean valueContains(String value, String toTest) {
        if (value.contains(toTest)) {
            Perl5Matcher matcher = new Perl5Matcher();
            Perl5Compiler compiler = new Perl5Compiler();
            Pattern pattern;

            try {
                pattern = compiler.compile(getQuotePattern(toTest));
                if (matcher.contains(value, pattern)) {
                    return true;
                }
            } catch (MalformedPatternException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private static String getQuotePattern(String toTest) {
        final String prefix = "\\b("; //$NON-NLS-1$
        String suffix = ")\\b"; //$NON-NLS-1$
        if (!ContextParameterUtils.isContainContextParam(toTest)) { // context parameter renamed
            suffix = ")(\\b|\\_)"; //$NON-NLS-1$
        }

        return prefix + UpdateContextVariablesHelper.replaceSpecialChar(toTest) + suffix;

    }
}
