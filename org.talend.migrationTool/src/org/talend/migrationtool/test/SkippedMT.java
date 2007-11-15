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
package org.talend.migrationtool.test;

import org.talend.core.model.general.Project;
import org.talend.core.model.migration.AbstractProjectMigrationTask;
import org.talend.core.model.migration.IProjectMigrationTask;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class SkippedMT extends AbstractProjectMigrationTask implements IProjectMigrationTask {

    public ExecutionResult execute(Project project) {
        return ExecutionResult.SKIPPED;
    }

}
