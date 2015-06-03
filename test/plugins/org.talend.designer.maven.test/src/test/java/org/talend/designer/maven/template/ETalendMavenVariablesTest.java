// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.template;

import org.junit.Assert;
import org.junit.Test;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ETalendMavenVariablesTest {

    @Test
    public void testCleanupVariable() {
        Assert.assertNull(ETalendMavenVariables.cleanupVariable(null));
        Assert.assertEquals("", ETalendMavenVariables.cleanupVariable(""));
        Assert.assertEquals("abc", ETalendMavenVariables.cleanupVariable("abc"));
        Assert.assertEquals("@abc", ETalendMavenVariables.cleanupVariable("@abc"));
        Assert.assertEquals("abc@", ETalendMavenVariables.cleanupVariable("abc@"));
        Assert.assertEquals("", ETalendMavenVariables.cleanupVariable("@abc@"));
        Assert.assertEquals("a", ETalendMavenVariables.cleanupVariable("a@bc@"));
        Assert.assertEquals("123", ETalendMavenVariables.cleanupVariable("@abc@123@xyz@"));
        Assert.assertEquals("a123", ETalendMavenVariables.cleanupVariable("a@bc@123@xyz@"));
        Assert.assertEquals("a123\t", ETalendMavenVariables.cleanupVariable("a@bc@123\t@xyz@"));
        Assert.assertEquals("a123\n", ETalendMavenVariables.cleanupVariable("a@bc@123\n@xyz@"));
    }
}
