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
package org.talend.commons.utils; /*
                                     * 
                                     * @author amaumont
                                     * 
                                     */

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Bean useful for various uses. <br/>
 * 
 * $Id$
 * 
 */
public class Bean {

    int primitiveInt;

    Integer objInteger;

    String string;

    List list;

    Collection collection;

    Map map;

    Set set;

    String string1;

    String string2;

    String string3;

    String name;

    public String getName() {
        return name;
    }

    public Collection getCollection() {
        return collection;
    }

    public List getList() {
        return list;
    }

    public Map getMap() {
        return map;
    }

    public Integer getObjInteger() {
        return objInteger;
    }

    public int getPrimitiveInt() {
        return primitiveInt;
    }

    public String getString() {
        return string;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setObjInteger(Integer objInteger) {
        this.objInteger = objInteger;
    }

    public void setPrimitiveInt(int primitiveInt) {
        this.primitiveInt = primitiveInt;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

}
