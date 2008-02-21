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
package org.talend.utils.format;

/**
 * @author scorreia
 * 
 * This class contains utilities for formatting strings.
 */
public final class StringFormatUtil {

    private StringFormatUtil() {
    }

    /**
     * Method "padString".
     * 
     * @param stringToPad the string to which white spaces must be added.
     * @param size the size of the new string.
     * @return the given string completed with white spaces up the the given size.
     */
    public static String padString(String stringToPad, int size) {
        return String.format("%" + size + "s", stringToPad);
    }
}
