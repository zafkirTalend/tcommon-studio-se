// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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

import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * Defines some constants relative to repository such as file patterns.<br/>
 * 
 * $Id: RepositoryConstants.java 8 2006-10-02 09:09:23 +0000 (星期一, 02 十月 2006) mhirt $
 * 
 */
public class RepositoryConstants {

    public static final String TABLE = "TABLE"; //$NON-NLS-1$

    public static final String TEMP_DIRECTORY = "temp"; //$NON-NLS-1$

    public static final String IMG_DIRECTORY = "images"; //$NON-NLS-1$

    public static final String IMG_DIRECTORY_OF_JOB_OUTLINE = "images/job_outlines"; //$NON-NLS-1$

    public static final String IMG_DIRECTORY_OF_JOBLET_OUTLINE = "images/joblet_outlines"; //$NON-NLS-1$

    public static final String JOB_DOCUMENTATION_PATH = "documentations/generated/jobs"; //$NON-NLS-1$

    public static final String JOBLET_DOCUMENTATION_PATH = "documentations/generated/joblets"; //$NON-NLS-1$

    public static final String SYSTEM_DIRECTORY = "system"; //$NON-NLS-1$

    public static final String USER_DEFINED = "UserDefined"; //$NON-NLS-1$

    public static final String CONTEXT_AND_VARIABLE_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9\\_]*$"; //$NON-NLS-1$

    public static final String PROJECT_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9 \\-_]*$"; //$NON-NLS-1$

    public static final String CODE_ITEM_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9\\_]*$"; //$NON-NLS-1$

    public static final String SCHEMA_NAME_PATTERN = "^[a-zA-Z0-9\\_]*$";; //$NON-NLS-1$

    public static final String SCHEMA_NAME_VALIDATED = "^[a-zA-Z_][a-zA-Z_0-9]*$"; //$NON-NLS-1$

    public static final String REPOSITORY_SCHEMA_PATTERN = "^[a-zA-Z0-9\\_]+$"; //$NON-NLS-1$

    public static final String FOLDER_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9\\_]*$"; //$NON-NLS-1$

    public static final String REPOSITORY_ITEM_PATTERN_INTERN = "a-zA-Z0-9\\.\\-\\_\\ \\(\\)\\[\\]="; //$NON-NLS-1$

    public static final String REPOSITORY_ITEM_PATTERN = "^[" + REPOSITORY_ITEM_PATTERN_INTERN + "]+$"; //$NON-NLS-1$

    public static final String REPOSITORY_ITEM_WILDCARD = "^[" + REPOSITORY_ITEM_PATTERN_INTERN + "\\*\\?]+$"; //$NON-NLS-1$

    public static final String PATH_WILDCARD = "^[a-zA-Z\\*\\?/]+[a-zA-Z0-9\\_\\*\\?/]*$"; //$NON-NLS-1$

    public static final String PORT_ITEM_PATTERN = "^[0-9]"; //$NON-NLS-1$

    public static final String MAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)"; //$NON-NLS-1$

    public static String getPattern(ERepositoryObjectType type) {
        switch (type) {
        case FOLDER:
            return FOLDER_PATTERN;
        case PROCESS:
        case ROUTINES:
        case JOBLET:
            return CODE_ITEM_PATTERN;
        default:
            return REPOSITORY_ITEM_PATTERN;
        }
    }

    public static boolean isSystemFolder(String label) {
        if (label == null) {
            return false;
        }
        return label.equalsIgnoreCase(SYSTEM_DIRECTORY);
    }

    public static boolean isGeneratedFolder(String label) {
        if (label == null) {
            return false;
        }
        return label.equalsIgnoreCase(ERepositoryObjectType.GENERATED.toString());
    }

    public static boolean isJobsFolder(String label) {
        if (label == null) {
            return false;
        }
        return label.equalsIgnoreCase(ERepositoryObjectType.JOBS.toString());
    }

    /**
     * DOC tang Comment method "isJobletsFolder".
     * 
     * @param label
     * @return
     */
    public static boolean isJobletsFolder(String label) {
        if (label == null) {
            return false;
        }
        return label.equalsIgnoreCase(ERepositoryObjectType.JOBLETS.toString());
    }
}
