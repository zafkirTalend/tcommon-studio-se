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
package org.talend.commons.ui.swt.tableviewer;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ReflectionPropertyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3425844870424877957L;

    public ReflectionPropertyException(Class reflectedClass, String reflectedProperty, boolean isGetter, Throwable cause) {

        super("Error when trying to access to the " + (isGetter ? "getter" : "setter") + " of the property '"
                + reflectedProperty + "' " + "of the class '"
                + (reflectedClass != null ? reflectedClass.getName() : "class null") + "'", cause);
    }
}
