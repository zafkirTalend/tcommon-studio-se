// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.window.Window;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.ui.wizards.metadata.ContextSetsSelectionDialog;

/**
 * DOC msjian class global comment. Detailled comment
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.metadata.builder.database.ISwitchContext#updateContextGroup(org.talend.core.model.properties
     * .ContextItem, org.talend.core.model.metadata.builder.connection.Connection)
     */
    public boolean updateContextGroup(ConnectionItem connItem) {
        if (connItem == null) {
            return false;
        }
        Connection con = connItem.getConnection();
        // MOD msjian 2012-2-13 TDQ-4559: make it support file/mdm connection
        if (con != null) {
            // TDQ-4559~
            String contextId = con.getContextId();
            ContextItem contextItem = ContextUtils.getContextItemById2(contextId);
            String selectedContext = contextItem.getDefaultContext();
            if (contextItem.getContext().size() > 1) {
                ContextSetsSelectionDialog setsDialog = new ContextSetsSelectionDialog(contextItem);
                if (setsDialog.open() == Window.OK) {
                    selectedContext = setsDialog.getSelectedContext();
                }
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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.utils.ISwitchContext#updateContextForConnectionItems(java.util.Map,
     * org.talend.core.model.properties.ContextItem)
     */
    public boolean updateContextForConnectionItems(Map<String, String> contextGroupRanamedMap, ContextItem contextItem) {
        if (contextItem == null) {
            return false;
        }
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        try {
            List<IRepositoryViewObject> allConnectionItem = factory.getAll(ProjectManager.getInstance().getCurrentProject(),
                    ERepositoryObjectType.METADATA_CONNECTIONS);

            for (IRepositoryViewObject connectionItem : allConnectionItem) {
                Item item = connectionItem.getProperty().getItem();
                if (item instanceof ConnectionItem && ConnectionContextHelper.checkContextMode((ConnectionItem) item) != null) {
                    Connection con = ((ConnectionItem) item).getConnection();
                    String contextId = con.getContextId();
                    if (contextId != null && contextId.equals(contextItem.getProperty().getId())) {
                        String oldContextGroup = con.getContextName();
                        boolean modified = false;
                        if (oldContextGroup != null && !"".equals(oldContextGroup)) { //$NON-NLS-1$
                            String newContextGroup = contextGroupRanamedMap.get(oldContextGroup);
                            if (newContextGroup != null) { // rename
                                con.setContextName(newContextGroup);
                                modified = true;
                            }
                        } else { // if not set, set default group
                            ContextItem originalItem = ContextUtils.getContextItemById2(contextId);
                            con.setContextName(originalItem.getDefaultContext());
                            modified = true;
                        }
                        if (modified) {
                            factory.save(item);
                        }
                    }
                }
            }
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return false;
    }
}
