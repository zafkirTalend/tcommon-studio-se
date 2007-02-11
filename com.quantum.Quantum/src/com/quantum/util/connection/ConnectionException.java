package com.quantum.util.connection;

/**
 * @author BC
 */
public class ConnectionException extends Exception {

    static final long serialVersionUID = 1L;
    
    private Throwable cause = null;

    /**
     * 
     */
    public ConnectionException() {
        super();
    }

    /**
     * @param message
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ConnectionException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * @param cause
     */
    public ConnectionException(Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String toString() {
        String base = super.toString();
        if (this.cause != null) {
            base += System.getProperty("line.separator")
                + "Root cause:"
                + System.getProperty("line.separator")
                + this.cause.toString();
        }
        return base;
    }

}
