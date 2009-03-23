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
package org.talend.repository.preview;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public class SalesforceSchemaBean {

    private String webServerUrl = null;

    private String userName = null;

    private String password = null;

    private String moduleName = null;

    private String queryCondition = null;

    private boolean useCustomModule = false;

    /**
     * Getter for webServerUrl.
     * 
     * @return the webServerUrl
     */
    public String getWebServerUrl() {
        return this.webServerUrl;
    }

    /**
     * Sets the webServerUrl.
     * 
     * @param webServerUrl the webServerUrl to set
     */
    public void setWebServerUrl(String webServerUrl) {
        this.webServerUrl = webServerUrl;
    }

    /**
     * Getter for userName.
     * 
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the userName.
     * 
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for password.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for moduleName.
     * 
     * @return the moduleName
     */
    public String getModuleName() {
        return this.moduleName;
    }

    /**
     * Sets the moduleName.
     * 
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Getter for queryCondition.
     * 
     * @return the queryCondition
     */
    public String getQueryCondition() {
        return this.queryCondition;
    }

    /**
     * Sets the queryCondition.
     * 
     * @param queryCondition the queryCondition to set
     */
    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public boolean isUseCustomModule() {
        return useCustomModule;
    }

    public void setUseCustomModule(boolean useCustomModule) {
        this.useCustomModule = useCustomModule;
    }

}
