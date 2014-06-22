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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.talend.commons.utils.database.DB2ForZosDataBaseMetadata;
import org.talend.commons.utils.database.SASDataBaseMetadata;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.template.EDatabaseConnTemplate;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import orgomg.cwm.objectmodel.core.Expression;

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
        neededList.add(EDatabaseTypeName.AS400);
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
        Assert.assertEquals(actualResult, value);
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

    @Test
    public void testGetMeataConnectionSchema4EmptyURL() {
        IMetadataConnection metadataConnection = mock(IMetadataConnection.class);
        when(metadataConnection.getSchema()).thenReturn("schemaTest");
        when(metadataConnection.getDbType()).thenReturn(EDatabaseConnTemplate.GENERAL_JDBC.getDBDisplayName());
        when(metadataConnection.getUrl()).thenReturn("");
        Assert.assertEquals(ExtractMetaDataUtils.getMeataConnectionSchema(metadataConnection), "schemaTest");
        // verify
        verify(metadataConnection).getSchema();
        verify(metadataConnection).getDbType();
        verify(metadataConnection).getUrl();
    }

    @Test
    public void testGetMeataConnectionSchema4URL1() {
        IMetadataConnection metadataConnection = mock(IMetadataConnection.class);
        when(metadataConnection.getSchema()).thenReturn("schemaTest");
        when(metadataConnection.getUsername()).thenReturn("userNameTest");
        when(metadataConnection.getDbType()).thenReturn(EDatabaseConnTemplate.GENERAL_JDBC.getDBDisplayName());
        when(metadataConnection.getUrl()).thenReturn(EDatabaseConnTemplate.ORACLEFORSID.getUrlTemplate(null));
        Assert.assertEquals(ExtractMetaDataUtils.getMeataConnectionSchema(metadataConnection), metadataConnection.getUsername()
                .toUpperCase());
        // verify
        verify(metadataConnection).getSchema();
        verify(metadataConnection).getDbType();
        verify(metadataConnection).getUrl();
        verify(metadataConnection, times(2)).getUsername();
    }

    @Test
    public void testGetMeataConnectionSchema4Null() {
        IMetadataConnection metadataConnection = mock(IMetadataConnection.class);
        when(metadataConnection.getSchema()).thenReturn(null);
        when(metadataConnection.getUsername()).thenReturn(null);
        when(metadataConnection.getDbType()).thenReturn(null);
        when(metadataConnection.getUrl()).thenReturn(null);
        Assert.assertNull(ExtractMetaDataUtils.getMeataConnectionSchema(metadataConnection));
        // verify
        verify(metadataConnection).getSchema();
        verify(metadataConnection).getDbType();
        verify(metadataConnection).getUrl();
    }

    @Test
    public void testGetDBConnectionSchema() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.getUiSchema()).thenReturn("schemaTest");
        Assert.assertEquals(ExtractMetaDataUtils.getDBConnectionSchema(conn), "schemaTest");
    }

    @Test
    public void testHandleDefaultValue4SystemFunctions_Contained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getSystemFunctions()).thenReturn("abc,bodyTest");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        when(metadataColumn.getTalendType()).thenReturn("id_Object");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        when(initialValue.getBody()).thenReturn("bodyTest");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        // verify
        verify(metadataColumn).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getSystemFunctions();
        verify(initialValue).getBody();
        verify(initialValue).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4SystemFunctions_NotContained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getSystemFunctions()).thenReturn("abc,xyz");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        // test integer javaType
        when(metadataColumn.getTalendType()).thenReturn("id_Integer");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        // don't contain
        when(initialValue.getBody()).thenReturn("bodyTest");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        Assert.assertEquals(initialValue.getBody(), "bodyTest");
        // verify
        verify(metadataColumn, times(2)).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getSystemFunctions();
        verify(initialValue, times(2)).getBody();
        verify(initialValue, times(2)).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4NumericFunctions_Contained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getNumericFunctions()).thenReturn("abc,bodyTest");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        when(metadataColumn.getTalendType()).thenReturn("id_Float");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        when(initialValue.getBody()).thenReturn("bodyTest");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        // verify
        verify(metadataColumn).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getNumericFunctions();
        verify(initialValue).getBody();
        verify(initialValue).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4NumericFunctions_NotContained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getNumericFunctions()).thenReturn("abc,xyz");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        // test bigDecimal javaType
        when(metadataColumn.getTalendType()).thenReturn("id_BigDecimal");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        // don't contain
        when(initialValue.getBody()).thenReturn("123");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        Assert.assertEquals(initialValue.getBody(), "123");
        // verify
        verify(metadataColumn, times(2)).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getNumericFunctions();
        verify(initialValue, times(2)).getBody();
        verify(initialValue, times(2)).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4StringFunctions_Contained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getStringFunctions()).thenReturn("abc,bodyTest");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        when(metadataColumn.getTalendType()).thenReturn("id_String");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        when(initialValue.getBody()).thenReturn("bodyTest");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        // verify
        verify(metadataColumn).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getStringFunctions();
        verify(initialValue).getBody();
        verify(initialValue).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4StringFunctions_NotContained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getStringFunctions()).thenReturn("abc,xyz");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        // test character javaType
        when(metadataColumn.getTalendType()).thenReturn("id_Character");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        // don't contain
        when(initialValue.getBody()).thenReturn("a");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        Assert.assertEquals(initialValue.getBody(), "a");
        // verify
        verify(metadataColumn, times(2)).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getStringFunctions();
        verify(initialValue, times(2)).getBody();
        verify(initialValue, times(2)).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4TimeDateFunctions_Contained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getTimeDateFunctions()).thenReturn("abc,bodyTest");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        when(metadataColumn.getTalendType()).thenReturn("id_Date");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        when(initialValue.getBody()).thenReturn("bodyTest");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        // verify
        verify(metadataColumn).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getTimeDateFunctions();
        verify(initialValue).getBody();
        verify(initialValue).setBody(initialValue.getBody());
    }

    @Test
    public void testHandleDefaultValue4TimeDateFunctions_NotContained() throws SQLException {
        DatabaseMetaData dbMetadata = mock(DatabaseMetaData.class);
        when(dbMetadata.getTimeDateFunctions()).thenReturn("abc,xyz");

        MetadataColumn metadataColumn = mock(MetadataColumn.class);
        // test date javaType
        when(metadataColumn.getTalendType()).thenReturn("id_Date");

        Expression initialValue = mock(Expression.class);
        when(metadataColumn.getInitialValue()).thenReturn(initialValue);
        // don't contain
        when(initialValue.getBody()).thenReturn("2012-7-27");

        ExtractMetaDataUtils.handleDefaultValue(metadataColumn, dbMetadata);
        Assert.assertEquals(initialValue.getBody(), "2012-7-27");
        // verify
        verify(metadataColumn).getTalendType();
        verify(metadataColumn).getInitialValue();
        verify(dbMetadata, times(2)).getTimeDateFunctions();
        verify(initialValue, times(2)).getBody();
        verify(initialValue, times(2)).setBody(initialValue.getBody());
    }

    @Test
    public void testGetMultiSchems() {
        // null
        Assert.assertNull(ExtractMetaDataUtils.getMultiSchems(null));

        String[] split = ExtractMetaDataUtils.getMultiSchems("hello,word");
        Assert.assertEquals(split[0], "hello");
        Assert.assertEquals(split[1], "word");
    }

    @Test
    public void testRetrieveSchemaPatternForAS4004Null() {
        Assert.assertNull(ExtractMetaDataUtils.retrieveSchemaPatternForAS400(null));
    }

    @Test
    public void testRetrieveSchemaPatternForAS4004EmptyURL() {
        Assert.assertNull(ExtractMetaDataUtils.retrieveSchemaPatternForAS400(""));
    }

    @Test
    public void testRetrieveSchemaPatternForAS4004URL1() {
        // have one library in the url
        String url = "jdbc:as400://127.0.0.1/test;libraries=test;prompt=false";
        String schema = ExtractMetaDataUtils.retrieveSchemaPatternForAS400(url);
        Assert.assertEquals(schema, "test");
        // verify
    }

    @Test
    public void testRetrieveSchemaPatternForAS4004URLMultiLibrary() {
        // multi-library in the url
        String url = "jdbc:as400://127.0.0.1/test;libraries=abc,xyz;prompt=false";
        String schema = ExtractMetaDataUtils.retrieveSchemaPatternForAS400(url);
        Assert.assertEquals(schema, "abc,xyz");
        // verify
    }

    @Test
    public void testRetrieveSchemaPatternForAS4004URL2() {
        // no library in the url
        String url = "jdbc:as400://localhost/test;prompt=false";
        String schema = ExtractMetaDataUtils.retrieveSchemaPatternForAS400(url);
        Assert.assertNull(schema);
    }

    @Test
    public void testGetStringMetaDataInfo_TwoArguments() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final int infoType = 1;
        final String value = "abc";
        when(resultSet.getString(infoType)).thenReturn(value);
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult, value);
        // verify
        verify(resultSet).getString(infoType);

    }

    @Test
    public void testGetStringMetaDataInfo_TwoArguments4SQLException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final int infoType = 1;
        final String value = "abc";
        when(resultSet.getString(infoType)).thenReturn(value);

        // test sql Exception
        doThrow(new SQLException()).when(resultSet).getString(infoType);
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType);
        Assert.assertNull(actualResult);
        // verify
        verify(resultSet).getString(infoType);
    }

    @Test
    public void testGetStringMetaDataInfo_TwoArguments4Exception() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final int infoType = 1;
        final String value = "abc";
        when(resultSet.getString(infoType)).thenReturn(value);

        // test Exception
        doThrow(new IllegalArgumentException()).when(resultSet).getString(infoType);
        String actualResult = ExtractMetaDataUtils.getStringMetaDataInfo(resultSet, infoType);
        Assert.assertNull(actualResult);
        // verify
        verify(resultSet).getString(infoType);
    }

    @Test
    public void testGetIntMetaDataInfo() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        final Integer value = 1;
        when(resultSet.getInt(anyString())).thenReturn(value);
        Integer actualResult = ExtractMetaDataUtils.getIntMetaDataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult, value);
        // verify
        verify(resultSet).getInt(anyString());
    }

    @Test
    public void testGetIntMetaDataInfo4SQLException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        final Integer value = 2;
        when(resultSet.getInt(anyString())).thenReturn(value);

        // test sql Exception
        doThrow(new SQLException()).when(resultSet).getInt(anyString());
        Integer actualResult = ExtractMetaDataUtils.getIntMetaDataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult, new Integer(0));
        // verify
        verify(resultSet).getInt(anyString());
    }

    @Test
    public void testGetIntMetaDataInfo4Exception() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        final Integer value = 3;
        when(resultSet.getInt(anyString())).thenReturn(value);

        // test Exception
        doThrow(new IllegalArgumentException()).when(resultSet).getInt(anyString());
        Integer actualResult = ExtractMetaDataUtils.getIntMetaDataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult, new Integer(0));
        // verify
        verify(resultSet).getInt(anyString());
    }

    @Test
    public void testGetOracleIntMatadataInfo() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        final Integer value = 8;
        final Integer actualValue = value / 2;
        when(resultSet.getInt(anyString())).thenReturn(value);
        when(resultSet.getString(anyString())).thenReturn("C");

        Integer actualResult = ExtractMetaDataUtils.getOracleIntMatadataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult, actualValue);
        // verify
        verify(resultSet).getInt(anyString());
        verify(resultSet).getString(anyString());
    }

    @Test
    public void testGetOracleIntMatadataInfo4SQLException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        final Integer value = 16;
        when(resultSet.getInt(anyString())).thenReturn(value);
        when(resultSet.getString(anyString())).thenReturn("C");

        // test sql Exception 1
        doThrow(new SQLException()).when(resultSet).getInt(anyString());
        Integer actualResult1 = ExtractMetaDataUtils.getOracleIntMatadataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult1, new Integer(0));

        // test sql Exception 2
        doThrow(new SQLException()).when(resultSet).getString(anyString());
        Integer actualResult2 = ExtractMetaDataUtils.getOracleIntMatadataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult2, new Integer(0));
        // verify
        verify(resultSet, times(2)).getInt(anyString());
    }

    @Test
    public void testGetOracleIntMatadataInfo4Exception() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        final Integer value = 32;
        when(resultSet.getInt(anyString())).thenReturn(value);
        when(resultSet.getString(anyString())).thenReturn("C");

        // test Exception 1
        doThrow(new IllegalArgumentException()).when(resultSet).getInt(anyString());
        Integer actualResult1 = ExtractMetaDataUtils.getOracleIntMatadataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult1, new Integer(0));

        // test Exception 2
        doThrow(new IllegalArgumentException()).when(resultSet).getString(anyString());
        Integer actualResult2 = ExtractMetaDataUtils.getOracleIntMatadataInfo(resultSet, infoType);
        Assert.assertEquals(actualResult2, new Integer(0));
        // verify
        verify(resultSet, times(2)).getInt(anyString());
    }

    @Test
    public void testGetMysqlIntMetaDataInfo() throws SQLException {
        ResultSetMetaData rMetadata = mock(ResultSetMetaData.class);

        final int columnIndex = 1;
        final Integer value = 2;
        when(rMetadata.getPrecision(columnIndex)).thenReturn(value);
        Integer actualResult = ExtractMetaDataUtils.getMysqlIntMetaDataInfo(rMetadata, columnIndex);
        Assert.assertEquals(actualResult, value);
        // verify
        verify(rMetadata).getPrecision(columnIndex);
    }

    @Test
    public void testGetMysqlIntMetaDataInfo4SQLException() throws SQLException {
        ResultSetMetaData rMetadata = mock(ResultSetMetaData.class);

        final int columnIndex = 1;
        final Integer value = 2;
        when(rMetadata.getPrecision(columnIndex)).thenReturn(value);

        // test sql Exception
        doThrow(new SQLException()).when(rMetadata).getPrecision(columnIndex);
        Integer actualResult = ExtractMetaDataUtils.getMysqlIntMetaDataInfo(rMetadata, columnIndex);
        Assert.assertEquals(actualResult, new Integer(0));
        // verify
        verify(rMetadata).getPrecision(columnIndex);
    }

    @Test
    public void testGetMysqlIntMetaDataInfo4Exception() throws SQLException {
        ResultSetMetaData rMetadata = mock(ResultSetMetaData.class);

        final int columnIndex = 1;
        final Integer value = 2;
        when(rMetadata.getPrecision(columnIndex)).thenReturn(value);

        // test Exception
        doThrow(new IllegalArgumentException()).when(rMetadata).getPrecision(columnIndex);
        Integer actualResult = ExtractMetaDataUtils.getMysqlIntMetaDataInfo(rMetadata, columnIndex);
        Assert.assertEquals(actualResult, new Integer(0));
        // verify
        verify(rMetadata).getPrecision(columnIndex);
    }

    @Test
    public void testGetBooleanMetaDataInfo4Null() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        when(resultSet.getString(anyString())).thenReturn(null);
        boolean actualResult = ExtractMetaDataUtils.getBooleanMetaDataInfo(resultSet, infoType);
        Assert.assertFalse(actualResult);
        // verify
        verify(resultSet).getString(anyString());
    }

    @Test
    public void testGetBooleanMetaDataInfo() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        when(resultSet.getString(anyString())).thenReturn("YES");
        boolean actualResult = ExtractMetaDataUtils.getBooleanMetaDataInfo(resultSet, infoType);
        Assert.assertTrue(actualResult);
        // verify
        verify(resultSet).getString(anyString());
    }

    @Test
    public void testGetBooleanMetaDataInfo4SQLException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        // test sql Exception
        doThrow(new SQLException()).when(resultSet).getString(anyString());
        boolean actualResult = ExtractMetaDataUtils.getBooleanMetaDataInfo(resultSet, infoType);
        Assert.assertTrue(actualResult);
        // verify
        verify(resultSet).getString(anyString());
    }

    @Test
    public void testGetBooleanMetaDataInfo4Exception() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        final String infoType = "test";
        // test Exception
        doThrow(new IllegalArgumentException()).when(resultSet).getString(anyString());
        boolean actualResult = ExtractMetaDataUtils.getBooleanMetaDataInfo(resultSet, infoType);
        Assert.assertTrue(actualResult);
        // verify
        verify(resultSet).getString(anyString());
    }
}
