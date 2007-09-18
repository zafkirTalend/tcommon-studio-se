// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
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

    public static final String TEMP_DIRECTORY = "temp"; //$NON-NLS-1$

    public static final String IMG_DIRECTORY = "images"; //$NON-NLS-1$

    public static final String IMG_DIRECTORY_OF_JOB_OUTLINE = "images/job_outlines"; //$NON-NLS-1$

    public static final String SYSTEM_DIRECTORY = "system"; //$NON-NLS-1$

    public static final String CONTEXT_AND_VARIABLE_PATTERN = "^[a-z]+[a-zA-Z0-9\\_]*$"; //$NON-NLS-1$

    public static final String PROJECT_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9 \\-_]*$"; //$NON-NLS-1$

    public static final String CODE_ITEM_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9\\_]*$"; //$NON-NLS-1$

    public static final String FOLDER_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9\\_]*$"; //$NON-NLS-1$

    public static final String REPOSITORY_ITEM_PATTERN = "^[a-zA-Z0-9\\.\\-\\_\\ \\(\\)\\[\\]=]+$"; //$NON-NLS-1$

    public static final String PORT_ITEM_PATTERN = "^[0-9]";

    public static final String MAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)"; //$NON-NLS-1$

    public static String getPattern(ERepositoryObjectType type) {
        switch (type) {
        case FOLDER:
            return FOLDER_PATTERN;
        case PROCESS:
        case ROUTINES:
            return CODE_ITEM_PATTERN;
        default:
            return REPOSITORY_ITEM_PATTERN;
        }
    }
}
