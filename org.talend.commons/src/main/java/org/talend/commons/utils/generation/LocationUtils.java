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
package org.talend.commons.utils.generation;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;
import org.talend.commons.utils.data.text.StringHelper;

/**
 * 
 * DOC pierrick class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LocationUtils {

    private Perl5Matcher matcher = new Perl5Matcher();

    private Perl5Compiler compiler = new Perl5Compiler();

    // private List<EntryLocation> resultList = new ArrayList<EntryLocation>();

    private Pattern pattern;

    // private PatternMatcherInput patternMatcherInput;

    // private String locationPattern;

    private static final String DOUBLE_ESCAPE = "\\";

    private static final String PREFIX_VARIABLE_NAME = "$";

    // private static final String AND_CONDITION = " && ";

    private static final String PREFIX_FIELD_NAME = "[";

    private static final String SUFFIX_FIELD_NAME = "]";

    private static final String PREFIX_TABLE_NAME = PREFIX_VARIABLE_NAME;

    private static final String PREFIX_TABLE_NAME_REGEXP = DOUBLE_ESCAPE + PREFIX_TABLE_NAME;

    // private static final String SUFFIX_TABLE_NAME = "";

    // private static final String SUFFIX_TABLE_NAME_REGEXP = SUFFIX_TABLE_NAME;

    private static final String PREFIX_FIELD_NAME_REGEXP = DOUBLE_ESCAPE + PREFIX_FIELD_NAME;

    private static final String SUFFIX_FIELD_NAME_REGEXP = DOUBLE_ESCAPE + SUFFIX_FIELD_NAME;

    /**
     * {0} and {1} must be replaced respectively by the table name and the column name.
     */
    private static final String SUBST_PATTERN_FOR_PREFIX_COLUMN_NAME = PREFIX_TABLE_NAME_REGEXP + "\\s*({0})\\s*"
            + PREFIX_FIELD_NAME_REGEXP + "\\s*({1})\\s*" + SUFFIX_FIELD_NAME_REGEXP;

    private void recompilePatternIfNecessary(String regexpPattern) {
        if (pattern == null || !regexpPattern.equals(pattern.getPattern())) {
            try {
                pattern = compiler.compile(regexpPattern);
            } catch (MalformedPatternException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String replaceColumnNameToConstantName(String expression, EntryLocation[] locations) {

        String returnedExpression = expression;
        for (EntryLocation location : locations) {
            recompilePatternIfNecessary(StringHelper.replacePrms(SUBST_PATTERN_FOR_PREFIX_COLUMN_NAME, new Object[] { location.tableName,
                    location.columnName }));
            if (returnedExpression != null) {
                matcher.setMultiline(true);
                Perl5Substitution substitution = new Perl5Substitution(PREFIX_TABLE_NAME_REGEXP + "$1" + PREFIX_FIELD_NAME_REGEXP
                        + "$1__$2" + SUFFIX_FIELD_NAME_REGEXP, Perl5Substitution.INTERPOLATE_ALL);
                returnedExpression = Util.substitute(matcher, pattern, substitution, returnedExpression, Util.SUBSTITUTE_ALL);
            }
        }
        return returnedExpression;

    }

}
