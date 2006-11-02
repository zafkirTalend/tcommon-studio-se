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
package org.talend.core.model.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum describing the different repository types available. <br/>
 * 
 * $Id$
 * 
 */
public enum ERepositoryType {
    LOCAL("Local Repository"),
    REMOTE("Remote Repository");

    private ERepositoryType(String label) {
        this.label = label;
    }

    private String label;

    /**
     * Getter for label.
     * 
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return getLabel();
    }

    /**
     * Sets the label.
     * 
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    public static String[] getLabels() {
        List<String> labels = new ArrayList<String>();
        for (ERepositoryType repository : ERepositoryType.values()) {
            labels.add(repository.getLabel());
        }
        return labels.toArray(new String[] {});
    }

    /**
     * Return the Repository Type.
     * 
     * @param repository
     * @return
     */
    public static ERepositoryType getRepository(String repository) {
        for (ERepositoryType repositoryValue : ERepositoryType.values()) {
            if (repositoryValue.getLabel().compareTo(repository) == 0) {
                return repositoryValue;
            }
        }
        return null;
    }
}
