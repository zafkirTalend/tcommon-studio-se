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


import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.MetadataFillFactory;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.cwm.helper.ColumnSetHelper;
import org.talend.cwm.helper.PackageHelper;
import org.talend.cwm.relational.TdColumn;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.metadata.constants.TableType;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;

import com.teradata.jdbc.TeraDatabaseMetaData;

import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.Schema;

/**
 * @author zshen
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ MetadataConnectionUtils.class,ConnectionUtils.class,MetadataFillFactory.class,ColumnSetHelper.class,ExtractMetaDataUtils.class,PackageHelper.class})
public class DqRepositoryViewServiceTest {

	/**
	 * Test method for {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#getColumns(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.ColumnSet, java.lang.String, boolean)}.
	 * @throws SQLException 
	 */
	@Test
	public void testGetColumns() throws SQLException {
		List<TdColumn> columns1 =null;
		List<TdColumn> columns2 =null;
		//mock TeraDatabaseMetaData
		TeraDatabaseMetaData metaData=Mockito.mock(TeraDatabaseMetaData.class);
		//~mock
		
		//mock DatabaseConnection
		DatabaseConnection mockDbConn = Mockito.mock(DatabaseConnection.class);
		Mockito.when(mockDbConn.getDatabaseType()).thenReturn(EDatabaseTypeName.TERADATA.getXmlName());
		Mockito.when(mockDbConn.getSID()).thenReturn("talendDB");//$NON-NLS-1$
		Mockito.when(mockDbConn.isSQLMode()).thenReturn(true);
		//~DatabaseConnection
		
		//prepare data for reConn
		TypedReturnCode<java.sql.Connection> reConn=new TypedReturnCode<java.sql.Connection>();
		java.sql.Connection sqlConn=Mockito.mock(java.sql.Connection.class);
		ReturnCode rc = new ReturnCode(true);
		reConn.setOk(true);
		reConn.setObject(sqlConn);
		try {
			Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		//~reConn
		
		//prepare data for columnSet
		ColumnSet mockColumnSet = Mockito.mock(ColumnSet.class);
		//~columnSet
		
		//prepare data for columnPattern
		String columnPattern=null;
		//~columnPattern
		
		//prepare data for loadFromDB
		boolean loadFromDB=true;
		//~loadFromDB
		
		//prepare data for FillFactory
		List<TdColumn> returnColumns = new ArrayList<TdColumn>();
		//~FillFactory
		MetadataFillFactory metadataMock = Mockito.mock(MetadataFillFactory.class);
		Mockito.when(metadataMock.fillColumns(Mockito.eq(mockColumnSet), (TeraDatabaseMetaData)Mockito.any(), (List<String>)Mockito.isNull(), (String)Mockito.isNull())).thenReturn(returnColumns);
//		MetadataFillFactory.getDBInstance()
		PowerMock.mockStatic(MetadataFillFactory.class);
		EasyMock.expect(MetadataFillFactory.getDBInstance()).andReturn(metadataMock);
//		Mockito.doReturn(returnColumns).when(spyFillFactory).fillColumns(mockColumnSet, metaData, null, null);
//		MetadataConnectionUtils.checkConnection(mockDbConn)
		PowerMock.mockStatic(MetadataConnectionUtils.class);
		EasyMock.expect(MetadataConnectionUtils.checkConnection(mockDbConn)).andReturn(reConn);
//		ConnectionUtils.closeConnection(sqlConn)
		PowerMock.mockStatic(ConnectionUtils.class);
		EasyMock.expect(ConnectionUtils.closeConnection(sqlConn)).andReturn(rc);
//		ColumnSetHelper.getColumns(mockColumnSet)
		PowerMock.mockStatic(ColumnSetHelper.class);
		EasyMock.expect(ColumnSetHelper.getColumns(mockColumnSet)).andReturn(returnColumns);
//		ExtractMetaDataUtils.getDatabaseMetaData
//		PowerMock.mockStatic(ExtractMetaDataUtils.class);
//		EasyMock.expect(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn,mockDbConn)).andReturn(metaData);
		
		
		PowerMock.replay(MetadataConnectionUtils.class,ConnectionUtils.class,MetadataFillFactory.class,ColumnSetHelper.class);
		
		try {
			columns1 = DqRepositoryViewService.getColumns(mockDbConn, mockColumnSet, columnPattern, loadFromDB);
			loadFromDB=false;
			columns2 = DqRepositoryViewService.getColumns(mockDbConn, mockColumnSet, columnPattern, loadFromDB);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		Mockito.verify(mockDbConn).getSID();
		Mockito.verify(mockDbConn).getDatabaseType();
		Mockito.verify(mockDbConn).isSQLMode();
//		Mockito.verify(sqlConn).getMetaData();

		Mockito.verifyZeroInteractions(metaData,mockDbConn,sqlConn,mockColumnSet);
		assertSame(returnColumns, columns1);
		assertSame(returnColumns, columns2);
	}

	/**
	 * Test method for {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsTable(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Catalog, java.lang.String)}.
	 * 
	 */
	@Test
	public void testIsContainsTableConnectionCatalogString() {
		String catalogName="catalog1";//$NON-NLS-1$
		String tablePattern=null;
		boolean containsTable =false;
		Connection dataProvider=null;
		DatabaseMetaData metaData=null;
		java.sql.Connection sqlConn=null;
		Catalog catalog=null;
		String[] tableType = new String[] { TableType.TABLE.toString() };
		//mock ResultSet
		ResultSet tables=Mockito.mock(ResultSet.class);
		try {
			Mockito.when(tables.next()).thenReturn(true);
		
		//~ResultSet
		//mock TeraDatabaseMetaData
		metaData=Mockito.mock(DatabaseMetaData.class);
//		catalogOrSchema.getName(), null, tablePattern, tableType
		
		Mockito.when(metaData.getTables(catalogName,null,tablePattern,tableType)).thenReturn(tables);
		
		//~mock
		//mock dataProvider
		//1.case of DatabaseConnection
		dataProvider=Mockito.mock(DatabaseConnection.class);
		//2.case of others if have
		//~dataProvider
		//prepare data for reConn
		TypedReturnCode<java.sql.Connection> reConn=new TypedReturnCode<java.sql.Connection>();
		sqlConn=Mockito.mock(java.sql.Connection.class);
		reConn.setOk(true);
		reConn.setObject(sqlConn);
		
			Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
			Mockito.when(sqlConn.isClosed()).thenReturn(true);
		
		//~reConn
		
		
		//mock catalog
		catalog=Mockito.mock(Catalog.class);
		Mockito.when(catalog.getName()).thenReturn(catalogName);
		//~catalog
		
		
		
//		MetadataConnectionUtils.checkConnection(mockDbConn)
		PowerMock.mockStatic(MetadataConnectionUtils.class);
		EasyMock.expect(MetadataConnectionUtils.checkConnection((DatabaseConnection)dataProvider)).andReturn(reConn);

//		ExtractMetaDataUtils.getDatabaseMetaData()
		PowerMock.mockStatic(ExtractMetaDataUtils.class);
		EasyMock.expect(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn,(DatabaseConnection)dataProvider,false)).andReturn(metaData);

//		PackageHelper.getCatalogOrSchema()
		PowerMock.mockStatic(PackageHelper.class);
		EasyMock.expect(PackageHelper.getCatalogOrSchema(catalog)).andReturn(catalog);

		
		PowerMock.replay(MetadataConnectionUtils.class,ExtractMetaDataUtils.class,PackageHelper.class);
		
			containsTable = DqRepositoryViewService.isContainsTable(dataProvider, catalog, tablePattern);
		
		
			Mockito.verify(metaData).getTables(catalogName,null,tablePattern,tableType);
			Mockito.verify(tables).next();
//			Mockito.verify(sqlConn).getMetaData();
			Mockito.verify(sqlConn).isClosed();
			Mockito.verify(catalog).getName();
		
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		Mockito.verifyZeroInteractions(metaData,tables,dataProvider,sqlConn,catalog);
		assertTrue(containsTable);
//		fail("Not yet implemented");
	}
//
	/**
	 * Test method for {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsTable(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Schema, java.lang.String)}.
	 */
	@Test
	public void testIsContainsTableConnectionSchemaString() {
		String schemaName="schema1";//$NON-NLS-1$
		String tablePattern=null;
		boolean containsTable =false;
		Connection dataProvider=null;
		DatabaseMetaData metaData=null;
		java.sql.Connection sqlConn=null;
		Schema schema=null;
		String[] tableType = new String[] { TableType.TABLE.toString() };
		//mock ResultSet
		ResultSet tables=Mockito.mock(ResultSet.class);
		try {
			Mockito.when(tables.next()).thenReturn(true);
		
		//~ResultSet
		//mock TeraDatabaseMetaData
		metaData=Mockito.mock(DatabaseMetaData.class);
//		catalogOrSchema.getName(), null, tablePattern, tableType
		
		Mockito.when(metaData.getTables(null,schemaName,tablePattern,tableType)).thenReturn(tables);
		
		//~mock
		//mock dataProvider
		//1.case of DatabaseConnection
		dataProvider=Mockito.mock(DatabaseConnection.class);
		//2.case of others if have
		//~dataProvider
		//prepare data for reConn
		TypedReturnCode<java.sql.Connection> reConn=new TypedReturnCode<java.sql.Connection>();
		sqlConn=Mockito.mock(java.sql.Connection.class);
		reConn.setOk(true);
		reConn.setObject(sqlConn);
		
			Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
			Mockito.when(sqlConn.isClosed()).thenReturn(true);
		
		//~reConn
		
		
		//mock catalog
		schema=Mockito.mock(Schema.class);
		Mockito.when(schema.getName()).thenReturn(schemaName);
		//~catalog
		
		
		
//		MetadataConnectionUtils.checkConnection(mockDbConn)
		PowerMock.mockStatic(MetadataConnectionUtils.class);
		EasyMock.expect(MetadataConnectionUtils.checkConnection((DatabaseConnection)dataProvider)).andReturn(reConn);

//		ExtractMetaDataUtils.getDatabaseMetaData()
		PowerMock.mockStatic(ExtractMetaDataUtils.class);
		EasyMock.expect(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn,(DatabaseConnection)dataProvider,false)).andReturn(metaData);

//		PackageHelper.getCatalogOrSchema()
		PowerMock.mockStatic(PackageHelper.class);
		EasyMock.expect(PackageHelper.getCatalogOrSchema(schema)).andReturn(schema);
//		PackageHelper.getParentPackage
		EasyMock.expect(PackageHelper.getParentPackage(schema)).andReturn(null);

		
		PowerMock.replay(MetadataConnectionUtils.class,ExtractMetaDataUtils.class,PackageHelper.class);
		
			containsTable = DqRepositoryViewService.isContainsTable(dataProvider, schema, tablePattern);
		
		
			Mockito.verify(metaData).getTables(null,schemaName,tablePattern,tableType);
			Mockito.verify(tables).next();
//			Mockito.verify(sqlConn).getMetaData();
			Mockito.verify(sqlConn).isClosed();
			Mockito.verify(schema).getName();
		
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		Mockito.verifyZeroInteractions(metaData,tables,dataProvider,sqlConn,schema);
		assertTrue(containsTable);
//		fail("Not yet implemented");
	}
//
	/**
	 * Test method for {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsView(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Catalog, java.lang.String)}.
	 */
	@Test
	public void testIsContainsViewConnectionCatalogString() {
		String catalogName="catalog1";//$NON-NLS-1$
		String tablePattern=null;
		boolean containsTable =false;
		Connection dataProvider=null;
		DatabaseMetaData metaData=null;
		java.sql.Connection sqlConn=null;
		Catalog catalog=null;
		String[] tableType = new String[] { TableType.VIEW.toString() };
		//mock ResultSet
		ResultSet tables=Mockito.mock(ResultSet.class);
		try {
			Mockito.when(tables.next()).thenReturn(true);
		
		//~ResultSet
		//mock TeraDatabaseMetaData
		metaData=Mockito.mock(DatabaseMetaData.class);
//		catalogOrSchema.getName(), null, tablePattern, tableType
		
		Mockito.when(metaData.getTables(catalogName,null,tablePattern,tableType)).thenReturn(tables);
		
		//~mock
		//mock dataProvider
		//1.case of DatabaseConnection
		dataProvider=Mockito.mock(DatabaseConnection.class);
		//2.case of others if have
		//~dataProvider
		//prepare data for reConn
		TypedReturnCode<java.sql.Connection> reConn=new TypedReturnCode<java.sql.Connection>();
		sqlConn=Mockito.mock(java.sql.Connection.class);
		reConn.setOk(true);
		reConn.setObject(sqlConn);
		
			Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
			Mockito.when(sqlConn.isClosed()).thenReturn(true);
		
		//~reConn
		
		
		//mock catalog
		catalog=Mockito.mock(Catalog.class);
		Mockito.when(catalog.getName()).thenReturn(catalogName);
		//~catalog
		
		
		
//		MetadataConnectionUtils.checkConnection(mockDbConn)
		PowerMock.mockStatic(MetadataConnectionUtils.class);
		EasyMock.expect(MetadataConnectionUtils.checkConnection((DatabaseConnection)dataProvider)).andReturn(reConn);

//		ExtractMetaDataUtils.getDatabaseMetaData()
		PowerMock.mockStatic(ExtractMetaDataUtils.class);
		EasyMock.expect(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn,(DatabaseConnection)dataProvider,false)).andReturn(metaData);

//		PackageHelper.getCatalogOrSchema()
		PowerMock.mockStatic(PackageHelper.class);
		EasyMock.expect(PackageHelper.getCatalogOrSchema(catalog)).andReturn(catalog);

		
		PowerMock.replay(MetadataConnectionUtils.class,ExtractMetaDataUtils.class,PackageHelper.class);
		
			containsTable = DqRepositoryViewService.isContainsView(dataProvider, catalog, tablePattern);
		
		
			Mockito.verify(metaData).getTables(catalogName,null,tablePattern,tableType);
			Mockito.verify(tables).next();
//			Mockito.verify(sqlConn).getMetaData();
			Mockito.verify(sqlConn).isClosed();
			Mockito.verify(catalog).getName();
		
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		Mockito.verifyZeroInteractions(metaData,tables,dataProvider,sqlConn,catalog);
		assertTrue(containsTable);
//		fail("Not yet implemented");
	}
//
	/**
	 * Test method for {@link org.talend.core.model.metadata.builder.database.DqRepositoryViewService#isContainsView(org.talend.core.model.metadata.builder.connection.Connection, orgomg.cwm.resource.relational.Schema, java.lang.String)}.
	 */
	@Test
	public void testIsContainsViewConnectionSchemaString() {
		String schemaName="schema1";//$NON-NLS-1$
		String tablePattern=null;
		boolean containsTable =false;
		Connection dataProvider=null;
		DatabaseMetaData metaData=null;
		java.sql.Connection sqlConn=null;
		Schema schema=null;
		String[] tableType = new String[] { TableType.VIEW.toString() };
		//mock ResultSet
		ResultSet tables=Mockito.mock(ResultSet.class);
		try {
			Mockito.when(tables.next()).thenReturn(true);
		
		//~ResultSet
		//mock TeraDatabaseMetaData
		metaData=Mockito.mock(DatabaseMetaData.class);
//		catalogOrSchema.getName(), null, tablePattern, tableType
		
		Mockito.when(metaData.getTables(null,schemaName,tablePattern,tableType)).thenReturn(tables);
		
		//~mock
		//mock dataProvider
		//1.case of DatabaseConnection
		dataProvider=Mockito.mock(DatabaseConnection.class);
		//2.case of others if have
		//~dataProvider
		//prepare data for reConn
		TypedReturnCode<java.sql.Connection> reConn=new TypedReturnCode<java.sql.Connection>();
		sqlConn=Mockito.mock(java.sql.Connection.class);
		reConn.setOk(true);
		reConn.setObject(sqlConn);
		
			Mockito.when(sqlConn.getMetaData()).thenReturn(metaData);
			Mockito.when(sqlConn.isClosed()).thenReturn(true);
		
		//~reConn
		
		
		//mock catalog
		schema=Mockito.mock(Schema.class);
		Mockito.when(schema.getName()).thenReturn(schemaName);
		//~catalog
		
		
		
//		MetadataConnectionUtils.checkConnection(mockDbConn)
		PowerMock.mockStatic(MetadataConnectionUtils.class);
		EasyMock.expect(MetadataConnectionUtils.checkConnection((DatabaseConnection)dataProvider)).andReturn(reConn);

//		ExtractMetaDataUtils.getDatabaseMetaData()
		PowerMock.mockStatic(ExtractMetaDataUtils.class);
		EasyMock.expect(ExtractMetaDataUtils.getDatabaseMetaData(sqlConn,(DatabaseConnection)dataProvider,false)).andReturn(metaData);

//		PackageHelper.getCatalogOrSchema()
		PowerMock.mockStatic(PackageHelper.class);
		EasyMock.expect(PackageHelper.getCatalogOrSchema(schema)).andReturn(schema);
//		PackageHelper.getParentPackage
		EasyMock.expect(PackageHelper.getParentPackage(schema)).andReturn(null);

		
		PowerMock.replay(MetadataConnectionUtils.class,ExtractMetaDataUtils.class,PackageHelper.class);
		
			containsTable = DqRepositoryViewService.isContainsView(dataProvider, schema, tablePattern);
		
		
			Mockito.verify(metaData).getTables(null,schemaName,tablePattern,tableType);
			Mockito.verify(tables).next();
//			Mockito.verify(sqlConn).getMetaData();
			Mockito.verify(sqlConn).isClosed();
			Mockito.verify(schema).getName();
		
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		Mockito.verifyZeroInteractions(metaData,tables,dataProvider,sqlConn,schema);
		assertTrue(containsTable);
//		fail("Not yet implemented");
	}

}
