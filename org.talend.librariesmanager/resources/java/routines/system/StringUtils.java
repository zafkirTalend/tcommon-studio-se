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
package routines.system;

public class StringUtils {

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
            separator = "";
        } else {
            separator = separator.trim();
        }
        if (startEnclosure == null) {
            startEnclosure = "";
        } else {
            startEnclosure = startEnclosure.trim();
        }
        if (endEnclosure == null) {
            endEnclosure = "";
        } else {
            endEnclosure = endEnclosure.trim();
        }
        if (escaper == null) {
            escaper = "";
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
}
