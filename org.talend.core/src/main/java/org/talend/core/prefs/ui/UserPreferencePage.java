// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.prefs.ui;

import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.prefs.PreferenceManipulator;

/**
 * Preference page for known users. <br/>
 * 
 * $Id$
 * 
 */
public class UserPreferencePage extends ListPreferencePage {

    /**
     * Constructs a new UserPreferencePage.
     */
    public UserPreferencePage() {
        super();
    }

    /**
     * @see org.talend.core.prefs.ui.ListPreferencePage#getDataSaved()()
     */
    @Override
    protected String[] getDataSaved() {
        PreferenceManipulator prefManipulator = new PreferenceManipulator(getPreferenceStore());
        return prefManipulator.readUsers();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        PreferenceManipulator prefManipulator = new PreferenceManipulator(getPreferenceStore());
        prefManipulator.saveUsers(getDataToSave());

        return super.performOk();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.prefs.ui.ListPreferencePage#getDeletableIndices(int[])
     */
    @Override
    protected int[] getDeletableIndices(int[] indices) {
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        String connectedUser = repositoryContext.getUser().getLogin();
        int disallowedIndex = -1;
        for (int i = 0; disallowedIndex == -1 && i < indices.length; i++) {
            if (dataList.getItem(indices[i]).equals(connectedUser)) {
                disallowedIndex = i;
            }
        }

        int[] res;
        if (disallowedIndex != -1) {
            res = new int[indices.length - 1];
            if (res.length > 0) {
                if (disallowedIndex > 0) {
                    System.arraycopy(indices, 0, res, 0, disallowedIndex - 1);
                }
                if (disallowedIndex > res.length) {
                    System.arraycopy(indices, disallowedIndex - 1, res, disallowedIndex, res.length - disallowedIndex);
                }
            }
        } else {
            res = indices;
        }
        return res;
    }
}
