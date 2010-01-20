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
package org.talend.amc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DOC stephane class global comment. Detailled comment
 */
public class DateFormat {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static String format(Date date) {
        if (date == null)
            return "";
        return sdf.format(date);
    }
}
