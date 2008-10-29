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
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.utils.PerlVarParserUtils;
import org.talend.core.model.utils.SQLPatternUtils;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.runprocess.ItemCacheManager;
import org.talend.repository.model.IProxyRepositoryFactory;

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
        IElementParameter param = null;
        boolean end = false;

        List<IElementParameter> params = (List<IElementParameter>) node.getElementParametersWithChildrens();
        for (int i = 0; i < params.size() && !end; i++) {
            param = params.get(i);
            if (text.indexOf(param.getVariableName()) != -1) {
                newText = getDisplayValue(param);
                end = true;
            }
        }

        // see feature 3725 replace tMsgBox MESSAGE parameter
        if (node instanceof INode) {
            INode valueNode = (INode) node;
            /*
             * Apply to all components in Perl mode
             */
            if (isPerlProject()) {
                return PerlVarParserUtils.findAndReplacesAll(newText, valueNode);
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
     * DOC xtan Comment method "getValueWithJavaType".
     * <p>
     * if there want a char ',', but user input in GUI like this ",", this method just deal with this case.
     * </p>
     * 
     * @param node
     * @param text
     * @param javaType
     * @return
     */
    public static String getValueWithJavaType(final INode node, final String text, JavaType javaType) {
        String value = getValue(node, text);

        // the default return old value
        String result = value;

        String trimValue = null;

        if (value == null) {
            return null;
        } else {
            trimValue = value.trim();
        }

        if (javaType == JavaTypesManager.CHARACTER) {
            // 5 cases: null(no this parameter in shadow process), String.length()=0(nothing in GUI), "", '', null(this
            // means null directly in GUI)
            if (trimValue.equals("") || trimValue.equals("\"\"") || trimValue.equals("''") || trimValue.equals("null")) {
                return null;
            } else if (trimValue.length() >= 3 && trimValue.startsWith("\"") && trimValue.endsWith("\"")) {
                // "a", "ab", "a, 'a', 'ab', 'a, 93, '\042', '\u0022', '\'', "\u0022"

                String substring = trimValue.substring(1, trimValue.length() - 1);

                if (substring.length() == 1 && (substring.charAt(0) == '\\' || substring.charAt(0) == '\'')) {
                    // support to input like that: "\", "'"
                    result = "'" + "\\" + substring + "'";
                } else {
                    result = "'" + substring + "'";
                }
            }
        }

        return result;
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
            if (text.indexOf(param.getVariableName()) != -1
                    || (param.getVariableName() != null && param.getVariableName().contains(text))) {
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
        if (paramValues != null) {
            for (Map<String, Object> currentLine : paramValues) {
                tableValues.add(copyLine(currentLine, param));
            }
        }
        return tableValues;
    }

    private static Map<String, String> copyLine(Map<String, Object> currentLine, IElementParameter param) {
        Map<String, String> newLine = new HashMap<String, String>();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
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
                    if (param.getName().equals("SQLPATTERN_VALUE")) {
                        SQLPatternItem item = SQLPatternUtils.getItemFromCompoundId(param.getElement(), ((String) o));
                        if (item != null) {
                            newLine.put(items[i], new String(item.getContent().getInnerContent()));
                        } else {
                            newLine.put(items[i], "");
                        }
                    } else {
                        newLine.put(items[i], (String) o);
                    }
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

    /**
     * 
     * If a database componenet use an existing connection, its label may replace with variable from the existing
     * connection not the component variable. see bug 0005456: Label Format __DBNAME__ not valid when using existing
     * connection
     * 
     * @param text String that contains variables which need to replace
     * @param variableMap A map contains variable name and IElementParameter in a pair
     * @return
     */
    public static String replaceWithExistingConnection(String text, Map<String, IElementParameter> variableMap) {
        if (text == null) {
            return "";
        }
        String newText = text;

        for (String var : variableMap.keySet()) {
            if (newText.contains(var)) {
                IElementParameter param = variableMap.get(var);
                String value = ElementParameterParser.getDisplayValue(param);
                newText = newText.replace(param.getVariableName(), value);
            }
        }
        return newText;

    }

    @SuppressWarnings("unchecked")
    private static String getDisplayValue(final IElementParameter param) {
        Object value = param.getValue();

        if (value instanceof String) {

            if (param.getName().equals("PROCESS_TYPE_VERSION") && value.equals(ItemCacheManager.LATEST_VERSION)) {
                String jobId = (String) param.getParentParameter().getChildParameters().get("PROCESS_TYPE_PROCESS").getValue();
                ProcessItem processItem = ItemCacheManager.getProcessItem(jobId);
                if (processItem == null) {
                    return "";
                }
                return processItem.getProperty().getVersion();
            }
            return (String) value;
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

    /**
     * 
     * DOC YeXiaowei Comment method "isPerlProject".
     * 
     * @return
     */
    public static boolean isPerlProject() {

        ECodeLanguage language = LanguageManager.getCurrentLanguage();

        switch (language) {
        case PERL:
            return true;
        default: // PERL
            return false;
        }
    }

}
