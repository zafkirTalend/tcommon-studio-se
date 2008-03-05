// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.cwm.management.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.talend.cwm.softwaredeployment.TdDataProvider;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class DqRepositoryViewServiceTest {

    private static Logger log = Logger.getLogger(DqRepositoryViewServiceTest.class);

    private static final File FOLDER = new File("out");

    /**
     * Test method for {@link org.talend.cwm.management.api.DqRepositoryViewService#listTdDataProviders(java.io.File)}.
     */
    @Test
    public void testListTdDataProviders() {

        List<TdDataProvider> dataProviders = DqRepositoryViewService.listTdDataProviders(FOLDER);
        assertNotNull(dataProviders);
        assertFalse(dataProviders.isEmpty());
        for (TdDataProvider tdDataProvider : dataProviders) {
            log.info("tdDataProvider name = " + tdDataProvider.getName());
        }

    }

    /**
     * Test method for
     * {@link org.talend.cwm.management.api.DqRepositoryViewService#getTables(orgomg.cwm.resource.relational.Catalog, boolean)}.
     */
    @Test
    public void testGetTables() {
        fail("Not yet implemented");
    }

}
