/**
 * 
 */
package org.talend.core.model.metadata.builder.database;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.commons.utils.database.SASDataBaseMetadata;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.utils.ManagementTextUtils;
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
    // @Test
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
    // @Test
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

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4Null() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        DatabaseMetaData metadata = mock(DatabaseMetaData.class);

        final String infoType = "test"; //$NON-NLS-1$
        Assert.assertNull(ExtractMetaDataUtils.getStringMetaDataInfo(null, null, null));
        Assert.assertNull(ExtractMetaDataUtils.getStringMetaDataInfo(null, null, metadata));
        Assert.assertNull(ExtractMetaDataUtils.getStringMetaDataInfo(null, "test", metadata)); //$NON-NLS-1$

        // test null DatabaseMetaData
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, null);

        // only one time
        verify(resultSet).getString(anyString());
        Assert.assertEquals(actualResult, value); // because the matadata is null.

        // second time, and have the metadata,but no functions
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);
        verify(resultSet, times(2)).getString(anyString());
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4SQLException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        // test sql Exception
        doThrow(new SQLException()).when(resultSet).getString(anyString());
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, null);
        // only one time
        verify(resultSet).getString(anyString());
        Assert.assertNull(actualResult);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4Exception() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        // test Exception
        doThrow(new IllegalArgumentException()).when(resultSet).getString(anyString());
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, null);
        // only one time
        verify(resultSet).getString(anyString());
        Assert.assertNull(actualResult);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4SystemFunctions_Contained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);
        // if contain.
        when(metadata.getSystemFunctions()).thenReturn("abc,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getSystemFunctions();
        Assert.assertEquals(actualResult, value);

        // split
        when(metadata.getSystemFunctions()).thenReturn("abc,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getSystemFunctions();
        Assert.assertEquals(actualResult, value);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4SystemFunctions_NotContained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);

        // don't contain
        when(metadata.getSystemFunctions()).thenReturn("123,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getSystemFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);

        // split
        when(metadata.getSystemFunctions()).thenReturn("123,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getSystemFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4NumericFunctions_Contained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);
        // if contain.
        when(metadata.getNumericFunctions()).thenReturn("abc,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getNumericFunctions();
        Assert.assertEquals(actualResult, value);

        // split
        when(metadata.getNumericFunctions()).thenReturn("abc,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getNumericFunctions();
        Assert.assertEquals(actualResult, value);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4NumericFunctions_NotContained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);

        // don't contain
        when(metadata.getNumericFunctions()).thenReturn("123,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getNumericFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);

        // split
        when(metadata.getNumericFunctions()).thenReturn("123,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getNumericFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4StringFunctions_Contained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);
        // if contain.
        when(metadata.getStringFunctions()).thenReturn("abc,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getStringFunctions();
        Assert.assertEquals(actualResult, value);

        // split
        when(metadata.getStringFunctions()).thenReturn("abc,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getStringFunctions();
        Assert.assertEquals(actualResult, value);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4StringFunctions_NotContained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);

        // don't contain
        when(metadata.getStringFunctions()).thenReturn("123,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getStringFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);

        // split
        when(metadata.getStringFunctions()).thenReturn("123,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getStringFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4TimeDateFunctions_Contained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);
        // if contain.
        when(metadata.getTimeDateFunctions()).thenReturn("abc,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getTimeDateFunctions();
        Assert.assertEquals(actualResult, value);

        // split
        when(metadata.getTimeDateFunctions()).thenReturn("abc,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getTimeDateFunctions();
        Assert.assertEquals(actualResult, value);
    }

    @Test
    public void testGetStringMetaDataInfo_ThreeArguments4TimeDateFunctions_NotContained() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "TABLE_NAME"; //$NON-NLS-1$
        final String value = "abc"; //$NON-NLS-1$
        when(resultSet.getString(anyString())).thenReturn(value);

        DatabaseMetaData metadata = mock(DatabaseMetaData.class);

        // don't contain
        when(metadata.getTimeDateFunctions()).thenReturn("123,xyz"); //$NON-NLS-1$
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet).getString(anyString());
        verify(metadata, times(2)).getTimeDateFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);

        // split
        when(metadata.getTimeDateFunctions()).thenReturn("123,  xyz"); //$NON-NLS-1$
        actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType, metadata);

        verify(resultSet, times(2)).getString(anyString());
        verify(metadata, times(4)).getTimeDateFunctions();
        Assert.assertEquals(actualResult, ManagementTextUtils.QUOTATION_MARK + value + ManagementTextUtils.QUOTATION_MARK);
    }

    @Test
    public void testIsOLAPConnection4Null() {

        Assert.assertFalse(ExtractMetaDataUtils.isOLAPConnection(null));

        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(null);

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        Assert.assertFalse(is);

    }

    @Test
    public void testIsOLAPConnection4NullURL() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn(null);

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertFalse(is);

    }

    @Test
    public void testIsOLAPConnection4EmptyURL() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn(""); //$NON-NLS-1$

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertFalse(is);

    }

    @Test
    public void testIsOLAPConnection4URL1() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn("jdbc:xxx://yyy:zzz");//$NON-NLS-1$

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertFalse(is);

    }

    @Test
    public void testIsOLAPConnection4URL2() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn("JDBC:OLAP://yyy:zzz");//$NON-NLS-1$

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertTrue(is);

    }

    @Test
    public void testIsOLAPConnection4URL3() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn("jdbc:olap://yyy:zzz");//$NON-NLS-1$

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertTrue(is);

    }

    @Test
    public void testIsOLAPConnection4URL4() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn("jdbc:olapx://yyy:zzz");//$NON-NLS-1$

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertFalse(is);

    }

    @Test
    public void testIsOLAPConnection4URL5() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getProductId()).thenReturn(EDatabaseTypeName.GENERAL_JDBC.getProduct());
        when(conn.getURL()).thenReturn("jdbc:olap,abc");//$NON-NLS-1$

        boolean is = ExtractMetaDataUtils.isOLAPConnection(conn);
        verify(conn).getProductId();
        verify(conn).getURL();
        Assert.assertFalse(is);

    }

    @Test
    public void testGetDriverClassByDbType() {
        // null
        Assert.assertNull(ExtractMetaDataUtils.getDriverClassByDbType(null));
        // mysql
        Assert.assertEquals(ExtractMetaDataUtils.getDriverClassByDbType(EDatabaseTypeName.MYSQL.getXmlName()),
                "org.gjt.mm.mysql.Driver");
        // oracle
        Assert.assertEquals(ExtractMetaDataUtils.getDriverClassByDbType(EDatabaseTypeName.ORACLEFORSID.getXmlName()),
                "oracle.jdbc.OracleDriver");
        // ibm db2
        Assert.assertEquals(ExtractMetaDataUtils.getDriverClassByDbType(EDatabaseTypeName.IBMDB2.getXmlName()),
                "com.ibm.db2.jcc.DB2Driver");
    }

    @Test
    public void testGetDbTypeByClassNameAndDriverJar() {
        // null
        Assert.assertNull(ExtractMetaDataUtils.getDbTypeByClassNameAndDriverJar(null, null));
        // mysql and DriverJar null
        Assert.assertEquals(ExtractMetaDataUtils.getDbTypeByClassNameAndDriverJar("org.gjt.mm.mysql.Driver", null),
                EDatabaseTypeName.MYSQL.getXmlName());
        // for some dbs use the same driverClassName. will got the first one
        Assert.assertEquals(ExtractMetaDataUtils.getDbTypeByClassNameAndDriverJar("org.postgresql.Driver", null),
                EDatabaseTypeName.GREENPLUM.getXmlName());
        // postgresql
        Assert.assertEquals(
                ExtractMetaDataUtils.getDbTypeByClassNameAndDriverJar("org.postgresql.Driver", "postgresql-8.3-603.jdbc4.jar"),
                EDatabaseTypeName.PSQL.getXmlName());
        //
        Assert.assertEquals(ExtractMetaDataUtils.getDbTypeByClassNameAndDriverJar("sun.jdbc.odbc.JdbcOdbcDriver",
                "mysql-connector-java-5.1.3-bin.jar"), EDatabaseTypeName.ACCESS.getXmlName());
    }
}
