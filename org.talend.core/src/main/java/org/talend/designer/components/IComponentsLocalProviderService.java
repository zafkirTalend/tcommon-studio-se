// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.designer.components;

import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.core.IService;

/**
 * DOC Administrator class global comment. Detailled comment <br/>
 * 
 */
public interface IComponentsLocalProviderService extends IService {

    public static final String FORMAT_IDS = "formatIds";

    public static final String IDS_SEPARATOR = ";";

    public static final String PALETTE_ENTRY_TYPE = ":ToolEntry";

    public static final String PALETTE_CONTAINER_TYPE = ":PaletteContainer";

    public final static String PREFERENCE_TYPE_HINT = ":HINT";

    public final static String PREFERENCE_TYPE_LABEL = ":LABEL";

    public final static String PREFERENCE_TYPE_CONNECTION = ":CONNECTION";

    public boolean isAvoidToShowJobAfterDoubleClick();

    public IPreferenceStore getPreferenceStore();

}
