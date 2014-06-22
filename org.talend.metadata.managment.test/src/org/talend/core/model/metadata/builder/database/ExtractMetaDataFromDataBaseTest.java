// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.database;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.talend.utils.sql.metadata.constants.GetTable;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class ExtractMetaDataFromDataBaseTest {

    /**
     * Test method for
     * {@link org.talend.core.model.metadata.builder.database.ExtractMetaDataFromDataBase#getTableNamesFromQuery(java.sql.ResultSet)}
     * .
     * 
     * @throws SQLException
     */
	 @Test
	    public void testGetTableNamesFromQuery() throws SQLException {
	        assertTrue(ExtractMetaDataFromDataBase.tableCommentsMap instanceof HashMap<?, ?>);
	        assertTrue(ExtractMetaDataFromDataBase.tableCommentsMap.isEmpty());
	        ResultSet mockResultSet = mock(ResultSet.class);
	        when(mockResultSet.getString(1)).thenReturn("test");
	        when(mockResultSet.getString(GetTable.REMARKS.name())).thenReturn("test comment");
	        String nameKey = mockResultSet.getString(1).trim();
	        String tableComment = ExtractMetaDataFromDataBase.getTableComment(nameKey, mockResultSet, true, ExtractMetaDataUtils
	                .conn);
	        assertNotNull(tableComment);
	        ExtractMetaDataFromDataBase.tableCommentsMap.put(nameKey, tableComment);
	        assertNotNull(ExtractMetaDataFromDataBase.tableCommentsMap);
	        assertNotNull(ExtractMetaDataFromDataBase.getTableNamesFromQuery(mockResultSet, ExtractMetaDataUtils.conn));

	    }

    @Before
    public void clearTableCommentsMap() {
        ExtractMetaDataFromDataBase.tableCommentsMap.clear();
    }
}
