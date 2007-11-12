// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.core.model.utils;

import java.io.File;
import java.io.FileReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * DOC acer class global comment. Detailled comment <br/>
 * 
 */
public class XSDValidaterTest {

    /**
     * DOC acer Comment method "setUpBeforeClass".
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * DOC acer Comment method "tearDownAfterClass".
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * DOC acer Comment method "setUp".
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * DOC acer Comment method "tearDown".
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.talend.core.model.utils.XSDValidater#validateWithDom(java.io.File, java.io.File)}.
     */
    @Test
    public final void testValidateWithDomFileFileOK() throws Exception {
        XSDValidater validator = new XSDValidater();
        File validatingFile = new File("mappings/mapping_IBMDB2.xml");
        File validatorFile = new File("mappings/mapping_validate.xsd");
        validator.validateWithDom(validatorFile, validatingFile);

        validatingFile = new File("mappings/mapping_MsOdbc.xml");
        validator.validateWithDom(validatorFile, validatingFile);
        
        validatingFile = new File("mappings/mapping_Mysql.xml");
        validator.validateWithDom(validatorFile, validatingFile);
        
        validatingFile = new File("mappings/mapping_Oracle.xml");
        validator.validateWithDom(validatorFile, validatingFile);
    
    }

    /**
     * Test method for {@link org.talend.core.model.utils.XSDValidater#validateWithDom(java.io.File, java.io.File)}.
     */
    @Test
    public final void testValidateWithDomFileFileInvalid() throws Exception {
        XSDValidater validator = new XSDValidater();
        File validatingFile = new File(this.getClass().getResource("mapping_InvalidateDB.xml").toURI());
        File validatorFile = new File("mappings/mapping_validate.xsd");
        try {
            validator.validateWithDom(validatorFile, validatingFile);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }
}
