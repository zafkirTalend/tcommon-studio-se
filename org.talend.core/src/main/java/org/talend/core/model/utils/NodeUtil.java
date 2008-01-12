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
package org.talend.core.model.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;

/**
 * DOC xtan class global comment. Detailled comment <br/>
 * 
 */
public class NodeUtil {

    /**
     * DOC sort the outgoingconnections to make sure the first connection is EConnectionType.FLOW_MAIN or
     * EConnectionType.FLOW_REF<br/>
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
                    if (lineStyle == EConnectionType.FLOW_MAIN || lineStyle == EConnectionType.FLOW_MERGE) {
                        return -1;
                    }
                    if (lineStyle2 == EConnectionType.FLOW_MAIN || lineStyle2 == EConnectionType.FLOW_MERGE) {
                        return 1;
                    } else {
                        return 0;
                    }

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

    public static boolean checkConnectionAfterNode(INode node, EConnectionType connectionType) {
        boolean result = false;
        List<? extends IConnection> onErrorConns = getOutgoingConnections(node, EConnectionType.ON_COMPONENT_ERROR);
        if (onErrorConns == null || onErrorConns.size() == 0) {
            List<? extends IConnection> conns = getOutgoingSortedConnections(node);
            if (conns != null && conns.size() > 0) {
                for (IConnection conn : conns) {
                    result = checkConnectionAfterNode(conn.getTarget(), EConnectionType.ON_COMPONENT_ERROR);
                }
            } else {
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }
}
