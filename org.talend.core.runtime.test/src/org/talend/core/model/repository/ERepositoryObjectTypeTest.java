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
package org.talend.core.model.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ERepositoryObjectTypeTest {

    private ERepositoryObjectType[] allTypes;

    @Before
    public void setUp() {
        allTypes = (ERepositoryObjectType[]) ERepositoryObjectType.values();
        Arrays.sort(allTypes, new Comparator<ERepositoryObjectType>() {

            @Override
            public int compare(ERepositoryObjectType o1, ERepositoryObjectType o2) {
                return o1.getType().compareToIgnoreCase(o2.getType());
            }

        });
    }

    @After
    public void tearDown() throws Exception {
        allTypes = null;
    }

    @Test
    public void testLoadedDITypes() {
        List<ERepositoryObjectType> resourceTypes = new ArrayList<ERepositoryObjectType>();
        for (ERepositoryObjectType type : allTypes) {
            if (type.isResouce() && type.isDIItemType()) {
                resourceTypes.add(type);
                // System.out.println(type.getType() + "(\"" + type.getKey() + "\"),");
            }
        }
        // System.out.println("DI Types totals:" + resourceTypes.size());
        Assert.assertEquals("Please check with TAC Team for Class ItemType, after add/delete some types for DI.", 41,
                resourceTypes.size());
    }

    @Test
    public void testLoadedCamelTypes() {
        List<ERepositoryObjectType> resourceTypes = new ArrayList<ERepositoryObjectType>();
        for (ERepositoryObjectType type : allTypes) {
            if (type.isResouce() && Arrays.asList(type.getProducts()).contains(ERepositoryObjectType.PROD_CAMEL)) {
                resourceTypes.add(type);
                // System.out.println(type.getType() + "(\"" + type.getKey() + "\"),");
            }
        }
        // System.out.println("Camel Types totals:" + resourceTypes.size());
        Assert.assertEquals("Please check with TAC Team for Class ItemType, after add/delete some types for Camel.", 4,
                resourceTypes.size());
    }

    // @Test
    public void testLoadedDQTypes() {
        List<ERepositoryObjectType> resourceTypes = new ArrayList<ERepositoryObjectType>();
        for (ERepositoryObjectType type : allTypes) {
            if (type.isDQItemType()) {
                resourceTypes.add(type);
                // System.out.println(type.getType() + "(\"" + type.getKey() + "\"),");
            }
        }
        // PTODO
        // Assert.assertEquals("Please check with TAC Team for Class ItemType, after add/delete some types for DQ.", -1,
        // resourceTypes.size());
        // System.out.println("DQ Types totals:" + resourceTypes.size());
    }

    // @Test
    public void testLoadedMDMTypes() {
        List<ERepositoryObjectType> resourceTypes = new ArrayList<ERepositoryObjectType>();
        for (ERepositoryObjectType type : allTypes) {
            if (type.getType().startsWith("MDM")) {
                resourceTypes.add(type);
                // System.out.println(type.getType() + "(\"" + type.getKey() + "\"),");
            }
        }
        // PTODO
        // Assert.assertEquals("Please check with TAC Team for Class ItemType, after add/delete some types for MDM.",
        // -1,
        // resourceTypes.size());
        // System.out.println("MDM Types totals:" + resourceTypes.size());
    }
}
