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

import org.talend.core.model.metadata.types.PerlDataTypeHelper;

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
        System.out.println("empty => " + PerlDataTypeHelper.getTalendTypeOfValue(""));
        System.out.println("1 => " + PerlDataTypeHelper.getTalendTypeOfValue("1"));
        System.out.println("A => " + PerlDataTypeHelper.getTalendTypeOfValue("A"));
        System.out.println("1E4 => " + PerlDataTypeHelper.getTalendTypeOfValue("1E4"));
        System.out.println("-2147483646 => " + PerlDataTypeHelper.getTalendTypeOfValue("-2147483646"));
        System.out.println("2147483647 => " + PerlDataTypeHelper.getTalendTypeOfValue("2147483647"));
        System.out.println("-2147483649 => " + PerlDataTypeHelper.getTalendTypeOfValue("-2147483649"));
        System.out.println("9223372036854775807 => " + PerlDataTypeHelper.getTalendTypeOfValue("9223372036854775807"));
        System.out.println("-9223372036854775809 => " + PerlDataTypeHelper.getTalendTypeOfValue("-9223372036854775809"));
        System.out.println("9223372036854775808 => " + PerlDataTypeHelper.getTalendTypeOfValue("9223372036854775808"));
        System.out.println("2.3 => " + PerlDataTypeHelper.getTalendTypeOfValue("2.3"));
        System.out.println("-2.3 => " + PerlDataTypeHelper.getTalendTypeOfValue("-2.3"));
        System.out.println("10002.3.1 => " + PerlDataTypeHelper.getTalendTypeOfValue("10002.3.1"));
        System.out.println("ABC3 => " + PerlDataTypeHelper.getTalendTypeOfValue("ABC3"));

        System.out.println("__________________________");

        // * float flottant (real) -1.4*10 -45 � 3.4*10 38
        System.out.println("float:" + PerlDataTypeHelper.getTalendTypeOfValue("1.11111111111"));
        System.out.println("float:" + PerlDataTypeHelper.getTalendTypeOfValue("-0.0000000000000000000000000000000000000000000014"));
        System.out.println("float:" + PerlDataTypeHelper.getTalendTypeOfValue("339999999999999999999999999999999999999.9")); // *
        // double
        // flottant double 4.9*10 -324 � 1.7*10 308
        System.out.println("double:"
                + PerlDataTypeHelper.getTalendTypeOfValue("1699999999999999999999999999999999999999999999999999999999999999999999999999.9"));
        System.out.println("double:"
                + PerlDataTypeHelper.getTalendTypeOfValue("1700000000000000000000000000000000000000000000000000000000000000000000000000"));
    }

}
