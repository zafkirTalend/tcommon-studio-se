// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.model;

import org.talend.core.IService;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;

/**
 * ggu class global comment. Detailled comment
 */
public interface IMetadataManagementUiService extends IService {

    public DatabaseConnection cloneOriginalValueConnection(DatabaseConnection dbConn);

    public DatabaseConnection cloneOriginalValueConnection(DatabaseConnection dbConn, boolean defaultContext);

    public DatabaseConnection cloneOriginalValueConnection(DatabaseConnection dbConn, boolean defaultContext,
            String selectedContext);
}
