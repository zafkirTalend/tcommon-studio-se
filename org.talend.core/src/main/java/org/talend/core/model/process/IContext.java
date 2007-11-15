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

import java.util.List;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IContext {

    String DEFAULT = "Default"; //$NON-NLS-1$

    public void setName(String name);

    public String getName();

    public boolean isConfirmationNeeded();

    public void setConfirmationNeeded(boolean confirmationNeeded);

    public List<IContextParameter> getContextParameterList();

    public void setContextParameterList(List<IContextParameter> contextParameterList);
    
    public IContextParameter getContextParameter(String parameterName);

    public IContext clone();
    
    public boolean sameAs(IContext context);
}
