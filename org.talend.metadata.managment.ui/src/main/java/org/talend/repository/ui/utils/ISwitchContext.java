// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.utils;

import java.util.Map;

import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;

/**
 * DOC msjian class global comment. Detailled comment
 */
public interface ISwitchContext {

    public boolean updateContextGroup(ConnectionItem connItem);

    public boolean updateContextForConnectionItems(Map<String, String> contextGroupRanamedMap, ContextItem contextItem);

}
