// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.migrationtool.model;

import org.talend.core.model.migration.AbstractMigrationTask;
import org.talend.core.model.migration.IWorkspaceMigrationTask;

/**
 * Used to know if workspace has been initialized.<br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class InitWorkspaceMigrationTask extends AbstractMigrationTask implements IWorkspaceMigrationTask {

    public boolean execute() {
        return true;
    }

}
