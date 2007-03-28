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
package org.talend.core.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;

/**
 * Session context of execution. <br/>
 * 
 * $Id$
 * 
 */
public final class Context {

    Map<String, Object> properties = new Hashtable<String, Object>();

    public static final String REPOSITORY_CONTEXT_KEY = "repositoryContext"; //$NON-NLS-1$

    private static final String BREAKPOINTS = "BREAKPOINTS"; //$NON-NLS-1$

    /**
     * Constructs a new Context.
     */
    public Context() {
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object getProperty(String arg0) {
        return this.properties.get(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Object putProperty(String arg0, Object arg1) {
        return this.properties.put(arg0, arg1);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Map#remove(java.lang.Object)
     */
    public Object removeProperty(Object arg0) {
        return this.properties.remove(arg0);
    }

    public List<INode> getBreakpointNodes(IProcess process) {
        Map<IProcess, List<INode>> nodesByProcess = (Map<IProcess, List<INode>>) properties.get(BREAKPOINTS);
        List<INode> nodes = nodesByProcess != null ? nodesByProcess.get(process) : null;
        return nodes != null ? nodes : new ArrayList<INode>();
    }

    public void addBreakpoint(IProcess process, INode node) {
        Map<IProcess, List<INode>> nodesByProcess = (Map<IProcess, List<INode>>) properties.get(BREAKPOINTS);
        if (nodesByProcess == null) {
            nodesByProcess = new HashMap<IProcess, List<INode>>();
            properties.put(BREAKPOINTS, nodesByProcess);
        }
        List<INode> nodes = nodesByProcess.get(process);
        if (nodes == null) {
            nodes = new ArrayList<INode>();
            nodesByProcess.put(process, nodes);
        }
        nodes.add(node);
    }

    public void removeBreakpoint(IProcess process, INode node) {
        Map<IProcess, List<INode>> nodesByProcess = (Map<IProcess, List<INode>>) properties.get(BREAKPOINTS);
        if (nodesByProcess != null) {
            List<INode> nodes = nodesByProcess.get(process);
            if (nodes != null) {
                nodes.remove(node);
                if (nodes.isEmpty()) {
                    nodesByProcess.remove(nodes);
                    if (nodesByProcess.isEmpty()) {
                        properties.remove(BREAKPOINTS);
                    }
                }
            }
        }
    }

}
