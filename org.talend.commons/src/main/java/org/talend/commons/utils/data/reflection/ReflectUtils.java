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
package org.talend.commons.utils.data.reflection;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class ReflectUtils {

    /**
     * Default Constructor. Must not be used.
     */
    private ReflectUtils() {
    }

    public static void setValue(Object object, String property, Object value) {
        try {
            PropertyUtils.setProperty(object, property, value);
        } catch (IllegalAccessException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        } catch (InvocationTargetException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        } catch (NoSuchMethodException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        } catch (IllegalArgumentException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        }
    }

    /**
     * DOC amaumont Comment method "getValue".
     * 
     * @param data
     * @param beanPropertyName
     * @return
     */
    public static Object getValue(Object object, String property) {
        try {
            return PropertyUtils.getProperty(object, property);
        } catch (IllegalAccessException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        } catch (InvocationTargetException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        } catch (NoSuchMethodException e) {
            throw new ReflectionPropertyException(object != null ? object.getClass() : null, property, false, e);
        }
    }
}
