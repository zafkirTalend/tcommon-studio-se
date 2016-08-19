// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
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

/**
 * Utility class for removal of accents in texts. This solution handles only the accented letters in latin-1 suppliment charset,
 * but is much faster than StringUtils.stripAccents(input) from Apache Commons Lang package.
 */
public final class AsciiUtils {

    /**
     * List of characters to transform. Correspondence should be maintained with {@link AsciiUtils#PLAIN_ASCII} field.
     * (code from http://www.rgagnon.com/javadetails/java-0456.html)
     */
    static final String UNICODE = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9" // grave
            + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" // acute
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB" // circumflex
            + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1" // tilde
            + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u00FF" // diaeresis
            + "\u00C5\u00E5" // ring
            + "\u00C7\u00E7" // cedilla
    ;

    /**
     * List of replacement characters. Correspondence should be maintained with {@link AsciiUtils#UNICODE} field.
     */
    static final String PLAIN_ASCII = "AaEeIiOoUu" // grave
            + "AaEeIiOoUuYy" // acute
            + "AaEeIiOoUu" // circumflex
            + "AaOoNn" // tilde
            + "AaEeIiOoUuy" // diaeresis
            + "Aa" // ring
            + "Cc" // cedilla
    ;

    // private constructor, can't be instantiated!
    private AsciiUtils() {
    }

    /**
     * Removes accents from a string and replace with ASCII equivalent.
     * 
     * @param s the input string
     * @return the output string (new string) in which accents are removed.
     * 
     * @deprecated use {@link AsciiUtils.removeDiacriticalMark()} instead for better performance
     */
    @Deprecated
    public static String unaccent(final String s) {
        return replaceCharacters(s, UNICODE, PLAIN_ASCII);
    }

    /**
     * Replace characters from a string by other characters.
     * 
     * @param s the string to convert
     * @param charsToReplace the list of characters to replace
     * @param replacementChars the replacement characters
     * @return the new string
     */
    public static String replaceCharacters(final String s, final String charsToReplace, final String replacementChars) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = charsToReplace.indexOf(c);
            if (pos > -1) {
                sb.append(replacementChars.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Removes diacritical mark from a character.
     * 
     * @param ch a character
     * @return the same input character without the diacritical mark if any.
     */
    public static char removeDiacriticalMark(char c) {

        if (c < 192)
            return c;
        if (c >= 192 && c <= 197)
            return 'A';
        if (c == 199)
            return 'C';
        if (c >= 200 && c <= 203)
            return 'E';
        if (c >= 204 && c <= 207)
            return 'I';
        if (c == 209)
            return 'N';
        if (c >= 210 && c <= 214)
            return 'O';
        if (c >= 217 && c <= 220)
            return 'U';
        if (c == 221)
            return 'Y';
        if (c >= 224 && c <= 229)
            return 'a';
        if (c == 231)
            return 'c';
        if (c >= 232 && c <= 235)
            return 'e';
        if (c >= 236 && c <= 239)
            return 'i';
        if (c == 241)
            return 'n';
        if (c >= 242 && c <= 246)
            return 'o';
        if (c >= 249 && c <= 252)
            return 'u';
        if (c == 253 || c == 255)
            return 'y';

        return c;
    }

    /**
     * Removes diacritical marks from a string.
     * 
     * @param st a string
     * @return a new string without the diacritical mark if any.
     */
    public static String removeDiacriticalMarks(String st) {
        if (st == null) {
            return null;
        }

        final int len = st.length();
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append(removeDiacriticalMark(st.charAt(i)));
        }
        return sb.toString();
    }

}
