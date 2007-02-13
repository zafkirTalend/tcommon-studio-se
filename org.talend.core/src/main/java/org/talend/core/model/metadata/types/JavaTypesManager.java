// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.metadata.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class JavaTypesManager {

    public static final String DEFAULT_CHAR = "' '";

    public static final String DEFAULT_NUMBER = "0";

    public static final String NULL = "null";

    public static final String JAVA_PRIMITIVE_CHAR = "char";

    public static final JavaType BOOLEAN = new JavaType(Boolean.class, boolean.class);

    public static final JavaType BYTE = new JavaType(Byte.class, byte.class);

    public static final JavaType BYTE_ARRAY = new JavaType(byte[].class);

    public static final JavaType CHARACTER = new JavaType(Character.class, char.class);

    public static final JavaType DATE = new JavaType(Date.class, true);

    public static final JavaType DOUBLE = new JavaType(Double.class, double.class);

    public static final JavaType FLOAT = new JavaType(Float.class, float.class);

    public static final JavaType INTEGER = new JavaType(Integer.class, int.class);

    public static final JavaType LONG = new JavaType(Long.class, long.class);

    public static final JavaType SHORT = new JavaType(Short.class, short.class);

    public static final JavaType STRING = new JavaType(String.class);

    public static final JavaType OBJECT = new JavaType(Object.class);

    public static final JavaType[] JAVA_TYPES = new JavaType[] { BOOLEAN, BYTE, BYTE_ARRAY, CHARACTER, DATE, DOUBLE, FLOAT, INTEGER, LONG,
            OBJECT, SHORT, STRING, };

    private static Map<String, JavaType> shortNameToJavaType;

    private static Map<String, JavaType> canonicalClassNameToJavaType;

    private static Map<String, JavaType> labelToJavaType;

    private static Map<String, JavaType> idToJavaType;

    private static List<JavaType> javaTypes;

    private static String[] javaTypesLabelsArray = new String[0];

    private static final List<String> JAVA_PRIMITIVE_TYPES = new ArrayList<String>();

    static {
        init();
    }

    private static final Set<String> PRIMITIVE_TYPES_SET = new HashSet<String>(JAVA_PRIMITIVE_TYPES);

    /**
     * DOC amaumont Comment method "init".
     */
    private static void init() {

        shortNameToJavaType = new HashMap<String, JavaType>();
        labelToJavaType = new HashMap<String, JavaType>();
        idToJavaType = new HashMap<String, JavaType>();
        canonicalClassNameToJavaType = new HashMap<String, JavaType>();
        javaTypes = new ArrayList<JavaType>();

        for (int i = 0; i < JAVA_TYPES.length; i++) {
            JavaType javaType = JAVA_TYPES[i];
            addJavaType(javaType);
        }

    }

    /**
     * Add a java type to the common list;
     * 
     * @param javaType
     */
    public static void addJavaType(JavaType javaType) {
        String primitiveName = null;
        Class primitiveClass = javaType.getPrimitiveClass();
        if (primitiveClass != null) {
            primitiveName = primitiveClass.getSimpleName();
            shortNameToJavaType.put(primitiveName, javaType);
            canonicalClassNameToJavaType.put(primitiveClass.getCanonicalName(), javaType);
            JAVA_PRIMITIVE_TYPES.add(primitiveClass.getSimpleName());
        }
        String nullableName = javaType.getNullableClass().getSimpleName();
        shortNameToJavaType.put(nullableName, javaType);
        canonicalClassNameToJavaType.put(javaType.getNullableClass().getCanonicalName(), javaType);
        labelToJavaType.put(javaType.getLabel(), javaType);
        idToJavaType.put(javaType.getId(), javaType);
        javaTypes.add(javaType);
    }

    /**
     * 
     * Search JavaType from short name (ex: "Double" or "double").
     * 
     * @param typeName
     * @return JavaType if found, else null
     */
    public static JavaType getJavaTypeFromName(String typeName) {
        return shortNameToJavaType.get(typeName);
    }

    /**
     * 
     * Search JavaType from label (ex: "double / Double" or "String").
     * 
     * @param typeName
     * @return JavaType if found, else null
     */
    public static JavaType getJavaTypeFromLabel(String label) {
        return labelToJavaType.get(label);
    }

    /**
     * 
     * Search JavaType from label (ex: "double / Double" or "String").
     * 
     * @param typeName
     * @return JavaType if found, else null
     */
    public static JavaType getJavaTypeFromId(String id) {
        JavaType javaTypeFromId = idToJavaType.get(id);
        if (javaTypeFromId == null) {
            throw new IllegalArgumentException("Unknown java id type : '" + id + "'");
        }
        return javaTypeFromId;
    }

    public static String[] getJavaTypesLabels() {
        int lstSize = javaTypes.size();
        if (javaTypesLabelsArray.length != lstSize) {
            javaTypesLabelsArray = new String[lstSize];
            for (int i = 0; i < lstSize; i++) {
                javaTypesLabelsArray[i] = javaTypes.get(i).getLabel();
            }
        }
        return (String[]) ArrayUtils.clone(javaTypesLabelsArray);
    }

    public static JavaType[] getJavaTypes() {
        return javaTypes.toArray(new JavaType[0]);
    }

    /**
     * 
     * Search JavaType from short name (ex: "Double" or "double").
     * 
     * @param typeName
     * @return JavaType if found, else null
     */
    public static JavaType getJavaTypeFromCanonicalName(String canonicalName) {
        return canonicalClassNameToJavaType.get(canonicalName);
    }

    /**
     * 
     * Get primitive or object type according to id and nullable parameters.
     * 
     * @param idType
     * @param nullable
     * @return canonical name of class (java.lang.String or int)
     */
    public static String getTypeToGenerate(String idType, boolean nullable) {
        JavaType javaTypeFromId = getJavaTypeFromId(idType);
        return getTypeToGenerate(javaTypeFromId, nullable);
    }

    /**
     * DOC amaumont Comment method "getFinalType".
     * 
     * @param javaType
     * @param nullable
     * @return
     */
    private static String getTypeToGenerate(JavaType javaType, boolean nullable) {
        if (javaType == null) {
            return null;
        }
        Class primitiveClass = javaType.getPrimitiveClass();
        Class nullableClass = javaType.getNullableClass();
        if (nullable) {
            if (javaType.isGenerateWithCanonicalName()) {
                return nullableClass.getCanonicalName();
            } else {
                return nullableClass.getSimpleName();
            }
        } else {
            if (primitiveClass != null) {
                return javaType.getPrimitiveClass().getSimpleName();
            } else {
                if (javaType.isGenerateWithCanonicalName()) {
                    return nullableClass.getCanonicalName();
                } else {
                    return nullableClass.getSimpleName();
                }
            }
        }
    }

    /**
     * 
     * Return true if given type represents a primitive java type.
     * 
     * @param type
     * @return true if given type represents a primitive java type
     */
    public static boolean isJavaPrimitiveType(String type) {
        return PRIMITIVE_TYPES_SET.contains(type);
    }

    /**
     * 
     * Return true if given type represents a primitive java type.
     * 
     * @param type
     * @return true if given type represents a primitive java type
     */
    public static boolean isJavaPrimitiveType(String idType, boolean nullable) {
        String typeToGenerate = getTypeToGenerate(idType, nullable);
        return isJavaPrimitiveType(typeToGenerate);
    }

    /**
     * 
     * Return true if given type represents a primitive java type.
     * 
     * @param type
     * @return true if given type represents a primitive java type
     */
    public static boolean isJavaPrimitiveType(JavaType javaType, boolean nullable) {
        String typeToGenerate = getTypeToGenerate(javaType, nullable);
        return isJavaPrimitiveType(typeToGenerate);
    }

    /**
     * 
     * Return the default value for a given type.
     * 
     * @param type
     * @return
     */
    public static String getDefaultValueFromJavaType(String type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        if (isJavaPrimitiveType(type)) {
            if (type.equals(JAVA_PRIMITIVE_CHAR)) {
                return DEFAULT_CHAR;
            } else {
                return DEFAULT_NUMBER;
            }
        } else {
            return NULL;
        }
    }

    /**
     * 
     * Return the default value for a given type.
     * 
     * @param type
     * @return
     */
    public static String getDefaultValueFromJavaIdType(String idType, boolean nullable) {
        String typeToGenerate = getTypeToGenerate(idType, nullable);
        return getDefaultValueFromJavaType(typeToGenerate);
    }

    public static JavaType getDefaultJavaType() {
        return STRING;
    }

    public static void main(String[] args) {

        System.out.println(JavaTypesManager.getJavaTypeFromName("String"));
        System.out.println(JavaTypesManager.getJavaTypeFromName("int"));
        System.out.println(JavaTypesManager.getJavaTypeFromName("Integer"));
        System.out.println(JavaTypesManager.getJavaTypeFromName("integer"));
        System.out.println(JavaTypesManager.getJavaTypeFromName("Object"));
    }

}
