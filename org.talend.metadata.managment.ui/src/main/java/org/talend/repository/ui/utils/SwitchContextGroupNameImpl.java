// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.utils;

import org.apache.log4j.Logger;
import org.eclipse.jface.window.Window;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.ui.wizards.metadata.ContextSetsSelectionDialog;

/**
 * DOC msjian  class global comment. Detailled comment
 */
public class SwitchContextGroupNameImpl implements ISwitchContext {

    private static Logger log = Logger.getLogger(SwitchContextGroupNameImpl.class);

    private static SwitchContextGroupNameImpl instance;

    private SwitchContextGroupNameImpl() {
    }

    /**
     * DOC msjian Comment method "getInstance".
     * 
     * @return
     */
    public static SwitchContextGroupNameImpl getInstance() {
        if (instance == null) {
            instance = new SwitchContextGroupNameImpl();
        }
        return instance;
    }

    /* (non-Javadoc)
     * @see org.talend.core.model.metadata.builder.database.ISwitchContext#updateContextGroup(org.talend.core.model.properties.ContextItem, org.talend.core.model.metadata.builder.connection.Connection)
     */
    public boolean updateContextGroup(ConnectionItem connItem) {
        if (connItem == null) {
            return false;
        }
        Connection con = connItem.getConnection();
        if (con != null && con instanceof DatabaseConnection) {
            String contextId = con.getContextId();
            ContextItem contextItem = ContextUtils.getContextItemById2(contextId);
            String contextName = con.getContextName();
            ContextSetsSelectionDialog setsDialog = new ContextSetsSelectionDialog(contextItem);
            String selectedContext = contextName;
            if (setsDialog.open() == Window.OK) {
                selectedContext = setsDialog.getSelectedContext();
            }
            con.setContextName(selectedContext);
            IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();

            try {
                factory.save(connItem);
            } catch (PersistenceException e) {
                log.error(e, e);
            }
            return true;
        }
        return false;
    }
}
