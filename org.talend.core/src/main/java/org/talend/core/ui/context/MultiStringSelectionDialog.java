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
package org.talend.core.ui.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.i18n.Messages;

/**
 * A dialog that config the context value sets.
 * 
 */
public class MultiStringSelectionDialog extends ObjectSelectionDialog<String> {

    private static String defaultMesage = "Configure value of list";

    IInputValidator validator = null;

    @SuppressWarnings("restriction")
    public MultiStringSelectionDialog(Shell parentShell, String[] input) {
        super(parentShell, "Configure Values", defaultMesage, null);
        setLabelProvider(getLabelProvider());
        if (input == null) {
            input = new String[0];
        }
        List<String> list = new ArrayList<String>(Arrays.asList(input));
        setData(list);
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    private IInputValidator getInputValidator() {
        return new IInputValidator() {

            public String isValid(String newText) {
                return validateContextName(newText);
            }
        };
    }

    public List<String> getResultContexts() {
        return getData();
    }

    LabelProvider getLabelProvider() {
        return new LabelProvider();
    }

    public void createElement() {
        InputDialog inputDial = new InputDialog(getShell(), "New Value", //$NON-NLS-1$
                "Give a name for the new value", "", getInputValidator()); //$NON-NLS-1$ //$NON-NLS-2$

        inputDial.open();
        String returnValue = inputDial.getValue();
        if (returnValue == null) {
            return;
        }

        getAllValues().add(returnValue);
        refreshViewer();
    }

    private String validateContextName(String name) {
        if (name.length() == 0) {
            return "Name can not be empty";
        }
        return isContextExisting(name);
    }

    public List<String> getAllValues() {
        return getData();
    }

    public String[] getResultString() {
        return getAllValues().toArray(new String[0]);
    }

    private String isContextExisting(String name) {
        boolean exist = false;
        for (String value : getAllValues()) {
            if (value.equalsIgnoreCase(name)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return Messages.getString("ContextProcessSection.30");
        } else {
            return null;
        }

    }

    protected void editSelectedElement() {
        String selectedValue = (String) (getSelection()).getFirstElement();
        InputDialog inputDial = new InputDialog(getShell(), "Rename", //$NON-NLS-1$
                "Give a new name for the value", "", getInputValidator()); //$NON-NLS-1$ //$NON-NLS-2$
        inputDial.open();
        String returnValue = inputDial.getValue();
        if (returnValue == null || returnValue.equals(selectedValue)) {
            return;
        }

        renameContext(selectedValue, returnValue);
        refreshViewer();
    }

    private void renameContext(String selectedValue, String newName) {
        int index = getAllValues().indexOf(selectedValue);
        if (index >= 0) {
            getAllValues().remove(index);
            getAllValues().add(index, newName);
        }
    }

    /**
     * Updates the modify buttons' enabled state based on the current seleciton.
     */
    protected void updateButtonAvailability() {
        super.updateButtonAvailability();
    }
}
