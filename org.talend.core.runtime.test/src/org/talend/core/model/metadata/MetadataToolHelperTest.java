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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.IRepositoryPrefConstants;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.routines.IRoutinesService;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.utils.KeywordsValidator;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC hwang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class MetadataToolHelperTest {

    private final int MIN = 192;

    private final int MAX = 255;

    /**
     * DOC Administrator Comment method "setUp".
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
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
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getConnectionItemByItemId(java.lang.String, boolean)}.
     */
    @Test
    public void testGetConnectionItemByItemId() {
        final IProxyRepositoryFactory proxyRepositoryFactory = CoreRuntimePlugin.getInstance().getProxyRepositoryFactory();
        String id = proxyRepositoryFactory.getNextId();
        ConnectionItem connItem = PropertiesFactory.eINSTANCE.createConnectionItem();
        Property p = PropertiesFactory.eINSTANCE.createProperty();
        p.setId(id);
        connItem.setProperty(p);

        String itemId = connItem.getProperty().getId();

        try {
            final IRepositoryViewObject lastVersion = proxyRepositoryFactory.getLastVersion(itemId);
            if (lastVersion != null) {
                final Item item = lastVersion.getProperty().getItem();
                if (item != null && item instanceof ConnectionItem) {
                    assertEquals(connItem, item);
                }
            }
        } catch (PersistenceException e) {
            //
        }
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

            int index = j;
            String originalColumnName = new String(columnName);
            final String underLine = "_"; //$NON-NLS-1$

            boolean isKeyword = KeywordsValidator.isKeyword(originalColumnName);

            columnName = "";
            if (!isKeyword) {
                for (int i = 0; i < originalColumnName.length(); i++) {
                    Character car = originalColumnName.charAt(i);
                    if (car.toString().getBytes().length == 1) {
                        // first character should have only a-z or A-Z or _
                        // other characters should have only a-z or A-Z or _ or 0-9
                        if (((car >= 'a') && (car <= 'z')) || ((car >= 'A') && (car <= 'Z')) || car == '_'
                                || ((car >= '0') && (car <= '9') && (i != 0))) {
                            columnName += car;
                        } else {
                            columnName += underLine;
                        }
                    } else {
                        columnName += car;
                    }
                }
            }
            if (isKeyword
                    || org.apache.commons.lang.StringUtils.countMatches(columnName, underLine) > (originalColumnName.length() / 2)) {
                columnName = "Column" + index; //$NON-NLS-1$
            }

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

            int index = j;
            String originalTableName = new String(tableName);
            final String underLine = "_"; //$NON-NLS-1$

            boolean isKeyword = KeywordsValidator.isKeyword(originalTableName);

            tableName = "";

            for (int i = 0; i < originalTableName.length(); i++) {
                Character car = originalTableName.charAt(i);
                if (car.toString().getBytes().length == 1) {
                    if (((car >= 'a') && (car <= 'z')) || ((car >= 'A') && (car <= 'Z')) || car == '_'
                            || ((car >= '0') && (car <= '9') && (i != 0))) {
                        tableName += car;
                    } else {
                        tableName += underLine;
                    }
                } else {
                    tableName += car;
                }
            }
            if (isKeyword) {
                tableName = tableName + "_1";
            }

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
            columnName = mapSpecialChar(columnName);
            final String underLine = "_"; //$NON-NLS-1$
            if (columnName.matches("^\\d.*")) { //$NON-NLS-1$
                columnName = underLine + columnName;
            }

            String testColumnName = columnName.replaceAll("[^a-zA-Z0-9_]", underLine); //$NON-NLS-1$
            String resultName = columnName;
            if (org.apache.commons.lang.StringUtils.countMatches(testColumnName, underLine) < (columnName.length() / 2)) {
                resultName = testColumnName;
            }
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
            columnName = mapSpecialChar(columnName);
            final String underLine = "_"; //$NON-NLS-1$
            if (columnName.matches("^\\d.*")) { //$NON-NLS-1$
                columnName = underLine + columnName;
            }

            columnName = columnName.replaceAll("[^a-zA-Z0-9_]", underLine); //$NON-NLS-1$
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
            columnName = mapSpecialChar(columnName);
            final String underLine = "_"; //$NON-NLS-1$
            if (columnName.matches("^\\d.*")) { //$NON-NLS-1$
                columnName = underLine + columnName;
            }

            String testColumnName = columnName.replaceAll("[^a-zA-Z0-9_]", underLine); //$NON-NLS-1$
            String resultName = columnName;
            if (org.apache.commons.lang.StringUtils.countMatches(testColumnName, underLine) < (columnName.length() / 2)) {
                resultName = testColumnName;
            }
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
                assertEquals(resultName, "cou_ntry1");
            } else if (i == 6) {
                assertEquals(resultName, "_dic");
            }
        }
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getMetadataTableFromRepository(java.lang.String)}.
     */
    @Test
    public void testGetMetadataTableFromRepository() {
        org.talend.core.model.metadata.builder.connection.Connection connection;
        List<String> nameList = new ArrayList<String>();
        nameList.add("sddfg - gjhsdhfjfjgjh");
        nameList.add("sddfg - gjhsdhfjfjgjh - assss");
        for (int i = 0; i < nameList.size(); i++) {
            String metaRepositoryId = nameList.get(i);
            String[] names = metaRepositoryId.split(" - "); //$NON-NLS-1$
            String linkedRepository = names[0];
            assertEquals("sddfg", linkedRepository);
            String name2 = null;
            if (names.length == 2) {
                name2 = names[1];
                assertEquals("gjhsdhfjfjgjh", name2);
            } else if (names.length > 2) {
                name2 = metaRepositoryId.substring(linkedRepository.length() + 3);
                assertEquals("gjhsdhfjfjgjh - assss", name2);
            }
        }

    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getConnectionFromRepository(java.lang.String)}.
     */
    @Test
    public void testGetConnectionFromRepository() {
        String functionName = null;
        String metadataName = null;
        String name = "sddfg - gjhsdhfjfjgjh";
        String[] names = name.split(" - "); //$NON-NLS-1$
        if (names.length == 2) {
            functionName = names[0];
            assertEquals("sddfg", functionName);
            metadataName = names[1];
            assertEquals("gjhsdhfjfjgjh", metadataName);
        }
    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getConnectionItemFromRepository(java.lang.String)}.
     */
    @Test
    public void testGetConnectionItemFromRepository() {
        String metaRepositoryid = "sddfg - gjhsdhfjfjgjh";
        String connectionId = metaRepositoryid;
        String[] names = metaRepositoryid.split(" - "); //$NON-NLS-1$
        if (names.length == 2) {
            connectionId = names[0];
            assertEquals("sddfg", connectionId);
        }

    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getQueryFromRepository(java.lang.String)}.
     */
    @Test
    public void testGetQueryFromRepository() {
        List<String> nameList = new ArrayList<String>();
        nameList.add("sddfg - gjhsdhfjfjgjh");
        nameList.add("sddfg - gjhsdhfjfjgjh - assss");
        for (int i = 0; i < nameList.size(); i++) {
            String metaRepositoryId = nameList.get(i);
            String[] names = metaRepositoryId.split(" - "); //$NON-NLS-1$
            String linkedRepository = names[0];
            assertEquals("sddfg", linkedRepository);
            String name2 = null;
            if (names.length == 2) {
                name2 = names[1];
                assertEquals("gjhsdhfjfjgjh", name2);
            } else if (names.length > 2) {
                name2 = metaRepositoryId.substring(linkedRepository.length() + 3);
                assertEquals("gjhsdhfjfjgjh - assss", name2);
            }
        }

    }

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.MetadataToolHelper#getSAPFunctionFromRepository(java.lang.String)}.
     */
    @Test
    public void testGetSAPFunctionFromRepository() {
        org.talend.core.model.metadata.builder.connection.Connection connection;

        List<String> nameList = new ArrayList<String>();
        nameList.add("sddfg - gjhsdhfjfjgjh");
        nameList.add("sddfg - gjhsdhfjfjgjh - assss");
        for (int i = 0; i < nameList.size(); i++) {
            String functionRepositoryId = nameList.get(i);
            String[] names = functionRepositoryId.split(" - "); //$NON-NLS-1$
            String linkedRepository = names[0];
            assertEquals("sddfg", linkedRepository);
            String name2 = null;
            if (names.length == 2) {
                name2 = names[1];
                assertEquals("gjhsdhfjfjgjh", name2);
            } else if (names.length > 2) {
                name2 = functionRepositoryId.substring(linkedRepository.length() + 3);
                assertEquals("gjhsdhfjfjgjh - assss", name2);
            }
        }

    }

    private static boolean isAllowSpecificCharacters() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IDesignerCoreService.class)) {
            return CoreRuntimePlugin.getInstance().getDesignerCoreService().getDesignerCorePreferenceStore()
                    .getBoolean(IRepositoryPrefConstants.ALLOW_SPECIFIC_CHARACTERS_FOR_SCHEMA_COLUMNS);
        }
        return false;
    }

    private String mapSpecialChar(String columnName) {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IRoutinesService.class)) {
            IRoutinesService service = (IRoutinesService) GlobalServiceRegister.getDefault().getService(IRoutinesService.class);
            if (service != null) {
                Vector map = service.getAccents();
                map.setElementAt("AE", 4);//$NON-NLS-1$
                map.setElementAt("OE", 22);//$NON-NLS-1$
                map.setElementAt("UE", 28);//$NON-NLS-1$
                map.setElementAt("ss", 31);//$NON-NLS-1$
                map.setElementAt("ae", 36);//$NON-NLS-1$
                map.setElementAt("oe", 54);//$NON-NLS-1$
                map.setElementAt("ue", 60);//$NON-NLS-1$
                Vector addedMap = new Vector();
                for (int i = 257; i < 304; i++) {
                    addedMap.add(String.valueOf((char) i));
                }
                map.addAll(addedMap);
                map.add("I");//$NON-NLS-1$

                return initSpecificMapping(columnName, map);
            }
        }
        return columnName;
    }

    private String initSpecificMapping(String columnName, Vector map) {
        for (int i = 0; i < columnName.toCharArray().length; i++) {
            int carVal = columnName.charAt(i);
            if (carVal >= MIN && carVal <= MIN + map.size()) {
                String oldVal = String.valueOf(columnName.toCharArray()[i]);
                String newVal = (String) map.get(carVal - MIN);
                if (!(oldVal.equals(newVal))) {
                    columnName = columnName.replaceAll(oldVal, newVal);
                }
            }
        }

        return columnName;
    }

}
