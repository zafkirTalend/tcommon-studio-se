// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.mapper;

import java.util.List;

import org.talend.core.IService;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IExternalData;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public interface IDesignerMapperService extends IService {

    /**
     * 
     * DOC YeXiaowei Comment method "isVirtualComponent".
     * 
     * @param node
     * @return
     */
    public boolean isVirtualComponent(final INode node);

    public void renameJoinTable(IProcess process, IExternalData data, List<String> createdNames);

    public List<String> getJoinTableNames(IExternalData data);

    public void createAutoMappedNode(INode node, IConnection inputConnection, IConnection outputConnection);

    public void updateLink(INode node, IConnection oldConnection, IConnection newConnection);
}
