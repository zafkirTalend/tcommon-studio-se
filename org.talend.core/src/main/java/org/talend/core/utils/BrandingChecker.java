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
package org.talend.core.utils;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.talend.commons.CommonsPlugin;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ui.branding.IBrandingService;

/**
 * DOC talend class global comment. Detailled comment
 */
public class BrandingChecker {

    private static boolean initialized = false;

    private static boolean isBrandingChanged;

    private static final String LAST_STARTED_PRODUCT = "last started product"; //$NON-NLS-1$

    public static boolean isBrandingChanged() {
        if (!initialized) {
            if (CommonsPlugin.isHeadless()) {
                isBrandingChanged = false;
            } else {
                IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                        IBrandingService.class);
                final String fullProductName = brandingService.getFullProductName();

                Display display = Display.getDefault();
                if (display == null) {
                    display = Display.getCurrent();
                }
                display.syncExec(new Runnable() {

                    public void run() {
                        IPreferenceStore preferenceStore = CorePlugin.getDefault().getPreferenceStore();
                        String oldBrandingName = preferenceStore.getString(LAST_STARTED_PRODUCT);
                        if (oldBrandingName == null || oldBrandingName.equals("") || !oldBrandingName.equals(fullProductName)) { //$NON-NLS-1$
                            isBrandingChanged = true;
                            preferenceStore.setValue(LAST_STARTED_PRODUCT, fullProductName);
                        }
                    }
                });
            }
            initialized = true;
        }
        return isBrandingChanged;
    }

}
