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
package org.talend.core.repository;

import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IProxyRepositoryService;
import org.talend.repository.model.ProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class ProxyRepositoryService implements IProxyRepositoryService {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.model.IProxyRepositoryService#getProxyRepositoryFactory()
     */
    @Override
    public IProxyRepositoryFactory getProxyRepositoryFactory() {
        return ProxyRepositoryFactory.getInstance();
    }

}
