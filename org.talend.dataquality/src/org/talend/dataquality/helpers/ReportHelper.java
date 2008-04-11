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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.reports.TdReport;
import orgomg.cwm.analysis.informationvisualization.RenderedObject;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class ReportHelper {

    /**
     * Method "getAnalyses".
     * 
     * @param report
     * @return a list of analyses or an empty list. Do not use this list to add analysis to the report.
     */
    public List<Analysis> getAnalyses(TdReport report) {
        List<Analysis> analyses = new ArrayList<Analysis>();
        EList<RenderedObject> components = report.getComponent();
        for (RenderedObject renderedObject : components) {
            Analysis analysis = DataqualitySwitchHelper.ANALYSIS_SWITCH.doSwitch(renderedObject);
            if (analysis != null) {
                analyses.add(analysis);
            }
        }
        return analyses;
    }

    /**
     * Method "removeAnalyses".
     * 
     * @param report
     * @param analyses analyses to remove from the report
     * @return true if the analyses list of the report changed as a result of the call.
     */
    public boolean removeAnalyses(TdReport report, Collection<Analysis> analyses) {
        return report.getComponent().removeAll(analyses);
    }

    /**
     * Method "addAnalyses".
     * 
     * @param analyses a collection of analyses.
     * @param report a report (must not be null)
     * @return true if the analysis list changed as a result of the call.
     */
    public boolean addAnalyses(Collection<Analysis> analyses, TdReport report) {
        return report.getComponent().addAll(analyses);
    }

    public void setHeader(TdReport report, String header) {
        // TODO scorreia set header in the report
    }

    public void setFooter(TdReport report, String footer) {
        // TODO scorreia set footer in the report
    }

    public void mustRefreshAllAnalyses(TdReport report) {
        // TODO scorreia set boolean
    }
}
