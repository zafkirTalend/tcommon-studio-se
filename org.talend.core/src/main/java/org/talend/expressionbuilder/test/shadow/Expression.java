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
package org.talend.expressionbuilder.test.shadow;

import java.util.ArrayList;
import java.util.List;

/**
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: Expression.java 上午10:50:58 2007-9-15 +0000 (2007-9-15) yzhang $
 * 
 */
public class Expression {

    private String expression;

    private final List<Variable> variables;

    /**
     * yzhang Expression constructor comment.
     */
    public Expression() {
        expression = null;
        variables = new ArrayList<Variable>();
    }

    /**
     * yzhang Expression constructor comment.
     * 
     * @param expression
     * @param vList
     */
    public Expression(String expression, List<Variable> vList) {
        this.expression = expression;
        this.variables = vList;
    }

    /**
     * Getter for expression.
     * 
     * @return the expression
     */
    public String getExpression() {
        return this.expression;
    }

    /**
     * Sets the expression.
     * 
     * @param expression the expression to set
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Getter for variables.
     * 
     * @return the variables
     */
    public List<Variable> getVariables() {
        return this.variables;
    }

}
