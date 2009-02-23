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
package org.talend.commons.utils.generation;

import org.eclipse.core.runtime.Platform;

/**
 * Utilities around perl stuff. <br/>
 * 
 * $Id: JavaUtils.java 1804 2007-02-04 09:50:15Z rli $
 * 
 */
public final class JavaUtils {

    public static final String JAVAMODULE_PLUGIN_ID = "org.talend.designer.codegen.javamodule"; //$NON-NLS-1$

    public static final String JAVA_LAUNCHCONFIGURATION = "org.talend.designer.runprocess.launchConfigurationJava"; //$NON-NLS-1$

    public static final String PROCESSOR_TYPE = "javaProcessor"; //$NON-NLS-1$

    /** added by rxl. */
    public static final String JAVATIP = "//The function of generating Java code haven't achive yet" //$NON-NLS-1$
            + System.getProperty("line.separator") + "public class JavaTest extends Test {}"; //$NON-NLS-1$ //$NON-NLS-2$

    /** Java project name. */
    public static final String JAVA_PROJECT_NAME = ".Java"; //$NON-NLS-1$

    /** Java File Extension. */
    public static final String JAVA_EXTENSION = ".java"; //$NON-NLS-1$

    /** Java sqltemplate Extension. */
    public static final String JAVA_SQLPATTERN_EXTENSION = ".sqltemplate"; //$NON-NLS-1$

    /** Java Context Extension. */
    public static final String JAVA_CONTEXT_EXTENSION = ".properties"; //$NON-NLS-1$

    /** Java Directory. */
    public static final String JAVA_DIRECTORY = "java"; //$NON-NLS-1$

    /** Java Routines Directory. */
    public static final String JAVA_ROUTINES_DIRECTORY = "routines"; //$NON-NLS-1$

    /** Java SQLTemplate Directory. */
    public static final String JAVA_SQLPATTERNS_DIRECTORY = "sqltemplates"; //$NON-NLS-1$

    /** Java Routines Directory. */
    public static final String JAVA_SYSTEM_ROUTINES_DIRECTORY = "system"; //$NON-NLS-1$

    /** Java UserDefined Directory */
    public static final String JAVA_USER_DEFINED = "user defined"; //$NON-NLS-1$

    /** Java Classes Directory. */
    public static final String JAVA_CLASSES_DIRECTORY = "classes"; //$NON-NLS-1$

    /** Java Lib Directory. */
    public static final String JAVA_LIB_DIRECTORY = "lib"; //$NON-NLS-1$

    /** Java Source Directory. */
    public static final String JAVA_SRC_DIRECTORY = "src"; //$NON-NLS-1$

    /** Java ClassPath Separator. */
    public static final String JAVA_CLASSPATH_SEPARATOR = (Platform.getOS().compareTo(Platform.WS_WIN32) == 0) ? ";" : ":"; //$NON-NLS-1$ //$NON-NLS-2$
}
