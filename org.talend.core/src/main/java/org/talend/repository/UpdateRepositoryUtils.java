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
package org.talend.repository;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * ggu class global comment. Detailled comment
 */
public final class UpdateRepositoryUtils {

    /**
     * ggu Comment method "getQueryFromItem".
     * 
     * get Query
     */
    @SuppressWarnings("unchecked")
    public static Query getQueryById(Item item, final String queryId) {
        if (item == null || queryId == null) {
            return null;
        }
        if (item instanceof ConnectionItem) {
            ConnectionItem connItem = (ConnectionItem) item;
            final Connection connection = connItem.getConnection();
            if (connection != null) {
                final QueriesConnection queryConn = connection.getQueries();
                if (queryConn != null) {
                    final EList queries = queryConn.getQuery();
                    if (queries != null) {
                        for (Query query : (List<Query>) queries) {
                            if (query.getId().equals(queryId)) {
                                return query;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static Query getQueryById(final String queryId) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ConnectionItem> metadataConnectionsItem = null;
        try {
            metadataConnectionsItem = factory.getMetadataConnectionsItem();
        } catch (PersistenceException e) {
            //
        }
        if (metadataConnectionsItem != null) {
            for (ConnectionItem connectionItem : metadataConnectionsItem) {
                if (factory.getStatus(connectionItem) != ERepositoryStatus.DELETED) {
                    return getQueryById(connectionItem, queryId);
                }
            }
        }
        return null;
    }

    public static Query getQueryById(Map<String, ConnectionItem> itemsMap, final String queryId) {
        if (itemsMap == null || itemsMap.isEmpty() || queryId == null) {
            return null;
        }
        for (String connId : itemsMap.keySet()) {
            ConnectionItem item = itemsMap.get(connId);
            final Query query = getQueryById(item, queryId);
            if (query != null) {
                return query;
            }

        }
        return null;
    }

    /**
     * 
     * ggu Comment method "getTableFromItem".
     * 
     * get MetadataTable
     */
    @SuppressWarnings("unchecked")
    public static MetadataTable getTableById(Item item, final String tableId) {
        if (item == null || tableId == null) {
            return null;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        if (item instanceof ConnectionItem) {
            ConnectionItem connItem = (ConnectionItem) item;
            final Connection connection = connItem.getConnection();
            if (connection != null) {
                final EList tables = connection.getTables();
                if (tables != null) {
                    for (MetadataTable table : (List<MetadataTable>) tables) {
                        if (!factory.isDeleted(table)) {
                            if (table.getId().equals(tableId)) {
                                return table;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static MetadataTable getTableById(final String tableId) {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        List<ConnectionItem> metadataConnectionsItem = null;
        try {
            metadataConnectionsItem = factory.getMetadataConnectionsItem();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        if (metadataConnectionsItem != null) {
            for (ConnectionItem connectionItem : metadataConnectionsItem) {
                if (factory.getStatus(connectionItem) != ERepositoryStatus.DELETED) {
                    return getTableById(connectionItem, tableId);
                }
            }
        }
        return null;
    }

    public static MetadataTable getTableById(Map<String, ConnectionItem> itemsMap, final String tableId) {
        if (itemsMap == null || itemsMap.isEmpty() || tableId == null) {
            return null;
        }
        for (String connId : itemsMap.keySet()) {
            ConnectionItem item = itemsMap.get(connId);
            final MetadataTable table = getTableById(item, tableId);
            if (table != null) {
                return table;
            }
        }
        return null;
    }

    /**
     * 
     * ggu Comment method "getConnectionItemByChildId".
     * 
     * @param itemsMap
     * @param childId
     * @return
     */
    public static ConnectionItem getConnectionItemByChildId(Map<String, ConnectionItem> itemsMap, final String childId) {
        if (itemsMap == null || itemsMap.isEmpty() || childId == null) {
            return null;
        }
        for (String connId : itemsMap.keySet()) {
            ConnectionItem item = itemsMap.get(connId);
            // check query
            if (getQueryById(item, childId) != null) {
                return item;
            }
            // check table
            if (getTableById(item, childId) != null) {
                return item;
            }
        }
        return null;
    }

    /**
     * 
     * ggu Comment method "getConnectionItemByItemId".
     * 
     * get item by id
     */
    public static ConnectionItem getConnectionItemByItemId(String itemId) {
        return getConnectionItemByItemId(itemId, false);
    }

    public static ConnectionItem getConnectionItemByItemId(String itemId, boolean deleted) {
        if (itemId == null || itemId.equals("")) {
            return null;
        }
        final IProxyRepositoryFactory proxyRepositoryFactory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            final IRepositoryObject lastVersion = proxyRepositoryFactory.getLastVersion(itemId);
            if (lastVersion != null) {
                if (!deleted && proxyRepositoryFactory.getStatus(lastVersion) == ERepositoryStatus.DELETED) {
                    return null;
                }
                final Item item = lastVersion.getProperty().getItem();
                if (item != null && item instanceof ConnectionItem) {
                    return (ConnectionItem) item;
                }
            }
        } catch (PersistenceException e) {
            // 
        }
        return null;
    }

    /**
     * 
     * ggu Comment method "getQueriesFromItemId".
     * 
     * get queries from the item.
     */
    public static List<Query> getQueriesFromItemId(final String itemId) {
        ConnectionItem item = getConnectionItemByItemId(itemId);
        if (item != null) {
            final Connection connection = item.getConnection();
            if (connection != null) {
                final QueriesConnection queryConn = connection.getQueries();
                if (queryConn != null) {
                    final EList query = queryConn.getQuery();
                    if (query != null) {
                        return (List<Query>) query;
                    }
                }
            }
        }
        return null;
    }

    public static List<Query> getQueriesFromItem(Item item) {
        if (item == null) {
            return null;
        }
        if (item instanceof ConnectionItem) {
            final Connection connection = ((ConnectionItem) item).getConnection();
            if (connection != null) {
                final QueriesConnection queryConn = connection.getQueries();
                if (queryConn != null) {
                    final EList query = queryConn.getQuery();
                    if (query != null) {
                        return (List<Query>) query;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 
     * ggu Comment method "getMetadataTablesFromItemId".
     * 
     * get tables from item
     */
    public static List<MetadataTable> getMetadataTablesFromItemId(final String itemId) {
        ConnectionItem item = getConnectionItemByItemId(itemId);
        if (item != null) {
            final Connection connection = item.getConnection();
            if (connection != null) {
                final EList tables = connection.getTables();
                if (tables != null) {
                    return tables;
                }
            }
        }
        return null;
    }

    public static List<MetadataTable> getMetadataTablesFromItem(Item item) {
        if (item == null) {
            return null;
        }
        if (item instanceof ConnectionItem) {
            final Connection connection = ((ConnectionItem) item).getConnection();
            if (connection != null) {
                final EList tables = connection.getTables();
                if (tables != null) {
                    return tables;
                }
            }
        }
        return null;
    }

}
