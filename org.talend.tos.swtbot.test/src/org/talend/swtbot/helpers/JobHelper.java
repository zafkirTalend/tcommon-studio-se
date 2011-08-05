// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.swtbot.helpers;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class JobHelper implements Helper {

    /**
     * DOC fzhong Comment method "filterStatistics". Filter all statistics for the execution result.
     * 
     * @param execution The result string in execution console
     * @return A string between statistics tags
     */
    public static String filterStatistics(String execution) {
        int beginIndex = execution.indexOf("[statistics] connected\n") + "[statistics] connected\n".length();
        int endIndex = execution.indexOf("\n[statistics] disconnected");
        return execution.substring(beginIndex, endIndex);
    }

}
