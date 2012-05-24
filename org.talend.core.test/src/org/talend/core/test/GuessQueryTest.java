package org.talend.core.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;

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
        IElement OracleSQLElement = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = OracleSQLElement.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(OracleSQLElement.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("mytable");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(OracleSQLElement.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);

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

        IElement mySQLElement = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = mySQLElement.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(mySQLElement.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("mytable");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(mySQLElement.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);

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
        IElement postGreSQLElement = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = postGreSQLElement.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(postGreSQLElement.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("mytable");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(postGreSQLElement.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);
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
        IElement as400Element = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = as400Element.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(as400Element.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("mytable");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(as400Element.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);

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

        IElement sybaseElement = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = sybaseElement.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(sybaseElement.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("test");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(sybaseElement.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);

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

        sybaseQG.setParameters(sybaseElement, newTable, "", "test");

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

        IElement db2Element = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = db2Element.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(db2Element.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("mytable");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(db2Element.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);

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

        db2QG.setParameters(db2Element, newTable, "", "mytable");

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
