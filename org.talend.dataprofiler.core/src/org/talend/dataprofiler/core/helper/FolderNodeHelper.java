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
package org.talend.dataprofiler.core.helper;

import java.util.HashMap;
import java.util.Map;

import org.talend.dataprofiler.core.model.nodes.IFolderNode;
import orgomg.cwm.resource.relational.Catalog;

/**
 * @author rli
 *
 */
public final class FolderNodeHelper {

    private static Map<Catalog, IFolderNode[]> catalogFolderNodeMap = new HashMap<Catalog, IFolderNode[]>();

    public static void put(Catalog catalog, IFolderNode[] folderNodes) {
        catalogFolderNodeMap.put(catalog, folderNodes);
    }

    public static IFolderNode[] get(Catalog catalog) {
        return catalogFolderNodeMap.get(catalog);
    }
}
