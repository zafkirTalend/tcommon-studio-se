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
package org.talend.commons.ui.swt.tableviewer.celleditor;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.ui.swt.calendar.SWTCalendarWithTime;

/**
 *  qiang.zhang class global comment. Detailled comment <br/>
 * 
 */
public class DateDialog extends Dialog {

    /**
     * qiang.zhang DateDialog constructor comment.
     * 
     * @param parentShell
     */
    public DateDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
    }

    /*
     * (non-Java)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Select Date & Time");
    }

    private SWTCalendarWithTime time;

    /*
     * (non-Java)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite control2 = (Composite) super.createDialogArea(parent);
        final GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginLeft = 0;
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        control2.setLayout(layout);
        control2.setLayoutData(new GridData(GridData.FILL_BOTH));
        time = new SWTCalendarWithTime(control2, SWT.NONE);
        return control2;
    }

    /**
     * qiang.zhang Comment method "getDate".
     * 
     * @return
     */
    public Date getDate() {
        return time.getCalendar().getTime();
    }

    public String getTalendDateString() {

        StringBuffer result = new StringBuffer();

        String pattern = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.format(getDate(), result, new FieldPosition(0));
        return result.toString();
    }
}
