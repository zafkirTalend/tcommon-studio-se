// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.model;

import org.eclipse.jdt.core.JavaCore;
import org.talend.commons.utils.generation.JavaUtils;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 * if OEM, maybe need change too.
 */
@SuppressWarnings("nls")
public interface TalendMavenContants {

    /* same as the JavaUtils.JAVA_PROJECT_NAME */
    static final String PROJECT_NAME = ".Java";

    static final String DEFAULT_GROUP_ID = "org.talend";

    static final String DEFAULT_CODE_PROJECT_ARTIFACT_ID = "project";

    static final String DEFAULT_VERSION = "0.1";

    static final String DEFAULT_ENCODING = "UTF-8";

    static final String DEFAULT_ROUTINES_ARTIFACT_ID = JavaUtils.ROUTINE_JAR_NAME;

    static final String DEFAULT_JUNIT_ARTIFACT_GROUP = "junit";

    static final String DEFAULT_JUNIT_ARTIFACT_ID = "junit";

    static final String DEFAULT_JUNIT_ARTIFACT_VERSION = "3.8.1";

    static final String DEFAULT_JUNIT_ARTIFACT_SCOPE = "test";

    static final String DEFAULT_JDK_VERSION = JavaCore.VERSION_1_7;

    static final String CURRENT_PATH = ".";

}
