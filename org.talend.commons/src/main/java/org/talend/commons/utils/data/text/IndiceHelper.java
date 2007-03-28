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
package org.talend.commons.utils.data.text;

import java.util.List;

/**
 * DOC ocarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class IndiceHelper {

    /**
     * Check the unicity of the newLabel in the list of existingLabel. Add or increase an indice if needed.
     * 
     * @param String newLabel
     * @param List existingLabel2
     * @return String
     */
    public static String getIndexedLabel(String newLabel, List existingLabel2) {
        String[] existingLabel = new String[existingLabel2.size()];
        for (int i = 0; i < existingLabel2.size(); i++) {
            existingLabel[i] = (String) existingLabel2.get(i);
        }
        return getIndexedString(newLabel, existingLabel);
    }

    /**
     * Check the unicity of the newLabel in the list of existingLabel. Add or increase an indice if needed.
     * 
     * @param String Label
     * @param String[] existingLabel
     * @return String
     */
    public static String getIndexedString(String string, String[] existingLabel) {
        int indicePosition = string.length();

        // define the position (to create or increase the indice)
        for (int f = string.length() - 1; f >= 0; f--) {
            if (Character.isDigit(string.charAt(f))) {
                indicePosition = f;
            } else {
                f = 0;
            }

        }

        // validate the value is unique and indice when if needed
        while (!isUniqLabel(string, existingLabel)) {
            try {
                String indiceString = string.substring(indicePosition, string.length());
                string = string.substring(0, indicePosition) + (Integer.parseInt(indiceString) + 1);
            } catch (Exception e) {
                string = string + "1";
            }
        }
        return string;
    }

    private static boolean isUniqLabel(String label, String[] existingLabel) {
        if (existingLabel == null) {
            return true;
        } else {
            for (int i = 0; i < existingLabel.length; i++) {
                if (label.equals(existingLabel[i])) {
                    i = existingLabel.length;
                    return false;
                }
            }
        }
        return true;
    }

}
