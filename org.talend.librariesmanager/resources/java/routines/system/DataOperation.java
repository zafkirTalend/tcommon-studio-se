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
 * $Id: DataOperation.java 下午01:46:17 2007-7-3 +0000 (2007-7-3) yzhang $
 * 
 */
public class DataOperation {

    /**
     * ASCII( ) Converts EBCDIC representation of character string data to the equivalent ASCII character code values.
     * 
     * {talendTypes} int | Integer
     * 
     * {Category} DataOperation
     * 
     * {param} char('a')
     * 
     * {example} ASCII('a')
     * 
     */
    public static int ASCII(char c) {
        return Character.digit(c, 10);
    }

    /**
     * CHAR( ) Converts a numeric value to its ASCII character string equivalent.
     * 
     * {talendTypes} char | Character
     * 
     * {Category} DataOperation
     * 
     * {param} int(1)
     * 
     * {example} CHAR(1)
     * 
     */
    public static char CHAR(int i) {
        return Character.forDigit(i, 10);
    }

    /**
     * DTX( ) Converts a decimal integer into its hexadecimal equivalent.
     * 
     * {talendTypes} Sting
     * 
     * {Category} DataOperation
     * 
     * {param} int(1)
     * 
     * {example} DTX(1)
     * 
     */
    public static String DTX(int i) {
        return Integer.toHexString(i);
    }

    // EBCDIC( ) Converts data from its ASCII representation to the equivalent
    // code value in EBCDIC.
    // 

    /**
     * FIX( ) Rounds an expression to a decimal number having the accuracy specified by the PRECISION statement.
     * 
     * {talendTypes} long | Long
     * 
     * {Category} DataOperation
     * 
     * {param} double(0.0)
     * 
     * {example} FIX(3.14)
     * 
     */
    public static long FIX(double d) {
        return Math.round(d);
    }

    // FMT( ) Converts data from its internal representation to a specified
    // format for output.
    // ICONV( ) Converts data to internal storage format.
    // OCONV( ) Converts data from its internal representation to an external
    // output format.
    // SEQ( ) Converts an ASCII character code value to its corresponding
    // numeric value.
    /**
     * XTD( ) Converts a hexadecimal string into its decimal equivalent.
     * 
     * {talendTypes} int | Integer
     * 
     * {Category} DataOperation
     * 
     * {param} string("0")
     * 
     * {example} XTD("1")
     * 
     */
    public static int XTD(String text) {
        return Integer.valueOf(text, 16);
    }
}
