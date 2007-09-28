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
package org.talend.expressionbuilder.test.shadow;

/**
 * Represents to the varialbes can be list in the table viewer.
 * 
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: Variable.java 下午04:07:16 2007-6-25 +0000 (2007-6-25) yzhang $
 * 
 */
public class Variable {

    private String name;

    private String value;

    private String talendType;

    private boolean nullable;

    /**
     * yzhang Variable constructor comment.
     */
    public Variable() {
    }

    /**
     * yzhang Variable constructor comment.
     */
    public Variable(int n) {
        name = "name" + String.valueOf(n);
        value = "value" + String.valueOf(n);
    }

    /**
     * yzhang Variable constructor comment.
     * 
     * @param name
     * @param value
     */
    public Variable(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * yzhang Variable constructor comment.
     * 
     * @param name
     * @param value
     */
    public Variable(String name, String value, String type, boolean nullable) {
        this.name = name;
        this.value = value;
        this.talendType = type;
        this.nullable = nullable;
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for value.
     * 
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * yzhang Comment method "getTalendType".
     * 
     * @return
     */
    public String getTalendType() {
        return this.talendType;
    }

    /**
     * yzhang Comment method "isNullable".
     * 
     * @return
     */
    public boolean isNullable() {
        return this.nullable;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the value.
     * 
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * yzhang Comment method "setTalendType".
     * 
     * @param typeId
     */
    public void setTalendType(String typeId) {
        this.talendType = typeId;
    }

    /**
     * yzhang Comment method "setNullable".
     * 
     * @param nullable
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
