package org.talend.core.model.metadata.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.runtime.CoreRuntimePlugin;

@PrepareForTest({ CoreRuntimePlugin.class })
public class ConvertionHelperTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

	@Test
    public void testConvertDatabaseConnectionBooleanString() {
        DatabaseConnection dbProvider = ConnectionFactory.eINSTANCE.createDatabaseConnection();

        // mock CoreRuntimePlugin
        CoreRuntimePlugin instanceMock = Mockito.mock(CoreRuntimePlugin.class);
        PowerMockito.mockStatic(CoreRuntimePlugin.class);
        Mockito.when(CoreRuntimePlugin.getInstance()).thenReturn(instanceMock);
        Mockito.when(instanceMock.getRepositoryService()).thenReturn(null);
        // ~CoreRuntimePlugin
        setJDBCMysqlConnection(dbProvider);
        IMetadataConnection convertMetadata = ConvertionHelper.convert(dbProvider, false, null);
        validJDBCMysqlConnection(convertMetadata, dbProvider);
    }

    private boolean validJDBCMysqlConnection(IMetadataConnection convertMetadata, DatabaseConnection dbProvider) {
        assertEquals(convertMetadata.getComment(), "");
        assertEquals(convertMetadata.getDatabase(), "");
        assertEquals(convertMetadata.getDataSourceName(), "");
        assertEquals(convertMetadata.getDbType(), "General JDBC");
        assertEquals(convertMetadata.getDriverJarPath(), "E:\\tools\\jdbc\\mysql-connector-java-5.1.12-bin.jar");
        assertEquals(convertMetadata.getDriverClass(), "org.gjt.mm.mysql.Driver");
        assertEquals(convertMetadata.getId(), "_9bw28cccEeGQNaw_qcyMFw");
        assertEquals(convertMetadata.getLabel(), "jdbcmysql1");
        assertEquals(convertMetadata.getPassword(), "shenze");
        assertEquals(convertMetadata.getPort(), "3306");
        assertEquals(convertMetadata.getSqlSyntax(), "SQL Syntax");
        assertEquals(convertMetadata.getUrl(), "jdbc:mysql://192.168.30.151:3306/?noDatetimeStringSync=true");
        assertEquals(convertMetadata.getUsername(), "shenze");
        assertEquals(convertMetadata.getMapping(), "mysql_id");
        assertEquals(convertMetadata.getProduct(), "JDBC");
        assertEquals(convertMetadata.isSqlMode(), false);
        assertEquals(convertMetadata.isContentModel(), false);
        assertEquals(convertMetadata.getCurrentConnection(), dbProvider);
        return true;
    }

    private boolean setJDBCMysqlConnection(DatabaseConnection dbProvider) {
        // General JDBC case
        dbProvider.setComment("");
        dbProvider.setSID("");
        dbProvider.setDatasourceName("");
        dbProvider.setDatabaseType("General JDBC");
        dbProvider.setDriverJarPath("E:\\tools\\jdbc\\mysql-connector-java-5.1.12-bin.jar");
        dbProvider.setDbVersionString("");
        dbProvider.setDriverClass("org.gjt.mm.mysql.Driver");
        dbProvider.setFileFieldName("");
        dbProvider.setId("_9bw28cccEeGQNaw_qcyMFw");
        dbProvider.setLabel("jdbcmysql1");
        dbProvider.setNullChar("");
        dbProvider.setPassword("shenze");
        dbProvider.setPort("3306");
        dbProvider.setServerName("");
        dbProvider.setSqlSynthax("SQL Syntax");
        dbProvider.setUiSchema("");
        dbProvider.setStringQuote("");
        dbProvider.setURL("jdbc:mysql://192.168.30.151:3306/?noDatetimeStringSync=true");
        dbProvider.setAdditionalParams("");
        dbProvider.setUsername("shenze");
        dbProvider.setDbmsId("mysql_id");
        dbProvider.setProductId("JDBC");
        dbProvider.setDBRootPath("");
        dbProvider.setSQLMode(false);
        dbProvider.setContextMode(false);
        dbProvider.setContextId("");
        dbProvider.setContextName("");
        return true;
    }

}
