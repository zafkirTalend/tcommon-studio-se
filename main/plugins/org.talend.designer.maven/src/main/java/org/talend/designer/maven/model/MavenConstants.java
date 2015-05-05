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

import org.eclipse.m2e.core.internal.IMavenConstants;

/**
 * created by ggu on 22 Jan 2015 Detailled comment
 *
 */
@SuppressWarnings({ "nls", "restriction" })
public interface MavenConstants {

    static final String POM_VERSION = "4.0.0";

    /* same as MavenArtifactComponent.JAR */
    static final String PACKAGING_JAR = "jar";

    /* same as MavenArtifactComponent.POM */
    static final String PACKAGING_POM = "pom";

    static final String SEPERATOR = ":";

    static final String POM_NAME = "pom";

    static final String XML_EXT = ".xml";

    static final String POM_FILE_NAME = IMavenConstants.POM_FILE_NAME; // POM_NAME+XML_EXT

    static final String ASSEMBLY_NAME = "assembly";

    static final String ASSEMBLY_FILE_NAME = ASSEMBLY_NAME + XML_EXT;

    static final String GOAL_CLEAN = "clean";

    static final String GOAL_COMPILE = "compile";

    static final String GOAL_TEST = "test";

    static final String GOAL_TEST_COMPILE = "test-compile";

    static final String GOAL_PACKAGE = "package";

    static final String GOAL_INSTALL = "install";

}
