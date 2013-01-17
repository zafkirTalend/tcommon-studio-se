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
package org.talend.core.model.emf;

import org.eclipse.emf.ecore.EClass;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.impl.ProcessItemImpl;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ProcessItemTest {

    private ProcessItem processItem;

    @Before
    public void setUp() throws Exception {
        processItem = PropertiesFactory.eINSTANCE.createProcessItem();
    }

    @After
    public void tearDown() throws Exception {
        processItem = null;
    }

    @Test
    public void testGetClass() {
        Class<? extends ProcessItem> clazz = processItem.getClass();

        Assert.assertNotSame(clazz, ProcessItem.class);

        Assert.assertEquals(clazz, ProcessItemImpl.class);
        Assert.assertEquals(clazz.getName(), ProcessItemImpl.class.getName());
    }

    @Test
    public void testEClass() {
        EClass eClass = processItem.eClass();

        Assert.assertEquals(eClass.getName(), "ProcessItem");
        Assert.assertEquals(eClass.getInstanceClass(), ProcessItem.class);
        Assert.assertEquals(eClass.getInstanceClassName(), ProcessItem.class.getName());

        Assert.assertEquals(eClass.eContainer(), PropertiesPackage.eINSTANCE);
        Assert.assertEquals(eClass.getClassifierID(), PropertiesPackage.PROCESS_ITEM);
    }

    // @Test
    public void testExxxFeature() {
        Assert.assertEquals(processItem.eContainmentFeature(), PropertiesPackage.Literals.PROCESS_ITEM);
        Assert.assertEquals(processItem.eContainingFeature(), PropertiesPackage.eINSTANCE.getProcessItem_Process());

    }
}
