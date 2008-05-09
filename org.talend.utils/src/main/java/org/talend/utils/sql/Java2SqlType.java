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
package org.talend.utils.sql;

import java.sql.Types;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class Java2SqlType {

    public static boolean isTextInSQL(int type) {
        
        switch (type) {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
        case Types.CLOB:
            
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
    
    public static boolean isOtheTypeInSQL(int type) {
        
        if (isTextInSQL(type)) {
            return false;
        }
        
        if (isNumbericInSQL(type)) {
            return false;
        }
        
        if (isDateInSQL(type)) {
            
            return false;
        }
        
        return true;
    }
}
