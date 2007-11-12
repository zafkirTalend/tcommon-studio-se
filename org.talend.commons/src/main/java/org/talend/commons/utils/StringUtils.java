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
package org.talend.commons.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * Utility class for strings. <br/>
 * 
 * $Id$
 * 
 */
public class StringUtils {

    public static String repeat(String str, int repeat) {
        return org.apache.commons.lang.StringUtils.repeat(str, repeat);
    }

    public static String join(Object[] array, String separator) {
        return org.apache.commons.lang.StringUtils.join(array, separator);
    }

    public static String[] split(String string, char separator) {
        return org.apache.commons.lang.StringUtils.split(string, separator);
    }

    public static String replace(String text, String repl, String with) {
        return org.apache.commons.lang.StringUtils.replace(text, repl, with);
    }

    public static List<String> splitAsList(String string, char separator) {
        String[] strings = org.apache.commons.lang.StringUtils.split(string, separator);
        return Arrays.asList(strings);
    }

    public static String capitalize(String str) {
        return org.apache.commons.lang.StringUtils.capitalize(str);
    }

    public static String getMysqlProtectedColumnName(String colname) {
        return "`" + colname + "`";
    }

    /**
     * Extract string between the first delimiter and the second delimiter.
     * 
     * @param text
     * @param delimiter
     * @return
     */
    public static String extractFirstDelimitedString(String text, String delimiter) {

        String returned = "";

        int start = text.indexOf(delimiter, 0);

        if (start != -1) {

            int end = text.indexOf(delimiter, start + 1);

            if (end != -1) {
                returned = text.substring(start + 1, end);
            }
        }

        return returned;
    }

    public static String protectMetachar(String input) {
    	
    	input = replace(input, "\\", "\\\\\\\\") ;
    	input = replace(input, "+", "\\\\+") ;
    	input = replace(input, ".", "\\\\.") ;
    	input = replace(input, "[", "\\\\[") ;
    	input = replace(input, "]", "\\]") ;
    	input = replace(input, "(", "\\\\(") ;
    	input = replace(input, ")", "\\\\)") ;
    	input = replace(input, "^", "\\\\^") ;
    	input = replace(input, "$", "\\\\$") ;

        return input;    	
    }

    public static String removeSpecialCharsForPackage(String input) {
        input = input.replaceAll(" ", "");
        input = input.replaceAll("/", ".");
        input = input.replaceAll("&", "and");
        input = input.replaceAll("<", "lt");
        input = input.replaceAll(">", "gt");
        input = input.replaceAll("'", "apos");
        input = input.replaceAll("\"", "quot");
        return input;
    }

}
