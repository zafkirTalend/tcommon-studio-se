// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.core.model.general;

import java.util.List;

/**
 * This bean is use to manage needed moduless (perl) and libraries (java).<br/>
 * 
 * $Id$
 * 
 */
public class ModuleNeeded {

    private String context;

    private String moduleName;

    private String informationMsg;

    private boolean required;

    private ELibraryInstallStatus status = ELibraryInstallStatus.UNKNOWN;

    private boolean isShow = true;

    List<InstallModule> installModule;

    List<String> installURL;

    /**
     * DOC smallet ModuleNeeded class global comment. Detailled comment <br/>
     * 
     * $Id$
     * 
     */
    public enum ELibraryInstallStatus {
        UNKNOWN,
        INSTALLED,
        UNUSED,
        NOT_INSTALLED;
    }

    /**
     * DOC smallet ModuleNeeded constructor comment.
     * 
     * @param context
     * @param moduleName
     * @param informationMsg
     * @param required
     * @param status
     */
    public ModuleNeeded(String context, String moduleName, String informationMsg, boolean required) {
        super();
        this.context = context;
        this.moduleName = moduleName;
        this.informationMsg = informationMsg;
        this.required = required;
    }

    public ModuleNeeded(String context, String moduleName, String informationMsg, boolean required, List<String> installURL) {
        super();
        this.context = context;
        this.moduleName = moduleName;
        this.informationMsg = informationMsg;
        this.required = required;
        this.installURL = installURL;
    }

    // public ModuleNeeded(String context, String moduleName, String informationMsg, boolean required,
    // List<InstallModule> installModule) {
    // super();
    // this.context = context;
    // this.moduleName = moduleName;
    // this.informationMsg = informationMsg;
    // this.required = required;
    // this.installModule = installModule;
    // }

    /**
     * Getter for installURL.
     * 
     * @return the installURL
     */
    public List<String> getInstallURL() {
        return this.installURL;
    }

    /**
     * Sets the installURL.
     * 
     * @param installURL the installURL to set
     */
    public void setInstallURL(List<String> installURL) {
        this.installURL = installURL;
    }

    /**
     * Getter for component.
     * 
     * @return the component
     */
    public String getContext() {
        return this.context;
    }

    /**
     * Sets the component.
     * 
     * @param component the component to set
     */
    public void setContext(String component) {
        this.context = component;
    }

    public List<InstallModule> getInstallModule() {
        return this.installModule;
    }

    public void setInstallModule(List<InstallModule> installModule) {
        this.installModule = installModule;
    }

    public String getInformationMsg() {
        return this.informationMsg;
    }

    public void setInformationMsg(String informationMsg) {
        this.informationMsg = informationMsg;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public ELibraryInstallStatus getStatus() {
        return this.status;
    }

    public void setStatus(ELibraryInstallStatus status) {
        this.status = status;
    }

    /**
     * Getter for isShow.
     * 
     * @return the isShow
     */
    public boolean isShow() {
        return this.isShow;
    }

    /**
     * Sets the isShow.
     * 
     * @param isShow the isShow to set
     */
    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }
}
