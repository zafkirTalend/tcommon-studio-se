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
package org.talend.repository.tester;

import org.talend.repository.model.RepositoryNode;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RecycleBinNodeTester extends AbstractNodeTester {

    private static final String IS_RECYCLE_BIN = "isRecycleBin"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.tester.AbstractNodeTester#testProperty(java.lang.Object, java.lang.String,
     * java.lang.Object[], java.lang.Object)
     */
    @Override
    protected Boolean testProperty(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) receiver;
            if (IS_RECYCLE_BIN.equals(property)) {
                return isRecycleBin(repositoryNode);
            }
        }
        return null;
    }

    public boolean isRecycleBin(RepositoryNode repositoryNode) {
        // boolean is = repositoryNode.getProperties(EProperties.CONTENT_TYPE) == ERepositoryObjectType.RECYCLE_BIN;
        // return is;
        return repositoryNode.isBin();
    }
}
