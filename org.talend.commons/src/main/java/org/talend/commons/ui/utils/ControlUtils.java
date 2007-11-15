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
package org.talend.commons.ui.utils;

import java.util.Arrays;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.i18n.internal.Messages;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ControlUtils {

    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static String getText(Control control) {
        String value = null;
        if (control instanceof Text) {
            value = ((Text) control).getText();
        } else if (control instanceof StyledText) {
            value = ((StyledText) control).getText();
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
        return value;

    }

    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static void setText(Control control, String text) {
        if (control instanceof Text) {
            ((Text) control).setText(text);
        } else if (control instanceof StyledText) {
            ((StyledText) control).setText(text);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }

    }

    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static Point getSelection(Control control) {
        if (control instanceof Text) {
            return ((Text) control).getSelection();
        } else if (control instanceof StyledText) {
            return ((StyledText) control).getSelection();
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
        
    }
    
    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static String getLineDelimiter(Control control) {
        if (control instanceof Text) {
            return ((Text) control).getLineDelimiter();
        } else if (control instanceof StyledText) {
            return ((StyledText) control).getLineDelimiter();
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
        
    }
    
    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static void addModifyListener(Control control, ModifyListener modifyListener) {
        if (control instanceof Text) {
            ((Text) control).addModifyListener(modifyListener);
        } else if (control instanceof StyledText) {
            ((StyledText) control).addModifyListener(modifyListener);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
    }

    /**
     * DOC amaumont Comment method "removeModifyListener".
     * 
     * @param control
     * @param modifyListener
     */
    public static void removeModifyListener(Control control, ModifyListener modifyListener) {
        if (control instanceof Text) {
            ((Text) control).removeModifyListener(modifyListener);
        } else if (control instanceof StyledText) {
            ((StyledText) control).removeModifyListener(modifyListener);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
    }

    public static int getCursorPosition(Control control) {
        int position = -1;
        if (control instanceof Text) {
            position = ((Text) control).getCaretPosition();
        } else if (control instanceof StyledText) {
            position = ((StyledText) control).getCaretOffset();
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
        return position;
    }
    
    public static void setCursorPosition(Control control, int position) {
        if (control instanceof Text) {
            ((Text) control).setSelection(position);
        } else if (control instanceof StyledText) {
            ((StyledText) control).setCaretOffset(position);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
    }
    
    
    public static void setSortedValuesForCombo(CCombo combo, String[] values){
		Arrays.sort(values);
		combo.setItems(values);
    }
    
}
