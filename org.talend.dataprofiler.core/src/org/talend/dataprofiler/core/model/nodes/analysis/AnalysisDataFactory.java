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
import org.talend.dataquality.analysis.category.AnalysisCategories;
//import org.talend.dq.analysis.category.CategoryHandler;

/**
 * @author zqin
 * 
 */
public class AnalysisDataFactory {

    public static Object createTreeData() {
        List<AnalysisTypeNode> returnList = new ArrayList<AnalysisTypeNode>();

        // TODO zqin use CategoryHandler
        //AnalysisCategories analysisCategories = CategoryHandler.getAnalysisCategories();
        // TODO zqin use this tree (use label attribute of each Category instance)
        //analysisCategories.getCategories();

        List<AnalysisType> list = AnalysisType.VALUES;
        AnalysisTypeNode typeNode = null;
        for (AnalysisType oneType : list) {
            typeNode = new AnalysisTypeNode(oneType.getName(), oneType.getLiteral(), null);
            AnalysisTypeNode[] childs = new AnalysisTypeNode[1];
            childs[0] = new AnalysisTypeNode("Default", oneType.getLiteral(), typeNode);

            typeNode.setChildren(childs);

            returnList.add(typeNode);
        }

        return returnList;

    }
}
