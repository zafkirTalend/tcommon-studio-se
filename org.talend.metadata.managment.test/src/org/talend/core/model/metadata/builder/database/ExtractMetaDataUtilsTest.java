/**
 * 
 */
package org.talend.core.model.metadata.builder.database;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.commons.utils.database.SASDataBaseMetadata;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;

/**
 * @author zshen
 * 
 */
public class ExtractMetaDataUtilsTest {

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils#getDatabaseMetaData(java.sql.Connection, org.talend.core.model.metadata.builder.connection.DatabaseConnection, boolean)}
     * .
     */
    @Test
    public void testGetDatabaseMetaDataWithThreeParameters() {
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);

        Connection mockConn = mock(java.sql.Connection.class);
        try {
            when(mockConn.getMetaData()).thenReturn(metaData);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        DatabaseConnection mockDBConn = mock(DatabaseConnection.class);
        when(mockDBConn.getDatabaseType()).thenReturn(EDatabaseTypeName.TERADATA.getXmlName());
        when(mockDBConn.getSID()).thenReturn("talendDB");//$NON-NLS-1$

        java.sql.DatabaseMetaData databaseMetaData1 = ExtractMetaDataUtils.getDatabaseMetaData(mockConn, mockDBConn, true);
        java.sql.DatabaseMetaData databaseMetaData2 = ExtractMetaDataUtils.getDatabaseMetaData(mockConn, mockDBConn, false);

        try {
            verify(mockConn).getMetaData();
            verify(mockDBConn, times(2)).getDatabaseType();
            verify(mockDBConn, times(2)).getSID();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
        if (!(databaseMetaData1 instanceof TeradataDataBaseMetadata && databaseMetaData2 instanceof DatabaseMetaData)) {
            fail();
        }

    }

    /**
     * 
     * test the getDatabaseMetaData method for 4 arguments
     */
    @Test
    public void testGetDatabaseMetaData_FourArguments4NullConn() throws Exception {
        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(null, null, false, null);
        Assert.assertNull(databaseMetaData);
    }

    /**
     * 
     * Can't test the the mssql
     */
    @Test
    public void testGetDatabaseMetaData_FourArguments4MSSQL() throws Exception {
        // ConnectionJDBC2 mockConn = mock(ConnectionJDBC2.class);
        //
        // DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        // when(mockConn.getMetaData()).thenReturn(metaData);
        //
        // DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn,
        // EDatabaseTypeName.MSSQL.getXmlName(), false, null);
        //
        // verify(mockConn).getMetaData();
        //
        // Assert.assertNotNull(databaseMetaData);
        // Assert.assertTrue(databaseMetaData instanceof JtdsDatabaseMetaData);
        // Assert.assertEquals(databaseMetaData, metaData);

        /*
         * There are two difficult things:
         * 
         * - can't mock the class name.
         * 
         * - more difficult to verify JtdsMetadataAdapter by MetadataService
         */
        fail("Need check this again. and find a way to test for mssql"); //$NON-NLS-1$
    }

    @Test
    public void testGetDatabaseMetaData_FourArguments4IBMDB2ZOS() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn,
                EDatabaseTypeName.IBMDB2ZOS.getXmlName(), false, null);

        Assert.assertNotNull(databaseMetaData);
        Assert.assertTrue(databaseMetaData instanceof DB2ForZosDataBaseMetadata);

    }

    @Test
    public void testGetDatabaseMetaData_FourArguments4SAS() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn,
                EDatabaseTypeName.SAS.getXmlName(), false, null);

        Assert.assertNotNull(databaseMetaData);
        Assert.assertTrue(databaseMetaData instanceof SASDataBaseMetadata);

    }

    @Test
    public void testGetDatabaseMetaData_FourArguments4TERADATA() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn,
                EDatabaseTypeName.TERADATA.getXmlName(), false, "talendDB"); //$NON-NLS-1$

        verify(mockConn).getMetaData();

        Assert.assertNotNull(databaseMetaData);
        Assert.assertFalse(databaseMetaData instanceof TeradataDataBaseMetadata);
        Assert.assertEquals(databaseMetaData, metaData);
    }

    @Test
    public void testGetDatabaseMetaData_FourArguments4TERADATAInSqlMode() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn,
                EDatabaseTypeName.TERADATA.getXmlName(), true, "talendDB"); //$NON-NLS-1$

        Assert.assertNotNull(databaseMetaData);
        Assert.assertTrue(databaseMetaData instanceof TeradataDataBaseMetadata);
        Assert.assertEquals(((TeradataDataBaseMetadata) databaseMetaData).getDatabaseName(), "talendDB"); //$NON-NLS-1$
    }

    private List<EDatabaseTypeName> getNeedFakeTypes() {
        List<EDatabaseTypeName> neededList = new ArrayList<EDatabaseTypeName>();
        neededList.add(EDatabaseTypeName.IBMDB2ZOS);
        neededList.add(EDatabaseTypeName.TERADATA); // need isSqlMode
        neededList.add(EDatabaseTypeName.SAS);
        return neededList;
    }

    @Test
    public void testGetDatabaseMetaData_FourArguments4Normal() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);

        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        List<EDatabaseTypeName> neededList = getNeedFakeTypes();

        EDatabaseTypeName[] values = EDatabaseTypeName.values();
        int times = 1;
        for (EDatabaseTypeName value : values) {
            EDatabaseTypeName type = value;
            if (!neededList.contains(type)) {
                DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn, type.getXmlName(), false,
                        null);

                verify(mockConn, times(times++)).getMetaData();

                Assert.assertNotNull(databaseMetaData);
                Assert.assertEquals(databaseMetaData, metaData);
            }
        }
    }

    @Test
    public void testGetDatabaseMetaData_FourArguments4NormalNullDbType() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);

        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getDatabaseMetaData(mockConn, null, false, null);

        verify(mockConn).getMetaData();

        Assert.assertNotNull(databaseMetaData);
        Assert.assertEquals(databaseMetaData, metaData);
    }

    @Test
    public void testGetConnectionMetadata4NullConn() throws Exception {
        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(null);
        Assert.assertNull(databaseMetaData);
    }

    @Test
    public void testGetConnectionMetadata4NullMetaData() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        when(mockConn.getMetaData()).thenReturn(null);
        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(mockConn);
        verify(mockConn).getMetaData();
        Assert.assertNull(databaseMetaData);
    }

    @Test
    public void testGetConnectionMetadata4NullDatabaseProductName() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);
        when(metaData.getDatabaseProductName()).thenReturn(null);

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(mockConn);

        verify(mockConn).getMetaData();
        verify(metaData).getDatabaseProductName();

        Assert.assertNotNull(databaseMetaData);
        Assert.assertEquals(databaseMetaData, metaData);
    }

    /**
     * 
     * Can't test the the mssql
     */
    @Test
    public void testGetConnectionMetadata4MSSQL() throws Exception {
        /*
         * There are two difficult things:
         * 
         * - can't mock the class name.
         * 
         * - more difficult to verify JtdsMetadataAdapter by MetadataService
         */
        fail("Need check this again. and find a way to test for mssql"); //$NON-NLS-1$
    }

    @Test
    public void testGetConnectionMetadata4IBMDB2ZOS() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);
        when(metaData.getDatabaseProductName()).thenReturn(EDatabaseTypeName.IBMDB2ZOS.getXmlName());

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(mockConn);

        verify(mockConn).getMetaData();
        verify(metaData, times(2)).getDatabaseProductName();

        Assert.assertNotNull(databaseMetaData);
        Assert.assertTrue(databaseMetaData instanceof DB2ForZosDataBaseMetadata);
    }

    @Test
    public void testGetConnectionMetadata4TERADATA() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);
        when(metaData.getDatabaseProductName()).thenReturn(EDatabaseTypeName.TERADATA.getXmlName());

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(mockConn);

        verify(mockConn, times(2)).getMetaData();
        verify(metaData, times(2)).getDatabaseProductName();

        Assert.assertNotNull(databaseMetaData);
        Assert.assertEquals(databaseMetaData, metaData);
    }

    @Test
    public void testGetConnectionMetadata4TERADATAInSqlMode() throws Exception {
        // same as normal
        testGetConnectionMetadata4TERADATA();
    }

    @Test
    public void testGetConnectionMetadata4SAS() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);
        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);
        when(metaData.getDatabaseProductName()).thenReturn(EDatabaseTypeName.SAS.getXmlName());

        DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(mockConn);

        verify(mockConn).getMetaData();
        verify(metaData, times(2)).getDatabaseProductName();

        Assert.assertNotNull(databaseMetaData);
        Assert.assertTrue(databaseMetaData instanceof SASDataBaseMetadata);
    }

    @Test
    public void testGetConnectionMetadata4Normal() throws Exception {
        Connection mockConn = mock(java.sql.Connection.class);

        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(mockConn.getMetaData()).thenReturn(metaData);

        List<EDatabaseTypeName> neededList = getNeedFakeTypes();

        EDatabaseTypeName[] values = EDatabaseTypeName.values();
        int times = 1;
        for (EDatabaseTypeName value : values) {
            EDatabaseTypeName type = value;
            if (!neededList.contains(type)) {
                when(metaData.getDatabaseProductName()).thenReturn(type.getXmlName());

                DatabaseMetaData databaseMetaData = ExtractMetaDataUtils.getConnectionMetadata(mockConn);

                // because twince
                verify(mockConn, times(2 * times)).getMetaData();
                verify(metaData, times(2 * times)).getDatabaseProductName();
                times++;

                Assert.assertNotNull(databaseMetaData);
                Assert.assertEquals(databaseMetaData, metaData);
            }
        }
    }

    @Test
    public void testNeedFakeDatabaseMetaData() {
        // test null type
        Assert.assertFalse(ExtractMetaDataUtils.needFakeDatabaseMetaData(null, false));

        //
        List<EDatabaseTypeName> neededList = getNeedFakeTypes();
        for (EDatabaseTypeName type : neededList) {
            Assert.assertTrue(ExtractMetaDataUtils.needFakeDatabaseMetaData(type.getXmlName(), true));
        }
        // special for TERADATA
        Assert.assertFalse(ExtractMetaDataUtils.needFakeDatabaseMetaData(EDatabaseTypeName.TERADATA.getXmlName(), false));

        for (EDatabaseTypeName type : EDatabaseTypeName.values()) {
            if (!neededList.contains(type)) {
                Assert.assertFalse(ExtractMetaDataUtils.needFakeDatabaseMetaData(type.getXmlName(), false));
            }
        }

    }

}
