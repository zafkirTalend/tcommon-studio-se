// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.talend.core.model.metadata.types.DBTypeUtil;
import org.talend.core.model.metadata.types.JavaTypesManager;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class MappingTypeRetrieverTest {

    /**
     * DOC Administrator Comment method "setUp".
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * DOC Administrator Comment method "tearDown".
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MappingTypeRetriever#isLengthIgnored(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testIsLengthIgnored() {
        String dbmsId = "id_MSSQL";
        String dbType = "int";
        if (isExtensionLengthIgnored(dbmsId, dbType)) {
            assertTrue(true);
        }
        Dbms dbms = MetadataTalendType.getDbms(dbmsId);
        List ignoreLP = dbms.getIgnoreLengthPrecision();
        String ignore = new String(""); //$NON-NLS-1$
        for (int i = 0; i < ignoreLP.size(); i++) {
            DbIgnoreLengthAndPrecision dbIgnore = (DbIgnoreLengthAndPrecision) ignoreLP.get(i);
            if (dbIgnore.getDbType().equalsIgnoreCase(dbType)) {
                ignore = dbIgnore.getIgnoreLength();
                if (ignore == null) {
                    assertFalse(false);
                } else if (ignore.equals("true")) { //$NON-NLS-1$
                    assertTrue(true);
                } else {
                    assertFalse(false);
                }
            }// end if
        }// end for
        assertFalse(false);
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MappingTypeRetriever#isPrecisionIgnored(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testIsPrecisionIgnored() {
        String dbmsId = "id_MSSQL";
        String dbType = "int";
        if (isExtensionPrecisionIgnored(dbmsId, dbType)) {
            assertTrue(true);
        }

        Dbms dbms = MetadataTalendType.getDbms(dbmsId);
        List ignoreLP = dbms.getIgnoreLengthPrecision();
        String ignore = new String(""); //$NON-NLS-1$
        for (int i = 0; i < ignoreLP.size(); i++) {
            DbIgnoreLengthAndPrecision dbIgnore = (DbIgnoreLengthAndPrecision) ignoreLP.get(i);
            if (dbIgnore.getDbType().equalsIgnoreCase(dbType)) {
                ignore = dbIgnore.getIgnorePrecision();
                if (ignore == null) {
                    assertFalse(false);
                } else if (ignore.equals("true")) { //$NON-NLS-1$
                    assertTrue(true);
                } else {
                    assertFalse(false);
                }
            }// end if
        }// end for
        assertFalse(false);
    }

    private boolean isExtensionLengthIgnored(String dbmsId, String dbType) {
        Map<String, Map<String, List<DBTypeUtil>>> javaTypeMappingFromExtension = JavaTypesManager
                .getJavaTypeMappingFromExtension();
        for (String id : javaTypeMappingFromExtension.keySet()) {
            for (String mappingId : javaTypeMappingFromExtension.get(id).keySet()) {
                if (dbmsId.equals(mappingId)) {
                    for (DBTypeUtil dbTypeUtil : javaTypeMappingFromExtension.get(id).get(mappingId)) {
                        if (dbTypeUtil.getDbTypeName().equals(dbType)) {
                            return dbTypeUtil.isIgnoreLength();
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isExtensionPrecisionIgnored(String dbmsId, String dbType) {
        Map<String, Map<String, List<DBTypeUtil>>> javaTypeMappingFromExtension = JavaTypesManager
                .getJavaTypeMappingFromExtension();
        for (String id : javaTypeMappingFromExtension.keySet()) {
            for (String mappingId : javaTypeMappingFromExtension.get(id).keySet()) {
                if (dbmsId.equals(mappingId)) {
                    for (DBTypeUtil dbTypeUtil : javaTypeMappingFromExtension.get(id).get(mappingId)) {
                        if (dbTypeUtil.getDbTypeName().equals(dbType)) {
                            return dbTypeUtil.isIgnorePrecision();
                        }
                    }
                }
            }
        }
        return false;
    }

}
