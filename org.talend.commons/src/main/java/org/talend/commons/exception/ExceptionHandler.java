// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.exception;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Implementation of exception handling strategy.<br/>
 * 
 * $Id$
 * 
 */
public final class ExceptionHandler {

    private static Logger log = Logger.getLogger(ExceptionHandler.class);

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
        String message = ex.getMessage();

        Priority priority = getPriority(ex);

        log.log(priority, message, ex);

        if (priority == Level.FATAL) {
            MessageBoxExceptionHandler.showError(ex);
        }
    }

    /**
     * Return priority corresponding to the exception implementation.
     * 
     * @param ex - the exception to evaluate priority
     * @return the priority corresponding to the exception implementation
     */
    private static Priority getPriority(Throwable ex) {
        if (ex == null) {
            throw new IllegalArgumentException("ex param cannot be null");
        }

        if (ex instanceof BusinessException) {
            return Level.INFO;
        } else if (ex instanceof FatalException) {
            return Level.FATAL;
        } else if (ex instanceof SystemException) {
            return Level.WARN;
        } else {
            return Level.ERROR;
        }
    }
}
