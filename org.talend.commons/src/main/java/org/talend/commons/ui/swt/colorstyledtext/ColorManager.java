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
package org.talend.commons.ui.swt.colorstyledtext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.talend.commons.ui.swt.colorstyledtext.scanner.ColoringScanner;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ColorManager {

    public static final RGB DEFAULT_STRING_COLOR = new RGB(0, 0, 0);

    public static final RGB DEFAULT_KEYWORD1_COLOR = new RGB(50, 32, 160);

    public static final RGB DEFAULT_KEYWORD2_COLOR = new RGB(240, 160, 80);

    public static final RGB DEFAULT_KEYWORD3_COLOR = new RGB(160, 32, 100);

    public static final RGB DEFAULT_COMMENT1_COLOR = new RGB(0, 140, 34);

    public static final RGB DEFAULT_COMMENT2_COLOR = new RGB(34, 140, 0);

    public static final RGB DEFAULT_LITERAL1_COLOR = new RGB(0, 0, 255);

    public static final RGB DEFAULT_LITERAL2_COLOR = new RGB(160, 100, 240);

    public static final RGB DEFAULT_LABEL_COLOR = new RGB(160, 0, 240);

    public static final RGB DEFAULT_FUNCTION_COLOR = new RGB(160, 32, 0);

    public static final RGB DEFAULT_MARKUP_COLOR = new RGB(178, 0, 34);

    public static final RGB DEFAULT_OPERATOR_COLOR = new RGB(178, 34, 0);

    public static final RGB DEFAULT_DIGIT_COLOR = new RGB(160, 32, 0);

    public static final RGB DEFAULT_INVALID_COLOR = new RGB(178, 0, 34);

    public static final String NULL_COLOR = "nullColor";

    public static final String COMMENT1_COLOR = "comment1Color";

    public static final String COMMENT2_COLOR = "comment2Color";

    public static final String LITERAL1_COLOR = "literal1Color";

    public static final String LITERAL2_COLOR = "literal2Color";

    public static final String LABEL_COLOR = "labelColor";

    public static final String KEYWORD1_COLOR = "keyword1Color";

    public static final String KEYWORD2_COLOR = "keyword2Color";

    public static final String KEYWORD3_COLOR = "keyword3Color";

    public static final String FUNCTION_COLOR = "functionColor";

    public static final String MARKUP_COLOR = "markupColor";

    public static final String OPERATOR_COLOR = "operatorColor";

    public static final String DIGIT_COLOR = "digitColor";

    public static final String INVALID_COLOR = "invalidColor";

    public static final String BOLD_SUFFIX = "Bold";

    private Map<String, Color> colorMap;

    private IPreferenceStore store;

    private Map<String, String> typeToColorMap;

    public ColorManager(IPreferenceStore store) {
        colorMap = new HashMap<String, Color>();
        this.store = store;
        initTypeToColorMap();
    }

    public Color getColor(String colorName) {
        RGB prefColor = PreferenceConverter.getColor(store, colorName);
        Color color = null;
        if (colorMap.containsKey(colorName) && (colorMap.get(colorName)).getRGB().equals(prefColor)) {
            color = (Color) colorMap.get(colorName);
        } else {
            color = new Color(Display.getDefault(), prefColor);
            colorMap.put(colorName, color);
        }
        return color;
    }

    public void dispose() {
        Collection colors = colorMap.values();
        for (Iterator iter = colors.iterator(); iter.hasNext();) {
            Color color = (Color) iter.next();
            colorMap.remove(color);
            color.dispose();
        }
        colorMap = null;
    }

    /**
     * Converts the ColoringPartitionScanner's type name to the appropriate preference store color name.
     * 
     * @param type
     * @return String
     */
    public String colorForType(String type) {
        return (String) typeToColorMap.get(type);
    }

    protected void initTypeToColorMap() {
        typeToColorMap = new HashMap<String, String>();
        typeToColorMap.put(ColoringScanner.COMMENT1, COMMENT1_COLOR);
        typeToColorMap.put(ColoringScanner.COMMENT2, COMMENT2_COLOR);
        typeToColorMap.put(ColoringScanner.LITERAL1, LITERAL1_COLOR);
        typeToColorMap.put(ColoringScanner.LITERAL2, LITERAL2_COLOR);
        typeToColorMap.put(ColoringScanner.LABEL, LABEL_COLOR);
        typeToColorMap.put(ColoringScanner.KEYWORD1, KEYWORD1_COLOR);
        typeToColorMap.put(ColoringScanner.KEYWORD2, KEYWORD2_COLOR);
        typeToColorMap.put(ColoringScanner.KEYWORD3, KEYWORD3_COLOR);
        typeToColorMap.put(ColoringScanner.FUNCTION, FUNCTION_COLOR);
        typeToColorMap.put(ColoringScanner.MARKUP, MARKUP_COLOR);
        typeToColorMap.put(ColoringScanner.OPERATOR, OPERATOR_COLOR);
        typeToColorMap.put(ColoringScanner.DIGIT, DIGIT_COLOR);
        typeToColorMap.put(ColoringScanner.INVALID, INVALID_COLOR);
        typeToColorMap.put(ColoringScanner.NULL, NULL_COLOR);
    }

    public Color getColorForType(String type) {
        String colorName = colorForType(type);
        if (colorName == null) {
            colorName = NULL_COLOR;
        }
        return getColor(colorName);
    }

    public static void initDefaultColors(IPreferenceStore store) {
        PreferenceConverter.setDefault(store, NULL_COLOR, DEFAULT_STRING_COLOR);
        PreferenceConverter.setDefault(store, COMMENT1_COLOR, DEFAULT_COMMENT1_COLOR);
        PreferenceConverter.setDefault(store, COMMENT2_COLOR, DEFAULT_COMMENT2_COLOR);
        PreferenceConverter.setDefault(store, LITERAL1_COLOR, DEFAULT_LITERAL1_COLOR);
        PreferenceConverter.setDefault(store, LITERAL2_COLOR, DEFAULT_LITERAL2_COLOR);
        PreferenceConverter.setDefault(store, LABEL_COLOR, DEFAULT_LABEL_COLOR);
        PreferenceConverter.setDefault(store, KEYWORD1_COLOR, DEFAULT_KEYWORD1_COLOR);
        PreferenceConverter.setDefault(store, KEYWORD2_COLOR, DEFAULT_KEYWORD2_COLOR);
        PreferenceConverter.setDefault(store, KEYWORD3_COLOR, DEFAULT_KEYWORD3_COLOR);
        PreferenceConverter.setDefault(store, FUNCTION_COLOR, DEFAULT_FUNCTION_COLOR);
        PreferenceConverter.setDefault(store, MARKUP_COLOR, DEFAULT_MARKUP_COLOR);
        PreferenceConverter.setDefault(store, OPERATOR_COLOR, DEFAULT_OPERATOR_COLOR);
        PreferenceConverter.setDefault(store, DIGIT_COLOR, DEFAULT_DIGIT_COLOR);
        PreferenceConverter.setDefault(store, INVALID_COLOR, DEFAULT_INVALID_COLOR);

        String bold = BOLD_SUFFIX;
        store.setDefault(COMMENT1_COLOR + bold, false);
        store.setDefault(COMMENT2_COLOR + bold, true);
        store.setDefault(DIGIT_COLOR + bold, false);
        store.setDefault(FUNCTION_COLOR + bold, false);
        store.setDefault(INVALID_COLOR + bold, false);
        store.setDefault(KEYWORD1_COLOR + bold, false);
        store.setDefault(KEYWORD2_COLOR + bold, false);
        store.setDefault(KEYWORD3_COLOR + bold, false);
        store.setDefault(LABEL_COLOR + bold, false);
        store.setDefault(LITERAL1_COLOR + bold, false);
        store.setDefault(LITERAL2_COLOR + bold, false);
        store.setDefault(MARKUP_COLOR + bold, false);
        store.setDefault(OPERATOR_COLOR + bold, false);
        store.setDefault(NULL_COLOR + bold, false);
    }

    public int getStyleFor(String colorName) {
        String boldSuffix = BOLD_SUFFIX;
        if (colorName == null) {
            colorName = NULL_COLOR;
        }
        String boldName = colorName + boldSuffix;
        boolean isBold = store.getBoolean(boldName);
        return isBold ? SWT.BOLD : SWT.NORMAL;
    }

    public int getStyleForType(String type) {
        return getStyleFor(colorForType(type));
    }

}
