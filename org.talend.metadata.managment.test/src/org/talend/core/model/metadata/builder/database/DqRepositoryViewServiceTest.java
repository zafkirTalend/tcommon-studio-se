/**
 * 
 */
package org.talend.core.model.metadata.builder.database;

import static org.junit.Assert.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.MetadataFillFactory;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.PackageHelper;
import org.talend.cwm.helper.SchemaHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.relational.TdView;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.metadata.constants.TableType;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.Schema;

/**
 * @author zshen
 * 
 */

@PrepareForTest({ MetadataConnectionUtils.class, ConnectionUtils.class, MetadataFillFactory.class, ColumnSetHelper.class,
        ExtractMetaDataUtils.class, PackageHelper.class, CatalogHelper.class, SchemaHelper.class, ConnectionHelper.class })
public class DqRepositoryViewServiceTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#getColumns(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.ColumnSet, java.lang.String, boolean)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public void testGetColumns() throws SQLException {
        List<TdColumn> columns1 = null;
        List<TdColumn> columns2 = null;
        // mock TeraDatabaseMetaData
        TeradataDataBaseMetadata metaData = Mockito.mock(TeradataDataBaseMetadata.class);
        // ~mock

        // mock DatabaseConnection
        DatabaseConnection mockDbConn = Mockito.mock(DatabaseConnection.class);
        Mockito.when(mockDbConn.getDatabaseType()).thenReturn(EDatabaseTypeName.TERADATA.getXmlName());
        Mockito.when(mockDbConn.getSID()).thenReturn("talendDB");//$NON-NLS-1$
        Mockito.when(mockDbConn.isSQLMode()).thenReturn(true);
        // ~DatabaseConnection

        // prepare data for reConn
        TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
        java.sql.Connection sqlConn = Mockito.mock(java.sql.Connection.class);
        ReturnCode rc = new ReturnCode(true);
        reConn.setOk(true);
        reConn.setObject(sqlConn);
        try {
            Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
        // ~reConn

        // prepare data for columnSet
        ColumnSet mockColumnSet = Mockito.mock(ColumnSet.class);
        // ~columnSet

        // prepare data for columnPattern
        String columnPattern = null;
        // ~columnPattern

        // prepare data for loadFromDB
        boolean loadFromDB = true;
        // ~loadFromDB

        // prepare data for FillFactory
        List<TdColumn> returnColumns = new ArrayList<TdColumn>();
        // ~FillFactory
        MetadataFillFactory metadataMock = Mockito.mock(MetadataFillFactory.class);
        Mockito.when(
                metadataMock.fillColumns(Mockito.eq(mockColumnSet), (DatabaseMetaData) Mockito.any(),
                        (List<String>) Mockito.isNull(), (String) Mockito.isNull())).thenReturn(returnColumns);
        // MetadataFillFactory.getDBInstance()
        PowerMockito.mockStatic(MetadataFillFactory.class);
        // EasyMock.expect(MetadataFillFactory.getDBInstance()).andReturn(metadataMock);
        Mockito.when(MetadataFillFactory.getDBInstance()).thenReturn(metadataMock);
        // Mockito.doReturn(returnColumns).when(spyFillFactory).fillColumns(mockColumnSet, metaData, null, null);
        // MetadataConnectionUtils.checkConnection(mockDbConn)
        PowerMockito.mockStatic(MetadataConnectionUtils.class);
        // EasyMock.expect(MetadataConnectionUtils.checkConnection(mockDbConn)).andReturn(reConn);
        Mockito.when(MetadataConnectionUtils.checkConnection(mockDbConn)).thenReturn(reConn);

        // ConnectionUtils.closeConnection(sqlConn)
        PowerMockito.mockStatic(ConnectionUtils.class);
        // EasyMock.expect(ConnectionUtils.closeConnection(sqlConn)).andReturn(rc);
        Mockito.when(ConnectionUtils.closeConnection(sqlConn)).thenReturn(rc);
        // ColumnSetHelper.getColumns(mockColumnSet)
        PowerMockito.mockStatic(ColumnSetHelper.class);
        // EasyMock.expect(ColumnSetHelper.getColumns(mockColumnSet)).andReturn(returnColumns);
        Mockito.when(ColumnSetHelper.getColumns(mockColumnSet)).thenReturn(returnColumns);

        PowerMockito.mockStatic(ConnectionHelper.class);
        Mockito.when(ConnectionHelper.getOtherParameter((ModelElement) Mockito.any())).thenReturn(""); //$NON-NLS-1$
        // ExtractMetaDataUtils.getDatabaseMetaData
        // PowerMock.mockStatic(ExtractMetaDataUtils.class);
        // EasyMock.expect(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn,mockDbConn)).andReturn(metaData);

        // PowerMock.replay(MetadataConnectionUtils.class, ConnectionUtils.class, MetadataFillFactory.class,
        // ColumnSetHelper.class);

        try {
            columns1 = DqRepositoryViewService.getColumns(mockDbConn, mockColumnSet, columnPattern, loadFromDB);
            loadFromDB = false;
            columns2 = DqRepositoryViewService.getColumns(mockDbConn, mockColumnSet, columnPattern, loadFromDB);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertSame(returnColumns, columns1);
        assertSame(returnColumns, columns2);
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsTable(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Catalog, java.lang.String)}
     * .
     * 
     */
    @Test
    public void testIsContainsTableConnectionCatalogString() {
        String catalogName = "catalog1";//$NON-NLS-1$
        String tablePattern = null;
        boolean containsTable = false;
        Connection dataProvider = null;
        DatabaseMetaData metaData = null;
        java.sql.Connection sqlConn = null;
        Catalog catalog = null;
        String[] tableType = new String[] { TableType.TABLE.toString() };
        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            Mockito.when(tables.next()).thenReturn(true);

            // ~ResultSet
            // mock TeraDatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType

            Mockito.when(metaData.getTables(catalogName, null, tablePattern, tableType)).thenReturn(tables);

            // ~mock
            // mock dataProvider
            // 1.case of DatabaseConnection
            dataProvider = Mockito.mock(DatabaseConnection.class);
            // 2.case of others if have
            // ~dataProvider
            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);

            Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
            Mockito.when(sqlConn.isClosed()).thenReturn(true);

            // ~reConn

            // mock catalog
            catalog = Mockito.mock(Catalog.class);
            Mockito.when(catalog.getName()).thenReturn(catalogName);
            // ~catalog

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getCatalogOrSchema(catalog)).thenReturn(catalog);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class, PackageHelper.class);

            containsTable = DqRepositoryViewService.isContainsTable(dataProvider, catalog, tablePattern);

            Mockito.verify(metaData).getTables(catalogName, null, tablePattern, tableType);
            Mockito.verify(tables).next();
            // Mockito.verify(sqlConn).getMetaData();
            Mockito.verify(sqlConn).isClosed();
            Mockito.verify(catalog).getName();

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        Mockito.verifyZeroInteractions(metaData, tables, dataProvider, sqlConn, catalog);
        assertTrue(containsTable);
        // fail("Not yet implemented");
    }

    // //
    // /**
    // * Test method for
    // * {@link
    // org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsTable(org.talend.core.model.metadata.builder.connection.Connection,
    // orgomg.cwm.resource.relational.Schema, java.lang.String)}
    // * .
    // */
    @Test
    public void testIsContainsTableConnectionSchemaString() {
        String schemaName = "schema1";//$NON-NLS-1$
        String tablePattern = null;
        boolean containsTable = false;
        Connection dataProvider = null;
        DatabaseMetaData metaData = null;
        java.sql.Connection sqlConn = null;
        Schema schema = null;
        String[] tableType = new String[] { TableType.TABLE.toString() };
        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            Mockito.when(tables.next()).thenReturn(true);

            // ~ResultSet
            // mock TeraDatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType

            Mockito.when(metaData.getTables(null, schemaName, tablePattern, tableType)).thenReturn(tables);

            // ~mock
            // mock dataProvider
            // 1.case of DatabaseConnection
            dataProvider = Mockito.mock(DatabaseConnection.class);
            // 2.case of others if have
            // ~dataProvider
            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);

            Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
            Mockito.when(sqlConn.isClosed()).thenReturn(true);

            // ~reConn

            // mock catalog
            schema = Mockito.mock(Schema.class);
            Mockito.when(schema.getName()).thenReturn(schemaName);
            // ~catalog

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getCatalogOrSchema(schema)).thenReturn(schema);
            // PackageHelper.getParentPackage
            Mockito.when(PackageHelper.getParentPackage(schema)).thenReturn(null);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class, PackageHelper.class);

            containsTable = DqRepositoryViewService.isContainsTable(dataProvider, schema, tablePattern);

            Mockito.verify(metaData).getTables(null, schemaName, tablePattern, tableType);
            Mockito.verify(tables).next();
            // Mockito.verify(sqlConn).getMetaData();
            Mockito.verify(sqlConn).isClosed();
            Mockito.verify(schema).getName();

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        Mockito.verifyZeroInteractions(metaData, tables, dataProvider, sqlConn, schema);
        assertTrue(containsTable);
        // fail("Not yet implemented");
    }

    //
    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsView(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Catalog, java.lang.String)}
     * .
     */
    @Test
    public void testIsContainsViewConnectionCatalogString() {
        String catalogName = "catalog1";//$NON-NLS-1$
        String tablePattern = null;
        boolean containsTable = false;
        Connection dataProvider = null;
        DatabaseMetaData metaData = null;
        java.sql.Connection sqlConn = null;
        Catalog catalog = null;
        String[] tableType = new String[] { TableType.VIEW.toString() };
        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            Mockito.when(tables.next()).thenReturn(true);

            // ~ResultSet
            // mock TeraDatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType

            Mockito.when(metaData.getTables(catalogName, null, tablePattern, tableType)).thenReturn(tables);

            // ~mock
            // mock dataProvider
            // 1.case of DatabaseConnection
            dataProvider = Mockito.mock(DatabaseConnection.class);
            // 2.case of others if have
            // ~dataProvider
            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);

            Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
            Mockito.when(sqlConn.isClosed()).thenReturn(true);

            // ~reConn

            // mock catalog
            catalog = Mockito.mock(Catalog.class);
            Mockito.when(catalog.getName()).thenReturn(catalogName);
            // ~catalog

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getCatalogOrSchema(catalog)).thenReturn(catalog);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class, PackageHelper.class);

            containsTable = DqRepositoryViewService.isContainsView(dataProvider, catalog, tablePattern);

            Mockito.verify(metaData).getTables(catalogName, null, tablePattern, tableType);
            Mockito.verify(tables).next();
            // Mockito.verify(sqlConn).getMetaData();
            Mockito.verify(sqlConn).isClosed();
            Mockito.verify(catalog).getName();

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        Mockito.verifyZeroInteractions(metaData, tables, dataProvider, sqlConn, catalog);
        assertTrue(containsTable);
        // fail("Not yet implemented");
    }

    //
    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsView(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Schema, java.lang.String)}
     * .
     */
    @Test
    public void testIsContainsViewConnectionSchemaString() {
        String schemaName = "schema1";//$NON-NLS-1$
        String tablePattern = null;
        boolean containsTable = false;
        Connection dataProvider = null;
        DatabaseMetaData metaData = null;
        java.sql.Connection sqlConn = null;
        Schema schema = null;
        String[] tableType = new String[] { TableType.VIEW.toString() };
        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            Mockito.when(tables.next()).thenReturn(true);

            // ~ResultSet
            // mock TeraDatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType

            Mockito.when(metaData.getTables(null, schemaName, tablePattern, tableType)).thenReturn(tables);

            // ~mock
            // mock dataProvider
            // 1.case of DatabaseConnection
            dataProvider = Mockito.mock(DatabaseConnection.class);
            // 2.case of others if have
            // ~dataProvider
            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);

            Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
            Mockito.when(sqlConn.isClosed()).thenReturn(true);

            // ~reConn

            // mock catalog
            schema = Mockito.mock(Schema.class);
            Mockito.when(schema.getName()).thenReturn(schemaName);
            // ~catalog

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getCatalogOrSchema(schema)).thenReturn(schema);
            // PackageHelper.getParentPackage
            Mockito.when(PackageHelper.getParentPackage(schema)).thenReturn(null);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class, PackageHelper.class);

            containsTable = DqRepositoryViewService.isContainsView(dataProvider, schema, tablePattern);

            Mockito.verify(metaData).getTables(null, schemaName, tablePattern, tableType);
            Mockito.verify(tables).next();
            // Mockito.verify(sqlConn).getMetaData();
            Mockito.verify(sqlConn).isClosed();
            Mockito.verify(schema).getName();

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        Mockito.verifyZeroInteractions(metaData, tables, dataProvider, sqlConn, schema);
        assertTrue(containsTable);
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#getTables(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Catalog, java.lang.String, boolean)}
     * .
     */
    @Test
    public void testGetTablesConnectionCatalogStringBoolean() {
        java.sql.Connection sqlConn = null;
        DatabaseMetaData metaData = null;
        String catalogName = "tbi";//$NON-NLS-1$
        List<TdTable> tableList = new ArrayList<TdTable>();
        List<TdTable> retableList = new ArrayList<TdTable>();
        List<TdTable> resultList1 = null;
        List<TdTable> resultList2 = null;
        List<TdTable> resultList3 = null;
        String tablePattern = null;
        boolean loadFromDB = true;
        // mock TdTable
        TdTable tableMock = Mockito.mock(TdTable.class);
        // ~TdTable
        retableList.add(tableMock);

        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            // mock Connection
            Connection dataProvider = Mockito.mock(DatabaseConnection.class);
            // ~Connection
            // mock Catalog
            Catalog catalog = Mockito.mock(Catalog.class);
            Mockito.when(catalog.getName()).thenReturn(catalogName);
            // ~Catalog

            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);
            ReturnCode rc = new ReturnCode(true);

            // mock DatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType
            // Mockito.when(metaData.getTables(null, schemaName, tablePattern, tableType)).thenReturn(tables);

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getTables(catalog)).thenReturn(tableList);

            // ~FillFactory
            MetadataFillFactory metadataMock = Mockito.mock(MetadataFillFactory.class);
            Mockito.when(
                    metadataMock.fillTables((orgomg.cwm.objectmodel.core.Package) Mockito.eq(catalog), Mockito.eq(metaData),
                            Mockito.anyList(), Mockito.eq(tablePattern), (String[]) Mockito.any())).thenReturn(retableList);

            // Mockito.when(
            // metadataMock.fillColumns(Mockito.eq(mockColumnSet), (DatabaseMetaData) Mockito.any(),
            // (List<String>) Mockito.isNull(), (String) Mockito.isNull())).thenReturn(returnColumns);
            // MetadataFillFactory.getDBInstance()
            PowerMockito.mockStatic(MetadataFillFactory.class);
            Mockito.when(MetadataFillFactory.getDBInstance()).thenReturn(metadataMock);
            // ConnectionUtils.closeConnection(sqlConn)
            PowerMockito.mockStatic(ConnectionUtils.class);
            Mockito.when(ConnectionUtils.closeConnection(sqlConn)).thenReturn(rc);

            // CatalogHelper.getTables
            PowerMockito.mockStatic(CatalogHelper.class);
            Mockito.when(CatalogHelper.getTables(catalog)).thenReturn(retableList);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class, PackageHelper.class,
            // MetadataFillFactory.class, CatalogHelper.class, ConnectionUtils.class);
            // case1: catalog no tables
            resultList1 = DqRepositoryViewService.getTables(dataProvider, catalog, tablePattern, loadFromDB);
            // case2:catalog hava tables
            tableList.add(tableMock);
            resultList2 = DqRepositoryViewService.getTables(dataProvider, catalog, tablePattern, loadFromDB);
            // // case3:get tables from file
            loadFromDB = false;
            resultList3 = DqRepositoryViewService.getTables(dataProvider, catalog, tablePattern, loadFromDB);

            Mockito.verify(catalog, Mockito.times(2)).getName();
            Mockito.verify(metadataMock, Mockito.times(2)).fillTables((orgomg.cwm.objectmodel.core.Package) Mockito.eq(catalog),
                    Mockito.eq(metaData), Mockito.anyList(), Mockito.eq(tablePattern), (String[]) Mockito.any());
            Mockito.verify(metadataMock).setLinked(true);
            Mockito.verify(metadataMock).setLinked(false);
            Mockito.verifyZeroInteractions(tableMock, tables, dataProvider, catalog, sqlConn, metaData, metadataMock);

            assertTrue(resultList1.size() == 1);
            assertTrue(resultList2.size() == 1);
            assertTrue(resultList3.size() == 1);

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#getTables(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Schema, java.lang.String, boolean)}
     * .
     */
    @Test
    public void testGetTablesConnectionSchemaStringBoolean() {
        java.sql.Connection sqlConn = null;
        DatabaseMetaData metaData = null;
        String schemaName = "schema1";//$NON-NLS-1$

        List<TdTable> tableList = new ArrayList<TdTable>();
        List<TdTable> retableList = new ArrayList<TdTable>();

        List<TdTable> resultList1 = null;
        List<TdTable> resultList2 = null;
        List<TdTable> resultList3 = null;
        String tablePattern = null;
        boolean loadFromDB = true;
        // mock TdTable
        TdTable tableMock = Mockito.mock(TdTable.class);
        // ~TdTable
        retableList.add(tableMock);

        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            // mock Connection
            Connection dataProvider = Mockito.mock(DatabaseConnection.class);
            // ~Connection
            // mock Catalog
            Catalog catalog = Mockito.mock(Catalog.class);
            // Mockito.when(methodCall)
            // ~Catalog
            // mock Schema
            Schema schema = Mockito.mock(Schema.class);
            // Mockito.when(schema.getName()).thenReturn(schemaName);
            // ~Schema

            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);
            ReturnCode rc = new ReturnCode(true);

            // mock DatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType
            // Mockito.when(metaData.getTables(null, schemaName, tablePattern, tableType)).thenReturn(tables);

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getTables(schema)).thenReturn(tableList);

            // ~FillFactory
            MetadataFillFactory metadataMock = Mockito.mock(MetadataFillFactory.class);
            Mockito.when(
                    metadataMock.fillTables((orgomg.cwm.objectmodel.core.Package) Mockito.eq(schema), Mockito.eq(metaData),
                            Mockito.anyList(), Mockito.eq(tablePattern), (String[]) Mockito.any())).thenReturn(retableList)
                    .thenReturn(retableList);

            // Mockito.when(
            // metadataMock.fillColumns(Mockito.eq(mockColumnSet), (DatabaseMetaData) Mockito.any(),
            // (List<String>) Mockito.isNull(), (String) Mockito.isNull())).thenReturn(returnColumns);
            // MetadataFillFactory.getDBInstance()
            PowerMockito.mockStatic(MetadataFillFactory.class);
            Mockito.when(MetadataFillFactory.getDBInstance()).thenReturn(metadataMock);
            // ConnectionUtils.closeConnection(sqlConn)
            PowerMockito.mockStatic(ConnectionUtils.class);
            Mockito.when(ConnectionUtils.closeConnection(sqlConn)).thenReturn(rc);

            // SchemaHelper.getTables
            PowerMockito.mockStatic(SchemaHelper.class);
            Mockito.when(SchemaHelper.getTables(schema)).thenReturn(retableList);
            // CatalogHelper.getParentCatalog
            PowerMockito.mockStatic(CatalogHelper.class);
            Mockito.when(CatalogHelper.getParentCatalog(schema)).thenReturn(catalog);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class,
            // PackageHelper.class,MetadataFillFactory.class, CatalogHelper.class, SchemaHelper.class,
            // ConnectionUtils.class);
            // case1: catalog no tables
            resultList1 = DqRepositoryViewService.getTables(dataProvider, schema, tablePattern, loadFromDB);
            // case2:catalog hava tables
            tableList.add(tableMock);
            resultList2 = DqRepositoryViewService.getTables(dataProvider, schema, tablePattern, loadFromDB);
            // case3:get tables from file
            loadFromDB = false;
            resultList3 = DqRepositoryViewService.getTables(dataProvider, schema, tablePattern, loadFromDB);

            // Mockito.verify(schema, Mockito.times(2)).getName();
            Mockito.verify(metadataMock, Mockito.times(2)).fillTables((orgomg.cwm.objectmodel.core.Package) Mockito.eq(schema),
                    Mockito.eq(metaData), Mockito.anyList(), Mockito.eq(tablePattern), (String[]) Mockito.any());
            Mockito.verify(metadataMock).setLinked(true);
            Mockito.verify(metadataMock).setLinked(false);
            Mockito.verifyZeroInteractions(tableMock, tables, dataProvider, schema, sqlConn, metaData, metadataMock, catalog);

            assertTrue(resultList1.size() == 1);
            assertTrue(resultList2.size() == 1);
            assertTrue(resultList3.size() == 1);

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#getViews(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Catalog, java.lang.String, boolean)}
     * .
     */
    @Test
    public void testGetViewsConnectionCatalogStringBoolean() {
        java.sql.Connection sqlConn = null;
        DatabaseMetaData metaData = null;
        String catalogName = "tbi";//$NON-NLS-1$
        List<TdView> tableList = new ArrayList<TdView>();
        List<TdView> retableList = new ArrayList<TdView>();
        List<TdView> resultList1 = null;
        List<TdView> resultList2 = null;
        List<TdView> resultList3 = null;
        String tablePattern = null;
        boolean loadFromDB = true;
        // mock TdTable
        TdView tableMock = Mockito.mock(TdView.class);
        // ~TdTable
        retableList.add(tableMock);

        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            // mock Connection
            Connection dataProvider = Mockito.mock(DatabaseConnection.class);
            // ~Connection
            // mock Catalog
            Catalog catalog = Mockito.mock(Catalog.class);
            // Mockito.when(catalog.getName()).thenReturn(catalogName);
            // ~Catalog

            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);
            ReturnCode rc = new ReturnCode(true);

            // mock DatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType
            // Mockito.when(metaData.getTables(null, schemaName, tablePattern, tableType)).thenReturn(tables);

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getViews(catalog)).thenReturn(tableList);

            // ~FillFactory
            MetadataFillFactory metadataMock = Mockito.mock(MetadataFillFactory.class);
            Mockito.when(
                    metadataMock.fillViews(Mockito.eq(catalog), Mockito.eq(metaData), Mockito.anyList(), Mockito.eq(tablePattern)))
                    .thenReturn(retableList).thenReturn(retableList);

            // Mockito.when(
            // metadataMock.fillColumns(Mockito.eq(mockColumnSet), (DatabaseMetaData) Mockito.any(),
            // (List<String>) Mockito.isNull(), (String) Mockito.isNull())).thenReturn(returnColumns);
            // MetadataFillFactory.getDBInstance()
            PowerMockito.mockStatic(MetadataFillFactory.class);
            Mockito.when(MetadataFillFactory.getDBInstance()).thenReturn(metadataMock);
            // ConnectionUtils.closeConnection(sqlConn)
            PowerMockito.mockStatic(ConnectionUtils.class);
            Mockito.when(ConnectionUtils.closeConnection(sqlConn)).thenReturn(rc);

            // CatalogHelper.getTables
            PowerMockito.mockStatic(CatalogHelper.class);
            Mockito.when(CatalogHelper.getViews(catalog)).thenReturn(retableList);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class,
            // PackageHelper.class,MetadataFillFactory.class, CatalogHelper.class, ConnectionUtils.class);
            // case1: catalog no tables
            resultList1 = DqRepositoryViewService.getViews(dataProvider, catalog, tablePattern, loadFromDB);
            // case2:catalog hava tables
            tableList.add(tableMock);
            resultList2 = DqRepositoryViewService.getViews(dataProvider, catalog, tablePattern, loadFromDB);
            // // case3:get tables from file
            loadFromDB = false;
            resultList3 = DqRepositoryViewService.getViews(dataProvider, catalog, tablePattern, loadFromDB);

            // Mockito.verify(catalog, Mockito.times(2)).getName();
            Mockito.verify(metadataMock, Mockito.times(2)).fillViews(Mockito.eq(catalog), Mockito.eq(metaData),
                    Mockito.anyList(), Mockito.eq(tablePattern));
            Mockito.verify(metadataMock).setLinked(true);
            Mockito.verify(metadataMock).setLinked(false);
            Mockito.verifyZeroInteractions(tableMock, tables, dataProvider, catalog, sqlConn, metaData, metadataMock);

            assertTrue(resultList1.size() == 1);
            assertTrue(resultList2.size() == 1);
            assertTrue(resultList3.size() == 1);

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#getViews(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Schema, java.lang.String, boolean)}
     * .
     */
    @Test
    public void testGetViewsConnectionSchemaStringBoolean() {
        java.sql.Connection sqlConn = null;
        DatabaseMetaData metaData = null;
        String schemaName = "schema1";//$NON-NLS-1$

        List<TdView> tableList = new ArrayList<TdView>();
        List<TdView> retableList = new ArrayList<TdView>();

        List<TdView> resultList1 = null;
        List<TdView> resultList2 = null;
        List<TdView> resultList3 = null;
        String tablePattern = null;
        boolean loadFromDB = true;
        // mock TdTable
        TdView tableMock = Mockito.mock(TdView.class);
        // ~TdTable
        retableList.add(tableMock);

        // mock ResultSet
        ResultSet tables = Mockito.mock(ResultSet.class);
        try {
            // mock Connection
            Connection dataProvider = Mockito.mock(DatabaseConnection.class);
            // ~Connection
            // mock Catalog
            Catalog catalog = Mockito.mock(Catalog.class);
            // Mockito.when(methodCall)
            // ~Catalog
            // mock Schema
            Schema schema = Mockito.mock(Schema.class);
            // Mockito.when(schema.getName()).thenReturn(schemaName);
            // ~Schema

            // prepare data for reConn
            TypedReturnCode<java.sql.Connection> reConn = new TypedReturnCode<java.sql.Connection>();
            sqlConn = Mockito.mock(java.sql.Connection.class);
            reConn.setOk(true);
            reConn.setObject(sqlConn);
            ReturnCode rc = new ReturnCode(true);

            // mock DatabaseMetaData
            metaData = Mockito.mock(DatabaseMetaData.class);
            // catalogOrSchema.getName(), null, tablePattern, tableType
            // Mockito.when(metaData.getTables(null, schemaName, tablePattern, tableType)).thenReturn(tables);

            // MetadataConnectionUtils.checkConnection(mockDbConn)
            PowerMockito.mockStatic(MetadataConnectionUtils.class);
            Mockito.when(MetadataConnectionUtils.checkConnection((DatabaseConnection) dataProvider)).thenReturn(reConn);

            // ExtractMetaDataUtils.getDatabaseMetaData()
            PowerMockito.mockStatic(ExtractMetaDataUtils.class);
            Mockito.when(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn, (DatabaseConnection) dataProvider, false)).thenReturn(
                    metaData);

            // PackageHelper.getCatalogOrSchema()
            PowerMockito.mockStatic(PackageHelper.class);
            Mockito.when(PackageHelper.getViews(schema)).thenReturn(tableList);

            // ~FillFactory
            MetadataFillFactory metadataMock = Mockito.mock(MetadataFillFactory.class);
            Mockito.when(
                    metadataMock.fillViews(Mockito.eq(schema), Mockito.eq(metaData), Mockito.anyList(), Mockito.eq(tablePattern)))
                    .thenReturn(retableList).thenReturn(retableList);

            // Mockito.when(
            // metadataMock.fillColumns(Mockito.eq(mockColumnSet), (DatabaseMetaData) Mockito.any(),
            // (List<String>) Mockito.isNull(), (String) Mockito.isNull())).thenReturn(returnColumns);
            // MetadataFillFactory.getDBInstance()
            PowerMockito.mockStatic(MetadataFillFactory.class);
            Mockito.when(MetadataFillFactory.getDBInstance()).thenReturn(metadataMock);
            // ConnectionUtils.closeConnection(sqlConn)
            PowerMockito.mockStatic(ConnectionUtils.class);
            Mockito.when(ConnectionUtils.closeConnection(sqlConn)).thenReturn(rc);

            // SchemaHelper.getTables
            PowerMockito.mockStatic(SchemaHelper.class);
            Mockito.when(SchemaHelper.getViews(schema)).thenReturn(retableList);
            // CatalogHelper.getParentCatalog
            PowerMockito.mockStatic(CatalogHelper.class);
            Mockito.when(CatalogHelper.getParentCatalog(schema)).thenReturn(catalog);

            // PowerMock.replay(MetadataConnectionUtils.class, ExtractMetaDataUtils.class, PackageHelper.class,
            // MetadataFillFactory.class, CatalogHelper.class, SchemaHelper.class, ConnectionUtils.class);
            // case1: catalog no tables
            resultList1 = DqRepositoryViewService.getViews(dataProvider, schema, tablePattern, loadFromDB);
            // case2:catalog hava tables
            tableList.add(tableMock);
            resultList2 = DqRepositoryViewService.getViews(dataProvider, schema, tablePattern, loadFromDB);
            // case3:get tables from file
            loadFromDB = false;
            resultList3 = DqRepositoryViewService.getViews(dataProvider, schema, tablePattern, loadFromDB);

            // Mockito.verify(schema, Mockito.times(2)).getName();
            Mockito.verify(metadataMock, Mockito.times(2)).fillViews(Mockito.eq(schema), Mockito.eq(metaData), Mockito.anyList(),
                    Mockito.eq(tablePattern));
            Mockito.verify(metadataMock).setLinked(true);
            Mockito.verify(metadataMock).setLinked(false);
            Mockito.verifyZeroInteractions(tableMock, tables, dataProvider, schema, sqlConn, metaData, metadataMock, catalog);

            assertTrue(resultList1.size() == 1);
            assertTrue(resultList2.size() == 1);
            assertTrue(resultList3.size() == 1);

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
