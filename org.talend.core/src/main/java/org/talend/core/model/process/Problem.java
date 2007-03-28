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
 * Class that will be used in the ProblemsView. <br/>
 * 
 * $Id$
 * 
 */
public class Problem {

    /**
     * DOC smallet Problem class global comment. Detailled comment <br/>
     * 
     * $Id$
     */
    public enum ProblemStatus {
        ERROR,
        WARNING;
    }
    
    /**
     * Added to enhance the refresh speed of the problems view.
     * <br/>
     *
     * $Id$
     *
     */
    public enum ProblemAction {
        DELETED,
        ADDED,
        NONE;
    }

    private Element element;

    private String description;

    private ProblemStatus status;
    
    private ProblemAction action;
    
    private String key;

    /**
     * DOC smallet Problem constructor comment.
     */
    public Problem() {
        super();
    }

    /**
     * DOC smallet Problem constructor comment.
     * 
     * @param element
     * @param description
     * @param status
     */
    public Problem(Element element, String description, ProblemStatus status) {
        super();
        this.element = element;
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Element getElement() {
        return this.element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    /**
     * Getter for status.
     * 
     * @return the status
     */
    public ProblemStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status.
     * 
     * @param status the status to set
     */
    public void setStatus(ProblemStatus status) {
        this.status = status;
    }

    
    public ProblemAction getAction() {
        return action;
    }

    
    public void setAction(ProblemAction action) {
        this.action = action;
    }

    
    /**
     * Getter for key.
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    
    /**
     * Sets the key.
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    
    
}
