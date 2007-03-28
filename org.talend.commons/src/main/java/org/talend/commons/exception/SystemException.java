// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

import org.talend.commons.i18n.internal.Messages;

/**
 * Defines system exception - Use or extends this class when a system problem occurs and affects a fonctionnality but
 * application can keep running.<br/>
 * 
 * $Id$
 * 
 */
public class SystemException extends Exception {

    @SuppressWarnings("unused") //$NON-NLS-1$
    private static final long serialVersionUID = 1L;

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message) {
        super(message);
    }
}
