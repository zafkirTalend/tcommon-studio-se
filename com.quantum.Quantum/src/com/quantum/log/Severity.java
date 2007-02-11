package com.quantum.log;


public class Severity {
    
    public static final Severity DEBUG = new Severity("debug", 0);
    public static final Severity INFO = new Severity("info", 1);
    public static final Severity WARN = new Severity("warn", 2);
    public static final Severity ERROR = new Severity("error", 3);
    
    private static final Severity[] SEVERITIES = new Severity[] {
            DEBUG, INFO, WARN, ERROR
    };
    
    private final String name;
    private final int rank;

    private Severity(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }
    public String getName() {
        return this.name;
    }
    public int getRank() {
        return this.rank;
    }
    
    public static Severity[] getSeverities() {
        return Severity.SEVERITIES;
    }
    /**
     * @param level
     */
    public static Severity getSeverity(String level) {
        if (Severity.DEBUG.getName().equals(level)) {
            return Severity.DEBUG;
        } else if (Severity.INFO.getName().equals(level)) {
            return Severity.INFO;
        } else if (Severity.WARN.getName().equals(level)) {
            return Severity.WARN;
        } else if (Severity.ERROR.getName().equals(level)) {
            return Severity.ERROR;
        } else {
            return Severity.INFO;
        }
    }
}