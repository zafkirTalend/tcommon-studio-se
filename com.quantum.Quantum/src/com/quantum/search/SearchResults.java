package com.quantum.search;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quantum.model.DatabaseObject;

/**
 * 
 * @author BC Holmes/JHvdV
 */
public class SearchResults {
    
//    public static final String NUMBER_OF_ENTRIES_PREFERENCE_NAME = 
//        	SearchResults.class.getName() + ".numberOfEntries";
//    public static final String LOG_LEVEL_PREFERENCE_NAME = 
//        	SearchResults.class.getName() + ".logLevel";
    private static final SearchResults instance = new SearchResults();

//    private IPropertyChangeListener listener = new IPropertyChangeListener() {
//        public void propertyChange(PropertyChangeEvent event) {
//            if (NUMBER_OF_ENTRIES_PREFERENCE_NAME.equals(event.getProperty())) {
//                setNumberOfEntriesFromPreferences();
//            } else if (LOG_LEVEL_PREFERENCE_NAME.equals(event.getProperty())) {
//                setLogLevelFromPreferences();
//            }
//        }
//    };
    private List results = Collections.synchronizedList(new ArrayList());
    private int numberOfEntries = 300;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    private SearchResults() {
//        QuantumPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this.listener);
    }

    public static SearchResults getInstance() {
        return SearchResults.instance;
    }
    
     public void addToResults(String message){
        SearchEntry searchEntry = new SearchEntry(message);
        this.results.add(searchEntry);
		trimResults();
	    this.propertyChangeSupport.firePropertyChange("entries", null, searchEntry);
    }
    
    public void addToResults(String keyword, String message)
    {
    	SearchEntry searchEntry = new SearchEntry(keyword, message);
        this.results.add(searchEntry);
		trimResults();
	    this.propertyChangeSupport.firePropertyChange("entries", null, searchEntry);
    }

    public void addToResults(String keyword, String message, String iconName)
    {
        SearchEntry searchEntry = new SearchEntry(keyword, message, null, null, iconName);
        this.results.add(searchEntry);
        trimResults();
        this.propertyChangeSupport.firePropertyChange("entries", null, searchEntry);
    }

    public void addToResults(String keyword, String procedureName, String iconName, DatabaseObject dbo, int count)
    {
        SearchEntry searchEntry = new SearchEntry(keyword, procedureName, iconName, dbo, count);
        this.results.add(searchEntry);
        trimResults();
        this.propertyChangeSupport.firePropertyChange("entries", null, searchEntry);
    }

    public void addToResults(String table, String column, String value, String primaryKey){
        SearchEntry searchEntry = new SearchEntry(table, column, value, primaryKey);
        this.results.add(searchEntry);
		trimResults();
	    this.propertyChangeSupport.firePropertyChange("entries", null, searchEntry);
    }

    public void addToResults(String table, String column, String value, String primaryKey, DatabaseObject dbo){
        SearchEntry searchEntry = new SearchEntry(table, column, value, primaryKey, dbo);
        this.results.add(searchEntry);
        trimResults();
        this.propertyChangeSupport.firePropertyChange("entries", null, searchEntry);
    }

    private void trimResults() {
        while (this.results.size() > this.numberOfEntries && !this.results.isEmpty()) {
            this.results.remove(0);
        }
    }

    public SearchEntry[] getEntries() {
    	if (this.results.size() == 0) return null;
        return (SearchEntry[]) this.results.toArray(new SearchEntry[this.results.size()]);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
    public void clear() {
        this.results.clear();
        this.propertyChangeSupport.firePropertyChange("entries", null, null);
    }
    public int getNumberOfEntries() {
        return this.numberOfEntries;
    }
}
