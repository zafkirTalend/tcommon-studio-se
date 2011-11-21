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
package org.talend.core.updatesite;

/**
 * DOC hywang class global comment. Detailled comment
 */
public interface IUpdateSiteBean {

    public IPatchClassfier getClassifer();

    public String getGroupID();

    public String getArtifactID();

    public String getVersion();

    public String getPackaging();

    public String getTempFolder();

    public String getURL();

    public void setURL(String url);
}
