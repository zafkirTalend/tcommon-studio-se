package com.quantum.search;

import com.quantum.model.DatabaseObject;

/**
 * @author BC Holmes
 */
public class SearchEntry {
    
    private final String message;
    private final String table;
    private final String column;
    private final String value;
    private final boolean isFullyQualified;
    private final String keyWord;
    private final String primaryKey;
    private final String icon;
    private final DatabaseObject dbo;
    private final int count;
    
    SearchEntry(String message) {
    	this.table="";
    	this.column="";
    	this.value="";
    	this.primaryKey = "";
        this.message = message;
        this.keyWord = "";
        this.isFullyQualified = false;
        this.icon = "";
        this.dbo = null;
        this.count = 0;
    }
    
    SearchEntry(String keyword, String message)
    {
    	this.table="";
    	this.column="";
    	this.value="";
    	this.primaryKey = "";
    	this.message = message;
    	this.isFullyQualified = false;
    	this.keyWord = keyword;
        this.icon = "";
        this.dbo = null;
        this.count = 0;
   }

    SearchEntry(String keyword, String message, String value, String primaryKey, String icon)
    {
        this.table="";
        this.column="";
        this.value="";
        this.primaryKey = "";
        this.message = message;
        this.isFullyQualified = false;
        this.keyWord = keyword;
        this.icon = icon;
        this.dbo = null;
        this.count = 0;
    }
    
    SearchEntry(String table, String column, String value)
    {
    	this.table = table;
    	this.column = column;
    	this.value = value;
    	this.primaryKey = "";
    	this.message = table + ";" + column + ";" + value;
    	this.isFullyQualified = true;
    	this.keyWord = "";
        this.icon = "";
        this.dbo = null;
        this.count = 0;
    }

    SearchEntry(String table, String column, String value, String primaryKey)
    {
    	this.table = table;
    	this.column = column;
    	this.value = value;
    	this.primaryKey = primaryKey;
    	this.message = table + ";" + column + ";" + value;
    	this.isFullyQualified = true;
    	this.keyWord = "";
        this.icon = "";
        this.dbo = null;
        this.count = 0;
    }

    SearchEntry(String table, String column, String value, String primaryKey, DatabaseObject dbo)
    {
        this.table = table;
        this.column = column;
        this.value = value;
        this.primaryKey = primaryKey;
        this.message = table + ";" + column + ";" + value;
        this.isFullyQualified = true;
        this.keyWord = "";
        this.icon = "";
        this.dbo = dbo;
        this.count = 0;
}

    SearchEntry(String keyword, String procedureName, String icon, DatabaseObject dbo, int count)
    {
        this.table = procedureName;
        this.column = "";
        this.value = "";
        this.primaryKey = "";
        this.message = "";
        this.isFullyQualified = false;
        this.keyWord = keyword;
        this.icon = icon;
        this.dbo = dbo;
        this.count = count;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getTable(){
    	return this.table;
    }
    
    public String getColumn(){
    	return this.column;
    }
    
    public String getValue(){
    	return this.value;
    }
    
    public String getKeyword(){
    	return this.keyWord;
    }
    
    public String getPrimaryKey(){
    	return this.primaryKey;
    }
    
    public String getIcon(){
        return icon;
    }

    public int getCount(){
        return count;
    }
    
    public DatabaseObject getDatabaseObject()
    {
        return dbo;
    }
    
    public boolean isFullyQualified(){
    	return this.isFullyQualified;
    }
    
    public String toString() {
    	if (this.message != null) {
            return this.message;
        } else {
            return "";
        }
    }
}
