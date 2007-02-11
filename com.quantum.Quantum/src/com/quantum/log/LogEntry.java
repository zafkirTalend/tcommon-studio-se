package com.quantum.log;

/**
 * @author BC Holmes
 */
public class LogEntry {
    
    private final Severity severity;
    private final String message;
    private final String exceptionText;

    LogEntry(Severity severity, String message, String exceptionText) {
        this.severity = severity;
        this.message = message;
        this.exceptionText = exceptionText;
        
    }
    public String getMessage() {
        return this.message;
    }
    public Severity getSeverity() {
        return this.severity;
    }
    public String getExceptionText() {
        return this.exceptionText;
    }
    public String toString() {
        if (this.message != null && this.exceptionText != null) {
            return this.message + "\n" + this.exceptionText;
        } else if (this.message != null) {
            return this.message;
        } else if (this.exceptionText != null) {
            return this.exceptionText;
        } else {
            return "";
        }
    }
}
