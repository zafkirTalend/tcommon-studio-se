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
package org.talend.commons.ui.image;

import org.talend.commons.CommonsPlugin;

/**
 * 
 * DOC smallet ImageProvider class global comment. Detailled comment <br/>
 * 
 * $Id: ImageProvider.java 418 2006-11-13 16:01:26 +0000 (lun., 13 nov. 2006) cantoine $
 * 
 */
public enum EImage implements IImage {

    DEFAULT_IMAGE,
    EMPTY("/icons/empty.gif"),

    SAVE_ICON("/icons/save.png"),
    COPY_ICON("/icons/copy.gif"),
    PASTE_ICON("/icons/paste.gif"),
    CUT_ICON("/icons/cut.png"),
    DELETE_ICON("/icons/delete.gif"),
    RESTORE_ICON("/icons/add.gif"),
    ADD_ICON("/icons/add.gif"),
    MINUS_ICON("/icons/delete.gif"),
    REFRESH_ICON("/icons/refresh.gif"),

    IMPORT_ICON("/icons/import.gif"),
    EXPORT_ICON("/icons/export.gif"),

    ERROR_ICON("/icons/error.gif"),
    WARNING_ICON("/icons/warning.gif"),
    ERROR_SMALL("/icons/error_small.gif"),
    WARNING_SMALL("/icons/warning_small.gif"),

    UP_ICON("/icons/up.gif"),
    DOWN_ICON("/icons/down.gif"),
    LEFT_ICON("/icons/left.gif"),
    RIGHT_ICON("/icons/right.gif"),

    KEY_ICON("/icons/key.gif"),
    HIERARCHY_ICON("/icons/hierarchicalLayout.gif"),

    THREE_DOTS_ICON("/icons/dots_button.gif"),

    ;

    private String path;

    EImage() {
        this.path = "/icons/unknown.gif";
    }

    EImage(String path) {
        this.path = path;
    }

    /**
     * Getter for path.
     * 
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Getter for clazz.
     * 
     * @return the clazz
     */
    public Class getLocation() {
        return CommonsPlugin.class;
    }

}
