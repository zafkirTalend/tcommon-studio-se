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

    public static final int NVARCHAR2 = 1111;

    public static final int BIT = -7;

    public static final int VARBINARY = 2004;

    // ~11875
    private Java2SqlType() {
    }

    public static boolean isBinaryInSQL(int type) {
        switch (type) {
        case Java2SqlType.BIT:
        case Java2SqlType.VARBINARY:
            return true;
        default:
            return false;
        }
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
        case Java2SqlType.NVARCHAR2:
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
        if ((type1 == type2) || (isTextInSQL(type1) && isTextInSQL(type2)) || (isNumbericInSQL(type1) && isNumbericInSQL(type2))
                || (isDateInSQL(type1) && isDateInSQL(type2))) {
            return true;
        }

        return false;
    }

    public static int getJavaTypeBySqlType(String sqlType) {
        // MOD klliu bug TDQ-1164 2011-09-26
        if (sqlType.trim().equals("DATE")) { //$NON-NLS-1$
            return Types.DATE;
        } else if (sqlType.trim().equals("BIGINT")) { //$NON-NLS-1$
            return Types.BIGINT;
        } else if (sqlType.trim().equals("INTEGER")) { //$NON-NLS-1$
            return Types.INTEGER;
        } else if (sqlType.trim().equals("SMALLINT")) { //$NON-NLS-1$
            return Types.SMALLINT;
        } else if (sqlType.trim().equals("FLOAT")) { //$NON-NLS-1$
            return Types.FLOAT;
        } else if (sqlType.trim().equals("CHAR")) { //$NON-NLS-1$
            return Types.CHAR;
        } else if (sqlType.trim().equals("VARCHAR")) { //$NON-NLS-1$
            return Types.VARCHAR;
        } else if (sqlType.trim().equals("DECIMAL")) { //$NON-NLS-1$
            return Types.DECIMAL;
        } else if (sqlType.trim().equals("TIME")) { //$NON-NLS-1$
            return Types.TIME;
        } else if (sqlType.trim().equals("TIMESTMP")) { //$NON-NLS-1$
            return Types.TIMESTAMP;
        } else if (sqlType.trim().equals("BLOB")) { //$NON-NLS-1$
            return Types.BLOB;
        } else if (sqlType.trim().equals("CLOB")) { //$NON-NLS-1$
            return Types.CLOB;
        } else if (sqlType.trim().equals("DISTINCT")) { //$NON-NLS-1$
            return Types.DISTINCT;
        }
        return 0;
    }
}
