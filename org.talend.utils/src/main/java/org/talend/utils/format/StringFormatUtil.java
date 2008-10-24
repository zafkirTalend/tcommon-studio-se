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

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * @author scorreia
 * 
 * This class contains utilities for formatting strings.
 */
public final class StringFormatUtil {

    public static final int PERCENT = 0;

    public static final int NUMBER = 1;

    public static final int OTHER = 99999;

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

    public static Object format(Object input, int style) {
        if (input == null) {
            return "";
        }

        try {
            Double dbl = new Double(input.toString());
            if (dbl.equals(Double.NaN)) {
                return String.valueOf(dbl);
            }

            DecimalFormat format = null;

            switch (style) {
            case 0:
                format = (DecimalFormat) DecimalFormat.getPercentInstance(Locale.ENGLISH);
                format.applyPattern("0.00%");
                break;
            case 1:
                format = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
                format.applyPattern("0.00");
                break;
            default:
                format = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
                return format.parse(input.toString());
            }

            return format.format(dbl);
        } catch (Exception e) {
            return input;
        }
    }
}
