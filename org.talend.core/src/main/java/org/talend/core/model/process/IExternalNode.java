// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.model.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.talend.core.model.components.IODataComponentContainer;
import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IExternalNode extends INode {

    public void initialize();

    /**
     * Open a modal swt Shell.
     * 
     * @param display
     * @return SWT.OK / SWT.CANCEL
     */
    // functions to implement
    public int open(Display display);

    public int open(Composite parent);

    public void setExternalData(IExternalData persistentData);

    public IExternalData getExternalData();

    /**
     * 
     * ExternalData to Xml.
     * 
     * @param out
     * @param writer
     * @throws IOException
     */
    public void loadDataOut(OutputStream out, Writer writer) throws IOException;

    /**
     * 
     * Xml to ExternalData.
     * 
     * @param inputStream
     * @param reader
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadDataIn(InputStream inputStream, Reader reader) throws IOException, ClassNotFoundException;

    public List<Problem> getProblems();

    // functions already implemented in abstract external node

    public void setComponentName(String componentName);

    public void setIncomingConnections(List<? extends IConnection> incomingConnections);

    public void setOutgoingConnections(List<? extends IConnection> outgoingConnections);

    public void setMetadataList(List<IMetadataTable> metadataList);

    public void setPluginFullName(String pluginFullName);

    public void setUniqueName(String uniqueName);

    public void setActivate(boolean activate);

    public void setStart(boolean start);

    public void setSubProcessStart(boolean subProcessStart);

    public abstract void renameInputConnection(String oldName, String newName);

    public abstract void renameOutputConnection(String oldName, String newName);

    public void setIODataComponents(IODataComponentContainer components);

    public IODataComponentContainer getIODataComponents();
}
