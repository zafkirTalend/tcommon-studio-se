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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.vafada.swtcalendar.SWTCalendar;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class SWTCalendarWithTime extends SWTCalendar {

    private Spinner hourChooser;

    private Spinner minuteChooser;

    private Spinner secondsChooser;

    private int hour;

    private int minute;

    private int seconds;

    private int millis = 0;

    public SWTCalendarWithTime(Composite parent, int style) {
        super(parent, style);

        GridLayout mainGridLayout = (GridLayout) getLayout();
        mainGridLayout.numColumns = 1;
        setLayout(mainGridLayout);

        Calendar calendar = Calendar.getInstance();

        final Composite timeSetting = new Composite(this, SWT.BORDER);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 6;
        gridLayout.marginWidth = 5;
        gridLayout.marginHeight = 5;
        timeSetting.setLayout(gridLayout);

        timeSetting.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));

        Label hourLabel = new Label(timeSetting, SWT.NONE);
        hourLabel.setText("Hr: ");
        hourLabel.setBackground(hourLabel.getParent().getBackground());

        hourChooser = new Spinner(timeSetting, SWT.BORDER);
        hourChooser.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL));
        hourChooser.setMinimum(0);
        hourChooser.setMaximum(23);
        hourChooser.setIncrement(1);
        hourChooser.setPageIncrement(1);
        hourChooser.setSelection(calendar.get(Calendar.HOUR_OF_DAY));
        hourChooser.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                hour = hourChooser.getSelection();
            }
        });

        Label minuteLabel = new Label(timeSetting, SWT.NONE);
        minuteLabel.setText("Min: ");
        minuteLabel.setBackground(hourLabel.getParent().getBackground());

        minuteChooser = new Spinner(timeSetting, SWT.BORDER);
        minuteChooser.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL));
        minuteChooser.setMinimum(0);
        minuteChooser.setMaximum(59);
        minuteChooser.setIncrement(1);
        minuteChooser.setPageIncrement(10);
        minuteChooser.setSelection(calendar.get(Calendar.MINUTE));
        minuteChooser.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                minute = minuteChooser.getSelection();
            }
        });

        Label secondsLabel = new Label(timeSetting, SWT.NONE);
        secondsLabel.setText("Sec: ");
        secondsLabel.setBackground(hourLabel.getParent().getBackground());

        secondsChooser = new Spinner(timeSetting, SWT.BORDER);
        secondsChooser.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL));
        secondsChooser.setMinimum(0);
        secondsChooser.setMaximum(59);
        secondsChooser.setIncrement(1);
        secondsChooser.setPageIncrement(10);
        secondsChooser.setSelection(calendar.get(Calendar.SECOND));
        secondsChooser.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                seconds = secondsChooser.getSelection();
            }
        });

        Composite buttonPalette = new Composite(timeSetting, SWT.NONE);
        GridData gridData = new GridData();
        gridData.horizontalSpan = 6;
        buttonPalette.setLayoutData(gridData);
        buttonPalette.setLayout(new FillLayout());

        Button morningButton = new Button(buttonPalette, SWT.PUSH);
        morningButton.setText("Morning");
        morningButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                setTime(0, 0, 0);
            }

            public void widgetSelected(SelectionEvent e) {
                setTime(0, 0, 0);
            }
        });

        Button noonButton = new Button(buttonPalette, SWT.PUSH);
        noonButton.setText("Noon");
        noonButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                setTime(12, 0, 0);
            }

            public void widgetSelected(SelectionEvent e) {
                setTime(12, 0, 0);
            }
        });

        Button eveningButton = new Button(buttonPalette, SWT.PUSH);
        eveningButton.setText("Evening");
        eveningButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                setTime(23, 59, 59);
            }

            public void widgetSelected(SelectionEvent e) {
                setTime(23, 59, 59);
            }
        });

        Button nowButton = new Button(buttonPalette, SWT.PUSH);
        nowButton.setText("Now");
        nowButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                setCalendar(Calendar.getInstance());
            }

            public void widgetSelected(SelectionEvent e) {
                setCalendar(Calendar.getInstance());
            }
        });
    }

    public SWTCalendarWithTime(Composite parent) {
        this(parent, SWT.FLAT);
    }

    private void setTime(int hour, int minute, int second) {
        Calendar cal = getCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        setCalendar(cal);
    }

    @Override
    public void setCalendar(Calendar cal) {
        super.setCalendar(cal);
        hourChooser.setSelection(cal.get(Calendar.HOUR_OF_DAY));
        hour = hourChooser.getSelection();

        minuteChooser.setSelection(cal.get(Calendar.MINUTE));
        minute = minuteChooser.getSelection();

        secondsChooser.setSelection(cal.get(Calendar.SECOND));
        seconds = secondsChooser.getSelection();
    }

    @Override
    public Calendar getCalendar() {
        Calendar toReturn = super.getCalendar();
        toReturn.set(Calendar.HOUR_OF_DAY, hour);
        toReturn.set(Calendar.MINUTE, minute);
        toReturn.set(Calendar.SECOND, seconds);
        toReturn.set(Calendar.MILLISECOND, millis);
        return toReturn;
    }

}
