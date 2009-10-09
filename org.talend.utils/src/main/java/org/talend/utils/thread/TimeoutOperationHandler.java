// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.thread;

import org.apache.log4j.Logger;

/**
 * 
 * Handler for an operation which processes with a timeout.
 * @param <R> type of result
 */
public abstract class TimeoutOperationHandler<R> {

    private static Logger log = Logger.getLogger(TimeoutOperationHandler.class);
    
    private R result;

    private boolean timeoutReached;

    private String labelOperation;

    private long timeout;

    private Throwable operationError;

    private R defaultResult;
    
    public TimeoutOperationHandler(long timeout) {
        super();
        this.timeout = timeout;
    }

    public TimeoutOperationHandler(long timeout, String labelOperation) {
        super();
        this.timeout = timeout;
        this.labelOperation = labelOperation;
    }

    public void start() {
        
        internalStart();

    }

    protected void internalStart() {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    result = TimeoutOperationHandler.this.internalRun();
                } catch (Throwable t) {
                    log.error(t.getMessage(), t);
                    operationError = t;
                } finally {
                    finalizeOperation();
                }
            }

        };
        
        if(labelOperation != null) {
            new Thread(runnable, labelOperation).start();
        } else {
            new Thread(runnable).start();
        }

        long timeStart = System.currentTimeMillis();

        while (true) {
            
            if(System.currentTimeMillis() - timeStart > timeout) {
                timeoutReached = true;
            }
            
            if (timeoutReached || hasValidResultOperation()) {
                break;
            }
            ThreadUtils.waitTimeBool(100);
        }
    }
    
    protected R internalRun() {
        return run();
    }

    public boolean hasValidResultOperation() {
        return result != null;
    }
    
    public abstract R run();

    public R getResult() {
        if(hasValidResultOperation()) {
            return result;
        } else {
            return getDefaultResult();
        }
    }
    
    public Throwable getOperationError() {
        return operationError;
    }
    
    public void finalizeOperation() {
        
    }
    
    public R getDefaultResult() {
        return defaultResult;
    }

    public void setDefaultResult(R defaultResult) {
        this.defaultResult = defaultResult;
    }

    
    public void setLabelOperation(String labelOperation) {
        this.labelOperation = labelOperation;
    }
    
}
