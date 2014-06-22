package org.talend.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

/**
 * Class that gathers all classes for test using the following java System properties to find the desired test classes. <br/>
 * see field description in javadoc
 * 
 * 
 */
@RunWith(GenericTestsJUnit4Suite.class)
@SuppressWarnings("nls")
public class GenericTestsJUnit4Suite extends Suite {

    private static String FULL_LOG_FILE = "all_junits.log";

    private static String SUM_UP_FILE = "junits_sum_up.txt";

    private static String CR = "\n";

    private StringBuffer errorBuffer = new StringBuffer();

    private int nbTestFailed = 0;

    private int nbTestExecuted = 0;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");

    private Set<String> errors = new HashSet<String>();

    private LinkedList<String> listOfClassesToExecute = new LinkedList<String>();

    private List<Runner> finalRunnersList = new ArrayList<Runner>();

    private RunListener runListener = new RunListener() {

        /*
         * (non-Javadoc)
         * 
         * @see org.junit.runner.notification.RunListener#testStarted(org.junit.runner.Description)
         */
        @Override
        public void testStarted(Description description) throws Exception {
            printBeforeTest(description);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.junit.runner.notification.RunListener#testFinished(org.junit.runner.Description)
         */
        @Override
        public void testFinished(Description description) throws Exception {
            printAfterTest(description);
            nbTestExecuted++;
        }

        @Override
        public void testFailure(Failure failure) throws Exception {
            String classMethodName = failure.getDescription().getClassName() + "." + failure.getDescription().getMethodName();
            if (!errors.contains(classMethodName)) {
                nbTestFailed++;
                errorBuffer.append(classMethodName + "\n");
                appendToLogFile("   **** Test Failed ****\n");
                errors.add(classMethodName);
            }
        }
    };

    static final TalendJunitsTestCollector ttc = new TalendJunitsTestCollector();

    public GenericTestsJUnit4Suite(Class<?> klass) throws InitializationError {
        super(klass, ttc.getTests());

        for (Class<?> classToTest : ttc.getTests()) {
            listOfClassesToExecute.add(classToTest.getName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.junit.runners.Suite#runChild(org.junit.runner.Runner, org.junit.runner.notification.RunNotifier)
     */
    @Override
    protected void runChild(Runner runner, RunNotifier notifier) {
        printBeforeTestClass(runner);
        super.runChild(runner, notifier);
        printAfterTestClass(runner);
    }

    private void appendToLogFile(String log) {
        File fullLogFile = new File(Platform.getLocation().removeLastSegments(2).append(FULL_LOG_FILE).toPortableString());

        FileWriter writer = null;
        try {
            writer = new FileWriter(fullLogFile, true);
            writer.append(log);
        } catch (IOException e) {
            // nothing
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.junit.runners.ParentRunner#run(org.junit.runner.notification.RunNotifier)
     */
    @Override
    public void run(RunNotifier arg0) {
        arg0.addListener(runListener);
        super.run(arg0);
        File sumUpFile = new File(Platform.getLocation().removeLastSegments(2).append(SUM_UP_FILE).toPortableString());

        FileWriter writer = null;
        try {
            writer = new FileWriter(sumUpFile);
            writer.append(nbTestExecuted + " test executed.\n");
            if (nbTestFailed == 0) {
                writer.append("no test failed.\n");
            } else {
                writer.append(nbTestFailed + " test failed.");
                writer.append(CR);
                writer.append("List of classes/methods in error bellow. For more details please check on hudson directly\n");
                writer.append(CR);
                writer.append("------------------------------------ Error details ------------------------------------");
                writer.append(CR);
                writer.append(errorBuffer.toString());
            }
        } catch (IOException e) {
            StringBuffer buff = new StringBuffer();
            buff.append("********************************************************\n");
            buff.append("Error when write sum up file for junits");
            if (e.getMessage() != null) {
                buff.append("--------------------------------------------------------\n");
                buff.append("Exception:" + e.getMessage());
                buff.append("********************************************************\n");
            }
            appendToLogFile(buff.toString());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * @param runner
     */
    private void printAfterTestClass(Runner runner) {
        StringBuffer buff = new StringBuffer();
        buff.append("|====================================================\n");
        appendToLogFile(buff.toString());
    }

    /**
     * @param runner
     */
    private void printBeforeTestClass(Runner runner) {
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        StringBuffer buff = new StringBuffer();
        buff.append("|====================================================\n");
        buff.append("| [memory :" + usedMemory + "b]  - Start test class: " + runner.getDescription() + "\n");
        listOfClassesToExecute.remove(runner.getDescription().getClassName());
        appendToLogFile(buff.toString());
    }

    /**
     * @param description
     */
    private void printAfterTest(Description description) {
        appendToLogFile("|" + format.format(new Date()) + "=> Finish: " + description.getMethodName() + "\n");
    }

    /**
     * @param description
     */
    private void printBeforeTest(Description description) {
        appendToLogFile("|" + format.format(new Date()) + "=> Start: " + description.getMethodName() + "\n");
    }

    @Override
    protected List<Runner> getChildren() {
        if (finalRunnersList.isEmpty()) {
            List<Runner> runners = super.getChildren();
            for (Runner runner : runners) {
                try {
                    listOfClassesToExecute.remove(runner.getDescription().getClassName());
                    // if no exception here, add test to final list
                    finalRunnersList.add(runner);
                } catch (Exception e) {
                    errorBuffer.append("* One test failed certainly because of PowerMock (check full log for detail)\n");
                }
            }

            StringBuffer buff = new StringBuffer();
            if (!listOfClassesToExecute.isEmpty()) {
                buff.append("-------- Classes not  executed (certainly problem with powermock) ---------\n");
                for (String className : listOfClassesToExecute) {
                    buff.append(className);
                    buff.append(CR);
                }
                buff.append("---------------------------------------------------------------------------\n");
                appendToLogFile(buff.toString());
            }
        }

        return finalRunnersList;
    }
}
