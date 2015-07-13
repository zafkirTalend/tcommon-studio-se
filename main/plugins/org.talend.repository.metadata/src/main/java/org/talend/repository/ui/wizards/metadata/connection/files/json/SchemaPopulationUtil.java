// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards.metadata.connection.files.json;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.talend.commons.exception.CommonExceptionHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

/**
 * created by cmeng on Jul 1, 2015 Detailled comment
 *
 */
public class SchemaPopulationUtil {

    public static String getFilteredJsonPath(String jsonPath) {
        if (jsonPath == null || jsonPath.isEmpty()) {
            return jsonPath;
        }
        return jsonPath.replaceAll("\\.@", ""); //$NON-NLS-1$//$NON-NLS-2$
    }

    public static JsonTreeNode getJsonTreeNodeByJsonPath(Object[] treeNodes, String jsonPath) {
        if (treeNodes == null || treeNodes.length <= 0 || jsonPath == null) {
            return null;
        }
        if (jsonPath.isEmpty()) {
            return null;
        }
        for (Object childObj : treeNodes) {
            if (!(childObj instanceof JsonTreeNode)) {
                continue;
            }
            JsonTreeNode childJsonTreeNode = (JsonTreeNode) childObj;
            String treeNodePath = childJsonTreeNode.getJsonPath();
            if (jsonPath.equals(treeNodePath)) {
                return childJsonTreeNode;
            } else if (jsonPath.startsWith(treeNodePath + ".")) {
                return getJsonTreeNodeByJsonPath(childJsonTreeNode.getChildren(), jsonPath);
            } else {
                continue;
            }
        }
        return null;
    }

    public static JsonTreeNode getSchemaTree(File jsonFile, int numberOfElementsAccessiable) {
        JsonTreeNode jsonTreeNode = null;
        try {
            ObjectMapper objMapper = new ObjectMapper();
            JsonNode jsonNode = objMapper.readTree(jsonFile);
            jsonTreeNode = new JsonTreeNode();
            jsonTreeNode.addValue(jsonNode);
            jsonTreeNode.setLabel("$"); //$NON-NLS-1$
            jsonTreeNode.setJsonPath("$"); //$NON-NLS-1$
            fetchTreeNode(jsonTreeNode, numberOfElementsAccessiable - 1);
        } catch (IOException e) {
            CommonExceptionHandler.process(e);
        }
        return jsonTreeNode;
    }

    public static JsonTreeNode getSchemaTree(String jsonString, int numberOfElementsAccessiable) {
        JsonTreeNode jsonTreeNode = null;
        try {
            ObjectMapper objMapper = new ObjectMapper();
            JsonNode jsonNode = objMapper.readTree(jsonString);
            jsonTreeNode = new JsonTreeNode();
            jsonTreeNode.addValue(jsonNode);
            jsonTreeNode.setLabel("$"); //$NON-NLS-1$
            jsonTreeNode.setJsonPath("$"); //$NON-NLS-1$
            fetchTreeNode(jsonTreeNode, numberOfElementsAccessiable - 1);
        } catch (IOException e) {
            CommonExceptionHandler.process(e);
        }
        return jsonTreeNode;
    }

    public static void fetchTreeNode(JsonTreeNode parentNode, int numberOfElementsAccessiable) {
        if (parentNode == null || numberOfElementsAccessiable == 0 || !parentNode.hasChildren()) {
            return;
        }
        Set<JsonNode> valueSet = parentNode.getValues();
        if (valueSet == null || valueSet.isEmpty()) {
            return;
        }

        Iterator<JsonNode> valueIter = valueSet.iterator();
        while (valueIter.hasNext()) {
            JsonNode jsonNode = valueIter.next();
            if (jsonNode.isArray()) {
                Iterator<JsonNode> childrenIter = jsonNode.iterator();
                Set<JsonNode> arraySet = new HashSet<JsonNode>();
                while (childrenIter.hasNext()) {
                    JsonNode childJsonNode = childrenIter.next();
                    arraySet.add(childJsonNode);
                }
                fetchArrayTreeNode(parentNode, arraySet, numberOfElementsAccessiable);
            } else {
                Iterator<Entry<String, JsonNode>> childrenIter = jsonNode.fields();
                while (childrenIter.hasNext()) {
                    Entry<String, JsonNode> childEntry = childrenIter.next();
                    JsonTreeNode childJsonTreeNode = new JsonTreeNode();
                    String label = childEntry.getKey();
                    JsonNode value = childEntry.getValue();
                    if (value != null && value.isArray()) {
                        label = label + "[*]"; //$NON-NLS-1$
                    }
                    String jsonPath = parentNode.getJsonPath() + "." + label; //$NON-NLS-1$
                    childJsonTreeNode.setLabel(label);
                    childJsonTreeNode.addValue(value);
                    childJsonTreeNode.setJsonPath(jsonPath);
                    parentNode.addChild(childJsonTreeNode);
                    if (childJsonTreeNode.hasChildren()) {
                        fetchTreeNode(childJsonTreeNode, numberOfElementsAccessiable - 1);
                    }
                }
            }
        }

    }

    public static boolean hasChildren(JsonNode jsonNode) {
        if (jsonNode == null) {
            return false;
        }
        // jsonNode.isContainerNode();// not test
        boolean hasChildren = false;
        JsonNodeType jsonNodeType = jsonNode.getNodeType();
        switch (jsonNodeType) {
        case ARRAY:
        case OBJECT:
        case POJO:
            hasChildren = true;
            break;
        default:
            hasChildren = false;
            break;
        }
        return hasChildren;
    }

    private static void fetchArrayTreeNode(JsonTreeNode parentNode, Set<JsonNode> arraySet, int numberOfElementsAccessiable) {
        if (parentNode == null || numberOfElementsAccessiable == 0 || arraySet == null || arraySet.isEmpty()) {
            return;
        }
        Iterator<JsonNode> iter = arraySet.iterator();
        while (iter.hasNext()) {
            fetchArrayTreeNode(parentNode, iter.next());
        }

        if (numberOfElementsAccessiable - 1 == 0) {
            return;
        }

        Object[] children = parentNode.getChildren();
        if (children != null) {
            for (Object child : children) {
                if (child instanceof JsonTreeNode) {
                    fetchTreeNode((JsonTreeNode) child, numberOfElementsAccessiable - 1);
                }
            }
        }
    }

    private static void fetchArrayTreeNode(JsonTreeNode parentNode, JsonNode jsonNode) {
        if (parentNode == null || jsonNode == null) {
            return;
        }

        Iterator<Entry<String, JsonNode>> childrenIter = jsonNode.fields();
        while (childrenIter.hasNext()) {
            Entry<String, JsonNode> childEntry = childrenIter.next();
            String name = childEntry.getKey();
            JsonTreeNode childJsonTreeNode = parentNode.getFromValueMap(name);
            if (childJsonTreeNode == null) {
                childJsonTreeNode = new JsonTreeNode();
                childJsonTreeNode.setLabel(name);
                JsonNode value = childEntry.getValue();
                String jsonPath = parentNode.getJsonPath() + "." + name; //$NON-NLS-1$
                childJsonTreeNode.addValue(value);
                childJsonTreeNode.setJsonPath(jsonPath);
                parentNode.addChild(childJsonTreeNode);
                parentNode.putValueMap(name, childJsonTreeNode);
            } else {
                childJsonTreeNode.addValue(childEntry.getValue());
            }

        }
    }
}
