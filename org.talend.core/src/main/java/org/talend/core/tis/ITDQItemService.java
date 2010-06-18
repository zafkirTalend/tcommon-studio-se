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

import java.util.List;

import org.talend.commons.exception.PersistenceException;
import org.talend.core.IService;
import org.talend.core.model.properties.Item;

/**
 * DOC bZhou class global comment. Detailled comment
 */
public interface ITDQItemService extends IService {

    public List<Item> getAllItems();

    public void deleteItem(Item item) throws Exception;

    public void changeItemStatus(String newStatus, Item item) throws PersistenceException;

    public void changeVersions(String newVersion, Item item) throws Exception;
}
