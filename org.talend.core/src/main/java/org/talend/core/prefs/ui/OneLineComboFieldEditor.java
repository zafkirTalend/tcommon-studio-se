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

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.talend.commons.utils.workbench.preferences.ComboFieldEditor;

/**
 *  
 * $Id: OneLineComboFieldEditor.java 2007-2-8,下午01:35:31 ftang $
 * 
 */
public class OneLineComboFieldEditor extends ComboFieldEditor {

    public OneLineComboFieldEditor(String name, String labelText, String[][] entryNamesAndValues, Composite parent) {
        super(name, labelText, entryNamesAndValues, parent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.workbench.preferences.ComboFieldEditor#doFillIntoGrid(org.eclipse.swt.widgets.Composite,
     * int)
     */
    protected void doFillIntoGrid(Composite parent, int numColumns) {
        GridLayout layout = new GridLayout();
        layout.numColumns = 4;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = HORIZONTAL_GAP;
        parent.setLayout(layout);
        
        Control control = getLabelControl(parent);
        GridData gd = new GridData();
        gd.horizontalSpan = numColumns;
        control.setLayoutData(gd);
        control = getComboBoxControl(parent);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = numColumns;
      
        
        control.setLayoutData(gd);
    }

}
