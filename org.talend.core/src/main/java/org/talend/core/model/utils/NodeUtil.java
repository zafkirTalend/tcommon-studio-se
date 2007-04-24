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
package org.talend.core.model.utils;

import java.util.ArrayList;
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
     * DOC sort the outgoingconnections to make sure the first connection is EConnectionType.FLOW_MAIN or EConnectionType.FLOW_REF<br/>
     * @param node
     * @return List<? extends IConnection>
     */
    public static List<? extends IConnection> getOutgoingSortedConnections(INode node) {
        
        List<? extends IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            IConnection[] sortArr = new IConnection[outgoingConnections.size()];

            int index = 0;
            for (int work = 0; work < outgoingConnections.size(); work++) {

                IConnection connection = outgoingConnections.get(work);
                if (connection.getLineStyle() == EConnectionType.FLOW_MAIN
                        || connection.getLineStyle() == EConnectionType.FLOW_REF) {

                    sortArr[index++] = connection;

                } else {

                    sortArr[sortArr.length - work + index - 1] = connection;

                }

            }
            
            conns = java.util.Arrays.asList(sortArr);
        }

        return conns;
    }
    
    
    /**
     * DOC get the EConnectionType.FLOW_MAIN or EConnectionType.FLOW_REF out goning Connections<br/>
     * @param node
     * @return List<? extends IConnection>
     */
    public static List<? extends IConnection> getMainOutgoingConnections(INode node) {
        List<IConnection> conns = null;

        List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
        if (outgoingConnections != null) {
            conns = new ArrayList<IConnection>();

            for (int work = 0; work < outgoingConnections.size(); work++) {

                IConnection connection = outgoingConnections.get(work);
                if (connection.getLineStyle() == EConnectionType.FLOW_MAIN
                        || connection.getLineStyle() == EConnectionType.FLOW_REF) {

                    conns.add(connection);

                } 
            }
        }

        return conns;
    }
}
