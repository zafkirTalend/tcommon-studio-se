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

import java.util.List;
import java.util.Map;

import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.temp.ECodeLanguage;

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
                return param.getValue();
            }
        }
        return null;
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
            List<Map<String, String>> tableValues = (List<Map<String, String>>) param.getValue();
            ECodeLanguage language = ((RepositoryContext) CorePlugin.getContext().getProperty(
                    Context.REPOSITORY_CONTEXT_KEY)).getProject().getLanguage();
            String[] items = (String[]) param.getListItemsValue(language);
            String stringValues = "{";
            for (int i = 0; i < tableValues.size(); i++) {
                Map<String, String> lineValues = tableValues.get(i);
                stringValues += "[";
                for (int j = 0; j < items.length; j++) {
                    String currentValue = lineValues.get(items[j]);
                    stringValues += currentValue;

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
