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
package org.talend.commons.utils.performance;

/**
 * 
 * Event occured by <code>PerformanceEvaluator</code> when the process is finished. <br/>
 * 
 * $Id$
 * 
 */
public class PerformanceEvaluatorEvent {

    private int indicePerformance;

    public PerformanceEvaluatorEvent(int indicePerformance) {
        this.indicePerformance = indicePerformance;
    }

    /**
     * A value lower than PerformanceEvaluator.GOOD_PERFORMANCE_INDICE is considerate how best performance.
     * 
     * @return indicePerformance value
     */
    public int getIndicePerformance() {
        return indicePerformance;
    }

    public void setIndicePerformance(int indicePerformance) {
        this.indicePerformance = indicePerformance;
    }

}
