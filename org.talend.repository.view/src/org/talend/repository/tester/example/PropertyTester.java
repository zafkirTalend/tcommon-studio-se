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
package org.talend.repository.tester.example;

import org.eclipse.core.runtime.Assert;

public class PropertyTester extends org.eclipse.core.expressions.PropertyTester {

    public PropertyTester() {

    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if ("isJob".equals(property)) { //$NON-NLS-1$
            return receiver != null && receiver.toString().startsWith("job"); //$NON-NLS-1$
        }
        if ("isBM".equals(property)) { //$NON-NLS-1$

            boolean isBm = receiver != null && receiver.toString().startsWith("bm"); //$NON-NLS-1$
            return isBm;
        }
        Assert.isTrue(false);
        return false;
    }

}
