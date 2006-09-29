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
package org.talend.commons.utils.workbench.extensions;

/**
 * Defines an extension point. To be use in ExtensionImplementationProviders<br/>
 * 
 * $Id$
 * 
 */
public interface ISimpleExtensionPoint {

    /**
     * Getter for extension point id.
     * 
     * @return the extPointId
     */
    public String getExtPointId();

    public String getElementName();

    /**
     * Getter for extension point maximum authorized implementation. Specify -1 for no max occurence
     * 
     * @return the maxOcc
     */
    public int getMaxOcc();

    /**
     * Getter for extension point minimum authorized implementation. Specify -1 for no min occurence
     * 
     * @return the minOcc
     */
    public int getMinOcc();
}
