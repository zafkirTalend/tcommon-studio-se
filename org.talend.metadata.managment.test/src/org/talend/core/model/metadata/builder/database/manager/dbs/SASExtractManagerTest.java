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
package org.talend.core.model.metadata.builder.database.manager.dbs;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;
import org.talend.core.database.EDatabaseTypeName;

/**
 * created by ggu on Jul 11, 2012 Detailled comment
 * 
 */
public class SASExtractManagerTest extends AbstractTest4ExtractManager {

    @Before
    public void setUp() throws Exception {
        init(EDatabaseTypeName.SAS);
    }

    @Override
    @Test
    public void testReturnColumns4DontCreateConnection() throws Exception {
        // super.testReturnColumns4DontCreateConnection();
        PTODO();
    }

    @Override
    protected Connection mockConnection4ReturnColumns() throws Exception {
        Connection conn = super.mockConnection4ReturnColumns();
        // need care the FakeDatabaseMetaData for SAS
        // PTODO
        return conn;
    }

}
