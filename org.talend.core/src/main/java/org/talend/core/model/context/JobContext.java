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
package org.talend.core.model.context;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextParameter;

/**
 * Context of a process.
 * 
 * $Id$
 * 
 */
public class JobContext implements IContext, Cloneable {

    String name;

    boolean confirmationNeeded;

    public JobContext(String name) {
        this.name = name;
    }

    List<IContextParameter> contextParameterList = new ArrayList<IContextParameter>();

    public List<IContextParameter> getContextParameterList() {
        return this.contextParameterList;
    }

    public void setContextParameterList(final List<IContextParameter> contextParameterList) {
        this.contextParameterList = contextParameterList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isConfirmationNeeded() {
        return this.confirmationNeeded;
    }

    public void setConfirmationNeeded(boolean confirmationNeeded) {
        this.confirmationNeeded = confirmationNeeded;
    }

    public IContext clone() {
        IContext clonedContext = null;
        try {
            clonedContext = (IContext) super.clone();
            List<IContextParameter> clonedContextParametersList = new ArrayList<IContextParameter>();
            clonedContext.setContextParameterList(clonedContextParametersList);
            for (int i = 0; i < contextParameterList.size(); i++) {
                clonedContextParametersList.add(contextParameterList.get(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            // nothing
        }
        return clonedContext;
    }

    public boolean sameAs(IContext context) {
        if (!context.getName().equals(name)) {
            return false;
        }
        if (contextParameterList.size() != context.getContextParameterList().size()) {
            return false;
        }
        for (int i = 0; i < contextParameterList.size(); i++) {
            IContextParameter curContextParam = contextParameterList.get(i);
            IContextParameter testContextParam = context.getContextParameterList().get(i);
            if (!curContextParam.sameAs(testContextParam)) {
                return false;
            }
        }
        return true;
    }

    public IContextParameter getContextParameter(String parameterName) {
        for (IContextParameter contextParam : contextParameterList) {
            if (contextParam.getName().equals(parameterName)) {
                return contextParam;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer(name + ":");
        buff.append("(");
        for (int i = 0; i < contextParameterList.size(); i++) {
            IContextParameter param = contextParameterList.get(i);
            buff.append(param.getName() + "=");
            buff.append(param.getValue());
            if (i < (contextParameterList.size() - 1)) {
                buff.append(",");
            }
        }
        buff.append(")");
        return buff.toString();
    }
}
