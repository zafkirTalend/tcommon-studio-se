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
package org.talend.core.model.process;

/**
 * Interface for Links between Nodes in a Process. <br/>
 * 
 * $Id$
 * 
 */
public interface INodeConnector {

    public abstract EConnectionType getConnectionType();

    public abstract void setConnectionType(final EConnectionType connectionType);

    public abstract int getMaxLinkOutput();

    public abstract void setMaxLinkOutput(final int maxLinkOutput);

    public abstract int getMaxLinkInput();

    public abstract void setMaxLinkInput(final int maxLinkInput);

    public abstract int getMinLinkInput();

    public abstract void setMinLinkInput(int minLinkInput);

    public abstract int getMinLinkOutput();

    public abstract void setMinLinkOutput(int minLinkOutput);

    public abstract boolean isBuiltIn();

    public abstract void setBuiltIn(final boolean builtIn);

    public abstract int getCurLinkNbInput();

    public abstract void setCurLinkNbInput(final int curLinkNb);

    public abstract int getCurLinkNbOutput();

    public abstract void setCurLinkNbOutput(final int curLinkNb);
}
