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
package org.talend.dataquality.helpers;

import org.talend.cwm.relational.TdColumn;
import org.talend.dataquality.indicators.DataminingType;

/**
 * @author scorreia
 * 
 * This class is a helper for handling data quality metadata.
 */
public class MetadataHelper {

    /**
     * Method "setDataminingType" sets the type of the content of a column.
     * 
     * @param type
     * @param column
     */
    public static void setDataminingType(DataminingType type, TdColumn column) {
        column.setContentType(type.getLiteral());
    }

    /**
     * Method "getDataminingType" gets the type of the content of a column.
     * 
     * @param column
     * @return the DataminingType or null if none has been set.
     */
    public static DataminingType getDataminingType(TdColumn column) {
        return DataminingType.get(column.getContentType());
    }
}
