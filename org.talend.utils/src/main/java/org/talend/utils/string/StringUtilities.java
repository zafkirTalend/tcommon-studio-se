// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.talend.utils.sugars.ReturnCode;

/**
 * @author scorreia. A String utility class.
 */
public final class StringUtilities {

    private StringUtilities() {
    }

    /**
     * Method "tokenize".
     * 
     * @param input the string to split
     * @param delimiters the delimiter(s) of the fields
     * @see StringTokenizer
     * @return a list of Strings or an empty array when a null argument is given or when there is no string to split
     */
    public static List<String> tokenize(String input, String delimiters) {
        List<String> stringArray = new ArrayList<String>();

        if (input == null) {
            return stringArray;
        }
        if (delimiters == null) {
            return stringArray;
        }

        StringTokenizer t = new StringTokenizer(input, delimiters);

        while (t.hasMoreTokens()) {
            stringArray.add(t.nextToken());
        }
        return stringArray;
    } // eom tokenize

    /**
     * Method "checkBalancedParenthesis".
     * 
     * @param input the string to check
     * @param openingBlock can be a left parenthesis,...
     * @param closingBlock can be a right parenthesis...
     * @return true when the parenthesis are well balanced.
     */
    public static ReturnCode checkBalancedParenthesis(String input, char openingBlock, char closingBlock) {
        int level = 0;
        int i;
        for (i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == openingBlock) {
                level++;
            } else if (currentChar == closingBlock) {
                level--;
            }
            if (level < 0) {
                return new ReturnCode("too many " + closingBlock + " at position " + i, false); //$NON-NLS-1$//$NON-NLS-2$
            }
        }
        if (level < 0) {
            return new ReturnCode("too many " + closingBlock + " at position " + i, false); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (level > 0) {
            return new ReturnCode("too many " + openingBlock + " at position " + i, false); //$NON-NLS-1$//$NON-NLS-2$
        }
        return new ReturnCode();
    }

    /**
     * DOC xqliu Comment method "getRandomString".
     * 
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789"; //$NON-NLS-1$
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);// 0~61
            sf.append(str.charAt(number));
        }
        return sf.toString();
    }

    /**
     * join the string array to a single string, use escapeCharacter to escape the separator. MUST call
     * {@link #split(String, String, String)} to split the joined string. (if the string end with escapeCharacter, there
     * will join to the next column!!!)
     * 
     * @param array
     * @param separator recommend to use |
     * @param escapeCharacter recommend to use \
     * @return
     */
    public static String join(String[] array, String separator, String escapeCharacter) {
        String doubleEscapeCharacter = escapeCharacter + escapeCharacter;
        String escapeCharacterSeparator = escapeCharacter + separator;
        StringBuilder sr = new StringBuilder();
        for (String str : array) {
            String temp = StringUtils.replace(str, escapeCharacter, doubleEscapeCharacter);
            temp = StringUtils.replace(temp, separator, escapeCharacterSeparator);
            sr.append(temp + separator);
        }
        return StringUtils.removeEnd(sr.toString(), separator);
    }

    /**
     * split the string into a string array, use escapeCharacter to escape the separator. the string MUST be generated
     * by {@link #join(String[], String, String)}.(if the string end with escapeCharacter, there will join to the next
     * column!!!)
     * 
     * @param string
     * @param separator recommend to use |
     * @param escapeCharacter recommend to use \
     * @return
     */
    public static String[] split(String string, String separator, String escapeCharacter) {
        String doubleEscapeCharacter = escapeCharacter + escapeCharacter;
        String escapeCharacterSeparator = escapeCharacter + separator;
        String regex = "(?<!" + Pattern.quote(escapeCharacter) + ")" + Pattern.quote(separator); //$NON-NLS-1$ //$NON-NLS-2$
        ArrayList<String> strs = new ArrayList<String>();
        for (String s : string.split(regex)) {
            String temp = StringUtils.replace(s, escapeCharacterSeparator, separator);
            temp = StringUtils.replace(temp, doubleEscapeCharacter, escapeCharacter);
            strs.add(temp);
        }
        return strs.toArray(new String[strs.size()]);
    }

    public static final String ESCAPE_CHARACTER = "\\"; //$NON-NLS-1$
}
