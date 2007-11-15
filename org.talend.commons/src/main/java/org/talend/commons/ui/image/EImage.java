// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
    EDIT_ICON("/icons/write_obj.gif"),
    READ_ICON("/icons/read_obj.gif"),

    RESET_DBTYPES_ICON("/icons/reset_dbtypes.jpg"),

    IMPORT_ICON("/icons/import.gif"),
    EXPORT_ICON("/icons/export.gif"),

    CHECKED_ICON("/icons/checked.gif"),
    UNCHECKED_ICON("/icons/unchecked.gif"),

    ERROR_ICON("/icons/error.gif"),
    WARNING_ICON("/icons/warning.gif"),
    ERROR_SMALL("/icons/error_small.gif"),
    WARNING_SMALL("/icons/warning_small.gif"),
    OK("/icons/ok.png"),

    UP_ICON("/icons/up.gif"),
    DOWN_ICON("/icons/down.gif"),
    LEFT_ICON("/icons/left.gif"),
    RIGHT_ICON("/icons/right.gif"),

    KEY_ICON("/icons/key.gif"),
    HIERARCHY_ICON("/icons/hierarchicalLayout.gif"),

    THREE_DOTS_ICON("/icons/dots_button.gif"),

    PROPERTIES_WIZ("/icons/editpref_wiz.gif"),

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
