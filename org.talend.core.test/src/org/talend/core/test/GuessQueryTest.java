package org.talend.core.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.talend.core.CorePlugin;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.metadata.query.generator.AS400QueryGenerator;
import org.talend.core.model.metadata.query.generator.H2QueryGenerator;
import org.talend.core.model.metadata.query.generator.NonDatabaseDefaultQueryGenerator;
import org.talend.core.model.metadata.query.generator.OracleQueryGenerator;
import org.talend.core.model.metadata.query.generator.PostgreQueryGenerator;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.components.ElementParameter;

public class GuessQueryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOracleGuessQuery() throws Exception {

        OracleQueryGenerator oraclegenerator = new OracleQueryGenerator(EDatabaseTypeName.ORACLEFORSID);
        FakeQueryDBElement OracleSQLElement = new FakeQueryDBElement();
        IElementParameter newParam = new ElementParameter(OracleSQLElement);
        newParam.setName(EParameterName.DBNAME.getName());
        newParam.setFieldType(EParameterFieldType.TEXT);
        newParam.setValue("talend");
        OracleSQLElement.addElementParameter(newParam);

        newParam = new ElementParameter(OracleSQLElement);

        newParam.setName(EParameterName.DBTABLE.getName());

        newParam.setFieldType(EParameterFieldType.DBTABLE);

        newParam.setValue("mytable");

        OracleSQLElement.addElementParameter(newParam);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("newColumn");

        newColumn.setOriginalDbColumnName("newColumn");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        oraclegenerator.setParameters(OracleSQLElement, newTable, "myschema", "mytable");

        String resultString = oraclegenerator.generateQuery();

        StringBuilder expectSql = new StringBuilder();
        expectSql.append(TalendTextUtils.getQuoteChar()).append("SELECT \n  myschema.mytable.newColumn\nFROM myschema.mytable")
                .append(TalendTextUtils.getQuoteChar());
        String expectString = expectSql.toString();

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectString.equals(resultString));

    }

    @Test
    public void testNonDatabaseGuessQuery() {
        NonDatabaseDefaultQueryGenerator nonQG = new NonDatabaseDefaultQueryGenerator(EDatabaseTypeName.MYSQL);

        FakeQueryDBElement mySQLElement = new FakeQueryDBElement();
        IElementParameter newParam = new ElementParameter(mySQLElement);
        newParam.setName(EParameterName.DBNAME.getName());
        newParam.setFieldType(EParameterFieldType.TEXT);
        newParam.setValue("talend");
        mySQLElement.addElementParameter(newParam);

        newParam = new ElementParameter(mySQLElement);

        newParam.setName(EParameterName.DBTABLE.getName());

        newParam.setFieldType(EParameterFieldType.DBTABLE);

        newParam.setValue("mytable");

        mySQLElement.addElementParameter(newParam);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("newColumn");

        newColumn.setOriginalDbColumnName("newColumn");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        nonQG.setParameters(mySQLElement, newTable, "", "mytable");

        String resultString = nonQG.generateQuery();

        StringBuilder expectSql = new StringBuilder();

        expectSql.append(TalendTextUtils.getQuoteChar()).append("SELECT \n  `mytable`.`newColumn`\nFROM `mytable`")
                .append(TalendTextUtils.getQuoteChar());

        String expectString = expectSql.toString();

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectString.equals(resultString));
    }

    @Test
    public void testPostGreSQL() {
        FakeQueryDBElement postGreSQLElement = new FakeQueryDBElement();
        IElementParameter newParam = new ElementParameter(postGreSQLElement);
        newParam.setName(EParameterName.DBNAME.getName());
        newParam.setFieldType(EParameterFieldType.TEXT);
        newParam.setValue("talend");
        postGreSQLElement.addElementParameter(newParam);

        newParam = new ElementParameter(postGreSQLElement);

        newParam.setName(EParameterName.DBTABLE.getName());

        newParam.setFieldType(EParameterFieldType.DBTABLE);

        newParam.setValue("mytable");

        postGreSQLElement.addElementParameter(newParam);

        PostgreQueryGenerator pQG = new PostgreQueryGenerator(EDatabaseTypeName.PSQL);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("newColumn");

        newColumn.setOriginalDbColumnName("newColumn");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        pQG.setParameters(postGreSQLElement, newTable, "myschema", "mytable");

        String resultString = pQG.generateQuery();

        StringBuilder expectSql = new StringBuilder();
        expectSql
                .append(TalendTextUtils.getQuoteChar())
                .append("SELECT \n  \\\"talend\\\".\\\"myschema\\\".\\\"mytable\\\".\\\"newColumn\\\"\nFROM \\\"talend\\\".\\\"myschema\\\".\\\"mytable\\\"")
                .append(TalendTextUtils.getQuoteChar());

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectSql.toString().equals(resultString));

    }

    @Test
    public void testAS400GuessQuery() {
        FakeQueryDBElement as400Element = new FakeQueryDBElement();

        IElementParameter newParam = new ElementParameter(as400Element);

        newParam.setName(EParameterName.DBNAME.getName());

        newParam.setFieldType(EParameterFieldType.TEXT);

        newParam.setValue("talend");

        as400Element.addElementParameter(newParam);

        newParam = new ElementParameter(as400Element);

        newParam.setFieldType(EParameterFieldType.DBTABLE);

        newParam.setValue("mytable");

        as400Element.addElementParameter(newParam);

        AS400QueryGenerator asQg = new AS400QueryGenerator();

        // AS400 has two diffenect sql,need both test

        String testStandardSQL = "SELECT \n  mytable.newColumn\nFROM mytable";

        String testSystemSQL = "SELECT \n  mytable/newColumn\nFROM mytable";

        CorePlugin.getDefault().getPreferenceStore().setValue(ITalendCorePrefConstants.AS400_SQL_SEG, true);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("newColumn");

        newColumn.setOriginalDbColumnName("newColumn");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        asQg.setParameters(as400Element, newTable, "", "mytable");

        // test standardSql for AS400
        String resultString = asQg.generateQuery();

        StringBuilder expectSql = new StringBuilder();

        expectSql.append(TalendTextUtils.getQuoteChar()).append(testStandardSQL).append(TalendTextUtils.getQuoteChar());

        String expectString = expectSql.toString();

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectString.equals(resultString));

        // test system Sql for AS400
        CorePlugin.getDefault().getPreferenceStore().setValue(ITalendCorePrefConstants.AS400_SQL_SEG, false);

        asQg.setParameters(as400Element, newTable, "", "mytable");

        String resultString1 = asQg.generateQuery();

        StringBuilder expectSql1 = new StringBuilder();

        expectSql1.append(TalendTextUtils.getQuoteChar()).append(testSystemSQL).append(TalendTextUtils.getQuoteChar());
        String expectString1 = expectSql1.toString();

        assertNotNull(resultString1);

        assertTrue(!"".equals(resultString1));

        assertTrue(expectString1.equals(resultString1));

    }

    @Test
    public void testH2GuessQuery() {
        // since H2 only need the table,no need to fake a node(for get db and schema) for it
        H2QueryGenerator h2QG = new H2QueryGenerator(EDatabaseTypeName.H2);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("newColumn");

        newColumn.setOriginalDbColumnName("newColumn");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        h2QG.setParameters(null, newTable, "", "mytable");

        String resultString = h2QG.generateQuery();

        StringBuilder expectSql = new StringBuilder();
        expectSql.append(TalendTextUtils.getQuoteChar())
                .append("SELECT \n  \\\"mytable\\\".\\\"newColumn\\\"\n FROM \\\"mytable\\\"")
                .append(TalendTextUtils.getQuoteChar());
        String expectString = expectSql.toString();

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectString.equals(resultString));
    }

    @Test
    public void testNetezzaGuessQuery() {

        // need improve,no enviroment about Netezza

    }

    @Test
    public void testSybaseQuery() {
        NonDatabaseDefaultQueryGenerator sybaseQG = new NonDatabaseDefaultQueryGenerator(EDatabaseTypeName.IBMDB2);

        FakeQueryDBElement mySQLElement = new FakeQueryDBElement();
        IElementParameter newParam = new ElementParameter(mySQLElement);
        newParam.setName(EParameterName.DBNAME.getName());
        newParam.setFieldType(EParameterFieldType.TEXT);
        newParam.setValue("talend");
        mySQLElement.addElementParameter(newParam);

        newParam = new ElementParameter(mySQLElement);

        newParam.setName(EParameterName.DBTABLE.getName());

        newParam.setFieldType(EParameterFieldType.DBTABLE);

        newParam.setValue("test");

        mySQLElement.addElementParameter(newParam);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("test");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("ID"); // ID is a keywords for Sybase,need a special tests

        newColumn.setOriginalDbColumnName("ID");

        columnsList.add(newColumn);

        newColumn = new MetadataColumn();

        newColumn.setLabel("NAME");

        newColumn.setOriginalDbColumnName("NAME");

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        sybaseQG.setParameters(mySQLElement, newTable, "", "test");

        String resultString = sybaseQG.generateQuery();

        StringBuilder expectSql = new StringBuilder();

        expectSql.append(TalendTextUtils.getQuoteChar()).append("SELECT \n  test.\\\"ID\\\", \n  test.NAME\nFROM test")
                .append(TalendTextUtils.getQuoteChar());

        String expectString = expectSql.toString();

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectString.equals(resultString));
    }

    @Test
    public void testDB2Query() {
        NonDatabaseDefaultQueryGenerator db2QG = new NonDatabaseDefaultQueryGenerator(EDatabaseTypeName.IBMDB2);

        FakeQueryDBElement mySQLElement = new FakeQueryDBElement();
        IElementParameter newParam = new ElementParameter(mySQLElement);
        newParam.setName(EParameterName.DBNAME.getName());
        newParam.setFieldType(EParameterFieldType.TEXT);
        newParam.setValue("talend");
        mySQLElement.addElementParameter(newParam);

        newParam = new ElementParameter(mySQLElement);

        newParam.setName(EParameterName.DBTABLE.getName());

        newParam.setFieldType(EParameterFieldType.DBTABLE);

        newParam.setValue("mytable");

        mySQLElement.addElementParameter(newParam);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("ID"); // ID is a keywords for db2,need a special tests

        newColumn.setOriginalDbColumnName("ID");

        columnsList.add(newColumn);

        newColumn = new MetadataColumn();

        newColumn.setLabel("NAME");

        newColumn.setOriginalDbColumnName("NAME");

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        db2QG.setParameters(mySQLElement, newTable, "", "mytable");

        String resultString = db2QG.generateQuery();

        StringBuilder expectSql = new StringBuilder();

        expectSql.append(TalendTextUtils.getQuoteChar()).append("SELECT \n  mytable.\\\"ID\\\", \n  mytable.NAME\nFROM mytable")
                .append(TalendTextUtils.getQuoteChar());

        String expectString = expectSql.toString();

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectString.equals(resultString));
    }
}
