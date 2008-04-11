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
import org.talend.dataquality.analysis.util.AnalysisSwitch;
import org.talend.dataquality.indicators.schema.ConnectionIndicator;
import org.talend.dataquality.indicators.schema.util.SchemaSwitch;

/**
 * @author scorreia
 * 
 * 
 */
public class DataqualitySwitchHelper {

    public static final SchemaSwitch<ConnectionIndicator> CONNECTION_SWITCH = new SchemaSwitch<ConnectionIndicator>() {

        @Override
        public ConnectionIndicator caseConnectionIndicator(ConnectionIndicator object) {
            return object;
        }

    };

    public static final AnalysisSwitch<Analysis> ANALYSIS_SWITCH = new AnalysisSwitch<Analysis>() {

        @Override
        public Analysis caseAnalysis(Analysis object) {
            return object;
        }

    };
}
