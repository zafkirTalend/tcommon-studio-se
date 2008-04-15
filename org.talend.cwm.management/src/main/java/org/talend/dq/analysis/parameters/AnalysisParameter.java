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

import java.util.HashMap;

import org.talend.cwm.management.api.FolderProvider;
import org.talend.dataquality.analysis.AnalysisType;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class AnalysisParameter {

    private static String analysisTypeName;
    
    private HashMap<String, String> analysisMetadate;

    private FolderProvider folderProvider;
    
    /**
     * Getter for analysisTypeName.
     * @return the analysisTypeName
     */
    public String getAnalysisTypeName() {
        return analysisTypeName;
    }

    
    /**
     * Sets the analysisTypeName.
     * @param analysisTypeName the analysisTypeName to set
     */
    public void setAnalysisTypeName(String typeName) {
        analysisTypeName = typeName;
    }
    
    /**
     * Sets the analysisMetadate.
     * @param analysisMetadate the analysisMetadate to set
     */
    public void setAnalysisMetadate(HashMap<String, String> analysisMetadate) {
        this.analysisMetadate = analysisMetadate;
    }
    
    public AnalysisType getAnalysisType() {
        if (getAnalysisTypeName() != null) {
            
            return AnalysisType.get(getAnalysisTypeName());
        } else {
            
            return null;
        }
    }
    
    public String getAnalysisName() {
        
        return analysisMetadate.get(IAnalysisParameterConstant.ANALYSIS_NAME);
    }
    
    public String getAnalysisPurpose() {
        
        return analysisMetadate.get(IAnalysisParameterConstant.ANALYSIS_PURPOSE);
    }
    
    public String getAnalysisDescription() {
        
        return analysisMetadate.get(IAnalysisParameterConstant.ANALYSIS_DESCRIPTION);
    }

    public String getAnalysisAuthor() {
        
        return analysisMetadate.get(IAnalysisParameterConstant.ANALYSIS_AUTHOR);
    }
    
    public String getAnalysisStatus() {
        
        return analysisMetadate.get(IAnalysisParameterConstant.ANALYSIS_STATUS);
    }

    public String getAnalysisVersion() {
        
        return analysisMetadate.get(IAnalysisParameterConstant.ANALYSIS_VERSION);
    }
    
    /**
     * Getter for folderProvider.
     * @return the folderProvider
     */
    public FolderProvider getFolderProvider() {
        return this.folderProvider;
    }


    
    /**
     * Sets the folderProvider.
     * @param folderProvider the folderProvider to set
     */
    public void setFolderProvider(FolderProvider folderProvider) {
        this.folderProvider = folderProvider;
    }

}
