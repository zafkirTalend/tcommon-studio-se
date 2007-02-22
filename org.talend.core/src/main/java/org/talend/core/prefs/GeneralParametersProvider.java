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
package org.talend.core.prefs;

import java.util.Arrays;
import java.util.ResourceBundle;

import org.talend.commons.i18n.MessagesCore;

/**
 * Use to retrieve general application parameters.<br/>
 * 
 * $Id: Messages.java 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class GeneralParametersProvider extends MessagesCore {

    private static final String BUNDLE_NAME = "parameters"; //$NON-NLS-1$

    private static ResourceBundle resourceBundle;

    private static ResourceBundle getBundle() {
        if (resourceBundle == null) {
            try {
                resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
            } catch (Exception e) {
                // Nothing to do (return null)
            }
        }
        return resourceBundle;
    }

    /**
     * Returns the value corresponding to the specified key.
     */
    public static String getString(GeneralParameters key) {
        return getString(key.getParamName(), getBundle());
    }

    /**
     * Returns a sorted string array containing values corresponding to the specified key.
     */
    public static String[] getStrings(GeneralParameters key) {
        String value = getString(key);
        String[] toReturn = value.split(",");
        Arrays.sort(toReturn);
        return toReturn;
    }

    /**
     * DOC smallet GeneralParametersProvider class global comment. Detailled comment <br/>
     * 
     * $Id$
     */
    public enum GeneralParameters {
        AUTHORIZED_LANGUAGE("param.authorizedlanguage");

        private String paramName;

        GeneralParameters(String paramName) {
            this.paramName = paramName;
        }

        public String getParamName() {
            return this.paramName;
        }
    }
}
