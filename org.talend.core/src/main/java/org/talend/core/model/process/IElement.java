// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
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
public interface IElement {
    
    public IElementParameter getElementParameter(String name);
    
//    public IElementParameter getElementParameterFromField(EParameterFieldType fieldType);

    public List<? extends IElementParameter> getElementParameters();

    public void setElementParameters(List<? extends IElementParameter> elementsParameters);

    public boolean isReadOnly();
    
    public void setReadOnly(boolean readOnly);
}
