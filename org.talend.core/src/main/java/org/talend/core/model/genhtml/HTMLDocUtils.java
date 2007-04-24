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
package org.talend.core.model.genhtml;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;

/**
 * Utility class for generating HTML file.
 * 
 */
public class HTMLDocUtils {

    /**
     * Strings handler.
     * 
     * @param str <code>String</code>
     * @return string
     */
    public static String checkString(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * Dates handler.
     * 
     * @param date
     * @return
     */
    public static String checkDate(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat formatter = DateFormat.getDateTimeInstance();
        return formatter.format(date);
    }

    /**
     * Gets the temporary folder.
     * 
     * @return a string representing temporary folder
     */
    public static String getTmpFolder() {
        String tmpFold = System.getProperty("user.dir") + File.separatorChar + IHTMLDocConstants.TEMP_FOLDER_NAME; //$NON-NLS-1$
        // String tmpFold = System.getProperty("osgi.instance.area") +
        return tmpFold;
    }

    /**
     * Checks if inputed list is null or length is 0.
     * 
     * @param tables
     */
    public static boolean checkList(List tables) {
        boolean isValid = (tables != null && tables.size() > 0);
        return isValid;
    }

    public static String getPreviewPicPath(INode node) {
        String previewImagePath = "";
        previewImagePath = getPreviewImagePath(node.getElementParameters());
        if (previewImagePath == null || previewImagePath.length() == 0) {
            return previewImagePath;
        }
        return IHTMLDocConstants.PICTUREFOLDERPATH + previewImagePath;
    }

    /**
     * Gets tMap's preview image path base on a list of <code>IElementParameter</code>
     * 
     * @param elementParameters
     * @return
     */
    private static String getPreviewImagePath(List<? extends IElementParameter> elementParameters) {

        for (IElementParameter parameter : elementParameters) {
            IElementParameter type = parameter;
            if (type.getName().equals("PREVIEW")) {
                return type.getValue().toString();
            }
        }
        return "";
    }
}
