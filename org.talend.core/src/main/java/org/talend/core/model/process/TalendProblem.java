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
package org.talend.core.model.process;

import org.eclipse.core.resources.IMarker;

/**
 * DOC xhuang class global comment. Detailled comment <br/>
 * 
 */
public class TalendProblem extends Problem {

    private IMarker marker;

    private String javaUnitName;

    private Integer lineNumber;

    private Integer charStart;

    private Integer charEnd;

    public TalendProblem(ProblemStatus status, String javaUnitName, IMarker marker, String markerErrorMessage,
            Integer lineNumber, Integer charStart, Integer charEnd, ProblemType type) {
        super();
        setDescription(markerErrorMessage);
        setStatus(status);
        this.javaUnitName = javaUnitName;
        this.marker = marker;
        this.lineNumber = lineNumber;
        this.charStart = charStart;
        this.charEnd = charEnd;
        setType(type);
    }

    private String getName() {
        return javaUnitName;
    }

    private Integer getLineNumber() {
        return lineNumber;
    }

    public Integer getCharStart() {
        return this.charStart;
    }

    public Integer getCharEnd() {
        return this.charEnd;
    }

    public String getJavaUnitName() {
        return this.javaUnitName;
    }

    public String getProblemResource() {
        return this.type.getTypeName() + ":" + this.getName() + " (line:" + this.getLineNumber() + ")";
    }

    //    

    public IMarker getMarker() {
        return this.marker;
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
        result = prime * result + ((this.javaUnitName == null) ? 0 : this.javaUnitName.hashCode());
        result = prime * result + ((this.lineNumber == null) ? 0 : this.lineNumber.hashCode());
        result = prime * result + ((this.marker == null) ? 0 : this.marker.hashCode());
        result = prime * result + ((this.charStart == null) ? 0 : this.charStart.hashCode());
        result = prime * result + ((this.charEnd == null) ? 0 : this.charEnd.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TalendProblem other = (TalendProblem) obj;
        if (this.javaUnitName == null) {
            if (other.javaUnitName != null) {
                return false;
            }
        } else if (!this.javaUnitName.equals(other.javaUnitName)) {
            return false;
        }
        if (this.lineNumber == null) {
            if (other.lineNumber != null) {
                return false;
            }
        } else if (!this.lineNumber.equals(other.lineNumber)) {
            return false;
        }
        if (this.marker == null) {
            if (other.marker != null) {
                return false;
            }
        } else if (!this.marker.equals(other.marker)) {
            return false;
        }
        if (this.charStart == null) {
            if (other.charStart != null) {
                return false;
            }
        } else if (!this.charStart.equals(other.charStart)) {
            return false;
        }
        if (this.charEnd == null) {
            if (other.charEnd != null) {
                return false;
            }
        } else if (!this.charEnd.equals(other.charEnd)) {
            return false;
        }
        if (this.getDescription() == null) {
            if (other.getDescription() != null) {
                return false;
            }
        } else if (!this.getDescription().equals(other.getDescription())) {
            return false;
        }
        return true;
    }

}
