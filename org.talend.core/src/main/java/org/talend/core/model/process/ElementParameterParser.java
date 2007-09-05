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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.utils.generation.CodeGenerationUtils;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class ElementParameterParser {

    private ElementParameterParser() {
    }

    public static String getValue(final IElement node, final String text) {
        String newText = new String(""); //$NON-NLS-1$
        if (text == null) {
            return newText;
        }
        IElementParameter param;
        boolean end = false;

        for (int i = 0; i < node.getElementParameters().size() && !end; i++) {
            param = node.getElementParameters().get(i);
            if (text.indexOf(param.getVariableName()) != -1) {
                newText = getDisplayValue(param);
                end = true;
            }
        }
        return newText;
    }

    /**
     * Prefixes and suffixes a Java comment to match Java code problems with UI designer fields.
     * 
     * @param node
     * @param text
     * @param fieldName name of field declared in xml component file (or other value for a manual processing)
     * @return
     */
    public static String getValueWithUIFieldKey(final INode node, final String text, String fieldName) {
        String value = getValue(node, text);
        String key = CodeGenerationUtils.buildProblemKey(node.getUniqueName(), fieldName);
        return CodeGenerationUtils.buildJavaStartFieldKey(key) + value + CodeGenerationUtils.buildJavaEndFieldKey(key);
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
            param = element.getElementParameters().get(i);
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
                if ((((Integer) o) == -1) || (tmpParam.getListItemsValue().length == 0)) {
                    newLine.put(items[i], ""); //$NON-NLS-1$
                } else {
                    newLine.put(items[i], (String) tmpParam.getListItemsValue()[(Integer) o]);
                }
            } else {
                if (o instanceof String) {
                    newLine.put(items[i], (String) o);
                } else {
                    if (o instanceof Boolean) {
                        newLine.put(items[i], ((Boolean) o).toString());
                    } else {
                        newLine.put(items[i], ""); //$NON-NLS-1$
                    }
                }
            }
        }
        return newLine;
    }

    /**
     * Only work with one element.
     * 
     * @param element
     * @param text
     * @return
     */
    public static Object getObjectValueXML(final IElement element, final String text) {
        if (text == null) {
            return null;
        }
        IElementParameter param;

        for (int i = 0; i < element.getElementParameters().size(); i++) {
            param = element.getElementParameters().get(i);
            if (text.indexOf(param.getVariableName()) != -1) {
                if (param.getField() == EParameterFieldType.TABLE) {
                    return createTableValuesXML((List<Map<String, Object>>) param.getValue(), param);
                }
                return param.getValue();
            }
        }
        return null;
    }

    private static List<Map<String, String>> createTableValuesXML(final List<Map<String, Object>> paramValues,
            final IElementParameter param) {
        List<Map<String, String>> tableValues = new ArrayList<Map<String, String>>();
        for (Map<String, Object> currentLine : paramValues) {
            tableValues.add(copyLineXML(currentLine, param));
        }
        return tableValues;
    }

    private static Map<String, String> copyLineXML(Map<String, Object> currentLine, IElementParameter param) {
        Map<String, String> newLine = new HashMap<String, String>();
        // PTODO cantoine : check with Nico if cause trouble with others Components.
        String[] items = currentLine.keySet().toArray(new String[] {});
        // //{"QUERY"};
        for (int i = 0; i < items.length; i++) {
            Object o = currentLine.get(items[i]);
            if (o instanceof Integer) {
                IElementParameter tmpParam = (IElementParameter) param.getListItemsValue()[i];
                if ((((Integer) o) == -1) || (tmpParam.getListItemsValue().length == 0)) {
                    newLine.put(items[i], ""); //$NON-NLS-1$
                } else {
                    newLine.put(items[i], (String) tmpParam.getListItemsValue()[(Integer) o]);
                }
            } else {
                if (o instanceof String) {
                    newLine.put(items[i], (String) o);
                } else {
                    if (o instanceof Boolean) {
                        newLine.put(items[i], ((Boolean) o).toString());
                    } else {
                        newLine.put(items[i], "*** ERROR in Table ***"); //$NON-NLS-1$
                    }
                }
            }
        }
        return newLine;
    }

    public static String parse(final IElement element, final String text) {
        String newText = ""; //$NON-NLS-1$
        if ((element == null) || (text == null)) {
            return newText;
        }
        IElementParameter param;

        newText = text;
        for (int i = 0; i < element.getElementParameters().size(); i++) {
            param = element.getElementParameters().get(i);
            if (newText.contains(param.getVariableName())) {
                String value = getDisplayValue(param);
                newText = newText.replace(param.getVariableName(), value);
            }
        }
        return newText;
    }

    @SuppressWarnings("unchecked")//$NON-NLS-1$
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
            String[] items = param.getListItemsDisplayCodeName();
            String stringValues = "{"; //$NON-NLS-1$
            for (int i = 0; i < tableValues.size(); i++) {
                Map<String, Object> lineValues = tableValues.get(i);
                stringValues += "["; //$NON-NLS-1$
                for (int j = 0; j < items.length; j++) {

                    Object currentValue = lineValues.get(items[j]);
                    if (currentValue instanceof Integer) {
                        IElementParameter tmpParam = (IElementParameter) param.getListItemsValue()[j];
                        if (tmpParam.getListItemsDisplayName().length != 0) {
                            stringValues += tmpParam.getListItemsDisplayName()[(Integer) currentValue];
                        }
                    } else {
                        stringValues += currentValue;
                    }

                    if (j != (items.length - 1)) {
                        stringValues += ","; //$NON-NLS-1$
                    }
                }
                stringValues += "]"; //$NON-NLS-1$
                if (i != (tableValues.size() - 1)) {
                    stringValues += ","; //$NON-NLS-1$
                }
            }
            stringValues += "}"; //$NON-NLS-1$
            return stringValues;
        }
        return new String(""); //$NON-NLS-1$
    }

    /**
     * DOC qiang.zhang Comment method "getUNIQUENAME".
     * 
     * @param node
     * @return
     */
    public static String getUNIQUENAME(NodeType node) {
        List<ElementParameterType> parameters = node.getElementParameter();
        for (ElementParameterType elementParam : parameters) {
            if (elementParam.getName().equals("UNIQUE_NAME")) {
                return elementParam.getValue();
            }
        }
        return "";
    }

}
