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
package org.talend.dataquality.helpers;

import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisFactory;

/**
 * @author scorreia
 * 
 * Helper class.
 */
public class AnalysisHelper {

    private AnalysisHelper() {
    }

    /**
     * Method "createAnalysis".
     * 
     * @param name the name of the analysis
     * @return the new analysis with the given name
     */
    public static Analysis createAnalysis(String name) {
        Analysis analysis = AnalysisFactory.eINSTANCE.createAnalysis();
        analysis.setName(name);
        return analysis;
    }
}
