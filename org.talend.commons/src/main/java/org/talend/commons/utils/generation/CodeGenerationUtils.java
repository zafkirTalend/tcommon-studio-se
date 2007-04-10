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

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CodeGenerationUtils {

    public static final String PROBLEM_KEY_FIELD_SEPARATOR = ":";

    public static final String START_FIELD = "Start field";

    public static final String END_FIELD = "End field";

    private static Perl5Matcher matcher = new Perl5Matcher();

    private static Perl5Compiler compiler = new Perl5Compiler();

    private static Pattern patternAlphaNumChar;

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
        return "/** " + START_FIELD + " " + key + " */";
    }

    /**
     * DOC amaumont Comment method "buildEndFieldKey".
     * 
     * @param key
     * @return
     */
    public static String buildJavaEndFieldKey(String key) {
        return "/** " + END_FIELD + " " + key + " */";
    }

    public static boolean hasAlphaNumericCharacter(String text) {
        if (text == null || text.trim().length() == 0) {
            return false;
        }
        if (patternAlphaNumChar == null) {
            try {
                patternAlphaNumChar = compiler.compile("\\w");
            } catch (MalformedPatternException e) {
                throw new RuntimeException(e);
            }
        }
        return matcher.contains(text, patternAlphaNumChar);
    }

    public static String replaceAllCrBySpace(String text) {
        return text.replaceAll("\n", " ");
    }

}
