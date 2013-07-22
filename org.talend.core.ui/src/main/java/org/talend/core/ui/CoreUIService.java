// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui;

import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.ui.swt.actions.ITreeContextualAction;
import org.talend.core.model.components.ComponentPaletteUtilities;
import org.talend.core.service.ICoreUIService;
import org.talend.core.ui.actions.ActionsHelper;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class CoreUIService implements ICoreUIService {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.service.ICoreUIService#updatePalette()
     */
    @Override
    public void updatePalette() {
        ComponentPaletteUtilities.updatePalette();
    }

    @Override
    public IPreferenceStore getPreferenceStore() {
        return CoreUIPlugin.getDefault().getPreferenceStore();
    }

    @Override
    public MenuManager[] getRepositoryContextualsActionGroups() {
        return ActionsHelper.getRepositoryContextualsActionGroups();
    }

    @Override
    public List<ITreeContextualAction> getRepositoryContextualsActions() {
        return ActionsHelper.getRepositoryContextualsActions();
    }
}
