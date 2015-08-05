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
package org.talend.repository.tester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.Assert;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.IRepositoryNode.EProperties;
import org.talend.repository.model.RepositoryNode;

public abstract class AbstractNodeTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof RepositoryNode) {
            Boolean testProperty = testProperty(receiver, property, args, expectedValue);
            if (testProperty != null) {
                return testProperty.booleanValue();
            }
            Assert.isTrue(false);// cause we should never be here
        }
        return false;
    }

    /**
     * 
     * DOC ggu Comment method "testProperty".
     * 
     * @param receiver
     * @param property
     * @param args
     * @param expectedValue
     * @return if null, will assert it, if not null, will return the value;
     */
    protected abstract Boolean testProperty(Object receiver, String property, Object[] args, Object expectedValue);

    public boolean isTypeNode(RepositoryNode repositoryNode, ERepositoryObjectType type) {
        ERepositoryObjectType contentType = (ERepositoryObjectType) repositoryNode.getProperties(EProperties.CONTENT_TYPE);
        return contentType != null && contentType.equals(type);
    }
}
