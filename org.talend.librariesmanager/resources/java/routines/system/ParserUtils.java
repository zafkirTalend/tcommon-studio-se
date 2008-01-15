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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParserUtils {

    public static List parseTo_List(String s) {
        if (s != null) {
            List list = new ArrayList();
            list.add(s);
            return list;
        }
        return null;
    }

    public static char parseTo_Character(String s) {
        return (s != null) ? s.charAt(0) : null;
    }

    public static char parseTo_char(String s) {
        return (s != null) ? s.charAt(0) : null;
    }

    public static byte parseTo_Byte(String s) {
        return Byte.decode(s).byteValue();
    }

    public static byte parseTo_byte(String s) {
        return Byte.decode(s).byteValue();
    }

    public static Double parseTo_Double(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static double parseTo_double(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static float parseTo_float(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static Float parseTo_Float(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static int parseTo_int(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static Integer parseTo_Integer(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static short parseTo_short(String s) {
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static Short parseTo_Short(String s) {
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static long parseTo_long(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static Long parseTo_Long(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("NumberFormatException " + nfe.getMessage());
        }
    }

    public static Boolean parseTo_Boolean(String s) {
        if (s.equals("1")) {
            return Boolean.parseBoolean("true");
        }
        return Boolean.parseBoolean(s);
    }

    public static boolean parseTo_boolean(String s) {
        if (s.equals("1")) {
            return Boolean.parseBoolean("true");
        }
        return Boolean.parseBoolean(s);
    }

    public static String parseTo_String(String s) {
        return s;
    }

    public static BigDecimal parseTo_BigDecimal(String s) {
        return new BigDecimal(s);
    }

    public static java.util.Date parseTo_Date(String s, String pattern) {
        // check the parameter for supporting " ","2007-09-13"," 2007-09-13 "
        if (s != null) {
            s = s.trim();
        }
        if (s == null || s.length() == 0) {
            return null;
        }
        java.util.Date date = null;
        try {
            date = FastDateParser.getInstance(pattern).parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            System.err.println("Current string to parse '" + s + "'");
        }
        return date;
    }

    public static java.util.Date parseTo_Date(java.util.Date date, String pattern) {
        // java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(pattern);
        // java.util.Date date = null;
        // try {
        // date = simpleDateFormat.parse(date);
        // } catch (java.text.ParseException e) {
        // e.printStackTrace();
        // System.err.println("Current string to parse '" + s + "'");
        // }
        return date;
    }

    /**
     * in order to transform the string "1.234.567,89" to number 1234567.89
     */
    public static String parseTo_Number(String s, char thousandsSeparator, char decimalSeparator) {
        if (s == null) {
            return null;
        }
        String temp = StringUtils.deleteChar(s, thousandsSeparator);
        String result = temp.replace(decimalSeparator, '.');
        return result;
    }
}
