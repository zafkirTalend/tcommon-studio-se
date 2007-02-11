package com.quantum.log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quantum.QuantumPlugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * <p>Create a log of the interactions with the database.
 * 
 * <p>Notes: The original log mechanisms contained a lot of oddities.  This revision
 * attempts to address those oddities.
 * 
 * @author BC Holmes
 */
public class QuantumLog {
    
    public static final String NUMBER_OF_ENTRIES_PREFERENCE_NAME = 
        	QuantumLog.class.getName() + ".numberOfEntries";
    public static final String LOG_LEVEL_PREFERENCE_NAME = 
        	QuantumLog.class.getName() + ".logLevel";
    private static final QuantumLog instance = new QuantumLog();

    private IPropertyChangeListener listener = new IPropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent event) {
            if (NUMBER_OF_ENTRIES_PREFERENCE_NAME.equals(event.getProperty())) {
                setNumberOfEntriesFromPreferences();
            } else if (LOG_LEVEL_PREFERENCE_NAME.equals(event.getProperty())) {
                setLogLevelFromPreferences();
            }
        }
    };
    private List log = Collections.synchronizedList(new ArrayList());
    private int numberOfEntries = 300;
    private Severity logLevel = Severity.INFO;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    private QuantumLog() {
        setLogLevelFromPreferences();
        setNumberOfEntriesFromPreferences();
        QuantumPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this.listener);
    }


    public static QuantumLog getInstance() {
        return QuantumLog.instance;
    }
    
    public void info(String message) {
        addToLog(Severity.INFO, message);
    }

    /**
     * @param severity
     * @param message
     */
    private void addToLog(Severity severity, String message) {
        addToLog(severity, message, null);
    }
    private void addToLog(Severity severity, String message, Throwable throwable) {
        if (severity.getRank() >= this.logLevel.getRank()) {
            LogEntry logEntry = new LogEntry(severity, message, 
                    getStackTraceAsString(throwable));
            this.log.add(logEntry);
		    trimLog();
		    
		    this.propertyChangeSupport.firePropertyChange("entries", null, logEntry);
        }
    }

    /**
     * @param throwable
     * @return
     */
    private String getStackTraceAsString(Throwable throwable) {
        if (throwable != null) {
	        StringWriter writer = new StringWriter();
	        PrintWriter printWriter = new PrintWriter(writer);
	        throwable.printStackTrace(printWriter);
	        printWriter.close();
	        return writer.toString();
        } else {
            return null;
        }
    }


    /**
     * 
     */
    private void trimLog() {
        while (this.log.size() > this.numberOfEntries && !this.log.isEmpty()) {
            this.log.remove(0);
        }
    }

    public void debug(String message) {
        addToLog(Severity.DEBUG, message);
    }
    
    public void error(String message) {
        addToLog(Severity.ERROR, message);
    }
    
    public void error(String message, Throwable throwable) {
        addToLog(Severity.ERROR, message, throwable);
    }
    
    public void warn(String message) {
        addToLog(Severity.WARN, message);
    }
    private void setNumberOfEntriesFromPreferences() {
        IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
        int temp = store.getInt(NUMBER_OF_ENTRIES_PREFERENCE_NAME);
        if (temp > 0) {
            this.numberOfEntries = temp;
        }
    }
    private void setLogLevelFromPreferences() {
        IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
        String level = store.getString(LOG_LEVEL_PREFERENCE_NAME);
        this.logLevel = Severity.getSeverity(level);
    }

    public LogEntry[] getEntries() {
        return (LogEntry[]) this.log.toArray(new LogEntry[this.log.size()]);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
    public void clear() {
        this.log.clear();
	    this.propertyChangeSupport.firePropertyChange("entries", null, null);
    }
    public int getNumberOfEntries() {
        return this.numberOfEntries;
    }
}
