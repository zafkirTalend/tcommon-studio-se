// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package routines.system;

import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunStat implements Runnable {

    private boolean openSocket = true;

    private static boolean debug = false;

    public void openSocket(boolean openSocket) {
        this.openSocket = openSocket;
    }

    public static int BEGIN = 0;

    public static int RUNNING = 1;

    public static int END = 2;

    public static int CLEAR = 3;

    // it is a dummy default value for jobStat field
    public static int JOBDEFAULT = -1;

    public static int JOBSTART = 0;

    public static int JOBEND = 1;

    // this is as an additinal info to test the command type
    public static String TYPE0_JOB = "0";

    public static String TYPE1_CONNECTION = "1";

    private class StatBean {

        private String itemId;

        private String connectionId;

        private int nbLine;

        private int state;

        private long startTime = 0;

        private long endTime = 0;

        private String exec = null;

        // feature:11356---1="Start Job" and 2="End job", default is -1
        private int jobStat = JOBDEFAULT;

        public StatBean(int jobStat, String itemId) {
            this.jobStat = jobStat;
            this.itemId = itemId;
            if (jobStat == JOBSTART) {
                this.startTime = System.currentTimeMillis();
            } else if (jobStat == JOBEND) {
                this.endTime = System.currentTimeMillis();
            }
        }

        public StatBean(String connectionId) {
            this.connectionId = connectionId;
            this.startTime = System.currentTimeMillis();
        }

        public String getConnectionId() {
            return this.connectionId;
        }

        public void setConnectionId(String connectionId) {
            this.connectionId = connectionId;
        }

        public int getNbLine() {
            return this.nbLine;
        }

        public void setNbLine(int nbLine) {
            this.nbLine = nbLine;
        }

        public int getState() {
            return this.state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getExec() {
            return this.exec;
        }

        public void setExec(String exec) {
            this.exec = exec;
        }

        public int getJobStat() {
            return jobStat;
        }

        public void setJobStat(int jobStat) {
            this.jobStat = jobStat;
        }

        public String getItemId() {
            return itemId;
        }

    }

    private java.util.concurrent.ConcurrentHashMap<String, StatBean> processStats = new java.util.concurrent.ConcurrentHashMap<String, StatBean>();

    // private java.util.ArrayList<StatBean> processStats = new java.util.ArrayList<StatBean>();

    private java.net.Socket s;

    private java.io.PrintWriter pred;

    private boolean jobIsFinished = false;

    private String str = ""; //$NON-NLS-1$

    public void startThreadStat(String clientHost, int portStats) throws java.io.IOException, java.net.UnknownHostException {
        if (!openSocket) {
            // if go here, it means it is a childJob, it should share the socket opened in parentJob.
            Socket s = null;
            Object object = GlobalResource.resourceMap.get(portStats);
            if (object == null || !(object instanceof Socket)) {
                // Here throw an Exception directly, because the ServerSocket only support one client to connect it.
                String lastCallerJobName = new Exception().getStackTrace()[1].getClassName();
                throw new RuntimeException(
                        "The socket for statistics function is unavailable in job "
                                + lastCallerJobName
                                + "."
                                + "\nUsually, please check the tRunJob, it should uncheck the option \"Use an independent process to run child job\".");
                // todo: if here open another new Socket in childJob, need to close it in the API: stopThreadStat()
                // s = new Socket(clientHost, portStats);
            } else {
                s = (Socket) object;
            }

            OutputStream output = s.getOutputStream();
            if (debug) {
                output = System.out;
            }
            pred = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.OutputStreamWriter(output)), true);
            Thread t = new Thread(this);
            t.start();

            return;
        }

        System.out.println("[statistics] connecting to socket on port " + portStats); //$NON-NLS-1$
        s = new Socket(clientHost, portStats);
        GlobalResource.resourceMap.put(portStats, s);
        OutputStream output = s.getOutputStream();
        if (debug) {
            output = System.out;
        }
        pred = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.OutputStreamWriter(output)), true);
        System.out.println("[statistics] connected"); //$NON-NLS-1$
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        if (!debug) {
            synchronized (this) {
                try {
                    while (!jobIsFinished) {
                        sendMessages();
                        wait(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("[statistics] interrupted"); //$NON-NLS-1$
                }
            }
        }
    }

    public void stopThreadStat() {
        if (!openSocket) {
            return;
        }
        jobIsFinished = true;
        try {
            sendMessages();
            pred.close();
            s.close();
            System.out.println("[statistics] disconnected"); //$NON-NLS-1$
        } catch (java.io.IOException ie) {
        }
    }

    public void sendMessages() {
        // if (!openSocket) {
        // return;
        // }

        // SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SZ");
        // System.out.println("############ Sending packets " + sdf.format(new Date()) + " ... #################");

        for (StatBean sb : processStats.values()) {
            // it is connection
            int jobStat = sb.getJobStat();
            if (jobStat == JOBDEFAULT) {
                str = TYPE1_CONNECTION + "|" + rootPid + "|" + fatherPid + "|" + pid + "|" + sb.getConnectionId();
                // str = sb.getConnectionId();
                if (sb.getState() == RunStat.CLEAR) {
                    str += "|" + "clear"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {

                    if (sb.getExec() == null) {
                        str += "|" + sb.getNbLine() + "|" + (sb.getEndTime() - sb.getStartTime()); //$NON-NLS-1$ //$NON-NLS-2$
                    } else {
                        str += "|" + sb.getExec(); //$NON-NLS-1$
                    }
                    if (sb.getState() != RunStat.RUNNING) {
                        str += "|" + ((sb.getState() == RunStat.BEGIN) ? "start" : "stop"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        processStats.remove(sb.getConnectionId());
                    }
                }

            } else {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSZ");

                // it is job, for feature:11356
                String jobStatStr = "";
                long time = 0;
                String itemId = sb.getItemId();
                itemId = itemId == null ? "" : itemId;
                if (jobStat == JOBSTART) {
                    jobStatStr = jobName + "|" + "start job" + "|" + itemId + "|"
                            + simpleDateFormat.format(new Date(sb.getStartTime()));
                } else if (jobStat == JOBEND) {
                    jobStatStr = jobName + "|" + "end job" + "|" + itemId + "|"
                            + simpleDateFormat.format(new Date(sb.getEndTime()));
                }
                String key = jobStat + "";
                processStats.remove(key);

                str = TYPE0_JOB + "|" + rootPid + "|" + fatherPid + "|" + pid + "|" + jobStatStr;
            }

            // System.out.println("Packet " + sdf.format(new Date()) + " => " + str);

            pred.println(str); // envoi d'un message
        }

    }

    public synchronized void updateStatOnConnection(String connectionId, int mode, int nbLine) {
        StatBean bean;
        if (processStats.containsKey(connectionId)) {
            bean = processStats.get(connectionId);
        } else {
            bean = new StatBean(connectionId);
        }
        bean.setState(mode);
        bean.setEndTime(System.currentTimeMillis());
        bean.setNbLine(bean.getNbLine() + nbLine);
        processStats.put(connectionId, bean);

        // if tFileList-->tFileInputDelimited-->tFileOuputDelimited, it should clear the data every iterate
        if (mode == BEGIN) {
            bean.setNbLine(0);
            sendMessages();
        }

        if (debug) {
            sendMessages();
        }
    }

    public synchronized void updateStatOnConnection(String connectionId, int mode, String exec) {
        StatBean bean;
        if (processStats.containsKey(connectionId)) {
            bean = processStats.get(connectionId);
        } else {
            bean = new StatBean(connectionId);
        }
        bean.setState(mode);
        bean.setExec(exec);
        processStats.put(connectionId, bean);

        sendMessages();
    }

    public synchronized void updateStatOnJob(int jobStat, String parentNodeName) {
        StatBean bean = new StatBean(jobStat, parentNodeName);
        String key = jobStat + "";
        processStats.put(key, bean);
        sendMessages();
    }

    // for feature:10589
    private String rootPid = null;

    private String fatherPid = null;

    private String pid = "0";

    private String jobName = null;

    // Notice: this API should be invoked after startThreadStat() closely.
    public void setAllPID(String rootPid, String fatherPid, String pid, String jobName) {
        this.rootPid = rootPid;
        this.fatherPid = fatherPid;
        this.pid = pid;
        this.jobName = jobName;
    }
}
