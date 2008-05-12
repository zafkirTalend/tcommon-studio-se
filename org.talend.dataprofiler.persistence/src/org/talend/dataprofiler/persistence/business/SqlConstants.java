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
package org.talend.dataprofiler.persistence.business;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author scorreia
 * 
 * Constants to use as database persisted values.
 */
public class SqlConstants {

    /**
     * Character to use as database values for boolean field.
     */
    public static final char YES = 'Y';

    /**
     * Character to use as database values for boolean field.
     */
    public static final char NO = 'N';

    /**
     * To be used when boolean data is undefined.
     */
    public static final char UNDEFINED = 'U';

    public static final Date END_DATE = getDefaultEndDate();

    /**
     * The default value to set in TDQ_INDICATOR_VALUE.INDV_REAL_VALUE column.
     */
    public static final Double DEFAULT_REAL = null;

    /**
     * The default value to set in TDQ_INDICATOR_VALUE.INDV_INT_VALUE column.
     */
    public static final Integer DEFAULT_INT = null;

    /**
     * The value to set when the indicator has no value to set (not applicable).
     */
    public static final String NOT_APPLICABLE_VALUE = "N/A TALEND";

    /**
     * The value to set when the data is null (missing).
     */
    public static final String NULL_VALUE = "NULL TALEND";

    public static final String DEFAULT_SUBCATEGORY = "None";

    public static final String DEFAULT_CATEGORY = "None";

    /**
     * @return 2035-12-31 0h00:00
     */
    private static Date getDefaultEndDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(2035, GregorianCalendar.DECEMBER, 31, 0, 0, 0);
        return cal.getTime();
    }

}
