// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui;

import java.util.List;

import org.talend.core.IService;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.properties.Property;

/**
 * cli class global comment. Detailled comment
 */
public interface IJobletProviderService extends IService {

    public boolean isJobletComponent(INode node);

    public Property getJobletComponentItem(INode node);

    public void reloadJobletProcess(INode node);

    public boolean isTriggerNode(INode node);

    public boolean isTriggerInputNode(INode node);

    public boolean canConnectTriggerNode(INode node, EConnectionType connType);

    public List<EConnectionType> getTriggerNodeSupportConnTypes(INode triggerNode);

    public List<INode> getTriggerNodes(INode jobletNode);

    public EConnectionType getTriggerNodeConnType(INode triggerNode);

    public boolean isBuiltTriggerConnector(INode jobletNode, INodeConnector connector);

    public List<INodeConnector> getTriggerBuiltConnectors(INode jobletNode, EConnectionType lineStyle, boolean input);

    public List<INodeConnector> getFreeTriggerBuiltConnectors(INode jobletNode, EConnectionType lineStyle, boolean input);

    public List<INode> getConnNodesForInputTrigger(INode jobletNode, IElementParameter param);

    public void upateJobletComonentList(INode jobletNode);

    public void loadComponentsFromProviders();

    public IComponent setPropertyForJobletComponent(String id, String version);
}
