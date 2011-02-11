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
package org.talend.utils.sql;

import java.sql.Types;

/**
 * DOC zqin class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 * 
 */
public final class Java2SqlType {

    // ADD by zshen bug 11875.to added additional type.
    public static final int NCHAR = -15;

    public static final int NTEXT = -16;

    public static final int NVARCHAR = -9;

    // ~11875
    private Java2SqlType() {
    }

    public static boolean isTextInSQL(int type) {

        switch (type) {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
        case Types.CLOB:
            // ADD by zshen bug 11875.to added additional type.
        case Java2SqlType.NCHAR:
        case Java2SqlType.NTEXT:
        case Java2SqlType.NVARCHAR:
            // ~11875
            return true;
        default:
            return false;
        }
    }

    public static boolean isNumbericInSQL(int type) {
        switch (type) {
        case Types.DOUBLE:
        case Types.REAL:
        case Types.FLOAT:
        case Types.INTEGER:
        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.BIGINT:
        case Types.DECIMAL:
        case Types.NUMERIC:

            return true;
        default:
            return false;
        }
    }

    public static boolean isDateInSQL(int type) {
        switch (type) {
        case Types.DATE:
        case Types.TIME:
        case Types.TIMESTAMP:

            return true;
        default:
            return false;
        }
    }

    public static boolean isDateTimeSQL(int type) {
        return type == Types.TIMESTAMP;
    }

    public static boolean isOtherTypeInSQL(int type) {

        if (isTextInSQL(type) || isNumbericInSQL(type) || isDateInSQL(type)) {
            return false;
        }

        return true;
    }

    public static boolean isGenericSameType(int type1, int type2) {
        if (isTextInSQL(type1) && isTextInSQL(type2) || isNumbericInSQL(type1) && isNumbericInSQL(type2) || isDateInSQL(type1)
                && isDateInSQL(type2)) {
            return true;
        }

        return false;
    }
}
