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
package org.talend.core.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC ggu class global comment. Detailled comment
 */
public final class UpdateRunJobComponentContextHelper {

    private static final String PROCESS_TYPE_PROCESS = "PROCESS_TYPE_PROCESS";

    private static final String CONTEXTPARAMS = "CONTEXTPARAMS";

    private static final String PARAM_NAME_COLUMN = "PARAM_NAME_COLUMN";

    private static final String TRUN_JOB = "tRunJob";

    public static synchronized void updateOpenedJobRunJobComponentReference(final List<IProcess> openedProcesses,
            final Map<String, String> nameMap, final String refJobName) {
        if (openedProcesses == null || refJobName == null || nameMap == null || nameMap.isEmpty()) {
            return;
        }
        for (IProcess process : openedProcesses) {
            if (process.getLabel().equals(refJobName)) {
                // ignore self
                continue;
            }
            for (INode node : foundRunJobNode(process)) {
                IElementParameter eleParam = node.getElementParameter(PROCESS_TYPE_PROCESS);
                // found type
                if (eleParam != null && refJobName.equals(eleParam.getValue())) {
                    IElementParameter contextParam = node.getElementParameter(CONTEXTPARAMS);
                    if (contextParam != null) {
                        List<Map> valuesList = (List<Map>) contextParam.getValue();
                        for (Map valueMap : valuesList) {
                            Object obj = valueMap.get(PARAM_NAME_COLUMN);
                            if (obj != null && obj instanceof String) {
                                String newName = foundNewName(nameMap, (String) obj);
                                // found renamed parameter
                                if (newName != null) {
                                    valueMap.put(PARAM_NAME_COLUMN, newName);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private static List<INode> foundRunJobNode(IProcess process) {
        List<INode> matchingNodes = new ArrayList<INode>();
        for (INode node : (List<INode>) process.getGeneratingNodes()) {
            if (TRUN_JOB.equals(node.getComponent().getName())) {
                matchingNodes.add(node);
            }
        }
        return matchingNodes;
    }

    public static synchronized void updateItemRunJobComponentReference(final IProxyRepositoryFactory factory,
            final Map<String, String> nameMap, final String refJobName) throws PersistenceException {
        if (refJobName == null || nameMap == null || nameMap.isEmpty()) {
            return;
        }

        List<IRepositoryObject> repositoryObjectList = factory.getAll(ERepositoryObjectType.PROCESS, true);
        for (IRepositoryObject rObject : repositoryObjectList) {
            List<IRepositoryObject> allVersion = factory.getAllVersion(rObject.getId());
            for (IRepositoryObject object : allVersion) {
                Item item = object.getProperty().getItem();
                if (item instanceof ProcessItem) {
                    ProcessItem processItem = (ProcessItem) item;
                    if (processItem.getProperty().getLabel().equals(refJobName)) {
                        // ignore self
                        continue;
                    }
                    boolean modified = false;
                    for (NodeType foundNode : foundRunJobNodeType(processItem)) {
                        // found tRunJob node
                        boolean found = false;
                        for (ElementParameterType paramType : (List<ElementParameterType>) foundNode.getElementParameter()) {
                            if (PROCESS_TYPE_PROCESS.equals(paramType.getName()) && paramType.getValue().equals(refJobName)) {
                                found = true;
                                continue;
                            }
                            // found current node reference
                            if (found && paramType.getName().equals(CONTEXTPARAMS)) {
                                for (ElementValueType eleValueType : (List<ElementValueType>) paramType.getElementValue()) {
                                    if (eleValueType.getElementRef().equals(PARAM_NAME_COLUMN)) {
                                        String newName = foundNewName(nameMap, eleValueType.getValue());
                                        if (newName != null) {
                                            eleValueType.setValue(newName);
                                            modified = true;
                                        }
                                    }
                                }
                            }

                        }
                    }

                    if (modified) {
                        factory.save(processItem);
                    }
                }
            }
        }

    }

    private static List<NodeType> foundRunJobNodeType(ProcessItem processItem) {
        List<NodeType> matchingNodes = new ArrayList<NodeType>();
        for (Object nodeObj : processItem.getProcess().getNode()) {
            NodeType nodeType = (NodeType) nodeObj;
            if (TRUN_JOB.equals(nodeType.getComponentName())) {
                matchingNodes.add(nodeType);
            }
        }
        return matchingNodes;
    }

    private static String foundNewName(Map<String, String> nameMap, String oldName) {
        if (oldName == null || nameMap == null) {
            return null;
        }
        for (String newName : nameMap.keySet()) {
            if (nameMap.get(newName).equals(oldName) && !newName.equals(oldName)) {
                return newName;
            }
        }
        return null;
    }
}
