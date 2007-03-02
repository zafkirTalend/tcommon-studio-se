// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.ui.swt.formtools;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * PerlDataTypeHelper is used to determine the Perl Type or the Talend Type of a string. TODO : This class don't determine
 * the type DATE.
 * 
 * $Id$
 * 
 */
public final class PerlDataTypeHelper {

    /**
     * Default Constructor. Must not be used.
     */
    private PerlDataTypeHelper() {
    }

    /**
     * Constant to define the min and the max of type Int Float Double.
     */
    private static final int INT_MIN = -2147483648;

    private static final int INT_MAX = 2147483647;

    private static final float FLOAT_MIN = -1.4E-45f;

    private static final float FLOAT_MAX = 3.4E38f;

    private static final double DOUBLE_MIN = 4.9E-324d;

    private static final double DOUBLE_MAX = 1.7E308d;

    /**
     * getTypeOfValue return STRING, CHAR, NUMBER, LONG, FLOAT, DOUBLE.
     * 
     * @param value
     * @return string or null if the value.equals("")
     */
    public static String getTalendTypeOfValue(final String value) {
        String perlType = getPerlTypeOfValue(value);
        if (perlType != null) {
            if (perlType.equals("Integer")) {
                return "NUMBER";
            } else if (perlType.equals("Character")) {
                return "CHAR";
            }
        } else {
            return "String";
        }
        return perlType.toUpperCase();
    }

    /**
     * getTypeOfValue return String, Character, Integer, Long, Float, Double.
     * 
     * @param value
     * @return string or null if the value.equals("")
     */
    public static String getPerlTypeOfValue(final String value) {

        // empty value => type is undef
        if (value.equals("")) {
            return null;
        }

        // only 1 char => type is char or int
        if (value.length() == 1) {
            try {
                // * int Entier -2 147 483 648 � 2 147 483 647
                Integer nbr = Integer.parseInt(value);
                if ((nbr >= INT_MIN) && (nbr <= INT_MAX)) {
                    return "Integer";
                }
            } catch (Exception e) {
                //
            }
            return "Character";
        }

        // SPECIFIQUE USE CASE (integer begin by 0; multiple dot use ; use of char E in scientific notation)
        //
        if (!isNumber(value)) {
            // Warning : 1.7E38 is interpreted like a float !
            return "String";
        }
        //
        // first char is 0 and no dot just after => force type String needed
        if (value.substring(0, 1).equals("0") && (!value.substring(1, 2).equals("."))) {
            return "String";
        }
        //
        // content more one dot => String
        if (value.contains(".")) {
            if (value.substring(value.indexOf(".") + 1, value.length()).contains(".")) {
                return "String";
            }
        }
        // END SPECIFIQUE USE CASE

        // content only a dot => float or double
        if (value.contains(".")) {
            try {
                Float nbrFloat = Float.parseFloat(value);
                if ((!nbrFloat.toString().equals("Infinity")) && (!nbrFloat.toString().equals("-Infinity"))) {
                    if ((nbrFloat >= FLOAT_MIN) && (nbrFloat <= FLOAT_MAX)) {
                        return "Float";
                    }
                }

                try {
                    // * double flottant double 4.9*10 -324 � 1.7*10 308
                    Double nbrDouble = Double.parseDouble(value);
                    if ((!nbrDouble.toString().equals("Infinity")) && (!nbrDouble.toString().equals("-Infinity"))) {
                        if ((nbrDouble >= DOUBLE_MIN) && (nbrDouble <= DOUBLE_MAX)) {
                            return "Double";
                        }
                    }
                } catch (Exception e) {
                    // 
                }

            } catch (Exception e) {
                // 
            }
        }

        // is not a char, not a float : parseMethod is usable
        try {
            Integer.parseInt(value);
            return "Integer";
        } catch (Exception e) {
            // 
        }
        try {
            Long.parseLong(value);
            return "Long";
        } catch (Exception e) {
            // 
        }
        try {
            Double.parseDouble(value);
            return "Double";
        } catch (Exception e) {
            // 
        }

        // by default the type is string
        return "String";
    }

    /**
     * getGlobalType compare two type and determine the common Type.
     * 
     * @param type1
     * @param type2
     * @return
     */
    public static String getCommonType(final String type1, final String type2) {
        if ((type1 == "String") || (type2 == "String")) {
            return "String";
        }
        if ((type1 == "Double") || (type2 == "Double")) {
            return "Double";
        }
        if ((type1 == "Float") || (type2 == "Float")) {
            return "Float";
        }
        if ((type1 == "Long") || (type2 == "Long")) {
            return "Long";
        }
        return "String";
    }

    // Function to test whether the string is valid number or not
    public static boolean isNumber(String value) {
        Perl5Compiler compiler = new Perl5Compiler();
        Perl5Matcher matcher = new Perl5Matcher();
        Pattern pattern = null;

        String strValidRealPattern = "^([-]|[.]|[-.]|[0-9])[0-9]*[.]*[0-9]+$";
        String strValidIntegerPattern = "^([-]|[0-9])[0-9]*$";
        String regex = "(" + strValidRealPattern + ")|(" + strValidIntegerPattern + ")";

        try {
            pattern = compiler.compile(regex);
            if (matcher.contains(value, pattern)) {
                return matcher.matches(value, pattern);
            }
        } catch (MalformedPatternException e) {
            // do nothing
        }
        return false;
    }

}
