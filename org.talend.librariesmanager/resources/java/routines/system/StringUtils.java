// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package routines.system;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static final String[] EMPTY_STRING_ARRAY = new String[0];
	
	/**
	 * replace the method : String.split(String regex)
	 * @param str
	 * @param separatorChars
	 * @return
	 */
	public static String[] splitNotRegex(String str,String separatorChars) {
		if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    lastMatch = true;
                    if (sizePlus1++ == -1) {
                        i = len;
                        lastMatch = false;
                    }
                    list.add(str.substring(start, i));
                    match = false;
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    lastMatch = true;
                    if (sizePlus1++ == -1) {
                        i = len;
                        lastMatch = false;
                    }
                    list.add(str.substring(start, i));
                    match = false;
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    lastMatch = true;
                    if (sizePlus1++ == -1) {
                        i = len;
                        lastMatch = false;
                    }
                    list.add(str.substring(start, i));
                    match = false;
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || lastMatch) {
            list.add(str.substring(start, i));
        }
        
        // Construct result
        int resultSize = list.size();
        while (resultSize > 0 && list.get(resultSize-1).equals("")) {
        	resultSize--;
        }
        String[] result = new String[resultSize];
        return list.subList(0, resultSize).toArray(result);
	}

    public static String[] split(String str, String separator) {
        return str.split(separator);
    }

    public static String deleteChar(String s, char delChar) {
        int len = s.length();
        char[] val = s.toCharArray();
        char buf[] = new char[len];
        int m = 0;
        for (int k = 0; k < len; k++) {
            char c = val[k];
            if (c != delChar) {
                buf[m] = c;
                m++;
            }
        }

        return new String(buf, 0, m);
    }

    public static String list(String[] stringArray) {
        return list(stringArray, null, null, null, null);
    }

    public static String list(String[] stringArray, String separator) {
        return list(stringArray, separator, null, null, null);
    }

    public static String list(String[] stringArray, String separator, String startEnclosure, String endEnclosure) {
        return list(stringArray, separator, startEnclosure, endEnclosure, null);
    }

    public static String list(String[] stringArray, String separator, String escaper) {
        return list(stringArray, separator, null, null, escaper);
    }

    public static String list(String[] stringArray, String separator, String startEnclosure, String endEnclosure, String escaper) {
        if (separator == null) {
            separator = ""; //$NON-NLS-1$
        } else {
            separator = separator.trim();
        }
        if (startEnclosure == null) {
            startEnclosure = ""; //$NON-NLS-1$
        } else {
            startEnclosure = startEnclosure.trim();
        }
        if (endEnclosure == null) {
            endEnclosure = ""; //$NON-NLS-1$
        } else {
            endEnclosure = endEnclosure.trim();
        }
        if (escaper == null) {
            escaper = ""; //$NON-NLS-1$
        } else {
            escaper = escaper.trim();
        }
        StringBuilder result = new StringBuilder();

        result.append(startEnclosure);

        boolean flag = false;
        for (String item : stringArray) {
            item = item.trim();
            if (flag) {
                result.append(separator);
            } else {
                flag = true;
            }
            result.append(escaper);
            result.append(item);
            result.append(escaper);
        }
        result.append(endEnclosure);

        return result.toString();
    }

    /**
     * to discuss the case: src == null || regex == null || replacement == null
     * 
     */
    public static String replaceAll(String src, String regex, String replacement) {

        // case 1:
        if (regex == null) {
            if (src == null) {
                return replacement; // regex == null && src == null
            } else {
                return src; // regex == null && src != null
            }
        } else {
            // case 2:
            if (src == null) {
                return null; // regex != null && src == null
            } else {
                // case 3:
                if (replacement == null) {
                    if (src.matches(regex)) {
                        // regex != null && src != null && replacement != null, and match the whole src
                        return replacement;
                    } else {
                        return src; // can't match the whole src
                    }

                } else {
                    // regex != null && src != null && replacement != null
                    return src.replaceAll(regex, replacement);

                }
            }
        }
    }

    /**
     * ignore regex
     * 
     */
    public static String replaceAllStrictly(String src, String search, String replacement, boolean entirelyMatch,
            boolean caseSensitive) {
        // case 1:
        if (search == null) {
            if (src == null) {
                return replacement; // regex == null && src == null
            } else {
                return src; // regex == null && src != null
            }
        } else {
            // case 2:
            if (src == null) {
                return null; // regex != null && src == null
            } else {
                // case 3:
                if (replacement == null) {
                    if (src.equals(search)) {
                        // regex != null && src != null && replacement != null, and match the whole src
                        return replacement;
                    } else {
                        return src; // can't match the whole src
                    }

                } else {
                    // regex != null && src != null && replacement != null
                    if (entirelyMatch) {
                        String upperSrc = caseSensitive ? src : src.toUpperCase();
                        String upperSearch = caseSensitive ? search : search.toUpperCase();
                        return upperSrc.equals(upperSearch) ? replacement : src;
                    } else {
                        int flag = caseSensitive ? Pattern.LITERAL : Pattern.LITERAL | Pattern.CASE_INSENSITIVE;
                        return Pattern.compile(search, flag).matcher(src).replaceAll(Matcher.quoteReplacement(replacement));
                    }
                }
            }
        }
    }

    /**
     * make \n to \\n. It will process these chars: \n, \r, \t, \f, \\, \", \', \b
     * 
     */
    public static String escapeChar(String s) {
        if (s == null) {
            return null;
        }

        int length = s.length();
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {

            char c = s.charAt(i);

            switch (c) {
            case '\n':
                sb.append("\\").append('n'); //$NON-NLS-1$
                break;
            case '\r':
                sb.append("\\").append('r'); //$NON-NLS-1$
                break;
            case '\t':
                sb.append("\\").append('t'); //$NON-NLS-1$
                break;
            case '\f':
                sb.append("\\").append('f'); //$NON-NLS-1$
                break;
            case '\b':
                sb.append("\\").append('b'); //$NON-NLS-1$
                break;
            case '\"':
                sb.append("\\").append('\"'); //$NON-NLS-1$
                break;
            case '\'':
                sb.append("\\").append('\''); //$NON-NLS-1$
                break;
            default:
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
