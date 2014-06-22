// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.model;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.talend.core.model.general.ModuleNeeded;

/**
 * created by ycbai on 2014-5-8 Detailled comment
 * 
 */
@RunWith(PowerMockRunner.class)
public class ExtensionModuleManagerTest {

    private ExtensionModuleManager manager;

    @BeforeClass
    public void setUp() throws Exception {
        manager = ExtensionModuleManager.getInstance();
    }

    @Test
    public void testGetModuleNeeded() {
        /*
         * Get single module from id.
         */
        List<ModuleNeeded> moduleNeededs = manager.getModuleNeeded("test-jar1"); //$NON-NLS-1$
        Assert.assertTrue(moduleNeededs != null && moduleNeededs.size() > 0
                && "test-jar1.jar".equals(moduleNeededs.get(0).getModuleName())); //$NON-NLS-1$

        /*
         * Get single module from name.
         */
        moduleNeededs = manager.getModuleNeeded("test-jar1.jar"); //$NON-NLS-1$
        Assert.assertTrue(moduleNeededs != null && moduleNeededs.size() > 0 && "test-jar1".equals(moduleNeededs.get(0).getId())); //$NON-NLS-1$

        /*
         * Get modules from group.
         */
        moduleNeededs = manager.getModuleNeeded("test-jars-group"); //$NON-NLS-1$
        assertNotNull(moduleNeededs);
        Assert.assertTrue(moduleNeededs.size() == 3);
        Assert.assertTrue("test-jar1".equals(moduleNeededs.get(0).getId())); //$NON-NLS-1$
        Assert.assertTrue("test-jar2".equals(moduleNeededs.get(1).getId())); //$NON-NLS-1$
        Assert.assertTrue("test-jar3".equals(moduleNeededs.get(2).getId())); //$NON-NLS-1$
    }
}
