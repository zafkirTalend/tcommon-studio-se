// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

/**
 * 
 * DOC mzhao class global comment. Detailled comment
 */
public final class DateUtils {

    protected static Logger log = Logger.getLogger(DateUtils.class);

    public static final String PATTERN_1 = "MM/dd/yyyy";//$NON-NLS-1$

    public static final String PATTERN_2 = "yyyy-MM-dd hh:mm:ss";//$NON-NLS-1$

    public static final String PATTERN_3 = "yyyy-MM-dd";//$NON-NLS-1$

    public static final String PATTERN_4 = "MM/dd/yyyy HH:mm";//$NON-NLS-1$

    public static final String PATTERN_5 = "yyyy-MM-dd HH:mm:ss";//$NON-NLS-1$

    public static final String PATTERN_6 = "yyyyMMddHHmmss";//$NON-NLS-1$

    // Added yyin 2012-05-14 TDQ-5142
    public static final String PATTERN_7 = "hh:mm:ss";//$NON-NLS-1$

    public static final String UTC = "UTC";//$NON-NLS-1$

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

    /**
     * 
     * Comment method "formatTimeStamp".
     * 
     * @param date
     * @return
     */
    public static String formatTimeStamp(String pattern, long date) {

        if (pattern == null || pattern.length() == 0) {
            pattern = "yyyyMMddHHmmss";//$NON-NLS-1$
        }
        java.util.Calendar nowDate = new java.util.GregorianCalendar();
        nowDate.setTimeInMillis(date);
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(nowDate.getTime());
    }

    /**
     * get the standard time: UTC.
     * 
     * @param localDate the date with local time zone
     * @return
     */
    public static Date convert2StandardTime(Date localDate) {
        Date result = localDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_5);
        dateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        String dateStr = dateFormat.format(localDate);
        try {
            dateFormat.setTimeZone(TimeZone.getDefault());
            result = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.error(e);
        }
        return result;
    }
}
