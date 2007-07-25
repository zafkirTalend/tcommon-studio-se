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
 * 
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: Relational.java 下午01:31:49 2007-7-3 +0000 (2007-7-3) yzhang $
 * 
 */
public class Relational {

    /**
     * ISNULL( ) Indicates when a variable is the null value.
     * 
     * {Category} Relational
     * 
     * {talendTypes} boolean | Boolean
     * 
     */
    public static boolean ISNULL(Object variable) {
        return variable == null;
    }

    /**
     * NOT( ) Returns the complement of the logical value of an expression.
     * 
     * {Category} Relational
     * 
     * {talendTypes} boolean | Boolean
     * 
     */
    public static boolean NOT(boolean expression) {
        return !expression;
    }
}
