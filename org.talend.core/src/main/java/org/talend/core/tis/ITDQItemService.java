// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.tis;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.IService;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.TDQItem;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC bZhou class global comment. Detailled comment
 */
public interface ITDQItemService extends IService {

    public List<Item> getAllItems();

    public List<Item> getAllVersion(ERepositoryObjectType type);

    public String getVersion(TDQItem item);

    public Map<String, List<TDQItem>> getItemPathMap();

    public void deleteItem(Item item) throws Exception;

    public void changeItemStatus(String newStatus, Item item) throws PersistenceException;

    public void changeVersions(String newVersion, Item item) throws Exception;

    public void exportItems(File file, List<Item> tdqItems, boolean isExportAll, boolean withDependencies,
            NullProgressMonitor nullProgressMonitor);

    public void importItems(File sourceFile, List<Item> validItems, boolean overwrite, NullProgressMonitor nullProgressMonitor);

}
