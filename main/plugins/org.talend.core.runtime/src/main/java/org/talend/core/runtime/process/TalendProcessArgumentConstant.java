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
package org.talend.core.runtime.process;

/**
 * DOC ggu class global comment. Detailled comment
 */
@SuppressWarnings("nls")
public interface TalendProcessArgumentConstant {

    static final String ARG_GOAL = "MAVEN_GOAL";

    static final String ARG_PROGRAM_ARGUMENTS = "PROGRAM_ARGUMENTS";

    static final String ARG_ENABLE_STATS = "ENABLE_STATS";

    static final String ARG_ENABLE_TRACS = "ENABLE_TRACS";

    static final String ARG_PORT_STATS = "PORT_OF_STATS";

    static final String ARG_PORT_TRACS = "PORT_OF_TRACS";

    static final String ARG_ENABLE_APPLY_CONTEXT_TO_CHILDREN = "ENABLE_APPLY_CONTEXT_TO_CHILDREN";

    static final String ARG_GENERATE_OPTION = "GENERATE_OPTION";

    static final String ARG_NEED_CONTEXT = "NEED_CONTEXT";

    static final String ARG_CONTEXT_NAME = "CONTEXT_NAME";

    static final String ARG_CONTEXT_PARAMS = "CONTEXT_PARAMS";

    static final String ARG_NEED_LOG4J_LEVEL = "NEED_LOG4J_LEVEL";

    static final String ARG_LOG4J_LEVEL = "LOG4J_LEVEL";

    /*
     * command
     */
    public static final String CMD_ARG_CONTEXT_PARAMETER = "--context_param"; //$NON-NLS-1$

    public static final String CMD_ARG_CONTEXT_NAME = "--context=";

    public static final String CMD_ARG_LOG4J_LEVEL = "--log4jLevel=";

    public static final String CMD_ARG_STATS_PORT = "--stat_port=";

    public static final String CMD_ARG_TRACE_PORT = "--trace_port=";

}
