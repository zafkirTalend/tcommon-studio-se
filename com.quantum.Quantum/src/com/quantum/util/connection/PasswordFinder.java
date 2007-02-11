package com.quantum.util.connection;

/**
 * When a bookmark doesn't have a saved password, an
 * implementation of a password finder class can be called 
 * to obtain one.
 * 
 * @author BC
 */
public interface PasswordFinder {
    
    public String getPassword();
    public boolean isPasswordMeantToBeSaved();

}
