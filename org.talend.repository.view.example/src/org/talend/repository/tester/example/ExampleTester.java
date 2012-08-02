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
        if ("isJob".equals(property)) { //$NON-NLS-1$

            return isJob(receiver);
        }
        if ("isBM".equals(property)) { //$NON-NLS-1$

            boolean isBm = receiver != null && receiver.toString().startsWith("bm"); //$NON-NLS-1$
            return isBm;
        }
        Assert.isTrue(false);
        return false;
    }

    /**
     * DOC sgandon Comment method "isJob".
     * 
     * @param repositoryNode
     * @return
     */
    public boolean isJob(Object receiver) {
        boolean isJob = false;

        if (receiver instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) receiver;

            isJob = repositoryNode.getProperties(EProperties.CONTENT_TYPE) == ERepositoryObjectType.PROCESS
                    && repositoryNode.getType() == ENodeType.REPOSITORY_ELEMENT;
        }// else return false
        return isJob;
    }

}
