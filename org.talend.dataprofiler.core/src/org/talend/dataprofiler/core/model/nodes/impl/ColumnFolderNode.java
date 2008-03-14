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
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.helper.TableHelper;
import org.talend.cwm.management.api.DqRepositoryViewService;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.helper.NeedSaveDataProviderHelper;
import org.talend.dataprofiler.core.model.nodes.AbstractFolderNode;


/**
 * @author rli
 *
 */
public class ColumnFolderNode extends AbstractFolderNode {

    /**
     * @param name
     */
    public ColumnFolderNode() {
        super("Columns");
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.model.nodes.AbstractFolderNode#loadChildren()
     */
    @Override
    public void loadChildren() {
        TdTable table = SwitchHelpers.TABLE_SWITCH.doSwitch(this.getParent());
        if (table != null) {
            List<TdColumn> columnList = TableHelper.getColumns(table);
            if (columnList.size() > 0) {
                this.setLoaded(true);
                return;
            }
            TdDataProvider provider = DataProviderHelper.getTdDataProvider(CatalogHelper.getParentCatalog(table));

            List<TdColumn> columns = DqRepositoryViewService.getColumns(provider, table, null, true);
            // store tables in catalog
            TableHelper.addColumns(table, columns);
            NeedSaveDataProviderHelper.register(provider.getName(), provider);
            this.setLoaded(true);
        }
    }

}
