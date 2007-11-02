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
package routines.system;

import java.util.List;
import java.util.ArrayList;

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
}
