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
package org.talend.designer.components;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.talend.core.IService;

/**
 * DOC Administrator class global comment. Detailled comment <br/>
 * 
 */
public interface IComponentsLocalProviderService extends IService {

    public static final String FORMAT_IDS = "formatIds"; //$NON-NLS-1$

    public static final String IDS_SEPARATOR = ";"; //$NON-NLS-1$

    public static final String PALETTE_ENTRY_TYPE = ":ToolEntry"; //$NON-NLS-1$

    public static final String PALETTE_CONTAINER_TYPE = ":PaletteContainer"; //$NON-NLS-1$

    public final static String PREFERENCE_TYPE_HINT = ":HINT"; //$NON-NLS-1$

    public final static String PREFERENCE_TYPE_LABEL = ":LABEL"; //$NON-NLS-1$

    public final static String PREFERENCE_TYPE_CONNECTION = ":CONNECTION"; //$NON-NLS-1$

    public boolean isAvoidToShowJobAfterDoubleClick();

    public IPreferenceStore getPreferenceStore();

    public AbstractUIPlugin getPlugin();

}
