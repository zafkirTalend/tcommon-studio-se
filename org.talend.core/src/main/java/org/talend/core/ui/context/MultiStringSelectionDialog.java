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

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.i18n.Messages;
import org.talend.repository.model.RepositoryConstants;

/**
 * A dialog that config the context value sets.
 * 
 */
public class MultiStringSelectionDialog extends ObjectSelectionDialog<String> {

    private static String defaultMesage = "Configure value of list";

    @SuppressWarnings("restriction")
    public MultiStringSelectionDialog(Shell parentShell, String input) {
        super(parentShell, "Configure Values", defaultMesage, null);
        setLabelProvider(getLabelProvider());

        String[] strings = input.split(",");
        List<String> list = new ArrayList<String>(Arrays.asList(strings));
        setData(list);
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    public List<String> getResultContexts() {
        return getData();
    }

    LabelProvider getLabelProvider() {
        return new LabelProvider();
    }

    public void createElement() {
        InputDialog inputDial = new InputDialog(getShell(), "New Value", //$NON-NLS-1$
                "Give a name for the new value", "", null); //$NON-NLS-1$ //$NON-NLS-2$

        inputDial.open();
        String returnValue = inputDial.getValue();
        if (returnValue == null) {
            return;
        }
        if (!validateContextName(returnValue)) {
            return;
        }
        getAllValues().add(returnValue);
        refreshViewer();
    }

    private boolean validateContextName(String name) {
        if (name.equals("") || !name.matches(RepositoryConstants.CODE_ITEM_PATTERN)) {
            MessageDialog.openWarning(new Shell(), Messages.getString(Messages.getString("ContextProcessSection.50")), Messages //$NON-NLS-1$
                    .getString(Messages.getString("ContextProcessSection.51"))); //$NON-NLS-1$
            return false;
        }

        return !isContextExisting(name);
    }

    public List<String> getAllValues() {
        if (getData().isEmpty()) {
            getData().add("null");
        }
        return getData();
    }

    public String getResultString() {
        StringBuilder sb = new StringBuilder();
        for (String value : getAllValues()) {
            sb.append(value).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private boolean isContextExisting(String name) {
        for (String value : getAllValues()) {
            if (value.equalsIgnoreCase(name)) {
                MessageBox mBox = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                mBox.setText(Messages.getString("ContextProcessSection.29")); //$NON-NLS-1$
                mBox.setMessage(Messages.getString("ContextProcessSection.30")); //$NON-NLS-1$
                mBox.open();
                return true;
            }
        }
        return false;
    }

    protected void editSelectedElement() {
        String selectedValue = (String) (getSelection()).getFirstElement();
        InputDialog inputDial = new InputDialog(getShell(), "Rename", //$NON-NLS-1$
                "Give a new name for the value", "", null); //$NON-NLS-1$ //$NON-NLS-2$
        inputDial.open();
        String returnValue = inputDial.getValue();
        if (returnValue == null || returnValue.equals(selectedValue)) {
            return;
        }
        if (!validateContextName(returnValue)) {
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
