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
package org.talend.core.language;

import org.talend.core.i18n.Messages;

/**
 * Enum for available Code Languages in the application.
 * 
 * $Id$
 * 
 */
public enum ECodeLanguage {
    PERL("perl", "perl"), //$NON-NLS-1$ //$NON-NLS-2$
    JAVA("java", "java"); //$NON-NLS-1$ //$NON-NLS-2$
    
    private ECodeLanguage(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public static ECodeLanguage getCodeLanguage(String name) {
        for (ECodeLanguage codeLanguage : ECodeLanguage.values()) {
            if (codeLanguage.getName().equals(name)) {
                return codeLanguage;
            }
        }
        return PERL;
    }

    private String name;

    private String extension;

    /**
     * Getter for extension.
     * 
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the extension.
     * 
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
