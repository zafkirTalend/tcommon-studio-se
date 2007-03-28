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
package org.talend.core.language;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.MultiValueMap;
import org.talend.core.model.process.Problem;
import org.talend.designer.codegen.IAloneProcessNodeConfigurer;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class CodeProblemsChecker implements ICodeProblemsChecker {

    private List<Problem> problems;

    private MultiValueMap multiValueMap = new MultiValueMap();

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.language.ICodeProblemsChecker#checkProblemsFromKey(java.lang.String)
     */
    public List<Problem> checkProblemsFromKey(String key, IAloneProcessNodeConfigurer nodeConfigurer) {
        List<Problem> problems = checkProblems(nodeConfigurer);
        setProblems(new ArrayList<Problem>(problems));
        if (problems == null) {
            problems = null;
        } else {
            for (Iterator iter = problems.iterator(); iter.hasNext();) {
                Problem problem = (Problem) iter.next();
                if (key == null && problem.getKey() != null || !key.equals(problem.getKey())) {
                    iter.remove();
                }
            }
            if (problems.size() == 0) {
                problems = null;
            } else {
                problems = new ArrayList<Problem>(problems);
            }
        }
        return problems;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.language.ICodeProblemsChecker#getProblemsFromKey(java.lang.String)
     */
    public List<Problem> getProblemsFromKey(String key) {
        List<Problem> list = (List<Problem>) multiValueMap.get(key);
        return list;
    }

    /**
     * Getter for problems.
     * 
     * @return the problems
     */
    protected List<Problem> getProblems() {
        return this.problems;
    }

    /**
     * Sets the problems.
     * 
     * @param problems the problems to set
     */
    protected void setProblems(List<Problem> problems) {
        this.problems = problems;
        multiValueMap.clear();
        if (problems != null) {
            for (Problem problem : problems) {
                multiValueMap.put(problem.getKey(), problem);
            }
        }
    }

}
