// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.branding;

/**
 * wzhang class global comment. Detailled comment
 */
public abstract class AbstractBrandingService implements IBrandingService {

    public String getJobLicenseHeader(String version) {
        String contents = "\n//\n// " + BrandingConstants.GENERATEDCODE_COPYRIGHT_TITLE + "\n//\n// " //$NON-NLS-1$ //$NON-NLS-2$
                + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY1 + "_" + this.getFullProductName() + "\n// " //$NON-NLS-1$ //$NON-NLS-2$
                + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY2 + " " + version + ")\n// " //$NON-NLS-1$ //$NON-NLS-2$
                + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY3 + "\n// " + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY4 //$NON-NLS-1$
                + "\n// " + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY5 + "\n//\n"; //$NON-NLS-1$ //$NON-NLS-2$
        return BrandingConstants.LICENSE_LINE + contents + BrandingConstants.LICENSE_LINE;
    }

    public String getRoutineLicenseHeader() {
        String contents = BrandingConstants.GENERATEDCODE_COPYRIGHT_TITLE + "\n//\n// " //$NON-NLS-1$
                + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY3 + "\n// " + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY4 //$NON-NLS-1$
                + "\n// " + BrandingConstants.GENERATEDCODE_COPYRIGHT_BODY5; //$NON-NLS-1$
        return contents;
    }
}
