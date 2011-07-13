// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Timer to measure elapsed time of any process or between steps.
 * 
 * $Id$
 * 
 */
public class TimeMeasure {

    // PTODO create junit test class
    private static HashMap<String, TimeStack> timers;

    private static int indent = 0;

    /**
     * measureActive is true by default. A true value means that all methods calls are processed else no one.
     */
    public static boolean measureActive = true;

    /**
     * display is true by default. A true value means that all informations are displayed.
     */
    public static boolean display = true;

    public static boolean displaySteps = true;

    public static boolean isLogToFile = true;

    public static boolean printMemoryUsed = true;

    public static String logFilePath;

    public static boolean isFromMainTest = false;

    // key represent the idTimer,value map represent the log rows.
    private static Map<String, List<Map<Integer, Object>>> logValue = new HashMap<String, List<Map<Integer, Object>>>();

    /**
     * 
     * DOC amaumont Comment method "start".
     * 
     * @param idTimer
     */
    public static void begin(String idTimer) {
        if (!measureActive) {
            return;
        }
        init();
        if (timers.containsKey(idTimer)) {
            if (display) {
                System.out.println(indent(indent) + "Warning (start): timer " + idTimer + " already exists"); //$NON-NLS-1$  //$NON-NLS-2$
            }
        } else {
            indent++;
            TimeStack times = new TimeStack();
            timers.put(idTimer, times);
            if (display) {
                System.out.println(indent(indent) + "Start '" + idTimer + "' ..."); //$NON-NLS-1$  //$NON-NLS-2$
            }
        }
    }

    /**
     * 
     * DOC amaumont Comment method "end".
     * 
     * @param idTimer
     * @return total elapsed time since start in ms
     */
    public static long end(String idTimer) {
        if (!measureActive) {
            return 0;
        }
        init();
        if (!timers.containsKey(idTimer)) {
            if (display) {
                System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " doesn't exist"); //$NON-NLS-1$  //$NON-NLS-2$
            }
            return -1;
        } else {
            TimeStack timeStack = timers.get(idTimer);
            timers.remove(idTimer);
            long elapsedTimeSinceLastRequest = timeStack.getLastStepElapsedTime();
            if (display && displaySteps) {
                System.out.println(indent(indent) + "End '" + idTimer + "', elapsed time since last request: " //$NON-NLS-1$  //$NON-NLS-2$
                        + elapsedTimeSinceLastRequest + " ms "); //$NON-NLS-1$
            }
            long totalElapsedTime = timeStack.getTotalElapsedTime();
            if (display) {
                System.out.println(indent(indent) + "End '" + idTimer + "', total elapsed time: " + totalElapsedTime + " ms "); //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$
            }
            indent--;
            if (isLogToFile) {
                logToFile(logValue);
                logValue.clear();
            }
            return totalElapsedTime;
        }
    }

    /**
     * 
     * DOC amaumont Comment method "timeSinceStart".
     * 
     * @param idTimer
     * @return total elapsed time since start in ms
     */
    public static long timeSinceBegin(String idTimer) {
        if (!measureActive) {
            return 0;
        }
        init();
        if (!timers.containsKey(idTimer)) {
            if (display) {
                System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist"); //$NON-NLS-1$  //$NON-NLS-2$
            }
            return -1;
        } else {
            long time = timers.get(idTimer).getTotalElapsedTime();
            if (display) {
                System.out.println(indent(indent) + "-> '" + idTimer + "', elapsed time since start: " + time + " ms "); //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$
            }
            return time;
        }
    }

    /**
     * 
     * DOC amaumont Comment method "timeStep".
     * 
     * @param idTimer
     * @return elapsed time since previous step in ms
     */
    public static long step(String idTimer, String stepName) {
        if (!measureActive) {
            return 0;
        }
        init();
        if (!timers.containsKey(idTimer)) {
            if (display) {
                System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist"); //$NON-NLS-1$  //$NON-NLS-2$
            }
            return -1;
        } else {
            TimeStack timeStack = timers.get(idTimer);
            timeStack.addStep();
            /*
             * trace the timeline of every step,problem is that the code below " Calendar ca = Calendar.getInstance();
             * Date now = ca.getTime();" will cost almost 13ms~15ms
             */
            Calendar ca = Calendar.getInstance();
            Date now = ca.getTime();
            long time = timeStack.getLastStepElapsedTime();
            if (display && displaySteps) {
                String timerStepName = idTimer + "', step name '" + stepName;
                System.out.println(indent(indent)
                        + "-> '" + timerStepName + "', elapsed time since previous step: " + time + " ms "); //$NON-NLS-1$  //$NON-NLS-2$
                if (isLogToFile) {
                    boolean foundTimerKey = false;
                    for (String keyTimer : logValue.keySet()) {
                        if (keyTimer.equals(idTimer)) {
                            /* rows */
                            List<Map<Integer, Object>> values = logValue.get(keyTimer);
                            if (values != null) {
                                Map<Integer, Object> newRowValue = new HashMap<Integer, Object>();
                                // step
                                newRowValue.put(ELogFileColumnConstant.STEP.locationY, timerStepName);
                                // timeused
                                newRowValue.put(ELogFileColumnConstant.TIME_USED.locationY, time);
                                // memory used
                                if (printMemoryUsed) {
                                    long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                                    newRowValue.put(ELogFileColumnConstant.MEMO_USED.locationY, usedMemory);
                                }
                                // current time
                                // newRowValue.put(ELogFileColumnConstant.TIMETRACE.locationY, now);
                                values.add(newRowValue);
                            }
                            foundTimerKey = true;
                            break;
                        }
                    }
                    if (!foundTimerKey) {
                        List<Map<Integer, Object>> newvalues = new ArrayList<Map<Integer, Object>>();
                        Map<Integer, Object> newRowValue = new HashMap<Integer, Object>();
                        // step
                        newRowValue.put(ELogFileColumnConstant.STEP.locationY, timerStepName);
                        // timeused
                        newRowValue.put(ELogFileColumnConstant.TIME_USED.locationY, time);
                        // memory used
                        if (printMemoryUsed) {
                            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            newRowValue.put(ELogFileColumnConstant.MEMO_USED.locationY, usedMemory);
                        }
                        // current time
                        newRowValue.put(ELogFileColumnConstant.TIMETRACE.locationY, now);
                        newvalues.add(newRowValue);
                        logValue.put(idTimer, newvalues);
                    }
                }
            }
            return time;
        }
    }

    public static void pause(String idTimer) {
        if (!measureActive) {
            return;
        }
        init();
        if (!timers.containsKey(idTimer)) {
            if (display) {
                System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist"); //$NON-NLS-1$  //$NON-NLS-2$
            }
            return;
        } else {
            TimeStack time = timers.get(idTimer);
            // long time = times.getElapsedTimeSinceLastRequest();
            time.pause();
            if (display) {
            }
        }
    }

    public static void resume(String idTimer) {
        if (!measureActive) {
            return;
        }
        init();
        if (!timers.containsKey(idTimer)) {
            begin(idTimer);
            // if (display) {
            //                System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist"); //$NON-NLS-1$  //$NON-NLS-2$
            // }
            return;
        } else {
            TimeStack times = timers.get(idTimer);
            long time = times.getLastStepElapsedTime();
            times.resume();
            if (display) {
            }
        }
    }

    /**
     * DOC amaumont Comment method "init".
     */
    private static void init() {
        if (timers == null) {
            timers = new HashMap<String, TimeStack>();
        }
    }

    public static String indent(final int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            stringBuilder.append("  "); //$NON-NLS-1$
        }
        return stringBuilder.toString();
    }

    private static void logToFile(Map<String, List<Map<Integer, Object>>> logValue) {
        WritableWorkbook wirteWorkbook = null;
        WritableSheet sheetToWrite = null;
        Workbook readWorkbook = null;
        try {
            File outputFile = new File(logFilePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                wirteWorkbook = Workbook.createWorkbook(outputFile);
            } else {
                readWorkbook = Workbook.getWorkbook(outputFile);
                wirteWorkbook = Workbook.createWorkbook(outputFile, readWorkbook);
            }
            int sheetsNum = wirteWorkbook.getSheets().length;
            if (sheetsNum > 0) {
                /* need to loop the exsits sheets,select the sheet to write recode */
                for (WritableSheet current : wirteWorkbook.getSheets()) {
                    if (current.getName().equals("Commandlinelog")) {
                        sheetToWrite = current;
                        break;
                    }
                }
                /* if no sheet,need to create sheet */
            } else {
                sheetToWrite = initWritableWorkbook(wirteWorkbook);
            }

            /* loop map to get all recodes */

            for (String timerKey : logValue.keySet()) {
                List<Map<Integer, Object>> values = logValue.get(timerKey);
                for (Map<Integer, Object> row : values) {
                    int rowSize = sheetToWrite.getRows();
                    for (int columnIndex = 0; columnIndex <= 3; columnIndex++) {
                        Object obj = row.get(columnIndex);
                        WritableCell cellToAdd = null;
                        if (obj instanceof String) {
                            cellToAdd = new Label(columnIndex, rowSize, (String) obj);
                        }
                        if (obj instanceof Long) {
                            cellToAdd = new jxl.write.Number(columnIndex, rowSize, (Long) obj);

                        }
                        if (obj instanceof Date) {
                            cellToAdd = new DateTime(columnIndex, rowSize, (Date) obj, DateTime.GMT);
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //$NON-NLS-N$
                            String time = df.format((Date) obj);
                            cellToAdd = new Label(columnIndex, rowSize, time);
                        }

                        if (cellToAdd != null) {
                            sheetToWrite.addCell(cellToAdd);
                        }
                    }
                }
            }

            wirteWorkbook.write();
            wirteWorkbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
        }
    }

    private static WritableSheet initWritableWorkbook(WritableWorkbook wirteWorkbook) throws WriteException,
            RowsExceededException {
        WritableSheet sheetToWrite;
        sheetToWrite = wirteWorkbook.createSheet("Commandlinelog", 0);
        // set column width
        sheetToWrite.setColumnView(0, 40);
        sheetToWrite.setColumnView(1, 25);
        sheetToWrite.setColumnView(2, 25);
        sheetToWrite.setColumnView(3, 25);
        WritableFont wf = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.BLACK); // define format,font,underline and color
        WritableCellFormat wcf = new WritableCellFormat(wf); // define cell
        wcf.setBackground(jxl.format.Colour.BLUE_GREY); // set background of cell
        wcf.setAlignment(jxl.format.Alignment.CENTRE); // set Alignment

        WritableFont wfTitle = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.RED);
        WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
        wcfTitle.setBackground(jxl.format.Colour.LIGHT_BLUE);
        wcfTitle.setAlignment(jxl.format.Alignment.CENTRE);
        initTitleAndColumns(sheetToWrite, wcf, wcfTitle);
        return sheetToWrite;
    }

    private static void initTitleAndColumns(WritableSheet sheetToWrite, WritableCellFormat wcf, WritableCellFormat wcfTitle)
            throws WriteException, RowsExceededException {
        Label title = new Label(ELogFileColumnConstant.TITLE.locationY, ELogFileColumnConstant.TITLE.locationX,
                ELogFileColumnConstant.TITLE.label, wcfTitle);
        Label stepColumn = new Label(ELogFileColumnConstant.STEP.locationY, ELogFileColumnConstant.STEP.locationX,
                ELogFileColumnConstant.STEP.label, wcf);
        Label timeUsed = new Label(ELogFileColumnConstant.TIME_USED.locationY, ELogFileColumnConstant.TIME_USED.locationX,
                ELogFileColumnConstant.TIME_USED.label, wcf);
        Label memoUsed = new Label(ELogFileColumnConstant.MEMO_USED.locationY, ELogFileColumnConstant.MEMO_USED.locationX,
                ELogFileColumnConstant.MEMO_USED.label, wcf);
        Label timeTrace = new Label(ELogFileColumnConstant.TIMETRACE.locationY, ELogFileColumnConstant.TIMETRACE.locationX,
                ELogFileColumnConstant.TIMETRACE.label, wcf);
        sheetToWrite.mergeCells(0, 0, 3, 0);
        sheetToWrite.addCell(title);
        sheetToWrite.addCell(stepColumn);
        sheetToWrite.addCell(timeUsed);
        sheetToWrite.addCell(memoUsed);
        sheetToWrite.addCell(timeTrace);
    }

    /* this enum define the attributes of columns in a log file */
    public enum ELogFileColumnConstant {

        TITLE(0, 0, "Welcome to CommandLine performance test"), //$NON-NLS-N$
        STEP(0, 1, "Step"), //$NON-NLS-N$
        TIME_USED(1, 1, "TimeUsed(ms)"), //$NON-NLS-N$
        MEMO_USED(2, 1, "memoryUsed(bytes)"), //$NON-NLS-N$
        TIMETRACE(3, 1, "timeLine");//$NON-NLS-N$

        public int locationY;

        public int locationX;

        public String label;

        private ELogFileColumnConstant(int locationY, int locationX, String label) {
            this.locationY = locationY;
            this.locationX = locationX;
            this.label = label;
        }
    }

    public static void main(String[] args) {
        try {
            logFilePath = "D:\\test.xls"; //$NON-NLS-N$
            TimeMeasure.begin("a"); //$NON-NLS-1$
            // TimeMeasure.end("b");
            Thread.sleep(500);
            TimeMeasure.step("a", "1"); //$NON-NLS-1$ //$NON-NLS-2$
            Thread.sleep(800);
            TimeMeasure.pause("a"); //$NON-NLS-1$
            Thread.sleep(600);
            TimeMeasure.step("a", "2"); //$NON-NLS-1$ //$NON-NLS-2$
            TimeMeasure.resume("a"); //$NON-NLS-1$
            Thread.sleep(2000);
            TimeMeasure.end("a"); //$NON-NLS-1$
            logFilePath = ""; //$NON-NLS-N$
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
