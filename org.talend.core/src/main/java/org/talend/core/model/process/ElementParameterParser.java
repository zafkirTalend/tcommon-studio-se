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
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class ElementParameterParser {

    private ElementParameterParser() {
    }

    public static String getValue(final IElement element, final String text) {
        String newText = new String(""); //$NON-NLS-1$
        if (text == null) {
            return newText;
        }
        IElementParameter param;
        boolean end = false;

        for (int i = 0; i < element.getElementParameters().size() && !end; i++) {
            param = (IElementParameter) element.getElementParameters().get(i);
            if (text.indexOf(param.getVariableName()) != -1) {
                newText = getDisplayValue(param);
                end = true;
            }
        }
        return newText;
    }

    /**
     * Only work with one element.
     * 
     * @param element
     * @param text
     * @return
     */
    public static Object getObjectValue(final IElement element, final String text) {
        if (text == null) {
            return null;
        }
        IElementParameter param;

        for (int i = 0; i < element.getElementParameters().size(); i++) {
            param = (IElementParameter) element.getElementParameters().get(i);
            if (text.indexOf(param.getVariableName()) != -1) {
                if (param.getField() == EParameterFieldType.TABLE) {
                    return createTableValues((List<Map<String, Object>>) param.getValue(), param);
                }
                return param.getValue();
            }
        }
        return null;
    }

    private static List<Map<String, String>> createTableValues(final List<Map<String, Object>> paramValues,
            final IElementParameter param) {
        List<Map<String, String>> tableValues = new ArrayList<Map<String, String>>();
        for (Map<String, Object> currentLine : paramValues) {
            tableValues.add(copyLine(currentLine, param));
        }
        return tableValues;
    }

    private static Map<String, String> copyLine(Map<String, Object> currentLine, IElementParameter param) {
        Map<String, String> newLine = new HashMap<String, String>();
        String[] items = param.getListItemsDisplayCodeName();
        for (int i = 0; i < items.length; i++) {
            Object o = currentLine.get(items[i]);
            if (o instanceof Integer) {
                IElementParameter tmpParam = (IElementParameter) param.getListItemsValue()[i];
                newLine.put(items[i], (String) tmpParam.getListItemsValue()[(Integer) o]);
            } else {
                newLine.put(items[i], (String) o);
            }
        }
        return newLine;
    }

    public static String parse(final IElement element, final String text) {
        String newText = ""; //$NON-NLS-1$
        if (text == null) {
            return newText;
        }
        IElementParameter param;

        newText = text;
        for (int i = 0; i < element.getElementParameters().size(); i++) {
            param = (IElementParameter) element.getElementParameters().get(i);
            if (newText.contains(param.getVariableName())) {
                String value = getDisplayValue(param);
                newText = newText.replace(param.getVariableName(), value);
            }
        }
        return newText;
    }

    @SuppressWarnings("unchecked")
    private static String getDisplayValue(final IElementParameter param) {
        Object value = param.getValue();
        if (value instanceof String) {
            return (String) value;
        }

        if (param.getField() == EParameterFieldType.CHECK) {
            return ((Boolean) param.getValue()).toString();
        }
        if (param.getField() == EParameterFieldType.TABLE) {
            List<Map<String, Object>> tableValues = (List<Map<String, Object>>) param.getValue();
            String[] items = (String[]) param.getListItemsDisplayCodeName();
            String stringValues = "{";
            for (int i = 0; i < tableValues.size(); i++) {
                Map<String, Object> lineValues = tableValues.get(i);
                stringValues += "[";
                for (int j = 0; j < items.length; j++) {

                    Object currentValue = lineValues.get(items[j]);
                    if (currentValue instanceof Integer) {
                        IElementParameter tmpParam = (IElementParameter) param.getListItemsValue()[j];
                        stringValues += tmpParam.getListItemsDisplayName()[(Integer) currentValue];
                    } else {
                        stringValues += currentValue;
                    }

                    if (j != (items.length - 1)) {
                        stringValues += ",";
                    }
                }
                stringValues += "]";
                if (i != (tableValues.size() - 1)) {
                    stringValues += ",";
                }
            }
            stringValues += "}";
            return stringValues;
        }
        return new String(""); //$NON-NLS-1$
    }
}
