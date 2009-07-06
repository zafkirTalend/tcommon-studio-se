// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
    
    public static Date parse(String pattern, String dateText) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        date = sdf.parse(dateText);
        return date;
    }
}
