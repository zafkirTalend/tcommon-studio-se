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
package org.talend.core.model.process;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.talend.core.i18n.Messages;

/**
 * Different types of connections in Talend. <br/>
 * 
 * $Id$
 * 
 */
public enum EConnectionType implements IConnectionCategory {
    FLOW_MAIN(0, "FLOW", //$NON-NLS-1$
              EConnectionCategory.MAIN,
              MAIN | DATA | FLOW | CUSTOM_NAME | UNIQUE_NAME,
              "Main", Messages.getString("EConnectionType.mainMenu"), //$NON-NLS-1$ //$NON-NLS-2$
              new Integer(Graphics.LINE_SOLID),
              new Color(null, new RGB(230, 100, 0))),
    /*
     * RUN_BEFORE(1, "BEFORE", //$NON-NLS-1$ EConnectionCategory.OTHER, EXECUTION_ORDER | DEPENDENCY, "RunBefore",
     * Messages.getString("EConnectionType.runBeforeMenu"), //$NON-NLS-1$ //$NON-NLS-2$ new
     * Integer(Graphics.LINE_SOLID), new Color(null, new RGB(100, 100, 100))),
     */
    // LOOKUP(3, "LOOKUP", //$NON-NLS-1$
    // EConnectionCategory.OTHER,
    // USE_HASH | CUSTOM_NAME | UNIQUE_NAME,
    // "Lookup", Messages.getString("EConnectionType.lookupMenu"), //$NON-NLS-1$ //$NON-NLS-2$
    // new Integer(Graphics.LINE_DASHDOTDOT),
    // new Color(null, new RGB(150, 150, 0))),
    RUN_IF_OK(4, "RUN_OK", //$NON-NLS-1$
              EConnectionCategory.OTHER,
              CONDITION | DEPENDENCY,
              "OnOk", Messages.getString("EConnectionType.runIfOKMenu"), //$NON-NLS-1$ //$NON-NLS-2$
              new Integer(Graphics.LINE_SOLID),
              new Color(null, new RGB(0, 150, 0))),
    RUN_IF_ERROR(5, "RUN_ERROR", //$NON-NLS-1$
                 EConnectionCategory.OTHER,
                 CONDITION | DEPENDENCY,
                 "OnError", Messages.getString("EConnectionType.runIfErrorMenu"), //$NON-NLS-1$ //$NON-NLS-2$
                 new Integer(Graphics.LINE_SOLID),
                 new Color(null, new RGB(200, 0, 0))),
    RUN_IF(6, "RUN_IF", //$NON-NLS-1$
           EConnectionCategory.OTHER,
           CONDITION | DEPENDENCY,
           "If", Messages.getString("EConnectionType.runIfMenu"), //$NON-NLS-1$ //$NON-NLS-2$
           new Integer(Graphics.LINE_DASHDOTDOT),
           new Color(null, new RGB(180, 100, 30))),
    ITERATE(7, "ITERATE", //$NON-NLS-1$
            EConnectionCategory.MAIN,
            MAIN,
            "Iterate", Messages.getString("EConnectionType.iteratorMenu"), //$NON-NLS-1$ //$NON-NLS-2$
            new Integer(Graphics.LINE_SOLID),
            new Color(null, new RGB(100, 230, 0))),
    FLOW_REF(8, "LOOKUP", //$NON-NLS-1$
             EConnectionCategory.OTHER,
             DATA | FLOW | CUSTOM_NAME | USE_HASH | UNIQUE_NAME,
             "Lookup", Messages.getString("EDesignerConnection.lookupMenu"), //$NON-NLS-1$ //$NON-NLS-2$
             new Integer(Graphics.LINE_DOT),
             new Color(null, new RGB(230, 100, 0))),
    TABLE(9, "TABLE", //$NON-NLS-1$
          EConnectionCategory.MAIN,
          MAIN | DATA | CUSTOM_NAME,
          "Table", Messages.getString("EConnectionType.tableMenu"), //$NON-NLS-1$ //$NON-NLS-2$
          new Integer(Graphics.LINE_SOLID),
          new Color(null, new RGB(0, 150, 100))),
    FLOW_MERGE(10, "MERGE", //$NON-NLS-1$
               EConnectionCategory.MAIN,
               DATA | FLOW | CUSTOM_NAME | UNIQUE_NAME,
               "Merge", Messages.getString("EConnectionType.mergeMenu"), //$NON-NLS-1$ //$NON-NLS-2$
               new Integer(Graphics.LINE_DASHDOT),
               new Color(null, new RGB(230, 100, 0))),
    THEN_RUN(1, "THEN_RUN", //$NON-NLS-1$
             EConnectionCategory.OTHER,
             EXECUTION_ORDER | DEPENDENCY,
             "ThenRun", Messages.getString("EConnectionType.thenRunMenu"), //$NON-NLS-1$ //$NON-NLS-2$
             new Integer(Graphics.LINE_SOLID),
             new Color(null, new RGB(100, 100, 100))),

    RUN_AFTER(2, "AFTER", //$NON-NLS-1$
              EConnectionCategory.OTHER,
              EXECUTION_ORDER | DEPENDENCY,
              "RunAfter", Messages.getString("EConnectionType.runAfterMenu"), //$NON-NLS-1$ //$NON-NLS-2$ 
              new Integer(Graphics.LINE_SOLID),
              new Color(null, new RGB(100, 100, 100)));

    private String name;

    private int id;

    private EConnectionCategory category;

    private int connectionCategory;

    private String defaultMenuName;

    private String defaultLinkName;

    /**
     * @see org.eclipse.draw2d.Graphics
     */
    private Integer defaultLineStyle;

    private Color defaultColor;

    EConnectionType(int id, String name, EConnectionCategory category, int connectionCategory, String linkName,
            String menuName, Integer lineStyle, Color color) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.connectionCategory = connectionCategory;
        this.defaultLinkName = linkName;
        this.defaultMenuName = menuName;
        this.defaultLineStyle = lineStyle;
        this.defaultColor = color;
    }

    public static EConnectionType getTypeFromId(int id) {
        EConnectionType[] listConnectionType = EConnectionType.values();
        for (int i = 0; i < listConnectionType.length; i++) {
            if ((listConnectionType[i].getId()) == id) {
                return listConnectionType[i];
            }
        }
        // Default Value
        return EConnectionType.FLOW_MAIN;
    }

    public static EConnectionType getTypeFromName(String name) {
        EConnectionType[] listConnectionType = EConnectionType.values();
        for (int i = 0; i < listConnectionType.length; i++) {
            if (listConnectionType[i].getName().equals(name)) {
                return listConnectionType[i];
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Getter for category.
     * 
     * @return the category
     * @deprecated
     */
    public EConnectionCategory getCategory() {
        return this.category;
    }

    public boolean hasConnectionCategory(int category) {
        return (connectionCategory & category) != 0;
    }

    /**
     * Getter for defaultColor.
     * 
     * @return the defaultColor
     */
    public Color getDefaultColor() {
        return defaultColor;
    }

    /**
     * Getter for defaultLineStyle.
     * 
     * @return the defaultLineStyle
     */
    public Integer getDefaultLineStyle() {
        return defaultLineStyle;
    }

    /**
     * Getter for defaultLinkName.
     * 
     * @return the defaultLinkName
     */
    public String getDefaultLinkName() {
        return defaultLinkName;
    }

    /**
     * Getter for defaultMenuName.
     * 
     * @return the defaultMenuName
     */
    public String getDefaultMenuName() {
        return defaultMenuName;
    }
}
