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
package org.talend.commons.utils.workbench.extensions;

/**
 * Default implementation of IExtensionPoint.<br/>
 * 
 * $Id$
 * 
 */
public class ExtensionPointLimiterImpl implements IExtensionPointLimiter {

    String extPointId;

    String elementName;
    
    int minOcc = -1;

    int maxOcc = -1;



    
    
    /**
     * DOC amaumont ExtensionPointLimiterImpl constructor comment.
     * @param extPointId
     * @param elementName can be null
     * @param namespaceIdentifier can be null
     * @param minOcc
     * @param maxOcc
     */
    public ExtensionPointLimiterImpl(String extPointId, String elementName, int minOcc, int maxOcc) {
        super();
        this.extPointId = extPointId;
        this.elementName = elementName;
        this.minOcc = minOcc;
        this.maxOcc = maxOcc;
    }


    /**
     * DOC amaumont ExtensionPointLimiterImpl constructor comment.
     * @param extPointId
     * @param minOcc
     * @param maxOcc
     */
    public ExtensionPointLimiterImpl(String extPointId, int minOcc, int maxOcc) {
        super();
        this.extPointId = extPointId;
        this.minOcc = minOcc;
        this.maxOcc = maxOcc;
    }



    /**
     * DOC amaumont ExtensionPointLimiterImpl constructor comment.
     * @param extPointId
     */
    public ExtensionPointLimiterImpl(String extPointId) {
        super();
        this.extPointId = extPointId;
    }



    /**
     * DOC amaumont ExtensionPointLimiterImpl constructor comment.
     * @param extPointId
     * @param elementName
     */
    public ExtensionPointLimiterImpl(String extPointId, String elementName) {
        super();
        this.extPointId = extPointId;
        this.elementName = elementName;
    }



    /**
     * Sets the extPointId.
     * 
     * @param extPointId the extPointId to set
     */
    public void setExtPointId(String extPointId) {
        this.extPointId = extPointId;
    }

    /**
     * Sets the maxOcc.
     * 
     * @param maxOcc the maxOcc to set
     */
    public void setMaxOcc(int maxOcc) {
        this.maxOcc = maxOcc;
    }

    /**
     * Sets the minOcc.
     * 
     * @param minOcc the minOcc to set
     */
    public void setMinOcc(int minOcc) {
        this.minOcc = minOcc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.extension.IExtensionPoint#getExtPointId()
     */
    public String getExtPointId() {
        return extPointId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.extension.IExtensionPoint#getMaxOcc()
     */
    public int getMaxOcc() {
        return maxOcc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.extension.IExtensionPoint#getMinOcc()
     */
    public int getMinOcc() {
        return minOcc;
    }

    /**
     * Getter for elementNAme.
     * 
     * @return the elementNAme
     */
    public String getConfigurationElementName() {
        return this.elementName;
    }

    /**
     * Sets the elementNAme.
     * 
     * @param elementNAme the elementNAme to set
     */
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

}
