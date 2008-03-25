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
package org.talend.dataprofiler.core.model.nodes.analysis;

import java.util.ArrayList;
import java.util.List;

import org.talend.dataquality.analysis.AnalysisType;


/**
 * @author zqin
 *
 */
public class AnalysisDataFactory {
    
    public static Object createTreeData() {
        List<AnalysisTypeNode> returnList = new ArrayList<AnalysisTypeNode>();
        
        List<AnalysisType> list = AnalysisType.VALUES;
        AnalysisTypeNode typeNode = null;
        for (AnalysisType oneType : list) {
            typeNode = new AnalysisTypeNode(oneType.getName(),null);
            AnalysisTypeNode[] childs = new AnalysisTypeNode[2];
            childs[0] = new AnalysisTypeNode("Default",typeNode);
            childs[1] = new AnalysisTypeNode(oneType.getLiteral(),typeNode);

            typeNode.setChildren(childs);
            
            
            returnList.add(typeNode);
        }

        
        return returnList;
        
    }
}