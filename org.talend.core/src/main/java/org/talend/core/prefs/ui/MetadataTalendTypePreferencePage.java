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
package org.talend.core.prefs.ui;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;

/**
 * Preference for the Metadata Talend type files.
 * 
 * $Id: MetadataTalendTypePreferencePage.java 2738 2007-04-26 13:12:27Z cantoine $
 * 
 */
public class MetadataTalendTypePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * MetadataTalendTypePreferencePage.
     * 
     * $Id: SpagoBiPreferencePage.java 2738 2007-04-26 13:12:27Z cantoine $
     * 
     */

    public MetadataTalendTypePreferencePage() {
        super(FLAT);
    }

    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        // return preferenceStore;
        return CorePlugin.getDefault().getPreferenceStore();
    }

    @Override
    protected void createFieldEditors() {
        addField(new MetadataTalendTypeEditor(MetadataTalendTypeEditor.ID, "Talend Type Mapping Files", getFieldEditorParent())); //$NON-NLS-1$
    }

    @Override
    protected void initialize() {
        try {
            super.initialize();
        } catch (RuntimeException e) {
            e.printStackTrace();
            setErrorMessage(e.getMessage());
        }
    }

    @Override
    protected void checkState() {
        if (getErrorMessage() == null) {
            super.checkState();
        } else {
            setValid(false);
        }
    }

    public void init(IWorkbench workbench) {
    }

}
