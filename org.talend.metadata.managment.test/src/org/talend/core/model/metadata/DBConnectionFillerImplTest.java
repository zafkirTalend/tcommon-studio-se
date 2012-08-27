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
package org.talend.core.model.metadata;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.*;
import static org.powermock.api.support.membermodification.MemberModifier.*;

import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataFromDataBase;
import org.talend.core.model.metadata.builder.database.TableInfoParameters;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.PackageHelper;
import org.talend.cwm.helper.SchemaHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdTable;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.metadata.constants.GetColumn;
import org.talend.utils.sql.metadata.constants.GetTable;
import org.talend.utils.sql.metadata.constants.TableType;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.Schema;

/**
 * DOC qiongli class global comment. Detailled comment
 */

@PrepareForTest({ StringUtils.class, ExtractMetaDataFromDataBase.class, MetadataConnectionUtils.class, PackageHelper.class,
        ConnectionHelper.class, ProxyRepositoryFactory.class, ExtensionImplementationProvider.class, CatalogHelper.class,
        SchemaHelper.class, ConnectionUtils.class, ColumnSetHelper.class })
public class DBConnectionFillerImplTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

    private Driver driver;

    private DBConnectionFillerImpl dBConnectionFillerImpl;

    @Before
    public void createDBConnectionFillerImpl() throws Exception {
        dBConnectionFillerImpl = new DBConnectionFillerImpl();
        Driver driver = mock(Driver.class);
        DBConnectionFillerImpl.setDriver(driver);
        dBConnectionFillerImpl.setLinked(true);
    }
    /**
     * DOC yyin Comment method "setUp".
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDBConnectionFillerImpl();
    }

    /**
     * DOC yyin Comment method "tearDown".
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillUIConnParams(org.talend.core.model.metadata.IMetadataConnection, org.talend.core.model.metadata.builder.connection.Connection)}.
     */
    @Test
    public void testFillUIConnParams() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillViews(orgomg.cwm.objectmodel.core.Package, java.sql.DatabaseMetaData, java.util.List, java.lang.String)}.
     */
    @Test
    public void testFillViews() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillColumns(orgomg.cwm.resource.relational.ColumnSet, java.sql.DatabaseMetaData, java.util.List, java.lang.String)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public void testFillColumnsColumnSetDatabaseMetaDataListOfStringString_odbcTerdata() throws Exception {
        ColumnSet columnSet = orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createColumnSet();
        columnSet.setName("table1");
        DatabaseMetaData dbJDBCMetadata = mock(DatabaseMetaData.class);
        List<String> columnFilter = new ArrayList<String>();
        Schema schema = mock(Schema.class);
        when(schema.getName()).thenReturn("talend");
        PowerMockito.mockStatic(CatalogHelper.class);
        when(CatalogHelper.getParentCatalog(columnSet)).thenReturn(null);
        PowerMockito.mockStatic(SchemaHelper.class);
        when(SchemaHelper.getParentSchema(columnSet)).thenReturn(schema);
        stub(method(MetadataConnectionUtils.class, "isSybase", DatabaseMetaData.class)).toReturn(false);
        stub(method(MetadataConnectionUtils.class, "isMssql", DatabaseMetaData.class)).toReturn(false);
        PowerMockito.mockStatic(ConnectionUtils.class);
        when(ConnectionUtils.isOdbcTeradata(dbJDBCMetadata)).thenReturn(true);
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString(GetColumn.COLUMN_NAME.name())).thenReturn("column1").thenReturn("column2");
        when(rs.getString(GetColumn.TYPE_NAME.name())).thenReturn("VARCHAR");
        when(rs.getInt(GetColumn.NULLABLE.name())).thenReturn(0);
        when(rs.getInt(GetColumn.DATA_TYPE.name())).thenReturn(1);

        when(rs.getString(GetColumn.REMARKS.name())).thenReturn("");
        when(rs.getString(GetColumn.IS_NULLABLE.name())).thenReturn("YES");
        when(dbJDBCMetadata.getColumns(anyString(), anyString(), anyString(), anyString())).thenReturn(rs);

        DatabaseConnection dbConnection = mock(DatabaseConnection.class);
        PowerMockito.mockStatic(ConnectionHelper.class);
        when(ConnectionHelper.getConnection(columnSet)).thenReturn(dbConnection);

        stub(method(MetadataToolHelper.class, "validateValueForDBType", String.class)).toReturn("VARCHAR");
        PowerMockito.mockStatic(ColumnSetHelper.class);
        stub(method(ColumnSetHelper.class, "addColumns")).toReturn(true);

        List<TdColumn> fillColumns = dBConnectionFillerImpl.fillColumns(columnSet, dbJDBCMetadata, columnFilter, null);
        assertNotNull(fillColumns);
        assertTrue(fillColumns.size() == 2);
        // for odbc teradata,should assert like this
        for (TdColumn tdColumn : fillColumns) {
            assertEquals(tdColumn.getSqlDataType().getNumericPrecision(), 0);
            assertEquals(tdColumn.getSqlDataType().getNumericPrecisionRadix(), 0);
            assertEquals(tdColumn.getLength(), 0);
            assertNull(null, tdColumn.getInitialValue().getBody());
        }

    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillColumns(orgomg.cwm.resource.relational.ColumnSet, org.talend.core.model.metadata.IMetadataConnection, java.sql.DatabaseMetaData, java.util.List, java.lang.String)}.
     */
    @Test
    public void testFillColumnsColumnSetIMetadataConnectionDatabaseMetaDataListOfStringString() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#checkConnection(org.talend.core.model.metadata.IMetadataConnection)}.
     */
    @Test
    public void testCheckConnection() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillSchemas(org.talend.core.model.metadata.builder.connection.Connection, java.sql.DatabaseMetaData, java.util.List)}.
     */
    @Test
    public void testFillSchemas() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillCatalogs(org.talend.core.model.metadata.builder.connection.Connection, java.sql.DatabaseMetaData, java.util.List)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public void testFillCatalogs_OdbcTeradata() throws SQLException {
        DatabaseMetaData dbJDBCMetadata = mock(DatabaseMetaData.class);
        when(dbJDBCMetadata.getDatabaseProductName()).thenReturn("teradata");
        stub(method(ConnectionUtils.class, "isOdbcTeradata", DatabaseMetaData.class)).toReturn(true);
        List<String> catalogFilter = new ArrayList<String>();
        Connection connection = mock(Connection.class);
        when(connection.isContextMode()).thenReturn(false);
        List<Catalog> fillCatalogs = this.dBConnectionFillerImpl.fillCatalogs(connection, dbJDBCMetadata, catalogFilter);
        assertTrue(fillCatalogs.isEmpty());

        
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillSchemaToCatalog(org.talend.core.model.metadata.builder.connection.Connection, java.sql.DatabaseMetaData, orgomg.cwm.resource.relational.Catalog, java.util.List)}.
     */
    @Test
    public void testFillSchemaToCatalog() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#fillAll(orgomg.cwm.objectmodel.core.Package, java.sql.DatabaseMetaData, java.util.List, java.lang.String, java.lang.String[])}.
     */
    @Test
    public void testFillAll() {
        // fail("Not yet implemented");
    }


    @Test
    public void testFillTables_withCatalog() throws SQLException {

        DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
        Catalog packge = mock(Catalog.class);
        String[] tableType = { TableType.TABLE.toString() };
        this.initializeForFillTables(packge, dbmd, tableType, false);
        List<TdTable> result = this.dBConnectionFillerImpl.fillTables(packge, dbmd, new ArrayList<String>(), "", tableType);
        assertNotNull(result);
        assertTrue(result.size() == 2);

    }

    @Test
    public void testFillTables_withSchema() throws SQLException {
        String[] tableType = { TableType.TABLE.toString() };
        DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
        Schema packge = mock(Schema.class);
        this.initializeForFillTables(packge, dbmd, tableType, true);
        List<TdTable> result = this.dBConnectionFillerImpl.fillTables(packge, dbmd, new ArrayList<String>(), "", tableType);
        assertNotNull(result);
        assertTrue(result.size() == 1);

    }

    private void initializeForFillTables(orgomg.cwm.objectmodel.core.Package pack, DatabaseMetaData dbmd, String[] tableType,
            boolean isOracle) throws SQLException {
        when(pack.getName()).thenReturn("tdqPackage");
        PowerMockito.mockStatic(PackageHelper.class);
        when(PackageHelper.getParentPackage(pack)).thenReturn(pack);
        when(PackageHelper.getCatalogOrSchema(pack)).thenReturn(pack);
        Connection con = mock(Connection.class);
        PowerMockito.mockStatic(MetadataConnectionUtils.class);
        when(MetadataConnectionUtils.isAS400(pack)).thenReturn(false);
        when(MetadataConnectionUtils.isOracle(con)).thenReturn(isOracle);
        when(MetadataConnectionUtils.isSybase(dbmd)).thenReturn(false);
        when(MetadataConnectionUtils.isOracle8i(con)).thenReturn(false);
        when(MetadataConnectionUtils.isOracleJDBC(con)).thenReturn(isOracle);
        PowerMockito.mockStatic(ConnectionHelper.class);
        when(ConnectionHelper.getConnection(pack)).thenReturn(con);

        List<String> filterNames = new ArrayList<String>();
        filterNames.add("Table1");
        if (isOracle) {
            java.sql.Connection sqlConn = mock(java.sql.Connection.class);
            when(dbmd.getConnection()).thenReturn(sqlConn);
            Statement stme = mock(Statement.class);
            ResultSet rsTables = mock(ResultSet.class);
            when(sqlConn.createStatement()).thenReturn(stme);
            when(stme.executeQuery(TableInfoParameters.ORACLE_10G_RECBIN_SQL)).thenReturn(rsTables);
            stub(method(ExtractMetaDataFromDataBase.class, "getTableNamesFromQuery")).toReturn(filterNames);
            stub(method(ExtensionImplementationProvider.class, "getInstanceV2", IExtensionPointLimiter.class)).toReturn(
                    new ArrayList<IConfigurationElement>());

        }

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        verify(rs, atMost(5)).next();
        when(rs.getString(GetTable.TABLE_NAME.name())).thenReturn("Table1").thenReturn("Table2");
        when(rs.getString(GetTable.TABLE_TYPE.name())).thenReturn("Table");
        when(rs.getString(GetTable.REMARKS.name())).thenReturn("");
        when(dbmd.getTables("tdqPackage", "tdqPackage", "", tableType)).thenReturn(rs);
        when(dbmd.getTables("tdqPackage", null, "", tableType)).thenReturn(rs);
        stub(method(StringUtils.class, "isBlank")).toReturn(false);
        ProxyRepositoryFactory proxFactory = mock(ProxyRepositoryFactory.class);
        when(proxFactory.getNextId()).thenReturn("abcd1").thenReturn("abcd2");
        stub(method(ProxyRepositoryFactory.class, "getInstance")).toReturn(proxFactory);
    }
    /**
     * Test method for {@link org.talend.core.model.metadata.DBConnectionFillerImpl#setDriver(java.sql.Driver)}.
     */
    @Test
    public void testSetDriver() {
        // fail("Not yet implemented");
    }

}
