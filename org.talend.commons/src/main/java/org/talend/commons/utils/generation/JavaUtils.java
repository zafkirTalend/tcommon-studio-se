// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.utils.generation;

/**
 * Utilities around perl stuff. <br/>
 * 
 * $Id: JavaUtils.java 1804 2007-02-04 09:50:15Z rli $
 * 
 */
public final class JavaUtils {
    
    public static final String JAVAMODULE_PLUGIN_ID = "org.talend.designer.codegen.javamodule";

    public static final String JAVA_LAUNCHCONFIGURATION = "org.talend.designer.runprocess.launchConfigurationJava"; //$NON-NLS-1$

    public static final String PROCESSOR_TYPE = "javaProcessor"; //$NON-NLS-1$

    /** added by rxl. */
    public static final String JAVATIP = "//The function of generating Java code haven't achive yet" //$NON-NLS-1$
            + System.getProperty("line.separator") + "public class JavaTest extends Test {}"; //$NON-NLS-1$ //$NON-NLS-2$

    /** Java project name. */
    public static final String JAVA_PROJECT_NAME = ".Java"; //$NON-NLS-1$

    /** Java File Extension. */
    public static final String JAVA_EXTENSION = ".java";
    
    /** Java Context Extension. */
    public static final String JAVA_CONTEXT_EXTENSION = ".properties";

    /** Java Directory. */
    public static final String JAVA_DIRECTORY = "java";
    
    /** Java Routines Directory. */
    public static final String JAVA_ROUTINES_DIRECTORY = "routines";

    /** Java Classes Directory. */
    public static final String JAVA_CLASSES_DIRECTORY = "classes";

    /** Java Lib Directory. */
    public static final String JAVA_LIB_DIRECTORY = "lib";

    /** Java Source Directory. */
    public static final String JAVA_SRC_DIRECTORY = "src";

}
