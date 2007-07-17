// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public class JavaTypesManagerTest {

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getJavaTypeFromName(java.lang.String)}.
     */
    @Test
    public final void testGetJavaTypeFromName() {
        assertEquals(JavaTypesManager.getJavaTypeFromName("String"), JavaTypesManager.STRING); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getJavaTypeFromName("int"), JavaTypesManager.INTEGER); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getJavaTypeFromName("Integer"), JavaTypesManager.INTEGER); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getJavaTypeFromName("Object"), JavaTypesManager.OBJECT); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getJavaTypeFromName("byte[]"), JavaTypesManager.BYTE_ARRAY); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getShortNameFromJavaType(org.talend.core.model.metadata.types.JavaType)}.
     */
    @Test
    public final void testGetShortNameFromJavaType() {

        assertEquals(JavaTypesManager.getShortNameFromJavaType(JavaTypesManager.STRING), "String"); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getShortNameFromJavaType(JavaTypesManager.BOOLEAN), "boolean"); //$NON-NLS-1$
    
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getJavaTypeFromLabel(java.lang.String)}.
     */
    @Test
    public final void testGetJavaTypeFromLabel() {
        assertEquals(JavaTypesManager.getJavaTypeFromLabel("boolean | Boolean"), JavaTypesManager.BOOLEAN); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getJavaTypeFromId(java.lang.String)}.
     */
    @Test
    public final void testGetJavaTypeFromId() {
        assertEquals(JavaTypesManager.getJavaTypeFromId("id_Boolean"), JavaTypesManager.BOOLEAN); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getJavaTypeFromCanonicalName(java.lang.String)}.
     */
    @Test
    public final void testGetJavaTypeFromCanonicalName() {
        assertEquals(JavaTypesManager.getJavaTypeFromCanonicalName("java.lang.Boolean"), JavaTypesManager.BOOLEAN); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getTypeToGenerate(java.lang.String, boolean)}.
     */
    @Test
    public final void testGetTypeToGenerate() {
        assertEquals(JavaTypesManager.getTypeToGenerate("id_Boolean", true), "Boolean"); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getTypeToGenerate("id_Boolean", false), "boolean"); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#isJavaPrimitiveType(java.lang.String)}.
     */
    @Test
    public final void testIsJavaPrimitiveTypeString() {
        assertEquals(JavaTypesManager.isJavaPrimitiveType("boolean"), true); //$NON-NLS-1$
        assertEquals(JavaTypesManager.isJavaPrimitiveType("Boolean"), false); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#isJavaPrimitiveType(java.lang.String, boolean)}.
     */
    @Test
    public final void testIsJavaPrimitiveTypeStringBoolean() {
        assertEquals(JavaTypesManager.isJavaPrimitiveType(JavaTypesManager.BOOLEAN, true), false); //$NON-NLS-1$
        assertEquals(JavaTypesManager.isJavaPrimitiveType(JavaTypesManager.BOOLEAN, false), true); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#isJavaPrimitiveType(org.talend.core.model.metadata.types.JavaType, boolean)}.
     */
    @Test
    public final void testIsJavaPrimitiveTypeJavaTypeBoolean() {
        assertEquals(JavaTypesManager.isJavaPrimitiveType("id_Boolean", true), false); //$NON-NLS-1$
        assertEquals(JavaTypesManager.isJavaPrimitiveType("id_Boolean", false), true); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getDefaultValueFromJavaType(java.lang.String)}.
     */
    @Test
    public final void testGetDefaultValueFromJavaTypeString() {
        assertEquals(JavaTypesManager.getDefaultValueFromJavaType("Boolean"), "null"); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getDefaultValueFromJavaType("boolean"), "false"); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getDefaultValueFromJavaType(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testGetDefaultValueFromJavaTypeStringString() {
        assertEquals(JavaTypesManager.getDefaultValueFromJavaType("boolean", "test"), "test"); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getDefaultValueFromJavaIdType(java.lang.String, boolean)}.
     */
    @Test
    public final void testGetDefaultValueFromJavaIdTypeStringBoolean() {
        assertEquals(JavaTypesManager.getDefaultValueFromJavaIdType("id_Boolean", false), "false"); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getDefaultValueFromJavaIdType("id_Boolean", true), "null"); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getDefaultValueFromJavaIdType(java.lang.String, boolean, java.lang.String)}.
     */
    @Test
    public final void testGetDefaultValueFromJavaIdTypeStringBooleanString() {
        assertEquals(JavaTypesManager.getDefaultValueFromJavaIdType("id_Boolean", false, "test"), "test"); //$NON-NLS-1$
        assertEquals(JavaTypesManager.getDefaultValueFromJavaIdType("id_Boolean", true, "test"), "test"); //$NON-NLS-1$
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.types.JavaTypesManager#getDefaultJavaType()}.
     */
    @Test
    public final void testGetDefaultJavaType() {
        assertEquals(JavaTypesManager.getDefaultJavaType(), JavaTypesManager.STRING); //$NON-NLS-1$
    }

}
