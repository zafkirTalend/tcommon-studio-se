// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core;

import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.IRepositoryViewObject;

/**
 * DOC zwzhao class global comment. Detailled comment
 */
public interface IProcessConvertService {

    public IProcess getProcessFromItem(Item item, boolean loadScreenshots);

    /**
     * Converts a given process item to another item. Added by Marvin Wang on Jan 29, 2013. Added by Marvin Wang on Jan
     * 30, 2013.
     * 
     * @param item
     * @param repViewObject
     * @return
     */
    Item doConvert(Item item, IRepositoryViewObject repViewObject);

    /**
     * Returns a boolean value to indicate if the original item is delelted. Added by Marvin Wang on Jan 31, 2013.
     * 
     * @return
     */
    boolean isOriginalItemDeleted();

    /**
     * Returns a boolean value to indicate if the new item is converted sucessfully. Added by Marvin Wang on Jan 31,
     * 2013.
     * 
     * @return
     */
    boolean isNewItemCreated();
}
