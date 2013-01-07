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
package org.talend.repository.metadata.seeker;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.seeker.AbstractRepoViewSeeker;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class NormalMetadatasRepositorySeeker extends AbstractRepoViewSeeker {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.repository.seeker.AbstractRepoViewSeeker#validType(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    protected boolean validType(ERepositoryObjectType itemType) {
        if (itemType != null) {
            if (itemType.equals(ERepositoryObjectType.METADATA_CONNECTIONS)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_DELIMITED)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_EXCEL)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_FTP)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_LDIF)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_POSITIONAL)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_REGEXP)
                    || itemType.equals(ERepositoryObjectType.METADATA_FILE_XML)
                    || itemType.equals(ERepositoryObjectType.METADATA_GENERIC_SCHEMA)
                    || itemType.equals(ERepositoryObjectType.METADATA_LDAP_SCHEMA)
                    || itemType.equals(ERepositoryObjectType.METADATA_SALESFORCE_SCHEMA)
                    || itemType.equals(ERepositoryObjectType.METADATA_WSDL_SCHEMA)) {
                return true;
            }
        }
        return false;
    }

}
