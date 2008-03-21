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
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.utils.generation.CodeGenerationUtils;
import org.talend.core.model.properties.ProcessItem;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.runprocess.ProcessorUtilities;

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

        List<IElementParameter> params = (List<IElementParameter>) node.getElementParametersWithChildrens();
        for (int i = 0; i < params.size() && !end; i++) {
            param = params.get(i);
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

        List<IElementParameter> params = (List<IElementParameter>) element.getElementParametersWithChildrens();
        for (int i = 0; i < params.size(); i++) {
            param = params.get(i);
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
            final String value2 = (String) value;

            if ("__PROCESS_TYPE_PROCESS__".indexOf(param.getVariableName()) != -1) { //$NON-NLS-1$
                ProcessItem item = ProcessorUtilities.getProcessItemById(value2);
                if (item != null) {
                    return item.getProperty().getLabel();
                } else {
                    return ""; //$NON-NLS-1$
                }

            }
            return value2;
        }
        if (param.getField() == EParameterFieldType.RADIO || param.getField() == EParameterFieldType.CHECK
                || param.getField() == EParameterFieldType.AS400_CHECK) {
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
            if (elementParam.getName().equals("UNIQUE_NAME")) { //$NON-NLS-1$
                return elementParam.getValue();
            }
        }
        return ""; //$NON-NLS-1$
    }

}
