// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
 * @author scorreia
 * 
 * code from http://www.rgagnon.com/javadetails/java-0456.html
 */
public final class AsciiUtils {

    /**
     * List of characters to transform. Correspondance should be maintained with {@link AsciiUtils#PLAIN_ASCII} field.
     */
    public static final String UNICODE = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
            + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
            + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" + "\u00C5\u00E5"
            + "\u00C7\u00E7";

    /**
     * List of replacement characters. Correspondance should be maintained with {@link AsciiUtils#UNICODE} field.
     */
    public static final String PLAIN_ASCII = "AaEeIiOoUu" // grave
            + "AaEeIiOoUuYy" // acute
            + "AaEeIiOoUuYy" // circumflex
            + "AaEeIiOoUuYy" // tilde
            + "AaEeIiOoUuYy" // umlaut
            + "Aa" // ring
            + "Cc" // cedilla
    ;

    // private constructor, can't be instanciated!
    private AsciiUtils() {
    }

    /**
     * Removes accentued from a string and replace with ascii equivalent.
     * 
     * @param s the input string
     * @return the output string (new string) in which accents are removed.
     */
    public static String unaccent(final String s) {
        return replaceCharacters(s, UNICODE, PLAIN_ASCII);
    }

    /**
     * Method "replaceCharacters".
     * 
     * @param s the string to convert
     * @param charsToRemove the list of characters to remove
     * @param replacementChars the replacement characters
     * @return the new string
     */
    public static String replaceCharacters(final String s, final String charsToRemove, final String replacementChars) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = charsToRemove.indexOf(c);
            if (pos > -1) {
                sb.append(replacementChars.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(UNICODE);
        System.out.println(AsciiUtils.unaccent(UNICODE));

        String remove = "&@:;%^~£$€'\"\\/ ";
        String replacement = "EACSPHTLDEQDBS_";

        System.out.println("'" + remove + "'");
        System.out.println("'" + AsciiUtils.replaceCharacters(remove, remove, replacement) + "'");

    }
}
