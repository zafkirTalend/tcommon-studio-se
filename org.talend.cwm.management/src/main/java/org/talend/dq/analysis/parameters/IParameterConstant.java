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
package org.talend.dq.analysis.parameters;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public interface IParameterConstant {

    //metadata parameter
    public static final String ANALYSIS_NAME = "AnalysisName";
    
    public static final String ANALYSIS_PURPOSE = "AnalysisPurpose";
    
    public static final String ANALYSIS_DESCRIPTION = "AnalysisDescription";
    
    public static final String ANALYSIS_AUTHOR = "ZQIN";
    
    public static final String ANALYSIS_VERSION = "0.1";
    
    public static final String ANALYSIS_STATUS = "Status";
    
    //indicator parameter
        //bins
    public static final String INDICATOR_MIN_VALUE = "BinsMinValue";
    
    public static final String INDICATOR_MAX_VALUE = "BinsMaxValue";
    
    public static final String INDICATOR_NUM_OF_BIN = "NumOfBin";
    
        //time
    public static final String INDICATOR_TIME_SLICES = "TimeSlices";
    
        //text
    public static final String INDICATOR_IGNORE_CASE = "TextIgnoreCase";
    
        //text length
    public static final String INDICATOR_COUNT_NULLS = "CountNulls";
    
    public static final String INDICATOR_COUNT_BLANKS = "CountBlanks";
    
        //data thresholds
    public static final String INDICATOR_LOWER_THRESHOLD = "LowerThreshold";
    
    public static final String INDICATOR_HIGHER_THRESHOLD = "HigherThreshold";
}
