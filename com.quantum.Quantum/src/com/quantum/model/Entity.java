package com.quantum.model;


/**
 * @author BC
 * En Entity is a DatabaseObject that can be queried, that is, has table or table-like
 * data, usually columns, etc.
 */
public interface Entity extends DatabaseObject, TableMetadata {
    
    
    /**
     * @return - TRUE if the entity exists in the database<br />
     *         - FALSE if the entity does not exist in the database<br />
     *         - null if it's not known whether or not the 
     *           entity exists in the database.
     */
    public Boolean exists();
    /**
     * Some table names must be quoted.
     * @return
     */
    public String getQuotedTableName();
}
