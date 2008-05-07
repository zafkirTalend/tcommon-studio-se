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
package org.talend.dataprofiler.persistence.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.talend.dataprofiler.persistence.business.SqlConstants;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class GenerateDate {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final DecimalFormat format = new DecimalFormat("00");

    private static void generateDate(Writer output) throws IOException {
        final int startYear = 2008;
        final int finalYear = 2025;

        GregorianCalendar cal = new GregorianCalendar();
        int key = 0;

        // set date to beginning of startYear
        cal.clear();
        cal.set(startYear, 0, 1);
        while (cal.get(GregorianCalendar.YEAR) < finalYear) {
            key++;
            writeDateRow(output, cal, key, "");
            cal.add(GregorianCalendar.DATE, 1);
        }

        // add end date
        Date endDate = SqlConstants.END_DATE;
        cal.clear();
        cal.setTime(endDate);
        writeDateRow(output, cal, key + 10000, "TALEND END DATE");

    }

    /**
     * DOC scorreia Comment method "writeDateRow".
     * 
     * @param output
     * @param cal
     * @param key
     * @throws IOException
     */
    private static void writeDateRow(Writer output, GregorianCalendar cal, int key, String specialDayLabel) throws IOException {
        Date time = cal.getTime();
        String date = dateFormat.format(time);
        int dayOfMonth = cal.get(GregorianCalendar.DAY_OF_MONTH);
        int dayOfYear = cal.get(GregorianCalendar.DAY_OF_YEAR);
        int lastWeekDay = getLastWeekDay(cal);
        int lastMonthDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
        String dayName = getDayName(dayOfWeek);
        int weekNum = cal.get(GregorianCalendar.WEEK_OF_YEAR);
        int m = cal.get(GregorianCalendar.MONTH);
        int monthNumber = m + 1;
        String monthName = getMonthName(m);
        int y = cal.get(GregorianCalendar.YEAR);
        String monthPeriod = String.valueOf(y) + format.format(monthNumber);
        int q = getQuarter(m);
        String quarterPeriod = String.valueOf(y) + q;
        int s = getSemester(q);
        char dayOff = 'N';
        char specialDay = 'N';

        String row = "" + key + "," + date + "," + dayOfMonth + "," + dayOfYear + "," + lastWeekDay + "," + lastMonthDay + ","
                + dayOfWeek + "," + dayName + "," + weekNum + "," + monthNumber + "," + monthName + "," + y + "," + monthPeriod
                + "," + q + "," + quarterPeriod + "," + s + "," + dayOff + "," + specialDay + "," + specialDayLabel + "\n";
        output.write(row);

        System.out.print(row);
    }

    /**
     * DOC scorreia Comment method "main".
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            File file = new File("data/calendar.csv");
            Writer output = new BufferedWriter(new FileWriter(file));
            generateDate(output);
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * DOC scorreia Comment method "getLastWeekDay".
     * 
     * @param cal
     * @return
     */
    private static int getLastWeekDay(GregorianCalendar cal) {
        // scorreia FIXME this is not correct
        return 0; // cal.getActualMaximum(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * DOC scorreia Comment method "getSemester".
     * 
     * @param q quarter
     * @return
     */
    private static int getSemester(int q) {
        switch (q) {
        case 1:
        case 2:
            return 1;
        case 3:
        case 4:
            return 2;
        default:
            return 0;
        }
    }

    /**
     * DOC scorreia Comment method "getQuarter".
     * 
     * @param m
     * @return
     */
    private static int getQuarter(int m) {
        switch (m) {
        case GregorianCalendar.JANUARY:
        case GregorianCalendar.FEBRUARY:
        case GregorianCalendar.MARCH:
            return 1;
        case GregorianCalendar.APRIL:
        case GregorianCalendar.MAY:
        case GregorianCalendar.JUNE:
            return 2;
        case GregorianCalendar.JULY:
        case GregorianCalendar.AUGUST:
        case GregorianCalendar.SEPTEMBER:
            return 3;
        case GregorianCalendar.OCTOBER:
        case GregorianCalendar.NOVEMBER:
        case GregorianCalendar.DECEMBER:
            return 4;
        }
        return 0;
    }

    /**
     * DOC scorreia Comment method "getMonthName".
     * 
     * @param m
     * @return
     */
    private static String getMonthName(int m) {
        switch (m) {
        case GregorianCalendar.JANUARY:
            return "January";
        case GregorianCalendar.FEBRUARY:
            return "February";
        case GregorianCalendar.MARCH:
            return "March";
        case GregorianCalendar.APRIL:
            return "April";
        case GregorianCalendar.MAY:
            return "May";
        case GregorianCalendar.JUNE:
            return "June";
        case GregorianCalendar.JULY:
            return "July";
        case GregorianCalendar.AUGUST:
            return "August";
        case GregorianCalendar.SEPTEMBER:
            return "September";
        case GregorianCalendar.OCTOBER:
            return "October";
        case GregorianCalendar.NOVEMBER:
            return "November";
        case GregorianCalendar.DECEMBER:
            return "December";
        default:
            return "N/A";
        }
    }

    /**
     * DOC scorreia Comment method "getDayName".
     * 
     * @param dayOfWeek
     * @return
     */
    private static String getDayName(int dayOfWeek) {
        switch (dayOfWeek) {
        case GregorianCalendar.MONDAY:
            return "Monday";
        case GregorianCalendar.TUESDAY:
            return "Tuesday";
        case GregorianCalendar.WEDNESDAY:
            return "Wednesday";
        case GregorianCalendar.THURSDAY:
            return "Thursday";
        case GregorianCalendar.FRIDAY:
            return "Friday";
        case GregorianCalendar.SATURDAY:
            return "Saturday";
        case GregorianCalendar.SUNDAY:
            return "Sunday";
        default:
            break;
        }
        return null;
    }

}
