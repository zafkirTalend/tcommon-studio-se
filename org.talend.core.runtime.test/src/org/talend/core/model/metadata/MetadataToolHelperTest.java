// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.SAPConnection;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.SAPConnectionItem;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.PackageHelper;
import org.talend.repository.model.IProxyRepositoryFactory;
import orgomg.cwm.resource.record.RecordFactory;
import orgomg.cwm.resource.record.RecordFile;

/**
 * DOC hwang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class MetadataToolHelperTest {

    private static org.talend.core.model.metadata.builder.connection.Connection connection;

    private static DatabaseConnectionItem databaseConnItem;

    private static MetadataTable inputTable;

    /**
     * DOC Administrator Comment method "setUp".
     * 
     * @throws PersistenceException
     * 
     * 
     */
    @BeforeClass
    public static void setUp() throws PersistenceException {
        init();
    }

    private static void init() throws PersistenceException {
        connection = ConnectionFactory.eINSTANCE.createDatabaseConnection();
        connection.setName("mysql_1");

        IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
        connection.setId(factory.getNextId());
        databaseConnItem = PropertiesFactory.eINSTANCE.createDatabaseConnectionItem();
        Property myProperty = PropertiesFactory.eINSTANCE.createProperty();
        myProperty.setId(factory.getNextId());
        myProperty.setLabel("mysql_1");
        myProperty.setVersion("0.1");

        ItemState itemState = PropertiesFactory.eINSTANCE.createItemState();
        itemState.setPath("");

        databaseConnItem.setProperty(myProperty);
        databaseConnItem.setState(itemState);
        databaseConnItem.setConnection(connection);

        RecordFile record = (RecordFile) ConnectionHelper.getPackage(connection.getName(), connection, RecordFile.class);
        inputTable = ConnectionFactory.eINSTANCE.createMetadataTable();

        inputTable.setId(factory.getNextId());
        inputTable.setLabel("Input");

        if (record != null) {
            PackageHelper.addMetadataTable(inputTable, record);
        } else {
            RecordFile newrecord = RecordFactory.eINSTANCE.createRecordFile();
            newrecord.setName(connection.getName());
            ConnectionHelper.addPackage(newrecord, connection);
            PackageHelper.addMetadataTable(inputTable, newrecord);
        }

        factory.create(databaseConnItem, new Path(""));
        factory.save(databaseConnItem);
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
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isBoolean(java.lang.String)}.
     */
    @Test
    public void testIsBoolean() {
        String value = "id_Boolean";
        assertEquals(value, JavaTypesManager.BOOLEAN.getId());
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isDirectory(java.lang.String)}.
     */
    @Test
    public void testIsDirectory() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        String java_value = "id_Directory";
        String perl_value = "Directory";
        if (codeLanguage == ECodeLanguage.JAVA) {
            assertEquals(java_value, JavaTypesManager.DIRECTORY.getId());
        } else {
            assertEquals(perl_value, ContextParameterJavaTypeManager.PERL_DIRECTORY);
        }
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isList(java.lang.String)}.
     */
    @Test
    public void testIsList() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        String java_value = "id_" + "List Of Value";
        String perl_value = "List Of Value";
        if (codeLanguage == ECodeLanguage.JAVA) {
            assertEquals(java_value, JavaTypesManager.VALUE_LIST.getId());
        } else {
            assertEquals(perl_value, ContextParameterJavaTypeManager.PERL_VALUE_LIST);
        }
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isDate(java.lang.String)}.
     */
    @Test
    public void testIsDate() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        String java_value = "id_" + Date.class.getSimpleName();
        String perl_value = "Day";
        if (codeLanguage == ECodeLanguage.JAVA) {
            assertEquals(java_value, JavaTypesManager.DATE.getId());
        } else {
            assertEquals(perl_value, ContextParameterJavaTypeManager.PERL_DAY);
        }
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isFile(java.lang.String)}.
     */
    @Test
    public void testIsFile() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        String java_value = "id_" + "File";
        String perl_value = "File";
        if (codeLanguage == ECodeLanguage.JAVA) {
            assertEquals(java_value, JavaTypesManager.FILE.getId());
        } else {
            assertEquals(perl_value, ContextParameterJavaTypeManager.PERL_FILE);
        }
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isPassword(java.lang.String)}.
     */
    @Test
    public void testIsPassword() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        String java_value = "id_" + "Password";
        String perl_value = "Password";
        if (codeLanguage == ECodeLanguage.JAVA) {
            assertEquals(java_value, JavaTypesManager.PASSWORD.getId());
        } else {
            assertEquals(perl_value, ContextParameterJavaTypeManager.PERL_PASSWORD);
        }
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isValidSchemaName(java.lang.String)}.
     */
    @Test
    public void testIsValidSchemaName() {
        String name = "na me";
        assertFalse(name, false);
        name = "name";
        assertTrue(name, true);
    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#isValidColumnName(java.lang.String)}.
     */
    @Test
    public void testIsValidColumnName() {
        String name = null;
        assertFalse(name, false);
        name = "public";
        assertFalse(name, false);

        // return isAllowSpecificCharacters() || Pattern.matches(RepositoryConstants.COLUMN_NAME_PATTERN, name);
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#validateColumnName(java.lang.String, int)}.
     */
    @Test
    public void testValidateColumnName() {
        List<String> nameList = new ArrayList<String>();
        nameList.add("public");
        nameList.add("id");
        nameList.add("na&me");
        nameList.add("addr_ess");
        nameList.add("9city");
        nameList.add("country1");
        nameList.add("_dic");
        for (int j = 0; j < nameList.size(); j++) {
            String columnName = nameList.get(j);
            columnName = MetadataToolHelper.validateColumnName(columnName, j);

            if (j == 0) {
                assertEquals(columnName, "Column0");
            } else if (j == 1) {
                assertEquals(columnName, "id");
            } else if (j == 2) {
                assertEquals(columnName, "na_me");
            } else if (j == 3) {
                assertEquals(columnName, "addr_ess");
            } else if (j == 4) {
                assertEquals(columnName, "_city");
            } else if (j == 5) {
                assertEquals(columnName, "country1");
            } else if (j == 5) {
                assertEquals(columnName, "_dic");
            }
        }

    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#validateTableName(java.lang.String)}.
     */
    @Test
    public void testValidateTableName() {
        List<String> nameList = new ArrayList<String>();
        nameList.add("public");
        nameList.add("id");
        nameList.add("na&me");
        nameList.add("addr_ess");
        nameList.add("9city");
        nameList.add("country1");
        nameList.add("_dic");
        for (int j = 0; j < nameList.size(); j++) {
            String tableName = nameList.get(j);

            tableName = MetadataToolHelper.validateTableName(tableName);

            if (j == 0) {
                assertEquals(tableName, "public_1");
            } else if (j == 1) {
                assertEquals(tableName, "id");
            } else if (j == 2) {
                assertEquals(tableName, "na_me");
            } else if (j == 3) {
                assertEquals(tableName, "addr_ess");
            } else if (j == 4) {
                assertEquals(tableName, "_city");
            } else if (j == 5) {
                assertEquals(tableName, "country1");
            } else if (j == 5) {
                assertEquals(tableName, "_dic");
            }
        }

    }

    /**
     * Test method for {@link org.talend.core.model.metadata.MetadataToolHelper#validateValue(java.lang.String)}.
     */
    @Test
    public void testValidateValue() {
        List<String> nameList = new ArrayList<String>();
        nameList.add("public");
        nameList.add("id");
        nameList.add("na&me");
        nameList.add("addr_ess");
        nameList.add("9city");
        nameList.add("cou ntry1");
        nameList.add("_dic");
        for (int i = 0; i < nameList.size(); i++) {
            String columnName = nameList.get(i);

            String resultName = MetadataToolHelper.validateTableName(columnName);

            if (i == 0) {
                assertEquals(resultName, "public_1");
            } else if (i == 1) {
                assertEquals(resultName, "id");
            } else if (i == 2) {
                assertEquals(resultName, "na_me");
            } else if (i == 3) {
                assertEquals(resultName, "addr_ess");
            } else if (i == 4) {
                assertEquals(resultName, "_city");
            } else if (i == 5) {
                assertEquals(resultName, "cou_ntry1");
            } else if (i == 6) {
                assertEquals(resultName, "_dic");
            }
        }

    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#validateValueNoLengthLimit(java.lang.String)}.
     */
    @Test
    public void testValidateValueNoLengthLimit() {
        List<String> nameList = new ArrayList<String>();
        nameList.add("public");
        nameList.add("id");
        nameList.add("na&me");
        nameList.add("addr_ess");
        nameList.add("9city");
        nameList.add("cou ntry1");
        nameList.add("_dic");
        for (int i = 0; i < nameList.size(); i++) {
            String columnName = nameList.get(i);

            columnName = MetadataToolHelper.validateValueNoLengthLimit(columnName);

            if (i == 0) {
                assertEquals(columnName, "public");
            } else if (i == 1) {
                assertEquals(columnName, "id");
            } else if (i == 2) {
                assertEquals(columnName, "na_me");
            } else if (i == 3) {
                assertEquals(columnName, "addr_ess");
            } else if (i == 4) {
                assertEquals(columnName, "_9city");
            } else if (i == 5) {
                assertEquals(columnName, "cou_ntry1");
            } else if (i == 6) {
                assertEquals(columnName, "_dic");
            }
        }

    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#validateValueForDBType(java.lang.String)}.
     */
    @Test
    public void testValidateValueForDBType() {

        List<String> nameList = new ArrayList<String>();
        nameList.add("public");
        nameList.add("id");
        nameList.add("na&me");
        nameList.add("addr_ess");
        nameList.add("9city");
        nameList.add("cou ntry1");
        nameList.add("_dic");
        for (int i = 0; i < nameList.size(); i++) {
            String columnName = nameList.get(i);

            String resultName = MetadataToolHelper.validateValueForDBType(columnName);

            if (i == 0) {
                assertEquals(resultName, "public");
            } else if (i == 1) {
                assertEquals(resultName, "id");
            } else if (i == 2) {
                assertEquals(resultName, "na_me");
            } else if (i == 3) {
                assertEquals(resultName, "addr_ess");
            } else if (i == 4) {
                assertEquals(resultName, "_9city");
            } else if (i == 5) {
                assertEquals(resultName, "cou ntry1");
            } else if (i == 6) {
                assertEquals(resultName, "_dic");
            }
        }
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getMetadataTableFromRepository(java.lang.String)}.
     * 
     * 
     */
    @Test
    public void testGetMetadataTableFromRepository() {
        String metaRepositoryId = databaseConnItem.getProperty().getId() + " - " + inputTable.getLabel();
        MetadataTable table = MetadataToolHelper.getMetadataTableFromRepository(metaRepositoryId);
        assertEquals(inputTable.getLabel(), table.getLabel());

        metaRepositoryId = databaseConnItem.getProperty().getId();
        table = MetadataToolHelper.getMetadataTableFromRepository(metaRepositoryId);
        assertNull(table);
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getConnectionFromRepository(java.lang.String)}.
     * 
     */
    @Test
    public void testGetConnectionFromRepository() {
        DatabaseConnection conn = (DatabaseConnection) MetadataToolHelper.getConnectionFromRepository(databaseConnItem
                .getProperty().getId());
        assertEquals(conn.getId(), connection.getId());
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getConnectionItemFromRepository(java.lang.String)}.
     * 
     * 
     */
    @Test
    public void testGetConnectionItemFromRepository() {
        DatabaseConnectionItem item = (DatabaseConnectionItem) MetadataToolHelper
                .getConnectionItemFromRepository(databaseConnItem.getProperty().getId());
        assertEquals(item, databaseConnItem);

        String metaRepositoryId = databaseConnItem.getProperty().getId() + " - " + inputTable.getLabel();
        item = (DatabaseConnectionItem) MetadataToolHelper.getConnectionItemFromRepository(metaRepositoryId);
        assertEquals(item, databaseConnItem);
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getQueryFromRepository(java.lang.String)}.
     * 
     * 
     */
    @Test
    public void testGetQueryFromRepository() {
        QueriesConnection queryConn = ConnectionFactory.eINSTANCE.createQueriesConnection();
        queryConn.setConnection(connection);
        org.talend.core.model.metadata.builder.connection.Query query = ConnectionFactory.eINSTANCE.createQuery();

        IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
        query.setName("query");
        query.setLabel("query");
        query.setId(factory.getNextId());
        queryConn.getQuery().add(query);

        String qid = databaseConnItem.getProperty().getId() + " - " + query.getLabel();
        org.talend.core.model.metadata.builder.connection.Query qu = MetadataToolHelper.getQueryFromRepository(qid);
        assertEquals(query.getId(), qu.getId());

        qid = databaseConnItem.getProperty().getId();
        qu = MetadataToolHelper.getQueryFromRepository(qid);
        assertNull(qu);
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getSAPFunctionFromRepository(java.lang.String)}.
     * 
     * @throws PersistenceException
     */
    @Test
    public void testGetSAPFunctionFromRepository() throws PersistenceException {
        SAPConnection sapConn = ConnectionFactory.eINSTANCE.createSAPConnection();

        sapConn.setName("sap");

        IProxyRepositoryFactory factory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
        sapConn.setId(factory.getNextId());
        SAPConnectionItem sapItem = PropertiesFactory.eINSTANCE.createSAPConnectionItem();
        Property myProperty = PropertiesFactory.eINSTANCE.createProperty();
        myProperty.setId(factory.getNextId());
        myProperty.setLabel("sap");
        myProperty.setVersion("0.1");

        ItemState itemState = PropertiesFactory.eINSTANCE.createItemState();
        itemState.setPath("");

        sapItem.setProperty(myProperty);
        sapItem.setState(itemState);
        sapItem.setConnection(sapConn);

        SAPFunctionUnit function = ConnectionFactory.eINSTANCE.createSAPFunctionUnit();

        sapConn.getFuntions().add(function);

        function.setId(factory.getNextId());
        function.setLabel("function");

        factory.create(sapItem, new Path(""));
        factory.save(sapItem);

        String id = sapItem.getProperty().getId() + " - " + function.getLabel();
        SAPFunctionUnit unit = MetadataToolHelper.getSAPFunctionFromRepository(id);
        assertEquals(unit, function);

        id = sapItem.getProperty().getId();
        unit = MetadataToolHelper.getSAPFunctionFromRepository(id);
        assertNull(unit);
    }

}
