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
package org.talend.dataprofiler.core.model.nodes.impl;

import java.util.List;

import org.talend.cwm.helper.CatalogHelper;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.management.api.DqRepositoryViewService;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.helper.NeedSaveDataProviderHelper;
import org.talend.dataprofiler.core.model.nodes.AbstractFolderNode;

import orgomg.cwm.resource.relational.Catalog;


/**
 * @author rli
 *
 */
public class TableFolderNode extends AbstractFolderNode {

    /**
     * 
     */
    public TableFolderNode() {
       super("Tables");
    }

    @Override
    public void loadChildren() {

        if (this.getParent() instanceof Catalog) {

            Catalog catalog = (Catalog) this.getParent();
            
            List<TdTable> tableList = CatalogHelper.getTables(catalog);
            
            if (tableList.size() > 0) {
                this.setLoaded(true);
                return;
            }

            TdDataProvider provider =  DataProviderHelper.getTdDataProvider(catalog);

            List<TdTable> tables = DqRepositoryViewService.getTables(provider, catalog, null, true);
            // store tables in catalog
            CatalogHelper.addTables(tables, catalog);
            NeedSaveDataProviderHelper.register(provider.getName(), provider);
            this.setLoaded(true);
        }
    }

}
