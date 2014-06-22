package org.talend.core.model.metadata.query.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;

public class AS400QueryGeneratorTest {

    @Test
    public void testGenerateQuery() {
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

}
