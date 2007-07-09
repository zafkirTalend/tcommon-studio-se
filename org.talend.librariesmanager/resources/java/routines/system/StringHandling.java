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
package routines;

/**
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: StringHandling.java 上午10:32:32 2007-7-2 +0000 (2007-7-2) yzhang $
 * 
 */
public class StringHandling {

    /**
     * Determines whether the expression is an alphabetic or nonalphabetic string.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} boolean | Boolean
     * 
     * {param} string("abcdefg") input: String need to be known whether is an alphabetic or nonalphabetic string.
     * 
     * {example} ALPHA("abcdefg") # true
     */
    public static boolean ALPHA(String input) {
        char[] val = input.toCharArray();

        for (int i = 0; i < val.length - 1; i++) {
            if (val[i] > val[i + 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Substitutes an element of a string with a replacement element.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hello world!") oldStr: The whole string.
     * 
     * {param} string("world") newStr: Regx.
     * 
     * {param} string("guy") replacement: Replacement.
     * 
     * {example} CHANGE("hello world!","world","guy") # hello world
     */
    public static String CHANGE(String oldStr, String newStr, String replacement) {
        String newString = null;
        newString = oldStr.replaceAll(newStr, replacement);
        return newString;
    }

    /**
     * Evaluates the number of times a substring is repeated in a string.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} int | Integer
     * 
     * {param} string("hello world!") string: The whole string.
     * 
     * {param} string("world") subString: subString.
     * 
     * {example} COUNT("hello world!","world") # 1
     */
    public static int COUNT(String string, String subString) {
        int counter = 0;
        int i = -1;
        while ((i = string.indexOf(subString, i + 1)) != -1) {
            counter++;
        }
        return counter;
    }

    /**
     * Converts all uppercase letters in an expression to lowercase.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("Hello") string: String
     * 
     * {example} DOWNCASE("Hello") # hello
     */
    public static String DOWNCASE(String string) {
        return string.toLowerCase();
    }

    /**
     * Converts all lowercase letters in an expression to uppercase.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("Hello") string: String
     * 
     * {example} DOWNCASE("Hello") # HELLOW
     */
    public static String UPCASE(String string) {
        return string.toUpperCase();
    }

    /**
     * Encloses an expression in double quotation marks.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("Hello") string: String
     * 
     * {example} DQUOTE("hello") # "hello"
     */
    public static String DQUOTE(String string) {
        return "\"" + string + "\"";
    }

    /**
     * Substitutes an element of a string with a replacement element.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hello world!") oldStr: The whole string.
     * 
     * {param} string("world") newStr: Regx.
     * 
     * {param} string("guy") replacement: Replacement.
     * 
     * {example} EREPLACE("hello world!","world","guy") # hello world
     */
    public static String EREPLACE(String oldStr, String newStr, String replacement) {
        String newString = null;
        newString = oldStr.replaceAll(newStr, replacement);
        return newString;
    }

    /**
     * Returns the starting column position of a specified occurrence of a particular substring within a string
     * expression.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} int | Integer
     * 
     * {param} string("hello world!") string: string.
     * 
     * {param} string("hello") element: element
     * 
     * {example} INDEX("hello world!","hello") # 0
     */
    public static int INDEX(String string, String element) {
        return string.indexOf(element);
    }

    /**
     * Specifies a substring consisting of the first n characters of a string.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hello world!") string: String.
     * 
     * {param} int(5) index : index
     * 
     * {example} LEFT("hello world!",5) # hello
     */
    public static String LEFT(String string, int index) {
        return string.substring(0, index);
    }

    /**
     * Specifies a substring consisting of the last n characters of a string.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hello world!") string: String
     * 
     * {param} int(6) index : Index
     * 
     * {example} RIGHT("hello world!",6) # world!
     */
    public static String RIGHT(String string, int index) {
        return string.substring(string.length() - index);
    }

    /**
     * Calculates the length of a string.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} int | Integer
     * 
     * {param} string("hello world!") string:
     * 
     * {example} LEN("hello world!") # 12
     */
    public static int LEN(String string) {
        return string.length();
    }

    /**
     * Converts system delimiters that appear in expressions to the next lower-level delimiter.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("HELLO world!") string: String.
     * 
     * {example} LOWER("HELLO world!") # hello world!
     */
    public static String LOWER(String string) {
        return string.toLowerCase();
    }

    /**
     * Builds a string by concatenating the elements of an array.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string(new String[]{"a","b","c"}) string: string.
     * 
     * {example} MATBUILD(new String[]{"a","b","c"}new String[]{"a","b","c"}) # abc
     */
    public static String MATBUILD(String[] stringArray) {
        StringBuffer buffer = new StringBuffer();
        for (String string : stringArray) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    /**
     * Generates a string consisting of a specified number of blank spaces.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hellow world!") string: string.
     * 
     * {param} int(2) i: amount of blank space need to generate.
     * 
     * {example} SPACE("hellow world!",2) # hello world!
     */
    public static String SPACE(String string, int i) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(string);
        for (int j = 0; j < i; j++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }

    /**
     * Encloses an expression in single quotation marks.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hellow world!") string:
     * 
     * {example} SQUOTE("hellow world!") # 'hello world!'
     */
    public static String SQUOTE(String string) {
        return "'" + string + "'";
    }

    /**
     * Generates a particular character string a specified number of times.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string('a') string: character
     * 
     * {param} int(6) int: times
     * 
     * {example} SPACE("hellow world!",2) # hello world!
     */
    public static String STR(char letter, int i) {
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < i; j++) {
            buffer.append(letter);
        }
        return buffer.toString();
    }

    /**
     * Deletes extra blank spaces and tabs from a character string.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string(" hellow world! ") string: string.
     * 
     * {example} TRIM(" hellow world! ") # hello world!
     */
    public static String TRIM(String string) {
        return string.trim();
    }

    /**
     * Deletes all blank spaces and tabs after the last nonblank character in an expression.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string("hellow world! ") string: string.
     * 
     * {example} BTRIM("hellow world! ") # helloworld!
     */
    public static String BTRIM(String string) {
        char[] val = string.toCharArray();
        int len = val.length;
        while (len > 0 && val[len - 1] <= ' ') {
            len--;
        }
        return string.substring(0, len);

    }

    /**
     * Deletes all blank spaces and tabs up to the first nonblank character in an expression.
     * 
     * {Category} StringHandling
     * 
     * {talendTypes} String
     * 
     * {param} string(" hellow world!") string: string.
     * 
     * {example} FTRIM(" hellow world!") # hello world!
     */
    public static String FTRIM(String string) {
        char[] val = string.toCharArray();
        int st = 0;
        int len = val.length;
        while ((st < len) && (val[st] <= ' ')) {
            st++;
        }
        return string.substring(st);

    }

}
