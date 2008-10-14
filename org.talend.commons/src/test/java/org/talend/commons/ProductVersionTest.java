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
package org.talend.commons;

import junit.framework.TestCase;

/***/
public class ProductVersionTest extends TestCase {
    
    public void testEquals() {
        assertEquals(new ProductVersion(1, 2, 3), new ProductVersion(1, 2, 3));
        assertEquals(new ProductVersion(11, 22, 33), new ProductVersion(11, 22, 33));
        if (new ProductVersion(1, 2, 3).equals(new ProductVersion(1, 2, 4))) {
            fail();
        }
    }
    
    public void testParse() {
        assertEquals(new ProductVersion(1, 2, 3), ProductVersion.fromString("1.2.3"));
        assertEquals(new ProductVersion(1, 2, 3), ProductVersion.fromString("1.2.3.r12345"));
        assertEquals(new ProductVersion(1, 2, 3), ProductVersion.fromString("1.2.3RC1"));
        assertEquals(new ProductVersion(1, 2, 3), ProductVersion.fromString("1.2.3RC1.r12345"));
    }
    
    public void testCompare() {
        if (new ProductVersion(1, 2, 3).compareTo(new ProductVersion(2, 3, 4)) > 0) {
            fail();
        }

        if (new ProductVersion(1, 2, 3).compareTo(new ProductVersion(2, 0, 0)) > 0) {
            fail();
        }

        if (new ProductVersion(2, 3, 4).compareTo(new ProductVersion(2, 3, 5)) > 0) {
            fail();
        }

        if (new ProductVersion(2, 3, 4).compareTo(new ProductVersion(2, 3, 4)) != 0) {
            fail();
        }

    }
}
