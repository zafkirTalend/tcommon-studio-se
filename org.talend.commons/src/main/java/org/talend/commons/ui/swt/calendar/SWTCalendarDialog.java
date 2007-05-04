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
package org.talend.commons.ui.swt.calendar;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.vafada.swtcalendar.SWTCalendar;
import org.vafada.swtcalendar.SWTCalendarListener;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class SWTCalendarDialog extends Dialog {

    public static final int RESET_ID = 666;

    private SWTCalendar swtcal;

    private Date date;

    private String title;

    public SWTCalendarDialog(Shell shell, Date date, String title) {
        super(shell);
        this.date = date;
        this.title = title;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(title);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout());
        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.CENTER;
        container.setLayoutData(gridData);

        swtcal = new SWTCalendarWithTime(container);
        setDate(date);
        return container;
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == RESET_ID) {
            resetPressed();
        } else {
            super.buttonPressed(buttonId);
        }
    }

    protected void resetPressed() {
        setReturnCode(RESET_ID);
        close();
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        this.createButton(parent, RESET_ID, "Reset", false);
    }

    public Calendar getCalendar() {
        Calendar calendar = swtcal.getCalendar();
        return calendar;
    }

    private void setDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        swtcal.setCalendar(calendar);
    }

    public void addDateChangedListener(SWTCalendarListener listener) {
        swtcal.addSWTCalendarListener(listener);
    }

}
