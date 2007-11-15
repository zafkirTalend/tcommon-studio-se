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
package org.talend.core.model.components.conversions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.talend.core.model.components.ComponentUtilities;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;

/**
 * DOC s class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2007-10-25 17:06:40 +0000 xzhang $
 */
public class UpdateMappingFortAdvancedXMLConversion implements IComponentConversion {

    private static final String OLD_ROOT_TABLE_NAME = "ROOT_TAGS";

    private static final String OLD_GROUPING_TABLE_NAME = "GROUPING";

    private static final String OLD_MAPPING_TABLE_NAME = "MAPPING";

    private static final String OLD_TAG = "TAG";

    private static final String OLD_LABEL = "LABEL";

    private static final String OLD_COLUMN = "COLUMN";

    private static final String OLD_ATTRIBUTE = "ATTRIBUTE";

    private static final String OLD_DEPTH = "DEPTH";

    private static final String NEW_ROOT_NAME = "ROOT";

    private static final String NEW_GROUP_NAME = "GROUP";

    private static final String NEW_LOOP_NAME = "LOOP";

    private static final String NEW_PATH = "PATH";

    private static final String NEW_COLUMN = "COLUMN";

    private static final String NEW_ATTRIBUTE = "ATTRIBUTE";

    private static final String NEW_VALUE = "VALUE";

    public void transform(NodeType node) {
        ElementParameterType eleParaRoot = ComponentUtilities.getNodeProperty(node, OLD_ROOT_TABLE_NAME);
        ElementParameterType eleParaGroup = ComponentUtilities.getNodeProperty(node, OLD_GROUPING_TABLE_NAME);
        ElementParameterType eleParaMap = ComponentUtilities.getNodeProperty(node, OLD_MAPPING_TABLE_NAME);
        if (eleParaRoot == null || eleParaGroup == null || eleParaMap == null) {
            return;
        }
        
        StringBuffer path = new StringBuffer();

        // count the values of "ROOT_TAGS" to "ROOT".
        EList elist = eleParaRoot.getElementValue();
        List<ElementValueType> newValueListForRoot = new ArrayList<ElementValueType>();
        for (Object value : elist) {
            ElementValueType eValue = (ElementValueType) value;
            if (eValue.getElementRef().equals(OLD_TAG)) {
                path.append("/").append(eValue.getValue());
                addOneRecord(newValueListForRoot, path.toString(), "", "", "");
            }
        }

        // count the values of "GROUPING" to "GROUP".
        elist = eleParaGroup.getElementValue();
        List<ElementValueType> newValueListForGroup = new ArrayList<ElementValueType>();
        for (int i = 0; i < elist.size(); i += 3) {
            String pathValue = "";
            String columnValue = "";
            String attributeValue = "";
            for (Object value : elist.subList(i, i + 3)) {
                ElementValueType eValue = (ElementValueType) value;
                if (eValue.getElementRef().equals(OLD_LABEL)) {
                    pathValue = eValue.getValue();
                } else if (eValue.getElementRef().equals(OLD_COLUMN)) {
                    columnValue = eValue.getValue();
                } else if (eValue.getElementRef().equals(OLD_ATTRIBUTE)) {
                    attributeValue = eValue.getValue();
                }
            }
            if (attributeValue.equals("false")) {
                pathValue = path.append("/").append(pathValue).toString();
            }
            addOneRecord(newValueListForGroup, pathValue, columnValue, "", attributeValue);
        }

        // count the values of "MAPPING" to "LOOP".
        elist = eleParaMap.getElementValue();
        List<ElementValueType> newValueListForLoop = new ArrayList<ElementValueType>();
        ArrayList<String> pathStrs = new ArrayList<String>();
        for (int i = 0; i < elist.size(); i += 4) {
            String pathValue = "";
            String columnValue = "";
            String attributeValue = "";
            String depthValue = "";
            // loop the elist in 4, it's a record of "MAPPING"
            for (Object value : elist.subList(i, i + 4)) {
                ElementValueType eValue = (ElementValueType) value;
                if (eValue.getElementRef().equals(OLD_LABEL)) {
                    pathValue = eValue.getValue();
                } else if (eValue.getElementRef().equals(OLD_COLUMN)) {
                    columnValue = eValue.getValue();
                } else if (eValue.getElementRef().equals(OLD_ATTRIBUTE)) {
                    attributeValue = eValue.getValue();
                } else if (eValue.getElementRef().equals(OLD_DEPTH)) {
                    depthValue = eValue.getValue();
                }
            }
            if (attributeValue.equals("false")) {
                pathValue = getPathByDepth(pathStrs, pathValue, Integer.valueOf(depthValue));
                pathValue = path.toString() + pathValue;
            }
            addOneRecord(newValueListForLoop, pathValue, columnValue, "", attributeValue);
        }
        
        // add new Property
        ComponentUtilities.addNodeProperty(node, NEW_ROOT_NAME, "TABLE");
        ComponentUtilities.setNodeProperty(node, NEW_ROOT_NAME, newValueListForRoot);
        ComponentUtilities.addNodeProperty(node, NEW_GROUP_NAME, "TABLE");
        ComponentUtilities.setNodeProperty(node, NEW_GROUP_NAME, newValueListForGroup);
        ComponentUtilities.addNodeProperty(node, NEW_LOOP_NAME, "TABLE");
        ComponentUtilities.setNodeProperty(node, NEW_LOOP_NAME, newValueListForLoop);

        // remove old table
        ComponentUtilities.removeNodeProperty(node, OLD_ROOT_TABLE_NAME);
        ComponentUtilities.removeNodeProperty(node, OLD_GROUPING_TABLE_NAME);
        ComponentUtilities.removeNodeProperty(node, OLD_MAPPING_TABLE_NAME);
    }

    /**
     * change the depth to path DOC s Comment method "getPathByDepth".
     * 
     * @param pathStrs store the pathValue
     * @param pathValue the LABEL of MAPPING table
     * @param depthValue the DEPTH of MAPPING table
     * @return the path in MAPPING table
     */
    private String getPathByDepth(ArrayList<String> pathStrs, String pathValue, int depthValue) {
        String result = "";
        if (depthValue <= pathStrs.size()) {
            pathStrs.set(depthValue - 1, pathValue);
        } else {
            pathStrs.add(pathValue);
        }

        for (int i = 0; i < depthValue; i++) {
            result += "/" + pathStrs.get(i);
        }
        return result;
    }

    /**
     * 
     * DOC s Comment method "addOneRecord".
     * 
     * @param values
     * @param pathValue
     * @param columnValue
     * @param value
     * @param attributeValue
     */
    private void addOneRecord(List<ElementValueType> values, String pathValue, String columnValue, String value,
            String attributeValue) {
        ElementValueType eValue = TalendFileFactory.eINSTANCE.createElementValueType();
        eValue.setElementRef(NEW_PATH);
        eValue.setValue(pathValue);
        values.add(eValue);

        eValue = TalendFileFactory.eINSTANCE.createElementValueType();
        eValue.setElementRef(NEW_COLUMN);
        eValue.setValue(columnValue);
        values.add(eValue);

        eValue = TalendFileFactory.eINSTANCE.createElementValueType();
        eValue.setElementRef(NEW_VALUE);
        eValue.setValue(value);
        values.add(eValue);

        eValue = TalendFileFactory.eINSTANCE.createElementValueType();
        eValue.setElementRef(NEW_ATTRIBUTE);
        eValue.setValue(attributeValue);
        values.add(eValue);
    }
}
