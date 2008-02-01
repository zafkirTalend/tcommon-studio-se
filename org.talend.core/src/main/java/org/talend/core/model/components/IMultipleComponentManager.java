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
package org.talend.core.model.components;

import java.util.List;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IMultipleComponentManager {

    public IMultipleComponentItem addItem(String name, String component);

    public void validateItems();

    public IMultipleComponentItem getInput();

    public IMultipleComponentItem getOutput();

    public List<IMultipleComponentItem> getItemList();

    public void addParam(String source, String target);

    public void addValue(String target, String value);

    public List<IMultipleComponentParameter> getParamList();

    public boolean isSetConnector();

    public String getConnector();
    
    public boolean existsROWSENDLinkTo();
    
    public void setExistsROWSENDLinkTo(boolean flag);
}
