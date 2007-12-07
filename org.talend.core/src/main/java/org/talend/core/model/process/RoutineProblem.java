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
public class RoutineProblem extends Problem {

    private IMarker marker;

    private String javaUnitName;

    private Integer lineNumber;

    public RoutineProblem(ProblemStatus status, String javaUnitName, IMarker marker, String markerErrorMessage, Integer lineNumber) {
        super();
        setDescription(markerErrorMessage);
        setStatus(status);
        this.javaUnitName = javaUnitName;
        this.marker = marker;
        this.lineNumber = lineNumber;
        setType(ProblemType.ROUTINE);
    }

    private String getName() {
        return javaUnitName;
    }

    private Integer getLineNumber() {
        return lineNumber;
    }

    public String getJavaUnitName() {
        return this.javaUnitName;
    }

    public String getProblemResource() {
        return "Routine:" + this.getName() + " (line:" + this.getLineNumber() + ")";
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
        final RoutineProblem other = (RoutineProblem) obj;
        if (this.javaUnitName == null) {
            if (other.javaUnitName != null)
                return false;
        } else if (!this.javaUnitName.equals(other.javaUnitName))
            return false;
        if (this.lineNumber == null) {
            if (other.lineNumber != null)
                return false;
        } else if (!this.lineNumber.equals(other.lineNumber))
            return false;
        if (this.marker == null) {
            if (other.marker != null)
                return false;
        } else if (!this.marker.equals(other.marker))
            return false;
        if (this.getDescription() == null) {
            if (other.getDescription() != null)
                return false;
        } else if (!this.getDescription().equals(other.getDescription()))
            return false;
        return true;
    }

}
