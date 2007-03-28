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
package org.talend.commons.utils.generation;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class CodeGenerationUtils {

    public static final String PROBLEM_KEY_FIELD_SEPARATOR = ":";
    
    public static final String START_FIELD = "Start field";

    public static final String END_FIELD = "End field";
    
    /**
     * DOC amaumont Comment method "buildProblemKey".
     * 
     * @param mapperComponent
     * @param problemKeyField
     * @param tableName
     * @param entryName
     */
    public static String buildProblemKey(String... valuesOfKey) {
        String key = "";
        for (int i = 0; i < valuesOfKey.length; i++) {
            String value = valuesOfKey[i];
            if (value != null) {
                if (i != 0) {
                    key += PROBLEM_KEY_FIELD_SEPARATOR;
                }
                key += value;
            }
        }
        return key;
    }


    /**
     * DOC amaumont Comment method "insertFieldKey".
     * 
     * @param string
     * @param expression
     * @return
     */
    public static String buildJavaStartFieldKey(String key) {
        return "/** "+ START_FIELD +" " + key + " */";
    }

    /**
     * DOC amaumont Comment method "buildEndFieldKey".
     * 
     * @param key
     * @return
     */
    public static String buildJavaEndFieldKey(String key) {
        return "/** "+ END_FIELD +" " + key + " */";
    }


    
}
