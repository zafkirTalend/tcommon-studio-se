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
package org.talend.core;

import org.eclipse.ui.IViewPart;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.properties.Item;



/**
 * DOC bZhou  class global comment. Detailled comment
 */
public interface ITDQRepositoryService extends IService {
    
    public IViewPart getTDQRespositoryView();

    public void openEditor(Item item);

    public void notifySQLExplorer(Item... items);

    public void fillMetadata(Connection connection);

    public void refresh();

}
