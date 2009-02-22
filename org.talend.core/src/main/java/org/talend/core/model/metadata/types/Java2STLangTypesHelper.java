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
package org.talend.core.model.metadata.types;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC wyang class global comment. Detailled comment
 */
public class Java2STLangTypesHelper {

    /**
     * DOC return as: int int64, big_int, float, bool, char, string.
     * 
     * @param metadataTable
     * @param columnLabel
     * @return
     */
    public static String getSTLangType(IMetadataTable metadataTable, String columnLabel) {
        IMetadataColumn column = metadataTable.getColumn(columnLabel);
        String talendType = column.getTalendType();

        String typeToGenerate = JavaTypesManager.getTypeToGenerate(talendType, false);
        // Integers: int(Integer), long(Long), short(Short), byte(Byte), BigDecimal.
        if (typeToGenerate.equals("int") || typeToGenerate.equals("short") || typeToGenerate.equals("byte")) {
            return "int";
        } else if (typeToGenerate.equals("long")) {
            return "int64";
        } else if (typeToGenerate.equals("BigDecimal")) {
            return "big_int";
        }

        // Floats: float(Float), double(Double)
        if (typeToGenerate.equals("float") || typeToGenerate.equals("double")) {
            return "float";
        }

        // Bool: bool(Boolean)
        if (typeToGenerate.equals("bool")) {
            return "bool";
        }

        // Characters: char(Character)
        if (typeToGenerate.equals("char")) {
            return "char";
        }

        // others treat as string
        return "string";

    }

    /**
     * DOC return as: %d, %f, %b, %s.
     * 
     * @param metadataTable
     * @param columnLabel
     * @return
     */
    public static String getFormatString(IMetadataTable metadataTable, String columnLabel) {
        IMetadataColumn column = metadataTable.getColumn(columnLabel);
        String talendType = column.getTalendType();

        String typeToGenerate = JavaTypesManager.getTypeToGenerate(talendType, false);
        // Integers: int(Integer), long(Long), short(Short), byte(Byte), BigDecimal.
        if (typeToGenerate.equals("int") || typeToGenerate.equals("short") || typeToGenerate.equals("byte")) {
            return "%d";
        } else if (typeToGenerate.equals("long")) {
            return "%d";
        } else if (typeToGenerate.equals("BigDecimal")) {
            return "%d";
        }

        // Floats: float(Float), double(Double)
        if (typeToGenerate.equals("float") || typeToGenerate.equals("double")) {
            return "%f";
        }

        // Bool: bool(Boolean)
        if (typeToGenerate.equals("bool")) {
            return "%b";
        }

        // Characters: char(Character)
        if (typeToGenerate.equals("char")) {
            return "%c";
        }

        // others treat as string
        return "%s";

    }
}
