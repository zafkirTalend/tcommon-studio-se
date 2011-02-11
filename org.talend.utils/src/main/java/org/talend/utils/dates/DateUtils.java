// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.utils.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * DOC mzhao class global comment. Detailled comment
 */
public final class DateUtils {

    public static final String PATTERN_1 = "MM/dd/yyyy";//$NON-NLS-1$

    public static final String PATTERN_2 = "yyyy-MM-dd hh:mm:ss";//$NON-NLS-1$

    public static final String PATTERN_3 = "yyyy-MM-dd";//$NON-NLS-1$

    public static final String PATTERN_4 = "MM/dd/yyyy HH:mm";//$NON-NLS-1$

    public static final String PATTERN_5 = "yyyy-MM-dd HH:mm:ss";//$NON-NLS-1$

    /**
     * DOC bZhou DateUtils constructor comment.
     */
    private DateUtils() {

    }

    /**
     * DOC bZhou Comment method "parse".
     * 
     * @param pattern
     * @param dateText
     * @return
     * @throws ParseException
     */
    public static Date parse(String pattern, String dateText) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        date = sdf.parse(dateText);
        return date;
    }

    /**
     * DOC bZhou Comment method "getCurrentDate".
     * 
     * @param pattern
     * @return
     */
    public static String getCurrentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
