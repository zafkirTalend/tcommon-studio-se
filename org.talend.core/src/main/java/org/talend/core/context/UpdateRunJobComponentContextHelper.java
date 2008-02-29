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
import java.util.Set;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
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

    private static final String PROCESS_TYPE_PROCESS = "PROCESS_TYPE_PROCESS"; //$NON-NLS-1$

    private static final String CONTEXTPARAMS = "CONTEXTPARAMS"; //$NON-NLS-1$

    private static final String PARAM_NAME_COLUMN = "PARAM_NAME_COLUMN"; //$NON-NLS-1$

    private static final String PARAM_VALUE_COLUMN = "PARAM_VALUE_COLUMN"; //$NON-NLS-1$

    private static final String TRUN_JOB = "tRunJob"; //$NON-NLS-1$

    public static synchronized void updateOpenedJobRunJobComponentReference(final List<IProcess> openedProcesses,
            final Map<String, String> nameMap, final String refJobName, final Set<String> varNameSet) {
        if (openedProcesses == null || refJobName == null) {
            return;
        }
        for (IProcess process : openedProcesses) {
            if (process.getLabel().equals(refJobName)) {
                // ignore self
                continue;
            }
            boolean changed = false;
            for (INode node : foundRunJobNode(process)) {
                IElementParameter eleParam = node.getElementParameter(PROCESS_TYPE_PROCESS);
                // found type
                if (eleParam != null && refJobName.equals(eleParam.getValue())) {
                    IElementParameter contextParam = node.getElementParameter(CONTEXTPARAMS);
                    if (contextParam != null) {
                        List<Map> valuesList = (List<Map>) contextParam.getValue();
                        List<Map> removedMap = new ArrayList<Map>();

                        for (Map valueMap : valuesList) {
                            Object obj = valueMap.get(PARAM_NAME_COLUMN);
                            if (obj != null && obj instanceof String) {
                                String oldName = (String) obj;
                                String newName = foundNewName(nameMap, oldName);
                                // found renamed parameter
                                if (newName != null) {
                                    valueMap.put(PARAM_NAME_COLUMN, newName);
                                    changed = true;
                                } else {
                                    // deleted parameter update, not existed in reference job context
                                    if (varNameSet != null && !varNameSet.contains(oldName)) {
                                        removedMap.add(valueMap);
                                    }
                                }
                            }
                        }
                        if (!removedMap.isEmpty()) {
                            for (Map valueMap : removedMap) {
                                valuesList.remove(valueMap);
                                changed = true;
                            }
                        }
                    }
                }
            }
            // update the job state
            if (changed && process instanceof IProcess2) {
                CommandStack commandStack = ((IProcess2) process).getCommandStack();
                if (commandStack != null) {
                    commandStack.execute(new Command() {
                    });
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
            final Map<String, String> nameMap, final String refJobName, final Set<String> varNameSet) throws PersistenceException {
        if (factory == null || refJobName == null) {
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
                    modified = updateRunJobComponent(processItem, nameMap, refJobName, varNameSet);

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

    private static boolean updateRunJobComponent(final ProcessItem processItem, final Map<String, String> nameMap,
            final String refJobName, final Set<String> varNameSet) {

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
                    List<ElementValueType> eleValueTypeList = paramType.getElementValue();
                    List<ElementValueType> movedRecord = new ArrayList<ElementValueType>();

                    for (int i = 0; i < eleValueTypeList.size(); i++) {
                        ElementValueType eleValueType = eleValueTypeList.get(i);
                        if (eleValueType.getElementRef().equals(PARAM_NAME_COLUMN)) {
                            String newName = foundNewName(nameMap, eleValueType.getValue());
                            if (newName != null) {
                                // renamed parameter update
                                eleValueType.setValue(newName);
                                modified = true;
                            } else {
                                // deleted parameter update, not existed in reference job context
                                if (varNameSet != null && !varNameSet.contains(eleValueType.getValue())) {
                                    ElementValueType valueType = eleValueTypeList.get(i + 1);
                                    if (valueType != null && valueType.getElementRef().equals(PARAM_VALUE_COLUMN)) {
                                        movedRecord.add(eleValueType);
                                        movedRecord.add(valueType);
                                        modified = true;
                                    }
                                }
                            }
                        }
                    }
                    if (!movedRecord.isEmpty()) {
                        for (ElementValueType eleValueType : movedRecord) {
                            eleValueTypeList.remove(eleValueType);
                        }
                    }
                }
            }
        }
        return modified;
    }

}
