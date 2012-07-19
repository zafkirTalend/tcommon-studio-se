// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.rcp.intro.contentProvider;

import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;

/**
 * DOC wchen class global comment. Detailled comment
 */
public abstract class IntroProvider implements IIntroXHTMLContentProvider {

    protected boolean isItemShow(String type) {
        String branding = System.getProperty("talend.license.branding");
        if ("ANALYSIS".equals(type)) {
            if (!"TIS_EE".equals(branding) && !"TIS_EE_MPX".equals(branding) && !"TIS_PE".equals(branding)
                    && !"TIS_PE_RTX".equals(branding) && !"TIS_TE".equals(branding) && !"ESB_BPM".equals(branding)
                    && !"DS_EE_MPX".equals(branding) && !"DS_EE".equals(branding) && !"DS_PE".equals(branding)
                    && !"DS_TE".equals(branding) && !"BPM_EE_MPX".equals(branding) && !"BPM_EE".equals(branding)
                    && !"BPM_PE".equals(branding) && !"BPM_TE".equals(branding)) {
                return true;
            } else {
                return false;
            }
        } else if ("SERVICES".equals(type) || "ROUTE".equals(type)) {
            if ("ESB_BPM".equals(branding) || "DS_EE_MPX".equals(branding) || "DS_EE".equals(branding)
                    || "DS_PE".equals(branding) || "DS_TE".equals(branding) || "CLOUD_EE_MPX".equals(branding)
                    || "CLOUD_EE".equals(branding) || "CLOUD_PE".equals(branding) || "CLOUD_TE".equals(branding)
                    || "BPM_EE_MPX".equals(branding) || "BPM_EE".equals(branding) || "BPM_PE".equals(branding)
                    || "BPM_TE".equals(branding) || "DS_BD_DQ".equals(branding) || "DS_TE_DQ".equals(branding)
                    || "DS_PE_DQ".equals(branding) || "DS_CE_DQ".equals(branding) || "ESB_EE".equals(branding)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
