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
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.IRepositoryNode.EProperties;
import org.talend.repository.model.RepositoryNode;

public class ExampleTester extends org.eclipse.core.expressions.PropertyTester {

    public ExampleTester() {

    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if ("isJob".equals(property) && receiver instanceof RepositoryNode) { //$NON-NLS-1$

            RepositoryNode repositoryNode = (RepositoryNode) receiver;
            boolean isJob = repositoryNode.getProperties(EProperties.CONTENT_TYPE) == ERepositoryObjectType.PROCESS
                    && repositoryNode.getType() == ENodeType.REPOSITORY_ELEMENT;
            return isJob;
        }
        if ("isBM".equals(property)) { //$NON-NLS-1$

            boolean isBm = receiver != null && receiver.toString().startsWith("bm"); //$NON-NLS-1$
            return isBm;
        }
        Assert.isTrue(false);
        return false;
    }

}
