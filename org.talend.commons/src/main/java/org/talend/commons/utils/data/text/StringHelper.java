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
package org.talend.commons.utils.data.text;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class StringHelper {

    private final static String OPEN_BRACE = "{";

    private final static String CLOSE_BRACE = "}";

    /**
     * Replace MessageFormat.format(..) because MessageFormat does'nt support single quote correctly This method replace
     * in the text all the patterns {0}, {1}, etc.. by all values in arguments array One or more values can be null, no
     * exception is thrown if a value is null.
     * 
     * @param pattern
     * @param arguments
     * @return
     * 
     */
    public static String replacePrms(String text, Object[] arguments) {

        for (int i = 0; i < arguments.length; i++) {
            text = StringUtils.replace(text, OPEN_BRACE + i + CLOSE_BRACE, String.valueOf(arguments[i]));
        }
        return text;
    }

    /**
     * Replace MessageFormat.format(..) because MessageFormat does'nt support single quote correctly This method replace
     * in the text all the patterns {0}, {1}, etc.. by all values in arguments array One or more values can be null, no
     * exception is thrown if a value is null.
     * 
     * @param pattern
     * @param arguments
     * @return
     * 
     */
    public static String replacePrms(String text, Object[] arguments, String strBeforeIndex, String strAfterIndex) {
        
        for (int i = 0; i < arguments.length; i++) {
            text = StringUtils.replace(text, strBeforeIndex + i + strAfterIndex, String.valueOf(arguments[i]));
        }
        return text;
    }
    
}
