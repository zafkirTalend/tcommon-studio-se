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
package org.talend.repository.documentation;

/**
 * ftang class global comment. Detailled comment.
 */
public enum ERepositoryActionName {

    JOB_RESTORE("job restore"),
    JOB_MOVE("job move"),
    JOB_COPY("job copy"),

    JOB_CREATE("job create"),
    JOB_SAVE("job save"),
    JOB_PROPERTIES_CHANGE("job properties change"),
    JOB_DELETE_TO_RECYCLE_BIN("job delete to recycle bin"),
    JOB_DELETE_FOREVER("job delete forever"),

    // for normal job
    FOLDER_CREATE("folder create"),
    FOLDER_DELETE("folder delete"),
    FOLDER_RENAME("folder rename"),
    FOLDER_MOVE("folder move"),

    // for joblet
    JOBLET_FOLDER_CREATE("joblet.folder.create"),
    JOBLET_FOLDER_DELETE("joblet.folder.delete"),
    JOBLET_FOLDER_RENAME("joblet.folder.rename"),
    JOBLET_FOLDER_MOVE("joblet.folder.move");

    private String name;

    /**
     * ftang ERepositoryActionName constructor comment.
     * 
     * @param name
     */
    private ERepositoryActionName(String name) {
        this.name = name;
    }

    /**
     * ftang Comment method "getName".
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }
}
