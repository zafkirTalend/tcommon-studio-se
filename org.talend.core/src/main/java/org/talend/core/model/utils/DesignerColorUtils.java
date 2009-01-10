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
package org.talend.core.model.utils;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.talend.commons.utils.image.ColorUtils;
import org.talend.core.CorePlugin;
import org.talend.core.model.process.EConnectionType;

/**
 * ggu class global comment. Detailled comment
 */
public final class DesignerColorUtils {

    public static final RGB SUBJOB_TITLE_COLOR = new RGB(160, 190, 240);

    public static final RGB SUBJOB_COLOR = new RGB(220, 220, 250);

    // public static final RGB SUBJOB_COLOR = new RGB(255, 255, 255);

    public static final String SUBJOB_TITLE_COLOR_NAME = "subjobTitleColor"; //$NON-NLS-1$

    public static final String SUBJOB_COLOR_NAME = "subjobColor"; //$NON-NLS-1$

    public static String getPreferenceConnectionName(EConnectionType connType) {
        if (connType == null) {
            return null;
        }
        return connType.getName() + "_COLOR"; //$NON-NLS-1$
    }

    public static RGB getPreferenceConnectionColor(EConnectionType connType, RGB defaultRGB) {
        if (connType == null || defaultRGB == null) {
            return defaultRGB;
        }
        String rgb = CorePlugin.getDefault().getDesignerCoreService().getPreferenceStore(getPreferenceConnectionName(connType));
        return ColorUtils.parseStringToRGB(rgb, defaultRGB);
    }

    public static RGB getPreferenceConnectionColor(EConnectionType connType) {
        return getPreferenceConnectionColor(connType, connType.getRGB());
    }

    /**
     * used for initialize preference.
     */
    public static void initPreferenceDefault(IPreferenceStore store) {
        if (store == null) { // store must be the designer core preference store.
            return;
        }
        // subjob
        PreferenceConverter.setDefault(store, DesignerColorUtils.SUBJOB_COLOR_NAME, DesignerColorUtils.SUBJOB_COLOR);
        PreferenceConverter.setDefault(store, DesignerColorUtils.SUBJOB_TITLE_COLOR_NAME, DesignerColorUtils.SUBJOB_TITLE_COLOR);
        // connection
        for (EConnectionType connType : EConnectionType.values()) {
            PreferenceConverter.setDefault(store, getPreferenceConnectionName(connType), connType.getRGB());
        }
    }

    /**
     * 
     * ggu Comment method "getPreferenceSubjobColor".
     * 
     * @param name must be SUBJOB_TITLE_COLOR_NAME and SUBJOB_COLOR_NAME
     * @param defaultColor if can't found the preference value, will use the default color.
     * @return
     */
    public static RGB getPreferenceSubjobRGB(String name, RGB defaultColor) {
        if (name == null || defaultColor == null || (!name.equals(SUBJOB_COLOR_NAME) && !name.equals(SUBJOB_TITLE_COLOR_NAME))) {
            return defaultColor;
        }
        String colorStr = CorePlugin.getDefault().getDesignerCoreService().getPreferenceStore(name);
        return ColorUtils.parseStringToRGB(colorStr, defaultColor);
    }
}
