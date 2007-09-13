package routines;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import routines.system.FastDateParser;

public class TalendDate {

    /**
     * Formats a Date into a date/time string.
     * @param date the time value to be formatted into a time string.
     * @return the formatted time string.
     * 
     * {talendTypes} String
     * 
     * {Category} TalendDate
     * 
     * {param} string("yyyy-MM-dd HH:mm:ss")
     */
    public synchronized static String format(String pattern, java.util.Date date) {
        return FastDateParser.getInstance(pattern).format(date);
    }

    public synchronized static String format(String pattern, java.util.Date date, Locale locale) {
        return FastDateParser.getInstance(pattern, locale).format(date);
    }
    
    /**
     * Parses text from the beginning of the given string to produce a date.
     * The method may not use the entire text of the given string.
     * <p>
     * See the {@link #parse(String, ParsePosition)} method for more information
     * on date parsing.
     *
     * @param source A <code>String</code> whose beginning should be parsed.
     * @return A <code>Date</code> parsed from the string.
     * @throws ParseException 
     * @exception ParseException if the beginning of the specified string
     *            cannot be parsed.
     * 
     * {talendTypes} String
     * 
     * {Category} TalendDate
     * 
     * {param} string("yyyy-MM-dd HH:mm:ss")
     */
    public synchronized static Date parse(String pattern, String stringDate) throws ParseException {
        return FastDateParser.getInstance(pattern).parse(stringDate);
    }
    
    public synchronized static Date parse(String pattern, String stringDate, Locale locale) throws ParseException {
        return FastDateParser.getInstance(pattern, locale).parse(stringDate);
    }
    
    /**
     * getDate : return the current datetime with the given display format format : (optional) string representing the
     * wished format of the date. This string contains fixed strings and variables related to the date. By default, the
     * format string is DD/MM/CCYY. Here is the list of date variables:
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} TalendDate
     * 
     * {param} string("CCYY-MM-DD hh:mm:ss") pattern : date pattern + CC for century + YY for year + MM for month + DD
     * for day + hh for hour + mm for minute + ss for second
     */
    public static String getDate(String pattern) {
        StringBuffer result = new StringBuffer();

        pattern = pattern.replace("CC", "yy");
        pattern = pattern.replace("YY", "yy");
        pattern = pattern.replace("MM", "MM");
        pattern = pattern.replace("DD", "dd");
        pattern = pattern.replace("hh", "HH");

        // not needed
        // pattern.replace("mm", "mm");
        // pattern.replace("ss", "ss");

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.format(Calendar.getInstance().getTime(), result, new FieldPosition(0));
        return result.toString();
    }

    /**
     * getDate : return the current date
     * 
     * 
     * 
     * {talendTypes} Date
     * 
     * {Category} TalendDate
     * 
     * {example} getCurrentDate()
     */
    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * return an ISO formatted random date
     * 
     * 
     * {talendTypes} Date
     * 
     * {Category} TalendDate
     * 
     * {param} string("2007-01-01") min : minimum date
     * {param} string("2008-12-31") max : maximum date (superior to min)
     * 
     * {example} getRandomDate("1981-01-18", "2005-07-24") {example} getRandomDate("1980-12-08", "2007-02-26")
     */
    public static Date getRandomDate(String minDate, String maxDate) {
        int minYear = Integer.parseInt(minDate.substring(0, 4));
        int minMonth = Integer.parseInt(minDate.substring(5, 7));
        int minDay = Integer.parseInt(minDate.substring(8, 10));

        int maxYear = Integer.parseInt(maxDate.substring(0, 4));
        int maxMonth = Integer.parseInt(maxDate.substring(5, 7));
        int maxDay = Integer.parseInt(maxDate.substring(8, 10));

        int yy = minYear + (int) ((maxYear - minYear + 1) * Math.random());
        int mm = minMonth + (int) ((maxMonth - minMonth + 1) * Math.random());
        int dd = minDay + (int) ((maxDay - minDay + 1) * Math.random());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yy);
        cal.set(Calendar.MONTH, mm - 1);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        return cal.getTime();
    }
    
    /**
     * 
     * Method used for tests only.
     * @param args
     */
    public static void main(String[] args) {
        final int LOOPS = 100000;
        final String dateTimeRef_Test1 = "1979-03-23 mars 12:30";
        Thread test1 = new Thread() {
            public void run() {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(1979, 2, 23, 12, 30, 40);
                Date dateCalendar = calendar.getTime();
                for (int i = 0; i < LOOPS; i++) {
                    String date = TalendDate.format("yyyy-MM-dd MMM HH:mm", dateCalendar);
//                    System.out.println("Test1:" + date + " # " + dateTimeRef_Test1);
                    if(!dateTimeRef_Test1.equals(date)) {
                        throw new IllegalStateException("Test1: Date ref : '" + dateTimeRef_Test1 + "' is different of '" + date + "'");
                    }
                }
                System.out.println("test1 ok");
            }
        };
        final String dateTimeRef_Test2 = "1980-03-23 mars 12:30";
        Thread test2 = new Thread() {
            public void run() {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(1980, 2, 23, 12, 30, 40);
                Date dateCalendar = calendar.getTime();
                for (int i = 0; i < LOOPS; i++) {
                    String date = TalendDate.format("yyyy-MM-dd MMM HH:mm", dateCalendar);
//                    System.out.println("Test2:" + date + " # " + dateTimeRef_Test2);
                    if(!dateTimeRef_Test2.equals(date)) {
                        throw new IllegalStateException("Test2: Date ref : '" + dateTimeRef_Test2 + "' is different of '" + date + "'");
                    }
                }
                System.out.println("test2 ok");
            }
        };
        
        final String dateTimeRef_Test3 = "1979-03-23 mars 12:30";
        Thread test3 = new Thread() {
            public void run() {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(1979, 2, 23, 12, 30, 40);
                Date dateCalendar = calendar.getTime();
                for (int i = 0; i < LOOPS; i++) {
                    String date = TalendDate.format("yyyy-MM-dd MMM HH:mm", dateCalendar, Locale.FRANCE);
//                    System.out.println("Test3:" + date + " # " + dateTimeRef_Test3);
                    if(!dateTimeRef_Test3.equals(date)) {
                        throw new IllegalStateException("Test3: Date ref : '" + dateTimeRef_Test3 + "' is different of '" + date + "'");
                    }
                }
                System.out.println("test3 ok");
            }
        };
        final String dateTimeRef_Test4 = "1980-03-23 Mar 12:30";
        Thread test4 = new Thread() {
            public void run() {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(1980, 2, 23, 12, 30, 40);
                Date dateCalendar = calendar.getTime();
                for (int i = 0; i < LOOPS; i++) {
                    String date = TalendDate.format("yyyy-MM-dd MMM HH:mm", dateCalendar, Locale.ENGLISH);
//                    System.out.println("Test4:" + date + " # " + dateTimeRef_Test4);
                    if(!dateTimeRef_Test4.equals(date)) {
                        throw new IllegalStateException("Test4: Date ref : '" + dateTimeRef_Test4 + "' is different of '" + date + "'");
                    }
                }
                System.out.println("test4 ok");
            }
        };
        
        final String dateTimeRef_Test5 = "1979-03-23";
        Thread test5 = new Thread() {
            public void run() {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(1979, 2, 23, 12, 30, 40);
                Date dateCalendar = calendar.getTime();
                for (int i = 0; i < LOOPS; i++) {
                    String date = TalendDate.format("yyyy-MM-dd", dateCalendar);
//                    System.out.println("Test5:" + date + " # " + dateTimeRef_Test5);
                    if(!dateTimeRef_Test5.equals(date)) {
                        throw new IllegalStateException("Test5: Date ref : '" + dateTimeRef_Test5 + "' is different of '" + date + "'");
                    }
                    
                }
                System.out.println("test5 ok");
            }
        };

        
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        test5.start();
    }
}
