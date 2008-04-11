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
package org.talend.dataprofiler.core.ui.dialog.provider;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.talend.cwm.relational.TdTable;
import org.talend.cwm.relational.TdView;
import org.talend.dataprofiler.core.ui.views.provider.DQRepositoryViewContentProvider;
import orgomg.cwm.resource.relational.NamedColumnSet;

/**
 * @author rli
 * 
 */
public class DBTablesViewContentProvider extends DQRepositoryViewContentProvider {

    private static Logger log = Logger.getLogger(DBTablesViewContentProvider.class);

    /**
     * @param adapterFactory
     */
    public DBTablesViewContentProvider() {
        super();
    }

    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IContainer) {
            try {
                return ((IContainer) parentElement).members();
            } catch (CoreException e) {
                log.error("Can't get the children of container:" + ((IContainer) parentElement).getLocation());
            }
        } else if (parentElement instanceof NamedColumnSet) {
            return null;
        }
        return super.getChildren(parentElement);
    }

    public boolean hasChildren(Object element) {
        return !(element instanceof TdView || element instanceof TdTable);
    }

}
