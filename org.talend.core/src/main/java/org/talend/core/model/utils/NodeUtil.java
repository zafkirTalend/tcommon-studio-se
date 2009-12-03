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
package org.talend.core.model.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;

/**
 * DOC xtan class global comment. Detailled comment <br/>
 * 
 */
public class NodeUtil {

    /**
     * DOC sort the outgoingconnections to make sure the first connection is EConnectionType.FLOW_MAIN or
     * EConnectionType.FLOW_REF<br/>
     * <p>
     * bug:9363, if a component have 2 output links, one is EConnectionType.FLOW_MAIN, one is EConnectionType.FLOW_REF,
     * make sure EConnectionType.FLOW_REF before EConnectionType.FLOW_MAIN(REJECT)
     * </p>
     * 
     * @param node
     * @return List<? extends IConnection>
     */
    public static List<? extends IConnection> getOutgoingSortedConnections(INode node) {

        List<IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            conns = new ArrayList<IConnection>(outgoingConnections);
            Collections.sort(conns, new Comparator<IConnection>() {

                public int compare(IConnection connection1, IConnection connection2) {

                    EConnectionType lineStyle = connection1.getLineStyle();
                    EConnectionType lineStyle2 = connection2.getLineStyle();

                    // 1. check EConnectionType.FLOW_REF
                    if (lineStyle == EConnectionType.FLOW_REF) {
                        return -1;
                    }
                    if (lineStyle2 == EConnectionType.FLOW_REF) {
                        return 1;
                    }

                    // 2. check EConnectionType.FLOW_MAIN, EConnectionType.FLOW_MERGE
                    if (lineStyle == EConnectionType.FLOW_MAIN || lineStyle == EConnectionType.FLOW_MERGE) {
                        return -1;
                    }
                    if (lineStyle2 == EConnectionType.FLOW_MAIN || lineStyle2 == EConnectionType.FLOW_MERGE) {
                        return 1;
                    }

                    // 3. check others case
                    if (!(lineStyle == EConnectionType.FLOW_MAIN || lineStyle == EConnectionType.FLOW_MERGE || lineStyle == EConnectionType.FLOW_REF)) {
                        return -1;
                    }
                    if (!(lineStyle2 == EConnectionType.FLOW_MAIN || lineStyle2 == EConnectionType.FLOW_MERGE || lineStyle2 == EConnectionType.FLOW_REF)) {
                        return 1;
                    }

                    return 0;

                }
            });
        }

        return conns;
    }

    /**
     * DOC get the EConnectionType.FLOW_MAIN or EConnectionType.FLOW_REF out goning Connections<br/>
     * 
     * @param node
     * @return List<? extends IConnection>
     */
    public static List<? extends IConnection> getMainOutgoingConnections(INode node) {
        List<IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < outgoingConnections.size(); i++) {

                IConnection connection = outgoingConnections.get(i);
                if (connection.getLineStyle() == EConnectionType.FLOW_MAIN
                        || connection.getLineStyle() == EConnectionType.FLOW_REF) {

                    conns.add(connection);

                }
            }
        }
        return conns;
    }

    public static List<? extends IConnection> getOutgoingConnections(INode node, EConnectionType connectionType) {
        List<IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < outgoingConnections.size(); i++) {
                IConnection connection = outgoingConnections.get(i);
                if (connection.getLineStyle() == connectionType) {
                    conns.add(connection);
                }
            }
        }
        return conns;
    }

    public static List<? extends IConnection> getIncomingConnections(INode node, EConnectionType connectionType) {
        List<IConnection> conns = null;

        List<? extends IConnection> incomingConnections = node.getIncomingConnections();
        if (incomingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < incomingConnections.size(); i++) {
                IConnection connection = incomingConnections.get(i);
                if (connection.getLineStyle() == connectionType) {
                    conns.add(connection);
                }
            }
        }
        return conns;
    }

    public static List<? extends IConnection> getOutgoingConnections(INode node, String connectorName) {
        List<IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < outgoingConnections.size(); i++) {

                IConnection connection = outgoingConnections.get(i);
                if (connectorName.equals(connection.getConnectorName())) {
                    conns.add(connection);
                }
            }
        }
        return conns;
    }

    /**
     * 
     * wzhang Comment method "getIncomingConnections".
     * 
     * @param node
     * @param category
     * @return
     */
    public static List<? extends IConnection> getIncomingConnections(INode node, int category) {
        List<IConnection> conns = null;

        List<? extends IConnection> incomingConnections = node.getIncomingConnections();
        if (incomingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < incomingConnections.size(); i++) {

                IConnection connection = incomingConnections.get(i);
                if (connection.getLineStyle().hasConnectionCategory(category)) {
                    conns.add(connection);
                }
            }
        }
        return conns;
    }

    public static List<? extends IConnection> getOutgoingConnections(INode node, int category) {
        List<IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < outgoingConnections.size(); i++) {

                IConnection connection = outgoingConnections.get(i);
                if (connection.getLineStyle().hasConnectionCategory(category)) {
                    conns.add(connection);
                }
            }
        }
        return conns;
    }

    public static List<IMetadataTable> getIncomingMetadataTable(INode node, int category) {
        List<IMetadataTable> metadatas = new ArrayList<IMetadataTable>();

        List<? extends IConnection> incomingConnections = node.getIncomingConnections();
        if (incomingConnections != null) {
            for (int i = 0; i < incomingConnections.size(); i++) {

                IConnection connection = incomingConnections.get(i);
                if (connection.getLineStyle().hasConnectionCategory(category)) {
                    metadatas.add(connection.getMetadataTable());
                }
            }
        }
        return metadatas;
    }

    public static List<? extends IConnection> getIncomingConnections(INode node, String connectorName) {
        List<IConnection> conns = null;

        List<? extends IConnection> incomingConnections = node.getIncomingConnections();
        if (incomingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int i = 0; i < incomingConnections.size(); i++) {

                IConnection connection = incomingConnections.get(i);
                if (connectorName.equals(connection.getConnectorName())) {
                    conns.add(connection);
                }
            }
        }
        return conns;
    }

    public static boolean checkConnectionAfterNode(INode node, EConnectionType connectionType, List<INode> checkedNodes) {
        // fix bug 0004935: Error on job save
        if (checkedNodes.contains(node)) {
            return false;
        } else {
            checkedNodes.add(node);
        }

        boolean result = false;
        List<? extends IConnection> onErrorConns = getOutgoingConnections(node, EConnectionType.ON_COMPONENT_ERROR);
        if (onErrorConns == null || onErrorConns.size() == 0) {
            List<? extends IConnection> conns = getOutgoingSortedConnections(node);
            if (conns != null && conns.size() > 0) {
                for (IConnection conn : conns) {
                    result = checkConnectionAfterNode(conn.getTarget(), EConnectionType.ON_COMPONENT_ERROR, checkedNodes);
                    if (result) {
                        break;
                    }
                }
            } else {
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }

    public static boolean checkComponentErrorConnectionAfterNode(INode node) {
        List<INode> checkedNodes = new ArrayList<INode>();
        return checkConnectionAfterNode(node, EConnectionType.ON_COMPONENT_ERROR, checkedNodes);
    }

    /**
     * DOC xtan
     * <p>
     * InLineJob means all nodes after a iterate link(The nodes will execute many times on every iterate).
     * </p>
     * Notice: The search method don't consider the second branch of the tUnite, but it is ok.
     * 
     * @param node
     * @return
     */
    public static Set<? extends IConnection> getAllInLineJobConnections(INode node) {
        Set<IConnection> conns = new HashSet<IConnection>();

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {

            conns.addAll(outgoingConnections); // add all

            for (int i = 0; i < outgoingConnections.size(); i++) {

                IConnection connection = outgoingConnections.get(i);
                INode nextNode = connection.getTarget();

                conns.addAll(getAllInLineJobConnections(nextNode)); // follow this way
            }
        }
        return conns;
    }

    public static INode getFirstMergeNode(INode node) {
        INode mergeNode = null;
        for (IConnection connection : node.getOutgoingConnections()) {
            if (connection.getLineStyle().hasConnectionCategory(IConnectionCategory.MERGE)) {
                mergeNode = connection.getTarget();
            } else if (connection.getLineStyle().hasConnectionCategory(IConnectionCategory.FLOW)) {
                mergeNode = getFirstMergeNode(connection.getTarget());
            }
            if (mergeNode != null) {
                break;
            }
        }
        return mergeNode;
    }

    public static boolean isLastFromMergeTree(INode node) {
        INode firstMergeNode = getFirstMergeNode(node);
        int totMerge = NodeUtil.getIncomingConnections(firstMergeNode, IConnectionCategory.MERGE).size();
        Integer posMerge = node.getLinkedMergeInfo().get(firstMergeNode);
        return totMerge == posMerge;
    }

    public static IConnection getRealConnectionTypeBased(IConnection connection) {
        IConnection realConnection = connection;

        boolean connectionAvailable = true;

        //        while (connectionAvailable && realConnection.getSource().getComponent().getName().equals("tReplace")) { //$NON-NLS-1$

        while (connectionAvailable && !realConnection.getSource().isSubProcessStart()
                && realConnection.getSource().getComponent().isDataAutoPropagated()) {

            List<IConnection> inConnections = (List<IConnection>) getIncomingConnections(realConnection.getSource(),
                    IConnectionCategory.FLOW);
            if (inConnections.size() > 0) {
                realConnection = inConnections.get(0);
            } else {
                connectionAvailable = false;
            }
        }

        return realConnection;
    }

    /**
     * DOC wliu
     * <p>
     * judge if the current connection is the last output connection of the component
     * </p>
     * Notice: It is used in subtree_end.javajet. And the aim is for feature5996
     * 
     * @param connection
     * @return
     */
    public static boolean isLastMultiplyingOutputComponents(IConnection connection) {

        List<? extends IConnection> conns = connection.getSource().getOutgoingConnections();
        int last = 0;
        if (conns != null && conns.size() > 0) {
            for (int i = 0; i < conns.size(); i++) {
                if (conns.get(i).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                    last = i;
                }
            }
        }

        if (connection.getName().equals(conns.get(last).getName())) {
            return true;
        }

        return false;
    }
}
