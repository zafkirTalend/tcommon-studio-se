// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.string;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author scorreia. A String utility class.
 */
public final class StringUtilities {

    private StringUtilities() {
    }

    /**
     * Method "tokenize".
     * 
     * @param input the string to split
     * @param delimiters the delimiter(s) of the fields
     * @see StringTokenizer
     * @return a list of Strings or an empty array when a null argument is given or when there is no string to split
     */
    public static List<String> tokenize(String input, String delimiters) {
        List<String> stringArray = new ArrayList<String>();

        if (input == null) {
            return stringArray;
        }
        if (delimiters == null) {
            return stringArray;
        }

        StringTokenizer t = new StringTokenizer(input, delimiters);

        while (t.hasMoreTokens()) {
            stringArray.add(t.nextToken());
        }
        return stringArray;
    } // eom tokenize

}
