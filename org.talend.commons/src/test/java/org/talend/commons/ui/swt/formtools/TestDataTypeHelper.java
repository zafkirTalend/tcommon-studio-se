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

/**
 * DOC ocarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class TestDataTypeHelper {

    /**
     * Default Constructor. Must not be used.
     */
    private TestDataTypeHelper() {
    }

    /**
     * DOC ocarbone Comment method "main".
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("empty => " + DataTypeHelper.getTalendTypeOfValue(""));
        System.out.println("1 => " + DataTypeHelper.getTalendTypeOfValue("1"));
        System.out.println("A => " + DataTypeHelper.getTalendTypeOfValue("A"));
        System.out.println("1E4 => " + DataTypeHelper.getTalendTypeOfValue("1E4"));
        System.out.println("-2147483646 => " + DataTypeHelper.getTalendTypeOfValue("-2147483646"));
        System.out.println("2147483647 => " + DataTypeHelper.getTalendTypeOfValue("2147483647"));
        System.out.println("-2147483649 => " + DataTypeHelper.getTalendTypeOfValue("-2147483649"));
        System.out.println("9223372036854775807 => " + DataTypeHelper.getTalendTypeOfValue("9223372036854775807"));
        System.out.println("-9223372036854775809 => " + DataTypeHelper.getTalendTypeOfValue("-9223372036854775809"));
        System.out.println("9223372036854775808 => " + DataTypeHelper.getTalendTypeOfValue("9223372036854775808"));
        System.out.println("2.3 => " + DataTypeHelper.getTalendTypeOfValue("2.3"));
        System.out.println("-2.3 => " + DataTypeHelper.getTalendTypeOfValue("-2.3"));
        System.out.println("10002.3.1 => " + DataTypeHelper.getTalendTypeOfValue("10002.3.1"));
        System.out.println("ABC3 => " + DataTypeHelper.getTalendTypeOfValue("ABC3"));

        System.out.println("__________________________");

        // * float flottant (real) -1.4*10 -45 � 3.4*10 38
        System.out.println("float:" + DataTypeHelper.getTalendTypeOfValue("1.11111111111"));
        System.out.println("float:" + DataTypeHelper.getTalendTypeOfValue("-0.0000000000000000000000000000000000000000000014"));
        System.out.println("float:" + DataTypeHelper.getTalendTypeOfValue("339999999999999999999999999999999999999.9")); // *
        // double
        // flottant double 4.9*10 -324 � 1.7*10 308
        System.out.println("double:"
                + DataTypeHelper.getTalendTypeOfValue("1699999999999999999999999999999999999999999999999999999999999999999999999999.9"));
        System.out.println("double:"
                + DataTypeHelper.getTalendTypeOfValue("1700000000000000000000000000000000000000000000000000000000000000000000000000"));
    }

}
