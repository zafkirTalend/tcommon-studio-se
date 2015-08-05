// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.runtime.exception;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.exception.CommonExceptionHandler;

/**
 * Implementation of exception handling strategy.<br/>
 * 
 * $Id: ExceptionHandler.java 7038 2007-11-15 14:05:48Z plegall $
 * 
 */
public final class ExceptionHandler {

    /**
     * Empty constructor.
     */
    private ExceptionHandler() {
    }

    /**
     * Log message relative to ex param. Log level depends on exception type.
     * 
     * @param ex - exception to log
     */
    public static void process(Throwable ex) {
        Priority priority = CommonExceptionHandler.getPriority(ex);
        process(ex, priority);
    }

    public static void log(String message) {
        CommonExceptionHandler.log(message);
    }

    public static void process(Throwable ex, Priority priority) {
        CommonExceptionHandler.process(ex, priority);

        if (priority == Level.FATAL) {
            MessageBoxExceptionHandler.showMessage(ex, new Shell());
        }
    }

    /**
     * bug 17654: import the xml file as the schema will throw error.
     * 
     * DOC yhch Comment method "processForSchemaImportXml".
     * 
     * @param ex
     */
    public static void processForSchemaImportXml(Throwable ex) {
        MessageBoxExceptionHandler.showMessageForSchemaImportXml(ex, new Shell());
    }
}
