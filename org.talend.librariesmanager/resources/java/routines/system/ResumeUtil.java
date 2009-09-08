package routines.system;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class ResumeUtil {

    final private static String fieldSeparator = ",";

    String logFileName = null;

    Writer writer = null;

    ResumeCommonInfo commonInfo = null;

    // step1: init the log file name
    public ResumeUtil(String logFileName) {
        if (logFileName == null || logFileName.equals("null")) {
            return;
        }

        this.logFileName = logFileName;
        File file = new File(logFileName);
        try {
            this.writer = new FileWriter(logFileName, true);

            // output the header part
            if (file.length() == 0) {
                writer.write("eventDate" + fieldSeparator);// eventDate--------------->???
                writer.write("pid" + fieldSeparator);// pid
                writer.write("root_pid" + fieldSeparator);// root_pid
                writer.write("father_pid" + fieldSeparator);// father_pid
                writer.write("type" + fieldSeparator);// type---------------->???
                writer.write("partName" + fieldSeparator);// partName
                writer.write("parentPart" + fieldSeparator);// parentPart
                writer.write("project" + fieldSeparator);// project
                writer.write("jobName" + fieldSeparator);// jobName
                writer.write("jobContext" + fieldSeparator);// jobContext
                writer.write("jobVersion" + fieldSeparator);// jobVersion
                writer.write("threadId" + fieldSeparator);// threadId
                writer.write("logPriority" + fieldSeparator);// logPriority
                writer.write("errorCode" + fieldSeparator);// errorCode
                writer.write("message" + fieldSeparator);// message
                writer.write("stackTrace");// stackTrace
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // step2: init the common log message info
    public void initCommonInfo(String pid, String root_pid, String father_pid, String project, String jobName, String jobContext,
            String jobVersion) {
        this.commonInfo = new ResumeCommonInfo();
        this.commonInfo.pid = pid;// 2
        this.commonInfo.root_pid = root_pid;// 3
        this.commonInfo.father_pid = father_pid;// 4
        // public String parentPart = null;// 7
        this.commonInfo.project = project;// 8
        this.commonInfo.jobName = jobName;// 9
        this.commonInfo.jobContext = jobContext;// 10
        this.commonInfo.jobVersion = jobVersion;// 11
    }

    // step3: add log item one by one
    public void addLog(String eventDate, String type, String partName, String parentPart, String threadId, String logPriority,
            String errorCode, String message, String stackTrace) {

        if (writer == null) {
            return;
        }

        JobLogItem item = new JobLogItem(eventDate, type, partName, parentPart, threadId, logPriority, errorCode, message,
                stackTrace);
        try {
            writer.write(item.eventDate + fieldSeparator);// eventDate--------------->???
            writer.write(commonInfo.pid + fieldSeparator);// pid
            writer.write(commonInfo.root_pid + fieldSeparator);// root_pid
            writer.write(commonInfo.father_pid + fieldSeparator);// father_pid
            writer.write(item.type + fieldSeparator);// type---------------->???
            writer.write(item.partName + fieldSeparator);// partName
            if (item.parentPart != null) {
                writer.write(item.parentPart + fieldSeparator);// parentPart
            } else {
                writer.write(fieldSeparator);// parentPart
            }
            writer.write(commonInfo.project + fieldSeparator);// project
            writer.write(commonInfo.jobName + fieldSeparator);// jobName
            writer.write(commonInfo.jobContext + fieldSeparator);// jobContext
            writer.write(commonInfo.jobVersion + fieldSeparator);// jobVersion
            writer.write(item.threadId + fieldSeparator);// threadId
            writer.write(item.logPriority + fieldSeparator);// logPriority
            writer.write(item.errorCode + fieldSeparator);// errorCode
            writer.write(item.message + fieldSeparator);// message
            writer.write(item.stackTrace);// stackTrace
            writer.write("\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Util: invoke target check point
    public static void invokeTargetCheckPoint(String resuming_checkpoint_path, Object jobObject,
            final java.util.Map<String, Object> globalMap) throws Exception {
        /*
         * String resuming_checkpoint_path =
         * "/JOB:parentJob/SUBJOB:tRunJob_1/NODE:tRunJob_1/JOB:ChildJob/SUBJOB:tSystem_2";
         */
        String currentJob_checkpoint_path = null;

        // 1. get currentJob_checkpoint_path
        if (resuming_checkpoint_path != null) {
            int indexOf = resuming_checkpoint_path.indexOf("/NODE:");

            if (indexOf != -1) {
                // currentJob_checkpoint_path: /JOB:parentJob/SUBJOB:tRunJob_1
                currentJob_checkpoint_path = resuming_checkpoint_path.substring(0, indexOf);
            } else {
                // currentJob_checkpoint_path: /JOB:ChildJob/SUBJOB:tSystem_2
                currentJob_checkpoint_path = resuming_checkpoint_path;
            }
        }

        String currentJob_subJob_resuming = null;
        // 2. get the target SUBJOB
        if (currentJob_checkpoint_path != null) {
            int indexOf = currentJob_checkpoint_path.indexOf("/SUBJOB:");
            if (indexOf != -1) {
                currentJob_subJob_resuming = currentJob_checkpoint_path.substring(indexOf + 8);
            }
        }

        String subjobMethodName = currentJob_subJob_resuming + "Process";
        // System.out.println(subjobMethodName);

        // 3. invoke the target method
        if (currentJob_subJob_resuming != null) {
            for (java.lang.reflect.Method m : jobObject.getClass().getMethods()) {
                if (m.getName().compareTo(subjobMethodName) == 0) {
                    m.invoke(jobObject, new Object[] { globalMap });
                    break;
                }
            }
        }
    }

    // Util: get check poit path for child job-->used by tRunJob
    public static String getChildJobCheckPointPath(String resuming_checkpoint_path) {
        /*
         * String resuming_checkpoint_path =
         * "/JOB:parentJob/SUBJOB:tRunJob_1/NODE:tRunJob_1/JOB:ChildJob/SUBJOB:tSystem_2";
         */
        String childJob_checkpoint_path = null;

        // get currentJob_checkpoint_path
        if (resuming_checkpoint_path != null) {
            int indexOf = resuming_checkpoint_path.indexOf("/NODE:");

            if (indexOf != -1) {
                String temp = resuming_checkpoint_path.substring(indexOf);

                int index = temp.indexOf("/JOB:");

                childJob_checkpoint_path = temp.substring(index);
            }
        }

        // System.out.println(childJob_checkpoint_path);

        return childJob_checkpoint_path;
    }

    // Util: get String type of ExceptionStackTrace
    public static String getExceptionStackTrace(Exception exception) {
        java.io.OutputStream out = new java.io.ByteArrayOutputStream();

        java.io.PrintStream ps = new java.io.PrintStream(out, true);
        exception.printStackTrace(ps);
        String str = out.toString();
        if (str.length() > 0) {
            str = "\"" + str + "\"";
        }
        return str;
    }

    // 7 fields
    public class ResumeCommonInfo {

        // there are 7 fields as common info in resume log message
        public String pid = null;// 2

        public String root_pid = null;// 3

        public String father_pid = null;// 4

        // public String parentPart = null;// 7
        public String project = null;// 8

        public String jobName = null;// 9

        public String jobContext = null;// 10

        public String jobVersion = null;// 11
    }

    // 10 fields
    public class JobLogItem {

        public JobLogItem(String eventDate, String type, String partName, String parentPart, String threadId, String logPriority,
                String errorCode, String message, String stackTrace) {
            this.eventDate = eventDate;
            this.type = type;
            this.partName = partName;
            this.parentPart = parentPart;
            this.threadId = threadId;
            this.logPriority = logPriority;
            this.errorCode = errorCode;
            this.message = message;
            this.stackTrace = stackTrace;
        }

        // there are 10 fields for every different message
        public String eventDate = null;// 1

        public String type = null;// 5

        public String partName = null;// 6

        public String parentPart = null;// 7

        public String threadId = null;// 12

        public String logPriority = null;// 13

        public String errorCode = null;// 14

        public String message = null;// 15

        public String stackTrace = null;// 16
    }

    public enum LogPriority {
        NONE,
        WARN,
        ERROR,
        FATAL;
    }

    public enum ResumeEventType {
        JOB_STARTED,
        CHECKPOINT,
        SYSTEM_LOG,
        USER_DEF_LOG,
        JOB_ENDED;
    }
}
