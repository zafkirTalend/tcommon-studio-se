// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
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
import java.util.Map;

import org.talend.core.model.metadata.IMetadataColumn;

public interface IBigDataNode extends INode {

    public String getIncomingType();

    public String getOutgoingType();

    public String getRequiredInputType();

    public String getRequiredOutputType();

    public void setRequiredOutputType(String requiredOutputType);

    public void setRequiredInputType(String requiredInputType);

    public boolean isIdentity();

    public void setKeyList(IBigDataNode bigDataNode, String direction);

    public Map<String, List<IMetadataColumn>> getKeyList();

}
