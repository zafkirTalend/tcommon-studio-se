package org.talend.core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.Test;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.User;
import org.talend.core.sqlbuilder.util.TextUtil;

public class TextUtilTest {

    @Test
    public void testTextUtil() {

    }

    @Test
    public void testGetNewQueryLabel() {
    }

    @Test
    public void testRemoveLineBreaks() {

        String testString = "select \n from\t test where \r id='4' ";

        String expertString = "select   from  test where  id='4' ";
        TextUtil tu = mock(TextUtil.class);

        assertNull(tu.removeLineBreaks(null));

        assertEquals(tu.removeLineBreaks(testString), expertString);

    }

    @Test
    public void testGetWrappedTextString() {

        TextUtil tu = mock(TextUtil.class);

        StringBuilder testString = new StringBuilder();

        String testString1 = null;

        StringBuilder testString2 = new StringBuilder();

        for (int i = 0; i < tu.DEFAULT_WRAPLENGTH; i++) {
            testString.append("a");
        }

        for (int i = 0; i < 200; i++) {
            testString2.append("a");
        }

        assertEquals(tu.DEFAULT_WRAPLENGTH, tu.getWrappedText(testString.toString()).length());

        assertEquals(testString.substring(0, testString.length()),
                tu.getWrappedText(testString.toString()).substring(0, testString.length()));

        assertEquals("", tu.getWrappedText(testString1));

        assertEquals(tu.DEFAULT_WRAPLENGTH, tu.getWrappedText(testString2.toString()).length() - 2);

        assertEquals(testString2.substring(0, tu.DEFAULT_WRAPLENGTH + 1),
                tu.getWrappedText(testString2.toString()).substring(0, tu.DEFAULT_WRAPLENGTH + 1));

    }

    @Test
    public void testGetWrappedTextStringInt() {

    }

    @Test
    public void testReplaceChar() {
        String testString1 = "abcdedfwersdf";

        String testString2 = "";

        String testString3 = null;

        TextUtil tu = mock(TextUtil.class);
        assertEquals("", tu.replaceChar(testString2, 'a', ""));

        assertNull(tu.replaceChar(testString3, 'a', ""));

        assertEquals("abwhatdedfwersdf", tu.replaceChar(testString1, 'c', "what"));
    }

    @Test
    public void testAddSqlQuots() {
    }

    @Test
    public void testRemoveQuots() {
        TextUtil tu = mock(TextUtil.class);

        String expectString = "select * from test";

        Project projectInfor = new Project();
        projectInfor.setLabel("testQuery");
        projectInfor.setDescription("no desc");
        projectInfor.setLanguage(ECodeLanguage.JAVA);

        User user = PropertiesFactory.eINSTANCE.createUser();
        user.setLogin("user@talend.com"); //$NON-NLS-1$
        projectInfor.setAuthor(user);

        RepositoryContext repositoryContext = new RepositoryContext();
        repositoryContext.setUser(user);
        HashMap<String, String> fields = new HashMap<String, String>();
        repositoryContext.setFields(fields);
        repositoryContext.setProject(projectInfor);
        Context ctx = CorePlugin.getContext();

        ctx.putProperty(Context.REPOSITORY_CONTEXT_KEY, repositoryContext);

        assertEquals("", tu.removeQuots(null));

        assertNotNull(tu.removeQuots("select * from test"));

        assertEquals(expectString, tu.removeQuots("select * from test"));

        assertNotNull(tu.removeQuots("\"select * from test\""));

        assertEquals(expectString, tu.removeQuots("\"select * from test\""));

    }

    @Test
    public void testGetDialogTitle() {
    }

    @Test
    public void testSetDialogTitleString() {
    }

    @Test
    public void testSetDialogTitleStringStringString() {
    }

    @Test
    public void testIsDoubleQuotesNeededDbType() {
        TextUtil tu = mock(TextUtil.class);

        assertTrue(tu.isDoubleQuotesNeededDbType(EDatabaseTypeName.PSQL.getXmlName()));

        assertTrue(tu.isDoubleQuotesNeededDbType(EDatabaseTypeName.GREENPLUM.getXmlName()));

        assertTrue(tu.isDoubleQuotesNeededDbType(EDatabaseTypeName.PARACCEL.getXmlName()));

        assertTrue(tu.isDoubleQuotesNeededDbType(EDatabaseTypeName.H2.getXmlName()));

        assertFalse(tu.isDoubleQuotesNeededDbType(EDatabaseTypeName.MYSQL.getXmlName()));

        assertFalse(tu.isDoubleQuotesNeededDbType("test"));
    }

    @Test
    public void testIsOracleDbType() {

        TextUtil tu = mock(TextUtil.class);

        assertTrue(tu.isOracleDbType(EDatabaseTypeName.ORACLEFORSID.getXmlName()));

        assertTrue(tu.isOracleDbType(EDatabaseTypeName.ORACLEFORSID.getDisplayName()));

        assertTrue(tu.isOracleDbType(EDatabaseTypeName.ORACLESN.getXmlName()));

        assertTrue(tu.isOracleDbType(EDatabaseTypeName.ORACLESN.getDisplayName()));

        assertTrue(tu.isOracleDbType(EDatabaseTypeName.ORACLE_OCI.getXmlName()));
        assertTrue(tu.isOracleDbType(EDatabaseTypeName.ORACLE_OCI.getDisplayName()));

        assertFalse(tu.isOracleDbType(EDatabaseTypeName.MYSQL.getDisplayName()));

        assertFalse(tu.isOracleDbType("test"));
    }

    @Test
    public void testCalEscapeValue() {
        TextUtil tu = mock(TextUtil.class);

        String testString = null;

        String testString1 = "select * \\n \\t \\r from test";

        String testString2 = "select * from test";

        assertNull(tu.calEscapeValue(testString));

        assertNotNull(tu.calEscapeValue(testString1));

        assertEquals("select * \n \t \r from test", tu.calEscapeValue(testString1));

        assertEquals(testString2, tu.calEscapeValue(testString2));
    }

}
