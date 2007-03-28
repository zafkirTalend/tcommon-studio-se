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

import java.util.List;
import java.util.Map;

import org.talend.core.model.process.Problem;
import org.talend.designer.codegen.IAloneProcessNodeConfigurer;

/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public interface ICodeProblemsChecker {

    public abstract List<Problem> checkProblemsForExpression(String code);

    /**
     * 
     * Force code generation, check all problems and load problems in cache.
     * @param externalData TODO
     * @return
     */
    public abstract List<Problem> checkProblems(IAloneProcessNodeConfigurer nodeConfigurer);
    
    /**
     * 
     * Force code generation, check problems which matches with given key and load all problems in cache.
     * @param key
     * @param externalData 
     * @return
     */
    public abstract List<Problem> checkProblemsFromKey(String key, IAloneProcessNodeConfigurer nodeConfigurer);
    
    /**
     * 
     * Use cache of problems and get problems.
     * @param key
     * @return
     */
    public abstract List<Problem> getProblemsFromKey(String key);
    
}
