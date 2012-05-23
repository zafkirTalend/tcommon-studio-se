package org.talend.core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.QueryUtil;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.User;

@PrepareForTest(QueryUtil.class)
public class QueryUtilTest {

    @Test
    public void testNeedFormatSQL() {
        QueryUtil qu = mock(QueryUtil.class);

        assertFalse(qu.needFormatSQL(null));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.NETEZZA.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.ORACLE_OCI.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.ORACLEFORSID.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.ORACLESN.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.PSQL.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.PLUSPSQL.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.AS400.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.ACCESS.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.MYSQL.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.IBMDB2.getDisplayName()));

        assertFalse(qu.needFormatSQL(EDatabaseTypeName.IBMDB2ZOS.getDisplayName()));

        assertTrue(qu.needFormatSQL(EDatabaseTypeName.H2.getDisplayName()));
    }

    @Test
    public void testGenerateNewQueryIElementIMetadataTableStringStringString() {
    }

    @Test
    public void testGenerateNewQueryIElementIMetadataTableBooleanStringStringString() {
    }

    @Test
    public void testGenerateNewQueryIElementIMetadataTableStringStringStringBoolean() {
    }

    @Test
    public void testGenerateNewQueryDelegateIElementIMetadataTableStringStringString() {
    }

    @Test
    public void testGenerateNewQueryDelegateIElementIMetadataTableStringStringStringBoolean() {
    }

    @Test
    public void testGenerateNewQueryIMetadataTableStringStringBoolean() {
    }

    @Test
    public void testGenerateNewQueryIMetadataTableStringStringStringArray() {
    }

    @Test
    public void testCheckIfIsNoQuotesAtAll() {
        String testQuery = "select mytable.ID from mytable";

        String testQuery1 = "select mytable.\\\"ID\\\" form mytable";

        QueryUtil qu = mock(QueryUtil.class);

        assertTrue(qu.checkIfIsNoQuotesAtAll(testQuery));

        assertTrue(!qu.checkIfIsNoQuotesAtAll(testQuery1));

    }

    @Test
    public void testCheckAndAddQuotes() {
        String testQuery = "select mytable.ID from mytable";

        String expectQuery = "\"select mytable.ID from mytable\"";
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

        QueryUtil qu = mock(QueryUtil.class);

        assertNotNull(qu.checkAndAddQuotes(testQuery));

        assertEquals(qu.checkAndAddQuotes(testQuery), expectQuery);

        assertNotNull(qu.checkAndAddQuotes(expectQuery));

        assertEquals(qu.checkAndAddQuotes(expectQuery), expectQuery);
    }

    @Test
    public void testcheckAndRemoveQuotes() {
        String testQuery = "select mytable.ID from mytable";

        String expectQuery = "\"select mytable.ID from mytable\"";
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

        QueryUtil qu = mock(QueryUtil.class);

        assertNotNull(qu.checkAndRemoveQuotes(testQuery));

        assertEquals(qu.checkAndRemoveQuotes(testQuery), testQuery);

        assertNotNull(qu.checkAndRemoveQuotes(expectQuery));

        assertEquals(qu.checkAndRemoveQuotes(expectQuery), testQuery);
    }

    @Test
    public void testcheckIfHasSpecialEscapeValue() {
        QueryUtil qu = mock(QueryUtil.class);
        assertTrue(qu.checkIfHasSpecialEscapeValue("select \\n"));

        assertTrue(qu.checkIfHasSpecialEscapeValue("select \\r"));

        assertTrue(qu.checkIfHasSpecialEscapeValue("select \\t"));

        assertFalse(qu.checkIfHasSpecialEscapeValue("test"));
    }
}
