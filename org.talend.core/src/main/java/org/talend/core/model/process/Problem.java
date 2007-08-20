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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.ui.views.markers.internal.MarkerNode;

/**
 * Class that will be used in the ProblemsView. <br/>
 * 
 * $Id$
 * 
 */
public class Problem {

    public final static String EMPTY_STRING = "";//$NON-NLS-1$

    public static final Problem[] EMPTY_PROBLEM_ARRAY = new Problem[0];

    protected static final Collection<Problem> EMPTY_PROBLEM_COLLECTION = Arrays.asList(new Problem[0]);

    /**
     * smallet Problem class global comment. Detailled comment <br/>
     * 
     * $Id$
     */
    public enum ProblemStatus {
        ERROR,
        WARNING,
        INFO;
    }

    /**
     * bqian Problem class global comment. Detailled comment <br/>
     */
    public enum ProblemType {
        JOB,
        ROUTINE;
    }

    /**
     * Added to enhance the refresh speed of the problems view. <br/>
     * 
     * $Id$
     * 
     */

    private Element element;

    private String description;

    private ProblemStatus status;

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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.element == null) ? 0 : this.element.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Problem other = (Problem) obj;
        if (this.description == null) {
            if (other.description != null)
                return false;
        } else if (!this.description.equals(other.description))
            return false;
        if (this.element == null) {
            if (other.element != null)
                return false;
        } else if (!this.element.equals(other.element))
            return false;
        return true;
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

    /**
     * Getter for key.
     * 
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key.
     * 
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    public Problem[] getChildren() {
        return EMPTY_PROBLEM_ARRAY;
    }

    public boolean isConcrete() {
        return true;
    }

    /**
     * bqian Comment method "getName".
     * 
     * @return
     */
    public String getProblemResource() {
        return element.getElementName();
    }
}