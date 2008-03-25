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
package org.talend.cwm.management.connection;

import org.talend.dataquality.analysis.AnalysisType;

/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class AnaConnectionParameters {
    
    private AnalysisType anaConnType;
    
    private String anaTypeValue;
    
    private String anaConnName;
    
    private String anaConnPurpose;
    
    private String anaConnDescription;
    
    private String anaConnAuthor;
    
    private String anaConnVersion;
    
    private String anaConnStatus;
    
    private String anaConnPath;

    
    /**
     * Getter for anaConnType.
     * @return the anaConnType
     */
    public AnalysisType getAnaConnType() {
        return this.anaConnType;
    }

    
    /**
     * Sets the anaConnType.
     * @param anaConnType the anaConnType to set
     */
    public void setAnaConnType(AnalysisType anaConnType) {
        this.anaConnType = anaConnType;
    }

    
    /**
     * Getter for anaTypeValue.
     * @return the anaTypeValue
     */
    public String getAnaTypeValue() {
        return this.anaTypeValue;
    }

    
    /**
     * Sets the anaTypeValue.
     * @param anaTypeValue the anaTypeValue to set
     */
    public void setAnaTypeValue(String anaTypeValue) {
        this.anaTypeValue = anaTypeValue;
    }

    
    /**
     * Getter for anaConnName.
     * @return the anaConnName
     */
    public String getAnaConnName() {
        return this.anaConnName;
    }

    
    /**
     * Sets the anaConnName.
     * @param anaConnName the anaConnName to set
     */
    public void setAnaConnName(String anaConnName) {
        this.anaConnName = anaConnName;
    }

    
    /**
     * Getter for anaConnPurpose.
     * @return the anaConnPurpose
     */
    public String getAnaConnPurpose() {
        return this.anaConnPurpose;
    }

    
    /**
     * Sets the anaConnPurpose.
     * @param anaConnPurpose the anaConnPurpose to set
     */
    public void setAnaConnPurpose(String anaConnPurpose) {
        this.anaConnPurpose = anaConnPurpose;
    }

    
    /**
     * Getter for anaConnDescription.
     * @return the anaConnDescription
     */
    public String getAnaConnDescription() {
        return this.anaConnDescription;
    }

    
    /**
     * Sets the anaConnDescription.
     * @param anaConnDescription the anaConnDescription to set
     */
    public void setAnaConnDescription(String anaConnDescription) {
        this.anaConnDescription = anaConnDescription;
    }

    
    /**
     * Getter for anaConnAuthor.
     * @return the anaConnAuthor
     */
    public String getAnaConnAuthor() {
        return this.anaConnAuthor;
    }

    
    /**
     * Sets the anaConnAuthor.
     * @param anaConnAuthor the anaConnAuthor to set
     */
    public void setAnaConnAuthor(String anaConnAuthor) {
        this.anaConnAuthor = anaConnAuthor;
    }

    
    /**
     * Getter for anaConnVersion.
     * @return the anaConnVersion
     */
    public String getAnaConnVersion() {
        return this.anaConnVersion;
    }

    
    /**
     * Sets the anaConnVersion.
     * @param anaConnVersion the anaConnVersion to set
     */
    public void setAnaConnVersion(String anaConnVersion) {
        this.anaConnVersion = anaConnVersion;
    }

    
    /**
     * Getter for anaConnStatus.
     * @return the anaConnStatus
     */
    public String getAnaConnStatus() {
        return this.anaConnStatus;
    }

    
    /**
     * Sets the anaConnStatus.
     * @param anaConnStatus the anaConnStatus to set
     */
    public void setAnaConnStatus(String anaConnStatus) {
        this.anaConnStatus = anaConnStatus;
    }

    
    /**
     * Getter for anaConnPath.
     * @return the anaConnPath
     */
    public String getAnaConnPath() {
        return this.anaConnPath;
    }

    
    /**
     * Sets the anaConnPath.
     * @param anaConnPath the anaConnPath to set
     */
    public void setAnaConnPath(String anaConnPath) {
        this.anaConnPath = anaConnPath;
    }
}
